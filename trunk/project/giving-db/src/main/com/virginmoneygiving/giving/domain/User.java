package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the User data to be persisted.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added properties userAuthGroups and userRoles
 */
/**
 * @author sushants
 *
 */
@Entity
@Table(name = "USER")
@Audited
public class User extends EnversAssociationAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -7477427375620273333L;

    /** User id. */
    private Long id;

    /** login email address. */
    private String loginEmailAddress;

    /** locked indicator. */
    private String lockedInd = "N";

    /** securityId indicator. */
    private Integer securityRef;

    /** User account closure reason. */
    private ClosureReason closureReason;

    /** Reference of Person data object. */
    private Person person;

    /** Reference of UserProfile data object. */
    private UserProfile userProfile;

    /** Get images associated with this user. */
    private Set<UserImages> userImages;

    /** The username of an internal user of the system. */
    private String username;

    /** The auth groups this user is part of. */
    private Set<UserAuthGroup> userAuthGroups;

    /** The groups this user is affiliated with. */
    private Set<UserRole> userRoles;

    /**
     * Getter method for user Id.
     * 
     * @return id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
    public Long getId() {
        return id;
    }

    /**
     * Settter method for user Id.
     * 
     * @param id new value for id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for login email address.
     * 
     * @return the loginEmailAddress
     */
    @Column(name = "LOGIN_EMAIL_ADDRESS")
    @Audited
    public String getLoginEmailAddress() {
        return loginEmailAddress;
    }

    /**
     * Setter method for login email address.
     * 
     * @param loginEmailAddress To set the loginEmailAddress.
     */
    public void setLoginEmailAddress(String loginEmailAddress) {
        this.loginEmailAddress = loginEmailAddress;
    }

    /**
     * Getter method for locked indicator.
     * 
     * @return lockedInd N if Not locked, T if Temporarily Locked, P if
     * Permanently Locked
     */

    @Column(name = "LOCKED_IND")
    @Audited
    public String getLockedInd() {
        if (lockedInd == null) {
            lockedInd = "N";
        }
        return lockedInd;
    }

    /**
     * Setter method for locked indicator.
     * <p />
     * Will accepts only 'N' or 'T' or 'P'. N if Not locked, T if Temporarily
     * Locked and P if Permanently Locked
     * 
     * @param lockedInd new value for lockedInd.
     */
    public void setLockedInd(String lockedInd) {
        this.lockedInd = lockedInd;
    }

    /**
     * Getter method for closureReason.
     * 
     * @return closureReason.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLOSURE_REASON_CODE")
    @Audited
    public ClosureReason getClosureReason() {
        return this.closureReason;
    }

    /**
     * Setter method for closureReason.
     * 
     * @param closureReason object of {@link ClosureReason}.
     */
    public void setClosureReason(ClosureReason closureReason) {
        this.closureReason = closureReason;
    }

    /**
     * Getter method for person.
     * 
     * @return object of {@link Person}.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    @Audited
    public Person getPerson() {
        return this.person;
    }

    /**
     * Setter method for person.
     * 
     * @param person .
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Gets the user profile.
     * 
     * @return the userProfile
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @NotAudited
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Sets the user profile.
     * 
     * @param userProfile the userProfile to set
     */
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * Gets the security ref.
     * 
     * @return the securityRef.
     */
    @Column(name = "SECURITY_REF")
    @Audited
    public Integer getSecurityRef() {
        return securityRef;
    }

    /**
     * Sets the security ref.
     * 
     * @param securityRef the securityRef to set
     */
    public void setSecurityRef(Integer securityRef) {
        this.securityRef = securityRef;
    }

    /**
     * Gets the user images.
     * 
     * @return the userImages
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @NotAudited
    public Set<UserImages> getUserImages() {
        return userImages;
    }

    /**
     * Sets the user images.
     * 
     * @param userImages the userImages to set
     */
    public void setUserImages(Set<UserImages> userImages) {
        this.userImages = userImages;
    }

    /**
     * Gets the username.
     * 
     * @return the username
     */
    @Column(name = "USERNAME")
    @NotAudited
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Gets the user roles.
     * 
     * @return the userRoles
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @NotAudited
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    /**
     * Sets the user roles.
     * 
     * @param userRoles the userRoles to set
     */
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    /**
     * Gets the user auth groups.
     * 
     * @return the userAuthGroups
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @NotAudited    
    public Set<UserAuthGroup> getUserAuthGroups() {
        return userAuthGroups;
    }

    /**
     * Sets the user auth groups.
     * 
     * @param userAuthGroups the userAuthGroups to set
     */
    public void setUserAuthGroups(Set<UserAuthGroup> userAuthGroups) {
        this.userAuthGroups = userAuthGroups;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    @Override
    public String toString() {

        final String tab = "    ";
        return new StringBuilder("User ( ").append("id = ").append(id).append(
                tab).append("loginEmailAddress = ").append(loginEmailAddress)
                .append(tab).append("lockedInd = ").append(lockedInd)
                .append(tab).append("closureReason = ").append(closureReason)
                .append(tab).append("userProfile = ").append(userProfile)
                .append(tab).append("securityRef = ").append(securityRef)
                .append(tab).append("username = ").append(username).append(tab)
                .append(super.toString()).append(" )").toString();
    }
}
