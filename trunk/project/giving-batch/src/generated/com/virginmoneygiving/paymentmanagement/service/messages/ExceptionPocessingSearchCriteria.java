
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ExceptionPocessingSearchCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExceptionPocessingSearchCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionFromDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="transactionToDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="transactionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transctionAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="transctionStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transctionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="paymentRefNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creditNoteNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invoiceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hmrcCharrityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="vmgRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExceptionPocessingSearchCriteria", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", propOrder = {
    "transactionFromDate",
    "transactionToDate",
    "transactionType",
    "transctionAmount",
    "transctionStatus",
    "transctionId",
    "paymentRefNumber",
    "creditNoteNumber",
    "invoiceNumber",
    "hmrcCharrityId",
    "vmgRef"
})
public class ExceptionPocessingSearchCriteria {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar transactionFromDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar transactionToDate;
    @XmlElement(required = true)
    protected String transactionType;
    @XmlElement(required = true)
    protected BigDecimal transctionAmount;
    @XmlElement(required = true)
    protected String transctionStatus;
    protected long transctionId;
    @XmlElement(required = true)
    protected String paymentRefNumber;
    @XmlElement(required = true)
    protected String creditNoteNumber;
    @XmlElement(required = true)
    protected String invoiceNumber;
    protected long hmrcCharrityId;
    @XmlElement(required = true)
    protected String vmgRef;

    /**
     * Gets the value of the transactionFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransactionFromDate() {
        return transactionFromDate;
    }

    /**
     * Sets the value of the transactionFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransactionFromDate(XMLGregorianCalendar value) {
        this.transactionFromDate = value;
    }

    /**
     * Gets the value of the transactionToDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransactionToDate() {
        return transactionToDate;
    }

    /**
     * Sets the value of the transactionToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransactionToDate(XMLGregorianCalendar value) {
        this.transactionToDate = value;
    }

    /**
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionType(String value) {
        this.transactionType = value;
    }

    /**
     * Gets the value of the transctionAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTransctionAmount() {
        return transctionAmount;
    }

    /**
     * Sets the value of the transctionAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTransctionAmount(BigDecimal value) {
        this.transctionAmount = value;
    }

    /**
     * Gets the value of the transctionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransctionStatus() {
        return transctionStatus;
    }

    /**
     * Sets the value of the transctionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransctionStatus(String value) {
        this.transctionStatus = value;
    }

    /**
     * Gets the value of the transctionId property.
     * 
     */
    public long getTransctionId() {
        return transctionId;
    }

    /**
     * Sets the value of the transctionId property.
     * 
     */
    public void setTransctionId(long value) {
        this.transctionId = value;
    }

    /**
     * Gets the value of the paymentRefNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentRefNumber() {
        return paymentRefNumber;
    }

    /**
     * Sets the value of the paymentRefNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentRefNumber(String value) {
        this.paymentRefNumber = value;
    }

    /**
     * Gets the value of the creditNoteNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditNoteNumber() {
        return creditNoteNumber;
    }

    /**
     * Sets the value of the creditNoteNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditNoteNumber(String value) {
        this.creditNoteNumber = value;
    }

    /**
     * Gets the value of the invoiceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets the value of the invoiceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceNumber(String value) {
        this.invoiceNumber = value;
    }

    /**
     * Gets the value of the hmrcCharrityId property.
     * 
     */
    public long getHmrcCharrityId() {
        return hmrcCharrityId;
    }

    /**
     * Sets the value of the hmrcCharrityId property.
     * 
     */
    public void setHmrcCharrityId(long value) {
        this.hmrcCharrityId = value;
    }

    /**
     * Gets the value of the vmgRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVmgRef() {
        return vmgRef;
    }

    /**
     * Sets the value of the vmgRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVmgRef(String value) {
        this.vmgRef = value;
    }

}
