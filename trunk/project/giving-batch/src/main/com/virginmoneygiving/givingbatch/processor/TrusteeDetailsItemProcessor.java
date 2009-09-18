package com.virginmoneygiving.givingbatch.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.apache.log4j.Logger;

import com.virginmoneygiving.giving.domain.AuditAttributes;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.Person;
import com.virginmoneygiving.giving.domain.TrusteeDetails;
import com.virginmoneygiving.giving.domain.TrusteeStatus;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.domain.Address;
import com.virginmoneygiving.givingbatch.domain.CharityTrusteeDetails;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;

import javax.annotation.Resource;

/**
 * The Class TrusteeDetailsItemProcessor.
 * 
 * @author Srinivas Nageli.
 */
public class TrusteeDetailsItemProcessor implements StepExecutionListener,
        ItemProcessor<TrusteeDetails, CharityTrusteeDetails> {

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER = Logger.getLogger(TrusteeDetailsItemProcessor.class);

    /** The giving service. */
    private GivingService givingService;
    
    /** The execution context. */
    private ExecutionContext executionContext;
    
    /** The details. */
    private Map<Long, CharityTrusteeDetails> details =
            new HashMap<Long, CharityTrusteeDetails>();

    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl givingBatchExtServiceLocator;

    /** The Constant TRUSTEE_STATUS_COMPLEATED. */
    private static final String TRUSTEE_STATUS_COMPLEATED = "CTD";

    /** The Constant TRUSTEE_STATUS_PROCESSED. */
    private static final String TRUSTEE_STATUS_PROCESSED = "PROC";

    /** The Constant ADDRESS_TYPE_REGISTER. */
    private static final String ADDRESS_TYPE_REGISTER = "Register";

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
    public CharityTrusteeDetails process(TrusteeDetails trusteeDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::process() - START");
        }
        CharityTrusteeDetails charityTrusteeDetails = null;
        Charity charity = trusteeDetails.getCharityDetails();

        LOGGER.debug("Reviewing CharityTrusteeDetails for: " + charity.getId());
        if (TRUSTEE_STATUS_COMPLEATED.equalsIgnoreCase(givingService
                .getOfflineCharityTrusteeStatus(charity.getId()))) {

            charityTrusteeDetails =
                    (CharityTrusteeDetails) executionContext.get(String
                            .valueOf(charity.getId()));

            if (charityTrusteeDetails == null) {
                charityTrusteeDetails = new CharityTrusteeDetails();
                charityTrusteeDetails =
                        populateCharityDetails(charityTrusteeDetails, charity);
            }

            charityTrusteeDetails =
                    populateTrusteeDetails(charityTrusteeDetails,
                            trusteeDetails);

            LOGGER.debug("Processing CharityTrusteeDetails for: " + charity.getId());

            executionContext.put(String.valueOf(charity.getId()),
                    charityTrusteeDetails);
            details.put(charity.getId(), charityTrusteeDetails);

            executionContext.put("CHARITY_TRUSTEE_DETAILS", details);
            TrusteeStatus status = new TrusteeStatus();
            status.setCode(TRUSTEE_STATUS_PROCESSED);
            trusteeDetails.setTrusteeStatus(status);
            // TODO need to find some generic implementation which would log the
            // audit info such as IP address and process name (in this case).
            // TODO replace following with actual implementation.
            final AuditAttributes attributes = new AuditAttributes();
            attributes.setCreatedProcess("TRUSTEE_DETAILS_PROCESSOR");
            attributes.setUpdatedIPAddress(Util.getHostIpaddress());
            attributes.setUpdatedProcess("charityTrusteeDetailsJob");
            givingService.saveTrusteeDetails(trusteeDetails, charity.getId(), attributes);
            createBatchEntity(trusteeDetails.getId(), charity.getId(), "Trustee processed.");
        }
        else {
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("CharityTrusteeDetails for: " + charity.getId() + " NOT PROCESSED ");
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::process() - END");
        }
        return charityTrusteeDetails;
    }

    /**
     * Populate charity details.
     * 
     * @param charityTrusteeDetails the charity trustee details
     * @param charity the charity
     * 
     * @return the charity trustee details
     */
    private CharityTrusteeDetails populateCharityDetails(
            final CharityTrusteeDetails charityTrusteeDetails,
            final Charity charity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::populateCharityDetails() - START");
        }
        charityTrusteeDetails.setName(charity.getName());
        charityTrusteeDetails.setReferenceNumber(charity.getRegisteredCharityNumber());
        Set<com.virginmoneygiving.giving.domain.CharityAddress> charityAddresses =
                charity.getCharityAddresses();

        for (final com.virginmoneygiving.giving.domain.CharityAddress charityAddress : charityAddresses) {

            if (ADDRESS_TYPE_REGISTER.equalsIgnoreCase(charityAddress.getAddress()
                    .getAddressType().getCode())) {
                Address charityRegisteredAddress = new Address();
                charityRegisteredAddress.setAddress1(charityAddress.getAddress()
                        .getAddressLine1());
                charityRegisteredAddress.setAddress2(charityAddress.getAddress()
                        .getAddressLine2());
                charityRegisteredAddress.setAddress3(charityAddress.getAddress()
                        .getTownCity());
                charityRegisteredAddress.setAddress4(charityAddress.getAddress().getCountyState());
                charityRegisteredAddress.setPostcode(charityAddress.getAddress()
                        .getPostCode());
                charityTrusteeDetails.setAddress(charityRegisteredAddress);
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::populateCharityDetails() - END");
        }
        return charityTrusteeDetails;
    }

    /**
     * Populate trustee details.
     * 
     * @param charityTrusteeDetails the charity trustee details
     * @param trusteeDetails the trustee details
     * 
     * @return the charity trustee details
     */
    private CharityTrusteeDetails populateTrusteeDetails(
            final CharityTrusteeDetails charityTrusteeDetails,
            final TrusteeDetails trusteeDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::populateTrusteeDetails() - START");
        }
        com.virginmoneygiving.givingbatch.domain.TrusteeDetails trDetails =
                new com.virginmoneygiving.givingbatch.domain.TrusteeDetails();
        Person personDetails = trusteeDetails.getPersonDetails();
        trDetails.setForename(personDetails.getForename());
        trDetails.setSurname(personDetails.getSurname());
        GregorianCalendar calendar =
                new GregorianCalendar(personDetails.getDobYear(), personDetails
                        .getDobMonth() - 1, personDetails.getDobDay());

        trDetails.setDob(new Date(calendar.getTimeInMillis()));
        Set<com.virginmoneygiving.giving.domain.PersonalAddress> trusteeAddresses =
                personDetails.getPersonalAddresses();

        final Address address = new Address();

        for (final com.virginmoneygiving.giving.domain.PersonalAddress trusteeAddress : trusteeAddresses) {
            address.setAddress1(trusteeAddress.getAddress().getAddressLine1());
            address.setAddress2(trusteeAddress.getAddress().getAddressLine2());
            address.setAddress3(trusteeAddress.getAddress().getTownCity());
            address.setAddress4(trusteeAddress.getAddress().getCountyState());
            address.setPostcode(trusteeAddress.getAddress().getPostCode());
            break;
        }
        trDetails.setAddress(address);
        List<com.virginmoneygiving.givingbatch.domain.TrusteeDetails> trustees =
                charityTrusteeDetails.getTrustees();

        if (trustees == null) {
            trustees =
                    new ArrayList<com.virginmoneygiving.givingbatch.domain.TrusteeDetails>();
        }
        trustees.add(trDetails);
        charityTrusteeDetails.setTrustees(trustees);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::populateTrusteeDetails() - END");
        }
        return charityTrusteeDetails;
    }

    /**
     * {@inheritDoc}
     */
    public void beforeStep(StepExecution stepExecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::beforeStep() - START");
        }
        executionContext = stepExecution.getJobExecution().getExecutionContext();
        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(Constant.TRUSTEE_DETAILS);
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("charityTrusteeDetailsJob");
        serviceBatch.setNextBatchName("giftAidClaimProcessJob");
        createBatchRequest.setServiceBatch(serviceBatch);

        try {
        	givingBatchExtServiceLocator.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	executionContext.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch : TrusteeDetailsItemProcessor."
                        + e.getMessage(), e);

         }
        catch(Exception ex)
        {
            LOGGER.error("TrusteeDetailsItemProcessor: Error occured while calling creating batch ", ex);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::beforeStep() - END");
        }
    }

    /**
     * {@inheritDoc}
     */
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::afterStep() - START");
        }
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "NO");
        Map<Long, CharityTrusteeDetails> tList = (Map<Long, CharityTrusteeDetails>) executionContext
                        .get("CHARITY_TRUSTEE_DETAILS");
        String errMsg  = "Batch processed.";
        String bStatus = Constant.BATCH_STATUS_SUCCESSFUL;
        if (tList == null || tList.size() < 1) {
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
            LOGGER.error("Unexpected error setting batch status to complete in afterstep"
                        + e.getMessage(), e);

         }
        catch (Exception e) {
            LOGGER.error("Unexpected error setting batch status to complete in afterstep", e);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::afterStep() - START");
        }
        return ExitStatus.COMPLETED;
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
     * Creates the batch entity.
     * 
     * @param trusteeId the trustee id
     * @param charityId the charity id
     * @param msgText the msg text
     */
    private void createBatchEntity(Long trusteeId, Long charityId, String msgText) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::createBatchEntity() - START");
        }
    	String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_SUCCESSFUL);
        batchEntity.setEntityId(charityId.toString());
        batchEntity.setNextBatchStatus(null);
        batchEntity.setPreviousStatus(null);
        batchEntity.setEntityTypeCode(Constant.ENTITY_CHARITY);
        batchEntity.setEntityReference(charityId.toString());
        batchEntity.setBaseReference(trusteeId.toString());
        batchEntity.setErrorMessage(msgText);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            givingBatchExtServiceLocator.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch entity : TrusteeDetailsItemProcessor."
                        + e.getMessage(), e);

         }
        catch(Exception ex)
        {
            LOGGER.error("Error occured while calling creating batch entity : TrusteeDetailsItemProcessor ", ex);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("VerifyCharityItemProcessor::createBatchEntity() - END");
        }
    }

}
