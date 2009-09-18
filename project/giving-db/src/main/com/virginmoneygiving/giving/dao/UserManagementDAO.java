package com.virginmoneygiving.giving.dao;

import java.util.List;

import com.virginmoneygiving.giving.domain.AuditAttributes;
import com.virginmoneygiving.giving.domain.CharityAdministrator;
import com.virginmoneygiving.giving.domain.Person;
import com.virginmoneygiving.giving.domain.User;
import com.virginmoneygiving.giving.domain.UserRole;

/**
 * DAO to handle all operations related to the User authentication and
 * authorization.
 * <p />
 * 
 * @author vikas kale
 */
public interface UserManagementDAO {

    /**
     * Fetch user roles associated with passed combination email address and
     * date of birth.
     * <p />
     * 
     * @param emailAddress login emailAdress.
     * @param dobDay day of birth.
     * @param dobMonth month of birth.
     * @param dobYear year of birth.
     * 
     * @return List of {@link UserRole}.
     */
    List<UserRole> fetchUserRoles(String emailAddress, int dobDay,
            int dobMonth, int dobYear);

    /**
     * Fetch person details associated with passed combination email address and
     * date of birth.
     * <p />
     * 
     * @param emailAddress login emailAdress.
     * @param dobDay day of birth.
     * @param dobMonth month of birth.
     * @param dobYear year of birth.
     * 
     * @return Object of {@link Person}.
     */
    Person fetchPersonDetails(String emailAddress, int dobDay,
            int dobMonth, int dobYear);

    /**
     * Gets the user.
     * 
     * @param emailAddress the email address
     * @param dayOfBirth the day of birth
     * @param monthOfBirth the month of birth
     * @param yearOfBirth the year of birth
     * 
     * @return the user
     */
    User getUser(String emailAddress, int dayOfBirth, int monthOfBirth,
            int yearOfBirth);

    /**
     * Save user.
     * 
     * @param user the user
     */
    void saveUser(User user);

    /**
     * Fetch the permissions assigned to the user identified by given unique
     * username.
     * <p>
     * In case of external user login ie for charity user / donor / fundraiser
     * the username would be the login email address and dobDay, dobMonth,
     * dobYear would be respective parameters of users date of birth as provided
     * during registration.
     * <p>
     * In case this is an internal user ie operations user, the username would
     * be the actual username of the user used to login in internal systems. The
     * dobDay, dobMonth, dobYear would not be passed in this case.
     * <p>
     * 
     * @param username the unique user identifier.
     * @param dobDay the day of date of birth.
     * @param dobMonth the month of date of birth.
     * @param dobYear the year of date of birth.
     * 
     * @return list of permissions assigned to the user.
     */
    List<String> fetchUserPermissions(String username, Integer dobDay, Integer dobMonth, Integer dobYear);

    /**
     * Grant given set of permissions to user identified by given userId.
     * 
     * @param userId the user identifier.
     * @param permissions the list of permissions.
     * @param attributes the audit attributes
     * 
     * @return true if grant is successful, else false.
     */
    boolean grantPermissions(Long userId, List<String> permissions,
            AuditAttributes attributes);

    /**
     * Fetch object of {@link User} to get the user user's personal details.
     * 
     * @param userName of String type.
     * 
     * @return object of {@link User}.
     */
    User fetchUserByUserName(String userName);

    /**
     * This method is used to fetch login username of the operations users registered on the system.
     * 
     * @return List that contains operation user's login usernames.
     */
    List<String> fetchOperationUsersLoginName();

    /**
     * Fetch user details.
     * 
     * @param userId the user id
     * 
     * @return user
     */
    User fetchUserDetailsById(final long userId);

    /**
     * fetchUserIdByUserName.
     * 
     * @param userName the user name
     * @param userFirstname the user first name
     * @param userSurname the user last name
     * 
     * @return userId
     */
    long fetchUserIdByUserName(String userName, String userFirstname,
            String userSurname);

    /**
     * Save user.
     * 
     * @param charityAdmin the charity admin
     * 
     * @return charity administrator id.
     */
    CharityAdministrator saveCharityAdministrator(CharityAdministrator charityAdmin);

    /**
     * Grant given set of permissions to user identified by given userId.
     * 
     * @param userId the user identifier.
     * @param permissionsList the list of permissions.
     * @param rolesList the list of roles.
     * @param auditAttributes the audit attributes
     * 
     * @return true if grant is successful, else false.
     */
    boolean grantPermissionsWithRoles(long userId,
            List<String> permissionsList, List<String> rolesList,
            AuditAttributes auditAttributes);

    /**
     * Return count of users who have User Read write access.
     * 
     * @return count
     */
    int getUserAccessReadWrightCount();
}
