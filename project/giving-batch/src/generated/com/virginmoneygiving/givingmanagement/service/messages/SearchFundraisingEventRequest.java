
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
 *         &lt;element name="searchFundraisingEventParameter" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceSearchFundraisingEventParameter"/>
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
    "searchFundraisingEventParameter"
})
@XmlRootElement(name = "searchFundraisingEventRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
public class SearchFundraisingEventRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected ServiceSearchFundraisingEventParameter searchFundraisingEventParameter;

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
     * Gets the value of the searchFundraisingEventParameter property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceSearchFundraisingEventParameter }
     *     
     */
    public ServiceSearchFundraisingEventParameter getSearchFundraisingEventParameter() {
        return searchFundraisingEventParameter;
    }

    /**
     * Sets the value of the searchFundraisingEventParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceSearchFundraisingEventParameter }
     *     
     */
    public void setSearchFundraisingEventParameter(ServiceSearchFundraisingEventParameter value) {
        this.searchFundraisingEventParameter = value;
    }

}
