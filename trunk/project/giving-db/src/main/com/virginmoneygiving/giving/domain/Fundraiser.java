package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Fundraiser details for the person who would be involved in raising fund
 * for charities.
 * 
 * @author Edwin Tauro
 * @author Puneet Swarup - added audit attributes and toString.
 */

@NamedNativeQuery(name = "fetchFundraisersByCharityId",
		resultClass = Fundraiser.class,
		query = " SELECT fr.* "
                + " FROM Fundraiser fr , Fundraiser_Activity fra , Fundraising_Charity_Split fcsp "
                + "  WHERE fr.id = fra.fundraiser_id "
                + " AND fra.id = fcsp.fundraiser_Activity_id "
                + " AND fr.fundraiser_Status_code != 'FAKE' "
                + "  AND fcsp.charity_id = :charityId "
                + " AND fr.id in ( "
                + "     SELECT p.fundraiser_id "
                + "      	          FROM PAGE p, FUNDRAISING_CHARITY_SPLIT fc, FUNDRAISER_ACTIVITY fa "
                + "      	          WHERE p.FUNDRAISER_ACTIVITY_ID = fa.ID "
                + "   			            AND fa.ID = fc.FUNDRAISER_ACTIVITY_ID "
                + "   			            AND fc.CHARITY_ID = :charityId "
        	    + "           UNION "
        	    + " 		      SELECT fgm.fundraiser_id "
        	    + " 		                FROM FUNDRAISER_GROUP_MEMBER fgm, FUNDRAISER_GROUP fg, "
        	    + " 	       			            FUNDRAISER_ACTIVITY fa, PAGE p, FUNDRAISING_CHARITY_SPLIT fcs "
        	    + " 		                WHERE  fcs.FUNDRAISER_ACTIVITY_ID = fa.ID "
        	    + " 	                      AND fgm.FUNDRAISER_GROUP_ID = fg.ID "
        	    + " 	                      AND fa.FUNDRAISER_GROUP_ID = fg.ID "
        	    + " 	                      AND p.FUNDRAISER_ACTIVITY_ID = fa.ID "
        	    + " 	                      AND p.EXPIRED_DATETIME IS NULL "
        	    + " 	                      AND p.PAGE_STATUS_CODE = 'ACT' "
        	    + " 	                      AND fcs.CHARITY_ID = :charityId) "
        	    + " GROUP BY fr.id ")
@Entity
@Table(name = "FUNDRAISER")
public class Fundraiser extends AuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -5872113952983305930L;

    /** Primary key for Fundraiser. */
    private Long id;

    /** Fundraiser status code. */
    private FundraiserStatus fundraiserStatus;

    /** The user role which links to the Person to capture the fundraiser details. The link is via the user role. */
    private UserRole userRole;

    /** Activities of the fundraiser. */
    private Set<FundraiserActivity> fundraiserActivities;

    /** URL of the fundraiser. */
    private UrlDetails urlDetails;

    /**
     * Getter Method for fundraiser ID.
     * 
     * @return fundraiser ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public final Long getId() {
        return id;
    }

    /**
     * Setter Method for fundraiser ID.
     * 
     * @param id Fundraiser's Id.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Returns the status of the {@link Fundraiser}.
     * 
     * @return fundraiserStatus the status of the fundraiser.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_STATUS_CODE")
    public FundraiserStatus getFundraiserStatus() {
        return fundraiserStatus;
    }

    /**
     * Setter method for fundraiserStatus.
     * 
     * @param fundraiserStatus status of the fundraiser.
     */
    public void setFundraiserStatus(final FundraiserStatus fundraiserStatus) {
        this.fundraiserStatus = fundraiserStatus;
    }

    /**
     * Returns the {@link UserRole} of the {@link Fundraiser}.
     * 
     * @return UserRole the role for the fundraiser.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ROLE_ID")
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets the {@link UserRole} of the {@link Fundraiser}.
     * 
     * @param userRole the userRole to set
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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
     * Returns the Set of {@link FundraiserActivity}.
     * 
     * @return Set of the {@link FundraiserActivity}.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fundraiser", fetch = FetchType.LAZY)
    public Set<FundraiserActivity> getFundraiserActivities() {
        return fundraiserActivities;
    }

    /**
     * Sets the {@link FundraiserActivity} activity.
     * 
     * @param fundraiserActivities the collection of {@link FundraiserActivity} to set.
     */
    public void setFundraiserActivities(
            Set<FundraiserActivity> fundraiserActivities) {
        this.fundraiserActivities = fundraiserActivities;
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
        return new StringBuilder("Fundraiser ( ").append("id = ").append(id)
                .append(tab).append("fundraiserStatus = ").append(
                        fundraiserStatus).append(tab).append(
                        "fundraiserActivities = ").append(fundraiserActivities)
                .append(tab).append("userRole = ").append(userRole).append(tab)
                .append(super.toString()).append(" )").toString();
    }
}
