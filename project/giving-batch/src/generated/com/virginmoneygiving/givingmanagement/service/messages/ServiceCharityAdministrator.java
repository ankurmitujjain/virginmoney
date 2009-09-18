
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCharityAdministrator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharityAdministrator">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityAdministratorId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityAdminForename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityAdminSurname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharityAdministrator", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "charityAdministratorId",
    "charityAdminForename",
    "charityAdminSurname"
})
public class ServiceCharityAdministrator {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityAdministratorId;
    @XmlElement(required = true, nillable = true)
    protected String charityAdminForename;
    @XmlElement(required = true, nillable = true)
    protected String charityAdminSurname;

    /**
     * Gets the value of the charityAdministratorId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCharityAdministratorId() {
        return charityAdministratorId;
    }

    /**
     * Sets the value of the charityAdministratorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCharityAdministratorId(Long value) {
        this.charityAdministratorId = value;
    }

    /**
     * Gets the value of the charityAdminForename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityAdminForename() {
        return charityAdminForename;
    }

    /**
     * Sets the value of the charityAdminForename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityAdminForename(String value) {
        this.charityAdminForename = value;
    }

    /**
     * Gets the value of the charityAdminSurname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityAdminSurname() {
        return charityAdminSurname;
    }

    /**
     * Sets the value of the charityAdminSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityAdminSurname(String value) {
        this.charityAdminSurname = value;
    }

}
