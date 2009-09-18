
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
 *         &lt;element name="donationCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "donationCount"
})
@XmlRootElement(name = "FetchFundraiserActivityDonationCountResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/")
public class FetchFundraiserActivityDonationCountResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/")
    protected long donationCount;

    /**
     * Gets the value of the donationCount property.
     * 
     */
    public long getDonationCount() {
        return donationCount;
    }

    /**
     * Sets the value of the donationCount property.
     * 
     */
    public void setDonationCount(long value) {
        this.donationCount = value;
    }

}
