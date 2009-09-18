
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="permissionsList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="rolesList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
    "userId",
    "permissionsList",
    "rolesList"
})
@XmlRootElement(name = "grantPermissionsWithRolesRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/")
public class GrantPermissionsWithRolesRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/")
    protected long userId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true)
    protected List<String> permissionsList;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true)
    protected List<String> rolesList;

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
     * Gets the value of the userId property.
     * 
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(long value) {
        this.userId = value;
    }

    /**
     * Gets the value of the permissionsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the permissionsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPermissionsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPermissionsList() {
        if (permissionsList == null) {
            permissionsList = new ArrayList<String>();
        }
        return this.permissionsList;
    }

    /**
     * Gets the value of the rolesList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rolesList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRolesList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRolesList() {
        if (rolesList == null) {
            rolesList = new ArrayList<String>();
        }
        return this.rolesList;
    }

}
