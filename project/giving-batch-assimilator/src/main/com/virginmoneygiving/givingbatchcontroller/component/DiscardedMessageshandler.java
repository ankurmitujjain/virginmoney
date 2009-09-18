package com.virginmoneygiving.givingbatchcontroller.component;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Headers;
import org.springframework.integration.core.Message;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.HashMap;

import com.virginmoneygiving.givingbatchcontroller.common.BatchConstant;
import com.virginmoneygiving.givingbatchcontroller.common.JobChainProcessorInterface;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: choprah
 * Date: 06-May-2009
 * Time: 15:27:00
 * To change this template use File | Settings | File Templates.
 */
@MessageEndpoint
public class DiscardedMessageshandler {
    
    /** Instance for logger. */
    private static Logger logger = Logger.getLogger(DiscardedMessageshandler.class);

    /** Job processer class. */
    private JobChainProcessorInterface jobChainProcessor;

    /**
     * Spring injector for the jobChainProcessor property.
     * 
     * @param jobChainProcessor Object to set
     */
    @Resource
    public void setJobChainProcessor(JobChainProcessorInterface jobChainProcessor) {
        this.jobChainProcessor = jobChainProcessor;
    }

    /**
     * This is the Service Activator method that processes the incoming message.
     * 
     * @param request SI message
     * @param headerMap Map of header parameters
     */
    @ServiceActivator(inputChannel = "discardChannel")
    public void processMessage(Message request, @Headers Map<String, Object> headerMap) {
        logger.debug("**** Inside the Discard processer **** ");
        Map<String, Object> parameters = new HashMap<String, Object>();

        String jobName = (String) request.getHeaders().get(BatchConstant.JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE);
        logger.debug("**** Discard processer job: " + jobName + " **** ");
        if (headerMap != null) {
            Object corrId = headerMap.get(BatchConstant.SI_CORRELATION_ID_PARAM_NAME);
            if (corrId != null) {
                parameters.put(BatchConstant.SI_CORRELATION_ID_PARAM_NAME, corrId.toString());
            }
            Integer seqSize = (Integer)headerMap.get(BatchConstant.SI_SEQUENCE_SIZE_PARAM_NAME);
            if (seqSize != null) {
                parameters.put(BatchConstant.SI_SEQUENCE_SIZE_PARAM_NAME, seqSize.longValue());
            }
            Integer seqNumber = (Integer)headerMap.get(BatchConstant.SI_SEQUENCE_NUMBER_PARAM_NAME);
            if (seqNumber != null) {
                parameters.put(BatchConstant.SI_SEQUENCE_NUMBER_PARAM_NAME, seqNumber.longValue());
            }

            String chainInd = (String)headerMap.get(BatchConstant.BATCH_PARAMETER_EXECUTE_CHAIN);
            if (chainInd != null) {
                parameters.put(BatchConstant.BATCH_PARAMETER_EXECUTE_CHAIN, chainInd);
            }
        }

        jobChainProcessor.processAggregatorTimeout(jobName, parameters);
        logger.debug("**** Discard processer job: " + jobName + " sent to jobProcesser **** ");
    }

}
