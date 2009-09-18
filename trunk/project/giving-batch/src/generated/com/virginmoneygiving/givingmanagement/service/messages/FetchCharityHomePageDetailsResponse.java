
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
 *         &lt;element name="charityHomePageDetails" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}ServiceCharityHomePageRegistration" maxOccurs="unbounded"/>
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
    "charityHomePageDetails",
    "basicReponse"
})
@XmlRootElement(name = "fetchCharityHomePageDetailsResponse")
public class FetchCharityHomePageDetailsResponse {

    @XmlElement(required = true)
    protected List<ServiceCharityHomePageRegistration> charityHomePageDetails;
    @XmlElement(required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the charityHomePageDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charityHomePageDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharityHomePageDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceCharityHomePageRegistration }
     * 
     * 
     */
    public List<ServiceCharityHomePageRegistration> getCharityHomePageDetails() {
        if (charityHomePageDetails == null) {
            charityHomePageDetails = new ArrayList<ServiceCharityHomePageRegistration>();
        }
        return this.charityHomePageDetails;
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
