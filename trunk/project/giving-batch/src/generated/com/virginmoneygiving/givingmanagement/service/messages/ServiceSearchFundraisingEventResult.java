
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
 * <p>Java class for ServiceSearchFundraisingEventResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceSearchFundraisingEventResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="eventName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charitySupportedInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserSplitOverrideInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="splitPercentage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityFundSplit" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceFundraiserActivityCharityFundSplit" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceSearchFundraisingEventResult", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "eventId",
    "eventName",
    "location",
    "charityId",
    "charityName",
    "eventDatetime",
    "description",
    "charitySupportedInd",
    "fundraiserSplitOverrideInd",
    "splitPercentage",
    "charityFundSplit"
})
public class ServiceSearchFundraisingEventResult {

    protected long eventId;
    @XmlElement(required = true)
    protected String eventName;
    @XmlElement(required = true)
    protected String location;
    protected long charityId;
    @XmlElement(required = true)
    protected String charityName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eventDatetime;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String charitySupportedInd;
    @XmlElement(required = true)
    protected String fundraiserSplitOverrideInd;
    @XmlElement(required = true)
    protected String splitPercentage;
    @XmlElement(nillable = true)
    protected List<ServiceFundraiserActivityCharityFundSplit> charityFundSplit;

    /**
     * Gets the value of the eventId property.
     * 
     */
    public long getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     */
    public void setEventId(long value) {
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
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the charityId property.
     * 
     */
    public long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     */
    public void setCharityId(long value) {
        this.charityId = value;
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
     * Gets the value of the eventDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventDatetime() {
        return eventDatetime;
    }

    /**
     * Sets the value of the eventDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventDatetime(XMLGregorianCalendar value) {
        this.eventDatetime = value;
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
     * Gets the value of the charitySupportedInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharitySupportedInd() {
        return charitySupportedInd;
    }

    /**
     * Sets the value of the charitySupportedInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharitySupportedInd(String value) {
        this.charitySupportedInd = value;
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
     * Gets the value of the splitPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitPercentage() {
        return splitPercentage;
    }

    /**
     * Sets the value of the splitPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitPercentage(String value) {
        this.splitPercentage = value;
    }

    /**
     * Gets the value of the charityFundSplit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charityFundSplit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharityFundSplit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFundraiserActivityCharityFundSplit }
     * 
     * 
     */
    public List<ServiceFundraiserActivityCharityFundSplit> getCharityFundSplit() {
        if (charityFundSplit == null) {
            charityFundSplit = new ArrayList<ServiceFundraiserActivityCharityFundSplit>();
        }
        return this.charityFundSplit;
    }

}
