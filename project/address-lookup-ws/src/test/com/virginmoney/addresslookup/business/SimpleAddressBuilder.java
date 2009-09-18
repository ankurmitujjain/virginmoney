package com.virginmoney.addresslookup.business;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Builds a List of R {@link com.virginmoney.addresslookup.business.PAFData} objects which can be used in testing
 *
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class SimpleAddressBuilder {

    private static Logger logger = Logger.getLogger(SimpleAddressBuilder.class);

    /**
     * Builds an {@AddressList} object using data from an IFDS call.
     *
     * @return an {@AddressList} object using data from an IFDS call.
     */
    public static AddressList buildAddressListFromSimpleAddresses(AddressSearchParameters searchParameters) {

        AddressList addressList = new AddressList(searchParameters);
        List<AbstractAddress> addresses = addressList.getAddresses();
        addresses.clear();
        addresses.addAll(buildListOfSimpleAddresses(searchParameters));

        return addressList;

    }

    public static AddressSearchParameters buildSearchParameters(String searchBuilding) {
        AddressSearchParameters searchParameters = new AddressSearchParameters("NR14 8PA",searchBuilding);
        return searchParameters;
    }


    /**
     * Builds a list of {@link com.virginmoney.addresslookup.business.PAFData} objects using data from a postcode
     * anywhere call.
     *
     * @return a list of {@link com.virginmoney.addresslookup.business.PAFData} objects using data from a postcode
     *         anywhere call.
     */
    public static List<AddressStyle1> buildListOfSimpleAddresses(AddressSearchParameters searchParameters) {
        List<AddressStyle1> addresses = new ArrayList<AddressStyle1>();

        addresses.add(new AddressStyle1(searchParameters,"Tas Valley House", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Tara", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Holy Cross Vicarage", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Old Mill Restaurant", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Netherfield", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Jacama", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Honeycombe", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"High Beech House", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Greenways", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Torre", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Dalriada", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Craigley", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Cordovan", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"99 Mill Road", "Stoke Holy Cross", "Norwich", "Norfolk", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Mill Road Stores", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Mill House", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Stonecroft", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Purbeck Cottage", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Dovedale", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Low Meadow", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Post Office", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"The Enchanted Rose", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"Mill Cottage", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"The Blue Barn", "Mill Road", "Stoke Holy Cross", "Norwich", "NR14 8PA"));
        addresses.add(new AddressStyle1(searchParameters,"A R B G Dive Supplies,The Workshops,Mill House","Mill Road","Stoke Holy Cross", "Norwich","NR14 8PA"));

        return addresses;
    }
}

