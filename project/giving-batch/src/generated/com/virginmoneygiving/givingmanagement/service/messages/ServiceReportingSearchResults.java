
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceReportingSearchResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceReportingSearchResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="searchCriteria" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceSearchCriteria"/>
 *         &lt;element name="donorDetailsDVOList" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceDonorDetailsDVO" maxOccurs="unbounded"/>
 *         &lt;element name="eventDetailsDVOList" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceEventDetailsDVO" maxOccurs="unbounded"/>
 *         &lt;element name="fundraiserDetailsDVOList" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceFundraiserDetailsDVO" maxOccurs="unbounded"/>
 *         &lt;element name="fundraiserPageDetailsDVOList" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceFundraiserPageDetailsDVO" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceReportingSearchResults", propOrder = {
    "searchCriteria",
    "donorDetailsDVOList",
    "eventDetailsDVOList",
    "fundraiserDetailsDVOList",
    "fundraiserPageDetailsDVOList"
})
public class ServiceReportingSearchResults {

    @XmlElement(required = true)
    protected ServiceSearchCriteria searchCriteria;
    @XmlElement(required = true)
    protected List<ServiceDonorDetailsDVO> donorDetailsDVOList;
    @XmlElement(required = true)
    protected List<ServiceEventDetailsDVO> eventDetailsDVOList;
    @XmlElement(required = true)
    protected List<ServiceFundraiserDetailsDVO> fundraiserDetailsDVOList;
    @XmlElement(required = true)
    protected List<ServiceFundraiserPageDetailsDVO> fundraiserPageDetailsDVOList;

    /**
     * Gets the value of the searchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceSearchCriteria }
     *     
     */
    public ServiceSearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    /**
     * Sets the value of the searchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceSearchCriteria }
     *     
     */
    public void setSearchCriteria(ServiceSearchCriteria value) {
        this.searchCriteria = value;
    }

    /**
     * Gets the value of the donorDetailsDVOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the donorDetailsDVOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDonorDetailsDVOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceDonorDetailsDVO }
     * 
     * 
     */
    public List<ServiceDonorDetailsDVO> getDonorDetailsDVOList() {
        if (donorDetailsDVOList == null) {
            donorDetailsDVOList = new ArrayList<ServiceDonorDetailsDVO>();
        }
        return this.donorDetailsDVOList;
    }

    /**
     * Gets the value of the eventDetailsDVOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventDetailsDVOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventDetailsDVOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceEventDetailsDVO }
     * 
     * 
     */
    public List<ServiceEventDetailsDVO> getEventDetailsDVOList() {
        if (eventDetailsDVOList == null) {
            eventDetailsDVOList = new ArrayList<ServiceEventDetailsDVO>();
        }
        return this.eventDetailsDVOList;
    }

    /**
     * Gets the value of the fundraiserDetailsDVOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fundraiserDetailsDVOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFundraiserDetailsDVOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFundraiserDetailsDVO }
     * 
     * 
     */
    public List<ServiceFundraiserDetailsDVO> getFundraiserDetailsDVOList() {
        if (fundraiserDetailsDVOList == null) {
            fundraiserDetailsDVOList = new ArrayList<ServiceFundraiserDetailsDVO>();
        }
        return this.fundraiserDetailsDVOList;
    }

    /**
     * Gets the value of the fundraiserPageDetailsDVOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fundraiserPageDetailsDVOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFundraiserPageDetailsDVOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFundraiserPageDetailsDVO }
     * 
     * 
     */
    public List<ServiceFundraiserPageDetailsDVO> getFundraiserPageDetailsDVOList() {
        if (fundraiserPageDetailsDVOList == null) {
            fundraiserPageDetailsDVOList = new ArrayList<ServiceFundraiserPageDetailsDVO>();
        }
        return this.fundraiserPageDetailsDVOList;
    }

}
