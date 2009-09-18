
package com.virginmoneygiving.paymentmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Batch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Batch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BatchNumber" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="BatchDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="BatchType" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="ProcessBatchOnErrors" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="BatchChunkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Batch", propOrder = {
    "batchNumber",
    "batchDate",
    "batchType",
    "processBatchOnErrors",
    "batchChunkId"
})
public class Batch {

    @XmlElement(name = "BatchNumber", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String batchNumber;
    @XmlElement(name = "BatchDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar batchDate;
    @XmlElement(name = "BatchType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String batchType;
    @XmlElement(name = "ProcessBatchOnErrors")
    protected Boolean processBatchOnErrors;
    @XmlElement(name = "BatchChunkId", required = true)
    protected String batchChunkId;

    /**
     * Gets the value of the batchNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the value of the batchNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchNumber(String value) {
        this.batchNumber = value;
    }

    /**
     * Gets the value of the batchDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBatchDate() {
        return batchDate;
    }

    /**
     * Sets the value of the batchDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBatchDate(XMLGregorianCalendar value) {
        this.batchDate = value;
    }

    /**
     * Gets the value of the batchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchType() {
        return batchType;
    }

    /**
     * Sets the value of the batchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchType(String value) {
        this.batchType = value;
    }

    /**
     * Gets the value of the processBatchOnErrors property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProcessBatchOnErrors() {
        return processBatchOnErrors;
    }

    /**
     * Sets the value of the processBatchOnErrors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProcessBatchOnErrors(Boolean value) {
        this.processBatchOnErrors = value;
    }

    /**
     * Gets the value of the batchChunkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchChunkId() {
        return batchChunkId;
    }

    /**
     * Sets the value of the batchChunkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchChunkId(String value) {
        this.batchChunkId = value;
    }

}
