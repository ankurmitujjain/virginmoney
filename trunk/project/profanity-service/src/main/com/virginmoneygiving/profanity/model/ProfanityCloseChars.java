package com.virginmoneygiving.profanity.model;

import java.io.Serializable;

/**
 * Class to represent screened word with neighbouring words.
 * <p>
 * It can be used to report the existence of any screened word with additional
 * information as the surrounding words.
 *
 * @author Rohit Mandlik
 * @author Puneet Swarup
 */
public final class ProfanityCloseChars implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -28765872658L;

    /** To hold n number of prefix characters. */
    private String beforeChars;

    /** To hold n number of postfix words. */
    private String afterChars;

    /** To hold the screened word. */
    private String screenedWord;

    /** To hold if of word in database. */
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
     * Gets the screened word.
     *
     * @return the screenedWord
     */
    public String getScreenedWord() {
        return screenedWord;
    }

    /**
     * Sets the screened word.
     *
     * @param screenedWord the screenedWord to set
     */
    public void setScreenedWord(String screenedWord) {
        this.screenedWord = screenedWord;
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

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     *
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("ProfanityCloseChars ( ")
            .append("beforeChars = ").append(this.beforeChars).append(tab)
            .append("afterChars = ").append(this.afterChars).append(tab)
            .append("screenedWord = ").append(this.screenedWord).append(tab)
            .append("id = ").append(this.id).append(tab)
            .append(super.toString()).append(" )").toString();
    }
}
