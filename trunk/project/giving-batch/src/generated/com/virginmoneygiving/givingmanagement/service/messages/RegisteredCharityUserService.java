
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegisteredCharityUserService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegisteredCharityUserService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="forename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loginEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="birthDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="birthMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="birthYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisteredCharityUserService", propOrder = {
    "id",
    "forename",
    "surname",
    "title",
    "loginEmailAddress",
    "birthDay",
    "birthMonth",
    "birthYear"
})
public class RegisteredCharityUserService {

    protected long id;
    @XmlElement(required = true)
    protected String forename;
    @XmlElement(required = true)
    protected String surname;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String loginEmailAddress;
    protected int birthDay;
    protected int birthMonth;
    protected int birthYear;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the forename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets the value of the forename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForename(String value) {
        this.forename = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
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
     * Gets the value of the loginEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginEmailAddress() {
        return loginEmailAddress;
    }

    /**
     * Sets the value of the loginEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginEmailAddress(String value) {
        this.loginEmailAddress = value;
    }

    /**
     * Gets the value of the birthDay property.
     * 
     */
    public int getBirthDay() {
        return birthDay;
    }

    /**
     * Sets the value of the birthDay property.
     * 
     */
    public void setBirthDay(int value) {
        this.birthDay = value;
    }

    /**
     * Gets the value of the birthMonth property.
     * 
     */
    public int getBirthMonth() {
        return birthMonth;
    }

    /**
     * Sets the value of the birthMonth property.
     * 
     */
    public void setBirthMonth(int value) {
        this.birthMonth = value;
    }

    /**
     * Gets the value of the birthYear property.
     * 
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Sets the value of the birthYear property.
     * 
     */
    public void setBirthYear(int value) {
        this.birthYear = value;
    }

}
