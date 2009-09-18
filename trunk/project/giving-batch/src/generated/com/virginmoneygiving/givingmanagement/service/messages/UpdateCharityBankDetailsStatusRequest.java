
package com.virginmoneygiving.givingmanagement.service.messages;

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
 *         &lt;element name="bankAccountId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="defaultAccountIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankAccountStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "bankAccountId",
    "charityId",
    "defaultAccountIndicator",
    "bankAccountStatus",
    "userName"
})
@XmlRootElement(name = "updateCharityBankDetailsStatusRequest")
public class UpdateCharityBankDetailsStatusRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    protected long bankAccountId;
    protected long charityId;
    @XmlElement(required = true)
    protected String defaultAccountIndicator;
    @XmlElement(required = true)
    protected String bankAccountStatus;
    @XmlElement(required = true)
    protected String userName;

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
     * Gets the value of the bankAccountId property.
     * 
     */
    public long getBankAccountId() {
        return bankAccountId;
    }

    /**
     * Sets the value of the bankAccountId property.
     * 
     */
    public void setBankAccountId(long value) {
        this.bankAccountId = value;
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
     * Gets the value of the defaultAccountIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultAccountIndicator() {
        return defaultAccountIndicator;
    }

    /**
     * Sets the value of the defaultAccountIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultAccountIndicator(String value) {
        this.defaultAccountIndicator = value;
    }

    /**
     * Gets the value of the bankAccountStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccountStatus() {
        return bankAccountStatus;
    }

    /**
     * Sets the value of the bankAccountStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccountStatus(String value) {
        this.bankAccountStatus = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

}
