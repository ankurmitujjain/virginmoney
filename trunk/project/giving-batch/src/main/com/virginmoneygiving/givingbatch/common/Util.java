package com.virginmoneygiving.givingbatch.common;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.virginmoneygiving.givingbatch.domain.Batch;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimedPeriod;
import com.virginmoneygiving.givingbatch.domain.MessageHeader;
import com.virginmoneygiving.payment.entitylistener.ControlData;
import com.virginmoneygiving.payment.entitylistener.ThreadLocalControlDataStorage;
import com.virginmoneygiving.paymentmanagement.service.messages.ClaimPeriod;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.ServiceBatch;
import com.virginmoneygiving.givingbatchcontroller.common.BatchConstant;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.JobParameter;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import java.lang.*;
import java.net.*;

/**
 * This class provides utility functions like getClaimPeriodStartDate and
 * getClaimPeriodEndDate and methods that are used to populate the control data.
 * 
 * @author Srinivas Nageli.
 */
public final class Util {


    /**
     * Instantiates a new util.
     */
    private Util() {

    }

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER = Logger.getLogger(Util.class);

    /**
     * This method used to get claim period start date. It returns 6th of last
     * month.
     * 
     * @return date object.
     */
    public static Date getClaimPeriodStartDate() {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();

        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
        final int year = gregorianCalendar.get(Calendar.MONTH) == 0 ? gregorianCalendar
                .get(Calendar.YEAR) - 1
                : gregorianCalendar.get(Calendar.YEAR);

        gregorianCalendar.set(year, gregorianCalendar.get(Calendar.MONTH) - 1,
                6);

        return new Date(gregorianCalendar.getTimeInMillis());
    }

    /**
     * This method used to get claim period end date. It returns 5th of current
     * month.
     * 
     * @return date object.
     */
    public static Date getClaimPeriodEndDate() {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR),
                gregorianCalendar.get(Calendar.MONTH), 5);

        return new Date(gregorianCalendar.getTimeInMillis());
    }

    /**
     * Generates and return a unique batch Number by concating the batch name .
     * 
     * @param batchStatus the batch status
     * 
     * @return String
     */
    public static String getGeneratedBatchNumber(String batchStatus) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR),
                gregorianCalendar.get(Calendar.MONTH), gregorianCalendar
                .get(Calendar.DATE)

                , gregorianCalendar.get(Calendar.HOUR_OF_DAY),
                gregorianCalendar.get(Calendar.MINUTE), gregorianCalendar
                .get(Calendar.SECOND));
        Format dateFormat = new SimpleDateFormat("yyyyMMddHHMMSS");
        String batchNumber = null;
        String currentDate = dateFormat.format(new Date(gregorianCalendar
                .getTime().getTime()));
        batchNumber = batchStatus.concat(currentDate);
        return batchNumber;

    }

    /**
     * Creates and returns an XMLGregorianCalendar date item from the supplied
     * Java date item.
     * 
     * @return XMLGregorianCalendar converted value
     */
    public static XMLGregorianCalendar buildXMLGregorianDate() {

        XMLGregorianCalendar returnDate;

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar
                .get(Calendar.DATE));

        try {
            DatatypeFactory df = DatatypeFactory.newInstance();
            returnDate = df.newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            returnDate = null;
        }

        return returnDate;
    }

    /**
     * Gets the time stamp date.
     * 
     * @return the time stamp date
     */
    public static Timestamp getTimeStampDate() {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR),
                gregorianCalendar.get(Calendar.MONTH), gregorianCalendar
                .get(Calendar.DATE)

                , gregorianCalendar.get(Calendar.HOUR_OF_DAY),
                gregorianCalendar.get(Calendar.MINUTE), gregorianCalendar
                .get(Calendar.SECOND));

        Timestamp timestamp = new Timestamp(gregorianCalendar.getTimeInMillis());
        return timestamp;
    }


    /**
     * Gets the batch object.
     * 
     * @param eventType the event type
     * @param batchNumber the batch number
     * 
     * @return the batch object
     */
    public static Batch getBatchObject(String eventType, String batchNumber) {

        Batch batch = new Batch();
        if (eventType != null) {

            batch.setBatchNumber(batchNumber);
            batch.setBatchDate(Util.getTimeStampDate());
            batch.setBatchType(eventType);
            // batch.setProcessBatchOnErrors(value);
            batch.getBatchChunkId();

        }
        return batch;
    }

    /**
     * Gets the message header object.
     * 
     * @param eventType the event type
     * 
     * @return the message header object
     */
    public static MessageHeader getMessageHeaderObject(String eventType) {
        MessageHeader header = new MessageHeader();
        if (eventType != null) {
            header.setSourceSystemId("Virgin Money Giving");
            // header.setSourceSubSystemId(value);
            header.setBrandReference("Virgin");
            header.setSystemTransactionID("CHTYREG");
            //header.setIPAddress("127.0.0.1");
            header.setSessionID("SESS1");
            header.setUserName("VMG Batch");
        }
        return header;

    }

    /**
     * Gets the sort code.
     * 
     * @param sortCode the sort code
     * 
     * @return the sort code
     */
    public static String getSortCode(String sortCode) {

        String returnSortCode = sortCode.replaceAll("-", "");
        return returnSortCode;
    }

    /**
     * Gets the bank account number.
     * 
     * @param bankAccountNumber the bank account number
     * 
     * @return the bank account number
     */
    public static String getBankAccountNumber(String bankAccountNumber) {
        String tempBankAccount = null;
        int length = bankAccountNumber.length();

        if (length == 10) {
            tempBankAccount = bankAccountNumber.substring(0, 9).trim();
        } else if (length == 9) {
            tempBankAccount = bankAccountNumber.substring(0, 9).trim();
        } else if (length == 8) {
            tempBankAccount = bankAccountNumber.substring(0, 8).trim();
        } else {
            tempBankAccount = bankAccountNumber.trim();
        }

        return tempBankAccount;
    }

    /**
     * Gets the next claim period.
     * 
     * @param claimPeriodTo the claim period to
     * 
     * @return the next claim period
     */
    public static GiftAidClaimedPeriod getNextClaimPeriod(Date claimPeriodTo) {

        Date fromDate = null;
        Date toDate = null;
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(claimPeriodTo.getTime());
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR),
                gregorianCalendar.get(Calendar.MONTH), gregorianCalendar
                .get(Calendar.DATE));
        gregorianCalendar.add(Calendar.DATE, 1);
        fromDate = new Date(gregorianCalendar.getTimeInMillis());

        final GregorianCalendar gregorianCalendarTo = new GregorianCalendar();
        gregorianCalendarTo.setTimeInMillis(claimPeriodTo.getTime());
        gregorianCalendarTo.set(gregorianCalendar.get(Calendar.YEAR),
                gregorianCalendarTo.get(Calendar.MONTH), 5);
        gregorianCalendarTo.add(Calendar.MONTH, 1);
        toDate = new Date(gregorianCalendarTo.getTimeInMillis());
        GiftAidClaimedPeriod claimPeriod = new GiftAidClaimedPeriod();
        claimPeriod.setClaimPeriodFrom(fromDate);
        claimPeriod.setClaimPeriodTo(toDate);

        return claimPeriod;
    }

    /**
     * Utility method used to generate a dummy header. TODO Replace with actual
     * implementation once audit service is in place.
     * 
     * @return MessageHeader.
     */
    public static MessageHeader getDummyHeader() {
        MessageHeader header = new MessageHeader();
        header.setBrandReference("12346");
        //header.setIPAddress("12346");
        header.setSessionID("12346");
        header.setSourceSubSystemId("12346");
        header.setSystemTransactionID("12346");
        header.setSourceSystemId("12346");
        header.setUserName("12346");
        return header;
    }

    /**
     * Utility method to calculate the Charity accountingPeriodEnd date year for
     * populating in the R68 form through UCGAC0010.
     * 
     * @param accountingPeriodEndDate the accounting period end date
     * 
     * @return the date
     */
    public static java.util.Date calculateAccountingPeriodEndDateYear(
            java.util.Date accountingPeriodEndDate) {

        //LOGGER.debug("Starting calculateAccountingPeriodEndDateYear for date: " + accountingPeriodEndDate);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(accountingPeriodEndDate.getTime());
        int accountingPeriodMonth = gregorianCalendar.get(Calendar.MONTH);
        int accountingPeriodDay = gregorianCalendar.get(Calendar.DATE);
        //int accountingPeriodYear = gregorianCalendar.get(Calendar.YEAR);
        java.util.Date finalAccountingPeriodEndDate = new java.util.Date(
                gregorianCalendar.getTimeInMillis());
        final GregorianCalendar currentCalendar = new GregorianCalendar();
        currentCalendar.setTimeInMillis(System.currentTimeMillis());
        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentDay = currentCalendar.get(Calendar.DATE);
        int currentYear = currentCalendar.get(Calendar.YEAR);

        DateTime systemDate    = new DateTime();
        //LOGGER.debug("calculateAccountingPeriodEndDateYear(1)-Month/Day: " + accMonth + "/" + accDay);
        //LOGGER.debug("calculateAccountingPeriodEndDateYear(2)-Month/Day: " + currentMonth + "/" + currentDay);

        if ((currentMonth > accountingPeriodMonth) && (currentDay > accountingPeriodDay)) {
            currentYear = currentYear + 1;
            gregorianCalendar.set(currentYear, accountingPeriodMonth, accountingPeriodDay);
            finalAccountingPeriodEndDate = new java.util.Date(gregorianCalendar
                    .getTimeInMillis());
        } else if ((currentMonth <= accountingPeriodMonth) && (currentDay <= accountingPeriodDay)) {
            gregorianCalendar.set(currentYear, accountingPeriodMonth, accountingPeriodDay);
            finalAccountingPeriodEndDate = new java.util.Date(gregorianCalendar
                    .getTimeInMillis());
        }

        //LOGGER.debug("calculateAccountingPeriodEndDateYear finalAccountingPeriodEndDate (1): "
        //        + finalAccountingPeriodEndDate);

        // Now check whether the final accounting period is in the past. If so, move it until it's after today.
        DateTime checkFAPEndDate = new DateTime(finalAccountingPeriodEndDate);
        if (checkFAPEndDate.isBefore(systemDate)) {
            int ctr = 0;
            while (checkFAPEndDate.isBefore(systemDate)) {
                checkFAPEndDate = checkFAPEndDate.plusYears(1);
                //LOGGER.debug("calculateAccountingPeriodEndDateYear Adding year: " + checkFAPEndDate);
                ctr++;
                if (ctr > 5) {
                    break;
                }
            }
            gregorianCalendar.set(checkFAPEndDate.getYear(), accountingPeriodMonth, accountingPeriodDay);
            finalAccountingPeriodEndDate = new java.util.Date(gregorianCalendar
                    .getTimeInMillis());
            //LOGGER.debug("calculateAccountingPeriodEndDateYear finalAccountingPeriodEndDate (2): "
            //    + finalAccountingPeriodEndDate);
        }

        //LOGGER.debug("calculateAccountingPeriodEndDateYear finalAccountingPeriodEndDate (3): "
        //        + finalAccountingPeriodEndDate);

        return finalAccountingPeriodEndDate;
    }

    /**
     * This method takes the Bigdecimal argument in pounds and convert it into pense.
     * 
     * @param amountInPounds the amount in pounds
     * 
     * @return the big decimal
     */
    public static BigDecimal convertInPense(BigDecimal amountInPounds) {
        BigDecimal amountInPense = amountInPounds.multiply(new BigDecimal(100));
        return amountInPense;
    }

    /**
     * This method returns the leftZeroPadded Integer value.
     * 
     * @param integer the integer
     * @param size the size
     * 
     * @return the string
     */
    public static String addLeftZeroPadding(Integer integer, int size) {
        String zeroPadBase = "00000000000000000000";
        int intSize = integer.toString().length();
        String zeroPad = zeroPadBase.substring(0, (size) - intSize);
        String donationCount = String.valueOf(integer);
        String result = zeroPad.concat(donationCount);
        return result;

    }

    /**
     * This method checks whether the supplied string is empty or not.
     * 
     * @param toCheck String to check
     * 
     * @return boolean result of check.
     */
    public static boolean isStringEmpty(String toCheck) {
        boolean result = false;
        if (toCheck == null || toCheck.equalsIgnoreCase("") || toCheck.length() < 1) {
            result = true;
        }
        return result;
    }

    /**
     * Static method to create a new service batch JAX object.
     * 
     * @param batchNumber  Number of the batch.
     * @param stepExecution Spring stepExecution to fetch Jobparameters from.
     * 
     * @return ServiceBatch JAX service batch object
     */
    public static ServiceBatch createServiceBatch(String batchNumber, StepExecution stepExecution) {

        LOGGER.debug("Creating service batch for number: " + batchNumber);

        Long seqNumber = null;
        Long seqSize = null;
        String chainInd = null;
        String corrId = null;
        String batchRunId = null;
        if (stepExecution != null) {
            Map<String, JobParameter> paramMap = stepExecution.getJobExecution().getJobInstance()
                    .getJobParameters().getParameters();

            JobParameter seqNumObj = paramMap.get(BatchConstant.SI_SEQUENCE_NUMBER_PARAM_NAME);
            JobParameter seqSizeObj = paramMap.get(BatchConstant.SI_SEQUENCE_SIZE_PARAM_NAME);
            JobParameter chainIndObj = paramMap.get(Constant.BATCH_PARAMETER_EXECUTE_CHAIN);
            JobParameter corrIdObj = paramMap.get(BatchConstant.SI_CORRELATION_ID_PARAM_NAME);
            JobParameter batchRunIdObj = paramMap.get(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID);

            LOGGER.debug("1. Number/Size/Chain/RunId = " + seqNumObj + "/" + seqSizeObj + "/" + chainIndObj + "/"
                    + batchRunIdObj);

            seqNumber = (seqNumObj != null) ? (Long) seqNumObj.getValue() : null;
            seqSize = (seqSizeObj != null) ? (Long) seqSizeObj.getValue() : null;
            chainInd = (chainIndObj != null) ? (String) chainIndObj.getValue() : null;
            corrId = (corrIdObj != null) ? (String) corrIdObj.getValue() : null;
            batchRunId = (batchRunIdObj != null) ? (String) batchRunIdObj.getValue() : null;
            LOGGER.debug("Number/Size/Chain = " + seqNumber + "/" + seqSize + "/" + chainInd);
        }

        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        serviceBatch.setExecutionDateTime(Util.buildXMLGregorianDate());
        serviceBatch.setBatchStatus(Constant.BATCH_STATUS_PROCESSING); // HunarC: Added this
        serviceBatch.setCorrelationId(corrId);
        serviceBatch.setBatchRunId(batchRunId);
        serviceBatch.setSequenceNumber((seqNumber == null) ? 0 : seqNumber.intValue());
        serviceBatch.setSequenceSize((seqSize == null) ? 0 : seqSize.intValue());
        serviceBatch.setJobChainRequest((chainInd == null || !chainInd.equalsIgnoreCase("YES")) ? false : true);

        return serviceBatch;
    }

    /**
     * Builds the timestamp.
     * 
     * @param inDate the in date
     * 
     * @return the timestamp
     */
    public static Timestamp buildTimestamp(XMLGregorianCalendar inDate) {
        if (inDate == null) {
            return null;
        }

        Timestamp target = new Timestamp(inDate.toGregorianCalendar().getTimeInMillis());
        return target;
    }
    
    /**
     * Utility method used to generate a giving header.
     * 
     * @param name the name
     * 
     * @return MessageHeader.
     */
    public static com.virginmoneygiving.givingmanagement.service.messages.MessageHeader getGivingHeader(
            String name) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("getHeader() -  START");
        }

        com.virginmoneygiving.givingmanagement.service.messages.MessageHeader header = 
            new com.virginmoneygiving.givingmanagement.service.messages.MessageHeader();
        
        String ipAddress = getHostIpaddress();
        String userName = "givingbatch";
        
        header.setIPAddress(ipAddress);
        header.setUserName(userName);
        header.setProcessName(name);
        header.setSystemTransactionID(generateGUID());
        header.setSourceSystemId("Giving Batch");
        header.setSessionID("");
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("getHeader() - END");
        }
        return header;
    }
    
    /**
     * Utility method used to generate a payment header.
     * 
     * @param name the name
     * 
     * @return MessageHeader.
     */
    public static  com.virginmoneygiving.paymentmanagement.service.messages.MessageHeader getPaymentHeader(
             String name) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("getHeader() -  START");
        }

        com.virginmoneygiving.paymentmanagement.service.messages.MessageHeader header = 
            new com.virginmoneygiving.paymentmanagement.service.messages.MessageHeader();
        String ipAddress = getHostIpaddress();
        String userName = "givingbatch";
        String processName = name;
        
        header.setIPAddress(ipAddress);
        header.setUserName(userName);
        header.setProcessName(processName);
        
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("getHeader() - END");
        }
        return header;
    }
    
    /**
     * Method to get the ip address of the system.
     * 
     * @return the system ip address.
     */
    public static String getHostIpaddress(){
        String systemIpAddress = null;
        try {
            systemIpAddress = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e) {
            LOGGER.error("Cannot get the host IP Address", e);
        }
        return systemIpAddress;
    }
    
    /**
     * Method to populate the payment control data.
     * 
     * @param processName the processName which is the batch name in this case.
     */
    public static void populateBatchPaymentControlData(String processName) {
        final ControlData controlData = new ControlData();
        String userName = "givingbatch";
        String ipAddress = getHostIpaddress();
        controlData.setIpAddress(ipAddress);
        controlData.setProcess(processName);
        controlData.setUsername(userName);
        ThreadLocalControlDataStorage.set(controlData);
    }
    
    /**
     * Method to populate the giving control data.
     * 
     * @param processName the processName which is the batch name in this case.
     */
    public static void populateBatchGivingControlData(String processName) {
        final com.virginmoneygiving.giving.entitylisterer.ControlData controlData = new com.virginmoneygiving.giving.entitylisterer.ControlData();
        String userName = "givingbatch";
        String ipAddress = getHostIpaddress();
        controlData.setIpAddress(ipAddress);
        controlData.setProcess(processName);
        controlData.setUsername(userName);
        com.virginmoneygiving.giving.entitylisterer.ThreadLocalControlDataStorage.set(controlData);
    }

    /**
     * Generate OCP GUID.
     *
     * @return new GUID
     */
    /**
     * @return
     */
    public static String generateGUID() {
        return UUID.randomUUID().toString();
    }
}
