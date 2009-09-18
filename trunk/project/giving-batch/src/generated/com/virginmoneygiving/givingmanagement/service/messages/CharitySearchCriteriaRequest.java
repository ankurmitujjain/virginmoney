
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
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vmgReference" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="postCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "charityName",
    "vmgReference",
    "postCode",
    "charityNumber"
})
@XmlRootElement(name = "charitySearchCriteriaRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
public class CharitySearchCriteriaRequest {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String charityName;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true, type = Long.class, nillable = true)
    protected Long vmgReference;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String postCode;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String charityNumber;

    /**
     * Gets the value of the charityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the value of the charityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityName(String value) {
        this.charityName = value;
    }

    /**
     * Gets the value of the vmgReference property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVmgReference() {
        return vmgReference;
    }

    /**
     * Sets the value of the vmgReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVmgReference(Long value) {
        this.vmgReference = value;
    }

    /**
     * Gets the value of the postCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostCode(String value) {
        this.postCode = value;
    }

    /**
     * Gets the value of the charityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityNumber() {
        return charityNumber;
    }

    /**
     * Sets the value of the charityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityNumber(String value) {
        this.charityNumber = value;
    }

}
