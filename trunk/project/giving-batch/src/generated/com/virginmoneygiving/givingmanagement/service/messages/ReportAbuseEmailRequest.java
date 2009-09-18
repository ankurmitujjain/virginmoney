
package com.virginmoneygiving.givingmanagement.service.messages;

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
 *         &lt;element name="Header" type="{http://www.virginmoneygiving.com/type/header/}MessageHeader"/>
 *         &lt;element name="profanityReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="webpageURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserSurname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userReportAbuseComment" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "header",
    "profanityReference",
    "webpageURL",
    "charityName",
    "charityNumber",
    "fundraiserFirstName",
    "fundraiserSurname",
    "userReportAbuseComment"
})
@XmlRootElement(name = "reportAbuseEmailRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
public class ReportAbuseEmailRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String profanityReference;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String webpageURL;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String charityName;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String charityNumber;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String fundraiserFirstName;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String fundraiserSurname;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String userReportAbuseComment;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link MessageHeader }
     *     
     */
    public MessageHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageHeader }
     *     
     */
    public void setHeader(MessageHeader value) {
        this.header = value;
    }

    /**
     * Gets the value of the profanityReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfanityReference() {
        return profanityReference;
    }

    /**
     * Sets the value of the profanityReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfanityReference(String value) {
        this.profanityReference = value;
    }

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
     * Gets the value of the userReportAbuseComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserReportAbuseComment() {
        return userReportAbuseComment;
    }

    /**
     * Sets the value of the userReportAbuseComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserReportAbuseComment(String value) {
        this.userReportAbuseComment = value;
    }

}
