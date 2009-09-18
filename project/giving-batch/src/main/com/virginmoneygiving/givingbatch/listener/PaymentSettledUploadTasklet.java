package com.virginmoneygiving.givingbatch.listener;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.thoughtworks.xstream.XStream;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.helper.EmailHelper;

/**
 * This class is used to make a send mail after a payment has been settled.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Yogesh Salunkhe
 */
public class PaymentSettledUploadTasklet extends StepExecutionListenerSupport
    implements Tasklet {

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

    /** Create instance of logger. */
    private static final Logger LOGGER =
        Logger.getLogger(PaymentSettledUploadTasklet.class);

    /** Create instance of batch delegate to inject inside xml. */
    private BatchDelegate batchDelegate;

    /** Create instance of stepExecution. */
    private StepExecution stepExecution;

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
     * @param stepExecution of type StepExecution
     */
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    /**
     * This is used for to execute the step and call the delegate after that it
     * returns status.
     * 
     * @param stepContribution of type StepContribution
     * @param chunkContext the chunk context
     * 
     * @return Status of the Step
     * 
     * @throws exception if step is not executed properly
     * @throws Exception the exception
     */
    public RepeatStatus execute(StepContribution stepContribution,
            ChunkContext chunkContext) throws Exception {

        sendEmail(EmailHelper.paymentSettledMessage("",""));

        return RepeatStatus.FINISHED;
    }

    /**
     * Send email.
     * 
     * @param message -
     * Message containing mail details.
     */
    public void sendEmail(
        com.virginmoneygiving.templateemail.service.messages.Message message) {
        message.setSender("esakki.yadav@arrkgroup.com");
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

}
