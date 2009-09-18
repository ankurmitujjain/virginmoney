
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceTelephoneNumber complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceTelephoneNumber">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="telephoneNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lineTypeInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceTelephoneNumber", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "telephoneNo",
    "lineTypeInd"
})
public class ServiceTelephoneNumber {

    @XmlElement(required = true)
    protected String telephoneNo;
    @XmlElement(required = true)
    protected String lineTypeInd;

    /**
     * Gets the value of the telephoneNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephoneNo() {
        return telephoneNo;
    }

    /**
     * Sets the value of the telephoneNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephoneNo(String value) {
        this.telephoneNo = value;
    }

    /**
     * Gets the value of the lineTypeInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineTypeInd() {
        return lineTypeInd;
    }

    /**
     * Sets the value of the lineTypeInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineTypeInd(String value) {
        this.lineTypeInd = value;
    }

}
