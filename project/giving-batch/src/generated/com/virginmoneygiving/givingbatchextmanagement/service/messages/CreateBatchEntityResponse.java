
package com.virginmoneygiving.givingbatchextmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="batchEntityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "batchEntityId"
})
@XmlRootElement(name = "createBatchEntityResponse")
public class CreateBatchEntityResponse {

    protected long batchEntityId;

    /**
     * Gets the value of the batchEntityId property.
     * 
     */
    public long getBatchEntityId() {
        return batchEntityId;
    }

    /**
     * Sets the value of the batchEntityId property.
     * 
     */
    public void setBatchEntityId(long value) {
        this.batchEntityId = value;
    }

}
