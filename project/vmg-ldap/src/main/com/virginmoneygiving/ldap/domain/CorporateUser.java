package com.virginmoneygiving.ldap.domain;

import java.io.Serializable;

/**
 * The class stores information for the Active Directory user.Here user is to be
 * creaated from frontend and the information related to that particular user is
 * stored in daatbase i.e in LDAP server.
 * 
 * @author vishals
 */
public class CorporateUser implements Serializable {
    
    /** Serial version UID. */
    private static final long serialVersionUID = -445546565665679L;

    
    /** Stores username. */
    private final String username;

    /** Stores surname. */
    private final String surname;

    /** Stores forename. */
    private final String forename;

    /** Stores distinguished name. */
    private final String distinguishedName;

    /** Stores account enabled status. */
    private boolean accountEnabled;

    /** Stores account expired status. */
    private boolean accountNonExpired;

    /** Stores account locked status. */
    private boolean accountNonLocked;

    /**
     * Default Constructor.
     * 
     * @param usernameValue the username
     * @param surnameValue the surname
     * @param forenameValue the forename
     * @param distinguishedNameValue the distinguished name
     * @param accountEnabledStatus account enabled flag
     * @param accountNonExpiredStatus account non-expired flag
     * @param accountNonLockedStatus account non-locked flag
     */
    public CorporateUser(final String usernameValue, final String surnameValue, final String forenameValue, final String distinguishedNameValue,
            final boolean accountEnabledStatus, final boolean accountNonExpiredStatus, final boolean accountNonLockedStatus) {
        this.username = usernameValue;
        this.surname = surnameValue;
        this.forename = forenameValue;
        this.distinguishedName = distinguishedNameValue;
        this.accountEnabled = accountEnabledStatus;
        this.accountNonExpired = accountNonExpiredStatus;
        this.accountNonLocked = accountNonLockedStatus;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the surname.
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Gets the forename.
     * @return the forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Gets the distinguished name.
     * @return the distinguished name
     */
    public String getDistinguishedName() {
        return distinguishedName;
    }

    /**
     * Checks if is account enabled.
     * @return true, if is account enabled
     */
    public boolean isAccountEnabled() {
        return accountEnabled;
    }

    /**
     * Checks if is account non expired.
     * @return true, if is account non expired
     */
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * Checks if is account non locked.
     * @return true, if is account non locked
     */
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * Custom to string implementation.
     * @return String representation of all properties.
     */
    public String toString() {

        return new StringBuffer().append("Username = ").append(username).append(" : Forename = ").append(forename).append(" : Surname = ").append(surname)
                .append(" : DistinguishedName =").append(distinguishedName).append(" : AccountEnabled = ").append(accountEnabled).append(
                        " : AccountNonExpired = ").append(accountNonExpired).append(" : AccountNonLocked = ").append(accountNonLocked).toString();
    }
}
