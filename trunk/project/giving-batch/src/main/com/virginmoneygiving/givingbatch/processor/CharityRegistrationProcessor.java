package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;

/**
 * This class is an Item Processor for Charity Registration. This class takes
 * the object returned by the ItemReader creates the Charity record,
 * update the process status in database and pass the Charity to
 * ItemWriter
 * 
 * @author Tarun Adiwal
 */
public class CharityRegistrationProcessor implements
        ItemProcessor<Charity, Charity> {

    /** instance for logger. * */
    private static Logger LOGGER =
            Logger.getLogger(CharityRegistrationProcessor.class);

    /** giving Service. * */
    private GivingService givingService;

    /** {@inheritDoc} */
    public Charity process(Charity charity) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("CharityRegistrationProcessor.process - Start");
        }
        updateBatchStatus(charity.getId(),MasterDataCodeConstants.IRC_PENDING);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("CharityRegistrationProcessor.process - End");
        }
        return charity;
    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * batch status to 'IRC_PENDING'in the database in DB.
     * 
     * @param id of Payment
     * @param status the status
     */
   public void updateBatchStatus(Long id, String status) {
       if(LOGGER.isTraceEnabled()) {
           LOGGER.trace("CharityRegistrationProcessor.updateBatchStatus - Start");
       }
        givingService.updateBatchStatus(id,status);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("CharityRegistrationProcessor.updateBatchStatus - End");
        }
    }

    /**
     * Sets the giving service.
     * 
     * @param givingService the giving service
     */
    public void setGivingService(GivingService givingService) {
        this.givingService = givingService;

    }

}
