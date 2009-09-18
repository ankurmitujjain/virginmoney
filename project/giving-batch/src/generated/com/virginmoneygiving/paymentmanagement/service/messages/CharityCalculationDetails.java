
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CharityCalculationDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CharityCalculationDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donationValue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="vmgTransactionFee" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="vatVmgTransactionFee" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="transactionCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="vatTransactionCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="giftAidAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharityCalculationDetails", propOrder = {
    "charityId",
    "donationValue",
    "vmgTransactionFee",
    "vatVmgTransactionFee",
    "transactionCharge",
    "vatTransactionCharge",
    "giftAidAmount"
})
public class CharityCalculationDetails {

    @XmlElement(required = true)
    protected String charityId;
    @XmlElement(required = true)
    protected BigDecimal donationValue;
    @XmlElement(required = true)
    protected BigDecimal vmgTransactionFee;
    @XmlElement(required = true)
    protected BigDecimal vatVmgTransactionFee;
    @XmlElement(required = true)
    protected BigDecimal transactionCharge;
    @XmlElement(required = true)
    protected BigDecimal vatTransactionCharge;
    @XmlElement(required = true)
    protected BigDecimal giftAidAmount;

    /**
     * Gets the value of the charityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityId(String value) {
        this.charityId = value;
    }

    /**
     * Gets the value of the donationValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDonationValue() {
        return donationValue;
    }

    /**
     * Sets the value of the donationValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDonationValue(BigDecimal value) {
        this.donationValue = value;
    }

    /**
     * Gets the value of the vmgTransactionFee property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVmgTransactionFee() {
        return vmgTransactionFee;
    }

    /**
     * Sets the value of the vmgTransactionFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVmgTransactionFee(BigDecimal value) {
        this.vmgTransactionFee = value;
    }

    /**
     * Gets the value of the vatVmgTransactionFee property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVatVmgTransactionFee() {
        return vatVmgTransactionFee;
    }

    /**
     * Sets the value of the vatVmgTransactionFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVatVmgTransactionFee(BigDecimal value) {
        this.vatVmgTransactionFee = value;
    }

    /**
     * Gets the value of the transactionCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTransactionCharge() {
        return transactionCharge;
    }

    /**
     * Sets the value of the transactionCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTransactionCharge(BigDecimal value) {
        this.transactionCharge = value;
    }

    /**
     * Gets the value of the vatTransactionCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVatTransactionCharge() {
        return vatTransactionCharge;
    }

    /**
     * Sets the value of the vatTransactionCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVatTransactionCharge(BigDecimal value) {
        this.vatTransactionCharge = value;
    }

    /**
     * Gets the value of the giftAidAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGiftAidAmount() {
        return giftAidAmount;
    }

    /**
     * Sets the value of the giftAidAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGiftAidAmount(BigDecimal value) {
        this.giftAidAmount = value;
    }

}
