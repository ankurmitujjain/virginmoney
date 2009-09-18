package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;

/**
 * The fundraiser activity holds the relation between the fundraiser and its
 * detailed charitable activity like the events to be supported by the
 * fund-raiser etc.
 * 
 * @author Edwin Tauro
 * @author Puneet Swarup - added audit attributes and toString.
 */
@Entity
@Table(name = "FUNDRAISER_ACTIVITY")
@Audited
public class FundraiserActivity extends AuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -3868361605102771569L;

    /** Primary key for fundraiser_activity. */
    private Long id;

    /** The {@link Fundraiser} for the activity. */
    private Fundraiser fundraiser;

    /** The {@link Fundraiser} reason. */
    private FundraisingReason fundraisingReason;

    /** The link to the pre-organized {@link Event}. */
    private Event event;

    /** The fundraising activity type. */
    private ActivityType activityType;

    /** The occasion type in case of celebrations. */
    private OccasionType occasionType;

    /** The other occasion type description. */
    private String otherOccasionType;

    /** The other activity type text description. If Other chosen in the activity type code. */
    private String otherActivityType;

    /** The location of the fundraising activity. */
    private Location location;

    /** The description for in memory of. */
    private String memorialDescription;

    /** Individual (I) or as a Group (G). */
    private String fundraisingAsIndicator;

    /** Organized event indicator, with O for Organized and U for unorganized. */
    private String organizedEventIndicator;

    /** Indicates whether the fundraising activity supports single or multiple charity. */
    private String charitiesSupportedIndicator ;

    /** Indicates whether the Fundraising activity is supported by the charity. */
    private String charityContributionIndicator;

    /** Period after completion of activity till when donation would be accepted. */
    private Integer openPeriod;

    /** The split for the charity across the fundraiser with percentage split. */
    private Set<FundraisingCharitySplit> fundraisingCharitySplit;

    /** The event completion date. */
    private Timestamp eventCompletionDate;

    /** Total donations raised. Not including tax-relief. Derived value, not stored in db. */
    private BigDecimal totalDonations;

    /** Total tax relief on donations raised. Derived value, not stored in db. */
    private BigDecimal totalTaxBack;

    /** The offline fundraising. */
    private BigDecimal offlineFundraising;

    /** Fundraising Target. */
    private BigDecimal fundraisingTarget;

    /** fundraiserPage **/
    private Page fundraiserPage;
    
    /** Fee online pay indicator. */
    private String feeOnlinePayInd;

    /** Fundraiser image url. */
    private String fundraiserImageUrl;

    /** Fundraising group. */
    private FundraiserGroup fundraiserGroup;

    /** Should the fundraiser be notified when someone makes a donation *. */
    private String donationNotificationInd;

    /** should a personal thank you text be include in emails *. */
    private String donorThankYouInd;

    /** Text for the personal thank you *. */
    private String donorThankYouText;
    
    /** Event Fee reference *. */
    private String feeReference;

    /** Event Fee situation *. */
    private String feeSituation;
    
    /** Event Fee Amount. */
    private BigDecimal feeAmount;
    
    /**
     * Default constructor.
     */
    public FundraiserActivity() {
        super();
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotAudited
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
     * Gets the fundraiser.
     * 
     * @return the fundraiser
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_ID")
    @NotAudited
    public Fundraiser getFundraiser() {
        return fundraiser;
    }

    /**
     * Sets the fundraiser.
     * 
     * @param fundraiser the fundraiser to set
     */
    public void setFundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
    }

    /**
     * Returns the {@link FundraisingReason}.
     * 
     * @return FundraisingReason the reason for fundraising as
     * {@link FundraisingReason}.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISING_REASON_CODE")
    @NotAudited
    public FundraisingReason getFundraisingReason() {
        return fundraisingReason;
    }

    /**
     * Set the {@link FundraisingReason}.
     * 
     * @param fundraisingReason the {@link FundraisingReason}.
     */
    public void setFundraisingReason(final FundraisingReason fundraisingReason) {
        this.fundraisingReason = fundraisingReason;
    }

    /**
     * Returns the fundraising {@link Event}.
     * 
     * @return Event the fundraising {@link Event}.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_ID")
    @NotAudited
    public Event getEvent() {
        return event;
    }

    /**
     * Sets the fundraising {@link Event}.
     * 
     * @param event the fundraising {@link Event}.
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Gets the activity type.
     * 
     * @return the ACTIVITY_TYPE object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACTIVITY_TYPE_CODE")
    @NotAudited
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * Sets the activity type.
     * 
     * @param activityType the Activity Type object to set
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    /**
     * Gets the fundraising charity split.
     * 
     * @return the charitySplit
     */
    @OneToMany(mappedBy = "fundraiserActivity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotAudited
    public Set<FundraisingCharitySplit> getFundraisingCharitySplit() {
        return fundraisingCharitySplit;
    }

    /**
     * Sets the fundraising charity split.
     * 
     * @param fundraisingCharitySplit the charitySplit to set
     */
    public void setFundraisingCharitySplit(
            Set<FundraisingCharitySplit> fundraisingCharitySplit) {
        this.fundraisingCharitySplit = fundraisingCharitySplit;
    }

    /**
     * Gets the other activity type.
     * 
     * @return the otherActivityType
     */
    @Column(name = "OTHER_ACTIVITY_TYPE")
    @NotAudited
    public String getOtherActivityType() {
        return otherActivityType;
    }

    /**
     * Sets the other activity type.
     * 
     * @param otherActivityType the otherActivityType to set
     */
    public void setOtherActivityType(String otherActivityType) {
        this.otherActivityType = otherActivityType;
    }

    /**
     * Gets the location.
     * 
     * @return the location
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_CODE")
    @NotAudited
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location.
     * 
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the memorial description.
     * 
     * @return the memorialDescription
     */
    @Column(name = "MEMORIAL_DESCRIPTION")
    @NotAudited
    public String getMemorialDescription() {
        return memorialDescription;
    }

    /**
     * Sets the memorial description.
     * 
     * @param memorialDescription the memorialDescription to set
     */
    public void setMemorialDescription(String memorialDescription) {
        this.memorialDescription = memorialDescription;
    }

    /**
     * Gets the fundraising as indicator.
     * 
     * @return the fundraisingAsIndicator
     */
    @Column(name = "FUNDRAISING_AS_GROUP_IND")
    @NotAudited
    public String getFundraisingAsIndicator() {
        return fundraisingAsIndicator;
    }

    /**
     * Sets the fundraising as indicator.
     * 
     * @param fundraisingAsIndicator the fundraisingAsIndicator to set
     */
    public void setFundraisingAsIndicator(String fundraisingAsIndicator) {
        this.fundraisingAsIndicator = fundraisingAsIndicator;
    }

    /**
     * Gets the organized event indicator.
     * 
     * @return the organizedEventIndicator
     */
    @Column(name = "ORGANISED_EVENT_IND")
    @NotAudited
    public String getOrganizedEventIndicator() {
        return organizedEventIndicator;
    }

    /**
     * Sets the organized event indicator.
     * 
     * @param organizedEventIndicator the organizedEventIndicator to set
     */
    public void setOrganizedEventIndicator(String organizedEventIndicator) {
        this.organizedEventIndicator = organizedEventIndicator;
    }

    /**
     * Gets the charities supported indicator.
     * 
     * @return the charitiesSupportedIndicator
     */
    @Column(name = "CHARITIES_SUPPORTED_IND")
    @NotAudited
    public String getCharitiesSupportedIndicator() {
        
        if(charitiesSupportedIndicator == null) {
            charitiesSupportedIndicator="N";
        }
        return charitiesSupportedIndicator;
    }

    /**
     * Sets the charities supported indicator.
     * 
     * @param charitiesSupportedIndicator the charitiesSupportedIndicator to set
     */
    public void setCharitiesSupportedIndicator(
            String charitiesSupportedIndicator) {
        this.charitiesSupportedIndicator = charitiesSupportedIndicator;
    }

    /**
     * Gets the charity contribution indicator.
     * 
     * @return the charityContributionIndicator
     */
    @Column(name = "CHARITIES_CONTRIBUTION_IND")
    @NotAudited
    public String getCharityContributionIndicator() {
        
        if(charityContributionIndicator == null) {
            charityContributionIndicator = "N";
        }
        return charityContributionIndicator;
    }

    /**
     * Sets the charity contribution indicator.
     * 
     * @param charitiesContributionIndicator the charityContributionIndicator to set
     */
    public void setCharityContributionIndicator(
            String charitiesContributionIndicator) {
        this.charityContributionIndicator = charitiesContributionIndicator;
    }

    /**
     * Gets the occasion type.
     * 
     * @return the occasionType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OCCASION_TYPE_CODE")
    @NotAudited
    public OccasionType getOccasionType() {
        return occasionType;
    }

    /**
     * Sets the occasion type.
     * 
     * @param occasionType the occasionType to set
     */
    public void setOccasionType(OccasionType occasionType) {
        this.occasionType = occasionType;
    }

    /**
     * Returns the other occasion type description.
     * 
     * @return String the other occasion type description.
     */
    @Column(name = "OTHER_OCCASION_TYPE")
    @NotAudited
    public String getOtherOccasionType() {
        return otherOccasionType;
    }

    /**
     * Sets the other occasion type description.
     * 
     * @param otherOccasionType the occasionType to set
     */
    public void setOtherOccasionType(String otherOccasionType) {
        this.otherOccasionType = otherOccasionType;
    }

    /**
     * Gets the open period.
     * 
     * @return the openPeriod
     */
    @Column(name = "OPEN_PERIOD")
    @NotAudited
    public Integer getOpenPeriod() {
        return openPeriod;
    }

    /**
     * Sets the open period.
     * 
     * @param openPeriod the openPeriod to set
     */
    public void setOpenPeriod(Integer openPeriod) {
        this.openPeriod = openPeriod;
    }

    /**
     * Gets the event completion date.
     * 
     * @return the eventCompletionDate
     */
    @Column(name = "EVENT_COMPLETION_DATE")
    @NotAudited
    public Timestamp getEventCompletionDate() {
        return eventCompletionDate;
    }

    /**
     * Sets the event completion date.
     * 
     * @param eventCompletionDate the eventCompletionDate to set
     */
    public void setEventCompletionDate(Timestamp eventCompletionDate) {
        this.eventCompletionDate = eventCompletionDate;
    }

    /**
     * Gets the total fund raised. 
     *
     * Derived value, not stored in db.
     *
     * @return the totalFundRaised
     */
    @Transient
    @NotAudited
    public BigDecimal getTotalDonations() {
        return totalDonations;
    }

    /**
     * Sets the total fund raised.
     *
     * Derived value, not stored in db.
     *
     * @param totalFundRaised the totalFundRaised to set
     */
    public void setTotalDonations(BigDecimal totalFundRaised) {
        this.totalDonations = totalFundRaised;
    }

    /**
     * Gets the offline fundraising.
     * 
     * @return the offlineFundraising
     */
    @Column(name = "OFFLINE_FUNDRAISING", columnDefinition = "decimal(19,2)")
    @NotAudited
    public BigDecimal getOfflineFundraising() {
        return offlineFundraising;
    }

    /**
     * Sets the offline fundraising.
     * 
     * @param offlineFundraising the offlineFundraising to set
     */
    public void setOfflineFundraising(BigDecimal offlineFundraising) {
        this.offlineFundraising = offlineFundraising;
    }


    /**
     * Gets the fundraising target.
     * 
     * @return the fundraisingTarget
     */
    @Column(name = "FUND_RAISING_TARGET", columnDefinition = "decimal(19,2)")
    @NotAudited
    public BigDecimal getFundraisingTarget() {
        return fundraisingTarget;
    }

    /**
     * Get the FundraiserPage.
     * 
     * @return the fundraiser page
     */
    @OneToOne(cascade=CascadeType.ALL, mappedBy="fundraiserActivity")    
    @NotAudited
    public Page getFundraiserPage() {
        return fundraiserPage;
    }

    /**
     * Sets the fundraiser page.
     * 
     * @param fundraiserPage the new fundraiser page
     */
    public void setFundraiserPage(Page fundraiserPage) {
        this.fundraiserPage = fundraiserPage;
    }

    /**
     * Sets the fundraising target.
     * 
     * @param fundraisingTarget the fundraisingTarget to set
     */
    public void setFundraisingTarget(BigDecimal fundraisingTarget) {
        this.fundraisingTarget = fundraisingTarget;
    }

    /**
     * @return the fundraiserImageUrl
     */
    @Column(name = "FUNDRAISER_IMAGE_URL")
    @NotAudited
    public String getFundraiserImageUrl() {
        return fundraiserImageUrl;
    }

    /**
     * Sets the fundraiser image url.
     * 
     * @param fundraiserImageUrl the fundraiserImageUrl to set
     */
    public void setFundraiserImageUrl(String fundraiserImageUrl) {
        this.fundraiserImageUrl = fundraiserImageUrl;
    }

    /**
     * Gets the fundraiser group.
     * 
     * @return the fundraiserGroup
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_GROUP_ID")
    @NotAudited
    public FundraiserGroup getFundraiserGroup() {
        return fundraiserGroup;
    }

    /**
     * Sets the fundraiser group.
     * 
     * @param fundraiserGroup the fundraiserGroup to set
     */
    public void setFundraiserGroup(FundraiserGroup fundraiserGroup) {
        this.fundraiserGroup = fundraiserGroup;
    }

    /**
     * Gets the fee online pay ind.
     * 
     * @return the feeOnlinePayInd
     */
    @Column(name = "FEE_ONLINE_PAY_IND")
    @NotAudited
    public String getFeeOnlinePayInd() {
        return feeOnlinePayInd;
    }

    /**
     * Sets the fee online pay ind.
     * 
     * @param feeOnlinePayInd the feeOnlinePayInd to set
     */
    public void setFeeOnlinePayInd(String feeOnlinePayInd) {
        this.feeOnlinePayInd = feeOnlinePayInd;
    }

    /**
     * Gets the donation notification ind.
     * 
     * @return the donation notification ind
     */
    @Column(name = "DONATION_NOTIFICATION_IND")
    @NotAudited
    public String getDonationNotificationInd() {
        return donationNotificationInd;
    }

    /**
     * Sets the donation notification ind.
     * 
     * @param donationNotificationInd the new donation notification ind
     */
    public void setDonationNotificationInd(String donationNotificationInd) {
        this.donationNotificationInd = donationNotificationInd;
    }

    /**
     * Gets the donor thank you ind.
     * 
     * @return the donor thank you ind
     */
    @Column(name = "DONOR_THANKYOU_IND")
    @NotAudited
    public String getDonorThankYouInd() {
        return donorThankYouInd;
    }

    /**
     * Sets the donor thank you ind.
     * 
     * @param donorThankYouInd the new donor thank you ind
     */
    public void setDonorThankYouInd(String donorThankYouInd) {
        this.donorThankYouInd = donorThankYouInd;
    }
   
    /**
     * Gets the donor thank you text.
     * 
     * @return the donor thank you text
     */
    @Column(name = "DONOR_THANKYOU_TEXT", columnDefinition = "text")
    @NotAudited
    public String getDonorThankYouText() {
        return donorThankYouText;
    }

    /**
     * Sets the donor thank you text.
     * 
     * @param donorThankYouText the new donor thank you text
     */
    public void setDonorThankYouText(String donorThankYouText) {
        this.donorThankYouText = donorThankYouText;
    }

    /**
     * Get Fee Reference code.
     * 
     * @return the Fee Reference code
     */
    @Column(name = "FEE_REFERENCE")
    @NotAudited
    public String getFeeReference() {
        return feeReference;
    }

    /**
     * Set Fee Reference code.
     * 
     * @param feeReference the Fee Reference code
     */
    public void setFeeReference(String feeReference) {
        this.feeReference = feeReference;
    }

    /**
     * Get Fee Situation.
     * 
     * @return the Fee Situation
     */
    @Column(name = "FEE_SITUATION")
    @NotAudited
    public String getFeeSituation() {
        return feeSituation;
    }

    /**
     * Set Fee Situation.
     * 
     * @param feeSituation the Fee Situation
     */
    public void setFeeSituation(String feeSituation) {
        this.feeSituation = feeSituation;
    }

    /**
     * Set Fee Amount.
     * 
     * @return the fee amount
     */
    @Column(name = "FEE_AMOUNT", columnDefinition = "decimal(10,2)")
    @NotAudited
    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    /**
     * Get Fee Amount.
     * 
     * @param feeAmount the fee amount
     */
    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
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
        return new StringBuilder("FundraiserActivity ( ").append("id = ")
                .append(id).append(tab).append("fundraisingReason = ").append(
                        fundraisingReason).append(tab).append("event = ")
                .append(event).append(tab).append("activityType = ").append(
                        activityType).append(tab).append("occasionType = ")
                .append(occasionType).append(tab)
                .append("otherOccasionType = ").append(otherOccasionType)
                .append(tab).append("otherActivityType = ").append(
                        otherActivityType).append(tab).append("location = ")
                .append(location).append(tab).append("memorialDescription = ")
                .append(memorialDescription).append(tab).append(
                        "fundraisingAsIndicator = ").append(
                        fundraisingAsIndicator).append(tab).append(
                        "organizedEventIndicator = ").append(
                        organizedEventIndicator).append(tab).append(
                        "charitiesSupportedIndicator = ").append(
                        charitiesSupportedIndicator).append(tab).append(
                        "charityContributionIndicator = ").append(
                        charityContributionIndicator).append(tab).append(
                        "openPeriod = ").append(openPeriod).append(tab).append(
                        "fundraisingCharitySplit = ").append(
                        fundraisingCharitySplit).append(tab).append(
                        "eventCompletionDate = ").append(eventCompletionDate)
                        .append(tab).append("totalFundRaised = ").append(
                        totalDonations).append(tab).append(
                        "fundraisingTarget = ").append(fundraisingTarget).append(tab)
                        //.append("eventFeeDetails = ").append(eventFeeDetails).append(tab)
                .append("feeReference = ").append(feeReference).append(tab)
                .append("feeSituation = ").append(feeSituation).append(tab)
                .append("feeAmount = ").append(feeAmount).append(tab)
                .append(super.toString())
                .append(" )").toString();
    }

    /**
     * Get the members of the fundraising team, if there is one.
     * 
     * Extracts Fundraisers from the team and also adds the primary
     * fundraiser.
     * 
     * Marked transient as we don't want to persist a derived property.
     * 
     * @return List<Fundraiser> all team-members attached to activity.
     */
    @Transient
    public List<Fundraiser> getTeamMembers() {
        
        List<Fundraiser> fundraisers = new ArrayList<Fundraiser>();
        
        if ( isTeamActivity() ) {

            // It's possible to get here _before_ the group has been initialised
            // so check for nulls
            FundraiserGroup group = getFundraiserGroup();
            if ( group.getGroupMembers() != null ) {
                Set<FundraiserGroupMember> groupMembers = group.getGroupMembers();
                for (FundraiserGroupMember fundraiserGroupMember : groupMembers) {

                    // Note that at a db level fundraisers are never deleted; they just have endDate set. So
                    // we should only return members where endDate == null
                    if ( fundraiserGroupMember.getEndDatetime() == null ) {
                        fundraisers.add(fundraiserGroupMember.getFundraiser());
                    }
                }
            }
        }
        
        if ( getFundraiser() != null ) {
            fundraisers.add(getFundraiser());
        }
        
        return fundraisers;
    }
    
    /**
     * Return true if this is a team activity.
     * 
     * Derived, so inhibit persistence.
     * Note that quite a lot of things need to be preset, so false negatives are
     * possible, for example if the group doesn't exist yet. Use with care.
     * 
     * @return true if it's a team activity.
     */
    @Transient
    public boolean isTeamActivity() {

        boolean teamActivity = false;
        
        if (MasterDataCodeConstants.FUNDRAISER_AS_GROUP.equals(getFundraisingAsIndicator())) {

            // It's possible to get here _before_ the group has been initialised
            // so check for nulls
            FundraiserGroup group = getFundraiserGroup();
            // Check it's a team group
            if ( group != null &&
                group.getFundraiserGroupType() != null &&
                MasterDataCodeConstants.GROUP_TYPE_TEAM.equals(group.getFundraiserGroupType().getCode())) 
            {
                teamActivity = true;
            }
        }
        
        return teamActivity;
    }

    /**
     * Gets the total fund raised. 
     *
     * Derived value, not stored in db.
     *
     * @return the totalFundRaised
     */
    @Transient
    @NotAudited
    public BigDecimal getTotalTaxBack() {
        return totalTaxBack;
    }

    /**
     * @param totalTaxBack the totalTaxBack to set
     */
    public void setTotalTaxBack(BigDecimal totalTaxBack) {
        this.totalTaxBack = totalTaxBack;
    }

}
