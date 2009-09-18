
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
 *         &lt;element name="cardTransaction" type="{http://www.virginmoneygiving.com/type/payment-management/}ServiceCardTransaction"/>
 *         &lt;element name="giftAidApplicable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="donationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="donationReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donationDistributionList" type="{http://www.virginmoneygiving.com/type/payment-management/}DonationDistribution" maxOccurs="unbounded"/>
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
    "cardTransaction",
    "giftAidApplicable",
    "donationId",
    "donationReference",
    "paymentReason",
    "donationDistributionList"
})
@XmlRootElement(name = "saveWebDonationRequest")
public class SaveWebDonationRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(required = true)
    protected ServiceCardTransaction cardTransaction;
    protected boolean giftAidApplicable;
    protected long donationId;
    @XmlElement(required = true)
    protected String donationReference;
    @XmlElement(required = true)
    protected String paymentReason;
    @XmlElement(required = true)
    protected List<DonationDistribution> donationDistributionList;

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
     * Gets the value of the giftAidApplicable property.
     * 
     */
    public boolean isGiftAidApplicable() {
        return giftAidApplicable;
    }

    /**
     * Sets the value of the giftAidApplicable property.
     * 
     */
    public void setGiftAidApplicable(boolean value) {
        this.giftAidApplicable = value;
    }

    /**
     * Gets the value of the donationId property.
     * 
     */
    public long getDonationId() {
        return donationId;
    }

    /**
     * Sets the value of the donationId property.
     * 
     */
    public void setDonationId(long value) {
        this.donationId = value;
    }

    /**
     * Gets the value of the donationReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonationReference() {
        return donationReference;
    }

    /**
     * Sets the value of the donationReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonationReference(String value) {
        this.donationReference = value;
    }

    /**
     * Gets the value of the paymentReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentReason() {
        return paymentReason;
    }

    /**
     * Sets the value of the paymentReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentReason(String value) {
        this.paymentReason = value;
    }

    /**
     * Gets the value of the donationDistributionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the donationDistributionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDonationDistributionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DonationDistribution }
     * 
     * 
     */
    public List<DonationDistribution> getDonationDistributionList() {
        if (donationDistributionList == null) {
            donationDistributionList = new ArrayList<DonationDistribution>();
        }
        return this.donationDistributionList;
    }

}
