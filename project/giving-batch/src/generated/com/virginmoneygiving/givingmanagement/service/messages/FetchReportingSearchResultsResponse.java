
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
 *         &lt;element name="reportingSearchResults" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceReportingSearchResults"/>
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
    "reportingSearchResults",
    "basicReponse"
})
@XmlRootElement(name = "fetchReportingSearchResultsResponse")
public class FetchReportingSearchResultsResponse {

    @XmlElement(required = true)
    protected ServiceReportingSearchResults reportingSearchResults;
    @XmlElement(required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the reportingSearchResults property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceReportingSearchResults }
     *     
     */
    public ServiceReportingSearchResults getReportingSearchResults() {
        return reportingSearchResults;
    }

    /**
     * Sets the value of the reportingSearchResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceReportingSearchResults }
     *     
     */
    public void setReportingSearchResults(ServiceReportingSearchResults value) {
        this.reportingSearchResults = value;
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
