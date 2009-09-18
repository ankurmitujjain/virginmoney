
package com.virginmoneygiving.givingmanagement.service.messages;

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
 *         &lt;element name="checkForProfanityResponseDetails" type="{http://www.virginmoneygiving.com/type/giving-management/operations/}checkForProfanityResponseDetails"/>
 *         &lt;element name="basicReponse" type="{http://www.virginmoneygiving.com/type/giving-management/common/}BasicResponse"/>
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
    "checkForProfanityResponseDetails",
    "basicReponse"
})
@XmlRootElement(name = "checkForProfanityResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
public class CheckForProfanityResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected CheckForProfanityResponseDetails checkForProfanityResponseDetails;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the checkForProfanityResponseDetails property.
     * 
     * @return
     *     possible object is
     *     {@link CheckForProfanityResponseDetails }
     *     
     */
    public CheckForProfanityResponseDetails getCheckForProfanityResponseDetails() {
        return checkForProfanityResponseDetails;
    }

    /**
     * Sets the value of the checkForProfanityResponseDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckForProfanityResponseDetails }
     *     
     */
    public void setCheckForProfanityResponseDetails(CheckForProfanityResponseDetails value) {
        this.checkForProfanityResponseDetails = value;
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
