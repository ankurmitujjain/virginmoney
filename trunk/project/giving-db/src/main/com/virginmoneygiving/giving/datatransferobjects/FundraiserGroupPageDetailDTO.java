package com.virginmoneygiving.giving.datatransferobjects;

/**
 * The Data Object which contains data related to fundraiser group page.
 * 
 * @author Rohit Mandlik
 */
public class FundraiserGroupPageDetailDTO {

    /** page title. */
	private String pageTitle;
    
    /** fundraiserId. */
	private Long fundraiserId;
    
    /** page Id. */
    private Long pageId;
    
    /** fundraiserGroupId. */
	private Long fundraiserGroupId;
	
	/** Page url. */
	private String pageUrl;
  
    /**
     * Gets the page title.
     * 
     * @return the pageTitle
     */
	public String getPageTitle() {
		return pageTitle;
	}
	
	/**
	 * Sets the page title.
	 * 
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	/**
	 * Gets the fundraiser id.
	 * 
	 * @return the fundraiserId
	 */
	public Long getFundraiserId() {
		return fundraiserId;
	}
	
	/**
	 * Sets the fundraiser id.
	 * 
	 * @param fundraiserId the fundraiserId to set
	 */
	public void setFundraiserId(Long fundraiserId) {
		this.fundraiserId = fundraiserId;
	}
	
	/**
	 * Gets the page id.
	 * 
	 * @return the pageId
	 */
	public Long getPageId() {
		return pageId;
	}
	
	/**
	 * Sets the page id.
	 * 
	 * @param pageId the pageId to set
	 */
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

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
	 * Gets the page url.
	 * 
	 * @return the page url
	 */
	public String getPageUrl() {
		return pageUrl;
	}
	
	/**
	 * Sets the page url.
	 * 
	 * @param pageUrl the new page url
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
}
