package com.virginmoneygiving.giving.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.dao.UserManagementDAO;
import com.virginmoneygiving.giving.domain.AuditAttributes;
import com.virginmoneygiving.giving.domain.AuthGroup;
import com.virginmoneygiving.giving.domain.CharityAdministrator;
import com.virginmoneygiving.giving.domain.Person;
import com.virginmoneygiving.giving.domain.Role;
import com.virginmoneygiving.giving.domain.User;
import com.virginmoneygiving.giving.domain.UserAuthGroup;
import com.virginmoneygiving.giving.domain.UserRole;

/**
 * This class handles all operations related to the User authentication and
 * authorisation.
 * <p />
 * 
 * @author vikas kale
 * @author Puneet Swarup - added method 'fetchUserPermissions'.
 */
public class JPAUserManagementDAOImpl implements UserManagementDAO {

    /** Logger for logging. */
    private static final Logger LOGGER =
            Logger.getLogger(JPAUserManagementDAOImpl.class);

    /** An Entity Manager Instance. */
    private EntityManager entityManager;

    /**
     * Dependency injection of JPA EntityManager.
     * 
     * @param entityManager an entity manger.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
    	if (LOGGER.isInfoEnabled()) {
    		LOGGER.info("JPA Entity Manager injected");
    	}
        this.entityManager = entityManager;
    }

    /** {@inheritDoc} */
    public List<UserRole> fetchUserRoles(String emailAddress, int dobDay,
            int dobMonth, int dobYear) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAUserManagementDAOImpl::fetchUserRoles() method - START");
        }

        Query fetchUserRoles = entityManager.createNamedQuery("fetchUserRoles");
        fetchUserRoles.setParameter("emailAddress", emailAddress);
        fetchUserRoles.setParameter("dobDay", dobDay);
        fetchUserRoles.setParameter("dobMonth", dobMonth);
        fetchUserRoles.setParameter("dobYear", dobYear);

        List<UserRole> userRoles = fetchUserRoles.getResultList();
        LOGGER.debug("UserRole size " + userRoles.size());

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAUserManagementDAOImpl::fetchUserRoles() method - END");
        }

        return userRoles;

    }

    /** {@inheritDoc} */
    public Person fetchPersonDetails(String emailAddress, int dobDay,
            int dobMonth, int dobYear) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAUserManagementDAOImpl::fetchPersonDetails() method - START");
        }

        Query fetchPersonDetails =
                entityManager.createNamedQuery("fetchPersonDetails");
        fetchPersonDetails.setParameter("emailAddress", emailAddress);
        fetchPersonDetails.setParameter("dobDay", dobDay);
        fetchPersonDetails.setParameter("dobMonth", dobMonth);
        fetchPersonDetails.setParameter("dobYear", dobYear);

        Person person = (Person) fetchPersonDetails.getSingleResult();
        if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("Person name " + person.getForename() + " "
	                + person.getSurname());
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAUserManagementDAOImpl::fetchPersonDetails() method - END");
        }

        return person;

    }

    /** {@inheritDoc} */
    public User getUser(String emailAddress, int dayOfBirth, int monthOfBirth,
            int yearOfBirth) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAUserManagementDAOImpl::getUser() method - START");
        }
        Query getUserByEmailAndDob =
                entityManager.createNamedQuery("getUserByEmailAndDob")
                        .setParameter("emailAddress", emailAddress)
                        .setParameter("dayOfBirth", dayOfBirth)
                        .setParameter("monthOfBirth", monthOfBirth)
                        .setParameter("yearOfBirth", yearOfBirth);
        User user = (User) getUserByEmailAndDob.getSingleResult();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAUserManagementDAOImpl::getUser() method - END");
        }
        return user;
    }

    /** {@inheritDoc} */
    public void saveUser(User user) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAUserManagementDAOImpl::saveUser() method - START");
        }
        entityManager.merge(user);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAUserManagementDAOImpl::saveUser() method - END");
        }
    }

    /** {@inheritDoc} */
    public List<String> fetchUserPermissions(final String userName,
            final Integer dobDay, final Integer dobMonth, final Integer dobYear) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAUserManagementDAOImpl::fetchUserPermissions() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("INPUT : username = " + userName + " dobDay = " + dobDay
                + " dobMonth = " + dobMonth + " dobYear = " + dobYear);
        }

        final List<String> userPermissionsList = new ArrayList<String>();

        if (dobDay == null && dobMonth == null && dobYear == null) {

            userPermissionsList.addAll(entityManager.createNamedQuery(
                    "fetchUserAuthorisationGroupsForInternalUser")
                    .setParameter("username", userName).getResultList());

            userPermissionsList.addAll(entityManager.createNamedQuery(
                    "fetchUserRoleCodesForInternalUser").setParameter(
                    "username", userName).getResultList());
        }
        else {

            userPermissionsList.addAll(entityManager.createNamedQuery(
                    "fetchUserAuthorisationGroupsForExternalUser")
                    .setParameter("loginEmailAddress", userName)
                    .setParameter("dobDay", dobDay)
                    .setParameter("dobMonth", dobMonth)
                    .setParameter("dobYear", dobYear).getResultList());

            userPermissionsList.addAll(entityManager.createNamedQuery(
                    "fetchUserRoleCodesForExternalUser")
                    .setParameter("loginEmailAddress", userName)
                    .setParameter("dobDay", dobDay)
                    .setParameter("dobMonth", dobMonth)
                    .setParameter("dobYear", dobYear).getResultList());
        }

        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("OUTPUT : " + userPermissionsList);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAUserManagementDAOImpl::fetchUserPermissions() method - END");
        }
        return userPermissionsList;
    }

    /** {@inheritDoc} */
    public List<String> fetchOperationUsersLoginName() {
        return entityManager.createNamedQuery("fetchOperationUsers")
                .getResultList();
    }

    /** {@inheritDoc} */
    public boolean grantPermissions(final Long userId,
            final List<String> permissions, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::grantPermissions() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("INPUT : userId = " + userId + " permissions = "
                + permissions);
        }

        boolean result = false;

        final User user = entityManager.find(User.class, userId);

        if (user != null) {
            grantPermissions(user, permissions);
            entityManager.merge(user);
            result = true;
        }
        else {
            final String errorMessage = "No user found with given id " + userId;
            LOGGER.error("Error Occured Inside JPAUserManagementDAOImpl::grantPermissions() method ");
            LOGGER.error("INPUT : userId =" + userId );
            LOGGER.error("Error Details" + errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("OUTPUT : " + result);
        }
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::grantPermissions() method - END");
        }
        return result;
    }

    /** {@inheritDoc} */
    public User fetchUserByUserName(String username) {
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::fetchUserByUserName() method - START");
        }
        User user =
                (User) entityManager.createNamedQuery("fetchUserByUserName")
                        .setParameter("username", username).getSingleResult();
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::fetchUserByUserName() method - END");
        }                
        return user;
    }

    /** {@inheritDoc} */
    public User fetchUserDetailsById(final long userId) {
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::fetchUserDetailsById() method - START");
        }
        Query getUserById =
                entityManager.createNamedQuery("getUserById").setParameter(
                        "userId", userId);
        User user = (User) getUserById.getSingleResult();
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::fetchUserDetailsById() method - END");
        }
        return user;
    }

    /** {@inheritDoc} */
    public long fetchUserIdByUserName(String userName, String userFirstname,
            String userSurname) {
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::fetchUserIdByUserName() method - START");
        }
        Date today = new Date();
        Timestamp ts = new java.sql.Timestamp(today.getTime());
        long resultUserId;
        User user = new User();
        Person person = new Person();
        UserRole userRole = new UserRole();
        Role role = entityManager.find(Role.class, "ROLE_O_US");

        if (StringUtils.isNotBlank(userFirstname)
                && StringUtils.isNotBlank(userSurname)) {
            // update

            person.setForename(userFirstname);
			person.setSurname(userSurname);
			person.setCreatedDateTime(ts);
			person.setDobDay(Integer.valueOf(10));
			person.setDobMonth(Integer.valueOf(10));
			person.setDobYear(Integer.valueOf(2009));

            Person newPerson = entityManager.merge(person);

            user.setUsername(userName);
            user.setPerson(newPerson);

            user.setLockedInd("N");
            user.setStartDatetime(ts);
            user.setCreatedDateTime(ts);

            User newUser = entityManager.merge(user);

            userRole.setRole(role);
            userRole.setUser(newUser);
            userRole.setStartDatetime(ts);
            userRole.setCreatedDateTime(ts);
            UserRole newUserRole = entityManager.merge(userRole);
            resultUserId = newUserRole.getUser().getId();
        }
        else {
            Query getUserById =
                    entityManager.createNamedQuery("getUserByName")
                            .setParameter("userName", userName);
            User newUser = (User) getUserById.getSingleResult();
            resultUserId = newUser.getId();
        }
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::fetchUserIdByUserName() method - END");
        }
        return resultUserId;
    }

    /** {@inheritDoc} */
    public CharityAdministrator saveCharityAdministrator(CharityAdministrator charityAdmin) {
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::saveCharityAdministrator() method - START");
        }
        charityAdmin = entityManager.merge(charityAdmin);

        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::saveCharityAdministrator() method - END");
        }
        return charityAdmin;
    }

    /**
     * Method to expire a users authorization group.
     * @param authGroup The user authorization group to expire.
     */
    private static void setAuthGroupExpired(UserAuthGroup authGroup) {
        authGroup.setEndDatetime(new Timestamp(System.currentTimeMillis()));
    }
    
    /**
     * Checks if a particular user authorization group has expired. This is because a user may
     * have a number of rows in the database for a particular role but if the end_datetime is set to a time
     * <= now then the role has expired.  
     * @param authGroup The user auth group to check
     * @return false if the end date time is null or the end date is in the future; true otherwise
     */
    private boolean isAuthGroupExpired(UserAuthGroup authGroup) {
        if (authGroup.getEndDatetime() == null) {
            return false;
        }
        
        /* If role ends in the future it's still valid. */
        if (authGroup.getEndDatetime().after(new Timestamp(System.currentTimeMillis()))) {
            return false;
        }
        
        return true;
    }
    
    /**
     * For a particular user and list of permissions, grant the permissions on the user. 
     * @param user
     * @param permissionsList
     */
    private void grantPermissions(User user, List<String> permissionsList) {
        if (user.getUserAuthGroups() == null) {
            user.setUserAuthGroups(new HashSet<UserAuthGroup>());
        }
        
        /* First iterate the current permissions to see if some need to be expired. */
        Set<UserAuthGroup> userAuthGroups = user.getUserAuthGroups();
        
        for (UserAuthGroup authGroup : Collections.unmodifiableSet(user.getUserAuthGroups())) {
            /* If the auth group isn't currently expired and the group has been removed from permissions */
            if (!isAuthGroupExpired(authGroup)) {
                if (permissionsList.contains(authGroup.getAuthGroup().getCode()) == false) {
                    /* Expire the users permissions. */
                    setAuthGroupExpired(authGroup);
                }
            }
        }
        
        /* Now iterate permissions that aren't already mapped and add them. */
        for (String permission : permissionsList) {
            if (permission.startsWith(MasterDataCodeConstants.USER_AUTH_GROUP_PREFIX)) {
                AuthGroup authGroup = entityManager.find(AuthGroup.class, permission);
                boolean found = false;
                
                /* If user already contains this auth group and it is expired create it */
                for (UserAuthGroup existingAuthGroup : userAuthGroups) {
                    if(authGroup != null) {
                        
                        if (existingAuthGroup.getAuthGroup().equals(authGroup)) {
                            /* If the existing auth group has expired it may be added again. */
                            if (isAuthGroupExpired(existingAuthGroup)) {
                                if (LOGGER.isTraceEnabled()) {
                                    LOGGER.trace("User contains expired auth group <" + authGroup.getCode() + "> - adding again");
                                }
                            } else {
                                if (LOGGER.isTraceEnabled()) {
                                    LOGGER.trace("User contains a non-expired auth group <" + authGroup.getCode() + "> - not adding again");
                                }
                                found = true;
                                break;
                            }
                        }
                    }
                }
                
                if (!found) {
                    final UserAuthGroup userAuthGroup = new UserAuthGroup();
                    if(authGroup != null) {
                    	userAuthGroup.setAuthGroup(authGroup);
                    }
                    user.getUserAuthGroups().add(userAuthGroup);
                    userAuthGroup.setUser(user);
                }
                
            }
            else {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid permission <").append(permission).append("> passed to grant.");
                LOGGER.error("Error Occured Inside JPAUserManagementDAOImpl::grantPermissionsWithRoles() method ");
                LOGGER.error(sb.toString());
                throw new IllegalArgumentException(sb.toString());
            }
        }
    }
    
    /** {@inheritDoc} */
    public boolean grantPermissionsWithRoles(long userId,
            List<String> permissionsList, List<String> rolesList,
            AuditAttributes auditAttributes) {
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::grantPermissionsWithRoles() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("INPUT : userId = " + userId + " permissions = "
                + permissionsList + " rolesList = " + rolesList);
        }

        boolean result = false;

        final User user = entityManager.find(User.class, userId);

        if (user != null) {
            /* Set the UserAuthGroup permissions */
            grantPermissions(user, permissionsList);
            
            if (user.getUserRoles() == null) {
                user.setUserRoles(new HashSet<UserRole>());
            }
            
            for (String role : rolesList) {
                if (role.startsWith(MasterDataCodeConstants.USER_ROLE_PREFIX)) {                    
                    final UserRole userRole = new UserRole();
                    Role roleDomain = entityManager.find(Role.class, role);
                    userRole.setRole(roleDomain);                    
                    user.getUserRoles().add(userRole);
                    userRole.setUser(user);

                }
                else {
                    final String errorMessage =
                            "Invalid role " + role + " passed to grant.";
                    LOGGER.error("Error Occured Inside JPAUserManagementDAOImpl::grantPermissionsWithRoles() method ");
                    LOGGER.error("INPUT : userId =" + userId );
                    LOGGER.error("Error Details" + errorMessage);
                    throw new IllegalArgumentException(errorMessage);
                }

            }

            entityManager.merge(user);
            result = true;
        }
        else {
            final String errorMessage = "No user found with given id " + userId;
            LOGGER.error("Error Occured Inside JPAUserManagementDAOImpl::grantPermissionsWithRoles() method ");
            LOGGER.error("INPUT : userId =" + userId );
            LOGGER.error("Error Details" + errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("OUTPUT : " + result);
        }
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::grantPermissionsWithRoles() method - END");
        }
        return result;
    }

    /** {@inheritDoc} */
    public int getUserAccessReadWrightCount() {
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::getUserAccessRWCount() method - START");
        }
        int count = 0;
        Object obj =
                entityManager.createNamedQuery("getUserAccessRWCount")
                        .getSingleResult();
        count = new Integer(obj.toString());
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.trace("JPAUserManagementDAOImpl::getUserAccessRWCount() method - END");
        }
        return count;
    }
}
