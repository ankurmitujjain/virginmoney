
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceFundraiserActivity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiserActivity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fundraiserActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="eventCompletionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="eventLocationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donationNotificationInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorThankYouInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorThankYouText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityFundSplit" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceFundraiserActivityCharityFundSplit" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiserActivity", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "fundraiserActivityId",
    "eventCompletionDate",
    "eventLocationCode",
    "donationNotificationInd",
    "donorThankYouInd",
    "donorThankYouText",
    "charityFundSplit"
})
public class ServiceFundraiserActivity {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserActivityId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eventCompletionDate;
    @XmlElement(required = true, nillable = true)
    protected String eventLocationCode;
    @XmlElement(required = true)
    protected String donationNotificationInd;
    @XmlElement(required = true)
    protected String donorThankYouInd;
    @XmlElement(required = true)
    protected String donorThankYouText;
    @XmlElement(required = true)
    protected List<ServiceFundraiserActivityCharityFundSplit> charityFundSplit;

    /**
     * Gets the value of the fundraiserActivityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserActivityId() {
        return fundraiserActivityId;
    }

    /**
     * Sets the value of the fundraiserActivityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserActivityId(Long value) {
        this.fundraiserActivityId = value;
    }

    /**
     * Gets the value of the eventCompletionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventCompletionDate() {
        return eventCompletionDate;
    }

    /**
     * Sets the value of the eventCompletionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventCompletionDate(XMLGregorianCalendar value) {
        this.eventCompletionDate = value;
    }

    /**
     * Gets the value of the eventLocationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventLocationCode() {
        return eventLocationCode;
    }

    /**
     * Sets the value of the eventLocationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventLocationCode(String value) {
        this.eventLocationCode = value;
    }

    /**
     * Gets the value of the donationNotificationInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonationNotificationInd() {
        return donationNotificationInd;
    }

    /**
     * Sets the value of the donationNotificationInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonationNotificationInd(String value) {
        this.donationNotificationInd = value;
    }

    /**
     * Gets the value of the donorThankYouInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonorThankYouInd() {
        return donorThankYouInd;
    }

    /**
     * Sets the value of the donorThankYouInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonorThankYouInd(String value) {
        this.donorThankYouInd = value;
    }

    /**
     * Gets the value of the donorThankYouText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonorThankYouText() {
        return donorThankYouText;
    }

    /**
     * Sets the value of the donorThankYouText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonorThankYouText(String value) {
        this.donorThankYouText = value;
    }

    /**
     * Gets the value of the charityFundSplit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charityFundSplit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharityFundSplit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFundraiserActivityCharityFundSplit }
     * 
     * 
     */
    public List<ServiceFundraiserActivityCharityFundSplit> getCharityFundSplit() {
        if (charityFundSplit == null) {
            charityFundSplit = new ArrayList<ServiceFundraiserActivityCharityFundSplit>();
        }
        return this.charityFundSplit;
    }

}
