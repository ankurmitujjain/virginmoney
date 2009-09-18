package com.virginmoneygiving.profanity.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Domain object representing a profane phrase.
 *
 * @author Puneet Swarup
 */
@Entity
@Table(name = "PROFANE_PHRASE")
@NamedQuery(name = "fetchAllProfanePhrases", query = "FROM ProfanePhrase pp ORDER BY pp.profanePhraseType.code")
public class ProfanePhrase extends BaseAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -27058333726522L;

    /** Primary key for profanity phrase. */
    private Long id;

    /** Profane phrase. */
    private String profanePhrase;

    /** Profane phrase type. */
    private ProfanePhraseType profanePhraseType;

    /**
     * Gets the id.
     *
     * @return the id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * Gets the profane phrase.
     *
     * @return the profanePhrase
     */
    @Column(name = "PROFANE_PHRASE")
    public String getProfanePhrase() {
        return profanePhrase;
    }

    /**
     * Sets the profane phrase.
     *
     * @param profanePhrase the profanePhrase to set
     */
    public void setProfanePhrase(String profanePhrase) {
        this.profanePhrase = profanePhrase;
    }

    /**
     * Gets the profane phrase type.
     *
     * @return the profanePhraseType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROFANE_PHRASE_TYPE_CODE")
    public ProfanePhraseType getProfanePhraseType() {
        return profanePhraseType;
    }

    /**
     * Sets the profane phrase type.
     *
     * @param profanePhraseType the profanePhraseType to set
     */
    public void setProfanePhraseType(ProfanePhraseType profanePhraseType) {
        this.profanePhraseType = profanePhraseType;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     *
     * @return a <code>String</code> representation of this object.
     */
    @Override
    public String toString() {

        final String tab = "    ";
        return new StringBuilder("ProfanePhrase ( ").append("id = ").append(id)
                .append(tab).append("profanePhrase = ").append(profanePhrase)
                .append(tab).append("profanePhraseType = ").append(profanePhraseType)
                .append(tab).append(super.toString()).append(" )").toString();
    }
}
