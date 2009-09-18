
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceModuleData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceModuleData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offlineRegModuleId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="module" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceModule"/>
 *         &lt;element name="offlineRegStatus" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceOfflineRegStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceModuleData", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "offlineRegModuleId",
    "module",
    "offlineRegStatus"
})
public class ServiceModuleData {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long offlineRegModuleId;
    @XmlElement(required = true, nillable = true)
    protected ServiceModule module;
    @XmlElement(required = true, nillable = true)
    protected ServiceOfflineRegStatus offlineRegStatus;

    /**
     * Gets the value of the offlineRegModuleId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOfflineRegModuleId() {
        return offlineRegModuleId;
    }

    /**
     * Sets the value of the offlineRegModuleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOfflineRegModuleId(Long value) {
        this.offlineRegModuleId = value;
    }

    /**
     * Gets the value of the module property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceModule }
     *     
     */
    public ServiceModule getModule() {
        return module;
    }

    /**
     * Sets the value of the module property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceModule }
     *     
     */
    public void setModule(ServiceModule value) {
        this.module = value;
    }

    /**
     * Gets the value of the offlineRegStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceOfflineRegStatus }
     *     
     */
    public ServiceOfflineRegStatus getOfflineRegStatus() {
        return offlineRegStatus;
    }

    /**
     * Sets the value of the offlineRegStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceOfflineRegStatus }
     *     
     */
    public void setOfflineRegStatus(ServiceOfflineRegStatus value) {
        this.offlineRegStatus = value;
    }

}
