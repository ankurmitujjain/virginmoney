
package com.logicgroup.extended.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Apacs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Apacs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HeadersCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TerminalDial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TerminalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TerminalType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageSeqNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Apacs", propOrder = {
    "responseCode",
    "headersCode",
    "terminalDial",
    "terminalId",
    "terminalType",
    "message",
    "messageType",
    "messageSeqNumber"
})
public class Apacs {

    @XmlElement(name = "ResponseCode")
    protected String responseCode;
    @XmlElement(name = "HeadersCode")
    protected String headersCode;
    @XmlElement(name = "TerminalDial")
    protected String terminalDial;
    @XmlElement(name = "TerminalId")
    protected String terminalId;
    @XmlElement(name = "TerminalType")
    protected String terminalType;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "MessageType")
    protected String messageType;
    @XmlElement(name = "MessageSeqNumber")
    protected String messageSeqNumber;

    /**
     * Gets the value of the responseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseCode(String value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the headersCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeadersCode() {
        return headersCode;
    }

    /**
     * Sets the value of the headersCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeadersCode(String value) {
        this.headersCode = value;
    }

    /**
     * Gets the value of the terminalDial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalDial() {
        return terminalDial;
    }

    /**
     * Sets the value of the terminalDial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalDial(String value) {
        this.terminalDial = value;
    }

    /**
     * Gets the value of the terminalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * Sets the value of the terminalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalId(String value) {
        this.terminalId = value;
    }

    /**
     * Gets the value of the terminalType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalType() {
        return terminalType;
    }

    /**
     * Sets the value of the terminalType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalType(String value) {
        this.terminalType = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the messageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * Sets the value of the messageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageType(String value) {
        this.messageType = value;
    }

    /**
     * Gets the value of the messageSeqNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageSeqNumber() {
        return messageSeqNumber;
    }

    /**
     * Sets the value of the messageSeqNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageSeqNumber(String value) {
        this.messageSeqNumber = value;
    }

}
