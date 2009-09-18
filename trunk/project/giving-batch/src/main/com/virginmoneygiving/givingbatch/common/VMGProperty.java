package com.virginmoneygiving.givingbatch.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class loads all the email related property into a static property object.
 * 
 * @author esakkiy
 */
public class VMGProperty {
    
    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(VMGProperty.class);

    /** Properties. */
    static Properties propertyObject = null;


    /**
     * Load data.
     */
    private static void loadData() {
        FileInputStream fis = null;
        if (propertyObject == null) {
            propertyObject = new Properties();
            try {
            	fis = new FileInputStream(new File(System.getProperty("GIVING_CONF_HOME")+"/templates/email.properties"));
                propertyObject.load(fis);
            } catch(FileNotFoundException e){
                LOGGER.error("Thrown FileNotFoundException in VMGProperty:loadData ", e);
            } catch (IOException e) {
                LOGGER.error("Thrown IOException in VMGProperty:loadData ", e);
            } finally {
                try {
                    if (fis !=null) {
                        fis.close();
                        }
                } catch (Exception e) {
                    LOGGER.error("VMGProperty : copy() : loadData closing stream.", e);
                }
            }
        }

    }

    /**
     * Set a Properties value.
     * 
     * @param key String key
     * @param value String value
     */
    public static void setProperty(String key, String value) {
        if (propertyObject == null) {
            loadData();
        }
        propertyObject.setProperty(key, value);
    }

    /**
     * Get a Properties value.
     * 
     * @param key the key to look up
     * 
     * @return the value, or null.
     */
    public static String getProperty(String key) {
        if (propertyObject == null) {
            loadData();
        }
        return propertyObject.getProperty(key);
    }

}
