
package com.virginmoneygiving.indexerservice.messages;

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
 *         &lt;element name="indexerUpdate" type="{http://www.virginmoneygiving.com/indexer-service}IndexerUpdate"/>
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
    "indexerUpdate"
})
@XmlRootElement(name = "updateInIndexMessage")
public class UpdateInIndexMessage {

    @XmlElement(required = true)
    protected IndexerUpdate indexerUpdate;

    /**
     * Gets the value of the indexerUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link IndexerUpdate }
     *     
     */
    public IndexerUpdate getIndexerUpdate() {
        return indexerUpdate;
    }

    /**
     * Sets the value of the indexerUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndexerUpdate }
     *     
     */
    public void setIndexerUpdate(IndexerUpdate value) {
        this.indexerUpdate = value;
    }

}
