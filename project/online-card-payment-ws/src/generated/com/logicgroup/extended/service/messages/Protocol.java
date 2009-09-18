
package com.logicgroup.extended.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Protocol complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Protocol">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProtocolName" type="{http://secure-payment-processing.com/}ProtocolNameEnum"/>
 *         &lt;element name="ProtocolResult" type="{http://secure-payment-processing.com/}ProtocolResultEnum"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Protocol", propOrder = {
    "protocolName",
    "protocolResult"
})
public class Protocol {

    @XmlElement(name = "ProtocolName", required = true)
    protected ProtocolNameEnum protocolName;
    @XmlElement(name = "ProtocolResult", required = true)
    protected ProtocolResultEnum protocolResult;

    /**
     * Gets the value of the protocolName property.
     * 
     * @return
     *     possible object is
     *     {@link ProtocolNameEnum }
     *     
     */
    public ProtocolNameEnum getProtocolName() {
        return protocolName;
    }

    /**
     * Sets the value of the protocolName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProtocolNameEnum }
     *     
     */
    public void setProtocolName(ProtocolNameEnum value) {
        this.protocolName = value;
    }

    /**
     * Gets the value of the protocolResult property.
     * 
     * @return
     *     possible object is
     *     {@link ProtocolResultEnum }
     *     
     */
    public ProtocolResultEnum getProtocolResult() {
        return protocolResult;
    }

    /**
     * Sets the value of the protocolResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProtocolResultEnum }
     *     
     */
    public void setProtocolResult(ProtocolResultEnum value) {
        this.protocolResult = value;
    }

}
