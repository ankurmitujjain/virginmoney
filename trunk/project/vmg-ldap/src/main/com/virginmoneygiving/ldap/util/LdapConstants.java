package com.virginmoneygiving.ldap.util;

/**
 * This class contains constants files for attribute for Active directory
 * server.
 * <p/>
 * @author vishals
 */
public final class LdapConstants {

    // User's common name attribute in Active directory server.
    /** Common name attribute constant. */
    public static final String COMMON_NAME = "CN";
    
    /** sAMAccountName attribute constant. */
    public static final String USERNAME = "sAMAccountName";
    
    /** Surname attribute constant. */
    public static final String SURNAME = "SN";
    
    /** givenName attribute constant. */
    public static final String FORENAME = "givenName";
    
    /** givenName attribute constant. */
    public static final String MEMBER_OF = "memberOf";
    
    /** distinguishedName attribute constant. */
    public static final String USER_DN = "distinguishedName";
    
    /** member attribute constant. */
    public static final String MEMBER = "member";
    
    /** objectClass attribute constant. */
    public static final String OBJECT_CLASS = "objectClass";
    
    /** group attribute constant. */
    public static final String GROUP = "group";
    
    /** user attribute constant. */
    public static final String USER = "user";
    
    /** lockoutDuration attribute constant. */
    public static final String LOCKOUT_DURATION = "lockoutDuration";
    
    /** lockoutThreshold attribute constant. */
    public static final String LOCKOUT_THRESHOLD = "lockoutThreshold";
    
    /** userAccountControl attribute constant. */
    public static final String USER_ACCOUNT_CONTROL = "userAccountControl";
    
    /** lockoutTime attribute constant. */
    public static final String LOCKOUT_TIME = "lockoutTime";
    
    /** badPwdCount attribute constant. */
    public static final String BAS_PASSWORD_COUNT = "badPwdCount";
    
    /** accountExpires attribute constant. */
    public static final String ACCOUNT_EXPIRES = "accountExpires";
    
    /** User account disabled control value. */
    public static final int USER_ACCOUNT_DISABLED_CONTROL_VALUE = 0x0002;
    
    /** object class user. */
    public static final String OBJECT_CLASS_USER = "user";

  /**
   * Private default constructor.
   */
  private LdapConstants() {

  }
}
