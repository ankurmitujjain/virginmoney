
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for EventProcessing complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventProcessing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.virginmoney.com/type/glis/header/2008/09}MessageHeader"/>
 *         &lt;element name="EventType" type="{http://www.virginmoney.com/type/glis/2009/01}EventProcessingTypes"/>
 *         &lt;element name="batchNumber" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="eventCategory" type="{http://www.virginmoney.com/type/glis/2009/01}EventCategoryType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventProcessing", propOrder = {
    "header",
    "eventType",
    "batchNumber",
    "eventCategory"
})
public class EventProcessing {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(name = "EventType", required = true)
    protected EventProcessingTypes eventType;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String batchNumber;
    protected EventCategoryType eventCategory;

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
     * Gets the value of the eventType property.
     * 
     * @return
     *     possible object is
     *     {@link EventProcessingTypes }
     *     
     */
    public EventProcessingTypes getEventType() {
        return eventType;
    }

    /**
     * Sets the value of the eventType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventProcessingTypes }
     *     
     */
    public void setEventType(EventProcessingTypes value) {
        this.eventType = value;
    }

    /**
     * Gets the value of the batchNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the value of the batchNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchNumber(String value) {
        this.batchNumber = value;
    }

    /**
     * Gets the value of the eventCategory property.
     * 
     * @return
     *     possible object is
     *     {@link EventCategoryType }
     *     
     */
    public EventCategoryType getEventCategory() {
        return eventCategory;
    }

    /**
     * Sets the value of the eventCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventCategoryType }
     *     
     */
    public void setEventCategory(EventCategoryType value) {
        this.eventCategory = value;
    }

}
