
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
 *         &lt;element name="ServicePersonDetails" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServicePersonDetails"/>
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
    "servicePersonDetails"
})
@XmlRootElement(name = "fetchPersonDetailsResponse")
public class FetchPersonDetailsResponse {

    @XmlElement(name = "ServicePersonDetails", required = true)
    protected ServicePersonDetails servicePersonDetails;

    /**
     * Gets the value of the servicePersonDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePersonDetails }
     *     
     */
    public ServicePersonDetails getServicePersonDetails() {
        return servicePersonDetails;
    }

    /**
     * Sets the value of the servicePersonDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePersonDetails }
     *     
     */
    public void setServicePersonDetails(ServicePersonDetails value) {
        this.servicePersonDetails = value;
    }

}
