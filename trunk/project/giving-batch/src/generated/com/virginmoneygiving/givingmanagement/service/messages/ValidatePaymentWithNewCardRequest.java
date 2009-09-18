
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="authResponseData" type="{http://www.virginmoneygiving.com/type/giving-management/card-payment/}GivingServiceNameValuePair" maxOccurs="unbounded" minOccurs="0"/>
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
    "cardDetails",
    "authResponseData"
})
@XmlRootElement(name = "ValidatePaymentWithNewCardRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
public class ValidatePaymentWithNewCardRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected GivingServiceEventFeePayDetails eventFeePayDetails;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected GivingServiceCardDetails cardDetails;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
    protected List<GivingServiceNameValuePair> authResponseData;

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

    /**
     * Gets the value of the authResponseData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authResponseData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthResponseData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GivingServiceNameValuePair }
     * 
     * 
     */
    public List<GivingServiceNameValuePair> getAuthResponseData() {
        if (authResponseData == null) {
            authResponseData = new ArrayList<GivingServiceNameValuePair>();
        }
        return this.authResponseData;
    }

}
