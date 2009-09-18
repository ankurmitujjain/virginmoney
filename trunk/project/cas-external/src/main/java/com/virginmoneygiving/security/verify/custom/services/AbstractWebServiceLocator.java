package com.virginmoneygiving.security.verify.custom.services;

/********************************************************************************
 * Class         :  AbstractWebServiceLocator
 * Functionality :  Factory for building web service clients for CAS - See Crumb service
 * Uses a properties file [web-service-clients.properties] to
 * hold the various values needed to construct a service. The properties file must be on the current classpath. There
 * are four values needed to build a service, which must share a common prefix for service. For example, for the web
 * service with a 'short' name of 'Example', the file must contain:<br/>
 * <p/>
 * <code>
 * Example.classname=com.virginmoney.example.messages.Example<br/>
 * Example.namespace=http://www.virginmoney.com/service/version<br/>
 * Example.name=FullExampleName<br/>
 * Example.url.system.property=crumb.service.example<br/>
 * </code>
 * <p/>
 * The url.system.property, as the name might suggest, should match a system property name in the current JVM. This
 * allows the fixed propeties like namespaces to be set once and changing properties, such as thehost the service is
 * currently running on to be set per-deployment.
 * <p/>
 * Classes that try to use this abstract class will need the jax-ws message classes available or the client
 * cannot be creaed.
 * Author(s)     :  choprah
 * Creation Date :  01-May-2008
 * Copyright     :  Virgin Money Ltd.
 ********************************************************************************/

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.springframework.core.io.ClassPathResource;
import org.apache.log4j.Logger;

import com.virginmoneygiving.common.exception.ServiceException;

/**
 * Class         :  AbstractWebServiceLocator
 * Functionality :  Factory for building web service clients for CAS - See Crumb service
 * Uses a properties file [web-service-clients.properties] to
 * hold the various values needed to construct a service. The properties file must be on the current classpath. There
 * are four values needed to build a service, which must share a common prefix for service. For example, for the web
 * service with a 'short' name of 'Example', the file must contain:<br/>
 * 
 * @author HunarC
 */
public abstract class AbstractWebServiceLocator {

	/** Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(AbstractWebServiceLocator.class);

	/** name of a properties file containing service information. */
    private static String SERVICE_PROPERTIES_FILE = "web-service-clients.properties";
    
    /** suffix added to service short name and read from SERVICE_PROPERTIES_FILE. */
    private static String SERVICE_CLASSNAME_SUFFIX = "classname";
    
    /** suffix added to service short name and read from SERVICE_PROPERTIES_FILE. */
    private static String SERVICE_NAMESPACE_SUFFIX = "namespace";
    
    /** suffix added to service short name and read from SERVICE_PROPERTIES_FILE. */
    private static String SERVICE_NAME_SUFFIX = "name";
    
    /** suffix added to service short name and read from SERVICE_PROPERTIES_FILE. */
    private static String SERVICE_URL_PROPERTY_SUFFIX = "url.system.property";
    
    /** List of properties read from SERVICE_PROPERTIES_FILE. */
    private static Properties serviceProperties;
    
    /** keep track of loading the servie properties explicitly. */
    private boolean servicePropertiesLoaded = false;

    /**
     * Creates an instance of a Service referring to a deployed web service.
     * 
     * @param serviceShortName the short name for the service used in the service properties file.
     * 
     * @return the web service
     * 
     * @throws Exception if a client for the service cannot be created for any reason
     */
    protected Service getWebService(String serviceShortName)  {
    	if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getWebService() - START");
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getWebService(serviceShortName=" + serviceShortName + ")");
		}

		// read the service information from the property file.
        String serviceClassName = getServiceProperty(serviceShortName, SERVICE_CLASSNAME_SUFFIX);
        String serviceName = getServiceProperty(serviceShortName, SERVICE_NAME_SUFFIX);
        String serviceURLPropertyName = getServiceProperty(serviceShortName, SERVICE_URL_PROPERTY_SUFFIX);
        String serviceNameSpace = getServiceProperty(serviceShortName, SERVICE_NAMESPACE_SUFFIX);

        // get the System property which contains the full url of the running service.
        // The url should be in the form  http://<hostname>:<port>/<service-webapp>/<service-name>?wsdl
        String serviceURLPropertyValue = System.getProperty(serviceURLPropertyName);
        if (serviceURLPropertyValue == null) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("getWebService(serviceShortName=" + serviceShortName + ")");
			}
             // TODO throw new ServiceLocationException(logger,
             // "System property named " + SERVICE_CLASSNAME_SUFFIX +
             // " for service name=" + serviceShortName +
             // " not set, cannot locate service");
        				}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("service URL is: " + serviceURLPropertyValue);
	        LOGGER.debug("service Namespace is: " + serviceNameSpace);
		}

		// create an instance of the Service class required
        Service webService = null;

        try {
            URL serviceUrl = new URL(serviceURLPropertyValue);
			
            QName serviceQName = new QName(serviceNameSpace, serviceName);
			

			// Use the service class, url from the system property, the service name and namespace to construct
            // the arguments required by the Service constructor
            // use Reflection to get a constructor for the Service class and a new instance.
            Class serviceClass = Class.forName(serviceClassName);
			
            Constructor serviceConstructor = serviceClass.getConstructor(URL.class, QName.class);
			
            webService = (Service)serviceConstructor.newInstance(serviceUrl, serviceQName);
			
        }
        catch (ClassNotFoundException e) {
			LOGGER.error("Error occured inside AbstractWebServiceLocator :: getWebService() method");
			LOGGER.error("Error details : " + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        catch (MalformedURLException e) {
			LOGGER.error("Error occured inside AbstractWebServiceLocator :: getWebService() method");
			LOGGER.error("Error details : " + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        catch (NoSuchMethodException e) {
			LOGGER.error("Error occured inside AbstractWebServiceLocator :: getWebService() method");
			LOGGER.error("Error details : " + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        catch (InstantiationException e) {
			LOGGER.error("Error occured inside AbstractWebServiceLocator :: getWebService() method");
			LOGGER.error("Error details : " + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        catch (IllegalAccessException e) {
			LOGGER.error("Error occured inside AbstractWebServiceLocator :: getWebService() method");
			LOGGER.error("Error details : " + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        catch (InvocationTargetException e) {
			LOGGER.error("Error occured inside AbstractWebServiceLocator :: getWebService() method");
			LOGGER.error("Error details : " + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        catch (ClassCastException e) {
			LOGGER.error("Error occured inside AbstractWebServiceLocator :: getWebService() method");
			LOGGER.error("Error details : " + e.getMessage());
            throw new ServiceException(e.getMessage());
        }

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getWebService(String) - END");
		}
		return webService;
    }

    /**
     * Load service location properties from the properties file (that should be deployed somewhere on the Classpath).
     * 
     * @throws Exception if the properties file cannot be loaded for any reason.
     */
    private void loadServiceLocations() {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("loadServiceLocations() -  START");
		}

		if (servicePropertiesLoaded) {
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("loadServiceLocations() - END");
			}
			return;
        }

        ClassPathResource resource = new ClassPathResource(SERVICE_PROPERTIES_FILE);
        serviceProperties = new Properties();
        try {
            serviceProperties.load(resource.getInputStream());
            servicePropertiesLoaded = true;
        }
        catch (IOException e) {
			LOGGER.error("Error occured inside AbstractWebServiceLocator :: getWebService() method");
			LOGGER.error("Error details : " + e.getMessage());
            throw new ServiceException(e.getMessage());
        }

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("loadServiceLocations() - END");
		}
	}

    /**
     * Get a value from the property file containing service location information. If the file has not been read yet,
     * reads the file.
     * 
     * @param serviceShortName the name that prefixes all the properties in the property file.
     * @param propertySuffix   the string that, with the short name, uniquely identifies the property.
     * 
     * @return the property value
     * 
     * @throws Exception if the property file cannot be loaded
     */
    private String getServiceProperty(String serviceShortName, String propertySuffix) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getServiceProperty() -  START");
		}

		loadServiceLocations();

		String returnString = serviceProperties.getProperty(serviceShortName + "." + propertySuffix);
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getServiceProperty(String, String) - END");
		}
		return returnString;
	}
}
