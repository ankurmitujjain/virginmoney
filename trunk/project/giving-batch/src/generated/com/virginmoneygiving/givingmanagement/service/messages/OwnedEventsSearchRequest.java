
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
 *         &lt;element name="Header" type="{http://www.virginmoneygiving.com/type/header/}MessageHeader"/>
 *         &lt;element name="eventStatusList" type="{http://www.virginmoneygiving.com/type/giving-management/event/}ServiceEventStatus" maxOccurs="unbounded"/>
 *         &lt;element name="sortCriteria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="operationalUserInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="latestEventInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "eventStatusList",
    "sortCriteria",
    "charityId",
    "userId",
    "operationalUserInd",
    "latestEventInd"
})
@XmlRootElement(name = "OwnedEventsSearchRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class OwnedEventsSearchRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected List<ServiceEventStatus> eventStatusList;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String sortCriteria;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected long charityId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true, type = Long.class, nillable = true)
    protected Long userId;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String operationalUserInd;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String latestEventInd;

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
     * Gets the value of the eventStatusList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventStatusList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventStatusList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceEventStatus }
     * 
     * 
     */
    public List<ServiceEventStatus> getEventStatusList() {
        if (eventStatusList == null) {
            eventStatusList = new ArrayList<ServiceEventStatus>();
        }
        return this.eventStatusList;
    }

    /**
     * Gets the value of the sortCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortCriteria() {
        return sortCriteria;
    }

    /**
     * Sets the value of the sortCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortCriteria(String value) {
        this.sortCriteria = value;
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
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUserId(Long value) {
        this.userId = value;
    }

    /**
     * Gets the value of the operationalUserInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationalUserInd() {
        return operationalUserInd;
    }

    /**
     * Sets the value of the operationalUserInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationalUserInd(String value) {
        this.operationalUserInd = value;
    }

    /**
     * Gets the value of the latestEventInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestEventInd() {
        return latestEventInd;
    }

    /**
     * Sets the value of the latestEventInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestEventInd(String value) {
        this.latestEventInd = value;
    }

}
