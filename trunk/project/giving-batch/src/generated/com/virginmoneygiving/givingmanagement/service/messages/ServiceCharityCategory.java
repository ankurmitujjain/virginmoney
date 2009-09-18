
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCharityCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharityCategory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="categoryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryImageLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subcategoryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subcategoryTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subcategoryImageLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharityCategory", propOrder = {
    "id",
    "categoryCode",
    "categoryTitle",
    "categoryImageLocation",
    "subcategoryCode",
    "subcategoryTitle",
    "subcategoryImageLocation"
})
public class ServiceCharityCategory {

    protected long id;
    @XmlElement(required = true)
    protected String categoryCode;
    @XmlElement(required = true)
    protected String categoryTitle;
    @XmlElement(required = true)
    protected String categoryImageLocation;
    @XmlElement(required = true)
    protected String subcategoryCode;
    @XmlElement(required = true)
    protected String subcategoryTitle;
    @XmlElement(required = true)
    protected String subcategoryImageLocation;

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
     * Gets the value of the categoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * Sets the value of the categoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode(String value) {
        this.categoryCode = value;
    }

    /**
     * Gets the value of the categoryTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryTitle() {
        return categoryTitle;
    }

    /**
     * Sets the value of the categoryTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryTitle(String value) {
        this.categoryTitle = value;
    }

    /**
     * Gets the value of the categoryImageLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryImageLocation() {
        return categoryImageLocation;
    }

    /**
     * Sets the value of the categoryImageLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryImageLocation(String value) {
        this.categoryImageLocation = value;
    }

    /**
     * Gets the value of the subcategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubcategoryCode() {
        return subcategoryCode;
    }

    /**
     * Sets the value of the subcategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubcategoryCode(String value) {
        this.subcategoryCode = value;
    }

    /**
     * Gets the value of the subcategoryTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubcategoryTitle() {
        return subcategoryTitle;
    }

    /**
     * Sets the value of the subcategoryTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubcategoryTitle(String value) {
        this.subcategoryTitle = value;
    }

    /**
     * Gets the value of the subcategoryImageLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubcategoryImageLocation() {
        return subcategoryImageLocation;
    }

    /**
     * Sets the value of the subcategoryImageLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubcategoryImageLocation(String value) {
        this.subcategoryImageLocation = value;
    }

}
