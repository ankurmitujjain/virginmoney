
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceCharityAdditionalDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharityAdditionalDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="personIdentifier" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="personForename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="personSurname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryPersonForename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryPersonSurname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityCommonName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="abbreviations" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="websiteURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="logoURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="annualIncome" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="onlineFundraisingIncome" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fundraisingInPercent" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="registrationFeeIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formsCompletedIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dayOfAccountingPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="yearOfAccountingPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monthOfAccountingPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formsIdList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded"/>
 *         &lt;element name="loginEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vmgCharityURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedCategoriesByCharity" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="billingAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="trusteeDetailsFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vmgCheck" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="setupFeeStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="setupFeeUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="setupFeeDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="giftAidFormStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="giftAidFormUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="giftAidFormDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="charityVerifyStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityVerifyUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityVerifyDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="donarBankAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donarUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donarDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="tradingBankAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tradingUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tradingDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="activationPostponed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="updatedUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updatedDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="categoryUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="registeredCharityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hmrcRefNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharityAdditionalDetails", propOrder = {
    "charityId",
    "charityName",
    "personIdentifier",
    "personForename",
    "personSurname",
    "categoryPersonForename",
    "categoryPersonSurname",
    "charityDescription",
    "charityCommonName",
    "abbreviations",
    "emailAddress",
    "phoneNumber",
    "websiteURL",
    "logoURL",
    "annualIncome",
    "onlineFundraisingIncome",
    "fundraisingInPercent",
    "registrationFeeIndicator",
    "formsCompletedIndicator",
    "dayOfAccountingPeriodEndDate",
    "yearOfAccountingPeriodEndDate",
    "monthOfAccountingPeriodEndDate",
    "formsIdList",
    "loginEmailAddress",
    "vmgCharityURL",
    "selectedCategoriesByCharity",
    "billingAddress",
    "trusteeDetailsFlag",
    "vmgCheck",
    "setupFeeStatus",
    "setupFeeUser",
    "setupFeeDateTime",
    "giftAidFormStatus",
    "giftAidFormUser",
    "giftAidFormDateTime",
    "charityVerifyStatus",
    "charityVerifyUser",
    "charityVerifyDateTime",
    "donarBankAccount",
    "donarUser",
    "donarDateTime",
    "tradingBankAccount",
    "tradingUser",
    "tradingDateTime",
    "activationPostponed",
    "createdUser",
    "createdDateTime",
    "updatedUser",
    "updatedDateTime",
    "categoryUser",
    "categoryDateTime",
    "registeredCharityNumber",
    "hmrcRefNo"
})
public class ServiceCharityAdditionalDetails {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityId;
    @XmlElement(required = true, nillable = true)
    protected String charityName;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long personIdentifier;
    @XmlElement(required = true, nillable = true)
    protected String personForename;
    @XmlElement(required = true, nillable = true)
    protected String personSurname;
    @XmlElement(required = true, nillable = true)
    protected String categoryPersonForename;
    @XmlElement(required = true, nillable = true)
    protected String categoryPersonSurname;
    @XmlElement(required = true, nillable = true)
    protected String charityDescription;
    @XmlElement(required = true, nillable = true)
    protected String charityCommonName;
    @XmlElement(required = true, nillable = true)
    protected String abbreviations;
    @XmlElement(required = true, nillable = true)
    protected String emailAddress;
    @XmlElement(required = true, nillable = true)
    protected String phoneNumber;
    @XmlElement(required = true, nillable = true)
    protected String websiteURL;
    @XmlElement(required = true, nillable = true)
    protected String logoURL;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal annualIncome;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal onlineFundraisingIncome;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal fundraisingInPercent;
    @XmlElement(required = true, nillable = true)
    protected String registrationFeeIndicator;
    @XmlElement(required = true, nillable = true)
    protected String formsCompletedIndicator;
    @XmlElement(required = true, nillable = true)
    protected String dayOfAccountingPeriodEndDate;
    @XmlElement(required = true, nillable = true)
    protected String yearOfAccountingPeriodEndDate;
    @XmlElement(required = true, nillable = true)
    protected String monthOfAccountingPeriodEndDate;
    @XmlElement(required = true, nillable = true)
    protected List<Long> formsIdList;
    @XmlElement(required = true, nillable = true)
    protected String loginEmailAddress;
    @XmlElement(required = true, nillable = true)
    protected String vmgCharityURL;
    @XmlElement(type = Long.class)
    protected List<Long> selectedCategoriesByCharity;
    @XmlElement(required = true)
    protected ServiceAddress billingAddress;
    @XmlElement(required = true)
    protected String trusteeDetailsFlag;
    @XmlElement(required = true)
    protected String vmgCheck;
    @XmlElement(required = true)
    protected String setupFeeStatus;
    @XmlElement(required = true)
    protected String setupFeeUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar setupFeeDateTime;
    @XmlElement(required = true)
    protected String giftAidFormStatus;
    @XmlElement(required = true)
    protected String giftAidFormUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar giftAidFormDateTime;
    @XmlElement(required = true)
    protected String charityVerifyStatus;
    @XmlElement(required = true)
    protected String charityVerifyUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar charityVerifyDateTime;
    @XmlElement(required = true)
    protected String donarBankAccount;
    @XmlElement(required = true)
    protected String donarUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar donarDateTime;
    @XmlElement(required = true)
    protected String tradingBankAccount;
    @XmlElement(required = true)
    protected String tradingUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tradingDateTime;
    @XmlElement(required = true)
    protected String activationPostponed;
    @XmlElement(required = true)
    protected String createdUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDateTime;
    @XmlElement(required = true)
    protected String updatedUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedDateTime;
    @XmlElement(required = true)
    protected String categoryUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar categoryDateTime;
    @XmlElement(required = true)
    protected String registeredCharityNumber;
    @XmlElement(required = true)
    protected String hmrcRefNo;

    /**
     * Gets the value of the charityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCharityId(Long value) {
        this.charityId = value;
    }

    /**
     * Gets the value of the charityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the value of the charityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityName(String value) {
        this.charityName = value;
    }

    /**
     * Gets the value of the personIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPersonIdentifier() {
        return personIdentifier;
    }

    /**
     * Sets the value of the personIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPersonIdentifier(Long value) {
        this.personIdentifier = value;
    }

    /**
     * Gets the value of the personForename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonForename() {
        return personForename;
    }

    /**
     * Sets the value of the personForename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonForename(String value) {
        this.personForename = value;
    }

    /**
     * Gets the value of the personSurname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonSurname() {
        return personSurname;
    }

    /**
     * Sets the value of the personSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonSurname(String value) {
        this.personSurname = value;
    }

    /**
     * Gets the value of the categoryPersonForename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryPersonForename() {
        return categoryPersonForename;
    }

    /**
     * Sets the value of the categoryPersonForename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryPersonForename(String value) {
        this.categoryPersonForename = value;
    }

    /**
     * Gets the value of the categoryPersonSurname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryPersonSurname() {
        return categoryPersonSurname;
    }

    /**
     * Sets the value of the categoryPersonSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryPersonSurname(String value) {
        this.categoryPersonSurname = value;
    }

    /**
     * Gets the value of the charityDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityDescription() {
        return charityDescription;
    }

    /**
     * Sets the value of the charityDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityDescription(String value) {
        this.charityDescription = value;
    }

    /**
     * Gets the value of the charityCommonName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityCommonName() {
        return charityCommonName;
    }

    /**
     * Sets the value of the charityCommonName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityCommonName(String value) {
        this.charityCommonName = value;
    }

    /**
     * Gets the value of the abbreviations property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbbreviations() {
        return abbreviations;
    }

    /**
     * Sets the value of the abbreviations property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbbreviations(String value) {
        this.abbreviations = value;
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
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the websiteURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebsiteURL() {
        return websiteURL;
    }

    /**
     * Sets the value of the websiteURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsiteURL(String value) {
        this.websiteURL = value;
    }

    /**
     * Gets the value of the logoURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoURL() {
        return logoURL;
    }

    /**
     * Sets the value of the logoURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoURL(String value) {
        this.logoURL = value;
    }

    /**
     * Gets the value of the annualIncome property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    /**
     * Sets the value of the annualIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAnnualIncome(BigDecimal value) {
        this.annualIncome = value;
    }

    /**
     * Gets the value of the onlineFundraisingIncome property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOnlineFundraisingIncome() {
        return onlineFundraisingIncome;
    }

    /**
     * Sets the value of the onlineFundraisingIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOnlineFundraisingIncome(BigDecimal value) {
        this.onlineFundraisingIncome = value;
    }

    /**
     * Gets the value of the fundraisingInPercent property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFundraisingInPercent() {
        return fundraisingInPercent;
    }

    /**
     * Sets the value of the fundraisingInPercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFundraisingInPercent(BigDecimal value) {
        this.fundraisingInPercent = value;
    }

    /**
     * Gets the value of the registrationFeeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationFeeIndicator() {
        return registrationFeeIndicator;
    }

    /**
     * Sets the value of the registrationFeeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationFeeIndicator(String value) {
        this.registrationFeeIndicator = value;
    }

    /**
     * Gets the value of the formsCompletedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormsCompletedIndicator() {
        return formsCompletedIndicator;
    }

    /**
     * Sets the value of the formsCompletedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormsCompletedIndicator(String value) {
        this.formsCompletedIndicator = value;
    }

    /**
     * Gets the value of the dayOfAccountingPeriodEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDayOfAccountingPeriodEndDate() {
        return dayOfAccountingPeriodEndDate;
    }

    /**
     * Sets the value of the dayOfAccountingPeriodEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDayOfAccountingPeriodEndDate(String value) {
        this.dayOfAccountingPeriodEndDate = value;
    }

    /**
     * Gets the value of the yearOfAccountingPeriodEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYearOfAccountingPeriodEndDate() {
        return yearOfAccountingPeriodEndDate;
    }

    /**
     * Sets the value of the yearOfAccountingPeriodEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYearOfAccountingPeriodEndDate(String value) {
        this.yearOfAccountingPeriodEndDate = value;
    }

    /**
     * Gets the value of the monthOfAccountingPeriodEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthOfAccountingPeriodEndDate() {
        return monthOfAccountingPeriodEndDate;
    }

    /**
     * Sets the value of the monthOfAccountingPeriodEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthOfAccountingPeriodEndDate(String value) {
        this.monthOfAccountingPeriodEndDate = value;
    }

    /**
     * Gets the value of the formsIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formsIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormsIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getFormsIdList() {
        if (formsIdList == null) {
            formsIdList = new ArrayList<Long>();
        }
        return this.formsIdList;
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
     * Gets the value of the vmgCharityURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVmgCharityURL() {
        return vmgCharityURL;
    }

    /**
     * Sets the value of the vmgCharityURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVmgCharityURL(String value) {
        this.vmgCharityURL = value;
    }

    /**
     * Gets the value of the selectedCategoriesByCharity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectedCategoriesByCharity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectedCategoriesByCharity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getSelectedCategoriesByCharity() {
        if (selectedCategoriesByCharity == null) {
            selectedCategoriesByCharity = new ArrayList<Long>();
        }
        return this.selectedCategoriesByCharity;
    }

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAddress }
     *     
     */
    public ServiceAddress getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAddress }
     *     
     */
    public void setBillingAddress(ServiceAddress value) {
        this.billingAddress = value;
    }

    /**
     * Gets the value of the trusteeDetailsFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrusteeDetailsFlag() {
        return trusteeDetailsFlag;
    }

    /**
     * Sets the value of the trusteeDetailsFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrusteeDetailsFlag(String value) {
        this.trusteeDetailsFlag = value;
    }

    /**
     * Gets the value of the vmgCheck property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVmgCheck() {
        return vmgCheck;
    }

    /**
     * Sets the value of the vmgCheck property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVmgCheck(String value) {
        this.vmgCheck = value;
    }

    /**
     * Gets the value of the setupFeeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetupFeeStatus() {
        return setupFeeStatus;
    }

    /**
     * Sets the value of the setupFeeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetupFeeStatus(String value) {
        this.setupFeeStatus = value;
    }

    /**
     * Gets the value of the setupFeeUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetupFeeUser() {
        return setupFeeUser;
    }

    /**
     * Sets the value of the setupFeeUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetupFeeUser(String value) {
        this.setupFeeUser = value;
    }

    /**
     * Gets the value of the setupFeeDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSetupFeeDateTime() {
        return setupFeeDateTime;
    }

    /**
     * Sets the value of the setupFeeDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSetupFeeDateTime(XMLGregorianCalendar value) {
        this.setupFeeDateTime = value;
    }

    /**
     * Gets the value of the giftAidFormStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGiftAidFormStatus() {
        return giftAidFormStatus;
    }

    /**
     * Sets the value of the giftAidFormStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGiftAidFormStatus(String value) {
        this.giftAidFormStatus = value;
    }

    /**
     * Gets the value of the giftAidFormUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGiftAidFormUser() {
        return giftAidFormUser;
    }

    /**
     * Sets the value of the giftAidFormUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGiftAidFormUser(String value) {
        this.giftAidFormUser = value;
    }

    /**
     * Gets the value of the giftAidFormDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGiftAidFormDateTime() {
        return giftAidFormDateTime;
    }

    /**
     * Sets the value of the giftAidFormDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGiftAidFormDateTime(XMLGregorianCalendar value) {
        this.giftAidFormDateTime = value;
    }

    /**
     * Gets the value of the charityVerifyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityVerifyStatus() {
        return charityVerifyStatus;
    }

    /**
     * Sets the value of the charityVerifyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityVerifyStatus(String value) {
        this.charityVerifyStatus = value;
    }

    /**
     * Gets the value of the charityVerifyUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityVerifyUser() {
        return charityVerifyUser;
    }

    /**
     * Sets the value of the charityVerifyUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityVerifyUser(String value) {
        this.charityVerifyUser = value;
    }

    /**
     * Gets the value of the charityVerifyDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCharityVerifyDateTime() {
        return charityVerifyDateTime;
    }

    /**
     * Sets the value of the charityVerifyDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCharityVerifyDateTime(XMLGregorianCalendar value) {
        this.charityVerifyDateTime = value;
    }

    /**
     * Gets the value of the donarBankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonarBankAccount() {
        return donarBankAccount;
    }

    /**
     * Sets the value of the donarBankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonarBankAccount(String value) {
        this.donarBankAccount = value;
    }

    /**
     * Gets the value of the donarUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonarUser() {
        return donarUser;
    }

    /**
     * Sets the value of the donarUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonarUser(String value) {
        this.donarUser = value;
    }

    /**
     * Gets the value of the donarDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDonarDateTime() {
        return donarDateTime;
    }

    /**
     * Sets the value of the donarDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDonarDateTime(XMLGregorianCalendar value) {
        this.donarDateTime = value;
    }

    /**
     * Gets the value of the tradingBankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradingBankAccount() {
        return tradingBankAccount;
    }

    /**
     * Sets the value of the tradingBankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradingBankAccount(String value) {
        this.tradingBankAccount = value;
    }

    /**
     * Gets the value of the tradingUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradingUser() {
        return tradingUser;
    }

    /**
     * Sets the value of the tradingUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradingUser(String value) {
        this.tradingUser = value;
    }

    /**
     * Gets the value of the tradingDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTradingDateTime() {
        return tradingDateTime;
    }

    /**
     * Sets the value of the tradingDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTradingDateTime(XMLGregorianCalendar value) {
        this.tradingDateTime = value;
    }

    /**
     * Gets the value of the activationPostponed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivationPostponed() {
        return activationPostponed;
    }

    /**
     * Sets the value of the activationPostponed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivationPostponed(String value) {
        this.activationPostponed = value;
    }

    /**
     * Gets the value of the createdUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * Sets the value of the createdUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedUser(String value) {
        this.createdUser = value;
    }

    /**
     * Gets the value of the createdDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets the value of the createdDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDateTime(XMLGregorianCalendar value) {
        this.createdDateTime = value;
    }

    /**
     * Gets the value of the updatedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * Sets the value of the updatedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedUser(String value) {
        this.updatedUser = value;
    }

    /**
     * Gets the value of the updatedDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedDateTime() {
        return updatedDateTime;
    }

    /**
     * Sets the value of the updatedDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedDateTime(XMLGregorianCalendar value) {
        this.updatedDateTime = value;
    }

    /**
     * Gets the value of the categoryUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryUser() {
        return categoryUser;
    }

    /**
     * Sets the value of the categoryUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryUser(String value) {
        this.categoryUser = value;
    }

    /**
     * Gets the value of the categoryDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCategoryDateTime() {
        return categoryDateTime;
    }

    /**
     * Sets the value of the categoryDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCategoryDateTime(XMLGregorianCalendar value) {
        this.categoryDateTime = value;
    }

    /**
     * Gets the value of the registeredCharityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegisteredCharityNumber() {
        return registeredCharityNumber;
    }

    /**
     * Sets the value of the registeredCharityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegisteredCharityNumber(String value) {
        this.registeredCharityNumber = value;
    }

    /**
     * Gets the value of the hmrcRefNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHmrcRefNo() {
        return hmrcRefNo;
    }

    /**
     * Sets the value of the hmrcRefNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHmrcRefNo(String value) {
        this.hmrcRefNo = value;
    }

}
