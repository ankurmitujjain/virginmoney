
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceFundraiserGroupPageDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiserGroupPageDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pageId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pageTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fundraiserGroupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pageUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiserGroupPageDetail", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "pageId",
    "pageTitle",
    "fundraiserId",
    "fundraiserGroupId",
    "pageUrl"
})
public class ServiceFundraiserGroupPageDetail {

    protected long pageId;
    @XmlElement(required = true, nillable = true)
    protected String pageTitle;
    protected long fundraiserId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserGroupId;
    @XmlElement(required = true, nillable = true)
    protected String pageUrl;

    /**
     * Gets the value of the pageId property.
     * 
     */
    public long getPageId() {
        return pageId;
    }

    /**
     * Sets the value of the pageId property.
     * 
     */
    public void setPageId(long value) {
        this.pageId = value;
    }

    /**
     * Gets the value of the pageTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageTitle() {
        return pageTitle;
    }

    /**
     * Sets the value of the pageTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageTitle(String value) {
        this.pageTitle = value;
    }

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
     * Gets the value of the pageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * Sets the value of the pageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageUrl(String value) {
        this.pageUrl = value;
    }

}
