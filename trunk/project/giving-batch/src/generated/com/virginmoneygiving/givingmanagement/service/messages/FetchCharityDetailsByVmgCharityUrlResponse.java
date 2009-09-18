
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
 *         &lt;element name="serviceCharity" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceCharity"/>
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
    "serviceCharity"
})
@XmlRootElement(name = "fetchCharityDetailsByVmgCharityUrlResponse")
public class FetchCharityDetailsByVmgCharityUrlResponse {

    @XmlElement(required = true)
    protected ServiceCharity serviceCharity;

    /**
     * Gets the value of the serviceCharity property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCharity }
     *     
     */
    public ServiceCharity getServiceCharity() {
        return serviceCharity;
    }

    /**
     * Sets the value of the serviceCharity property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCharity }
     *     
     */
    public void setServiceCharity(ServiceCharity value) {
        this.serviceCharity = value;
    }

}
