
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
 *         &lt;element name="AuthoriseExistingCardResult" type="{http://secure-payment-processing.com/}AuthResponseExt" minOccurs="0"/>
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
    protected AuthResponseExt authoriseExistingCardResult;

    /**
     * Gets the value of the authoriseExistingCardResult property.
     * 
     * @return
     *     possible object is
     *     {@link AuthResponseExt }
     *     
     */
    public AuthResponseExt getAuthoriseExistingCardResult() {
        return authoriseExistingCardResult;
    }

    /**
     * Sets the value of the authoriseExistingCardResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthResponseExt }
     *     
     */
    public void setAuthoriseExistingCardResult(AuthResponseExt value) {
        this.authoriseExistingCardResult = value;
    }

}
