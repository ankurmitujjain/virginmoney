
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="eventName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="feeInstructions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alternateFeeInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventFeeList" type="{http://www.virginmoneygiving.com/type/giving-management/event/}ServiceEventFeeDetails" maxOccurs="unbounded" minOccurs="0"/>
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
    "eventId",
    "eventName",
    "charityId",
    "charityName",
    "feeInstructions",
    "alternateFeeInd",
    "eventFeeList"
})
@XmlRootElement(name = "EventFeeListResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class EventFeeListResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected long eventId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String eventName;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected long charityId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String charityName;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String feeInstructions;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String alternateFeeInd;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected List<ServiceEventFeeDetails> eventFeeList;

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
     * Gets the value of the eventName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the value of the eventName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventName(String value) {
        this.eventName = value;
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
     * Gets the value of the charityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the value of the charityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityName(String value) {
        this.charityName = value;
    }

    /**
     * Gets the value of the feeInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeeInstructions() {
        return feeInstructions;
    }

    /**
     * Sets the value of the feeInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeeInstructions(String value) {
        this.feeInstructions = value;
    }

    /**
     * Gets the value of the alternateFeeInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateFeeInd() {
        return alternateFeeInd;
    }

    /**
     * Sets the value of the alternateFeeInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateFeeInd(String value) {
        this.alternateFeeInd = value;
    }

    /**
     * Gets the value of the eventFeeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventFeeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventFeeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceEventFeeDetails }
     * 
     * 
     */
    public List<ServiceEventFeeDetails> getEventFeeList() {
        if (eventFeeList == null) {
            eventFeeList = new ArrayList<ServiceEventFeeDetails>();
        }
        return this.eventFeeList;
    }

}
