package com.virginmoneygiving.cardpayment.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import com.virginmoneygiving.cardpayment.service.messages.ServiceNameValuePair;
import net.sf.dozer.util.mapping.MappingException;
import net.sf.dozer.util.mapping.converters.CustomConverter;

/**
 * Allow for custom mapping of service tier NameValuePair objects to a Map. This
 * doesn't work properly in Dozer - we get 'generics' instead of Map<String,String>
 * etc. TODO: catch class cast exceptions
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class MapToNVPListCustomConverter implements CustomConverter {

    /** Logger for this class. */
    private static final Logger LOGGER = Logger
            .getLogger(MapToNVPListCustomConverter.class);

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.dozer.util.mapping.converters.CustomConverter#convert(java.lang
     *      .Object, java.lang.Object, java.lang.Class, java.lang.Class)
     */
    public Object convert(Object existingDestinationFieldValue,
            Object sourceFieldValue, Class destinationClass, Class sourceClass) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Source: " + sourceFieldValue + ", Target: "
                    + existingDestinationFieldValue);
        }

        if (sourceFieldValue != null && sourceFieldValue instanceof Map) {
            // map to list
            Map<String, String> sourceMap = (Map<String, String>) sourceFieldValue;
            List<ServiceNameValuePair> targetList;
            if (existingDestinationFieldValue == null) {
                targetList = new ArrayList<ServiceNameValuePair>();
            } else {
                targetList = (List<ServiceNameValuePair>) existingDestinationFieldValue;
                targetList.clear(); // to be prudent
            }

            // iterative mapping
            Iterator<Entry<String,String>> it = sourceMap.entrySet().iterator();
            while(it.hasNext()) {
                Entry<String,String> entry = it.next();
                ServiceNameValuePair nvp = new ServiceNameValuePair();
                nvp.setName(entry.getKey());
                nvp.setValue(entry.getValue());

                targetList.add(nvp);
            }

            return targetList;
        } else if (sourceFieldValue instanceof List) {
            // list to map
            List<ServiceNameValuePair> sourceList = (List<ServiceNameValuePair>) sourceFieldValue;
            Map<String, String> targetMap;

            if (existingDestinationFieldValue == null) {
                targetMap = new HashMap<String, String>();
            } else {
                targetMap = (Map<String, String>) existingDestinationFieldValue;
            }

            // iterative mapping
            for (ServiceNameValuePair nvp : sourceList) {
                targetMap.put(nvp.getName(), nvp.getValue());
            }

            return targetMap;
        } else if (existingDestinationFieldValue instanceof List) {
            List<ServiceNameValuePair> targetList;
            if (existingDestinationFieldValue == null) {
                targetList = new ArrayList<ServiceNameValuePair>();
            } else {
                targetList = (List<ServiceNameValuePair>) existingDestinationFieldValue;
                targetList.clear(); // to be prudent
            }

            return targetList;
        } else if (existingDestinationFieldValue instanceof Map) {
            // list to map
            Map<String, String> targetMap;

            if (existingDestinationFieldValue == null) {
                targetMap = new HashMap<String, String>();
            } else {
                targetMap = (Map<String, String>) existingDestinationFieldValue;
            }

            return targetMap;
        } else {
            throw new MappingException("Misconfigured/unsupported mapping");
        }
    }
}
