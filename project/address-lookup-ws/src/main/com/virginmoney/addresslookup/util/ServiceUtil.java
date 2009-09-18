package com.virginmoney.addresslookup.util;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

import javax.xml.ws.Service;
import javax.xml.namespace.QName;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBException;
import javax.xml.rpc.Stub;
import javax.xml.rpc.ServiceException;
import java.util.Iterator;
import java.io.StringWriter;

/**
 * Utility functions for logging and debugging Web Service classes and messages.
 */

public class ServiceUtil {
     private static Logger logger = Logger.getLogger(ServiceUtil.class);

    /**
     * Utility method to unmarshal a JAX-WS message to a String for logging / debugging etc purposes.
     *
     * By default it does not insert line feeds into the text; use method messageToString(Service service, Object message, boolean linefeeds) for this.
     *
     * @param message JAX-WS object containing the message from to unmarshal.
     * @return the message as an XML string
     */
    public static String messageToString(Object message) {
        return messageToString(message, false);
    }

    /**
     * Utility method to unmarshal a JAX-WS message to a String for logging / debugging etc purposes.
     * Use {@link com.virginmoney.addresslookup.util.MarshalUtil} for business-critical marshalling and unmarshalling.
     *
     * @param message   JAX object containing the message to convert to a String.
     * @param linefeeds indicates if linefeeds should be inserted between elements.
     * @return the message as an XML string
     */
    public static String messageToString(Object message, boolean linefeeds) {

        StringWriter stringWriter = new StringWriter();
        try {
             //The contextPath contains a list of Java package names that contain schema derived interfaces.
             //The value of this parameter initializes the JAXBContext object so that it is capable of managing the schema derived interfaces.
             //For this usage, we use the package of the message to generate the context
             String packageName = message.getClass().getPackage().getName();
             JAXBContext context = JAXBContext.newInstance(packageName);

             //create a JAXBElement using the service and message details, the value of QName is irrelevant but it insists that we have one.
             JAXBElement element = new JAXBElement(new QName("serviceutil","serviceutil"), message.getClass(), message);

             //Marshal the JAXB classes from the message to XML
             //Create a Marshaller to marshall the message into a StringWriter
             Marshaller m = context.createMarshaller();
             m.marshal(element, stringWriter);

         } catch (JAXBException e) {
             logger.error("JAXB exception marshalling message: " + message, e);
         } 

        //Return the XML as a string
        if (linefeeds) {
            // insert a linefeed between each element
            return StringUtils.replace(stringWriter.toString(), "><", ">\n<");
        } else {
            return stringWriter.toString();
        }

    }


}
