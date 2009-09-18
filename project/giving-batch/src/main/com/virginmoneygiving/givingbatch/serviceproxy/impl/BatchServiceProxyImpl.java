package com.virginmoneygiving.givingbatch.serviceproxy.impl;

import java.util.GregorianCalendar;
import java.util.List;

import net.sf.dozer.util.mapping.DozerBeanMapper;

import org.apache.log4j.Logger;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.DateTime;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.virginmoney.glis.messages.*;
import com.virginmoneygiving.alert.service.messages.AlertContent;
import com.virginmoneygiving.alert.service.messages.AlertDetail;
import com.virginmoneygiving.alert.service.messages.AlertPort;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.Charities;
import com.virginmoneygiving.givingbatch.domain.CharitiesBatch;
import com.virginmoneygiving.givingbatch.domain.DonationDistribution;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentCollected;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentCollectedBatch;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentInitiatedBatch;
import com.virginmoneygiving.givingbatch.domain.EventProcessing;
import com.virginmoneygiving.givingbatch.domain.FailedDonationPaymentBatch;
import com.virginmoneygiving.givingbatch.domain.FailedPayment;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimedBatch;
import com.virginmoneygiving.givingbatch.domain.GiftAidReceived;
import com.virginmoneygiving.givingbatch.domain.GiftAidReceivedBatch;
import com.virginmoneygiving.givingbatch.domain.MakeDonationWithActiveCard;
import com.virginmoneygiving.givingbatch.domain.PaymentInitiated;
import com.virginmoneygiving.givingbatch.domain.RegistrationFee;
import com.virginmoneygiving.givingbatch.domain.RegistrationFeesBatch;
import com.virginmoneygiving.givingbatch.domain.SaveRegularDonationRequest;
import com.virginmoneygiving.givingbatch.domain.Transaction;
import com.virginmoneygiving.givingbatch.domain.TransactionChargePaid;
import com.virginmoneygiving.givingbatch.domain.TransactionChargePaidBatch;
import com.virginmoneygiving.givingbatch.domain.TransactionFeePaid;
import com.virginmoneygiving.givingbatch.domain.TransactionFeePaidBatch;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefClaimed;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefClaimedBatch;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefReceived;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefReceivedBatch;
import com.virginmoneygiving.givingbatch.domain.WaiveRegistrationFee;
import com.virginmoneygiving.givingbatch.domain.WaiveRegistrationFeesBatch;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.exception.GlisServiceException;
import com.virginmoneygiving.givingbatch.jms.GlisMessageDelegate;
import com.virginmoneygiving.givingbatch.serviceproxy.BatchServiceProxy;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.GivingBatchExtManagementServiceFaultException;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.UpdateAllBatchEntityStatusRequest;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.UpdateBatchStatusRequest;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.UpdateBatchEntityStatusByReferenceRequest;
import com.virginmoneygiving.givingmanagement.service.messages.GivingServiceDonationDetails;
import com.virginmoneygiving.givingmanagement.service.messages.MakeRegularDonationWithActiveCardRequest;
import com.virginmoneygiving.givingmanagement.service.messages.MakeRegularDonationWithActiveCardResponse;
import com.virginmoneygiving.givingmanagement.service.messages.MessageHeader;
import com.virginmoneygiving.paymentmanagement.service.messages.CreateRegularWebDonationRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.CreateRegularWebDonationResponse;
import com.virginmoneygiving.paymentmanagement.service.messages.ServiceCardTransaction;
import com.virginmoneygiving.giving.domain.BankAccountType;

/**
 * This class contains all the methods which are required to make a gliss call.
 * Methods in this class will return a boolean outcome depending on the processing of
 * the object send to the gliss.
 * 
 * @author Siva Kumar
 */
public class BatchServiceProxyImpl implements BatchServiceProxy {
    
    /** Logger initialization. */
    private static final Logger LOGGER = Logger.getLogger(BatchServiceProxyImpl.class);
    
    /** web service locator - set by spring. */
    private GlisWebServiceLocatorImpl glisServiceLocator;

    /** Web Service locator - set by spring. */
    private GivingManagementServiceLocatorImpl givingManagementServiceLocator;

    /** Web Service locator - set by Spring. */
    private PaymentManagementServiceLocatorImpl paymentManagementServiceLocator;

    /** Giving batch ext. service locator. */
    private GivingBatchExtManagementServiceLocatorImpl givingBatchExtManagementServiceLocatorImpl = null;
    
    /** Dozer bean mapping instance. */
    private DozerBeanMapper dozer;

    /** Alert service locator. */
    private AlertServiceLocatorImpl alertServiceLocator = null;

    /**
     * Gets the alert service locator.
     * 
     * @return the alert service locator
     */
    public AlertServiceLocatorImpl getAlertServiceLocator() {
        return alertServiceLocator;
    }

    /**
     * Sets the alert service locator.
     * 
     * @param alertServiceLocator the new alert service locator
     */
    public void setAlertServiceLocator(
            AlertServiceLocatorImpl alertServiceLocator) {
        this.alertServiceLocator = alertServiceLocator;
    }

    /**
     * Sets the giving batch ext management service locator impl.
     * 
     * @param givingBatchExtManagementServiceLocatorImpl the new giving batch ext management service locator impl
     */
    public void setGivingBatchExtManagementServiceLocatorImpl(
            GivingBatchExtManagementServiceLocatorImpl givingBatchExtManagementServiceLocatorImpl) {
        this.givingBatchExtManagementServiceLocatorImpl = givingBatchExtManagementServiceLocatorImpl;
    }

    /**
     * Sets the dozer.
     * 
     * @param dozer the dozer to set
     */
    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }

    /**
     * Sets the glis service locator.
     * 
     * @param glisServiceLocator the glisServiceLocator to set
     */
    public void setGlisServiceLocator(
            GlisWebServiceLocatorImpl glisServiceLocator) {
        this.glisServiceLocator = glisServiceLocator;
    }

    /** {@inheritDoc} */
    public boolean processDonationPaymentsInitiated(
            DonationPaymentInitiatedBatch domainDonationPaymentInitiatedBatch)
            throws GivingBatchException {
        com.virginmoney.glis.messages.DonationPaymentInitiatedBatch serviceDonationPaymentInitiated =
            new com.virginmoney.glis.messages.DonationPaymentInitiatedBatch();

        String batchNumber = domainDonationPaymentInitiatedBatch.getBatch()
                .getBatchNumber();
        logFetchTime("ProcessDonationPaymentInitiated Start. batch = " + batchNumber);

        serviceDonationPaymentInitiated
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        domainDonationPaymentInitiatedBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceDonationPaymentInitiated
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                domainDonationPaymentInitiatedBatch
                                        .getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(domainDonationPaymentInitiatedBatch
                .getPaymentInitiated())) {
            for (PaymentInitiated paymentInitiated : domainDonationPaymentInitiatedBatch
                    .getPaymentInitiated()) {

                serviceDonationPaymentInitiated
                        .getWebDonationPayments()
                        .add(
                                (com.virginmoney.glis.messages.PaymentInitiated) dozer
                                        .map(
                                                paymentInitiated,
                                                com.virginmoney.glis.messages.PaymentInitiated.class));
            }
            LOGGER
                    .debug("Before Dozer mapping domain Payment Initiated is:"
                            + domainDonationPaymentInitiatedBatch
                                    .getPaymentInitiated());
            dozer.map(
                    domainDonationPaymentInitiatedBatch.getPaymentInitiated(),
                    serviceDonationPaymentInitiated.getWebDonationPayments());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceDonationPaymentInitiated "
                            + serviceDonationPaymentInitiated
                                    .getWebDonationPayments());

        }
        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        boolean donationResult = false;
        try {
            LOGGER.debug("domainDonationPaymentInitiatedBatch = "
                    + domainDonationPaymentInitiatedBatch);
            LOGGER.debug("domainDonationPaymentInitiatedBatch = "
                    + domainDonationPaymentInitiatedBatch.getPaymentInitiated()
                            .size());
            logFetchTime("ProcessDonationPaymentInitiated Calling GLIS for batch: " + batchNumber);
            response = glisServiceLocator.getGlisPort()
                    .processDonationPaymentsInitiated(
                            serviceDonationPaymentInitiated);
            logFetchTime("ProcessDonationPaymentInitiated GLIS call over for batch: " + batchNumber);
        } catch (Exception exception) {
            LOGGER.error("Error (1) in ProcessDonationPaymentInitiated for Batch: " + batchNumber, exception);

            // Calling this service to reset the current status from pending to
            // Failure.
            logFetchTime("ProcessDonationPaymentInitiated Process GLIS call error for batch: " + batchNumber);
           // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
                errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed ::BatchServiceProxyImpl: processDonationPaymentsInitiated",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed ::BatchServiceProxyImpl: processDonationPaymentsInitiated",e);
            }
            logFetchTime("ProcessDonationPaymentInitiated Completed GLIS call error for batch: " + batchNumber);
            // Raise alert then re-throw to rollback transaction.

            try {
                raiseAlert("processDonationPaymentsInitiated",
                        "batchNumber "+ batchNumber, exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processDonationPaymentsInitiated",e);
            }
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: processDonationPaymentsInitiated");

        }

        logFetchTime("ProcessDonationPaymentInitiated Completed GLIS call for batch: " + batchNumber);
        if (response.isOk()) {
            // HunarC: Update the batch status to WAITING
            logFetchTime("ProcessDonationPaymentInitiated Send GLIS Event call for batch: " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    domainDonationPaymentInitiatedBatch
                                            .getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.WEB_DONATIONS_PAYMENT_INITIATED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            //com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in ProcessDonationPaymentInitiated for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.

                // HunarC: Update the batch status to FAILED
                String errorMessage = "Error calling G/L System (Is it unavailable?)";
                if (exception.getMessage() != null) {
                    errorMessage += exception.getMessage();
                }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: processEvent:processDonationPaymentsInitiated",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: processEvent:processDonationPaymentsInitiated",e);
                }

                try {
                    raiseAlert("processDonationPaymentsInitiated",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(LOGGER, "Batch Failed while Raising Alert:processDonationPaymentsInitiated",e);
                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processEvent:processDonationPaymentsInitiated");
            }
            logFetchTime("ProcessDonationPaymentInitiated Completed GLIS Event call for batch: " + batchNumber);

        } else {

            // HunarC: Set the batch status to FAILED
            logFetchTime("ProcessDonationPaymentInitiated Start GLIS rejection for batch: " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("processDonationPaymentsInitiated",
                        "batchNumber"+ batchNumber, response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while Raising Alert::BatchServiceProxyImpl:processDonationPaymentsInitiated",e);
            }
            logFetchTime("ProcessDonationPaymentInitiated Completed GLIS rejection for batch: " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running processDonationPaymentsInitiated. See alert log for details");
        }

        LOGGER.debug("ProcessDonationPaymentsInitiated after Glis webservice call response is OK");
        return true;
    }

    /** {@inheritDoc} */
    public boolean processDonationPaymentsCollected(
            DonationPaymentCollectedBatch donationPaymentCollectedBatch)
            throws GivingBatchException {

        com.virginmoney.glis.messages.DonationPaymentCollectedBatch serviceDonationPaymentCollected =
            new com.virginmoney.glis.messages.DonationPaymentCollectedBatch();

        String batchNumber = donationPaymentCollectedBatch.getBatch()
                .getBatchNumber();
        logFetchTime("processDonationPaymentsCollected inside BatchServiceProxyImpl - START. batch = " + batchNumber);

        serviceDonationPaymentCollected
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        donationPaymentCollectedBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceDonationPaymentCollected
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                donationPaymentCollectedBatch
                                        .getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(donationPaymentCollectedBatch
                .getWebDonationPayments())) {
            for (DonationPaymentCollected donationPaymentCollected : donationPaymentCollectedBatch
                    .getWebDonationPayments()) {

                serviceDonationPaymentCollected
                        .getWebDonationPayments()
                        .add(
                                (com.virginmoney.glis.messages.DonationPaymentCollected) dozer
                                        .map(
                                                donationPaymentCollected,
                                                com.virginmoney.glis.messages.DonationPaymentCollected.class));

            }
            LOGGER.debug("Before Dozer mapping Payment Collected is:"
                    + donationPaymentCollectedBatch.getWebDonationPayments());
            dozer.map(donationPaymentCollectedBatch.getWebDonationPayments(),
                    serviceDonationPaymentCollected.getWebDonationPayments());
            LOGGER.debug("After Dozer mapping Payment Collected is:"
                    + serviceDonationPaymentCollected.getWebDonationPayments());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceDonationPaymentCollected "
                            + serviceDonationPaymentCollected
                                    .getWebDonationPayments());
        }
        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        logFetchTime("ProcessDonationPaymentsCollected before Glis call for batch: " + batchNumber);

        try {
            LOGGER.info("donationPaymentCollectedBatch = "
                    + donationPaymentCollectedBatch);
            LOGGER.info("donationPaymentCollectedBatch = "
                    + donationPaymentCollectedBatch.getWebDonationPayments()
                            .size());

            response = glisServiceLocator.getGlisPort()
                    .processDonationPaymentsCollected(
                            serviceDonationPaymentCollected);
        logFetchTime("ProcessDonationPaymentsCollected after Glis call for batch: " + batchNumber);

        } catch (Exception exception) {
            LOGGER.error("Error (1) in ProcessDonationPaymentsCollected for Batch: " + batchNumber, exception);
            // Calling this service to reset the current status from pending to
            // Failure.
            logFetchTime("ProcessDonationPaymentsCollected process Glis Error for batch: " + batchNumber);
           // HunarC: Update the batch status to FAILED
            String erroMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
                erroMessage += exception.getMessage();
            }

            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, erroMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed ::BatchServiceProxyImpl: processDonationPaymentsCollected",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed ::BatchServiceProxyImpl: processDonationPaymentsCollected",e);
            }
            // Raise alert then re-throw to rollback transaction.
            try {
                raiseAlert("processDonationPaymentsCollected",
                        "batchNumber"+ batchNumber, exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processDonationPaymentsCollected",e);
            }

            logFetchTime("ProcessDonationPaymentsCollected finished process Glis Error for batch: " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed ::BatchServiceProxyImpl: processDonationPaymentsCollected");

        }
        LOGGER.info("Got response from GLIS: " + response);
        if (response.isOk()) {
            logFetchTime("ProcessDonationPaymentsCollected Send Glis Event for batch: " + batchNumber);
            // HunarC: Update the batch status to WAITING
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    donationPaymentCollectedBatch
                                            .getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.WEB_DONATIONS_PAYMENT_COLLECTED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                processEventResponse = glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in ProcessDonationPaymentsCollected for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
                String errorMessage = "Error calling G/L System (Is it unavailable?)";
                if (exception.getMessage() != null) {
                    errorMessage += exception.getMessage();
                }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed ::BatchServiceProxyImpl: processDonationPaymentsCollected",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed ::BatchServiceProxyImpl: processDonationPaymentsCollected",e);
                }

                try {
                    raiseAlert("processDonationPaymentsCollected",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processDonationPaymentsCollected",e);
                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed ::BatchServiceProxyImpl: processDonationPaymentsCollected");

            }

            logFetchTime("ProcessDonationPaymentsCollected Send Glis Event finished for batch: " + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("ProcessDonationPaymentsCollected Glis call rejection (1) for batch: " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("processDonationPaymentsCollected",
                        "batchNumber"+ batchNumber, response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processDonationPaymentsCollected",e);
            }
            logFetchTime("ProcessDonationPaymentsCollected Glis call rejection (2) for batch: " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running processDonationPaymentsCollected. See alert log for details");
        }
        logFetchTime("ProcessDonationPaymentsCollected Glis call completed for batch: " + batchNumber);

        return true;

    }

    /** {@inheritDoc} */
    public boolean processGiftAidReceived(
            GiftAidReceivedBatch giftAidReceivedBatch) throws GivingBatchException {

        com.virginmoney.glis.messages.GiftAidReceivedBatch serviceGiftAidReceived = new com.virginmoney.glis.messages.GiftAidReceivedBatch();
        String batchNumber = giftAidReceivedBatch.getBatch().getBatchNumber();
        logFetchTime("processGiftAidReceived inside BatchServiceProxyImpl - Start. batch = " + batchNumber);

        serviceGiftAidReceived
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        giftAidReceivedBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceGiftAidReceived
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                giftAidReceivedBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(giftAidReceivedBatch
                .getGiftAidReceived())) {
            for (GiftAidReceived giftAidReceived : giftAidReceivedBatch
                    .getGiftAidReceived()) {

                serviceGiftAidReceived
                        .getGiftAidReceived()
                        .add(
                                (com.virginmoney.glis.messages.GiftAidReceived) dozer
                                        .map(
                                                giftAidReceived,
                                                com.virginmoney.glis.messages.GiftAidReceived.class));

            }
            LOGGER.debug("Before Dozer mapping GiftAidReceived is:"
                    + giftAidReceivedBatch.getGiftAidReceived());
            dozer.map(giftAidReceivedBatch.getGiftAidReceived(),
                    serviceGiftAidReceived.getGiftAidReceived());
            LOGGER.debug("After Dozer mapping GiftAidReceived is:"
                    + serviceGiftAidReceived.getGiftAidReceived());
        }
        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        logFetchTime("processGiftAidReceived before Glis webservice call is Called for batch: " + batchNumber);

        try {
            LOGGER.info("giftAidClaimedBatch = " + giftAidReceivedBatch);
            LOGGER.info("giftAidClaimedBatch = "
                    + giftAidReceivedBatch.getGiftAidReceived().size());

            response = glisServiceLocator.getGlisPort().processGiftAidReceived(
                    serviceGiftAidReceived);
            logFetchTime("processGiftAidReceived after Glis call for batch: " + batchNumber);
        } catch (Exception exception) {
            LOGGER.error("Error (1) in processGiftAidReceived for Batch: " + batchNumber, exception);
            // Calling this service to reset the current status from pending to
            // Failure.

           // HunarC: Update the batch status to FAILED
            logFetchTime("processGiftAidReceived process Glis call error for batch: " + batchNumber);
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidReceived",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidReceived",e);
            }
            // Raise alert then re-throw to rollback transaction.

            try {
                raiseAlert("processGiftAidReceived", "batchNumber"+ batchNumber,
                        exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising Alert:BatchServiceProxyImpl: processGiftAidReceived",e);
            }
            logFetchTime("processGiftAidReceived completed Glis call error for batch: " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidReceived");
        }
        if (response.isOk()) {
            logFetchTime("processGiftAidReceived Start Glis Event call for batch: " + batchNumber);
            // HunarC: Update the batch status to WAITING
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    giftAidReceivedBatch.getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.GIFT_AID_RECEIVED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            //com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {

                LOGGER.error("Error (2) in processGiftAidReceived for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidReceived",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidReceived",e);
                }

                try {
                    raiseAlert("processGiftAidReceived",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising Alert:BatchServiceProxyImpl: processGiftAidReceived",e);

                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidReceived");
            }
            logFetchTime("processGiftAidReceived Completed Glis Event call for batch: " + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("processGiftAidReceived process Glis rejection (1) for batch: " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("processGiftAidReceived", "batchNumber"+ batchNumber,
                        response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising Alert:BatchServiceProxyImpl: processGiftAidReceived",e);
            }
            logFetchTime("processGiftAidReceived process Glis rejection (2) for batch: " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running processGiftAidReceived. See alert log for details");
        }

        logFetchTime("processGiftAidReceived inside BatchServiceProxyImpl - Completed. batch = " + batchNumber);
        return true;
    }

    /** {@inheritDoc} */
    public boolean processGiftAidClaimed(GiftAidClaimedBatch giftAidClaimedBatch)
            throws GivingBatchException {

        com.virginmoney.glis.messages.GiftAidClaimedBatch serviceGiftAidClaimed =
                new com.virginmoney.glis.messages.GiftAidClaimedBatch();
        String batchNumber = giftAidClaimedBatch.getBatch().getBatchNumber();
        logFetchTime("processGiftAidClaimed inside BatchServiceProxyImpl - Start. batch = " + batchNumber);

        serviceGiftAidClaimed
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        giftAidClaimedBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceGiftAidClaimed
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                giftAidClaimedBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(giftAidClaimedBatch.getGiftAidClaimed())) {

            for (Transaction transaction : giftAidClaimedBatch
                    .getGiftAidClaimed()) {
                LOGGER.debug("processGiftAidClaimed-Invoice/Amount: " + transaction.getInvoiceNumber() + "/" 
                        + transaction.getAmount());
                serviceGiftAidClaimed
                        .getGiftAidClaimed()
                        .add(
                                (com.virginmoney.glis.messages.Transaction) dozer
                                        .map(
                                                transaction,
                                                com.virginmoney.glis.messages.Transaction.class));

            }
            LOGGER.debug("Before Dozer mapping GiftAidClaimed is:" + giftAidClaimedBatch.getGiftAidClaimed());
            dozer.map(giftAidClaimedBatch.getGiftAidClaimed(), serviceGiftAidClaimed.getGiftAidClaimed());
            LOGGER.debug("After Dozer mapping GiftAidClaimed is:" + serviceGiftAidClaimed.getGiftAidClaimed());
        }
        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        logFetchTime("processGiftAidClaimed before Glis webservice call is Called. batch = " + batchNumber);

        try {
            LOGGER.debug("giftAidClaimedBatch = " + giftAidClaimedBatch.getGiftAidClaimed().size());

            response = glisServiceLocator.getGlisPort().processGiftAidClaimed(
                    serviceGiftAidClaimed);
            logFetchTime("processGiftAidClaimed after Glis webservice call is Called. Batch = " + batchNumber);
        } catch (Exception exception) {
            LOGGER.error("Error (1) in processGiftAidClaimed for Batch: " + batchNumber, exception);
            // Calling this service to reset the current status from pending to
            // Failure.

           // HunarC: Update the batch status to FAILED
            logFetchTime("processGiftAidClaimed process Glis call Error. Batch = " + batchNumber);
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidClaimed",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidClaimed",e);
            }
            // Raise alert then re-throw to rollback transaction.

            try {
                raiseAlert("processGiftAidClaimed", "batchNumber"+ batchNumber,
                        exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert :BatchServiceProxyImpl: processGiftAidClaimed",e);
            }
            logFetchTime("processGiftAidClaimed processed Glis call Error. Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidClaimed");
        }
        if (response.isOk()) {
            // HunarC: Update the batch status to WAITING
            logFetchTime("processGiftAidClaimed process Glis Event call. Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    giftAidClaimedBatch.getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.GIFT_AID_CLAIMED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                processEventResponse = glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in processGiftAidClaimed for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
                String errorMessage = "Error calling G/L System (Is it unavailable?)";
                if (exception.getMessage() != null) {
                   errorMessage += exception.getMessage();
                }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidClaimed",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidClaimed",e);
                }

                try {
                    raiseAlert("processGiftAidClaimed",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processGiftAidClaimed",e);
                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidClaimed");
            }
            logFetchTime("processGiftAidClaimed finished Glis Event call. Batch = " + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("processGiftAidClaimed process Glis rejection (1). Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("processGiftAidClaimed", "batchNumber"+ batchNumber,
                        response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processGiftAidClaimed",e);
            }
            logFetchTime("processGiftAidClaimed process Glis rejection (2). Batch = " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running processGiftAidClaimed. See alert log for details");
        }

        logFetchTime("processGiftAidClaimed inside BatchServiceProxyImpl - End. batch = " + batchNumber);
        return true;
    }

    /** {@inheritDoc} */
    public boolean processTransactionChargePaid(
            TransactionChargePaidBatch transactionChargePaidBatch)
            throws GivingBatchException {

        com.virginmoney.glis.messages.TransactionChargePaidBatch serviceTransactionChargePaid = new com.virginmoney.glis.messages.TransactionChargePaidBatch();
        String batchNumber = transactionChargePaidBatch.getBatch()
                .getBatchNumber();
        logFetchTime("processTransactionChargePaid inside BatchServiceProxyImpl - Start. Batch = " + batchNumber);

        serviceTransactionChargePaid
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        transactionChargePaidBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceTransactionChargePaid
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                transactionChargePaidBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(transactionChargePaidBatch
                .getTransactionCharge())) {
            for (TransactionChargePaid transactionChargePaid : transactionChargePaidBatch
                    .getTransactionCharge()) {
                serviceTransactionChargePaid
                        .getTransactionCharge()
                        .add(
                                (com.virginmoney.glis.messages.TransactionChargePaid) dozer
                                        .map(
                                                transactionChargePaid,
                                                com.virginmoney.glis.messages.TransactionChargePaid.class));

            }
            LOGGER.debug("Before Dozer mapping TransactionChargePaid is:"
                    + transactionChargePaidBatch.getTransactionCharge());
            dozer.map(transactionChargePaidBatch.getTransactionCharge(),
                    serviceTransactionChargePaid.getTransactionCharge());
            LOGGER.debug("After Dozer mapping TransactionChargePaid is:"
                    + serviceTransactionChargePaid.getTransactionCharge());
        }

        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        logFetchTime("processTransactionChargePaid before Glis webservice call is Called. Batch = " + batchNumber);

        try {
            LOGGER.info("transactionChargePaidBatch = "
                    + transactionChargePaidBatch);
            LOGGER.info("transactionChargePaidBatch = "
                    + transactionChargePaidBatch.getTransactionCharge().size());
            response = glisServiceLocator.getGlisPort()
                    .processTransactionChargePaid(serviceTransactionChargePaid);
            logFetchTime("processTransactionChargePaid after Glis webservice call. Batch = " + batchNumber);
        } catch (Exception exception) {
            LOGGER.error("Error (1) in processTransactionChargePaid for Batch: " + batchNumber, exception);
            // Calling this service to reset the current status from pending to
            // Failure.
            logFetchTime("processTransactionChargePaid Glis webservice error (1). Batch = " + batchNumber);
           // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransactionChargePaid",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransactionChargePaid",e);

            }
            // Raise alert then re-throw to rollback transaction.
            try {
                raiseAlert("processTransactionChargePaid",
                        "batchNumber"+ batchNumber, exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processTransactionChargePaid",e);

            }
            logFetchTime("processTransactionChargePaid Glis webservice error (2). Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: processTransactionChargePaid");
        }
        LOGGER.info("Got response from GLIS: " + response);
        if (response.isOk()) {
            // HunarC: Update the batch status to WAITING
            logFetchTime("processTransactionChargePaid Glis Event. Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    transactionChargePaidBatch
                                            .getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.TRANSACTION_CHARGE_PAID);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                processEventResponse = glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in processTransactionChargePaid for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransactionChargePaid",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransactionChargePaid",e);
                }

                try {
                    raiseAlert("processTransactionChargePaid",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processTransactionChargePaid",e);
                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processTransactionChargePaid");

            }
            logFetchTime("processTransactionChargePaid Glis Event completed. Batch = " + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("processTransactionChargePaid Glis rejection (1). Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("processTransactionChargePaid",
                        "batchNumber"+ batchNumber, response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processTransactionChargePaid",e);

            }
            logFetchTime("processTransactionChargePaid Glis rejection (2). Batch = " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running processTransactionChargePaid. See alert log for details");
        }

        logFetchTime("processTransactionChargePaid inside BatchServiceProxyImpl - Completed. Batch = " + batchNumber);
        return true;
    }

    /** {@inheritDoc} */
    public boolean processTransactionFeesPaid(
            TransactionFeePaidBatch transactionFeePaidBatch) throws GivingBatchException {

        com.virginmoney.glis.messages.TransactionFeePaidBatch serviceTransactionFeePaidBatch = 
            new com.virginmoney.glis.messages.TransactionFeePaidBatch();

        String batchNumber = transactionFeePaidBatch.getBatch()
                .getBatchNumber();
        logFetchTime("processTransactionFeesPaid inside BatchServiceProxyImpl - Start. Batch = " + batchNumber);

        serviceTransactionFeePaidBatch
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        transactionFeePaidBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceTransactionFeePaidBatch
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                transactionFeePaidBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(transactionFeePaidBatch
                .getTransactionFee())) {
            for (TransactionFeePaid transactionFeePaid : transactionFeePaidBatch
                    .getTransactionFee()) {
                serviceTransactionFeePaidBatch
                        .getTransactionFee()
                        .add(
                                (com.virginmoney.glis.messages.TransactionFeePaid) dozer
                                        .map(
                                                transactionFeePaid,
                                                com.virginmoney.glis.messages.TransactionFeePaid.class));

            }
            LOGGER.debug("Before Dozer mapping processTransactionFeesPaid is:"
                    + transactionFeePaidBatch.getTransactionFee());
            dozer.map(transactionFeePaidBatch.getTransactionFee(),
                    serviceTransactionFeePaidBatch.getTransactionFee());
            LOGGER.debug("After Dozer mapping processTransactionFeesPaid is:"
                    + serviceTransactionFeePaidBatch.getTransactionFee());
        }

        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        logFetchTime("processTransactionFeesPaid before Glis webservice is Called. Batch = " + batchNumber);

        try {
            LOGGER.info("transactionFeePaidBatch = " + transactionFeePaidBatch);
            LOGGER.info("transactionFeePaidBatch = "
                    + transactionFeePaidBatch.getTransactionFee().size());

            response = glisServiceLocator.getGlisPort()
                    .processTransactionFeesPaid(serviceTransactionFeePaidBatch);
            logFetchTime("processTransactionFeesPaid After Glis webservice is Called. Batch = " + batchNumber);
        } catch (Exception exception) {
            LOGGER.error("Error (1) in processTransactionFeesPaid for Batch: " + batchNumber, exception);
            // Calling this service to reset the current status from pending to
            // Failure.
            logFetchTime("processTransactionFeesPaid Glis webservice Error (1). Batch = " + batchNumber);
           // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransactionFeesPaid",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransactionFeesPaid",e);
            }
            // Raise alert then re-throw to rollback transaction.
            try {
                raiseAlert("processTransactionFeesPaid",
                        "batchNumber"+ batchNumber, exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl while raising alert: processTransactionFeesPaid",e);
            }
            logFetchTime("processTransactionFeesPaid Glis webservice Error (2). Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransactionFeesPaid");
        }

        if (response.isOk()) {
            // HunarC: Update the batch status to WAITING
            logFetchTime("processTransactionFeesPaid Glis webservice Event call (1). Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    transactionFeePaidBatch.getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.TRANSACTION_FEE_PAID);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            //com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in processTransactionFeesPaid for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransactionFeesPaid",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransactionFeesPaid",e);
                }

                try {
                    raiseAlert("processTransactionFeesPaid",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processTransactionFeesPaid",e);
                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransactionFeesPaid");
            }
            logFetchTime("processTransactionFeesPaid Glis webservice Event call (2). Batch = " + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("processTransactionFeesPaid Glis webservice Rejection (1). Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("processTransactionFeesPaid",
                        "batchNumber"+ batchNumber, response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processTransactionFeesPaid",e);
            }
            logFetchTime("processTransactionFeesPaid Glis webservice Rejection (2). Batch = " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running processTransactionFeesPaid. See alert log for details");
        }

        logFetchTime("processTransactionFeesPaid inside BatchServiceProxyImpl - Completed. Batch = " + batchNumber);
        return true;
    }

    /** {@inheritDoc} */
    public boolean registerCharities(CharitiesBatch charitiesBatch)
            throws GivingBatchException {

        com.virginmoney.glis.messages.CharitiesBatch serviceCharitiesBatch = new com.virginmoney.glis.messages.CharitiesBatch();

        String batchNumber = charitiesBatch.getBatch().getBatchNumber();
        logFetchTime("registerCharities inside BatchServiceProxyImpl - Start. Batch = " + batchNumber);

        serviceCharitiesBatch
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        charitiesBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceCharitiesBatch
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                charitiesBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));
        if (CollectionUtils.isNotEmpty(charitiesBatch.getCharities())) {

            for (Charities domainCharitiesObject : charitiesBatch
                    .getCharities()) {
                serviceCharitiesBatch.getCharity().add(
                        (com.virginmoney.glis.messages.Charity) dozer.map(
                                domainCharitiesObject,
                                com.virginmoney.glis.messages.Charity.class));

            }
        }
        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        logFetchTime("registerCharities before Glis webservice call is Called. Batch = " + batchNumber);
        try {
            LOGGER.debug("serviceCharitiesBatch = " + serviceCharitiesBatch);
            LOGGER.debug("serviceCharitiesBatch = "
                    + serviceCharitiesBatch.getCharity().size());
            response = glisServiceLocator.getGlisPort().registerCharities(
                    serviceCharitiesBatch);
            logFetchTime("registerCharities After Glis webservice call. Batch = " + batchNumber);

        } catch (Exception exception) {
            LOGGER.error("Error (1) in registerCharities for Batch: " + batchNumber, exception);
            logFetchTime("registerCharities After Glis webservice Error (1). Batch = " + batchNumber);
            // Calling this service to reset the current status from pending to
            // Failure.

           // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            request.setErrorMessage("Exception (Service call) - See batch header for details.");
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: registerCharities",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: registerCharities",e);
            }
            // Raise alert then re-throw to rollback transaction.
            try {
                raiseAlert("registerCharities", "batchNumber"+ batchNumber, exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: registerCharities",e);
            }

            logFetchTime("registerCharities After Glis webservice Error (2). Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: registerCharities");
        }

        LOGGER.info("Got response from GLIS: " + response);

        if (response.isOk()) {
            logFetchTime("registerCharities Glis webservice Event call (1). Batch = " + batchNumber);
            // HunarC: Update the batch status to FAILED
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    charitiesBatch.getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.REGISTER_CHARITY);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                processEventResponse = glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in registerCharities for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.

                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                request.setErrorMessage("Exception (Event call) - See batch header for details.");
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: registerCharities",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: registerCharities",e);
                }

                try {
                    raiseAlert("registerCharities", "batchNumber"+ batchNumber,
                            exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: registerCharities",e);
                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: registerCharities");
            }
            logFetchTime("registerCharities Glis webservice Event call (2). Batch = " + batchNumber);

        } else {
            // Raise alert then throw exception to rollback transaction
            logFetchTime("registerCharities Glis webservice Rejection (1). Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            try {
                raiseAlert("registerCharities", "batchNumber"+ batchNumber,
                        response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert :BatchServiceProxyImpl: registerCharities",e);
            }
            logFetchTime("registerCharities Glis webservice Rejection (2). Batch = " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running registerCharities. See alert log for details");
        }
        logFetchTime("registerCharities inside BatchServiceProxyImpl - Completed. Batch = " + batchNumber);
        return true;
    }

    /** {@inheritDoc} */
    public boolean processEventRegistrationPaymentsCollected(
            DonationPaymentCollectedBatch donationPaymentCollectedBatch)
            throws GivingBatchException {

        com.virginmoney.glis.messages.DonationPaymentCollectedBatch serviceDonationPaymentCollected =
            new com.virginmoney.glis.messages.DonationPaymentCollectedBatch();
        String batchNumber = donationPaymentCollectedBatch.getBatch()
                .getBatchNumber();
        logFetchTime("processEventRegistrationPaymentsCollected BatchServiceProxyImpl - Start. batch = " + batchNumber);

        serviceDonationPaymentCollected
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        donationPaymentCollectedBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceDonationPaymentCollected
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                donationPaymentCollectedBatch
                                        .getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(donationPaymentCollectedBatch
                .getWebDonationPayments())) {
            for (DonationPaymentCollected donationPaymentCollected : donationPaymentCollectedBatch
                    .getWebDonationPayments()) {

                serviceDonationPaymentCollected
                        .getWebDonationPayments()
                        .add(
                                (com.virginmoney.glis.messages.DonationPaymentCollected) dozer
                                        .map(
                                                donationPaymentCollected,
                                                com.virginmoney.glis.messages.DonationPaymentCollected.class));

            }
            LOGGER.debug("Before Dozer mapping Payment Collected is:"
                    + donationPaymentCollectedBatch.getWebDonationPayments());
            dozer.map(donationPaymentCollectedBatch.getWebDonationPayments(),
                    serviceDonationPaymentCollected.getWebDonationPayments());
            LOGGER.debug("After Dozer mapping Payment Collected is:"
                    + serviceDonationPaymentCollected.getWebDonationPayments());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceDonationPaymentCollected "
                            + serviceDonationPaymentCollected
                                    .getWebDonationPayments());
        }

        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        logFetchTime("processEventRegistrationPaymentsCollected before Glis webservice call Batch = " + batchNumber);

        try {
            LOGGER.info("eventRegistrationDonationPaymentCollectedBatch = "
                    + donationPaymentCollectedBatch);
            LOGGER.info("eventRegistrationDonationPaymentCollectedBatch = "
                    + donationPaymentCollectedBatch.getWebDonationPayments()
                            .size());
            response = glisServiceLocator.getGlisPort()
                    .processEventRegistrationPaymentsCollected(
                            serviceDonationPaymentCollected);
            logFetchTime("processEventRegistrationPaymentsCollected After Glis webservice call Batch = " + batchNumber);
        } catch (Exception exception) {
            LOGGER.error("Error (1) in processEventRegistrationPaymentsCollected for Batch: " + batchNumber, exception);
            // Calling this service to reset the current status from pending to
            // Failure.
            logFetchTime("processEventRegistrationPaymentsCollected Glis webservice Error (1) Batch = " + batchNumber);
           // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processEventRegistrationPaymentsCollected",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processEventRegistrationPaymentsCollected",e);

            }
            // Raise alert then re-throw to rollback transaction.

            try {
                raiseAlert("processEventRegistrationPaymentsCollected",
                        "batchNumber"+ batchNumber, exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert :BatchServiceProxyImpl: processEventRegistrationPaymentsCollected",e);

            }
            logFetchTime("processEventRegistrationPaymentsCollected Glis webservice Error (2) Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed  :BatchServiceProxyImpl: processEventRegistrationPaymentsCollected");

            
        }
        LOGGER.info("Got response from GLIS: " + response);

        if (response.isOk()) {
            // HunarC: Update the batch status to WAITING
            logFetchTime("processEventRegistrationPaymentsCollected Glis webservice Event call (1) Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    donationPaymentCollectedBatch
                                            .getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.WEB_EVENT_REGISTRATION_FEE_PAYMENT_COLLECTED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                processEventResponse = glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in processEventRegistrationPaymentsCollected for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: processEventRegistrationPaymentsCollected",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: processEventRegistrationPaymentsCollected",e);
                }

                try {
                    raiseAlert("processEventRegistrationPaymentsCollected",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processEventRegistrationPaymentsCollected",e);

                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processEventRegistrationPaymentsCollected");

            }
            logFetchTime("processEventRegistrationPaymentsCollected Glis webservice Event call (2) Batch = " + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("processEventRegistrationPaymentsCollected Glis webservice Rejection (1) Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("processEventRegistrationPaymentsCollected",
                        "batchNumber"+ batchNumber, response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processEventRegistrationPaymentsCollected",e);

            }
            logFetchTime("processEventRegistrationPaymentsCollected Glis webservice Rejection (2) Batch = " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running processEventRegistrationPaymentsCollected. See alert log for details");
        }

        logFetchTime("processEventRegistrationPaymentsCollected BatchServiceProxyImpl - Completed. batch = " + batchNumber);
        return true;

    }

    /** {@inheritDoc} */
    public boolean processRegularDonationPaymentsCollected(
            DonationPaymentCollectedBatch donationPaymentCollectedBatch)
            throws GivingBatchException {

        com.virginmoney.glis.messages.DonationPaymentCollectedBatch serviceDonationPaymentCollected = new com.virginmoney.glis.messages.DonationPaymentCollectedBatch();

        String batchNumber = donationPaymentCollectedBatch.getBatch()
                .getBatchNumber();
        logFetchTime("processRegularDonationPaymentsCollected BatchServiceProxyImpl - Start. Batch = " + batchNumber);

        serviceDonationPaymentCollected
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        donationPaymentCollectedBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceDonationPaymentCollected
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                donationPaymentCollectedBatch
                                        .getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(donationPaymentCollectedBatch
                .getWebDonationPayments())) {
            for (DonationPaymentCollected donationPaymentCollected : donationPaymentCollectedBatch
                    .getWebDonationPayments()) {

                serviceDonationPaymentCollected
                        .getWebDonationPayments()
                        .add(
                                (com.virginmoney.glis.messages.DonationPaymentCollected) dozer
                                        .map(
                                                donationPaymentCollected,
                                                com.virginmoney.glis.messages.DonationPaymentCollected.class));

            }
            LOGGER.debug("Before Dozer mapping Payment Collected is:"
                    + donationPaymentCollectedBatch.getWebDonationPayments());
            dozer.map(donationPaymentCollectedBatch.getWebDonationPayments(),
                    serviceDonationPaymentCollected.getWebDonationPayments());
            LOGGER.debug("After Dozer mapping Payment Collected is:"
                    + serviceDonationPaymentCollected.getWebDonationPayments());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceDonationPaymentCollected "
                            + serviceDonationPaymentCollected
                                    .getWebDonationPayments());
        }

        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        boolean donationResult = false;
        logFetchTime("processRegularDonationPaymentsCollected before Glis webservice call. batch = " + batchNumber);
        try {
            LOGGER.info("regularDonationPaymentCollectedBatch = "
                    + donationPaymentCollectedBatch);
            LOGGER.info("regularDonationPaymentCollectedBatch = "
                    + donationPaymentCollectedBatch.getWebDonationPayments()
                            .size());

            response = glisServiceLocator.getGlisPort()
                    .processRegularDonationPaymentsCollected(
                            serviceDonationPaymentCollected);
            logFetchTime("processRegularDonationPaymentsCollected after Glis webservice call. batch = " + batchNumber);
            donationResult = response.isOk();
        } catch (Exception exception) {
            LOGGER.error("Error (1) in processRegularDonationPaymentsCollected for Batch: " + batchNumber, exception);
            // HunarC: Update the batch status to FAILED
            logFetchTime("processRegularDonationPaymentsCollected Glis webservice error (1). batch = " + batchNumber);
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processRegularDonationPaymentsCollected",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processRegularDonationPaymentsCollected",e);
            }
            // Raise alert then re-throw to rollback transaction.

            try {
                raiseAlert("processRegularDonationPaymentsCollected",
                        "batchNumber"+ batchNumber, exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processRegularDonationPaymentsCollected",e);
            }
            logFetchTime("processRegularDonationPaymentsCollected Glis webservice error (2). batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: processRegularDonationPaymentsCollected");

        }
        LOGGER.info("Got response from GLIS: " + response);
        if (response.isOk()) {
            // HunarC: Update the batch status to WAITING
            logFetchTime("processRegularDonationPaymentsCollected Glis Event call (1). batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    donationPaymentCollectedBatch
                                            .getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.WEB_REGULAR_DONATIONS_PAYMENT_COLLECTED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                processEventResponse = glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in processRegularDonationPaymentsCollected for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: processRegularDonationPaymentsCollected",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: processRegularDonationPaymentsCollected",e);
                }

                try {
                    raiseAlert("paymentCollected", "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processRegularDonationPaymentsCollected",e);
                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processRegularDonationPaymentsCollected");
            }
            logFetchTime("processRegularDonationPaymentsCollected Glis Event call (2). batch = " + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("processRegularDonationPaymentsCollected Glis Rejection (1). batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("processRegularDonationPaymentsCollected",
                        "batchNumber"+ batchNumber, response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processRegularDonationPaymentsCollected",e);
            }
            logFetchTime("processRegularDonationPaymentsCollected Glis Rejection (2). batch = " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running processRegularDonationPaymentsCollected. See alert log for details");
        }

        logFetchTime("processRegularDonationPaymentsCollected BatchServiceProxyImpl - Completed. Batch = " + batchNumber);
        return donationResult;

    }

    /**
     * Send new charity registration fees to GLIS.
     * 
     * @param domainRegistrationFeesBatch the batch to send
     * 
     * @return boolean always true - throws exception otherwise
     * 
     * @throws Exception with appropriate message
     */
    public boolean createRegistrationFees(
            RegistrationFeesBatch domainRegistrationFeesBatch) throws GivingBatchException {

        com.virginmoney.glis.messages.RegistrationFeesBatch serviceRegistrationFeesBatch = new com.virginmoney.glis.messages.RegistrationFeesBatch();
        String batchNumber = domainRegistrationFeesBatch.getBatch()
                .getBatchNumber();
        logFetchTime("createRegistrationFees inside BatchServiceProxyImpl - Start. batch = " + batchNumber);

        serviceRegistrationFeesBatch
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        domainRegistrationFeesBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceRegistrationFeesBatch
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                domainRegistrationFeesBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(domainRegistrationFeesBatch
                .getRegistrationFee())) {
            for (RegistrationFee domainRegistrationFee : domainRegistrationFeesBatch
                    .getRegistrationFee()) {

                serviceRegistrationFeesBatch
                        .getRegistrationFee()
                        .add(
                                (com.virginmoney.glis.messages.RegistrationFee) dozer
                                        .map(
                                                domainRegistrationFee,
                                                com.virginmoney.glis.messages.RegistrationFee.class));
            }
            LOGGER.debug("Before Dozer mapping domain RegistrationFee is:"
                    + domainRegistrationFeesBatch.getRegistrationFee());
            dozer.map(domainRegistrationFeesBatch.getRegistrationFee(),
                    serviceRegistrationFeesBatch.getRegistrationFee());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceRegistrationFeesBatch "
                            + serviceRegistrationFeesBatch.getRegistrationFee());

        }

        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        boolean donationResult = false;
        logFetchTime("CreateRegistrationFees before Glis webservice call Batch = " + batchNumber);
        try {
            response = glisServiceLocator.getGlisPort().createRegistrationFees(
                    serviceRegistrationFeesBatch);
        } catch (Exception exception) {
            logFetchTime("CreateRegistrationFees Glis Error (1) Batch = " + batchNumber);
            LOGGER.error("Error (1) in CreateRegistrationFees for Batch: " + batchNumber, exception);
            // If GLIS service fails than we need to rollback the current status
            // in batch entity table.
           // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: createRegistrationFees",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: createRegistrationFees",e);
            }

            try {
                raiseAlert("createRegistrationFees", "batchNumber"+ batchNumber,
                        exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: createRegistrationFees",e);

            }

            logFetchTime("CreateRegistrationFees Glis Error (2) Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: createRegistrationFees");

        }

        if (response.isOk()) {
            // HunarC: Update the batch status to WAITING
            logFetchTime("CreateRegistrationFees Glis Event call (1) Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    domainRegistrationFeesBatch
                                            .getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.CHARITY_REGISTRATION_FEE_PAYMENT_INITIATED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            //com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;
            try {
                glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in CreateRegistrationFees for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: createRegistrationFees",e);

                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: createRegistrationFees",e);

                }

                try {
                    raiseAlert("CreateRegistrationFees",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: createRegistrationFees",e);

                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: createRegistrationFees");

            }
            logFetchTime("CreateRegistrationFees Glis Event call (2) Batch = " + batchNumber);
        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("CreateRegistrationFees Glis Rejection (1) Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("CreateRegistrationFees", "batchNumber"+ batchNumber,
                        response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: createRegistrationFees",e);

            }
            logFetchTime("CreateRegistrationFees Glis Rejection (2) Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: createRegistrationFees");
        }

        if (!response.isOk()) {
            try {
                raiseAlert("createRegistrationFees", "batchNumber"+ batchNumber,
                        response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: createRegistrationFees",e);

            }
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: createRegistrationFees");
        }
        donationResult = response.isOk();
        logFetchTime("createRegistrationFees inside BatchServiceProxyImpl - Completed. batch = " + batchNumber);
        return donationResult;
    }

    /**
     * Send transition-relief records to GLIS.
     * 
     * @param transitionalReliefClaimedBatch the batch data to send
     * 
     * @return boolean always true - throws exception otherwise
     * 
     * @throws Exception with appropriate message
     */
    public boolean transitionalReliefClaimed(
            TransitionalReliefClaimedBatch transitionalReliefClaimedBatch)
            throws GivingBatchException {

        com.virginmoney.glis.messages.TransitionalReliefClaimedBatch serviceTransitionalReliefClaimedBatch = new com.virginmoney.glis.messages.TransitionalReliefClaimedBatch();

        String batchNumber = transitionalReliefClaimedBatch.getBatch()
                .getBatchNumber();
        logFetchTime("transitionalReliefClaimed inside BatchServiceProxyImpl - Start. Batch = " + batchNumber);

        serviceTransitionalReliefClaimedBatch
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        transitionalReliefClaimedBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));

        serviceTransitionalReliefClaimedBatch
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                transitionalReliefClaimedBatch
                                        .getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(transitionalReliefClaimedBatch
                .getTransitionalReliefClaimed())) {
            for (TransitionalReliefClaimed domainTransitionalReliefClaimed : transitionalReliefClaimedBatch
                    .getTransitionalReliefClaimed()) {

                serviceTransitionalReliefClaimedBatch
                        .getTransitionalReliefClaimed()
                        .add(
                                (com.virginmoney.glis.messages.TransitionalReliefClaimed) dozer
                                        .map(
                                                domainTransitionalReliefClaimed,
                                                com.virginmoney.glis.messages.TransitionalReliefClaimed.class));
            }
            LOGGER
                    .debug("Before Dozer mapping domain TransitionalReliefClaimed is:"
                            + transitionalReliefClaimedBatch
                                    .getTransitionalReliefClaimed());
            dozer.map(transitionalReliefClaimedBatch
                    .getTransitionalReliefClaimed(),
                    serviceTransitionalReliefClaimedBatch
                            .getTransitionalReliefClaimed());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceTransitionalReliefClaimed is "
                            + serviceTransitionalReliefClaimedBatch
                                    .getTransitionalReliefClaimed());

        }

        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;

        boolean donationResult = false;
        logFetchTime("TransitionalReliefClaimed before Glis webservice call Batch = " + batchNumber);
        try {
            LOGGER.info("transitionalReliefClaimedBatch = "
                    + transitionalReliefClaimedBatch);
            LOGGER.info("transitionalReliefClaimedBatch = "
                    + transitionalReliefClaimedBatch
                            .getTransitionalReliefClaimed().size());
            response = glisServiceLocator.getGlisPort()
                    .processTransitionalReliefClaimed(
                            serviceTransitionalReliefClaimedBatch);
            logFetchTime("TransitionalReliefClaimed after Glis webservice call Batch = " + batchNumber);
        } catch (Exception exception) {
            // Calling this service to reset the current status from pending to
            // Failure.
            logFetchTime("TransitionalReliefClaimed Glis webservice error (1) Batch = " + batchNumber);
            LOGGER.error("Error (1) in TransitionalReliefClaimed for Batch: " + batchNumber, exception);
           // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransitionalReliefClaimed",e);

            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransitionalReliefClaimed",e);

            }
            // Raise alert then re-throw to rollback transaction.

            try {
                raiseAlert("transitionalReliefClaimed",
                        "Where do I get Job id from", exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed:BatchServiceProxyImpl while raising alert: processTransitionalReliefClaimed",e);

            }
            logFetchTime("TransitionalReliefClaimed Glis webservice error (2) Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransitionalReliefClaimed");
        }
        LOGGER.info("Got response from GLIS: " + response);

        if (response.isOk()) {
            logFetchTime("TransitionalReliefClaimed Glis Event call (1) Batch = " + batchNumber);
            // HunarC: Update the batch status to WAITING
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    transitionalReliefClaimedBatch
                                            .getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.TRANSITIONAL_RELIEF_CLAIMED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                processEventResponse = glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in TransitionalReliefClaimed for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransitionalReliefClaimed",e);

                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed:BatchServiceProxyImpl: processTransitionalReliefClaimed",e);

                }

                try {
                    raiseAlert("processTransitionalReliefClaimed",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processTransitionalReliefClaimed",e);

                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: processTransitionalReliefClaimed");

            }
            logFetchTime("TransitionalReliefClaimed Glis Event call (2) Batch = " + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("TransitionalReliefClaimed Glis Rejection (1) Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("transitionalReliefClaimed",
                        "batchNumber"+ batchNumber, response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processTransitionalReliefClaimed",e);

            }
            logFetchTime("TransitionalReliefClaimed Glis Rejection (2) Batch = " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running processTransactionChargePaid. See alert log for details");
        }

        donationResult = response.isOk();
        logFetchTime("transitionalReliefClaimed inside BatchServiceProxyImpl - Completed. Batch = " + batchNumber);
        return donationResult;
    }

    /**
     * Send transition-relief records to GLIS.
     * 
     * @param transitionalReliefReceivedBatch the batch data to send
     * 
     * @return boolean always true - throws exception otherwise
     * 
     * @throws Exception with appropriate message
     */
    public boolean transitionalReliefReceived(
            TransitionalReliefReceivedBatch transitionalReliefReceivedBatch)
             {

        com.virginmoney.glis.messages.TransitionalReliefReceivedBatch serviceTransitionalReliefReceivedBatch =
            new com.virginmoney.glis.messages.TransitionalReliefReceivedBatch();

        String batchNumber = transitionalReliefReceivedBatch.getBatch()
                .getBatchNumber();
        logFetchTime("transitionalReliefReceived inside BatchServiceProxyImpl - Start. batch = " + batchNumber);

        serviceTransitionalReliefReceivedBatch
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        transitionalReliefReceivedBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));

        serviceTransitionalReliefReceivedBatch
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                transitionalReliefReceivedBatch
                                        .getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(transitionalReliefReceivedBatch
                .getTransitionalReliefReceived())) {
            for (TransitionalReliefReceived domainTransitionalReliefReceived : transitionalReliefReceivedBatch
                    .getTransitionalReliefReceived()) {

                serviceTransitionalReliefReceivedBatch
                        .getTransitionalReliefReceived()
                        .add(
                                (com.virginmoney.glis.messages.TransitionalReliefReceived) dozer
                                        .map(
                                                domainTransitionalReliefReceived,
                                                com.virginmoney.glis.messages.TransitionalReliefReceived.class));
            }
            LOGGER
                    .debug("Before Dozer mapping domain TransitionalReliefReceived is:"
                            + transitionalReliefReceivedBatch
                                    .getTransitionalReliefReceived());
            dozer.map(transitionalReliefReceivedBatch
                    .getTransitionalReliefReceived(),
                    serviceTransitionalReliefReceivedBatch
                            .getTransitionalReliefReceived());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceTransitionalReliefReceived is "
                            + serviceTransitionalReliefReceivedBatch
                                    .getTransitionalReliefReceived());

        }

        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;

        boolean donationResult = false;
        logFetchTime("TransitionalReliefReceived before Glis webservice call Batch = " + batchNumber);
        try {
            LOGGER.info("transitionalReliefReceivedBatch = "
                    + transitionalReliefReceivedBatch);
            LOGGER.info("transitionalReliefReceivedBatch = "
                    + transitionalReliefReceivedBatch
                            .getTransitionalReliefReceived().size());
            response = glisServiceLocator.getGlisPort()
                    .processTransitionalReliefReceived(
                            serviceTransitionalReliefReceivedBatch);
            logFetchTime("TransitionalReliefReceived after Glis webservice call Batch = " + batchNumber);
        } catch (Exception exception) {
           // HunarC: Update the batch status to FAILED
            logFetchTime("TransitionalReliefReceived Glis webservice Error (1) Batch = " + batchNumber);
            LOGGER.error("Error (1) in TransitionalReliefReceived for Batch: " + batchNumber, exception);
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            try {
                raiseAlert("transitionalReliefReceived",
                        "batchNumber"+ batchNumber, exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: transitionalReliefReceived",e);

            }
            logFetchTime("TransitionalReliefReceived Glis webservice Error (2) Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: transitionalReliefReceived");

        }
        if(response != null){
        if (response.isOk()) {
            // HunarC: Update the batch status to WAITING
            logFetchTime("TransitionalReliefReceived Glis Event call (1) Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    transitionalReliefReceivedBatch
                                            .getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.TRANSITIONAL_RELIEF_RECEIVED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            //com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in TransitionalReliefReceived for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: transitionalReliefReceived",e);

                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: transitionalReliefReceived",e);

                }

                try {
                    raiseAlert("processTransitionalReliefReceived",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: transitionalReliefReceived",e);

                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: transitionalReliefReceived");

            }
            logFetchTime("TransitionalReliefReceived Glis Event call (2) Batch = " + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("TransitionalReliefReceived Glis Rejection (1) Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("TransitionalReliefReceived",
                        "batchNumber"+ batchNumber, response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: transitionalReliefReceived",e);
            }
            logFetchTime("TransitionalReliefReceived Glis Rejection (2) Batch = " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running transitionalReliefReceived. See alert log for details");
        }

        donationResult = response.isOk();
            logFetchTime("transitionalReliefReceived inside BatchServiceProxyImpl - Completed. batch = " + batchNumber);        }
        return donationResult;
    }

    /** {@inheritDoc} */
    public boolean processEventRegistrationPaymentsInitiated(
            DonationPaymentInitiatedBatch donationPaymentInitiatedBatch) {

        com.virginmoney.glis.messages.DonationPaymentInitiatedBatch serviceDonationPaymentInitiated =
                new com.virginmoney.glis.messages.DonationPaymentInitiatedBatch();

        String batchNumber = donationPaymentInitiatedBatch.getBatch().getBatchNumber();
        logFetchTime("processEventRegistrationPaymentsInitiated inside BatchServiceProxyImpl - Start. batch = " + batchNumber);

        if (CollectionUtils.isNotEmpty(donationPaymentInitiatedBatch
                .getPaymentInitiated())) {
            for (PaymentInitiated paymentInitiated : donationPaymentInitiatedBatch
                    .getPaymentInitiated()) {
                serviceDonationPaymentInitiated
                        .getWebDonationPayments()
                        .add(
                                (com.virginmoney.glis.messages.PaymentInitiated) dozer
                                        .map(
                                                paymentInitiated,
                                                com.virginmoney.glis.messages.PaymentInitiated.class));
            }
            LOGGER.debug("Before Dozer mapping domain Payment Initiated is:"
                    + donationPaymentInitiatedBatch.getPaymentInitiated());
            dozer.map(donationPaymentInitiatedBatch.getPaymentInitiated(),
                    serviceDonationPaymentInitiated.getWebDonationPayments());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceDonationPaymentInitiated "
                            + serviceDonationPaymentInitiated
                                    .getWebDonationPayments());
        }
        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        boolean paymentInitiatedResult = false;
        logFetchTime("processEventRegistrationPaymentsInitiated before Glis webservice call Batch = " + batchNumber);

        try {
            response = glisServiceLocator.getGlisPort()
                    .processEventRegistrationPaymentsInitiated(
                            serviceDonationPaymentInitiated);
            logFetchTime("processEventRegistrationPaymentsInitiated after Glis webservice call Batch = " + batchNumber);
            LOGGER.info("response" + response);
        } catch (Exception exception) {
            logFetchTime("processEventRegistrationPaymentsInitiated Glis Error (1) Batch = " + batchNumber);
            LOGGER.error("Error (1) in processEventRegistrationPaymentsInitiated for Batch: " + batchNumber, exception);
            try {
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

                raiseAlert("processEventRegistrationPaymentsInitiated",
                        "batchNumber"+ batchNumber, exception);
                LOGGER.error("Error when processing processEventRegistrationPaymentsInitiated for batchNumber" + batchNumber, exception);
                throw new RuntimeException(exception);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (!response.isOk()) {
            try {
                LOGGER.info("raise Alert 2");
                raiseAlert("processEventRegistrationPaymentsInitiated",
                        "batchNumber"+ batchNumber, response);
                throw new RuntimeException("GLIS Response false");
            } catch (Exception exception) {
                LOGGER.error("Error when processing processEventRegistrationPaymentsInitiated for batchNumber" + batchNumber, exception);
            }
            try {
                throw new GlisServiceException(
                        LOGGER,
                        "Error running processEventRegistrationPaymentsInitiated. See alert log for details");
            } catch (GlisServiceException exception) { 
                LOGGER.error("Error when processing processEventRegistrationPaymentsInitiated for batchNumber" + batchNumber, exception);
            }
        }

        logFetchTime("processEventRegistrationPaymentsInitiated BatchServiceProxyImpl - Completed. batch = " + batchNumber);
        return true;
    }

    /** {@inheritDoc} */
    public boolean processEventRegistrationPaymentsFailed(
            FailedDonationPaymentBatch failedDonationPaymentBatch)
            throws GivingBatchException {


        com.virginmoney.glis.messages.FailedDonationPaymentBatch serviceFailedDonationPayment = new com.virginmoney.glis.messages.FailedDonationPaymentBatch();

        String batchNumber = failedDonationPaymentBatch.getBatch()
                .getBatchNumber();
        logFetchTime("processEventRegistrationPaymentsFailed BatchServiceProxyImpl - Start. Batch = " + batchNumber);

        serviceFailedDonationPayment
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        failedDonationPaymentBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceFailedDonationPayment
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                failedDonationPaymentBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(failedDonationPaymentBatch
                .getFailedPayment())) {

            for (FailedPayment failedPayment : failedDonationPaymentBatch
                    .getFailedPayment()) {
                serviceFailedDonationPayment
                        .getFailedPayments()
                        .add(
                                (com.virginmoney.glis.messages.FailedPayment) dozer
                                        .map(
                                                failedPayment,
                                                com.virginmoney.glis.messages.FailedPayment.class));
            }
            LOGGER.debug("Before Dozer mapping domain Payment Initiated is:"
                    + failedDonationPaymentBatch.getFailedPayment());
            dozer.map(failedDonationPaymentBatch.getFailedPayment(),
                    serviceFailedDonationPayment.getFailedPayments());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceDonationPaymentInitiated "
                            + serviceFailedDonationPayment.getFailedPayments());
        }
        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        boolean paymentFailedResult = false;
        logFetchTime("processEventRegistrationPaymentsFailed before Glis webservice call Batch = " + batchNumber);
        try {
            LOGGER.info("failedDonationPaymentBatch = "
                    + failedDonationPaymentBatch);
            LOGGER.info("failedDonationPaymentBatch = "
                    + failedDonationPaymentBatch.getFailedPayment().size());

            response = glisServiceLocator.getGlisPort()
                    .processEventRegistrationPaymentsFailed(
                            serviceFailedDonationPayment);
            logFetchTime("processEventRegistrationPaymentsFailed after Glis webservice call Batch = " + batchNumber);
        } catch (Exception exception) {
            logFetchTime("processEventRegistrationPaymentsFailed Glis Error (1) Batch = " + batchNumber);
            LOGGER.error("Error (1) in processEventRegistrationPaymentsFailed for Batch: " + batchNumber, exception);
            try {
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);
                logFetchTime("processEventRegistrationPaymentsFailed Glis Error (2) Batch = " + batchNumber);

                raiseAlert("processEventRegistrationPaymentsFailed",
                        "batchNumber"+ batchNumber, exception);
            } catch (Exception exception2) {
                throw new RuntimeException();
            }
        }

        if(response != null) {
            if (response.isOk()) {
                logFetchTime("processEventRegistrationPaymentsFailed Glis Event call (1) Batch = " + batchNumber);
    
                // HunarC: Update the batch status to WAITING
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);
    
                com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
                eventProcessing.setBatchNumber(batchNumber);
                eventProcessing
                        .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                                .map(
                                        failedDonationPaymentBatch
                                                .getMessageHeader(),
                                        com.virginmoney.glis.messages.MessageHeader.class));
                eventProcessing
                        .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.WEB_EVENT_REGISTRATION_FEE_PAYMENT_FAILED);
                eventProcessing.setEventCategory(EventCategoryType.PROCESS);
                com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;
    
                try {
                    processEventResponse = glisServiceLocator.getGlisPort()
                            .processEvent(eventProcessing);
    
                } catch (Exception exception) {
                    LOGGER.error("Error (2) in processEventRegistrationPaymentsFailed for Batch: " + batchNumber, exception);
                    // Calling this service to reset the current status from pending
                    // to Failure.
                    // HunarC: Update the batch status to FAILED
                String errorMessage = "Error calling G/L System (Is it unavailable?)";
                if (exception.getMessage() != null) {
                   errorMessage += exception.getMessage();
                }
                    setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);
    
                    final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                    request.setBatchNumber(batchNumber);
                    request.setStatus(Constant.BATCH_STATUS_FAILED);
                    try {
                        givingBatchExtManagementServiceLocatorImpl
                                .getGivingBatchExtManagementPort()
                                .updateAllBatchEntityStatus(request);
                    }
                    catch (GivingBatchExtManagementServiceFaultException e) {
                        throw new GivingBatchException(
                                LOGGER, "Batch Failed :BatchServiceProxyImpl: processEventRegistrationPaymentsFailed",e);
                        
                    }
                    catch (Exception e) {
                        throw new GivingBatchException(
                                LOGGER, "Batch Failed :BatchServiceProxyImpl: processEventRegistrationPaymentsFailed",e);
    
                    }
    
                    try {
                        raiseAlert("processEventRegistrationPaymentsFailed",
                                "batchNumber"+ batchNumber, exception);
                    }
                    catch (Exception e) {
                        throw new GivingBatchException(
                                LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processEventRegistrationPaymentsFailed",e);
                    }
                    logFetchTime("processEventRegistrationPaymentsFailed Glis Event call (2) Batch = " + batchNumber);
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: processEventRegistrationPaymentsFailed");
    
                }
    
            } else {
                // HunarC: Set the batch status to FAILED
                logFetchTime("processEventRegistrationPaymentsFailed Glis Rejection (1) Batch = " + batchNumber);
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);
    
                // Raise alert then throw exception to rollback transaction
                try {
                    raiseAlert("processEventRegistrationPaymentsFailed",
                            "batchNumber"+ batchNumber, response);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processEventRegistrationPaymentsFailed",e);
    
                }
                logFetchTime("processEventRegistrationPaymentsFailed Glis Rejection (2) Batch = " + batchNumber);
                throw new GivingBatchException(
                        LOGGER,
                        "Error running processEventRegistrationPaymentsFailed. See alert log for details");
            }
        }

        logFetchTime("processEventRegistrationPaymentsFailed BatchServiceProxyImpl - Completed. Batch = " + batchNumber);

        return true;

    }

    /** {@inheritDoc} */
    public boolean processFailedDonationPayments(
            FailedDonationPaymentBatch failedDonationPaymentBatch)
            throws GivingBatchException {

        com.virginmoney.glis.messages.FailedDonationPaymentBatch serviceFailedDonationPayment = new com.virginmoney.glis.messages.FailedDonationPaymentBatch();

        String batchNumber = failedDonationPaymentBatch.getBatch()
                .getBatchNumber();
        logFetchTime("processFailedDonationPayments inside BatchServiceProxyImpl - Start Batch = " + batchNumber);
        serviceFailedDonationPayment
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        failedDonationPaymentBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceFailedDonationPayment
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                failedDonationPaymentBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(failedDonationPaymentBatch
                .getFailedPayment())) {
            for (FailedPayment failedPayment : failedDonationPaymentBatch
                    .getFailedPayment()) {
                serviceFailedDonationPayment
                        .getFailedPayments()
                        .add(
                                (com.virginmoney.glis.messages.FailedPayment) dozer
                                        .map(
                                                failedPayment,
                                                com.virginmoney.glis.messages.FailedPayment.class));
            }
            LOGGER.debug("Before Dozer mapping domain Payment Initiated is:"
                    + failedDonationPaymentBatch.getFailedPayment());
            dozer.map(failedDonationPaymentBatch.getFailedPayment(),
                    serviceFailedDonationPayment.getFailedPayments());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceDonationPaymentInitiated "
                            + serviceFailedDonationPayment.getFailedPayments());
        }
        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        boolean paymentFailedResult = false;
        logFetchTime("processFailedDonationPayments before Glis webservice call Batch = ." + batchNumber);
        try {
            LOGGER.debug("failedDonationPaymentBatch = "
                    + failedDonationPaymentBatch);

            response = glisServiceLocator
                    .getGlisPort()
                    .processFailedDonationPayments(serviceFailedDonationPayment);
            logFetchTime("processFailedDonationPayments After Glis webservice call Batch = ." + batchNumber);
        } catch (Exception exception) {
            logFetchTime("processFailedDonationPayments Glis call Error (1) Batch = ." + batchNumber);
            LOGGER.error("Error (1) in processFailedDonationPayments for Batch: " + batchNumber, exception);
            try {
                // Calling this service to reset the current status from pending
                // to
                // Failure.

                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                        + batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
                // Raise alert then re-throw to rollback transaction.

                raiseAlert("processFailedDonationPayments",
                        "batchNumber"+ batchNumber, exception);
                logFetchTime("processFailedDonationPayments Glis call Error (2) Batch = ." + batchNumber);
                throw new RuntimeException();
            } catch (Exception exception2) {
                throw new RuntimeException();
            }
        }
        if(response != null) {
        if (response.isOk()) {
            logFetchTime("processFailedDonationPayments Glis Event call (1) Batch = ." + batchNumber);
            // HunarC: Update the batch status to WAITING
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    failedDonationPaymentBatch
                                            .getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.WEB_DONATIONS_PAYMENT_FAILED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            //com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;

            try {
                glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in processFailedDonationPayments for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: processFailedDonationPayments",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: processFailedDonationPayments",e);

                }

                try {
                    raiseAlert("processFailedDonationPayments",
                            "batchNumber"+ batchNumber, exception);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processFailedDonationPayments",e);

                }
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processFailedDonationPayments");
            }
            logFetchTime("processFailedDonationPayments Glis Event call (2) Batch = ." + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("processFailedDonationPayments Glis Rejection (1) Batch = ." + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("processFailedDonationPayments",
                        "batchNumber"+ batchNumber, response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processFailedDonationPayments",e);

            }
            logFetchTime("processFailedDonationPayments Glis Rejection (2) Batch = ." + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running processFailedDonationPayments. See alert log for details");
        }

        logFetchTime("processFailedDonationPayments inside BatchServiceProxyImpl - Completed Batch = " + batchNumber);
        }
        return true;
    }

    /**
     * Raise an alert for a failed job. Will covert the response object to a
     * message before raising the alert.
     * 
     * @param jobName name of the job that was running. E.g.
     * transitionalReliefClaimed
     * @param jobId id of the job. Need to get from batch runtime context
     * @param response the response object from glis
     * 
     * @throws Exception when unable to raise alert
     */
    private void raiseAlert(String jobName, String jobId,
            MaintenanceResponse response) throws GivingBatchException {

        /*
         * Convert the response to a message
         */
        StringBuilder sb = new StringBuilder();
        sb.append("Message = ").append(response.getResponseMessage()).append(
                "\n");
        for (FailedRecords failedRecord : response.getFailedRecords()) {
            sb.append("FailedRecord = ").append(
                    failedRecord.getFailedRecordType()).append(", ").append(
                    failedRecord.getFailureReason()).append(", ").append(
                    failedRecord.getRecordReference()).append("\n");
        }
        for (ErrorMessage errorMessage : response.getErrors().getErrors()) {
            sb.append("ErrorMessage = ").append(
                    errorMessage.getErrorDefaultMessage()).append(", ").append(
                    errorMessage.getErrorField()).append(", ").append(
                    errorMessage.getErrorMessageKey()).append("\n");
        }

        raiseAlert(jobName, jobId, sb.toString());
    }

    /**
     * Call processEvent on GLIS.
     * 
     * The GLIS processEvent() method is used to tell glis we want the results
     * of some processing it should have done. The type of processing depends on
     * the parameters of the serviceEventProcessing object sent with the
     * request.
     * 
     * GLIS should respond by sending a JMS that will be picked up by the
     * {@link GlisMessageDelegate} and used to invoke another job.
     * 
     * @param domainEventProcessing the event to raise
     * 
     * @return boolean always true - throws exception on failure
     * 
     * @throws Exception with appropriate message
     */
    public boolean processEvent(EventProcessing domainEventProcessing)
            throws GivingBatchException {

        LOGGER.debug("processEvent inside BatchServiceProxyImpl - Start");

        /*
         * Copy the domain object to the service message
         */
        com.virginmoney.glis.messages.EventProcessing serviceEventProcessing = new com.virginmoney.glis.messages.EventProcessing();

        LOGGER.debug("Before Dozer mapping domainEventProcessing is:"
                + domainEventProcessing);

        dozer.map(domainEventProcessing, serviceEventProcessing);

        LOGGER
                .debug("inside BatchServiceProxyImpl after dozer mapper serviceEventProcessing is "
                        + serviceEventProcessing);

        /*
         * Call GLIS
         */
        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        try {
            response = glisServiceLocator.getGlisPort().processEvent(
                    serviceEventProcessing);
        } catch (Exception exception) {
            LOGGER.error("Error (1) in processEvent", exception);
            try {
                raiseAlert("processEvent", "Where do I get Job id from", exception);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processEvent",e);
            }
            throw new GivingBatchException(
                    LOGGER, "Batch Failed :BatchServiceProxyImpl: processEvent");
        }

        if (!response.isOk()) {
            try {
                raiseAlert("processEvent", "Where do I get Job id from", response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processEvent",e);

            }
            throw new GivingBatchException(LOGGER,
                    "Error running processEvent. See alert log for details");
        }

        LOGGER.debug("processEvent after Glis webservice call response is OK");
        LOGGER.info("processEvent inside BatchServiceProxyImpl - End");
        return true;
    }

    /**
     * Raise an alert for a failed job Will covert the exception to a message
     * before raising the alert.
     * 
     * @param jobName name of the job that was running. E.g.
     * transitionalReliefClaimed
     * @param jobId id of the job. Need to get from batch runtime context
     * @param exception the exception thrown
     * 
     * @throws Exception with appropriate message
     */
    public void raiseAlert(String jobName, String jobId, Exception exception)
            throws GivingBatchException {
        LOGGER.error("Raising alert for job name/id: " + jobName + "/" + jobId, exception);
        raiseAlert(jobName, jobId, exception.getMessage());
    }

    /**
     * Raise an alert for a failed job.
     * 
     * @param jobName name of the job that was running. E.g.
     * transitionalReliefClaimed
     * @param jobId id of the job. Need to get from batch runtime context
     * @param message to send to the alert service
     * 
     * @throws Exception with appropriate message
     */
    private void raiseAlert(String jobName, String jobId, String message)
            throws GivingBatchException {
        /*
         * Create the service messages
         */
        AlertContent alertContent = new AlertContent();
        alertContent.setAlertMessage(message);
        GregorianCalendar cal = new GregorianCalendar();
        alertContent.setDateTimeOfAlert(new XMLGregorianCalendarImpl(cal));
        alertContent.setJobId(jobId);
        alertContent.setJobName(jobName);
        alertContent.setService("giving-batch");

        AlertDetail alertDetail = new AlertDetail();
        alertDetail.setContent(alertContent);

        /*
         * Call the alert service
         */
        AlertPort alertService = null;
        try {
            alertService = getAlertServiceLocator().getAlertPort();
        }
        catch (Exception e) {
            throw new GivingBatchException(LOGGER,
            "Error in raiseAlert method.",e);
        }
        alertService.logAlertRequest(alertDetail);
    }

    /**
     * {@inheritDoc}
     */
    public boolean processWebDonationPaymentsFailed(
            FailedDonationPaymentBatch failedDonationPaymentBatch)
            throws GivingBatchException {

        com.virginmoney.glis.messages.FailedDonationPaymentBatch serviceFailedDonationPayment = 
            new com.virginmoney.glis.messages.FailedDonationPaymentBatch();

        String batchNumber = failedDonationPaymentBatch.getBatch()
                .getBatchNumber();
        logFetchTime("processWebDonationPaymentsFailed inside BatchServiceProxyImpl - Start. Batch = " + batchNumber);

        serviceFailedDonationPayment
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        failedDonationPaymentBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceFailedDonationPayment
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                failedDonationPaymentBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(failedDonationPaymentBatch
                .getFailedPayment())) {

            for (FailedPayment failedPayment : failedDonationPaymentBatch
                    .getFailedPayment()) {
                serviceFailedDonationPayment
                        .getFailedPayments()
                        .add(
                                (com.virginmoney.glis.messages.FailedPayment) dozer
                                        .map(
                                                failedPayment,
                                                com.virginmoney.glis.messages.FailedPayment.class));
            }
            LOGGER.debug("Before Dozer mapping domain failedPayment is:"
                    + failedDonationPaymentBatch.getFailedPayment());
            dozer.map(failedDonationPaymentBatch.getFailedPayment(),
                    serviceFailedDonationPayment.getFailedPayments());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper servicePaymentFailed is "
                            + serviceFailedDonationPayment.getFailedPayments());

        }

        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        boolean paymentFailedResult = false;
        logFetchTime("processWebDonationPaymentsFailed before Glis webservice call Batch = " + batchNumber);
        try {
            LOGGER.debug("failedDonationPaymentBatch = "
                    + failedDonationPaymentBatch);

            response = glisServiceLocator
                    .getGlisPort()
                    .processFailedDonationPayments(serviceFailedDonationPayment);
            logFetchTime("processWebDonationPaymentsFailed after Glis webservice call Batch = " + batchNumber);
        } catch (Exception exception) {
            // Calling this service to reset the current status from pending to
            // Failure.
           logFetchTime("processWebDonationPaymentsFailed Glis call Error (1) Batch = " + batchNumber);
            LOGGER.error("Error (1) in processWebDonationPaymentsFailed for Batch: " + batchNumber, exception);
           // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processWebDonationPaymentsFailed",e);
            }
            catch (Exception e) {
                // Raise alert then re-throw to rollback transaction.
                try {
                    raiseAlert("processWebDonationPaymentsFailed",
                            "batchNumber"+ batchNumber, exception);
                } catch (Exception exception2) {
                    throw new RuntimeException();
                }
                
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processWebDonationPaymentsFailed",e);
            }
            logFetchTime("processWebDonationPaymentsFailed Glis call Error (2) Batch = " + batchNumber);
        }
        LOGGER.info("Got response from GLIS: " + response);
        if (response != null) {
            if (response.isOk()) {
                logFetchTime("processWebDonationPaymentsFailed Glis Event call (1) Batch = " + batchNumber);
                // HunarC: Update the batch status to WAITING
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);
    
                com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
                eventProcessing.setBatchNumber(batchNumber);
                eventProcessing
                        .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                                .map(
                                        failedDonationPaymentBatch
                                                .getMessageHeader(),
                                        com.virginmoney.glis.messages.MessageHeader.class));
                eventProcessing
                        .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.WEB_DONATIONS_PAYMENT_FAILED);
                eventProcessing.setEventCategory(EventCategoryType.PROCESS);
                com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;
    
                try {
                    processEventResponse = glisServiceLocator.getGlisPort()
                            .processEvent(eventProcessing);
    
                } catch (Exception exception) {
                    LOGGER.error("Error (2) in processWebDonationPaymentsFailed for Batch: " + batchNumber, exception);
                    // Calling this service to reset the current status from pending
                    // to Failure.
                    // HunarC: Update the batch status to FAILED
                String errorMessage = "Error calling G/L System (Is it unavailable?)";
                if (exception.getMessage() != null) {
                   errorMessage += exception.getMessage();
                }
                    setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);
    
                    final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                    request.setBatchNumber(batchNumber);
                    request.setStatus(Constant.BATCH_STATUS_FAILED);
                    try {
                        givingBatchExtManagementServiceLocatorImpl
                                .getGivingBatchExtManagementPort()
                                .updateAllBatchEntityStatus(request);
                    }
                    catch (GivingBatchExtManagementServiceFaultException e) {
                        throw new GivingBatchException(
                                LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processWebDonationPaymentsFailed",e);
    
                    }
                    catch (Exception e) {
                        throw new GivingBatchException(
                                LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processWebDonationPaymentsFailed",e);
    
                    }
    
                    raiseAlert("processFailedDonationPayments",
                            "batchNumber"+ batchNumber, exception);
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processWebDonationPaymentsFailed");
                }
                logFetchTime("processWebDonationPaymentsFailed Glis Event call (2) Batch = " + batchNumber);
    
            } else {
                // HunarC: Set the batch status to FAILED
                logFetchTime("processWebDonationPaymentsFailed Glis Rejection (1) Batch = " + batchNumber);
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);
    
                // Raise alert then throw exception to rollback transaction
                try {
                    raiseAlert("processFailedDonationPayments",
                            "batchNumber"+ batchNumber, response);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: processWebDonationPaymentsFailed",e);
    
                }
                logFetchTime("processWebDonationPaymentsFailed Glis Rejection (2) Batch = " + batchNumber);
                throw new GivingBatchException(LOGGER,
                        "Error running registerCharities. See alert log for details");
            }
        }
        logFetchTime("processWebDonationPaymentsFailed inside BatchServiceProxyImpl - Completed. Batch = " + batchNumber);
        return true;

    }

    /**
     * {@inheritDoc}
     */
    public boolean waiveRegistrationFees(
            WaiveRegistrationFeesBatch waiveRegistrationFeesBatch)
            throws GivingBatchException {
        com.virginmoney.glis.messages.WaiveRegistrationFeesBatch serviceFeesBatch = 
            new com.virginmoney.glis.messages.WaiveRegistrationFeesBatch();

        String batchNumber = waiveRegistrationFeesBatch.getBatch()
                .getBatchNumber();

        logFetchTime("waiveRegistrationFees inside BatchServiceProxyImpl - Start. Batch = " + batchNumber);

        serviceFeesBatch.setBatch((com.virginmoney.glis.messages.Batch) dozer
                .map(waiveRegistrationFeesBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceFeesBatch
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                waiveRegistrationFeesBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(waiveRegistrationFeesBatch
                .getRegistrationFee())) {
            for (WaiveRegistrationFee waiveRegistrationFee : waiveRegistrationFeesBatch
                    .getRegistrationFee()) {

                serviceFeesBatch
                        .getRegistrationFee()
                        .add(
                                (com.virginmoney.glis.messages.WaiveRegistrationFee) dozer
                                        .map(
                                                waiveRegistrationFee,
                                                com.virginmoney.glis.messages.WaiveRegistrationFee.class));
            }
            LOGGER.debug("Before Dozer mapping domain WaiveRegistrationFee is:"
                    + waiveRegistrationFeesBatch.getRegistrationFee());
            dozer.map(waiveRegistrationFeesBatch.getRegistrationFee(),
                    serviceFeesBatch.getRegistrationFee());
            LOGGER
                    .debug("inside BatchServiceProxyImpl after dozer mapper serviceFeesBatch is "
                            + serviceFeesBatch.getRegistrationFee());

        }
        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        logFetchTime("waiveRegistrationFees before Glis webservice call Batch = " + batchNumber);

        try {
            response = glisServiceLocator.getGlisPort().waiveRegistrationFees(
                    serviceFeesBatch);
            logFetchTime("waiveRegistrationFees after Glis webservice call Batch = " + batchNumber);
        } catch (Exception exception) {
            // If GLIS service fails than we need to rollback the current status
            // in batch entity table.
            // HunarC: Update the batch status to FAILED
            logFetchTime("waiveRegistrationFees Glis webservice Error (1) Batch = " + batchNumber);
            LOGGER.error("Error (1) in waiveRegistrationFees for Batch: " + batchNumber, exception);
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: waiveRegistrationFees",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: waiveRegistrationFees",e);
            }

            raiseAlert("WaiveRegistrationFees", "batchNumber"+ batchNumber,
                    exception);
            logFetchTime("waiveRegistrationFees Glis webservice Error (2) Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: waiveRegistrationFees");

        }

        if (response.isOk()) {
            logFetchTime("waiveRegistrationFees Glis Event call (1) Batch = " + batchNumber);
            // HunarC: Update the batch status to WAITING
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    waiveRegistrationFeesBatch
                                            .getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.CHARITY_REGISTRATION_FEE_PAYMENT_WAIVED);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);
            com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;
            try {
                processEventResponse = glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in waiveRegistrationFees for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: waiveRegistrationFees",e);

                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: waiveRegistrationFees",e);

                }

                raiseAlert("WaiveRegistrationFees",
                        "batchNumber"+ batchNumber, exception);
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: waiveRegistrationFees");

            }
            logFetchTime("waiveRegistrationFees Glis Event call (2) Batch = " + batchNumber);
        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("waiveRegistrationFees Glis Rejection (1) Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("WaiveRegistrationFees", "batchNumber"+ batchNumber,
                        response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: waiveRegistrationFees",e);

            }
            logFetchTime("waiveRegistrationFees Glis Rejection (2) Batch = " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running WaiveRegistrationFees. See alert log for details");
        }

        logFetchTime("waiveRegistrationFees inside BatchServiceProxyImpl - Completed. Batch = " + batchNumber);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean maintainCharities(CharitiesBatch charitiesBatch)
            throws GivingBatchException {

        String batchNumber = charitiesBatch.getBatch().getBatchNumber();
        logFetchTime("maintainCharities inside BatchServiceProxyImpl - Start. batch = " + batchNumber);

        com.virginmoney.glis.messages.CharitiesBatch serviceCharitiesBatch = new com.virginmoney.glis.messages.CharitiesBatch();
        serviceCharitiesBatch
                .setBatch((com.virginmoney.glis.messages.Batch) dozer.map(
                        charitiesBatch.getBatch(),
                        com.virginmoney.glis.messages.Batch.class));
        serviceCharitiesBatch
                .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                        .map(
                                charitiesBatch.getMessageHeader(),
                                com.virginmoney.glis.messages.MessageHeader.class));

        if (CollectionUtils.isNotEmpty(charitiesBatch.getCharities())) {
            for (Charities domainCharitiesObject : charitiesBatch.getCharities()) {
                LOGGER.debug("Domain Charity Details ID/bank Accounts: " + domainCharitiesObject.getVmgCharityRefId()
                        + "/" + domainCharitiesObject.getBankAccounts().size());

                // HunarC: Changed the mapping mechanism to cater for bank accounts
                com.virginmoney.glis.messages.Charity wsCharity = (com.virginmoney.glis.messages.Charity)
                        dozer.map(domainCharitiesObject, com.virginmoney.glis.messages.Charity.class);
                if (domainCharitiesObject.getBankAccounts().size() > 0) {
                    for (com.virginmoneygiving.givingbatch.domain.BankAccountType account :
                            domainCharitiesObject.getBankAccounts()) {
                        com.virginmoney.glis.messages.BankAccountType wsAccount =
                                (com.virginmoney.glis.messages.BankAccountType)
                                        dozer.map (account, com.virginmoney.glis.messages.BankAccountType.class);
                        wsCharity.getBankAccounts().add(wsAccount);
                    }
                }
                serviceCharitiesBatch.getCharity().add(wsCharity);

            }
        }
        // HunarC: Update the batch status to VALIDATING
        setBatchStatus(batchNumber, Constant.BATCH_STATUS_VALIDATING, null, false, null, null);

        com.virginmoney.glis.messages.MaintenanceResponse response = null;
        logFetchTime("maintainCharities before Glis webservice call Batch = " + batchNumber);
        try {
            LOGGER.info("serviceCharitiesBatch = " + serviceCharitiesBatch);
            LOGGER.info("serviceCharitiesBatch = "
                    + serviceCharitiesBatch.getCharity().size());
            response = glisServiceLocator.getGlisPort().maintainCharities(
                    serviceCharitiesBatch);
            logFetchTime("maintainCharities after Glis webservice call Batch = " + batchNumber);
        } catch (Exception exception) {

            // Calling this service to reset the current status from pending to
            // Failure.
            logFetchTime("maintainCharities Glis call Error (1) Batch = " + batchNumber);
            LOGGER.error("Error (1) in maintainCharities for Batch: " + batchNumber, exception);
           // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, true, "Service call", response);

            final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
            request.setBatchNumber(batchNumber);
            LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:"
                    + batchNumber);
            request.setStatus(Constant.BATCH_STATUS_FAILED);
            try {
                givingBatchExtManagementServiceLocatorImpl
                        .getGivingBatchExtManagementPort()
                        .updateAllBatchEntityStatus(request);
            }
            catch (GivingBatchExtManagementServiceFaultException e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: maintainCharities",e);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed :BatchServiceProxyImpl: maintainCharities",e);
            }
            // Raise alert then re-throw to rollback transaction.
            raiseAlert("maintainCharities", "batchNumber"+ batchNumber, exception);
            logFetchTime("maintainCharities Glis call Error (2) Batch = " + batchNumber);
            throw new GivingBatchException(
                    LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: maintainCharities");
        }
        LOGGER.info("Got response from GLIS: " + response);

        com.virginmoney.glis.messages.MaintenanceResponse processEventResponse = null;
        if (response.isOk()) {
            logFetchTime("maintainCharities Glis Event call (1) Batch = " + batchNumber);
            // HunarC: Update the batch status to WAITING
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_WAITING, null, false, null, null);

            com.virginmoney.glis.messages.EventProcessing eventProcessing = new com.virginmoney.glis.messages.EventProcessing();
            eventProcessing.setBatchNumber(batchNumber);
            eventProcessing
                    .setHeader((com.virginmoney.glis.messages.MessageHeader) dozer
                            .map(
                                    charitiesBatch.getMessageHeader(),
                                    com.virginmoney.glis.messages.MessageHeader.class));
            eventProcessing
                    .setEventType(com.virginmoney.glis.messages.EventProcessingTypes.MAINTAIN_CHARITY);
            eventProcessing.setEventCategory(EventCategoryType.PROCESS);

            try {
                processEventResponse = glisServiceLocator.getGlisPort()
                        .processEvent(eventProcessing);

            } catch (Exception exception) {
                LOGGER.error("Error (2) in maintainCharities for Batch: " + batchNumber, exception);
                // Calling this service to reset the current status from pending
                // to Failure.
                // HunarC: Update the batch status to FAILED
            String errorMessage = "Error calling G/L System (Is it unavailable?)";
            if (exception.getMessage() != null) {
               errorMessage += exception.getMessage();
            }
                setBatchStatus(batchNumber, Constant. BATCH_STATUS_ERROR, errorMessage, false, "Event call", null);

                final UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setStatus(Constant.BATCH_STATUS_FAILED);
                try {
                    givingBatchExtManagementServiceLocatorImpl
                            .getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                catch (GivingBatchExtManagementServiceFaultException e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: maintainCharities",e);
                }
                catch (Exception e) {
                    throw new GivingBatchException(
                            LOGGER, "Batch Failed :BatchServiceProxyImpl: maintainCharities",e);
                }

                raiseAlert("maintainCharities", "batchNumber"+ batchNumber,
                        exception);
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: maintainCharities");
            }
            logFetchTime("maintainCharities Glis Event call (2) Batch = " + batchNumber);

        } else {
            // HunarC: Set the batch status to FAILED
            logFetchTime("maintainCharities Glis Rejection (1) Batch = " + batchNumber);
            setBatchStatus(batchNumber, Constant. BATCH_STATUS_FAIL, null, true, null, response);

            // Raise alert then throw exception to rollback transaction
            try {
                raiseAlert("maintainCharities", "batchNumber"+ batchNumber,
                        response);
            }
            catch (Exception e) {
                throw new GivingBatchException(
                        LOGGER, "Batch Failed while raising alert:BatchServiceProxyImpl: maintainCharities",e);
            }
            logFetchTime("maintainCharities Glis Rejection (2) Batch = " + batchNumber);
            throw new GivingBatchException(LOGGER,
                    "Error running maintainCharities. See alert log for details");
        }

        logFetchTime("maintainCharities inside BatchServiceProxyImpl - Completed. batch = " + batchNumber);
        return processEventResponse.isOk();

    }

    /**
     * {@inheritDoc}
     */
    public boolean makeRegularDonationWithActiveCard(
            MakeDonationWithActiveCard donationRequest) throws GivingBatchException {
        MakeRegularDonationWithActiveCardRequest activeCardRequest = new MakeRegularDonationWithActiveCardRequest();
        activeCardRequest.setHeader((MessageHeader) dozer.map(donationRequest
                .getHeader(), MessageHeader.class));
        activeCardRequest
                .setDonationDetails((GivingServiceDonationDetails) dozer.map(
                        donationRequest.getDonationDetails(),
                        GivingServiceDonationDetails.class));
        MakeRegularDonationWithActiveCardResponse activeCardResponse = null;
        boolean returnResponse = false;
        try {
            logFetchTime("makeRegularDonationWithActiveCard Start");
            activeCardResponse = givingManagementServiceLocator
                    .getGivingManagementPort().makeRegularDonationWithActiveCard(
                            activeCardRequest);
            logFetchTime("makeRegularDonationWithActiveCard finish");
            LOGGER.debug("After makeRegularDonationWithActiveCard service call [response] ="
                            + activeCardResponse.getResult().isSuccess());
            LOGGER.debug("After makeRegularDonationWithActiveCard service call [DonRef] ="
                            + activeCardResponse.getResult().getDonationId());
            LOGGER.debug("After makeRegularDonationWithActiveCard service call [Result message] ="
                            + activeCardResponse.getResult().getPaymentResult().getMessage());

        } catch (Exception exception) {
            LOGGER.error("Error when processing makeDonationWithActiveCard for Donation: "
                    + donationRequest.getDonationDetails().getDonationReference() + ", Donor: "
                    + donationRequest.getDonationDetails().getDonorId(), exception);
            raiseAlert("CollectRegularDonationJob", "", exception);
            return false;
        }

        returnResponse = activeCardResponse.getResult().isSuccess();
        LOGGER.debug("After makeRegularDonationWithActiveCard service call [DonRef] ="
                            + activeCardResponse.getResult().getDonationId() + ", Response = " + returnResponse);
        if (returnResponse) {
            // Save the donation in PAYMENT table - Awaiting for CR.
            // call the saveRegularDonation web service.
            return returnResponse;
        }
        return returnResponse;
        //return true;
    }

    /**
     * Sets the giving management service locator.
     * 
     * @param givingManagementServiceLocator the givingManagementServiceLocator to set
     */
    public void setGivingManagementServiceLocator(
            GivingManagementServiceLocatorImpl givingManagementServiceLocator) {
        this.givingManagementServiceLocator = givingManagementServiceLocator;
    }

    /**
     * Sets the payment management service locator.
     * 
     * @param paymentManagementServiceLocator the paymentManagementServiceLocator to set
     */
    public void setPaymentManagementServiceLocator(
            PaymentManagementServiceLocatorImpl paymentManagementServiceLocator) {
        this.paymentManagementServiceLocator = paymentManagementServiceLocator;
    }

    /**
     * Save regular donation.
     * 
     * @param donationRequest the donation request
     * 
     * @return true, if save regular donation
     * 
     * @throws Exception the exception
     */
    public boolean saveRegularDonation(
            SaveRegularDonationRequest donationRequest) throws GivingBatchException {
        LOGGER
                .debug("In side BatchServiceProxyImpl saveRegularDonation : START");

        CreateRegularWebDonationRequest createRegularWebDonationRequest = new CreateRegularWebDonationRequest();
        createRegularWebDonationRequest
                .setHeader((com.virginmoneygiving.paymentmanagement.service.messages.MessageHeader) dozer
                        .map(
                                donationRequest.getHeader(),
                                com.virginmoneygiving.paymentmanagement.service.messages.MessageHeader.class));

        createRegularWebDonationRequest
                .setCardTransaction((ServiceCardTransaction) dozer.map(
                        donationRequest.getCardTransaction(),
                        ServiceCardTransaction.class));

        List<DonationDistribution> list = donationRequest.getRegularDonation();
        for (DonationDistribution donationDistribution : list) {
            createRegularWebDonationRequest
                    .getRegularDonation()
                    .add(
                            (com.virginmoneygiving.paymentmanagement.service.messages.DonationDistribution) dozer
                                    .map(
                                            donationDistribution,
                                            com.virginmoneygiving.paymentmanagement.service.messages.DonationDistribution.class));
        }

        createRegularWebDonationRequest.setDonationId(donationRequest
                .getDonationId());
        createRegularWebDonationRequest.setGiftAidApplicable(donationRequest
                .isGiftAidApplicable());

        CreateRegularWebDonationResponse response = null;
        try {
            response = paymentManagementServiceLocator
                    .getPaymentManagementPort().saveRegularWebDonation(
                            createRegularWebDonationRequest);
            LOGGER.debug("After calling saveRegularWebDonation ");
        } catch (Exception exception) {
            LOGGER.error("ERROR saving regular donation for ID: " + donationRequest.getDonationId(), exception);
        }

        LOGGER.debug("In side BatchServiceProxyImpl saveRegularDonation : END");
        return false;
    }

    /**
     * This private method calls the BatchExtManagement Service to update the batch status.
     * 
     * @param batchNumber Number of the batch to update
     * @param status Status to set
     * @param response Response received from GLIS (if any)
     * @param message Any message to set
     * @param setEnd Indicates whether end date should be set
     * @param action Action that was being executed.
     */
    private void setBatchStatus(String batchNumber, String status, String message, boolean setEnd,
                                String action, MaintenanceResponse response) {
        String msg = "";

        if (!Util.isStringEmpty(message)) {
            msg+= message;
        }
        if (!Util.isStringEmpty(action)) {
            msg+= "(" + action + ")";
        }

        try {
            UpdateBatchStatusRequest updateBSRequest = new UpdateBatchStatusRequest();
            updateBSRequest.setBatchNumber(batchNumber);
            updateBSRequest.setStatus(status);
            if (setEnd) {
                updateBSRequest.setEndDateTime(Util.buildXMLGregorianDate());
            }
            if (response != null) {
                String rMsg = response.getResponseMessage();
                if (rMsg != null && !rMsg.equals("")) {
                    msg+= ": " + rMsg;
                }
                else {
                    msg+= " Please refer to entities against this batch";
                }
            }
            if (Util.isStringEmpty(msg)) {
                msg = " Please refer to entities against this batch";
            }

            updateBSRequest.setErrorMessage(msg);
            givingBatchExtManagementServiceLocatorImpl.getGivingBatchExtManagementPort()
                    .updateBatchStatus(updateBSRequest);

            if (response != null) {
                if (!response.isOk()) {
                    UpdateAllBatchEntityStatusRequest request = new UpdateAllBatchEntityStatusRequest();
                    request.setBatchNumber(batchNumber);
                    LOGGER.info("****Batch Number in updateAllBatchEntityStatus =:" + batchNumber);
                    request.setStatus(Constant.BATCH_STATUS_FAIL);
                    givingBatchExtManagementServiceLocatorImpl.getGivingBatchExtManagementPort()
                            .updateAllBatchEntityStatus(request);
                }
                if (response.getFailedRecords() != null && response.getFailedRecords().size() > 0) {
                    for (FailedRecords fr : response.getFailedRecords()) {
                        setBatchEntityStatus(batchNumber, fr.getRecordReference(), Constant.BATCH_STATUS_ERROR,
                                fr.getFailureReason());
                    }
                }
            }
        }
        catch (Exception exception) {
            LOGGER.error("Unexpected error setting batch status (Batch/Status): " + batchNumber + "/"
                    + status, exception);
        }

    }

    /**
     * Sets the specified status on the batch entity.
     * 
     * @param batchNumber Batch number
     * @param entityRef   Entity reference
     * @param status      Status to set
     * @param message     Error message, if any
     */
    private void setBatchEntityStatus(String batchNumber, String entityRef, String status, String message) {

        try {
            UpdateBatchEntityStatusByReferenceRequest request = new UpdateBatchEntityStatusByReferenceRequest();
            request.setBatchNumber(batchNumber);
            request.setEntityReference(entityRef);
            request.setStatus(status);
            request.setErrorMessage(message);

            givingBatchExtManagementServiceLocatorImpl.getGivingBatchExtManagementPort()
                    .updateBatchEntityStatusByReference(request);
        }
        catch (Exception exception) {
            LOGGER.error("Unexpected error setting batch entity status (Batch/Entity/Status): " + batchNumber + "/"
                    + entityRef + "/" + status, exception);
        }
    }

    private void logFetchTime(String message) {
        DateTimeFormatter fmt = Constant.DATETIMEFORMATTER;
        DateTime sDate = new DateTime();
        String dt = fmt.print(sDate);
        LOGGER.debug(message + " GlisBatch-Date/Time: " + dt);
    }



}
