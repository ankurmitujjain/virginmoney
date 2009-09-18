
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceFundraiser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fundraiserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="userRoleId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="forename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="preferredTelephoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preferredTelephoneNumberType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alternateTelephoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alternateTelephoneNumberType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="termsAndConditionsAccepted" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserSecurityDetails" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceFundraiserSecurityDetails"/>
 *         &lt;element name="profileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VMGMarketingIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserActivities" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceFundraisingReason" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiser", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "fundraiserId",
    "userId",
    "userRoleId",
    "title",
    "forename",
    "surname",
    "countryCode",
    "fundraiserAddress",
    "preferredTelephoneNumber",
    "preferredTelephoneNumberType",
    "alternateTelephoneNumber",
    "alternateTelephoneNumberType",
    "emailAddress",
    "termsAndConditionsAccepted",
    "fundraiserSecurityDetails",
    "profileName",
    "fundraiserUrl",
    "vmgMarketingIndicator",
    "fundraiserActivities"
})
public class ServiceFundraiser {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long userId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long userRoleId;
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
    protected String termsAndConditionsAccepted;
    @XmlElement(required = true)
    protected ServiceFundraiserSecurityDetails fundraiserSecurityDetails;
    @XmlElement(required = true)
    protected String profileName;
    @XmlElement(required = true, nillable = true)
    protected String fundraiserUrl;
    @XmlElement(name = "VMGMarketingIndicator", required = true)
    protected String vmgMarketingIndicator;
    @XmlElement(nillable = true)
    protected List<ServiceFundraisingReason> fundraiserActivities;

    /**
     * Gets the value of the fundraiserId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserId() {
        return fundraiserId;
    }

    /**
     * Sets the value of the fundraiserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserId(Long value) {
        this.fundraiserId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUserId(Long value) {
        this.userId = value;
    }

    /**
     * Gets the value of the userRoleId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUserRoleId() {
        return userRoleId;
    }

    /**
     * Sets the value of the userRoleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUserRoleId(Long value) {
        this.userRoleId = value;
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
     * Gets the value of the fundraiserSecurityDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceFundraiserSecurityDetails }
     *     
     */
    public ServiceFundraiserSecurityDetails getFundraiserSecurityDetails() {
        return fundraiserSecurityDetails;
    }

    /**
     * Sets the value of the fundraiserSecurityDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceFundraiserSecurityDetails }
     *     
     */
    public void setFundraiserSecurityDetails(ServiceFundraiserSecurityDetails value) {
        this.fundraiserSecurityDetails = value;
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

    /**
     * Gets the value of the fundraiserUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserUrl() {
        return fundraiserUrl;
    }

    /**
     * Sets the value of the fundraiserUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserUrl(String value) {
        this.fundraiserUrl = value;
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
     * Gets the value of the fundraiserActivities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fundraiserActivities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFundraiserActivities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFundraisingReason }
     * 
     * 
     */
    public List<ServiceFundraisingReason> getFundraiserActivities() {
        if (fundraiserActivities == null) {
            fundraiserActivities = new ArrayList<ServiceFundraisingReason>();
        }
        return this.fundraiserActivities;
    }

}
