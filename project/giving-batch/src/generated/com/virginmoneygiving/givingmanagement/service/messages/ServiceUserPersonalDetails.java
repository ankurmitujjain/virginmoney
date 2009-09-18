
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceUserPersonalDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceUserPersonalDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userid" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="forename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="donorAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="preferredTelephoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preferredTelephoneNumberType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alternateTelephoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alternateTelephoneNumberType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldDateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newDateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="termsAndConditionsAccepted" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityMarketingIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VMGMarketingIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="profileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceUserPersonalDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "userid",
    "title",
    "forename",
    "surname",
    "countryCode",
    "fundraiserAddress",
    "donorAddress",
    "preferredTelephoneNumber",
    "preferredTelephoneNumberType",
    "alternateTelephoneNumber",
    "alternateTelephoneNumberType",
    "emailAddress",
    "oldEmailAddress",
    "oldDateOfBirth",
    "newDateOfBirth",
    "termsAndConditionsAccepted",
    "charityMarketingIndicator",
    "vmgMarketingIndicator",
    "profileName"
})
public class ServiceUserPersonalDetails {

    protected long userid;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String forename;
    @XmlElement(required = true)
    protected String surname;
    @XmlElement(required = true)
    protected String countryCode;
    @XmlElement(required = true)
    protected ServiceAddress fundraiserAddress;
    @XmlElement(required = true)
    protected ServiceAddress donorAddress;
    @XmlElement(required = true)
    protected String preferredTelephoneNumber;
    @XmlElement(required = true)
    protected String preferredTelephoneNumberType;
    @XmlElement(required = true)
    protected String alternateTelephoneNumber;
    @XmlElement(required = true)
    protected String alternateTelephoneNumberType;
    @XmlElement(required = true)
    protected String emailAddress;
    @XmlElement(required = true)
    protected String oldEmailAddress;
    @XmlElement(required = true)
    protected String oldDateOfBirth;
    @XmlElement(required = true)
    protected String newDateOfBirth;
    @XmlElement(required = true)
    protected String termsAndConditionsAccepted;
    @XmlElement(required = true)
    protected String charityMarketingIndicator;
    @XmlElement(name = "VMGMarketingIndicator", required = true)
    protected String vmgMarketingIndicator;
    @XmlElement(required = true)
    protected String profileName;

    /**
     * Gets the value of the userid property.
     * 
     */
    public long getUserid() {
        return userid;
    }

    /**
     * Sets the value of the userid property.
     * 
     */
    public void setUserid(long value) {
        this.userid = value;
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
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
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
     * Gets the value of the preferredTelephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredTelephoneNumber() {
        return preferredTelephoneNumber;
    }

    /**
     * Sets the value of the preferredTelephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredTelephoneNumber(String value) {
        this.preferredTelephoneNumber = value;
    }

    /**
     * Gets the value of the preferredTelephoneNumberType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredTelephoneNumberType() {
        return preferredTelephoneNumberType;
    }

    /**
     * Sets the value of the preferredTelephoneNumberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredTelephoneNumberType(String value) {
        this.preferredTelephoneNumberType = value;
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
     * Gets the value of the alternateTelephoneNumberType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateTelephoneNumberType() {
        return alternateTelephoneNumberType;
    }

    /**
     * Sets the value of the alternateTelephoneNumberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateTelephoneNumberType(String value) {
        this.alternateTelephoneNumberType = value;
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

    /**
     * Gets the value of the oldEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldEmailAddress() {
        return oldEmailAddress;
    }

    /**
     * Sets the value of the oldEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldEmailAddress(String value) {
        this.oldEmailAddress = value;
    }

    /**
     * Gets the value of the oldDateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldDateOfBirth() {
        return oldDateOfBirth;
    }

    /**
     * Sets the value of the oldDateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldDateOfBirth(String value) {
        this.oldDateOfBirth = value;
    }

    /**
     * Gets the value of the newDateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewDateOfBirth() {
        return newDateOfBirth;
    }

    /**
     * Sets the value of the newDateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewDateOfBirth(String value) {
        this.newDateOfBirth = value;
    }

    /**
     * Gets the value of the termsAndConditionsAccepted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermsAndConditionsAccepted() {
        return termsAndConditionsAccepted;
    }

    /**
     * Sets the value of the termsAndConditionsAccepted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermsAndConditionsAccepted(String value) {
        this.termsAndConditionsAccepted = value;
    }

    /**
     * Gets the value of the charityMarketingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityMarketingIndicator() {
        return charityMarketingIndicator;
    }

    /**
     * Sets the value of the charityMarketingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityMarketingIndicator(String value) {
        this.charityMarketingIndicator = value;
    }

    /**
     * Gets the value of the vmgMarketingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVMGMarketingIndicator() {
        return vmgMarketingIndicator;
    }

    /**
     * Sets the value of the vmgMarketingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVMGMarketingIndicator(String value) {
        this.vmgMarketingIndicator = value;
    }

    /**
     * Gets the value of the profileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * Sets the value of the profileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfileName(String value) {
        this.profileName = value;
    }

}
