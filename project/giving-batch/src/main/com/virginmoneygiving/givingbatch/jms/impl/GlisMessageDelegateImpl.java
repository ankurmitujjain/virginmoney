package com.virginmoneygiving.givingbatch.jms.impl;

import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.virginmoneygiving.givingbatch.jms.GlisMessageDelegate;
import com.virginmoneygiving.givingbatch.launcher.JobExecutionService;
import com.virginmoney.glis.messages.CollectedRegistrationFeeBatch;
import com.virginmoney.glis.messages.SettledCharityPaymentsBatch;

/**
 * The Class GlisMessageDelegateImpl.
 */
public class GlisMessageDelegateImpl implements GlisMessageDelegate {

    /** The LOGGER. */
    private static Logger LOGGER = Logger.getLogger(GlisMessageDelegateImpl.class);


    /** The job execution service. */
    private JobExecutionService jobExecutionService = null;

    /**
     * Gets the job execution service.
     * 
     * @return the job execution service
     */
    public JobExecutionService getJobExecutionService() {
        return jobExecutionService;
    }

    /**
     * Sets the job execution service.
     * 
     * @param jobExecutionService the new job execution service
     */
    public void setJobExecutionService(JobExecutionService jobExecutionService) {
        this.jobExecutionService = jobExecutionService;
    }

    /**
     * Recieve an inbound JMS from GLIS and run a job from it.
     * 
     * @param message the message
     */
    //public void handleMessage(TextMessage message) {
    public void handleMessage(Message message) {

        try {

            if (message instanceof TextMessage) {
                //String messageContent = message.getText();
                TextMessage txtMsg = (TextMessage) message;
                String messageContent = txtMsg.getText();
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("GlisMessageDelegateImpl.handleMessage: Start payload deconstructL: " + messageContent);
                }
                Object msgObject = null;
                XStream xstream = new XStream(new DomDriver());
                // Set up the classloader to avoid false ClassCastExceptions
                xstream.setClassLoader(getClass().getClassLoader());

                msgObject = xstream.fromXML(messageContent);
                Class msgClass = msgObject.getClass();
                String msgClassName = msgClass.getSimpleName();
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("GlisMessageDelegateImpl.handleMessage Message Class Name is: " + msgClassName);
                    LOGGER.debug("GlisMessageDelegateImpl.handleMessage - Payload deconstructed for class: " + msgClassName);
                }
            } else if (message instanceof ObjectMessage) {
                LOGGER.debug("GlisMessageDelegateImpl.handleMessage ObjectMessage: " + message);
                ObjectMessage objMsg = (ObjectMessage) message;
                Object responseObject = objMsg.getObject();
                final Class msgClass = responseObject.getClass();
                final String msgClassName = msgClass.getSimpleName();
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("GlisMessageDelegateImpl.handleMessage: Object Message Class Name: " + msgClassName);
                }

            }


        }
        catch (Exception e) {
            LOGGER.error("Unexpected error in GlisMessageDelegateImpl.handleMessage", e);
        }
    }

}
