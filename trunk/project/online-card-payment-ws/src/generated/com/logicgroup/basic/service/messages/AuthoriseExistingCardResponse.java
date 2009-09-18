
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
 *         &lt;element name="AuthoriseExistingCardResult" type="{http://secure-payment-processing.com/}AuthResponse" minOccurs="0"/>
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
    "authoriseExistingCardResult"
})
@XmlRootElement(name = "AuthoriseExistingCardResponse")
public class AuthoriseExistingCardResponse {

    @XmlElement(name = "AuthoriseExistingCardResult")
    protected AuthResponse authoriseExistingCardResult;

    /**
     * Gets the value of the authoriseExistingCardResult property.
     * 
     * @return
     *     possible object is
     *     {@link AuthResponse }
     *     
     */
    public AuthResponse getAuthoriseExistingCardResult() {
        return authoriseExistingCardResult;
    }

    /**
     * Sets the value of the authoriseExistingCardResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthResponse }
     *     
     */
    public void setAuthoriseExistingCardResult(AuthResponse value) {
        this.authoriseExistingCardResult = value;
    }

}
