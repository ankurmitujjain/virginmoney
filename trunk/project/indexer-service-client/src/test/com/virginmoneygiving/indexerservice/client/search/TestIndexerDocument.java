package com.virginmoneygiving.indexerservice.client.search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.common.SolrDocument;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.indexerservice.client.search.IndexerDocument;

/**
 * The Test Class for IndexerDocument.
 * 
 * @author Ian Priest
 */
public class TestIndexerDocument {

    /** The indexer document. */
    private IndexerDocument indexerDocument;
    
    /**
     * Sets the up.
     * 
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        indexerDocument = new IndexerDocument(null, false, null);
    }

    /**
     * Test method to test replace functionality.
     */
    @Test
    public void testReplace() {
        List<String> keywords = new ArrayList<String>();
        keywords.add("fund");
        keywords.add("one");
        indexerDocument = new IndexerDocument(null, true, keywords);
        
        String highlighted = indexerDocument.hightlight("Fundraiser One");
        assertEquals("<b>Fund</b>raiser <b>One</b>", highlighted);

        highlighted = indexerDocument.hightlight("Charity One");
        assertEquals("Charity <b>One</b>", highlighted);

        highlighted = indexerDocument.hightlight("Charity Two");
        assertEquals("Charity Two", highlighted);

        highlighted = indexerDocument.hightlight("Somefundraiser Phone");
        assertEquals("Some<b>fund</b>raiser Ph<b>one</b>", highlighted);
    }

    /**
     * Test return types.
     */
    @Test
    public void testReturnTypes() {
        SolrDocument solrDocument = new SolrDocument();
        solrDocument.addField("charity-name", "a");
        
        solrDocument.addField("charity-url", "b");
        solrDocument.addField("charity-url", "b");

        solrDocument.addField("team-activity", true);
        
        Object aVal = solrDocument.getFieldValue("charity-name");
        assertTrue( aVal instanceof String );

        Object bVal = solrDocument.getFieldValue("charity-url");
        assertTrue( bVal instanceof List );
        
        Object cVal = solrDocument.getFieldValue("team-activity");
        assertTrue( cVal instanceof Boolean);
        
        IndexerDocument m_indexerDocument = new IndexerDocument(solrDocument, false, null);
        aVal = m_indexerDocument.getCharityNames();
        assertTrue( aVal instanceof List );
        assertEquals( 1, ((List)aVal).size() );

        aVal = m_indexerDocument.getCharityUrls();
        assertTrue( aVal instanceof List );
        assertEquals( 2, ((List)aVal).size() );
        
        solrDocument = new SolrDocument();
        m_indexerDocument = new IndexerDocument(solrDocument, false, null);
        
        aVal = m_indexerDocument.getCharityNames();
        assertTrue( aVal instanceof List );
        assertEquals( 0, ((List)aVal).size() );
        
    }
}
