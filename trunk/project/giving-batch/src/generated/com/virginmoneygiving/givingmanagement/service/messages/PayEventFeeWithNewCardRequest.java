
package com.virginmoneygiving.givingmanagement.service.messages;

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
 *         &lt;element name="eventFeePayDetails" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}GivingServiceEventFeePayDetails"/>
 *         &lt;element name="cardDetails" type="{http://www.virginmoneygiving.com/type/giving-management/card-payment/}GivingServiceCardDetails"/>
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
    "eventFeePayDetails",
    "cardDetails"
})
@XmlRootElement(name = "PayEventFeeWithNewCardRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
public class PayEventFeeWithNewCardRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected GivingServiceEventFeePayDetails eventFeePayDetails;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected GivingServiceCardDetails cardDetails;

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
     * Gets the value of the eventFeePayDetails property.
     * 
     * @return
     *     possible object is
     *     {@link GivingServiceEventFeePayDetails }
     *     
     */
    public GivingServiceEventFeePayDetails getEventFeePayDetails() {
        return eventFeePayDetails;
    }

    /**
     * Sets the value of the eventFeePayDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GivingServiceEventFeePayDetails }
     *     
     */
    public void setEventFeePayDetails(GivingServiceEventFeePayDetails value) {
        this.eventFeePayDetails = value;
    }

    /**
     * Gets the value of the cardDetails property.
     * 
     * @return
     *     possible object is
     *     {@link GivingServiceCardDetails }
     *     
     */
    public GivingServiceCardDetails getCardDetails() {
        return cardDetails;
    }

    /**
     * Sets the value of the cardDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GivingServiceCardDetails }
     *     
     */
    public void setCardDetails(GivingServiceCardDetails value) {
        this.cardDetails = value;
    }

}
