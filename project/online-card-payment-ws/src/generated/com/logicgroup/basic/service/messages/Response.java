
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Requester" type="{http://secure-payment-processing.com/}Requester" minOccurs="0"/>
 *         &lt;element name="Error" type="{http://secure-payment-processing.com/}SolveError" minOccurs="0"/>
 *         &lt;element name="ResponseResult" type="{http://secure-payment-processing.com/}ResponseLabelEnum"/>
 *         &lt;element name="ResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReplyFormat" type="{http://secure-payment-processing.com/}ReplyFormatEnum"/>
 *         &lt;element name="Metrics" type="{http://secure-payment-processing.com/}Metrics" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
    "requester",
    "error",
    "responseResult",
    "responseCode",
    "reference",
    "replyFormat",
    "metrics",
    "description"
})
@XmlSeeAlso({
    AuthorisationResponse.class,
    ValidationResponse.class
})
public class Response {

    @XmlElement(name = "Requester")
    protected Requester requester;
    @XmlElement(name = "Error")
    protected SolveError error;
    @XmlElement(name = "ResponseResult", required = true)
    protected ResponseLabelEnum responseResult;
    @XmlElement(name = "ResponseCode")
    protected String responseCode;
    @XmlElement(name = "Reference")
    protected String reference;
    @XmlElement(name = "ReplyFormat", required = true)
    protected ReplyFormatEnum replyFormat;
    @XmlElement(name = "Metrics")
    protected Metrics metrics;
    @XmlElement(name = "Description")
    protected String description;

    /**
     * Gets the value of the requester property.
     * 
     * @return
     *     possible object is
     *     {@link Requester }
     *     
     */
    public Requester getRequester() {
        return requester;
    }

    /**
     * Sets the value of the requester property.
     * 
     * @param value
     *     allowed object is
     *     {@link Requester }
     *     
     */
    public void setRequester(Requester value) {
        this.requester = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link SolveError }
     *     
     */
    public SolveError getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link SolveError }
     *     
     */
    public void setError(SolveError value) {
        this.error = value;
    }

    /**
     * Gets the value of the responseResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseLabelEnum }
     *     
     */
    public ResponseLabelEnum getResponseResult() {
        return responseResult;
    }

    /**
     * Sets the value of the responseResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseLabelEnum }
     *     
     */
    public void setResponseResult(ResponseLabelEnum value) {
        this.responseResult = value;
    }

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
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Gets the value of the replyFormat property.
     * 
     * @return
     *     possible object is
     *     {@link ReplyFormatEnum }
     *     
     */
    public ReplyFormatEnum getReplyFormat() {
        return replyFormat;
    }

    /**
     * Sets the value of the replyFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReplyFormatEnum }
     *     
     */
    public void setReplyFormat(ReplyFormatEnum value) {
        this.replyFormat = value;
    }

    /**
     * Gets the value of the metrics property.
     * 
     * @return
     *     possible object is
     *     {@link Metrics }
     *     
     */
    public Metrics getMetrics() {
        return metrics;
    }

    /**
     * Sets the value of the metrics property.
     * 
     * @param value
     *     allowed object is
     *     {@link Metrics }
     *     
     */
    public void setMetrics(Metrics value) {
        this.metrics = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
