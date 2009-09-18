
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updateSuccessful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "updateSuccessful"
})
@XmlRootElement(name = "updateRegularDonationResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/")
public class UpdateRegularDonationResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/")
    protected boolean updateSuccessful;

    /**
     * Gets the value of the updateSuccessful property.
     * 
     */
    public boolean isUpdateSuccessful() {
        return updateSuccessful;
    }

    /**
     * Sets the value of the updateSuccessful property.
     * 
     */
    public void setUpdateSuccessful(boolean value) {
        this.updateSuccessful = value;
    }

}