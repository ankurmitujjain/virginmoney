
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
 *         &lt;element name="ServiceUserRoleDetails" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceUserRoleDetails"/>
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
    "serviceUserRoleDetails"
})
@XmlRootElement(name = "fetchUserRolesResponse")
public class FetchUserRolesResponse {

    @XmlElement(name = "ServiceUserRoleDetails", required = true)
    protected ServiceUserRoleDetails serviceUserRoleDetails;

    /**
     * Gets the value of the serviceUserRoleDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceUserRoleDetails }
     *     
     */
    public ServiceUserRoleDetails getServiceUserRoleDetails() {
        return serviceUserRoleDetails;
    }

    /**
     * Sets the value of the serviceUserRoleDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceUserRoleDetails }
     *     
     */
    public void setServiceUserRoleDetails(ServiceUserRoleDetails value) {
        this.serviceUserRoleDetails = value;
    }

}
