package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Domain class that represents user profile to be persisted.
 * 
 * @author dibaskumarp
 * @author Puneet Swarup - added audit attributes and toString.
 */
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile extends AssociationAuditAttributes implements
        Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = 5104017378127085252L;

    /** Unique Id to identify user profile. */
    private Long id;

    /** Attribute user. */
    private User user;

    /** Charity marketing indicator. */
    private String charityMarketingIndicator = "N";

    /** Charity marketing indicator. */
    private String allCharityMarketingIndicator = "N";

    /** Accept terma and conditiona indicator. */
    private String acceptTermsAndConditions = "N";

    /** Contact by email indicator. */
    private String contactByEmailIndicator = "N";

    /** Contact by phone indicator. */
    private String contactByPhoneIndicator = "N";

    /** Contact by mail indicator. */
    private String contactByMailIndicator = "N";

    /** Contact by sms indicator. */
    private String contactBySmsIndicator = "N";

    /** VMG marketing indicator. */
    private String virginMoneyMarketingIndicator = "N";

    /** VM group marketing indicator. */
    private String virginGroupMarketingIndicator = "N";

    /** Gift aid usually applies indicator. */
    private String giftAidUsuallyAppliesIndicator = "Y";

    /**
     * Unique Id for the User Profile.
     * 
     * @return Id of the user profile
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * Sets unique id to user profile.
     * 
     * @param id - Unique Id to be set to User Profile.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Mapped to user id of User Domain.
     *
     * @return user who owns this profile
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    /**
     * Sets users to this profile.
     * 
     * @param user - User object
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the charity marketing indicator.
     * 
     * @return the charityMarketingIndicator
     */
    @Column(name = "CHARITY_MARKETING_IND")
    public String getCharityMarketingIndicator() {
        return charityMarketingIndicator;
    }

    /**
     * Sets the charity marketing indicator.
     * 
     * @param charityMarketingIndicator the charityMarketingIndicator to set
     */
    public void setCharityMarketingIndicator(String charityMarketingIndicator) {
        this.charityMarketingIndicator = charityMarketingIndicator;
    }

    /**
     * Gets the accept terms and conditions.
     * 
     * @return the acceptTermsAndConditions
     */
    @Column(name = "ACCEPT_TNC_IND")
    public String getAcceptTermsAndConditions() {
        return acceptTermsAndConditions;
    }

    /**
     * Sets the accept terms and conditions.
     * 
     * @param acceptTermsAndConditions the acceptTermsAndConditions to set
     */
    public void setAcceptTermsAndConditions(String acceptTermsAndConditions) {
        this.acceptTermsAndConditions = acceptTermsAndConditions;
    }

    /**
     * Gets the contact by email indicator.
     * 
     * @return the contactByEmailIndicator
     */
    @Column(name = "CONTACT_BY_EMAIL_IND")
    public String getContactByEmailIndicator() {
        return contactByEmailIndicator;
    }

    /**
     * Sets the contact by email indicator.
     * 
     * @param contactByEmailIndicator the contactByEmailIndicator to set
     */
    public void setContactByEmailIndicator(String contactByEmailIndicator) {
        this.contactByEmailIndicator = contactByEmailIndicator;
    }

    /**
     * Gets the contact by phone indicator.
     * 
     * @return the contactByPhoneIndicator
     */
    @Column(name = "CONTACT_BY_PHONE_IND")
    public String getContactByPhoneIndicator() {
        return contactByPhoneIndicator;
    }

    /**
     * Sets the contact by phone indicator.
     * 
     * @param contactByPhoneIndicator the contactByPhoneIndicator to set
     */
    public void setContactByPhoneIndicator(String contactByPhoneIndicator) {
        this.contactByPhoneIndicator = contactByPhoneIndicator;
    }

    /**
     * Gets the contact by mail indicator.
     * 
     * @return the contactByMailIndicator
     */
    @Column(name = "CONTACT_BY_MAIL_IND")
    public String getContactByMailIndicator() {
        return contactByMailIndicator;
    }

    /**
     * Sets the contact by mail indicator.
     * 
     * @param contactByMailIndicator the contactByMailIndicator to set
     */
    public void setContactByMailIndicator(String contactByMailIndicator) {
        this.contactByMailIndicator = contactByMailIndicator;
    }

    /**
     * Gets the contact by sms indicator.
     * 
     * @return the contactBySmsIndicator
     */
    @Column(name = "CONTACT_BY_SMS_IND")
    public String getContactBySmsIndicator() {
        return contactBySmsIndicator;
    }

    /**
     * Sets the contact by sms indicator.
     * 
     * @param contactBySmsIndicator the contactBySmsIndicator to set
     */
    public void setContactBySmsIndicator(String contactBySmsIndicator) {
        this.contactBySmsIndicator = contactBySmsIndicator;
    }

    /**
     * Gets the virgin money marketing indicator.
     * 
     * @return the virginMoneyMarketingIndicator
     */
    @Column(name = "VIRGIN_MONEY_MARKETING_IND")
    public String getVirginMoneyMarketingIndicator() {
        return virginMoneyMarketingIndicator;
    }

    /**
     * Sets the virgin money marketing indicator.
     * 
     * @param virginMoneyMarketingIndicator the virginMoneyMarketingIndicator to set
     */
    public void setVirginMoneyMarketingIndicator(
            String virginMoneyMarketingIndicator) {
        this.virginMoneyMarketingIndicator = virginMoneyMarketingIndicator;
    }

    /**
     * Gets the virgin group marketing indicator.
     * 
     * @return the virginGroupMarketingIndicator
     */
    @Column(name = "VIRGIN_GROUP_MARKETING_IND")
    public String getVirginGroupMarketingIndicator() {
        return virginGroupMarketingIndicator;
    }

    /**
     * Sets the virgin group marketing indicator.
     * 
     * @param virginGroupMarketingIndicator the virginGroupMarketingIndicator to set
     */
    public void setVirginGroupMarketingIndicator(
            String virginGroupMarketingIndicator) {
        this.virginGroupMarketingIndicator = virginGroupMarketingIndicator;
    }

    /**
     * Gets the gift aid usually applies indicator.
     * 
     * @return the giftAidUsuallyAppliesIndicator
     */
    @Column(name = "GIFT_AID_USUALLY_APPLIES_IND")
    public String getGiftAidUsuallyAppliesIndicator() {
        return giftAidUsuallyAppliesIndicator;
    }

    /**
     * Sets the gift aid usually applies indicator.
     * 
     * @param giftAidUsuallyAppliesIndicator the giftAidUsuallyAppliesIndicator to set
     */
    public void setGiftAidUsuallyAppliesIndicator(
            String giftAidUsuallyAppliesIndicator) {
        this.giftAidUsuallyAppliesIndicator = giftAidUsuallyAppliesIndicator;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {
        final String tab = "    ";

        return new StringBuilder("UserProfile ( ").append(super.toString())
                .append(tab).append("id = ").append(this.id).append(
                        tab)
                .append("charityMarketingIndicator = ").append(
                        this.charityMarketingIndicator).append(tab).append(
                        "acceptTermsAndConditions = ").append(
                        this.acceptTermsAndConditions).append(tab).append(
                        "contactByEmailIndicator = ").append(
                        this.contactByEmailIndicator).append(tab).append(
                        "contactByPhoneIndicator = ").append(
                        this.contactByPhoneIndicator).append(tab).append(
                        "contactByMailIndicator = ").append(
                        this.contactByMailIndicator).append(tab).append(
                        "contactBySmsIndicator = ").append(
                        this.contactBySmsIndicator).append(tab).append(
                        "virginMoneyMarketingIndicator = ").append(
                        this.virginMoneyMarketingIndicator).append(tab).append(
                        "virginGroupMarketingIndicator = ").append(
                        this.virginGroupMarketingIndicator).append(tab).append(
                        "giftAidUsuallyAppliesIndicator = ").append(
                        this.giftAidUsuallyAppliesIndicator).append(tab)
                .append(" )").toString();
    }

    /**
     * Gets the all charity marketing indicator.
     * 
     * @return the allCharityMarketingIndicator
     */
    @Column(name = "ALL_CHARITY_MARKETING_IND")
    public String getAllCharityMarketingIndicator() {
        return allCharityMarketingIndicator;
    }

    /**
     * Sets the all charity marketing indicator.
     * 
     * @param allCharityMarketingIndicator the allCharityMarketingIndicator to set
     */
    public void setAllCharityMarketingIndicator(String allCharityMarketingIndicator) {
        this.allCharityMarketingIndicator = allCharityMarketingIndicator;
    }
}
