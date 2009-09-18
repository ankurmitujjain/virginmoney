
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
 *         &lt;element name="eventHomePageDetails" type="{http://www.virginmoneygiving.com/type/giving-management/event/}EventHomePageDetails" minOccurs="0"/>
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
    "eventHomePageDetails"
})
@XmlRootElement(name = "fetchEventHomePageDetailsResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class FetchEventHomePageDetailsResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected EventHomePageDetails eventHomePageDetails;

    /**
     * Gets the value of the eventHomePageDetails property.
     * 
     * @return
     *     possible object is
     *     {@link EventHomePageDetails }
     *     
     */
    public EventHomePageDetails getEventHomePageDetails() {
        return eventHomePageDetails;
    }

    /**
     * Sets the value of the eventHomePageDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventHomePageDetails }
     *     
     */
    public void setEventHomePageDetails(EventHomePageDetails value) {
        this.eventHomePageDetails = value;
    }

}
