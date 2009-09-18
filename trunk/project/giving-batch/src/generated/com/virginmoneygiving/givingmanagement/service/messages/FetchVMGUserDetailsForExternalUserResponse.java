
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
 *         &lt;element name="serviceVMGUserDetails" type="{http://www.virginmoneygiving.com/type/giving-management/common/}serviceVMGUserDetails"/>
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
    "serviceVMGUserDetails"
})
@XmlRootElement(name = "fetchVMGUserDetailsForExternalUserResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/")
public class FetchVMGUserDetailsForExternalUserResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true)
    protected ServiceVMGUserDetails serviceVMGUserDetails;

    /**
     * Gets the value of the serviceVMGUserDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceVMGUserDetails }
     *     
     */
    public ServiceVMGUserDetails getServiceVMGUserDetails() {
        return serviceVMGUserDetails;
    }

    /**
     * Sets the value of the serviceVMGUserDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceVMGUserDetails }
     *     
     */
    public void setServiceVMGUserDetails(ServiceVMGUserDetails value) {
        this.serviceVMGUserDetails = value;
    }

}
