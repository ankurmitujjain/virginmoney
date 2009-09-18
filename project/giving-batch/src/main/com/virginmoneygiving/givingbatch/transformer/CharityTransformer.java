package com.virginmoneygiving.givingbatch.transformer;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.domain.Address;
import com.virginmoneygiving.giving.domain.CharityAddress;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.Charities;
import com.virginmoneygiving.givingbatch.domain.CharityAddresses;
import com.virginmoneygiving.givingbatch.domain.CharityStatusType;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;

/**
 * The responsibility of CharityTransformer is to Transform the Charity object
 * to the CharitySupplierRecord object.
 * 
 * @author Henry Jacob
 */
public class CharityTransformer implements StepExecutionListener,
        ItemProcessor<Charity, Charities> {

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER = Logger.getLogger(CharityTransformer.class);

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;
    
    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl locatorImpl;

    /**
     * Sets the locator impl.
     * 
     * @param locatorImpl the locatorImpl to set
     */
	public void setLocatorImpl(
			GivingBatchExtManagementServiceLocatorImpl locatorImpl) {
		this.locatorImpl = locatorImpl;
	}

	/**
	 * This method used to process the charity details.
	 * The Charity object will be transformed into Charities object which
	 * is required by Oracle Financials.
	 * 
	 * @param charity of type Charity
	 * 
	 * @return Charity object
	 * 
	 * @throws Exception process the charity details throws Exception.
	 */
    public Charities process(Charity charity) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside CharityTransformer - process - Start");
        }
        Charities supplierRecord = null;
        if (charity != null) {
            supplierRecord = new Charities();
            Set<CharityAddress> addressList = charity.getCharityAddresses();

            supplierRecord.setCharityStatus(CharityStatusType.IRC);

            supplierRecord.setCharityName(charity.getName());
            supplierRecord.setRegistrationNumber(charity
                    .getRegisteredCharityNumber());
            supplierRecord.setVmgCharityRefId(charity.getId().toString());
            supplierRecord.setTransactionDate(charity.getCreatedDateTime());
            // TODO : PaymentGroup and Category are optional and can be ignored.
            // supplierRecord.setPaymentGroup(paymentGroup);
            // supplierRecord.setCategory(category);

            for (Iterator<CharityAddress> iterator = addressList.iterator(); iterator
                    .hasNext();) {
                Address address = (Address) iterator.next().getAddress();
                CharityAddresses charityAddresses = new CharityAddresses();

                if (Constant.ADMIN.equalsIgnoreCase(address.getAddressType().getCode())) {
                    charityAddresses.setAddressLine1(address.getAddressLine1());
                    charityAddresses.setAddressLine2(address.getAddressLine2());
                    charityAddresses.setCity(address.getTownCity());
                    charityAddresses.setCountyOrState(address.getCountyState());
                    charityAddresses
                            .setPostCodeOrZipCode(address.getPostCode());
                    charityAddresses.setCountry(address.getCountry().getIsoCode());
                    supplierRecord.setAdministrationAddress(charityAddresses);

                }
                else if (Constant.REGISTER.equalsIgnoreCase(address.getAddressType()
                        .getCode())) {
                    charityAddresses.setAddressLine1(address.getAddressLine1());
                    charityAddresses.setAddressLine2(address.getAddressLine2());
                    charityAddresses.setCity(address.getTownCity());
                    charityAddresses.setCountyOrState(address.getCountyState());
                    charityAddresses
                            .setPostCodeOrZipCode(address.getPostCode());
                    charityAddresses.setCountry(address.getCountry().getIsoCode());
                    supplierRecord.setRegisteredAddress(charityAddresses);
                }

            }
        
    	
        //Step 2: Here for every charity, we create the BatchEntity record.
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
    	String batchNumber = (String)context.get(Constant.BATCH_NUMBER);
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
        batchEntity.setEntityId(charity.getId().toString());
        batchEntity.setNextBatchStatus(MasterDataCodeConstants.CHARITY_STATUS_CODE_IRC);
        batchEntity.setPreviousStatus(null);
        batchEntity.setEntityTypeCode(Constant.ENTITY_CHARITY);
        batchEntity.setEntityReference(charity.getId().toString());  //HunarC: Added this
        batchEntity.setBaseReference(charity.getId().toString());    //HunarC: Added this
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch entity : CharityTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);

         }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch entity "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }

        updateObjectInContext(supplierRecord);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside CharityRegistrationTransaformer - process - End");
        }
        } // Null check 13051 ::TarunA
        return supplierRecord;
    }

    /**
     * This method is used for to get the registered charities list from execution context.
     * If it is null then add charitiesObjectList to executionContext.
     * 
     * @param charities the charities.
     */
    private void updateObjectInContext(Charities charities) {
        LOGGER
                .debug("Inside CharityTransformer UpdateObjectContext -Start");
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        List<Charities> charitiesObjectList =
                (List<Charities>) executionContext
                        .get(Constant.REGISTER_CHARITY_OBJECT_LIST);
        if (charitiesObjectList == null) {
            charitiesObjectList = new ArrayList<Charities>();
            executionContext.put(Constant.REGISTER_CHARITY_OBJECT_LIST,
                    charitiesObjectList);
        }
        charitiesObjectList.add(charities);
        LOGGER
                .debug("Inside CharityTransformer UpdateObjectContext -End.");
        
    }

    /**
     * This method will execute after step completes and returns status.
     * This method will update the batch status to BATCH_STATUS_SUCCESSFUL or
     * BATCH_STATUS_FAIL along with the reasons for the same.
     * 
     * @param stepExecution of type StepExecution
     * 
     * @return Exit Status after completion of step.
     */
    public ExitStatus afterStep(StepExecution stepExecution) {
        LOGGER.debug("inside afterStep method ");
        ExecutionContext executionContext = this.stepExecution.getJobExecution().getExecutionContext();
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "YES");
        List<Charities> charitiesObjectList = (List<Charities>) executionContext
                        .get(Constant.REGISTER_CHARITY_OBJECT_LIST);
        if (charitiesObjectList == null || charitiesObjectList.size() < 1) {
            LOGGER.debug("inside afterStep method - No matching records found ... setting batch status to completed.");
            try {
                UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setEndDateTime(Util.buildXMLGregorianDate());
                request.setStatus(Constant.BATCH_STATUS_SUCCESSFUL);
                request.setErrorMessage("No records found.");
                locatorImpl.getGivingBatchExtManagementPort().updateBatchStatus(request);
                executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "NO");
            }
            catch (GivingBatchExtManagementServiceFaultException exception) {
                LOGGER.error("Unexpected error setting batch status to complete when no records found"+ exception.getMessage(), exception);
            }
        }
        return ExitStatus.COMPLETED;
    }

    /**
     * This method is called before step starts and assign the step execution
     * values.
     * This method is used to create a batch record before starting the actual batch processing
     * and to populate the control data columns.
     * 
     * @param stepExecution of type StepExecution
     */
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.debug("inside before step  execution context:" + stepExecution);
        this.stepExecution = stepExecution;
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchGivingControlData(jobName);

        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(Constant.REGISTER_CHARITY);
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("charityRegistrationJob");
        serviceBatch.setNextBatchName("CharityRegistrationPartTwo");
        createBatchRequest.setServiceBatch(serviceBatch);
        
        try{
        	locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	context.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch : CharityTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);

         }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
        
    }

}
