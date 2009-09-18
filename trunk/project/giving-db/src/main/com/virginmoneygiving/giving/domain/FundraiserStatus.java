package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;

/**
 * Domain class for Fundraiser status.
 * 
 * @author Vikas Kale
 */
// both JPA and Hibernate Entity annotation is used for it defines mutability
// for the entity
@javax.persistence.Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "FUNDRAISER_STATUS")
public class FundraiserStatus extends BaseAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = 1187055095347162911L;

    /** Fundraiser status code. */
    private String code;

    /** Fundraiser Status code description. */
    private String description;

    /**
     * Getter method for Fundraiser status code.
     * 
     * @return String the code.
     */
    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    /**
     * Setter method for Fundraiser status code.
     * 
     * @param code for Fundraiser status.
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Getter method for Fundraiser status code description.
     * 
     * @return description for fundraiser status code.
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for Fundraiser status code description.
     * 
     * @param description for fundraiser status code.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Default Constructor.
     */
    public FundraiserStatus() {
        // Empty constructor.
    }

    /**
     * Full Constructor.
     * 
     * @param code the code
     * @param description the description
     */
    public FundraiserStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /** The FUNDRAISE r_ statu s_ closed. */
    public static FundraiserStatus FUNDRAISER_STATUS_CLOSED =
            new FundraiserStatus(
                    MasterDataCodeConstants.FUNDRAISER_STATUS_CODE_CLOSED,
                    "Fundraiser closed.");

}
