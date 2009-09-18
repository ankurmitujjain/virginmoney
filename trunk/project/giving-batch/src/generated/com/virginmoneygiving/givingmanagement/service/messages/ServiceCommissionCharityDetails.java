
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCommissionCharityDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCommissionCharityDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="registrationNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="subsidiaryNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="orgType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="address" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="charityWebSiteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="derivedUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="companyNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCommissionCharityDetails", propOrder = {
    "id",
    "charityName",
    "registrationNumber",
    "subsidiaryNumber",
    "orgType",
    "address",
    "charityWebSiteUrl",
    "derivedUrl",
    "companyNumber"
})
public class ServiceCommissionCharityDetails {

    protected long id;
    @XmlElement(required = true)
    protected String charityName;
    protected long registrationNumber;
    protected long subsidiaryNumber;
    @XmlElement(required = true)
    protected String orgType;
    @XmlElement(required = true)
    protected ServiceAddress address;
    @XmlElement(required = true)
    protected String charityWebSiteUrl;
    @XmlElement(required = true)
    protected String derivedUrl;
    @XmlElement(required = true)
    protected String companyNumber;

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
     * Gets the value of the registrationNumber property.
     * 
     */
    public long getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the value of the registrationNumber property.
     * 
     */
    public void setRegistrationNumber(long value) {
        this.registrationNumber = value;
    }

    /**
     * Gets the value of the subsidiaryNumber property.
     * 
     */
    public long getSubsidiaryNumber() {
        return subsidiaryNumber;
    }

    /**
     * Sets the value of the subsidiaryNumber property.
     * 
     */
    public void setSubsidiaryNumber(long value) {
        this.subsidiaryNumber = value;
    }

    /**
     * Gets the value of the orgType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * Sets the value of the orgType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgType(String value) {
        this.orgType = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAddress }
     *     
     */
    public ServiceAddress getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAddress }
     *     
     */
    public void setAddress(ServiceAddress value) {
        this.address = value;
    }

    /**
     * Gets the value of the charityWebSiteUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityWebSiteUrl() {
        return charityWebSiteUrl;
    }

    /**
     * Sets the value of the charityWebSiteUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityWebSiteUrl(String value) {
        this.charityWebSiteUrl = value;
    }

    /**
     * Gets the value of the derivedUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDerivedUrl() {
        return derivedUrl;
    }

    /**
     * Sets the value of the derivedUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDerivedUrl(String value) {
        this.derivedUrl = value;
    }

    /**
     * Gets the value of the companyNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyNumber() {
        return companyNumber;
    }

    /**
     * Sets the value of the companyNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyNumber(String value) {
        this.companyNumber = value;
    }

}
