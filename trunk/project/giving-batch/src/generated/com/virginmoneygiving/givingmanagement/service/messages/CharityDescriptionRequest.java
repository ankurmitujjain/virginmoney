
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
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="adminMailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adminforename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "adminMailAddress",
    "charityName",
    "adminforename",
    "charityDescription"
})
@XmlRootElement(name = "CharityDescriptionRequest")
public class CharityDescriptionRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    protected long charityId;
    @XmlElement(required = true)
    protected String adminMailAddress;
    @XmlElement(required = true)
    protected String charityName;
    @XmlElement(required = true)
    protected String adminforename;
    @XmlElement(required = true)
    protected String charityDescription;

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
     * Gets the value of the adminMailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminMailAddress() {
        return adminMailAddress;
    }

    /**
     * Sets the value of the adminMailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminMailAddress(String value) {
        this.adminMailAddress = value;
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
     * Gets the value of the adminforename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminforename() {
        return adminforename;
    }

    /**
     * Sets the value of the adminforename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminforename(String value) {
        this.adminforename = value;
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

}
