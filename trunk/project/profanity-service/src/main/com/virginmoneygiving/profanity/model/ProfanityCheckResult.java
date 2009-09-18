package com.virginmoneygiving.profanity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Class representing an output from profanity check.
 * <p>
 * If the input text provided for profanity check does not contain any profane
 * phrases, the status will be true and the output text would be same as input
 * text.
 * <p>
 * But, if the input text fails profanity check, and does have any profane
 * words, the status will be false and output text would contain processed
 * profane phrases.
 *
 * @author Puneet Swarup
 */
public final class ProfanityCheckResult implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -164689349047L;

    /** Profanity check pass status. */
    private boolean status;

    /** The input provided to test for profanity. */
    private String inputText;

    /** The output produced after profanity check. */
    private String outputText;

    /** The output for screened virgin words. */
    private List<ProfanityCloseChars> screenedVirginWordsResult;

    /** The output for screened non virgin words. */
    private List<ProfanityCloseChars> screenedNonVirginWordsResult;
    
    /**
     * Checks if is status.
     *
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Gets the input text.
     *
     * @return the inputText
     */
    public String getInputText() {
        return inputText;
    }

    /**
     * Sets the input text.
     *
     * @param inputText the inputText to set
     */
    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    /**
     * Gets the output text.
     *
     * @return the outputText
     */
    public String getOutputText() {
        return outputText;
    }

    /**
     * Sets the output text.
     *
     * @param outputText the outputText to set
     */
    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    /**
     * Gets the screened words result.
     *
     * @return the screenedVirginWordsResult
     */
    public List<ProfanityCloseChars> getScreenedVirginWordsResult() {
        return screenedVirginWordsResult;
    }

    /**
     * Sets the screened words result.
     *
     * @param screenedVirginWordsResult the screenedVirginWordsResult to set
     */
    public void setScreenedVirginWordsResult(
            List<ProfanityCloseChars> screenedWordsResult) {
        this.screenedVirginWordsResult = screenedWordsResult;
    }

    /**
     * @return the screenedNonVirginWordsResult
     */
    public List<ProfanityCloseChars> getScreenedNonVirginWordsResult() {
        return screenedNonVirginWordsResult;
    }

    /**
     * @param screenedNonVirginWordsResult the screenedNonVirginWordsResult to set
     */
    public void setScreenedNonVirginWordsResult(
            List<ProfanityCloseChars> screenedNonVirginWordsResult) {
        this.screenedNonVirginWordsResult = screenedNonVirginWordsResult;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     *
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("ProfanityCheckResult ( ")
            .append("status = ").append(this.status).append(tab)
            .append("inputText = ").append(this.inputText).append(tab)
            .append("outputText = ").append(this.outputText).append(tab)
            .append("screenedVirginWordsResult = ").append(this.screenedVirginWordsResult).append(tab)
            .append(super.toString()).append(" )").toString();
    }
}
