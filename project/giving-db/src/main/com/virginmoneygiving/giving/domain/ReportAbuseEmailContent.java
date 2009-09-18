package com.virginmoneygiving.giving.domain;

import java.util.Date;

/**
 * This object hold email content when user report abuse any page.
 * 
 * @author RohitM
 */
public class ReportAbuseEmailContent {

    /** Reference no of email information stored in database. */
    private String profanityReference;

    /** webpageURL this will be URL of page where profanity occures. */
    private String webpageURL;

    /** charity name if profanity found on charity page. */
    private String charityName;

    /** charityNumber. */
    private String charityNumber;

    /** fundraiser first name if profanity found on fundraiser page. */
    private String fundraiserFirstName;

    /** fundraiser surname. */
    private String fundraiserSurname;

    /** Comment entered by user while reporting any page as abuse. */
    private String userReportAbuseComment;

    /** dateGenerated on which email generated. */
    private Date dateGenerated = new Date();

    /**
     * Gets the profanity reference.
     * 
     * @return the profanityReference
     */
    public String getProfanityReference() {
        return profanityReference;
    }

    /**
     * Sets the profanity reference.
     * 
     * @param profanityReference the profanityReference to set
     */
    public void setProfanityReference(String profanityReference) {
        this.profanityReference = profanityReference;
    }

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
     * Gets the user report abuse comment.
     * 
     * @return the userReportAbuseComment
     */
    public String getUserReportAbuseComment() {
        return userReportAbuseComment;
    }

    /**
     * Sets the user report abuse comment.
     * 
     * @param userReportAbuseComment the userReportAbuseComment to set
     */
    public void setUserReportAbuseComment(String userReportAbuseComment) {
        this.userReportAbuseComment = userReportAbuseComment;
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
}
