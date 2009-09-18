package com.virginmoneygiving.integrationtests.charity.batch;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;
import com.virginmoneygiving.integrationtests.dbunit.creators.CreatorUtils;

/**
 * Test Put Charity Live.
 * 
 * Set up the database with a charity then create a fake message from
 * GLIS to simulate the process. Check the database is in the expected state
 * afterwards and that the charity has been indexed.
 *
 * @author ian.priest@opsera.com
 */
public class TestPutCharityLive {

    private static final String BATCH_QUEUE_NAME = System.getProperty("queue.name", "queue/givingBatchJobServiceQueue");
    private static final Object JBOSS_JNDI_URL = "jnp://" + System.getProperty("jboss.hostname", "localhost") + ":1099";
    private static final String SOLR_URL = System.getProperty("solr.searcher.url", "http://127.0.0.1:8080/apache-solr-1.3.0");
    
    /** Db unit helper. */
    private DbUnitHelper givingDbUnitHelper = null;

    /** Db unit helper. */
    private DbUnitHelper paymentDbUnitHelper = null;

    /** Solr server. */
    CommonsHttpSolrServer solrServer;
    
    /**
     * Create the db-helper, set up the initial dataset, check we have a
     * connection to jms.
     *
     * After setup has run we should have 5 users set up, icluding password info, and
     * five charities in IRC state that will be set live by the batch job.
     */
    @Before
    public void setUp() throws Exception {

        /*
         * Use creator utils to set up secure-data and secure-ws
         */
        Integer passwordId = CreatorUtils.insertSecureData();
        CreatorUtils.insertSecurityInfo(
            passwordId,
            "/com/virginmoneygiving/integrationtests/dbunit/creators/common/five-charity-admins-security-dataset.xml");

        /*
         * Set up dbunit helpers and put the database in a known state
         */
        givingDbUnitHelper =
            new DbUnitHelper(
                DbUnitHelper.GIVING_DB_URL, 
                DbUnitHelper.GIVING_DB_USERNAME, 
                DbUnitHelper.GIVING_DB_PASSWORD, 
                DbUnitHelper.GIVING_DB_SCHEMA,
                "/com/virginmoneygiving/integrationtests/charity/putbatchlive/giving-after-dataset.xml",
                "/com/virginmoneygiving/integrationtests/charity/putbatchlive/giving-before-dataset.xml",
                "/com/virginmoneygiving/integrationtests/dbunit/creators/common/five-charities-dataset.xml");
        givingDbUnitHelper.getObjectMap().put("[status]", "IRC");
        givingDbUnitHelper.onSetUp();

        paymentDbUnitHelper =
            new DbUnitHelper(
                DbUnitHelper.PAYMENT_DB_URL, 
                DbUnitHelper.PAYMENT_DB_USERNAME, 
                DbUnitHelper.PAYMENT_DB_PASSWORD, 
                DbUnitHelper.PAYMENT_DB_SCHEMA,
                "/com/virginmoneygiving/integrationtests/charity/putbatchlive/payment-after-dataset.xml",
                "/com/virginmoneygiving/integrationtests/charity/putbatchlive/payment-before-dataset.xml");
        paymentDbUnitHelper.onSetUp();

        /*
         * Connect to Solr and make sure that the index is clear
         */
        solrServer = new CommonsHttpSolrServer(SOLR_URL);
        solrServer.deleteByQuery("*:*");
        solrServer.commit();

    }

    @After
    public void after() throws Exception {

        paymentDbUnitHelper.onTeardown();
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
    public void testPutLive() throws Exception {

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
        
        // Check the charities have been indexed
        SolrQuery query = new SolrQuery().setQuery( "*:*" );
        QueryResponse queryResponse = null;
        queryResponse = solrServer.query(query);
        SolrDocumentList docs = queryResponse.getResults();
        assertEquals(5, docs.size());
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
        myMessage.setStringProperty("jobName", "putCharityLiveBatchJob");

        MessageProducer producer = session.createProducer(destination);
        producer.send(myMessage);
        
        connection.close();

    }
}
