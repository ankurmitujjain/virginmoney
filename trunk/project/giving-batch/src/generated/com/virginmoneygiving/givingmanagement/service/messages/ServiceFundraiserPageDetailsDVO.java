
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceFundraiserPageDetailsDVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiserPageDetailsDVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="event" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FundraisingActivity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageCreated" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amountRaised" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldLabel5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldvalue5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiserPageDetailsDVO", propOrder = {
    "id",
    "name",
    "pageTitle",
    "event",
    "fundraisingActivity",
    "pageCreated",
    "pageType",
    "pageStatus",
    "targetAmount",
    "amountRaised",
    "customFieldLabel1",
    "customFieldLabel2",
    "customFieldLabel3",
    "customFieldLabel4",
    "customFieldLabel5",
    "customFieldvalue1",
    "customFieldvalue2",
    "customFieldvalue3",
    "customFieldvalue4",
    "customFieldvalue5"
})
public class ServiceFundraiserPageDetailsDVO {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String pageTitle;
    @XmlElement(required = true)
    protected String event;
    @XmlElement(name = "FundraisingActivity", required = true)
    protected String fundraisingActivity;
    @XmlElement(required = true)
    protected String pageCreated;
    @XmlElement(required = true)
    protected String pageType;
    @XmlElement(required = true)
    protected String pageStatus;
    @XmlElement(required = true)
    protected String targetAmount;
    @XmlElement(required = true)
    protected String amountRaised;
    @XmlElement(required = true)
    protected String customFieldLabel1;
    @XmlElement(required = true)
    protected String customFieldLabel2;
    @XmlElement(required = true)
    protected String customFieldLabel3;
    @XmlElement(required = true)
    protected String customFieldLabel4;
    @XmlElement(required = true)
    protected String customFieldLabel5;
    @XmlElement(required = true)
    protected String customFieldvalue1;
    @XmlElement(required = true)
    protected String customFieldvalue2;
    @XmlElement(required = true)
    protected String customFieldvalue3;
    @XmlElement(required = true)
    protected String customFieldvalue4;
    @XmlElement(required = true)
    protected String customFieldvalue5;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
     * Gets the value of the event property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvent() {
        return event;
    }

    /**
     * Sets the value of the event property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvent(String value) {
        this.event = value;
    }

    /**
     * Gets the value of the fundraisingActivity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraisingActivity() {
        return fundraisingActivity;
    }

    /**
     * Sets the value of the fundraisingActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraisingActivity(String value) {
        this.fundraisingActivity = value;
    }

    /**
     * Gets the value of the pageCreated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageCreated() {
        return pageCreated;
    }

    /**
     * Sets the value of the pageCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageCreated(String value) {
        this.pageCreated = value;
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
     * Gets the value of the targetAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetAmount() {
        return targetAmount;
    }

    /**
     * Sets the value of the targetAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetAmount(String value) {
        this.targetAmount = value;
    }

    /**
     * Gets the value of the amountRaised property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmountRaised() {
        return amountRaised;
    }

    /**
     * Sets the value of the amountRaised property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmountRaised(String value) {
        this.amountRaised = value;
    }

    /**
     * Gets the value of the customFieldLabel1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel1() {
        return customFieldLabel1;
    }

    /**
     * Sets the value of the customFieldLabel1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel1(String value) {
        this.customFieldLabel1 = value;
    }

    /**
     * Gets the value of the customFieldLabel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel2() {
        return customFieldLabel2;
    }

    /**
     * Sets the value of the customFieldLabel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel2(String value) {
        this.customFieldLabel2 = value;
    }

    /**
     * Gets the value of the customFieldLabel3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel3() {
        return customFieldLabel3;
    }

    /**
     * Sets the value of the customFieldLabel3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel3(String value) {
        this.customFieldLabel3 = value;
    }

    /**
     * Gets the value of the customFieldLabel4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel4() {
        return customFieldLabel4;
    }

    /**
     * Sets the value of the customFieldLabel4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel4(String value) {
        this.customFieldLabel4 = value;
    }

    /**
     * Gets the value of the customFieldLabel5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldLabel5() {
        return customFieldLabel5;
    }

    /**
     * Sets the value of the customFieldLabel5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldLabel5(String value) {
        this.customFieldLabel5 = value;
    }

    /**
     * Gets the value of the customFieldvalue1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue1() {
        return customFieldvalue1;
    }

    /**
     * Sets the value of the customFieldvalue1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue1(String value) {
        this.customFieldvalue1 = value;
    }

    /**
     * Gets the value of the customFieldvalue2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue2() {
        return customFieldvalue2;
    }

    /**
     * Sets the value of the customFieldvalue2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue2(String value) {
        this.customFieldvalue2 = value;
    }

    /**
     * Gets the value of the customFieldvalue3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue3() {
        return customFieldvalue3;
    }

    /**
     * Sets the value of the customFieldvalue3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue3(String value) {
        this.customFieldvalue3 = value;
    }

    /**
     * Gets the value of the customFieldvalue4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue4() {
        return customFieldvalue4;
    }

    /**
     * Sets the value of the customFieldvalue4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue4(String value) {
        this.customFieldvalue4 = value;
    }

    /**
     * Gets the value of the customFieldvalue5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldvalue5() {
        return customFieldvalue5;
    }

    /**
     * Sets the value of the customFieldvalue5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldvalue5(String value) {
        this.customFieldvalue5 = value;
    }

}
