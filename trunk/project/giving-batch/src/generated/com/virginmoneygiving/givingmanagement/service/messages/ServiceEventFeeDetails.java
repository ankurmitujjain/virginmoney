
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceEventFeeDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceEventFeeDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="feeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="feeReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="feeSituation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="feeAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="feeCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charitableActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceEventFeeDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", propOrder = {
    "feeId",
    "feeReference",
    "feeSituation",
    "feeAmount",
    "feeCurrency",
    "charitableActivityId"
})
public class ServiceEventFeeDetails {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long feeId;
    @XmlElement(required = true, nillable = true)
    protected String feeReference;
    @XmlElement(required = true, nillable = true)
    protected String feeSituation;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal feeAmount;
    @XmlElement(required = true, nillable = true)
    protected String feeCurrency;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charitableActivityId;

    /**
     * Gets the value of the feeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFeeId() {
        return feeId;
    }

    /**
     * Sets the value of the feeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFeeId(Long value) {
        this.feeId = value;
    }

    /**
     * Gets the value of the feeReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeeReference() {
        return feeReference;
    }

    /**
     * Sets the value of the feeReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeeReference(String value) {
        this.feeReference = value;
    }

    /**
     * Gets the value of the feeSituation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeeSituation() {
        return feeSituation;
    }

    /**
     * Sets the value of the feeSituation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeeSituation(String value) {
        this.feeSituation = value;
    }

    /**
     * Gets the value of the feeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    /**
     * Sets the value of the feeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFeeAmount(BigDecimal value) {
        this.feeAmount = value;
    }

    /**
     * Gets the value of the feeCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeeCurrency() {
        return feeCurrency;
    }

    /**
     * Sets the value of the feeCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeeCurrency(String value) {
        this.feeCurrency = value;
    }

    /**
     * Gets the value of the charitableActivityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCharitableActivityId() {
        return charitableActivityId;
    }

    /**
     * Sets the value of the charitableActivityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCharitableActivityId(Long value) {
        this.charitableActivityId = value;
    }

}
