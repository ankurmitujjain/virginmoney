package com.virginmoneygiving.ldap.service;

import java.util.List;

import com.virginmoneygiving.ldap.domain.CorporateUser;

/**
 * This interface is used to lists various operations to interact with LDAP
 * server.
 * <p/>
 * @author vishals
 */
public interface DirectoryService {

    /**
     * This method is used to find all users on domain controller that are
     * member of giving organization unit on directory server.
     * @return List of {@link CorporateUser} matching the pattern.
     */
    List<CorporateUser> findUsers();

    /**
     * Checks if given user account is locked on the directory server.
     * @param username the name of the user to be searched
     * @return <code>true</code> if account status is locked, <code>false</code>
     * otherwise.
     */
    boolean isUserAccountLocked(final String username);

}
