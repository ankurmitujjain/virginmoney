
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for emailContent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="emailContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="webpageURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserSurname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "emailContent", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", propOrder = {
    "webpageURL",
    "charityName",
    "charityNumber",
    "fundraiserFirstName",
    "fundraiserSurname",
    "fundraiserReference"
})
public class EmailContent {

    @XmlElement(required = true)
    protected String webpageURL;
    @XmlElement(required = true)
    protected String charityName;
    @XmlElement(required = true)
    protected String charityNumber;
    @XmlElement(required = true)
    protected String fundraiserFirstName;
    @XmlElement(required = true)
    protected String fundraiserSurname;
    @XmlElement(required = true)
    protected String fundraiserReference;

    /**
     * Gets the value of the webpageURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebpageURL() {
        return webpageURL;
    }

    /**
     * Sets the value of the webpageURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebpageURL(String value) {
        this.webpageURL = value;
    }

    /**
     * Gets the value of the charityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the value of the charityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityName(String value) {
        this.charityName = value;
    }

    /**
     * Gets the value of the charityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityNumber() {
        return charityNumber;
    }

    /**
     * Sets the value of the charityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityNumber(String value) {
        this.charityNumber = value;
    }

    /**
     * Gets the value of the fundraiserFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserFirstName() {
        return fundraiserFirstName;
    }

    /**
     * Sets the value of the fundraiserFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserFirstName(String value) {
        this.fundraiserFirstName = value;
    }

    /**
     * Gets the value of the fundraiserSurname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserSurname() {
        return fundraiserSurname;
    }

    /**
     * Sets the value of the fundraiserSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserSurname(String value) {
        this.fundraiserSurname = value;
    }

    /**
     * Gets the value of the fundraiserReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserReference() {
        return fundraiserReference;
    }

    /**
     * Sets the value of the fundraiserReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserReference(String value) {
        this.fundraiserReference = value;
    }

}
