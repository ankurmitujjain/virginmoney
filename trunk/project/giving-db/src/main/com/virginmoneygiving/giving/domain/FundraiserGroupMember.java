package com.virginmoneygiving.giving.domain;

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
 * The data object that represents the Fundraising Team data to be persisted.
 * 
 * @author esakkiy.
 */

@Entity
@Table(name = "FUNDRAISER_GROUP_MEMBER")
public class FundraiserGroupMember extends AssociationAuditAttributes {

    /** serial version UID. */
    private static final long serialVersionUID = -3608364457419720217L;

    /** Primary key for FUNDRAISING_TEAM_MEMBER. */
    private Long id;

    /** FUNDRAISING_TEAM_MEMBER Team Owner flag. */
    private boolean owner;

    /** FUNDRAISING_TEAM_MEMBER Invited email Addresses. */
    private String invitedEmailAddress;

    /** creating FundraiserGroup object for relationship. */
    private FundraiserGroup fundraiserGroup;

    /** The {@link Fundraiser} for the activity. */
    private Fundraiser fundraiser;

    /**
     * Getter method for FUNDRAISING_TEAM_MEMBER id.
     * 
     * @return Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    /**
     * Setter method for FUNDRAISING_TEAM_MEMBER id.
     * 
     * @param id of FUNDRAISING_TEAM_MEMBER.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Checks if is owner.
     * 
     * @return the owner
     */
    @Column(name = "OWNER")
    public boolean isOwner() {
        return owner;
    }

    /**
     * Sets the owner.
     * 
     * @param owner the owner to set
     */
    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    /**
     * Gets the invited email address.
     * 
     * @return the invitedEmailAddresses
     */
    @Column(name = "INVITED_EMAIL_ADDRESS")
    public String getInvitedEmailAddress() {
        return invitedEmailAddress;
    }

    /**
     * Sets the invited email address.
     * 
     * @param invitedEmailAddress the invitedEmailAddresses to set
     */
    public void setInvitedEmailAddress(String invitedEmailAddress) {
        this.invitedEmailAddress = invitedEmailAddress;
    }

    /**
     * Gets the fundraiser group.
     * 
     * @return the fundraiserGroup
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_GROUP_ID")
    public FundraiserGroup getFundraiserGroup() {
        return fundraiserGroup;
    }

    /**
     * Sets the fundraiser group.
     * 
     * @param fundraiserGroup the fundraiserGroup to set
     */
    public void setFundraiserGroup(FundraiserGroup fundraiserGroup) {
        this.fundraiserGroup = fundraiserGroup;
    }

    /**
     * Gets the fundraiser.
     * 
     * @return the fundraiser
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_ID")
    public Fundraiser getFundraiser() {
        return fundraiser;
    }

    /**
     * Sets the fundraiser.
     * 
     * @param fundraiser the fundraiser to set
     */
    public void setFundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
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
        return new StringBuilder("FundraiserGroupMember ( ").append("id = ").append(id)
                .append(tab).append("invitedEmailAddress = ").append(
                        invitedEmailAddress).append(tab).append("owner = ")
                .append(owner).append(tab).append("fundraiserGroup = ").append(
                        fundraiserGroup).append(tab).append("fundraiser = ")
                .append(fundraiser).append(tab).append(super.toString())
                .append(" )").toString();
    }
}
