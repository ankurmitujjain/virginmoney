
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SettlementReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SettlementReturn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://secure-payment-processing.com/}Result" minOccurs="0"/>
 *         &lt;element name="SettlementDetails" type="{http://secure-payment-processing.com/}SettlementDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SettlementReturn", propOrder = {
    "result",
    "settlementDetails"
})
public class SettlementReturn {

    @XmlElement(name = "Result")
    protected Result result;
    @XmlElement(name = "SettlementDetails")
    protected SettlementDetails settlementDetails;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

    /**
     * Gets the value of the settlementDetails property.
     * 
     * @return
     *     possible object is
     *     {@link SettlementDetails }
     *     
     */
    public SettlementDetails getSettlementDetails() {
        return settlementDetails;
    }

    /**
     * Sets the value of the settlementDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link SettlementDetails }
     *     
     */
    public void setSettlementDetails(SettlementDetails value) {
        this.settlementDetails = value;
    }

}
