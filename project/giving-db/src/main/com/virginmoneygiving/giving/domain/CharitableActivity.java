package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.sql.Date;
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

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The association between the {@link Event} and the {@link Charity},
 * {@link FundraiserActivity}, to define the charitable activity.
 * 
 * @author Edwin Tauro
 * @author Puneet Swarup - added audit attributes and toString.
 * @author Saurabh Herwadkar- added new members to serve event use cases
 * @author Sushant Sawant - added bankaccountfee, splitpercentage, alternatemanagefeeind
 */
@Entity
@Table(name = "CHARITABLE_ACTIVITY")
@Audited
public class CharitableActivity extends EnversAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Primary key for charitable_activity. */
    private Long id;

    /** Thank you message. */
    private String thankYouMessage;

    /** Fundraiser group indicator. */
    private String fundraiserGroupsInd;

    /** Charity. */
    private Charity charity;

    /** Event. */
    private Event event;

    /** Split percentage for each charity which is part of Event. */
    private Integer splitPercentage;

    /** Online entry limit for an Event. */
    private Integer onlineEntryLimit;

    /** Online entry closure date. */
    private Date onlineClosureDate;

    /** Indicator which tells whether creating or joining an event. */
    private String eventCreatorInd; // Added by Saurabh

    /** Fundraising Bank account for this event for particular charity. */
    private BankAccount bankAccountFundraising; // Added by Saurabh

    /** Event Fee Bank account for this event for particular charity. */
    private BankAccount bankAccountFees; // Added by Sushant

    /** Determines whether VMG would manage the fee or not. */
    private String vmgManageFeeInd; // Added by Saurabh

    /** Determines whether fundraiser can join an event by any other fee payment method. */
    private String alternateFeePayInd; // Added by Saurabh

    /** payment instructions to join an event. */
    private String paymentInstructions; // Added by Saurabh

    /** List to get fee details associated with this Charitable activity. */
    private Set<EventFeeDetails> eventFeeDetails; // Added by Saurabh

    /** List to get event administrator associated with this chairty and event. */
    private Set<CharityEventAdministrator> charityEventAdminDetails; // Added by
                                                                     // Saurabh
    /** All users indicator. */
    private String allUsersInd;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * Gets the thank you message.
     * 
     * @return the thank you message
     */
    @Column(name = "THANK_YOU_MESSAGE")
    @NotAudited
    public String getThankYouMessage() {
        return thankYouMessage;
    }

    /**
     * Sets the thank you message.
     * 
     * @param thankYouMessage the new thank you message
     */
    public void setThankYouMessage(String thankYouMessage) {
        this.thankYouMessage = thankYouMessage;
    }

    /**
     * Gets the fundraiser groups ind.
     * 
     * @return the fundraiser groups ind
     */
    @Column(name = "FUNDRAISER_GROUPS_IND")
    @Audited
    public String getFundraiserGroupsInd() {
        return fundraiserGroupsInd;
    }

    /**
     * Sets the fundraiser groups ind.
     * 
     * @param fundraiserGroupsInd the new fundraiser groups ind
     */
    public void setFundraiserGroupsInd(String fundraiserGroupsInd) {
        this.fundraiserGroupsInd = fundraiserGroupsInd;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_ID")
    @Audited
    public Charity getCharity() {
        return charity;
    }

    /**
     * Sets the charity.
     * 
     * @param charity the new charity
     */
    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    /**
     * Gets the event.
     * 
     * @return the event
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_ID")
    @Audited
    public Event getEvent() {
        return event;
    }

    /**
     * Sets the event.
     * 
     * @param event the new event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Gets the split percentage.
     * 
     * @return splitPercentage Percentage split for each charity
     */
    @Column(name = "SPLIT_PERCENTAGE")
    @Audited
    public Integer getSplitPercentage() {
        return splitPercentage;
    }

    /**
     * Sets the split percentage.
     * 
     * @param splitPercentage Percentage split for each charity
     */
    public void setSplitPercentage(Integer splitPercentage) {
        this.splitPercentage = splitPercentage;
    }

    /**
     * Gets the online entry limit.
     * 
     * @return onlineEntryLimit onlineEntryLimit
     */
    @Column(name = "ONLINE_ENTRY_LIMIT")
    @Audited
    public Integer getOnlineEntryLimit() {
        return onlineEntryLimit;
    }

    /**
     * Sets the online entry limit.
     * 
     * @param onlineEntryLimit Online entry limit for event.
     */
    public void setOnlineEntryLimit(Integer onlineEntryLimit) {
        this.onlineEntryLimit = onlineEntryLimit;
    }

    /**
     * Gets the online closure date.
     * 
     * @return onlineClosureDate
     */
    @Column(name = "ONLINE_CLOSURE_DATE")
    @Audited
    public Date getOnlineClosureDate() {
        return onlineClosureDate;
    }

    /**
     * Sets the online closure date.
     * 
     * @param onlineClosureDate online entry Closure Date
     */
    public void setOnlineClosureDate(Date onlineClosureDate) {
        this.onlineClosureDate = onlineClosureDate;
    }

    /**
     * Gets the bank account fundraising.
     * 
     * @return the bankAccountFundraising
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ACCOUNT_ID_FUNDRAISING")
    @Audited
    public BankAccount getBankAccountFundraising() {
        return bankAccountFundraising;
    }

    /**
     * Sets the bank account fundraising.
     * 
     * @param bankAccountFundraising the bankAccountFundraising to set
     */
    public void setBankAccountFundraising(BankAccount bankAccountFundraising) {
        this.bankAccountFundraising = bankAccountFundraising;
    }

    /**
     * Gets the bank account fees.
     * 
     * @return bankAccountFees the bankAccountFees to set
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ACCOUNT_ID_FEES")
    @Audited
    public BankAccount getBankAccountFees() {
        return bankAccountFees;
    }

    /**
     * Sets the bank account fees.
     * 
     * @param bankAccountFees bankAccountFees
     */
    public void setBankAccountFees(BankAccount bankAccountFees) {
        this.bankAccountFees = bankAccountFees;
    }

    /**
     * Gets the event creator ind.
     * 
     * @return the eventCreatorInd
     */
    @Column(name = "EVENT_CREATOR_IND")
    @Audited
    public String getEventCreatorInd() {
        if (eventCreatorInd == null) {
            eventCreatorInd = "N";
        }
        return eventCreatorInd;
    }

    /**
     * Sets the event creator ind.
     * 
     * @param eventCreatorInd the eventCreatorInd to set
     */
    public void setEventCreatorInd(String eventCreatorInd) {
        this.eventCreatorInd = eventCreatorInd;
    }

    /**
     * Gets the vmg manage fee ind.
     * 
     * @return the vmgManageFeeInd
     * TODO modify script accordingy.
     */
    @Column(name = "VMG_MANAGE_FEE_IND")
    @Audited
    public String getVmgManageFeeInd() {
        return vmgManageFeeInd;
    }

    /**
     * Sets the vmg manage fee ind.
     * 
     * @param vmgManageFeeInd the vmgManageFeeInd to set
     */
    public void setVmgManageFeeInd(String vmgManageFeeInd) {
        this.vmgManageFeeInd = vmgManageFeeInd;
    }

    /**
     * Gets the alternate fee pay ind.
     * 
     * @return alternateFeePayInd
     */
    @Column(name = "ALTERNATE_FEE_PAYMENT_IND")
    @Audited
    public String getAlternateFeePayInd() {
        return alternateFeePayInd;
    }

    /**
     * Sets the alternate fee pay ind.
     * 
     * @param alternateFeePayInd alternateFeePayInd
     */
    public void setAlternateFeePayInd(String alternateFeePayInd) {
        this.alternateFeePayInd = alternateFeePayInd;
    }

    /**
     * Gets the payment instructions.
     * 
     * @return paymentInstructions
     */
    @Column(name = "PAYMENT_INSTRUCTIONS")
    @NotAudited
    public String getPaymentInstructions() {
        return paymentInstructions;
    }

    /**
     * Sets the payment instructions.
     * 
     * @param paymentInstructions paymentInstructions
     */
    public void setPaymentInstructions(String paymentInstructions) {
        this.paymentInstructions = paymentInstructions;
    }

    /**
     * Gets the event fee details.
     * 
     * @return the eventFeeDetails
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "charitableActivity")
    @NotAudited
    public Set<EventFeeDetails> getEventFeeDetails() {
        return eventFeeDetails;
    }

    /**
     * Sets the event fee details.
     * 
     * @param eventFeeDetails the eventFeeDetails to set
     */
    public void setEventFeeDetails(Set<EventFeeDetails> eventFeeDetails) {
        this.eventFeeDetails = eventFeeDetails;
    }

    /**
     * Gets the charity event admin details.
     * 
     * @return the charityEventAdminDetails
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "charitableActivity")
    @NotAudited
    public Set<CharityEventAdministrator> getCharityEventAdminDetails() {
        return charityEventAdminDetails;
    }

    /**
     * Sets the charity event admin details.
     * 
     * @param charityEventAdminDetails the charityEventAdminDetails to set
     */
    public void setCharityEventAdminDetails(
            Set<CharityEventAdministrator> charityEventAdminDetails) {
        this.charityEventAdminDetails = charityEventAdminDetails;
    }

    /**
     * Gets the all users ind.
     * 
     * @return the allUsersInd
     * TODO modify script accordingy.
     */
    @Column(name = "ALL_USERS_IND")
    @NotAudited
    public String getAllUsersInd() {
        if (allUsersInd == null) {
            allUsersInd = "N";
        }
        return allUsersInd;
    }

    /**
     * Sets the all users ind.
     * 
     * @param allUsersInd the allUsersInd to set
     */
    public void setAllUsersInd(String allUsersInd) {
        this.allUsersInd = allUsersInd;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    @Override
    public String toString() {

        final String tab = "    ";
        return new StringBuilder("CharitableActivity ( ").append("id = ")
                .append(id).append(tab)
                .append(" charity = ").append(charity).append(tab)
                .append(" splitPercentage = ").append(splitPercentage).append(
                        tab).append(" bankAccountFundraising = ").append(
                        bankAccountFundraising).append(tab).append(
                        " bankAccountFees = ").append(bankAccountFees).append(
                        tab).append(" onlineEntryLimit = ").append(
                        onlineEntryLimit).append(tab).append(
                        " onlineClosureDate = ").append(onlineClosureDate)
                .append(tab).append(" thankYouMessage = ").append(
                        thankYouMessage).append(tab).append(
                        " fundraiserGroupsInd = ").append(fundraiserGroupsInd)
                .append(tab).append(" vmgManageFeeInd= ").append(
                        vmgManageFeeInd).append(tab).append(
                        " alternateFeePayInd = ").append(alternateFeePayInd)
                .append(tab).append(" eventCreatorInd= ").append(
                        eventCreatorInd).append(tab).append(" allUsersInd= ")
                .append(allUsersInd).append(tab).append(super.toString())
                .append(" )").toString();
    }

}
