package com.virginmoneygiving.giving.domain;

/**
 * Service layer model for response object of checkProfanity service.
 * 
 * @author RohitM
 */
public class CheckForProfanityResponseDetailsModel {

    /** Text with profane words replaced by **** string. */
    private String text;

    /** This will hold the status of check. */
    private String resultStatus;

    /** indicates if an email alert was sent as a result of the profanity check */
    private boolean emailAlertSent = false;

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
     * Gets the result status.
     * 
     * @return the resultStatus
     */
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * Sets the result status.
     * 
     * @param resultStatus the resultStatus to set
     */
    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }


    /**
     * Getter for property 'emailAlertSent'.
     *
     * @return Value for property 'emailAlertSent'.
     */
    public boolean isEmailAlertSent() {
        return emailAlertSent;
    }

    /**
     * Setter for property 'emailAlertSent'.
     *
     * @param emailAlertSent Value to set for property 'emailAlertSent'.
     */
    public void setEmailAlertSent(boolean emailAlertSent) {
        this.emailAlertSent = emailAlertSent;
    }
}
