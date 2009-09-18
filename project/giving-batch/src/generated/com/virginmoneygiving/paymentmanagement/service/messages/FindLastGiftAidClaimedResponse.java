
package com.virginmoneygiving.paymentmanagement.service.messages;

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
 *         &lt;element name="previousGiftAidClaimDetails" type="{http://www.virginmoneygiving.com/type/payment-management/operations/}PreviousGiftAidClaimDetails"/>
 *         &lt;element name="basicReponse" type="{http://www.virginmoneygiving.com/type/payment-management/common/}BasicResponse"/>
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
    "previousGiftAidClaimDetails",
    "basicReponse"
})
@XmlRootElement(name = "findLastGiftAidClaimedResponse", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class FindLastGiftAidClaimedResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected PreviousGiftAidClaimDetails previousGiftAidClaimDetails;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the previousGiftAidClaimDetails property.
     * 
     * @return
     *     possible object is
     *     {@link PreviousGiftAidClaimDetails }
     *     
     */
    public PreviousGiftAidClaimDetails getPreviousGiftAidClaimDetails() {
        return previousGiftAidClaimDetails;
    }

    /**
     * Sets the value of the previousGiftAidClaimDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link PreviousGiftAidClaimDetails }
     *     
     */
    public void setPreviousGiftAidClaimDetails(PreviousGiftAidClaimDetails value) {
        this.previousGiftAidClaimDetails = value;
    }

    /**
     * Gets the value of the basicReponse property.
     * 
     * @return
     *     possible object is
     *     {@link BasicResponse }
     *     
     */
    public BasicResponse getBasicReponse() {
        return basicReponse;
    }

    /**
     * Sets the value of the basicReponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicResponse }
     *     
     */
    public void setBasicReponse(BasicResponse value) {
        this.basicReponse = value;
    }

}
