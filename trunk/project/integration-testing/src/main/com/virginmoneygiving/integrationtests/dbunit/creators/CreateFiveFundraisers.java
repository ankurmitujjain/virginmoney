package com.virginmoneygiving.integrationtests.dbunit.creators;

import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;

public class CreateFiveFundraisers {

    /**
     * Allow this class to run itself.
     * @param args unused.
     */
    public static void main(String[] args) {
        try {
            CreateFiveFundraisers.createFiveFundraisers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create 5 Fundraisers without pages.
     *
     * Creates combined dataset with charities to ensure that fundraisers 
     * have something to raise money for.
     */
    public static void createFiveFundraisers() throws Exception {

        Integer passwordId = CreatorUtils.insertSecureData();
        CreatorUtils.insertSecurityInfo(
            passwordId,
            "/com/virginmoneygiving/integrationtests/dbunit/creators/common/five-charity-admins-security-dataset.xml",
            "/com/virginmoneygiving/integrationtests/dbunit/creators/five-fundraisers-security-dataset.xml");
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
                "/com/virginmoneygiving/integrationtests/dbunit/creators/common/five-charities-dataset.xml",
                "/com/virginmoneygiving/integrationtests/dbunit/creators/five-fundraisers-dataset.xml");
            givingDbUnitHelper.getObjectMap().put("[status]", "LIV");
            givingDbUnitHelper.onSetUp();
        }
        finally {
            if ( givingDbUnitHelper != null ) {
                givingDbUnitHelper.onTeardown();
            }
        }
    }
}
