
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CharityHMRCDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CharityHMRCDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="hmrcRefNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharityHMRCDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", propOrder = {
    "id",
    "hmrcRefNo"
})
public class CharityHMRCDetails {

    protected long id;
    @XmlElement(required = true)
    protected String hmrcRefNo;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the hmrcRefNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHmrcRefNo() {
        return hmrcRefNo;
    }

    /**
     * Sets the value of the hmrcRefNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHmrcRefNo(String value) {
        this.hmrcRefNo = value;
    }

}
