
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceFundraiserDonationDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiserDonationDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fundraiserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fundraiserDetailsList" type="{http://www.virginmoneygiving.com/type/giving-management/donor/}ServiceFundraiserSubset" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="charitySplitList" type="{http://www.virginmoneygiving.com/type/giving-management/donor/}ServiceCharitySplitDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eventName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="target" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalRaised" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fundraiserLogoUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charitiesContributionInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserGroupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="userURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiserDonationDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", propOrder = {
    "fundraiserId",
    "fundraiserDetailsList",
    "charitySplitList",
    "eventName",
    "eventDate",
    "target",
    "totalRaised",
    "fundraiserLogoUrl",
    "charitiesContributionInd",
    "fundraiserGroupId",
    "userURL",
    "pageURL"
})
public class ServiceFundraiserDonationDetails {

    protected long fundraiserId;
    @XmlElement(nillable = true)
    protected List<ServiceFundraiserSubset> fundraiserDetailsList;
    @XmlElement(nillable = true)
    protected List<ServiceCharitySplitDetails> charitySplitList;
    @XmlElement(required = true)
    protected String eventName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eventDate;
    @XmlElement(required = true)
    protected BigDecimal target;
    @XmlElement(required = true)
    protected BigDecimal totalRaised;
    @XmlElement(required = true)
    protected String fundraiserLogoUrl;
    @XmlElement(required = true)
    protected String charitiesContributionInd;
    protected long fundraiserGroupId;
    @XmlElement(required = true)
    protected String userURL;
    @XmlElement(required = true)
    protected String pageURL;

    /**
     * Gets the value of the fundraiserId property.
     * 
     */
    public long getFundraiserId() {
        return fundraiserId;
    }

    /**
     * Sets the value of the fundraiserId property.
     * 
     */
    public void setFundraiserId(long value) {
        this.fundraiserId = value;
    }

    /**
     * Gets the value of the fundraiserDetailsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fundraiserDetailsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFundraiserDetailsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFundraiserSubset }
     * 
     * 
     */
    public List<ServiceFundraiserSubset> getFundraiserDetailsList() {
        if (fundraiserDetailsList == null) {
            fundraiserDetailsList = new ArrayList<ServiceFundraiserSubset>();
        }
        return this.fundraiserDetailsList;
    }

    /**
     * Gets the value of the charitySplitList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charitySplitList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharitySplitList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceCharitySplitDetails }
     * 
     * 
     */
    public List<ServiceCharitySplitDetails> getCharitySplitList() {
        if (charitySplitList == null) {
            charitySplitList = new ArrayList<ServiceCharitySplitDetails>();
        }
        return this.charitySplitList;
    }

    /**
     * Gets the value of the eventName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the value of the eventName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventName(String value) {
        this.eventName = value;
    }

    /**
     * Gets the value of the eventDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventDate() {
        return eventDate;
    }

    /**
     * Sets the value of the eventDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventDate(XMLGregorianCalendar value) {
        this.eventDate = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTarget(BigDecimal value) {
        this.target = value;
    }

    /**
     * Gets the value of the totalRaised property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalRaised() {
        return totalRaised;
    }

    /**
     * Sets the value of the totalRaised property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalRaised(BigDecimal value) {
        this.totalRaised = value;
    }

    /**
     * Gets the value of the fundraiserLogoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundraiserLogoUrl() {
        return fundraiserLogoUrl;
    }

    /**
     * Sets the value of the fundraiserLogoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundraiserLogoUrl(String value) {
        this.fundraiserLogoUrl = value;
    }

    /**
     * Gets the value of the charitiesContributionInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharitiesContributionInd() {
        return charitiesContributionInd;
    }

    /**
     * Sets the value of the charitiesContributionInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharitiesContributionInd(String value) {
        this.charitiesContributionInd = value;
    }

    /**
     * Gets the value of the fundraiserGroupId property.
     * 
     */
    public long getFundraiserGroupId() {
        return fundraiserGroupId;
    }

    /**
     * Sets the value of the fundraiserGroupId property.
     * 
     */
    public void setFundraiserGroupId(long value) {
        this.fundraiserGroupId = value;
    }

    /**
     * Gets the value of the userURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserURL() {
        return userURL;
    }

    /**
     * Sets the value of the userURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserURL(String value) {
        this.userURL = value;
    }

    /**
     * Gets the value of the pageURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageURL() {
        return pageURL;
    }

    /**
     * Sets the value of the pageURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageURL(String value) {
        this.pageURL = value;
    }

}
