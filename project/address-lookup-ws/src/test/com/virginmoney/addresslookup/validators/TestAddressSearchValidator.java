package com.virginmoney.addresslookup.validators;

import java.util.List;
import org.apache.log4j.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import com.virginmoney.addresslookup.business.AddressSearchParameters;

/** Junit test class for {@link AddressSearchValidator}. */

public class TestAddressSearchValidator {
    private Logger logger = Logger.getLogger(TestAddressSearchValidator.class);

    private AddressSearchValidator testAddressSearchValidator;
    private AddressSearchParameters parameters;
    private Errors errors;

    @Before
    public void setUp() throws Exception {
        testAddressSearchValidator = new AddressSearchValidator();
        assertNotNull(testAddressSearchValidator);

        parameters = new AddressSearchParameters("NR14 8PA", null);
        assertNotNull(parameters);

        errors = new BeanPropertyBindingResult(parameters, "AddressSearchParameters");
        assertNotNull(errors);
    }

    @After
    public void tearDown() throws Exception {

        testAddressSearchValidator = null;
        parameters = null;
        errors = null;
    }

    @Test
    public void testValidatorSupports() throws Exception {
        assertTrue("validator should support AddressSearchParameters class ",
                testAddressSearchValidator.supports(parameters.getClass()));
        assertFalse("validator should not support this class ", testAddressSearchValidator.supports(this.getClass()));
    }

    @Test
    public void testValidPostcodeOnly() throws Exception {
        testAddressSearchValidator.validate(parameters, errors);
        assertFalse("validator should not generate any errors ", errors.hasErrors());
    }

    @Test
    public void testValidPostcodeAndBuilding() throws Exception {
        parameters = new AddressSearchParameters("NR14 8PA", "A House",null, null,null,null,null);
        testAddressSearchValidator.validate(parameters, errors);
        assertFalse("validator should not generate any errors ", errors.hasErrors());
    }

    @Test
    public void testNullPostcode() throws Exception {
        parameters = new AddressSearchParameters(null, null,null, null,null,null,null);
        testAddressSearchValidator.validate(parameters, errors);
        debugErrors(errors);
        assertTrue("validator should generate an error for a Null postcode ", errors.hasErrors());
        assertTrue("Null postcode should generate an error code ",
                checkForErrorcode(errors, "address-lookup-ws.postcode.null"));
    }

    @Test
    public void testLongPostcode() throws Exception {
        parameters = new AddressSearchParameters("NR 14 8PAabc", null);
        testAddressSearchValidator.validate(parameters, errors);
        debugErrors(errors);
        assertTrue("validator should generate an error for a too-long postcode ", errors.hasErrors());
        assertTrue("A too-long postcode should generate an error code ",
                checkForErrorcode(errors, "address-lookup-ws.postcode.length.toolong"));
    }

    @Test
    public void testShortPostcode() throws Exception {
        parameters = new AddressSearchParameters("x", null);
        testAddressSearchValidator.validate(parameters, errors);
        debugErrors(errors);
        assertTrue("validator should generate an error for a too-short postcode ", errors.hasErrors());
        assertTrue("A too-short postcode should generate an error code ",
                checkForErrorcode(errors, "address-lookup-ws.postcode.length.tooshort"));
    }

    @Test
    public void testPostcodeAnywherePAFData() throws Exception {
        parameters = new AddressSearchParameters("NR14 8PA",
                "A House",
                AddressSearchParameters.defaultResultFormat,
                AddressSearchParameters.ServiceProviders.PostcodeAnywhere,
                AddressSearchParameters.defaultReturnMatchingResultRule,
                AddressSearchParameters.defaultSortMatchingResultRule,
                AddressSearchParameters.ReturnPAFDataRules.returnPAFData);
        testAddressSearchValidator.validate(parameters, errors);
        assertFalse("validator should not generate any errors ", errors.hasErrors());
    }

    @Test
    public void testIFDSPAFData() throws Exception {
        parameters = new AddressSearchParameters("NR10 7RF",
                null,
                AddressSearchParameters.defaultResultFormat,
                AddressSearchParameters.ServiceProviders.IFDS,
                AddressSearchParameters.defaultReturnMatchingResultRule,
                AddressSearchParameters.defaultSortMatchingResultRule,
                AddressSearchParameters.ReturnPAFDataRules.returnPAFData);

        testAddressSearchValidator.validate(parameters, errors);
        debugErrors(errors);
        assertEquals("PAF data should be overriden for IFDS ",
                AddressSearchParameters.ReturnPAFDataRules.noPAFData,parameters.getReturnPAFDataRule());
        assertFalse("Requesting PAF data from IFDS should not generate an error code , " +
                "because it is overriden by the code", errors.hasErrors());
    }

    @Test
    public void testIFDSDefaultResultStyle() throws Exception {
        parameters = new AddressSearchParameters("NR14 8PA",
                "A House",
                AddressSearchParameters.defaultResultFormat,
                AddressSearchParameters.ServiceProviders.IFDS,
                AddressSearchParameters.defaultReturnMatchingResultRule,
                AddressSearchParameters.defaultSortMatchingResultRule,
                AddressSearchParameters.ReturnPAFDataRules.noPAFData);
        testAddressSearchValidator.validate(parameters, errors);
        assertFalse("validator should not generate any errors ", errors.hasErrors());
    }

    @Test
    public void testIFDSResultStyle2() throws Exception {
        parameters = new AddressSearchParameters("NR103EL",
                null,
                AddressSearchParameters.ResultFormats.STYLE2,
                AddressSearchParameters.ServiceProviders.IFDS,
                AddressSearchParameters.defaultReturnMatchingResultRule,
                AddressSearchParameters.defaultSortMatchingResultRule,
                AddressSearchParameters.ReturnPAFDataRules.noPAFData);
        testAddressSearchValidator.validate(parameters, errors);
        debugErrors(errors);

        assertEquals("Address Style should be overriden for IFDS ",
                AddressSearchParameters.ResultFormats.STYLE1 ,parameters.getResultFormat());
        assertFalse("Requesting Address Style 2 from IFDS should not generate an error code , " +
                "because it is overriden by the code", errors.hasErrors());
    }

    /**
     * Utility method to write the error list to the logger.
     *
     * @param errors The Errors object to debug.
     */
    private void debugErrors(Errors errors) {
        for (ObjectError error : (List<ObjectError>)errors.getAllErrors()) {
            logger.debug("error code=" + error.getCode() + " message=" + error.getDefaultMessage());
        }
    }

    /**
     * checks that the Errors object contains a specific code.
     *
     * @param errors The errors object.
     * @param code   The code to check for.
     *
     * @return true if the errors object contains the supplied error code.
     */
    private boolean checkForErrorcode(Errors errors, String code) {
        for (ObjectError error : (List<ObjectError>)errors.getAllErrors()) {
            //        logger.debug("error=" + error + " class=" + error.getClass().getName());
            if (error.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

}
