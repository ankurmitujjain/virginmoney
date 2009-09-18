
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
 *         &lt;element name="trusteeDetails" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceTrusteeDetails"/>
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
    "trusteeDetails"
})
@XmlRootElement(name = "saveTrusteeDetailsResponse")
public class SaveTrusteeDetailsResponse {

    @XmlElement(required = true)
    protected ServiceTrusteeDetails trusteeDetails;

    /**
     * Gets the value of the trusteeDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceTrusteeDetails }
     *     
     */
    public ServiceTrusteeDetails getTrusteeDetails() {
        return trusteeDetails;
    }

    /**
     * Sets the value of the trusteeDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceTrusteeDetails }
     *     
     */
    public void setTrusteeDetails(ServiceTrusteeDetails value) {
        this.trusteeDetails = value;
    }

}
