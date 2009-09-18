package com.virginmoneygiving.giving.datatransferobjects;

/**
 * The Data Object which contains data related to fundraiser group.
 * 
 * @author Rohit Mandlik
 */
public class FundraiserGroupDetailDTO {

	/** fundraiserId. */
    private Long fundraiserId;

	/** name. */
	private String name;

    /** description. */
    private String description;

	/** url. */
	private String url;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** fundraiserGroupId. */
	private Long fundraiserGroupId;

	/** fundraiserGroupId. */
    private Long groupMemberId;

    /**
     * Gets the fundraiser group id.
     *
     * @return the fundraiserGroupId
     */
	public Long getFundraiserGroupId() {
		return fundraiserGroupId;
	}

	/**
	 * Sets the fundraiser group id.
	 *
	 * @param fundraiserGroupId the fundraiserGroupId to set
	 */
	public void setFundraiserGroupId(Long fundraiserGroupId) {
		this.fundraiserGroupId = fundraiserGroupId;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
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
     * Getter for property 'groupMemberId'.
     *
     * @return the groupMemberId
     */
    public Long getGroupMemberId() {
        return groupMemberId;
    }

    /**
     * Setter for property 'groupMemberId'.
     *
     * @param groupMemberId the groupMemberId to set
     */
    public void setGroupMemberId(Long groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

}
