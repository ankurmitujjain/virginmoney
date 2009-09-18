package com.virginmoneygiving.givingbatch.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.Charity;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimRecord;
import com.virginmoneygiving.givingbatch.domain.Person;
import com.virginmoneygiving.givingbatch.serviceproxy.GiftAidClaimServiceProxy;
import com.virginmoneygiving.givingmanagement.service.messages.GivingManagementServiceFaultException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.GiftAidAmount;
import com.virginmoneygiving.payment.domain.LastGiftAidClaim;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.TransitionalReliefAmount;
import com.virginmoneygiving.payment.domain.TransitionalReliefStatus;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class used to store charity and donor information to generate flat xml output file.
 * This class takes the payment object returned by ItemReader and returns the GiftAidClaimRecord ItemWriter object.
 *
 * @author Srinivas Nageli
 */
public class GiftAidClaimReportProcessor implements
        ItemProcessor<Payment, GiftAidClaimRecord>, StepExecutionListener {

    /** instance for logger. * */
    private static final Logger LOGGER =
            Logger.getLogger(GiftAidClaimReportProcessor.class);

    /** gift Aid Claim Service Proxy. * */
    private GiftAidClaimServiceProxy giftAidClaimServiceProxy;

    /** payment Service. * */
    private PaymentService paymentService;

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** {@inheritDoc} */
    public GiftAidClaimRecord process(final Payment payment) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimReportProcessor::process() - START");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GiftAidClaimReportProcessor before process for payment: " + payment.getId());
        }
        final GiftAidClaimRecord giftAidClaimRecord = new GiftAidClaimRecord();
        giftAidClaimRecord.setCharityId(payment.getPaymentTarget());
        final Set<GiftAidAmount> giftAidAmounts = payment.getGiftAidAmount();

        for (GiftAidAmount giftAidAmount : giftAidAmounts) {
            giftAidClaimRecord.setGiftAidAmount(giftAidAmount
                    .getGiftAidAmount());
            break;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GiftAidClaimReportProcessor Fetch charity (PayId/target): " + payment.getId() + "/"
                    + payment.getPaymentTarget());
        }
        Charity charity = null;
        try {
            charity = giftAidClaimServiceProxy.fetchCharityDetailsById(new Long(
                    payment.getPaymentTarget()));
        }
        catch (NumberFormatException e) {
            LOGGER.error("Number format exception", e);
        }
        catch (GivingManagementServiceFaultException e) {
            LOGGER.error("Error occured while in GiftAidClaimReportProcessor : while calling fetchCharityDetailsById ", e);
        }
        if(charity != null) {
            giftAidClaimRecord.setCharityName(charity.getCharityName());
        }
        try {
            giftAidClaimRecord
                    .setVmgHMRCReference(giftAidClaimServiceProxy
                            .fetchVmgHMRCReference(Long.valueOf(payment
                                    .getPaymentTarget())));
        }
        catch (NumberFormatException e) {
            LOGGER.error("Number format exception", e);
        }
        catch (GivingManagementServiceFaultException e) {
            LOGGER.error("Error occured while in GiftAidClaimReportProcessor : while calling fetchVmgHMRCReference ", e);
        }
        // giftAidClaimRecord.setCharityReference(charity.getCharityReference());

        // calculate and set the accounting period end year.
        // Accounting period end date year rolled forward logic.
        // VMGCR 83 implemented.

        giftAidClaimRecord.setAccountingPeriodEndDate(Util
                .calculateAccountingPeriodEndDateYear(charity
                        .getAccountingPeriodEndDate()));
        // giftAidClaimRecord.setAccountingPeriodEndDate(charity
        // .getAccountingPeriodEndDate());

        /*
        final Donor donor =
                giftAidClaimServiceProxy.fetchDonorDetails(new Long(payment
                        .getPaymentSource()));
        giftAidClaimRecord.setDonationAmount(payment.getGrossAmount());
        giftAidClaimRecord.setDonationDate(payment.getCreatedDateTime());
        giftAidClaimRecord.setDonorSurname(donor.getSurname());
        giftAidClaimRecord.setDonorForname(donor.getForename());
        */
        Person person = null;
        try {
            person = giftAidClaimServiceProxy.fetchPersonDetailsOfDonation(new Long(payment.getPaymentSource()));
        }
        catch (NumberFormatException e) {
            LOGGER.error("Number format exception", e);
        }
        catch (GivingManagementServiceFaultException e) {
            LOGGER.error("Error occured while in GiftAidClaimReportProcessor : while calling fetchPersonDetailsOfDonation ", e);
        }
        giftAidClaimRecord.setDonationAmount(payment.getGrossAmount());
        giftAidClaimRecord.setDonationDate(payment.getCreatedDateTime());
        if(person != null){
            giftAidClaimRecord.setDonorSurname(person.getSurname());
            giftAidClaimRecord.setDonorForname(person.getForename());
        }
        giftAidClaimRecord
                .setTransitionalReliefAmount(setTransitionalReliefAmount(payment));
        updateObjectInContext(giftAidClaimRecord);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimReportProcessor::process() - END");
        }   
        return giftAidClaimRecord;

    }

    /**
     * This method is used for to get the execution context.If it is null then
     * put PaymentInitiatedObjectList.
     * 
     * @param giftAidClaimRecord of type GiftAidClaimRecord to add this object to
     * executionContextList.
     */
    private void updateObjectInContext(GiftAidClaimRecord giftAidClaimRecord) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimReportProcessor::updateObjectInContext() - START");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER
                    .debug("Inside GiftAidClaimReportProcessor UpdateObjectContext -Start: "
                            + giftAidClaimRecord.getCharityId());
        }
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        List<GiftAidClaimRecord> paymentCollectedObjectList =
                (List<GiftAidClaimRecord>) executionContext
                        .get(Constant.GIFT_AID_CLAIM_RECORD_OBJECT_LIST);
        if (paymentCollectedObjectList == null) {
            paymentCollectedObjectList = new ArrayList<GiftAidClaimRecord>();
            executionContext.put(Constant.GIFT_AID_CLAIM_RECORD_OBJECT_LIST,
                    paymentCollectedObjectList);
        }
        paymentCollectedObjectList.add(giftAidClaimRecord);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimReportProcessor::updateObjectInContext() - END");
        }

    }

    /**
     * Sets the transitional relief amount.
     * 
     * @param payment the payment
     * 
     * @return the transitional relief amount
     */
    public TransitionalReliefAmount setTransitionalReliefAmount(Payment payment) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimReportProcessor::setTransitionalReliefAmount() - START");
        }
        LastGiftAidClaim lastGiftAidClaim = fetchCurrentClaimedPeriod();
        final TransitionalReliefAmount transitionalReliefAmount =
                new TransitionalReliefAmount();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Now creating Tran Relief Amount for payment: " + payment.getId());
        }
        final TransitionalReliefStatus status = new TransitionalReliefStatus();
        status
                .setCode(MasterDataCodeConstants.TransitionalRelief.TRANSITIONAL_RELIEF_CLAIMED
                        .getCode());
        transitionalReliefAmount.setTransitionalReliefStatus(status);
        transitionalReliefAmount.setPayment(payment);
        transitionalReliefAmount.setFromDate(lastGiftAidClaim
                .getGiftAidClaimFromDate());
        transitionalReliefAmount.setToDate(lastGiftAidClaim
                .getGiftAidClaimToDate());
        transitionalReliefAmount.setCurrency(payment.getCurrency());
        transitionalReliefAmount
                .setCharityReference(payment.getPaymentTarget());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Created Tran Relief Amount for payment: " + transitionalReliefAmount.getPayment().getId());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimReportProcessor::setTransitionalReliefAmount() - END");
        }
        return transitionalReliefAmount;
    }

    /**
     * Sets the gift aid claim service proxy.
     * 
     * @param giftAidClaimServiceProxy the giftAidClaimServiceProxy to set
     */
    public void setGiftAidClaimServiceProxy(
            GiftAidClaimServiceProxy giftAidClaimServiceProxy) {
        this.giftAidClaimServiceProxy = giftAidClaimServiceProxy;
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
     * Fetch current claimed period.
     * 
     * @return the last gift aid claim
     */
    private LastGiftAidClaim fetchCurrentClaimedPeriod() {
        return paymentService.fetchLastGiftAidClaimedPeriod(Constant.CURRENT);

    }

    /** {@inheritDoc} */
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

    /** {@inheritDoc} */
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
