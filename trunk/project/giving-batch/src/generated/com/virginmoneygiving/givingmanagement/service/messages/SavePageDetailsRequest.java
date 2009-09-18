
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
 *         &lt;element name="pageDetails" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServicePageDetails"/>
 *         &lt;element name="fundraiserActivity" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceFundraisingReason"/>
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
    "pageDetails",
    "fundraiserActivity"
})
@XmlRootElement(name = "savePageDetailsRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
public class SavePageDetailsRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected ServicePageDetails pageDetails;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true, nillable = true)
    protected ServiceFundraisingReason fundraiserActivity;

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
     * Gets the value of the pageDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePageDetails }
     *     
     */
    public ServicePageDetails getPageDetails() {
        return pageDetails;
    }

    /**
     * Sets the value of the pageDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePageDetails }
     *     
     */
    public void setPageDetails(ServicePageDetails value) {
        this.pageDetails = value;
    }

    /**
     * Gets the value of the fundraiserActivity property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceFundraisingReason }
     *     
     */
    public ServiceFundraisingReason getFundraiserActivity() {
        return fundraiserActivity;
    }

    /**
     * Sets the value of the fundraiserActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceFundraisingReason }
     *     
     */
    public void setFundraiserActivity(ServiceFundraisingReason value) {
        this.fundraiserActivity = value;
    }

}
