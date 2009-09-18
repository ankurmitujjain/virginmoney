package com.virginmoneygiving.giving.domain;

/**
 * Service layer model for service request object object of checkProfanity service.
 * 
 * @author RohitM
 */
public class CheckForProfanityRequestModel {

    /** Text to check profanity on. */
    private String text;

    /** Hold email content to be send when profane word found. */
    private EmailContentModel emailContent;

    /**
     * Gets the text.
     * 
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text.
     * 
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the email content.
     * 
     * @return the emailContent
     */
    public EmailContentModel getEmailContent() {
        return emailContent;
    }

    /**
     * Sets the email content.
     * 
     * @param emailContent the emailContent to set
     */
    public void setEmailContent(EmailContentModel emailContent) {
        this.emailContent = emailContent;
    }

}
