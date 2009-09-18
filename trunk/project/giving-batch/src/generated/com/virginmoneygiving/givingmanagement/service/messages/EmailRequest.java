
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
 *         &lt;element name="templatePath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.virginmoneygiving.com/type/giving-management/operations/}Email"/>
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
    "templatePath",
    "email"
})
@XmlRootElement(name = "emailRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
public class EmailRequest {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String templatePath;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected Email email;

    /**
     * Gets the value of the templatePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * Sets the value of the templatePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplatePath(String value) {
        this.templatePath = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link Email }
     *     
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link Email }
     *     
     */
    public void setEmail(Email value) {
        this.email = value;
    }

}
