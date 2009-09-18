package com.virginmoneygiving.givingbatch.launcher.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.virginmoney.glis.messages.CollectedRegistrationFeeBatch;
import com.virginmoney.glis.messages.ErrorList;
import com.virginmoney.glis.messages.ErrorMessage;
import com.virginmoney.glis.messages.RegistrationFeePaymentCollected;
import com.virginmoney.glis.messages.SettledCharityPayments;
import com.virginmoney.glis.messages.SettledCharityPaymentsBatch;
import com.virginmoneygiving.alert.service.messages.AlertContent;
import com.virginmoneygiving.alert.service.messages.AlertDetail;
import com.virginmoneygiving.alert.service.messages.AlertPort;
import com.virginmoneygiving.givingbatch.launcher.JobExecutionService;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.AlertServiceLocatorImpl;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.common.JobChainProcessorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.FetchBatchParameterRequest;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.GivingBatchExtManagementServiceFaultException;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.FetchBatchParameterResponse;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.UpdateBatchStatusRequest;
import com.virginmoneygiving.givingbatchcontroller.common.JobChainProcessorInterface;

import javax.annotation.Resource;
// TODO: Exception handling needs to be implemented

/**
 * This is the implementation for Job Execution service.
 * 
 * @author henryj
 */
public class JobExecutionServiceImpl implements JobExecutionService {

    /** The Constant COLLECTED_REGISTRATION_FEES_JOB_NAME. */
    private static final String COLLECTED_REGISTRATION_FEES_JOB_NAME = "collectedRegistrationFeesJob";
    
	/** The SETTLE d_ paymen t_ batc h_ name. */
	private static String SETTLED_PAYMENT_BATCH_NAME = "settledPaymentJob";
    
	/** Instance for logger. */
    private static Logger LOGGER =
            Logger.getLogger(JobExecutionServiceImpl.class);

    /** Job locator. * */
    private JobLocator jobLocator;

    /** Job launcher. * */
    private JobLauncher jobLauncher;

    /** Filepath to write flat files to. */
    private String filePath;

    /** Giving batch ext. service locator. */
    private GivingBatchExtManagementServiceLocatorImpl givingBatchExtManagementServiceLocatorImpl = null;
    
    /** Alert service locator. */
    private AlertServiceLocatorImpl alertServiceLocator = null;

    /** The job chain processor. */
    private JobChainProcessorInterface jobChainProcessor;

    /**
     * Getter for the  alertServiceLocator property.
     * 
     * @return AlertServiceLocatorImpl instance
     */
    public AlertServiceLocatorImpl getAlertServiceLocator() {
        return alertServiceLocator;
    }

    /**
     * Spring injector for the AlertServiceLocatorImpl property.
     * 
     * @param alertServiceLocator return property
     */
    public void setAlertServiceLocator(
            AlertServiceLocatorImpl alertServiceLocator) {
        this.alertServiceLocator = alertServiceLocator;
    }

    /**
     * Sets the giving batch ext management service locator impl.
     * 
     * @param givingBatchExtManagementServiceLocatorImpl the givingBatchExtManagementServiceLocatorImpl to set
     */
    public void setGivingBatchExtManagementServiceLocatorImpl(
            GivingBatchExtManagementServiceLocatorImpl givingBatchExtManagementServiceLocatorImpl) {
        this.givingBatchExtManagementServiceLocatorImpl = givingBatchExtManagementServiceLocatorImpl;
        LOGGER.trace("GivingBatchExtManagementServiceLocatorImpl injected.");
    }

    /**
     * Sets the job chain processor.
     * 
     * @param jobChainProcessor the new job chain processor
     */
    @Resource
    public void setJobChainProcessor(JobChainProcessorInterface jobChainProcessor) {
        this.jobChainProcessor = jobChainProcessor;
    }

    /**
     * Execute a job with an arbitrary number of JobParameters.
     * 
     * Each entry in the jobParameters list will be added as a JobParatmeter to the
     * final call, so the need to adhere to the rules for that class. An exception will
     * be thrown if they don't.
     * 
     * The map <i>must</i> include a JOB_NAME key/parameter pair.
     * 
     * @param jobParametersMap Map<String, Object> where String is the parameter name and Object is
     * the Date, String, Double or Long value of the parameter.
     */
    public void executeJob(Map<String, Object> jobParametersMap) {
        
    	if ( LOGGER.isDebugEnabled() ) {
        	LOGGER.debug("Started executeJob");
        	StringBuilder sb = new StringBuilder();
        	sb.append(" JobParametersMap = {");
        	for (String key : jobParametersMap.keySet() ) {
        		Object parameter = jobParametersMap.get(key);
				sb.append("key=").append(key).append(", ");
				sb.append("parameter=").append(parameter).append("}");
			}
        	sb.append("}");
        	LOGGER.debug(sb.toString());
        }

        /*
         * Get the job name. Remove from the map to avoid it being
         * set up as a JobParameter.
         * 
         * Note that the checks do not throw exceptions. The reason is that if a message is 
         * is persistent then throwing here simply results in the message being resubmitted
         * to the MessageDelegate, causing a loop. 
         */
        if( ! jobParametersMap.containsKey(JobExecutionService.JOB_NAME) ) {
        	StringBuilder sb = new StringBuilder();
        	sb.append("Rejected job: jobParametersMap must include a \"").append(JobExecutionService.JOB_NAME).append("\" parameter");
        	raiseAlert("JobExecutionService.executeJob()","N/A", sb.toString());
        	return;
        }
        String jobName = (String) jobParametersMap.remove(JobExecutionService.JOB_NAME);
        if ( StringUtils.isEmpty(jobName) ) {
        	StringBuilder sb = new StringBuilder();
        	sb.append("Rejected job: \"").append(JobExecutionService.JOB_NAME).append("\" parameter must contain a job name");
        	raiseAlert("JobExceutionService.executeJob()","N/A", sb.toString());
        	return;
        }
        
        /*
         * Ensure jobParameters contains a scheduled date and time.
         * Add if not present.
         */
        if ( !jobParametersMap.containsKey("scheduledDate") ) {
        	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        	String scheduledDate = sdf.format(new Date());
        	jobParametersMap.put("scheduledDate", scheduledDate);
        }
        if ( !jobParametersMap.containsKey("scheduledTime") ) {
        	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        	String scheduledTime = sdf.format(new Date());
        	jobParametersMap.put("scheduledTime", scheduledTime);
        }

        // HunarC: Set the cutoff date
        if ( !jobParametersMap.containsKey("cutOffDate") ) {
            String defaultCutoff = getCutoffDateTime();
        	jobParametersMap.put("cutOffDate", defaultCutoff);
        }

        // Store the execution kick-off time. Can be used to timestamp output files.
        String kickoff = getKickoffDateTime();
      	jobParametersMap.put("kickOffDate", kickoff);

        // Set Job Parameters
        JobParametersBuilder builder = new JobParametersBuilder();
        for ( String key : jobParametersMap.keySet() ) {
			Object parameter = jobParametersMap.get(key);
			
			if ( parameter instanceof Date) {
				builder.addDate(key, (Date) parameter);	
			} 
			else if ( parameter instanceof Long) {
                builder.addLong(key, (Long)parameter);
			} 
			else if ( parameter instanceof Double) {
				builder.addDouble(key, (Double) parameter);	
			}
			else if ( parameter instanceof String) {
				builder.addString(key, (String) parameter); 
			}
			else {
				StringBuilder sb = new StringBuilder();
				sb.append("The jobParameter for key ").append(key); 
				sb.append(" must be one of Date, Double, Long or String but it is ");
				sb.append(parameter.getClass().getName());
	        	raiseAlert("JobExecutionService.executeJob()","N/A", sb.toString());
                LOGGER.debug(sb.toString());
            }
		}

        JobParameters jobParameters = builder.toJobParameters();

        if ( LOGGER.isDebugEnabled() ) {
            LOGGER.debug("Final Job Parameter List  executeJob");
            StringBuilder sb = new StringBuilder();
            sb.append(" JobParametersMap = {");
            for (String key : jobParameters.getParameters().keySet() ) {
                Object parameter = jobParametersMap.get(key);
                sb.append("key=").append(key).append(", ");
                sb.append("parameter=").append(parameter).append("}");
            }
            sb.append("}");
            LOGGER.debug(sb.toString());
        }

        Job job = null;
        try {
            LOGGER.debug("**** About to start Job (" + jobName + ") Execution. Date/Time: " + fetchTime() + " ****");
            job = getJobLocator().getJob(jobName);
            JobExecution execution = getJobLauncher().run(job, jobParameters);
            String eStatus = execution.getExitStatus().getExitCode();
            DateTime eDate = new DateTime();
            LOGGER.debug("**** Job (" + jobName + ") completed with exit code: " +  eStatus + ", Date/Time: " + fetchTime() + " ****");
            if (execution.getExitStatus() != null) {
                String batchNumber = (String)execution.getExecutionContext().get(Constant.BATCH_NUMBER);
                if (execution.getExitStatus().compareTo(ExitStatus.FAILED) == 0) {
                    setBatchStatusToError(batchNumber, "Job failed. See log files");
                    throw new Exception ("Job (" + jobName + ") failed to execute. Refer to log files.");
                }
                else if (execution.getExitStatus().compareTo(ExitStatus.STOPPED) == 0) {
                    setBatchStatusToError(batchNumber, "Job interrupted. See log files");
                    throw new Exception ("Job (" + jobName + ") was interrupted. Refer to log files.");
                }
            }
            processJobCompletion(jobName, execution, null);
            LOGGER.debug("**** Completed Job (" + jobName + ") Execution ****");
        }
        catch (Exception exp) {
        	raiseAlert("JobExecutionService.executeJob()","N/A", 
        			"Caught exception starting job " + jobName 
        			+ ": exception = " + exp.getMessage());
            LOGGER.error("Unexpected error processing job: " + jobName, exp);
        }


    }

    /**
     * Sets the job locator.
     * 
     * @param jobLocator the jobLocator to set
     */
    public void setJobLocator(JobLocator jobLocator) {
        this.jobLocator = jobLocator;
    }

    /**
     * Sets the job launcher.
     * 
     * @param jobLauncher the jobLauncher to set
     */
    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    /**
     * Gets the job locator.
     * 
     * @return the jobLocator
     */
    public JobLocator getJobLocator() {
        return jobLocator;
    }

    /**
     * Gets the job launcher.
     * 
     * @return the jobLauncher
     */
    public JobLauncher getJobLauncher() {
        return jobLauncher;
    }

    /**
     * Fire a batch job from GLIS.
     * 
     * @param collectedRegistrationFeeBatch the collected registration fee batch
     */
	public void executeCollectedRegistrationFeeJob(CollectedRegistrationFeeBatch collectedRegistrationFeeBatch) {
		
		if ( LOGGER.isDebugEnabled() ) {
			StringBuilder sb = 
				new StringBuilder("executeCollectedRegistrationFeeJob: collectedRegistrationFeeBatch = {");
			sb.append("batchNumber=").append(collectedRegistrationFeeBatch.getBatchNumber()).append(", ");
			sb.append("processDate=").append(collectedRegistrationFeeBatch.getProcessDate()).append(", ");
			sb.append("errors={");
			ErrorList errorList = collectedRegistrationFeeBatch.getErrors();
			if ( errorList != null ) {
				List<ErrorMessage> errorMessages = collectedRegistrationFeeBatch.getErrors().getErrors();
				for (ErrorMessage errorMessage : errorMessages) {
					sb.append("{errorDefaultMessage=").append(errorMessage.getErrorDefaultMessage()).append(", ");
					sb.append("errorField=").append(errorMessage.getErrorField()).append(", ");
					sb.append("errorMessageKey=").append(errorMessage.getErrorMessageKey()).append("}");
				}
			}
			sb.append("}, feePayments={");
			List<RegistrationFeePaymentCollected> registrationFees = collectedRegistrationFeeBatch.getFeePayments();
			for (RegistrationFeePaymentCollected fee : registrationFees) {
				sb.append("{feeAmount=").append(fee.getFeeAmount()).append(", ");
				sb.append("vatAmount=").append(fee.getVatAmount()).append(", ");
				sb.append("charityReference=").append(fee.getCharityReference()).append(", ");
				sb.append("invoiceNumber=").append(fee.getInvoiceNumber()).append("}");
			}
			sb.append("}}");
			LOGGER.debug(sb.toString());
			
		}
		/*
		 * Work out the name of the file to write to
		 */
		String filename = collectedRegistrationFeeBatch.getBatchNumber();
		if ( StringUtils.isEmpty(filename) ) {
        	raiseAlert("JobExecutionService.executeCollectedRegistrationFeeJob()","N/A", 
        			"Rejected job: batchNumber must be set");
        	return;
		}
		String filepath = getFilePath();
		String fileOnDisk = filepath + filename + ".xml";
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {

			/*
			 * Open the file and prepare the BufferedWriter 
			 */
			fileWriter = new FileWriter(fileOnDisk);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			/*
			 * Set up XStream
			 */
	        XStream xstream = new XStream(new DomDriver());
	        xstream.setClassLoader(getClass().getClassLoader());
	
			/*
			 * Write out the contents of the batch as XML
			 */
	        xstream.toXML(collectedRegistrationFeeBatch.getFeePayments(), bufferedWriter);
		
		} catch (IOException e) {
        	raiseAlert("JobExecutionService.executeCollectedRegistrationFeeJob()","N/A", 
        			"IOException writing to XML file: " + e.getMessage());
        	LOGGER.error("IOException writing to XML file", e);
        	return;
		}
		finally {
			if ( bufferedWriter != null ) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
		        	LOGGER.error("IOException closing XML file", e);
				}
			}
			if ( fileWriter != null ) {
				try {
					fileWriter.close();
				} catch (IOException e) {
		        	LOGGER.error("IOException closing XML file", e);
				}
			}
		}

        LOGGER.debug("JobExecutionService.executeCollectedRegistrationFeeJob FILENAME: " + fileOnDisk);

        /*
		 * Set up the job parameters
		 */
        JobParametersBuilder builder = new JobParametersBuilder();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	String scheduledDate = dateFormat.format(new Date());
    	builder.addString("scheduledDate", scheduledDate);

    	SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        String scheduledTime = timeFormat.format(new Date());
        builder.addString("scheduledTime", scheduledTime);

        builder.addString("filename", "file://" + fileOnDisk);

        builder.addString(Constant.BATCH_NUMBER, collectedRegistrationFeeBatch.getBatchNumber());
        
        JobParameters jobParameters = builder.toJobParameters();

        if ( LOGGER.isDebugEnabled() ) {
            LOGGER.debug("executeCollectedRegistrationFeeJob: Final Job Parameter List ");
            StringBuilder sb = new StringBuilder();
            sb.append(" JobParametersMap = {");
            for (String key : jobParameters.getParameters().keySet() ) {
                Object parameter = jobParameters.getParameters().get(key);
                sb.append("key=").append(key).append(", ");
                sb.append("parameter=").append(parameter).append("}");
            }
            sb.append("}");
            LOGGER.debug(sb.toString());
        }

        int feeSize = collectedRegistrationFeeBatch.getFeePayments().size();
        String batchStatus = Constant.BATCH_STATUS_SUCCESSFUL;
        String batchRemarks = "";
        if (feeSize < 1) {
            batchRemarks = "No records processed";
        }
        if (!collectedRegistrationFeeBatch.isOk()) {
            batchStatus  = Constant.BATCH_STATUS_ERROR;
            batchRemarks = fetchErrorMessage(collectedRegistrationFeeBatch.getErrors());
        }
        updateBatchStatus(collectedRegistrationFeeBatch.getBatchNumber(), batchStatus, batchRemarks);

        /*
		 * Run the job
		 */
        String batchNumber = collectedRegistrationFeeBatch.getBatchNumber();
        Job job = null;
        try {
            LOGGER.debug("JobExecutionService.executeCollectedRegistrationFeeJob Starting job: "
                    + COLLECTED_REGISTRATION_FEES_JOB_NAME + ", Date/Time: " + fetchTime());
            job = getJobLocator().getJob(COLLECTED_REGISTRATION_FEES_JOB_NAME);
            JobExecution execution = getJobLauncher().run(job, jobParameters);

            String eStatus = execution.getExitStatus().getExitCode();
            LOGGER.debug("**** Job (" + COLLECTED_REGISTRATION_FEES_JOB_NAME + ") completed with exit code: "
                    +  eStatus + ", Date/Time: " + fetchTime() + " ****");
            if (execution.getExitStatus() != null) {
                if (execution.getExitStatus().compareTo(ExitStatus.FAILED) == 0) {
                    setBatchStatusToError(batchNumber, "Job failed. See log files");
                    throw new Exception ("Batch (" + batchNumber + ") failed to execute. Refer to log files.");
                }
                else if (execution.getExitStatus().compareTo(ExitStatus.STOPPED) == 0) {
                    setBatchStatusToError(batchNumber, "Job interrupted. See log files");
                    throw new Exception ("Batch (" + batchNumber + ") was interrupted. Refer to log files.");
                }
            }
            processJobCompletion(COLLECTED_REGISTRATION_FEES_JOB_NAME, execution, batchNumber);     
            LOGGER.debug("**** Completed Job (" + COLLECTED_REGISTRATION_FEES_JOB_NAME + ") Execution ****");

            LOGGER.debug("JobExecutionService.executeCollectedRegistrationFeeJob Completed job: "
                    + COLLECTED_REGISTRATION_FEES_JOB_NAME);
        }
        catch (Exception exp) {
        	raiseAlert("JobExecutionService.executeCollectedRegistrationFeeJob()","N/A", 
        			"Exception executing job " + COLLECTED_REGISTRATION_FEES_JOB_NAME + ": " + exp.getMessage());
            LOGGER.error("Exception executing job " + COLLECTED_REGISTRATION_FEES_JOB_NAME, exp);
        }
	}
	
	/**
	 * Gets the file path.
	 * 
	 * @return the file path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Sets the file path.
	 * 
	 * @param filePath the new file path
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Initialise class.
	 */
	public void init() {
		assert(!StringUtils.isEmpty(getFilePath()));
	}

    /**
     * Raise an alert for a failed job.
     * 
     * @param jobName name of the job that was running. E.g.
     * transitionalReliefClaimed
     * @param jobId id of the job. Need to get from batch runtime context
     * @param message to send to the alert service
     * 
     * @throws Exception      */
    private void raiseAlert(String jobName, String jobId, String message) {
        /*
         * Create the service messages
         */
        AlertContent alertContent = new AlertContent();
        alertContent.setAlertMessage(message);
        GregorianCalendar cal = new GregorianCalendar();
        alertContent.setDateTimeOfAlert(new XMLGregorianCalendarImpl(cal));
        alertContent.setJobId(jobId);
        alertContent.setJobName(jobName);
        alertContent.setService("giving-batch");

        AlertDetail alertDetail = new AlertDetail();
        alertDetail.setContent(alertContent);

        /*
         * Call the alert service
         */
        AlertPort alertService = null;
		try {
			alertService = getAlertServiceLocator().getAlertPort();
		} catch (Exception e) {
			LOGGER.error("Caught exception trying to find alert service: ", e);
			return;
		}
        alertService.logAlertRequest(alertDetail);
    }

    /**
     * Method to execute the Settled Payments process.
     * 
     * @param paymentSettledBatch payment batch to process.
     */
    public void executeSettledPayment(SettledCharityPaymentsBatch paymentSettledBatch) {

		if ( LOGGER.isDebugEnabled() ) {
			StringBuilder sb = 
				new StringBuilder("executeCollectedRegularDonationPaymentJob: collectedRegistrationFeeBatch = {");
			sb.append("batchNumber=").append(paymentSettledBatch.getBatchNumber()).append(", ");
			sb.append("processDate=").append(paymentSettledBatch.getProcessDate()).append(", ");
			sb.append("errors={");
			ErrorList errorList = paymentSettledBatch.getErrors();
			if ( errorList != null ) {
				List<ErrorMessage> errorMessages = paymentSettledBatch.getErrors().getErrors();
				for (ErrorMessage errorMessage : errorMessages) {
					sb.append("{errorDefaultMessage=").append(errorMessage.getErrorDefaultMessage()).append(", ");
					sb.append("errorField=").append(errorMessage.getErrorField()).append(", ");
					sb.append("errorMessageKey=").append(errorMessage.getErrorMessageKey()).append("}");
				}
			}
			sb.append("}, feePayments={");
			List<SettledCharityPayments> regularDonation = paymentSettledBatch.getSettledPayments();
			for (SettledCharityPayments fee : regularDonation) {
				sb.append("{feeAmount=").append(fee.getInvoiceOrCrNoteNumber()).append(", ");
				sb.append("invoiceNumber=").append(fee.getInvoiceOrCrNoteNumber()).append("}");
			}
			sb.append("}}");
			LOGGER.debug(sb.toString());
			
		}
		
		 // Work out the name of the file to write to
		 
		String filename = paymentSettledBatch.getBatchNumber();
		if ( StringUtils.isEmpty(filename) ) {
        	raiseAlert("JobExecutionService.executeCollectedRegularDonationPaymentJob()","N/A", 
        			"Rejected job: batchNumber must be set");
        	return;
		}
		String filepath = getFilePath();
		String fileOnDisk = filepath + filename + ".xml";
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {

			
			 // Open the file and prepare the BufferedWriter 
			 
			fileWriter = new FileWriter(fileOnDisk);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			
			 // Set up XStream
			 
	        XStream xstream = new XStream(new DomDriver());
	        xstream.setClassLoader(getClass().getClassLoader());
	
			
			 // Write out the contents of the batch as XML
			 
	        xstream.toXML(paymentSettledBatch.getSettledPayments(), bufferedWriter);
		
		} catch (IOException e) {
        	raiseAlert("JobExecutionService.executeCollectedRegularDonationPaymentJob()","N/A", 
        			"IOException writing to XML file: " + e.getMessage());
        	LOGGER.error("IOException writing to XML file", e);
        	return;
		}
		finally {
			if ( bufferedWriter != null ) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
		        	LOGGER.error("IOException closing XML file", e);
				}
			}
			if ( fileWriter != null ) {
				try {
					fileWriter.close();
				} catch (IOException e) {
		        	LOGGER.error("IOException closing XML file", e);
				}
			}
		}

		
		 // Set up the job parameters
		 
        JobParametersBuilder builder = new JobParametersBuilder();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	String scheduledDate = dateFormat.format(new Date());
    	builder.addString("scheduledDate", scheduledDate);

    	SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        String scheduledTime = timeFormat.format(new Date());
        builder.addString("scheduledTime", scheduledTime);

        builder.addString("filename", "file://" + fileOnDisk);

        builder.addString(Constant.BATCH_NUMBER, paymentSettledBatch.getBatchNumber());
        
        JobParameters jobParameters = builder.toJobParameters();
		
        if ( LOGGER.isDebugEnabled() ) {
            LOGGER.debug("executeCollectedRegularDonationPaymentJob: Final Job Parameter List ");
            StringBuilder sb = new StringBuilder();
            sb.append(" JobParametersMap = {");
            for (String key : jobParameters.getParameters().keySet() ) {
                Object parameter = jobParameters.getParameters().get(key);
                sb.append("key=").append(key).append(", ");
                sb.append("parameter=").append(parameter).append("}");
            }
            sb.append("}");
            LOGGER.debug(sb.toString());
        }

        int feeSize = paymentSettledBatch.getSettledPayments().size();
        String batchStatus = Constant.BATCH_STATUS_SUCCESSFUL;
        String batchRemarks = "";
        if (feeSize < 1) {
            batchRemarks = "No records processed";
        }
        if (!paymentSettledBatch.isOk()) {
            batchStatus  = Constant.BATCH_STATUS_ERROR;
            batchRemarks = fetchErrorMessage(paymentSettledBatch.getErrors());
        }
        updateBatchStatus(paymentSettledBatch.getBatchNumber(), batchStatus, batchRemarks);


         // Run the job
		String batchNumber = paymentSettledBatch.getBatchNumber();
        Job job = null;
        try {
            LOGGER.debug("JobExecutionService.executeSettledPayment Starting job: " + SETTLED_PAYMENT_BATCH_NAME
                    + ", Date/Time: " + fetchTime());
            job = getJobLocator().getJob(SETTLED_PAYMENT_BATCH_NAME);
            JobExecution execution = getJobLauncher().run(job, jobParameters);

            String eStatus = execution.getExitStatus().getExitCode();
            LOGGER.debug("**** Job (" + SETTLED_PAYMENT_BATCH_NAME + ") completed with exit code: "
                    +  eStatus + ", Date/Time: " + fetchTime() + " ****");
            if (execution.getExitStatus() != null) {
                if (execution.getExitStatus().compareTo(ExitStatus.FAILED) == 0) {
                    setBatchStatusToError(batchNumber, "Job failed. See log files");
                    throw new Exception ("Batch (" + batchNumber + ") failed to execute. Refer to log files.");
                }
                else if (execution.getExitStatus().compareTo(ExitStatus.STOPPED) == 0) {
                    setBatchStatusToError(batchNumber, "Job interrupted. See log files");
                    throw new Exception ("Batch (" + batchNumber + ") was interrupted. Refer to log files.");
                }
            }
            processJobCompletion(SETTLED_PAYMENT_BATCH_NAME, execution, batchNumber);
            LOGGER.debug("**** Completed Job (" + SETTLED_PAYMENT_BATCH_NAME + ") Execution ****");

        }
        catch (Exception exp) {
        	raiseAlert("JobExecutionService.executeCollectedRegularDonationPaymentJob()","N/A", 
        			"Exception executing job " + SETTLED_PAYMENT_BATCH_NAME + ": " + exp.getMessage());
            LOGGER.error("Exception executing job " + SETTLED_PAYMENT_BATCH_NAME, exp);
        }
			
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.givingbatch.launcher.JobExecutionService#executeCollectedEventRegistrationFeeJob(com.virginmoney.glis.messages.SettledCharityPaymentsBatch)
	 */
	public void executeCollectedEventRegistrationFeeJob(
			SettledCharityPaymentsBatch eventRegistrationFeePaidBatch) {
		
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.givingbatch.launcher.JobExecutionService#executeCollectedRegularDonationPaymentJob(com.virginmoney.glis.messages.SettledCharityPaymentsBatch)
	 */
	public void executeCollectedRegularDonationPaymentJob(
			SettledCharityPaymentsBatch regularDonationPaymentCollectedBatch) {
		
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.givingbatch.launcher.JobExecutionService#executePaidTransactionChargeJob(com.virginmoney.glis.messages.SettledCharityPaymentsBatch)
	 */
	public void executePaidTransactionChargeJob(
			SettledCharityPaymentsBatch transactionChargePaidBatch) {
		
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.givingbatch.launcher.JobExecutionService#executePaidTransactionFeeJob(com.virginmoney.glis.messages.SettledCharityPaymentsBatch)
	 */
	public void executePaidTransactionFeeJob(
			SettledCharityPaymentsBatch transactionFeePaidBatch) {
		
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.givingbatch.launcher.JobExecutionService#executeReceivedGiftAidJob(com.virginmoney.glis.messages.SettledCharityPaymentsBatch)
	 */
	public void executeReceivedGiftAidJob(
			SettledCharityPaymentsBatch giftAidReceivedBatch) {
		
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.givingbatch.launcher.JobExecutionService#executeReceivedTransitionalReliefJob(com.virginmoney.glis.messages.SettledCharityPaymentsBatch)
	 */
	public void executeReceivedTransitionalReliefJob(
			SettledCharityPaymentsBatch transitionalReliefReceivedBatch) {
		
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.givingbatch.launcher.JobExecutionService#executeCollectedDonationPaymentJob(com.virginmoney.glis.messages.SettledCharityPaymentsBatch)
	 */
	public void executeCollectedDonationPaymentJob(
			SettledCharityPaymentsBatch donationPaymentCollectedBatch) {
		
	}

    /**
     * Retrieves the cutoff implementation indicator from the batch database.
     * If a cutoff parameter is available in the Batch DB, and it has a date value, then that date/time
     * is used as the cutoff value. If no date/time is available, then the End-Of-Day of previous day is used.
     * If Batch DB does not return a cut-off parameter, then SYSDATE is used as cut-off parameter.
     * 
     * @return String Cutoff time in String format.
     */
    private String getCutoffDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        DateTime now = new DateTime();
        String nowCutoff = sdfTime.format(now.toDate());
        String eodCutoff = sdf.format(now.toDate()) + " 00:00:01";
        String cutOffDate = nowCutoff;
        LOGGER.debug("getCutoffDateTime: Now/EOD = " + nowCutoff + "/" + eodCutoff);

        FetchBatchParameterRequest request = new FetchBatchParameterRequest();
        request.setParameterName(Constant.BATCH_PARAMETER_CUTOFF_DATE);
        try {
            FetchBatchParameterResponse response =
            givingBatchExtManagementServiceLocatorImpl.getGivingBatchExtManagementPort().fetchBatchParameter(request);
            if (response != null && response.getBatchParameter() != null) {
                LOGGER.debug("getCutoffDateTime: Cut-off enabled");
                if (response.getBatchParameter().getDateTimeValue() != null) {
                    Date batchDate =  response.getBatchParameter().getDateTimeValue().toGregorianCalendar().getTime();
                    cutOffDate = sdfTime.format(batchDate);
                    LOGGER.debug("getCutoffDateTime: Cut-off reset to  = " + cutOffDate);
                }
                else {
                    cutOffDate = eodCutoff;
                    LOGGER.debug("getCutoffDateTime: Cut-off set to EOD");
                }
            }
        }
        catch (GivingBatchExtManagementServiceFaultException se) {
            LOGGER.error("Service Error retrieving cutoff date", se);
        }
        catch (Exception e) {
            LOGGER.error("Error retrieving cutoff date", e);
        }

        LOGGER.debug("getCutoffDateTime: Using cutoff datetime  = " + cutOffDate);

        return cutOffDate;
    }

    /**
     * Updates the batch status for the given batch.
     * 
     * @param batchNumber Batch number of batch to update.
     * @param batchStatus New status.
     * @param remarks Any comments or error message.
     */
    private void updateBatchStatus(String batchNumber, String batchStatus, String remarks) {
        LOGGER.debug("inside updateBatchStatus - Number/Status: " + batchNumber + "/" + batchStatus);
        try {
            UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
            request.setBatchNumber(batchNumber);
            request.setEndDateTime(Util.buildXMLGregorianDate());
            request.setStatus(batchStatus);
            request.setErrorMessage(remarks);
            givingBatchExtManagementServiceLocatorImpl.getGivingBatchExtManagementPort().updateBatchStatus(request);
        }
        catch (Exception e) {
            LOGGER.error("Unexpected error setting batch status to complete when no records found", e);
        }

    }

    /**
     * Retrieves the error message(s) from the given error list.
     * 
     * @param errors Error list
     * 
     * @return A combined string of error messages.
     */
    private String fetchErrorMessage(ErrorList errors) {
        if (errors == null) {
            return "";
        }
        String errMsg = "";
        for (ErrorMessage err : errors.getErrors()) {
            errMsg += err.getErrorMessageKey();
        }

        return errMsg;
    }

    /**
     * Process job completion.
     * 
     * @param jobName the job name
     * @param execution the execution
     * @param bNumber the b number
     */
    private void processJobCompletion(String jobName, JobExecution execution, String bNumber) {
        String batchNumber = (String)execution.getExecutionContext().get(Constant.BATCH_NUMBER);
        if (batchNumber == null || batchNumber.length() < 1) {
            batchNumber = bNumber;
        }
        String cStatus = (String)execution.getExecutionContext().get(Constant.BATCH_PARAMETER_GLIS_PROCESSING);
        LOGGER.debug("**** Job (" + jobName + ") Batch (" + batchNumber + ") has status = " + cStatus + " ****");
        LOGGER.debug("**** Job (" + jobName + ") Exit status = " + execution.getExitStatus().getExitCode() + " ****");

        Map<String, JobParameter> params = execution.getJobInstance().getJobParameters().getParameters();
        
        if (params != null && !params.isEmpty()) {
            LOGGER.debug("**** Job (" + jobName + ") has parameters = " + params.size() + " ****");
            Iterator itr = params.keySet().iterator();
            while (itr.hasNext()) {
                String key = (String)itr.next();
                JobParameter jp = params.get(key);
                LOGGER.debug("**** Key (" + key + ") has value = " + jp.toString() + " ****");
            }
        }

        if (cStatus != null && cStatus.equalsIgnoreCase("YES")) {
            LOGGER.debug("**** Job (" + jobName + ") Processing handled by GLIS Response manager. ****");
            return;
        }

        jobChainProcessor.processNextJob(batchNumber, null);
    }

    /**
     * Sets the batch status to error.
     * 
     * @param batchNumber the batch number
     * @param errorMessage the error message
     */
    private void setBatchStatusToError(String batchNumber, String errorMessage) {
        if (batchNumber == null || batchNumber.length() < 1) {
            return;
        }
        try {
                UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setEndDateTime(Util.buildXMLGregorianDate());
                request.setStatus(Constant.BATCH_STATUS_FAIL);
                request.setErrorMessage(errorMessage);
                givingBatchExtManagementServiceLocatorImpl.getGivingBatchExtManagementPort().updateBatchStatus(request);
            }
            catch (Exception e) {
                LOGGER.error("Unexpected error setting batch status to complete when no records found", e);
            }
    }

    private String getKickoffDateTime() {
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyyMMddHHmmss");
        DateTime now = new DateTime();
        String nowCutoff = sdfTime.format(now.toDate());

        LOGGER.debug("getKickoffDateTime: Using datetime  = " + nowCutoff);

        return nowCutoff;
    }

    private String fetchTime() {
        DateTimeFormatter fmt = Constant.DATETIMEFORMATTER;
        DateTime sDate = new DateTime();
        String dt = fmt.print(sDate);
        return dt;
    }


}
