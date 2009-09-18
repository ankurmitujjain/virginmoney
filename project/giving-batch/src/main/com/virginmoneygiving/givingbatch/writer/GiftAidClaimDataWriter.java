package com.virginmoneygiving.givingbatch.writer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimEnd;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimFooter;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimRecord;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimSchedule;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimStart;
import com.virginmoneygiving.givingbatch.serviceproxy.GiftAidClaimServiceProxy;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class used to prepare the detailed control output xml report by writing
 * the claim start, schedule and claim end part.
 * 
 * @author Srinivas Nageli
 * @author henrij
 */
public class GiftAidClaimDataWriter extends FlatFileItemWriter implements
        StepExecutionListener {

    /** Instance for Logger. * */
    private static Logger LOGGER =
            Logger.getLogger(GiftAidClaimDataWriter.class);

    /** Instance for ExecutionContext. * */
    private ExecutionContext executionContext;
    
    /** Payment Service. * */
    private PaymentService paymentService;

    /** Constants. * */
    private static String CHARITY_ID = "PreviousCharityId";
    
    /** gift Aid Claim Service Proxy. * */
    private GiftAidClaimServiceProxy giftAidClaimServiceProxy;

    /** The final gift aid record. */
    GiftAidClaimRecord finalGARecord = null;
    
    /**
     * This method used to write the claim start , schedule and claim end.
     * 
     * @param items of type List.
     * 
     * @throws Exception the exception
     */
    public void write(List items) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::write() - START");
        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Starting GiftAidClaimDataWriter with list size: " + items.size());
        }
        
        for (int i = 0; i < items.size(); i++) {

            GiftAidClaimRecord giftAidClaimRecord =
                    (GiftAidClaimRecord) items.get(i);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("GiftAidClaimDataWriter-GA Record: " + giftAidClaimRecord);
            }
            // Retrieve previous Charity details
            String previousCharityId = getPreviousCharity();
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("previousCharityId/This Charity: " + previousCharityId + "/" + giftAidClaimRecord.getCharityId());
            }
            boolean newCharityRecord = false;
            // if Charity is new, add a header
            if (isNewCharityRecord(giftAidClaimRecord.getCharityId())) {
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Processing new Charity: " + giftAidClaimRecord.getCharityId());
                }
                // Add Claim End before adding Claim Start, Except for the first
                // time
                
            	if (previousCharityId != null) {
            	    if(LOGGER.isDebugEnabled()) {    
            	        LOGGER.debug("Adding end for : " + previousCharityId);
            	    }
                    addClaimEnd(giftAidClaimRecord);
                }
            	if(LOGGER.isDebugEnabled()) {
            	    LOGGER.debug("Adding start for Charity: " + giftAidClaimRecord.getCharityId());
            	}
                addClaimStart(giftAidClaimRecord);
                newCharityRecord = true;
            }      
            

            // Add Gift Aid Schedule
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Adding Claim Schedule Charity: " + giftAidClaimRecord.getCharityId());
            }
            addClaimSchedule(giftAidClaimRecord);

            // Update Footer details
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Updating footer Charity: " + giftAidClaimRecord.getCharityId());
            }
            updateFooter(giftAidClaimRecord, newCharityRecord);

            // Store Charity Id in Context
            setPreviousCharity(giftAidClaimRecord.getCharityId());

            finalGARecord = giftAidClaimRecord;
           
        }
        
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::write() - END");
        }

    }

    /**
     * This method used to write the claim start.
     * 
     * @param giftAidClaimRecord the gift aid claim record
     * 
     * @throws Exception the exception
     */
    private void addClaimStart(GiftAidClaimRecord giftAidClaimRecord){
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::addClaimStart() - START");
        }
        GiftAidClaimStart giftAidClaimStart = new GiftAidClaimStart();
        giftAidClaimStart.setCharityName(giftAidClaimRecord.getCharityName());
        giftAidClaimStart.setHmrcRef(giftAidClaimRecord.getVmgHMRCReference());

        final Date date = giftAidClaimRecord.getAccountingPeriodEndDate();
        giftAidClaimStart
                .setAccountingPeriodEndDate(date != null ? new SimpleDateFormat(
                        Constant.DATE_FORMAT).format(date)
                        : null);

        try {
            super.write(Collections.singletonList(giftAidClaimStart));
        }
        catch (Exception e) {
            LOGGER.error("Error occured in addClaimStart while writing data",e);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::addClaimStart() - END");
        }
    }

    /**
     * This method used to write the claim end.
     * 
     * @param giftAidClaimRecord the giftAidClaimRecord instance.
     * 
     * @throws Exception the exception
     */
    private void addClaimEnd(GiftAidClaimRecord giftAidClaimRecord) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::addClaimEnd() - START");
        }
        GiftAidClaimEnd giftAidClaimEnd =
                (GiftAidClaimEnd) executionContext
                        .get(Constant.GIFT_AID_CLAIM_END);
        /*Integer don = Util.addLeftZeroPadding(giftAidClaimEnd.getDonationCount());
        giftAidClaimEnd.setDonationCount(don);
        BigDecimal donationTotal = Util.convertInPense(giftAidClaimEnd.getDonationTotal());
        BigDecimal giftAidTotal = Util.convertInPense(giftAidClaimEnd.getGiftAidTotal());
        giftAidClaimEnd.setDonationTotal(donationTotal);
        giftAidClaimEnd.setGiftAidTotal(giftAidTotal);*/
        try {
            super.write(Collections.singletonList(giftAidClaimEnd));
        }
        catch (Exception e) {
            LOGGER.error("Error occured in addClaimStart while writing data",e);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::addClaimEnd() - END");
        }
    }

    /**
     * This method used to write the schedule.
     * 
     * @param giftAidClaimRecord the giftAidClaimRecord instance.
     * 
     * @throws Exception the exception
     */
    private void addClaimSchedule(GiftAidClaimRecord giftAidClaimRecord) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::addClaimSchedule() - START");
        }
        GiftAidClaimSchedule giftAidClaimSchedule = new GiftAidClaimSchedule();
        BigDecimal donationAmount = Util.convertInPense(giftAidClaimRecord.getDonationAmount());
        giftAidClaimSchedule.setDonationAmount(donationAmount);
        giftAidClaimSchedule.setDonorSurname(giftAidClaimRecord
                .getDonorSurname());
        giftAidClaimSchedule.setDonorForename(giftAidClaimRecord
                .getDonorForname());

        final Date date = giftAidClaimRecord.getDonationDate();

        giftAidClaimSchedule
                .setDonationDate(date != null ? new SimpleDateFormat(
                        Constant.DATE_FORMAT).format(date) : null);

        // giftAidClaimStart.setCharityName(giftAidClaimRecord.charityName);
        
        try {
            super.write(Collections.singletonList(giftAidClaimSchedule));
        }
        catch (Exception e) {
            LOGGER.error("Error occured in addClaimStart while writing data",e);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::addClaimSchedule() - END");
        }
    }

    /**
     * This method used to update the claim end and footer. </p> Using this
     * method calculate the total gift aid amount, total donations and total
     * charities.
     * 
     * @param giftAidClaimRecord the gift aid claim record.
     * @param newCharityRecord boolean value is charity new or not.
     */
    private void updateFooter(GiftAidClaimRecord giftAidClaimRecord,
            boolean newCharityRecord) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::updateFooter() - START");
        }
        // update count and amount
        GiftAidClaimEnd giftAidClaimEnd =
                (GiftAidClaimEnd) executionContext
                        .get(Constant.GIFT_AID_CLAIM_END);
        if (giftAidClaimEnd == null || newCharityRecord) {
            giftAidClaimEnd = new GiftAidClaimEnd();
            executionContext.put(Constant.GIFT_AID_CLAIM_END, giftAidClaimEnd);
        }
        GiftAidClaimFooter giftAidClaimFooter =
                (GiftAidClaimFooter) executionContext
                        .get(Constant.GIFT_AID_CLAIM_FOOTER);
        if (giftAidClaimFooter == null) {
            giftAidClaimFooter = new GiftAidClaimFooter();
            executionContext.put(Constant.GIFT_AID_CLAIM_FOOTER,
                    giftAidClaimFooter);
        }

        // Set or Update GiftAidClaimEnd
        giftAidClaimEnd.updateDonationCount();
        giftAidClaimEnd.updateDonationTotal(giftAidClaimRecord
                .getDonationAmount());
        giftAidClaimEnd.updateGiftAidTotal(giftAidClaimRecord
                .getGiftAidAmount());

        // Set or Update GiftAidClaimFooter
        if (newCharityRecord) {
            giftAidClaimFooter.updateCharityCount();
        }
        giftAidClaimFooter.updateDonationCount();
        giftAidClaimFooter.setJobEndDateTime(new SimpleDateFormat(
                Constant.DATE_TIME_FORMAT).format(new Date(System
                .currentTimeMillis())));
        giftAidClaimFooter.updateDonationTotal(giftAidClaimRecord
                .getDonationAmount());
        giftAidClaimFooter.updateGiftAidTotal(giftAidClaimRecord
                .getGiftAidAmount());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::updateFooter() - END");
        }
    }

    /**
     * This method used to check the is charity new or not.
     * 
     * @param charityId the charity id
     * 
     * @return boolean value if new returns true other wise false.
     */
    private boolean isNewCharityRecord(String charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::isNewCharityRecord() - START");
        }
        String previousCharityId = getPreviousCharity();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimDataWriter::isNewCharityRecord() - END");
        }
        return (charityId.equalsIgnoreCase(previousCharityId) == false ? true
                : false);
    }

    /**
     * This method used to store the charity id in context.
     * 
     * @param charityId the charity id
     */
    private void setPreviousCharity(String charityId) {
        executionContext.putString(CHARITY_ID, charityId);
    }
    
        
    /**
     * This method used to retrieve charity id from context.
     * 
     * @return the charity id.
     */
    private String getPreviousCharity() {
        String result = null;
        if (executionContext.containsKey(CHARITY_ID)) {
            result = executionContext.getString(CHARITY_ID);
        }
        return result;
    }
    
    
    /** {@inheritDoc} **/
    public void beforeStep(StepExecution stepExecution) {
        executionContext = stepExecution.getJobExecution().getExecutionContext();
    }

    /** {@inheritDoc} **/
    public ExitStatus afterStep(StepExecution stepExecution) {
        /*
        LOGGER.debug("GiftAidClaimDataWriter AfterStep started ");
        try {
            if (finalGARecord != null) {
                LOGGER.debug("GiftAidClaimDataWriter AfterStep: " + finalGARecord.getCharityId());
                //addClaimEnd(finalGARecord);
            }
        }
        catch (Exception e) {
            LOGGER.error("Unexpected error in GiftAidClaimDataWriter AfterStep", e);
        }
        */
        return ExitStatus.COMPLETED;
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
    
	
}
