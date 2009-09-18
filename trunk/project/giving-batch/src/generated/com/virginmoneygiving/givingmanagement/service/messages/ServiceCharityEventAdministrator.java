
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceCharityEventAdministrator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharityEventAdministrator">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityEventAdminId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityAdmin" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}CharityAdminDetails"/>
 *         &lt;element name="mainInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharityEventAdministrator", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", propOrder = {
    "charityEventAdminId",
    "charityAdmin",
    "mainInd",
    "startDatetime",
    "endDatetime"
})
public class ServiceCharityEventAdministrator {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityEventAdminId;
    @XmlElement(required = true, nillable = true)
    protected CharityAdminDetails charityAdmin;
    @XmlElement(required = true)
    protected String mainInd;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDatetime;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDatetime;

    /**
     * Gets the value of the charityEventAdminId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCharityEventAdminId() {
        return charityEventAdminId;
    }

    /**
     * Sets the value of the charityEventAdminId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCharityEventAdminId(Long value) {
        this.charityEventAdminId = value;
    }

    /**
     * Gets the value of the charityAdmin property.
     * 
     * @return
     *     possible object is
     *     {@link CharityAdminDetails }
     *     
     */
    public CharityAdminDetails getCharityAdmin() {
        return charityAdmin;
    }

    /**
     * Sets the value of the charityAdmin property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharityAdminDetails }
     *     
     */
    public void setCharityAdmin(CharityAdminDetails value) {
        this.charityAdmin = value;
    }

    /**
     * Gets the value of the mainInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainInd() {
        return mainInd;
    }

    /**
     * Sets the value of the mainInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainInd(String value) {
        this.mainInd = value;
    }

    /**
     * Gets the value of the startDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDatetime() {
        return startDatetime;
    }

    /**
     * Sets the value of the startDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDatetime(XMLGregorianCalendar value) {
        this.startDatetime = value;
    }

    /**
     * Gets the value of the endDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDatetime() {
        return endDatetime;
    }

    /**
     * Sets the value of the endDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDatetime(XMLGregorianCalendar value) {
        this.endDatetime = value;
    }

}
