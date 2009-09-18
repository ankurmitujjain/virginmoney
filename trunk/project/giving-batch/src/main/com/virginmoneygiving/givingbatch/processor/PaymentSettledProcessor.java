package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoney.glis.messages.SettledCharityPayments;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.exception.GlisServiceException;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.payment.service.PaymentService;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Timestamp;

import net.sf.dozer.util.mapping.DozerBeanMapper;

/**
 * This class is an Item Processor for the PaymentSettledProcessor batch. Takes
 * the object returned by the ItemReader and creates the class for the
 * itemWriter. Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class PaymentSettledProcessor implements
        ItemProcessor<SettledCharityPayments, SettledCharityPayments>,
        StepExecutionListener {

    /** Instance of logger. * */
    private static Logger LOGGER =
            Logger.getLogger(PaymentSettledProcessor.class);

    /** Payment service. * */
    private PaymentService paymentService;

    /** Mapper. */
    private DozerBeanMapper dozer;

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** instance for ExecutionContext. * */
    private ExecutionContext executionContext;

    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl locatorImpl;

    /** Batch number extracted from Job parameters. */
    private String batchNumber;

    /** Indicates whether any of the payments has fallen over. */
    private String batchErrorFlag = null;

    /** ID set. */
    private final Set<Long> idSet = new HashSet<Long>();

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

    /** ID. */
    private Long id = null;
    
    /** Charity ID. */
    private String charityId = null;

    /** Batch status. */
    private String batchStatus = null;

    /** Amount. */
    private BigDecimal amount1;

    /** Status code. */
    private String statusCode = null;

    /** ID of the entity. */
    private Long entityId = null;

    /** {@inheritDoc} */
    public SettledCharityPayments process(
            SettledCharityPayments paymentCollected) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("PaymentSettledProcessor.process - Start");
        }

        com.virginmoneygiving.payment.domain.PaymentSettled domainPaymentCollected =
                new com.virginmoneygiving.payment.domain.PaymentSettled();

        Timestamp settledDate = null;
        if (paymentCollected.getSettlementDate() > 0) {
            settledDate = new Timestamp(paymentCollected.getSettlementDate());
        }
        String batchNumber    = paymentCollected.getBatchNumber();
        String invoiceNumber = paymentCollected.getInvoiceOrCrNoteNumber();
        // dozer.map(paymentCollected, domainPaymentCollected);

        // Will throw an exception if the payment can't be matched
        // List<Object[]> list =
        // paymentService.fetchRecordsForSettlement(invoiceNumber);
        List<Object[]> paymentList =
                paymentService.fetchPaymentRecordsForSettlement(invoiceNumber);
        List<Object[]> cardChargeList =
                paymentService
                        .fetchCardChargeRecordsForSettlement(invoiceNumber);
        List<Object[]> feeList =
                paymentService
                        .fetchHandlingChargeRecordsForSettlement(invoiceNumber);
        List<Object[]> giftAidList =
                paymentService.fetchGiftAidRecordsForSettlement(invoiceNumber);
        List<Object[]> transitionalReliefList =
                paymentService
                        .fetchTransitionalReliefRecordsForSettlement(invoiceNumber);

        String entityType = Constant.ENTITY_PAYMENT;

        if (paymentList != null && !paymentList.isEmpty()) {
            settleRecords(paymentList, batchNumber, settledDate);
            entityType = Constant.ENTITY_PAYMENT;
            // populateIds(paymentList);
        }
        if (cardChargeList != null && !cardChargeList.isEmpty()) {
            settleRecords(cardChargeList, batchNumber, settledDate);
            entityType = Constant.ENTITY_CARD_CHARGE;
            // populateIds(cardChargeList);
        }
        if (feeList != null && !feeList.isEmpty()) {
            settleRecords(feeList, batchNumber, settledDate);
            entityType = Constant.ENTITY_HANDLING_CHARGE;
            // populateIds(feeList);
        }
        if (giftAidList != null && !giftAidList.isEmpty()) {
            settleRecords(giftAidList, batchNumber, settledDate);
            entityType = Constant.ENTITY_GIFTAID;
            // populateIds(giftAidList);
        }
        if (transitionalReliefList != null && !transitionalReliefList.isEmpty()) {
            settleRecords(transitionalReliefList, batchNumber, settledDate);
            entityType = Constant.ENTITY_TRANSITIONAL_RELIEF;
            // populateIds(transitionalReliefList);
        }

        if ((paymentList == null || paymentList.isEmpty())
                && (cardChargeList == null || cardChargeList.isEmpty())
                && (feeList == null || feeList.isEmpty())
                && (giftAidList == null || giftAidList.isEmpty())
                && (transitionalReliefList == null || transitionalReliefList.isEmpty())) {
            String eMsg = "Payment not found for Invoice/Creditnote: " + invoiceNumber;
            createBatchEntity((long)0, (long)0, invoiceNumber, entityType, Constant.BATCH_STATUS_ERROR, eMsg);
            updateBatchStatus();
            batchErrorFlag = "YES";
            
            //throw new GlisServiceException(LOGGER, "Can't identify payment for record: " + invoiceNumber);
        }

        createBatchEntity(id, entityId, invoiceNumber, entityType, Constant.BATCH_STATUS_SUCCESSFUL, null);

        return paymentCollected;

    }

    /**
     * To populate unique charity ids.
     * 
     * @param elementList the list of elements.
     * 
     * @return set of ids.
     */
    private Set<Long> populateIds(List<Object[]> elementList) {

        for (Object[] objArray : elementList) {
            idSet.add((Long.valueOf((String)objArray[1])));
        }
        executionContext.put("charityId", idSet);
        return idSet;
    }

    /**
     * Settle records.
     * 
     * @param list the list of objects.
     * @param batchNumber the batch number
     * @param settledDate the settled date
     */
    public void settleRecords(List<Object[]> list, String batchNumber, Timestamp settledDate) {
        for (Object[] objArray : list) {
            id = ((Long) objArray[0]);
            charityId = ((String)objArray[1]);
            batchStatus = ((String) objArray[2]);
            amount1 = ((BigDecimal) objArray[3]);
            statusCode = ((String) objArray[4]);
            if (objArray[5] != null) {
                entityId = ((Long) objArray[5]);
            }
        }

        if (batchStatus != null && statusCode != null) {

            if (statusCode.equals("WEBPAYCOLL")
                    && batchStatus.equals("WEBPAYCOLL")) {

                updateProcessStatus(id, "WEBDONSTLD", batchNumber, settledDate);
                populateIds(list);

            }
            if (statusCode.equals("ERFPAYCOLL")
                    && batchStatus.equals("ERFPAYCOLL")) {

                updateProcessStatus(id, "ERFPAYSETL", batchNumber, settledDate);
                populateIds(list);

            }
            if (statusCode.equals("TRCHRGPAID")
                    && batchStatus.equals("TRCHRGPAID")) {
                updateStatusCardChargeSettle(id, " ", batchNumber, settledDate);
                populateIds(list);

            }
            if (statusCode.equals("TRNFEEPAID")
                    && batchStatus.equals("TRNFEEPAID")) {
                updateStatusFeeChargeSettle(id, " ", batchNumber, settledDate);
                populateIds(list);

            }
            if (statusCode.equals("GFTAIDRECD")
                    && batchStatus.equals("GFTAIDRECD")) {
                updateGiftAidStatusSettle(id, " ", batchNumber, settledDate);
                populateIds(list);

            }
            if (statusCode.equals("TRNRLFRECD")
                    && batchStatus.equals("TRNRLFRECD")) {
                updateTransitionalReiefStatusSettle(id, " ", batchNumber, settledDate);
                populateIds(list);

            }
        }
    }

    /**
     * The updateProcessStatus method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param aid of type Long.
     * @param astatus of type String.
     * @param batchNumber the batch number
     * @param settledDate the settled date
     */
    public void updateProcessStatus(Long aid, String astatus, String batchNumber, Timestamp settledDate) {
        //paymentService.updateStatus(aid, astatus);
        paymentService.updateStatusPaymentSettlement(aid, astatus, batchNumber, settledDate);
    }

    /**
     * The updateStatusCardChargeSettle method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param aid of type Long.
     * @param astatus of type String.
     * @param batchNumber the batch number
     * @param settledDate the settled date
     */
    public void updateStatusCardChargeSettle(Long aid, String astatus, String batchNumber, Timestamp settledDate) {
        paymentService.updateStatusCardChargeSettle(aid, astatus, batchNumber, settledDate);
    }

    /**
     * The updateStatusFeeChargeSettle method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param aid of type Long.
     * @param astatus of type String.
     * @param batchNumber the batch number
     * @param settledDate the settled date
     */
    public void updateStatusFeeChargeSettle(Long aid, String astatus, String batchNumber, Timestamp settledDate) {
        paymentService.updateStatusFeeChargeSettle(aid, astatus, batchNumber, settledDate);
    }

    /**
     * The updateProcessStatus method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param aid of type Long.
     * @param batchNumber the batch number
     * @param settledDate the settled date
     */
    public void updateProcessStatus(Long aid, String batchNumber, Timestamp settledDate) {
        // paymentService.updateStatus(id, status);
        paymentService.updateStatusPaymentSettled(id, batchNumber, settledDate);
    }

    /**
     * The updateGiftAidStatusSettle method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param aid the id
     * @param astatus the status
     * @param batchNumber the batch number
     * @param settledDate the settled date
     */
    public void updateGiftAidStatusSettle(Long aid, String astatus, String batchNumber, Timestamp settledDate) {

        paymentService.updateGiftAidStatusSettle(aid, astatus, batchNumber, settledDate);

    }

    /**
     * Update status of Transitional relief.
     * 
     * @param aid the id.
     * @param astatus the status.
     * @param batchNumber the batch number
     * @param settledDate the settled date
     */
    public void updateTransitionalReiefStatusSettle(Long aid, String astatus, String batchNumber,
                                                    Timestamp settledDate) {
        paymentService.updateTransitionalReiefStatusSettle(aid, astatus, batchNumber, settledDate);
    }

    /** {@inheritDoc} */
    public ExitStatus afterStep(StepExecution astepExecution) {
        if (batchErrorFlag != null && batchErrorFlag.equalsIgnoreCase("YES")) {
            updateBatchStatus();
            return ExitStatus.FAILED;
        }
        return ExitStatus.COMPLETED;
    }

    /** {@inheritDoc} */
    public void beforeStep(StepExecution astepExecution) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("inside before step  execution context:" + astepExecution);
        }
        if (astepExecution != null) {
            executionContext = astepExecution.getJobExecution().getExecutionContext();
            JobParameter param = astepExecution.getJobParameters().getParameters().get(Constant.BATCH_NUMBER);
            if (param != null) {
                this.batchNumber = (String)param.getValue();
                LOGGER.debug("batch : " + batchNumber );
            }
            else {
                LOGGER.debug("batch : param is NULL"  );
            }
        }

    }

    /**
     * Creates a batch entity.
     * 
     * @param id ID of the entity.
     * @param entityReference Reference to use.
     * @param status Status of entity
     * @param eMsg Any error message
     * @param entityId the entity id
     * @param entityType the entity type
     */
    private void createBatchEntity(Long id, Long entityId, String entityReference, String entityType, String status, String eMsg ) {
        LOGGER.debug("Create entity (batch/Ref/Type/Id/Id2): " + batchNumber + "/" + entityReference + "/"
                + entityType + "/" + id + "/" + entityId);

        Long eId = entityId;
        if (eId == null) {
            eId = id;
        }
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(status);
        batchEntity.setEntityId(eId.toString());
        batchEntity.setEntityReference(entityReference);
        batchEntity.setBaseReference(entityReference);
        batchEntity.setNextBatchStatus("");
        batchEntity.setPreviousStatus(null);
        batchEntity.setErrorMessage(eMsg);
        batchEntity.setEntityTypeCode(entityType);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch Payment Entity  : PaymentInitiatedTransformer-"
                    + "Batch/Entity/Id: "+ batchNumber + "/" + entityReference + "/" + eId, e);
        }
        catch (Exception ex) {
            LOGGER.error("Unexpected error creating batch entity: PaymentInitiatedTransformer-"
                    + "Batch/Entity/Id: "+ batchNumber + "/" + entityReference + "/" + eId, ex);
        }
    }

    /**
     * Updates the batch status to failed if an error occurs.
     */
    private void updateBatchStatus() {
            LOGGER.debug("inside updateBatchStatus method - Error in batch.");
            try {
                UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setEndDateTime(Util.buildXMLGregorianDate());
                request.setStatus(Constant.BATCH_STATUS_FAIL);
                request.setErrorMessage("Please see entity details");
                locatorImpl.getGivingBatchExtManagementPort().updateBatchStatus(request);
            }
            catch (Exception e) {
                LOGGER.error("Unexpected error setting batch status to error. " + batchNumber, e);
            }
    }

}
