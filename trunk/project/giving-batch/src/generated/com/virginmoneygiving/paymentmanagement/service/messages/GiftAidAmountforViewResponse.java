
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
 *         &lt;element name="GiftAidPayments" type="{http://www.virginmoneygiving.com/type/payment-management/operations/}GiftAidPayment" maxOccurs="unbounded"/>
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
    "giftAidPayments"
})
@XmlRootElement(name = "GiftAidAmountforViewResponse", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class GiftAidAmountforViewResponse {

    @XmlElement(name = "GiftAidPayments", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected List<GiftAidPayment> giftAidPayments;

    /**
     * Gets the value of the giftAidPayments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the giftAidPayments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGiftAidPayments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GiftAidPayment }
     * 
     * 
     */
    public List<GiftAidPayment> getGiftAidPayments() {
        if (giftAidPayments == null) {
            giftAidPayments = new ArrayList<GiftAidPayment>();
        }
        return this.giftAidPayments;
    }

}
