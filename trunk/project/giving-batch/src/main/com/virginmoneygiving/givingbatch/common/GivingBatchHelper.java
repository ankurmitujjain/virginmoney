package com.virginmoneygiving.givingbatch.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * This is a helper class which has commonly used methods like
 * validateInputString,prefixReferenceType.
 * 
 * @author Tarun Adiwal
 */
public class GivingBatchHelper {
    
    /**
     * Constructor for the class.
     */
    public GivingBatchHelper() {
    }

    /** Hold the prefix value which append to the reference number. */
    private Map<String, String> prefixMap;
    
    /** Hold the transaction type list. */
    private List<String> validTransactionTypeList;
    
    /** Hold the valid reference list. */
    private List<String> validReferenceList;
    
    /** Hold the batch status codes and values. */
    private Map<String, String> batchStatusesMap;

    /**
     * Method checks the inputs are valid if valid then return true else false.
     * 
     * @param transactionType valid transaction
     * type(WEBDONATION,GIFAID,TRANSITIONALRELIEF,CHARITYREGISTRATIONFEE
     * ,VMGTRANSACTIONFEE,TRANSACTIONCHARGE)
     * @param referenceType valid reference type(INVOICE,CREDITNOTE)
     * 
     * @return validInput return true if valid else false
     */
    public boolean validateInputString(final String transactionType,
            final String referenceType) {

        boolean validInput = false;

        if (validTransactionTypeList.contains(transactionType)
                && validReferenceList.contains(referenceType)) {
            validInput = true;
        }

        return validInput;
    }

    /**
     * Method append the corresponding String in front of the generated number
     * and append zero digit to after that to obtain a 15 digit reference
     * number. First three digit represent the transaction type and remaining
     * twelve will represent the reference number.
     * 
     * @param transactionType Transaction type
     * @param referenceType Reference Type
     * @param sequenceNumber generated number
     * 
     * @return generatedReferenceNumber
     */

    public String prefixReferenceType(final String transactionType,
            final String referenceType, final String sequenceNumber) {
        return new StringBuilder(generateReferenceType(transactionType,
                referenceType)).append(
                String.valueOf(StringUtils.leftPad(sequenceNumber,
                        12, "0")))
                .toString();

    }

    /**
     * Method generate a reference type according to the input provided.
     * 
     * @param transactionType Transaction type
     * @param referenceType Reference type
     * 
     * @return referenceId
     */
    private String generateReferenceType(String transactionType,
            String referenceType) {
        return prefixMap.get(referenceType.concat(transactionType));
    }

    /**
     * Sets the valid transaction type list.
     * 
     * @param validTransactionTypeList the new valid transaction type list
     */
    public void setValidTransactionTypeList(
            List<String> validTransactionTypeList) {
        this.validTransactionTypeList = validTransactionTypeList;
    }

    /**
     * Sets the valid reference list.
     * 
     * @param validReferenceList the valid reference list to set
     */
    public void setValidReferenceList(List<String> validReferenceList) {
        this.validReferenceList = validReferenceList;
    }

    /**
     * Sets the prefix map.
     * 
     * @param prefixMap map contains different reference types
     */
    public void setPrefixMap(Map<String, String> prefixMap) {
        this.prefixMap = prefixMap;
    }

    /**
     * Gets the  batchStatusesMap property.
     * 
     * @return batchStatusesMap Map
     */

    public Map<String, String> getBatchStatusesMap() {
        return batchStatusesMap;
    }

    /**
     * Sets the batchStatusesMap property.
     * 
     * @param batchStatusesMap value to set.
     */
    public void setBatchStatusesMap(Map<String, String> batchStatusesMap) {
        this.batchStatusesMap = batchStatusesMap;
    }

    /**
     * Retrieves the batch status code value from the map.
     * 
     * @param statusCode Code to retrieve
     * 
     * @return String value
     */
    public String fetchBatchStatusType(String statusCode) {
        String bs = batchStatusesMap.get(statusCode);
        if (bs == null) {
            return statusCode;
        }
        else {
            return bs;
        }
    }
}
