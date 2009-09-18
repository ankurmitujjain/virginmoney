
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceAccountHolderInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceAccountHolderInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mainInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="occupation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dobDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dobMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dobYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="preferredTelephone" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceTelephoneNumber"/>
 *         &lt;element name="alternateTelephone" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceTelephoneNumber"/>
 *         &lt;element name="emailAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceEmailAddress"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceAccountHolderInfo", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "mainInd",
    "title",
    "firstName",
    "lastName",
    "occupation",
    "dobDay",
    "dobMonth",
    "dobYear",
    "preferredTelephone",
    "alternateTelephone",
    "emailAddress",
    "password"
})
public class ServiceAccountHolderInfo {

    @XmlElement(required = true)
    protected String mainInd;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true, nillable = true)
    protected String occupation;
    protected int dobDay;
    protected int dobMonth;
    protected int dobYear;
    @XmlElement(required = true)
    protected ServiceTelephoneNumber preferredTelephone;
    @XmlElement(required = true)
    protected ServiceTelephoneNumber alternateTelephone;
    @XmlElement(required = true)
    protected ServiceEmailAddress emailAddress;
    @XmlElement(required = true)
    protected String password;

    /**
     * Gets the value of the mainInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainInd() {
        return mainInd;
    }

    /**
     * Sets the value of the mainInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainInd(String value) {
        this.mainInd = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the occupation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets the value of the occupation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccupation(String value) {
        this.occupation = value;
    }

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
     * Gets the value of the preferredTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceTelephoneNumber }
     *     
     */
    public ServiceTelephoneNumber getPreferredTelephone() {
        return preferredTelephone;
    }

    /**
     * Sets the value of the preferredTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceTelephoneNumber }
     *     
     */
    public void setPreferredTelephone(ServiceTelephoneNumber value) {
        this.preferredTelephone = value;
    }

    /**
     * Gets the value of the alternateTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceTelephoneNumber }
     *     
     */
    public ServiceTelephoneNumber getAlternateTelephone() {
        return alternateTelephone;
    }

    /**
     * Sets the value of the alternateTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceTelephoneNumber }
     *     
     */
    public void setAlternateTelephone(ServiceTelephoneNumber value) {
        this.alternateTelephone = value;
    }

    /**
     * Gets the value of the emailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceEmailAddress }
     *     
     */
    public ServiceEmailAddress getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the value of the emailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceEmailAddress }
     *     
     */
    public void setEmailAddress(ServiceEmailAddress value) {
        this.emailAddress = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

}
