package com.virginmoneygiving.givingbatch.web.action;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Simple test class to handle request for GLIS response JMS testing.
 * 
 * @author Puneet Swarup
 */
public class GlisResponseTestJMS extends ActionSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -23467676577L;
		
	/** The LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(GlisResponseTestJMS.class);
	
	/** The jms template. */
	private JmsTemplate jmsTemplate;
	
	/** The text. */
	private String text;

	/**
	 * Sets the jms template.
	 * 
	 * @param jmsTemplate the new jms template
	 */
	@Resource
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
		if(LOGGER.isTraceEnabled()) {
		    LOGGER.trace("JMSTemplate injected in GlisResponseTestJMS action.");
		}
	}

	/**
	 * Sets the text.
	 * 
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
	    if(LOGGER.isTraceEnabled()) {
	        LOGGER.trace("Before sending message to queue............");
	    }
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setStringProperty("payload", text);
				LOGGER.debug("Message : " + message.getText());
				return message;
			}
		});
		 if(LOGGER.isTraceEnabled()) {
		     LOGGER.trace("After sending message to queue............");
		 }

		return SUCCESS;
	}
}