
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceVMGUserDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceVMGUserDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loginEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="forename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dayOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monthOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="yearOfBirth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="registeredCharityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fundraiserUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="eventIds" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded"/>
 *         &lt;element name="roleAndPermissions" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceVMGUserDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "userId",
    "username",
    "loginEmailAddress",
    "personId",
    "title",
    "forename",
    "surname",
    "dayOfBirth",
    "monthOfBirth",
    "yearOfBirth",
    "charityId",
    "charityName",
    "registeredCharityNumber",
    "charityStatus",
    "fundraiserId",
    "fundraiserUrl",
    "donorId",
    "eventIds",
    "roleAndPermissions"
})
public class ServiceVMGUserDetails {

    protected long userId;
    @XmlElement(required = true, nillable = true)
    protected String username;
    @XmlElement(required = true, nillable = true)
    protected String loginEmailAddress;
    protected long personId;
    @XmlElement(required = true, nillable = true)
    protected String title;
    @XmlElement(required = true)
    protected String forename;
    @XmlElement(required = true)
    protected String surname;
    protected int dayOfBirth;
    protected int monthOfBirth;
    protected int yearOfBirth;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityId;
    @XmlElement(required = true, nillable = true)
    protected String charityName;
    @XmlElement(required = true, nillable = true)
    protected String registeredCharityNumber;
    @XmlElement(required = true, nillable = true)
    protected String charityStatus;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserId;
    @XmlElement(required = true, nillable = true)
    protected String fundraiserUrl;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long donorId;
    @XmlElement(required = true, nillable = true)
    protected List<Long> eventIds;
    @XmlElement(required = true)
    protected List<String> roleAndPermissions;

    /**
     * Gets the value of the userId property.
     * 
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(long value) {
        this.userId = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
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
     * Gets the value of the personId property.
     * 
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Sets the value of the personId property.
     * 
     */
    public void setPersonId(long value) {
        this.personId = value;
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
     * Gets the value of the forename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets the value of the forename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForename(String value) {
        this.forename = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the dayOfBirth property.
     * 
     */
    public int getDayOfBirth() {
        return dayOfBirth;
    }

    /**
     * Sets the value of the dayOfBirth property.
     * 
     */
    public void setDayOfBirth(int value) {
        this.dayOfBirth = value;
    }

    /**
     * Gets the value of the monthOfBirth property.
     * 
     */
    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    /**
     * Sets the value of the monthOfBirth property.
     * 
     */
    public void setMonthOfBirth(int value) {
        this.monthOfBirth = value;
    }

    /**
     * Gets the value of the yearOfBirth property.
     * 
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Sets the value of the yearOfBirth property.
     * 
     */
    public void setYearOfBirth(int value) {
        this.yearOfBirth = value;
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
     * Gets the value of the registeredCharityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegisteredCharityNumber() {
        return registeredCharityNumber;
    }

    /**
     * Sets the value of the registeredCharityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegisteredCharityNumber(String value) {
        this.registeredCharityNumber = value;
    }

    /**
     * Gets the value of the charityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityStatus() {
        return charityStatus;
    }

    /**
     * Sets the value of the charityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityStatus(String value) {
        this.charityStatus = value;
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
     * Gets the value of the fundraiserUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserUrl() {
        return fundraiserUrl;
    }

    /**
     * Sets the value of the fundraiserUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserUrl(String value) {
        this.fundraiserUrl = value;
    }

    /**
     * Gets the value of the donorId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDonorId() {
        return donorId;
    }

    /**
     * Sets the value of the donorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDonorId(Long value) {
        this.donorId = value;
    }

    /**
     * Gets the value of the eventIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getEventIds() {
        if (eventIds == null) {
            eventIds = new ArrayList<Long>();
        }
        return this.eventIds;
    }

    /**
     * Gets the value of the roleAndPermissions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roleAndPermissions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoleAndPermissions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRoleAndPermissions() {
        if (roleAndPermissions == null) {
            roleAndPermissions = new ArrayList<String>();
        }
        return this.roleAndPermissions;
    }

}
