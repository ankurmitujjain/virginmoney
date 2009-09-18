
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
 * <p>Java class for ServiceCharitableActivity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharitableActivity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charitableActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="thankYouMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserGroupsInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="splitPercentage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="onlineEntryLimit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="onlineClosureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="eventCreatorInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vmgManageFeeInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alternateFeePayInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentInstructions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankAccountFundraisingId" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceBankAccount"/>
 *         &lt;element name="bankAccountFees" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceBankAccount"/>
 *         &lt;element name="eventFeeDetails" type="{http://www.virginmoneygiving.com/type/giving-management/event/}ServiceEventFeeDetails" maxOccurs="unbounded"/>
 *         &lt;element name="charityEventAdmin" type="{http://www.virginmoneygiving.com/type/giving-management/event/}ServiceCharityEventAdministrator" maxOccurs="unbounded"/>
 *         &lt;element name="allUsersInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharitableActivity", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", propOrder = {
    "charitableActivityId",
    "thankYouMessage",
    "fundraiserGroupsInd",
    "locationCode",
    "charityId",
    "charityName",
    "eventId",
    "splitPercentage",
    "onlineEntryLimit",
    "onlineClosureDate",
    "eventCreatorInd",
    "vmgManageFeeInd",
    "alternateFeePayInd",
    "paymentInstructions",
    "bankAccountFundraisingId",
    "bankAccountFees",
    "eventFeeDetails",
    "charityEventAdmin",
    "allUsersInd"
})
public class ServiceCharitableActivity {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charitableActivityId;
    @XmlElement(required = true, nillable = true)
    protected String thankYouMessage;
    @XmlElement(required = true, nillable = true)
    protected String fundraiserGroupsInd;
    @XmlElement(required = true)
    protected String locationCode;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityId;
    @XmlElement(required = true, nillable = true)
    protected String charityName;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long eventId;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer splitPercentage;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer onlineEntryLimit;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar onlineClosureDate;
    @XmlElement(required = true, nillable = true)
    protected String eventCreatorInd;
    @XmlElement(required = true, nillable = true)
    protected String vmgManageFeeInd;
    @XmlElement(required = true, nillable = true)
    protected String alternateFeePayInd;
    @XmlElement(required = true, nillable = true)
    protected String paymentInstructions;
    @XmlElement(required = true, nillable = true)
    protected ServiceBankAccount bankAccountFundraisingId;
    @XmlElement(required = true, nillable = true)
    protected ServiceBankAccount bankAccountFees;
    @XmlElement(required = true, nillable = true)
    protected List<ServiceEventFeeDetails> eventFeeDetails;
    @XmlElement(required = true, nillable = true)
    protected List<ServiceCharityEventAdministrator> charityEventAdmin;
    @XmlElement(required = true, nillable = true)
    protected String allUsersInd;

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
     * Gets the value of the thankYouMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThankYouMessage() {
        return thankYouMessage;
    }

    /**
     * Sets the value of the thankYouMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThankYouMessage(String value) {
        this.thankYouMessage = value;
    }

    /**
     * Gets the value of the fundraiserGroupsInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserGroupsInd() {
        return fundraiserGroupsInd;
    }

    /**
     * Sets the value of the fundraiserGroupsInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserGroupsInd(String value) {
        this.fundraiserGroupsInd = value;
    }

    /**
     * Gets the value of the locationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * Sets the value of the locationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationCode(String value) {
        this.locationCode = value;
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
     * Gets the value of the splitPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSplitPercentage() {
        return splitPercentage;
    }

    /**
     * Sets the value of the splitPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSplitPercentage(Integer value) {
        this.splitPercentage = value;
    }

    /**
     * Gets the value of the onlineEntryLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOnlineEntryLimit() {
        return onlineEntryLimit;
    }

    /**
     * Sets the value of the onlineEntryLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOnlineEntryLimit(Integer value) {
        this.onlineEntryLimit = value;
    }

    /**
     * Gets the value of the onlineClosureDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOnlineClosureDate() {
        return onlineClosureDate;
    }

    /**
     * Sets the value of the onlineClosureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOnlineClosureDate(XMLGregorianCalendar value) {
        this.onlineClosureDate = value;
    }

    /**
     * Gets the value of the eventCreatorInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCreatorInd() {
        return eventCreatorInd;
    }

    /**
     * Sets the value of the eventCreatorInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCreatorInd(String value) {
        this.eventCreatorInd = value;
    }

    /**
     * Gets the value of the vmgManageFeeInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVmgManageFeeInd() {
        return vmgManageFeeInd;
    }

    /**
     * Sets the value of the vmgManageFeeInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVmgManageFeeInd(String value) {
        this.vmgManageFeeInd = value;
    }

    /**
     * Gets the value of the alternateFeePayInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateFeePayInd() {
        return alternateFeePayInd;
    }

    /**
     * Sets the value of the alternateFeePayInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateFeePayInd(String value) {
        this.alternateFeePayInd = value;
    }

    /**
     * Gets the value of the paymentInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentInstructions() {
        return paymentInstructions;
    }

    /**
     * Sets the value of the paymentInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentInstructions(String value) {
        this.paymentInstructions = value;
    }

    /**
     * Gets the value of the bankAccountFundraisingId property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceBankAccount }
     *     
     */
    public ServiceBankAccount getBankAccountFundraisingId() {
        return bankAccountFundraisingId;
    }

    /**
     * Sets the value of the bankAccountFundraisingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceBankAccount }
     *     
     */
    public void setBankAccountFundraisingId(ServiceBankAccount value) {
        this.bankAccountFundraisingId = value;
    }

    /**
     * Gets the value of the bankAccountFees property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceBankAccount }
     *     
     */
    public ServiceBankAccount getBankAccountFees() {
        return bankAccountFees;
    }

    /**
     * Sets the value of the bankAccountFees property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceBankAccount }
     *     
     */
    public void setBankAccountFees(ServiceBankAccount value) {
        this.bankAccountFees = value;
    }

    /**
     * Gets the value of the eventFeeDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventFeeDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventFeeDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceEventFeeDetails }
     * 
     * 
     */
    public List<ServiceEventFeeDetails> getEventFeeDetails() {
        if (eventFeeDetails == null) {
            eventFeeDetails = new ArrayList<ServiceEventFeeDetails>();
        }
        return this.eventFeeDetails;
    }

    /**
     * Gets the value of the charityEventAdmin property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charityEventAdmin property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharityEventAdmin().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceCharityEventAdministrator }
     * 
     * 
     */
    public List<ServiceCharityEventAdministrator> getCharityEventAdmin() {
        if (charityEventAdmin == null) {
            charityEventAdmin = new ArrayList<ServiceCharityEventAdministrator>();
        }
        return this.charityEventAdmin;
    }

    /**
     * Gets the value of the allUsersInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllUsersInd() {
        return allUsersInd;
    }

    /**
     * Sets the value of the allUsersInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllUsersInd(String value) {
        this.allUsersInd = value;
    }

}
