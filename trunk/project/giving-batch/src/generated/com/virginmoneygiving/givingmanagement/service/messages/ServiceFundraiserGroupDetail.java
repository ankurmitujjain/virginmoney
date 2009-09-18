
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceFundraiserGroupDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiserGroupDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fundraiserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserGroupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserGroupType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="groupMemberId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiserGroupDetail", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "fundraiserId",
    "name",
    "fundraiserGroupId",
    "description",
    "url",
    "fundraiserGroupType",
    "groupMemberId"
})
public class ServiceFundraiserGroupDetail {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserId;
    @XmlElement(required = true, nillable = true)
    protected String name;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserGroupId;
    @XmlElement(required = true, nillable = true)
    protected String description;
    @XmlElement(name = "URL", required = true, nillable = true)
    protected String url;
    @XmlElement(required = true, nillable = true)
    protected String fundraiserGroupType;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long groupMemberId;

    /**
     * Gets the value of the fundraiserId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserId() {
        return fundraiserId;
    }

    /**
     * Sets the value of the fundraiserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserId(Long value) {
        this.fundraiserId = value;
    }

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
     * Gets the value of the fundraiserGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserGroupId() {
        return fundraiserGroupId;
    }

    /**
     * Sets the value of the fundraiserGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserGroupId(Long value) {
        this.fundraiserGroupId = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURL(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the fundraiserGroupType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserGroupType() {
        return fundraiserGroupType;
    }

    /**
     * Sets the value of the fundraiserGroupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserGroupType(String value) {
        this.fundraiserGroupType = value;
    }

    /**
     * Gets the value of the groupMemberId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGroupMemberId() {
        return groupMemberId;
    }

    /**
     * Sets the value of the groupMemberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGroupMemberId(Long value) {
        this.groupMemberId = value;
    }

}
