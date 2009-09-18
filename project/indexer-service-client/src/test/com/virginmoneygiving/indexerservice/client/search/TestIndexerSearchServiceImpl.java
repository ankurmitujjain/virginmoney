package com.virginmoneygiving.indexerservice.client.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.core.CoreContainer;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.indexerservice.messages.IndexedType;

public class TestIndexerSearchServiceImpl {

    private final String[][] charities = new String[][] {
        {"Charity One", "http://logo1.url", "http://charity1.url", "Collect money for stuff", "1", "London"},
        {"Charity Two", "http://logo2.url", "http://charity2.url", "Collect money for stuff", "2", "Paris"}
    };
    
    private final String[][] events = new String[][] {
        {"Event One", "http://logo1.url", "http://charity1.url", "Collect money for stuff", "London", "1"},
        {"Event Two", "http://logo2.url", "http://charity2.url", "Help out others", "New York", "2"}
    };

    private final String[][] fundraiserActivities = new String[][] {
        {"Grow Beard", "Fundraiser One", "http://logo1.url", "http://charity1.url", "1"},
        {"Loose Weight", "Fundraiser Two", "http://logo2.url", "http://charity2.url", "2"},        
        {"Run Marathon", "John Smith", "http://logo3.url", "http://charity3.url", "3"}        
    };

    private SolrServer solrServer = null;
    private IndexerSearchServiceImpl indexerSearchService = null;
    
    /**
     * Set up an embedded Solr instance, add entries to it.
     * Create the IndexerSearchService under test. 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        
        URL url = getClass().getResource("/test/solr-config/solr.xml");
        File configFile = new File(url.getFile());
        CoreContainer coreContainer = new CoreContainer(configFile.getParent(), configFile );
        solrServer = new EmbeddedSolrServer( coreContainer, "core0" );
        solrServer.deleteByQuery("*:*");
        solrServer.commit();

        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        for (String[] charity : charities) {
            //{"Charity One", "http://logo1.url", "http://charity1.url", "Collect money for stuff", "1"},
            
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("name", charity[0]);
            doc.addField("logo-url", charity[1]);
            doc.addField("url", charity[2]);
            doc.addField("description", charity[3]);
            doc.addField("registered-number", charity[4]);
            doc.addField("location", charity[5]);

            String type = IndexedType.CHARITY.value();
            String id = charity[4];
            doc.addField("type", type);
            QName qname = new QName(type, id);
            doc.addField("id", qname.toString());

            docs.add(doc);
        }

        for (String[] event : events) {
            //{"Event Two", "http://logo2.url", "http://charity2.url", "An event", "Paris", "2"}

            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("name", event[0]);
            doc.addField("logo-url", event[1]);
            doc.addField("url", event[2]);
            doc.addField("description", event[3]);
            doc.addField("location", event[4]);

            String type = IndexedType.EVENT.value();
            String id = event[5];
            doc.addField("type", type);
            QName qname = new QName(type, id);
            doc.addField("id", qname.toString());

            docs.add(doc);
        }
        
        for (String[] fundraiserActivity : fundraiserActivities) {
            //{"Fundraiser Activity One", "http://logo1.url", "http://charity1.url", "1"},

            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("title", fundraiserActivity[0]);
            doc.addField("name", fundraiserActivity[1]);
            doc.addField("logo-url", fundraiserActivity[2]);
            doc.addField("url", fundraiserActivity[3]);

            String type = IndexedType.FUNDRAISER_ACTIVITY.value();
            String id = fundraiserActivity[4];
            doc.addField("type", type);
            QName qname = new QName(type, id);
            doc.addField("id", qname.toString());

//            for (String fundraiser : fundraisers) {
//                doc.addField("fundraiser", fundraiser); 
//            }

            docs.add(doc);
        }

        solrServer.add(docs);
        solrServer.commit();
        
        indexerSearchService = new IndexerSearchServiceImpl();
        indexerSearchService.setSolrServer(solrServer);
    }

    @Test
    public void testSimpleCharitySearch() throws Exception {
        
        // Search for "Charity" among charities - should return 2
        List<IndexerDocument> results = 
            indexerSearchService.search("Charity", IndexedType.CHARITY);
        
        //System.out.println(results);
        assertTrue(results.size() == 2);
    }

    @Test
    public void testSimpleMultiTypeSearch() throws Exception {
        
        // Search for "One Fund" among Charities and Fundraisers.
        // Should return three - "Fundraiser One" activity as the highest scorer, 
        // "Fundraiser Two" and "Charity One" as the others  
        List<IndexerDocument> results = 
            indexerSearchService.search("One Fund", IndexedType.CHARITY, IndexedType.FUNDRAISER_ACTIVITY);
        
        //System.out.println(results);
        assertTrue(results.size() == 3);

        // Check ordered correctly: all name matches, so should order by score
        Float previousScore = null;
        for (IndexerDocument solrDocument : results) {
            
            Float thisScore = (Float)solrDocument.getScore();
            
            if ( previousScore == null ) {
                previousScore = thisScore;
                continue;
            }
            
            assertTrue(previousScore >= thisScore);
            previousScore = thisScore;
        }
        
        // Check highest was the expected one
        IndexerDocument higestScore = results.get(0);
        assertEquals("Fundraiser One", (String)higestScore.getName());
    }

    @Test
    public void testSimpleMultiWordSearch() throws Exception {
        
        List<IndexerDocument> results = 
            indexerSearchService.search("Fund");
        
        //System.out.println(results);
        assertTrue(results.size() == 2);
    }

    /**
     * Check the search results match the usecase requirements
     */
    @Test
    public void testUseCaseConformance() throws Exception {

        // "John Smith" == "John Smith"
        List<IndexerDocument> results = 
            indexerSearchService.search("John Smith", IndexedType.FUNDRAISER_ACTIVITY);
        assertEquals(1, results.size());
        assertEquals("John Smith", results.get(0).getName());
        
        // "Smith" == "John Smith"
        results = 
            indexerSearchService.search("Smith", IndexedType.FUNDRAISER_ACTIVITY);
        assertEquals(1, results.size());
        assertEquals("John Smith", results.get(0).getName());

        // "Smit" == "John Smith"
        results = 
            indexerSearchService.search("Smit", IndexedType.FUNDRAISER_ACTIVITY);
        assertEquals(1, results.size());
        assertEquals("John Smith", results.get(0).getName());

        // "Smi" == "John Smith"
        results = 
            indexerSearchService.search("Smi", IndexedType.FUNDRAISER_ACTIVITY);
        assertEquals(1, results.size());
        assertEquals("John Smith", results.get(0).getName());
    }
    
    /**
     * Check the search returns results in the correct order:
     * Name, followed by location, followed by description.
     * 
     * Should order as: 
     * Fundraiser One - name matches two keywords
     * Charity One, Event One - name and description matches. Charity One 
     * first as alphabetic ordering applies
     * Fundraiser Two - name match
     * Event Two - location match
     * Charity Two - description match
     * @throws Exception
     */
    @Test
    public void testSearchOrder() throws Exception {
        List<IndexerDocument> results = 
            indexerSearchService.search("Fund One Money New", 
                IndexedType.FUNDRAISER_ACTIVITY,
                IndexedType.CHARITY,
                IndexedType.EVENT);
        
        //System.out.println(results);

        assertEquals(6, results.size());

        assertEquals("Fundraiser One",  results.get(0).getName());
        assertEquals("Charity One",       results.get(1).getName());
        assertEquals("Event One",     results.get(2).getName());
        assertEquals("Fundraiser Two",  results.get(3).getName());
        assertEquals("Event Two",       results.get(4).getName());
        assertEquals("Charity Two",     results.get(5).getName());
    }
}
