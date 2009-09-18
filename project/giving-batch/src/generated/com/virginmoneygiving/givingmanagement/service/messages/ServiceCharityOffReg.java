
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCharityOffReg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharityOffReg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offRegId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charity" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceCharity"/>
 *         &lt;element name="charityHmrcRefNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vmgHmrcRefNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankStatementStatus" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceOfflineRegStatus"/>
 *         &lt;element name="charityVerifyStatus" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceOfflineRegStatus"/>
 *         &lt;element name="giftFormStatus" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceOfflineRegStatus"/>
 *         &lt;element name="hmrcFormStatus" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceOfflineRegStatus"/>
 *         &lt;element name="createdUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdProcess" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdIPAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updatedUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updatedProcess" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updatedIPAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityOffRegLogs" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceCharityOffRegLog" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="charityOffRegNotes" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceCharityOffRegNote" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharityOffReg", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "offRegId",
    "charity",
    "charityHmrcRefNumber",
    "vmgHmrcRefNumber",
    "bankStatementStatus",
    "charityVerifyStatus",
    "giftFormStatus",
    "hmrcFormStatus",
    "createdUser",
    "createdProcess",
    "createdIPAddress",
    "updatedUser",
    "updatedProcess",
    "updatedIPAddress",
    "charityOffRegLogs",
    "charityOffRegNotes"
})
public class ServiceCharityOffReg {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long offRegId;
    @XmlElement(required = true, nillable = true)
    protected ServiceCharity charity;
    @XmlElement(required = true, nillable = true)
    protected String charityHmrcRefNumber;
    @XmlElement(required = true, nillable = true)
    protected String vmgHmrcRefNumber;
    @XmlElement(required = true, nillable = true)
    protected ServiceOfflineRegStatus bankStatementStatus;
    @XmlElement(required = true, nillable = true)
    protected ServiceOfflineRegStatus charityVerifyStatus;
    @XmlElement(required = true, nillable = true)
    protected ServiceOfflineRegStatus giftFormStatus;
    @XmlElement(required = true, nillable = true)
    protected ServiceOfflineRegStatus hmrcFormStatus;
    @XmlElement(required = true, nillable = true)
    protected String createdUser;
    @XmlElement(required = true, nillable = true)
    protected String createdProcess;
    @XmlElement(required = true, nillable = true)
    protected String createdIPAddress;
    @XmlElement(required = true, nillable = true)
    protected String updatedUser;
    @XmlElement(required = true, nillable = true)
    protected String updatedProcess;
    @XmlElement(required = true, nillable = true)
    protected String updatedIPAddress;
    @XmlElement(nillable = true)
    protected List<ServiceCharityOffRegLog> charityOffRegLogs;
    @XmlElement(nillable = true)
    protected List<ServiceCharityOffRegNote> charityOffRegNotes;

    /**
     * Gets the value of the offRegId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOffRegId() {
        return offRegId;
    }

    /**
     * Sets the value of the offRegId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOffRegId(Long value) {
        this.offRegId = value;
    }

    /**
     * Gets the value of the charity property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCharity }
     *     
     */
    public ServiceCharity getCharity() {
        return charity;
    }

    /**
     * Sets the value of the charity property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCharity }
     *     
     */
    public void setCharity(ServiceCharity value) {
        this.charity = value;
    }

    /**
     * Gets the value of the charityHmrcRefNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityHmrcRefNumber() {
        return charityHmrcRefNumber;
    }

    /**
     * Sets the value of the charityHmrcRefNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityHmrcRefNumber(String value) {
        this.charityHmrcRefNumber = value;
    }

    /**
     * Gets the value of the vmgHmrcRefNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVmgHmrcRefNumber() {
        return vmgHmrcRefNumber;
    }

    /**
     * Sets the value of the vmgHmrcRefNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVmgHmrcRefNumber(String value) {
        this.vmgHmrcRefNumber = value;
    }

    /**
     * Gets the value of the bankStatementStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceOfflineRegStatus }
     *     
     */
    public ServiceOfflineRegStatus getBankStatementStatus() {
        return bankStatementStatus;
    }

    /**
     * Sets the value of the bankStatementStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceOfflineRegStatus }
     *     
     */
    public void setBankStatementStatus(ServiceOfflineRegStatus value) {
        this.bankStatementStatus = value;
    }

    /**
     * Gets the value of the charityVerifyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceOfflineRegStatus }
     *     
     */
    public ServiceOfflineRegStatus getCharityVerifyStatus() {
        return charityVerifyStatus;
    }

    /**
     * Sets the value of the charityVerifyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceOfflineRegStatus }
     *     
     */
    public void setCharityVerifyStatus(ServiceOfflineRegStatus value) {
        this.charityVerifyStatus = value;
    }

    /**
     * Gets the value of the giftFormStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceOfflineRegStatus }
     *     
     */
    public ServiceOfflineRegStatus getGiftFormStatus() {
        return giftFormStatus;
    }

    /**
     * Sets the value of the giftFormStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceOfflineRegStatus }
     *     
     */
    public void setGiftFormStatus(ServiceOfflineRegStatus value) {
        this.giftFormStatus = value;
    }

    /**
     * Gets the value of the hmrcFormStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceOfflineRegStatus }
     *     
     */
    public ServiceOfflineRegStatus getHmrcFormStatus() {
        return hmrcFormStatus;
    }

    /**
     * Sets the value of the hmrcFormStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceOfflineRegStatus }
     *     
     */
    public void setHmrcFormStatus(ServiceOfflineRegStatus value) {
        this.hmrcFormStatus = value;
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
     * Gets the value of the createdProcess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedProcess() {
        return createdProcess;
    }

    /**
     * Sets the value of the createdProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedProcess(String value) {
        this.createdProcess = value;
    }

    /**
     * Gets the value of the createdIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedIPAddress() {
        return createdIPAddress;
    }

    /**
     * Sets the value of the createdIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedIPAddress(String value) {
        this.createdIPAddress = value;
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
     * Gets the value of the updatedProcess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedProcess() {
        return updatedProcess;
    }

    /**
     * Sets the value of the updatedProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedProcess(String value) {
        this.updatedProcess = value;
    }

    /**
     * Gets the value of the updatedIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedIPAddress() {
        return updatedIPAddress;
    }

    /**
     * Sets the value of the updatedIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedIPAddress(String value) {
        this.updatedIPAddress = value;
    }

    /**
     * Gets the value of the charityOffRegLogs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charityOffRegLogs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharityOffRegLogs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceCharityOffRegLog }
     * 
     * 
     */
    public List<ServiceCharityOffRegLog> getCharityOffRegLogs() {
        if (charityOffRegLogs == null) {
            charityOffRegLogs = new ArrayList<ServiceCharityOffRegLog>();
        }
        return this.charityOffRegLogs;
    }

    /**
     * Gets the value of the charityOffRegNotes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charityOffRegNotes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharityOffRegNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceCharityOffRegNote }
     * 
     * 
     */
    public List<ServiceCharityOffRegNote> getCharityOffRegNotes() {
        if (charityOffRegNotes == null) {
            charityOffRegNotes = new ArrayList<ServiceCharityOffRegNote>();
        }
        return this.charityOffRegNotes;
    }

}
