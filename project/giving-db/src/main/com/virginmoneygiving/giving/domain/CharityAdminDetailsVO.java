package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

/**
 * Value Object to store charity event administrator details.
 * 
 * @author Sushant Sawant
 */
public class CharityAdminDetailsVO implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Charity Administrator Id. */
    private long charityAdminId;
    
    /** Charity Administrator Email Address. */
    private String emailAddress;
    
    /** Charity Administrator Surname. */
    private String surname;
    
    /** Charity Administrator First name. */
    private String foreName;
    
    /** Charity Administrator Name title. */
    private String title;
    
    /** Person id. */
    private long personId;   
    
    /** Specifies whether this admin is part of creator charity of event. */
    private String eventCreatorInd;     
    
    /** Charity Id. */
    private long charityId;      

	/**
	 * Gets the charity admin id.
	 * 
	 * @return the charity admin id
	 */
	public long getCharityAdminId() {
		return charityAdminId;
	}

	/**
	 * Sets the charity admin id.
	 * 
	 * @param charityAdminId the charity admin id
	 */
	public void setCharityAdminId(long charityAdminId) {
		this.charityAdminId = charityAdminId;
	}

	/**
	 * Gets the email address.
	 * 
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the email address.
	 * 
	 * @param emailAddress the email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets the surname.
	 * 
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 * 
	 * @param surname the surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the fore name.
	 * 
	 * @return the fore name
	 */
	public String getForeName() {
		return foreName;
	}

	/**
	 * Sets the fore name.
	 * 
	 * @param foreName the fore name
	 */
	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

    /**
     * Gets the person id.
     * 
     * @return the personId
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Sets the person id.
     * 
     * @param personId the personId to set
     */
    public void setPersonId(long personId) {
        this.personId = personId;
    }

    /**
     * Gets the event creator ind.
     * 
     * @return the eventCreatorInd
     */
    public String getEventCreatorInd() {
        return eventCreatorInd;
    }

    /**
     * Sets the event creator ind.
     * 
     * @param eventCreatorInd the eventCreatorInd to set
     */
    public void setEventCreatorInd(String eventCreatorInd) {
        this.eventCreatorInd = eventCreatorInd;
    }

    /**
     * Gets the charity id.
     * 
     * @return the charityId
     */
    public long getCharityId() {
        return charityId;
    }

    /**
     * Sets the charity id.
     * 
     * @param charityId the charityId to set
     */
    public void setCharityId(long charityId) {
        this.charityId = charityId;
    }


}
