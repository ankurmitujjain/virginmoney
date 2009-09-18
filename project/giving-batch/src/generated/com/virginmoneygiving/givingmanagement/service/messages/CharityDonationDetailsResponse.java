
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
 *         &lt;element name="charityDonationDetails" type="{http://www.virginmoneygiving.com/type/giving-management/donor/}ServiceCharityDonationDetails"/>
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
    "charityDonationDetails"
})
@XmlRootElement(name = "charityDonationDetailsResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/")
public class CharityDonationDetailsResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", required = true)
    protected ServiceCharityDonationDetails charityDonationDetails;

    /**
     * Gets the value of the charityDonationDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCharityDonationDetails }
     *     
     */
    public ServiceCharityDonationDetails getCharityDonationDetails() {
        return charityDonationDetails;
    }

    /**
     * Sets the value of the charityDonationDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCharityDonationDetails }
     *     
     */
    public void setCharityDonationDetails(ServiceCharityDonationDetails value) {
        this.charityDonationDetails = value;
    }

}
