package com.virginmoneygiving.givingbatch.processor;

import java.util.Set;
import java.sql.Timestamp;

import javax.annotation.Resource;

import net.sf.dozer.util.mapping.DozerBeanMapper;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameter;

import com.virginmoneygiving.givingbatch.exception.GlisServiceException;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoney.glis.messages.RegistrationFeePaymentCollected;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.PaymentStatus;
import com.virginmoneygiving.payment.domain.VatAmount;
import com.virginmoneygiving.payment.domain.VatStatus;
import com.virginmoneygiving.payment.service.PaymentService;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;

/**
 * This class is an Item Processor for the CollectedRegistrationFees batch.
 * 
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * 
 * 
 * @author ian priest
 */
public class CollectedRegistrationFeesProcessor implements StepExecutionListener,
        ItemProcessor<RegistrationFeePaymentCollected, RegistrationFeePaymentCollected> {

    /** instance of logger. * */
    private static Logger LOGGER =
            Logger.getLogger(CollectedRegistrationFeesProcessor.class);

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;
    
    /** payment service. * */
    private PaymentService paymentService;

    /** Mapper. */
    private DozerBeanMapper dozer;

    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl locatorImpl;

    /** Batch number extracted from Job parameters. */
    private String batchNumber;


    /** Indicates whether any of the payments has fallen over. */
    private String batchErrorFlag = null;    
    
    /**
     * TODO Alert service.
     * 
     * @param dozer the dozer
     */
    
    @Resource
	public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;

    }

	/**
	 * Sets the payment service.
	 * 
	 * @param paymentService the paymentService to set
	 */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Spring injector for the Giving EXT management service.
     * 
     * @param locatorImpl Locator to set
     */
    public void setLocatorImpl(GivingBatchExtManagementServiceLocatorImpl locatorImpl) {
        this.locatorImpl = locatorImpl;
    }

    /**
     * {@inheritDoc}
     */
    public RegistrationFeePaymentCollected process(RegistrationFeePaymentCollected paymentCollected) throws GlisServiceException {
       
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("CollectedRegistrationFeesProcessor.process - Start: Charity/Invoice/Batch= "
                    + paymentCollected.getCharityReference() + "/" + paymentCollected.getInvoiceNumber() + "/"
                    + paymentCollected.getBatchNumber());
        }
        /*
         * Try to find the payment record for the collected payment.
         * Will throw an exception and force a rollback if it fails
         */
        Payment payment = getPayment(paymentCollected);
        if (payment == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No payment details found for Ref: " + paymentCollected.getInvoiceNumber()
                                + ", Charity: " + paymentCollected.getCharityReference());
            }
            return paymentCollected;
        }

        /*
         * Update it's status
         */
        Timestamp settledDate = null;
        if (paymentCollected.getSettlementDate() > 0) {
            settledDate = new Timestamp(paymentCollected.getSettlementDate());
        }
        updateBatchStatus(payment, paymentCollected.getBatchNumber(), settledDate);

        createBatchEntity(payment.getId(), paymentCollected.getInvoiceNumber(),Constant.BATCH_STATUS_SUCCESSFUL, null);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("CollectedRegistrationFeesProcessor.process - End: Charity/Invoice/Batch= "
                    + paymentCollected.getCharityReference() + "/" + paymentCollected.getInvoiceNumber() + "/"
                    + paymentCollected.getBatchNumber());
        }
        return paymentCollected;

    }

    /**
     * Get the payment from the database if it can be mapped.
     * 
     * Note the returned payment is mapped from the discovered on, so is completely
     * disconnected from hibernate.
     * 
     * @param paymentCollected the payment collected
     * 
     * @return payment that matches the paymentCollected parameter
     * 
     * @throws GlisServiceException if payment can;t be found
     */
    private Payment getPayment(RegistrationFeePaymentCollected paymentCollected) throws GlisServiceException {
		
    	com.virginmoneygiving.payment.domain.RegistrationFeePaymentCollected domainPaymentCollected =
    		new com.virginmoneygiving.payment.domain.RegistrationFeePaymentCollected();
    	
        try {
            dozer.map(paymentCollected, domainPaymentCollected);
        }
        catch (Exception e) {
            LOGGER.error("Encountered error dozering it.", e);
            throw new GlisServiceException(LOGGER, "Encountered error dozering registration fee payment collected", e);
        }

        // Will throw an exception if the payment can't be matched
		Payment payment = paymentService.fetchCollectedRegistrationPayment(domainPaymentCollected);
		
		if ( payment == null ) {
            String eMsg = "Payment not found (Charity/Amount): " + domainPaymentCollected.getCharityReference() + "/"
                    + domainPaymentCollected.getFeeAmount();
            createBatchEntity((long)0, domainPaymentCollected.getInvoiceNumber(), Constant.BATCH_STATUS_ERROR, eMsg);
            updateBatchStatus();
            batchErrorFlag = "YES";
            return null;
            
            /*
            throw new GlisServiceException(LOGGER, "Can't identify payment for record: "
					+ "charityReference=" + domainPaymentCollected.getCharityReference()
					+ ", feeAmount=" + domainPaymentCollected.getFeeAmount()
					+ ", invoiceNumber=" + domainPaymentCollected.getInvoiceNumber()
					+ ", vatAmount=" + domainPaymentCollected.getVatAmount() );
		    */
		}
		
		// Disconnect payment from hibernate to avoid annoying exceptions of many types
		Payment domainPayment = new Payment();
        dozer.map(payment, domainPayment);
		return domainPayment;
	}

	/**
     * Update the batch status of the payment and vat records to show they've been passed to GLIS
     * @param payment
     *            of type Payment.
     */
    public void updateBatchStatus(Payment payment, String batchNumber, Timestamp settledDate) {
    	
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("CollectedRegistrationFeesProcessor.updateBatchStatus - Start: " + payment.getId());
        }      
        PaymentStatus status = new PaymentStatus();
        status.setCode(MasterDataCodeConstants.CRF_PAYMENT_COLLECTED);
        payment.setPaymentStatus(status);
        payment.setBatchStatus(status.getCode());

        payment.setSettlementBatchId(batchNumber);
        payment.setSettledDateTime(settledDate);
        
        Set<VatAmount> vatAmounts = payment.getVatAmount();
        
        // How can this test ever fail? What if there's more than one vat 
        // record for a payment? Can that ever happen?
        if ( vatAmounts != null && vatAmounts.size() > 0 ) {
        	VatAmount vatAmount = vatAmounts.iterator().next();

        	VatStatus vatStatus = new VatStatus();
        	vatStatus.setCode(MasterDataCodeConstants.VAT.VAT_PAID.getCode());
        	vatAmount.setVatStatus(vatStatus);
        	vatAmount.setBatchStatus(vatStatus.getCode());
        }
        
        paymentService.savePaymentDetails(payment);
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("CollectedRegistrationFeesProcessor.updateBatchStatus - End: " + payment.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
        if (stepExecution != null) {
            JobParameter param = stepExecution.getJobParameters().getParameters().get(Constant.BATCH_NUMBER);
            if (param != null) {
                this.batchNumber = (String)param.getValue();
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("CollectedRegistrationFeesProcessor:batch : " + batchNumber );
                }
            }
            else {
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("CollectedRegistrationFeesProcessor:batch : param is NULL"  );
                }
            }
         }
    }

    /**
     * {@inheritDoc}
     */
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (batchErrorFlag != null && batchErrorFlag.equalsIgnoreCase("YES")) {
            updateBatchStatus();
            return ExitStatus.FAILED;
        }
        return ExitStatus.COMPLETED;
    }

    /**
     * Creates a batch entity.
     * 
     * @param id ID of the entity.
     * @param entityReference Reference to use.
     * @param status Status of entity
     * @param eMsg Any error message
     */
    private void createBatchEntity(Long id, String entityReference, String status, String eMsg ) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("CollectedRegistrationFeesProcessor:Creating entity for batch/Reference: " + batchNumber + "/" + entityReference);
        }
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(status);
        batchEntity.setEntityId(id.toString());
        batchEntity.setEntityReference(entityReference);  //HunarC: Added this
        batchEntity.setBaseReference(entityReference);    //HunarC: Added this
        batchEntity.setNextBatchStatus("");
        batchEntity.setPreviousStatus(null);
        batchEntity.setEntityTypeCode(Constant.ENTITY_PAYMENT);
        batchEntity.setErrorMessage(eMsg);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch Payment Entity  : CollectedRegistrationFeesProcessor."
                                    + e.getMessage(), e);
        }
        catch (Exception ex) {
            LOGGER.error("Error occured while calling creating batch Payment Entity " + ex);
        }
    }

    /**
     * Updates the batch status to failed if an error occurs.
     */
    private void updateBatchStatus() {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("CollectedRegistrationFeesProcessor:inside updateBatchStatus method - Error in batch.");
        }
            try {
                UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setEndDateTime(Util.buildXMLGregorianDate());
                request.setStatus(Constant.BATCH_STATUS_FAIL);
                request.setErrorMessage("Please see entity details");
                locatorImpl.getGivingBatchExtManagementPort().updateBatchStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                LOGGER.error("Unexpected error setting batch status to error.", e);
            }
    }

}
