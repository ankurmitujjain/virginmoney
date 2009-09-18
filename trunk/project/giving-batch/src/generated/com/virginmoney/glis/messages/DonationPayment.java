
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DonationPayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DonationPayment">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.virginmoney.com/type/glis/2009/01}PaymentType">
 *       &lt;sequence>
 *         &lt;element name="amountCurrency" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DonationPayment", propOrder = {
    "amountCurrency"
})
public class DonationPayment
    extends PaymentType
{

    protected Double amountCurrency;

    /**
     * Gets the value of the amountCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmountCurrency() {
        return amountCurrency;
    }

    /**
     * Sets the value of the amountCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmountCurrency(Double value) {
        this.amountCurrency = value;
    }

}
