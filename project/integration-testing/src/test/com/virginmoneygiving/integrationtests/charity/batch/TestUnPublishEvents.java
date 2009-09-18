package com.virginmoneygiving.integrationtests.charity.batch;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.xml.namespace.QName;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;
import com.virginmoneygiving.integrationtests.dbunit.creators.CreateFiveLiveCharities;

/**
 * Test un-publish Events
 * 
 * Set up the database with some charities and some published events, then 
 * create a fake message from scheduler to invoke the unpublish event job. 
 * Check the database is in the expected state afterwards and that the events 
 * have been deleted from the index.
 *
 * @author ian.priest@opsera.com
 */
public class TestUnPublishEvents {

    private static final String BATCH_QUEUE_NAME = System.getProperty("queue.name", "queue/givingBatchJobServiceQueue");
    private static final Object JBOSS_JNDI_URL = "jnp://" + System.getProperty("jboss.hostname", "localhost") + ":1099";
    private static final String SOLR_URL = System.getProperty("solr.searcher.url", "http://127.0.0.1:8080/apache-solr-1.3.0");
    
    /** Db unit helper. */
    private DbUnitHelper givingDbUnitHelper = null;

    /** Solr server. */
    CommonsHttpSolrServer solrServer;
    
    /**
     * Create the db-helper, set up the initial dataset, check we have a
     * connection to jms.
     */
    @Before
    public void setUp() throws Exception {

        // Add five live charities
        CreateFiveLiveCharities.createFiveLiveCharites();
        
        /*
         * Set up dbunit helpers and put the database in a known state
         */
        givingDbUnitHelper =
            new DbUnitHelper(
                DbUnitHelper.GIVING_DB_URL, 
                DbUnitHelper.GIVING_DB_USERNAME, 
                DbUnitHelper.GIVING_DB_PASSWORD, 
                DbUnitHelper.GIVING_DB_SCHEMA,
                "/com/virginmoneygiving/integrationtests/charity/unpublishevents/giving-after-dataset.xml",
                "/com/virginmoneygiving/integrationtests/charity/unpublishevents/giving-before-dataset.xml");
        givingDbUnitHelper.onSetUp();

        /*
         * Connect to Solr, clear the index then index 5 documents with ids that will match the events
         */
        solrServer = new CommonsHttpSolrServer(SOLR_URL);
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
        
        for( int i = 1; i < 6; i++ ) {
        
            SolrInputDocument doc1 = new SolrInputDocument();
        
            String type = "EVENT";
            String id = "" + i;
            String name = "Selenium_Event";
            QName qname = new QName(id, type + "-" + name);

            doc1.addField("id", qname.toString());
            doc1.addField("type", type);
            doc1.addField("name", name);

            solrServer.add(doc1);
        }
        
        solrServer.commit();

    }

    @After
    public void after() throws Exception {

        givingDbUnitHelper.onTeardown();
    }

    /**
     * Send a message that appears to be from scheduler-service.
     * 
     * Should cause giving-batch to invoke the PutCharityLiveJob and
     * update the status of the charity records in the database.
     * It should also cause the cahrities to be indexed.
     */
    @Test
    public void testUnpublish() throws Exception {

        /*
         * Assert that the index has content
         */
        SolrQuery query = new SolrQuery().setQuery( "*:*" );
        QueryResponse queryResponse = null;
        queryResponse = solrServer.query(query);
        SolrDocumentList docs = queryResponse.getResults();
        assertEquals(5, docs.size());

        /**
         * Send the message.
         */
        sendJmsTextMessage();

        // Pause to give the batch job time to run on jboss. It takes a while, about
        // 34 seconds on my pc from jms message sent until last charity indexed, so give it
        // 40 seconds
        Thread.sleep(40000);

        // Check the database
        givingDbUnitHelper.checkExpected();
        
        // Check the events have been deleted from the index
        query = new SolrQuery().setQuery( "*:*" );
        queryResponse = null;
        queryResponse = solrServer.query(query);
        docs = queryResponse.getResults();
        assertEquals(0, docs.size());
    }

    private void sendJmsTextMessage() throws Exception {

        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY,
            "org.jnp.interfaces.NamingContextFactory");
        p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        p.put(Context.PROVIDER_URL, JBOSS_JNDI_URL);
        InitialContext init = new InitialContext(p);

        // get reference to JMS destination
        javax.jms.Queue destination =
            (javax.jms.Queue) init
                .lookup(BATCH_QUEUE_NAME);

        // get reference to the ConnectionFactory
        ConnectionFactory connectionFactory =
            (ConnectionFactory) init.lookup("ConnectionFactory");

        Connection connection = connectionFactory.createConnection();
        Session session =
            connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        TextMessage myMessage = session.createTextMessage();
        myMessage.setStringProperty("jobName", "eventUnpublishJob");

        MessageProducer producer = session.createProducer(destination);
        producer.send(myMessage);
        
        connection.close();

    }
}
