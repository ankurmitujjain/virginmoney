
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
 *         &lt;element name="ServiceUserPersonalDetails" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceUserPersonalDetails"/>
 *         &lt;element name="ServiceFundraiserSecurityDetails" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceFundraiserSecurityDetails"/>
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
    "serviceUserPersonalDetails",
    "serviceFundraiserSecurityDetails"
})
@XmlRootElement(name = "updateUserPersonalDetailsRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
public class UpdateUserPersonalDetailsRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected MessageHeader header;
    @XmlElement(name = "ServiceUserPersonalDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected ServiceUserPersonalDetails serviceUserPersonalDetails;
    @XmlElement(name = "ServiceFundraiserSecurityDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected ServiceFundraiserSecurityDetails serviceFundraiserSecurityDetails;

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
     * Gets the value of the serviceUserPersonalDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceUserPersonalDetails }
     *     
     */
    public ServiceUserPersonalDetails getServiceUserPersonalDetails() {
        return serviceUserPersonalDetails;
    }

    /**
     * Sets the value of the serviceUserPersonalDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceUserPersonalDetails }
     *     
     */
    public void setServiceUserPersonalDetails(ServiceUserPersonalDetails value) {
        this.serviceUserPersonalDetails = value;
    }

    /**
     * Gets the value of the serviceFundraiserSecurityDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceFundraiserSecurityDetails }
     *     
     */
    public ServiceFundraiserSecurityDetails getServiceFundraiserSecurityDetails() {
        return serviceFundraiserSecurityDetails;
    }

    /**
     * Sets the value of the serviceFundraiserSecurityDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceFundraiserSecurityDetails }
     *     
     */
    public void setServiceFundraiserSecurityDetails(ServiceFundraiserSecurityDetails value) {
        this.serviceFundraiserSecurityDetails = value;
    }

}
