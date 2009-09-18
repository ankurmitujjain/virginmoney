package com.virginmoney.addresslookup.validators;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.apache.commons.lang.StringUtils;
import com.virginmoney.addresslookup.business.AddressSearchParameters;

/**
 * Performs basic validation of an address search parameters.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class AddressSearchValidator implements Validator {

    private static Logger logger = Logger.getLogger(AddressSearchValidator.class);

    /**
     * @@
     * @param aClass
     * @return
     */
    public boolean supports(Class aClass) {
        return aClass.equals(AddressSearchParameters.class);
    }



    /** {@inheritDoc} */
    public void validate(Object object, Errors errors) {
//        logger.debug("validate() validating " + object);

        AddressSearchParameters searchParameters = (AddressSearchParameters) object;

        validatePostcode(searchParameters, errors);

        if (logger.isTraceEnabled()) {
            logger.trace("Errors found - " + errors.hasErrors() + " when validating " + searchParameters);
            for (ObjectError error : (List<ObjectError>)errors.getAllErrors()) {
                logger.trace("error code=" + error.getCode() + " message=" + error.getDefaultMessage());
            }
        }
    }


    private void validatePostcode(AddressSearchParameters parameters, Errors errors) {
        if (StringUtils.isBlank(parameters.getSearchPostcode())) {
            errors.reject("address-lookup-ws.postcode.null","Search postcode is null");
            return;
        }

        if (parameters.getSearchPostcode().length() > 10) {
            errors.reject("address-lookup-ws.postcode.length.toolong","Search postcode is too long");
        }

        if (parameters.getSearchPostcode().length() < 5) {
            errors.reject("address-lookup-ws.postcode.length.tooshort","Search postcode is too short");
        }
    }


}

