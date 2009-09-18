package com.virginmoney.addresslookup.serviceproxy.ifds.builders;

import java.rmi.server.UID;
import org.apache.log4j.Logger;
import net.sf.dozer.util.mapping.DozerBeanMapper;

/**
 * This is a utility class to populate the content of the 'Header' element in an IFDS message call.
 *
 * Because each call has its own identical copy of Header this class uses Dozer to map from an inner class
 * to the instance which bas been passed in.
 *
 * @author woodsn
 *         <p/>
 *         Last change : $Date$  Revision : $Revision$  By : $Author$
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class IFDSHeaderPopulator {

    private static Logger logger = Logger.getLogger(IFDSHeaderPopulator.class);

    // although the mapHeader method is static , inner classes cannot be referenced statically nor can they
    // contain their own static objects, so the class needs to store a singleton instance of itself
    private static IFDSHeaderPopulator myself = new IFDSHeaderPopulator();
    private static IFDSHeaderTemplate template;
    private static DozerBeanMapper mapper = new DozerBeanMapper();

    public IFDSHeaderPopulator() {
        // create an instance of the template. This cant be done statically.
        template = new IFDSHeaderTemplate();
    }


    public static void mapHeader(Object header) {
//        logger.trace("mapHeader " + myself.hashCode()  + " mapping template : " + myself.getTemplate() + " to header " + header);
        mapper.map(myself.getTemplate(),header);

    }

    /**
     * Inner class which mirrors the structure of an IFDS header.
     * @return
     */
    private IFDSHeaderTemplate getTemplate() {
        return template;
    }

    public class IFDSHeaderTemplate {

    private String mc = "000045";
    private Source source = new Source();
    private String sessionID = "0";


    public String getMc() {
        return mc;
    }

    public Source getSource() {
        return source;
    }

    public String getSessionID() {
        return sessionID;
    }

    public String toString() {
        return "IFDSHeaderTemplate_internal {" + "mc='" + mc + '\'' + ", source=" + source + ", sessionID='" + sessionID + '\'' +
                '}';
    }

        /**
         * Inner class which mirrors the structure of the Header 'Source' item.
         */
    public class Source {

        private int systemInstance = 33;       //todo replace with system parameter
        private int subSystemInstance = 0;    //todo replace with system parameter
        private String requestUniqueID = "";

        public int getSystemInstance() {
            return systemInstance;
        }

        public int getSubSystemInstance() {
            return subSystemInstance;
        }

        public String getRequestUniqueID() {
            String uniqueId = "";
            try
            {
                UID uniqueID = new UID();
                String uidString = uniqueID.toString();
                int uidHashCode = uniqueID.hashCode();
                //System.out.println("Unique ID String: "+uidString+", HashCode = "+uidHashCode);
                uniqueId = ""+Math.abs(uidHashCode);

                // Schema can only handle 20-characters. So, truncate the first few digits.
                if (uniqueId.length() > 19)
                {
                    uniqueId = uniqueId.substring(uniqueId.length()-19);
                }

            }
            catch (IndexOutOfBoundsException e) {
                logger.debug("Index out of bounds exception.");
                logger.debug("Exception caught executing getRequestUniqueID.", e);
                uniqueId = "";
            }
            return uniqueId;

        }

        public String toString() {
            return "Source_internal{" + "systemInstance=" + systemInstance + ", subSystemInstance=" + subSystemInstance +
                    ", requestUniqueID='" + requestUniqueID + '\'' + '}';
        }
    }


}
}

