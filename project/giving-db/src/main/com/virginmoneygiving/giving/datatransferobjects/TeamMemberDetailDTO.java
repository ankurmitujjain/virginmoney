package com.virginmoneygiving.giving.datatransferobjects;

/**
 * The Data Object which contains data related to team member details.
 * 
 * @author Rohit Mandlik
 */
public class TeamMemberDetailDTO {

    /** emailAddress. */
	private String emailAddress;
    
    /** invitedEmailAddress. */
    private String invitedEmailAddress;
    
    /** foreName. */
    private String foreName;
    
    /** surName. */
    private String surName;
    
    /** fundraiserId. */
    private Long fundraiserId;
    
    /** owner. */
    private boolean owner;

    /**
     * The imageurl and pageUrl are actually activity/page data required together with network data in pages.
     * Hitting limitations of domain model here.
     */
    /** The image url. */
    private String imageUrl;
    /**  **/
    private String pageUrl;

    /** The userUrl */
    private String userUrl;
    
    /** Email address type. */
    private String emailAddressType;
    

    /**
     * Gets the value of the emailAddress property.
     * 
     * @return the email address
     * 
     * possible object is
     * {@link String }
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the value of the emailAddress property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }
    
    /**
     * Gets the value of the invitedEmailAddress property.
     * 
     * @return the invited email address
     * 
     * possible object is
     * {@link String }
     */
    public String getInvitedEmailAddress() {
        return invitedEmailAddress;
    }

    /**
     * Sets the value of the invitedEmailAddress property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    public void setInvitedEmailAddress(String value) {
        this.invitedEmailAddress = value;
    }
    

    /**
     * Gets the value of the foreName property.
     * 
     * @return the fore name
     * 
     * possible object is
     * {@link String }
     */
    public String getForeName() {
        return foreName;
    }

    /**
     * Sets the value of the foreName property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    public void setForeName(String value) {
        this.foreName = value;
    }

    /**
     * Gets the value of the surName property.
     * 
     * @return the sur name
     * 
     * possible object is
     * {@link String }
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets the value of the surName property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    public void setSurName(String value) {
        this.surName = value;
    }

    /**
     * Gets the value of the fundraiserId property.
     * 
     * @return the fundraiser id
     * 
     * possible object is
     * {@link Long }
     */
    public Long getFundraiserId() {
        return fundraiserId;
    }

    /**
     * Sets the value of the fundraiserId property.
     * 
     * @param value allowed object is
     * {@link Long }
     */
    public void setFundraiserId(Long value) {
        this.fundraiserId = value;
    }

    /**
     * Checks if is owner.
     * 
     * @return the owner
     */
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
     * Gets the image url.
     * 
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image url.
     * 
     * @param imageUrl the new image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Get the pageUrl.
     * @return
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * Set the pageUrl.
     * @param pageUrl
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }


    /**
     * Get the userUrl.
     * @return
     */
    public String getUserUrl() {
        return userUrl;
    }

    /**
     * Set the userUrl.
     * @param userUrl
     */
    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

	/**
	 * @return the emailAddressType
	 */
	public String getEmailAddressType() {
		return emailAddressType;
	}

	/**
	 * @param emailAddressType the emailAddressType to set
	 */
	public void setEmailAddressType(String emailAddressType) {
		this.emailAddressType = emailAddressType;
	}
    
    
}
