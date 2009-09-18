package com.virginmoneygiving.indexerservice.client.search;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.SolrCore;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.giving.domain.FundraisingCharitySplit;
import com.virginmoneygiving.indexerservice.client.search.IndexerDocument;
import com.virginmoneygiving.indexerservice.client.search.IndexerSearchServiceImpl;
import com.virginmoneygiving.indexerservice.messages.IndexedType;
import com.virginmoneygiving.indexerservice.messages.IndexerCharity;

/**
 * The Class to index ten fundraiser activities.
 */
public class IndexTenFundraiserActivitiesAction {

    /** The fundraiser activities. */
    private final String[][] fundraiserActivities = new String[][] {
        {"Grow Beard", "Fundraiser One", "100x100no-image.jpg", "activity1.url", "1", "1-12-2009"},
        {"Loose Weight", "Fundraiser Two", "100x100no-image.jpg", "activity2.url", "2", "1-12-2009"},        
        {"Run Marathon", "John Smith", "100x100no-image.jpg", "activity3.url", "3", "1-12-2009"},        
        {"Run Marathon", "John Smithville", "100x100no-image.jpg", "activity4.url", "4", "1-12-2009"},        
        {"Run Marathon", "John Smithson", "100x100no-image.jpg", "activity5.url", "5", "1-12-2009"},        
        {"Run Marathon", "John Smithsouth", "100x100no-image.jpg", "activity6.url", "6", "1-12-2009"},        
        {"Run Marathon", "Jenny Smith", "100x100no-image.jpg", "activity7.url", "7", "1-12-2009"},        
        {"Run Marathon", "Julie Smith", "100x100no-image.jpg", "activity8.url", "8", "1-12-2009"},        
        {"Run Marathon", "Jane Smith", "100x100no-image.jpg", "activity9.url", "9", "1-12-2009"},       
        {"Run Marathon", "Joan Smith", "100x100no-image.jpg", "activity10.url", "10", "1-12-2009"}        
    };
    
    /** The charities. */
    private final String[] charities = new String[] {
        "Charity One", "Charity Two"
    };
    
    /** The charity urls. */
    private final String[] charityUrls = new String[] {
        "charity1.url", "charity2.url"
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

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        solrServer = new CommonsHttpSolrServer("http://127.0.0.1:8080/apache-solr-1.3.0");

        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        for (String[] fundraiserActivity : fundraiserActivities) {
            //{"Fundraiser Activity One", "http://logo1.url", "http://charity1.url", "1"},

            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("title", fundraiserActivity[0]);
            doc.addField("name", fundraiserActivity[1]);
            doc.addField("logo-url", fundraiserActivity[2]);
            doc.addField("url", fundraiserActivity[3]);

            Date eventDate = sdf.parse(fundraiserActivity[5]);
            doc.addField("date", eventDate);

            String type = IndexedType.FUNDRAISER_ACTIVITY.value();
            String id = fundraiserActivity[4];
            doc.addField("type", type);
            QName qname = new QName(id, type + "-" + fundraiserActivity[1].replaceAll(" ", "_"));
            doc.addField("id", qname.toString());

            for (String charity : charities) {
                doc.addField("charity-name", charity); 
            }
            for (String charityUrl : charityUrls) {
                doc.addField("charity-url", charityUrl); 
            }

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
        
        // Search for "Charity" among charities - should return 2
        List<IndexerDocument> results = 
            indexerSearchService.search("Fundraiser Smith", IndexedType.FUNDRAISER_ACTIVITY);
        
        assertTrue(results.size() == 10);
    }

}
