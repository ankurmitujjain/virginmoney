package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * An equivalent object of ProfaneReport used to transfer data upto DAO in saveProfanityEmails service.
 * 
 * @author RohitM
 */
public class ProfanityReportModel implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Date and time when email generated. */
    private Date emailDateTime;

    /** Email content. */
    private String emailContent;

    /** Category of email from where its generated. */
    private String category;

    /**
     * Gets the email date time.
     * 
     * @return the emailDateTime
     */
    public Date getEmailDateTime() {
        return emailDateTime;
    }

    /**
     * Sets the email date time.
     * 
     * @param emailDateTime the emailDateTime to set
     */
    public void setEmailDateTime(Date emailDateTime) {
        this.emailDateTime = emailDateTime;
    }

    /**
     * Gets the email content.
     * 
     * @return the emailContent
     */
    public String getEmailContent() {
        return emailContent;
    }

    /**
     * Sets the email content.
     * 
     * @param emailContent the emailContent to set
     */
    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    /**
     * Gets the category.
     * 
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category.
     * 
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
