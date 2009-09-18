package com.virginmoneygiving.givingbatch.serviceproxy.impl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.virginmoneygiving.givingbatch.exception.ServiceException;

/**
 * Factory for building web service clients for service classes.
 * 
 * @author Srinivas Nageli
 */
public class AbstractGivingBatchServiceLocator {
    
    /** creating logger instance. */
    private static final Logger LOGGER =
            Logger.getLogger(AbstractGivingBatchServiceLocator.class);
    
    /** name of a properties file containing service information. */
    private static final String SERVICE_PROPERTIES_FILE =
            "web-service-clients.properties";
    
    /** suffix added to service short name and read from SERVICE_PROPERTIES_FILE. */
    private static final String SERVICE_CLASSNAME_SUFFIX = "classname";
    
    /** suffix added to service short name and read from SERVICE_PROPERTIES_FILE. */
    private static final String SERVICE_NAMESPACE_SUFFIX = "namespace";
    
    /** suffix added to service short name and read from SERVICE_PROPERTIES_FILE. */
    private static final String SERVICE_NAME_SUFFIX = "name";
    
    /** suffix added to service short name and read from SERVICE_PROPERTIES_FILE. */
    private static final String SERVICE_URL_PROPERTY_SUFFIX =
            "url.system.property";

    /** List of properties read from SERVICE_PROPERTIES_FILE. */
    private static Properties serviceProperties;

    /** Keep track of loading the service properties explicitly. */
    private boolean servicePropertiesLoaded = false;
    
    /** Accessing URL from out side of the application thru spring injection. */
    private String webServiceURL;

    /**
     * Gets the web service url.
     * 
     * @return the webServiceURL
     */
    public String getWebServiceURL() {
        return webServiceURL;
    }

    /**
     * Creates an instance of a Service referring to a deployed web service.
     * 
     * @param serviceShortName of type String service used in the service properties file.
     * 
     * @return deployed web service
     * 
     * @throws Exception if webservice is not getting properly
     */
    protected Service getWebService(final String serviceShortName)
            {
        // Create an instance of the Service class required
        Service webService = null;

        LOGGER.trace("getting web service for  " + serviceShortName);
        try {
        final String serviceClassName =
                getServiceProperty(serviceShortName, SERVICE_CLASSNAME_SUFFIX);
        final String serviceName =
                getServiceProperty(serviceShortName, SERVICE_NAME_SUFFIX);

        final String serviceNameSpace =
                getServiceProperty(serviceShortName, SERVICE_NAMESPACE_SUFFIX);

        if (serviceClassName == null || serviceClassName.equals("")) {
            if(LOGGER.isTraceEnabled()){
                LOGGER.trace("checking service class name equals or empty");
            }
            // TODO throw new ServiceLocationException(logger,
            // "System property named " + SERVICE_CLASSNAME_SUFFIX +
            // " for service name=" + serviceShortName +
            // " not set, cannot locate service");
        }
        LOGGER.debug("serviceShortName        =" + serviceShortName);
        LOGGER.debug("serviceClassName        =" + serviceClassName);
        LOGGER.debug("serviceName             =" + serviceName);
        LOGGER.debug("serviceURLPropertyName  =" + getWebServiceURL());
        LOGGER.debug("serviceNameSpace        =" + serviceNameSpace);

        // get the System property which contains the full url of the running
        // service.
        // The url should be in the form
        // http://<hostname>:<port>/<service-webapp>/<service-name>?wsdl

        if (webServiceURL == null || webServiceURL.equals("")) {
            LOGGER
                    .error("serviceURLPropertyValue = **ERROR** System property named "
                            + webServiceURL
                            + " for service name="
                            + serviceShortName
                            + " not set, cannot locate service");
            /*
             * throw new ServiceLocationException(logger,
             * "System property named " + serviceURLPropertyName +
             * " for service name=" + serviceShortName +
             * " not set, cannot locate service");
             */
        }
        LOGGER.debug("webServiceURL =" + webServiceURL);
        // creating instance of URL passing serviceURLPropert Value
        URL serviceUrl = new URL(getWebServiceURL());

        LOGGER.debug("Constructing QName(\"" + serviceNameSpace + "\",\""
                + serviceName + "\"");
        QName serviceQName = new QName(serviceNameSpace, serviceName);
        // Use the service class, url from the system property, the service
        // name and namespace to construct
        // the arguments required by the Service constructor
        // use Reflection to get a constructor for the Service class and a
        // new instance.
        Class serviceClass = Class.forName(serviceClassName);
        Constructor serviceConstructor =
                serviceClass.getConstructor(URL.class, QName.class);
        LOGGER.debug("Constructing service from URL=" + serviceUrl + "  QName="
                + serviceQName);
        webService =
                (Service) serviceConstructor.newInstance(serviceUrl,
                        serviceQName);
        }
        catch (MalformedURLException malformedUrlException) {
            LOGGER.error("Thrown MalformedURLException in " +
                    "AbstractWebServiceLocator.getWebService for service::"
                            + serviceShortName);              
            LOGGER.error("Thrown MalformedURLException in AbstractWebServiceLocator.getWebService"
                            + malformedUrlException.getMessage(), malformedUrlException);
            throw new ServiceException(
                    "Thrown ServiceException in AbstractWebServiceLocator.getWebService"
                            + malformedUrlException.getMessage(), malformedUrlException);
        }
        catch (InvocationTargetException invocationTargetException) {
            LOGGER.error("Thrown InvocationTargetException in " +
                    "AbstractWebServiceLocator.getWebService for service::"
                            + serviceShortName);              
            LOGGER.error("Thrown InvocationTargetException in AbstractWebServiceLocator.getWebService"
                            + invocationTargetException.getMessage(), invocationTargetException);
            throw new ServiceException(
                    "Thrown ServiceException in AbstractWebServiceLocator.getWebService"
                            + invocationTargetException.getMessage(), invocationTargetException);
        }
        catch (NoSuchMethodException noSuchMethodException) {
            LOGGER.error("Thrown NoSuchMethodException in " +
                    "AbstractWebServiceLocator.getWebService for service::"
                            + serviceShortName);              
            LOGGER.error("Thrown NoSuchMethodException in AbstractWebServiceLocator.getWebService"
                            + noSuchMethodException.getMessage(), noSuchMethodException);
            throw new ServiceException(
                    "Thrown ServiceException in AbstractWebServiceLocator.getWebService"
                            + noSuchMethodException.getMessage(), noSuchMethodException);
        }
        catch (IllegalAccessException illegalAccessException) {
            LOGGER.error("Thrown IllegalAccessException in " +
                    "AbstractWebServiceLocator.getWebService for service::"
                            + serviceShortName);              
            LOGGER.error("Thrown IllegalAccessException in AbstractWebServiceLocator.getWebService"
                            + illegalAccessException.getMessage(), illegalAccessException);
            throw new ServiceException(
                    "Thrown ServiceException in AbstractWebServiceLocator.getWebService"
                            + illegalAccessException.getMessage(), illegalAccessException);
        }
        catch (InstantiationException instantiationException) {
            LOGGER.error("Thrown InstantiationException in " +
                    "AbstractWebServiceLocator.getWebService for service::"
                            + serviceShortName);              
            LOGGER.error("Thrown InstantiationException in AbstractWebServiceLocator.getWebService"
                            + instantiationException.getMessage(), instantiationException);
            throw new ServiceException(
                    "Thrown ServiceException in AbstractWebServiceLocator.getWebService"
                            + instantiationException.getMessage(), instantiationException);
        }
        catch (ClassNotFoundException classNotFoundException) {
            LOGGER.error("Thrown ClassNotFoundException in " +
                    "AbstractWebServiceLocator.getWebService for service::"
                            + serviceShortName);              
            LOGGER.error("Thrown ClassNotFoundException in AbstractWebServiceLocator.getWebService"
                            + classNotFoundException.getMessage(), classNotFoundException);
            throw new ServiceException(
                    "Thrown ServiceException in AbstractWebServiceLocator.getWebService"
                            + classNotFoundException.getMessage(), classNotFoundException);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("AbstractWebServiceLocator :: getWebService(String) -method - END");
        }
        return webService;

    }

    /**
     * Load service location properties from the properties file (that should
     * be. deployed somewhere on the Classpath) TODO ServiceLocationException if
     * the properties file cannot be loaded for any reason.
     */
    private void loadServiceLocations() {
        if (!servicePropertiesLoaded) {
            ClassPathResource resource =
                    new ClassPathResource(SERVICE_PROPERTIES_FILE);
            serviceProperties = new Properties();

            try {
                serviceProperties.load(resource.getInputStream());
                servicePropertiesLoaded = true;
            }
            catch (IOException e) {
                LOGGER.error("unable to load service locations from "
                        + SERVICE_PROPERTIES_FILE, e);
                // TODO Need to implement Custom Class for throw new
                // ServiceLocationException(logger,
                // "unable to load service locations from " +
                // SERVICE_PROPERTIES_FILE);
            }

            for (Map.Entry entry : serviceProperties.entrySet()) {
                LOGGER.trace("entry : key=" + entry.getKey() + " value="
                        + entry.getValue());

            }
        }
    }

    /**
     * Get a value from the property file containing service location
     * information. If the file has not been read yet, reads the file.
     * 
     * @param serviceShortName the name that prefixes all the properties in the property
     * file.
     * @param propertySuffix the string that, with the short name, uniquely identifies the
     * property.
     * 
     * @return the property value throws //TODO Need Custom Exception if the
     * property file cannot be loaded
     */
    private String getServiceProperty(String serviceShortName,
            String propertySuffix) {
        loadServiceLocations();

        return serviceProperties.getProperty(serviceShortName + "."
                + propertySuffix);
    }

    /**
     * Sets the web service url.
     * 
     * @param webServiceURL the webServiceURL to set
     */
    public void setWebServiceURL(String webServiceURL) {
        this.webServiceURL = webServiceURL;
    }

}
