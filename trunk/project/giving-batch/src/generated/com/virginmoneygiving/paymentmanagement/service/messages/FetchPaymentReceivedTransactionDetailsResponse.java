
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="paymentList" type="{http://www.virginmoneygiving.com/type/payment-management/operations/}Payment" maxOccurs="unbounded"/>
 *         &lt;element name="basicReponse" type="{http://www.virginmoneygiving.com/type/payment-management/common/}BasicResponse"/>
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
    "paymentList",
    "basicReponse"
})
@XmlRootElement(name = "fetchPaymentReceivedTransactionDetailsResponse", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class FetchPaymentReceivedTransactionDetailsResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected List<Payment> paymentList;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the paymentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Payment }
     * 
     * 
     */
    public List<Payment> getPaymentList() {
        if (paymentList == null) {
            paymentList = new ArrayList<Payment>();
        }
        return this.paymentList;
    }

    /**
     * Gets the value of the basicReponse property.
     * 
     * @return
     *     possible object is
     *     {@link BasicResponse }
     *     
     */
    public BasicResponse getBasicReponse() {
        return basicReponse;
    }

    /**
     * Sets the value of the basicReponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicResponse }
     *     
     */
    public void setBasicReponse(BasicResponse value) {
        this.basicReponse = value;
    }

}
