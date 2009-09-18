
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceCharity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityRegistrationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hmrcRefNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="registrationFeeIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formsCompletedIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityLogoUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityCommonName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityAbbreviations" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mainEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mainTelephoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="websiteURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extendedDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountingPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="annualIncome" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fundraisingPercentage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="onlineIncome" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fundraiserThankyouIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserThankyouText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorThankyouIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorThankyouText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="administrationAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="registeredAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="serviceMainAccountHolderInfo" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAccountHolderInfo"/>
 *         &lt;element name="serviceNotMainAccountHolderInfo" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAccountHolderInfo"/>
 *         &lt;element name="activationPostponed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vmgCharityUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharity", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "charityId",
    "charityName",
    "charityRegistrationNumber",
    "hmrcRefNo",
    "charityStatus",
    "charityDescription",
    "registrationFeeIndicator",
    "formsCompletedIndicator",
    "charityLogoUrl",
    "charityCommonName",
    "charityAbbreviations",
    "mainEmailAddress",
    "mainTelephoneNumber",
    "websiteURL",
    "extendedDescription",
    "accountingPeriodEndDate",
    "annualIncome",
    "fundraisingPercentage",
    "onlineIncome",
    "fundraiserThankyouIndicator",
    "fundraiserThankyouText",
    "donorThankyouIndicator",
    "donorThankyouText",
    "administrationAddress",
    "registeredAddress",
    "serviceMainAccountHolderInfo",
    "serviceNotMainAccountHolderInfo",
    "activationPostponed",
    "vmgCharityUrl"
})
public class ServiceCharity {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityId;
    @XmlElement(required = true)
    protected String charityName;
    @XmlElement(required = true)
    protected String charityRegistrationNumber;
    @XmlElement(required = true)
    protected String hmrcRefNo;
    @XmlElement(required = true)
    protected String charityStatus;
    @XmlElement(required = true, nillable = true)
    protected String charityDescription;
    @XmlElement(required = true, nillable = true)
    protected String registrationFeeIndicator;
    @XmlElement(required = true, nillable = true)
    protected String formsCompletedIndicator;
    @XmlElement(required = true, nillable = true)
    protected String charityLogoUrl;
    @XmlElement(required = true, nillable = true)
    protected String charityCommonName;
    @XmlElement(required = true, nillable = true)
    protected String charityAbbreviations;
    @XmlElement(required = true, nillable = true)
    protected String mainEmailAddress;
    @XmlElement(required = true, nillable = true)
    protected String mainTelephoneNumber;
    @XmlElement(required = true, nillable = true)
    protected String websiteURL;
    @XmlElement(required = true, nillable = true)
    protected String extendedDescription;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar accountingPeriodEndDate;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal annualIncome;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal fundraisingPercentage;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal onlineIncome;
    @XmlElement(required = true, nillable = true)
    protected String fundraiserThankyouIndicator;
    @XmlElement(required = true, nillable = true)
    protected String fundraiserThankyouText;
    @XmlElement(required = true, nillable = true)
    protected String donorThankyouIndicator;
    @XmlElement(required = true, nillable = true)
    protected String donorThankyouText;
    @XmlElement(required = true)
    protected ServiceAddress administrationAddress;
    @XmlElement(required = true)
    protected ServiceAddress registeredAddress;
    @XmlElement(required = true)
    protected ServiceAccountHolderInfo serviceMainAccountHolderInfo;
    @XmlElement(required = true)
    protected ServiceAccountHolderInfo serviceNotMainAccountHolderInfo;
    @XmlElement(required = true, nillable = true)
    protected String activationPostponed;
    @XmlElement(required = true, nillable = true)
    protected String vmgCharityUrl;

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
     * Gets the value of the charityRegistrationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityRegistrationNumber() {
        return charityRegistrationNumber;
    }

    /**
     * Sets the value of the charityRegistrationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityRegistrationNumber(String value) {
        this.charityRegistrationNumber = value;
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

    /**
     * Gets the value of the charityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityStatus() {
        return charityStatus;
    }

    /**
     * Sets the value of the charityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityStatus(String value) {
        this.charityStatus = value;
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
     * Gets the value of the charityLogoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityLogoUrl() {
        return charityLogoUrl;
    }

    /**
     * Sets the value of the charityLogoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityLogoUrl(String value) {
        this.charityLogoUrl = value;
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
     * Gets the value of the charityAbbreviations property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityAbbreviations() {
        return charityAbbreviations;
    }

    /**
     * Sets the value of the charityAbbreviations property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityAbbreviations(String value) {
        this.charityAbbreviations = value;
    }

    /**
     * Gets the value of the mainEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainEmailAddress() {
        return mainEmailAddress;
    }

    /**
     * Sets the value of the mainEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainEmailAddress(String value) {
        this.mainEmailAddress = value;
    }

    /**
     * Gets the value of the mainTelephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainTelephoneNumber() {
        return mainTelephoneNumber;
    }

    /**
     * Sets the value of the mainTelephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainTelephoneNumber(String value) {
        this.mainTelephoneNumber = value;
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
     * Gets the value of the extendedDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendedDescription() {
        return extendedDescription;
    }

    /**
     * Sets the value of the extendedDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendedDescription(String value) {
        this.extendedDescription = value;
    }

    /**
     * Gets the value of the accountingPeriodEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAccountingPeriodEndDate() {
        return accountingPeriodEndDate;
    }

    /**
     * Sets the value of the accountingPeriodEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAccountingPeriodEndDate(XMLGregorianCalendar value) {
        this.accountingPeriodEndDate = value;
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
     * Gets the value of the fundraisingPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFundraisingPercentage() {
        return fundraisingPercentage;
    }

    /**
     * Sets the value of the fundraisingPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFundraisingPercentage(BigDecimal value) {
        this.fundraisingPercentage = value;
    }

    /**
     * Gets the value of the onlineIncome property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOnlineIncome() {
        return onlineIncome;
    }

    /**
     * Sets the value of the onlineIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOnlineIncome(BigDecimal value) {
        this.onlineIncome = value;
    }

    /**
     * Gets the value of the fundraiserThankyouIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserThankyouIndicator() {
        return fundraiserThankyouIndicator;
    }

    /**
     * Sets the value of the fundraiserThankyouIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserThankyouIndicator(String value) {
        this.fundraiserThankyouIndicator = value;
    }

    /**
     * Gets the value of the fundraiserThankyouText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserThankyouText() {
        return fundraiserThankyouText;
    }

    /**
     * Sets the value of the fundraiserThankyouText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserThankyouText(String value) {
        this.fundraiserThankyouText = value;
    }

    /**
     * Gets the value of the donorThankyouIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonorThankyouIndicator() {
        return donorThankyouIndicator;
    }

    /**
     * Sets the value of the donorThankyouIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonorThankyouIndicator(String value) {
        this.donorThankyouIndicator = value;
    }

    /**
     * Gets the value of the donorThankyouText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonorThankyouText() {
        return donorThankyouText;
    }

    /**
     * Sets the value of the donorThankyouText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonorThankyouText(String value) {
        this.donorThankyouText = value;
    }

    /**
     * Gets the value of the administrationAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAddress }
     *     
     */
    public ServiceAddress getAdministrationAddress() {
        return administrationAddress;
    }

    /**
     * Sets the value of the administrationAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAddress }
     *     
     */
    public void setAdministrationAddress(ServiceAddress value) {
        this.administrationAddress = value;
    }

    /**
     * Gets the value of the registeredAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAddress }
     *     
     */
    public ServiceAddress getRegisteredAddress() {
        return registeredAddress;
    }

    /**
     * Sets the value of the registeredAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAddress }
     *     
     */
    public void setRegisteredAddress(ServiceAddress value) {
        this.registeredAddress = value;
    }

    /**
     * Gets the value of the serviceMainAccountHolderInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAccountHolderInfo }
     *     
     */
    public ServiceAccountHolderInfo getServiceMainAccountHolderInfo() {
        return serviceMainAccountHolderInfo;
    }

    /**
     * Sets the value of the serviceMainAccountHolderInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAccountHolderInfo }
     *     
     */
    public void setServiceMainAccountHolderInfo(ServiceAccountHolderInfo value) {
        this.serviceMainAccountHolderInfo = value;
    }

    /**
     * Gets the value of the serviceNotMainAccountHolderInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAccountHolderInfo }
     *     
     */
    public ServiceAccountHolderInfo getServiceNotMainAccountHolderInfo() {
        return serviceNotMainAccountHolderInfo;
    }

    /**
     * Sets the value of the serviceNotMainAccountHolderInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAccountHolderInfo }
     *     
     */
    public void setServiceNotMainAccountHolderInfo(ServiceAccountHolderInfo value) {
        this.serviceNotMainAccountHolderInfo = value;
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
     * Gets the value of the vmgCharityUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVmgCharityUrl() {
        return vmgCharityUrl;
    }

    /**
     * Sets the value of the vmgCharityUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVmgCharityUrl(String value) {
        this.vmgCharityUrl = value;
    }

}
