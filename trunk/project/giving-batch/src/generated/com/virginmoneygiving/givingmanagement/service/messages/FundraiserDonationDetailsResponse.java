
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
 *         &lt;element name="fundraiserDonationDetails" type="{http://www.virginmoneygiving.com/type/giving-management/donor/}ServiceFundraiserDonationDetails"/>
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
    "fundraiserDonationDetails"
})
@XmlRootElement(name = "fundraiserDonationDetailsResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/")
public class FundraiserDonationDetailsResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", required = true)
    protected ServiceFundraiserDonationDetails fundraiserDonationDetails;

    /**
     * Gets the value of the fundraiserDonationDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceFundraiserDonationDetails }
     *     
     */
    public ServiceFundraiserDonationDetails getFundraiserDonationDetails() {
        return fundraiserDonationDetails;
    }

    /**
     * Sets the value of the fundraiserDonationDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceFundraiserDonationDetails }
     *     
     */
    public void setFundraiserDonationDetails(ServiceFundraiserDonationDetails value) {
        this.fundraiserDonationDetails = value;
    }

}
