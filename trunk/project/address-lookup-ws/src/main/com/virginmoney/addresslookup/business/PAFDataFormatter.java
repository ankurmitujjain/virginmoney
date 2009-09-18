package com.virginmoney.addresslookup.business;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 * Takes an unformatted PAF address such as that supplied by Postcode Anywhere and reformats it into a Virgin Money address.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class PAFDataFormatter {


    private static Logger logger = Logger.getLogger(PAFDataFormatter.class);

    public static AbstractAddress formatAddress(AddressSearchParameters searchParameters,
                           PAFData pafData) {

        AddressSearchParameters.ResultFormats resultFormat = searchParameters.getResultFormat();

        // the unformatted PAF address format is very detailed, so the first step is to amalgamate the various parts
        // into more manageable chunks.

        // Organisation name; varies according to whether there is a deparment or not
        String organisation = getOrganisation(pafData);

        // Premise name; varies according to whether there is a name or a number
        String premise = getPremiseName(pafData);

        // thoroughfare name; may be simple, e.g 'High Street' or may have a dependent thoroughfare,
        // e.g 'Norwich Business Park, Whiting Road'
        String thoroughfare = getThoroughfareName(pafData);
        
        // Po Box; its either present or it isnt
        String POBox = getPOBox(pafData);

        // locality (village etc)
        String locality = getLocality(pafData);

        // post town
        String postTown = pafData.getPostTown();

        //county
        String county = pafData.getCounty();

        debugAddressDetails(pafData, organisation, premise, thoroughfare, POBox, locality, postTown, county);

        switch (resultFormat) {
            case STYLE2: {
                return buildAddressStyle2(searchParameters,
                                          pafData,
                                          resultFormat,
                                          organisation,
                                          POBox,
                                          premise,
                                          thoroughfare,
                                          locality,
                                          postTown,
                                          county);
            }
            default: {
                return buildAddressStyle1(searchParameters,
                                          pafData,
                                          resultFormat,
                                          organisation,
                                          POBox,
                                          premise,
                                          thoroughfare,
                                          locality,
                                          postTown,
                                          county);
            }
        }

    }

    /**
     * Builds an address in Style 1 - four lines of address, plus seperate post code.
     * @param pafData  the original PAF data
     * @param resultFormat the required result format
     * @param organisation the organisaion name
     * @param POBox OPO box number string
     * @param premise  the premise name
     * @param thoroughfare    the thoroughfare name
     * @param locality  the locality
     * @param postTown post town
     * @param county county
     * @return  A Style 1 address containing the formatted data
     */
    private static AddressStyle1 buildAddressStyle1(AddressSearchParameters searchParameters,
                                                     PAFData pafData,
                                                     AddressSearchParameters.ResultFormats resultFormat,
                                                     String organisation,
                                                     String POBox,
                                                     String premise,
                                                     String thoroughfare,
                                                     String locality,
                                                     String postTown,
                                                     String county)
    {

        int maxLines = 4;
        int maxLineLength = 50;

        ArrayList<String> addressLines = new ArrayList<String>();

        // Organisation is always the first line, and on its own
        if (StringUtils.isNotBlank(organisation)) {
            addressLines.add(organisation);
        }

        // PO Box is always on its own
        if (StringUtils.isNotBlank(POBox)) {
            addressLines.add(POBox);
        }
        else {
            // If theres no PO box, check for a premise line
            StringBuffer premiseLine = formatPremiseLine(premise, thoroughfare, pafData);

            if (premiseLine.length() > 0) {
                addressLines.add(tidyBuffer(premiseLine));
            }
        }

        // locality is on its own, if there is room for it, otherwise it goes with post town and we drop county
        if (StringUtils.isNotBlank(locality)) {
            if (addressLines.size() >= maxLines) {
                addressLines.add(locality + " , " + postTown);
            }
            else {
                addressLines.add(locality);
            }
        }

        if (addressLines.size() == maxLines) {
            // last line already in use , append post town onto it, ignore county
            addressLines.set(addressLines.size() - 1, addressLines.get(addressLines.size() - 1) + ", postTown");
        }
        else if (addressLines.size() == maxLines - 1) {
            //  only one line left, put post town and county on it
            addressLines.add(postTown + ", " + county);
        }
        else {
            // room for both post town and county; add as seperate entries
            addressLines.add(postTown);
            addressLines.add(county);
        }

        // pad out to 4 lines
        while (addressLines.size() < maxLines) {
            addressLines.add(null);
        }

        return new AddressStyle1(searchParameters,
                addressLines.get(0),
                addressLines.get(1),
                addressLines.get(2),
                addressLines.get(3),
                pafData.getPostcode(),
                pafData);
    }

    /**
     * Builds an address in Style 2 - two lines of address, plus seperate post town, county, post code
     * and organisation name.
     * @param pafData  the original PAF data
     * @param resultFormat the required result format
     * @param organisation the organisaion name
     * @param POBox OPO box number string
     * @param premise  the premise name
     * @param thoroughfare    the thoroughfare name
     * @param locality  the locality
     * @param postTown post town
     * @param county county
     * @return  A Style 2 address containing the formatted data
     */
    private static AddressStyle2 buildAddressStyle2(AddressSearchParameters searchParameters,
                                                    PAFData pafData,
                                                    AddressSearchParameters.ResultFormats resultFormat,
                                                    String organisation,
                                                    String POBox,
                                                    String premise,
                                                    String thoroughfare,
                                                    String locality,
                                                    String postTown,
                                                    String county)
    {

        int maxLines = 2;
        int maxLineLength = 50;

        ArrayList<String> addressLines = new ArrayList<String>();

        if (StringUtils.isNotBlank(POBox)) {
            // PO Box is always on its own
            addressLines.add(POBox);
        }
        else {
            // If theres no PO box, check for a premise line
            StringBuffer premiseLine = formatPremiseLine(premise, thoroughfare, pafData);

            if (premiseLine.length() > 0) {
                addressLines.add(tidyBuffer(premiseLine));
            }
        }

        // locality is on its own, if there is room for it, otherwise it goes with post town and we drop county
        if (StringUtils.isNotBlank(locality)) {
            addressLines.add(locality);
        }

        // pad out to the maximum number of lines
        while (addressLines.size() < maxLines) {
            addressLines.add(null);
        }

        return new AddressStyle2(searchParameters,
                organisation,
                addressLines.get(0),
                addressLines.get(1),
                postTown,
                county,
                pafData.getPostcode(),
                pafData);

    }

    private static StringBuffer formatPremiseLine(String premise, String thoroughfare, PAFData pafData) {
        // the line with premise name / number and street name is the most complex
        // These are merged together to make one line
        StringBuffer premiseLine = new StringBuffer();
        if (StringUtils.isNotBlank(premise)) {
            premiseLine.append(premise);
        }

        // add the thoroughfare if there is one
        if (StringUtils.isNotBlank(thoroughfare)) {
            if (premiseLine.length() > 0) {
                // there is already a premise name or number on this line, so add a space or comma as appropriate
                if (StringUtils.isBlank(pafData.getBuildingNumber())) {
                                // named properties have a comma between the name and the thoroughfare
                                premiseLine.append(", ");
                            }
                            else {
                                premiseLine.append(" ");

                            }
            }
            premiseLine.append(thoroughfare);
        }
        return premiseLine;
    }

    private static void debugAddressDetails(PAFData pafData,
                                            String organisation,
                                            String premise,
                                            String thoroughfare,
                                            String POBox,
                                            String locality,
                                            String postTown,
                                            String county)
    {

        if (!logger.isTraceEnabled())
        {
            // dont log anything if the appender isnt set up to allow it
            return;
        }

        logger.trace(" ");
        logger.trace("Input :- ");
        logger.trace("      DepartmentName                   =" + pafData.getDepartmentName());
        logger.trace("      OrganisationName                 =" + pafData.getOrganisationName());
        logger.trace("      PostTown                         =" + pafData.getPostTown());
        logger.trace("      County                           =" + pafData.getCounty());
        logger.trace("      Postcode                         =" + pafData.getPostcode());
        logger.trace("      SubBuildingName                  =" + pafData.getSubBuildingName());
        logger.trace("      BuildingName                     =" + pafData.getBuildingName());
        logger.trace("      BuildingNumber                   =" + pafData.getBuildingNumber());
        logger.trace("      ThoroughfareName                 =" + pafData.getThoroughfareName());
        logger.trace("      ThoroughfareDescriptor           =" + pafData.getThoroughfareDescriptor());
        logger.trace("      DependentThoroughfareName        =" + pafData.getDependentThoroughfareName());
        logger.trace("      DependentThoroughfareDescriptor  =" + pafData.getDependentThoroughfareDescriptor());
        logger.trace("      DoubleDependentLocality          =" + pafData.getDoubleDependentLocality());
        logger.trace("      DependentLocality                =" + pafData.getDependentLocality());
        logger.trace("      POBoxNumber                      =" + pafData.getPoBoxNumber());

        logger.trace("Chunks :- ");
        logger.trace("      organisation                     =" + organisation  );
        logger.trace("      premise                          =" + premise);
        logger.trace("      thoroughfare                     =" + thoroughfare);
        logger.trace("      POBox                            =" + POBox         );
        logger.trace("      locality                         =" + locality      );
        logger.trace("      postTown                         =" + postTown      );
        logger.trace("      county                           =" + county        );
    }

    /** Extracts the organisation name from theunformatted PAF address.
     * <b/>
     * This varies slightly according to whether there is a department name, e.g. 'Virgin Money' or
     * 'IT and Change Department, Virgin Money'
     *
     * @param pafData Unformatted PAF address data.
     * @return the formatted organisation name, or blank if one is not present in theunformatted PAF address data.
     */
    private static String getOrganisation(PAFData pafData) {
        if (StringUtils.isBlank(pafData.getOrganisationName())) {
            return null;
        }
            StringBuffer buffer = new StringBuffer();

            if (StringUtils.isNotBlank(pafData.getDepartmentName())) {
                // has a department name so the detail becomes 'department, organisation'
                buffer.append(pafData.getDepartmentName());
                buffer.append(", ");
            }
                buffer.append(pafData.getOrganisationName());
          return tidyBuffer(buffer);
    }

    /**
     * Extracts the PO Box number from the unformatted PAF address. <b/>
     *
     * @param pafData Unformatted PAF address data.
     *
     * @return the formatted PO Box number name, or blank if one is not present in the unformatted PAF address data.
     */
    private static String getPOBox(PAFData pafData) {
        if (StringUtils.isBlank(pafData.getPoBoxNumber())) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();

        if (!StringUtils.containsIgnoreCase(pafData.getPoBoxNumber(), "PO") &&
                !StringUtils.containsIgnoreCase(pafData.getPoBoxNumber(), "Box")) {
            buffer.append("PO Box ");
        }
        buffer.append(pafData.getPoBoxNumber());

        return tidyBuffer(buffer);
    }

    /** Extracts the premise name from theunformatted PAF address.
     * <b/>
     * This varies slightly according to whether the property is named or numbered;  e.g 'Honeycombe, Mill road'
     * or '12 Mill Road'.
     *
     * @param pafData Unformatted PAF address data.
     * @return the formatted property name, or blank if one is not present in theunformatted PAF address data.
     */
    private static String getPremiseName(PAFData pafData) {
        // postcode anywhere do most of the work for us, so all we need to do is establish whether the property is
        // named or numbered, and add a traling comma if its named
        StringBuffer buffer = new StringBuffer();

        if (StringUtils.isNotBlank(pafData.getSubBuildingName())) {
            buffer.append(pafData.getSubBuildingName());
        }

        if (StringUtils.isNotBlank(pafData.getBuildingName())) {
            if (buffer.length() > 0) {
                buffer.append(", ");
            }
            buffer.append(pafData.getBuildingName());
        }

        if (StringUtils.isNotBlank(pafData.getBuildingNumber())) {
            if (buffer.length() > 0) {
                buffer.append(", ");
            }
            buffer.append(pafData.getBuildingNumber());
        }
        return tidyBuffer(buffer);
    }

    /** Extracts the road name from theunformatted PAF address.
     * <b/>
     * Road name may be simple, e.g 'High Street' or may have a dependent thoroughfare,
     * e.g 'Norwich Business Park, Whiting Road'.
     *
     * @param pafData Unformatted PAF address data.
     * @return the formatted road name, or blank if one is not present in theunformatted PAF address data.
     */
    private static String getThoroughfareName(PAFData pafData) {
        StringBuffer buffer = new StringBuffer();
        if (StringUtils.isNotBlank(pafData.getDependentThoroughfareName())) {
            buffer.append(pafData.getDependentThoroughfareName());

            // if there is a descriptor ('road','street' etc) then add it
            if (StringUtils.isNotBlank(pafData.getDependentThoroughfareDescriptor())) {
                buffer.append(" ");
                buffer.append(pafData.getDependentThoroughfareDescriptor());
            }
            buffer.append(", ");
        }

        if (StringUtils.isNotBlank(pafData.getThoroughfareName())) {
            buffer.append(pafData.getThoroughfareName());

            // if there is a descriptor ('road','street' etc) then add it
            if (StringUtils.isNotBlank(pafData.getThoroughfareDescriptor())) {
                buffer.append(" ");
                buffer.append(pafData.getThoroughfareDescriptor());
            }
        }
        return tidyBuffer(buffer);
    }

    /** Extracts the locality from theunformatted PAF address.
     * <b/>
     * Road name may be simple, e.g 'Horsford' or may have a dependent thoroughfare,
     * e.g 'Pendlebury, Swinton'.
     *
     * @param pafData Unformatted PAF address data.
     * @return the formatted locality name, or blank if one is not present in theunformatted PAF address data.
     */
    private static String getLocality(PAFData pafData) {
        StringBuffer buffer = new StringBuffer();
        if (StringUtils.isNotBlank(pafData.getDoubleDependentLocality())) {
            buffer.append(pafData.getDoubleDependentLocality());

            // if there is a descriptor ('road','street' etc) then add it
            if (StringUtils.isNotBlank(pafData.getDependentThoroughfareDescriptor())) {
                buffer.append(" ");
                buffer.append(pafData.getDependentThoroughfareDescriptor());
            }
            buffer.append(", ");
        }

        if (StringUtils.isNotBlank(pafData.getDependentLocality())) {
            buffer.append(pafData.getDependentLocality());
        }
        return tidyBuffer(buffer);
    }

    private static String tidyBuffer(StringBuffer buffer) {
        return StringUtils.replace(buffer.toString(),"  "," ").trim(); // remove multiple spaces
    }
}

