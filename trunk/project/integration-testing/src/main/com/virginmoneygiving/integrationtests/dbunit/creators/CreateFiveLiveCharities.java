package com.virginmoneygiving.integrationtests.dbunit.creators;

import org.apache.log4j.Logger;

import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;

public class CreateFiveLiveCharities {

    private static Logger LOGGER = Logger.getLogger(CreateFiveLiveCharities.class);
    
    /**
     * Create 5 charities in the live state.
     * 
     * Data taken from the flat XML files.
     * 
     */
    public static void createFiveLiveCharites() throws Exception {
    
        Integer passwordId = CreatorUtils.insertSecureData();
        CreatorUtils.insertSecurityInfo(
            passwordId,
            "/com/virginmoneygiving/integrationtests/dbunit/creators/common/five-charity-admins-security-dataset.xml");
        
        insertGivingData();
    }        
    
    public static void insertGivingData() throws Exception {
        DbUnitHelper givingDbUnitHelper = null;
        try {
            givingDbUnitHelper = new DbUnitHelper(
                DbUnitHelper.GIVING_DB_URL, 
                DbUnitHelper.GIVING_DB_USERNAME, 
                DbUnitHelper.GIVING_DB_PASSWORD, 
                DbUnitHelper.GIVING_DB_SCHEMA,
                "unused",
                "/com/virginmoneygiving/integrationtests/dbunit/creators/common/five-charities-dataset.xml");
            givingDbUnitHelper.getObjectMap().put("[status]", "LIV");
            givingDbUnitHelper.onSetUp();
        }
        finally {
            if ( givingDbUnitHelper != null ) {
                givingDbUnitHelper.onTeardown();
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            CreateFiveLiveCharities.createFiveLiveCharites();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
