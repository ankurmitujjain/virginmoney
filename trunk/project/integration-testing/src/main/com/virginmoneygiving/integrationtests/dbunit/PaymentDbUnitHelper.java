package com.virginmoneygiving.integrationtests.dbunit;

/**
 * Help out with Giving schema related DbUnit stuff.
 *
 * @author vishals
 */
public class PaymentDbUnitHelper extends DbUnitHelper {   

    /**
     * Default constructor.
     */
    public PaymentDbUnitHelper(String expectedDatasetName, String... initialDatasetNames ) {
        super(DbUnitHelper.PAYMENT_DB_URL,
        	  DbUnitHelper.PAYMENT_DB_USERNAME,
        	  DbUnitHelper.PAYMENT_DB_PASSWORD,
              DbUnitHelper.PAYMENT_DB_SCHEMA,
              expectedDatasetName,
              initialDatasetNames);
    }
}
