
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
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="mode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "eventId",
    "charityId",
    "mode",
    "userId"
})
@XmlRootElement(name = "CancelEventRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class CancelEventRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected long eventId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected long charityId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String mode;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String userId;

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
     * Gets the value of the eventId property.
     * 
     */
    public long getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     */
    public void setEventId(long value) {
        this.eventId = value;
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
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMode(String value) {
        this.mode = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

}
