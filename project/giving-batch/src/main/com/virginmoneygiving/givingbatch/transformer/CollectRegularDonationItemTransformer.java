package com.virginmoneygiving.givingbatch.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.thoughtworks.xstream.XStream;
import com.virginmoneygiving.giving.domain.BankAccount;
import com.virginmoneygiving.giving.domain.Donation;
import com.virginmoneygiving.giving.domain.PaymentCard;
import com.virginmoneygiving.giving.domain.PersonEmailAddress;
import com.virginmoneygiving.giving.domain.RegularDonation;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.CollectRegularDonationRequest;
import com.virginmoneygiving.givingbatch.domain.DonationDistribution;
import com.virginmoneygiving.givingbatch.domain.MakeDonationWithActiveCard;
import com.virginmoneygiving.givingbatch.domain.RegularDonationRecord;
import com.virginmoneygiving.givingbatch.domain.SaveRegularDonationRequest;
import com.virginmoneygiving.givingbatch.domain.ServiceCardTransaction;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.helper.EmailHelper;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;

/**
 * This class processes the donation record and saves the record in regular_donation table.
 * 
 * @author Tarun Adiwal
 */
public class CollectRegularDonationItemTransformer implements
        ItemProcessor<Donation, RegularDonationRecord>, StepExecutionListener {

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(CollectRegularDonationItemTransformer.class);

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** Giving service. */
    private GivingService givingService;

    /** create instance of batch delegate. */
    private BatchDelegate batchDelegate;

    /** Email template. */
    JmsTemplate jmsEmailTemplate;

    /** The Constant REGULAR_DONATION_PROCESSED_IND. */
    private static final String REGULAR_DONATION_PROCESSED_IND = "RegularDonationCollectedInd";

    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl locatorImpl;

    /** Flag to indicate whether batch was successful. * */
    private boolean processSuccessful = true;

    /**
     * Sets the locator impl.
     * 
     * @param locatorImpl the locatorImpl to set
     */
	public void setLocatorImpl(
			GivingBatchExtManagementServiceLocatorImpl locatorImpl) {
		this.locatorImpl = locatorImpl;
	}
	
    /**
     * {@inheritDoc}
     */
    public RegularDonationRecord process(Donation donation) throws GivingBatchException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemTransformer::process() - START");
        }
        CollectRegularDonationRequest request = null;
        RegularDonationRecord regularDonationRecord = null;
        SaveRegularDonationRequest saveRegularDonationRequest = null;
        if (donation != null && donation.getRegularDonation() != null) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("CollectRegularDonationItemTransformer - "
                        + " Starting process for id/Amount/Ref/RegId/payday/Charity/Donor/GA-Ind: "
                        + donation.getId() + "/" + donation.getGrossAmount() + "/" + donation.getDonationReference()
                        + "/" + donation.getRegularDonation().getId() + "/"
                        + donation.getRegularDonation().getPaymentDay() + "/"
                        + donation.getRegularDonation().getCharity().getId() + "/"
                        + donation.getRegularDonation().getDonor().getId() + "/"
                        + donation.getRegularDonation().getGiftAidEligibleIndicator());
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Full record: " + donation.toString());
            }

            saveRegularDonationRequest = new SaveRegularDonationRequest();
            RegularDonation regularDonation = donation.getRegularDonation();
            request = new CollectRegularDonationRequest();
            request.setCharityId(regularDonation.getCharity().getId());
            request.setDonorId(regularDonation.getDonor().getId());
            request.setGrossAmount(regularDonation.getGrossAmount());
            if (Constant.YES.equalsIgnoreCase(regularDonation
                    .getGiftAidEligibleIndicator())) {
                request.setEligibleForGiftAid(true);
                saveRegularDonationRequest.setGiftAidApplicable(true);
                // request.setGiftAidAmount(regularDonation.);
            }
            else {
                request.setEligibleForGiftAid(false);
            }
            request.setPaymentFrequency(regularDonation.getPaymentFrequency());
            request.setPaymentDay(regularDonation.getPaymentDay());

            request.setFirstPaymentDate(regularDonation.getFirstPaymentDate());
            request.setRegularDonationId(regularDonation.getId());

            request.setDonorName(donation.getDonorName());
            request.setDonorMessage(donation.getDonorMessage());
            // request.setDonorEmailAddress(donation.setD);
            request.setDonationReference(donation.getDonationReference());
            if (donation.getFundraiserActivity() != null) {
                request.setFundraiserActivityId(donation
                        .getFundraiserActivity().getId());
            }
            // Populating data for saveRegularDonation web service.
            saveRegularDonationRequest.setDonationId(donation.getId());
            ServiceCardTransaction cardTransaction =
                    new ServiceCardTransaction();
            // Fetching the cardType.
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Fetch payment card for person: " + donation.getPerson().getId());
            }
            PaymentCard card =
                    givingService.fetchPaymentcardDetails(donation.getPerson()
                            .getId());
            cardTransaction.setCardType(card.getCardProvider().getCode());

            cardTransaction.setCurrency(regularDonation.getCurrency());
            cardTransaction.setTransactionAmount(regularDonation
                    .getGrossAmount());
            cardTransaction.setTransactionDatetime(Util.getTimeStampDate());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Payment card details for person (Id/cardtype): "
                        + donation.getPerson().getId() + "/" + card.getCardProvider().getCode());
            }

            DonationDistribution distribution = new DonationDistribution();
            distribution.setCharityId(regularDonation.getCharity().getId());

            // Fetching the bank account details of charity.
            Set<BankAccount> bankAccounts =
                    regularDonation.getCharity().getBankAccounts();
            for (BankAccount bankAccount : bankAccounts) {
                if (Constant.YES.equalsIgnoreCase(bankAccount
                        .getDefaultAccountIndicator())) {
                    distribution.setAccountNumber(bankAccount
                            .getAccountNumber());
                    distribution.setSortCode(bankAccount.getSortCode());
                }
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("bank accountfor person (Id/Sortcode/Account): "
                        + donation.getPerson().getId() + "/" + distribution.getSortCode() + "/"
                        + distribution.getAccountNumber());
            }

            distribution.setSplitValue(100);
            List<DonationDistribution> listDistribution =
                    new ArrayList<DonationDistribution>();
            listDistribution.add(distribution);
            saveRegularDonationRequest.setRegularDonation(listDistribution);
            saveRegularDonationRequest.setCardTransaction(cardTransaction);

            // regularDonationRecord.setNextCollectionDate();
            regularDonationRecord = new RegularDonationRecord();
            regularDonationRecord.setNameOnCard(card.getCardHolderName());
            regularDonationRecord.setPaymentAmount(regularDonation
                    .getGrossAmount());
            regularDonationRecord.setCardType(card.getCardProvider().getCode());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("About to save donation for person/Ref: "
                        + donation.getPerson().getId() + "/" + donation.getDonationReference());
            }
            
        }
        if (saveRegularDonationRequest != null) {
            saveRegularDonation(request,saveRegularDonationRequest);
        }
        //updateObjectInContext(request, saveRegularDonationRequest);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemTransformer::process() - START");
        }
        return regularDonationRecord;

    }

    /**
     * {@inheritDoc}
     */
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemTransformer::afterStep() - START");
        }
        ExecutionContext executionContext = this.stepExecution.getJobExecution().getExecutionContext();
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        String donationProcessInd = (String)executionContext.get(REGULAR_DONATION_PROCESSED_IND);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "NO");
        String batchStatus = Constant.BATCH_STATUS_SUCCESSFUL;
        String batchMessage = "Batch completed.";
        if (donationProcessInd == null || !donationProcessInd.equalsIgnoreCase("YES")) {
            batchMessage = "No records found.";
        }
        if (!processSuccessful) {
            batchMessage = "Errors processing regular donations. Please see log files.";
            batchStatus = Constant.BATCH_STATUS_WARNING;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("inside afterStep - Batch/Message: " + batchNumber + "/" + batchMessage);
        }

        try {
            UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
            request.setBatchNumber(batchNumber);
            request.setEndDateTime(Util.buildXMLGregorianDate());
            request.setStatus(batchStatus);
            request.setErrorMessage(batchMessage);
            locatorImpl.getGivingBatchExtManagementPort().updateBatchStatus(request);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch : CollectRegularDonationItemTransformer:afterStep"
                    + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER, "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
        }
        catch (Exception exception) {
            LOGGER.error("" +
                    "Unexpected error setting batch status to complete when no records found :CollectRegularDonationItemTransformer:afterStep" +exception.getMessage(), exception);
            throw new GivingBatchException(LOGGER, "Batch Number"+batchNumber, exception);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemTransformer::afterStep() - END");
        }
        return ExitStatus.COMPLETED;

    }

    /**
     * {@inheritDoc}
     */
    public void beforeStep(StepExecution stepExecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemTransformer::beforeStep() - START");
        }
        this.stepExecution = stepExecution;
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchGivingControlData(jobName);
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        context.put(REGULAR_DONATION_PROCESSED_IND, "NO");
        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(Constant.COLLECT_REGULAR_DONATION);
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("collectRegularDonationJob");
        serviceBatch.setNextBatchName("INDEPENDANT");
        createBatchRequest.setServiceBatch(serviceBatch);

        try{
        	locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	context.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch : CollectRegularDonationItemTransformer:beforeStep"
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
         }
        catch(Exception exception)
        {
            LOGGER.error("Error occured calling create batch :CollectRegularDonationItemTransformer:beforeStep" +exception.getMessage(), exception);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemTransformer::beforeStep() - END");
        }
    }

    /**
     * Sets the giving service.
     * 
     * @param givingService the givingService to set
     */
    public void setGivingService(GivingService givingService) {
        this.givingService = givingService;
    }

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
     * Save regular donation.
     * 
     * @param collectRegularDonationRequest the collect regular donation request
     * @param saveRegularDonationRequest the save regular donation request
     */
    public void saveRegularDonation(
            CollectRegularDonationRequest collectRegularDonationRequest,
            SaveRegularDonationRequest saveRegularDonationRequest) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemTransformer::saveRegularDonation() - START");
        }
        String entityMsg = "Regular Donation: Charity (" + collectRegularDonationRequest.getCharityId() + ")"
                + ", Donor (" + collectRegularDonationRequest.getDonorId() + "), Donation Ref ("
                + collectRegularDonationRequest.getDonationReference() + "), Amount ("
                + collectRegularDonationRequest.getGrossAmount() + ")";

        String processStep = "";

        boolean serviceResponse = false;
        String status = Constant.BATCH_STATUS_FAIL;
        try {
            MakeDonationWithActiveCard makeDonationRequest =
                    new MakeDonationWithActiveCard();
            makeDonationRequest.setHeader(Util.getGivingHeader("collectRegularDonationJob"));
            makeDonationRequest
                    .setDonationDetails(collectRegularDonationRequest);

            String donationRef = makeDonationRequest.getDonationDetails().getDonationReference();
            processStep = "Process card payment";
            LOGGER.debug("About to call batchDelegate.makeRegularDonationWithActiveCard for: " + donationRef);
            serviceResponse =
                    batchDelegate
                            .makeRegularDonationWithActiveCard(makeDonationRequest);
            LOGGER.debug("batchDelegate.makeRegularDonationWithActiveCard for: " + donationRef + " returns result: "
                    + serviceResponse);
             //serviceResponse = false;
            if (serviceResponse) {
                // call the db service to date stamp the record.
                LOGGER.debug("Donation successful. About to call batchDelegate.saveRegularDonation for: " + donationRef);
                saveRegularDonationRequest.setHeader(Util.getDummyHeader());
                /* The Payment management System has now been updated to always create a Donation record.
                 * Therefore this call is no longer required.
                processStep = "Saving donation";
                batchDelegate.saveRegularDonation(saveRegularDonationRequest);
                LOGGER.debug("About to call givingService.dateStampRegularDonation for Donor id/Don ref: "
                        + collectRegularDonationRequest.getDonorId() + "/" + donRef);
                 */
                 processStep = "Stamping donation";
                givingService
                        .dateStampRegularDonation(collectRegularDonationRequest
                                .getDonorId());
                // for successful payments call UCMON0010 to
                // update LastPayment field.
                processStep = "Donation successful";
                status = Constant.BATCH_STATUS_SUCCESSFUL;
            }
            else {
                processStep = "Process card payment failed";
                // 1) call UCMON0010 for failed payments
                processSuccessful = false;
                status = Constant.BATCH_STATUS_FAIL;
                LOGGER.debug("batchDelegate.makeRegularDonationWithActiveCard failed for Donor id/Don ref: "
                        + collectRegularDonationRequest.getDonorId() + "/" + donationRef);
                givingService.updateRegularDonationStatusInd(
                        collectRegularDonationRequest.getDonorId(),
                        Constant.FAILED);

                // 2) Trigger an email to the donor.
                // List<String> emailAddresses =
                // givingService.fetchDonorEmailAddress(collectRegularDonationRequest
                // .getDonorId());
                // LOGGER.debug("###################### emailAddress List size ######## :"+emailAddresses.size());
                Donation donation =
                        givingService.fetchDonation(saveRegularDonationRequest
                                .getDonationId());
                Set<PersonEmailAddress> emailAddresses =
                        donation.getPerson().getPersonEmailAddresses();

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("###################### Donation ######## :");
                }
                for (PersonEmailAddress emailAddress : emailAddresses) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("###################### Before Sending sendMail  ######## :");
                        LOGGER.debug("###################### Email address :  ######## :"
                                        + emailAddress.getEmailAddress().getEmailAddress());
                    }
                    sendEmail(EmailHelper.createMessageForFailedRegularDonationToDonor(
                            donation, emailAddress.getEmailAddress().getEmailAddress()));

                    LOGGER.debug("###################### After Sending sendMail :  ######## :");
                }

            }
        }
        catch (Exception exception) {
            LOGGER.error("Error occurred in  CollectRegularDonationItemTransformer :saveRegularDonation" +exception.getMessage(), exception);
            processStep = "Failed";
            processSuccessful = false;
        }
        createBatchEntity(collectRegularDonationRequest.getCharityId(), entityMsg, processStep, status);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemTransformer::saveRegularDonation() - END");
        }
    }

    /**
     * Send email.
     * 
     * @param message - Message containing mail details.
     */
    public void sendEmail(
            com.virginmoneygiving.templateemail.service.messages.Message message) {
        // message.setSender("esakki.yadav@arrkgroup.com");
        XStream xstream = new XStream();
        final String textMessage = xstream.toXML(message);
        jmsEmailTemplate.send(new MessageCreator() {
            public javax.jms.Message createMessage(Session session)
                    throws JMSException {
                LOGGER.debug("Text Message is:" + textMessage);
                return session.createTextMessage(textMessage);
            }
        });
    }

    /**
     * Creates the batch entity.
     * 
     * @param charityid the charityid
     * @param entityErrorMessage the entity msg
     * @param processStep the process step
     * @param status processing status
     */
    private void createBatchEntity(Long charityid, String entityErrorMessage, String processStep, String status) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemTransformer::createBatchEntity() - START");
        }
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        context.put(REGULAR_DONATION_PROCESSED_IND, "YES");
        String batchNumber = (String)context.get(Constant.BATCH_NUMBER);
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(status);
        batchEntity.setEntityId(charityid.toString());
        batchEntity.setNextBatchStatus("INDEPENDANT");
        batchEntity.setErrorMessage(entityErrorMessage + "-" + processStep);
        batchEntity.setPreviousStatus(null);
        batchEntity.setEntityTypeCode(Constant.ENTITY_CHARITY);
        batchEntity.setEntityReference(charityid.toString());  //HunarC: Added this
        batchEntity.setBaseReference(charityid.toString());    //HunarC: Added this
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch entity : CollectRegularDonationItemTransformer:createBatchEntity"
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
         }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch entity : CollectRegularDonationItemTransformer:createBatchEntity " +exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemTransformer::createBatchEntity() - END");
        }
    }

}
