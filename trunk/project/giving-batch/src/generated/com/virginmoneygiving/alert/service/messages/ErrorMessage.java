
package com.virginmoneygiving.alert.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ErrorMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorMessageKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorDefaultMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorMessage", propOrder = {
    "errorMessageKey",
    "errorDefaultMessage"
})
public class ErrorMessage {

    @XmlElement(name = "ErrorMessageKey", required = true)
    protected String errorMessageKey;
    @XmlElement(name = "ErrorDefaultMessage", required = true)
    protected String errorDefaultMessage;

    /**
     * Gets the value of the errorMessageKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessageKey() {
        return errorMessageKey;
    }

    /**
     * Sets the value of the errorMessageKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessageKey(String value) {
        this.errorMessageKey = value;
    }

    /**
     * Gets the value of the errorDefaultMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDefaultMessage() {
        return errorDefaultMessage;
    }

    /**
     * Sets the value of the errorDefaultMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDefaultMessage(String value) {
        this.errorDefaultMessage = value;
    }

}
