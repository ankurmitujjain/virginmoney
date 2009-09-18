
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claimFromDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claimToDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claimTotal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numberOfRecords" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateProcessInitiated" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claimType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="listData" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "claimFromDate",
    "claimToDate",
    "claimTotal",
    "transactionType",
    "transactionDate",
    "numberOfRecords",
    "dateProcessInitiated",
    "claimType",
    "transStatus",
    "listData"
})
@XmlRootElement(name = "claimEmailRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
public class ClaimEmailRequest {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String claimFromDate;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String claimToDate;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String claimTotal;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String transactionType;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String transactionDate;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String numberOfRecords;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String dateProcessInitiated;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String claimType;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String transStatus;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String listData;

    /**
     * Gets the value of the claimFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimFromDate() {
        return claimFromDate;
    }

    /**
     * Sets the value of the claimFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimFromDate(String value) {
        this.claimFromDate = value;
    }

    /**
     * Gets the value of the claimToDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimToDate() {
        return claimToDate;
    }

    /**
     * Sets the value of the claimToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimToDate(String value) {
        this.claimToDate = value;
    }

    /**
     * Gets the value of the claimTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimTotal() {
        return claimTotal;
    }

    /**
     * Sets the value of the claimTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimTotal(String value) {
        this.claimTotal = value;
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
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionDate(String value) {
        this.transactionDate = value;
    }

    /**
     * Gets the value of the numberOfRecords property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     * Sets the value of the numberOfRecords property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfRecords(String value) {
        this.numberOfRecords = value;
    }

    /**
     * Gets the value of the dateProcessInitiated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateProcessInitiated() {
        return dateProcessInitiated;
    }

    /**
     * Sets the value of the dateProcessInitiated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateProcessInitiated(String value) {
        this.dateProcessInitiated = value;
    }

    /**
     * Gets the value of the claimType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimType() {
        return claimType;
    }

    /**
     * Sets the value of the claimType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimType(String value) {
        this.claimType = value;
    }

    /**
     * Gets the value of the transStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransStatus() {
        return transStatus;
    }

    /**
     * Sets the value of the transStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransStatus(String value) {
        this.transStatus = value;
    }

    /**
     * Gets the value of the listData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListData() {
        return listData;
    }

    /**
     * Sets the value of the listData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListData(String value) {
        this.listData = value;
    }

}
