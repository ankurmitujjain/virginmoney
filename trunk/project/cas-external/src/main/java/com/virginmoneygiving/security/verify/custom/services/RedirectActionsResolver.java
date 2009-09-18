package com.virginmoneygiving.security.verify.custom.services;

import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Class to resolve the redirect actions.
 * 
 * Created by IntelliJ IDEA.
 * User: choprah
 * Date: 11-Feb-2009
 * Time: 09:49:42
 * To change this template use File | Settings | File Templates.
 */
public final class RedirectActionsResolver {
    
    /** List of properties read from the Redirect properties file. */
    private Properties defaultRedirectProperties;
    
    /** The action resolver. */
    private static RedirectActionsResolver actionResolver;
    
    /** The LOGGER. */
    private static Logger LOGGER = Logger.getLogger(RedirectActionsResolver.class);

    /**
     * Instantiates a new redirect actions resolver.
     * 
     * @param defaultRedirectProperties the default redirect properties
     */
    private RedirectActionsResolver(Properties defaultRedirectProperties) {
        this.defaultRedirectProperties = defaultRedirectProperties;
    }

    /**
     * Creates the instance.
     * 
     * @param redirectproperties the redirectproperties
     * 
     * @return the redirect actions resolver
     */
    public static RedirectActionsResolver createInstance (Properties redirectproperties) {
    	if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("createInstance(Properties) - START");
		}
        actionResolver = new RedirectActionsResolver (redirectproperties);

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("createInstance(Properties) - END");
		}
		return actionResolver;
    }

    /**
     * Gets the single instance of RedirectActionsResolver.
     * 
     * @return single instance of RedirectActionsResolver
     */
    public static RedirectActionsResolver getInstance() {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getInstance() -  START");
		}

		if (actionResolver == null) {
            createInstance(null);
        }

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getInstance() - END");
		}
		return actionResolver;
    }
    
    /**
     * Gets the redirect property.
     * 
     * @param propertyType the property type
     * 
     * @return the redirect property
     */
    public String getRedirectProperty(String propertyType) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getRedirectProperty() -  START");
		}

		String propValue = "";
        if (this.defaultRedirectProperties != null) {
            propValue = this.defaultRedirectProperties.getProperty(propertyType);
        }

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getRedirectProperty(String) - END");
		}
		return propValue;
    }
}
