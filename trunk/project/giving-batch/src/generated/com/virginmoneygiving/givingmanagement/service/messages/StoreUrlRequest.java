
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
 *         &lt;element name="fundraiserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dayOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monthOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="yearOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="urlType" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "fundraiserId",
    "emailAddress",
    "dayOfBirth",
    "monthOfBirth",
    "yearOfBirth",
    "firstname",
    "lastname",
    "url",
    "urlType"
})
@XmlRootElement(name = "storeUrlRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
public class StoreUrlRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
    protected long fundraiserId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected String emailAddress;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
    protected int dayOfBirth;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
    protected int monthOfBirth;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
    protected int yearOfBirth;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected String firstname;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected String lastname;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected String url;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected String urlType;

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
     * Gets the value of the fundraiserId property.
     * 
     */
    public long getFundraiserId() {
        return fundraiserId;
    }

    /**
     * Sets the value of the fundraiserId property.
     * 
     */
    public void setFundraiserId(long value) {
        this.fundraiserId = value;
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

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Gets the value of the lastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the value of the lastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastname(String value) {
        this.lastname = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the urlType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlType() {
        return urlType;
    }

    /**
     * Sets the value of the urlType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlType(String value) {
        this.urlType = value;
    }

}
