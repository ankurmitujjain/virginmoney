package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * The data object representing Bank Account Type Details.
 * 
 * @author taruna
 */
public class BankAccountType implements Serializable {

    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /** sort code - Bank Sort code. **/
    private String sortCode;

    /** accountNumber - Bank Account Number. **/
    private String accountNumber;

    /** bankName - Bank Name. **/
    private String bankName;

    /** branchName - Bank Branch Name. **/
    private String branchName;

    /** accountUniqueId - Account Unique Id. **/
    private String accountUniqueId;

    /** accountType - Type of Bank Account. **/
    private String accountType;

    /** primaryUserFlag - User Flag. **/
    private String primaryUserFlag;

    /** closeThisAccount - Flag for closing Account. **/
    private boolean closeThisAccount;
    
    /** Account name. */
    private String accountName;

    /**
     * Gets the sort code.
     * 
     * @return the sort code
     */
    public String getSortCode() {
        return sortCode;
    }

    /**
     * Sets the sort code.
     * 
     * @param sortCode the new sort code
     */
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    /**
     * Gets the account number.
     * 
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number.
     * 
     * @param accountNumber the new account number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the bank name.
     * 
     * @return the bank name
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the bank name.
     * 
     * @param bankName the new bank name
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * Gets the branch name.
     * 
     * @return the branch name
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Sets the branch name.
     * 
     * @param branchName the new branch name
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * Gets the account unique id.
     * 
     * @return the account unique id
     */
    public String getAccountUniqueId() {
        return accountUniqueId;
    }

    /**
     * Sets the account unique id.
     * 
     * @param accountUniqueId the new account unique id
     */
    public void setAccountUniqueId(String accountUniqueId) {
        this.accountUniqueId = accountUniqueId;
    }

    /**
     * Gets the account type.
     * 
     * @return the account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type.
     * 
     * @param accountType the new account type
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets the primary user flag.
     * 
     * @return the primary user flag
     */
    public String getPrimaryUserFlag() {
        return primaryUserFlag;
    }

    /**
     * Sets the primary user flag.
     * 
     * @param primaryUserFlag the new primary user flag
     */
    public void setPrimaryUserFlag(String primaryUserFlag) {
        this.primaryUserFlag = primaryUserFlag;
    }

    public boolean isCloseThisAccount() {
        return closeThisAccount;
    }

    /**
     * Sets the close this account.
     * 
     * @param closeThisAccount the new close this account
     */
    public void setCloseThisAccount(boolean closeThisAccount) {
        this.closeThisAccount = closeThisAccount;
    }

    /**
     * Gets the account name.
     * 
     * @return the account name
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the account name.
     * 
     * @param accountName the new account name
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
