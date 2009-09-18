
package com.virginmoneygiving.paymentmanagement.service.messages;

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
 *         &lt;element name="prsStatus" type="{http://www.virginmoneygiving.com/type/payment-management/operations/}PrsStatus"/>
 *         &lt;element name="paymentReceiveSelection" type="{http://www.virginmoneygiving.com/type/payment-management/operations/}PaymentReceiveSelection"/>
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
    "prsStatus",
    "paymentReceiveSelection"
})
@XmlRootElement(name = "fetchPaymentReceiveDisplayRequest", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class FetchPaymentReceiveDisplayRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected PrsStatus prsStatus;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected PaymentReceiveSelection paymentReceiveSelection;

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
     * Gets the value of the prsStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PrsStatus }
     *     
     */
    public PrsStatus getPrsStatus() {
        return prsStatus;
    }

    /**
     * Sets the value of the prsStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrsStatus }
     *     
     */
    public void setPrsStatus(PrsStatus value) {
        this.prsStatus = value;
    }

    /**
     * Gets the value of the paymentReceiveSelection property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentReceiveSelection }
     *     
     */
    public PaymentReceiveSelection getPaymentReceiveSelection() {
        return paymentReceiveSelection;
    }

    /**
     * Sets the value of the paymentReceiveSelection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentReceiveSelection }
     *     
     */
    public void setPaymentReceiveSelection(PaymentReceiveSelection value) {
        this.paymentReceiveSelection = value;
    }

}
