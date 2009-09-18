
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceUserLoginDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceUserLoginDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dobDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dobMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dobYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceUserLoginDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "dobDay",
    "dobMonth",
    "dobYear",
    "emailAddress"
})
public class ServiceUserLoginDetails {

    protected int dobDay;
    protected int dobMonth;
    protected int dobYear;
    @XmlElement(required = true)
    protected String emailAddress;

    /**
     * Gets the value of the dobDay property.
     * 
     */
    public int getDobDay() {
        return dobDay;
    }

    /**
     * Sets the value of the dobDay property.
     * 
     */
    public void setDobDay(int value) {
        this.dobDay = value;
    }

    /**
     * Gets the value of the dobMonth property.
     * 
     */
    public int getDobMonth() {
        return dobMonth;
    }

    /**
     * Sets the value of the dobMonth property.
     * 
     */
    public void setDobMonth(int value) {
        this.dobMonth = value;
    }

    /**
     * Gets the value of the dobYear property.
     * 
     */
    public int getDobYear() {
        return dobYear;
    }

    /**
     * Sets the value of the dobYear property.
     * 
     */
    public void setDobYear(int value) {
        this.dobYear = value;
    }

    /**
     * Gets the value of the emailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the value of the emailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

}
