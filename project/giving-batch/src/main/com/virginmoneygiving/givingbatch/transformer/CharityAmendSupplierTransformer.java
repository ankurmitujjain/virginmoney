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
import com.virginmoneygiving.giving.domain.CharityAddress;
import com.virginmoneygiving.giving.domain.Charity;
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
 * This class takes the Charity Object and converts into Charities object which
 * is required by Oracle Finacials.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class CharityAmendSupplierTransformer implements StepExecutionListener,
        ItemProcessor<Charity, Charities> {

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(CharityAmendSupplierTransformer.class);

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
     * @throws GivingBatchException While process the charity details throws Exception.
     */
    public Charities process(Charity charity) throws GivingBatchException {
        if (LOGGER.isDebugEnabled()){
            if(charity != null) {
                LOGGER
                        .debug("Inside CharityAmendSupplierTransformer - process - Start for charity: " + charity.getId());
            }
        }
        Charities supplierRecord = null;

        if (charity != null) {
            supplierRecord = new Charities();
            Set<CharityAddress> addressSet = charity.getCharityAddresses();
            Set<BankAccount> bankAccountSet = charity.getBankAccounts();
            List<BankAccountType> bankAccounts =
                    new ArrayList<BankAccountType>();
            supplierRecord.setCharityStatus(CharityStatusType.LIVE);
            supplierRecord.setCharityName(charity.getName());
            supplierRecord.setRegistrationNumber(charity
                    .getRegisteredCharityNumber());
            supplierRecord.setVmgCharityRefId(charity.getId().toString());
            if (bankAccountSet != null) {
                for (Iterator<BankAccount> iterator = bankAccountSet.iterator(); iterator.hasNext();) {
                    BankAccount bankAccount = (BankAccount) iterator.next();
                    if (bankAccount.getAccountStatus().getCode().equalsIgnoreCase(MasterDataCodeConstants.ACTIVE) ||
                            bankAccount.getAccountStatus().getCode().equalsIgnoreCase(MasterDataCodeConstants.INACTIVE)) {
                        BankAccountType accountType = new BankAccountType();
                        accountType.setAccountNumber(bankAccount.getAccountNumber());
                        accountType.setAccountType(bankAccount.getBankAccountType().getCode());
                        accountType.setSortCode(Util.getSortCode(bankAccount.getSortCode()));
                        //HunarC: added the following
                        if(bankAccount.getSortCode() != null && bankAccount.getAccountNumber() != null) {
                            String accountNumber = Util.getBankAccountNumber(bankAccount.getAccountNumber());
                            String bankSortCode = Util.getSortCode(bankAccount.getSortCode());
                            if (LOGGER.isDebugEnabled()){
                                LOGGER.debug("***** 2. Sortcode/AccNo ***** :"+ bankSortCode + "/" + accountNumber);
                            }
                            accountType.setAccountUniqueId(bankSortCode.concat(accountNumber));
                            if (LOGGER.isDebugEnabled()){
                                LOGGER.debug("***** Unique ID ***** :"+bankSortCode.concat(accountNumber));
                            }
                        }
                        if (bankAccount.getAccountStatus().getCode().equalsIgnoreCase("INAC")) {
                            accountType.setCloseThisAccount(true);
                        }
                        accountType.setBranchName(bankAccount.getBank().getName());
                        accountType.setBankName(bankAccount.getBank().getName());
                        // HunarC-Addition over
                        // accountType.setBranchName(bankAccount.getBank().setBankAddress(bankAddress));
                        accountType.setAccountName(bankAccount.getAccountName());
                        bankAccounts.add(accountType);
                    }
                }
                supplierRecord.setBankAccounts(bankAccounts);
            }

            // supplierRecord.setTransactionDate(charity.geCreatedDateTime);
            // supplierRecord.setPaymentGroup(paymentGroup);
            // supplierRecord.setCategory(category);

            if (addressSet != null) {
                for (Iterator<CharityAddress> iterator = addressSet.iterator(); iterator
                        .hasNext();) {
                    Address address = (Address) iterator.next().getAddress();
                    CharityAddresses charityAddresses = new CharityAddresses();

                    if (Constant.ADMIN.equalsIgnoreCase(address.getAddressType().getCode())) {
                        charityAddresses.setAddressLine1(address.getAddressLine1());
                        charityAddresses.setAddressLine2(address.getAddressLine2());
                        charityAddresses.setCity(address.getTownCity());
                        charityAddresses.setCountyOrState(address.getCountyState());
                        charityAddresses.setPostCodeOrZipCode(address.getPostCode());
                        //charityAddresses.setCountry(address.getCountry().getName());
                        charityAddresses.setCountry(address.getCountry().getIsoCode());
                        supplierRecord.setAdministrationAddress(charityAddresses);

                    }
                    else if (Constant.REGISTER.equalsIgnoreCase(address.getAddressType().getCode())) {
                        charityAddresses.setAddressLine1(address.getAddressLine1());
                        charityAddresses.setAddressLine2(address.getAddressLine2());
                        charityAddresses.setCountyOrState(address.getCountyState());
                        charityAddresses.setPostCodeOrZipCode(address.getPostCode());
                        //charityAddresses.setCountry(address.getCountry().getName());
                        charityAddresses.setCountry(address.getCountry().getIsoCode());
                        supplierRecord.setRegisteredAddress(charityAddresses);
                    }

                }
            }

            // Step 2: Here for every charity, we create the BatchEntity record.
            ExecutionContext context =
                    stepExecution.getJobExecution().getExecutionContext();
            String batchNumber = (String) context.get(Constant.BATCH_NUMBER);
            CreateBatchEntityRequest createBatchEntityRequest =
                    new CreateBatchEntityRequest();
            ServiceBatchEntity batchEntity = new ServiceBatchEntity();
            batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
            batchEntity.setEntityId(charity.getId().toString());
            batchEntity
                    .setNextBatchStatus(MasterDataCodeConstants.CHARITY_STATUS_CODE_LIV);
            batchEntity
                    .setPreviousStatus(MasterDataCodeConstants.CHARITY_STATUS_CODE_LIV);
            batchEntity.setEntityTypeCode(Constant.ENTITY_CHARITY);
            batchEntity.setEntityReference(charity.getId().toString());  //HunarC: Added this
            batchEntity.setBaseReference(charity.getId().toString());    //HunarC: Added this
            ServiceBatch serviceBatch = new ServiceBatch();
            serviceBatch.setBatchNumber(batchNumber);
            batchEntity.setBatch(serviceBatch);
            createBatchEntityRequest.setServiceBatchEntity(batchEntity);
            try {
                locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(
                        createBatchEntityRequest);
            }
            catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
                LOGGER
                .error(
                        "Error occurred while creating batch Giving Entity  : CharityAmendSupplierTransformer."
                                + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
                throw new GivingBatchException(LOGGER, "Entity Reference"+charity.getId().toString(), batchExtManagementServiceFaultException);

            }
            catch (Exception exception) {
                LOGGER
                .error("Error occured while calling creating batch Giving Entity "
                        + exception.getMessage(),exception);
                throw new GivingBatchException(LOGGER, "Entity Reference"+charity.getId().toString(), exception);
            }
            updateObjectInContext(supplierRecord);
        }
        if (LOGGER.isDebugEnabled()){
            if (charity != null) {
                LOGGER.debug("Inside CharityAmendSupplierTransformer - process -End for charity: " + charity.getId());
            }
        }
        return supplierRecord;
    }

    /**
     * This method is used for to get the registered charities list from execution context.
     * If it is null then add charitiesObjectList to executionContext.
     * 
     * @param charities the charities.
     */
    private void updateObjectInContext(Charities charities) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside CharityAmendSupplierTransformer UpdateObjectContext -Start");
        }
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        // HunarC: Changed from  REGISTER_CHARITY_OBJECT_LIST to MAINTAIN_CHARITY_OBJECT_LIST
        List<Charities> charitiesObjectList =
                (List<Charities>) executionContext
                        .get(Constant.MAINTAIN_CHARITY_OBJECT_LIST);
        if (charitiesObjectList == null) {
            charitiesObjectList = new ArrayList<Charities>();
            executionContext.put(Constant.MAINTAIN_CHARITY_OBJECT_LIST,
                    charitiesObjectList);
        }
        charitiesObjectList.add(charities);
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside CharityAmendSupplierTransformer UpdateObjectContext -End.");
        }
    }

    /**
     * This method will execute after step completes and returns status.
     * This method will update the batch status to BATCH_STATUS_SUCCESSFUL or
     * BATCH_STATUS_FAIL along with the reasons for the same.
     * 
     * @param astepExecution the astep execution
     * 
     * @return Exit Status after completion of step.
     */
    public ExitStatus afterStep(StepExecution astepExecution) {

        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "YES");
        List<Charities> charitiesObjectList = (List<Charities>) executionContext
                        .get(Constant.MAINTAIN_CHARITY_OBJECT_LIST);
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
                LOGGER.error("Unexpected error setting batch status to complete when no records found" + exception.getMessage(),exception);
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
     * @param astepExecution the astep execution
     */
    public void beforeStep(StepExecution astepExecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CharityAmendSupplierTransformer:inside before step  execution context:" + stepExecution);
        }
        this.stepExecution = astepExecution;
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchGivingControlData(jobName);
        // Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(Constant.MAINTAIN_CHARITY);
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, astepExecution);
        serviceBatch.setCurrentJobName("charityRegistrationAmendSupplierJob");
        serviceBatch.setNextBatchName("INDEPENDENT");

        createBatchRequest.setServiceBatch(serviceBatch);

        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
            context.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error(
                    "Error occurred while creating batch: CharityAmendSupplierTransformer."
                            + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER, "Batch Number"+batchNumber, batchExtManagementServiceFaultException);

        }
        catch (Exception exception) {
            LOGGER.error("Error occured while calling createBatch Service "
                    + exception);
            throw new GivingBatchException(LOGGER, "Batch Number"+batchNumber, exception);
        }
    }
}
