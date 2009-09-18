
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
 *         &lt;element name="Header" type="{http://www.virginmoneygiving.com/type/header/}MessageHeader"/>
 *         &lt;element name="ServiceDonorPersonalDetails" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceDonorPersonalDetails"/>
 *         &lt;element name="ServiceDonorSecurityDetails" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceDonorSecurityDetails"/>
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
    "serviceDonorPersonalDetails",
    "serviceDonorSecurityDetails"
})
@XmlRootElement(name = "donorPersonalDetailsRequest")
public class DonorPersonalDetailsRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(name = "ServiceDonorPersonalDetails", required = true)
    protected ServiceDonorPersonalDetails serviceDonorPersonalDetails;
    @XmlElement(name = "ServiceDonorSecurityDetails", required = true)
    protected ServiceDonorSecurityDetails serviceDonorSecurityDetails;

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

    /**
     * Gets the value of the serviceDonorSecurityDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceDonorSecurityDetails }
     *     
     */
    public ServiceDonorSecurityDetails getServiceDonorSecurityDetails() {
        return serviceDonorSecurityDetails;
    }

    /**
     * Sets the value of the serviceDonorSecurityDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDonorSecurityDetails }
     *     
     */
    public void setServiceDonorSecurityDetails(ServiceDonorSecurityDetails value) {
        this.serviceDonorSecurityDetails = value;
    }

}
