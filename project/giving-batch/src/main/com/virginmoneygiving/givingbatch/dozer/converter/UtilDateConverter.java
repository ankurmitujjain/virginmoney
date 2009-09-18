
package com.virginmoneygiving.givingbatch.dozer.converter;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import net.sf.dozer.util.mapping.MappingException;
import net.sf.dozer.util.mapping.converters.CustomConverter;

/**
 * The Class UtilDateConverter.
 * 
 * @author Siva Kumar
 */
public class UtilDateConverter implements CustomConverter {
    
    /** Logger to log events. */
    private static final Logger LOGGER =
            Logger.getLogger(UtilDateConverter.class);
    
    /**
     * Implementation for
     * {@link CustomConverter#convert(Object, Object, Class, Class)}.
     * 
     * @param destination the destination object
     * @param source the source object
     * @param destinationClass the destination class
     * @param sourceClass the source class
     * 
     * @return object
     */
    public Object convert(Object destination, Object source,
            Class destinationClass, Class sourceClass) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("UtilDateConverter::convert() method - START");
        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("INPUT destination=" + destination );
            LOGGER.debug("INPUT source="+ source );
        }
        Object returnObject = null;
        if (source != null) {
            if (source instanceof java.util.Date) {
                try {
                    Date sqlDate = (Date) source;
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.setTimeInMillis(sqlDate.getTime());
                    XMLGregorianCalendar calendar =
                            DatatypeFactory.newInstance()
                                    .newXMLGregorianCalendar(cal);
                    returnObject = calendar;
                }
                catch (DatatypeConfigurationException e) {
                    throw new MappingException(e);
                }
            }
            else if (source instanceof javax.xml.datatype.XMLGregorianCalendar) {
                returnObject =
                        new Date(
                                ((javax.xml.datatype.XMLGregorianCalendar) source)
                                        .toGregorianCalendar()
                                        .getTimeInMillis());
            }
            else {
                throw new MappingException(
                        "Converter DateConverter used incorrectly. Arguments passed in were:"
                                + destination + " and " + source);
            }
        }
        return returnObject;
    }
}
