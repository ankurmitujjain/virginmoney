package com.virginmoney.addresslookup.business;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 * Represents the search criteria used for an address search.
 *
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */

public class AddressSearchParameters {

    private static Logger logger = Logger.getLogger(AddressSearchParameters.class);


    private String searchPostcode;
    private String searchBuilding;
    // all the variable below are held as enums to enforce type-safety.
    private ServiceProviders serviceProvider = defaultServiceProvider;
    private SortMatchingResultRules sortMatchingResultRule = defaultSortMatchingResultRule;
    private ReturnMatchingResultRules returnMatchingResultRule = defaultReturnMatchingResultRule;
    private ReturnPAFDataRules returnPAFDataRule = defaultReturnPAFDataRule;
    private ResultFormats resultFormat = defaultResultFormat;

    /**
     * Creates a default search request object for a building and optional postcode, with all other parameters set to their
     * default values.
     * @param searchPostCode             the postcode to search for.
     * @param searchBuilding bulding name / number to use in the search, if not supplied then all results matching the
     */
        public AddressSearchParameters(String searchPostCode,
                                   String searchBuilding)
        {
            this(searchPostCode,searchBuilding,null,null,null,null,null);
        }

    /**
     * Construct a search request for a postcode, with optional building name/number and optional search provider.
     *
     * @param searchPostCode             the postcode to search for.
     * @param searchBuilding bulding name / number to use in the search, if not supplied then all results matching the
    *  postcode will be returned.
     * @param provider                   search provider , if null the default provider is
     * @param resultFormat               result address format, if null the default value is
   {@link ResultFormats#STYLE1}
     * @param sortMatchingResultRule  Indicates if only results which contain the value of searchBuilding are to be
    *  returned; if not supplied, or there is no search bulding, it defaults to {@link SortMatchingResultRules#dontSort}.
     * @param returnMatchingResultRule  Indicates if results which contain the value of searchBuilding are to be
    *  sorted to the start of the results list;  if not supplied, or there is no search bulding, it defaults to
    {@link ReturnMatchingResultRules#allResults}.
     * @param returnPAFDataRule              Indicates if unformatted PAF Address data is to be returned, if not supplied it defaults
    *  to {@link ReturnPAFDataRules#noPAFData}
     */
    public AddressSearchParameters(String searchPostCode,
                                   String searchBuilding,
                                   ResultFormats resultFormat,
                                   ServiceProviders provider,
                                   ReturnMatchingResultRules returnMatchingResultRule,
                                   SortMatchingResultRules sortMatchingResultRule,
                                   ReturnPAFDataRules returnPAFDataRule)
    {
        this.searchPostcode = searchPostCode;

        this.searchBuilding = searchBuilding;

        this.serviceProvider = (provider == null? defaultServiceProvider : provider);

        // IFDS provider only supports Style 1 addresses
        if (this.serviceProvider == ServiceProviders.IFDS) {
            this.resultFormat = ResultFormats.STYLE1;
        }
        else
        {
            this.resultFormat = (resultFormat == null ? defaultResultFormat : resultFormat);
        }


        // return only matching results and sort matching results are  set if it has a value and there is a search building
        if (StringUtils.isEmpty(searchBuilding)) {
              this.returnMatchingResultRule = defaultReturnMatchingResultRule;
              this.sortMatchingResultRule = defaultSortMatchingResultRule;
        }
        else
        {
            // if they are supplied then set them, otherwise use the defaults.
            this.returnMatchingResultRule = (returnMatchingResultRule != null ? returnMatchingResultRule : defaultReturnMatchingResultRule);
            this.sortMatchingResultRule = (sortMatchingResultRule != null ? sortMatchingResultRule : defaultSortMatchingResultRule);
        }

        // IFDS Provider does not support returning of PAF data
        if (this.serviceProvider == ServiceProviders.IFDS) {
            this.returnPAFDataRule = ReturnPAFDataRules.noPAFData;
        }
        else
       {
             this.returnPAFDataRule = (returnPAFDataRule != null ? returnPAFDataRule : defaultReturnPAFDataRule);
       }

    }

    /**
     * Getter for property 'searchPostCode'.
     *
     * @return Value for property 'searchPostCode'.
     */
    public String getSearchPostcode() {
        return searchPostcode;
    }

    /**
     * Getter for property 'searchBuilding'.
     *
     * @return Value for property 'searchBuilding'.
     */
    public String getSearchBuilding() {
        return searchBuilding;
    }

    /**
     * Getter for property 'provider'.
     *
     * @return Value for property 'provider'.
     */
    public ServiceProviders getServiceProvider() {
        return serviceProvider;
    }


    /**
     * Getter for property 'resultFormat'.
     *
     * @return Value for property 'resultFormat'.
     */
    public ResultFormats getResultFormat() {
        return resultFormat;
    }

    /**
     * Getter for property 'returnMatchingResultRule'.
     *
     * @return Value for property 'returnMatchingResultRule'.
     */
    public ReturnMatchingResultRules getReturnMatchingResultRule() {
        return returnMatchingResultRule;
    }

    /**
     * Utility method to return a boolean value which indicates if only matching addresses should be returned.
     * @return true if only matching addresses should be returned.
     */
    public boolean returnOnlyMatchingResults() {
        return returnMatchingResultRule.equals(ReturnMatchingResultRules.onlyMatchingResults);
    }


   /**
    * Getter for property 'sortMatchingResultRule'.
    *
    * @return Value for property 'sortMatchingResultRule'.
    */
   public SortMatchingResultRules getSortMatchingResultRule() {
        return sortMatchingResultRule;
    }

    /**
     * Utility method to return a boolean value which indicates if matching addresses should be sorted to the start
     * of the results list.
     * @return true if matching addresses should be sorted to the start of the results list.
     */
    public boolean sortMatchingResults() {
        return sortMatchingResultRule.equals(SortMatchingResultRules.sort);
    }

     /**
      * Getter for property 'returnPAFDataRule'.
      *
      * @return Value for property 'returnPAFDataRule'.
      */
     public ReturnPAFDataRules getReturnPAFDataRule() {
        return returnPAFDataRule;
    }

    /**
     * Utility method to return a boolean value which indicates if matching addresses should be sorted to the start
     * of the results list.
     * @return true if matching addresses should be sorted to the start of the results list.
     */
    public boolean returnPAFData() {
        return returnPAFDataRule.equals(ReturnPAFDataRules.returnPAFData);
    }


    /**
     * toString() method generated by IntelliJ GenerateToString plugin.
     *
     * @return A String representation of this instance.
     */
    public String toString() {
        return "AddressSearchParameters{" +
                "searchPostcode='" + searchPostcode + '\'' +
                ", searchBuilding='" + searchBuilding + '\'' +
                ", serviceProvider=" + serviceProvider +
                ", resultFormat=" + resultFormat +
                ", sortMatchingResults=" + sortMatchingResultRule +  " = " + sortMatchingResults() +
                ", returnOnlyMatchingResults=" + returnMatchingResultRule +   " = " + returnOnlyMatchingResults() +
                ", returnPAFData=" + returnPAFDataRule +  " = " + returnPAFData() +
                '}';
    }

    /**
         * Defines the list of available address search providers.
         */
        public enum ServiceProviders {
            /**
             * Postcode Anywhere search provider
             */
            PostcodeAnywhere("postcodeAnywhere.accountCode", "postcodeAnywhere.licenseKey"),
            /** Postcode Anywhere search provider for Virgin Money Giving website */
            VMGivingConsumer(
                    "postcodeAnywhere.VMGivingConsumer.accountCode",
                    "postcodeAnywhere.VMGivingConsumer.licenseKey"),
            /** Postcode Anywhere search provider for Virgin Money Giving call centre */
            VMGivingOperator(
                    "postcodeAnywhere.VMGivingOperator.accountCode",
                    "postcodeAnywhere.VMGivingOperator.licenseKey"),
            /**
             * IFDS search provider
             */
            IFDS ();



        // this needs a little workbecause it only works for postcode anywhere
        String accountCodePropertyName;
        String licenseKeyPropertyName;
        ServiceProviders () {} // default empty constructor

        /** construct an instance of this enum with the property names needed to obtain
         * the account code and license key. Only valid for postcode anywhere at present.
         * @param _accountCodePropertyName Postcode anywhere account code property name
         * @param _licensekeyPropertyName  Postcode anywhere license key property name
         */
        ServiceProviders (String _accountCodePropertyName,String _licensekeyPropertyName) {
              this.accountCodePropertyName = _accountCodePropertyName;
              this.licenseKeyPropertyName = _licensekeyPropertyName;
        }

        /** return the enum property which indicates the property name that holds the postcode anywhere account code */
        public String getAccountCodePropertyName() {
            return accountCodePropertyName;
        }

        /** return the enum property which indicates the property name that holds the postcode anywhere license key */
        public String getLicenseKeyPropertyName() {
            return licenseKeyPropertyName;
        }

    }


    /** Default value for {@link ServiceProviders}  */
    public static final ServiceProviders defaultServiceProvider = ServiceProviders.PostcodeAnywhere;

    /** Defines the list of supported formats that addresses will be returned in */
        public enum ResultFormats {
            /** Style 1; see {@link com.virginmoney.addresslookup.ws.AddressLookupServiceEndPoint} for details */
            STYLE1,
            /** Style 2; see {@link com.virginmoney.addresslookup.ws.AddressLookupServiceEndPoint} for details */
            STYLE2;
    }

    /** Default value for {@link ResultFormats}  */
   public static final ResultFormats defaultResultFormat = ResultFormats.STYLE1;

    /**
         * Defines whether to return all search results or only ones which match search bulding name; this is an enum
         * to ensure type-safety in the constructor.
         *
         */
        public enum ReturnMatchingResultRules {
            /** If a value for searchBuilding is provided, then return only those addresses which match it */
            onlyMatchingResults,
            /** Return all addresses */
            allResults;

    }

    /** Default value for {@link ReturnMatchingResultRules}  */
   public static final ReturnMatchingResultRules defaultReturnMatchingResultRule = ReturnMatchingResultRules.allResults;

    /**
         * Defines whether to return all search results or only ones which match search bulding name; this is an enum
         * to ensure type-safety in the constructor.
         *
         */
        public enum SortMatchingResultRules {
            /** If a value for searchBuilding is provided, then return only those addresses which match it */
            sort,
            /** Return all addresses */
            dontSort;
    }

    /** Default value for {@link SortMatchingResultRules}  */
    public static final SortMatchingResultRules defaultSortMatchingResultRule = SortMatchingResultRules.sort;

    /**
         * Defines whether to return all search results or only ones which match search bulding name; this is an enum
         * to ensure type-safety in the constructor.
         *
         */
        public enum ReturnPAFDataRules {
            /** If a value for searchBuilding is provided, then return only those addresses which match it */
            returnPAFData,
            /** Return all addresses */
            noPAFData

    }

    /** Default value for {@link ReturnPAFDataRules}  */
    private static final ReturnPAFDataRules defaultReturnPAFDataRule = ReturnPAFDataRules.noPAFData;



}
