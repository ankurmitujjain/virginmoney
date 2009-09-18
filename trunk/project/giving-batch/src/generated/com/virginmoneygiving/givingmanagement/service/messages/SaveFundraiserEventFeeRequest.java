
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.virginmoneygiving.com/type/header/}MessageHeader"/>
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fundraiserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="feeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="eventName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventFeeAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="eventFeeReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventFeeSituation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "eventId",
    "fundraiserId",
    "feeId",
    "eventName",
    "charityId",
    "charityName",
    "eventFeeAmount",
    "eventFeeReference",
    "eventFeeSituation",
    "fundraiserActivityId"
})
@XmlRootElement(name = "SaveFundraiserEventFeeRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class SaveFundraiserEventFeeRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected long eventId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected long fundraiserId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected long feeId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String eventName;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected long charityId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String charityName;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected BigDecimal eventFeeAmount;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String eventFeeReference;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String eventFeeSituation;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected long fundraiserActivityId;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link MessageHeader }
     *     
     */
    public MessageHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageHeader }
     *     
     */
    public void setHeader(MessageHeader value) {
        this.header = value;
    }

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
     * Gets the value of the feeId property.
     * 
     */
    public long getFeeId() {
        return feeId;
    }

    /**
     * Sets the value of the feeId property.
     * 
     */
    public void setFeeId(long value) {
        this.feeId = value;
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
     * Gets the value of the eventFeeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEventFeeAmount() {
        return eventFeeAmount;
    }

    /**
     * Sets the value of the eventFeeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEventFeeAmount(BigDecimal value) {
        this.eventFeeAmount = value;
    }

    /**
     * Gets the value of the eventFeeReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventFeeReference() {
        return eventFeeReference;
    }

    /**
     * Sets the value of the eventFeeReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventFeeReference(String value) {
        this.eventFeeReference = value;
    }

    /**
     * Gets the value of the eventFeeSituation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventFeeSituation() {
        return eventFeeSituation;
    }

    /**
     * Sets the value of the eventFeeSituation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventFeeSituation(String value) {
        this.eventFeeSituation = value;
    }

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

}
