
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
 *         &lt;element name="fundraiserGroupDetail" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceFundraiserGroupDetail"/>
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
    "fundraiserGroupDetail"
})
@XmlRootElement(name = "findFundraiserGroupResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
public class FindFundraiserGroupResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected ServiceFundraiserGroupDetail fundraiserGroupDetail;

    /**
     * Gets the value of the fundraiserGroupDetail property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceFundraiserGroupDetail }
     *     
     */
    public ServiceFundraiserGroupDetail getFundraiserGroupDetail() {
        return fundraiserGroupDetail;
    }

    /**
     * Sets the value of the fundraiserGroupDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceFundraiserGroupDetail }
     *     
     */
    public void setFundraiserGroupDetail(ServiceFundraiserGroupDetail value) {
        this.fundraiserGroupDetail = value;
    }

}
