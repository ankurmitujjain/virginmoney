
package com.logicgroup.basic.service.messages;

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
 *         &lt;element name="SettlementExistingCardResult" type="{http://secure-payment-processing.com/}SettlementReturn" minOccurs="0"/>
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
    "settlementExistingCardResult"
})
@XmlRootElement(name = "SettlementExistingCardResponse")
public class SettlementExistingCardResponse {

    @XmlElement(name = "SettlementExistingCardResult")
    protected SettlementReturn settlementExistingCardResult;

    /**
     * Gets the value of the settlementExistingCardResult property.
     * 
     * @return
     *     possible object is
     *     {@link SettlementReturn }
     *     
     */
    public SettlementReturn getSettlementExistingCardResult() {
        return settlementExistingCardResult;
    }

    /**
     * Sets the value of the settlementExistingCardResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SettlementReturn }
     *     
     */
    public void setSettlementExistingCardResult(SettlementReturn value) {
        this.settlementExistingCardResult = value;
    }

}
