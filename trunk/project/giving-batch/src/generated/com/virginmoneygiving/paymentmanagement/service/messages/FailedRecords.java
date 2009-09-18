
package com.virginmoneygiving.paymentmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for FailedRecords complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FailedRecords">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="failedRecordType" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="recordReference" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="failureReason" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FailedRecords", propOrder = {
    "failedRecordType",
    "recordReference",
    "failureReason"
})
public class FailedRecords {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String failedRecordType;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String recordReference;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String failureReason;

    /**
     * Gets the value of the failedRecordType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailedRecordType() {
        return failedRecordType;
    }

    /**
     * Sets the value of the failedRecordType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailedRecordType(String value) {
        this.failedRecordType = value;
    }

    /**
     * Gets the value of the recordReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordReference() {
        return recordReference;
    }

    /**
     * Sets the value of the recordReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordReference(String value) {
        this.recordReference = value;
    }

    /**
     * Gets the value of the failureReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailureReason() {
        return failureReason;
    }

    /**
     * Sets the value of the failureReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailureReason(String value) {
        this.failureReason = value;
    }

}
