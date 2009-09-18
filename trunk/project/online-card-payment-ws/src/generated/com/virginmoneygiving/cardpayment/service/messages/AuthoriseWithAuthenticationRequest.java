
package com.virginmoneygiving.cardpayment.service.messages;

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
 *         &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentDetails" type="{http://www.virginmoneygiving.com/type/online-card-payment/card-payment}ServicePaymentDetails"/>
 *         &lt;element name="cardDetails" type="{http://www.virginmoneygiving.com/type/online-card-payment/card-payment}ServiceCardDetails"/>
 *         &lt;sequence>
 *           &lt;element name="authResponseData" type="{http://www.virginmoneygiving.com/type/online-card-payment/card-payment}ServiceNameValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
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
    "guid",
    "paymentDetails",
    "cardDetails",
    "authResponseData"
})
@XmlRootElement(name = "AuthoriseWithAuthenticationRequest")
public class AuthoriseWithAuthenticationRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(required = true)
    protected String guid;
    @XmlElement(required = true)
    protected ServicePaymentDetails paymentDetails;
    @XmlElement(required = true)
    protected ServiceCardDetails cardDetails;
    protected List<ServiceNameValuePair> authResponseData;

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
     * Gets the value of the guid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the value of the guid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuid(String value) {
        this.guid = value;
    }

    /**
     * Gets the value of the paymentDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePaymentDetails }
     *     
     */
    public ServicePaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    /**
     * Sets the value of the paymentDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePaymentDetails }
     *     
     */
    public void setPaymentDetails(ServicePaymentDetails value) {
        this.paymentDetails = value;
    }

    /**
     * Gets the value of the cardDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCardDetails }
     *     
     */
    public ServiceCardDetails getCardDetails() {
        return cardDetails;
    }

    /**
     * Sets the value of the cardDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCardDetails }
     *     
     */
    public void setCardDetails(ServiceCardDetails value) {
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
     * {@link ServiceNameValuePair }
     * 
     * 
     */
    public List<ServiceNameValuePair> getAuthResponseData() {
        if (authResponseData == null) {
            authResponseData = new ArrayList<ServiceNameValuePair>();
        }
        return this.authResponseData;
    }

}
