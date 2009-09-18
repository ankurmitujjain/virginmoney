package com.virginmoneygiving.givingbatch.launcher.impl;

import java.util.*;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.DateTime;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.virginmoney.glis.messages.*;
import com.virginmoney.glis.messages.ErrorMessage;
import com.virginmoneygiving.alert.service.messages.AlertContent;
import com.virginmoneygiving.alert.service.messages.AlertDetail;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.CharityAddress;
import com.virginmoneygiving.giving.domain.BankAccount;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.exception.GlisServiceException;
import com.virginmoneygiving.givingbatch.launcher.GLISResponseService;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.AlertServiceLocatorImpl;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.PaymentManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchCardChargeByIdRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchCardChargeByIdResponse;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchGiftAidByIdRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchGiftAidByIdResponse;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchHandlingChargeByIdRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchHandlingChargeByIdResponse;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchPaymentByIdRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchPaymentByIdResponse;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchTransitionalRlfByIdRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchTransitionalRlfByIdResponse;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchVatAmountByIdRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.FetchVatAmountByIdResponse;
import com.virginmoneygiving.paymentmanagement.service.messages.PaymentManagementServiceFaultException;
import com.virginmoneygiving.paymentmanagement.service.messages.SaveCardChargeRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.SaveGiftAidRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.SaveHandlingChargeRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.SavePaymentRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.SaveTransitionalRlfRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.SaveVatAmountRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.ServiceEntity;
import com.virginmoneygiving.givingbatchcontroller.common.JobChainProcessorInterface;

import javax.annotation.Resource;

/**
 * Implementation class for GLIS Response Service.
 * 
 * @author Puneet Swarup
 */
public class GLISResponseServiceImpl implements GLISResponseService {

    /** Logger instance. */
    private static Logger LOGGER = Logger.getLogger(GLISResponseServiceImpl.class);

    /** Giving batch ext. service locator. */
    private GivingBatchExtManagementServiceLocatorImpl givingBatchExtManagementServiceLocatorImpl = null;

    /** Giving Management service locator. */
    private GivingService givingService = null;

    /** Payment Management Service. */
    private PaymentManagementServiceLocatorImpl paymentService;

    /** Alert service locator. */
    private AlertServiceLocatorImpl alertServiceLocatorImpl = null;

    /** Batch helper class. */
    private GivingBatchHelper batchHelper = null;

    /** The job chain processor. */
    private JobChainProcessorInterface jobChainProcessor = null;

    /**
     * Sets the job chain processor.
     * 
     * @param jobChainProcessor the new job chain processor
     */
    @Resource
    public void setJobChainProcessor(JobChainProcessorInterface jobChainProcessor) {
        this.jobChainProcessor = jobChainProcessor;
    }

    /**
     * {@inheritDoc}
     */
    public void processMaintenanceResponse(final MaintenanceResponse maintenanceResponse) {

        LOGGER.debug("GLISResponseServiceImpl.processMaintenanceResponse - START Batch No: "
                + maintenanceResponse.getHeader().getRequestBatchNumber() + ", Date/Time: " + fetchTime());

        try {
            final FetchBatchEntityRequest fetchBatchEntityRequest = new FetchBatchEntityRequest();
            fetchBatchEntityRequest.setBatchNumber(maintenanceResponse.getHeader().getRequestBatchNumber());
            final FetchBatchEntityResponse fetchBatchEntityResponse =
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort().fetchBatchEntity(fetchBatchEntityRequest);

            final List<ServiceBatchEntity> batchEntities = fetchBatchEntityResponse.getBatchEntityList();

            final String batchNumber = maintenanceResponse.getHeader().getRequestBatchNumber();

            LOGGER.debug("GLISResponseServiceImpl.processMaintenanceResponse - batch Number/Status ="
                    + batchNumber + "/" + maintenanceResponse.isOk());

            String batchStatusSuccess  = Constant.BATCH_STATUS_SUCCESSFUL; //batchHelper.fetchBatchStatusType(Constant.BATCH_STATUS_SUCCESSFUL);
            String batchStatusFailed   = Constant.BATCH_STATUS_FAIL; //batchHelper.fetchBatchStatusType(Constant.BATCH_STATUS_FAIL);
            String batchStatusWarning  = Constant.BATCH_STATUS_WARNING; //batchHelper.fetchBatchStatusType(Constant.BATCH_STATUS_WARNING);
            String batchStatusRejected = Constant.BATCH_STATUS_REJECTED; //batchHelper.fetchBatchStatusType(Constant.BATCH_STATUS_WARNING);

            if (maintenanceResponse.isOk()) {

                if (Util.isStringEmpty(maintenanceResponse.getResponseMessage())
                        && maintenanceResponse.getFailedRecords().size() < 1) {

                    LOGGER.debug("Batch (" + batchNumber + ") processed successfully.");

                    for (ServiceBatchEntity batchEntity : batchEntities) {
                        processEntity(batchEntity, true);
                    }

                    final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                    request.setBatchNumber(batchNumber);
                    request.setStatus(batchStatusSuccess);
                    givingBatchExtManagementServiceLocatorImpl.getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);

                    setBatchStatus(batchNumber, batchStatusSuccess, null, true);

                    processNextBatchJob(batchNumber);

                } else {
                    // HunarC: Process failed records.
                    final List<FailedRecords> failedRecords = maintenanceResponse.getFailedRecords();
                    LOGGER.debug("BATCH WARNING: " + failedRecords.size() + " records failed while processing batch: "
                            + batchNumber);

                    setBatchStatus(batchNumber, batchStatusWarning, maintenanceResponse.getResponseMessage(), true);

                    Map<String, String> failedRecordsMap = new HashMap<String, String>();
                    for (FailedRecords failedRecord : failedRecords) {
                        String fMsg = failedRecord.getFailureMessage() + " (" + failedRecord.getFailureReason() + ")";
                        failedRecordsMap.put(failedRecord.getRecordReference(), fMsg);
                    }

                    for (ServiceBatchEntity batchEntity : batchEntities) {
                        final UpdateBatchEntityStatusRequest request = new UpdateBatchEntityStatusRequest();
                        boolean success = true;

                        request.setStatus(batchStatusFailed);
                        String errorMsg = getEntityErrorMessage(batchEntity.getEntityReference(), failedRecordsMap,
                                                                false);
                        if (Util.isStringEmpty(errorMsg)) {
                            request.setStatus(batchStatusSuccess);
                            success = true;
                        }

                        LOGGER.debug("Batch/Entity (" + batchNumber + "/" + batchEntity.getEntityId()
                                + ") has status/message: " + success + "/" + errorMsg);

                        request.setErrorMessage(errorMsg);
                        processEntity(batchEntity, success);
                        request.setBatchNumber(batchNumber);
                        request.setEntityId(batchEntity.getEntityId());

                        givingBatchExtManagementServiceLocatorImpl.getGivingBatchExtManagementPort()
                                .updateBatchEntityStatus(request);
                    }
                    LOGGER.debug("GLISResponseServiceImpl.processMaintenanceResponse - Finish Batch No (1): "
                            + maintenanceResponse.getHeader().getRequestBatchNumber() + ", Date/Time: " + fetchTime());

                    throw new GlisServiceException(LOGGER, "Exception occurred in GLISResponseServiceImpl : "
                            + "processMaintenanceResponse():The batch (" + batchNumber + ") has Warnings.");
                }
            } else {
                final List<FailedRecords> failedRecords = maintenanceResponse.getFailedRecords();
                LOGGER.debug("BATCH ERROR: " + failedRecords.size() + " records failed while processing batch: "
                            + batchNumber);
                String errorMessage = maintenanceResponse.getResponseMessage();
                if (maintenanceResponse.getErrors() != null && maintenanceResponse.getErrors().getErrors().size() > 0) {
                    for (ErrorMessage errMsg : maintenanceResponse.getErrors().getErrors()) {
                        errorMessage+= "." + errMsg.getErrorDefaultMessage();
                    }
                }

                setBatchStatus(batchNumber, batchStatusFailed, errorMessage, true);
                //final List<String> failedRecordIds = new ArrayList<String>();
                Map<String, String> failedRecordsMap = new HashMap<String, String>();
                for (FailedRecords failedRecord : failedRecords) {
                    String fMsg = failedRecord.getFailureMessage() + " (" + failedRecord.getFailureReason() + ")";
                    failedRecordsMap.put(failedRecord.getRecordReference(), fMsg);
                }

                for (ServiceBatchEntity batchEntity : batchEntities) {
                    final UpdateBatchEntityStatusRequest request = new UpdateBatchEntityStatusRequest();
                    boolean success = false;

                    //LOGGER.debug("#################################################");
                    //LOGGER.debug(failedRecordIds);
                    //LOGGER.debug("#################################################");

                    // Here we need to put a condition to check the Batch type
                    // and if batch type is any of the type where returned response
                    // may contain Failed records credit note number than we need to fetch the
                    // credit number and failed that record plus failed the entire batch.
                    /****************************************************/
                    String beStatus = batchStatusFailed;
                    if (!failedRecordsMap.containsKey(batchEntity.getEntityReference())) {
                        beStatus = batchStatusRejected; 
                    }
                    request.setStatus(beStatus);
                    request.setErrorMessage(getEntityErrorMessage(batchEntity.getEntityReference(),
                            failedRecordsMap, false));
                    processEntity(batchEntity, success);
                    request.setBatchNumber(batchNumber);
                    request.setEntityId(batchEntity.getEntityId());

                    givingBatchExtManagementServiceLocatorImpl.getGivingBatchExtManagementPort()
                            .updateBatchEntityStatus(request);
                }

                LOGGER.debug("GLISResponseServiceImpl.processMaintenanceResponse - Finish Batch No (2): "
                        + maintenanceResponse.getHeader().getRequestBatchNumber() + ", Date/Time: " + fetchTime());


                throw new GlisServiceException(LOGGER, "Exception occurred in GLISResponseServiceImpl : "
                        + "processMaintenanceResponse():The batch (" + batchNumber + ") has failed.");
            }
        }
        catch (Exception e) {
            LOGGER.debug("GLISResponseServiceImpl.processMaintenanceResponse - Exception in Batch No: "
                    + maintenanceResponse.getHeader().getRequestBatchNumber() + ", Date/Time: " + fetchTime());
            final String errorMessage = "Exception occurred in GLISResponseServiceImpl : processMaintenanceResponse()" ;
            LOGGER.error(errorMessage, e);
            raiseAlert(errorMessage);
        }

        LOGGER.debug("GLISResponseServiceImpl.processMaintenanceResponse - Completed Batch No: "
                + maintenanceResponse.getHeader().getRequestBatchNumber() + ", Date/Time: " + fetchTime());

        LOGGER.debug("GLISResponseServiceImpl.processMaintenanceResponse - END");
    }

    /**
     * Process an entity depending on the type returned by the batch entity type
     * code, with previous or next status depending on success record flag.
     * 
     * @param batchEntity   the ServiceBatchEntity
     * @param successRecord flag indicating success or failure.
     */
    private void processEntity(final ServiceBatchEntity batchEntity, final boolean successRecord) {

        String status = batchEntity.getNextBatchStatus();

        LOGGER.debug("processEntity-Start: Entity (" + batchEntity.getEntityId() + ") has status/type: " + status + "/"
                + batchEntity.getEntityTypeCode() + ", Entity-Date/Time: " + fetchTime());

        if (!successRecord) {
            status = batchEntity.getPreviousStatus();
        }

        if (Constant.ENTITY_CHARITY.equals(batchEntity.getEntityTypeCode())) {
            processCharityEntity(batchEntity, status, successRecord);
        } else if (Constant.ENTITY_PAYMENT.equals(batchEntity.getEntityTypeCode())) {
            processPaymentEntity(batchEntity, status);
        } else if (Constant.ENTITY_VAT.equals(batchEntity.getEntityTypeCode())) {
            processVATEntity(batchEntity, status);
        } else if (Constant.ENTITY_CARD_CHARGE.equals(batchEntity.getEntityTypeCode())) {
            processCardChargeEntity(batchEntity, status);
        } else if (Constant.ENTITY_HANDLING_CHARGE.equals(batchEntity.getEntityTypeCode())) {
            processHandlingChargeEntity(batchEntity, status);
        } else if (Constant.ENTITY_GIFTAID.equals(batchEntity.getEntityTypeCode())) {
            processGiftAidEntity(batchEntity, status);
        } else if (Constant.ENTITY_TRANSITIONAL_RELIEF.equals(batchEntity.getEntityTypeCode())) {
            processTransitionalRlfEntity(batchEntity, status);
        }

        LOGGER.debug("processEntity-Finish: Entity (" + batchEntity.getEntityId() + ")  Entity-Date/Time: " + fetchTime());

    }

    /**
     * Process Charity entity.
     * 
     * @param batchEntity the ServiceBatchEntity
     * @param status      flag indicating success or failure.
     * @param successRecord Indicates if the update was successful.
     */
    private void processCharityEntity(final ServiceBatchEntity batchEntity, final String status,
                                      boolean successRecord) {
        Long charityId = Long.valueOf(batchEntity.getEntityId());
        boolean updateInd = false;
        if (successRecord && status != null && status.equalsIgnoreCase("LIV")) {
            //resetCharityAmendmentFlags(charity);
            updateInd = true;
        }
        //Charity charity = givingService.fetchCharityDetailsById(Long.valueOf(batchEntity.getEntityId()));
        //LOGGER.debug("processCharityEntity-1: ID (" + charity.getId() + ") has current/new status: " + charity.getBatchStatus() + "/" + status);
        //charity.setBatchStatus(status);
        //givingService.updateCharityDetails(charity);
        givingService.updateCharityBatchStatus(charityId, status, updateInd, null);
    }

    /**
     * Process Payment entity.
     * 
     * @param batchEntity the ServiceBatchEntity
     * @param status      flag indicating success or failure.
     */
    private void processPaymentEntity(final ServiceBatchEntity batchEntity, final String status) {
        try {
            final FetchPaymentByIdRequest request = new FetchPaymentByIdRequest();
            request.setId(Long.valueOf(batchEntity.getEntityId()));
            request.setHeader(Util.getPaymentHeader("processPaymentEntity"));
            final FetchPaymentByIdResponse response =
                    paymentService.getPaymentManagementPort().fetchPaymentById(request);
            ServiceEntity payment = response.getPayment();
            payment.setBatchStatus(status);
            final SavePaymentRequest saveRequest = new SavePaymentRequest();
            saveRequest.setPayment(payment);
            saveRequest.setHeader(Util.getPaymentHeader("processPaymentEntity"));
            paymentService.getPaymentManagementPort().savePayment(saveRequest);
        }
        catch (PaymentManagementServiceFaultException ex) {
            final String errorMessage = "Error occurred while processing Payment entity." + ex.getMessage();
            LOGGER.error(errorMessage, ex);
            raiseAlert(errorMessage);
        }
    }

    /**
     * Process VAT entity.
     * 
     * @param batchEntity the ServiceBatchEntity
     * @param status      flag indicating success or failure.
     */
    private void processVATEntity(final ServiceBatchEntity batchEntity, final String status) {
        try {
            final FetchVatAmountByIdRequest request = new FetchVatAmountByIdRequest();
            request.setId(Long.valueOf(batchEntity.getEntityId()));
            request.setHeader(Util.getPaymentHeader("processVATEntity"));
            final FetchVatAmountByIdResponse response =
                    paymentService.getPaymentManagementPort().fetchVatAmountById(request);
            ServiceEntity vatAmount = response.getVatAmount();
            vatAmount.setBatchStatus(status);
            final SaveVatAmountRequest saveRequest = new SaveVatAmountRequest();
            saveRequest.setVatAmount(vatAmount);
            saveRequest.setHeader(Util.getPaymentHeader("processVATEntity"));
            paymentService.getPaymentManagementPort().saveVatAmount(saveRequest);
        }
        catch (PaymentManagementServiceFaultException ex) {
            final String errorMessage = "Error occurred while processing VatAmount entity." + ex.getMessage();
            LOGGER.error(errorMessage, ex);
            raiseAlert(errorMessage);
        }
    }

    /**
     * Process CardCharge entity.
     * 
     * @param batchEntity the ServiceBatchEntity
     * @param status      flag indicating success or failure.
     */
    private void processCardChargeEntity(final ServiceBatchEntity batchEntity, final String status) {
        try {
            final FetchCardChargeByIdRequest request = new FetchCardChargeByIdRequest();
            request.setId(Long.valueOf(batchEntity.getEntityId()));
            request.setHeader(Util.getPaymentHeader("processCardChargeEntity"));
            final FetchCardChargeByIdResponse response =
                    paymentService.getPaymentManagementPort().fetchCardChargeById(request);
            ServiceEntity cardChargeEntity = response.getCardCharge();
            cardChargeEntity.setBatchStatus(status);

            final SaveCardChargeRequest saveRequest = new SaveCardChargeRequest();
            saveRequest.setCardCharge(cardChargeEntity);
            saveRequest.setHeader(Util.getPaymentHeader("processCardChargeEntity"));
            paymentService.getPaymentManagementPort().saveCardChage(saveRequest);
        }
        catch (PaymentManagementServiceFaultException ex) {
            final String errorMessage = "Error occurred while processing CardChargeEntity entity." + ex.getMessage();
            LOGGER.error(errorMessage, ex);
            raiseAlert(errorMessage);
        }
    }

    /**
     * Process handling charge entity.
     * 
     * @param batchEntity the batch entity
     * @param status the status
     */
    private void processHandlingChargeEntity(final ServiceBatchEntity batchEntity, final String status) {
        try {
            final FetchHandlingChargeByIdRequest request = new FetchHandlingChargeByIdRequest();
            request.setId(Long.valueOf(batchEntity.getEntityId()));
            request.setHeader(Util.getPaymentHeader("processHandlingChargeEntity"));
            final FetchHandlingChargeByIdResponse response =
                    paymentService.getPaymentManagementPort().fetchHandlingChargeById(request);
            ServiceEntity handlingCharge = response.getHandlingCharge();
            handlingCharge.setBatchStatus(status);
            final SaveHandlingChargeRequest saveHandlingChargeRequest = new SaveHandlingChargeRequest();
            saveHandlingChargeRequest.setHandlingCharge(handlingCharge);
            saveHandlingChargeRequest.setHeader(Util.getPaymentHeader("processHandlingChargeEntity"));
            paymentService.getPaymentManagementPort().saveHandlingCharge(saveHandlingChargeRequest);
        }
        catch (Exception ex) {
            final String errorMessage = "Error occurred while processing HandlingChargeEntity." + ex.getMessage();
            LOGGER.error(errorMessage, ex);
            raiseAlert(errorMessage);
        }
    }

    /**
     * Process gift aid entity.
     * 
     * @param batchEntity the batch entity
     * @param status the status
     */
    private void processGiftAidEntity(final ServiceBatchEntity batchEntity, final String status) {
        try {
            final FetchGiftAidByIdRequest request = new FetchGiftAidByIdRequest();
            request.setId(Long.valueOf(batchEntity.getEntityId()));
            request.setHeader(Util.getPaymentHeader("processGiftAidEntity"));
            final FetchGiftAidByIdResponse response =
                    paymentService.getPaymentManagementPort().fetchGiftAidById(request);
            ServiceEntity giftAidEntity = response.getGiftAid();
            giftAidEntity.setBatchStatus(status);

            final SaveGiftAidRequest giftAidRequest = new SaveGiftAidRequest();
            giftAidRequest.setGiftAid(giftAidEntity);
            giftAidRequest.setHeader(Util.getPaymentHeader("processGiftAidEntity"));
            paymentService.getPaymentManagementPort().saveGiftAid(giftAidRequest);
        }
        catch (Exception ex) {
            final String errorMessage = "Error occurred while processing GiftAidEntity." + ex.getMessage();
            LOGGER.error(errorMessage, ex);
            raiseAlert(errorMessage);
        }
    }


    /**
     * Process transitional rlf entity.
     * 
     * @param batchEntity the batch entity
     * @param status the status
     */
    private void processTransitionalRlfEntity(final ServiceBatchEntity batchEntity, final String status) {
        try {
            final FetchTransitionalRlfByIdRequest request = new FetchTransitionalRlfByIdRequest();
            request.setId(Long.valueOf(batchEntity.getEntityId()));
            request.setHeader(Util.getPaymentHeader("processTransitionalRlfEntity"));
            final FetchTransitionalRlfByIdResponse response =
                    paymentService.getPaymentManagementPort().fetchTransitionalRlfById(request);
            ServiceEntity transitionalRlf = response.getTransitionalRlf();
            transitionalRlf.setBatchStatus(status);

            final SaveTransitionalRlfRequest trasitionalRequest = new SaveTransitionalRlfRequest();
            trasitionalRequest.setTransitionalRlf(transitionalRlf);
            trasitionalRequest.setHeader(Util.getPaymentHeader("processTransitionalRlfEntity"));
            paymentService.getPaymentManagementPort().saveTransitionalRlf(trasitionalRequest);
        }
        catch (Exception ex) {
            final String errorMessage = "Error occurred while processing GiftAidEntity." + ex.getMessage();
            LOGGER.error(errorMessage, ex);
            raiseAlert(errorMessage);
        }
    }


    /**
     * Raise an alert, log it in the alert audit.
     * 
     * @param message the message for alert.
     */
    private void raiseAlert(final String message) {
        AlertContent alertContent = new AlertContent();
        alertContent.setAlertMessage(message);
        GregorianCalendar cal = new GregorianCalendar();
        alertContent.setDateTimeOfAlert(new XMLGregorianCalendarImpl(cal));
        alertContent.setService("giving-batch");

        AlertDetail alertDetail = new AlertDetail();
        alertDetail.setContent(alertContent);

        try {
            alertServiceLocatorImpl.getAlertPort().logAlertRequest(alertDetail);
        }
        catch (Exception e) {
            LOGGER.error("Exception occurred while raising alert.", e);
        }
    }

    /**
     * Sets the giving batch ext management service locator impl.
     * 
     * @param givingBatchExtManagementServiceLocatorImpl the givingBatchExtManagementServiceLocatorImpl to set
     */
    public void setGivingBatchExtManagementServiceLocatorImpl(
            GivingBatchExtManagementServiceLocatorImpl givingBatchExtManagementServiceLocatorImpl) {
        this.givingBatchExtManagementServiceLocatorImpl =
                givingBatchExtManagementServiceLocatorImpl;
        //LOGGER.trace("GivingBatchExtManagementServiceLocatorImpl injected.");
    }

    /**
     * Sets the alert service locator impl.
     * 
     * @param alertServiceLocator the alertServiceLocator to set
     */
    public void setAlertServiceLocatorImpl(
            AlertServiceLocatorImpl alertServiceLocator) {
        this.alertServiceLocatorImpl = alertServiceLocator;
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
     * Sets the payment service.
     * 
     * @param paymentService the paymentService to set
     */
    public void setPaymentService(
            PaymentManagementServiceLocatorImpl paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Spring-injected batch helper class.
     * 
     * @param batchHelper batchHelper to set
     */
    public void setBatchHelper(GivingBatchHelper batchHelper) {
        this.batchHelper = batchHelper;
    }

/*    private MessageHeader getDummyHeader() {
        MessageHeader header = new MessageHeader();
        header.setBrandReference("12346");
        //header.setIPAddress("12346");
        header.setSessionID("12346");
        header.setSourceSubSystemId("12346");
        header.setSystemTransactionID("12346");
        header.setSourceSystemId("12346");
        header.setUserName("12346");
        return header;
    }

    private com.virginmoneygiving.paymentmanagement.service.messages.MessageHeader getDummyPaymentHeader() {
        com.virginmoneygiving.paymentmanagement.service.messages.MessageHeader header =
                new com.virginmoneygiving.paymentmanagement.service.messages.MessageHeader();
        header.setBrandReference("12346");
        //header.setIPAddress("12346");
        header.setSessionID("12346");
        header.setSourceSubSystemId("12346");
        header.setSystemTransactionID("12346");
        header.setSourceSystemId("12346");
        header.setUserName("12346");
        return header;
    }*/

    /**
 * Retrieves the error message for the specified entity from the entity map.
 * 
 * @param eRef Entity ref
 * @param failedRecordsMap Map of failed records.
 * @param defaultMessage Indicates whether the default message should be shown.
 * 
 * @return String error message.
 */
    private String getEntityErrorMessage(String eRef, Map<String, String> failedRecordsMap, boolean defaultMessage) {
        String errorMessage = null;
        if (defaultMessage) {
            errorMessage = "Processing error. Refer to log files for details.";
        }
        if (failedRecordsMap == null || failedRecordsMap.isEmpty()) {
            return errorMessage;
        }

        if (failedRecordsMap.containsKey(eRef)) {
            String errMsg = failedRecordsMap.get(eRef);
            if (!Util.isStringEmpty(errMsg)) {
                errorMessage = errMsg;
            }
        }
        if (errorMessage != null && errorMessage.length() > 250) {
            errorMessage = errorMessage.substring(0, 249);
        }
        return errorMessage;
    }

    /**
     * Updated the batch status.
     * 
     * @param batchNumber Batch number to update
     * @param status The status to set
     * @param message Any error message
     * @param setEnd Indicates whether the end date should be set on the batch
     */
    private void setBatchStatus(String batchNumber, String status, String message, boolean setEnd) {
        try {
            UpdateBatchStatusRequest updateBSRequest = new UpdateBatchStatusRequest();
            updateBSRequest.setBatchNumber(batchNumber);
            updateBSRequest.setStatus(status);
            if (setEnd) {
                updateBSRequest.setEndDateTime(Util.buildXMLGregorianDate());
            }
            if (message != null && message.length() > 250) {
                message = message.substring(0, 249);
            }
            updateBSRequest.setErrorMessage(message);
            givingBatchExtManagementServiceLocatorImpl.getGivingBatchExtManagementPort()
                    .updateBatchStatus(updateBSRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Unexpected error setting batch status (Batch/Status): " + batchNumber + "/"
                    + status, e);
        }

    }

    /**
     * Process next batch job.
     * 
     * @param batchNumber the batch number
     */
    private void processNextBatchJob(String batchNumber) {
        LOGGER.debug("*****************************************************************************");
        LOGGER.debug("processNextBatchJob-Starting next job in thread for batch: " + batchNumber);
        jobChainProcessor.processNextJob(batchNumber, null);
        LOGGER.debug("processNextBatchJob-Done");
        LOGGER.debug("*****************************************************************************");
    }

    /**
     * Reset charity amendment flags.
     * 
     * @param charity the charity
     */
/*    private void resetCharityAmendmentFlags(Charity charity) {
         if (charity.getCharityStatus() == null ||charity.getBatchStatus() == null) {
            //LOGGER.debug("resetCharityAmendmentFlags Returning (1) for Charity: " + charity.getId());
            return;
        }

        if (!charity.getCharityStatus().getCode().equalsIgnoreCase("LIV")
                || !charity.getBatchStatus().equalsIgnoreCase("LIV")) {
            //LOGGER.debug("resetCharityAmendmentFlags Returning (2) for Charity: " + charity.getId());
            return;
        }

        //LOGGER.debug("resetCharityAmendmentFlags Resetting flags for Charity: " + charity.getId());

        charity.setCharityUpdateInd(null);
        for (CharityAddress address : charity.getCharityAddresses()) {
            address.getAddress().setAddressUpdateInd(null);
        }
        for (BankAccount account : charity.getBankAccounts()) {
            account.setBankAccountUpdateInd(null);
        }
    }*/

    private String fetchTime() {
        DateTimeFormatter fmt = Constant.DATETIMEFORMATTER;
        DateTime sDate = new DateTime();
        String dt = fmt.print(sDate);
        return dt;
    }

}
