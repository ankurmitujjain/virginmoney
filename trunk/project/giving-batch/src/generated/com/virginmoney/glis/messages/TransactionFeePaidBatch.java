
package com.virginmoney.glis.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionFeePaidBatch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionFeePaidBatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.virginmoney.com/type/glis/header/2008/09}MessageHeader"/>
 *         &lt;element name="Batch" type="{http://www.virginmoney.com/type/glis/2009/01}Batch"/>
 *         &lt;element name="TransactionFee" type="{http://www.virginmoney.com/type/glis/2009/01}TransactionFeePaid" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionFeePaidBatch", propOrder = {
    "header",
    "batch",
    "transactionFee"
})
public class TransactionFeePaidBatch {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(name = "Batch", required = true)
    protected Batch batch;
    @XmlElement(name = "TransactionFee", required = true)
    protected List<TransactionFeePaid> transactionFee;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link MessageHeader }
     *     
     */
    public MessageHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageHeader }
     *     
     */
    public void setHeader(MessageHeader value) {
        this.header = value;
    }

    /**
     * Gets the value of the batch property.
     * 
     * @return
     *     possible object is
     *     {@link Batch }
     *     
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * Sets the value of the batch property.
     * 
     * @param value
     *     allowed object is
     *     {@link Batch }
     *     
     */
    public void setBatch(Batch value) {
        this.batch = value;
    }

    /**
     * Gets the value of the transactionFee property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactionFee property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactionFee().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransactionFeePaid }
     * 
     * 
     */
    public List<TransactionFeePaid> getTransactionFee() {
        if (transactionFee == null) {
            transactionFee = new ArrayList<TransactionFeePaid>();
        }
        return this.transactionFee;
    }

}
