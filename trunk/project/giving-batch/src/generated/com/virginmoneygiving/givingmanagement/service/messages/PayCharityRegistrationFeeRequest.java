
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
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityRegistrationFeeAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
    "personId",
    "charityRegistrationFeeAmount",
    "cardDetails"
})
@XmlRootElement(name = "PayCharityRegistrationFeeRequest")
public class PayCharityRegistrationFeeRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    protected long personId;
    @XmlElement(required = true)
    protected BigDecimal charityRegistrationFeeAmount;
    @XmlElement(required = true)
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
     * Gets the value of the personId property.
     * 
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Sets the value of the personId property.
     * 
     */
    public void setPersonId(long value) {
        this.personId = value;
    }

    /**
     * Gets the value of the charityRegistrationFeeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCharityRegistrationFeeAmount() {
        return charityRegistrationFeeAmount;
    }

    /**
     * Sets the value of the charityRegistrationFeeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCharityRegistrationFeeAmount(BigDecimal value) {
        this.charityRegistrationFeeAmount = value;
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
