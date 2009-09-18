package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The data object that represents charity off line registration data.
 * 
 * @author Mahesh Yerudkar
 */
@Entity
@Table(name = "CHARITY_OFFLINE_REG")
public class CharityOfflineRegistration extends AuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Primary key for Charity Off line Registration. */
    private Long id;

    /** Charity. */
    private Charity charity;

    /** Charity HMRC Reference Number. */
    private String charityHmrcRefNumber;

    /** VMG HMRC Reference Number. */
    private String vmgHmrcRefNumber;

    /** Bank Statement Status. */
    private OfflineRegStatus bankStatementStatus;

    /** Charity Verify Status. */
    private OfflineRegStatus charityVerifyStatus;

    /** Gift Form Status. */
    private OfflineRegStatus giftFormStatus;

    /** HMRC Form Status. */
    private OfflineRegStatus hmrcFormStatus;

    /** Registration Fee Status. */
    private OfflineRegStatus regFeeStatus;

    /** Charity Off Line Registration Log list. */
    private Set<CharityOfflineRegistrationLog> charityOfflineRegistrationLogs;

    /** Charity Off Line Registration Notes list. */
    private Set<CharityOfflineRegistrationNotes> charityOfflineRegistrationNotes;
    
    /** Trustee status. */
    private TrusteeStatus trusteeStatus;

    /**
     * Gets the id.
     * 
     * @return the id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the charity.
     * 
     * @return the charity object.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_ID")
    public Charity getCharity() {
        return charity;
    }

    /**
     * Sets the charity.
     * 
     * @param charity the charity to set.
     */
    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    /**
     * Gets the charity hmrc ref number.
     * 
     * @return the string Charity HMRC Reference Number.
     */
    @Column(name = "CHARITY_HMRC_REF_NO")
    public String getCharityHmrcRefNumber() {
        return charityHmrcRefNumber;
    }

    /**
     * Sets the charity hmrc ref number.
     * 
     * @param charityHmrcRefNumber the Charity HMRC Reference Number to set.
     */
    public void setCharityHmrcRefNumber(String charityHmrcRefNumber) {
        this.charityHmrcRefNumber = charityHmrcRefNumber;
    }

    /**
     * Gets the vmg hmrc ref number.
     * 
     * @return the string VMG HMRC Reference Number.
     */
    @Column(name = "VMG_HMRC_REF_NO")
    public String getVmgHmrcRefNumber() {
        return vmgHmrcRefNumber;
    }

    /**
     * Sets the vmg hmrc ref number.
     * 
     * @param vmgHmrcRefNumber the VMG HMRC Reference Number to set.
     */
    public void setVmgHmrcRefNumber(String vmgHmrcRefNumber) {
        this.vmgHmrcRefNumber = vmgHmrcRefNumber;
    }

    /**
     * Gets the bank statement status.
     * 
     * @return the string Bank Statement Status.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_STATEMENT_STATUS")
    public OfflineRegStatus getBankStatementStatus() {
        return bankStatementStatus;
    }

    /**
     * Sets the bank statement status.
     * 
     * @param bankStatementStatus the Bank Statement Status.
     */
    public void setBankStatementStatus(OfflineRegStatus bankStatementStatus) {
        this.bankStatementStatus = bankStatementStatus;
    }

    /**
     * Gets the charity verify status.
     * 
     * @return the string Charity Verification Status.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_VERIFY_STATUS")
    public OfflineRegStatus getCharityVerifyStatus() {
        return charityVerifyStatus;
    }

    /**
     * Sets the charity verify status.
     * 
     * @param charityVerifyStatus Charity Verification Status.
     */
    public void setCharityVerifyStatus(OfflineRegStatus charityVerifyStatus) {
        this.charityVerifyStatus = charityVerifyStatus;
    }

    /**
     * Gets the gift form status.
     * 
     * @return the string Gift form Status.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GIFT_FORM_STATUS")
    public OfflineRegStatus getGiftFormStatus() {
        return giftFormStatus;
    }

    /**
     * Sets the gift form status.
     * 
     * @param giftFormStatus Gift form Status.
     */
    public void setGiftFormStatus(OfflineRegStatus giftFormStatus) {
        this.giftFormStatus = giftFormStatus;
    }

    /**
     * Gets the hmrc form status.
     * 
     * @return the string HMRC Form Status.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HMRC_FORM_STATUS")
    public OfflineRegStatus getHmrcFormStatus() {
        return hmrcFormStatus;
    }

    /**
     * Sets the hmrc form status.
     * 
     * @param hmrcFormStatus HMRC Form Status.
     */
    public void setHmrcFormStatus(OfflineRegStatus hmrcFormStatus) {
        this.hmrcFormStatus = hmrcFormStatus;
    }

    /**
     * Gets the reg fee status.
     * 
     * @return the string Registration Fee Status.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REG_FEE_STATUS")
    public OfflineRegStatus getRegFeeStatus() {
        return regFeeStatus;
    }

    /**
     * Sets the reg fee status.
     * 
     * @param regFeeStatus Registration Fee Status to set.
     */
    public void setRegFeeStatus(OfflineRegStatus regFeeStatus) {
        this.regFeeStatus = regFeeStatus;
    }

    /**
     * Gets the charity offline registration logs.
     * 
     * @return the Charity Off line Registration Logs
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CHARITY_OFFLINE_REG_ID")
    public Set<CharityOfflineRegistrationLog> getCharityOfflineRegistrationLogs() {
        return charityOfflineRegistrationLogs;
    }

    /**
     * Sets the charity offline registration logs.
     * 
     * @param charityOfflineRegistrationLogs charityOfflineRegistrationLog Objects.
     */
    public void setCharityOfflineRegistrationLogs(
            Set<CharityOfflineRegistrationLog> charityOfflineRegistrationLogs) {
        this.charityOfflineRegistrationLogs = charityOfflineRegistrationLogs;
    }

    /**
     * Gets the charity offline registration notes.
     * 
     * @return the Charity Off line Registration Notes
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CHARITY_OFFLINE_REG_ID")
    public Set<CharityOfflineRegistrationNotes> getCharityOfflineRegistrationNotes() {
        return charityOfflineRegistrationNotes;
    }

    /**
     * Sets the charity offline registration notes.
     * 
     * @param charityOfflineRegistrationNotes charityOfflineRegistrationNotes Objects.
     */
    public void setCharityOfflineRegistrationNotes(
            Set<CharityOfflineRegistrationNotes> charityOfflineRegistrationNotes) {
        this.charityOfflineRegistrationNotes = charityOfflineRegistrationNotes;
    }
    
    /**
     * Gets the trustee status.
     * 
     * @return the trusteeStatus
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TRUSTEE_STATUS_CODE")
    public TrusteeStatus getTrusteeStatus() {
        return trusteeStatus;
    }

    /**
     * Sets the trustee status.
     * 
     * @param trusteeStatus the trusteeStatus to set
     */
    public void setTrusteeStatus(TrusteeStatus trusteeStatus) {
        this.trusteeStatus = trusteeStatus;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("CharityOfflineRegistration ( ").append(
                "id = ").append(this.id).append(tab).append("charity = ")
                .append(this.charity).append(tab).append(
                        "charityHmrcRefNumber = ").append(
                        this.charityHmrcRefNumber).append(tab).append(
                        "vmgHmrcRefNumber = ").append(this.vmgHmrcRefNumber)
                .append(tab).append("bankStatementStatus = ").append(
                        this.bankStatementStatus).append(tab).append(
                        "charityVerifyStatus = ").append(
                        this.charityVerifyStatus).append(tab).append(
                        "giftFormStatus = ").append(this.giftFormStatus)
                .append(tab).append("hmrcFormStatus = ").append(
                        this.hmrcFormStatus).append(tab).append(
                        "regFeeStatus = ").append(this.regFeeStatus)                
                .append("trusteeStatus = ").append(trusteeStatus)
                .append(tab)
                .append(super.toString()).append(" )").toString();
    }

}
