
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
 *         &lt;element name="ServiceRegularDonationDetails" type="{http://www.virginmoneygiving.com/type/giving-management/donor/}ServiceRegularDonationDetails"/>
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
    "serviceRegularDonationDetails"
})
@XmlRootElement(name = "updateRegularDonationRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/")
public class UpdateRegularDonationRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", required = true)
    protected MessageHeader header;
    @XmlElement(name = "ServiceRegularDonationDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", required = true)
    protected ServiceRegularDonationDetails serviceRegularDonationDetails;

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
     * Gets the value of the serviceRegularDonationDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRegularDonationDetails }
     *     
     */
    public ServiceRegularDonationDetails getServiceRegularDonationDetails() {
        return serviceRegularDonationDetails;
    }

    /**
     * Sets the value of the serviceRegularDonationDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRegularDonationDetails }
     *     
     */
    public void setServiceRegularDonationDetails(ServiceRegularDonationDetails value) {
        this.serviceRegularDonationDetails = value;
    }

}
