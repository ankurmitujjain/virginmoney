
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
 *         &lt;element name="ServiceModuleDataList" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceModuleData" maxOccurs="unbounded"/>
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
    "serviceModuleDataList"
})
@XmlRootElement(name = "fetchModuleDataResponse")
public class FetchModuleDataResponse {

    @XmlElement(name = "ServiceModuleDataList", required = true)
    protected List<ServiceModuleData> serviceModuleDataList;

    /**
     * Gets the value of the serviceModuleDataList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceModuleDataList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceModuleDataList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceModuleData }
     * 
     * 
     */
    public List<ServiceModuleData> getServiceModuleDataList() {
        if (serviceModuleDataList == null) {
            serviceModuleDataList = new ArrayList<ServiceModuleData>();
        }
        return this.serviceModuleDataList;
    }

}
