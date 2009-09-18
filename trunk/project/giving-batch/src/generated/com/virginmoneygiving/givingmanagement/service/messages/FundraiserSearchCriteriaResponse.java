
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
 *         &lt;element name="foundraiserDonorList" type="{http://www.virginmoneygiving.com/type/giving-management/operations/}ServiceFundraiserOrDonorDetails" maxOccurs="unbounded"/>
 *         &lt;element name="basicReponse" type="{http://www.virginmoneygiving.com/type/giving-management/common/}BasicResponse"/>
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
    "foundraiserDonorList",
    "basicReponse"
})
@XmlRootElement(name = "fundraiserSearchCriteriaResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/")
public class FundraiserSearchCriteriaResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected List<ServiceFundraiserOrDonorDetails> foundraiserDonorList;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the foundraiserDonorList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the foundraiserDonorList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFoundraiserDonorList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFundraiserOrDonorDetails }
     * 
     * 
     */
    public List<ServiceFundraiserOrDonorDetails> getFoundraiserDonorList() {
        if (foundraiserDonorList == null) {
            foundraiserDonorList = new ArrayList<ServiceFundraiserOrDonorDetails>();
        }
        return this.foundraiserDonorList;
    }

    /**
     * Gets the value of the basicReponse property.
     * 
     * @return
     *     possible object is
     *     {@link BasicResponse }
     *     
     */
    public BasicResponse getBasicReponse() {
        return basicReponse;
    }

    /**
     * Sets the value of the basicReponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicResponse }
     *     
     */
    public void setBasicReponse(BasicResponse value) {
        this.basicReponse = value;
    }

}
