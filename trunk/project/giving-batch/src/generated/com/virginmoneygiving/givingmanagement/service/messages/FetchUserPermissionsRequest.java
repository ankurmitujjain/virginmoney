
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
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dobDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dobMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dobYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "username",
    "dobDay",
    "dobMonth",
    "dobYear"
})
@XmlRootElement(name = "fetchUserPermissionsRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/")
public class FetchUserPermissionsRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true)
    protected String username;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true, type = Integer.class, nillable = true)
    protected Integer dobDay;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true, type = Integer.class, nillable = true)
    protected Integer dobMonth;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", required = true, type = Integer.class, nillable = true)
    protected Integer dobYear;

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
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the dobDay property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDobDay() {
        return dobDay;
    }

    /**
     * Sets the value of the dobDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDobDay(Integer value) {
        this.dobDay = value;
    }

    /**
     * Gets the value of the dobMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDobMonth() {
        return dobMonth;
    }

    /**
     * Sets the value of the dobMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDobMonth(Integer value) {
        this.dobMonth = value;
    }

    /**
     * Gets the value of the dobYear property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDobYear() {
        return dobYear;
    }

    /**
     * Sets the value of the dobYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDobYear(Integer value) {
        this.dobYear = value;
    }

}
