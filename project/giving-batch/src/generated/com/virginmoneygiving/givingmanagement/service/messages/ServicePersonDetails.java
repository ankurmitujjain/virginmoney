
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServicePersonDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServicePersonDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="forename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dobDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dobMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dobYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="occupation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="personalAddresses" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress" maxOccurs="unbounded"/>
 *         &lt;element name="fundraiserAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="donorAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="emailAddresses" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceEmailAddress" maxOccurs="unbounded"/>
 *         &lt;element name="contactEmailAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceEmailAddress"/>
 *         &lt;element name="personalEmailAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceEmailAddress"/>
 *         &lt;element name="telephoneNumbers" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceTelephoneNumber" maxOccurs="unbounded"/>
 *         &lt;element name="preferredTelephoneNumber" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceTelephoneNumber"/>
 *         &lt;element name="alternateTelephoneNumber" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceTelephoneNumber"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServicePersonDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "personId",
    "surname",
    "forename",
    "title",
    "dobDay",
    "dobMonth",
    "dobYear",
    "occupation",
    "userType",
    "personalAddresses",
    "fundraiserAddress",
    "donorAddress",
    "emailAddresses",
    "contactEmailAddress",
    "personalEmailAddress",
    "telephoneNumbers",
    "preferredTelephoneNumber",
    "alternateTelephoneNumber"
})
public class ServicePersonDetails {

    protected long personId;
    @XmlElement(required = true)
    protected String surname;
    @XmlElement(required = true)
    protected String forename;
    @XmlElement(required = true)
    protected String title;
    protected int dobDay;
    protected int dobMonth;
    protected int dobYear;
    @XmlElement(required = true)
    protected String occupation;
    @XmlElement(required = true)
    protected String userType;
    @XmlElement(required = true)
    protected List<ServiceAddress> personalAddresses;
    @XmlElement(required = true)
    protected ServiceAddress fundraiserAddress;
    @XmlElement(required = true)
    protected ServiceAddress donorAddress;
    @XmlElement(required = true)
    protected List<ServiceEmailAddress> emailAddresses;
    @XmlElement(required = true)
    protected ServiceEmailAddress contactEmailAddress;
    @XmlElement(required = true)
    protected ServiceEmailAddress personalEmailAddress;
    @XmlElement(required = true)
    protected List<ServiceTelephoneNumber> telephoneNumbers;
    @XmlElement(required = true)
    protected ServiceTelephoneNumber preferredTelephoneNumber;
    @XmlElement(required = true)
    protected ServiceTelephoneNumber alternateTelephoneNumber;

    /**
     * Gets the value of the personId property.
     * 
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Sets the value of the personId property.
     * 
     */
    public void setPersonId(long value) {
        this.personId = value;
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
     * Gets the value of the userType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserType(String value) {
        this.userType = value;
    }

    /**
     * Gets the value of the personalAddresses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personalAddresses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonalAddresses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceAddress }
     * 
     * 
     */
    public List<ServiceAddress> getPersonalAddresses() {
        if (personalAddresses == null) {
            personalAddresses = new ArrayList<ServiceAddress>();
        }
        return this.personalAddresses;
    }

    /**
     * Gets the value of the fundraiserAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAddress }
     *     
     */
    public ServiceAddress getFundraiserAddress() {
        return fundraiserAddress;
    }

    /**
     * Sets the value of the fundraiserAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAddress }
     *     
     */
    public void setFundraiserAddress(ServiceAddress value) {
        this.fundraiserAddress = value;
    }

    /**
     * Gets the value of the donorAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAddress }
     *     
     */
    public ServiceAddress getDonorAddress() {
        return donorAddress;
    }

    /**
     * Sets the value of the donorAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAddress }
     *     
     */
    public void setDonorAddress(ServiceAddress value) {
        this.donorAddress = value;
    }

    /**
     * Gets the value of the emailAddresses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emailAddresses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmailAddresses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceEmailAddress }
     * 
     * 
     */
    public List<ServiceEmailAddress> getEmailAddresses() {
        if (emailAddresses == null) {
            emailAddresses = new ArrayList<ServiceEmailAddress>();
        }
        return this.emailAddresses;
    }

    /**
     * Gets the value of the contactEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceEmailAddress }
     *     
     */
    public ServiceEmailAddress getContactEmailAddress() {
        return contactEmailAddress;
    }

    /**
     * Sets the value of the contactEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceEmailAddress }
     *     
     */
    public void setContactEmailAddress(ServiceEmailAddress value) {
        this.contactEmailAddress = value;
    }

    /**
     * Gets the value of the personalEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceEmailAddress }
     *     
     */
    public ServiceEmailAddress getPersonalEmailAddress() {
        return personalEmailAddress;
    }

    /**
     * Sets the value of the personalEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceEmailAddress }
     *     
     */
    public void setPersonalEmailAddress(ServiceEmailAddress value) {
        this.personalEmailAddress = value;
    }

    /**
     * Gets the value of the telephoneNumbers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the telephoneNumbers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTelephoneNumbers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceTelephoneNumber }
     * 
     * 
     */
    public List<ServiceTelephoneNumber> getTelephoneNumbers() {
        if (telephoneNumbers == null) {
            telephoneNumbers = new ArrayList<ServiceTelephoneNumber>();
        }
        return this.telephoneNumbers;
    }

    /**
     * Gets the value of the preferredTelephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceTelephoneNumber }
     *     
     */
    public ServiceTelephoneNumber getPreferredTelephoneNumber() {
        return preferredTelephoneNumber;
    }

    /**
     * Sets the value of the preferredTelephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceTelephoneNumber }
     *     
     */
    public void setPreferredTelephoneNumber(ServiceTelephoneNumber value) {
        this.preferredTelephoneNumber = value;
    }

    /**
     * Gets the value of the alternateTelephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceTelephoneNumber }
     *     
     */
    public ServiceTelephoneNumber getAlternateTelephoneNumber() {
        return alternateTelephoneNumber;
    }

    /**
     * Sets the value of the alternateTelephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceTelephoneNumber }
     *     
     */
    public void setAlternateTelephoneNumber(ServiceTelephoneNumber value) {
        this.alternateTelephoneNumber = value;
    }

}
