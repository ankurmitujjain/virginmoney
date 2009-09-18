package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DTO representing a address book entry.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "ADDRESS_BOOK_ENTRY")
public class AddressBookEntry  extends AssociationAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -7937225224999L;

    /** This userPermission identifier. */
    private Long id = null;

    /** User to whom this permission is assigned. */
    private User user = null;

    /** The email address user is associated with. */
    private EmailAddress emailAddress = null;

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
     * Gets the user.
     * 
     * @return the user
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     * 
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the email address.
     * 
     * @return the emailAddress
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMAIL_ADDRESS_ID")
    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address.
     * 
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {
        final String tab = "    ";
        return new StringBuilder("AddressBookEntry ( ").append("id = ").append(id)
                .append(tab).append("user = ").append(user)
                .append(tab).append("emailAddress = ").append(emailAddress)
                .append(tab).append(super.toString()).append(" )").toString();
    }
}
