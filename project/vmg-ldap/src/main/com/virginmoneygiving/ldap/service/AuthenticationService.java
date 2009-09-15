package com.virginmoneygiving.ldap.service;

import org.springframework.ldap.AuthenticationException;

import com.virginmoneygiving.ldap.domain.CorporateUser;
import com.virginmoneygiving.ldap.exception.UserNotInGroupException;

/**
 * This interface is used to lists various operations to support user
 * authentication .
 * <p/>
 * @author vishals
 */
public interface AuthenticationService {

  /**
   * Authenticate user's credentials (usename and password) on domain controller.
   * 
   * @param username the username.
   * @param password the password.
   * @param group default user group.
   * 
   * @return {@link CorporateUser} object,
   * if the username and password authentication is successful.
   * 
   * @throws AuthenticationException if supplied credential do not match on the system.
   * @throws UserNotInGroupException user is not member of specified group.
   */
  CorporateUser authenticate(final String username,
                                     final String password,
                                     final String group) throws AuthenticationException, UserNotInGroupException;

}
