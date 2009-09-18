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
 * The data object that represents the bank address data to be persisted.
 * 
 * @author Rahul Vaidya
 */
@Entity
@Table(name = "BANK_ADDRESS")
public class BankAddress extends AssociationAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 5896589892145L;

    /** Primary key for bank address. */
    private Long id;

    /** Bank address. */
    private Address address;

    /** Bank. */
    private Bank bank;

    /** Address type code. */
    private AddressType addressType;

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
     * Gets the address.
     * 
     * @return the address
     */
    /**
     * @return the charity
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID")
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address.
     * 
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the bank.
     * 
     * @return the bank
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ID")
    public Bank getBank() {
        return bank;
    }

    /**
     * Sets the bank.
     * 
     * @param bank the bank to set
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * Gets the address type.
     * 
     * @return the addressType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_TYPE_CODE")
    public AddressType getAddressType() {
        return addressType;
    }

    /**
     * Sets the address type.
     * 
     * @param addressType the addressType to set
     */
    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("BankAddress ( ").append("id = ").append(
                this.id).append(tab).append("address = ").append(this.address)
                .append(tab).append("bank = ").append(this.bank).append(tab)
                .append(super.toString()).append(" )").toString();
    }
}
