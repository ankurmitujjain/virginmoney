package com.virginmoneygiving.givingbatch.writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.oxm.XmlMappingException;

import com.virginmoneygiving.givingbatch.domain.BankAccountType;
import com.virginmoneygiving.givingbatch.domain.Charities;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;

/**
 * Class to create the summary report of registered charities.
 * 
 * @author taruna
 */
public class CharityRegistrationXMLWriter extends StaxEventItemWriter implements
        StepExecutionListener {

    /** instance for Logger. * */
    private static Logger LOGGER =
            Logger.getLogger(CharityRegistrationXMLWriter.class);

    /** instance for ExecutionContext. * */
    private ExecutionContext executionContext;

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** {@inheritDoc} ***/
    public void write(List item) throws XmlMappingException, IOException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                .trace("Inside CharityRegistrationXMLWriter write method- START");
        }
        List<Charities> tempCharitiesList = new ArrayList<Charities>();
        List<Charities> charitiesList = item;

        Charities charitiesObj = null;
        for (Charities charities : charitiesList) {

            List<BankAccountType> bankAccounts = charities.getBankAccounts();
            
            if (bankAccounts != null && bankAccounts.size() > 1) {

                for (BankAccountType bankAccountType : bankAccounts) {
                    charitiesObj = new Charities();
                    List<BankAccountType> bankAccountList = new ArrayList<BankAccountType>();
                    
                    charitiesObj.setAdministrationAddress(charities
                            .getAdministrationAddress());
                    bankAccountList.add(bankAccountType);
                    charitiesObj.setBankAccounts(bankAccountList);
                    charitiesObj.setCharityName(charities.getCharityName());
                    charitiesObj.setCharityStatus(charities.getCharityStatus());
                    charitiesObj.setEventRef(charities.getEventRef());
                    charitiesObj.setRegisteredAddress(charities
                            .getRegisteredAddress());
                    charitiesObj.setRegistrationNumber(charities
                            .getRegistrationNumber());
                    charitiesObj.setTransactionDate(charities
                            .getTransactionDate());
                    charitiesObj.setVmgCharityRefId(charities
                            .getVmgCharityRefId());
                    tempCharitiesList.add(charitiesObj);
                }

            }
            else if (bankAccounts != null && bankAccounts.size() == 1) {
                charitiesObj = new Charities();
                charitiesObj.setAdministrationAddress(charities
                        .getAdministrationAddress());
                charitiesObj.setBankAccounts(charities.getBankAccounts());
                charitiesObj.setCharityName(charities.getCharityName());
                charitiesObj.setCharityStatus(charities.getCharityStatus());
                charitiesObj.setEventRef(charities.getEventRef());
                charitiesObj.setRegisteredAddress(charities
                        .getRegisteredAddress());
                charitiesObj.setRegistrationNumber(charities
                        .getRegistrationNumber());
                charitiesObj.setTransactionDate(charities.getTransactionDate());
                charitiesObj.setVmgCharityRefId(charities.getVmgCharityRefId());
                tempCharitiesList.add(charitiesObj);
            }
        }
        
        writeCharityObjects(tempCharitiesList);
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                .trace("Inside CharityRegistrationXMLWriter write method- STOP");
        }
    }

    
    /**
     * Write charity objects.
     * 
     * @param charityList the charity list
     * 
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void writeCharityObjects(List<Charities> charityList) 
    {
        for (Charities charities : charityList) {
            try {
                super.write(Collections.singletonList(charities));
            }
            catch (XmlMappingException e) {
                throw new GivingBatchException(LOGGER, "Batch Failed : CharityRegistrationXMLWriter Error while writing summarised object", e);
            }
            catch (IOException e) {
                throw new GivingBatchException(LOGGER, "Batch Failed : CharityRegistrationXMLWriter Error while writing summarised object", e);
            }
        }
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
