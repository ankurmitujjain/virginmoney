package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the Pages data to be persisted.
 * 
 * @author Diptirmaya Rout
 */

@NamedNativeQuery(name = "fetchFundraiserPages",
		resultClass = Page.class,
		query = " SELECT p.* "
        	+ " 	     	FROM PAGE p, FUNDRAISING_CHARITY_SPLIT fc, FUNDRAISER_ACTIVITY fa "
        	+ " 	     	WHERE p.FUNDRAISER_ACTIVITY_ID = fa.ID "
        	+ "      			AND fa.ID = fc.FUNDRAISER_ACTIVITY_ID "
        	+ "      			AND fc.CHARITY_ID = :charityId "
        	+ "      			AND p.FUNDRAISER_ID  = :fundraiserId   "
			+ " 	UNION "
			+ " 			SELECT p.* "
			+ " 			FROM FUNDRAISER_GROUP_MEMBER fgm, FUNDRAISER_GROUP fg, "
			+ "		       			FUNDRAISER_ACTIVITY fa, PAGE p, FUNDRAISING_CHARITY_SPLIT fcs "
			+ " 			WHERE  fcs.FUNDRAISER_ACTIVITY_ID = fa.ID" 
			+ " 		        AND fgm.FUNDRAISER_GROUP_ID = fg.ID "
			+ " 		        AND fa.FUNDRAISER_GROUP_ID = fg.ID "
			+ " 		        AND p.FUNDRAISER_ACTIVITY_ID = fa.ID "
			+ " 		        AND p.EXPIRED_DATETIME IS NULL "
			+ " 		        AND p.PAGE_STATUS_CODE = 'ACT' "
			+ " 		        AND fcs.CHARITY_ID = :charityId "
			+ "					AND fgm.FUNDRAISER_ID = :fundraiserId ")
@Entity
@Table(name = "PAGE", uniqueConstraints=
    @UniqueConstraint(columnNames={"FUNDRAISER_ACTIVITY_ID"}))
public class Page extends AuditAttributes implements Serializable {

    /** Default SErial Id. */
    private static final long serialVersionUID = 1L;

    /** Primary key for Page. */
    private Long id;

    /** Pagetitle for page. */
    private String title;

    /** Pagebody for page. */
    private String body;

    /** The message. */
    private String message;
    
     /** Published date time. */
    private Timestamp publishedDateTime;

    /** Expired date time. */
    private Timestamp expiredDateTime;
    
    
    /** pagestatus of page. */
    private PageStatus pageStatus;

    /** pageType *. */
    private PageType pageType;
    
    /** Charity. */
    private Charity charity;

    
    /** charitableActivity *. */
    private CharitableActivity charitableActivity;

    /** fundraiserActivity *. */
    private FundraiserActivity fundraiserActivity;

    /** Fundraiser. */
    private Fundraiser fundraiser;

    /** event *. */
    private Event event;

    /** url *. */
    private String url;

    /** The page widgets. */
    private Set<PageWidget> pageWidgets;

 
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
     * Gets the title.
     * 
     * @return the title
     */

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * 
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the body.
     * 
     * @return the body
     */

    @Column(name = "BODY_CONTENT", columnDefinition = "text")
    public String getBody() {
        return body;
    }

    /**
     * Sets the body.
     * 
     * @param bodyContent the bodyContent to set
     */
    public void setBody(String bodyContent) {
        this.body = bodyContent;
    }
    
       /**
        * Gets the published date time.
        * 
        * @return the publishedDateTime
        */
    @Column(name = "PUBLISHED_DATETIME")
    public Timestamp getPublishedDateTime() {
        return publishedDateTime;
    }

    /**
     * Sets the published date time.
     * 
     * @param publishedDateTime the publishedDateTime to set
     */
    public void setPublishedDateTime(Timestamp publishedDateTime) {
        this.publishedDateTime = publishedDateTime;
    }

    /**
     * Gets the expired date time.
     * 
     * @return the expiredDateTime
     */
    @Column(name = "EXPIRED_DATETIME")
    public Timestamp getExpiredDateTime() {
        return expiredDateTime;
    }

    /**
     * Sets the expired date time.
     * 
     * @param expiredDateTime the expiredDateTime to set
     */
    public void setExpiredDateTime(Timestamp expiredDateTime) {
        this.expiredDateTime = expiredDateTime;
    }

    /**
     * Gets the page status.
     * 
     * @return the status
     */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAGE_STATUS_CODE")
    public PageStatus getPageStatus() {
        return pageStatus;
    }

    /**
     * Sets the page status.
     * 
     * @param pageStatus the new page status
     */
    public void setPageStatus(PageStatus pageStatus) {
        this.pageStatus = pageStatus;
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
     * Gets the charity.
     * 
     * @return the charity
     */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_ID")
    public Charity getCharity() {
        return charity;
    }

    /**
     * Gets the page type.
     * 
     * @return the pageType object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAGE_TYPE_CODE")
    public PageType getPageType() {
        return pageType;
    }

    /**
     * Sets the page type.
     * 
     * @param pageType the page type
     */
    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    /**
     * Gets the charitable activity.
     * 
     * @return the charitableActivity  object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITABLE_ACTIVITY_ID")
    public CharitableActivity getCharitableActivity() {
        return charitableActivity;
    }

    /**
     * Sets the charitable activity.
     * 
     * @param charitableActivity the charitable activity
     */
    public void setCharitableActivity(CharitableActivity charitableActivity) {
        this.charitableActivity = charitableActivity;
    }

    /**
     * Gets the fundraiser activity.
     * 
     * @return the fundraiserActivity object
     */
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_ACTIVITY_ID")
    public FundraiserActivity getFundraiserActivity() {
        return fundraiserActivity;
    }

    /**
     * Sets the fundraiser activity.
     * 
     * @param fundraiserActivity the fundraiser activity
     */
    public void setFundraiserActivity(FundraiserActivity fundraiserActivity) {
        this.fundraiserActivity = fundraiserActivity;
    }

    /**
     * Gets the fundraiser.
     * 
     * @return the fundraiser object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_ID") 
    public Fundraiser getFundraiser() {
        return fundraiser;
    }

    /**
     * Sets the fundraiser.
     * 
     * @param fundraiser the fundraiser
     */
    public void setFundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
    }

    /**
     * Gets the event.
     * 
     * @return the evenet object
     */    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_ID")
    public Event getEvent() {
        return event;
    }

    /**
     * Sets the event.
     * 
     * @param event the event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Gets the url.
     * 
     * @return URL
     */
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     * 
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Return the message.
     * 
     * @return the message
     */
    @Column(name = "MESSAGE", columnDefinition = "text")
    public String getMessage() {
        return message;
    }

    /**
     * Set the message.
     * 
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the page widgets.
     * 
     * @return the page widgets
     */
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="page")
    @NotAudited
    public Set<PageWidget> getPageWidgets() {
        return pageWidgets;
    }

    /**
     * Sets the page widgets.
     * 
     * @param pageWidgets the new page widgets
     */
    public void setPageWidgets(Set<PageWidget> pageWidgets) {
        this.pageWidgets = pageWidgets;
    }
    



    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("Page ( ")
                .append("id = ").append(this.id).append(tab)
                .append("title = ").append(this.title).append(tab)
                .append("body = ").append(this.body).append(tab)
                .append("publishedDateTime = ").append(this.publishedDateTime).append(tab)
                .append("expiredDateTime = ").append(this.expiredDateTime).append(tab)
                .append("pageStatus = ").append(this.pageStatus).append(tab)
                .append("pageType = ").append(this.pageType).append(tab)
                .append("charity = ").append(this.charity).append(tab)
                .append("charitableActivity = ").append(this.charitableActivity).append(tab)
                .append("fundraiserActivity = ").append(this.fundraiserActivity).append(tab)
                .append("fundraiser = ").append(this.fundraiser).append(tab)
                .append("event = ").append(this.event).append(tab)
                .append("url = ").append(this.url).append(tab)
                .append("message = ").append(this.message).append(tab)
                .append(super.toString()).append(" )")
                .toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Page == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Page rhs = (Page) obj;
        return new EqualsBuilder()
            .append(url, rhs.url)
            .append(pageType, rhs.pageType)
            .isEquals();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(135, 531)
        .append(url)
        .append(pageType)
        .toHashCode();
    }
}
