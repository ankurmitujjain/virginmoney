
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCharityHomePageRegistration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharityHomePageRegistration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="body" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pageStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adminForename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userEmailId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updatedUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updatedIpAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imageStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="videoGaleryStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharityHomePageRegistration", propOrder = {
    "id",
    "title",
    "body",
    "status",
    "charityId",
    "pageStatus",
    "pageType",
    "createdUser",
    "adminForename",
    "userEmailId",
    "updatedUser",
    "updatedIpAddress",
    "imageStatus",
    "videoGaleryStatus"
})
public class ServiceCharityHomePageRegistration {

    protected long id;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String body;
    @XmlElement(required = true)
    protected String status;
    protected long charityId;
    @XmlElement(required = true)
    protected String pageStatus;
    @XmlElement(required = true)
    protected String pageType;
    @XmlElement(required = true)
    protected String createdUser;
    @XmlElement(required = true)
    protected String adminForename;
    @XmlElement(required = true)
    protected String userEmailId;
    @XmlElement(required = true)
    protected String updatedUser;
    @XmlElement(required = true)
    protected String updatedIpAddress;
    @XmlElement(required = true)
    protected String imageStatus;
    @XmlElement(required = true)
    protected String videoGaleryStatus;

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
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBody(String value) {
        this.body = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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
     * Gets the value of the pageStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageStatus() {
        return pageStatus;
    }

    /**
     * Sets the value of the pageStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageStatus(String value) {
        this.pageStatus = value;
    }

    /**
     * Gets the value of the pageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageType() {
        return pageType;
    }

    /**
     * Sets the value of the pageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageType(String value) {
        this.pageType = value;
    }

    /**
     * Gets the value of the createdUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * Sets the value of the createdUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedUser(String value) {
        this.createdUser = value;
    }

    /**
     * Gets the value of the adminForename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminForename() {
        return adminForename;
    }

    /**
     * Sets the value of the adminForename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminForename(String value) {
        this.adminForename = value;
    }

    /**
     * Gets the value of the userEmailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserEmailId() {
        return userEmailId;
    }

    /**
     * Sets the value of the userEmailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserEmailId(String value) {
        this.userEmailId = value;
    }

    /**
     * Gets the value of the updatedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * Sets the value of the updatedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedUser(String value) {
        this.updatedUser = value;
    }

    /**
     * Gets the value of the updatedIpAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedIpAddress() {
        return updatedIpAddress;
    }

    /**
     * Sets the value of the updatedIpAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedIpAddress(String value) {
        this.updatedIpAddress = value;
    }

    /**
     * Gets the value of the imageStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageStatus() {
        return imageStatus;
    }

    /**
     * Sets the value of the imageStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageStatus(String value) {
        this.imageStatus = value;
    }

    /**
     * Gets the value of the videoGaleryStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoGaleryStatus() {
        return videoGaleryStatus;
    }

    /**
     * Sets the value of the videoGaleryStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoGaleryStatus(String value) {
        this.videoGaleryStatus = value;
    }

}
