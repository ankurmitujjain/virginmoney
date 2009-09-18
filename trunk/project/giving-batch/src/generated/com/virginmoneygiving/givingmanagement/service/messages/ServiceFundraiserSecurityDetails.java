
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceFundraiserSecurityDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiserSecurityDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dayOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monthOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="yearOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiserSecurityDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "emailAddress",
    "dayOfBirth",
    "monthOfBirth",
    "yearOfBirth",
    "userPassword"
})
public class ServiceFundraiserSecurityDetails {

    @XmlElement(required = true)
    protected String emailAddress;
    protected int dayOfBirth;
    protected int monthOfBirth;
    protected int yearOfBirth;
    @XmlElement(required = true)
    protected String userPassword;

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

    /**
     * Gets the value of the dayOfBirth property.
     * 
     */
    public int getDayOfBirth() {
        return dayOfBirth;
    }

    /**
     * Sets the value of the dayOfBirth property.
     * 
     */
    public void setDayOfBirth(int value) {
        this.dayOfBirth = value;
    }

    /**
     * Gets the value of the monthOfBirth property.
     * 
     */
    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    /**
     * Sets the value of the monthOfBirth property.
     * 
     */
    public void setMonthOfBirth(int value) {
        this.monthOfBirth = value;
    }

    /**
     * Gets the value of the yearOfBirth property.
     * 
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Sets the value of the yearOfBirth property.
     * 
     */
    public void setYearOfBirth(int value) {
        this.yearOfBirth = value;
    }

    /**
     * Gets the value of the userPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the value of the userPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserPassword(String value) {
        this.userPassword = value;
    }

}
