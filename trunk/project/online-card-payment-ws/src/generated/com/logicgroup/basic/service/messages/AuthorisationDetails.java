
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorisationDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorisationDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CardToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AuthorisationID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorisationDetails", propOrder = {
    "cardToken",
    "authorisationID"
})
public class AuthorisationDetails {

    @XmlElement(name = "CardToken")
    protected String cardToken;
    @XmlElement(name = "AuthorisationID")
    protected int authorisationID;

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
     * Gets the value of the authorisationID property.
     * 
     */
    public int getAuthorisationID() {
        return authorisationID;
    }

    /**
     * Sets the value of the authorisationID property.
     * 
     */
    public void setAuthorisationID(int value) {
        this.authorisationID = value;
    }

}
