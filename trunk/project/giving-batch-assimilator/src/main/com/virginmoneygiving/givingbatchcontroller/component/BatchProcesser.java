package com.virginmoneygiving.givingbatchcontroller.component;

import org.springframework.integration.core.Message;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Headers;
import org.springframework.integration.annotation.MessageEndpoint;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.HashMap;

import com.virginmoneygiving.givingbatchcontroller.common.BatchConstant;
import com.virginmoneygiving.givingbatchcontroller.common.JobChainProcessorInterface;

import javax.annotation.Resource;

/**
 * Message Endpoint to start a batch job.
 * It receives the job name, and any associated properties via a SI message, and
 * invokes the JobProcesser to start the batch job in Spring Batch.
 * User: choprah
 * Date: 30-Apr-2009
 * Time: 11:23:05
 */
@MessageEndpoint
public class BatchProcesser {
    
    /** Instance for logger. */
    private static Logger logger = Logger.getLogger(BatchProcesser.class);
    
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
    @ServiceActivator(inputChannel = "batch")
    public void processMessage(Message request, @Headers Map<String, Object> headerMap) {
        logger.debug("Now processing batch message");

        String jobName = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (request.getHeaders().containsKey(BatchConstant.JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE)) {
            jobName = (String) request.getHeaders().get(BatchConstant.JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE);
            logger.debug("+++++++ Batch processer (2) found job: " + jobName);
            logger.debug("Batch processer CorrelationID: " + request.getHeaders().getCorrelationId());
            if (headerMap != null) {
                Object corrId = headerMap.get(BatchConstant.SI_CORRELATION_ID_PARAM_NAME);
                if (corrId != null) {
                    parameters.put(BatchConstant.SI_CORRELATION_ID_PARAM_NAME, corrId.toString());
                }
                //Object corrId2 = request.getHeaders().getCorrelationId();
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

                String batchRunId = (String)headerMap.get(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID);
                if (batchRunId != null) {
                    parameters.put(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID, batchRunId);
                }

                logger.debug("Batch CorrId/BRunID/SeqSize/SeqNum: " + corrId + "/" + batchRunId + "/" + seqSize
                        + "/" + seqNumber);
            }
            startBatchJob(jobName, parameters);
        }
        else {
            logger.debug("Batch processer - No job received");
        }

    }

    /**
     * Starts the specified job.
     * 
     * @param jobName Job to start
     * @param parameters Job parameters
     */
    private void startBatchJob(String jobName, Map<String, Object> parameters) {
        logger.debug("BatchProcesser.startBatchJob Starting job: " + jobName);
        this.jobChainProcessor.processCurrentJob(jobName, parameters);
        logger.debug("BatchProcesser.startBatchJob job: " + jobName + " started");
    }
}
