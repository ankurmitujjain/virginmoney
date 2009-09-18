
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
 *         &lt;element name="eventList" type="{http://www.virginmoneygiving.com/type/giving-management/event/}WebServiceEvent" maxOccurs="unbounded"/>
 *         &lt;element name="eventLatestList" type="{http://www.virginmoneygiving.com/type/giving-management/event/}WebServiceEvent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cancallableEventsPresent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityLogo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "eventList",
    "eventLatestList",
    "cancallableEventsPresent",
    "charityLogo",
    "charityName"
})
@XmlRootElement(name = "OwnedEventsSearchResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class OwnedEventsSearchResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected List<WebServiceEvent> eventList;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected List<WebServiceEvent> eventLatestList;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String cancallableEventsPresent;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String charityLogo;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String charityName;

    /**
     * Gets the value of the eventList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WebServiceEvent }
     * 
     * 
     */
    public List<WebServiceEvent> getEventList() {
        if (eventList == null) {
            eventList = new ArrayList<WebServiceEvent>();
        }
        return this.eventList;
    }

    /**
     * Gets the value of the eventLatestList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventLatestList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventLatestList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WebServiceEvent }
     * 
     * 
     */
    public List<WebServiceEvent> getEventLatestList() {
        if (eventLatestList == null) {
            eventLatestList = new ArrayList<WebServiceEvent>();
        }
        return this.eventLatestList;
    }

    /**
     * Gets the value of the cancallableEventsPresent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancallableEventsPresent() {
        return cancallableEventsPresent;
    }

    /**
     * Sets the value of the cancallableEventsPresent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancallableEventsPresent(String value) {
        this.cancallableEventsPresent = value;
    }

    /**
     * Gets the value of the charityLogo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityLogo() {
        return charityLogo;
    }

    /**
     * Sets the value of the charityLogo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityLogo(String value) {
        this.charityLogo = value;
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

}
