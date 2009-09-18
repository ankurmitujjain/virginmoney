package com.virginmoneygiving.integrationtests.webdriver.fundraiser;

/**
 * The Class FundraiserRegistrationDetails.
 * 
 * @author rohit mandlik
 */
public class FundraiserRegistrationDetails {

    /**
     * Holds information for Fundraiser details.
     */
    public class FundraiserDetails {

        /** The code indicating the reason for fundraising. */
        public String reasonCode;

        /** This property stores theactivityRaisedMoney value in jsp. */
        public String activityRaisedMoney;

        /** eventCompletionDay for eventCompletionDay. */
        public String eventCompletionDay = null;

        /** eventCompletionMonth for eventCompletionDay. */
        public String eventCompletionMonth = null;

        /** eventCompletionYear for eventCompletionDay. */
        public String eventCompletionYear = null;

        /** Single charity identification to raise money for. */
        public String charityId;

        /** Single charity name to raise money for. */
        public String charityName;

        /** charity name for search events. */
        public String searchCharityTextName;

        /** The event location. */
        public String eventLocation;

        /** The address line1. */
        public String addressLine1;
        
        /** The address line2. */
        public String addressLine2;
        
        /** The town. */
        public String town;
        
        /** The county. */
        public String county;
        
        /** The post code. */
        public String postCode;
        
        /** The building name and number. */
        public String buildingNameAndNumber;
        
        /** The preferred telephone number. */
        public String preferredTelephoneNumber;
        
        /** The preferred telephone number type. */
        public String preferredTelephoneNumberType;
        
        /** The email address. */
        public String emailAddress;
        
        /** The terms and conditions accepted. */
        public String termsAndConditionsAccepted;
        
        /** The fundraiser url. */
        public String fundraiserUrl;
        
        /** The page title. */
        public String pageTitle;
        
        /** The personal comment. */
        public String personalComment;

        /**
         * Gets the personal comment.
         * 
         * @return the personalComment
         */
        public String getPersonalComment() {
            return personalComment;
        }

        /**
         * Sets the personal comment.
         * 
         * @param personalComment the personalComment to set
         */
        public void setPersonalComment(String personalComment) {
            this.personalComment = personalComment;
        }

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
         * Gets the fundraiser url.
         * 
         * @return the fundraiserUrl
         */
        public String getFundraiserUrl() {
            return fundraiserUrl;
        }

        /**
         * Sets the fundraiser url.
         * 
         * @param fundraiserUrl the fundraiserUrl to set
         */
        public void setFundraiserUrl(String fundraiserUrl) {
            this.fundraiserUrl = fundraiserUrl;
        }

        /**
         * Gets the terms and conditions accepted.
         * 
         * @return the termsAndConditionsAccepted
         */
        public String getTermsAndConditionsAccepted() {
            return termsAndConditionsAccepted;
        }

        /**
         * Sets the terms and conditions accepted.
         * 
         * @param termsAndConditionsAccepted the termsAndConditionsAccepted to set
         */
        public void setTermsAndConditionsAccepted(String termsAndConditionsAccepted) {
            this.termsAndConditionsAccepted = termsAndConditionsAccepted;
        }

        /**
         * Gets the email address.
         * 
         * @return the emailAddress
         */
        public String getEmailAddress() {
            return emailAddress;
        }

        /**
         * Sets the email address.
         * 
         * @param emailAddress the emailAddress to set
         */
        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        /**
         * Gets the preferred telephone number type.
         * 
         * @return the preferredTelephoneNumberType
         */
        public String getPreferredTelephoneNumberType() {
            return preferredTelephoneNumberType;
        }

        /**
         * Sets the preferred telephone number type.
         * 
         * @param preferredTelephoneNumberType the preferredTelephoneNumberType to set
         */
        public void setPreferredTelephoneNumberType(String preferredTelephoneNumberType) {
            this.preferredTelephoneNumberType = preferredTelephoneNumberType;
        }

        /**
         * Gets the preferred telephone number.
         * 
         * @return the preferredTelephoneNumber
         */
        public String getPreferredTelephoneNumber() {
            return preferredTelephoneNumber;
        }

        /**
         * Sets the preferred telephone number.
         * 
         * @param preferredTelephoneNumber the preferredTelephoneNumber to set
         */
        public void setPreferredTelephoneNumber(String preferredTelephoneNumber) {
            this.preferredTelephoneNumber = preferredTelephoneNumber;
        }

        /**
         * Gets the address line1.
         * 
         * @return the addressLine1
         */
        public String getAddressLine1() {
            return addressLine1;
        }

        /**
         * Sets the address line1.
         * 
         * @param addressLine1 the addressLine1 to set
         */
        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }

        /**
         * Gets the address line2.
         * 
         * @return the addressLine2
         */
        public String getAddressLine2() {
            return addressLine2;
        }

        /**
         * Sets the address line2.
         * 
         * @param addressLine2 the addressLine2 to set
         */
        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }

        /**
         * Gets the town.
         * 
         * @return the town
         */
        public String getTown() {
            return town;
        }

        /**
         * Sets the town.
         * 
         * @param town the town to set
         */
        public void setTown(String town) {
            this.town = town;
        }

        /**
         * Gets the county.
         * 
         * @return the county
         */
        public String getCounty() {
            return county;
        }

        /**
         * Sets the county.
         * 
         * @param county the county to set
         */
        public void setCounty(String county) {
            this.county = county;
        }

        /**
         * Gets the post code.
         * 
         * @return the postCode
         */
        public String getPostCode() {
            return postCode;
        }

        /**
         * Sets the post code.
         * 
         * @param postCode the postCode to set
         */
        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        /**
         * Gets the building name and number.
         * 
         * @return the buildingNameAndNumber
         */
        public String getBuildingNameAndNumber() {
            return buildingNameAndNumber;
        }

        /**
         * Sets the building name and number.
         * 
         * @param buildingNameAndNumber the buildingNameAndNumber to set
         */
        public void setBuildingNameAndNumber(String buildingNameAndNumber) {
            this.buildingNameAndNumber = buildingNameAndNumber;
        }

        /**
         * Gets the reason code.
         * 
         * @return the reasonCode
         */
        public String getReasonCode() {
            return reasonCode;
        }

        /**
         * Sets the reason code.
         * 
         * @param reasonCode the reasonCode to set
         */
        public void setReasonCode(String reasonCode) {
            this.reasonCode = reasonCode;
        }

        /**
         * Gets the activity raised money.
         * 
         * @return the activityRaisedMoney
         */
        public String getActivityRaisedMoney() {
            return activityRaisedMoney;
        }

        /**
         * Sets the activity raised money.
         * 
         * @param activityRaisedMoney the activityRaisedMoney to set
         */
        public void setActivityRaisedMoney(String activityRaisedMoney) {
            this.activityRaisedMoney = activityRaisedMoney;
        }

        /**
         * Gets the event completion day.
         * 
         * @return the eventCompletionDay
         */
        public String getEventCompletionDay() {
            return eventCompletionDay;
        }

        /**
         * Sets the event completion day.
         * 
         * @param eventCompletionDay the eventCompletionDay to set
         */
        public void setEventCompletionDay(String eventCompletionDay) {
            this.eventCompletionDay = eventCompletionDay;
        }

        /**
         * Gets the event completion month.
         * 
         * @return the eventCompletionMonth
         */
        public String getEventCompletionMonth() {
            return eventCompletionMonth;
        }

        /**
         * Sets the event completion month.
         * 
         * @param eventCompletionMonth the eventCompletionMonth to set
         */
        public void setEventCompletionMonth(String eventCompletionMonth) {
            this.eventCompletionMonth = eventCompletionMonth;
        }

        /**
         * Gets the event completion year.
         * 
         * @return the eventCompletionYear
         */
        public String getEventCompletionYear() {
            return eventCompletionYear;
        }

        /**
         * Sets the event completion year.
         * 
         * @param eventCompletionYear the eventCompletionYear to set
         */
        public void setEventCompletionYear(String eventCompletionYear) {
            this.eventCompletionYear = eventCompletionYear;
        }

        /**
         * Gets the charity id.
         * 
         * @return the charityId
         */
        public String getCharityId() {
            return charityId;
        }

        /**
         * Sets the charity id.
         * 
         * @param charityId the charityId to set
         */
        public void setCharityId(String charityId) {
            this.charityId = charityId;
        }

        /**
         * Gets the charity name.
         * 
         * @return the charityName
         */
        public String getCharityName() {
            return charityName;
        }

        /**
         * Sets the charity name.
         * 
         * @param charityName the charityName to set
         */
        public void setCharityName(String charityName) {
            this.charityName = charityName;
        }

    }

    /**
     * Holds information for charity administrator details.
     */
    public class AdministratorDetails {
        
        /** The title. */
        public String title;
        
        /** The first name. */
        public String firstName;
        
        /** The last name. */
        public String lastName;
        
        /** The occupation. */
        public String occupation;
        
        /** The telephone number. */
        public String telephoneNumber;
        
        /** The email. */
        public String email;
        
        /** The accept to c. */
        public boolean acceptToC;

        /**
         * Sets the title.
         * 
         * @param title the new title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * Sets the first name.
         * 
         * @param firstName the new first name
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * Sets the last name.
         * 
         * @param lastName the new last name
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         * Sets the occupation.
         * 
         * @param occupation the new occupation
         */
        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        /**
         * Sets the telephone number.
         * 
         * @param telephoneNumber the new telephone number
         */
        public void setTelephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
        }

        /**
         * Sets the email.
         * 
         * @param email the new email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * Sets the accept to c.
         * 
         * @param acceptToC the new accept to c
         */
        public void setAcceptToC(boolean acceptToC) {
            this.acceptToC = acceptToC;
        }
    }

    /**
     * Holds information for charity administrator security details.
     */
    public class SecurityDetails {
        
        /** The dob day. */
        public String dobDay;
        
        /** The dob month. */
        public String dobMonth;
        
        /** The dob year. */
        public String dobYear;
        
        /** The password. */
        public String password;
        
        /** The confirm password. */
        public String confirmPassword;

        /**
         * Sets the dob day.
         * 
         * @param dobDay the new dob day
         */
        public void setDobDay(String dobDay) {
            this.dobDay = dobDay;
        }

        /**
         * Sets the dob month.
         * 
         * @param dobMonth the new dob month
         */
        public void setDobMonth(String dobMonth) {
            this.dobMonth = dobMonth;
        }

        /**
         * Sets the dob year.
         * 
         * @param dobYear the new dob year
         */
        public void setDobYear(String dobYear) {
            this.dobYear = dobYear;
        }

        /**
         * Sets the password.
         * 
         * @param password the new password
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * Gets the confirm password.
         * 
         * @return the confirmPassword
         */
        public String getConfirmPassword() {
            return confirmPassword;
        }

        /**
         * Sets the confirm password.
         * 
         * @param confirmPassword the confirmPassword to set
         */
        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

    }

    /** CharityDetails instance. */
    public FundraiserDetails fundraiserDetails = new FundraiserDetails();
    
    /** AdministratorDetails instance. */
    public AdministratorDetails administratorDetails = new AdministratorDetails();
    
    /** SecurityDetails instance. */
    public SecurityDetails securityDetails = new SecurityDetails();

    /**
     * Gets the fundraiser details.
     * 
     * @return the fundraiserDetails
     */
    public FundraiserDetails getFundraiserDetails() {
        return fundraiserDetails;
    }

    /**
     * Sets the fundraiser details.
     * 
     * @param fundraiserDetails the fundraiserDetails to set
     */
    public void setFundraiserDetails(FundraiserDetails fundraiserDetails) {
        this.fundraiserDetails = fundraiserDetails;
    }

    /**
     * Gets the administrator details.
     * 
     * @return the administratorDetails
     */
    public AdministratorDetails getAdministratorDetails() {
        return administratorDetails;
    }

    /**
     * Sets the administrator details.
     * 
     * @param administratorDetails the administratorDetails to set
     */
    public void setAdministratorDetails(AdministratorDetails administratorDetails) {
        this.administratorDetails = administratorDetails;
    }

    /**
     * Gets the security details.
     * 
     * @return the securityDetails
     */
    public SecurityDetails getSecurityDetails() {
        return securityDetails;
    }

    /**
     * Sets the security details.
     * 
     * @param securityDetails the securityDetails to set
     */
    public void setSecurityDetails(SecurityDetails securityDetails) {
        this.securityDetails = securityDetails;
    }

}
