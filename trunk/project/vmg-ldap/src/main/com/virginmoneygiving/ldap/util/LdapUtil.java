package com.virginmoneygiving.ldap.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.apache.log4j.Logger;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.ObjectRetrievalException;

import com.virginmoneygiving.ldap.domain.CorporateUser;
import javax.naming.NamingException;

/**
 * The class provides utility methods required for LDAP operations.
 * <p/>
 * @author vishals
 */
public final class LdapUtil {

    /** Logger instance for class. */
    private static final Logger LOGGER = Logger.getLogger(LdapUtil.class);

    /**
     * Default constructor.
     */
    private LdapUtil() {

    }

    /**
     * The method maps required user attributes to {@link CorporateUser}
     * instance.
     * @param directoryAttributes contains user attributes on active directory.
     * @return {@link CorporateUser} instance.
     */
    public static CorporateUser getCorporateUser(final Attributes directoryAttributes) {
    	if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Inside :LdapUtil :: getCorporateUser method ( Based on directoryAttributes) - START");
    	}

        try {
            final String logOnName = getStringRepresentationForAttributeObject(directoryAttributes.get(LdapConstants.USERNAME));
            final String distinguishedName =
                getStringRepresentationForAttributeObject(directoryAttributes.get(LdapConstants.USER_DN));
            final String surname = getStringRepresentationForAttributeObject(directoryAttributes.get(LdapConstants.SURNAME));
            final String foreName = getStringRepresentationForAttributeObject(directoryAttributes.get(LdapConstants.FORENAME));

            // get lockout time etc. if present
            final String lockoutTime = getNullOrStringRepresentationForAttributeObject(
                    directoryAttributes.get(LdapConstants.LOCKOUT_TIME));
            final String userAccountControl = getNullOrStringRepresentationForAttributeObject(
                    directoryAttributes.get(LdapConstants.USER_ACCOUNT_CONTROL));
            final String accountExpires = getNullOrStringRepresentationForAttributeObject(
                    directoryAttributes.get(LdapConstants.ACCOUNT_EXPIRES));

			CorporateUser returnCorporateUser = new CorporateUser(logOnName, surname, foreName, distinguishedName, !isAccountDisabled(userAccountControl), !isAccountExpired(accountExpires), !isAccountLocked(lockoutTime));
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("LdapUtil :: getCorporateUser(Attributes) - END");
			}
			return returnCorporateUser;
		}
        catch (NamingException namingException) {
            LOGGER.error("Error ocured in LdapUtil : getCorporateUser method", namingException);
            throw new ObjectRetrievalException("Error ocured in LdapUtil : getCorporateUser method ",
                    namingException);
        }

    }

    /**
     * The method maps required user attributes to {@link CorporateUser} instance.
     * @param dirContextOperations contains user details on active directory.
     * @return {@link CorporateUser} instance.
     */
    public static CorporateUser getCorporateUser(final DirContextOperations dirContextOperations) {
    	if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Inside :LdapUtil :: getCorporateUser method ( Based on dirContextOperations) - START");
    	}
        //LOGGER.debug("DirContextOperations: " + dirContextOperations);

        final String logOnName = dirContextOperations.getStringAttribute(LdapConstants.USERNAME);
        final String distinguishedName = dirContextOperations.getStringAttribute(LdapConstants.USER_DN);
        final String surname = dirContextOperations.getStringAttribute(LdapConstants.SURNAME);
        final String foreName = dirContextOperations.getStringAttribute(LdapConstants.FORENAME);
        final String userAccountControl = dirContextOperations.getStringAttribute(LdapConstants.USER_ACCOUNT_CONTROL);
        final String lockoutTime = dirContextOperations.getStringAttribute(LdapConstants.LOCKOUT_TIME);
        final String accountExpires = dirContextOperations.getStringAttribute(LdapConstants.ACCOUNT_EXPIRES);

		CorporateUser returnCorporateUser = new CorporateUser(logOnName, surname != null ? surname : "", foreName != null ? foreName : "", distinguishedName, !isAccountDisabled(userAccountControl), !isAccountExpired(accountExpires), !isAccountLocked(lockoutTime));
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getCorporateUser(DirContextOperations) - END");
		}
		return returnCorporateUser;
	}

    /**
     * Utility method perform Null OR Empty checks on a list.
     * @param list contains values.
     * @return <code>true</code> if list is null or empty, <code>false</code> otherwise.
     */
    public static boolean isNullOREmpty( final List list ) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("isNullOREmpty() -  START");
		}

		boolean returnboolean = list == null || list.isEmpty();
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("isNullOREmpty(List) - END");
		}
		return returnboolean;
	}

    /**
     * Utility method to convert an {@link Attribute} object to a string representation.
     * @param obj object instance.
     * @return string representation for given {@link Attribute} object.
     * @throws NamingException the naming exception
     */
    public static String getStringRepresentationForAttributeObject(Object obj) throws NamingException {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getStringRepresentationForAttributeObject() -  START");
		}

		String attributeObjectString = (obj != null) ? ((Attribute) obj).get().toString() : "";
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getStringRepresentationForAttributeObject(Object) - END");
		}
		return attributeObjectString;
	}

    /**
     * Utility method to convert an {@link Attribute} object to a string
     * representation (unless the object is null).
     * @param obj object instance.
     * @return string representation for given {@link Attribute} object.
     * @throws NamingException the naming exception
     */
    public static String getNullOrStringRepresentationForAttributeObject(Object obj) throws NamingException {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getNullOrStringRepresentationForAttributeObject() -  START");
		}

		String attributeObjectString = (obj != null) ? ((Attribute) obj).get().toString() : null;
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getNullOrStringRepresentationForAttributeObject(Object) - END");
		}
		return attributeObjectString;
	}

    /**
     * Utility method to convert an object to a string representation.
     * @param obj object instance.
     * @return string representation for given object.
     */
    public static String convertObjectToString(Object obj) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("convertObjectToString() -  START");
		}

		String objToString = (obj != null) ? obj.toString() : "";
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("convertObjectToString(Object) - END");
		}
		return objToString;
	}

    /**
     * Utility method to verify if user is member of a specified group.
     * @param groupToVerify group to be verified.
     * @param memebrOfAttribute member of attribute on active directory.
     * @return <code>true</code> if user is member of specified group, <code>false</code> otherwise.
     */
    public static boolean verifyUserGroupMemebership(String groupToVerify, Attribute memebrOfAttribute) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("verifyUserGroupMemebership() -  START");
		}

        if (groupToVerify == null) {
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("verifyUserGroupMemebership(String, Attribute) - END");
			}
			return false;
        }

        String memebrOf = convertObjectToString(memebrOfAttribute);
        /*
         * Member of attribute always contains string in following format
         * CN={GROUP NAME},OU={},DC={}
         * To verify user group membership, first append CN= to groupToverify variable.
         * This can be used to verify group presence using indexOf operation.
         */
        String group = new StringBuffer("CN=").append(groupToVerify).append(",").toString();
		boolean results = memebrOf.indexOf(group) > 0;
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("verifyUserGroupMemebership(String, Attribute) - END");
		}
		return results;
	}

    /**
     * It calculates the time elapsed between the current time and 1 January
     * 1601, and returns milli-seconds for the same.
     * <p/>
     * @return time elapsed from 1 Jan 1601 in milli-seconds
     */
    public static Long getElapsedTime() {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getElapsedTime() -  START");
		}

		Long elapsedTime = 10000 * (new GregorianCalendar().getTime().getTime() - new GregorianCalendar(1601, Calendar.JANUARY, 1).getTime().getTime());
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("getElapsedTime() - END");
		}
		return elapsedTime;
	}

    /**
     * This method checks if an active directory account is expired.
     * @param accountExpires acountExpires attribute value.
     * @return <code>true</code> if user account is expired, <code>false</code> otherwise.
     */
    public static boolean isAccountExpired(final String accountExpires) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("isAccountExpired() -  START");
		}

		boolean results = accountExpires == null ? false : (Long.parseLong(accountExpires) >= 1 && getElapsedTime() >= Long.parseLong(accountExpires));
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("isAccountExpired(String) - END");
		}
		return results;
	}

    /**
     * This method checks if an active directory account is disabled.
     * @param accountControl accountControl attribute value.
     * @return <code>true</code> if user account is disabled, <code>false</code> otherwise.
     */
    public static boolean isAccountDisabled(final String accountControl) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("isAccountDisabled() -  START");
		}

		boolean results = accountControl == null ? false : ((Long.parseLong(accountControl) & 0x0002) == 0x0002);
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("isAccountDisabled(String) - END");
		}
        return results;
    }

    /**
     * Method is used to check if specified user account is locked on the active directory server.
     * @param lockoutTime lockout time attribute value on active directory for user account.
     * @return <code>true</code> if account is locked. <code>false</code> otherwise.
     */
    public static boolean isAccountLocked(final String lockoutTime) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("isAccountLocked() -  START");
		}

		boolean results = lockoutTime == null ? false : Long.parseLong(lockoutTime) > 0;
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("isAccountLocked(String) - END");
		}
        return results;
      }
}
