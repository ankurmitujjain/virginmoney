
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceCharityOffRegNote complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharityOffRegNote">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offRegNoteId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="createdUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="verifyNotes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updatedUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updatedDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="module" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceModule"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharityOffRegNote", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "offRegNoteId",
    "createdUser",
    "createdDateTime",
    "verifyNotes",
    "updatedUser",
    "updatedDateTime",
    "module"
})
public class ServiceCharityOffRegNote {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long offRegNoteId;
    @XmlElement(required = true, nillable = true)
    protected String createdUser;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDateTime;
    @XmlElement(required = true, nillable = true)
    protected String verifyNotes;
    @XmlElement(required = true, nillable = true)
    protected String updatedUser;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedDateTime;
    @XmlElement(required = true, nillable = true)
    protected ServiceModule module;

    /**
     * Gets the value of the offRegNoteId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOffRegNoteId() {
        return offRegNoteId;
    }

    /**
     * Sets the value of the offRegNoteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOffRegNoteId(Long value) {
        this.offRegNoteId = value;
    }

    /**
     * Gets the value of the createdUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * Sets the value of the createdUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedUser(String value) {
        this.createdUser = value;
    }

    /**
     * Gets the value of the createdDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets the value of the createdDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDateTime(XMLGregorianCalendar value) {
        this.createdDateTime = value;
    }

    /**
     * Gets the value of the verifyNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerifyNotes() {
        return verifyNotes;
    }

    /**
     * Sets the value of the verifyNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerifyNotes(String value) {
        this.verifyNotes = value;
    }

    /**
     * Gets the value of the updatedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * Sets the value of the updatedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedUser(String value) {
        this.updatedUser = value;
    }

    /**
     * Gets the value of the updatedDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedDateTime() {
        return updatedDateTime;
    }

    /**
     * Sets the value of the updatedDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedDateTime(XMLGregorianCalendar value) {
        this.updatedDateTime = value;
    }

    /**
     * Gets the value of the module property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceModule }
     *     
     */
    public ServiceModule getModule() {
        return module;
    }

    /**
     * Sets the value of the module property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceModule }
     *     
     */
    public void setModule(ServiceModule value) {
        this.module = value;
    }

}
