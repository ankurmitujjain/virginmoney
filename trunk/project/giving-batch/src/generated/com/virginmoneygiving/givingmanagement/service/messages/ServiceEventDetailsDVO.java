
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceEventDetailsDVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceEventDetailsDVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="organiser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraisers" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventCopy1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventCopy2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventCopy3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventCopy4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventCopy5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceEventDetailsDVO", propOrder = {
    "id",
    "name",
    "location",
    "type",
    "eventDate",
    "organiser",
    "fundraisers",
    "eventCopy1",
    "eventCopy2",
    "eventCopy3",
    "eventCopy4",
    "eventCopy5",
    "customFieldLabel1",
    "customFieldLabel2",
    "customFieldLabel3",
    "customFieldLabel4",
    "customFieldLabel5",
    "customFieldvalue1",
    "customFieldvalue2",
    "customFieldvalue3",
    "customFieldvalue4",
    "customFieldvalue5"
})
public class ServiceEventDetailsDVO {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String location;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String eventDate;
    @XmlElement(required = true)
    protected String organiser;
    @XmlElement(required = true)
    protected String fundraisers;
    @XmlElement(required = true)
    protected String eventCopy1;
    @XmlElement(required = true)
    protected String eventCopy2;
    @XmlElement(required = true)
    protected String eventCopy3;
    @XmlElement(required = true)
    protected String eventCopy4;
    @XmlElement(required = true)
    protected String eventCopy5;
    @XmlElement(required = true)
    protected String customFieldLabel1;
    @XmlElement(required = true)
    protected String customFieldLabel2;
    @XmlElement(required = true)
    protected String customFieldLabel3;
    @XmlElement(required = true)
    protected String customFieldLabel4;
    @XmlElement(required = true)
    protected String customFieldLabel5;
    @XmlElement(required = true)
    protected String customFieldvalue1;
    @XmlElement(required = true)
    protected String customFieldvalue2;
    @XmlElement(required = true)
    protected String customFieldvalue3;
    @XmlElement(required = true)
    protected String customFieldvalue4;
    @XmlElement(required = true)
    protected String customFieldvalue5;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the eventDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * Sets the value of the eventDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventDate(String value) {
        this.eventDate = value;
    }

    /**
     * Gets the value of the organiser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganiser() {
        return organiser;
    }

    /**
     * Sets the value of the organiser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganiser(String value) {
        this.organiser = value;
    }

    /**
     * Gets the value of the fundraisers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraisers() {
        return fundraisers;
    }

    /**
     * Sets the value of the fundraisers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraisers(String value) {
        this.fundraisers = value;
    }

    /**
     * Gets the value of the eventCopy1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCopy1() {
        return eventCopy1;
    }

    /**
     * Sets the value of the eventCopy1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCopy1(String value) {
        this.eventCopy1 = value;
    }

    /**
     * Gets the value of the eventCopy2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCopy2() {
        return eventCopy2;
    }

    /**
     * Sets the value of the eventCopy2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCopy2(String value) {
        this.eventCopy2 = value;
    }

    /**
     * Gets the value of the eventCopy3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCopy3() {
        return eventCopy3;
    }

    /**
     * Sets the value of the eventCopy3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCopy3(String value) {
        this.eventCopy3 = value;
    }

    /**
     * Gets the value of the eventCopy4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCopy4() {
        return eventCopy4;
    }

    /**
     * Sets the value of the eventCopy4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCopy4(String value) {
        this.eventCopy4 = value;
    }

    /**
     * Gets the value of the eventCopy5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCopy5() {
        return eventCopy5;
    }

    /**
     * Sets the value of the eventCopy5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCopy5(String value) {
        this.eventCopy5 = value;
    }

    /**
     * Gets the value of the customFieldLabel1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel1() {
        return customFieldLabel1;
    }

    /**
     * Sets the value of the customFieldLabel1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel1(String value) {
        this.customFieldLabel1 = value;
    }

    /**
     * Gets the value of the customFieldLabel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel2() {
        return customFieldLabel2;
    }

    /**
     * Sets the value of the customFieldLabel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel2(String value) {
        this.customFieldLabel2 = value;
    }

    /**
     * Gets the value of the customFieldLabel3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel3() {
        return customFieldLabel3;
    }

    /**
     * Sets the value of the customFieldLabel3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel3(String value) {
        this.customFieldLabel3 = value;
    }

    /**
     * Gets the value of the customFieldLabel4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel4() {
        return customFieldLabel4;
    }

    /**
     * Sets the value of the customFieldLabel4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel4(String value) {
        this.customFieldLabel4 = value;
    }

    /**
     * Gets the value of the customFieldLabel5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel5() {
        return customFieldLabel5;
    }

    /**
     * Sets the value of the customFieldLabel5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel5(String value) {
        this.customFieldLabel5 = value;
    }

    /**
     * Gets the value of the customFieldvalue1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue1() {
        return customFieldvalue1;
    }

    /**
     * Sets the value of the customFieldvalue1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue1(String value) {
        this.customFieldvalue1 = value;
    }

    /**
     * Gets the value of the customFieldvalue2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue2() {
        return customFieldvalue2;
    }

    /**
     * Sets the value of the customFieldvalue2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue2(String value) {
        this.customFieldvalue2 = value;
    }

    /**
     * Gets the value of the customFieldvalue3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue3() {
        return customFieldvalue3;
    }

    /**
     * Sets the value of the customFieldvalue3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue3(String value) {
        this.customFieldvalue3 = value;
    }

    /**
     * Gets the value of the customFieldvalue4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue4() {
        return customFieldvalue4;
    }

    /**
     * Sets the value of the customFieldvalue4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue4(String value) {
        this.customFieldvalue4 = value;
    }

    /**
     * Gets the value of the customFieldvalue5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue5() {
        return customFieldvalue5;
    }

    /**
     * Sets the value of the customFieldvalue5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue5(String value) {
        this.customFieldvalue5 = value;
    }

}
