
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
 *         &lt;element name="fundraiserUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="includeInactivePages" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="includeNonOwnerTeamActivities" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "fundraiserUrl",
    "includeInactivePages",
    "includeNonOwnerTeamActivities"
})
@XmlRootElement(name = "fetchFundraiserDetailsRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
public class FetchFundraiserDetailsRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true, type = Long.class, nillable = true)
    protected Long fundraiserId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true, nillable = true)
    protected String fundraiserUrl;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true, type = Boolean.class, nillable = true)
    protected Boolean includeInactivePages;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true, type = Boolean.class, nillable = true)
    protected Boolean includeNonOwnerTeamActivities;

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
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserId() {
        return fundraiserId;
    }

    /**
     * Sets the value of the fundraiserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserId(Long value) {
        this.fundraiserId = value;
    }

    /**
     * Gets the value of the fundraiserUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserUrl() {
        return fundraiserUrl;
    }

    /**
     * Sets the value of the fundraiserUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserUrl(String value) {
        this.fundraiserUrl = value;
    }

    /**
     * Gets the value of the includeInactivePages property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeInactivePages() {
        return includeInactivePages;
    }

    /**
     * Sets the value of the includeInactivePages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeInactivePages(Boolean value) {
        this.includeInactivePages = value;
    }

    /**
     * Gets the value of the includeNonOwnerTeamActivities property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeNonOwnerTeamActivities() {
        return includeNonOwnerTeamActivities;
    }

    /**
     * Sets the value of the includeNonOwnerTeamActivities property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeNonOwnerTeamActivities(Boolean value) {
        this.includeNonOwnerTeamActivities = value;
    }

}
