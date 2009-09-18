
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Email complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Email">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="emailHeader" type="{http://www.virginmoneygiving.com/type/giving-management/operations/}EmailHeader"/>
 *         &lt;element name="emailBodyParameters" type="{http://www.virginmoneygiving.com/type/giving-management/operations/}EmailBodyParameters" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Email", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", propOrder = {
    "emailHeader",
    "emailBodyParameters"
})
public class Email {

    @XmlElement(required = true)
    protected EmailHeader emailHeader;
    @XmlElement(required = true)
    protected List<EmailBodyParameters> emailBodyParameters;

    /**
     * Gets the value of the emailHeader property.
     * 
     * @return
     *     possible object is
     *     {@link EmailHeader }
     *     
     */
    public EmailHeader getEmailHeader() {
        return emailHeader;
    }

    /**
     * Sets the value of the emailHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailHeader }
     *     
     */
    public void setEmailHeader(EmailHeader value) {
        this.emailHeader = value;
    }

    /**
     * Gets the value of the emailBodyParameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emailBodyParameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmailBodyParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmailBodyParameters }
     * 
     * 
     */
    public List<EmailBodyParameters> getEmailBodyParameters() {
        if (emailBodyParameters == null) {
            emailBodyParameters = new ArrayList<EmailBodyParameters>();
        }
        return this.emailBodyParameters;
    }

}
