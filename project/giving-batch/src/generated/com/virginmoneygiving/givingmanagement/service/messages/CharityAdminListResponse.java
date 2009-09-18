
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
 *         &lt;element name="isAllCheckInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityAdminList" type="{http://www.virginmoneygiving.com/type/giving-management/charity/}CharityAdminDetails" maxOccurs="unbounded" minOccurs="0"/>
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
    "isAllCheckInd",
    "charityAdminList"
})
@XmlRootElement(name = "CharityAdminListResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
public class CharityAdminListResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/", required = true)
    protected String isAllCheckInd;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/event/")
    protected List<CharityAdminDetails> charityAdminList;

    /**
     * Gets the value of the isAllCheckInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAllCheckInd() {
        return isAllCheckInd;
    }

    /**
     * Sets the value of the isAllCheckInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAllCheckInd(String value) {
        this.isAllCheckInd = value;
    }

    /**
     * Gets the value of the charityAdminList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charityAdminList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharityAdminList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharityAdminDetails }
     * 
     * 
     */
    public List<CharityAdminDetails> getCharityAdminList() {
        if (charityAdminList == null) {
            charityAdminList = new ArrayList<CharityAdminDetails>();
        }
        return this.charityAdminList;
    }

}
