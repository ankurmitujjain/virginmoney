package com.virginmoney.addresslookup.business;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 * Extends the default address style by adding post town and post county. 
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class AddressStyle2 extends AbstractAddress {

    private static Logger logger = Logger.getLogger(AddressStyle2.class);

    private String organisationName;
    private String postTown;
    private String county;


    public AddressStyle2(AddressSearchParameters searchParameters,
                         String organisationName,
                         String addressLine1,
                         String addressLine2,
                         String postTown,
                         String county,
                         String postcode,
                         PAFData pafData)
    {
        super(searchParameters,addressLine1, addressLine2, postcode, pafData);
        this.organisationName = organisationName;
        this.postTown = postTown;
        this.county = county;
        if (logger.isTraceEnabled()){
            logger.trace("created address style 2");
            logger.trace("organisationName =" + organisationName);
            logger.trace("    addressLine1 =" + addressLine1);
            logger.trace("    addressLine2 =" + addressLine2);
            logger.trace("        postTown =" + postTown);
            logger.trace("          county =" + county);
            logger.trace("        postcode =" + postcode);
        }

    }

    public String getOrganisationName() {
        return organisationName;
    }

    public boolean isMatchesSearchBuilding() {
        if (StringUtils.isBlank(searchParameters.getSearchBuilding())) {
            return false;
        }

        // Search the address data for the building name
        // If paf data is present then use it, because sometimes organisation names and road names are similar
        // which causes 'false positives'. E.g. searching for 'Mill' might match against
        // 'Old Mill Restaurant, Mill Road' but also against 'Other House, Mill Road' , which is less correct.
         return super.isMatchesSearchBuilding() ||
               StringUtils.containsIgnoreCase(organisationName, searchParameters.getSearchBuilding());

    }

    public String getPostTown() {
        return postTown;
    }

    public String getCounty() {
        return county;
    }

    /**
     * Returns a 'picklist' entry for this address containing the address in a single line.
     * @return a 'picklist' entry for this address containing the address in a single line.
     */
    public String getPicklistEntry() {
        // populate the picklist entry

        StringBuffer buffer = new StringBuffer();
        buffer.append("");

        appendToBuffer(buffer, getOrganisationName());
        appendToBuffer(buffer, getAddressLine1());
        appendToBuffer(buffer, getAddressLine2());
        appendToBuffer(buffer, getPostTown());
        appendToBuffer(buffer, getCounty());
        appendToBuffer(buffer, getPostcode());

        return buffer.toString();
    }

    /**
     * toString() method generated by IntelliJ GenerateToString plugin.
     *
     * @return A String representation of this instance.
     */
    public String toString() {
        return "AddressStyle2{" + "addressLine1='" + addressLine1 + '\'' + ", addressLine2='" + addressLine2 + '\''
                + " postTown='" + postTown + '\'' + ", county='" + county + '\'' +
                ", postcode='" + postcode + '\'' + '}';
    }
}

