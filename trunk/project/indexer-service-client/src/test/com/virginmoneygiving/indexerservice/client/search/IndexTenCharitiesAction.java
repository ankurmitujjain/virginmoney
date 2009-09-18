package com.virginmoneygiving.indexerservice.client.search;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.indexerservice.messages.IndexedType;

/**
 * The Class to index ten charities.
 * 
 * @author ian.priest@opsera.com
 */
public class IndexTenCharitiesAction {

    /** The charities. */
    private final String[][] charities = new String[][] {
        {"Charity One", "2_Henry.JPG", "charity1.url", "Collect money for stuff", "1"},
        {"Charity Two", "2_Henry.JPG", "charity2.url", "Collect money for stuff", "2"},
        {"Charity Three", "2_Henry.JPG", "charity3.url", "Collect money for stuff", "3"},
        {"Charity Four", "2_Henry.JPG", "charity4.url", "Collect money for stuff", "4"},
        {"Charity Five", "2_Henry.JPG", "charity5.url", "Collect money for stuff", "5"},
        {"Charity Six", "2_Henry.JPG", "charity6.url", "Collect money for stuff", "6"},
        {"Charity Seven", "2_Henry.JPG", "charity7.url", "Collect money for stuff", "7"},
        {"Charity Eight", "2_Henry.JPG", "charity8.url", "Collect money for stuff", "8"},
        {"Charity Nine", "2_Henry.JPG", "charity9.url", "Collect money for stuff", "9"},
        {"Charity Ten", "2_Henry.JPG", "charity10.url", "Collect money for stuff", "10"}
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

        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        for (String[] charity : charities) {
            //{"Charity One", "logo1.url", "charity1.url", "Collect money for stuff", "1"},
            
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("name", charity[0]);
            doc.addField("title", charity[0]);
            doc.addField("logo-url", charity[1]);
            doc.addField("url", charity[2]);
            doc.addField("description", charity[3]);
            doc.addField("registered-number", charity[4]);

            String type = IndexedType.CHARITY.value();
            String id = charity[4];
            doc.addField("type", type);
            QName qname = new QName(id, type + "-" + charity[0].replaceAll(" ", "_"));
            doc.addField("id", qname.toString());

            docs.add(doc);
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
        
        List<IndexerDocument> results = 
            indexerSearchService.search("Charity", IndexedType.CHARITY);
        
        assertTrue(results.size() == charities.length);
    }

}
