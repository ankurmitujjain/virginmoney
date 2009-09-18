package com.virginmoneygiving.giving.vo;

import java.io.Serializable;
import java.util.List;


/**
 * Class to hold user's comoon details to use in thorugh out application. This
 * will be used on all the layers (Presentaion, Orchastration, Persistnce).
 * 
 * @author vikas kale
 */
/**
 * @author vikask
 *
 */
public class VMGUserDetails implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -2916732379160809989L;

    /** To hold user id. */
    private Long userId;

    /** To hold login email address. */
    private String loginEmailAddress;

    /** To hold username. */
    private String username;

    /** To hold user's title. */
    private String title;

    /** To hold user's forename. */
    private String forename;

    /** To hold user's surname. */
    private String surname;

    /** To hold user's day of birth. */
    private Integer dayOfBirth;

    /** To hold user's month of birth. */
    private Integer monthOfBirth;

    /** To hold user's year of birth. */
    private Integer yearOfBirth;

    /** To hold charity id. */
    private Long charityId;

    /** To hold charity name. */
    private String charityName;

    /** To hold user's registered charity number. */
    private String registeredCharityNumber;

    /** To hold charity status. */
    private String charityStatus;

    /** To hold fundraiser Id. */
    private Long fundraiserId;

    /** To hold donor id. */
    private Long donorId;

    /** To hold list of event id's associated with user. */
    private List<Long> eventIds;

    /** To hold roles and permission. */
    private List<String> roleAndPermissions;
    
    /** To hold Id of person table. */
    private Long personId;
    
    /** To hold fundraiser url. */
    private String fundraiserUrl;



    /**
     * Getter method for userId.
     * 
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter method for userId.
     * 
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter method for loginEmailAddress.
     * 
     * @return the loginEmailAddress
     */
    public String getLoginEmailAddress() {
        return loginEmailAddress;
    }

    /**
     * Setter method for loginEmailAddress.
     * 
     * @param loginEmailAddress the loginEmailAddress to set
     */
    public void setLoginEmailAddress(String loginEmailAddress) {
        this.loginEmailAddress = loginEmailAddress;
    }

    /**
     * Getter method for title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for title.
     * 
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for forename.
     * 
     * @return the forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Setter method for forename.
     * 
     * @param forename the forename to set
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Getter method for surname.
     * 
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter method for surname.
     * 
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter method for dayOfBirth.
     * 
     * @return the dayOfBirth
     */
    public Integer getDayOfBirth() {
        return dayOfBirth;
    }

    /**
     * Setter method for dayOfBirth.
     * 
     * @param dayOfBirth the dayOfBirth to set
     */
    public void setDayOfBirth(Integer dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    /**
     * Getter method for monthOfBirth.
     * 
     * @return the monthOfBirth
     */
    public Integer getMonthOfBirth() {
        return monthOfBirth;
    }

    /**
     * Setter method for monthOfBirth.
     * 
     * @param monthOfBirth the monthOfBirth to set
     */
    public void setMonthOfBirth(Integer monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    /**
     * Getter method for yearOfBirth.
     * 
     * @return the yearOfBirth
     */
    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Setter method for yearOfBirth.
     * 
     * @param yearOfBirth the yearOfBirth to set
     */
    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Getter method for charityId.
     * 
     * @return the charityId
     */
    public Long getCharityId() {
        return charityId;
    }

    /**
     * Setter method for charityId.
     * 
     * @param charityId the charityId to set
     */
    public void setCharityId(Long charityId) {
        this.charityId = charityId;
    }

    /**
     * Getter method for charityName.
     * 
     * @return the charityName
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Setter method for charityName.
     * 
     * @param charityName the charityName to set
     */
    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    /**
     * Getter method for registeredCharityNumber.
     * 
     * @return the registeredCharityNumber
     */
    public String getRegisteredCharityNumber() {
        return registeredCharityNumber;
    }

    /**
     * Setter method for registeredCharityNumber.
     * 
     * @param registeredCharityNumber the registeredCharityNumber to set
     */
    public void setRegisteredCharityNumber(String registeredCharityNumber) {
        this.registeredCharityNumber = registeredCharityNumber;
    }

    /**
     * Getter method for charityStatus.
     * 
     * @return the charityStatus
     */
    public String getCharityStatus() {
        return charityStatus;
    }

    /**
     * Setter method for charityStatus.
     * 
     * @param charityStatus the charityStatus to set
     */
    public void setCharityStatus(String charityStatus) {
        this.charityStatus = charityStatus;
    }

    /**
     * Getter method for fundraiserId.
     * 
     * @return the fundraiserId
     */
    public Long getFundraiserId() {
        return fundraiserId;
    }

    /**
     * Setter method for fundraiserId.
     * 
     * @param fundraiserId the fundraiserId to set
     */
    public void setFundraiserId(Long fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    /**
     * Getter method for donorId.
     * 
     * @return the donorId
     */
    public Long getDonorId() {
        return donorId;
    }

    /**
     * Setter method for donorId.
     * 
     * @param donorId the donorId to set
     */
    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    /**
     * Getter method for eventIds.
     * 
     * @return the eventIds
     */
    public List<Long> getEventIds() {
        return eventIds;
    }

    /**
     * Setter method for eventIds.
     * 
     * @param eventIds the eventIds to set
     */
    public void setEventIds(List<Long> eventIds) {
        this.eventIds = eventIds;
    }

    /**
     * Getter method for roleAndPermissions.
     * 
     * @return the roleAndPermissions
     */
    public List<String> getRoleAndPermissions() {
        return roleAndPermissions;
    }

    /**
     * Setter method for roleAndPermissions.
     * 
     * @param roleAndPermissions the roleAndPermissions to set
     */
    public void setRoleAndPermissions(List<String> roleAndPermissions) {
        this.roleAndPermissions = roleAndPermissions;
    }
    
    /**
     * Getter method for username.
     * 
     * @return username.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Setter method for username.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for personId.
     * 
     * @return the personId
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * Setter method for  personId.
     * 
     * @param personId the personId to set
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * Getter method for fundraiserUrl.
     * 
     * @return the fundraiserUrl
     */
    public String getFundraiserUrl() {
        return fundraiserUrl;
    }

    /**
     * Setter method for fundraiserUrl.
     * 
     * @param fundraiserUrl the fundraiserUrl to set
     */
    public void setFundraiserUrl(String fundraiserUrl) {
        this.fundraiserUrl = fundraiserUrl;
    }
    
    
}
