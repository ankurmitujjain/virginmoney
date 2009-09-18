
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoginReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoginReturn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://secure-payment-processing.com/}Result" minOccurs="0"/>
 *         &lt;element name="LoginDetails" type="{http://secure-payment-processing.com/}LoginDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoginReturn", propOrder = {
    "result",
    "loginDetails"
})
public class LoginReturn {

    @XmlElement(name = "Result")
    protected Result result;
    @XmlElement(name = "LoginDetails")
    protected LoginDetails loginDetails;

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
     * Gets the value of the loginDetails property.
     * 
     * @return
     *     possible object is
     *     {@link LoginDetails }
     *     
     */
    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    /**
     * Sets the value of the loginDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoginDetails }
     *     
     */
    public void setLoginDetails(LoginDetails value) {
        this.loginDetails = value;
    }

}
