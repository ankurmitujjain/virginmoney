package com.virginmoneygiving.cardpayment.helper;

import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * String helper.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class DelimitedStringHandler {
    
    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(DelimitedStringHandler.class);

    /**
     * Convert delimited NVP text into a Map.
     * 
     * @param text delimited text
     * @param recSep record separator
     * @param nameSep separate name from value
     * 
     * @return the map< string, string>
     */
    public static Map<String, String> processNVP(String text, String recSep, String nameSep) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("processNVP(text=" + text + ", recSep=" + recSep + ", nameSep=" + nameSep + ")");
        }

        Map<String, String> map = new HashMap<String, String>();

        if (text == null) {
            // nothing to do
        } else {
            String[] records = text.split(recSep);
            for (String record : records) {
                String[] fields = record.split(nameSep);
    
                if (fields.length == 2) {
                    map.put(fields[0], fields[1]);
                }
                // TODO: else logging
            }
        }

        return map;
    }

    /**
     * Delimit a map.
     * <br>Notes:
     * <li>Using set ordering (hashcode)
     * <li>Includes a trailing recSep.
     * 
     * @param map the map
     * @param recSep the rec sep
     * @param nameSep the name sep
     * 
     * @return the string
     */
    public static String nvpMapToString(Map<String, String> map, String recSep, 
            String nameSep) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("nvpMapToString(recSep=" + recSep + ", nameSep=" + nameSep );
        }

        StringBuilder sb = new StringBuilder(); 

        // iterative mapping
        if (map != null) {
            // iterative mapping
            Iterator<Entry<String,String>> it = map.entrySet().iterator();
            while(it.hasNext()) {
                Entry<String,String> entry = it.next();
                sb.append(entry.getKey())
                .append(nameSep)
                .append(entry.getValue())
                .append(recSep);
                //TODO: remove stray recSep?
            }
        }

        return sb.toString();
    }
}
