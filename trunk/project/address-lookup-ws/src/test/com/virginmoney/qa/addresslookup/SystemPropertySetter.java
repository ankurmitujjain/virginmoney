package com.virginmoney.qa.addresslookup;

import fit.ColumnFixture;
import org.apache.log4j.Logger;

/**
 * *****************************************************************************
 * Class         :  SpringBuilder
 * Functionality :  Utility class to set a system property
 * Author(s)     :  woodsn
 * Creation Date :  22-Nov-2007
 * Copyright     :  Virgin Money Ltd.
 * ******************************************************************************
 */

public class SystemPropertySetter extends ColumnFixture {
    Logger logger = Logger.getLogger(SystemPropertySetter.class);

    public String propertyName = null;
    public String propertyValue = null;
    public String propertyValueAfterSet = null;

    public boolean set() {

        // if the value is already set, do NOT overwrite it because it means its
        // been set by some process prior to fitnesse
        propertyValueAfterSet = System.getProperty(propertyName);
        if (propertyValueAfterSet != null && !propertyValueAfterSet.equals(propertyValue)) {
            return false;
        } else {

            System.setProperty(propertyName, propertyValue);
            propertyValueAfterSet = System.getProperty(propertyName);
        }
        return propertyValueAfterSet.equals(propertyValue);
    }

}
