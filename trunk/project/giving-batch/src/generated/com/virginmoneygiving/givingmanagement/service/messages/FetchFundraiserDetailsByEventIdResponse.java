
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
 *         &lt;element name="fundriaserCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fundraiserDetails" type="{http://www.virginmoneygiving.com/type/giving-management/event/}FundraiserDetails" maxOccurs="unbounded" minOccurs="0"/>
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
    "fundriaserCount",
    "fundraiserDetails"
})
@XmlRootElement(name = "fetchFundraiserDetailsByEventIdResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class FetchFundraiserDetailsByEventIdResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected Integer fundriaserCount;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected List<FundraiserDetails> fundraiserDetails;

    /**
     * Gets the value of the fundriaserCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFundriaserCount() {
        return fundriaserCount;
    }

    /**
     * Sets the value of the fundriaserCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFundriaserCount(Integer value) {
        this.fundriaserCount = value;
    }

    /**
     * Gets the value of the fundraiserDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fundraiserDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFundraiserDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FundraiserDetails }
     * 
     * 
     */
    public List<FundraiserDetails> getFundraiserDetails() {
        if (fundraiserDetails == null) {
            fundraiserDetails = new ArrayList<FundraiserDetails>();
        }
        return this.fundraiserDetails;
    }

}
