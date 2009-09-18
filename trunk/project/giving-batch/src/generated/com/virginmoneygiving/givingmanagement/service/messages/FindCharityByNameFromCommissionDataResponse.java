
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="serviceCommissionCharityDetails" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceCommissionCharityDetails" maxOccurs="unbounded"/>
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
    "serviceCommissionCharityDetails"
})
@XmlRootElement(name = "findCharityByNameFromCommissionDataResponse")
public class FindCharityByNameFromCommissionDataResponse {

    @XmlElement(required = true)
    protected List<ServiceCommissionCharityDetails> serviceCommissionCharityDetails;

    /**
     * Gets the value of the serviceCommissionCharityDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceCommissionCharityDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceCommissionCharityDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceCommissionCharityDetails }
     * 
     * 
     */
    public List<ServiceCommissionCharityDetails> getServiceCommissionCharityDetails() {
        if (serviceCommissionCharityDetails == null) {
            serviceCommissionCharityDetails = new ArrayList<ServiceCommissionCharityDetails>();
        }
        return this.serviceCommissionCharityDetails;
    }

}
