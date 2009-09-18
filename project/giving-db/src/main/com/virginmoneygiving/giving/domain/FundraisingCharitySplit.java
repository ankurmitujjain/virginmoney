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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * The link between the {@link FundraisingActivity}, {@link Charity} and
 * {@link ActivityType}.
 * 
 * @author Edwin Tauro
 * @author Puneet Swarup - added audit attributes and toString.
 */
@Entity
@Table(name = "FUNDRAISING_CHARITY_SPLIT")
@Audited
public class FundraisingCharitySplit extends EnversAuditAttributes implements
        Serializable {

    /** The default serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Id. */
    private Long id;

    /** The fundraising activity. */
    private FundraiserActivity fundraiserActivity;

    /** The charity. */
    private Charity charity;

    /** The charity split percentage always rounded. */
    private Integer splitPercentage;

    /** Charitable activity. */
    private CharitableActivity charitableActivity;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
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
     * Gets the fundraiser activity.
     * 
     * @return the fundraiserActivity
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_ACTIVITY_ID")
    @Audited
    public FundraiserActivity getFundraiserActivity() {
        return fundraiserActivity;
    }

    /**
     * Sets the fundraiser activity.
     * 
     * @param fundraiserActivity the fundraiserActivity to set
     */
    public void setFundraiserActivity(FundraiserActivity fundraiserActivity) {
        this.fundraiserActivity = fundraiserActivity;
    }

    /**
     * Gets the charity.
     * 
     * @return the charity
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_ID")
    @Audited
    public Charity getCharity() {
        return charity;
    }

    /**
     * Sets the charity.
     * 
     * @param charity the charity to set
     */
    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    /**
     * Gets the split percentage.
     * 
     * @return the splitPercentage
     */
    @Column(name = "SPLIT_PERCENTAGE")
    @Audited
    public Integer getSplitPercentage() {
        return splitPercentage;
    }

    /**
     * Sets the split percentage.
     * 
     * @param splitPercentage the splitPercentage to set
     */
    public void setSplitPercentage(Integer splitPercentage) {
        this.splitPercentage = splitPercentage;
    }

    /**
     * Gets the charitable activity.
     * 
     * @return the charitableActivity
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITABLE_ACTIVITY_ID")
    @Audited
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
        return new StringBuilder("FundraisingCharitySplit ( ").append("id = ")
                .append(id).append(tab).append("splitPercentage = ").append(
                        splitPercentage).append(tab).append(super.toString())
                .append(" )").toString();
    }
}
