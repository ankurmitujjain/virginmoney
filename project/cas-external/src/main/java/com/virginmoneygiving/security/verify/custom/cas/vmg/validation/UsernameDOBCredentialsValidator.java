/**
 * This validator validates  that Login ID and Date of birth are valid
 *
 * Author(s)     :  choprah,ankurj
 * Creation Date :  01-May-2008
 * Copyright     :  Virgin Money Ltd.
 */
package com.virginmoneygiving.security.verify.custom.cas.vmg.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.DateValidator;
import org.apache.commons.validator.EmailValidator;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials;

/**
 * This validator validates  that Login ID and Date of birth are valid
 * 
 * @author HunarC
 */
public final class UsernameDOBCredentialsValidator implements Validator {
    
    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(UsernameDOBCredentialsValidator.class);

    /**
     * Checks whether this class supports validation of the Class provided.
     * 
     * @param clazz Class to check
     * 
     * @return boolean result
     */
    @SuppressWarnings("unchecked")
    public boolean supports(final Class clazz) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("supports(Class) - start");
        }

        boolean result = UsernamePasswordDOBCredentials.class.isAssignableFrom(clazz);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("supports(Class) - end");
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("validate(Object, Errors) - start");
        }
        checkEmail(usernamePasswordDOBCredentials, errors);
        checkDOB(usernamePasswordDOBCredentials, errors);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("validate(Object, Errors) - end");
        }
    }



    /**
     * Checks whether the Date of Birth is a valid date.
     * 
     * @param obj user credentails
     * @param errors the errors
     * 
     * @return boolean validation result
     */
      private void checkDOB(Object obj , Errors errors) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("checkDOB(Object) - start");
        }

        final UsernamePasswordDOBCredentials myObj = (UsernamePasswordDOBCredentials)obj;

        if (StringUtils.isEmpty(myObj.getDobDay()) && StringUtils.isEmpty(myObj.getDobMonth()) && StringUtils.isEmpty(myObj.getDobYear())) {
            errors.rejectValue("dateOfBirth","vmg.external.dob.no.fields");
            return;
        }
        if (StringUtils.isEmpty(myObj.getDobDay()) || StringUtils.isEmpty(myObj.getDobMonth()) || StringUtils.isEmpty(myObj.getDobYear())) {
            errors.rejectValue("dateOfBirth", "vmg.external.dob.some.fields");
            return;
        }
        String dob = myObj.getDateOfBirth();
        if (StringUtils.isEmpty(dob)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("checkDOB(Object) - end");
            }
        }

        if(!DateValidator.getInstance().isValid(dob, "dd/mm/yyyy", true)) {
            errors.rejectValue("dateOfBirth","vmg.external.required.dob.valid");
        }else if(!isValidDate(dob)) {
            errors.rejectValue("dateOfBirth","vmg.external.required.dob.valid");
        }
	}

    /**
     * Check email.
     * 
     * @param obj the obj
     * @param errors the errors
     * 
     * @return true, if successful
     */
    private void checkEmail(Object obj, Errors errors) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("checkEmail(Object) - start");
        }
        final UsernamePasswordDOBCredentials myObj = (UsernamePasswordDOBCredentials)obj;
        String username = myObj.getUsername();

        if (StringUtils.isEmpty(username)) {
            if (LOGGER.isDebugEnabled()) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "vmg.external.required.username");
                LOGGER.debug("checkEmail(Object) - end");
            }
            return;
        }
        if (username.length()>100) {
            if (LOGGER.isDebugEnabled()) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "vmg.external.required.username.maxlength");
                LOGGER.debug("checkEmail(Object) - end");
            }
            return;
        }
        if(!EmailValidator.getInstance().isValid(username)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("checkEmail(Object) - " + username);
                LOGGER.debug("checkEmail(Object) - end");
            }
            errors.rejectValue("username","vmg.external.required.username.valid");
            return;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("checkEmail(Object) - end");
        }
    }



    /**
     * Checks if is valid date.
     * 
     * @param inDate the in date
     * 
     * @return true, if is valid date
     */
    public boolean isValidDate(String inDate) {
      if (inDate == null)
        return false;

      //set the format to use as a constructor argument
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

      if (inDate.trim().length() != dateFormat.toPattern().length())
        return false;
      dateFormat.setLenient(false);

      try {
        //parse the inDate parameter
        dateFormat.parse(inDate.trim());
      }
      catch (ParseException pe) {
            LOGGER.error("isValidDate(String)", pe);
            return false;
      }catch (IllegalArgumentException e) {
            LOGGER.error("isValidDate(String)", e);

            return false;
      }

      return true;
    }

}
