
package com.logicgroup.extended.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Acquirer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Acquirer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcquirerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcquirerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Acquirer", propOrder = {
    "acquirerId",
    "acquirerName"
})
public class Acquirer {

    @XmlElement(name = "AcquirerId")
    protected String acquirerId;
    @XmlElement(name = "AcquirerName")
    protected String acquirerName;

    /**
     * Gets the value of the acquirerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcquirerId() {
        return acquirerId;
    }

    /**
     * Sets the value of the acquirerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcquirerId(String value) {
        this.acquirerId = value;
    }

    /**
     * Gets the value of the acquirerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcquirerName() {
        return acquirerName;
    }

    /**
     * Sets the value of the acquirerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcquirerName(String value) {
        this.acquirerName = value;
    }

}
