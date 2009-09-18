
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for responseHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="responseHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestType" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="requestBatchNumber" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="requestBatchDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="requestBatchType" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="requestChunkId" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseHeader", propOrder = {
    "requestType",
    "requestBatchNumber",
    "requestBatchDate",
    "requestBatchType",
    "requestChunkId"
})
public class ResponseHeader {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String requestType;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String requestBatchNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar requestBatchDate;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String requestBatchType;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String requestChunkId;

    /**
     * Gets the value of the requestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Sets the value of the requestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestType(String value) {
        this.requestType = value;
    }

    /**
     * Gets the value of the requestBatchNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestBatchNumber() {
        return requestBatchNumber;
    }

    /**
     * Sets the value of the requestBatchNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestBatchNumber(String value) {
        this.requestBatchNumber = value;
    }

    /**
     * Gets the value of the requestBatchDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestBatchDate() {
        return requestBatchDate;
    }

    /**
     * Sets the value of the requestBatchDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestBatchDate(XMLGregorianCalendar value) {
        this.requestBatchDate = value;
    }

    /**
     * Gets the value of the requestBatchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestBatchType() {
        return requestBatchType;
    }

    /**
     * Sets the value of the requestBatchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestBatchType(String value) {
        this.requestBatchType = value;
    }

    /**
     * Gets the value of the requestChunkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestChunkId() {
        return requestChunkId;
    }

    /**
     * Sets the value of the requestChunkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestChunkId(String value) {
        this.requestChunkId = value;
    }

}
