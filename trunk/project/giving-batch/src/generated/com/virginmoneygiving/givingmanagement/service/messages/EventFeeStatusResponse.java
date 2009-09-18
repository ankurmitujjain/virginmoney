
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
 *         &lt;element name="onlineFeePayOpenInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "onlineFeePayOpenInd"
})
@XmlRootElement(name = "EventFeeStatusResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class EventFeeStatusResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String onlineFeePayOpenInd;

    /**
     * Gets the value of the onlineFeePayOpenInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnlineFeePayOpenInd() {
        return onlineFeePayOpenInd;
    }

    /**
     * Sets the value of the onlineFeePayOpenInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnlineFeePayOpenInd(String value) {
        this.onlineFeePayOpenInd = value;
    }

}
