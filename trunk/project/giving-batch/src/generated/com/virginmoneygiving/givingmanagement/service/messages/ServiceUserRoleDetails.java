
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceUserRoleDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceUserRoleDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userLoginDetails" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceUserLoginDetails"/>
 *         &lt;element name="roles" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceRole" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceUserRoleDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "userLoginDetails",
    "roles"
})
public class ServiceUserRoleDetails {

    @XmlElement(required = true)
    protected ServiceUserLoginDetails userLoginDetails;
    @XmlElement(required = true)
    protected List<ServiceRole> roles;

    /**
     * Gets the value of the userLoginDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceUserLoginDetails }
     *     
     */
    public ServiceUserLoginDetails getUserLoginDetails() {
        return userLoginDetails;
    }

    /**
     * Sets the value of the userLoginDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceUserLoginDetails }
     *     
     */
    public void setUserLoginDetails(ServiceUserLoginDetails value) {
        this.userLoginDetails = value;
    }

    /**
     * Gets the value of the roles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRole }
     * 
     * 
     */
    public List<ServiceRole> getRoles() {
        if (roles == null) {
            roles = new ArrayList<ServiceRole>();
        }
        return this.roles;
    }

}
