package com.virginmoney.addresslookup;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.virginmoney.addresslookup.messages.AddressLookupServicePortType;
import com.virginmoney.addresslookup.serviceproxy.AddressSearchServiceProxy;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.qa.addresslookup.SystemPropertySetter;
import net.sf.dozer.util.mapping.MapperIF;

/**
 * Utility class to assist setting up the Junit test environment.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class SpringBuilder {

    private static Logger logger = Logger.getLogger(SpringBuilder.class);
    public static ApplicationContext ac;

    public static final String TEST_SPRING_BEANS_CONFIG_XML = "WEB-INF/TEST-spring-beans-config.xml";

    public static final String DOZER_BEAN_NAME = "dozer";
    public static final String ENDPOINT_BEAN_NAME = "serviceEndPoint";
    public static final String ENDPOINT_WITH_MOCK_SERVICES_BEAN_NAME = "serviceEndPointWithMockServices";
    public static final String POSTCODE_ANYWHERE_SERVICEPROXY = "postcodeAnywhereServiceProxyBean";
    public static final String POSTCODE_ANYWHERE_SERVICEPROXY_WITH_MOCK_SERVICES_BEAN_NAME = "postcodeAnywhereServiceProxyWithMockServices";
    public static final String IFDS_SERVICEPROXY = "IFDSServiceProxyBean";
    public static final String IFDS_SERVICEPROXY_WITH_MOCK_SERVICES_BEAN_NAME = "IFDSServiceProxyWithMockServices";

    /**
     * Ontain a reference to the spring application context.
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        if(ac == null){
            ac = new ClassPathXmlApplicationContext(TEST_SPRING_BEANS_CONFIG_XML);
        }
        return ac;
    }

    /**
     * Returns an instance of the Webservice endpoint which uses 'live' calls.
     * @return
     */
    public static AddressLookupServicePortType getEndPoint() {
        return (AddressLookupServicePortType) getApplicationContext().getBean(ENDPOINT_BEAN_NAME);
    }

    /**
     * Returns an instance of the Webservice endpoint which uses mock service calls.
     * @return
     */
    public static AddressLookupServicePortType getEndPointWithMockServices() {
        return (AddressLookupServicePortType) getApplicationContext().getBean(ENDPOINT_WITH_MOCK_SERVICES_BEAN_NAME);
    }

    /**
     * Returns an instance of the Webservice endpoint which uses mock service calls.
     * @return
     */
    public static AddressSearchServiceProxy getPostcodeAnywhereServiceProxy() {
        return (AddressSearchServiceProxy) getApplicationContext().getBean(POSTCODE_ANYWHERE_SERVICEPROXY);
    }

    /**
     * Returns an instance of the Webservice endpoint which uses mock service calls.
     * @return
     */
    public static AddressSearchServiceProxy getPostcodeAnywhereServiceProxyWithMockServices() {
        return (AddressSearchServiceProxy) getApplicationContext().getBean(POSTCODE_ANYWHERE_SERVICEPROXY_WITH_MOCK_SERVICES_BEAN_NAME);
    }

    /**
     * Returns an instance of the Webservice endpoint which uses mock service calls.
     * @return
     */
    public static AddressSearchServiceProxy getIFDSServiceProxyWithMockServices() {
        return (AddressSearchServiceProxy) getApplicationContext().getBean(IFDS_SERVICEPROXY_WITH_MOCK_SERVICES_BEAN_NAME);
    }
   /**
     * Returns an instance of the dozer bean mapper
     * @return
     */
    public static MapperIF getDozer() {
        return (MapperIF) getApplicationContext().getBean(DOZER_BEAN_NAME);
    }

    /**
     *    Sets default system properties when testing the service. These should already be set via
     *    the Intellij-local-junit-config ant task but this attempts to set them anyway.
     *    the SpringBuilder will ignore a value if its already set so there is no risk of
     *   using the wrong values
      * @throws Exception
     */
    public static void setSystemProperties() throws Exception {

        SystemPropertySetter setter = new SystemPropertySetter();

        setter.propertyName = "http.proxyHost";
        setter.propertyValue="zoinks.eit.wrl.virginmoney.com";
        setter.set();

        setter.propertyName = "http.proxyPort";
        setter.propertyValue="8888";
        setter.set();

        setter.propertyName = "http.nonProxyHosts";
        setter.propertyValue="*.unix.vmoney.local|localhost";
        setter.set();

        setter.propertyName = "postcodeAnywhere.service.url";
        setter.propertyValue="http://services.postcodeanywhere.co.uk/uk/simplelookup.asmx?wsdl";
        setter.set();

        // we have 3 seperate accounts at postcode anywhere, depending upon the search we are doing
        // in development these all match to the same test account

        // default postcode anywhere properties
        setter.propertyName = AddressSearchParameters.ServiceProviders.PostcodeAnywhere.getAccountCodePropertyName();
        setter.propertyValue = "VIRGI11117";
        setter.set();

        setter.propertyName = AddressSearchParameters.ServiceProviders.PostcodeAnywhere.getLicenseKeyPropertyName();
        setter.propertyValue= "CH97-ZW47-JN11-MX65";
        setter.set();

        // postcode anywhere properties for VM Giving website consumer
        setter.propertyName = AddressSearchParameters.ServiceProviders.VMGivingConsumer.getAccountCodePropertyName();
        setter.propertyValue = "VIRGI11117";
        setter.set();

        setter.propertyName = AddressSearchParameters.ServiceProviders.VMGivingConsumer.getLicenseKeyPropertyName();
        setter.propertyValue= "CH97-ZW47-JN11-MX65";
        setter.set();


        // postcode anywhere properties for VM Giving website consumer
        setter.propertyName = AddressSearchParameters.ServiceProviders.VMGivingOperator.getAccountCodePropertyName();
        setter.propertyValue = "VIRGI11117";
        setter.set();

        setter.propertyName = AddressSearchParameters.ServiceProviders.VMGivingOperator.getLicenseKeyPropertyName();
        setter.propertyValue= "CH97-ZW47-JN11-MX65";
        setter.set();

        // ifds webserive port
        setter.propertyName = "IFDS.WEBSERVICE_PORT_ADDRESS";
        setter.propertyValue= "http://qa-virgin-onlineservices.ifdsgroup.co.uk/OnlineServicesSoap/OnlineServicesSoapHttpuri";
        setter.set();


        logger.debug("http.proxyHost               =" + System.getProperty("http.proxyHost"));
        logger.debug("http.proxyPort               =" + System.getProperty("http.proxyPort"));
        logger.debug("http.nonProxyHosts           =" + System.getProperty("http.nonProxyHosts"));
        logger.debug("postcodeAnywhere.service.url =" + System.getProperty("postcodeAnywhere.service.url"));

        // we have 3 seperate accounts at postcode anywhere, depending upon the search we are doing
        // in development these all match to the same test account
        logger.debug("postcodeAnywhere.accountCode                  =" + System.getProperty("postcodeAnywhere.accountCode"));
        logger.debug("postcodeAnywhere.licenseKey                   =" + System.getProperty("postcodeAnywhere.licenseKey"));
        logger.debug("postcodeAnywhere.VMGivingConsumer.accountCode =" + System.getProperty("postcodeAnywhere.VMGivingConsumer.accountCode"));
        logger.debug("postcodeAnywhere.VMGivingConsumer.licenseKey  =" + System.getProperty("postcodeAnywhere.VMGivingConsumer.licenseKey"));
        logger.debug("postcodeAnywhere.VMGivingOperator.accountCode =" + System.getProperty("postcodeAnywhere.VMGivingOperator.accountCode"));
        logger.debug("postcodeAnywhere.VMGivingOperator.licenseKey  =" + System.getProperty("postcodeAnywhere.VMGivingOperator.licenseKey"));

        logger.debug("IFDS.WEBSERVICE_PORT_ADDRESS                  =" + System.getProperty("IFDS.WEBSERVICE_PORT_ADDRESS"));

    }
}

