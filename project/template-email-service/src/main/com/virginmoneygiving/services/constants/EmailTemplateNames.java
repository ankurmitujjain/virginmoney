package com.virginmoneygiving.services.constants;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This files loads all the email related property into a static property object.
 * 
 * @author esakkiy
 */
public class EmailTemplateNames {
    
    /** The email template names. */
    static Properties emailTemplateNames = null;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(EmailTemplateNames.class);

    /**
     * Load data.
     */
    private static void loadData() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("EmailTemplateNames::loadData() - START");
        }

        FileInputStream fis = null;
        if (emailTemplateNames == null) {
            emailTemplateNames = new Properties();
            try {
                fis = new FileInputStream(new File(System.getProperty("GIVING_CONF_HOME") + "/templates/emailTemplates.properties"));
                emailTemplateNames.load(fis);
            } catch (FileNotFoundException e) {
                LOGGER.error("Couldn't locate emailTemplates.properties file on the file system.",e);
                throw new RuntimeException("Couldn't locate emailTemplates.properties file on the file system.",e);
            } catch (IOException e) {
                LOGGER.error("Error occured while loading emailTemplates.properties file !!!! Due to this Emails will not be sent from the application.", e);
                throw new RuntimeException("Error occured while loading emailTemplates.properties file !!!! Due to this Emails will not be sent from the application.", e);
            } finally {
            	safeClose(fis);
            }
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("EmailTemplateNames::loadData() - END");
        }
    }
    
    /**
     * Safe close.
     * 
     * @param obj the obj
     */
    public static void safeClose(Object obj) {
		if (obj != null) {
			try {
	            if (obj instanceof FileInputStream) {
			    	 FileInputStream in = (FileInputStream) obj;
			    	 in.close();
				} else if (obj instanceof FileOutputStream) {
					FileOutputStream out = (FileOutputStream) obj;
				      out.close();
				} else if (obj instanceof BufferedInputStream) {
					BufferedInputStream inBuffer = (BufferedInputStream) obj;
					inBuffer.close();
				} else if (obj instanceof BufferedOutputStream) {
					 BufferedOutputStream outBuffer = (BufferedOutputStream) obj;
					 outBuffer.close();
				}
			} catch (IOException e) {
				LOGGER.error("Error while closing emailTemplates.properties file reference. POSSIBLE CANDIDATE FOR MEMORY LEAKAGE.!!!!!!!!", e);
                throw new RuntimeException("Error while closing emailTemplates.properties file reference. POSSIBLE CANDIDATE FOR MEMORY LEAKAGE.!!!!!!!!", e);
			}
		}
	}

    /**
     * Gets the template name.
     * 
     * @param key the key
     * 
     * @return the template name
     */
    public static String getTemplateName(String key) {
        if (emailTemplateNames == null) {
            loadData();
        }
        return emailTemplateNames.getProperty(key);
    }

}
