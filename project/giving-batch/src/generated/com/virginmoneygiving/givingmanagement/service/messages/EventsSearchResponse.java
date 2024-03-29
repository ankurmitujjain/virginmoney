
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
    "eventList"
})
@XmlRootElement(name = "EventsSearchResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class EventsSearchResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected List<WebServiceEvent> eventList;

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

}
