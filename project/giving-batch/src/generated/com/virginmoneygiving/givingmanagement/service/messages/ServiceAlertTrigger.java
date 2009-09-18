
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceAlertTrigger complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceAlertTrigger">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alertId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="thresholdAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="thresholdDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="thresholdFrequency" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="charity" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceCharity"/>
 *         &lt;element name="alertType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alertUsers" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAdministrator" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceAlertTrigger", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "alertId",
    "thresholdAmount",
    "thresholdDuration",
    "thresholdFrequency",
    "charity",
    "alertType",
    "alertUsers"
})
public class ServiceAlertTrigger {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long alertId;
    @XmlElement(required = true)
    protected BigDecimal thresholdAmount;
    protected int thresholdDuration;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer thresholdFrequency;
    @XmlElement(required = true, nillable = true)
    protected ServiceCharity charity;
    @XmlElement(required = true, nillable = true)
    protected String alertType;
    @XmlElement(nillable = true)
    protected List<ServiceAdministrator> alertUsers;

    /**
     * Gets the value of the alertId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAlertId() {
        return alertId;
    }

    /**
     * Sets the value of the alertId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAlertId(Long value) {
        this.alertId = value;
    }

    /**
     * Gets the value of the thresholdAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    /**
     * Sets the value of the thresholdAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setThresholdAmount(BigDecimal value) {
        this.thresholdAmount = value;
    }

    /**
     * Gets the value of the thresholdDuration property.
     * 
     */
    public int getThresholdDuration() {
        return thresholdDuration;
    }

    /**
     * Sets the value of the thresholdDuration property.
     * 
     */
    public void setThresholdDuration(int value) {
        this.thresholdDuration = value;
    }

    /**
     * Gets the value of the thresholdFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getThresholdFrequency() {
        return thresholdFrequency;
    }

    /**
     * Sets the value of the thresholdFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setThresholdFrequency(Integer value) {
        this.thresholdFrequency = value;
    }

    /**
     * Gets the value of the charity property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCharity }
     *     
     */
    public ServiceCharity getCharity() {
        return charity;
    }

    /**
     * Sets the value of the charity property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCharity }
     *     
     */
    public void setCharity(ServiceCharity value) {
        this.charity = value;
    }

    /**
     * Gets the value of the alertType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlertType() {
        return alertType;
    }

    /**
     * Sets the value of the alertType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlertType(String value) {
        this.alertType = value;
    }

    /**
     * Gets the value of the alertUsers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alertUsers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlertUsers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceAdministrator }
     * 
     * 
     */
    public List<ServiceAdministrator> getAlertUsers() {
        if (alertUsers == null) {
            alertUsers = new ArrayList<ServiceAdministrator>();
        }
        return this.alertUsers;
    }

}
