package com.virginmoneygiving.givingbatch.writer;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimEnd;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimFooter;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimRecord;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimSchedule;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimStart;

//TODO-This class is not being used and need to be removed.
/**
 * This class used to write the claim start, schedule and claim end part.
 * 
 * @author Srinivas Nageli
 * @author henrij
 */
public class PaymentSettledWriter<Payment> implements ItemWriter<Payment> {

    /** instance for Logger. * */
    private static Logger LOGGER =
            Logger.getLogger(PaymentSettledWriter.class);

    /** instance for ExecutionContext. * */
    private ExecutionContext executionContext;

    /** constants. * */
    private static String CHARITY_ID = "PreviousCharityId";

    /**
     * This method used to write the claim start , schedule and claim end.
     * 
     * @param items of type List.
     * 
     * @throws Exception.      * @throws Exception the exception
     * @throws Exception the exception
     */
    public void write(List items) throws Exception {

        for (int i = 0; i < items.size(); i++) {

            GiftAidClaimRecord giftAidClaimRecord =
                    (GiftAidClaimRecord) items.get(i);

            LOGGER.debug(giftAidClaimRecord);

            // Retrieve previous Charity details
            String previousCharityId = getPreviousCharity();

            LOGGER.debug("previousCharityId: " + previousCharityId);

            boolean newCharityRecord = false;
            // if Charity is new, add a header
            if (isNewCharityRecord(giftAidClaimRecord.getCharityId())) {

                // Add Claim End before adding Claim Start, Except for the first
                // time
                if (previousCharityId != null) {
                    addClaimEnd(giftAidClaimRecord);
                }
                addClaimStart(giftAidClaimRecord);
                newCharityRecord = true;
            }

            // Add Gift Aid Schedule
            addClaimSchedule(giftAidClaimRecord);

            // Update Footer details
            updateFooter(giftAidClaimRecord, newCharityRecord);

            // Store Charity Id in Context
            setPreviousCharity(giftAidClaimRecord.getCharityId());
        }
    }

    /**
     * This method used to write the claim start.
     * 
     * @param giftAidClaimRecord the gift aid claim record
     * 
     * @throws Exception the exception
     */
    private void addClaimStart(GiftAidClaimRecord giftAidClaimRecord)
            throws Exception {
        GiftAidClaimStart giftAidClaimStart = new GiftAidClaimStart();
        giftAidClaimStart.setCharityName(giftAidClaimRecord.getCharityName());
        giftAidClaimStart.setHmrcRef(giftAidClaimRecord.getCharityReference());

        final Date date = giftAidClaimRecord.getAccountingPeriodEndDate();
        giftAidClaimStart
                .setAccountingPeriodEndDate(date != null ? new SimpleDateFormat(
                        Constant.DATE_FORMAT).format(date)
                        : null);

       // super.write(Collections.singletonList(giftAidClaimStart));
    }

    /**
     * This method used to write the claim end.
     * 
     * @param giftAidClaimRecord the giftAidClaimRecord instance.
     * 
     * @throws Exception the exception
     */
    private void addClaimEnd(GiftAidClaimRecord giftAidClaimRecord)
            throws Exception {
        //GiftAidClaimEnd giftAidClaimEnd =
                executionContext
                        .get(Constant.GIFT_AID_CLAIM_END);
      //  super.write(Collections.singletonList(giftAidClaimEnd));
    }

    /**
     * This method used to write the schedule.
     * 
     * @param giftAidClaimRecord the giftAidClaimRecord instance.
     * 
     * @throws Exception.      * @throws Exception the exception
     * @throws Exception the exception
     */
    private void addClaimSchedule(GiftAidClaimRecord giftAidClaimRecord)
            throws Exception {
        GiftAidClaimSchedule giftAidClaimSchedule = new GiftAidClaimSchedule();
        giftAidClaimSchedule.setDonationAmount(giftAidClaimRecord
                .getDonationAmount());
        giftAidClaimSchedule.setDonorSurname(giftAidClaimRecord
                .getDonorSurname());
        giftAidClaimSchedule.setDonorForename(giftAidClaimRecord
                .getDonorForname());

        final Date date = giftAidClaimRecord.getDonationDate();

        giftAidClaimSchedule
                .setDonationDate(date != null ? new SimpleDateFormat(
                        Constant.DATE_FORMAT).format(date) : null);

        // giftAidClaimStart.setCharityName(giftAidClaimRecord.charityName);
        //super.write(Collections.singletonList(giftAidClaimSchedule));
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

    }

    /**
     * This method used to check the is charity new or not.
     * 
     * @param charityId the charity id
     * 
     * @return boolean value if new returns true other wise false.
     */
    private boolean isNewCharityRecord(String charityId) {
        String previousCharityId = getPreviousCharity();
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
        return ExitStatus.COMPLETED;
    }
}
