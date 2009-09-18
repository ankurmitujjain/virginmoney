package com.virginmoneygiving.givingbatch.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.common.VMGProperty;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingmanagement.service.messages.ContactDetails;
import com.virginmoneygiving.givingmanagement.service.messages.FetchEmailForSettlementRequest;
import com.virginmoneygiving.givingmanagement.service.messages.FetchEmailForSettlementResponse;
import com.virginmoneygiving.givingmanagement.service.messages.GivingManagementServiceFaultException;

import javax.jms.JMSException;
import com.thoughtworks.xstream.XStream;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import com.virginmoneygiving.givingbatch.helper.EmailHelper;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingManagementServiceLocatorImpl;

/**
 * This class is used to make a gliss call for PaymentSettled job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Yogesh Salunkhe
 */
public class PaymentSettledListener extends
    StepExecutionListenerSupport implements StepExecutionListener, ItemWriteListener {

    /** create instance of logger. */
    private static final Logger LOGGER =
        Logger.getLogger(PaymentSettledListener.class);

    /** create instance of batch delegate to injectinside xml. */
    private BatchDelegate batchDelegate;

    /** create instance of stepExecution. */
    private StepExecution stepExecution;
    
    /** instance for ExecutionContext. * */
    private ExecutionContext executionContext;
    
    /** Giving service - web service locator. */
    private GivingManagementServiceLocatorImpl givingService;

    /** Email template. */
    JmsTemplate jmsEmailTemplate;

    /**
     * Sets the jms email template.
     * 
     * @param jmsEmailTemplate the new jms email template
     */
    public void setJmsEmailTemplate(JmsTemplate jmsEmailTemplate) {
        this.jmsEmailTemplate = jmsEmailTemplate;
    }
    
    /**
     * Sets the batch delegate.
     * 
     * @param batchDelegate the batchDelegate to set
     */
    public void setBatchDelegate(BatchDelegate batchDelegate) {
        this.batchDelegate = batchDelegate;
    }

    /**
     * This method will assign the step execution context whenever transformer
     * called.
     * 
     * @param stepExecution of tyep StepExecution
     */
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    /**
     * Call GLIS Service.
     * 
     * Will raise runtime exception to ensure transaction rollback if a
     * GLIS error is returned.
     * 
     * @param ignored not used
     */
    public void afterWrite(List ignored) {
        ExecutionContext executionContext = stepExecution.getJobExecution()
        .getExecutionContext();
        Set<Long> charityIds = (Set) executionContext.get("charityId");
        if (charityIds != null){
            //Iterator<Long> iter = charityIds.iterator();
            try {
                final FetchEmailForSettlementRequest request = new FetchEmailForSettlementRequest();
                request
                        .setHeader(Util.getGivingHeader("settledPaymentJob"));
                request.getCharityIds().addAll(new ArrayList<Long>(charityIds));
                final FetchEmailForSettlementResponse response = givingService.getGivingManagementPort().fetchEmailAddresssForSettlement(request);
                    if (response != null) {
                    final List<ContactDetails> contact = response.getContact();
                    for(ContactDetails contactDetails : contact) {
                        String emailAddress = contactDetails.getEmailAddress();
                        String foreName = contactDetails.getForeName();
                        String surName = contactDetails.getSurName();
                        if(surName != null) {
                            foreName = foreName.concat(" ").concat(surName);
                        }
                        sendEmail(EmailHelper.paymentSettledMessage(emailAddress, foreName));
                    }
                }
           
            }
            catch (GivingManagementServiceFaultException exception) {
                LOGGER.error("Exception occured in PaymentSettledListener while fetching email address", exception);
            }
        
        }

    }

    /**
     * Unimplemented.
     * 
     * @param ignored not used
     */
    public void beforeWrite(List ignored) {
    }

    /**
     * Unimplemented.
     * 
     * @param exception not used
     * @param list not used
     */
    public void onWriteError(Exception exception, List list) {
    }
    

    /**
     * Send email.
     * 
     * @param message -
     * Message containing mail details.
     */
    public void sendEmail(
        com.virginmoneygiving.templateemail.service.messages.Message message) {
        message.setSender(VMGProperty.getProperty("sender.email.address"));
        XStream xstream = new XStream();
        final String textMessage = xstream.toXML(message);
        jmsEmailTemplate.send(new MessageCreator(){
            public javax.jms.Message createMessage(Session session)
                throws JMSException {
                LOGGER.debug("Text Message is:" + textMessage);
                return session.createTextMessage(textMessage);
            }
        });
    }
    
    /**
     * Sets the giving service.
     * 
     * @param givingService the givingService to set
     */
    public void setGivingService(
            GivingManagementServiceLocatorImpl givingService) {
        this.givingService = givingService;
    }
    
}
