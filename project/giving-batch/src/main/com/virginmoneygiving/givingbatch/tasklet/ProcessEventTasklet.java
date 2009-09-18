package com.virginmoneygiving.givingbatch.tasklet;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import com.virginmoney.glis.messages.EventCategoryType;
import com.virginmoney.glis.messages.EventProcessingTypes;
import com.virginmoney.glis.messages.MessageHeader;
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.EventProcessing;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.CreateBatchRequest;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.ServiceBatch;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * Send a processEvent() message to GLIS to prompt it to send a jms message containing data from Oracle Financials
 * 
 * I've tried to make the job as parameterized as possible so it can be used for all processEvent jobs.
 * 
 * @author Ian Priest
 */
public class ProcessEventTasklet extends StepExecutionListenerSupport
        implements Tasklet {

    /** create instance of logger. */
    private static final Logger LOGGER =
            Logger.getLogger(ProcessEventTasklet.class);

    /** create instance of batch delegate. */
    private BatchDelegate batchDelegate;
    
    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl locatorImpl;

    /**
     * Sets the locator impl.
     * 
     * @param locatorImpl the locatorImpl to set
     */
	public void setLocatorImpl(
			GivingBatchExtManagementServiceLocatorImpl locatorImpl) {
		this.locatorImpl = locatorImpl;
	}

    /** Processing task type. */
    private String processingType;
    
    /** Processing task category. */
    private String categoryType;
    
    /** The sequence transaction type. */
    private String sequenceTransactionType;
    
    /** The sequence reference type. */
    private String sequenceReferenceType;
    
    /** Payment Service. */
    private PaymentService paymentService;
    
    /** Giving Batch helper. */
    private GivingBatchHelper generateSequenceHelper;

    /** StepExecution object to fetch execution properties. */
    private StepExecution stepExecution;

    
    /**
     * Sets the sequence transaction type.
     * 
     * @param sequenceTransactionType the new sequence transaction type
     */
    public void setSequenceTransactionType(String sequenceTransactionType) {
		this.sequenceTransactionType = sequenceTransactionType;
	}

	/**
	 * Sets the sequence reference type.
	 * 
	 * @param sequenceReferenceType the new sequence reference type
	 */
	public void setSequenceReferenceType(String sequenceReferenceType) {
		this.sequenceReferenceType = sequenceReferenceType;
	}

	/**
	 * Sets the generate sequence helper.
	 * 
	 * @param generateSequenceHelper the new generate sequence helper
	 */
	public void setGenerateSequenceHelper(GivingBatchHelper generateSequenceHelper) {
		this.generateSequenceHelper = generateSequenceHelper;
	}

	/**
	 * Sets the payment service.
	 * 
	 * @param paymentService the new payment service
	 */
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	/**
	 * Sets the processing type.
	 * 
	 * @param processingType the new processing type
	 */
	public void setProcessingType(String processingType) {
		this.processingType = processingType;
	}

	/**
	 * Sets the category type.
	 * 
	 * @param categoryType the new category type
	 */
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
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
     * This is used to execute the step, call the delegate and return status.
     * 
     * @param stepContribution of type StepContribution
     * @param chunkContext the chunk context
     * 
     * @return Status of the Step
     * 
     * @throws Exception if step is not executed properly
     */
    @SuppressWarnings("unchecked")
	public RepeatStatus execute(StepContribution stepContribution,
	        ChunkContext chunkContext) throws Exception {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside CreateRegistrationFeesUploadTasklet execute method - Start");
        }
        
         CreateBatchRequest createBatchRequest = new CreateBatchRequest();
         String batchNumber = Util.getGeneratedBatchNumber(MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_COLLECTED.getCode());
         ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
         //ServiceBatch serviceBatch = new ServiceBatch();
         //serviceBatch.setBatchNumber(batchNumber);
         //serviceBatch.setBatchStatus(Constant.BATCH_STATUS_WAITING);
         //serviceBatch.setExecutionDateTime(Util.buildXMLGregorianDate());
         serviceBatch.setCurrentJobName("processEventCollectedRegistrationFeesJob");
         serviceBatch.setNextBatchName("PaymentSettled");
         createBatchRequest.setServiceBatch(serviceBatch);
         
         try{
         	locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
         }catch(Exception ex)
         {
         	LOGGER.error("Error occured while calling createBatch Service "+ex);
         	//TODO: Use alert Service
         }
    	
    	EventProcessing eventProcessing = new EventProcessing();
    	eventProcessing.setBatchNumber(batchNumber);
    	eventProcessing.setEventCategory(EventCategoryType.fromValue(categoryType));
    	eventProcessing.setEventType(EventProcessingTypes.fromValue(processingType));
    	 MessageHeader header = new MessageHeader();
         header.setBrandReference("Virgin");
         header.setSourceSystemId("Virgin Money Giving");
         //header.setIPAddress("127.0.0.1");
         header.setSessionID("SESS1");
         //header.setSourceSubSystemId(value)
         header.setSystemTransactionID("PROCESS");
         header.setUserName("Giving Batch");
         eventProcessing.setHeader(header); 	
    	this.batchDelegate.processEvent(eventProcessing);
    	 if(LOGGER.isTraceEnabled()) {
    	     LOGGER.trace("Inside CreateRegistrationFeesUploadTasklet execute method - End");
    	 }
        return RepeatStatus.FINISHED;
    }

    /**
     * This method called before step starts and assigns the step execution
     * values.
     * 
     * @param stepExecution of type StepExecution
     */
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.debug("inside before step  execution context:" + stepExecution);
        this.stepExecution = stepExecution;
    }
}

