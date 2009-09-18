package com.virginmoneygiving.integrationtests.dbunit;

/**
 * Help out with Giving schema related DbUnit stuff.
 *
 * @author vishals
 */
public class GivingDbUnitHelper extends DbUnitHelper {   

    /**
     * Default constructor.
     */
    public GivingDbUnitHelper(String expectedDatasetName, String... initialDatasetNames ) {
        super(DbUnitHelper.GIVING_DB_URL,
        	  DbUnitHelper.GIVING_DB_USERNAME,
        	  DbUnitHelper.GIVING_DB_PASSWORD,
              DbUnitHelper.GIVING_DB_SCHEMA,
              expectedDatasetName,
              initialDatasetNames);
    }
}
