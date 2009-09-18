package com.virginmoneygiving.indexerservice.client.search;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.indexerservice.messages.IndexedType;

/**
 * The Class to index ten events.
 * 
 * @author ian.priest@opsera.com
 */
public class IndexTenEventsAction {

    /** The charities. */
    private final String[][] charities = new String[][] {
        {"Charity One", "charity1.url", "1"},
    };
  
    /** The events. */
    private final String[][] events = new String[][] {
        {"Event One", "logo1.url", "event1.url", "An Event to collect money for stuff", "London", "1", "1-12-2009"},
        {"Event Two", "logo2.url", "event2.url", "An Event to collect money for stuff", "Paris", "2", "2-12-2009"},
        {"Event Three", "logo3.url", "event3.url", "An Event to collect money for stuff", "Paris", "3", "3-12-2009"},
        {"Event Four", "logo4.url", "event4.url", "An Event to collect money for stuff", "Paris", "4", "4-12-2009"},
        {"Event Five", "logo5.url", "event5.url", "An Event to collect money for stuff", "Paris", "5", "5-12-2009"},
        {"Event Six", "logo6.url", "event6.url", "An Event to collect money for stuff", "Paris", "6", "6-12-2009"},
        {"Event Seven", "logo7.url", "event7.url", "An Event to collect money for stuff", "Paris", "7", "7-12-2009"},
        {"Event Eight", "logo8.url", "event8.url", "An Event to collect money for stuff", "Paris", "8", "8-12-2009"},
        {"Event Nine", "logo9.url", "event9.url", "An Event to collect money for stuff", "Paris", "9", "9-12-2009"},
        {"Event Ten", "logo10.url", "event10.url", "An Event to collect money for stuff", "Paris", "10", "10-12-2009"}
    };
    
    /** The solr server. */
    private SolrServer solrServer = null;
    
    /** The indexer search service. */
    private IndexerSearchServiceImpl indexerSearchService = null;
    
    /**
     * Set up an embedded Solr instance, add entries to it.
     * Create the IndexerSearchService under test.
     * 
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        
        solrServer = new CommonsHttpSolrServer("http://127.0.0.1:8080/apache-solr-1.3.0");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        

        for (String[] charity : charities ) {
        
            for (String[] event : events) {
                //{"Event Two", "logo2.url", "event.url", "An event", "Paris", "2"}
    
                SolrInputDocument doc = new SolrInputDocument();
                doc.addField("name", event[0]);
                doc.addField("title", event[0]);
                doc.addField("logo-url", event[1]);
                doc.addField("url", event[2]);
                doc.addField("description", event[3]);
                doc.addField("location", event[4]);
    
                doc.addField("charity-name", charity[0]);
                doc.addField("charity-url", charity[1]);
                doc.addField("charity-id", charity[2]);
                
                String type = IndexedType.EVENT.value();
                doc.addField("type", type);
                QName qname = new QName(event[5], type + "-" + charity[2]);
                doc.addField("id", qname.toString());
    
                Date eventDate = sdf.parse(event[6]);
                doc.addField("date", eventDate);
                
                docs.add(doc);
            }
        }
        
        solrServer.add(docs);
        solrServer.commit();
        
        indexerSearchService = new IndexerSearchServiceImpl();
        indexerSearchService.setSolrServer(solrServer);
    }

    /**
     * Test simple charity search.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testSimpleCharitySearch() throws Exception {
        
        // Search for "Charity" among charities - should return 2
        List<IndexerDocument> results = 
            indexerSearchService.search("Event", IndexedType.EVENT);
        
        assertTrue(results.size() == 10);
    }

}
