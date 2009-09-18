
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
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="birthDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="birthMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="birthYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="occupation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telephoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alternateTelephoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="permissionsList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
    "charityId",
    "title",
    "firstName",
    "lastName",
    "birthDay",
    "birthMonth",
    "birthYear",
    "occupation",
    "telephoneNumber",
    "alternateTelephoneNumber",
    "email",
    "permissionsList"
})
@XmlRootElement(name = "saveCharityAdministratorRequest")
public class SaveCharityAdministratorRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    protected long charityId;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    protected int birthDay;
    protected int birthMonth;
    protected int birthYear;
    @XmlElement(required = true)
    protected String occupation;
    @XmlElement(required = true)
    protected String telephoneNumber;
    @XmlElement(required = true)
    protected String alternateTelephoneNumber;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected List<String> permissionsList;

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
     * Gets the value of the charityId property.
     * 
     */
    public long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     */
    public void setCharityId(long value) {
        this.charityId = value;
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
     * Gets the value of the telephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets the value of the telephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephoneNumber(String value) {
        this.telephoneNumber = value;
    }

    /**
     * Gets the value of the alternateTelephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateTelephoneNumber() {
        return alternateTelephoneNumber;
    }

    /**
     * Sets the value of the alternateTelephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateTelephoneNumber(String value) {
        this.alternateTelephoneNumber = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
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

}
