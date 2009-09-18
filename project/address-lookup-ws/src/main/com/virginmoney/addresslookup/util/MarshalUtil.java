package com.virginmoney.addresslookup.util;


import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.IOException;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.stream.*;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.apache.commons.lang.StringUtils;

/**
 * Contains utility methods to marshall an object to XML or vice-versa, with optional schema validation.
 * @author woodsn
 *         <p/>
 *         Last change : $Date$  Revision : $Revision$  By : $Author$
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class MarshalUtil {

    private static Logger logger = Logger.getLogger(MarshalUtil.class);

    /**
     * Marshals an object into an XML String, without validation.
     * @param requestObj the object to marshal.
     * @return the request object as an XML String.
     * @throws JAXBException
     * @throws SchemaNotFoundException
     */
    public static String marshal(Object requestObj)
            throws JAXBException, SchemaNotFoundException {
        return marshalAndValidate(requestObj,null);
    }

    /**
     * Marshals an object into an XML String, with schema validation applied.
     * @param requestObj  the object to marshal.
     * @param schemaName the name of the schema XSD to be used for validation, if null then no validation will
     * be performed.
     * @return the request object as an XML String.
     * @throws JAXBException
     * @throws SchemaNotFoundException
     */
    public static String marshalAndValidate(Object requestObj, String schemaName)
            throws JAXBException, SchemaNotFoundException
    {
        logger.trace("marshalAndValidate() Marshalling " + requestObj + " using schema " + schemaName);

        if (requestObj == null) {
            throw new MarshalException("Null requestObj passed to unmarshalAndValidate()") ;
        }

        // Create a marshaller using the request class as the context.
        Marshaller marshaller = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(requestObj.getClass());
            marshaller = jaxbContext.createMarshaller();
        }
        catch (JAXBException e) {
            logger.error("marshalAndValidate() JAXBException when getting JAXBContext " +
                                          " : schema name=" + schemaName +
                                          " : requestObj=" + requestObj, e);
            throw e;
        }

        // If a schema name has been provided, use it to validate the request object.
        if (StringUtils.isNotBlank(schemaName)) {
            logger.trace("validating using schema " + schemaName);

            // get the schema to be used for validation.
            Schema schema;
            try {
                schema = fetchXMLSchema(schemaName);
            }
            catch (SAXException e) {
                logger.error("marshalAndValidate() SAXException when getting schema " +
                                              " : schema name=" + schemaName +
                                              " : requestObj=" + requestObj, e);
                throw new ValidationException("marshalAndValidate() SAXException when getting schema " +
                                              " : schema name=" + schemaName +
                                              " : requestObj=" + requestObj, e);
            }

            // set the schema into the marshaller and create a Validation event to handle errors.
            try {
                marshaller.setSchema(schema);
                marshaller.setEventHandler(new ValidationEventHandler() {
                    // allow unmarshalling to continue even if there are errors
                    public boolean handleEvent(ValidationEvent ve) {
                        // ignore warnings
                        if (ve.getSeverity() != ValidationEvent.WARNING) {
                            ValidationEventLocator vel = ve.getLocator();
                        }
                        return false;
                    }
                });
            }
            catch (JAXBException e) {
                logger.error("marshalAndValidate() JAXBException when setting validator " +
                                              " : schema name=" + schemaName +
                                              " : requestObj=" + requestObj, e);
                throw e;
            }
        }

        // marshall the request object into a StringWriter
        StringWriter requestXML = new StringWriter();


        try {
            // by using an XMLStreamWriter it may be possible to marshall class regardless of whether it
            // has the @XmlRootElement annotation.
            XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(requestXML);
            marshaller.marshal(requestObj, xmlStreamWriter);
        }
        catch (MarshalException e) {
            logger.error("marshalAndValidate() MarshalException when marshalling " +
                                          " : schema name=" + schemaName +
                                          " : requestObj=" + requestObj, e);
            throw e;
        }
        catch (JAXBException e) {
            logger.error("marshalAndValidate() JAXBException when marshalling " +
                                          " : schema name=" + schemaName +
                                          " : requestObj=" + requestObj, e);
            throw e;
        }
        catch (Exception e) {
            logger.error("marshalAndValidate() unexpected Exception when marshalling " +
                                          " : schema name=" + schemaName +
                                          " : requestObj=" + requestObj, e);
            throw new ValidationException("Error validating schema : schema name=" + schemaName, e);
        }

        logger.trace("marshalAndValidate() marshalled " + requestObj + " to " + "\n" + requestXML.toString());
        return requestXML.toString();
    }

    /**
     * Unmarshals an XML String into an object, without validation.
     * @param responseXML The XML String to be unmarshalled
     * @param responseClass The Class on the object the XML is to be unmarshalled into.
     * @return
     * @throws JAXBException
     * @throws SchemaNotFoundException
     */
    public static Object unmarshal(String responseXML, Class responseClass)
            throws JAXBException, SchemaNotFoundException
    {
       return unmarshalAndValidate(responseXML,responseClass,null);
    }

    /**
     * Unmarshals an XML String into an object, with optional schema validation.
     * @param responseXML The XML String to be unmarshalled
     * @param responseClass The Class on the object the XML is to be unmarshalled into.
     * @param schemaName the name of the schema XSD to be used for validation, if null then no validation will
     * be performed.
     * @return
     * @throws JAXBException
     * @throws SchemaNotFoundException
     */
  public static Object unmarshalAndValidate(String responseXML, Class responseClass, String schemaName)
            throws JAXBException, SchemaNotFoundException
    {

        logger.trace("unmarshalling to " + responseClass.getName()  + " using schema " + schemaName + " XML=\n" + responseXML);
        Object responseObject;

        if (StringUtils.isBlank(responseXML)) {
            throw new UnmarshalException("No XML passed to unmarshalAndValidate() for class " + responseClass.getName()) ;
        }

        // create an unmarshaller using the response class as the context.
        Unmarshaller unmarshaller = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(responseClass);
            unmarshaller = jaxbContext.createUnmarshaller();
        }
        catch (JAXBException e) {
            logger.error("unmarshalAndValidate() JAXBException when getting JAXBContext " +
                                          " : schema name=" + schemaName +
                                          " : responseClass=" + responseClass.getName(), e);
            throw e;
        }

        // if a schema name was provided, use it to validate the response.
        if (StringUtils.isNotBlank(schemaName)) {
            logger.trace("validating using schema " + schemaName);

            // get the schema to be used for validation.
            Schema schema;
            try {
                schema = fetchXMLSchema(schemaName);
            }
            catch (SAXException e) {
                logger.error("unmarshalAndValidate() SAXException when getting schema " +
                                              " : schema name=" + schemaName +
                                              " : responseClass=" + responseClass.getName(), e);
                throw new ValidationException("unmarshalAndValidate() SAXException when getting schema " +
                                              " : schema name=" + schemaName +
                                              " : responseClass=" + responseClass.getName(), e);
            }

            // set the schema into the unmarshaller and create a Validation event to handle errors.
            try {
                unmarshaller.setSchema(schema);
                unmarshaller.setEventHandler(new ValidationEventHandler() {
                    // allow unmarshalling to continue even if there are errors
                    public boolean handleEvent(ValidationEvent ve) {
                        // ignore warnings
                        if (ve.getSeverity() != ValidationEvent.WARNING) {
                            ValidationEventLocator vel = ve.getLocator();
                        }
                        return false;
                    }
                });
            }
            catch (JAXBException e) {
                logger.error("unmarshalAndValidate() JAXBException when setting validator " +
                                              " : schema name=" + schemaName +
                                              " : responseClass=" + responseClass.getName(), e);
                throw e;
            }
        }


        try {

            // by using an XMLStreamReader it is possble to unmarshall to any class regardless of whether it
            // has the @XmlRootElement annotation.
            StringReader responseReader = new StringReader(responseXML);
            XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(responseReader);
            JAXBElement root = unmarshaller.unmarshal( xmlStreamReader,responseClass);
            responseObject = root.getValue();
        }
        catch (UnmarshalException e) {
            logger.error("unmarshalAndValidate() UnmarshalException when unmarshalling " +
                                          " : schema name=" + schemaName +
                                          " : responseClass=" + responseClass.getName(), e);
            throw e;
        }
        catch (JAXBException e) {
            logger.error("unmarshalAndValidate() JAXBException when unmarshalling " +
                                          " : schema name=" + schemaName +
                                          " : responseClass=" + responseClass.getName(), e);
            throw e;
        }
        catch (Exception e) {
            logger.error("unmarshalAndValidate() unexpected Exception when unmarshalling " +
                                          " : schema name=" + schemaName +
                                          " : responseClass=" + responseClass.getName(), e);
            throw new ValidationException("unmarshalAndValidate() unexpected Exception when unmarshalling " +
                                          " : schema name=" + schemaName +
                                          " : responseClass=" + responseClass.getName(), e);
        }
        logger.trace("unmarshalled to " + responseObject);
        return responseObject;
    }

    /**
     * Returns an XML Schema object from the specified file name.
     * @param schemaFileName
     * @return
     * @throws SchemaNotFoundException
     * @throws SAXException
     */
    private static Schema fetchXMLSchema(String schemaFileName) throws SchemaNotFoundException,SAXException {

        logger.trace("loading schemaFileName=" + schemaFileName + " via Thread  " + Thread.currentThread().getName());

        Schema schema = null;
        InputStream in = null;
        Source schemaSource = null;

         /*
        NOTE - If you are running this from within the IntelliJ 'Run' command and are having
            problems with it not finding the files, do the followinf

            go to File... Settings... Compiler and at the top is a box with resource patterns in it.
            Add the file pattern you need, e.g ?*.xsd to it and it will work
         */

        try {
            
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName);

            logger.trace("got InputStream=" + in);
            if (in == null) {
                throw new SchemaNotFoundException("ValidateSchema.fetchXMLSchema() unable to open file " + schemaFileName);
            }

            schemaSource = new StreamSource(in);
            schemaSource.setSystemId(Thread.currentThread().getContextClassLoader().getResource(schemaFileName).toExternalForm());

            logger.trace("got schemaSource=" + schemaSource);

            SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            schema  = sf.newSchema(schemaSource);

        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException e) {
                    logger.debug("Unable to close inputStream.", e);
                }
            }
            in = null;
            schemaSource = null;
        }
        return schema;
    }
}

