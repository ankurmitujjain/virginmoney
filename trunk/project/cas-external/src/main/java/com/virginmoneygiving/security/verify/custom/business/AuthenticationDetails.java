package com.virginmoneygiving.security.verify.custom.business;

import java.util.List;
import java.util.ArrayList;

/**
 * This Class contains all the details related to authentication process.
 *
 * Created by IntelliJ IDEA.
 * User: choprah
 * Date: 03-Feb-2009
 * Time: 11:25:33
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationDetails {

    /** The system transaction id. */
    private String systemTransactionId;
    
    /** The session id. */
    private String sessionId;
    
    /** The brand ref. */
    private String brandRef;
    
    /** The user name. */
    private String userName;
    
    /** The date of birth. */
    private String dateOfBirth;
    
    /** The ip address. */
    private String ipAddress;
    
    /** The validated. */
    private boolean validated;
    
    /** The account locked. */
    private boolean accountLocked;
    
    /** The user id. */
    private Long    userId;
    
    /** The validation action. */
    private String  validationAction;
    
    /** The unique reference id. */
    private String uniqueReferenceId;
    
    /** The account id. */
    private String accountId;
    
    /** The response target. */
    private String responseTarget;
    
    /** The password. */
    private List<PasswordDetails> password;


    /**
     * Gets the system transaction id.
     * 
     * @return the system transaction id
     */
    public String getSystemTransactionId() {
        return systemTransactionId;
    }

    /**
     * Sets the system transaction id.
     * 
     * @param systemTransactionId the new system transaction id
     */
    public void setSystemTransactionId(String systemTransactionId) {
        this.systemTransactionId = systemTransactionId;
    }

    /**
     * Gets the session id.
     * 
     * @return the session id
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the session id.
     * 
     * @param sessionId the new session id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Gets the brand ref.
     * 
     * @return the brand ref
     */
    public String getBrandRef() {
        return brandRef;
    }

    /**
     * Sets the brand ref.
     * 
     * @param brandRef the new brand ref
     */
    public void setBrandRef(String brandRef) {
        this.brandRef = brandRef;
    }

    /**
     * Gets the user name.
     * 
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     * 
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the date of birth.
     * 
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth.
     * 
     * @param dateOfBirth the new date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the ip address.
     * 
     * @return the ip address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the ip address.
     * 
     * @param ipAddress the new ip address
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Checks if is validated.
     * 
     * @return true, if is validated
     */
    public boolean isValidated() {
        return validated;
    }

    /**
     * Sets the validated.
     * 
     * @param validated the new validated
     */
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    /**
     * Checks if is account locked.
     * 
     * @return true, if is account locked
     */
    public boolean isAccountLocked() {
        return accountLocked;
    }

    /**
     * Sets the account locked.
     * 
     * @param accountLocked the new account locked
     */
    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    /**
     * Gets the user id.
     * 
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     * 
     * @param userId the new user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }


    /**
     * Gets the validation action.
     * 
     * @return the validation action
     */
    public String getValidationAction() {
        return validationAction;
    }

    /**
     * Sets the validation action.
     * 
     * @param validationAction the new validation action
     */
    public void setValidationAction(String validationAction) {
        this.validationAction = validationAction;
    }


    /**
     * Gets the unique reference id.
     * 
     * @return the unique reference id
     */
    public String getUniqueReferenceId() {
        return uniqueReferenceId;
    }

    /**
     * Sets the unique reference id.
     * 
     * @param uniqueReferenceId the new unique reference id
     */
    public void setUniqueReferenceId(String uniqueReferenceId) {
        this.uniqueReferenceId = uniqueReferenceId;
    }

    /**
     * Gets the account id.
     * 
     * @return the account id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets the account id.
     * 
     * @param accountId the new account id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the response target.
     * 
     * @return the response target
     */
    public String getResponseTarget() {
        return responseTarget;
    }

    /**
     * Sets the response target.
     * 
     * @param responseTarget the new response target
     */
    public void setResponseTarget(String responseTarget) {
        this.responseTarget = responseTarget;
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    public List<PasswordDetails> getPassword() {
        if (this.password == null) {
            this.password = new ArrayList<PasswordDetails>();
        }
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param password the new password
     */
    public void setPassword(List<PasswordDetails> password) {
        this.password = password;
    }
}
