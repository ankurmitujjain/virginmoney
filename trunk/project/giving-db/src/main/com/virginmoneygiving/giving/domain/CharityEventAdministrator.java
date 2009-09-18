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
 * The domain object represents those Charity Admins who have been assigned to a
 * particular event.
 */
@Entity
@Table(name = "CHARITY_EVENT_ADMINISTRATOR")
public class CharityEventAdministrator extends AssociationAuditAttributes implements
        Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -1L;

    /** Primary key for event_activity table. */
    private Long id;

    /** Charity admin for the event. */
    private CharityAdministrator charityAdministrator;

    /** Indicates whether this admin is main amongst all others. */
    private String mainInd;

    /** Linked to which charitable activity. */

    private CharitableActivity charitableActivity;

    /**
     * Get id.
     * 
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    /**
     * Set id.
     * 
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the charity administrator.
     * 
     * @return the charityAdministrator
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_ADMINISTRATOR_ID")
    public CharityAdministrator getCharityAdministrator() {
        return charityAdministrator;
    }

    /**
     * Sets the charity administrator.
     * 
     * @param charityAdministrator the charityAdministrator to set
     */
    public void setCharityAdministrator(
            CharityAdministrator charityAdministrator) {
        this.charityAdministrator = charityAdministrator;
    }

    /**
     * Gets the main ind.
     * 
     * @return the mainInd
     */

    @Column(name = "MAIN_IND")
    public String getMainInd() {
        if (mainInd == null) {
            mainInd = "Y";
        }
        return mainInd;
    }

    /**
     * Sets the main ind.
     * 
     * @param mainInd the mainInd to set
     */
    public void setMainInd(String mainInd) {
        this.mainInd = mainInd;
    }

    /**
     * Gets the charitable activity.
     * 
     * @return the charitableActivity
     */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITABLE_ACTIVITY_ID")
    public CharitableActivity getCharitableActivity() {
        return charitableActivity;
    }

    /**
     * Sets the charitable activity.
     * 
     * @param charitableActivity the charitableActivity to set
     */
    public void setCharitableActivity(CharitableActivity charitableActivity) {
        this.charitableActivity = charitableActivity;
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
        return new StringBuilder("CharityEventAdministrator ( ")
                .append("id = ").append(id).append(tab).append("mainInd= ")
                .append(mainInd).append(tab)
                .append("charityAdministrator = ").append(charityAdministrator).append(tab)
                .append("charitableActivity = ").append(charitableActivity).append(tab)
                .append("id = ").append(id).append(tab)
                .append(super.toString())
                .append(" )").toString();
    } // end of method

} // end of class
