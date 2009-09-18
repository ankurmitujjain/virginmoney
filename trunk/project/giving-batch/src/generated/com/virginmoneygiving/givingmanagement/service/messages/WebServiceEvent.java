
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
 * <p>Java class for WebServiceEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WebServiceEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charitableActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="eventTimeText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="openDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="location" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceLocation"/>
 *         &lt;element name="eventStatus" type="{http://www.virginmoneygiving.com/type/giving-management/event/}ServiceEventStatus"/>
 *         &lt;element name="joiningInstructions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="logoUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="websiteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maximumCharitites" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fundraiserSplitOverrideInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="eventActivitiy" type="{http://www.virginmoneygiving.com/type/giving-management/event/}ServiceEventActivity" maxOccurs="unbounded"/>
 *         &lt;element name="charitableActivity" type="{http://www.virginmoneygiving.com/type/giving-management/event/}ServiceCharitableActivity" maxOccurs="unbounded"/>
 *         &lt;element name="publishInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createJoinInd" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="operationalEventInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebServiceEvent", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", propOrder = {
    "eventId",
    "charityId",
    "charitableActivityId",
    "name",
    "description",
    "eventDateTime",
    "eventTimeText",
    "openDate",
    "expiryDate",
    "location",
    "eventStatus",
    "joiningInstructions",
    "logoUrl",
    "websiteUrl",
    "contactEmailAddress",
    "contactName",
    "contactPhone",
    "maximumCharitites",
    "fundraiserSplitOverrideInd",
    "eventAddress",
    "eventActivitiy",
    "charitableActivity",
    "publishInd",
    "createJoinInd",
    "operationalEventInd"
})
public class WebServiceEvent {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long eventId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charitableActivityId;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true, nillable = true)
    protected String description;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eventDateTime;
    @XmlElement(required = true, nillable = true)
    protected String eventTimeText;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar openDate;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiryDate;
    @XmlElement(required = true, nillable = true)
    protected ServiceLocation location;
    @XmlElement(required = true, nillable = true)
    protected ServiceEventStatus eventStatus;
    @XmlElement(required = true, nillable = true)
    protected String joiningInstructions;
    @XmlElement(required = true, nillable = true)
    protected String logoUrl;
    @XmlElement(required = true, nillable = true)
    protected String websiteUrl;
    @XmlElement(required = true, nillable = true)
    protected String contactEmailAddress;
    @XmlElement(required = true, nillable = true)
    protected String contactName;
    @XmlElement(required = true, nillable = true)
    protected String contactPhone;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer maximumCharitites;
    @XmlElement(required = true, nillable = true)
    protected String fundraiserSplitOverrideInd;
    @XmlElement(required = true, nillable = true)
    protected ServiceAddress eventAddress;
    @XmlElement(required = true, nillable = true)
    protected List<ServiceEventActivity> eventActivitiy;
    @XmlElement(required = true, nillable = true)
    protected List<ServiceCharitableActivity> charitableActivity;
    @XmlElement(required = true, nillable = true)
    protected String publishInd;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer createJoinInd;
    @XmlElement(required = true, nillable = true)
    protected String operationalEventInd;

    /**
     * Gets the value of the eventId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEventId(Long value) {
        this.eventId = value;
    }

    /**
     * Gets the value of the charityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCharityId(Long value) {
        this.charityId = value;
    }

    /**
     * Gets the value of the charitableActivityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCharitableActivityId() {
        return charitableActivityId;
    }

    /**
     * Sets the value of the charitableActivityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCharitableActivityId(Long value) {
        this.charitableActivityId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the eventDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventDateTime() {
        return eventDateTime;
    }

    /**
     * Sets the value of the eventDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventDateTime(XMLGregorianCalendar value) {
        this.eventDateTime = value;
    }

    /**
     * Gets the value of the eventTimeText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventTimeText() {
        return eventTimeText;
    }

    /**
     * Sets the value of the eventTimeText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventTimeText(String value) {
        this.eventTimeText = value;
    }

    /**
     * Gets the value of the openDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOpenDate() {
        return openDate;
    }

    /**
     * Sets the value of the openDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOpenDate(XMLGregorianCalendar value) {
        this.openDate = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate(XMLGregorianCalendar value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceLocation }
     *     
     */
    public ServiceLocation getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceLocation }
     *     
     */
    public void setLocation(ServiceLocation value) {
        this.location = value;
    }

    /**
     * Gets the value of the eventStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceEventStatus }
     *     
     */
    public ServiceEventStatus getEventStatus() {
        return eventStatus;
    }

    /**
     * Sets the value of the eventStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceEventStatus }
     *     
     */
    public void setEventStatus(ServiceEventStatus value) {
        this.eventStatus = value;
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
     * Gets the value of the logoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * Sets the value of the logoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoUrl(String value) {
        this.logoUrl = value;
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
     * Gets the value of the contactEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    /**
     * Sets the value of the contactEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactEmailAddress(String value) {
        this.contactEmailAddress = value;
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
     * Gets the value of the maximumCharitites property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaximumCharitites() {
        return maximumCharitites;
    }

    /**
     * Sets the value of the maximumCharitites property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaximumCharitites(Integer value) {
        this.maximumCharitites = value;
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
     * Gets the value of the eventAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAddress }
     *     
     */
    public ServiceAddress getEventAddress() {
        return eventAddress;
    }

    /**
     * Sets the value of the eventAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAddress }
     *     
     */
    public void setEventAddress(ServiceAddress value) {
        this.eventAddress = value;
    }

    /**
     * Gets the value of the eventActivitiy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventActivitiy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventActivitiy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceEventActivity }
     * 
     * 
     */
    public List<ServiceEventActivity> getEventActivitiy() {
        if (eventActivitiy == null) {
            eventActivitiy = new ArrayList<ServiceEventActivity>();
        }
        return this.eventActivitiy;
    }

    /**
     * Gets the value of the charitableActivity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charitableActivity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharitableActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceCharitableActivity }
     * 
     * 
     */
    public List<ServiceCharitableActivity> getCharitableActivity() {
        if (charitableActivity == null) {
            charitableActivity = new ArrayList<ServiceCharitableActivity>();
        }
        return this.charitableActivity;
    }

    /**
     * Gets the value of the publishInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublishInd() {
        return publishInd;
    }

    /**
     * Sets the value of the publishInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublishInd(String value) {
        this.publishInd = value;
    }

    /**
     * Gets the value of the createJoinInd property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCreateJoinInd() {
        return createJoinInd;
    }

    /**
     * Sets the value of the createJoinInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCreateJoinInd(Integer value) {
        this.createJoinInd = value;
    }

    /**
     * Gets the value of the operationalEventInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationalEventInd() {
        return operationalEventInd;
    }

    /**
     * Sets the value of the operationalEventInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationalEventInd(String value) {
        this.operationalEventInd = value;
    }

}
