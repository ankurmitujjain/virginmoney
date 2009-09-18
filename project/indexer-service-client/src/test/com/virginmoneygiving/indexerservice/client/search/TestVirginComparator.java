
package com.virginmoneygiving.indexerservice.client.search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.common.SolrDocument;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.indexerservice.client.search.IndexerSearchServiceImpl.VirginComparator;
import com.virginmoneygiving.indexerservice.messages.IndexedType;

/**
 * The Test Class to test comparison.
 * 
 * @author Ian Priest
 */
public class TestVirginComparator {

    /** The comparator. */
    VirginComparator comparator = null;
    
    /** The keywords. */
    List<String> keywords = null;
    
    /** The control. */
    IndexerDocument control = null;
    
    /**
     * Sets the SolrDocument object for testing.
     * 
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {

        keywords = new ArrayList<String>();
        keywords.add("hello");
        keywords.add("world");

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "Hello");
        solrDoc.addField("location", "World");
        solrDoc.addField("description", "Hello World");
        solrDoc.addField("type", IndexedType.EVENT.value());
        solrDoc.addField("charity-name", "A Charity");
        
        control = new IndexerDocument(solrDoc, false, keywords);
        
        comparator = new IndexerSearchServiceImpl.VirginComparator();
    }

    /**
     * Test field match_null and empty values.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testFieldMatch_nullAndEmptyValues() throws Exception {

        assertEquals(0, comparator.fieldMatch(null, null));
        assertEquals(0, comparator.fieldMatch("", null));
        assertEquals(0, comparator.fieldMatch(null, new ArrayList<String>()));
        assertEquals(0, comparator.fieldMatch("", new ArrayList<String>()));
    }
    
    /**
     * Test field match_non matching values.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testFieldMatch_nonMatchingValues() throws Exception {
        assertEquals(0, comparator.fieldMatch("Don't match me", keywords));
        assertEquals(0, comparator.fieldMatch("", keywords));
        assertEquals(0, comparator.fieldMatch("ello orld", keywords));
    }

    /**
     * Test field match_matching values.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testFieldMatch_matchingValues() throws Exception {
        assertEquals(1, comparator.fieldMatch("Hello", keywords));
        assertEquals(1, comparator.fieldMatch("World", keywords));
        // Should return 2 as both keywords will match
        assertEquals(2, comparator.fieldMatch("A long descriptive piece with hello world embedded in the middle", keywords));
    }

    /**
     * Test name field comparison two keywords one keyword.
     */
    @Test
    public void testNameFieldComparisonTwoKeywordsOneKeyword() {

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "Hello World");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "No Match");
        
        IndexerDocument test = new IndexerDocument(solrDoc, false, keywords);
 
        // d1 will match on name twice, so d1 is "smaller"
        assertEquals(1, comparator.compare(test, control));
        // d2 will match on name twice, so d2 is "bigger"
        assertEquals(-1, comparator.compare(control, test));
    }

    /**
     * Test name field comparison.
     */
    @Test
    public void testNameFieldComparison() {

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "No Match");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "No Match");
        
        IndexerDocument test = new IndexerDocument(solrDoc, false, keywords);
 
        // d2 will match on name, so d1 is "smaller"
        assertEquals(-1, comparator.compare(test, control));
        // d1 will match on name, so d1 is "bigger"
        assertEquals(1, comparator.compare(control, test));
    }

    /**
     * Names fields match for keywords, no other fields match, name fields differ alphabetically.
     */
    @Test
    public void testNameAndAlphabeticFieldComparison() {

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "A Hello");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "No Match");
        IndexerDocument test1 = new IndexerDocument(solrDoc, false, keywords);
 
        solrDoc = new SolrDocument();
        solrDoc.addField("name", "Z Hello");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "No Match");
        IndexerDocument test2 = new IndexerDocument(solrDoc, false, keywords);

        // lhs is alphabetically before rhs, so is "bigger" as comparator
        // reverses natural alphabetical ordering
        assertTrue(comparator.compare(test1, test2) > 0);
        // lhs is alphabetically after rhs, so is "smaller" as comparator
        // reverses natural alphabetical ordering
        assertTrue(comparator.compare(test2, test1) < 0);
        
        
    }

    /**
     * Test location field comparison.
     */
    @Test
    public void testLocationFieldComparison() {

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "Hello");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "No Match");
        
        IndexerDocument test = new IndexerDocument(solrDoc, false, keywords);
 
        // d2 will match on location, so d1 is "smaller"
        assertEquals(-1, comparator.compare(test, control));
        // d1 will match on location, so d1 is "bigger"
        assertEquals(1, comparator.compare(control, test));
    }
    
    /**
     * Locations fields match on keyword, no other fields match, name fields differ alphabetically.
     */
    @Test
    public void testLocationAndAlphabeticFieldComparison() {

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "A No Match");
        solrDoc.addField("location", "Hello");
        solrDoc.addField("description", "No Match");
        IndexerDocument test1 = new IndexerDocument(solrDoc, false, keywords);
 
        solrDoc = new SolrDocument();
        solrDoc.addField("name", "B No Match");
        solrDoc.addField("location", "World");
        solrDoc.addField("description", "No Match");
        IndexerDocument test2 = new IndexerDocument(solrDoc, false, keywords);

        // lhs is alphabetically before rhs, so is "bigger" as comparator
        // reverses natural alphabetical ordering
        assertTrue(comparator.compare(test1, test2) > 0);
        // lhs is alphabetically after rhs, so is "smaller" as comparator
        // reverses natural alphabetical ordering
        assertTrue(comparator.compare(test2, test1) < 0);
    }

    /**
     * Test description field comparison.
     */
    @Test
    public void testDescriptionFieldComparison() {

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "Hello");
        solrDoc.addField("location", "World");
        solrDoc.addField("description", "No Match");
        
        IndexerDocument test = new IndexerDocument(solrDoc, false, keywords);
 
        // d2 will match on description, so d1 is "smaller"
        // d2 will match on both keywords, so should be -2 rather than -1
        assertEquals(-2, comparator.compare(test, control));
        // d1 will match on description, so d1 is "bigger"
        // d2 will match on both keywords, so should be 2 rather than 1
        assertEquals(2, comparator.compare(control, test));
    }

    /**
     * Description fields match, no other fields match, names differ alphabetically.
     */
    @Test
    public void testDescriptionAndAlphabeticFieldComparison() {

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "A No Match");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "Hello");
        IndexerDocument test1 = new IndexerDocument(solrDoc, false, keywords);
 
        solrDoc = new SolrDocument();
        solrDoc.addField("name", "B No Match");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "World");
        IndexerDocument test2 = new IndexerDocument(solrDoc, false, keywords);

        // lhs is alphabetically before rhs, so is "bigger" as comparator
        // reverses natural alphabetical ordering
        assertTrue(comparator.compare(test1, test2) > 0);
        // lhs is alphabetically after rhs, so is "smaller" as comparator
        // reverses natural alphabetical ordering
        assertTrue(comparator.compare(test2, test1) < 0);
    }

    /**
     * Test event type no charity name.
     */
    @Test
    public void testEventTypeNoCharityName() {

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "Hello");
        solrDoc.addField("location", "World");
        solrDoc.addField("description", "Hello World");
        solrDoc.addField("type", IndexedType.EVENT.value());
        
        IndexerDocument test = new IndexerDocument(solrDoc, false, keywords);
 
        // d2 has higher score, so d1 is "smaller"
        assertEquals(-1, comparator.compare(control, test));
        // d1 has higher score, so d1 is "bigger"
        assertEquals(1, comparator.compare(test, control));
        
        
    }

    /**
     * Test event type alphabetic charity name.
     */
    @Test
    public void testEventTypeAlphabeticCharityName() {

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "Hello");
        solrDoc.addField("location", "World");
        solrDoc.addField("description", "Hello World");
        solrDoc.addField("type", IndexedType.EVENT.value());
        solrDoc.addField("charity-name", "B Charity");
        
        IndexerDocument test = new IndexerDocument(solrDoc, false, keywords);
 
        // lhs is alphabetically before rhs, so is "bigger" as comparator
        // reverses natural alphabetical ordering
        assertTrue(comparator.compare(control, test) > 0);
        // lhs is alphabetically after rhs, so is "smaller" as comparator
        // reverses natural alphabetical ordering
        assertTrue(comparator.compare(test, control) < 0);
        
    }

    /**
     * Test reorder list.
     */
    @Test
    public void testReorderList() {

        SolrDocument solrDoc = new SolrDocument();
        solrDoc.addField("name", "Z Hello");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "No Match");
        IndexerDocument nameMatchLow = new IndexerDocument(solrDoc, false, keywords);

        solrDoc = new SolrDocument();
        solrDoc.addField("name", "A Hello");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "No Match");
        IndexerDocument nameMatchHigh = new IndexerDocument(solrDoc, false, keywords);

        solrDoc = new SolrDocument();
        solrDoc.addField("name", "Hello");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "No Match");
        solrDoc.addField("type", IndexedType.EVENT.value());
        solrDoc.addField("charity-name", "B Charity");
        IndexerDocument charityNameMatchLow = new IndexerDocument(solrDoc, false, keywords);

        solrDoc = new SolrDocument();
        solrDoc.addField("name", "Hello");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "No Match");
        solrDoc.addField("type", IndexedType.EVENT.value());
        solrDoc.addField("charity-name", "A Charity");
        IndexerDocument charityNameMatchMiddle = new IndexerDocument(solrDoc, false, keywords);

        solrDoc = new SolrDocument();
        solrDoc.addField("name", "Hello");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "No Match");
        solrDoc.addField("type", IndexedType.EVENT.value());
        IndexerDocument charityNameMatchHigh = new IndexerDocument(solrDoc, false, keywords);

        solrDoc = new SolrDocument();
        solrDoc.addField("name", "Z No Match");
        solrDoc.addField("location", "World");
        solrDoc.addField("description", "No Match");
        IndexerDocument locationMatchLow = new IndexerDocument(solrDoc, false, keywords);
        
        solrDoc = new SolrDocument();
        solrDoc.addField("name", "A No Match");
        solrDoc.addField("location", "World");
        solrDoc.addField("description", "No Match");
        IndexerDocument locationMatchHigh = new IndexerDocument(solrDoc, false, keywords);

        solrDoc = new SolrDocument();
        solrDoc.addField("name", "Z No Match");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "Hello World");
        IndexerDocument descriptionMatchLow = new IndexerDocument(solrDoc, false, keywords);

        solrDoc = new SolrDocument();
        solrDoc.addField("name", "A No Match");
        solrDoc.addField("location", "No Match");
        solrDoc.addField("description", "Hello World");
        IndexerDocument descriptionMatchHigh = new IndexerDocument(solrDoc, false, keywords);

        List<IndexerDocument> docs = new ArrayList<IndexerDocument>();
        docs.add(descriptionMatchLow);
        docs.add(nameMatchLow);
        docs.add(locationMatchLow);
        docs.add(descriptionMatchHigh);
        docs.add(nameMatchHigh);
        docs.add(locationMatchHigh);
        docs.add(control);
        docs.add(charityNameMatchHigh);
        docs.add(charityNameMatchMiddle);
        docs.add(charityNameMatchLow);

        IndexerSearchServiceImpl.reorderList(docs);
        
        System.out.println(docs);
        
        // Note that comparators always order smallest -> greatest. So we need to 
        // reverse the compara
        assertSame(control, docs.get(0));
        assertSame(nameMatchHigh, docs.get(1));
        assertSame(charityNameMatchHigh, docs.get(2));
        assertSame(charityNameMatchMiddle, docs.get(3));
        assertSame(charityNameMatchLow, docs.get(4));
        assertSame(nameMatchLow, docs.get(5));
        assertSame(locationMatchHigh, docs.get(6));
        assertSame(locationMatchLow, docs.get(7));
        assertSame(descriptionMatchHigh, docs.get(8));
        assertSame(descriptionMatchLow, docs.get(9));
    }

}
