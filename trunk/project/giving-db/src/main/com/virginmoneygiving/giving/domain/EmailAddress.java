package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * The data object that represents the Person data to be persisted.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added audit attributes and toString.
 */
@Entity
@Table(name = "EMAIL_ADDRESS")
@Audited
public class EmailAddress extends EnversAssociationAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -1560148056665520395L;

    /** Email address id. */
    private Long id;

    /** Email Address Type. */
    private String emailAddressType;

    /** Email Address. */
    private String emailAddress;

    /**
     * Getter method for email address Id.
     * 
     * @return id email address Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
    public Long getId() {
        return id;
    }

    /**
     * Setter emthod for email address id.
     * 
     * @param id new value for id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for email address type.
     * <p>
     * U=User email address; C=Contact email address
     * </p>
     * 
     * @return emailAddressType
     */
    @Column(name = "EMAIL_ADDRESS_TYPE")
    @Audited
    public String getEmailAddressType() {
        return emailAddressType;
    }

    /**
     * Setter method for email address type.
     * 
     * @param emailAddressType new value for emailAddressType
     */
    public void setEmailAddressType(String emailAddressType) {
        this.emailAddressType = emailAddressType;
    }

    /**
     * Gets the email address.
     * 
     * @return emailAddress
     */
    @Column(name = "EMAIL_ADDRESS")
    @Audited
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address.
     * 
     * @param emailAddress new value for emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation
     * of this object.
     */
     @Override
     public String toString() {

        final String tab = "    ";
        return new StringBuilder("EmailAddress ( ")
            .append("id = ").append(id).append(tab)
            .append("emailAddressType = ").append(emailAddressType).append(tab)
            .append("emailAddress = ").append(emailAddress).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
