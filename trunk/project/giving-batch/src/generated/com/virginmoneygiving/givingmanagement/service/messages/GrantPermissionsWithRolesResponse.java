
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
 *         &lt;element name="permissionsGranted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="basicReponse" type="{http://www.virginmoneygiving.com/type/giving-management/common/}BasicResponse"/>
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
    "permissionsGranted",
    "basicReponse"
})
@XmlRootElement(name = "grantPermissionsWithRolesResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/")
public class GrantPermissionsWithRolesResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/")
    protected boolean permissionsGranted;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the permissionsGranted property.
     * 
     */
    public boolean isPermissionsGranted() {
        return permissionsGranted;
    }

    /**
     * Sets the value of the permissionsGranted property.
     * 
     */
    public void setPermissionsGranted(boolean value) {
        this.permissionsGranted = value;
    }

    /**
     * Gets the value of the basicReponse property.
     * 
     * @return
     *     possible object is
     *     {@link BasicResponse }
     *     
     */
    public BasicResponse getBasicReponse() {
        return basicReponse;
    }

    /**
     * Sets the value of the basicReponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicResponse }
     *     
     */
    public void setBasicReponse(BasicResponse value) {
        this.basicReponse = value;
    }

}
