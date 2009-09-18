
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="loginEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="person" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServicePersonDetails"/>
 *         &lt;element name="accountLocked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="accountLockedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountLockedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="accountLockedReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="basicReponse" type="{http://www.virginmoneygiving.com/type/giving-management/common/}BasicResponse"/>
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
    "id",
    "loginEmailAddress",
    "person",
    "accountLocked",
    "accountLockedBy",
    "accountLockedDate",
    "accountLockedReason",
    "basicReponse"
})
@XmlRootElement(name = "fetchUserDetailsByIdResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
public class FetchUserDetailsByIdResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
    protected long id;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String loginEmailAddress;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected ServicePersonDetails person;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
    protected boolean accountLocked;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String accountLockedBy;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar accountLockedDate;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String accountLockedReason;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the loginEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginEmailAddress() {
        return loginEmailAddress;
    }

    /**
     * Sets the value of the loginEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginEmailAddress(String value) {
        this.loginEmailAddress = value;
    }

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePersonDetails }
     *     
     */
    public ServicePersonDetails getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePersonDetails }
     *     
     */
    public void setPerson(ServicePersonDetails value) {
        this.person = value;
    }

    /**
     * Gets the value of the accountLocked property.
     * 
     */
    public boolean isAccountLocked() {
        return accountLocked;
    }

    /**
     * Sets the value of the accountLocked property.
     * 
     */
    public void setAccountLocked(boolean value) {
        this.accountLocked = value;
    }

    /**
     * Gets the value of the accountLockedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountLockedBy() {
        return accountLockedBy;
    }

    /**
     * Sets the value of the accountLockedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountLockedBy(String value) {
        this.accountLockedBy = value;
    }

    /**
     * Gets the value of the accountLockedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAccountLockedDate() {
        return accountLockedDate;
    }

    /**
     * Sets the value of the accountLockedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAccountLockedDate(XMLGregorianCalendar value) {
        this.accountLockedDate = value;
    }

    /**
     * Gets the value of the accountLockedReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountLockedReason() {
        return accountLockedReason;
    }

    /**
     * Sets the value of the accountLockedReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountLockedReason(String value) {
        this.accountLockedReason = value;
    }

    /**
     * Gets the value of the basicReponse property.
     * 
     * @return
     *     possible object is
     *     {@link BasicResponse }
     *     
     */
    public BasicResponse getBasicReponse() {
        return basicReponse;
    }

    /**
     * Sets the value of the basicReponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicResponse }
     *     
     */
    public void setBasicReponse(BasicResponse value) {
        this.basicReponse = value;
    }

}
