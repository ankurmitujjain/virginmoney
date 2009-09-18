
package com.virginmoneygiving.paymentmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for multiCharityDivision complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="multiCharityDivision">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="percentages" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "multiCharityDivision", propOrder = {
    "charityReference",
    "percentages"
})
public class MultiCharityDivision {

    @XmlElement(required = true)
    protected String charityReference;
    protected int percentages;

    /**
     * Gets the value of the charityReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityReference() {
        return charityReference;
    }

    /**
     * Sets the value of the charityReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityReference(String value) {
        this.charityReference = value;
    }

    /**
     * Gets the value of the percentages property.
     * 
     */
    public int getPercentages() {
        return percentages;
    }

    /**
     * Sets the value of the percentages property.
     * 
     */
    public void setPercentages(int value) {
        this.percentages = value;
    }

}
