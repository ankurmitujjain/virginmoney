
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GivingServiceDonationResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GivingServiceDonationResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="donationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="paymentResult" type="{http://www.virginmoneygiving.com/type/giving-management/card-payment/}GivingServiceAuthorisationResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GivingServiceDonationResult", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", propOrder = {
    "success",
    "donationId",
    "donationDate",
    "paymentResult"
})
public class GivingServiceDonationResult {

    protected boolean success;
    @XmlElement(required = true, nillable = true)
    protected String donationId;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar donationDate;
    @XmlElement(required = true)
    protected GivingServiceAuthorisationResult paymentResult;

    /**
     * Gets the value of the success property.
     * 
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(boolean value) {
        this.success = value;
    }

    /**
     * Gets the value of the donationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonationId() {
        return donationId;
    }

    /**
     * Sets the value of the donationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonationId(String value) {
        this.donationId = value;
    }

    /**
     * Gets the value of the donationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDonationDate() {
        return donationDate;
    }

    /**
     * Sets the value of the donationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDonationDate(XMLGregorianCalendar value) {
        this.donationDate = value;
    }

    /**
     * Gets the value of the paymentResult property.
     * 
     * @return
     *     possible object is
     *     {@link GivingServiceAuthorisationResult }
     *     
     */
    public GivingServiceAuthorisationResult getPaymentResult() {
        return paymentResult;
    }

    /**
     * Sets the value of the paymentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GivingServiceAuthorisationResult }
     *     
     */
    public void setPaymentResult(GivingServiceAuthorisationResult value) {
        this.paymentResult = value;
    }

}
