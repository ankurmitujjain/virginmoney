
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceFundraiserDetailsDVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiserDetailsDVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="address" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="permission1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="permission2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserPages" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserFunds" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiserDetailsDVO", propOrder = {
    "id",
    "name",
    "address",
    "dateOfBirth",
    "phoneNumber",
    "permission1",
    "email",
    "permission2",
    "fundraiserPages",
    "fundraiserFunds",
    "customFieldLabel1",
    "customFieldLabel2",
    "customFieldLabel3",
    "customFieldLabel4",
    "customFieldLabel5",
    "customFieldvalue1",
    "customFieldvalue2",
    "customFieldvalue3",
    "customFieldvalue4",
    "customFieldvalue5"
})
public class ServiceFundraiserDetailsDVO {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected ServiceAddress address;
    @XmlElement(required = true)
    protected String dateOfBirth;
    @XmlElement(required = true)
    protected String phoneNumber;
    @XmlElement(required = true)
    protected String permission1;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String permission2;
    @XmlElement(required = true)
    protected String fundraiserPages;
    @XmlElement(required = true)
    protected String fundraiserFunds;
    @XmlElement(required = true)
    protected String customFieldLabel1;
    @XmlElement(required = true)
    protected String customFieldLabel2;
    @XmlElement(required = true)
    protected String customFieldLabel3;
    @XmlElement(required = true)
    protected String customFieldLabel4;
    @XmlElement(required = true)
    protected String customFieldLabel5;
    @XmlElement(required = true)
    protected String customFieldvalue1;
    @XmlElement(required = true)
    protected String customFieldvalue2;
    @XmlElement(required = true)
    protected String customFieldvalue3;
    @XmlElement(required = true)
    protected String customFieldvalue4;
    @XmlElement(required = true)
    protected String customFieldvalue5;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
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
     * Gets the value of the permission1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermission1() {
        return permission1;
    }

    /**
     * Sets the value of the permission1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermission1(String value) {
        this.permission1 = value;
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
     * Gets the value of the permission2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermission2() {
        return permission2;
    }

    /**
     * Sets the value of the permission2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermission2(String value) {
        this.permission2 = value;
    }

    /**
     * Gets the value of the fundraiserPages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserPages() {
        return fundraiserPages;
    }

    /**
     * Sets the value of the fundraiserPages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserPages(String value) {
        this.fundraiserPages = value;
    }

    /**
     * Gets the value of the fundraiserFunds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserFunds() {
        return fundraiserFunds;
    }

    /**
     * Sets the value of the fundraiserFunds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserFunds(String value) {
        this.fundraiserFunds = value;
    }

    /**
     * Gets the value of the customFieldLabel1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel1() {
        return customFieldLabel1;
    }

    /**
     * Sets the value of the customFieldLabel1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel1(String value) {
        this.customFieldLabel1 = value;
    }

    /**
     * Gets the value of the customFieldLabel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel2() {
        return customFieldLabel2;
    }

    /**
     * Sets the value of the customFieldLabel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel2(String value) {
        this.customFieldLabel2 = value;
    }

    /**
     * Gets the value of the customFieldLabel3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel3() {
        return customFieldLabel3;
    }

    /**
     * Sets the value of the customFieldLabel3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel3(String value) {
        this.customFieldLabel3 = value;
    }

    /**
     * Gets the value of the customFieldLabel4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel4() {
        return customFieldLabel4;
    }

    /**
     * Sets the value of the customFieldLabel4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel4(String value) {
        this.customFieldLabel4 = value;
    }

    /**
     * Gets the value of the customFieldLabel5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel5() {
        return customFieldLabel5;
    }

    /**
     * Sets the value of the customFieldLabel5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel5(String value) {
        this.customFieldLabel5 = value;
    }

    /**
     * Gets the value of the customFieldvalue1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue1() {
        return customFieldvalue1;
    }

    /**
     * Sets the value of the customFieldvalue1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue1(String value) {
        this.customFieldvalue1 = value;
    }

    /**
     * Gets the value of the customFieldvalue2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue2() {
        return customFieldvalue2;
    }

    /**
     * Sets the value of the customFieldvalue2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue2(String value) {
        this.customFieldvalue2 = value;
    }

    /**
     * Gets the value of the customFieldvalue3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue3() {
        return customFieldvalue3;
    }

    /**
     * Sets the value of the customFieldvalue3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue3(String value) {
        this.customFieldvalue3 = value;
    }

    /**
     * Gets the value of the customFieldvalue4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue4() {
        return customFieldvalue4;
    }

    /**
     * Sets the value of the customFieldvalue4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue4(String value) {
        this.customFieldvalue4 = value;
    }

    /**
     * Gets the value of the customFieldvalue5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue5() {
        return customFieldvalue5;
    }

    /**
     * Sets the value of the customFieldvalue5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue5(String value) {
        this.customFieldvalue5 = value;
    }

}
