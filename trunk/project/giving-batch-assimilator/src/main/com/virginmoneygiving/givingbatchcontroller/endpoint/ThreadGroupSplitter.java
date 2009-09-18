package com.virginmoneygiving.givingbatchcontroller.endpoint;

import java.util.*;

import org.apache.log4j.Logger;

import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.GenericMessage;
import com.virginmoneygiving.givingbatchcontroller.common.BatchConstant;
import com.virginmoneygiving.givingbatchcontroller.common.BatchAssimilatorHelperInterface;

import javax.annotation.Resource;

/**
 * This is the splitter class that splits a set into it's component threads, and sends the
 * initial jobs to the batch channel.
 * User: choprah
 * Date: 05-May-2009
 * Time: 10:24:08
 */
@MessageEndpoint
public class ThreadGroupSplitter {

	/** Instance for logger. */
    private static Logger logger = Logger.getLogger(ThreadGroupSplitter.class);
    
    /** Helper class. */
    private BatchAssimilatorHelperInterface batchAssimilatorHelper;

    /**
     * Spring injector for the helper class.
     * 
     * @param batchAssimilatorHelper Helper class to set.
     */
    @Resource
    public void setBatchControllerHelper(BatchAssimilatorHelperInterface batchAssimilatorHelper) {
        logger.debug("ThreadGroupSplitter: Setting Batch helper");
        this.batchAssimilatorHelper = batchAssimilatorHelper;
    }

    /**
     * Split a colon (:) delimited job thread.
     * 
     * @param triggerGroup Job threads
     * @param batchRunId the batch run id
     * 
     * @return List of SI messages
     */
    @Splitter(inputChannel="triggers", outputChannel="batch")
    public List<Message> splitTrigger(@Header(required=true,
                                              value= BatchConstant.JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE)
                                      String triggerGroup,
                                      @Header(required=false,
                                              value= BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID)
                                      String batchRunId) {

        logger.debug("ThreadGroupSplitter: Splitter received trigger: " + triggerGroup);
        logger.debug("ThreadGroupSplitter: Splitter received Batch Run ID: " + batchRunId);

        // prep response object
        List<Message> list = new ArrayList<Message>();

        String[] chains = triggerGroup.split(":");
        String corrId   = UUID.randomUUID().toString();

        logger.debug("ThreadGroupSplitter: Splitting into " + chains.length + " batch messages.");
        for (String chain : chains) {
            Map<String, Object> properties = new HashMap<String, Object>();
            logger.debug("ThreadGroupSplitter: processing thread " + chain);
            String batchJobName = fetchThreadJobName(chain);
            if (batchJobName != null && batchJobName.length() > 0) {
                logger.debug("ThreadGroupSplitter: Creating message for thread/job " + chain + "/" + batchJobName);
                properties.put(BatchConstant.JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE, batchJobName);
                properties.put(BatchConstant.BATCH_PARAMETER_EXECUTE_CHAIN, "YES");
                properties.put(BatchConstant.SI_CORRELATION_ID_PARAM_NAME, corrId);
                properties.put(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID, batchRunId);
                Message msg = new GenericMessage("", properties);
                list.add(msg);
            }

            logger.debug("ThreadGroupSplitter: Message created for: " + chain);
        }

        return list;
    }

    /**
     * Retrieves the initial job for the given thread.
     * 
     * @param threadName Thread to look up
     * 
     * @return Initial batch job.
     */
    private String fetchThreadJobName(String threadName) {
        String result = "";
        if (this.batchAssimilatorHelper != null) {
            result = this.batchAssimilatorHelper.fetchInitialJob(threadName);
        }

        return result;
    }
}
