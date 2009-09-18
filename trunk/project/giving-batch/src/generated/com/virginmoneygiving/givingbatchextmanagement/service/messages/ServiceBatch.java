
package com.virginmoneygiving.givingbatchextmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for serviceBatch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceBatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="batchNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="executionDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="nextBatchName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceBatch", propOrder = {
    "id",
    "batchNumber",
    "executionDateTime",
    "nextBatchName"
})
public class ServiceBatch {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true)
    protected String batchNumber;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar executionDateTime;
    @XmlElement(required = true, nillable = true)
    protected String nextBatchName;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

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
     * Gets the value of the executionDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExecutionDateTime() {
        return executionDateTime;
    }

    /**
     * Sets the value of the executionDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExecutionDateTime(XMLGregorianCalendar value) {
        this.executionDateTime = value;
    }

    /**
     * Gets the value of the nextBatchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextBatchName() {
        return nextBatchName;
    }

    /**
     * Sets the value of the nextBatchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextBatchName(String value) {
        this.nextBatchName = value;
    }

}
