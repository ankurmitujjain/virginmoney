package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the charity data to be persisted.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - changed AnnualIncome to annualIncome, added
 * serialVersionUID, added List of CategorisedCharity and List of
 * CharityRegistrationForm. Added column ACTIVATION_POSTPONED
 */
@Entity
@Table(name = "CHARITY")
@Audited
public class Charity extends EnversAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -7937225173659753253L;

    /** Primary key for Charity. */
    private Long id;

    /** Charity Status Code for Charity. */
    private CharityStatus charityStatus;

    /** Charity Name for Charity. */
    private String name;

    /** Registered Charity Number for Charity. */
    private String registeredCharityNumber;

    /** Description for Charity. */
    private String description;

    /** Registration Fee for Charity. */
     private String registrationFeeInd;

    /** Completed Forms for Charity. */
    private String formsCompletedInd;

    /** URL LOGO for Charity. */
    private String logoUrl;

    /** Accounting Period End Date. */
    private Timestamp accountingPeriodEndDate;

    /** commonName of type String. */
    private String commonName;

    /** Abbreviations. */
    private String abbreviations;

    /** main Email Address. */
    private String mainEmailAddress;

    /** mainTelephoneNumber. */
    private String mainTelephoneNumber;

    /** Website URL. */
    private String websiteURL;

    /** Annual Income. */
    private BigDecimal annualIncome;

    /** Fundraising percentage. */
    private BigDecimal fundraisingPercentage;

    /** online Income. */
    private BigDecimal onlineIncome;

    /** Charity addresses. */
    private Set<CharityAddress> charityAddresses;

    /** Charity administrator details. */
    private Set<CharityAdministrator> charityAdministrators;

    /** Charity register code . */
    private CharityRegister charityRegister;

    /** Charity registration forms. */
    private Set<CharityRegistrationForm> charityRegistrationForms = null;

    /** Charity categories. */
    private Set<CategorisedCharity> charityCategories = null;

    /** Fundraiser thankyou indicator. */
    private String fundraiserThankyouIndicator;

    /** Fundraiser thankyou messgae. */
    private String fundraiserThankyouText = null;

    /** Donor thankyou indicator. */
    private String donorThankyouIndicator;

    /** Donor thankyou messgae. */
    private String donorThankyouText = null;

    /** HMRC Ref number. */
    private String hmrcRefNo = null;

    /** Charity bank accounts. */
    private Set<BankAccount> bankAccounts = null;

    /** Alert triggers. */
    private Set<AlertTrigger> alertTriggers;

    /** The vmg charity URL. */
    private String vmgCharityURL;

    /** The Batch Status. */
    private String batchStatus = null;

      /** Trustee Details . */
    private Set<TrusteeDetails> trusteeDetails;

    /** Charity Off Line Registration. */
    private Set<CharityOfflineRegistration> charityOfflineRegistration;

    /** URL details. */
    private UrlDetails urlDetails;

    /** Trustee Details Count. */
    private Integer trusteeCount;

    /** Activation postponed. */
    private String activationPostponed;

    /** Indicates if an amendment has been carried out on the charity. */
    private String charityUpdateInd;

    /** Property to hold the first colour choice for the charity page; the UI decides where to apply this */
     private String charityPageColourCode1;

     /** Property to hold the second colour choice for the charity page; the UI decides where to apply this */
     private String charityPageColourCode2;



    /**
     * Get id for charity.
     * 
     * @return the id for Charity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
    public Long getId() {
        return id;
    }

    /**
     * Set id for charity.
     * 
     * @param id the id to set for charity
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get name for charity.
     * 
     * @return the name for Charity
     */
     @Column(name = "NAME")
     @Audited
    public String getName() {
        return name;
    }

    /**
     * Set name for charity.
     * 
     * @param name the name for Charity
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get registered charity number for charity.
     * 
     * @return the registered charity number for Charity
     */
    @Column(name = "REGISTERED_CHARITY_NUMBER")
    @Audited
    public String getRegisteredCharityNumber() {
        return registeredCharityNumber;
    }

    /**
     * Set registered charity number for charity.
     * 
     * @param registeredCharityNumber the registered charity number for Charity
     */
    public void setRegisteredCharityNumber(String registeredCharityNumber) {
        this.registeredCharityNumber = registeredCharityNumber;
    }

    /**
     * Get description for charity.
     *
     * @return the description for Charity
     */
    @Column(name = "DESCRIPTION", columnDefinition = "text")
    @NotAudited
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for charity.
     * 
     * @param description the description for charity.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get registration fee ind for charity.
     * 
     * @return the registration fee ind for Charity
     */
     @Column(name = "REGISTRATION_FEE_IND")
     @Audited
    public String getRegistrationFeeInd() {
         if (registrationFeeInd == null) {
             registrationFeeInd = "N";
         }
        return registrationFeeInd;
    }

    /**
     * Set registration fee ind for charity.
     * 
     * @param registrationFeeInd the registration fee ind
     */
    public void setRegistrationFeeInd(String registrationFeeInd) {
        this.registrationFeeInd = registrationFeeInd;
    }

    /**
     * Get forms completed ind for charity.
     * 
     * @return the formsCompletedInd for Charity
     */
    @Column(name = "FORMS_COMPLETED_IND")
    @Audited
    public String getFormsCompletedInd() {
        if (formsCompletedInd == null) {
            formsCompletedInd = "N";
        }
        return formsCompletedInd;
    }

    /**
     * Set forms completed ind for charity.
     * 
     * @param formsCompletedInd the charity status code
     */
    public void setFormsCompletedInd(String formsCompletedInd) {
        this.formsCompletedInd = formsCompletedInd;
    }

    /**
     * Get the charity logo url.
     * 
     * @return the logoUrl for Charity.
     */
    @Column(name = "LOGO_URL")
    @NotAudited
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * Set the charity logo url.
     * 
     * @param logoUrl the charity logo url.
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     * Gets the charity status.
     * 
     * @return the charityStatusCode
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_STATUS_CODE")
    // TODO : fix lookup data etc. before reinstating
    @Audited
    public CharityStatus getCharityStatus() {
        return charityStatus;
    }

    /**
     * Sets the charity status.
     * 
     * @param charityStatus the charityStatusCode to set
     */
    public void setCharityStatus(CharityStatus charityStatus) {
        this.charityStatus = charityStatus;
    }

    /**
     * Gets the accounting period end date.
     * 
     * @return the accountingPeriodEndDate
     */
    @Column(name = "ACCOUNTING_PERIOD_END_DATE")
    @NotAudited
    public Timestamp getAccountingPeriodEndDate() {
        return accountingPeriodEndDate;
    }

    /**
     * Sets the accounting period end date.
     * 
     * @param accountingPeriodEndDate the accountingPeriodEndDate to set
     */
    public void setAccountingPeriodEndDate(Timestamp accountingPeriodEndDate) {
        this.accountingPeriodEndDate = accountingPeriodEndDate;
    }

    /**
     * Gets the common name.
     * 
     * @return the commonName
     */
    @Column(name = "COMMON_NAME")
    @NotAudited
    public String getCommonName() {
        return commonName;
    }

    /**
     * Sets the common name.
     * 
     * @param commonName the commonName to set
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     * Gets the abbreviations.
     * 
     * @return the abbreviations
     */
    @Column(name = "ABBREVIATIONS")
    @NotAudited
    public String getAbbreviations() {
        return abbreviations;
    }

    /**
     * Sets the abbreviations.
     * 
     * @param abbreviations the abbreviations to set
     */
    public void setAbbreviations(String abbreviations) {
        this.abbreviations = abbreviations;
    }

    /**
     * Gets the main email address.
     * 
     * @return the mainEmailAddress
     */
    @Column(name = "MAIN_EMAIL_ADDRESS")
    @NotAudited
    public String getMainEmailAddress() {
        return mainEmailAddress;
    }

    /**
     * Sets the main email address.
     * 
     * @param mainEmailAddress the mainEmailAddress to set
     */
    public void setMainEmailAddress(String mainEmailAddress) {
        this.mainEmailAddress = mainEmailAddress;
    }

    /**
     * Gets the main telephone number.
     * 
     * @return the mainTelephoneNumber
     */
    @Column(name = "MAIN_TELEPHONE_NUMBER")
    @NotAudited
    public String getMainTelephoneNumber() {
        return mainTelephoneNumber;
    }

    /**
     * Sets the main telephone number.
     * 
     * @param mainTelephoneNumber the mainTelephoneNumber to set
     */
    public void setMainTelephoneNumber(String mainTelephoneNumber) {
        this.mainTelephoneNumber = mainTelephoneNumber;
    }

    /**
     * Gets the website url.
     * 
     * @return the websiteURL
     */
    @Column(name = "WEBSITE_URL")
    @NotAudited
    public String getWebsiteURL() {
        return websiteURL;
    }

    /**
     * Sets the website url.
     * 
     * @param websiteURL the websiteURL to set
     */
    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    /**
     * Gets the annual income.
     * 
     * @return the annualIncome
     */
    @Column(name = "ANNUAL_INCOME", columnDefinition = "decimal(19,2)")
    @NotAudited
    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    /**
     * Sets the annual income.
     * 
     * @param annualIncome the annualIncome to set
     */
    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    /**
     * Gets the fundraising percentage.
     * 
     * @return the fundraisingPercentage
     */
    @Column(name = "FUNDRAISING_PERCENTAGE", columnDefinition = "decimal(19,2)")
    @NotAudited
    public BigDecimal getFundraisingPercentage() {
        return fundraisingPercentage;
    }

    /**
     * Sets the fundraising percentage.
     * 
     * @param fundraisingPercentage the fundraisingPercentage to set
     */
    public void setFundraisingPercentage(BigDecimal fundraisingPercentage) {
        this.fundraisingPercentage = fundraisingPercentage;
    }

    /**
     * Gets the online income.
     * 
     * @return the onlineIncome
     */
    @Column(name = "ONLINE_INCOME", columnDefinition = "decimal(19,2)")
    @NotAudited
    public BigDecimal getOnlineIncome() {
        return onlineIncome;
    }

    /**
     * Sets the online income.
     * 
     * @param onlineIncome the onlineIncome to set
     */
    public void setOnlineIncome(BigDecimal onlineIncome) {
        this.onlineIncome = onlineIncome;
    }

    /**
     * Gets the charity administrators.
     * 
     * @return the charityAdministrator
     */
    @OneToMany(mappedBy = "charity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotAudited
    public Set<CharityAdministrator> getCharityAdministrators() {
        return charityAdministrators;
    }

    /**
     * Sets the charity administrators.
     * 
     * @param charityAdministrators the charityAdministrator to set
     */
    public void setCharityAdministrators(
            Set<CharityAdministrator> charityAdministrators) {
        this.charityAdministrators = charityAdministrators;
    }

    /**
     * Gets the charity addresses.
     * 
     * @return the charity_addresses
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "charity")
    @NotAudited
    public Set<CharityAddress> getCharityAddresses() {
        return charityAddresses;
    }

    /**
     * Sets the charity addresses.
     * 
     * @param charityAddresses the charity_addresses to set
     */
    public void setCharityAddresses(Set<CharityAddress> charityAddresses) {
        this.charityAddresses = charityAddresses;
    }

    /**
     * Gets the charity register.
     * 
     * @return the charityRegister
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_REGISTER_CODE")
    @Audited
    public CharityRegister getCharityRegister() {
        return charityRegister;
    }

    /**
     * Sets the charity register.
     * 
     * @param charityRegister the charityRegister code to set
     */
    public void setCharityRegister(CharityRegister charityRegister) {
        this.charityRegister = charityRegister;
    }

    /**
     * Gets the charity registration forms.
     * 
     * @return the charityRegistrationForms
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "charity")
    @NotAudited
    public Set<CharityRegistrationForm> getCharityRegistrationForms() {
        return charityRegistrationForms;
    }

    /**
     * Sets the charity registration forms.
     * 
     * @param charityRegistrationForms the charityRegistrationForms to set
     */
    public void setCharityRegistrationForms(
            Set<CharityRegistrationForm> charityRegistrationForms) {
        this.charityRegistrationForms = charityRegistrationForms;
    }

    /**
     * Gets the charity categories.
     * 
     * @return the charityCategories
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "charity")
    @NotAudited
    public Set<CategorisedCharity> getCharityCategories() {
        return charityCategories;
    }

    /**
     * Sets the charity categories.
     * 
     * @param charityCategories the charityCategories to set
     */
    public void setCharityCategories(Set<CategorisedCharity> charityCategories) {
        this.charityCategories = charityCategories;
    }

    /**
     * Gets the fundraiser thankyou indicator.
     * 
     * @return the fundraiserThankyouIndicator
     */
    @Column(name = "FUNDRAISER_THANKYOU_IND")
    @NotAudited
    public String getFundraiserThankyouIndicator() {
        if (fundraiserThankyouIndicator == null) {
            fundraiserThankyouIndicator = "N";
        }
        return fundraiserThankyouIndicator;
    }

    /**
     * Sets the fundraiser thankyou indicator.
     * 
     * @param fundraiserThankyouIndicator the fundraiserThankyouIndicator to set
     */
    public void setFundraiserThankyouIndicator(
            String fundraiserThankyouIndicator) {
        this.fundraiserThankyouIndicator = fundraiserThankyouIndicator;
    }

    /**
     * Gets the fundraiser thankyou text.
     * 
     * @return the fundraiserThankyouText
     */
   @Column(name = "FUNDRAISER_THANKYOU_TEXT", columnDefinition = "text")
   @NotAudited
    public String getFundraiserThankyouText() {
        return fundraiserThankyouText;
    }

    /**
     * Sets the fundraiser thankyou text.
     * 
     * @param fundraiserThankyouText the fundraiserThankyouText to set
     */
    public void setFundraiserThankyouText(String fundraiserThankyouText) {
        this.fundraiserThankyouText = fundraiserThankyouText;
    }

    /**
     * Gets the donor thankyou indicator.
     * 
     * @return the donorThankyouIndicator
     */
    @Column(name = "DONOR_THANKYOU_IND")
    @NotAudited
    public String getDonorThankyouIndicator() {
        if (donorThankyouIndicator == null) {
            donorThankyouIndicator = "N";
        }
        return donorThankyouIndicator;
    }

    /**
     * Sets the donor thankyou indicator.
     * 
     * @param donorThankyouIndicator the donorThankyouIndicator to set
     */
    public void setDonorThankyouIndicator(String donorThankyouIndicator) {
        this.donorThankyouIndicator = donorThankyouIndicator;
    }

    /**
     * Gets the donor thankyou text.
     * 
     * @return the donorThankyouText
     */
    @Column(name = "DONOR_THANKYOU_TEXT", columnDefinition = "text")
    @NotAudited
    public String getDonorThankyouText() {
        return donorThankyouText;
    }

    /**
     * Sets the donor thankyou text.
     * 
     * @param donorThankyouText the donorThankyouText to set
     */
    public void setDonorThankyouText(String donorThankyouText) {
        this.donorThankyouText = donorThankyouText;
    }

    /**
     * Gets the hmrc ref no.
     * 
     * @return the hmrcRefNo
     */
    @Column(name = "HMRC_REF_NO")
    @Audited
    public String getHmrcRefNo() {
        return hmrcRefNo;
    }

    /**
     * Sets the hmrc ref no.
     * 
     * @param hmrcRefNo the hmrcRefNo to set
     */
    public void setHmrcRefNo(String hmrcRefNo) {
        this.hmrcRefNo = hmrcRefNo;
    }

    /**
     * Gets the bank accounts.
     * 
     * @return the bankAccounts
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "charity")
    @NotAudited
    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    /**
     * Sets the bank accounts.
     * 
     * @param bankAccounts the bankAccounts to set
     */
    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    /**
     * Gets the alert triggers.
     * 
     * @return the alert triggers.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "charity")
    @NotAudited
    public Set<AlertTrigger> getAlertTriggers() {
        return alertTriggers;
    }

    /**
     * Sets the alert triggers.
     * 
     * @param alertTriggers the alert triggers to set
     */
    public void setAlertTriggers(Set<AlertTrigger> alertTriggers) {
        this.alertTriggers = alertTriggers;
    }

    /**
     * Gets the vmg charity url.
     * 
     * @return the vmgCharityURL
     */
    @Column(name = "VMG_CHARITY_URL")
    @NotAudited
    public String getVmgCharityURL() {
        return vmgCharityURL;
    }

    /**
     * Sets the vmg charity url.
     * 
     * @param vmgCharityURL the vmgCharityURL to set
     */
    public void setVmgCharityURL(String vmgCharityURL) {
        this.vmgCharityURL = vmgCharityURL;
    }

    /**
     * Gets the batch status.
     * 
     * @return the batchStatus
     */
    @Column(name = "BATCH_STATUS")
    @NotAudited
    public String getBatchStatus() {
        return batchStatus;
    }

    /**
     * Sets the batch status.
     * 
     * @param batchStatus the batchStatus to set
     */
    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    /**
     * Gets the trustee details.
     * 
     * @return the Trustee Details.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CHARITY_ID")
    @NotAudited
    public Set<TrusteeDetails> getTrusteeDetails() {
        return trusteeDetails;
    }

    /**
     * Sets the trustee details.
     * 
     * @param trusteeDetails the trustee Details to set
     */
    public void setTrusteeDetails(Set<TrusteeDetails> trusteeDetails) {
        this.trusteeDetails = trusteeDetails;
    }

    /**
     * Gets the charity offline registration.
     * 
     * @return the Charity Off Line Registration.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CHARITY_ID")
    @NotAudited
    public Set<CharityOfflineRegistration> getCharityOfflineRegistration() {
        return charityOfflineRegistration;
    }

    /**
     * Sets the charity offline registration.
     * 
     * @param charityOfflineRegistration the charity Off line Registration to set
     */
    public void setCharityOfflineRegistration(
            Set<CharityOfflineRegistration> charityOfflineRegistration) {
        this.charityOfflineRegistration = charityOfflineRegistration;
    }

    /**
     * Gets the url details.
     * 
     * @return the urlDetails
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "URL_DETAILS_ID")
    @NotAudited
    public UrlDetails getUrlDetails() {
        return urlDetails;
    }

    /**
     * Sets the url details.
     * 
     * @param urlDetails the urlDetails to set
     */
    public void setUrlDetails(UrlDetails urlDetails) {
        this.urlDetails = urlDetails;
    }

    /**
     * Gets the trustee count.
     * 
     * @return the trustee Details count
     */
    @Column(name = "TRUSTEE_COUNT")
    @NotAudited
    public Integer getTrusteeCount() {
        return trusteeCount;
    }

    /**
     * Sets the trustee count.
     * 
     * @param trusteeCount the Trustee Details count to set
     */
    public void setTrusteeCount(Integer trusteeCount) {
        this.trusteeCount = trusteeCount;
    }

    /**
     * Gets the activation postponed.
     * 
     * @return the activationPostponed
     */
    @Column(name = "ACTIVATION_POSTPONED")
    @NotAudited
    public String getActivationPostponed() {
        if (activationPostponed == null) {
            activationPostponed = "N";
        }
        return activationPostponed;
    }

    /**
     * Sets the activation postponed.
     * 
     * @param activationPostponed the activationPostponed to set
     */
    public void setActivationPostponed(String activationPostponed) {
        this.activationPostponed = activationPostponed;
    }

    /**
     * Gets the charityUpdateInd property.
     * 
     * @return String value
     */
    @Column(name = "CHARITY_AMENDMENT_IND")
    @NotAudited
    public String getCharityUpdateInd() {
        return charityUpdateInd;
    }

    /**
     * Sets the charityUpdateInd property.
     * 
     * @param charityUpdateInd value to set
     */
    public void setCharityUpdateInd(String charityUpdateInd) {
        this.charityUpdateInd = charityUpdateInd;
    }

    /**
      * Returns the charity's first colour choice, which will be an HTML code, e.g "#CC0000".
      * @return  the charity's first colour choice , which will be an HTML code, e.g "#CC0000".
      */
    @Column(name = "CHARITY_PAGE_COLOUR_CODE1")
    @NotAudited
     public String getCharityPageColourCode1() {
         return charityPageColourCode1;
     }

     /**
      * Sets the charity's first colour choice, which will be an HTML code, e.g "#CC0000".
      * @param charityPageColourCode1 the charity's first colour choice, which will be an HTML code, e.g "#CC0000".
      */
     public void setCharityPageColourCode1(String charityPageColourCode1) {
         this.charityPageColourCode1 = charityPageColourCode1;
     }

     /**
      * Returns the charity's second colour choice, which will be an HTML code, e.g "#CC0000".
      * @return  the charity's second colour choice , which will be an HTML code, e.g "#CC0000".
      */
     @Column(name = "CHARITY_PAGE_COLOUR_CODE2")
     @NotAudited
     public String getCharityPageColourCode2() {
         return charityPageColourCode2;
     }

     /**
      * Sets the charity's secondt colour choice, which will be an HTML code, e.g "#CC0000".
      * @param charityPageColourCode2 the charity's second colour choice, which will be an HTML code, e.g "#CC0000".
      */
     public void setCharityPageColourCode2(String charityPageColourCode2) {
         this.charityPageColourCode2 = charityPageColourCode2;
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
        return new StringBuilder("Charity ( ").append("id = ").append(id)
                .append(tab).append("charityStatus = ").append(charityStatus)
                .append(tab).append("name = ").append(name).append(tab).append(
                        "registeredCharityNumber = ").append(
                        registeredCharityNumber).append(tab).append(
                        "description = ").append(description).append(tab)
                .append("registrationFeeInd = ").append(registrationFeeInd)
                .append(tab).append("formsCompletedInd = ").append(
                        formsCompletedInd).append(tab).append("logoUrl = ")
                .append(logoUrl).append(tab).append(
                        "accountingPeriodEndDate = ").append(
                        accountingPeriodEndDate).append(tab).append(
                        "commonName = ").append(commonName).append(tab).append(
                        "abbreviations = ").append(abbreviations).append(tab)
                .append("mainEmailAddress = ").append(mainEmailAddress).append(
                        tab).append("mainTelephoneNumber = ").append(
                        mainTelephoneNumber).append(tab)
                .append("websiteURL = ").append(websiteURL).append(tab).append(
                        "annualIncome = ").append(annualIncome).append(tab)
                .append("fundraisingPercentage = ").append(
                        fundraisingPercentage).append(tab).append(
                        "onlineIncome = ").append(onlineIncome).append(tab)
                .append("charityAddresses = ").append(charityAddresses).append(
                        tab).append("charityAdministrators = ").append(
                        charityAdministrators).append(tab).append(
                        "charityRegister = ").append(charityRegister).append(
                        tab).append("charityRegistrationForms = ").append(
                        charityRegistrationForms).append(tab).append(
                        "charityCategories = ").append(charityCategories)
                .append(tab).append("activationPostponed = ").append(
                        activationPostponed).append(tab).append(
                        super.toString()).append(" )").toString();
    }
}
