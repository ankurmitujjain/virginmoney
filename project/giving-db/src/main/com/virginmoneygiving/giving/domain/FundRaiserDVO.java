package com.virginmoneygiving.giving.domain;

/**
 * The Class FundRaiserDVO.
 * 
 * @author saurabhh
 * A DVO to save fundraiser details as required on event home page
 */
public class FundRaiserDVO {


/** Fundraisers first name. */
private String foreName; 

/** Fundraisers image url. */
private String imageUrl;

/** Fundraisers home page link. */
private String homePageLink;

/**
 * Gets the fore name.
 * 
 * @return the foreName
 */
public String getForeName() {
	return foreName;
}

/**
 * Sets the fore name.
 * 
 * @param foreName the foreName to set
 */
public void setForeName(String foreName) {
	this.foreName = foreName;
}

/**
 * Gets the image url.
 * 
 * @return the imageUrl
 */
public String getImageUrl() {
	return imageUrl;
}

/**
 * Sets the image url.
 * 
 * @param imageUrl the imageUrl to set
 */
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

/**
 * Gets the home page link.
 * 
 * @return the homePageLink
 */
public String getHomePageLink() {
	return homePageLink;
}

/**
 * Sets the home page link.
 * 
 * @param homePageLink the homePageLink to set
 */
public void setHomePageLink(String homePageLink) {
	this.homePageLink = homePageLink;
}

}//end of class
