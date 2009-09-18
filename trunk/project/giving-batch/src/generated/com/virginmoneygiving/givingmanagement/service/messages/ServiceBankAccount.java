
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceBankAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceBankAccount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="accountDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="address" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="accountType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="defaultAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="buildingSocietyRollNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updatedUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="updatedDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceBankAccount", propOrder = {
    "id",
    "charityId",
    "accountDescription",
    "accountName",
    "accountNumber",
    "sortCode",
    "bankName",
    "address",
    "accountType",
    "defaultAccount",
    "accountStatus",
    "buildingSocietyRollNumber",
    "createdUser",
    "updatedUser",
    "createdDateTime",
    "updatedDateTime"
})
public class ServiceBankAccount {

    protected long id;
    protected long charityId;
    @XmlElement(required = true)
    protected String accountDescription;
    @XmlElement(required = true)
    protected String accountName;
    @XmlElement(required = true)
    protected String accountNumber;
    @XmlElement(required = true)
    protected String sortCode;
    @XmlElement(required = true)
    protected String bankName;
    @XmlElement(required = true)
    protected ServiceAddress address;
    @XmlElement(required = true)
    protected String accountType;
    @XmlElement(required = true)
    protected String defaultAccount;
    @XmlElement(required = true)
    protected String accountStatus;
    @XmlElement(required = true)
    protected String buildingSocietyRollNumber;
    @XmlElement(required = true)
    protected String createdUser;
    @XmlElement(required = true)
    protected String updatedUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDateTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedDateTime;

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
     * Gets the value of the accountDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountDescription() {
        return accountDescription;
    }

    /**
     * Sets the value of the accountDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountDescription(String value) {
        this.accountDescription = value;
    }

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the sortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortCode() {
        return sortCode;
    }

    /**
     * Sets the value of the sortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortCode(String value) {
        this.sortCode = value;
    }

    /**
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankName(String value) {
        this.bankName = value;
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
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountType(String value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the defaultAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultAccount() {
        return defaultAccount;
    }

    /**
     * Sets the value of the defaultAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultAccount(String value) {
        this.defaultAccount = value;
    }

    /**
     * Gets the value of the accountStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the value of the accountStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountStatus(String value) {
        this.accountStatus = value;
    }

    /**
     * Gets the value of the buildingSocietyRollNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingSocietyRollNumber() {
        return buildingSocietyRollNumber;
    }

    /**
     * Sets the value of the buildingSocietyRollNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingSocietyRollNumber(String value) {
        this.buildingSocietyRollNumber = value;
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

}
