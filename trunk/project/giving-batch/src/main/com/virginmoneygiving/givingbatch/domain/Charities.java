package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The data object representing Charity details.
 * 
 * @author taruna
 */
public class Charities implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Charity status. * */
    private CharityStatusType charityStatus;

    /** Date of creation of charity. * */
    private Date transactionDate;

    /** The name of charity. * */
    private String charityName;

    /** Registration Number. * */
    private String registrationNumber;

    /** VMG Charity Ref Id. * */
    private String vmgCharityRefId;

    /** Category. * */
    private String category;

    /** Payment Group. * */
    private String paymentGroup;

    /** Registered Address. * */
    private CharityAddresses registeredAddress;

    /** Administration Address. * */
    private CharityAddresses administrationAddress;

    /** Bank Account. * */
    private List<BankAccountType> bankAccounts;

    /** Event reference. * */
    private Integer eventRef;

    /**
     * Gets the charity status.
     * 
     * @return the charityStatus
     */
    public CharityStatusType getCharityStatus() {
        return charityStatus;
    }

    /**
     * Sets the charity status.
     * 
     * @param charityStatus the charityStatus to set
     */
    public void setCharityStatus(CharityStatusType charityStatus) {
        this.charityStatus = charityStatus;
    }

    /**
     * Gets the transaction date.
     * 
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the transaction date.
     * 
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the charity name.
     * 
     * @return the charityName
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the charity name.
     * 
     * @param charityName the charityName to set
     */
    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    /**
     * Gets the registration number.
     * 
     * @return the registrationNumber
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the registration number.
     * 
     * @param registrationNumber the registrationNumber to set
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Gets the vmg charity ref id.
     * 
     * @return the vmgCharityRefId
     */
    public String getVmgCharityRefId() {
        return vmgCharityRefId;
    }

    /**
     * Sets the vmg charity ref id.
     * 
     * @param vmgCharityRefId the vmgCharityRefId to set
     */
    public void setVmgCharityRefId(String vmgCharityRefId) {
        this.vmgCharityRefId = vmgCharityRefId;
    }

    /**
     * Gets the category.
     * 
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category.
     * 
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the payment group.
     * 
     * @return the paymentGroup
     */
    public String getPaymentGroup() {
        return paymentGroup;
    }

    /**
     * Sets the payment group.
     * 
     * @param paymentGroup the paymentGroup to set
     */
    public void setPaymentGroup(String paymentGroup) {
        this.paymentGroup = paymentGroup;
    }

    /**
     * Gets the registered address.
     * 
     * @return the registeredAddress
     */
    public CharityAddresses getRegisteredAddress() {
        return registeredAddress;
    }

    /**
     * Sets the registered address.
     * 
     * @param registeredAddress the registeredAddress to set
     */
    public void setRegisteredAddress(CharityAddresses registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    /**
     * Gets the administration address.
     * 
     * @return the administrationAddress
     */
    public CharityAddresses getAdministrationAddress() {
        return administrationAddress;
    }

    /**
     * Sets the administration address.
     * 
     * @param administrationAddress the administrationAddress to set
     */
    public void setAdministrationAddress(CharityAddresses administrationAddress) {
        this.administrationAddress = administrationAddress;
    }

    /**
     * Gets the bank accounts.
     * 
     * @return the bankAccounts
     */
    public List<BankAccountType> getBankAccounts() {
        return bankAccounts;
    }

    /**
     * Sets the bank accounts.
     * 
     * @param bankAccounts the bankAccounts to set
     */
    public void setBankAccounts(List<BankAccountType> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    /**
     * Gets the event ref.
     * 
     * @return the eventRef
     */
    public Integer getEventRef() {
        return eventRef;
    }

    /**
     * Sets the event ref.
     * 
     * @param eventRef the eventRef to set
     */
    public void setEventRef(Integer eventRef) {
        this.eventRef = eventRef;
    }

}
