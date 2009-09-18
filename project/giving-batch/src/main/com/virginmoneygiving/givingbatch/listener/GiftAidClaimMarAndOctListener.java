package com.virginmoneygiving.givingbatch.listener;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimRecord;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimedPeriod;
import com.virginmoneygiving.givingbatch.serviceproxy.GiftAidClaimServiceProxy;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.LastGiftAidClaim;
import com.virginmoneygiving.payment.domain.TransitionalReliefAmount;
import com.virginmoneygiving.payment.service.PaymentService;
import com.virginmoneygiving.paymentmanagement.service.messages.PaymentManagementServiceFaultException;

/**
 * This listener will write in the last gift aid record after successful
 * execution of the Gift aid claim batch job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author taruna
 */
public class GiftAidClaimMarAndOctListener extends StepExecutionListenerSupport
		implements Tasklet,ItemWriteListener {

	/** create instance of logger. */
	private static final Logger LOGGER = Logger
			.getLogger(GiftAidClaimMarAndOctListener.class);

	/** create instance of batch delegate to injectinside xml. */
	private BatchDelegate batchDelegate;

	/** create instance of PaymentService. */
	private PaymentService paymentService;

	/** create instance of stepExecution. */
	private StepExecution stepExecution;

	/** gift Aid Claim Service Proxy. * */
	private GiftAidClaimServiceProxy giftAidClaimServiceProxy;

	/**
	 * Sets the batch delegate.
	 * 
	 * @param batchDelegate the batchDelegate to set
	 */
	public void setBatchDelegate(BatchDelegate batchDelegate) {
		this.batchDelegate = batchDelegate;
	}

	/**
	 * This method will assign the step execution context whenever transformer
	 * called.
	 * 
	 * @param stepExecution of tyep StepExecution
	 */
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	/**
	 * Send batch to GLIS.
	 * 
	 * @param ignored not used
	 */
	public void afterWrite(List ignored) {
		
	}

	/**
	 * Unimplemented.
	 * 
	 * @param ignored not used
	 */
	public void beforeWrite(List ignored) {
		

	}

	/**
	 * Unimplmented.
	 * 
	 * @param exception not used
	 * @param list not used
	 */
	public void onWriteError(Exception exception, List list) {
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
		return paymentService.fetchLastGiftAidClaimedPeriod("current");

	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.springframework.batch.core.StepContribution, org.springframework.batch.core.scope.context.ChunkContext)
	 */
	public RepeatStatus execute(StepContribution stepContribution,
	        ChunkContext chunkContext) throws Exception {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimMarAndOctListener::process() - START");
        }
        Long recordCount = new Long(0);
        BigDecimal totalGAAmount = new BigDecimal(0);

        LastGiftAidClaim lastGiftAidClaim = new LastGiftAidClaim();
		lastGiftAidClaim = fetchCurrentClaimedPeriod();
		lastGiftAidClaim.setStatus(Constant.PREVIOUS);
	    lastGiftAidClaim.setUpdatedProcess("giftAidClaimProcessJob");
	    lastGiftAidClaim.setUpdatedIPAddress(Util.getHostIpaddress());
		paymentService.saveLastGiftAidClaim(lastGiftAidClaim);

		GiftAidClaimedPeriod nextGiftAidClaimedPeriod = Util
				.getNextClaimPeriod(lastGiftAidClaim.getGiftAidClaimToDate());

		LastGiftAidClaim nextGiftAidClaim = new LastGiftAidClaim();
		nextGiftAidClaim.setStatus(Constant.CURRENT);
		nextGiftAidClaim.setGiftAidClaimFromDate(nextGiftAidClaimedPeriod
				.getClaimPeriodFrom());
		nextGiftAidClaim.setGiftAidClaimToDate(nextGiftAidClaimedPeriod
				.getClaimPeriodTo());
        nextGiftAidClaim.setCreatedProcess("giftAidClaimProcessJob");
        nextGiftAidClaim.setCreatedIPAddress(Util.getHostIpaddress());
		paymentService.saveLastGiftAidClaim(nextGiftAidClaim);

		if (LOGGER.isDebugEnabled()) {
    		LOGGER
    				.debug("############################################################");
    		LOGGER.debug("Next From Date :"
    				+ nextGiftAidClaimedPeriod.getClaimPeriodFrom());
    		LOGGER.debug("Next To Date :"
    				+ nextGiftAidClaimedPeriod.getClaimPeriodTo());
    		LOGGER
    				.debug("############################################################");
		}

		ExecutionContext executionContext = stepExecution.getJobExecution()
				.getExecutionContext();
		LOGGER.debug("Exceution context is:" + executionContext);
		if (executionContext != null) {
			List<GiftAidClaimRecord> giftAidClaimRecordList = (List<GiftAidClaimRecord>) executionContext
					.get(Constant.GIFT_AID_CLAIM_RECORD_OBJECT_LIST);
			if (giftAidClaimRecordList != null) {

				
				List<Object[]> list = paymentService
						.fetchSumGiftAidDueToCharityForMarAndOct();

				String previousCharityId = null;
				for (GiftAidClaimRecord giftAidClaimRecord : giftAidClaimRecordList) {
                    recordCount++;
                    totalGAAmount = totalGAAmount.add(giftAidClaimRecord.getGiftAidAmount());

                    TransitionalReliefAmount transitionalReliefAmount = giftAidClaimRecord
							.getTransitionalReliefAmount();

                    //transitionalReliefAmount.getPayment().setId(new Long(1));

                    for (int i = 0; i < list.size(); i++) {
						Object[] objects = list.get(i);
						BigDecimal amount = (BigDecimal) objects[0];
						String charityId = (String) objects[1];
						if (LOGGER.isDebugEnabled()) {
    						LOGGER.debug("########### Sum of Gift Aid Group by Charity ################");
    						LOGGER.debug("########### Amount : "+ amount+"################");
    						LOGGER.debug("########### Amount : "+ charityId+"################");
    						LOGGER.debug("########### Sum of Gift Aid Group by Charity ################");
						}
						if (transitionalReliefAmount.getCharityReference()
								.equals(charityId)) {

							if ((previousCharityId == null)
									|| (!previousCharityId
											.equals(transitionalReliefAmount
													.getCharityReference()))) {

								saveSummaryTransitionalReliefByCharity(
										transitionalReliefAmount, amount);
							}

							previousCharityId = charityId;
						}
					}

				}
				if (LOGGER.isDebugEnabled()) {
				    LOGGER.debug("GiftAidClaimMarAndOctListener-Final Number/Amount:" + recordCount + "/" + totalGAAmount);
				}
                lastGiftAidClaim.setClaimAmount(totalGAAmount);
                lastGiftAidClaim.setNoOfRecords(recordCount);
                paymentService.saveLastGiftAidClaim(lastGiftAidClaim);
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("GiftAidClaimMarAndOctListener::process() - END");
                }

            }

		}
		return RepeatStatus.FINISHED;
	}

	/**
	 * Save summary transitional relief by charity.
	 * 
	 * @param transitionalReliefAmount the transitional relief amount
	 * @param giftAidAmount the gift aid amount
	 * 
	 * @throws Exception the exception
	 */
	private void saveSummaryTransitionalReliefByCharity(
			TransitionalReliefAmount transitionalReliefAmount,
			BigDecimal giftAidAmount) throws PaymentManagementServiceFaultException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimMarAndOctListener::saveSummaryTransitionalReliefByCharity() - START");
        }
	    if (LOGGER.isDebugEnabled()) {
    		LOGGER.debug("GiftAidClaimMarAndOctListener-saveSummaryTransitionalReliefByCharity GA Amt: " + giftAidAmount);
            LOGGER.debug("GiftAidClaimMarAndOctListener-saveSummaryTransitionalReliefByCharity Payment Record: "
                    + transitionalReliefAmount.getPayment().toString());
	    }

        if (MasterDataCodeConstants.PaymentType.PAYMENT_TYPE_WEB_DONATION
				.getCode().equalsIgnoreCase(
						transitionalReliefAmount.getPayment().getPaymentType()
								.getCode())) {
			transitionalReliefAmount
					.setTransitionalAmount(giftAidClaimServiceProxy
							.calculateTransitionalRelief(giftAidAmount,
									"WEB_DONATION"));
			if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("GiftAidClaimMarAndOctListener-saveSummaryTransitionalReliefByCharity TrnRel Amt: "
                        + transitionalReliefAmount.getTransitionalAmount());
			}
        }

		transitionalReliefAmount.setFinanceReference(giftAidClaimServiceProxy
				.generateSequence("TRANSITIONAL_RELIEF", Constant.INVOICE));
		paymentService.saveTransitionalReliefAmount(transitionalReliefAmount);
		if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GiftAidClaimMarAndOctListener-saveTransitionalReliefAmount Id/Amount:"
                    + transitionalReliefAmount.getId() + "/" + transitionalReliefAmount.getTransitionalAmount());
		}
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimMarAndOctListener::saveSummaryTransitionalReliefByCharity() - END");
        }
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

}
