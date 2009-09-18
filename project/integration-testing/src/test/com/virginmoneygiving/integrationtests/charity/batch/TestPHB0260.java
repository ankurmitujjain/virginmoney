package com.virginmoneygiving.integrationtests.charity.batch;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;

/**
 * Test UC PHB0260 - Charity Registration Fee collected.
 * 
 * Set up the database with some fake payments then create a fake message from
 * GLIS to simulate the process. Check the database is in the expected state
 * afterwards. Note that this is a reasonably complex test-case because it has
 * to connect to a JMS queue and send a message. 
 * 
 * @author ian.priest@opsera.com
 * 
 */
public class TestPHB0260 {

    private static final String GLIS_RESPONSE_QUEUE = System.getProperty("glis.queue.name", "queue/virginMoneyGivingQ.glis_responses");
    private static final Object JBOSS_JNDI_URL = "jnp://" + System.getProperty("jboss.hostname", "localhost") + ":1099";
    
    /** Db unit helper. */
    private DbUnitHelper paymentDbUnitHelper = null;

    /**
     * Create the db-helper, set up the initial dataset, check we have a
     * connection to jms.
     */
    @Before
    public void setUp() throws Exception {

        /*
         * Set up dbunit helper and put the database in a known state
         */
        paymentDbUnitHelper =
            new DbUnitHelper(
                DbUnitHelper.GIVING_DB_URL, 
                DbUnitHelper.GIVING_DB_USERNAME, 
                DbUnitHelper.GIVING_DB_PASSWORD, 
                DbUnitHelper.GIVING_DB_SCHEMA,
                "/com/virginmoneygiving/integrationtests/charity/phb0260/payment-after-dataset.xml",
                "/com/virginmoneygiving/integrationtests/charity/phb0260/payment-before-dataset.xml");
        paymentDbUnitHelper.onSetUp();

    }

    @After
    public void after() throws Exception {

        paymentDbUnitHelper.onTeardown();
    }

    /**
     * Send a message that appears to be from GLIS.
     * 
     * Should cause giving-batch to invoke the registrationFeeCollected job and
     * update the payment status of the records in the database.
     */
    @Test
    public void testPhb0260() throws Exception {

        /*
         * Load the test data from file.
         * Contents of the the file get sent to Jboss as a JMS message
         * that will apppear to have come from GLIS.
         */
        InputStream isTestData =
            getClass()
                .getResourceAsStream(
                    "/com/virginmoneygiving/integrationtests/charity/phb0260/collectedRegistrationFeeBatch.xml");
        assertNotNull(isTestData);
        InputStreamReader isReader = new InputStreamReader(isTestData);
        BufferedReader br = new BufferedReader(isReader);

        final StringBuilder sb = new StringBuilder();
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
            if (isReader != null) {
                isReader.close();
            }
            if (isTestData != null) {
                isTestData.close();
            }
        }

        /**
         * Send the message.
         */
        sendJmsTextMessage(sb.toString());

        // Pause to give the batch job time to run on jboss
        Thread.sleep(10000);

        // Check the database
        paymentDbUnitHelper.checkExpected();
    }

    private void sendJmsTextMessage(String message) throws Exception {

        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY,
            "org.jnp.interfaces.NamingContextFactory");
        p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        p.put(Context.PROVIDER_URL, JBOSS_JNDI_URL);
        InitialContext init = new InitialContext(p);

        // get reference to JMS destination
        javax.jms.Queue destination =
            (javax.jms.Queue) init
                .lookup(GLIS_RESPONSE_QUEUE);

        // get reference to the ConnectionFactory
        ConnectionFactory connectionFactory =
            (ConnectionFactory) init.lookup("ConnectionFactory");

        Connection connection = connectionFactory.createConnection();
        Session session =
            connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        TextMessage myMessage = session.createTextMessage();
        myMessage.setStringProperty("payload", message);

        MessageProducer producer = session.createProducer(destination);
        producer.send(myMessage);
        
        connection.close();

    }
}
