
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServicePageDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServicePageDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bodyContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="pageStatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charitableActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fundraiserActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fundraiserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageWidgets" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fundraiserGroupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="expiredDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServicePageDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "id",
    "title",
    "bodyContent",
    "message",
    "createdDatetime",
    "pageStatusCode",
    "pageTypeCode",
    "charityId",
    "charitableActivityId",
    "fundraiserActivityId",
    "fundraiserId",
    "eventId",
    "url",
    "pageWidgets",
    "fundraiserGroupId",
    "expiredDateTime"
})
public class ServicePageDetails {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String bodyContent;
    @XmlElement(required = true)
    protected String message;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDatetime;
    @XmlElement(required = true)
    protected String pageStatusCode;
    @XmlElement(required = true)
    protected String pageTypeCode;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charitableActivityId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserActivityId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long eventId;
    @XmlElement(required = true)
    protected String url;
    @XmlElement(nillable = true)
    protected List<String> pageWidgets;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserGroupId;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiredDateTime;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the bodyContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBodyContent() {
        return bodyContent;
    }

    /**
     * Sets the value of the bodyContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBodyContent(String value) {
        this.bodyContent = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the createdDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * Sets the value of the createdDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDatetime(XMLGregorianCalendar value) {
        this.createdDatetime = value;
    }

    /**
     * Gets the value of the pageStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageStatusCode() {
        return pageStatusCode;
    }

    /**
     * Sets the value of the pageStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageStatusCode(String value) {
        this.pageStatusCode = value;
    }

    /**
     * Gets the value of the pageTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageTypeCode() {
        return pageTypeCode;
    }

    /**
     * Sets the value of the pageTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageTypeCode(String value) {
        this.pageTypeCode = value;
    }

    /**
     * Gets the value of the charityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCharityId(Long value) {
        this.charityId = value;
    }

    /**
     * Gets the value of the charitableActivityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCharitableActivityId() {
        return charitableActivityId;
    }

    /**
     * Sets the value of the charitableActivityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCharitableActivityId(Long value) {
        this.charitableActivityId = value;
    }

    /**
     * Gets the value of the fundraiserActivityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserActivityId() {
        return fundraiserActivityId;
    }

    /**
     * Sets the value of the fundraiserActivityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserActivityId(Long value) {
        this.fundraiserActivityId = value;
    }

    /**
     * Gets the value of the fundraiserId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserId() {
        return fundraiserId;
    }

    /**
     * Sets the value of the fundraiserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserId(Long value) {
        this.fundraiserId = value;
    }

    /**
     * Gets the value of the eventId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEventId(Long value) {
        this.eventId = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the pageWidgets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pageWidgets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPageWidgets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPageWidgets() {
        if (pageWidgets == null) {
            pageWidgets = new ArrayList<String>();
        }
        return this.pageWidgets;
    }

    /**
     * Gets the value of the fundraiserGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserGroupId() {
        return fundraiserGroupId;
    }

    /**
     * Sets the value of the fundraiserGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserGroupId(Long value) {
        this.fundraiserGroupId = value;
    }

    /**
     * Gets the value of the expiredDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiredDateTime() {
        return expiredDateTime;
    }

    /**
     * Sets the value of the expiredDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiredDateTime(XMLGregorianCalendar value) {
        this.expiredDateTime = value;
    }

}
