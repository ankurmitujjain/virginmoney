package com.virginmoneygiving.security.verify.custom.cas.vmg.validation;

import org.apache.log4j.Logger;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials;

/**
 * This validator validates  that password characters are valid
 * 
 * Author(s)     :  HunarC
 * Creation Date :  01-May-2008
 * Copyright     :  Virgin Money Ltd.
 */
public final class UserPasswordCredentialsValidator implements Validator {
	
	/** Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(UserPasswordCredentialsValidator.class);

   /**
    * Checks whether this class supports validation of the Class provided.
    * 
    * @param clazz Class to check
    * 
    * @return boolean result
    */
    public boolean supports(final Class clazz) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("supports() -  START");
		}

		boolean result = UsernamePasswordDOBCredentials.class.isAssignableFrom(clazz);
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("supports(Class) - END");
		}
		return result;
	}

   /**
    * Validates the object provided.
    * 
    * @param usernamePasswordDOBCredentials Object to validate
    * @param errors List of validation errors
    */
    public void validate(final Object usernamePasswordDOBCredentials, final Errors errors) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("validate() -  START");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCharacter1", "vmg.external.required.password1");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCharacter2", "vmg.external.required.password2");

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("validate(Object, Errors) - END");
		}
	}

}
