package com.virginmoneygiving.services.constants;

import java.util.Properties;

/**
 * Workaround package visibility so we can test...
 * @author Robin Bramley, Opsera Ltd.
 *
 */
public class EmailTemplateNamesHelper {
    
    public static void fixEmailTemplateNames(Properties props) { 
        EmailTemplateNames.emailTemplateNames = props;
    }
}
