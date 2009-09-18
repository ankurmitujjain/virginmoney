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
import com.virginmoneygiving.giving.domain.BankAccount;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.CharityAddress;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.BankAccountType;
import com.virginmoneygiving.givingbatch.domain.Charities;
import com.virginmoneygiving.givingbatch.domain.CharityAddresses;
import com.virginmoneygiving.givingbatch.domain.CharityStatusType;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;

/**
 * The responsibility of this class is to take the Object returned by the
 * charityRegistrationPartTwoJpaPagingItemReader and converts it to an
 * CharitySupplier Record required by Oracle Financial service.
 * 
 * @author Tarun Adiwal
 */
public class CharityRegistrationPartTwoTransformer implements
        StepExecutionListener, ItemProcessor<Charity, Charities> {

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(CharityRegistrationPartTwoTransformer.class);

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
                    .trace("Inside CharityRegistrationPartTwoTransformer - process - Start");
        }
        Charities supplierRecord = null;
        
        if (charity != null) {
            supplierRecord = new Charities();
            Set<CharityAddress> addressSet = charity.getCharityAddresses();
            Set<BankAccount> bankAccountSet = charity.getBankAccounts();
            List<BankAccountType> bankAccounts = new ArrayList<BankAccountType>();
            supplierRecord.setCharityStatus(CharityStatusType.LIVE);
            supplierRecord.setCharityName(charity.getName());
            supplierRecord.setRegistrationNumber(charity
                    .getRegisteredCharityNumber());
            supplierRecord.setVmgCharityRefId(charity.getId().toString());
            for (Iterator<BankAccount> iterator = bankAccountSet.iterator(); iterator.hasNext();) {
                BankAccount bankAccount = (BankAccount) iterator.next();
                if (bankAccount.getAccountStatus().getCode().equalsIgnoreCase(MasterDataCodeConstants.ACTIVE)) {
                    BankAccountType accountType = new BankAccountType();
                    accountType.setAccountNumber(bankAccount.getAccountNumber());
                    accountType.setAccountType(bankAccount.getBankAccountType().getCode());
                    accountType.setBankName(bankAccount.getBank().getName());
                    //HunarC: added the following
                    accountType.setAccountName(bankAccount.getAccountName());
                    if(bankAccount.getSortCode() != null && bankAccount.getAccountNumber() != null) {
                        String accountNumber = Util.getBankAccountNumber(bankAccount.getAccountNumber());
                        String bankSortCode = Util.getSortCode(bankAccount.getSortCode());
                        accountType.setAccountUniqueId(bankSortCode.concat(accountNumber));
                        LOGGER.debug("***** Unique ID ***** :"+bankSortCode.concat(accountNumber));
                    }
                    accountType.setBranchName(bankAccount.getBank().getName());
                    // HunarC-Addition over
                    accountType.setSortCode(Util.getSortCode(bankAccount.getSortCode()));
                    bankAccounts.add(accountType);
                }
            }
            supplierRecord.setBankAccounts(bankAccounts);
            // TODO : Need clarification from business
            // supplierRecord.setTransactionDate(charity.geCreatedDateTime);
            // supplierRecord.setPaymentGroup(paymentGroup);
            // supplierRecord.setCategory(category);
            
            for (Iterator<CharityAddress> iterator = addressSet.iterator(); iterator
                    .hasNext();) {
                Address address = (Address) iterator.next().getAddress();
                CharityAddresses charityAddresses = new CharityAddresses();

                if (Constant.ADMIN.equals(address.getAddressType().getCode())) {
                    charityAddresses.setAddressLine1(address.getAddressLine1());
                    charityAddresses.setAddressLine2(address.getAddressLine2());
                    charityAddresses.setCity(address.getTownCity());
                    charityAddresses.setCountyOrState(address.getCountyState());
                    charityAddresses
                            .setPostCodeOrZipCode(address.getPostCode());
                    charityAddresses.setCountry(address.getCountry().getIsoCode());
                    supplierRecord.setAdministrationAddress(charityAddresses);

                }
                else if (Constant.REGISTER.equals(address.getAddressType()
                        .getCode())) {
                    charityAddresses.setAddressLine1(address.getAddressLine1());
                    charityAddresses.setAddressLine2(address.getAddressLine2());
                    charityAddresses.setCountyOrState(address.getCountyState());
                    charityAddresses.setCity(address.getTownCity());
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

        batchEntity.setEntityReference(charity.getId().toString());  //HunarC: Added this
        batchEntity.setBaseReference(charity.getId().toString());    //HunarC: Added this

        batchEntity.setNextBatchStatus(MasterDataCodeConstants.CHARITY_STATUS_CODE_LIV);
        batchEntity.setPreviousStatus(MasterDataCodeConstants.CHARITY_STATUS_CODE_IRC);
        batchEntity.setEntityTypeCode(Constant.ENTITY_CHARITY);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch entity : CharityRegistrationPartTwoTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER, "Entity Reference"+charity.getId().toString(), batchExtManagementServiceFaultException);

         }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch entity "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER, "Entity Reference"+charity.getId().toString(), exception);
        }

        updateObjectInContext(supplierRecord);
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside CharityRegistrationPartTwoTransformer - process -End");
        }
        }                           // Null check 13051 :: TarunA
        return supplierRecord;
    }

    /**
     * This method is used for to get the registered charities list from execution context.
     * If it is null then add charitiesObjectList to executionContext.
     * 
     * @param charities the charities.
     */
    private void updateObjectInContext(Charities charities) {
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
                LOGGER.error("Unexpected error setting batch status to complete when no records found" +exception.getMessage(),exception);
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
        String batchNumber = Util.getGeneratedBatchNumber(Constant.REGISTER_CHARITY_LIV);
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("charityRegistrationPartTwoJob");
        serviceBatch.setNextBatchName("INDEPENDENT");

        createBatchRequest.setServiceBatch(serviceBatch);
        
        try{
        	locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	context.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch: CharityRegistrationPartTwoTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER, "Batch Number"+batchNumber, batchExtManagementServiceFaultException);

         }
        catch(Exception exception)
        {
        	LOGGER.error("Error occured while calling createBatch Service "+exception.getMessage(),exception);
        	throw new GivingBatchException(LOGGER, "Batch Number"+batchNumber, exception);
        }

    }

}
