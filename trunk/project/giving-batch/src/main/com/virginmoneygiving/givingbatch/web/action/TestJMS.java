package com.virginmoneygiving.givingbatch.web.action;


import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The Class TestJMS for testing the individual batch jobs.
 */
public class TestJMS extends ActionSupport {

	/** The jms template. */
	private JmsTemplate jmsTemplate;
	
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
	
	
    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute() {
    	
    	if ( !StringUtils.isEmpty(jobName) ) {

	        jmsTemplate.send(new MessageCreator() {
	            public Message createMessage(Session session) throws JMSException {
	                TextMessage message = session.createTextMessage();
	    			message.setStringProperty("jobName", getJobName());	    			
	                return message;
	            }
	        });
    	
    	}		
		return SUCCESS;
    }	
}