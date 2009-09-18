package com.virginmoneygiving.giving.domain;

import java.util.List;

/**
 * This object is for internal use in checkForProfanity service. Function
 * checkProfanity in CheckForProfanityHelper class will return this object.
 * 
 * @author rohitm
 */
public class CheckForProfanityResult {

    /** Response detail object. */
    private CheckForProfanityResponseDetailsModel checkForProfanityResponseDetailsModel;

    /** List of object which hold 50 words before and after virgin screened words found in text enter by user. */
    private List<ProfanityCloseChars> virginScreenedWords;

    /** List of object which hold 50 words before and after non virgin screened words found in text enter by user. */
    private List<ProfanityCloseChars> nonVirginScreenedWords;

    /**
     * Gets the check for profanity response details model.
     * 
     * @return the checkForProfanityResponseDetailsModel
     */
    public CheckForProfanityResponseDetailsModel getCheckForProfanityResponseDetailsModel()
    {
        return checkForProfanityResponseDetailsModel;
    }

    /**
     * Sets the check for profanity response details model.
     * 
     * @param checkForProfanityResponseDetailsModel the checkForProfanityResponseDetailsModel to set
     */
    public void setCheckForProfanityResponseDetailsModel(
            CheckForProfanityResponseDetailsModel checkForProfanityResponseDetailsModel)
    {
        this.checkForProfanityResponseDetailsModel =
                checkForProfanityResponseDetailsModel;
    }

    /**
     * Gets the virgin screened words.
     * 
     * @return the virginScreenedWords
     */
    public List<ProfanityCloseChars> getVirginScreenedWords() {
        return virginScreenedWords;
    }

    /**
     * Sets the virgin screened words.
     * 
     * @param virginScreenedWords the virginScreenedWords to set
     */
    public void setVirginScreenedWords(
            List<ProfanityCloseChars> virginScreenedWords) {
        this.virginScreenedWords = virginScreenedWords;
    }

    /**
     * Gets the non virgin screened words.
     * 
     * @return the nonVirginScreenedWords
     */
    public List<ProfanityCloseChars> getNonVirginScreenedWords() {
        return nonVirginScreenedWords;
    }

    /**
     * Sets the non virgin screened words.
     * 
     * @param nonVirginScreenedWords the nonVirginScreenedWords to set
     */
    public void setNonVirginScreenedWords(
            List<ProfanityCloseChars> nonVirginScreenedWords) {
        this.nonVirginScreenedWords = nonVirginScreenedWords;
    }
}
