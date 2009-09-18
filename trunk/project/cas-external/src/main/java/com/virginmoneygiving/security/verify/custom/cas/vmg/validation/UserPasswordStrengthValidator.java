package com.virginmoneygiving.security.verify.custom.cas.vmg.validation;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.virginmoney.security.service.client.messages.ServiceFaultException;
import com.virginmoneygiving.common.helper.AccountSecurityHelper;
import com.virginmoneygiving.security.verify.custom.business.CASConstants;
import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials;
import com.virginmoneygiving.security.verify.custom.serviceproxy.AuthenticationProxy;

/**
 * This validator validates the strength of the user password.
 * 
 * @author HunarC
 * @author AnkurJ
 */
public class UserPasswordStrengthValidator implements Validator {


    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(UserPasswordStrengthValidator.class);

    /** Constant used as key to define the error message for password. */
    public static final String INVALID_PASSWORD ="password.invalid";

    /** Constant for password pattern string. */
    public static final String PASSWORD_PATTERN ="([0-9A-Za-z!\\$%\\^&\\*\\(\\),\\.;'/<>\\?:\\]{}]{0,20})";

    /** The authentication proxy. */
    private AuthenticationProxy authenticationProxy;

    /**
     * Sets the authentication proxy.
     * 
     * @param authenticationProxy the new authentication proxy
     */
    public void setAuthenticationProxy(AuthenticationProxy authenticationProxy) {
		this.authenticationProxy = authenticationProxy;
    }

    /**
     * Supports.
     * 
     * @param clazz the clazz
     * 
     * @return true, if supports
     * 
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {

        boolean result = UsernamePasswordDOBCredentials.class.isAssignableFrom(clazz);

        return result;
    }

    /**
     * Validates the object provided.
     * 
     * @param usernamePasswordDOBCredentials Object to validate
     * @param errors List of validation errors
     */
    public void validate(final Object usernamePasswordDOBCredentials, final Errors errors) {

		final UsernamePasswordDOBCredentials usernamePassword = (UsernamePasswordDOBCredentials) usernamePasswordDOBCredentials;
        String errorCode = null;
        if (StringUtils.isEmpty(usernamePassword.getPassword())) {
            errorCode = "password.required";
            errors.rejectValue("password",errorCode);

        }
        else if (!usernamePassword.getPassword().matches(PASSWORD_PATTERN)) {
            errorCode = "password.invalid";
            errors.rejectValue("password",errorCode);
        }
        if (StringUtils.isEmpty(usernamePassword.getConfirmPassword())) {
            errorCode = "confirmpassword.required";
            errors.rejectValue("confirmPassword",errorCode);
        }
        if(errorCode == null) {
            errorCode = AccountSecurityHelper.validateAccountSecurityDetails(usernamePassword.getUsername(), usernamePassword.getPassword(), usernamePassword.getConfirmPassword());
            if(errorCode != null) {
               errors.rejectValue("password",errorCode);
            }
        }
        if(errorCode == null) {
            usernamePassword.setResponseTarget(CASConstants.PASSWORD_VALIDATION_OK);
            try {
				authenticationProxy.changeForgottenPassword(usernamePassword);
			} catch (ServiceFaultException e) {
				LOGGER.error("Error in validation of usernamePasswordDOBCredentials errors details =" + errors, e);
				errors.reject("serviceNotAvailable");
			}
        }

	}
}
