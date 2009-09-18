
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
 * <p>Java class for SettledCharityPaymentsBatch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SettledCharityPaymentsBatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchNumber" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="processDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ok" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="settledPayments" type="{http://www.virginmoney.com/type/glis/2009/01}SettledCharityPayments" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Errors" type="{http://www.virginmoney.com/type/glis/faults/2008/01}ErrorList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SettledCharityPaymentsBatch", propOrder = {
    "batchNumber",
    "processDate",
    "ok",
    "settledPayments",
    "errors"
})
public class SettledCharityPaymentsBatch {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String batchNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar processDate;
    protected boolean ok;
    @XmlElement(nillable = true)
    protected List<SettledCharityPayments> settledPayments;
    @XmlElement(name = "Errors")
    protected ErrorList errors;

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
     * Gets the value of the processDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProcessDate() {
        return processDate;
    }

    /**
     * Sets the value of the processDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProcessDate(XMLGregorianCalendar value) {
        this.processDate = value;
    }

    /**
     * Gets the value of the ok property.
     * 
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * Sets the value of the ok property.
     * 
     */
    public void setOk(boolean value) {
        this.ok = value;
    }

    /**
     * Gets the value of the settledPayments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the settledPayments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSettledPayments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SettledCharityPayments }
     * 
     * 
     */
    public List<SettledCharityPayments> getSettledPayments() {
        if (settledPayments == null) {
            settledPayments = new ArrayList<SettledCharityPayments>();
        }
        return this.settledPayments;
    }

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorList }
     *     
     */
    public ErrorList getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorList }
     *     
     */
    public void setErrors(ErrorList value) {
        this.errors = value;
    }

}
