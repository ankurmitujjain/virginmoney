
package com.virginmoney.glis.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Charity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Charity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityStatus" type="{http://www.virginmoney.com/type/glis/2009/01}CharityStatusType"/>
 *         &lt;element name="transactionDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="registrationNumber" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="vmgCharityRefId" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="paymentGroup" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="registeredAddress" type="{http://www.virginmoney.com/type/glis/2009/01}Address"/>
 *         &lt;element name="administrationAddress" type="{http://www.virginmoney.com/type/glis/2009/01}Address" minOccurs="0"/>
 *         &lt;element name="bankAccounts" type="{http://www.virginmoney.com/type/glis/2009/01}BankAccountType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eventRef" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Charity", propOrder = {
    "charityStatus",
    "transactionDate",
    "charityName",
    "registrationNumber",
    "vmgCharityRefId",
    "category",
    "paymentGroup",
    "registeredAddress",
    "administrationAddress",
    "bankAccounts",
    "eventRef"
})
public class Charity {

    @XmlElement(required = true)
    protected CharityStatusType charityStatus;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar transactionDate;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String charityName;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String registrationNumber;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String vmgCharityRefId;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String category;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String paymentGroup;
    @XmlElement(required = true)
    protected Address registeredAddress;
    protected Address administrationAddress;
    @XmlElement(nillable = true)
    protected List<BankAccountType> bankAccounts;
    protected Integer eventRef;

    /**
     * Gets the value of the charityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CharityStatusType }
     *     
     */
    public CharityStatusType getCharityStatus() {
        return charityStatus;
    }

    /**
     * Sets the value of the charityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharityStatusType }
     *     
     */
    public void setCharityStatus(CharityStatusType value) {
        this.charityStatus = value;
    }

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransactionDate(XMLGregorianCalendar value) {
        this.transactionDate = value;
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
     * Gets the value of the registrationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the value of the registrationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationNumber(String value) {
        this.registrationNumber = value;
    }

    /**
     * Gets the value of the vmgCharityRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVmgCharityRefId() {
        return vmgCharityRefId;
    }

    /**
     * Sets the value of the vmgCharityRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVmgCharityRefId(String value) {
        this.vmgCharityRefId = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the paymentGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentGroup() {
        return paymentGroup;
    }

    /**
     * Sets the value of the paymentGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentGroup(String value) {
        this.paymentGroup = value;
    }

    /**
     * Gets the value of the registeredAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getRegisteredAddress() {
        return registeredAddress;
    }

    /**
     * Sets the value of the registeredAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setRegisteredAddress(Address value) {
        this.registeredAddress = value;
    }

    /**
     * Gets the value of the administrationAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAdministrationAddress() {
        return administrationAddress;
    }

    /**
     * Sets the value of the administrationAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAdministrationAddress(Address value) {
        this.administrationAddress = value;
    }

    /**
     * Gets the value of the bankAccounts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bankAccounts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBankAccounts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BankAccountType }
     * 
     * 
     */
    public List<BankAccountType> getBankAccounts() {
        if (bankAccounts == null) {
            bankAccounts = new ArrayList<BankAccountType>();
        }
        return this.bankAccounts;
    }

    /**
     * Gets the value of the eventRef property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEventRef() {
        return eventRef;
    }

    /**
     * Sets the value of the eventRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEventRef(Integer value) {
        this.eventRef = value;
    }

}
