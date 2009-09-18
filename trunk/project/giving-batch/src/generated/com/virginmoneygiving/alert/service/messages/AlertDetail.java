
package com.virginmoneygiving.alert.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AlertDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AlertDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Content" type="{http://www.virginmoney.com/type/alert/2008/07}AlertContent"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlertDetail", namespace = "http://www.virginmoney.com/type/alert/2008/07", propOrder = {
    "content"
})
public class AlertDetail {

    @XmlElement(name = "Content", required = true)
    protected AlertContent content;

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link AlertContent }
     *     
     */
    public AlertContent getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertContent }
     *     
     */
    public void setContent(AlertContent value) {
        this.content = value;
    }

}
