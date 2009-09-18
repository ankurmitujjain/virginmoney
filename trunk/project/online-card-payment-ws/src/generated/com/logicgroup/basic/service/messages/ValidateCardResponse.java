
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
 *         &lt;element name="ValidateCardResult" type="{http://secure-payment-processing.com/}ValidationExtendedResponse" minOccurs="0"/>
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
    "validateCardResult"
})
@XmlRootElement(name = "ValidateCardResponse")
public class ValidateCardResponse {

    @XmlElement(name = "ValidateCardResult")
    protected ValidationExtendedResponse validateCardResult;

    /**
     * Gets the value of the validateCardResult property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationExtendedResponse }
     *     
     */
    public ValidationExtendedResponse getValidateCardResult() {
        return validateCardResult;
    }

    /**
     * Sets the value of the validateCardResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationExtendedResponse }
     *     
     */
    public void setValidateCardResult(ValidationExtendedResponse value) {
        this.validateCardResult = value;
    }

}
