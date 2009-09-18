package com.virginmoneygiving.cardpayment.helper;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.dozer.util.mapping.MappingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.virginmoneygiving.cardpayment.service.messages.ServiceNameValuePair;

/**
 * Test the custom Dozer field converter.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestMapToNVPListCustomConverter {

    // class under test
    /** The bean. */
    private MapToNVPListCustomConverter bean;

    // test data objects
    /** The list. */
    private List<ServiceNameValuePair> list;
    
    /** The map. */
    private Map<String, String> map;
    
    /** The nvp1. */
    private ServiceNameValuePair nvp1;
    
    /** The nvp2. */
    private ServiceNameValuePair nvp2;
    
    /** The nvp3. */
    private ServiceNameValuePair nvp3;

    // test constants
    /** The Constant TEST_KEY1. */
    private static final String TEST_KEY1 = "KEY1";
    
    /** The Constant TEST_VAL1. */
    private static final String TEST_VAL1 = "VAL1";

    /** The Constant TEST_KEY2. */
    private static final String TEST_KEY2 = "KEY2";
    
    /** The Constant TEST_VAL2. */
    private static final String TEST_VAL2 = "VAL2";

    /** The Constant TEST_KEY3. */
    private static final String TEST_KEY3 = "KEY3";
    
    /** The Constant TEST_VAL3. */
    private static final String TEST_VAL3 = "VAL3";

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new MapToNVPListCustomConverter();

        map = new HashMap<String, String>();
        list = new ArrayList<ServiceNameValuePair>();

        // set up test data
        map.put(TEST_KEY1, TEST_VAL1);
        map.put(TEST_KEY2, TEST_VAL2);
        map.put(TEST_KEY3, TEST_VAL3);

        nvp1 = new ServiceNameValuePair();
        nvp1.setName(TEST_KEY1);
        nvp1.setValue(TEST_VAL1);
        list.add(nvp1);

        nvp2 = new ServiceNameValuePair();
        nvp2.setName(TEST_KEY2);
        nvp2.setValue(TEST_VAL2);
        list.add(nvp2);

        nvp3 = new ServiceNameValuePair();
        nvp3.setName(TEST_KEY3);
        nvp3.setValue(TEST_VAL3);
        list.add(nvp3);
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
     * {@link com.virginmoneygiving.cardpayment.helper.MapToNVPListCustomConverter#convert(java.lang.Object, java.lang.Object, java.lang.Class, java.lang.Class)}
     * .
     */
    @Test
    public void testConvertMapToListWithEmptyList() {
        Object obj = bean.convert(new ArrayList<ServiceNameValuePair>(), map, null, null);

        assertNotNull(obj);
        assertTrue(obj instanceof List);

        List<ServiceNameValuePair> res = (List<ServiceNameValuePair>) obj;
        assertEquals(3, res.size());

        // cannot assert 'contains' as the JAXWS class doesn't implement
        // equals/hashCode
        // also ordering from HashMap is hash key set dependent
        for (ServiceNameValuePair nvp : res) {
            assertEquals(nvp.getValue(), map.remove(nvp.getName()));
        }
        assertTrue(map.isEmpty());
    }

    /**
     * Test convert map to list with null target.
     */
    @Test
    public void testConvertMapToListWithNullTarget() {
        Object obj = bean.convert(null, map, null, null);

        assertNotNull(obj);
        assertTrue(obj instanceof List);

        List<ServiceNameValuePair> res = (List<ServiceNameValuePair>) obj;
        assertEquals(3, res.size());

        // cannot assert 'contains' as the JAXWS class doesn't implement
        // equals/hashCode
        // also ordering from HashMap is hash key set dependent
        for (ServiceNameValuePair nvp : res) {
            assertEquals(nvp.getValue(), map.remove(nvp.getName()));
        }
        assertTrue(map.isEmpty());
    }

    /**
     * Test convert map to list with source.
     */
    @Test
    public void testConvertMapToListWithSource() {
        Object obj = bean.convert(new ArrayList<ServiceNameValuePair>(), null, null, null);

        assertNotNull(obj);
        assertTrue(obj instanceof List);

        List<ServiceNameValuePair> res = (List<ServiceNameValuePair>) obj;
        assertEquals(0, res.size());
    }

    /**
     * Test convert map to list with full list.
     */
    @Test
    public void testConvertMapToListWithFullList() {
        Object obj = bean.convert(list, map, null, null);

        assertNotNull(obj);
        assertTrue(obj instanceof List);

        List<ServiceNameValuePair> res = (List<ServiceNameValuePair>) obj;
        assertEquals(3, res.size());

        // cannot assert 'contains' as the JAXWS class doesn't implement
        // equals/hashCode
        // also ordering from HashMap is hash key set dependent
        for (ServiceNameValuePair nvp : res) {
            assertEquals(nvp.getValue(), map.remove(nvp.getName()));
        }
        assertTrue(map.isEmpty());
    }

    /**
     * Test convert list to map with empty map.
     */
    @Test
    public void testConvertListToMapWithEmptyMap() {
        Object obj = bean.convert(new HashMap<String, String>(), list, null, null);

        assertNotNull(obj);
        assertTrue(obj instanceof Map);

        Map<String, String> res = (Map<String, String>) obj;
        assertEquals(3, res.size());
        assertEquals(TEST_VAL1, res.get(TEST_KEY1));
        assertEquals(TEST_VAL2, res.get(TEST_KEY2));
        assertEquals(TEST_VAL3, res.get(TEST_KEY3));
    }

    /**
     * Test convert list to map with null target.
     */
    @Test
    public void testConvertListToMapWithNullTarget() {
        Object obj = bean.convert(null, list, null, null);

        assertNotNull(obj);
        assertTrue(obj instanceof Map);

        Map<String, String> res = (Map<String, String>) obj;
        assertEquals(3, res.size());
        assertEquals(TEST_VAL1, res.get(TEST_KEY1));
        assertEquals(TEST_VAL2, res.get(TEST_KEY2));
        assertEquals(TEST_VAL3, res.get(TEST_KEY3));
    }

    /**
     * Test convert list to map with null source.
     */
    @Test
    public void testConvertListToMapWithNullSource() {
        Object obj = bean.convert(new HashMap<String, String>(), null, null, null);

        assertNotNull(obj);
        assertTrue(obj instanceof Map);

        Map<String, String> res = (Map<String, String>) obj;
        assertEquals(0, res.size());
    }

    /**
     * Test convert list to map with full map.
     */
    @Test
    public void testConvertListToMapWithFullMap() {
        Object obj = bean.convert(map, list, null, null);

        assertNotNull(obj);
        assertTrue(obj instanceof Map);

        Map<String, String> res = (Map<String, String>) obj;
        assertEquals(3, res.size());
        assertEquals(TEST_VAL1, res.get(TEST_KEY1));
        assertEquals(TEST_VAL2, res.get(TEST_KEY2));
        assertEquals(TEST_VAL3, res.get(TEST_KEY3));
    }

    /**
     * Test unsupported convert.
     */
    @Test
    public void testUnsupportedConvert() {
        try {
            bean.convert(null, "Foo", null, null);
            fail("exception expected");
        } catch (MappingException expected) {
            assertNotNull(expected.getMessage());
        }
    }

}
