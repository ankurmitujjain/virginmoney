package com.virginmoneygiving.integrationtests.dbunit;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;

/**
 * Help out with DbUnit stuff.
 *
 * @author ian.priest@opsera.com
 */
public class DbUnitHelper {
    
	// TODO : Externalise DB properties.
    public static String GIVING_DB_URL = System.getProperty("givingdb.ds.url", "jdbc:mysql://localhost:3306/giving");
    public static String GIVING_DB_USERNAME = System.getProperty("givingdb.ds.username", "giving");
    public static String GIVING_DB_PASSWORD = System.getProperty("givingdb.ds.password", "giving");
    public static String GIVING_DB_SCHEMA = System.getProperty("givingdb.ds.dbname", "giving");

    public static String PAYMENT_DB_URL = System.getProperty("paymentdb.ds.url", "jdbc:mysql://localhost:3306/payment");
    public static String PAYMENT_DB_USERNAME = System.getProperty("paymentdb.ds.username", "payment");
    public static String PAYMENT_DB_PASSWORD = System.getProperty("paymentdb.ds.password", "payment");
    public static String PAYMENT_DB_SCHEMA = System.getProperty("paymentdb.ds.dbname", "payment");

    public static String SECURITY_DB_URL = System.getProperty("securitydb.ds.url", "jdbc:mysql://localhost:3306/security");
    public static String SECURITY_DB_USERNAME = System.getProperty("securitydb.ds.username", "security");
    public static String SECURITY_DB_PASSWORD = System.getProperty("securitydb.ds.password", "security");
    public static String SECURITY_DB_SCHEMA = System.getProperty("securitydb.ds.dbname", "security");

    public static String SECURE_DATA_DB_URL = System.getProperty("securedatadb.ds.url", "jdbc:mysql://gretna-dev.opsera.com:3306/secure_data");
    public static String SECURE_DATA_DB_USERNAME = System.getProperty("securedatadb.ds.username", "root");
    public static String SECURE_DATA_DB_PASSWORD = System.getProperty("securedatadb.ds.password", "letmein");
    public static String SECURE_DATA_DB_SCHEMA = System.getProperty("securedatadb.ds.dbname", "secure_data");

    /** Default to "com.mysql.jdbc.Driver", but protected so sub-classes can change if they want to. */
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    /** The database tester. */
    private JdbcDatabaseTester databaseTester; 
    /** The initial-datasets. Will be combined into one dataset. */
    private String[] initialDatasetNames;
    /** The expected dataset. */
    private String expectedDatasetName;
    /** The connectionUrl. */
    private String connectionUrl;
    /** The username. */
    private String username;
    /** The password. */
    private String password;
    /** The schema. */
    private String schema;
    /** The setup database operation. Default CLEAN_INSERT. */
    private DatabaseOperation setupDatabaseOperation = DatabaseOperation.CLEAN_INSERT;
    /** Map of object replacements in initial dataset. */
    private Map<String, Object> objectMap = new HashMap<String, Object>();
    /** Map of substring replacements in initial dataset. */
    private Map<String, String> substringMap = new HashMap<String, String>();

    /**
     * Constructor.
     * 
     * The initial datasets will be combined into a single dataset and then used to put the
     * target database into a known state.
     * Before being combined, each dataset will be opened as a replacement dataset so that
     * token replacement can be performed.
     *
     * @param connectionUrl to database
     * @param username to login
     * @param password to login
     * @param schema to use
     * @param expectedDatasetName filename of post-update dataset
     * @param initialDatasetNames filenames of initial dataset. Will be combined into a single dataset.
     */
    public DbUnitHelper(String connectionUrl, String username, String password, String schema, String expectedDatasetName, String... initialDatasetNames ) {
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
        this.schema = schema;
        this.initialDatasetNames = initialDatasetNames;
        this.expectedDatasetName = expectedDatasetName;
    }
    
    /**
     * @return the databaseTester
     */
    public JdbcDatabaseTester getDatabaseTester() {
        return databaseTester;
    }

    /**
     * The file-names of the initial dataset. 
     *
     * Files should be DbUnit flat XML datasets. They will be opened using 
     * {@link java.lang.Class#getResourceAsStream(String)} so the 
     * names must obey rules for that method.
     * The supplied datasets will be combined by the use of a 
     * {@link CompositeDataSet}.
     * The ultimate dataset used is a ReplacementDataSet, (wrapping the 
     * CompositeDataSet) so file can contain tokens 
     * to be replaced. Set {@link setObjectMap} to define the replacements.
     * @return the initialDatasetNames
     */
    public String[] getInitialDatasetNames() {
        return initialDatasetNames;
    }

    /**
     * The file-name of the expected dataset. 
     *
     * File should be a DbUnit flat XML dataset. It will be opened using 
     * {@link java.lang.Class#getResourceAsStream(String)} so the 
     * name must obey rules for that method. 
     * @return the expectedDatasetName
     */
    public String getExpectedDatasetName() {
        return expectedDatasetName;
    }

    /**
     * Call from unit-test setup method.
     * 
     * Connect to a database and put it in a known state.
     * Runs a CLEAN_INSERT action, described in the DbUnit javadocs as this:
     * 
     * <quote>This composite operation performs a DELETE_ALL  operation followed by an INSERT  operation. This 
     * is the safest approach to ensure that the database is in a known state. This is appropriate for 
     * tests that require the database to only contain a specific set of data.</quote>
     * 
     * The DELETE_ALL operation is described as:
     * <quote>Deletes all rows of tables present in the specified dataset. If the dataset
     * does not contains a particular table, but that table exists in the database,
     * the database table is not affected. <b>Table are truncated in
     * reverse sequence.</b></quote>
     * 
     * The INSERT operation is described as:
     * <quote>Inserts the dataset contents into the database. This operation assumes that
     * table data does not exist in the database and fails if this is not the case.
     * To prevent problems with foreign keys, tables must be sequenced appropriately
     * in dataset.</quote>
     * 
     * The helper will read initialDatasetNames and expectedDatasetName from flat-xml files. The file is
     * opened as a resource so must be in the classpath.
     * 
     * Will connect to a MySQL database using a jdbc connection using the (hopefully obvious) properties
     * provided.
     * 
     * <ul>
     * <li>
     * @throws Exception
     */
    public void onSetUp() throws Exception {

        /*
         * Set up dbunit tester and configure for MySQL
         */
        databaseTester = new JdbcDatabaseTester(DRIVER_CLASS, 
            this.connectionUrl, 
            this.username, 
            this.password, 
            this.schema);
        IDatabaseConnection connection = databaseTester.getConnection();
        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());

        /*
         * Get the initial datasets and set the initial action.
         * Note that we use a ReplacementDataSet, so callers can do symbol
         * replacement by setting the objectMap and substringMap. Leaving them
         * null is safe if no replacements are needed.
         * Multiple datasets are combined into one.
         */
        List<IDataSet> initialDatasets = new ArrayList<IDataSet>(); 
        for (String datasetName : getInitialDatasetNames()) {
            InputStream isInitialDataset = getClass().getResourceAsStream(datasetName);
            assertNotNull(isInitialDataset);
            IDataSet initialDataset = new FlatXmlDataSet(isInitialDataset);
            initialDatasets.add(initialDataset);
        }
        IDataSet combinedInitialDatasets = new CompositeDataSet(initialDatasets.toArray(new IDataSet[0]));
        ReplacementDataSet replacementInitialDataset = new ReplacementDataSet(combinedInitialDatasets, objectMap, substringMap);
        databaseTester.setDataSet(replacementInitialDataset);
        databaseTester.setSetUpOperation(getSetupDatabaseOperation());
        
        /*
         * Perform set-up. DbUnit bug here. If we call databaseTester.onSetup()
         * it doesn't use the mysql factories and we get all sorts of incorrect
         * "column doesn't exist" errors. 
         */
        //databaseTester.onSetup();
        getSetupDatabaseOperation().execute(connection, replacementInitialDataset);
    }

    /**
     * Close connection and so forth.
     *
     * Call from unit-test tear-down method.
     */
    public void onTeardown() throws Exception {
        if ( databaseTester != null ) {
            databaseTester.onTearDown();
        }        
    }
    
    /**
     * Ensure the database matches the expected dataset.
     * 
     * @throws Exception
     */
    public void checkExpected() throws Exception {

        /*
         * Confirm the database is in the expected state
         */
        
        InputStream isExpectedDataset = getClass().getResourceAsStream(getExpectedDatasetName());
        IDataSet expectedDataset = new FlatXmlDataSet(isExpectedDataset);
    
        //Get connection to actual dbase
        IDatabaseConnection connection = databaseTester.getConnection();
        
        // Do a table-by-table expected->actual comparison.
        // Use a FilteredTable to only compare columns that are declared in the expected dataset.
        // Use a SortedTable to ensure the rows are in the same order in expected and actual
        String[] tableNames = expectedDataset.getTableNames();
        for (int i = 0; i < tableNames.length; i++) {

            // Get the expected table
            ITable expectedTable = expectedDataset.getTable(tableNames[i]);
            Column[] compareColumns = expectedTable.getTableMetaData().getColumns();
            
            // Get the actual table, with col filtered to match those in the expected table
            ITable databaseTable = connection.createTable(tableNames[i]);
            ITable filteredDatabaseTable = 
                DefaultColumnFilter.includedColumnsTable(databaseTable, compareColumns);
    
            // Sort both tables to the same order
            ITable sortedExpectedTable = new SortedTable(expectedTable);
            ITable sortedFilteredDatabaseTable = new SortedTable(filteredDatabaseTable, expectedTable.getTableMetaData());
            
            // Check they match
            Assertion.assertEquals(sortedExpectedTable, sortedFilteredDatabaseTable);
        }
    }

    /**
     * @return the setupDatabaseOperation
     */
    public DatabaseOperation getSetupDatabaseOperation() {
        return setupDatabaseOperation;
    }

    /**
     * @param setupDatabaseOperation the setupDatabaseOperation to set
     */
    public void setSetupDatabaseOperation(DatabaseOperation setupDatabaseOperation) {
        this.setupDatabaseOperation = setupDatabaseOperation;
    }

    /**
     * @return the objectMap
     */
    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    /**
     * @return the substringMap
     */
    public Map<String, String> getSubstringMap() {
        return substringMap;
    }

}
