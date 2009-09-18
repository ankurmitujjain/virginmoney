package com.virginmoneygiving.givingbatch.jms.impl;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.virginmoney.glis.messages.CollectedRegistrationFeeBatch;
import com.virginmoney.glis.messages.MaintenanceResponse;
import com.virginmoney.glis.messages.SettledCharityPaymentsBatch;
import com.virginmoneygiving.givingbatch.jms.GlisMessageDelegate;
import com.virginmoneygiving.givingbatch.launcher.GLISResponseService;
import com.virginmoneygiving.givingbatch.launcher.JobExecutionService;

/**
 * Listener to handle messages from GLIS after processing.
 * 
 * @author Puneet Swarup
 */
public class GlisProcessedMessageDelegateImpl implements GlisMessageDelegate {

    /** Logger instance. */
    private static Logger LOGGER = Logger
            .getLogger(GlisProcessedMessageDelegateImpl.class);

    /** GLIS Response Service. */
    private GLISResponseService glisResponseService;

    /** Job Execution Service. */
    private JobExecutionService jobExecutionService;

    /** Maintenance response simple class name. */
    private static final String MAINTENANCE_RESPONSE_NAME = MaintenanceResponse.class
            .getSimpleName();
    
    /** Name of settled charity payments batch. */
    private static final String SETTLED_CHARITY_PAYMENTS_BATCH_NAME =
            SettledCharityPaymentsBatch.class.getSimpleName();

    /** Name of settled collected registration fee batch. */
    private static String COLLECTED_REGISTRATION_FEE_BATCH_NAME =
            CollectedRegistrationFeeBatch.class.getSimpleName();

    /**
     * {@inheritDoc}
     */
    //public void handleMessage(final TextMessage message) {
    public void handleMessage(final Message message) {

        try {
            Object response = getResponse(message);
            if (response != null) {
                processResponse(response);
            } else {
                LOGGER.error("Unable to identify the message type.");
            }
        } catch (Exception e) {
            LOGGER.error("Exception occurred while processing GLIS output.", e);
        }
    }

    /**
     * Returns the object after converting the given TextMessage..
     * 
     * @param message the message from listener queue.
     * 
     * @return the converted object.
     */
    //private Object getResponse(final TextMessage message) {
    private Object getResponse(final Message message) {

        Object responseObject = null;
        try {
            if (message instanceof TextMessage) {
                //String messageContent = message.getText();
                TextMessage txtMsg = (TextMessage) message;
                LOGGER.debug("TextMessage: " + txtMsg);
                String messageContent = txtMsg.getStringProperty("payload");
                LOGGER.debug("Text message from queue: "
                        + messageContent);
                XStream xstream = new XStream(new DomDriver());
                xstream.setClassLoader(getClass().getClassLoader());

                responseObject = xstream.fromXML(messageContent);
                final Class msgClass = responseObject.getClass();
                final String msgClassName = msgClass.getSimpleName();
                LOGGER.debug("Text Message Class Name: " + msgClassName);
            } else if (message instanceof ObjectMessage) {
                LOGGER.debug("ObjectMessage: " + message);
                ObjectMessage objMsg = (ObjectMessage) message;
                responseObject = objMsg.getObject();
                final Class msgClass = responseObject.getClass();
                final String msgClassName = msgClass.getSimpleName();
                LOGGER.debug("Object Message Class Name: " + msgClassName);

            }
        } catch (JMSException jmse) {
            LOGGER.error("JMSException occurred while identifying message for listener. ", jmse);
        }
        return responseObject;
    }

    /**
     * Processes the response from the message queue.
     * 
     * @param responseObject the response object from the queue.
     */
    private void processResponse(final Object responseObject) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GlisProcessedMessageDelegateImpl.processResponse - START");
        }

        if (responseObject == null) {
            LOGGER.debug("GlisProcessedMessageDelegateImpl.Message is NULL - Returning ...");
            return;
        }
        if (MAINTENANCE_RESPONSE_NAME.equalsIgnoreCase(responseObject.getClass().getSimpleName())) {
            glisResponseService.processMaintenanceResponse((MaintenanceResponse) responseObject);
        }
        if (COLLECTED_REGISTRATION_FEE_BATCH_NAME.equalsIgnoreCase(responseObject.getClass().getSimpleName())) {
            getJobExecutionService()
                    .executeCollectedRegistrationFeeJob((CollectedRegistrationFeeBatch) responseObject);
        }
        if (SETTLED_CHARITY_PAYMENTS_BATCH_NAME.equalsIgnoreCase(responseObject.getClass().getSimpleName())) {
            getJobExecutionService().executeSettledPayment((SettledCharityPaymentsBatch) responseObject);
        }
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GlisProcessedMessageDelegateImpl.processResponse - END");
        }
    }

    /**
     * Sets the glis response service.
     * 
     * @param glisResponseService the glisResponseService to set
     */
    public void setGlisResponseService(GLISResponseService glisResponseService) {
        this.glisResponseService = glisResponseService;
    }

    /**
     * Gets the job execution service.
     * 
     * @return the jobExecutionService
     */
    public JobExecutionService getJobExecutionService() {
        return jobExecutionService;
    }

    /**
     * Sets the job execution service.
     * 
     * @param jobExecutionService the jobExecutionService to set
     */
    public void setJobExecutionService(JobExecutionService jobExecutionService) {
        this.jobExecutionService = jobExecutionService;
    }
}
