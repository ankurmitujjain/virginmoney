package com.virginmoneygiving.profanity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.virginmoneygiving.profanity.domain.ProfanePhrase;

/**
 * Model object representing segregated profane phrases.
 *
 * @author Puneet Swarup
 */
public class ProfanityWords implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -8346662525453L;

    /** Unacceptable profane phrases (type = UN_ACC). */
    private List<ProfanePhrase> unAcceptablePhrases;

    /** Virgin screened profane phrases (type = SCR_VIR). */
    private List<ProfanePhrase> virginScreenedPhrases;

    /** Non-Virgin screened profane phrases (type = SCR_NONVIR). */
    private List<ProfanePhrase> nonVirginScreenedPhrases;

    /**
     * Gets the un acceptable phrases.
     *
     * @return the unAcceptablePhrases
     */
    public List<ProfanePhrase> getUnAcceptablePhrases() {
        if (unAcceptablePhrases == null) {
            unAcceptablePhrases = new ArrayList<ProfanePhrase>();
        }
        return unAcceptablePhrases;
    }

    /**
     * Sets the un acceptable phrases.
     *
     * @param unAcceptablePhrases the unAcceptablePhrases to set
     */
    public void setUnAcceptablePhrases(List<ProfanePhrase> unAcceptablePhrases) {
        this.unAcceptablePhrases = unAcceptablePhrases;
    }

    /**
     * Gets the virgin screened phrases.
     *
     * @return the virginScreenedPhrases
     */
    public List<ProfanePhrase> getVirginScreenedPhrases() {
        if (virginScreenedPhrases == null) {
            virginScreenedPhrases = new ArrayList<ProfanePhrase>();
        }
        return virginScreenedPhrases;
    }

    /**
     * Sets the virgin screened phrases.
     *
     * @param virginScreenedPhrases the virginScreenedPhrases to set
     */
    public void setVirginScreenedPhrases(
            List<ProfanePhrase> virginScreenedPhrases) {
        this.virginScreenedPhrases = virginScreenedPhrases;
    }

    /**
     * Gets the non virgin screened phrases.
     *
     * @return the nonVirginScreenedPhrases
     */
    public List<ProfanePhrase> getNonVirginScreenedPhrases() {
        if (nonVirginScreenedPhrases == null) {
            nonVirginScreenedPhrases = new ArrayList<ProfanePhrase>();
        }
        return nonVirginScreenedPhrases;
    }

    /**
     * Sets the non virgin screened phrases.
     *
     * @param nonVirginScreenedPhrases the nonVirginScreenedPhrases to set
     */
    public void setNonVirginScreenedPhrases(
            List<ProfanePhrase> nonVirginScreenedPhrases) {
        this.nonVirginScreenedPhrases = nonVirginScreenedPhrases;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     *
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("ProfanityWords ( ")
            .append("unAcceptablePhrases = ")
            .append(this.unAcceptablePhrases).append(tab)
            .append("virginScreenedPhrases = ")
            .append(this.virginScreenedPhrases).append(tab)
            .append("nonVirginScreenedPhrases = ")
            .append(this.nonVirginScreenedPhrases).append(tab)
            .append(super.toString()).append(" )").toString();
    }
}
