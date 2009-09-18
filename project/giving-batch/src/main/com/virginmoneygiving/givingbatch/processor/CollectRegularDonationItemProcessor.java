package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.giving.domain.Donation;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;

/**
 * This Item processor collects the daily regular donation records.
 * 
 * @author Tarun Adiwal.
 */
public class CollectRegularDonationItemProcessor implements
        ItemProcessor<Donation, Donation> {

    /** Logger instance. */
    private static Logger LOGGER =
            Logger.getLogger(EventRegistrationPaymentInitiatedProcessor.class);

    /** GivingService service. * */
    private GivingService givingService;

    /** {@inheritDoc} */
    public Donation process(Donation donation) throws GivingBatchException {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemProcessor::process() - START");
        }

        updateBatchStatus(
                donation.getRegularDonation().getId(),"REGDONCOLLECTED" );
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CollectRegularDonationItemProcessor::process() - END");
        }
        return donation;

    }

    //Currently this method is not being used.But in future this method can be used for updating the batch Status.
    /**
     * The updateBatchStatus method calls givingService and updates the
     * batch status to 'REGDONCOLLECTED'in the database in DB.
     * 
     * @param id of type Long.
     * @param status of type String.
     */
    public void updateBatchStatus(Long id, String status) {
        LOGGER
                .trace("CollectRegularDonationItemProcessor.updateBatchStatus - Start");
       // givingService.updateStatus(id, status);
        LOGGER
                .trace("CollectRegularDonationItemProcessor.updateBatchStatus - End");
    }

    /**
     * Sets the giving service.
     * 
     * @param givingService the givingService to set
     */
    public void setGivingService(GivingService givingService) {
        this.givingService = givingService;
    }

}
