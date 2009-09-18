
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidationExtendedResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidationExtendedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://secure-payment-processing.com/}Result" minOccurs="0"/>
 *         &lt;element name="InternalValidationPassed" type="{http://secure-payment-processing.com/}ValidationPassedEnum"/>
 *         &lt;element name="CardToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SolveResponse" type="{http://secure-payment-processing.com/}ValidationResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationExtendedResponse", propOrder = {
    "result",
    "internalValidationPassed",
    "cardToken",
    "solveResponse"
})
public class ValidationExtendedResponse {

    @XmlElement(name = "Result")
    protected Result result;
    @XmlElement(name = "InternalValidationPassed", required = true)
    protected ValidationPassedEnum internalValidationPassed;
    @XmlElement(name = "CardToken")
    protected String cardToken;
    @XmlElement(name = "SolveResponse")
    protected ValidationResponse solveResponse;

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
     * Gets the value of the internalValidationPassed property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationPassedEnum }
     *     
     */
    public ValidationPassedEnum getInternalValidationPassed() {
        return internalValidationPassed;
    }

    /**
     * Sets the value of the internalValidationPassed property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationPassedEnum }
     *     
     */
    public void setInternalValidationPassed(ValidationPassedEnum value) {
        this.internalValidationPassed = value;
    }

    /**
     * Gets the value of the cardToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardToken() {
        return cardToken;
    }

    /**
     * Sets the value of the cardToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardToken(String value) {
        this.cardToken = value;
    }

    /**
     * Gets the value of the solveResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationResponse }
     *     
     */
    public ValidationResponse getSolveResponse() {
        return solveResponse;
    }

    /**
     * Sets the value of the solveResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationResponse }
     *     
     */
    public void setSolveResponse(ValidationResponse value) {
        this.solveResponse = value;
    }

}
