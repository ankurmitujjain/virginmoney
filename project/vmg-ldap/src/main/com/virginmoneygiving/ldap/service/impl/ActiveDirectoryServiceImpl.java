package com.virginmoneygiving.ldap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.ObjectRetrievalException;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import com.virginmoneygiving.ldap.domain.CorporateUser;
import com.virginmoneygiving.ldap.service.DirectoryService;
import com.virginmoneygiving.ldap.util.LdapConstants;
import com.virginmoneygiving.ldap.util.LdapUtil;

/**
 * This class is used to provides funtionality to perform various operations on the
 * directory server.
 * <p/>
 * @author vishals
 */
public class ActiveDirectoryServiceImpl implements DirectoryService {

    /** Logger instance for class. */
    private static final Logger LOGGER = Logger.getLogger(ActiveDirectoryServiceImpl.class);
    
    /** LDAP template instance. */
    private LdapTemplate ldapTemplate;
    
    /** User baseDn on active directory. This will determine limit the scope of searching users on directory server to a specific tree structure. This user dn should always exclude the base dn of the directory server. */
    private String userBaseDn;
    
    /** Error message for user not found. */
    private static final String ERRMSG_USER_NOT_AVAILABLE = "User account is not available on Active Directory.";
    
    /** Error message for account locked operation processing failure. */
    private static final String ERRMSG_DIRECTORY_OPERATION_FAILURE = "General exception occured while processing the directory service request.";

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
	public List<CorporateUser> findUsers() {
    	if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("LDAPAuthenticationServiceImpl ::  findUsers() -START");
		}
        List<CorporateUser> corporateUsers = null;


        try {
        	if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("userBaseDn is :: " + userBaseDn);
        	}
            corporateUsers = ldapTemplate.search(userBaseDn,
                                                new EqualsFilter( LdapConstants.OBJECT_CLASS, LdapConstants.OBJECT_CLASS_USER ).encode(),
                                                    new ContextMapper(){
                                                        public Object mapFromContext(final Object o) {
                                                            return LdapUtil.getCorporateUser((DirContextOperations) o);
                                                        }
                                                    });

        } catch (EmptyResultDataAccessException erdexp) {
            LOGGER.error("Exception occured inside LDAPAuthenticationServiceImpl :: findUsers method");
            LOGGER.error("No matching user accounts found.", erdexp);
            corporateUsers = new ArrayList<CorporateUser>();
        } catch (Exception e) {
            LOGGER.error("Exception occured inside LDAPAuthenticationServiceImpl :: findUsers method");
            LOGGER.error("General exception occured while processing the directory service request.", e);
            throw new ObjectRetrievalException(ERRMSG_DIRECTORY_OPERATION_FAILURE, e.getCause());
        }
        if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("User details are :: " + corporateUsers.toString());
        }
        if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("LDAPAuthenticationServiceImpl ::  findUsers() -END");
		}

        return corporateUsers;

    }

    /** {@inheritDoc} */
    public boolean isUserAccountLocked(final String username) {
    	if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("LDAPAuthenticationServiceImpl :: isUserAccountLocked() -  START");
		}

        try {
            LOGGER.debug("username :: " + username);

            final CorporateUser corporateUsers =
                (CorporateUser) ldapTemplate.searchForObject("",
                                                             new AndFilter().and(new EqualsFilter(LdapConstants.OBJECT_CLASS, LdapConstants.USER))
                                                                       .and(new EqualsFilter(LdapConstants.USERNAME, username)).encode(),
                                                                        new ContextMapper() {
                                                                            public Object mapFromContext(final Object o) {
                                                                                return LdapUtil.getCorporateUser((DirContextOperations) o);
                                                                            }
                                                             });

            if (corporateUsers == null) {
                LOGGER.error(ERRMSG_USER_NOT_AVAILABLE);
                throw new NameNotFoundException(ERRMSG_USER_NOT_AVAILABLE);
            }
            if (LOGGER.isTraceEnabled()) {
    			LOGGER.trace("LDAPAuthenticationServiceImpl :: isUserAccountLocked() -  END");
    		}

            return !corporateUsers.isAccountNonLocked();
        } catch (EmptyResultDataAccessException erdexp) {
            LOGGER.error("Exception occured inside LDAPAuthenticationServiceImpl :: isUserAccountLocked method");
            LOGGER.error("No matching user account found.", erdexp);
            throw new NameNotFoundException(ERRMSG_USER_NOT_AVAILABLE);
        } catch (Exception e) {
            LOGGER.error("Exception occured inside LDAPAuthenticationServiceImpl :: isUserAccountLocked method");
            LOGGER.error(ERRMSG_DIRECTORY_OPERATION_FAILURE, e);
            throw new ObjectRetrievalException(ERRMSG_DIRECTORY_OPERATION_FAILURE, e);
        }

    }

    /**
     * Setter for LDAP template.
     * @param ldapTemplateObj ldap template.
     */
    public void setLdapTemplate(final LdapTemplate ldapTemplateObj) {
        ldapTemplate = ldapTemplateObj;
    }

    /**
     * Setter for user base DN.
     * @param userBaseDn user base DN.
     */
    public void setUserBaseDn(String userBaseDn) {
        this.userBaseDn = userBaseDn;
    }

}
