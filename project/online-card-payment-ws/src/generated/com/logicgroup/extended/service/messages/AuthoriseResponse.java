
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
 *         &lt;element name="AuthoriseResult" type="{http://secure-payment-processing.com/}AuthResponseExt" minOccurs="0"/>
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
    "authoriseResult"
})
@XmlRootElement(name = "AuthoriseResponse")
public class AuthoriseResponse {

    @XmlElement(name = "AuthoriseResult")
    protected AuthResponseExt authoriseResult;

    /**
     * Gets the value of the authoriseResult property.
     * 
     * @return
     *     possible object is
     *     {@link AuthResponseExt }
     *     
     */
    public AuthResponseExt getAuthoriseResult() {
        return authoriseResult;
    }

    /**
     * Sets the value of the authoriseResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthResponseExt }
     *     
     */
    public void setAuthoriseResult(AuthResponseExt value) {
        this.authoriseResult = value;
    }

}
