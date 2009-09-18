
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCharityStatusInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharityStatusInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activationPostponed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharityStatusInfo", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "charityId",
    "charityName",
    "charityStatus",
    "activationPostponed"
})
public class ServiceCharityStatusInfo {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityId;
    @XmlElement(required = true, nillable = true)
    protected String charityName;
    @XmlElement(required = true, nillable = true)
    protected String charityStatus;
    @XmlElement(required = true, nillable = true)
    protected String activationPostponed;

    /**
     * Gets the value of the charityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCharityId(Long value) {
        this.charityId = value;
    }

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
     * Gets the value of the charityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityStatus() {
        return charityStatus;
    }

    /**
     * Sets the value of the charityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityStatus(String value) {
        this.charityStatus = value;
    }

    /**
     * Gets the value of the activationPostponed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivationPostponed() {
        return activationPostponed;
    }

    /**
     * Sets the value of the activationPostponed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivationPostponed(String value) {
        this.activationPostponed = value;
    }

}
