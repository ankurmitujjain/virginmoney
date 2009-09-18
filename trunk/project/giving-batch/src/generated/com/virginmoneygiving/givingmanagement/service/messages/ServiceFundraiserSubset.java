
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceFundraiserSubset complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiserSubset">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fundraiserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fundraiserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiserSubset", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", propOrder = {
    "fundraiserId",
    "fundraiserName",
    "fundraiserUrl",
    "fundraiserEmailAddress"
})
public class ServiceFundraiserSubset {

    protected long fundraiserId;
    @XmlElement(required = true)
    protected String fundraiserName;
    @XmlElement(required = true)
    protected String fundraiserUrl;
    @XmlElement(required = true)
    protected String fundraiserEmailAddress;

    /**
     * Gets the value of the fundraiserId property.
     * 
     */
    public long getFundraiserId() {
        return fundraiserId;
    }

    /**
     * Sets the value of the fundraiserId property.
     * 
     */
    public void setFundraiserId(long value) {
        this.fundraiserId = value;
    }

    /**
     * Gets the value of the fundraiserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserName() {
        return fundraiserName;
    }

    /**
     * Sets the value of the fundraiserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserName(String value) {
        this.fundraiserName = value;
    }

    /**
     * Gets the value of the fundraiserUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserUrl() {
        return fundraiserUrl;
    }

    /**
     * Sets the value of the fundraiserUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserUrl(String value) {
        this.fundraiserUrl = value;
    }

    /**
     * Gets the value of the fundraiserEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserEmailAddress() {
        return fundraiserEmailAddress;
    }

    /**
     * Sets the value of the fundraiserEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserEmailAddress(String value) {
        this.fundraiserEmailAddress = value;
    }

}
