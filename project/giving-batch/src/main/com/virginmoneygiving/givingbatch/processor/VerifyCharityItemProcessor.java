package com.virginmoneygiving.givingbatch.processor;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.domain.BankAccount;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.CharityOfflineRegistration;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.FailedToGoLiveCharity;
import com.virginmoneygiving.givingbatch.domain.PutCharityLiveJobSummary;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.PaymentManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.indexerservice.client.event.IndexerEventService;
import com.virginmoneygiving.givingmanagement.service.messages.PutCharityLiveRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.CharityRegistrationFeePaidRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.MessageHeader;
import com.virginmoneygiving.paymentmanagement.service.messages.PaymentManagementServiceFaultException;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;

import javax.annotation.Resource;

/**
 * VerifyCharityItemProcessor verifies whether all the details have been
 * collected for the given charity and have been verified successfully.
 * <p>
 * If yes, then the charity is put live, else it remains in same state.
 * <p>
 * 
 * @author Puneet Swarup
 */
public class VerifyCharityItemProcessor implements
        ItemProcessor<Charity, PutCharityLiveJobSummary>, StepExecutionListener {

    /** Logger instance. */
    private static final Logger LOGGER =
            Logger.getLogger(VerifyCharityItemProcessor.class);

    /** Step execution instance. */
    private StepExecution stepExecution;

    /** Giving service - web service locator. */
    private GivingManagementServiceLocatorImpl givingService;

    /** Payment service - web service locator. */
    private PaymentManagementServiceLocatorImpl paymentService;

    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl givingBatchExtServiceLocator;

    /** Object to hold processing summary. */
    private PutCharityLiveJobSummary summary;

    /** Index charity when it goes live. */
    private IndexerEventService indexerEventService;

    /**
     * Sets the giving batch ext service locator.
     * 
     * @param givingBatchExtServiceLocator the new giving batch ext service locator
     */
    @Resource
    public void setGivingBatchExtServiceLocator(GivingBatchExtManagementServiceLocatorImpl
            givingBatchExtServiceLocator) {
        this.givingBatchExtServiceLocator = givingBatchExtServiceLocator;
    }

    /** {@inheritDoc} */
    public PutCharityLiveJobSummary process(final Charity charity)
            throws GivingBatchException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::process() - START");
        }
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Charity verification process started for Charity "
                    + charity.getId());
        }
        summary
                .setNumberOfRecordsReviewed(summary
                        .getNumberOfRecordsReviewed() + 1);
        try {
            LOGGER.trace("Verifying charity registration.");
            verifyCharityRegistration(charity);
        }
        catch (Exception exception) {
            LOGGER.error("Error Occured Inside VerifyCharityItemProcessor::process() method ");
            LOGGER.error("INPUT : Charity Id "+charity.getId());
            LOGGER.error("ERROR DETAILS ", exception);
            updateSummaryForError(charity, exception.getMessage());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::process() - END");
        }
        return summary;
    }

    /**
     * Verify Charity registration status.
     * 
     * @param charity the charity.
     */
    private void verifyCharityRegistration(final Charity charity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityRegistration() - START");
        }
        LOGGER.debug("verifyCharityRegistration: Checking charity status for: " + charity.getId());

        if (MasterDataCodeConstants.CHARITY_STATUS_CODE_IRC.equals(charity
                .getCharityStatus().getCode())) {
            LOGGER.debug("Charity initial registration completed for charity " + charity.getId());
            verifyCharityAndFormsStatus(charity);
        }
        else {
            LOGGER.debug("Charity initial registration not completed for charity " + charity.getId());
            updateSummary(false);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityRegistration() - END");
        }
    }

    /**
     * Verify charity and forms status.
     * 
     * @param charity the charity.
     */
    private void verifyCharityAndFormsStatus(final Charity charity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityRegistration() - START");
        }
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("verifyCharityRegistration: Verifying charity registration forms status: " + charity.getId());
        }
        if (CollectionUtils.isEmpty(charity.getCharityOfflineRegistration())) {
            LOGGER.error("Error Occured Inside VerifyCharityItemProcessor::verifyCharityAndFormsStatus() method ");
            LOGGER.error("Charity verification (1) not completed: " + charity.getId());
            updateSummary(false);
        }
        else {
            for (CharityOfflineRegistration offlineRegistration : charity
                    .getCharityOfflineRegistration()) {
                if (MasterDataCodeConstants.OfflineRegStatus.ORS_COMPLETED
                        .getCode().equals(
                                offlineRegistration.getCharityVerifyStatus()
                                        .getCode())) {
                    if (MasterDataCodeConstants.OfflineRegStatus.ORS_HMRC_RECEIVED
                            .getCode().equals(
                                    offlineRegistration.getGiftFormStatus()
                                            .getCode())
                            && (MasterDataCodeConstants.OfflineRegStatus.ORS_RECEIVED
                                    .getCode().equals(offlineRegistration.getHmrcFormStatus().getCode()) ||
                                MasterDataCodeConstants.OfflineRegStatus.ORS_NOT_APPLICABLE
                                    .getCode().equals(offlineRegistration.getHmrcFormStatus().getCode()))
                    ) {
                        LOGGER.debug("Charity forms completed for charity: " + charity.getId());
                        verifyCharityBankDetails(charity);
                    }
                    else {
                        LOGGER.debug("Charity forms not completed for charity: " + charity.getId());
                        updateSummary(false);
                    }
                }
                else {
                    LOGGER.debug("Charity verification (2) not completed: " + charity.getId());
                    updateSummary(false);
                }
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityRegistration() - END");
        }
    }

    /**
     * Verify charity donation bank account.
     * 
     * @param charity the charity
     */
    private void verifyCharityBankDetails(final Charity charity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityBankDetails() - START");
        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Verifying charity bank details for charity: " + charity.getId());
        }

        boolean activeDonationAccountFound = false;
        for (BankAccount account : charity.getBankAccounts()) {
            activeDonationAccountFound =
                    MasterDataCodeConstants.BankAccountStatus.BANK_ACCOUNT_STATUS_ACTIVE
                            .getCode().equals(
                                    account.getAccountStatus().getCode())
                            && MasterDataCodeConstants.YES.equalsIgnoreCase(account.getDefaultAccountIndicator())
                            && (MasterDataCodeConstants.BankAccountType.BANK_ACCOUNT_TYPE_BOTH
                                    .getCode().equals(
                                            account.getBankAccountType()
                                                    .getCode())
                                || MasterDataCodeConstants.BankAccountType.BANK_ACCOUNT_TYPE_DONATION
                                    .getCode().equals(
                                            account.getBankAccountType()
                                                    .getCode()));
            if (activeDonationAccountFound) {
                break;
            }
        }
        if (activeDonationAccountFound) {
            LOGGER.debug("Charity donation account registered for charity: " + charity.getId());
            verifyCharityRegistrationFeePaid(charity);
        }
        else {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Charity donation account not registered for charity: " + charity.getId());
            }
            updateSummary(false);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityBankDetails() - END");
        }
    }

    /**
     * Verify charity registration fee paid.
     * 
     * @param charity the charity
     */
    private void verifyCharityRegistrationFeePaid(final Charity charity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityRegistrationFeePaid() - START");
        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Verifying charity registration fee paid for charity: " + charity.getId());
        }

        try {
            final CharityRegistrationFeePaidRequest request =
                    new CharityRegistrationFeePaidRequest();
            request.setCharityId(charity.getId());
            request
            .setHeader(Util.getPaymentHeader("PutCharityLive"));

            final boolean registrationFeePaid =
                    paymentService.getPaymentManagementPort()
                            .isCharityRegistrationFeePaid(request);

            if (registrationFeePaid) {
                LOGGER.debug("Charity registration fee paid for charity: " + charity.getId());
                verifyCharityDetails(charity);
            }
            else {
                LOGGER.debug("Charity registration fee not paid for charity: " + charity.getId());
                updateSummary(false);
            }
        }
        catch (PaymentManagementServiceFaultException ex) {
            LOGGER.error("Error Occured Inside VerifyCharityItemProcessor::verifyCharityRegistrationFeePaid() method ", ex);
            LOGGER.error("INPUT : Charity Id"+charity.getId());
            updateSummaryForError(
                    charity,
                    "Failure while calling payment service to check charity registration fee payment details. "
                            + "Charity/REASON: " + charity.getId() + "/" + ex.getMessage());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityRegistrationFeePaid() - END");
        }
    }

    /**
     * Verify charity registration details such as category, description, other
     * details.
     * 
     * @param charity the charity.
     */
    private void verifyCharityDetails(final Charity charity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityDetails() - START");
        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Verifying charity registration details for charity: " + charity.getId());
        }

        if (StringUtils.isBlank(charity.getDescription())) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Charity description is not available for charity: " + charity.getId());
            }
            updateSummary(false);

        }
        else if (StringUtils.isBlank(charity.getCommonName())
                || StringUtils.isBlank(charity.getAbbreviations())
                || charity.getAccountingPeriodEndDate() == null) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Chanrity details not available for charity: " + charity.getId());
            }
            updateSummary(false);

        }
        else if (CollectionUtils.isEmpty(charity.getCharityCategories())) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Chanrity category not available for charity: " + charity.getId());
            }
            updateSummary(false);

        }
        else {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Charity registration details completed for charity: " + charity.getId());
            }
            verifyCharityActivationPostponement(charity);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityDetails() - END");
        }
    }

    /**
     * Verify charity activation has been postponed.
     * 
     * @param charity the charity
     */
    private void verifyCharityActivationPostponement(final Charity charity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityActivationPostponement() - START");
        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Verifying charity activation postponed for charity: " + charity.getId());
        }

        if (MasterDataCodeConstants.YES.equals(charity.getActivationPostponed())) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Charity going live has been postponed for charity: " + charity.getId());
            }
            updateSummary(false);
        }
        else {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Charity activation not postponed for charity: " + charity.getId());
            }
            putCharityLive(charity);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::verifyCharityActivationPostponement() - END");
        }
    }

    /**
     * Make the status of charity live.
     * 
     * @param charity the charity
     */
    private void putCharityLive(final Charity charity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::putCharityLive() - START");
        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Charity verification successful, putting it to LIVE for charity: " + charity.getId());
        }

        try {
            final PutCharityLiveRequest request = new PutCharityLiveRequest();
            com.virginmoneygiving.givingmanagement.service.messages.MessageHeader messageHeader =
                new com.virginmoneygiving.givingmanagement.service.messages.MessageHeader();
            
            request
                    .setHeader(Util.getGivingHeader("PutCharityLive"));
            request.setCharityId(charity.getId());
            givingService.getGivingManagementPort().putCharityLive(request);
            updateSummary(true);

            // Index the charity
            indexerEventService.publishCharitySavedEvent(charity);

            createBatchEntity(charity.getId(), "Charity put live");

        }
        catch (Exception ex) {
            LOGGER.error("Error Occured Inside VerifyCharityItemProcessor::putCharityLive() method ");
            LOGGER.error("INPUT : Charity Id"+charity.getId());
            LOGGER.error(ex.getMessage(), ex);
            updateSummaryForError(charity,
                    "Failure while calling giving service to make charity live. Charity/REASON: "
                            + charity.getId() + "/" + ex.getMessage());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::putCharityLive() - END");
        }
    }

    /**
     * Updates the summary info with given information.
     * 
     * @param success flag to indicate whether charity was successfully made LIVE.
     */
    private void updateSummary(final boolean success) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::updateSummary() - START");
        }
        if (success) {
            summary.setNumberOfCharitiesPutLive(summary
                    .getNumberOfCharitiesPutLive() + 1);
        }
        else {
            summary.setNumberOfSkippedRecords(summary
                    .getNumberOfSkippedRecords() + 1);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::updateSummary() - END");
        }
    }

    /**
     * Updates the summary info with given information.
     * 
     * @param charity charity info
     * @param message the message
     */
    private void updateSummaryForError(final Charity charity,
            final String message) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::updateSummaryForError() - START");
            LOGGER.trace("VerifyCharityItemProcessor::updateSummaryForError() - END");
        }
        summary.getFailedRecords().add(
                new FailedToGoLiveCharity(charity.getId(), charity.getName(),
                        message));
    }

    /** {@inheritDoc} */
    public ExitStatus afterStep(final StepExecution stepexecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::afterStep() - START");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(summary);
        }
        this.stepExecution = stepexecution;
        this.stepExecution.getJobExecution().getExecutionContext().put(
                Constant.PUT_CHARITY_LIVE_SUMMARY, summary);
        LOGGER.debug("Execution of step 1 for PutCharityLiveJob completed.");
        // Update batch status
        this.stepExecution.getJobExecution().getExecutionContext().put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "NO");
        String batchNumber = summary.getBatchNumber();
        String errMsg  = "Batch processed. Reviewed: " + summary.getNumberOfRecordsReviewed() + ", Put live: "
                + summary.getNumberOfCharitiesPutLive();
        String bStatus = Constant.BATCH_STATUS_SUCCESSFUL;
        if (summary == null || summary.getNumberOfRecordsReviewed() < 1) {
            errMsg = "No records found.";
        }

        LOGGER.debug("inside afterStep method - updating batch status.");
        try {
            UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
            request.setBatchNumber(batchNumber);
            request.setEndDateTime(Util.buildXMLGregorianDate());
            request.setStatus(bStatus);
            request.setErrorMessage(errMsg);
            givingBatchExtServiceLocator.getGivingBatchExtManagementPort().updateBatchStatus(request);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error Occured Inside VerifyCharityItemProcessor::afterStep() method ");
            LOGGER.error("Unexpected error setting batch status to complete in afterstep", e);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::afterStep() - END");
        }
        
        return ExitStatus.COMPLETED;
    }

    /** {@inheritDoc} */
    public void beforeStep(final StepExecution stepexecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::beforeStep() - START");
        }
        this.stepExecution = stepexecution;
        final ExecutionContext context =
                this.stepExecution.getJobExecution().getExecutionContext();
        if (context.get(Constant.PUT_CHARITY_LIVE_SUMMARY) == null) {
            LOGGER.debug("Summary data initialized.");
            summary = new PutCharityLiveJobSummary();
            summary
                    .setBatchNumber(Util
                            .getGeneratedBatchNumber("LIVE_CHARITY"));
            context.put(Constant.PUT_CHARITY_LIVE_SUMMARY, summary);

            CreateBatchRequest createBatchRequest = new CreateBatchRequest();
            String batchNumber = summary.getBatchNumber();
            ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
            serviceBatch.setCurrentJobName("putCharityLiveBatchJob");
            serviceBatch.setNextBatchName("INDEPENDANT");
            createBatchRequest.setServiceBatch(serviceBatch);
            try{
                givingBatchExtServiceLocator.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
                context.put(Constant.BATCH_NUMBER, batchNumber);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                LOGGER.error("Error Occured Inside VerifyCharityItemProcessor::beforeStep() method ");
                LOGGER.error("Error occurred while creating batch : VerifyCharityItemProcessor."
                        + e.getMessage(), e);
            }
            catch (Exception ex)
            {
                LOGGER.error("Error Occured Inside VerifyCharityItemProcessor::beforeStep() method ");
                LOGGER.error("VerifyCharityItemProcessor: Error occured while calling creating batch ", ex);
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::beforeStep() - END");
        }
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

    /**
     * Sets the payment service.
     * 
     * @param paymentService the paymentService to set
     */
    public void setPaymentService(
            PaymentManagementServiceLocatorImpl paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Sets the indexer event service.
     * 
     * @param indexerEventService the indexerEventService to set
     */
    public void setIndexerEventService(IndexerEventService indexerEventService) {
        this.indexerEventService = indexerEventService;
    }

    /**
     * Creates the batch entity.
     * 
     * @param charityId the charity id
     * @param msgText the msg text
     */
    private void createBatchEntity(Long charityId, String msgText) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::createBatchEntity() - START");
        }
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
    	String batchNumber = (String)context.get(Constant.BATCH_NUMBER);
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_SUCCESSFUL);
        batchEntity.setEntityId(charityId.toString());
        batchEntity.setNextBatchStatus(null);
        batchEntity.setPreviousStatus(null);
        batchEntity.setEntityTypeCode(Constant.ENTITY_CHARITY);
        batchEntity.setEntityReference(charityId.toString());
        batchEntity.setErrorMessage(msgText);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            givingBatchExtServiceLocator.getGivingBatchExtManagementPort()
                    .createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch entity : VerifyCharityItemProcessor."
                        + e.getMessage(), e);

         }
        catch(Exception ex)
        {
            LOGGER.error("Error occured while calling creating batch entity : VerifyCharityItemProcessor ",ex);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::createBatchEntity() - END");
        }

    }
}
