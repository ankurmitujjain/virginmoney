
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
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vmgReference" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="postcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "name",
    "vmgReference",
    "postcode"
})
@XmlRootElement(name = "fundraiserSearchCriteriaRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
public class FundraiserSearchCriteriaRequest {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String name;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true, type = Long.class, nillable = true)
    protected Long vmgReference;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected String postcode;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the postcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostcode(String value) {
        this.postcode = value;
    }

}
