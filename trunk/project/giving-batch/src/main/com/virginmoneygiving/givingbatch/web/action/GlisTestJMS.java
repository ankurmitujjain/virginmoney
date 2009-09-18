package com.virginmoneygiving.givingbatch.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.Resource;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The Class GlisTestJMS for testing the response from Glis for batch Jobs UCPHB150 and UCPHB260.
 */
public class GlisTestJMS extends ActionSupport {
    
    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER = Logger.getLogger(GlisTestJMS.class);

	/** The JMS template to use for sending message. */
	private JmsTemplate jmsTemplate;
	
	/** The path to the data files. */
	String filePath = null;

	/** The name of the job to run. */
	private String jobName;
	
	/**
	 * Gets the job name.
	 * 
	 * @return the job name
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * Sets the job name.
	 * 
	 * @param jobName the new job name
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * Sets the jms template.
	 * 
	 * @param jmsTemplate the new jms template
	 */
	@Resource
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Sets the file path.
     * 
     * @param filePath the new file path
     */
    public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		
		if ( !StringUtils.isEmpty(jobName) ) {
			FileReader fileReader = null;
			BufferedReader bufferedReader = null;
	    	final StringBuffer stringBuffer = new StringBuffer();
	    	
	    	/*
	    	 * Get the data for the message
	    	 */
			try {
				fileReader = new FileReader(filePath + File.separator + jobName + ".xml");
				bufferedReader = new BufferedReader(fileReader);
				String line = null;
				while( (line = bufferedReader.readLine()) != null ) {
					stringBuffer.append(line);
				}
			} catch (FileNotFoundException fileNotFoundException) {
			    LOGGER.error("File not found"+fileReader, fileNotFoundException);
				return SUCCESS;
			} catch (IOException ioException) {
			    LOGGER.error("IO Exception"+fileReader, ioException);
				return SUCCESS;
			}
			finally {
				if ( bufferedReader != null ) {
					try {
						bufferedReader.close();
					} catch (IOException ioException) {
					    LOGGER.error("Error occured while closing buffer reader"+bufferedReader, ioException);
					}
				}
				if ( fileReader != null ) {
					try {
						fileReader.close();
					} catch (IOException ioException) {
					    LOGGER.error("Error occured while closing file reader"+fileReader, ioException);
					}
				}
			}
	    	
	        jmsTemplate.send(new MessageCreator() {
	            public Message createMessage(Session session) throws JMSException {
	                TextMessage message = session.createTextMessage();
	                message.setStringProperty("payload", stringBuffer.toString());
	                return message;
	            }
	        });
		}		
		return SUCCESS;
    }

	
}