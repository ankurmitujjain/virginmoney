
package com.virginmoneygiving.paymentmanagement.service.messages;

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
 *         &lt;element name="exceptionPocessingSearchCriteria" type="{http://www.virginmoneygiving.com/type/payment-management/operations/}ExceptionPocessingSearchCriteria"/>
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
    "exceptionPocessingSearchCriteria"
})
@XmlRootElement(name = "fetchExceptionPocessingCrireriaResultsRequest", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class FetchExceptionPocessingCrireriaResultsRequest {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected ExceptionPocessingSearchCriteria exceptionPocessingSearchCriteria;

    /**
     * Gets the value of the exceptionPocessingSearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link ExceptionPocessingSearchCriteria }
     *     
     */
    public ExceptionPocessingSearchCriteria getExceptionPocessingSearchCriteria() {
        return exceptionPocessingSearchCriteria;
    }

    /**
     * Sets the value of the exceptionPocessingSearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExceptionPocessingSearchCriteria }
     *     
     */
    public void setExceptionPocessingSearchCriteria(ExceptionPocessingSearchCriteria value) {
        this.exceptionPocessingSearchCriteria = value;
    }

}
