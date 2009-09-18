
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
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dayOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monthOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="yearOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "emailAddress",
    "dayOfBirth",
    "monthOfBirth",
    "yearOfBirth"
})
@XmlRootElement(name = "fetchVMGUserDetailsForExternalUserRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/")
public class FetchVMGUserDetailsForExternalUserRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true)
    protected String emailAddress;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/")
    protected int dayOfBirth;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/")
    protected int monthOfBirth;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/")
    protected int yearOfBirth;

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
     * Gets the value of the dayOfBirth property.
     * 
     */
    public int getDayOfBirth() {
        return dayOfBirth;
    }

    /**
     * Sets the value of the dayOfBirth property.
     * 
     */
    public void setDayOfBirth(int value) {
        this.dayOfBirth = value;
    }

    /**
     * Gets the value of the monthOfBirth property.
     * 
     */
    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    /**
     * Sets the value of the monthOfBirth property.
     * 
     */
    public void setMonthOfBirth(int value) {
        this.monthOfBirth = value;
    }

    /**
     * Gets the value of the yearOfBirth property.
     * 
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Sets the value of the yearOfBirth property.
     * 
     */
    public void setYearOfBirth(int value) {
        this.yearOfBirth = value;
    }

}
