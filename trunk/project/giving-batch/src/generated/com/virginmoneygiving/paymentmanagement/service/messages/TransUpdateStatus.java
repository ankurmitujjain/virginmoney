
package com.virginmoneygiving.paymentmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransUpdateStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransUpdateStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transUpdateSuccessful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransUpdateStatus", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", propOrder = {
    "transUpdateSuccessful"
})
public class TransUpdateStatus {

    protected boolean transUpdateSuccessful;

    /**
     * Gets the value of the transUpdateSuccessful property.
     * 
     */
    public boolean isTransUpdateSuccessful() {
        return transUpdateSuccessful;
    }

    /**
     * Sets the value of the transUpdateSuccessful property.
     * 
     */
    public void setTransUpdateSuccessful(boolean value) {
        this.transUpdateSuccessful = value;
    }

}
