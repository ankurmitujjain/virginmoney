
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorisationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorisationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secure-payment-processing.com/}Response">
 *       &lt;sequence>
 *         &lt;element name="MerchantNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cv2Avs" type="{http://secure-payment-processing.com/}Cv2Avs" minOccurs="0"/>
 *         &lt;element name="AuthorisationCode" type="{http://secure-payment-processing.com/}AuthorisationCodeResponse" minOccurs="0"/>
 *         &lt;element name="Card" type="{http://secure-payment-processing.com/}Card" minOccurs="0"/>
 *         &lt;element name="Transaction" type="{http://secure-payment-processing.com/}Transaction" minOccurs="0"/>
 *         &lt;element name="AuthorisedAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Acquirer" type="{http://secure-payment-processing.com/}Acquirer" minOccurs="0"/>
 *         &lt;element name="Bank" type="{http://secure-payment-processing.com/}Bank" minOccurs="0"/>
 *         &lt;element name="Apacs" type="{http://secure-payment-processing.com/}Apacs" minOccurs="0"/>
 *         &lt;element name="Telephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorisationResponse", propOrder = {
    "merchantNumber",
    "cv2Avs",
    "authorisationCode",
    "card",
    "transaction",
    "authorisedAmount",
    "acquirer",
    "bank",
    "apacs",
    "telephone"
})
public class AuthorisationResponse
    extends Response
{

    @XmlElement(name = "MerchantNumber")
    protected String merchantNumber;
    @XmlElement(name = "Cv2Avs")
    protected Cv2Avs cv2Avs;
    @XmlElement(name = "AuthorisationCode")
    protected AuthorisationCodeResponse authorisationCode;
    @XmlElement(name = "Card")
    protected Card card;
    @XmlElement(name = "Transaction")
    protected Transaction transaction;
    @XmlElement(name = "AuthorisedAmount")
    protected String authorisedAmount;
    @XmlElement(name = "Acquirer")
    protected Acquirer acquirer;
    @XmlElement(name = "Bank")
    protected Bank bank;
    @XmlElement(name = "Apacs")
    protected Apacs apacs;
    @XmlElement(name = "Telephone")
    protected String telephone;

    /**
     * Gets the value of the merchantNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantNumber() {
        return merchantNumber;
    }

    /**
     * Sets the value of the merchantNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantNumber(String value) {
        this.merchantNumber = value;
    }

    /**
     * Gets the value of the cv2Avs property.
     * 
     * @return
     *     possible object is
     *     {@link Cv2Avs }
     *     
     */
    public Cv2Avs getCv2Avs() {
        return cv2Avs;
    }

    /**
     * Sets the value of the cv2Avs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cv2Avs }
     *     
     */
    public void setCv2Avs(Cv2Avs value) {
        this.cv2Avs = value;
    }

    /**
     * Gets the value of the authorisationCode property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorisationCodeResponse }
     *     
     */
    public AuthorisationCodeResponse getAuthorisationCode() {
        return authorisationCode;
    }

    /**
     * Sets the value of the authorisationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorisationCodeResponse }
     *     
     */
    public void setAuthorisationCode(AuthorisationCodeResponse value) {
        this.authorisationCode = value;
    }

    /**
     * Gets the value of the card property.
     * 
     * @return
     *     possible object is
     *     {@link Card }
     *     
     */
    public Card getCard() {
        return card;
    }

    /**
     * Sets the value of the card property.
     * 
     * @param value
     *     allowed object is
     *     {@link Card }
     *     
     */
    public void setCard(Card value) {
        this.card = value;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * @return
     *     possible object is
     *     {@link Transaction }
     *     
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Sets the value of the transaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transaction }
     *     
     */
    public void setTransaction(Transaction value) {
        this.transaction = value;
    }

    /**
     * Gets the value of the authorisedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorisedAmount() {
        return authorisedAmount;
    }

    /**
     * Sets the value of the authorisedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorisedAmount(String value) {
        this.authorisedAmount = value;
    }

    /**
     * Gets the value of the acquirer property.
     * 
     * @return
     *     possible object is
     *     {@link Acquirer }
     *     
     */
    public Acquirer getAcquirer() {
        return acquirer;
    }

    /**
     * Sets the value of the acquirer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Acquirer }
     *     
     */
    public void setAcquirer(Acquirer value) {
        this.acquirer = value;
    }

    /**
     * Gets the value of the bank property.
     * 
     * @return
     *     possible object is
     *     {@link Bank }
     *     
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Sets the value of the bank property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bank }
     *     
     */
    public void setBank(Bank value) {
        this.bank = value;
    }

    /**
     * Gets the value of the apacs property.
     * 
     * @return
     *     possible object is
     *     {@link Apacs }
     *     
     */
    public Apacs getApacs() {
        return apacs;
    }

    /**
     * Sets the value of the apacs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Apacs }
     *     
     */
    public void setApacs(Apacs value) {
        this.apacs = value;
    }

    /**
     * Gets the value of the telephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets the value of the telephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone(String value) {
        this.telephone = value;
    }

}
