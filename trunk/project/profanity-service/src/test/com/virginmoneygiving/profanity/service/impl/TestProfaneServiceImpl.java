package com.virginmoneygiving.profanity.service.impl;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.virginmoneygiving.profanity.dao.ProfaneDAO;
import com.virginmoneygiving.profanity.dao.impl.MockProfaneDAO;
import com.virginmoneygiving.profanity.model.ProfanityCheckResult;

public class TestProfaneServiceImpl {

    private ProfaneServiceImpl profaneService;
    private ProfaneDAO profaneDAO;
    
    @Before
    public void setUp() throws Exception {
        profaneDAO = new MockProfaneDAO();
        profaneService = new ProfaneServiceImpl();
        profaneService.setProfaneDAO(profaneDAO);
    }
    
    @Test
    public void testSafeWordsForProfanity() {
        doProfanityCheck("test damnTEST", "test damnTEST", true);
        doProfanityCheck("damnTEST test test", "damnTEST test test", true);
        doProfanityCheck("You are id'io't's", "You are id'io't's", true);
    }
    
    @Test
    public void testUnacceptableWordsForProfanity() {
        doProfanityCheck("idiots Hi how are you.", "**** Hi how are you.", false);
        doProfanityCheck("Hi how are you idiots.", "Hi how are you ****.", false);
        doProfanityCheck("Hi how are you idiots what are you doing.", "Hi how are you **** what are you doing.", false);
        doProfanityCheck("damn", "****", false);
        doProfanityCheck("test ?damn# test.", "test ?****# test.", false);
        doProfanityCheck("Hi my name is Dave. You are idiots!", "Hi my name is Dave. You are ****!", false);
        doProfanityCheck("You are ;-)idiots", "You are ;-)****", false);
        doProfanityCheck("You are idiots5-)", "You are ****5-)", false);
    }
    
    @Test
    public void testVirginWordsForProfanity() {
        doProfanityCheck("We are Virgin Money", "We are Virgin Money", true);
    }
    
    @Test
    public void testNonVirginWordsForProfanity() {
        doProfanityCheck("Good luck Just Giving", "Good luck Just Giving", true);
    }
    
    /**
     * Helper method to perform a single profanity filter check and test the results.
     *
     * @param stringToCheck the string to check
     * @param expectedFilterResult the expected filter result
     * @param expectedResultStatus the expected result status
     * @param profanityWords the profanity words
     */
    private void doProfanityCheck(String stringToCheck, String expectedFilterResult, boolean expectedResultStatus) {
        ProfanityCheckResult profanityCheckResult = profaneService.checkProfanity(stringToCheck);

        assertEquals("profanity result : for '" + stringToCheck + "'",
                    expectedResultStatus,
                    profanityCheckResult.isStatus());

        if (!expectedResultStatus) {
            assertEquals("profanity filtered text : for ' " + stringToCheck + "'",
                    expectedFilterResult,
                    profanityCheckResult.getOutputText());
        }
    }


}
