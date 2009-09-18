
package com.virginmoneygiving.givingbatchextmanagement.service.messages;

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
 *         &lt;element name="batchEntityList" type="{http://www.virginmoneygiving.com/type/giving-batch-ext-management/giving-batch-ext/}serviceBatchEntity" maxOccurs="unbounded"/>
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
    "batchEntityList"
})
@XmlRootElement(name = "fetchBatchEntityResponse")
public class FetchBatchEntityResponse {

    @XmlElement(required = true)
    protected List<ServiceBatchEntity> batchEntityList;

    /**
     * Gets the value of the batchEntityList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batchEntityList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatchEntityList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceBatchEntity }
     * 
     * 
     */
    public List<ServiceBatchEntity> getBatchEntityList() {
        if (batchEntityList == null) {
            batchEntityList = new ArrayList<ServiceBatchEntity>();
        }
        return this.batchEntityList;
    }

}
