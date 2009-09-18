package com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.authentication.principal.Credentials;

import com.virginmoneygiving.security.verify.custom.business.UserDetails;
import com.virginmoneygiving.security.verify.custom.business.UtilityFunctions;

/**
 * The Class contains username, password and date of birth credentials.
 * 
 * @author AnkurJ
 */
public class UsernamePasswordDOBCredentials implements Credentials {

    /** Unique ID for serialization. */
    private static final long serialVersionUID = -8343864967200862794L;

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    /** The confirm password. */
    private String confirmPassword;

    /** Date of birth. */
    private String dateOfBirth;

    /** Object to store user details. */
    private UserDetails userDetails;

    /** Brand Reference for Login. */
    private String brandReference;

    /** Login Ticket. */
    private String accountId;

    /** The service to send. */
    private String serviceToSend;

    /** The validation target. */
    private String validationTarget;

    /** The dob day. */
    private String dobDay;

    /** The dob month. */
    private String dobMonth;

    /** The dob year. */
    private String dobYear;

    /** The ip address. */
    private String ipAddress;

    /** The session id. */
    private String sessionId;

    /** The response target. */
    private String responseTarget;

    /** The session pass thru. */
    private boolean sessionPassThru = false;



    /**
     * Default constructor.
     */
    public UsernamePasswordDOBCredentials() {
        super();
        this.userDetails = new UserDetails();
    }

    /**
     * Gets the user ID property.
     * 
     * @return Long value
     */
    public final Long getUserId() {
        return this.getVmUser().getId();
    }

    /**
     * Sets the userID property.
     * 
     * @param userId value to set
     */
    public final void setUserId(final Long userId) {
        this.getVmUser().setId(userId);
    }

    /**
     * Gets the password.
     * 
     * @return Returns the password.
     */
    public final String getPassword() {
        return this.password;
    }

    /**
     * Sets the password.
     * 
     * @param password The password to set.
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets the username.
     * 
     * @return Returns the userName.
     */
    public final String getUsername() {
        return this.username;
    }

    /**
     * Sets the username.
     * 
     * @param userName The userName to set.
     */
    public final void setUsername(final String userName) {
        this.username = userName;
    }

    /**
     * Gets the date of birth.
     * 
     * @return the date of birth
     */
    public final String getDateOfBirth() {

        this.dateOfBirth = createDate();
        if(StringUtils.isEmpty(this.dateOfBirth)){
        	this.dateOfBirth="";
        }
        return this.dateOfBirth;
    }

    /**
     * Sets the date of birth.
     * 
     * @param dateOfBirth The dateOfBirth to set.
     */
    public final void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns a string representation of this object.
     * 
     * @return String value
     */
    public String toString() {
        return this.username;
    }

    /**
     * Compares this object to another UsernamePasswordDOBCredentials object.
     * 
     * @param obj Object to compare
     * 
     * @return Boolean result of comparision
     */
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof UsernamePasswordDOBCredentials)) {
            return false;
        }

        final UsernamePasswordDOBCredentials c =
                (UsernamePasswordDOBCredentials) obj;

        return this.username.equals(c.getUsername())
                && this.password.equals(c.getPassword());
    }

    /**
     * Returns a hashcode value.
     * 
     * @return int value
     */
    public int hashCode() {
        return this.username.hashCode() ^ this.password.hashCode();
    }

    /**
     * Gets the userDetails property.
     * 
     * @return UserDetails value
     */
    public UserDetails getVmUser() {
        if (this.userDetails == null) {
            this.userDetails = new UserDetails();
        }

        return this.userDetails;
    }

    /**
     * Sets the userDetails property.
     * 
     * @param userDetails value to set
     */
    public void setVmUser(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    /**
     * Gets the first password position property.
     * 
     * @return int value
     */
    public final int getPasswordLocation1() {
        return this.getVmUser().getLocation1();
    }

    /**
     * Sets the first password position property.
     * 
     * @param pLocation value to set
     */
    public final void setPasswordLocation1(int pLocation) {
        this.getVmUser().setLocation1(pLocation);
    }

    /**
     * Gets the second password position property.
     * 
     * @return int value
     */
    public final int getPasswordLocation2() {
        return this.getVmUser().getLocation2();
    }

    /**
     * Sets the second password position property.
     * 
     * @param pLocation value to set
     */
    public final void setPasswordLocation2(int pLocation) {
        this.getVmUser().setLocation2(pLocation);
    }

    /**
     * Gets the first password character property.
     * 
     * @return String value
     */
    public final String getPasswordCharacter1() {
        return this.getVmUser().getCharacter1();
    }

    /**
     * Sets the first password character property.
     * 
     * @param pCharacter value to set
     */
    public final void setPasswordCharacter1(String pCharacter) {
        this.getVmUser().setCharacter1(pCharacter);
    }

    /**
     * Gets the second password character property.
     * 
     * @return String value
     */
    public final String getPasswordCharacter2() {
        return this.getVmUser().getCharacter2();
    }

    /**
     * Sets the second password character property.
     * 
     * @param pCharacter value to set
     */
    public final void setPasswordCharacter2(String pCharacter) {
        this.getVmUser().setCharacter2(pCharacter);
    }

    /**
     * Gets the brandReference property.
     * 
     * @return String value
     */
    public String getBrandReference() {
        return brandReference;
    }

    /**
     * Sets the brandReference property.
     * 
     * @param brandReference value to set
     */
    public void setBrandReference(String brandReference) {
        this.brandReference = brandReference;
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
     * Gets the service to send.
     * 
     * @return the service to send
     */
    public String getServiceToSend() {
        return serviceToSend;
    }

    /**
     * Sets the service to send.
     * 
     * @param serviceToSend the new service to send
     */
    public void setServiceToSend(String serviceToSend) {
        this.serviceToSend = serviceToSend;
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
     * Gets the validation target.
     * 
     * @return the validation target
     */
    public String getValidationTarget() {
        return validationTarget;
    }

    /**
     * Sets the validation target.
     * 
     * @param validationTarget the new validation target
     */
    public void setValidationTarget(String validationTarget) {
        this.validationTarget = validationTarget;
    }

    /**
     * Gets the dob day.
     * 
     * @return the dob day
     */
    public String getDobDay() {
        return dobDay;
    }

    /**
     * Sets the dob day.
     * 
     * @param dobDay the new dob day
     */
    public void setDobDay(String dobDay) {
        this.dobDay = dobDay;
    }

    /**
     * Gets the dob month.
     * 
     * @return the dob month
     */
    public String getDobMonth() {
        return dobMonth;
    }

    /**
     * Sets the dob month.
     * 
     * @param dobMonth the new dob month
     */
    public void setDobMonth(String dobMonth) {
        this.dobMonth = dobMonth;
    }

    /**
     * Gets the dob year.
     * 
     * @return the dob year
     */
    public String getDobYear() {
        return dobYear;
    }

    /**
     * Sets the dob year.
     * 
     * @param dobYear the new dob year
     */
    public void setDobYear(String dobYear) {
        this.dobYear = dobYear;
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
     * Gets the formatted position1.
     * 
     * @return the formatted position1
     */
    public String getFormattedPosition1() {
        String pos = "";

        pos = UtilityFunctions.getFormattedPasswordPosition(getPasswordLocation1());
        if (pos == null || pos.length() < 1) {
            pos = "" + getPasswordLocation1();
        }

        return pos;
    }

    /**
     * Gets the formatted position2.
     * 
     * @return the formatted position2
     */
    public String getFormattedPosition2() {
        String pos = "";

        pos =UtilityFunctions.getFormattedPasswordPosition(getPasswordLocation2());
        if (pos == null || pos.length() < 1) {
            pos = "" + getPasswordLocation1();
        }

        return pos;
    }

    /**
     * Creates the date.
     * 
     * @return the string
     */
    private String createDate() {
        String dtc = null;
        if(StringUtils.isNotEmpty(dobDay) && StringUtils.isNotEmpty(dobMonth) && StringUtils.isNotEmpty(dobYear)){
        	if(StringUtils.isNumeric(dobDay) && StringUtils.isNumeric(dobMonth) && StringUtils.isNumeric(dobYear)){
	            Integer.parseInt(dobDay);
	            Integer.parseInt(dobMonth);
	            Integer.parseInt(dobYear);
	            dtc = dobDay + "/" + dobMonth + "/" + dobYear;
        	}
    	}
        return dtc;
    }

    /**
     * Gets the confirm password.
     * 
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the confirm password.
     * 
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Checks if is session pass thru.
     * 
     * @return the sessionPassThru
     */
    public boolean isSessionPassThru() {
        return sessionPassThru;
    }

    /**
     * Sets the session pass thru.
     * 
     * @param sessionPassThru the sessionPassThru to set
     */
    public void setSessionPassThru(boolean sessionPassThru) {
        this.sessionPassThru = sessionPassThru;
    }
}
