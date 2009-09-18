package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the bank account to be persisted.
 * 
 * @author Rahul Vaidya
 * @author Puneet Swarup - added missing columns and modified annotation
 * parameters.
 */
@Entity
@Table(name = "BANK_ACCOUNT")
@Audited
public class BankAccount extends EnversAssociationAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -7937225173659753253L;

    /** Primary key for bank account. */
    private Long id;

    /** Referred bank. */
    private Bank bank;

    /** The bank account type. */
    private BankAccountType bankAccountType;

    /** Charity. */
    private Charity charity;

    /** Account sort code. */
    private String sortCode;

    /** Account number. */
    private String accountNumber;

    /** Account name. */
    private String accountName;

    /** Account decsription. */
    private String accountDescription;

    /** Default account indicator. */
    private String defaultAccountIndicator;

    /** Flag to check that account requested for change status. */
    private BankAccountStatus accountStatus;

    /** Bank socitey roll number. */
    private String buildingSocietyRollNumber;

    /** Account holder name. */
    private String accountHolderName;

    /** Indicates if an amendment has been carried out on the bank acocunt. */
    private String bankAccountUpdateInd;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the bank.
     * 
     * @return the bank
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ID")
    @Audited
    public Bank getBank() {
        return bank;
    }

    /**
     * Sets the bank.
     * 
     * @param bank the bank to set
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * Gets the bank account type.
     * 
     * @return the bankAccountType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ACCOUNT_TYPE_CODE")
    @Audited
    public BankAccountType getBankAccountType() {
        return bankAccountType;
    }

    /**
     * Sets the bank account type.
     * 
     * @param bankAccountType the bankAccountType to set
     */
    public void setBankAccountType(BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    /**
     * Gets the charity.
     * 
     * @return the charity
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_ID")
    @NotAudited
    public Charity getCharity() {
        return charity;
    }

    /**
     * Sets the charity.
     * 
     * @param charity the charity to set
     */
    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    /**
     * Gets the sort code.
     * 
     * @return the sortCode
     */
    @Column(name = "SORT_CODE")
    @Audited
    public String getSortCode() {
        return sortCode;
    }

    /**
     * Sets the sort code.
     * 
     * @param sortCode the sortCode to set
     */
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    /**
     * Gets the account number.
     * 
     * @return the accountNumber
     */
    @Column(name = "ACCOUNT_NUMBER")
    @Audited
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number.
     * 
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the account name.
     * 
     * @return the accountName
     */
    @Column(name = "ACCOUNT_NAME")
    @Audited
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the account name.
     * 
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Gets the account description.
     * 
     * @return the accountDescription
     */
    @Column(name = "ACCOUNT_DESCRIPTION")
    @Audited
    public String getAccountDescription() {
        return accountDescription;
    }

    /**
     * Sets the account description.
     * 
     * @param accountDescription the accountDescription to set
     */
    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    /**
     * Gets the default account indicator.
     * 
     * @return the defaultAccountIndicator
     */
    @Column(name = "DEFAULT_ACCOUNT_IND")
    @Audited
    public String getDefaultAccountIndicator() {
        if (defaultAccountIndicator == null) {
            defaultAccountIndicator = "N";
        }
        return defaultAccountIndicator;
    }

    /**
     * Sets the default account indicator.
     * 
     * @param defaultAccountIndicator the defaultAccountIndicator to set
     */
    public void setDefaultAccountIndicator(String defaultAccountIndicator) {
        this.defaultAccountIndicator = defaultAccountIndicator;
    }

    /**
     * Gets the account status.
     * 
     * @return the accountStatus
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ACCOUNT_STATUS_CODE")
    @NotAudited
    public BankAccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the account status.
     * 
     * @param accountStatus the accountStatus to set
     */
    public void setAccountStatus(BankAccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * Gets the building society roll number.
     * 
     * @return the buildingSocietyRollNumber
     */
    @Column(name = "BUILDING_SOCIETY_ROLL_NUMBER")
    @NotAudited
    public String getBuildingSocietyRollNumber() {
        return buildingSocietyRollNumber;
    }

    /**
     * Sets the building society roll number.
     * 
     * @param buildingSocietyRollNumber the buildingSocietyRollNumber to set
     */
    public void setBuildingSocietyRollNumber(String buildingSocietyRollNumber) {
        this.buildingSocietyRollNumber = buildingSocietyRollNumber;
    }

    /**
     * Gets the account holder name.
     * 
     * @return the accountHolderName
     */
    @Column(name = "ACCOUNT_HOLDER_NAME")
    @Audited
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Sets the account holder name.
     * 
     * @param accountHolderName the accountHolderName to set
     */
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    /**
     * Gets the bankAccountUpdateInd property.
     * 
     * @return String value
     */
    @Column(name = "BANKACCOUNT_AMENDMENT_IND")
    @NotAudited
    public String getBankAccountUpdateInd() {
        return bankAccountUpdateInd;
    }

    /**
     * Sets the bankAccountUpdateInd property.
     * 
     * @param bankAccountUpdateInd value to set
     */
    public void setBankAccountUpdateInd(String bankAccountUpdateInd) {
        this.bankAccountUpdateInd = bankAccountUpdateInd;
    }
}
