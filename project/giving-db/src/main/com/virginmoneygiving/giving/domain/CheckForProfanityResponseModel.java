package com.virginmoneygiving.giving.domain;

/**
 * Object return to web layer for service checkForProfanity.
 * 
 * @author RohitM
 */
public class CheckForProfanityResponseModel {

    /** Response detail object. */
    private CheckForProfanityResponseDetailsModel checkForProfanityResponseDetailsModel;

    /**
     * Gets the value of the checkForProfanityResponseDetails property.
     * 
     * @return possible object is {@link CheckForProfanityResponseDetailsModel }
     */
    public CheckForProfanityResponseDetailsModel getCheckForProfanityResponseDetails() {
        return checkForProfanityResponseDetailsModel;
    }

    /**
     * Sets the value of the CheckForProfanityResponseDetailsModel property.
     * 
     * @param value allowed object is
     * {@link CheckForProfanityResponseDetailsModel }
     */
    public void setCheckForProfanityResponseDetailsModel(
            CheckForProfanityResponseDetailsModel value) {
        this.checkForProfanityResponseDetailsModel = value;
    }
}
