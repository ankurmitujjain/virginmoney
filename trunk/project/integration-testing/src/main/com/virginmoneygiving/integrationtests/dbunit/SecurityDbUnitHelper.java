package com.virginmoneygiving.integrationtests.dbunit;

/**
 * Help out with Giving schema related DbUnit stuff.
 *
 * @author vishals
 */
public class SecurityDbUnitHelper extends DbUnitHelper {   

    /**
     * Default constructor.
     */
    public SecurityDbUnitHelper(String expectedDatasetName, String... initialDatasetNames ) {
        super(DbUnitHelper.SECURITY_DB_URL,
        	  DbUnitHelper.SECURITY_DB_USERNAME,
        	  DbUnitHelper.SECURITY_DB_PASSWORD,
              DbUnitHelper.SECURITY_DB_SCHEMA,
              expectedDatasetName,
              initialDatasetNames);
    }
}
