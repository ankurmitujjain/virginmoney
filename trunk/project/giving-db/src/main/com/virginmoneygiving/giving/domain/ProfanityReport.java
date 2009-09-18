package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The data object that represents the profanity report to be persisted.
 * 
 * @author RohitM
 */
@Entity
@Table(name = "PROFANITY_REPORT")
public class ProfanityReport extends AuditAttributes implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Primary key for profanity report. */
    private Long id;

    /** Date and time when email generated. */
    private Timestamp emailDateTime;

    /** Email content. */
    private String emailContent;

    /** Category of email from where its generated. */
    private String category;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
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
     * Gets the email date time.
     * 
     * @return the emailDateTime
     */
    @Column(name = "EMAIL_DATETIME")
    public Timestamp getEmailDateTime() {
        return emailDateTime;
    }

    /**
     * Sets the email date time.
     * 
     * @param emailDateTime the emailDateTime to set
     */
    public void setEmailDateTime(Timestamp emailDateTime) {
        this.emailDateTime = emailDateTime;
    }

    /**
     * Gets the email content.
     * 
     * @return the emailContent
     */
   
    @Column(name = "EMAIL_CONTENT", columnDefinition = "text")
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
    @Column(name = "CATEGORY")
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
