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

/**
 * DTO representing a donation invitation.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "DONATION_INVITATION")
public class DonationInvitation extends AuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -7937225224659L;

    /** Primary key for donation invitation. */
    private Long id;

    /** Fundraiser activity. */
    private FundraiserActivity fundraiserActivity;

    /** Address book entry. */
    private AddressBookEntry addressBookEntry;

    /** Donation received indicator. */
    private String donationReceivedIndicator;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
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
     * Gets the fundraiser activity.
     * 
     * @return the fundraiserActivity
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_ACTIVITY_ID")
    public FundraiserActivity getFundraiserActivity() {
        return fundraiserActivity;
    }

    /**
     * Sets the fundraiser activity.
     * 
     * @param fundraiserActivity the fundraiserActivity to set
     */
    public void setFundraiserActivity(FundraiserActivity fundraiserActivity) {
        this.fundraiserActivity = fundraiserActivity;
    }

    /**
     * Gets the address book entry.
     * 
     * @return the addressBookEntry
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_BOOK_ENTRY_ID")
    public AddressBookEntry getAddressBookEntry() {
        return addressBookEntry;
    }

    /**
     * Sets the address book entry.
     * 
     * @param addressBookEntry the addressBookEntry to set
     */
    public void setAddressBookEntry(AddressBookEntry addressBookEntry) {
        this.addressBookEntry = addressBookEntry;
    }

    /**
     * Gets the donation received indicator.
     * 
     * @return the donationReceivedIndicator
     */
    @Column(name = "DONATION_RECEIVED_IND")
    public String getDonationReceivedIndicator() {
        if(donationReceivedIndicator == null){
            donationReceivedIndicator="N";
        }
        return donationReceivedIndicator;
    }

    /**
     * Sets the donation received indicator.
     * 
     * @param donationReceivedIndicator the donationReceivedIndicator to set
     */
    public void setDonationReceivedIndicator(String donationReceivedIndicator) {
        this.donationReceivedIndicator = donationReceivedIndicator;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("DonationInvitation ( ")
            .append("id = ").append(this.id).append(tab)
            .append("fundraiserActivity = ").append(this.fundraiserActivity).append(tab)
            .append("addressBookEntry = ").append(this.addressBookEntry).append(tab)
            .append("donationReceivedIndicator = ").append(this.donationReceivedIndicator).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }
}
