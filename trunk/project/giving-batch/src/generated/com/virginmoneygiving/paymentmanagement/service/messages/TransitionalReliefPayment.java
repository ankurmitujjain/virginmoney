
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransitionalReliefPayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransitionalReliefPayment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="transitionalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransitionalReliefPayment", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", propOrder = {
    "paymentId",
    "charityId",
    "transitionalAmount"
})
public class TransitionalReliefPayment {

    protected long paymentId;
    protected long charityId;
    @XmlElement(required = true)
    protected BigDecimal transitionalAmount;

    /**
     * Gets the value of the paymentId property.
     * 
     */
    public long getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the value of the paymentId property.
     * 
     */
    public void setPaymentId(long value) {
        this.paymentId = value;
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
     * Gets the value of the transitionalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTransitionalAmount() {
        return transitionalAmount;
    }

    /**
     * Sets the value of the transitionalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTransitionalAmount(BigDecimal value) {
        this.transitionalAmount = value;
    }

}
