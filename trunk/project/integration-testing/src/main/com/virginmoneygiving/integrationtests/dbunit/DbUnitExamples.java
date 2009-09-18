package com.virginmoneygiving.integrationtests.dbunit;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Before;
import org.junit.Test;

/**
 * Also tests the database has been correctly populated using dbunit.
 *
 * Note that Mysql is semi-case-sensitive. Use the correct case for table-names to avoid
 * mystery AmbiguousTableNameExceptions
 * @author ipriest
 * 
 */
public class DbUnitExamples {

    @Before
    public void setUp() throws Exception {
        
    }

    /**
     * Method to demonstrate how to generate an XML flat file from an existing database. Handy for
     * setting up initial-dataset files.
     * 
     * @throws Exception
     */
    @Test
    public void fullDatabaseExportSample() throws Exception {

        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/payment" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "payment" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "payment" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "payment" );

        // database connection
        Class.forName(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS));
        Connection jdbcConnection = DriverManager.getConnection(
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD));

        IDatabaseConnection connection = 
            new DatabaseConnection(jdbcConnection);
        
        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("payment-full.xml"));

        connection.close();

        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/giving" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "giving" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "giving" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "giving" );

        // database connection
        Class.forName(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS));
        jdbcConnection = DriverManager.getConnection(
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD));

        connection = 
            new DatabaseConnection(jdbcConnection);
        
        // full database export
        fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("giving-full.xml"));

        connection.close();

        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/security" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "security" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "security" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "security" );

        // database connection
        Class.forName(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS));
        jdbcConnection = DriverManager.getConnection(
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD));

        connection = 
            new DatabaseConnection(jdbcConnection);
        
        // full database export
        fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("security-full.xml"));

        connection.close();

        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://gretna-dev.opsera.com:3306/secure_data" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "letmein" ); 
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "secure_data" );

        // database connection
        Class.forName(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS));
        jdbcConnection = DriverManager.getConnection(
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD));

        connection = 
            new DatabaseConnection(jdbcConnection);
        
        // full database export
        fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("secure-data-full.xml"));

        connection.close();
    }

    public void partialDatabaseExportSample() throws Exception {

        // database connection
        Class.forName(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS));
        Connection jdbcConnection = DriverManager.getConnection(
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD));

        IDatabaseConnection connection = 
            new DatabaseConnection(jdbcConnection);
        
        // partial database export - some rows and all rows of specific tables
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable("charity_status", "SELECT * FROM charity_status WHERE CODE='LIV'");
        partialDataSet.addTable("fundraiser_status");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

        connection.close();
    }

    public void dependentDatabaseExportSample() throws Exception {

        // database connection
        Class.forName(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS));
        Connection jdbcConnection = DriverManager.getConnection(
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD));

        IDatabaseConnection connection = 
            new DatabaseConnection(jdbcConnection);
        
        // dependent tables database export: export table X and all tables
        // that have a PK which is a FK on X, in the right order for insertion
        String[] depTableNames = 
            TablesDependencyHelper.getAllDependentTables( connection, "charity" );
        IDataSet depDataSet = connection.createDataSet( depTableNames );
        FlatXmlDataSet.write(depDataSet, new FileOutputStream("dependents.xml"));          
            
        connection.close();
    }

    /**
     * Method that shows how to create a dtd for the datbase.
     *
     * Have yet to find a use for the dtd.
     *
     * @throws Exception
     */
    public void generateDtd() throws Exception {
        Class.forName(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS));
        Connection jdbcConnection = DriverManager.getConnection(
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME), 
            System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD));

        IDatabaseConnection connection = 
            new DatabaseConnection(jdbcConnection);

        // write DTD file
        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("test.dtd"));
        
        connection.close();
    }
    
}
