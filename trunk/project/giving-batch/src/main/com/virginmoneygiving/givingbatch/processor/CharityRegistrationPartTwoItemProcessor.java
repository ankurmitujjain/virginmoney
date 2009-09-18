package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;

/**
 * This class is an Item Processor for Charity Registration part two. This class
 * takes the object returned by the ItemReader creates the
 * Charity, update the process status in database and pass the
 * Charity to ItemWriter
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class CharityRegistrationPartTwoItemProcessor implements
        ItemProcessor<Charity, Charity> {
    
    /** create Logger instance for to log the information. */
    private static Logger LOGGER =
            Logger.getLogger(CharityRegistrationPartTwoItemProcessor.class);

    /** create instance of GivingService to inject in xml and call the service methods. */
    private GivingService givingService;

    /**
     * This method for process the given Job in step by step.
     * 
     * @param charity of type Charity
     * 
     * @return Charity object
     * 
     * @throws exception if process is not performed
     * @throws Exception the exception
     */
    public Charity process(Charity charity) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
        LOGGER.trace("CharityRegistrationPartTwoItemProcessor.process - Start");
                updateBatchStatus(charity.getId(), MasterDataCodeConstants.LIV_PENDING);
        }
        if(LOGGER.isTraceEnabled()) {
        LOGGER.trace("CharityRegistrationPartTwoItemProcessor.process - End");
        }
        return charity;
    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'LIV_PENDING'in the database in DB.
     * 
     * @param id of type Long.
     * @param status the status
     */
   public void updateBatchStatus(Long id, String status) {
        givingService.updateBatchStatus(id,status);
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
