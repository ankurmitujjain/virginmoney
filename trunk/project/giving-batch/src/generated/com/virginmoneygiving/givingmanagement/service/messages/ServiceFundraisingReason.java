
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceFundraisingReason complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraisingReason">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fundraiserActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fundraiserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="reasonCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="organizedEventIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activityTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="specialOccasionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraisingAsIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="openPeriod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="challengeDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="memorialDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otherActivityType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventCompletionDay" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventCompletionMonth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventCompletionYear" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventCompletionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="otherOccasion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charitiesSupportedIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="singleCharityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="singleCharityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraisingTargetAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityContributionIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundSplit" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceFundraisingReasonFundSplit" maxOccurs="unbounded"/>
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserSplitOverrideInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserPage" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServicePageDetails"/>
 *         &lt;element name="totalFundRaised" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="fundraiserImageUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserGroupUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserGroupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="donationNotificationInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorThankYouInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorThankYouText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraisingReason", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "fundraiserActivityId",
    "fundraiserId",
    "reasonCode",
    "organizedEventIndicator",
    "activityTypeCode",
    "specialOccasionCode",
    "fundraisingAsIndicator",
    "openPeriod",
    "challengeDetails",
    "locationCode",
    "memorialDescription",
    "otherActivityType",
    "eventCompletionDay",
    "eventCompletionMonth",
    "eventCompletionYear",
    "eventCompletionDate",
    "otherOccasion",
    "charitiesSupportedIndicator",
    "singleCharityId",
    "singleCharityName",
    "fundraisingTargetAmount",
    "charityContributionIndicator",
    "fundSplit",
    "eventId",
    "eventName",
    "eventLocation",
    "fundraiserSplitOverrideInd",
    "eventDescription",
    "fundraiserPage",
    "totalFundRaised",
    "fundraiserImageUrl",
    "fundraiserGroupUrl",
    "fundraiserGroupId",
    "donationNotificationInd",
    "donorThankYouInd",
    "donorThankYouText"
})
public class ServiceFundraisingReason {

    protected long fundraiserActivityId;
    protected long fundraiserId;
    @XmlElement(required = true)
    protected String reasonCode;
    @XmlElement(required = true)
    protected String organizedEventIndicator;
    @XmlElement(required = true)
    protected String activityTypeCode;
    @XmlElement(required = true)
    protected String specialOccasionCode;
    @XmlElement(required = true)
    protected String fundraisingAsIndicator;
    @XmlElement(required = true)
    protected String openPeriod;
    @XmlElement(required = true)
    protected String challengeDetails;
    @XmlElement(required = true)
    protected String locationCode;
    @XmlElement(required = true)
    protected String memorialDescription;
    @XmlElement(required = true)
    protected String otherActivityType;
    @XmlElement(required = true)
    protected String eventCompletionDay;
    @XmlElement(required = true)
    protected String eventCompletionMonth;
    @XmlElement(required = true)
    protected String eventCompletionYear;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eventCompletionDate;
    @XmlElement(required = true)
    protected String otherOccasion;
    @XmlElement(required = true)
    protected String charitiesSupportedIndicator;
    @XmlElement(required = true)
    protected String singleCharityId;
    @XmlElement(required = true)
    protected String singleCharityName;
    @XmlElement(required = true)
    protected String fundraisingTargetAmount;
    @XmlElement(required = true)
    protected String charityContributionIndicator;
    @XmlElement(required = true)
    protected List<ServiceFundraisingReasonFundSplit> fundSplit;
    @XmlElement(required = true)
    protected String eventId;
    @XmlElement(required = true)
    protected String eventName;
    @XmlElement(required = true)
    protected String eventLocation;
    @XmlElement(required = true)
    protected String fundraiserSplitOverrideInd;
    @XmlElement(required = true)
    protected String eventDescription;
    @XmlElement(required = true)
    protected ServicePageDetails fundraiserPage;
    @XmlElement(required = true, type = Float.class, nillable = true)
    protected Float totalFundRaised;
    @XmlElement(required = true, nillable = true)
    protected String fundraiserImageUrl;
    @XmlElement(required = true, nillable = true)
    protected String fundraiserGroupUrl;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserGroupId;
    @XmlElement(required = true, nillable = true)
    protected String donationNotificationInd;
    @XmlElement(required = true, nillable = true)
    protected String donorThankYouInd;
    @XmlElement(required = true, nillable = true)
    protected String donorThankYouText;

    /**
     * Gets the value of the fundraiserActivityId property.
     * 
     */
    public long getFundraiserActivityId() {
        return fundraiserActivityId;
    }

    /**
     * Sets the value of the fundraiserActivityId property.
     * 
     */
    public void setFundraiserActivityId(long value) {
        this.fundraiserActivityId = value;
    }

    /**
     * Gets the value of the fundraiserId property.
     * 
     */
    public long getFundraiserId() {
        return fundraiserId;
    }

    /**
     * Sets the value of the fundraiserId property.
     * 
     */
    public void setFundraiserId(long value) {
        this.fundraiserId = value;
    }

    /**
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonCode(String value) {
        this.reasonCode = value;
    }

    /**
     * Gets the value of the organizedEventIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizedEventIndicator() {
        return organizedEventIndicator;
    }

    /**
     * Sets the value of the organizedEventIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizedEventIndicator(String value) {
        this.organizedEventIndicator = value;
    }

    /**
     * Gets the value of the activityTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    /**
     * Sets the value of the activityTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityTypeCode(String value) {
        this.activityTypeCode = value;
    }

    /**
     * Gets the value of the specialOccasionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialOccasionCode() {
        return specialOccasionCode;
    }

    /**
     * Sets the value of the specialOccasionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialOccasionCode(String value) {
        this.specialOccasionCode = value;
    }

    /**
     * Gets the value of the fundraisingAsIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraisingAsIndicator() {
        return fundraisingAsIndicator;
    }

    /**
     * Sets the value of the fundraisingAsIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraisingAsIndicator(String value) {
        this.fundraisingAsIndicator = value;
    }

    /**
     * Gets the value of the openPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenPeriod() {
        return openPeriod;
    }

    /**
     * Sets the value of the openPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenPeriod(String value) {
        this.openPeriod = value;
    }

    /**
     * Gets the value of the challengeDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChallengeDetails() {
        return challengeDetails;
    }

    /**
     * Sets the value of the challengeDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChallengeDetails(String value) {
        this.challengeDetails = value;
    }

    /**
     * Gets the value of the locationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * Sets the value of the locationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationCode(String value) {
        this.locationCode = value;
    }

    /**
     * Gets the value of the memorialDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemorialDescription() {
        return memorialDescription;
    }

    /**
     * Sets the value of the memorialDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemorialDescription(String value) {
        this.memorialDescription = value;
    }

    /**
     * Gets the value of the otherActivityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherActivityType() {
        return otherActivityType;
    }

    /**
     * Sets the value of the otherActivityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherActivityType(String value) {
        this.otherActivityType = value;
    }

    /**
     * Gets the value of the eventCompletionDay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCompletionDay() {
        return eventCompletionDay;
    }

    /**
     * Sets the value of the eventCompletionDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCompletionDay(String value) {
        this.eventCompletionDay = value;
    }

    /**
     * Gets the value of the eventCompletionMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCompletionMonth() {
        return eventCompletionMonth;
    }

    /**
     * Sets the value of the eventCompletionMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCompletionMonth(String value) {
        this.eventCompletionMonth = value;
    }

    /**
     * Gets the value of the eventCompletionYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCompletionYear() {
        return eventCompletionYear;
    }

    /**
     * Sets the value of the eventCompletionYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCompletionYear(String value) {
        this.eventCompletionYear = value;
    }

    /**
     * Gets the value of the eventCompletionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventCompletionDate() {
        return eventCompletionDate;
    }

    /**
     * Sets the value of the eventCompletionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventCompletionDate(XMLGregorianCalendar value) {
        this.eventCompletionDate = value;
    }

    /**
     * Gets the value of the otherOccasion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherOccasion() {
        return otherOccasion;
    }

    /**
     * Sets the value of the otherOccasion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherOccasion(String value) {
        this.otherOccasion = value;
    }

    /**
     * Gets the value of the charitiesSupportedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharitiesSupportedIndicator() {
        return charitiesSupportedIndicator;
    }

    /**
     * Sets the value of the charitiesSupportedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharitiesSupportedIndicator(String value) {
        this.charitiesSupportedIndicator = value;
    }

    /**
     * Gets the value of the singleCharityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSingleCharityId() {
        return singleCharityId;
    }

    /**
     * Sets the value of the singleCharityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSingleCharityId(String value) {
        this.singleCharityId = value;
    }

    /**
     * Gets the value of the singleCharityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSingleCharityName() {
        return singleCharityName;
    }

    /**
     * Sets the value of the singleCharityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSingleCharityName(String value) {
        this.singleCharityName = value;
    }

    /**
     * Gets the value of the fundraisingTargetAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraisingTargetAmount() {
        return fundraisingTargetAmount;
    }

    /**
     * Sets the value of the fundraisingTargetAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraisingTargetAmount(String value) {
        this.fundraisingTargetAmount = value;
    }

    /**
     * Gets the value of the charityContributionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityContributionIndicator() {
        return charityContributionIndicator;
    }

    /**
     * Sets the value of the charityContributionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityContributionIndicator(String value) {
        this.charityContributionIndicator = value;
    }

    /**
     * Gets the value of the fundSplit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fundSplit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFundSplit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFundraisingReasonFundSplit }
     * 
     * 
     */
    public List<ServiceFundraisingReasonFundSplit> getFundSplit() {
        if (fundSplit == null) {
            fundSplit = new ArrayList<ServiceFundraisingReasonFundSplit>();
        }
        return this.fundSplit;
    }

    /**
     * Gets the value of the eventId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventId(String value) {
        this.eventId = value;
    }

    /**
     * Gets the value of the eventName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the value of the eventName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventName(String value) {
        this.eventName = value;
    }

    /**
     * Gets the value of the eventLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * Sets the value of the eventLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventLocation(String value) {
        this.eventLocation = value;
    }

    /**
     * Gets the value of the fundraiserSplitOverrideInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserSplitOverrideInd() {
        return fundraiserSplitOverrideInd;
    }

    /**
     * Sets the value of the fundraiserSplitOverrideInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserSplitOverrideInd(String value) {
        this.fundraiserSplitOverrideInd = value;
    }

    /**
     * Gets the value of the eventDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * Sets the value of the eventDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventDescription(String value) {
        this.eventDescription = value;
    }

    /**
     * Gets the value of the fundraiserPage property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePageDetails }
     *     
     */
    public ServicePageDetails getFundraiserPage() {
        return fundraiserPage;
    }

    /**
     * Sets the value of the fundraiserPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePageDetails }
     *     
     */
    public void setFundraiserPage(ServicePageDetails value) {
        this.fundraiserPage = value;
    }

    /**
     * Gets the value of the totalFundRaised property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTotalFundRaised() {
        return totalFundRaised;
    }

    /**
     * Sets the value of the totalFundRaised property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTotalFundRaised(Float value) {
        this.totalFundRaised = value;
    }

    /**
     * Gets the value of the fundraiserImageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserImageUrl() {
        return fundraiserImageUrl;
    }

    /**
     * Sets the value of the fundraiserImageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserImageUrl(String value) {
        this.fundraiserImageUrl = value;
    }

    /**
     * Gets the value of the fundraiserGroupUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserGroupUrl() {
        return fundraiserGroupUrl;
    }

    /**
     * Sets the value of the fundraiserGroupUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserGroupUrl(String value) {
        this.fundraiserGroupUrl = value;
    }

    /**
     * Gets the value of the fundraiserGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserGroupId() {
        return fundraiserGroupId;
    }

    /**
     * Sets the value of the fundraiserGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserGroupId(Long value) {
        this.fundraiserGroupId = value;
    }

    /**
     * Gets the value of the donationNotificationInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonationNotificationInd() {
        return donationNotificationInd;
    }

    /**
     * Sets the value of the donationNotificationInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonationNotificationInd(String value) {
        this.donationNotificationInd = value;
    }

    /**
     * Gets the value of the donorThankYouInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonorThankYouInd() {
        return donorThankYouInd;
    }

    /**
     * Sets the value of the donorThankYouInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonorThankYouInd(String value) {
        this.donorThankYouInd = value;
    }

    /**
     * Gets the value of the donorThankYouText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonorThankYouText() {
        return donorThankYouText;
    }

    /**
     * Sets the value of the donorThankYouText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonorThankYouText(String value) {
        this.donorThankYouText = value;
    }

}
