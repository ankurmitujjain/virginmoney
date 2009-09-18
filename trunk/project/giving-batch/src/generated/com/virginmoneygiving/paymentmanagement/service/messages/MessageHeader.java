
package com.virginmoneygiving.paymentmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for MessageHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SourceSystemId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SourceSubSystemId" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="BrandReference" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="SystemTransactionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IPAddress" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="ProcessName" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageHeader", namespace = "http://www.virginmoneygiving.com/type/header/", propOrder = {
    "sourceSystemId",
    "sourceSubSystemId",
    "brandReference",
    "systemTransactionID",
    "ipAddress",
    "sessionID",
    "processName",
    "userName"
})
public class MessageHeader {

    @XmlElement(name = "SourceSystemId", required = true)
    protected String sourceSystemId;
    @XmlElement(name = "SourceSubSystemId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sourceSubSystemId;
    @XmlElement(name = "BrandReference")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String brandReference;
    @XmlElement(name = "SystemTransactionID", required = true)
    protected String systemTransactionID;
    @XmlElement(name = "IPAddress", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String ipAddress;
    @XmlElement(name = "SessionID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sessionID;
    @XmlElement(name = "ProcessName")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String processName;
    @XmlElement(name = "UserName", required = true)
    protected String userName;

    /**
     * Gets the value of the sourceSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSystemId() {
        return sourceSystemId;
    }

    /**
     * Sets the value of the sourceSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSystemId(String value) {
        this.sourceSystemId = value;
    }

    /**
     * Gets the value of the sourceSubSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSubSystemId() {
        return sourceSubSystemId;
    }

    /**
     * Sets the value of the sourceSubSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSubSystemId(String value) {
        this.sourceSubSystemId = value;
    }

    /**
     * Gets the value of the brandReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandReference() {
        return brandReference;
    }

    /**
     * Sets the value of the brandReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandReference(String value) {
        this.brandReference = value;
    }

    /**
     * Gets the value of the systemTransactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemTransactionID() {
        return systemTransactionID;
    }

    /**
     * Sets the value of the systemTransactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemTransactionID(String value) {
        this.systemTransactionID = value;
    }

    /**
     * Gets the value of the ipAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPAddress() {
        return ipAddress;
    }

    /**
     * Sets the value of the ipAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPAddress(String value) {
        this.ipAddress = value;
    }

    /**
     * Gets the value of the sessionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionID(String value) {
        this.sessionID = value;
    }

    /**
     * Gets the value of the processName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * Sets the value of the processName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessName(String value) {
        this.processName = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

}
