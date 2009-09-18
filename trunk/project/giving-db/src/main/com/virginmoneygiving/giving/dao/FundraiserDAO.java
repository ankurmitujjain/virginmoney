package com.virginmoneygiving.giving.dao;

import java.math.BigDecimal;
import java.util.List;

import com.virginmoneygiving.giving.datatransferobjects.FundraiserGroupDetailDTO;
import com.virginmoneygiving.giving.datatransferobjects.FundraiserGroupPageDetailDTO;
import com.virginmoneygiving.giving.datatransferobjects.TeamMemberDetailDTO;
import com.virginmoneygiving.giving.domain.ActivityType;
import com.virginmoneygiving.giving.domain.AuditAttributes;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.Fundraiser;
import com.virginmoneygiving.giving.domain.FundraiserActivity;
import com.virginmoneygiving.giving.domain.FundraiserDonorVO;
import com.virginmoneygiving.giving.domain.FundraiserGroupMember;
import com.virginmoneygiving.giving.domain.FundrasierURLDetails;
import com.virginmoneygiving.giving.domain.Location;
import com.virginmoneygiving.giving.domain.FundraiserGroup;
import com.virginmoneygiving.giving.domain.Page;
import com.virginmoneygiving.giving.domain.SearchFundraisingEventParameter;
import com.virginmoneygiving.giving.domain.SearchFundraisingEventResult;
import com.virginmoneygiving.giving.domain.FundraiserGroupType;
import com.virginmoneygiving.giving.domain.UrlDetails;
import com.virginmoneygiving.giving.domain.UrlType;
import com.virginmoneygiving.giving.domain.PageStatus;
import com.virginmoneygiving.giving.domain.PageType;
import com.virginmoneygiving.giving.domain.PageWidgetType;
import com.virginmoneygiving.giving.domain.User;
import com.virginmoneygiving.giving.domain.UserProfile;

/**
 * DAO to handle operation related to saving and fetching fundraiser and the
 * fundraiser related details.
 * 
 * @author Edwin Tauro
 * @author Esakki Yadav. - Added couple of method signatures to be used
 * FundraisingEvent search screen.
 */
public interface FundraiserDAO {

    /**
     * Persist the {@link Fundraiser} as a new record.
     * 
     * @param fundraiser the fundraiser details
     * 
     * @return Long the unique identifier of the persisted {@link Fundraiser}.
     */
    Long saveFundraiser(Fundraiser fundraiser);

    /**
     * Fetch all records from the Event table.
     * 
     * @param eventName Name of the event.
     * 
     * @return List containing all the records from Events table.
     */
    List<Event> findEventsByName(String eventName);

    /**
     * Fetch all records from the Location table.
     * 
     * @return List containing all the records from location table.
     */
    List<Location> fetchAllLocations();

    /**
     * Fetch all records from the ActivityType table.
     * 
     * @param reason - either ("CODE","CHALLENGE", "MEMORY","OCCASION").
     * 
     * @return List containing all the records from ActivityType table.
     */
    List<ActivityType> fetchAllActivityTypes(String reason);

    /**
     * Fetch Event, charity, location and event date time based on the search
     * criteria.
     * 
     * @param parameter {@link SearchFundraisingEventParameter} Search parameter event
     * and charity and time period and event type (all are nillable
     * parameters so can be empty).
     * 
     * @return result set List .
     */
    List<SearchFundraisingEventResult> searchFundraisingEvent(
            SearchFundraisingEventParameter parameter);

    /**
     * Returns true indicating the fundraising reason details were saved else
     * false.
     * 
     * @param fundraiserActivity {@link FundraiserActivity} the fundraising acitivity details.
     * 
     * @return FundraiserActivity the persisted object with it's id.
     */
    FundraiserActivity saveFundraisingReason(
            FundraiserActivity fundraiserActivity);

/**
 * Get the {@link Fundraiser) By passing fundraiser identifier.
 * 
 * @param fundraiserId of type Long
 * 
 * @return Fundraiser the {@link Fundraiser}.
 */
    Fundraiser fetchFundraiserDetailsById(Long fundraiserId);

    /**
     * Fetch the fundraiser activity for the given id.
     * 
     * @param fundraiserActivityId the id of the object to be fetched.
     * 
     * @return {@link FundraiserActivity} the fundraiser activity object.
     */
    FundraiserActivity fetchFundraiserActivityById(Long fundraiserActivityId);

    /**
     * Determine whether this url is already assigned to any fundraiser.
     * 
     * @param url new url chossen by fundraiser.
     * @param urlType Url entity name, can be Fundraiser, Charity, Fundraising Team.
     * 
     * @return true if new url is already assigned to someone else false
     */
    boolean checkIfUrlIsAvailable(String url, String urlType);

    /**
     * Get number of existing user with same name and having profile.
     * 
     * @param firstname - firstname of the user
     * @param lastname - last name of the user
     * 
     * @return - number of existing user with same name and having profile
     */
    int getRegisteredUserCount(String firstname, String lastname);

    /**
     * This service will store url in user profile.
     * 
     * @param fundraiser - object of fundraiser.
     */
    void storeUrl(Fundraiser fundraiser);

    /**
     * This method is used to fetch event details by eventId.
     * 
     * @param eventId of type Long
     * 
     * @return {@link Event} Event object
     */
    Event getEventDetailsById(Long eventId);

    /**
     * This method returns a boolean flag indicating if the payment details
     * exists. <br>
     * 
     * @param emailAddress email address of user.
     * @param dobDay day of birth.
     * @param dobMonth month of birth.
     * @param dobYear year of birth.
     * 
     * @return boolean <code>true</code> if payment details exist in database
     * else false
     */
    boolean isPaymentCardDetailsExist(String emailAddress, int dobDay,
            int dobMonth, int dobYear);

    /**
     * Fetch all fundraisers and donors with the given inputs.
     * 
     * @param name - fundraiser name
     * @param vmgRefNo - vmg reference number
     * @param postCode - postcode of fundraiser
     * 
     * @return - list of fundraiser/donors
     */
    List<FundraiserDonorVO> fetchFundraisersAndDonorsByCriteria(String name,
            Long vmgRefNo, String postCode);

    /**
     * Updates the fundraiser personal details into database. Personal details
     * are like title, forename, surname, address, contact details.
     * <p />
     * 
     * @param user Domain object which represents the {@link Fundraiser} details.
     * 
     * @return Long fundraiserId.
     */
    User updateUserPersonalDetails(User user);

    /**
     * Checks if any team is already registered with the name passed as
     * parameter.
     * 
     * @param teamName the fundraiserTeamName
     * 
     * @return boolean, true is record found else false.
     */
    boolean isFundraiserTeamAlreadyExist(String teamName);

    /**
     * Persist the {@link FundraiserGroup} as a new record.
     * 
     * @param fundraiserGroup the fundraiserTeam details
     * 
     * @return Long the unique identifier of the persisted
     * {@link FundraiserGroup}.
     */
    Long saveFundraiserGroup(FundraiserGroup fundraiserGroup);

    /**
     * Searches registered fundraisers based on the email address send as input
     * parameter.
     * 
     * @param emailAddress the fundraiser emailAddress
     * 
     * @return List TeamMemberDetailDTO.
     */
    List<TeamMemberDetailDTO> searchRegisteredFundraiserByEmailAddress(
            String emailAddress);

    /**
     * Persist the {@link FundraiserGroupMember} as a new record.
     * 
     * @param fundraiserGroupMember the fundraiserTeamMember details
     * 
     * @return Long the unique identifier of the persisted
     * {@link FundraiserGroupMember}.
     */
    Long saveFundraiserTeamMember(FundraiserGroupMember fundraiserGroupMember);

    /**
     * Removed / Deletes the {@link FundraiserGroupMember} from the Table.
     * 
     * @param fundraiserGroupMember the fundraiserTeamMember details
     */
    void removeFundraiserTeamMember(FundraiserGroupMember fundraiserGroupMember);

    /**
     * Fetch all FundraiserGroupMember for the teamId passed.
     * 
     * @param teamId - teamId
     * 
     * @return - list of FundraiserGroupMember
     */
    List<FundraiserGroupMember> fetchTeamMembers(long teamId);

    /**
     * Get the {@link FundraiserGroup) By passing FundraiserGroup identifier.
     * 
     * @param fundraiserTeamId of type Long
     * 
     * @return FundraiserGroup the {@link FundraiserGroup}.
     */
    FundraiserGroup findFundraiserGroupById(long fundraiserTeamId);
    
    /**
     * Get the {@link FundraiserGroup) By passing FundraiserGroup identifier.
     * 
     * @param fundraiserTeamId of type Long
     * @param memberId of type Long
     * 
     * @return FundraiserGroup the {@link FundraiserGroup}.
     */
    FundraiserGroup findFundraiserGroupByIdAndMemeber(long fundraiserTeamId, long memberId); 

    /**
     * Get the Page object of type and url.
     * 
     * @param userUrl the url
     * @param pageUrl the page url
     * 
     * @return Page the page
     */
    Page fetchPage(String userUrl, String pageUrl);

    /**
     * Fetch page with given id.
     * 
     * @param id the id
     * 
     * @return Page the page
     */
    Page fetchPage(Long id);

    /**
     * Fetch the PageStatus object with given code.
     * 
     * @param code the code
     * 
     * @return PageStatus the status
     */
    PageStatus fetchPageStatus(String code);

    /**
     * Fetch the PageType object with given code.
     * 
     * @param code the code
     * 
     * @return PageType the type
     */
    PageType fetchPageType(String code);

    /**
     * Fetch the PageWidgetType with given code.
     * 
     * @param code the code
     * 
     * @return PageWidgetType the widget type
     */
    PageWidgetType fetchPageWidgetType(String code);

    /**
     * Save page.
     * 
     * @param page the page
     * 
     * @return the page
     */
    Page savePage(Page page);

    /**
     * Fetch fundraiser.
     * 
     * @param fundraiserId the id
     * @param includeNonOwnerTeamActivities flag
     * 
     * @return Fundraiser the fundraiser
     */
    Fundraiser fetchFundraiser(Long fundraiserId,
            boolean includeNonOwnerTeamActivities);

    /**
     * Fetch the fundraiser having the supplied unique url.
     * 
     * @param url the url
     * @param includeNonOwnerTeamActivities flag
     * 
     * @return fundraiser
     */
    Fundraiser fetchFundraiserByUrl(String url,
            boolean includeNonOwnerTeamActivities);

    /**
     * Fetch the fundraiser for the supplied user, null if does not exist.
     * 
     * @param userId the id
     * 
     * @return Fundraiser the fundraiser
     */
    Fundraiser fetchFundraiserForUser(Long userId);

    /**
     * Find the activities where the fundraiser is a team member but not the
     * owner.
     * 
     * @param fundraiserId the fundraiser id
     * 
     * @return list of fundraiser activity
     */
    List<FundraiserActivity> findTeamActivitiesWhereNotOwner(long fundraiserId);

/**
 * Get the
 * {@link FundraiserGroupType) By passing FundraiserGroupType identifier.
 * 
 * @param code -
 * FundraiserGroupType code
 * 
 * @return FundraiserGroupType the {@link FundraiserGroupType}.
 */
    FundraiserGroupType findFundraiserGroupTypeById(String code);

/**
 * Get the {@link UrlType) By passing FundraiserGroup identifier.
 * 
 * @param code -
 * UrlType code
 * 
 * @return UrlType the {@link UrlType}.
 */
    UrlType findUrlTypeByCode(String code);

/**
 * Get the
 * {@link FundraiserGroupMember) By passing FundraiserGroupMember identifier.
 * 
 * @param memberId of type Long
 * 
 * @return FundraiserGroupMember the {@link FundraiserGroupMember}.
 */
    FundraiserGroupMember findFundraiserGroupMemberById(long memberId);

    /**
     * Updates fund raiser.
     * 
     * @param fundrasier the fundraiser
     * 
     * @return Fundraiser the new Fundraiser
     */
    Fundraiser closeFundraiseAccount(Fundraiser fundrasier);

    /**
     * Removes given url from db.
     * <p>
     * needs to be called in transactional context and urlDetails must be
     * attached.
     * 
     * @param urlDetails the url
     */
    void removeUrlDetails(UrlDetails urlDetails);

    /**
     * Searches page details based on the fundraiser id send as input parameter.
     * 
     * @param fundraiserId the fundraiserId fundraiserId
     * 
     * @return List FundraiserGroupPageDetailDTO.
     */
    List<FundraiserGroupPageDetailDTO> findPageDetailsbyFundraiserId(
            Long fundraiserId);

    /**
     * Sets relations between Page and FundraiserGroup.
     * 
     * @param pageId the pageId pageId
     * @param fundraiserGroupId the fundraiserGroupId fundraiserGroupId
     * @param attributes the audit attributes
     */
    void updatePagewithFundraiserGroupID(Long pageId, Long fundraiserGroupId,
            AuditAttributes attributes);

/**
 * Get the {@link Page) By passing Page identifier.
 * 
 * @param pageId of type Long
 * 
 * @return Page the {@link Page}.
 */
    Page findPageById(long pageId);

/**
 * Get the {@link List FundraiserGroupDetailDTO ) By passing fundraiserId.
 * 
 * @param fundraiserId of type Long
 * @param fundraiserGroupType of type String
 * 
 * @return List FundraiserGroupDetailDTO the
 * {@link FundraiserGroupDetailDTO}.
 */
    List<FundraiserGroupDetailDTO> findFundraiserGroupsbyFundraiserId(
            Long fundraiserId, String fundraiserGroupType);

    /**
     * Fetch the fundraiser using user id.
     * 
     * @param userId to fetch fundraiser details.
     * 
     * @return object of {@link Fundraiser}.
     */
    Fundraiser fetchFundraiserByUserId(Long userId);

    /**
     * Fetch the list of event ids.
     * 
     * @param fundraiserId to fetch event ids.
     * 
     * @return list of event ids.
     */
    List<Long> fetchEventIdsByFundraiserId(Long fundraiserId);

    /**
     * Updates Fundraiser Activity.
     * 
     * @param fundraiserActivity .
     */
    void updateFundraiserActivity(FundraiserActivity fundraiserActivity);

    /**
     * If the registered Fundraiser has been previously invited by any other
     * fundraiser to join a team, this function will update the
     * InvitedEmailAddress with this Fundraiser ID.
     * 
     * @param emailAddress the emailAddress of the fundraiser.
     * @param fundraiserId the fundraiser id.
     * @param attributes the audit attributes
     */
    void updateInvitedEmailAddressWithFundraiserId(String emailAddress,
            Long fundraiserId, AuditAttributes attributes);

    /**
     * Function used to update Fundraiser_activity table with Fundraiser Group
     * ID.
     * 
     * @param fundraiserActivityId the fundraiser activity id.
     * @param fundraiserGroupId the fundraiser group id.
     * @param fundraiser the fundraiser id.
     * @param attributes the audit attributes
     */
    void updateFundraiserActivityWithGroupIdOrFundraiserId(Long fundraiserActivityId,
            Long fundraiserGroupId, Fundraiser fundraiser, AuditAttributes attributes);

    /**
     * Fetch location.
     * 
     * @param isoCode the isoCode
     * 
     * @return location
     */
    Location fetchLocationByIsoCode(String isoCode);

    /**
     * Fetch pagewidgets with given page id.
     * 
     * @param pageId the page id
     * 
     * @return Page the page
     */
    List<String> fetchPageWidgets(Long pageId);  
    
    /**
     * Fetch FundrasierURLDetails with given page fundraiserActivityId.
     * 
     * @param fundraiserActivityId the fundraiserActivityId
     * 
     * @return FundrasierURLDetails
     */
    public FundrasierURLDetails fetchFundraiserURLByFundraiserActivityId(Long fundraiserActivityId);

    /**
     * Fetch user profile by user id.
     * 
     * @param userId the user id
     * 
     * @return the user profile
     */
    UserProfile fetchUserProfileByUserId(Long userId);

    /**
     * Update user profile.
     * 
     * @param userProfile the user profile
     */
    void updateUserProfile(UserProfile userProfile);

    /**
     * Fetch random network activities.
     * 
     * @param groupId the group id
     * @param numberOfResults the number of results
     * @param fundraiserToExclude the fundraiser to exclude
     * 
     * @return the list< fundraiser activity>
     */
    public List<FundraiserActivity> fetchRandomNetworkActivities(Long groupId, Long numberOfResults, Long fundraiserToExclude);

    /**
     * Get a FundraiserActivity for a team.
     * 
     * @param teamUrl the url of the team
     * 
     * @return FundraiserActivity, or null
     */
    FundraiserActivity fetchFundraiserActivityByTeamUrl(String teamUrl);
    
    /**
     * Update page status.
     * 
     * @param pageId the page id
     * @param pageStatus the page status
     */
    void updatePageStatus(Long pageId, String pageStatus);
    
     void updateDonationFailedIndicator(List<Long> donationIds);

     /**
      * Get the total of all the donations for a particular FundraiserActivity.
      *
      * Result doesn't include tax relief.
      *
      * @param fundraiserActivity the fundraiserId
      * @return the total raised
      */
    BigDecimal fetchFundraiserActivityTotalDonations(Long fundraiserActivityId);
    
    /**
     * Get the total of all the tax-relief on donations for a particular FundraiserActivity.
     *
     * When added to the result from fetchFundraiserActivityDonations, you'll get the grand total that
     * the charity will receive.
     *
     * @param fundraiserActivity the fundraiserId
     * @return the total tax relief
     */
   BigDecimal fetchFundraiserActivityTotalDonationsTaxBack(Long fundraiserActivityId);
   
   /**
    * Get the all fundraiser activities of owner of team for given non owner team member.
    * @param fundraiserId non owner fundraiser id
    * @return List of Fundraiser Activity
    */
    List<FundraiserActivity> fetchFundraiserActivityForMemberOfTeam(Long fundraiserId);
}
