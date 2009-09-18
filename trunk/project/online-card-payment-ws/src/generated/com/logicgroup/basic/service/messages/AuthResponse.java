
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SolveResponse" type="{http://secure-payment-processing.com/}AuthorisationResponse" minOccurs="0"/>
 *         &lt;element name="Result" type="{http://secure-payment-processing.com/}Result" minOccurs="0"/>
 *         &lt;element name="AuthorisationDetails" type="{http://secure-payment-processing.com/}AuthorisationDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthResponse", propOrder = {
    "solveResponse",
    "result",
    "authorisationDetails"
})
public class AuthResponse {

    @XmlElement(name = "SolveResponse")
    protected AuthorisationResponse solveResponse;
    @XmlElement(name = "Result")
    protected Result result;
    @XmlElement(name = "AuthorisationDetails")
    protected AuthorisationDetails authorisationDetails;

    /**
     * Gets the value of the solveResponse property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorisationResponse }
     *     
     */
    public AuthorisationResponse getSolveResponse() {
        return solveResponse;
    }

    /**
     * Sets the value of the solveResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorisationResponse }
     *     
     */
    public void setSolveResponse(AuthorisationResponse value) {
        this.solveResponse = value;
    }

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
     * Gets the value of the authorisationDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorisationDetails }
     *     
     */
    public AuthorisationDetails getAuthorisationDetails() {
        return authorisationDetails;
    }

    /**
     * Sets the value of the authorisationDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorisationDetails }
     *     
     */
    public void setAuthorisationDetails(AuthorisationDetails value) {
        this.authorisationDetails = value;
    }

}
