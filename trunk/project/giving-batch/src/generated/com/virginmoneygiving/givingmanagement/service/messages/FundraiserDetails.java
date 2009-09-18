
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FundraiserDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FundraiserDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.virginmoneygiving.com/type/header/}MessageHeader"/>
 *         &lt;element name="foreName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imageUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="homePageLink" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FundraiserDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", propOrder = {
    "header",
    "foreName",
    "imageUrl",
    "homePageLink"
})
public class FundraiserDetails {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(required = true)
    protected String foreName;
    @XmlElement(required = true)
    protected String imageUrl;
    @XmlElement(required = true)
    protected String homePageLink;

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
     * Gets the value of the foreName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForeName() {
        return foreName;
    }

    /**
     * Sets the value of the foreName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForeName(String value) {
        this.foreName = value;
    }

    /**
     * Gets the value of the imageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the value of the imageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageUrl(String value) {
        this.imageUrl = value;
    }

    /**
     * Gets the value of the homePageLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomePageLink() {
        return homePageLink;
    }

    /**
     * Sets the value of the homePageLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomePageLink(String value) {
        this.homePageLink = value;
    }

}
