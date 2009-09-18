
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EventHomePageDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventHomePageDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eventName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="eventTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="websiteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventLogo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="buildingName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="buildingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="postCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="daysToGo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityLogo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="joiningInstructions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="townCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addressLine2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operationEventInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventHomePageDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", propOrder = {
    "eventName",
    "eventLocation",
    "eventDate",
    "eventTime",
    "description",
    "contactName",
    "contactPhone",
    "contactEmail",
    "websiteUrl",
    "eventLogo",
    "buildingName",
    "buildingNumber",
    "postCode",
    "daysToGo",
    "charityLogo",
    "charityName",
    "joiningInstructions",
    "townCity",
    "addressLine1",
    "addressLine2",
    "operationEventInd",
    "eventStatus"
})
public class EventHomePageDetails {

    @XmlElement(required = true)
    protected String eventName;
    @XmlElement(required = true)
    protected String eventLocation;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar eventDate;
    @XmlElement(required = true)
    protected String eventTime;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String contactName;
    @XmlElement(required = true)
    protected String contactPhone;
    @XmlElement(required = true)
    protected String contactEmail;
    @XmlElement(required = true)
    protected String websiteUrl;
    @XmlElement(required = true)
    protected String eventLogo;
    @XmlElement(required = true)
    protected String buildingName;
    @XmlElement(required = true)
    protected String buildingNumber;
    @XmlElement(required = true)
    protected String postCode;
    @XmlElement(required = true)
    protected String daysToGo;
    @XmlElement(required = true)
    protected String charityLogo;
    @XmlElement(required = true)
    protected String charityName;
    @XmlElement(required = true)
    protected String joiningInstructions;
    @XmlElement(required = true)
    protected String townCity;
    @XmlElement(required = true)
    protected String addressLine1;
    @XmlElement(required = true)
    protected String addressLine2;
    @XmlElement(required = true)
    protected String operationEventInd;
    @XmlElement(required = true)
    protected String eventStatus;

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
     * Gets the value of the eventDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventDate() {
        return eventDate;
    }

    /**
     * Sets the value of the eventDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventDate(XMLGregorianCalendar value) {
        this.eventDate = value;
    }

    /**
     * Gets the value of the eventTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * Sets the value of the eventTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventTime(String value) {
        this.eventTime = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactName(String value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the contactPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Sets the value of the contactPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPhone(String value) {
        this.contactPhone = value;
    }

    /**
     * Gets the value of the contactEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets the value of the contactEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactEmail(String value) {
        this.contactEmail = value;
    }

    /**
     * Gets the value of the websiteUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * Sets the value of the websiteUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsiteUrl(String value) {
        this.websiteUrl = value;
    }

    /**
     * Gets the value of the eventLogo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventLogo() {
        return eventLogo;
    }

    /**
     * Sets the value of the eventLogo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventLogo(String value) {
        this.eventLogo = value;
    }

    /**
     * Gets the value of the buildingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Sets the value of the buildingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingName(String value) {
        this.buildingName = value;
    }

    /**
     * Gets the value of the buildingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * Sets the value of the buildingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingNumber(String value) {
        this.buildingNumber = value;
    }

    /**
     * Gets the value of the postCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostCode(String value) {
        this.postCode = value;
    }

    /**
     * Gets the value of the daysToGo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaysToGo() {
        return daysToGo;
    }

    /**
     * Sets the value of the daysToGo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaysToGo(String value) {
        this.daysToGo = value;
    }

    /**
     * Gets the value of the charityLogo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityLogo() {
        return charityLogo;
    }

    /**
     * Sets the value of the charityLogo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityLogo(String value) {
        this.charityLogo = value;
    }

    /**
     * Gets the value of the charityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the value of the charityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityName(String value) {
        this.charityName = value;
    }

    /**
     * Gets the value of the joiningInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJoiningInstructions() {
        return joiningInstructions;
    }

    /**
     * Sets the value of the joiningInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJoiningInstructions(String value) {
        this.joiningInstructions = value;
    }

    /**
     * Gets the value of the townCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTownCity() {
        return townCity;
    }

    /**
     * Sets the value of the townCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTownCity(String value) {
        this.townCity = value;
    }

    /**
     * Gets the value of the addressLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the value of the addressLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine1(String value) {
        this.addressLine1 = value;
    }

    /**
     * Gets the value of the addressLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the value of the addressLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine2(String value) {
        this.addressLine2 = value;
    }

    /**
     * Gets the value of the operationEventInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationEventInd() {
        return operationEventInd;
    }

    /**
     * Sets the value of the operationEventInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationEventInd(String value) {
        this.operationEventInd = value;
    }

    /**
     * Gets the value of the eventStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventStatus() {
        return eventStatus;
    }

    /**
     * Sets the value of the eventStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventStatus(String value) {
        this.eventStatus = value;
    }

}
