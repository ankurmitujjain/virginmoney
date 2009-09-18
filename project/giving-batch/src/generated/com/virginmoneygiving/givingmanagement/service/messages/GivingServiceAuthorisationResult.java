
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GivingServiceAuthorisationResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GivingServiceAuthorisationResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="outcome" type="{http://www.virginmoneygiving.com/type/giving-management/card-payment/}GivingServiceAuthorisationOutcomeTypeEnum"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="processorTransactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authTargetUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cardToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authRequestData" type="{http://www.virginmoneygiving.com/type/giving-management/card-payment/}GivingServiceNameValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extra" type="{http://www.virginmoneygiving.com/type/giving-management/card-payment/}GivingServiceNameValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="validationMessages" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GivingServiceAuthorisationResult", namespace = "http://www.virginmoneygiving.com/type/giving-management/card-payment/", propOrder = {
    "outcome",
    "message",
    "code",
    "processorTransactionId",
    "authTargetUrl",
    "guid",
    "cardToken",
    "authRequestData",
    "extra",
    "validationMessages"
})
public class GivingServiceAuthorisationResult {

    @XmlElement(required = true)
    protected GivingServiceAuthorisationOutcomeTypeEnum outcome;
    @XmlElement(required = true, nillable = true)
    protected String message;
    protected int code;
    @XmlElement(required = true, nillable = true)
    protected String processorTransactionId;
    @XmlElement(required = true, nillable = true)
    protected String authTargetUrl;
    @XmlElement(required = true, nillable = true)
    protected String guid;
    @XmlElement(required = true, nillable = true)
    protected String cardToken;
    @XmlElement(nillable = true)
    protected List<GivingServiceNameValuePair> authRequestData;
    @XmlElement(nillable = true)
    protected List<GivingServiceNameValuePair> extra;
    @XmlElement(nillable = true)
    protected List<String> validationMessages;

    /**
     * Gets the value of the outcome property.
     * 
     * @return
     *     possible object is
     *     {@link GivingServiceAuthorisationOutcomeTypeEnum }
     *     
     */
    public GivingServiceAuthorisationOutcomeTypeEnum getOutcome() {
        return outcome;
    }

    /**
     * Sets the value of the outcome property.
     * 
     * @param value
     *     allowed object is
     *     {@link GivingServiceAuthorisationOutcomeTypeEnum }
     *     
     */
    public void setOutcome(GivingServiceAuthorisationOutcomeTypeEnum value) {
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
     * {@link GivingServiceNameValuePair }
     * 
     * 
     */
    public List<GivingServiceNameValuePair> getAuthRequestData() {
        if (authRequestData == null) {
            authRequestData = new ArrayList<GivingServiceNameValuePair>();
        }
        return this.authRequestData;
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
     * {@link GivingServiceNameValuePair }
     * 
     * 
     */
    public List<GivingServiceNameValuePair> getExtra() {
        if (extra == null) {
            extra = new ArrayList<GivingServiceNameValuePair>();
        }
        return this.extra;
    }

    /**
     * Gets the value of the validationMessages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validationMessages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidationMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValidationMessages() {
        if (validationMessages == null) {
            validationMessages = new ArrayList<String>();
        }
        return this.validationMessages;
    }

}
