package com.virginmoneygiving.giving.domain;

import java.util.Date;
import java.util.List;

/**
 * This object will hold email content.
 * 
 * @author RohitM
 */
public class EmailContentModel {

    /** profanityReferenceNo is Id of profane word in database. */
    private String profanityReferenceNo;
    
    /** webpageURL this will be URL of page where profanity occures. */
    private String webpageURL;
    
    /** dateGenerated on which email generated. */
    private Date dateGenerated;
    
    /** charity name if profanity found on charity page. */
    private String charityName;
    
    /** charityNumber. */
    private String charityNumber;
    
    /** fundraiser first name if profanity found on fundraiser page. */
    private String fundraiserFirstName;
    
    /** fundraiser surname. */
    private String fundraiserSurname;
    
    /** fundraiserReference. */
    private String fundraiserReference;
    
    /** event type either charity or operation. */
    private String eventType;
    
    /** event name */
    private String eventName;    
    
    /** profanityCloseCharsList hold the list of ProfanityCloseChars object. */
    private List<ProfanityCloseChars> profanityCloseCharsList;

    /** A reference for the report */
    private String reportReference;

    /**
     * Gets the webpage url.
     * 
     * @return the webpageURL
     */
    public String getWebpageURL() {
        return webpageURL;
    }

    /**
     * Sets the webpage url.
     * 
     * @param webpageURL the webpageURL to set
     */
    public void setWebpageURL(String webpageURL) {
        this.webpageURL = webpageURL;
    }

    /**
     * Gets the date generated.
     * 
     * @return the dateGenerated
     */
    public Date getDateGenerated() {
        return dateGenerated;
    }

    /**
     * Sets the date generated.
     * 
     * @param dateGenerated the dateGenerated to set
     */
    public void setDateGenerated(Date dateGenerated) {
        this.dateGenerated = dateGenerated;
    }

    /**
     * Gets the charity name.
     * 
     * @return the charityName
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the charity name.
     * 
     * @param charityName the charityName to set
     */
    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    /**
     * Gets the charity number.
     * 
     * @return the charityNumber
     */
    public String getCharityNumber() {
        return charityNumber;
    }

    /**
     * Sets the charity number.
     * 
     * @param charityNumber the charityNumber to set
     */
    public void setCharityNumber(String charityNumber) {
        this.charityNumber = charityNumber;
    }

    /**
     * Gets the fundraiser first name.
     * 
     * @return the fundraiserFirstName
     */
    public String getFundraiserFirstName() {
        return fundraiserFirstName;
    }

    /**
     * Sets the fundraiser first name.
     * 
     * @param fundraiserFirstName the fundraiserFirstName to set
     */
    public void setFundraiserFirstName(String fundraiserFirstName) {
        this.fundraiserFirstName = fundraiserFirstName;
    }

    /**
     * Gets the fundraiser surname.
     * 
     * @return the fundraiserSurname
     */
    public String getFundraiserSurname() {
        return fundraiserSurname;
    }

    /**
     * Sets the fundraiser surname.
     * 
     * @param fundraiserSurname the fundraiserSurname to set
     */
    public void setFundraiserSurname(String fundraiserSurname) {
        this.fundraiserSurname = fundraiserSurname;
    }

    /**
     * Gets the fundraiser reference.
     * 
     * @return the fundraiserReference
     */
    public String getFundraiserReference() {
        return fundraiserReference;
    }

    /**
     * Sets the fundraiser reference.
     * 
     * @param fundraiserReference the fundraiserReference to set
     */
    public void setFundraiserReference(String fundraiserReference) {
        this.fundraiserReference = fundraiserReference;
    }

    /**
     * Gets the profanity reference no.
     * 
     * @return the profanityReferenceNo
     */
    public String getProfanityReferenceNo() {
        return profanityReferenceNo;
    }

    /**
     * Sets the profanity reference no.
     * 
     * @param profanityReferenceNo the profanityReferenceNo to set
     */
    public void setProfanityReferenceNo(String profanityReferenceNo) {
        this.profanityReferenceNo = profanityReferenceNo;
    }

    /**
     * Gets the profanity close chars list.
     * 
     * @return the profanityCloseCharsList
     */
    public List<ProfanityCloseChars> getProfanityCloseCharsList() {
        return profanityCloseCharsList;
    }

    /**
     * Sets the profanity close chars list.
     * 
     * @param profanityCloseCharsList the profanityCloseCharsList to set
     */
    public void setProfanityCloseCharsList(
            List<ProfanityCloseChars> profanityCloseCharsList) {
        this.profanityCloseCharsList = profanityCloseCharsList;
    }


    /**
     * Getter for property 'reportReference'.
     *
     * @return Value for property 'reportReference'.
     */
    public String getReportReference() {
        return reportReference;
    }

    /**
     * Setter for property 'reportReference'.
     *
     * @param reportReference Value to set for property 'reportReference'.
     */
    public void setReportReference(String reportReference) {
        this.reportReference = reportReference;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @param eventName the eventName to set
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * toString() method generated by IntelliJ GenerateToString plugin.
     * 
     * @return A String representation of this instance.
     */
    public String toString() {
        return "EmailContentModel{" + "hash=" + this.hashCode() + " profanityReferenceNo='" + profanityReferenceNo +
                '\'' + ", charityNumber='" + charityNumber + '\'' + ", fundraiserSurname='" + fundraiserSurname + '\'' +
                ", fundraiserReference='" + fundraiserReference + '\'' + ", fundraiserFirstName='" +
                fundraiserFirstName 
                + '\'' + ", charityName='" + charityName + '\''
                + '\'' + ", eventType='" + eventType + '\''
                + '\'' + ", eventName='" + eventName + '\''+ '}';
    }
}
