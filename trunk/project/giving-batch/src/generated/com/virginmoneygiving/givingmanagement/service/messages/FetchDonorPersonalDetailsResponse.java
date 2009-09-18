
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
 *         &lt;element name="ServiceDonorPersonalDetails" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceDonorPersonalDetails"/>
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
    "serviceDonorPersonalDetails"
})
@XmlRootElement(name = "fetchDonorPersonalDetailsResponse")
public class FetchDonorPersonalDetailsResponse {

    @XmlElement(name = "ServiceDonorPersonalDetails", required = true)
    protected ServiceDonorPersonalDetails serviceDonorPersonalDetails;

    /**
     * Gets the value of the serviceDonorPersonalDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceDonorPersonalDetails }
     *     
     */
    public ServiceDonorPersonalDetails getServiceDonorPersonalDetails() {
        return serviceDonorPersonalDetails;
    }

    /**
     * Sets the value of the serviceDonorPersonalDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDonorPersonalDetails }
     *     
     */
    public void setServiceDonorPersonalDetails(ServiceDonorPersonalDetails value) {
        this.serviceDonorPersonalDetails = value;
    }

}
