
package com.virginmoneygiving.cardpayment.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceAuthorisationResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceAuthorisationResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="outcome" type="{http://www.virginmoneygiving.com/type/online-card-payment/card-payment}ServiceAuthorisationOutcomeTypeEnum"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="processorTransactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cardToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;sequence>
 *           &lt;element name="authRequestData" type="{http://www.virginmoneygiving.com/type/online-card-payment/card-payment}ServiceNameValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element name="authTargetUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;sequence>
 *           &lt;element name="extra" type="{http://www.virginmoneygiving.com/type/online-card-payment/card-payment}ServiceNameValuePair" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ServiceAuthorisationResult", namespace = "http://www.virginmoneygiving.com/type/online-card-payment/card-payment", propOrder = {
    "outcome",
    "message",
    "code",
    "processorTransactionId",
    "cardToken",
    "authRequestData",
    "authTargetUrl",
    "extra"
})
public class ServiceAuthorisationResult {

    @XmlElement(required = true)
    protected ServiceAuthorisationOutcomeTypeEnum outcome;
    @XmlElement(required = true, nillable = true)
    protected String message;
    protected int code;
    @XmlElement(required = true, nillable = true)
    protected String processorTransactionId;
    @XmlElement(required = true, nillable = true)
    protected String cardToken;
    protected List<ServiceNameValuePair> authRequestData;
    @XmlElement(required = true, nillable = true)
    protected String authTargetUrl;
    protected List<ServiceNameValuePair> extra;

    /**
     * Gets the value of the outcome property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAuthorisationOutcomeTypeEnum }
     *     
     */
    public ServiceAuthorisationOutcomeTypeEnum getOutcome() {
        return outcome;
    }

    /**
     * Sets the value of the outcome property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAuthorisationOutcomeTypeEnum }
     *     
     */
    public void setOutcome(ServiceAuthorisationOutcomeTypeEnum value) {
        this.outcome = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the code property.
     * 
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the value of the processorTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessorTransactionId() {
        return processorTransactionId;
    }

    /**
     * Sets the value of the processorTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessorTransactionId(String value) {
        this.processorTransactionId = value;
    }

    /**
     * Gets the value of the cardToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardToken() {
        return cardToken;
    }

    /**
     * Sets the value of the cardToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardToken(String value) {
        this.cardToken = value;
    }

    /**
     * Gets the value of the authRequestData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authRequestData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthRequestData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceNameValuePair }
     * 
     * 
     */
    public List<ServiceNameValuePair> getAuthRequestData() {
        if (authRequestData == null) {
            authRequestData = new ArrayList<ServiceNameValuePair>();
        }
        return this.authRequestData;
    }

    /**
     * Gets the value of the authTargetUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthTargetUrl() {
        return authTargetUrl;
    }

    /**
     * Sets the value of the authTargetUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthTargetUrl(String value) {
        this.authTargetUrl = value;
    }

    /**
     * Gets the value of the extra property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extra property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtra().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceNameValuePair }
     * 
     * 
     */
    public List<ServiceNameValuePair> getExtra() {
        if (extra == null) {
            extra = new ArrayList<ServiceNameValuePair>();
        }
        return this.extra;
    }

}
