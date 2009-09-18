package com.virginmoneygiving.integrationtests.solr;

import static org.junit.Assert.assertTrue;

import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.junit.Before;
import org.junit.Test;

/**
 * Clean the Solr indexes.
 *
 * @author ian.priest@opsera.com
 */
public class SolrClearIndex {

    private static final String SOLR_URL = System.getProperty("solr.searcher.url", "http://127.0.0.1:8080/apache-solr-1.3.0");
    
    /** Solr server. */
    CommonsHttpSolrServer solrServer;
    
    /**
     * Create the db-helper, set up the initial dataset, check we have a
     * connection to jms.
     */
    @Before
    public void setUp() throws Exception {

        /*
         * Connect to Solr and make sure that the index is clear
         */
        solrServer = new CommonsHttpSolrServer(SOLR_URL);
        solrServer.deleteByQuery("*:*");
        solrServer.commit();

    }

    /**
     * Send a message that appears to be from scheduler-service.
     * 
     * Should cause giving-batch to invoke the PutCharityLiveJob and
     * update the status of the charity records in the database.
     * It should also cause the cahrities to be indexed.
     */
    @Test
    public void testNothing() throws Exception {
        assertTrue(true);
    }

}
