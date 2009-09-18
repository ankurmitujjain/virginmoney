
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
 *         &lt;element name="CancelEventStatusInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="basicReponse" type="{http://www.virginmoneygiving.com/type/giving-management/common/}BasicResponse"/>
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
    "cancelEventStatusInd",
    "basicReponse"
})
@XmlRootElement(name = "CancelEventResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class CancelEventResponse {

    @XmlElement(name = "CancelEventStatusInd", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String cancelEventStatusInd;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the cancelEventStatusInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelEventStatusInd() {
        return cancelEventStatusInd;
    }

    /**
     * Sets the value of the cancelEventStatusInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelEventStatusInd(String value) {
        this.cancelEventStatusInd = value;
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
