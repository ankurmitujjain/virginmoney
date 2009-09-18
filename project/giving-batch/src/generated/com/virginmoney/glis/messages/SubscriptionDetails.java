
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SubscriptionDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriptionDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subscriptionBankAccount" type="{http://www.virginmoney.com/type/glis/2009/01}BankAccountType"/>
 *         &lt;element name="subscriptionAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Frequency" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PaymentMethod" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="CollectionDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriptionDetails", propOrder = {
    "subscriptionBankAccount",
    "subscriptionAmount",
    "frequency",
    "paymentMethod",
    "collectionDate"
})
public class SubscriptionDetails {

    @XmlElement(required = true)
    protected BankAccountType subscriptionBankAccount;
    protected double subscriptionAmount;
    @XmlElement(name = "Frequency")
    protected int frequency;
    @XmlElement(name = "PaymentMethod", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String paymentMethod;
    @XmlElement(name = "CollectionDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar collectionDate;

    /**
     * Gets the value of the subscriptionBankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link BankAccountType }
     *     
     */
    public BankAccountType getSubscriptionBankAccount() {
        return subscriptionBankAccount;
    }

    /**
     * Sets the value of the subscriptionBankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankAccountType }
     *     
     */
    public void setSubscriptionBankAccount(BankAccountType value) {
        this.subscriptionBankAccount = value;
    }

    /**
     * Gets the value of the subscriptionAmount property.
     * 
     */
    public double getSubscriptionAmount() {
        return subscriptionAmount;
    }

    /**
     * Sets the value of the subscriptionAmount property.
     * 
     */
    public void setSubscriptionAmount(double value) {
        this.subscriptionAmount = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     */
    public void setFrequency(int value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the paymentMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the value of the paymentMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentMethod(String value) {
        this.paymentMethod = value;
    }

    /**
     * Gets the value of the collectionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCollectionDate() {
        return collectionDate;
    }

    /**
     * Sets the value of the collectionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCollectionDate(XMLGregorianCalendar value) {
        this.collectionDate = value;
    }

}
