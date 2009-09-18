
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
 *         &lt;element name="transitionalReliefAmountSettlement" type="{http://www.virginmoneygiving.com/type/giving-management/operations/}TransitionalReliefAmountSettlement"/>
 *         &lt;element name="userEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "transitionalReliefAmountSettlement",
    "userEmailAddress"
})
@XmlRootElement(name = "fetchTransitionalReliefAmountforSettlementRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
public class FetchTransitionalReliefAmountforSettlementRequest {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected TransitionalReliefAmountSettlement transitionalReliefAmountSettlement;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String userEmailAddress;

    /**
     * Gets the value of the transitionalReliefAmountSettlement property.
     * 
     * @return
     *     possible object is
     *     {@link TransitionalReliefAmountSettlement }
     *     
     */
    public TransitionalReliefAmountSettlement getTransitionalReliefAmountSettlement() {
        return transitionalReliefAmountSettlement;
    }

    /**
     * Sets the value of the transitionalReliefAmountSettlement property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransitionalReliefAmountSettlement }
     *     
     */
    public void setTransitionalReliefAmountSettlement(TransitionalReliefAmountSettlement value) {
        this.transitionalReliefAmountSettlement = value;
    }

    /**
     * Gets the value of the userEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    /**
     * Sets the value of the userEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserEmailAddress(String value) {
        this.userEmailAddress = value;
    }

}
