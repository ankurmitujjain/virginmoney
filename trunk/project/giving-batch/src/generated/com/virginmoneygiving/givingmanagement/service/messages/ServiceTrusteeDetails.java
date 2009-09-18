
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceTrusteeDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceTrusteeDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="trusteeType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="trusteeStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="personDetails" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServicePersonDetails"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceTrusteeDetails", propOrder = {
    "id",
    "trusteeType",
    "trusteeStatus",
    "charityId",
    "personDetails"
})
public class ServiceTrusteeDetails {

    protected long id;
    @XmlElement(required = true)
    protected String trusteeType;
    @XmlElement(required = true)
    protected String trusteeStatus;
    protected long charityId;
    @XmlElement(required = true)
    protected ServicePersonDetails personDetails;

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
     * Gets the value of the trusteeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrusteeType() {
        return trusteeType;
    }

    /**
     * Sets the value of the trusteeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrusteeType(String value) {
        this.trusteeType = value;
    }

    /**
     * Gets the value of the trusteeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrusteeStatus() {
        return trusteeStatus;
    }

    /**
     * Sets the value of the trusteeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrusteeStatus(String value) {
        this.trusteeStatus = value;
    }

    /**
     * Gets the value of the charityId property.
     * 
     */
    public long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     */
    public void setCharityId(long value) {
        this.charityId = value;
    }

    /**
     * Gets the value of the personDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePersonDetails }
     *     
     */
    public ServicePersonDetails getPersonDetails() {
        return personDetails;
    }

    /**
     * Sets the value of the personDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePersonDetails }
     *     
     */
    public void setPersonDetails(ServicePersonDetails value) {
        this.personDetails = value;
    }

}
