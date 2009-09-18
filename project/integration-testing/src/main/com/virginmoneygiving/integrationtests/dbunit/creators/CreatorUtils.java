package com.virginmoneygiving.integrationtests.dbunit.creators;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;

import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;

public class CreatorUtils {

    private static Logger LOGGER = Logger.getLogger(CreatorUtils.class);
    
    public static void main(String[] args) {
        try {
            Integer id = CreatorUtils.insertSecureData();
            System.out.println("id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Inserts a row of data into secure-data.
     *
     * The row inserted represents the password "Pa55word!" and will be taken 
     * from common/secure-data-dataset.xml with [id] set to an appropriate value.
     *
     * @return Integer the SI_SEQ value of the inserted row.
     * @throws Exception if anything SQL goes wrong.
     */
    public static Integer insertSecureData() throws Exception {
        
        DbUnitHelper secureDataDbUnitHelper = null;
        try {
            /*
             * Connect to the secure_data database
             */
            secureDataDbUnitHelper = 
                new DbUnitHelper(
                    DbUnitHelper.SECURE_DATA_DB_URL, 
                    DbUnitHelper.SECURE_DATA_DB_USERNAME, 
                    DbUnitHelper.SECURE_DATA_DB_PASSWORD, 
                    DbUnitHelper.SECURE_DATA_DB_SCHEMA,
                    "unused",
                    "/com/virginmoneygiving/integrationtests/dbunit/creators/common/secure-data-dataset.xml");
            secureDataDbUnitHelper.setSetupDatabaseOperation(DatabaseOperation.NONE);
            secureDataDbUnitHelper.onSetUp();
            IDatabaseConnection connection = secureDataDbUnitHelper.getDatabaseTester().getConnection();

            /*
             * Make sure we use the correct handlers. (Note this is also set in
             * DbUnitHelper.onSetup(), but using the NONE operation seems to
             * unset it).
             */
            DatabaseConfig config = connection.getConfig();
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
            config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());

            /*
             * Get the next auto-increment value for the secure_data table.
             */
            QueryDataSet dataSet = new QueryDataSet(connection);
            dataSet.addTable("AUTO1", 
                "select auto_increment from information_schema.tables where table_schema = 'secure_data' and table_name = 'secure_information'");
            ITable auto1 = dataSet.getTable("AUTO1");
            Integer nextAutoIncrement = Integer.parseInt(auto1.getValue(0, "auto_increment").toString());
            LOGGER.debug("auto_increment = " + nextAutoIncrement);
    
            /*
             * Open the dataset to insert, and token replace the [id] string with the auto-increment value.
             * Note that using "select last_insert_id()" after the insert seems not to 
             * work over the dbunit connection. Not sure why.
             */
            ReplacementDataSet replacementInitialDataset = 
                (ReplacementDataSet) secureDataDbUnitHelper.getDatabaseTester().getDataSet();
            replacementInitialDataset.addReplacementObject("[id]", nextAutoIncrement);
            
            /*
             * Insert the record and return id
             */
            DatabaseOperation.INSERT.execute(connection, replacementInitialDataset);
            return nextAutoIncrement;
        }
        finally {
            /*
             * Tidy up connections
             */
            if ( secureDataDbUnitHelper != null ) {
                secureDataDbUnitHelper.onTeardown();
            }
        }
        
    }

    /**
     * Add users to security database.
     *
     * The supplied passwordId will be used to replace [id] tokens in the 
     * supplied dataset. By using the {@linkplain #insertSecureData} method before
     * this one it's possible to insert all types of users configured to use a 
     * known password.
     * 
     * @param passwordId the id of the entry in secure-data to use as the password.
     * @param datasetFileName the path to the dataset. Opened as a resource, so needs to be
     *              on the classpath
     * @throws Exception
     */
    public static void insertSecurityInfo(Integer passwordId, String... datasetFileNames) throws Exception {
        
        /*
         * Connect to db and put into known state. 
         */
        DbUnitHelper securityDbUnitHelper = null;
        try {
            
            securityDbUnitHelper = new DbUnitHelper(
                DbUnitHelper.SECURITY_DB_URL, 
                DbUnitHelper.SECURITY_DB_USERNAME, 
                DbUnitHelper.SECURITY_DB_PASSWORD, 
                DbUnitHelper.SECURITY_DB_SCHEMA,
                "unused",
                datasetFileNames);
            securityDbUnitHelper.getObjectMap().put("[id]", passwordId);
            securityDbUnitHelper.onSetUp();
        }
        finally {
            if ( securityDbUnitHelper != null ) {
                securityDbUnitHelper.onTeardown();
            }
        }
    }
}
