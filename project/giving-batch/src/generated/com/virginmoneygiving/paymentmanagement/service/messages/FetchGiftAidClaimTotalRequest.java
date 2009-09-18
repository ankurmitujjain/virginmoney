
package com.virginmoneygiving.paymentmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="giftAidClaimFromDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="giftAidClaimToDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
    "giftAidClaimFromDate",
    "giftAidClaimToDate"
})
@XmlRootElement(name = "fetchGiftAidClaimTotalRequest", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class FetchGiftAidClaimTotalRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar giftAidClaimFromDate;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar giftAidClaimToDate;

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
     * Gets the value of the giftAidClaimFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGiftAidClaimFromDate() {
        return giftAidClaimFromDate;
    }

    /**
     * Sets the value of the giftAidClaimFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGiftAidClaimFromDate(XMLGregorianCalendar value) {
        this.giftAidClaimFromDate = value;
    }

    /**
     * Gets the value of the giftAidClaimToDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGiftAidClaimToDate() {
        return giftAidClaimToDate;
    }

    /**
     * Sets the value of the giftAidClaimToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGiftAidClaimToDate(XMLGregorianCalendar value) {
        this.giftAidClaimToDate = value;
    }

}
