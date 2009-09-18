package com.virginmoneygiving.giving.domain;

import java.io.Serializable;


public class TeamMemberDetail implements Serializable{
    
	 private Long teamMemberId;
	    private String emailAddress;
	    private String foreName;
	    private String surName;
	    private String invitedEmailAddress;
	    private Long fundraiserId;
	    private String owner;
	    private String imageUrl;
	    private String userUrl;
	    private String pageUrl;
		/**
		 * @return the teamMemberId
		 */
		public Long getTeamMemberId() {
			return teamMemberId;
		}
		/**
		 * @param teamMemberId the teamMemberId to set
		 */
		public void setTeamMemberId(Long teamMemberId) {
			this.teamMemberId = teamMemberId;
		}
		/**
		 * @return the emailAddress
		 */
		public String getEmailAddress() {
			return emailAddress;
		}
		/**
		 * @param emailAddress the emailAddress to set
		 */
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
		/**
		 * @return the foreName
		 */
		public String getForeName() {
			return foreName;
		}
		/**
		 * @param foreName the foreName to set
		 */
		public void setForeName(String foreName) {
			this.foreName = foreName;
		}
		/**
		 * @return the surName
		 */
		public String getSurName() {
			return surName;
		}
		/**
		 * @param surName the surName to set
		 */
		public void setSurName(String surName) {
			this.surName = surName;
		}
		/**
		 * @return the invitedEmailAddress
		 */
		public String getInvitedEmailAddress() {
			return invitedEmailAddress;
		}
		/**
		 * @param invitedEmailAddress the invitedEmailAddress to set
		 */
		public void setInvitedEmailAddress(String invitedEmailAddress) {
			this.invitedEmailAddress = invitedEmailAddress;
		}
		/**
		 * @return the fundraiserId
		 */
		public Long getFundraiserId() {
			return fundraiserId;
		}
		/**
		 * @param fundraiserId the fundraiserId to set
		 */
		public void setFundraiserId(Long fundraiserId) {
			this.fundraiserId = fundraiserId;
		}
		/**
		 * @return the owner
		 */
		public String getOwner() {
			return owner;
		}
		/**
		 * @param owner the owner to set
		 */
		public void setOwner(String owner) {
			this.owner = owner;
		}
		/**
		 * @return the imageUrl
		 */
		public String getImageUrl() {
			return imageUrl;
		}
		/**
		 * @param imageUrl the imageUrl to set
		 */
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		/**
		 * @return the userUrl
		 */
		public String getUserUrl() {
			return userUrl;
		}
		/**
		 * @param userUrl the userUrl to set
		 */
		public void setUserUrl(String userUrl) {
			this.userUrl = userUrl;
		}
		/**
		 * @return the pageUrl
		 */
		public String getPageUrl() {
			return pageUrl;
		}
		/**
		 * @param pageUrl the pageUrl to set
		 */
		public void setPageUrl(String pageUrl) {
			this.pageUrl = pageUrl;
		}

}
