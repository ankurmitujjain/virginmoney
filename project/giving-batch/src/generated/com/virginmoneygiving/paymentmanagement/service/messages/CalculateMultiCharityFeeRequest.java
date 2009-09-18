
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.math.BigDecimal;
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
 *         &lt;element name="Header" type="{http://www.virginmoneygiving.com/type/header/}MessageHeader"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="giftAidApplicable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="multiCharityDivision" type="{http://www.virginmoneygiving.com/type/payment-management/}multiCharityDivision" maxOccurs="unbounded"/>
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
    "amount",
    "giftAidApplicable",
    "multiCharityDivision"
})
@XmlRootElement(name = "CalculateMultiCharityFeeRequest")
public class CalculateMultiCharityFeeRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(required = true)
    protected BigDecimal amount;
    protected boolean giftAidApplicable;
    @XmlElement(required = true)
    protected List<MultiCharityDivision> multiCharityDivision;

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
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the giftAidApplicable property.
     * 
     */
    public boolean isGiftAidApplicable() {
        return giftAidApplicable;
    }

    /**
     * Sets the value of the giftAidApplicable property.
     * 
     */
    public void setGiftAidApplicable(boolean value) {
        this.giftAidApplicable = value;
    }

    /**
     * Gets the value of the multiCharityDivision property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the multiCharityDivision property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMultiCharityDivision().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MultiCharityDivision }
     * 
     * 
     */
    public List<MultiCharityDivision> getMultiCharityDivision() {
        if (multiCharityDivision == null) {
            multiCharityDivision = new ArrayList<MultiCharityDivision>();
        }
        return this.multiCharityDivision;
    }

}
