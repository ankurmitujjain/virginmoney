package com.virginmoneygiving.ldap.service.impl;

import java.text.MessageFormat;

import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;

import org.apache.log4j.Logger;
import org.springframework.ldap.AuthenticationException;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.ObjectRetrievalException;

import com.virginmoneygiving.ldap.domain.CorporateUser;
import com.virginmoneygiving.ldap.exception.UserNotInGroupException;
import com.virginmoneygiving.ldap.service.AuthenticationService;
import com.virginmoneygiving.ldap.util.LdapConstants;
import com.virginmoneygiving.ldap.util.LdapUtil;

/**
 * This class is used to process authentication requests against a
 * configured active directory environment.
 * @author vishals
 */
public class AuthenticationServiceActiveDirectoryImpl implements AuthenticationService {

    /** Logger instance for class. */
    private static final Logger LOGGER = Logger.getLogger(AuthenticationServiceActiveDirectoryImpl.class);
    
    /** User baseDn on active directory. This will determine limit the scope of searching users on directory server to a specific tree structure. This user dn should always exclude the base dn of the directory server. */
    private String userBaseDn;
    
    /** Base DN for active directory server. */
    private String baseDn;
    
    /** Context source instance. */
    private ContextSource contextSource;
    
    /** Error message for group membership. */
    private static final String ERRMSG_USER_NOT_IN_GROUP = "User not associated with {0} group.";

    /** {@inheritDoc} */
    public CorporateUser authenticate(final String username, final String password, final String group)
            throws AuthenticationException, UserNotInGroupException {
    	if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("LDAPAuthenticationServiceImpl :: authenticate() -  START");
		}

        CorporateUser corporateUser = null;
        DirContext ctx = null;
        Attributes directoryAttributes = null;

        try {

            final String formattedUserBaseDn = MessageFormat.format(userBaseDn, username);
            final StringBuffer userDn = new StringBuffer(formattedUserBaseDn).append(",").append(baseDn);
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Formatted userBaseDn is :: " + formattedUserBaseDn +
                    "userDn is :: " + userDn.toString());
            }
            //LOGGER.info("Calling contextsource getContext method to authenticate user.");
            ctx = contextSource.getContext(userDn.toString(), password);
            if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Authenticate success for " + userDn.toString());
            }
            // Take care here - if a base was specified on the ContextSource
            // that needs to be removed from the user DN for the lookup to
            // succeed.
            directoryAttributes = ctx.getAttributes(formattedUserBaseDn);

        }
        catch (javax.naming.AuthenticationException authenticationException) {
            // Context creation failed - authentication did not succeed
            LOGGER.error("AuthenticationException failed", authenticationException);
            throw new AuthenticationException(authenticationException);
        }
        catch (javax.naming.NamingException namingException) {
            LOGGER.error("Naming Exception thrown while performing directory operation.", namingException);
            throw new ObjectRetrievalException(
                    "Error ocured in LDAPAuthenticationServiceImpl : authenticate method.", namingException);
        }
        finally {
            // It is imperative that the created DirContext instance is always
            // closed
            org.springframework.ldap.support.LdapUtils.closeContext(ctx);
        }

        // Verify user group membership.
        if (!LdapUtil.verifyUserGroupMemebership(group, directoryAttributes.get(LdapConstants.MEMBER_OF))) {
            LOGGER.error(MessageFormat.format(ERRMSG_USER_NOT_IN_GROUP, group));
            throw new UserNotInGroupException(MessageFormat.format(ERRMSG_USER_NOT_IN_GROUP, group));
        }
        // Load user details.
        corporateUser = LdapUtil.getCorporateUser(directoryAttributes);

        if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("LDAPAuthenticationServiceImpl :: authenticate() -  END");
		}

        return corporateUser;
    }

    /**
     * Setter for context source.
     * @param contextSource context source.
     */
    public void setContextSource(ContextSource contextSource) {
        this.contextSource = contextSource;
    }

    /**
     * Setter for user base DN.
     * @param userBaseDn user base DN.
     */
    public void setUserBaseDn(String userBaseDn) {
        this.userBaseDn = userBaseDn;
    }

    /**
     * Setter for directory server base DN.
     * @param baseDn directory server base DN.
     */
    public void setBaseDn(String baseDn) {
        this.baseDn = baseDn;
    }
}
