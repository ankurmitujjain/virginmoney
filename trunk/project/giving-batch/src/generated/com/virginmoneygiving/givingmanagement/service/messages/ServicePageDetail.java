
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServicePageDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServicePageDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pageId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fundraiserGroupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServicePageDetail", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "pageId",
    "fundraiserGroupId"
})
public class ServicePageDetail {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long pageId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserGroupId;

    /**
     * Gets the value of the pageId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPageId() {
        return pageId;
    }

    /**
     * Sets the value of the pageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPageId(Long value) {
        this.pageId = value;
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

}
