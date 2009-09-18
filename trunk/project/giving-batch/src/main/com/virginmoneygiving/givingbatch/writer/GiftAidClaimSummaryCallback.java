package com.virginmoneygiving.givingbatch.writer;

import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.apache.log4j.Logger;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimEnd;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimFooter;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimHeader;
import com.virginmoneygiving.payment.domain.PreviousGiftAidClaimDetails;
import com.virginmoneygiving.payment.domain.LastGiftAidClaim;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class used to prepare the control xml output report by writing the header and footer part.
 * 
 * @author Srinivas Nageli
 */
public class GiftAidClaimSummaryCallback extends StepExecutionListenerSupport
        implements FlatFileFooterCallback, FlatFileHeaderCallback {

/** instance for logger. * */
    private static final Logger LOGGER =
            Logger.getLogger(GiftAidClaimSummaryCallback.class);
    
    /** LineAggregator for GiftAidClaimFooter. */
    private LineAggregator<GiftAidClaimFooter> giftAidClaimFooterAggregator;

    /** LineAggregator for GiftAidClaimEnd. */
    private LineAggregator<GiftAidClaimEnd> giftAidClaimEndAggregator;

    /** LineAggregator for GiftAidClaimHeader. */
    private LineAggregator<GiftAidClaimHeader> giftAidClaimHeaderAggregator;

    /** instance for ExecutionContext. */
    private ExecutionContext executionContext;

    /** constants. */
    private static final String LINE_SEPARATOR =
            System.getProperty("line.separator");

    /** The Constant PREVIOUS_GIFTAID_CLAIM_DETAILS. */
    private static final String PREVIOUS_GIFTAID_CLAIM_DETAILS = "Previous_GiftAid_Claim_Details";

    /** payment Service. * */
    private PaymentService paymentService;

	/**
	 * Sets the payment service.
	 * 
	 * @param paymentService the paymentService to set
	 */
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

    /**
     * This method used to write the header.
     * 
     * @param writer the writer
     * 
     * @throws IOException exception
     */
    public void writeHeader(Writer writer) throws IOException {

        LOGGER.debug("Now starting GiftAidClaimSummaryCallback.writeHeader");
        GiftAidClaimHeader giftAidClaimHeader = new GiftAidClaimHeader();
        LastGiftAidClaim previousGADetails = (LastGiftAidClaim)
                executionContext.get(PREVIOUS_GIFTAID_CLAIM_DETAILS);

        giftAidClaimHeader.setJobStartDate(new SimpleDateFormat(
                Constant.DATE_TIME_FORMAT).format(new Timestamp(System.currentTimeMillis())));

        if (previousGADetails != null && previousGADetails.getGiftAidClaimFromDate() != null
                && previousGADetails.getGiftAidClaimToDate() != null) {
            LOGGER.debug("Using previous claim dates (From/To): " + previousGADetails.getGiftAidClaimFromDate()
                    + ", " + previousGADetails.getGiftAidClaimToDate());
            giftAidClaimHeader.setClaimPeriodStart(new SimpleDateFormat(
                    Constant.DATE_FORMAT).format(previousGADetails.getGiftAidClaimFromDate()));
            giftAidClaimHeader.setClaimPeriodEnd(new SimpleDateFormat(
                    Constant.DATE_FORMAT).format(previousGADetails.getGiftAidClaimToDate()));
        }
        else {
            giftAidClaimHeader.setClaimPeriodStart(new SimpleDateFormat(
                    Constant.DATE_FORMAT).format(Util.getClaimPeriodStartDate()));
            giftAidClaimHeader.setClaimPeriodEnd(new SimpleDateFormat(
                    Constant.DATE_FORMAT).format(Util.getClaimPeriodEndDate()));

            LOGGER.debug("Using Calculated claim dates (From/To): " + giftAidClaimHeader.getClaimPeriodStart()
                    + ", " + giftAidClaimHeader.getClaimPeriodEnd());
        }

        //writer.write(giftAidClaimHeaderAggregator.aggregate(giftAidClaimHeader) + LINE_SEPARATOR);

        writer.write(giftAidClaimHeaderAggregator.aggregate(giftAidClaimHeader));

    }

    /**
     * This method used to write the claim end and footer.
     * 
     * @param writer the writer
     * 
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void writeFooter(Writer writer) throws IOException {
        LOGGER.debug("*** In GiftAidClaimSummaryCallback writeFooter *** ");

        // Get Claim End from ExecutionContext
        GiftAidClaimEnd giftAidClaimEnd =
                (GiftAidClaimEnd) executionContext
                        .get(Constant.GIFT_AID_CLAIM_END);
        if ( giftAidClaimEnd == null ) {
        	giftAidClaimEnd = new GiftAidClaimEnd();
        }
        writer.write(giftAidClaimEndAggregator.aggregate(giftAidClaimEnd)
                + LINE_SEPARATOR);

        // Get Claim Footer from ExecutionContext
        GiftAidClaimFooter giftAidClaimFooter =
                (GiftAidClaimFooter) executionContext
                        .get(Constant.GIFT_AID_CLAIM_FOOTER);
        if ( giftAidClaimFooter == null ) {
        	giftAidClaimFooter = new GiftAidClaimFooter();
        }
        writer
                .write(giftAidClaimFooterAggregator
                        .aggregate(giftAidClaimFooter));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {
        //this.executionContext = stepExecution.getExecutionContext();
        executionContext = stepExecution.getJobExecution().getExecutionContext();
        LastGiftAidClaim previousGAClaim = fetchPreviousClaimedPeriod();
        executionContext.put(PREVIOUS_GIFTAID_CLAIM_DETAILS, previousGAClaim);
    }

    /**
     * Sets the gift aid claim footer aggregator.
     * 
     * @param giftAidClaimFooterAggregator the giftAidClaimFooterAggregator to set
     */
    public void setGiftAidClaimFooterAggregator(
            LineAggregator<GiftAidClaimFooter> giftAidClaimFooterAggregator) {
        this.giftAidClaimFooterAggregator = giftAidClaimFooterAggregator;
    }

    /**
     * Sets the gift aid claim header aggregator.
     * 
     * @param giftAidClaimHeaderAggregator the giftAidClaimHeaderAggregator to set
     */
    public void setGiftAidClaimHeaderAggregator(
            LineAggregator<GiftAidClaimHeader> giftAidClaimHeaderAggregator) {
        this.giftAidClaimHeaderAggregator = giftAidClaimHeaderAggregator;
    }

    /**
     * Sets the gift aid claim end aggregator.
     * 
     * @param giftAidClaimEndAggregator the giftAidClaimEndAggregator to set
     */
    public void setGiftAidClaimEndAggregator(
            LineAggregator<GiftAidClaimEnd> giftAidClaimEndAggregator) {
        this.giftAidClaimEndAggregator = giftAidClaimEndAggregator;
    }

    /**
     * Fetch previous claimed period.
     * 
     * @return the last gift aid claim
     */
    private LastGiftAidClaim fetchPreviousClaimedPeriod(){
        //return paymentService.findLastGiftAidClaimed();
        return paymentService.fetchLastGiftAidClaimedPeriod("current");
    }

}
