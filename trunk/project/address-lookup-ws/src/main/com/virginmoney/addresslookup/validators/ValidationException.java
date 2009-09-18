package com.virginmoney.addresslookup.validators;

import org.apache.log4j.Logger;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import com.virginmoney.addresslookup.messages.AddressLookupException;

/**
 * General exception thrown if data provided to a service fails validation. The exception can contain optional elements:
 * a causing exception and a list of validation errors.
 *
 * @author laveyi
 * @author $Author$
 * @version $Version$
 * @vm.creation.date Nov 26, 2007
 * @vm.copyright.year 2007
 */
public class ValidationException extends Exception {

    private static final Logger logger = Logger.getLogger(ValidationException.class);


    private Errors errors;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException( String message, Errors errors) {
        super(message);
        setErrors(errors);
    }

    public ValidationException( String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException( String message, Throwable cause, Errors errors) {
        super(message, cause);
        setErrors(errors);
    }

    /** Set this exceptions error list and write them out to the error logger. */
    public void setErrors(Errors errors) {
        this.errors = errors;
        loggerErrors();
    }

    public boolean hasErrors() {
        return errors != null && errors.hasErrors();
    }

    /**
     * Getter for property 'errors'.
     *
     * @return Value for property 'errors'.
     */
    public Errors getErrors() {
        return errors;
    }

    public void loggerErrors() {
        if (!hasErrors()) {
            logger.debug("No validation errors for");
            return;
        }
        logger.error("Validation errors for '" + errors.getObjectName() + "'");
        for (ObjectError error : (List<ObjectError>)errors.getAllErrors()) {
            logger.debug("error code=" + error.getCode() + " message=" + error.getDefaultMessage());
        }
    }
}


