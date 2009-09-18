
package com.logicgroup.extended.service.messages;

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
 *         &lt;element name="CancelResult" type="{http://secure-payment-processing.com/}AuthResponseExt" minOccurs="0"/>
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
    "cancelResult"
})
@XmlRootElement(name = "CancelResponse")
public class CancelResponse {

    @XmlElement(name = "CancelResult")
    protected AuthResponseExt cancelResult;

    /**
     * Gets the value of the cancelResult property.
     * 
     * @return
     *     possible object is
     *     {@link AuthResponseExt }
     *     
     */
    public AuthResponseExt getCancelResult() {
        return cancelResult;
    }

    /**
     * Sets the value of the cancelResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthResponseExt }
     *     
     */
    public void setCancelResult(AuthResponseExt value) {
        this.cancelResult = value;
    }

}
