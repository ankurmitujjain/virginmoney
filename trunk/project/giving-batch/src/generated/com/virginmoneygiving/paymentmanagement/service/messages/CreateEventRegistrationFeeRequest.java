
package com.virginmoneygiving.paymentmanagement.service.messages;

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
 *         &lt;element name="fundraiserActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="targetBankAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetBankAccountSortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cardTransaction" type="{http://www.virginmoneygiving.com/type/payment-management/}ServiceCardTransaction"/>
 *         &lt;element name="eventRegistration" type="{http://www.virginmoneygiving.com/type/payment-management/}DonationDistribution" maxOccurs="unbounded"/>
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
    "fundraiserActivityId",
    "targetBankAccountNumber",
    "targetBankAccountSortCode",
    "cardTransaction",
    "eventRegistration"
})
@XmlRootElement(name = "createEventRegistrationFeeRequest")
public class CreateEventRegistrationFeeRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserActivityId;
    @XmlElement(required = true)
    protected String targetBankAccountNumber;
    @XmlElement(required = true)
    protected String targetBankAccountSortCode;
    @XmlElement(required = true)
    protected ServiceCardTransaction cardTransaction;
    @XmlElement(required = true)
    protected List<DonationDistribution> eventRegistration;

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
     * Gets the value of the fundraiserActivityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserActivityId() {
        return fundraiserActivityId;
    }

    /**
     * Sets the value of the fundraiserActivityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserActivityId(Long value) {
        this.fundraiserActivityId = value;
    }

    /**
     * Gets the value of the targetBankAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetBankAccountNumber() {
        return targetBankAccountNumber;
    }

    /**
     * Sets the value of the targetBankAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetBankAccountNumber(String value) {
        this.targetBankAccountNumber = value;
    }

    /**
     * Gets the value of the targetBankAccountSortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetBankAccountSortCode() {
        return targetBankAccountSortCode;
    }

    /**
     * Sets the value of the targetBankAccountSortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetBankAccountSortCode(String value) {
        this.targetBankAccountSortCode = value;
    }

    /**
     * Gets the value of the cardTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCardTransaction }
     *     
     */
    public ServiceCardTransaction getCardTransaction() {
        return cardTransaction;
    }

    /**
     * Sets the value of the cardTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCardTransaction }
     *     
     */
    public void setCardTransaction(ServiceCardTransaction value) {
        this.cardTransaction = value;
    }

    /**
     * Gets the value of the eventRegistration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventRegistration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventRegistration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DonationDistribution }
     * 
     * 
     */
    public List<DonationDistribution> getEventRegistration() {
        if (eventRegistration == null) {
            eventRegistration = new ArrayList<DonationDistribution>();
        }
        return this.eventRegistration;
    }

}
