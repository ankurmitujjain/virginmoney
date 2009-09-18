package com.virginmoneygiving.giving.domain;


/**
 * This object to prepare the string need to send an email when screened word
 * found in text entered by user.
 * 
 * @author rohitm
 */
public class ProfanityCloseChars {

    /** This will hold 50 chars before screened word. */
    private String beforeChars;

    /** This will hold 50 chars after screened word. */
    private String afterChars;

    /** This will hold screened word. */
    private String doubtfulWord;

    /** word id. */
    private Long id;

    /**
     * Gets the before chars.
     * 
     * @return the beforeChars
     */
    public String getBeforeChars() {
        return beforeChars;
    }

    /**
     * Sets the before chars.
     * 
     * @param beforeChars the beforeChars to set
     */
    public void setBeforeChars(String beforeChars) {
        this.beforeChars = beforeChars;
    }

    /**
     * Gets the after chars.
     * 
     * @return the afterChars
     */
    public String getAfterChars() {
        return afterChars;
    }

    /**
     * Sets the after chars.
     * 
     * @param afterChars the afterChars to set
     */
    public void setAfterChars(String afterChars) {
        this.afterChars = afterChars;
    }

    /**
     * Gets the doubtful word.
     * 
     * @return the doubtfulWord
     */
    public String getDoubtfulWord() {
        return doubtfulWord;
    }

    /**
     * Sets the doubtful word.
     * 
     * @param doubtfulWord the doubtfulWord to set
     */
    public void setDoubtfulWord(String doubtfulWord) {
        this.doubtfulWord = doubtfulWord;
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
