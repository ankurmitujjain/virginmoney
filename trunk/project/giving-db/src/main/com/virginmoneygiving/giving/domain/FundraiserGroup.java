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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;

/**
 * The data object that represents the Fundraising Team data to be persisted.
 * 
 * @author esakkiy.
 */

@Entity
@Table(name = "FUNDRAISER_GROUP")
public class FundraiserGroup extends AssociationAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -3608364457419720217L;

    /** Primary key for FUNDRAISER_GROUP. */
    private Long id;

    /** FUNDRAISER_GROUP Team name. */
    private String name;

    /** FUNDRAISER_GROUP Team description. */
    private String description;

    /** URL of the fundraiser. */
    private UrlDetails urlDetails;

    /** creating fundraiserGroupType object for relationship. */
    private FundraiserGroupType fundraiserGroupType;

    /** The group members. */
    private Set<FundraiserGroupMember> groupMembers;

    /**
     * Getter method for FUNDRAISER_GROUP id.
     * 
     * @return FUNDRAISER_GROUP Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    /**
     * Setter method for FUNDRAISER_GROUP id.
     * 
     * @param id of FUNDRAISER_GROUP.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    @Column(name = "TEAM_NAME")
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
     * Gets the description.
     * 
     * @return the description
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the {@link UrlDetails} of the {@link Fundraiser}.
     * 
     * @return the urlDetails
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "URL_DETAILS_ID")
    public UrlDetails getUrlDetails() {
        return urlDetails;
    }

    /**
     * Sets the {@link UrlDetails} of the {@link Fundraiser}.
     * 
     * @param urlDetails the urlDetails to set
     */
    public void setUrlDetails(UrlDetails urlDetails) {
        this.urlDetails = urlDetails;
    }

    /**
     * Gets the fundraiser group type.
     * 
     * @return the fundraiserGroupType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_GROUP_TYPE_CODE")
    public FundraiserGroupType getFundraiserGroupType() {
        return fundraiserGroupType;
    }

    /**
     * Sets the fundraiser group type.
     * 
     * @param fundraiserGroupType the fundraiserGroupType to set
     */
    public void setFundraiserGroupType(FundraiserGroupType fundraiserGroupType) {
        this.fundraiserGroupType = fundraiserGroupType;
    }

    /**
     * Gets the group members.
     * 
     * @return the group members
     */
    @OneToMany(mappedBy = "fundraiserGroup", fetch = FetchType.LAZY)  
    public Set<FundraiserGroupMember> getGroupMembers() {
        return groupMembers;
    }

    /**
     * Sets the group members.
     * 
     * @param groupMembers the new group members
     */
    public void setGroupMembers(Set<FundraiserGroupMember> groupMembers) {
        this.groupMembers = groupMembers;
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
        return new StringBuilder("FundraiserGroup ( ").append("id = ").append(id)
                .append(tab).append("name = ").append(name).append(tab).append(
                        "description = ").append(description).append(tab)
                .append("urlDetails = ").append(urlDetails).append(tab).append(
                        "fundraiserGroupType = ").append(fundraiserGroupType)
                .append(tab).append(super.toString()).append(" )").toString();
    }
}
