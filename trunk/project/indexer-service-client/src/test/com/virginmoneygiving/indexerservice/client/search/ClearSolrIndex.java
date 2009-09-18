package com.virginmoneygiving.indexerservice.client.search;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;

/**
 * The Class to index ten charities.
 * 
 * @author ian.priest@opsera.com
 */
public class ClearSolrIndex {

    /** The solr server. */
    private SolrServer solrServer = null;
    
    /**
     * Delete all entries from the local Solr index
     *
     * @throws Exception the exception
     */
    public void clearSolrIndex() throws Exception {
        
        solrServer = new CommonsHttpSolrServer("http://127.0.0.1:8080/apache-solr-1.3.0");

        solrServer.deleteByQuery("*:*");
        solrServer.commit();
        
    }


}
