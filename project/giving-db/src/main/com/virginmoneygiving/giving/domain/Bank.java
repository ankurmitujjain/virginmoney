package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the bank data to be persisted.
 * 
 * @author Rahul Vaidya
 */
@Entity
@Table(name = "BANK")
@Audited
public class Bank extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 5896589655656L;

    /** Primary key for bank. */
    private Long id;

    /** Bank name. */
    private String name;

    /** Bank Address. */
    private Set<BankAddress> bankAddress;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @NotAudited
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
     * Gets the name.
     * 
     * @return the name
     */
    @Column(name = "NAME")
    @NotAudited
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the bank address.
     * 
     * @return the bankAddress
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bank")
    @NotAudited
    public Set<BankAddress> getBankAddress() {
        return bankAddress;
    }

    /**
     * Sets the bank address.
     * 
     * @param bankAddress the bankAddress to set
     */
    public void setBankAddress(Set<BankAddress> bankAddress) {
        this.bankAddress = bankAddress;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("Bank ( ").append("id = ").append(this.id)
                .append(tab).append("name = ").append(this.name).append(tab)
                .append("bankAddress = ").append(this.bankAddress).append(tab)
                .append(super.toString()).append(" )").toString();
    }
}
