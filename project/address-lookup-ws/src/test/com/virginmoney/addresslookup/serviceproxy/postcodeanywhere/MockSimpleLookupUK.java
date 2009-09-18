package com.virginmoney.addresslookup.serviceproxy.postcodeanywhere;

import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceFeature;
import org.apache.log4j.Logger;
import uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUK;
import uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUKSoap;
import com.virginmoney.addresslookup.serviceproxy.postcodeanywhere.mockObjects.MockPostcodeAnywhereSoap;

/**
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class MockSimpleLookupUK extends SimpleLookupUK {

   private static Logger logger = Logger.getLogger(MockSimpleLookupUK.class);
        private final static URL SIMPLELOOKUPUK_WSDL_LOCATION = null;

//    static {
//        URL url = null;
//        try {
//            URL baseUrl;
//            baseUrl = uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUK.class.getResource(".");
//            url = new URL(baseUrl, "http://services.postcodeanywhere.co.uk/uk/simplelookup.asmx?wsdl");
//        } catch (MalformedURLException e) {
//            logger.warn("Failed to create URL for the wsdl Location: 'http://services.postcodeanywhere.co.uk/uk/simplelookup.asmx?wsdl', retrying as a local file");
//            logger.warn(e.getMessage());
//        }
//        SIMPLELOOKUPUK_WSDL_LOCATION = url;
//    }

    public MockSimpleLookupUK(URL wsdlLocation, QName serviceName) {
//        super(wsdlLocation, serviceName);
        logger.debug("created MockSimpleLookupUK with url=" + wsdlLocation);
    }

    public MockSimpleLookupUK() {
//        super(SIMPLELOOKUPUK_WSDL_LOCATION, new QName("PostcodeAnywhere2", "SimpleLookupUK"));
    }

    /**
     *
     * @return
     *     returns SimpleLookupUKSoap
     */
    @WebEndpoint(name = "SimpleLookupUKSoap")
    public SimpleLookupUKSoap getSimpleLookupUKSoap() {
        return new MockPostcodeAnywhereSoap();
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SimpleLookupUKSoap
     */
    @WebEndpoint(name = "SimpleLookupUKSoap")
    public SimpleLookupUKSoap getSimpleLookupUKSoap(WebServiceFeature... features) {
        return new MockPostcodeAnywhereSoap();

    }
}

