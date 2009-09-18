package com.virginmoneygiving.cardpayment.helper;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the delimited string handler (includes some negative testing).
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestDelimitedStringHandler {

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Tear down.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.helper.DelimitedStringHandler#processNVP(java.lang.String, java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testProcessNVP() {
        String text = "key1: value1\nkey2: value2\nkey3: value3";
        String recSep = "\n";
        String nameSep = ": ";

        Map<String, String> map = DelimitedStringHandler.processNVP(text, recSep, nameSep);

        assertNotNull(map);
        assertEquals(3, map.size());
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.helper.DelimitedStringHandler#processNVP(java.lang.String, java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testProcessNVPTrailingRecSep() {
        String text = "key1: value1\nkey2: value2\nkey3: value3\n";
        String recSep = "\n";
        String nameSep = ": ";

        Map<String, String> map = DelimitedStringHandler.processNVP(text, recSep, nameSep);

        assertNotNull(map);
        assertEquals(3, map.size());
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.helper.DelimitedStringHandler#processNVP(java.lang.String, java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testProcessNVPBrokenRecord() {
        String text = "key1: value1\nkey2: value2\nkey3value3\n";
        String recSep = "\n";
        String nameSep = ": ";

        Map<String, String> map = DelimitedStringHandler.processNVP(text, recSep, nameSep);

        assertNotNull(map);
        assertEquals(2, map.size());
    }
    
    /**
     * Test nvp map to string.
     */
    @Test
    public void testNvpMapToString() {
        String expectedText1 = "key1: value1\n";
        String expectedText2 = "key2: value2\n";
        String expectedText3 = "key3: value3\n";
        String recSep = "\n";
        String nameSep = ": ";
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        
        String text = DelimitedStringHandler.nvpMapToString(map, recSep, nameSep);
        
        // can't do direct string equals because of hashcode ordering
        assertTrue("Should contain text1", text.contains(expectedText1));
        assertTrue("Should contain text2", text.contains(expectedText2));
        assertTrue("Should contain text3", text.contains(expectedText3));
    }

}
