
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
 *         &lt;element name="fundraiserGroupDetailList" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceFundraiserGroupDetail" maxOccurs="unbounded"/>
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
    "fundraiserGroupDetailList"
})
@XmlRootElement(name = "findFundraiserGroupsbyFundraiserIdResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
public class FindFundraiserGroupsbyFundraiserIdResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected List<ServiceFundraiserGroupDetail> fundraiserGroupDetailList;

    /**
     * Gets the value of the fundraiserGroupDetailList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fundraiserGroupDetailList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFundraiserGroupDetailList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFundraiserGroupDetail }
     * 
     * 
     */
    public List<ServiceFundraiserGroupDetail> getFundraiserGroupDetailList() {
        if (fundraiserGroupDetailList == null) {
            fundraiserGroupDetailList = new ArrayList<ServiceFundraiserGroupDetail>();
        }
        return this.fundraiserGroupDetailList;
    }

}
