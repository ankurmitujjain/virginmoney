package com.virginmoney.addresslookup.serviceproxy;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import com.virginmoney.addresslookup.exception.ServiceLocationException;

/**
 * Factory for building web service clients for service proxy classes.
 *  * <br/> 
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
 *
 * @author Rob Kay
 * @author Ian Lavey
 * @author $Author$
 * @version $Version$
 * @vm.creation.date Dec 10, 2007
 * @vm.copyright.year 2007
 */
public abstract class AbstractWebServiceLocator {
    private static Logger logger = Logger.getLogger(AbstractWebServiceLocator.class);

    /**
     * name of a properties file containing service information
     */
    private static String SERVICE_PROPERTIES_FILE = "WEB-INF/classes/web-service-clients.properties";
    /**
     * suffix added to service short name and read from SERVICE_PROPERTIES_FILE
     */
    private static String SERVICE_CLASSNAME_SUFFIX = "classname";
    /**
     * suffix added to service short name and read from SERVICE_PROPERTIES_FILE
     */
    private static String SERVICE_NAMESPACE_SUFFIX = "namespace";
    /**
     * suffix added to service short name and read from SERVICE_PROPERTIES_FILE
     */
    private static String SERVICE_NAME_SUFFIX = "name";
    /**
     * suffix added to service short name and read from SERVICE_PROPERTIES_FILE
     */
    private static String SERVICE_URL_PROPERTY_SUFFIX = "url.system.property";

    /**
     * List of properties read from SERVICE_PROPERTIES_FILE
     */
    private static Properties serviceProperties;

    /**
     * keep track of loading the servie properties explicitly
     */
    private boolean servicePropertiesLoaded = false;

    /**
     * Creates an instance of a Service referring to a deployed web service.
     *
     * @param serviceShortName the short name for the service used in the service properties file.
     * @throws com.virginmoney.addresslookup.exception
     *          if a client for the service cannot be created for any reason
     */
    protected Service getWebService(String serviceShortName) throws ServiceLocationException {
        logger.trace("getting web service for " + serviceShortName);

        // read the service information from the property file.
        String serviceClassName = getServiceProperty(serviceShortName, SERVICE_CLASSNAME_SUFFIX);
        String serviceName = getServiceProperty(serviceShortName, SERVICE_NAME_SUFFIX);
        String serviceURLPropertyName = getServiceProperty(serviceShortName, SERVICE_URL_PROPERTY_SUFFIX);
        String serviceNameSpace = getServiceProperty(serviceShortName, SERVICE_NAMESPACE_SUFFIX);

        logger.trace("service properties :");
        if (serviceClassName == null || serviceClassName.equals("")) {
            throw new ServiceLocationException(logger,
                    "System property named " + SERVICE_CLASSNAME_SUFFIX + " for service name=" + serviceShortName +
                            " not set, cannot locate service");
        }
        logger.trace("\n");
        logger.trace("   serviceShortName        =" + serviceShortName);
        logger.trace("   serviceClassName        =" + serviceClassName);
        logger.trace("   serviceName             =" + serviceName);
        logger.trace("   serviceURLPropertyName  =" + serviceURLPropertyName);
        logger.trace("   serviceNameSpace        =" + serviceNameSpace);

        // get the System property which contains the full url of the running service.
        // The url should be in the form  http://<hostname>:<port>/<service-webapp>/<service-name>?wsdl
        String serviceURLPropertyValue = System.getProperty(serviceURLPropertyName);
        logger.trace("\n");
        if (serviceURLPropertyValue == null || serviceURLPropertyValue.equals("")) {
            logger.error("   serviceURLPropertyValue = **ERROR** System property named "
                    + serviceURLPropertyName + " for service name=" + serviceShortName +
                    " not set, cannot locate service");
            throw new ServiceLocationException(logger,
                    "System property named " + serviceURLPropertyName + " for service name=" + serviceShortName +
                            " not set, cannot locate service");
        }
        logger.trace("   serviceURLPropertyValue =" + serviceURLPropertyValue);
        logger.trace("\n");

        // create an instance of the Service class required
        Service webService = null;

        try {
            logger.trace("Constructing URL(\"" + serviceURLPropertyValue + "\"") ;
            URL serviceUrl = new URL(serviceURLPropertyValue);

            logger.trace("Constructing QName(\"" + serviceNameSpace  + "\",\"" + serviceName + "\"") ;
            QName serviceQName = new QName(serviceNameSpace, serviceName);
            // Use the service class, url from the system property, the service name and namespace to construct
            // the arguments required by the Service constructor
            // use Reflection to get a constructor for the Service class and a new instance.
            Class serviceClass = Class.forName(serviceClassName);
            Constructor serviceConstructor = serviceClass.getConstructor(URL.class, QName.class);
            logger.trace("Constructing service from URL=" + serviceUrl + "  QName=" + serviceQName);
            webService = (Service) serviceConstructor.newInstance(serviceUrl, serviceQName);
        }
        catch (ClassNotFoundException e) {
            logger.error(
                    "Unable to load class " + serviceClassName +
                            " for service " + serviceShortName +
                            " serviceName =" + serviceName +
                            " serviceURLPropertyName =" + serviceURLPropertyName +
                            " serviceNameSpace =" + serviceNameSpace +
                            " serviceURLPropertyValue =" + serviceURLPropertyValue,
                    e);

            throw new ServiceLocationException(logger,
                    "Unable to load class " + serviceClassName +
                            " for service " + serviceShortName +
                            " serviceName =" + serviceName +
                            " serviceURLPropertyName =" + serviceURLPropertyName +
                            " serviceNameSpace =" + serviceNameSpace +
                            " serviceURLPropertyValue =" + serviceURLPropertyValue,
                    e);
        }
        catch (MalformedURLException e) {
            logger.error("URL " + serviceURLPropertyValue + " is malformed" +
                    " for service " + serviceShortName +
                    " serviceName =" + serviceName +
                    " serviceURLPropertyName =" + serviceURLPropertyName +
                    " serviceNameSpace =" + serviceNameSpace +
                    " serviceURLPropertyValue =" + serviceURLPropertyValue);
            throw new ServiceLocationException(logger, "URL " + serviceURLPropertyValue + " is malformed" +
                    " for service " + serviceShortName +
                    " serviceName =" + serviceName +
                    " serviceURLPropertyName =" + serviceURLPropertyName +
                    " serviceNameSpace =" + serviceNameSpace +
                    " serviceURLPropertyValue =" + serviceURLPropertyValue);
        }
        catch (NoSuchMethodException e) {
            logger.error("No constructor: " + serviceClassName + "(URL, QName)" +
                    " for service " + serviceShortName +
                    " serviceName =" + serviceName +
                    " serviceURLPropertyName =" + serviceURLPropertyName +
                    " serviceNameSpace =" + serviceNameSpace +
                    " serviceURLPropertyValue =" + serviceURLPropertyValue, e);
            throw new ServiceLocationException(logger, "No constructor: " + serviceClassName + "(URL, QName)" +
                    " for service " + serviceShortName +
                    " serviceName =" + serviceName +
                    " serviceURLPropertyName =" + serviceURLPropertyName +
                    " serviceNameSpace =" + serviceNameSpace +
                    " serviceURLPropertyValue =" + serviceURLPropertyValue, e);
        }
        catch (InstantiationException e) {
            logger.error(
                    "unable to instantiate class " + serviceClassName +
                            " for service " + serviceShortName +
                            " : caught Exception:" + e +
                            " serviceName =" + serviceName +
                            " serviceURLPropertyName =" + serviceURLPropertyName +
                            " serviceNameSpace =" + serviceNameSpace +
                            " serviceURLPropertyValue =" + serviceURLPropertyValue,
                    e);
            throw new ServiceLocationException(logger,
                    "unable to instantiate class " + serviceClassName +
                            " for service " + serviceShortName +
                            " : caught Exception:" + e +
                            " serviceName =" + serviceName +
                            " serviceURLPropertyName =" + serviceURLPropertyName +
                            " serviceNameSpace =" + serviceNameSpace +
                            " serviceURLPropertyValue =" + serviceURLPropertyValue,
                    e);
        }
        catch (IllegalAccessException e) {
            logger.error(
                    "unable to access class " + serviceClassName +
                            " for service " + serviceShortName +
                            " : caught Exception:" + e +
                            " serviceName =" + serviceName +
                            " serviceURLPropertyName =" + serviceURLPropertyName +
                            " serviceNameSpace =" + serviceNameSpace +
                            " serviceURLPropertyValue =" + serviceURLPropertyValue,
                    e);
            throw new ServiceLocationException(logger,
                    "unable to access class " + serviceClassName +
                            " for service " + serviceShortName +
                            " : caught Exception:" + e +
                            " serviceName =" + serviceName +
                            " serviceURLPropertyName =" + serviceURLPropertyName +
                            " serviceNameSpace =" + serviceNameSpace +
                            " serviceURLPropertyValue =" + serviceURLPropertyValue,
                    e);
        }
        catch (InvocationTargetException e) {
            logger.error(
                    "Unable to invoke access service" + serviceShortName +
                            " - InvocationTargetException caused by possible URL / network addressing issue " +
                            " serviceURLPropertyValue =" + serviceURLPropertyValue +
                            " : caught Exception:" + e +
                            " serviceName =" + serviceName +
                            " serviceURLPropertyName =" + serviceURLPropertyName +
                            " serviceNameSpace =" + serviceNameSpace +
                            " class=" + serviceClassName,
                    e);
            throw new ServiceLocationException(logger,
                    "Unable to invoke access service" + serviceShortName +
                            " - InvocationTargetException caused by possible URL / network addressing issue " +
                            " serviceURLPropertyValue =" + serviceURLPropertyValue +
                            " : caught Exception:" + e +
                            " serviceName =" + serviceName +
                            " serviceURLPropertyName =" + serviceURLPropertyName +
                            " serviceNameSpace =" + serviceNameSpace +
                            " class=" + serviceClassName,
                    e);
        }
        catch (ClassCastException e) {
            logger.error(
                    "Class " + serviceClassName + " for " + serviceShortName + " is not a jax web service : caught Exception:" + e +
                            " for service " + serviceShortName +
                            " serviceName =" + serviceName +
                            " serviceURLPropertyName =" + serviceURLPropertyName +
                            " serviceNameSpace =" + serviceNameSpace +
                            " serviceURLPropertyValue =" + serviceURLPropertyValue,
                    e);
            throw new ServiceLocationException(logger,
                    "Class " + serviceClassName + " for " + serviceShortName + " is not a jax web service : caught Exception:" + e +
                            " for service " + serviceShortName +
                            " serviceName =" + serviceName +
                            " serviceURLPropertyName =" + serviceURLPropertyName +
                            " serviceNameSpace =" + serviceNameSpace +
                            " serviceURLPropertyValue =" + serviceURLPropertyValue,
                    e);
        }

        return webService;
    }

    /**
     * Load service location properties from the properties file (that should be deployed somewhere on the Classpath)
     *
     * @throws ServiceLocationException if the properties file cannot be loaded for any reason.
     */
    private void loadServiceLocations() throws ServiceLocationException {
        if (servicePropertiesLoaded) {
            return;
        }

        ClassPathResource resource = new ClassPathResource(SERVICE_PROPERTIES_FILE);
        serviceProperties = new Properties();
        InputStream in  = null;
        try {
            in = resource.getInputStream();
            serviceProperties.load(in);
            servicePropertiesLoaded = true;
        }
        catch (IOException e) {
            logger.error("unable to load service locations from " + SERVICE_PROPERTIES_FILE,e);
            throw new ServiceLocationException(logger, "unable to load service locations from " + SERVICE_PROPERTIES_FILE);
        } finally {
            try {
                if(in != null) in.close();
            } catch(IOException e) {
                logger.debug("Unable to close input stream.", e);    
            }
            in = null;
            resource = null;
        }

        for (Map.Entry entry : serviceProperties.entrySet()) {
            logger.trace("entry : key=" + entry.getKey() + " value=" + entry.getValue());

        }
    }

    /**
     * Get a value from the property file containing service location information. If the file has not been read yet,
     * reads the file.
     *
     * @param serviceShortName the name that prefixes all the properties in the property file.
     * @param propertySuffix   the string that, with the short name, uniquely identifies the property.
     * @return the property value
     * @throws ServiceLocationException if the property file cannot be loaded
     */
    private String getServiceProperty(String serviceShortName, String propertySuffix) throws ServiceLocationException {
        loadServiceLocations();

        return serviceProperties.getProperty(serviceShortName + "." + propertySuffix);
    }

}
