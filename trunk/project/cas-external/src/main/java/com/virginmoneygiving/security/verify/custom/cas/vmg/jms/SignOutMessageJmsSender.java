package com.virginmoneygiving.security.verify.custom.cas.vmg.jms;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.virginmoneygiving.security.verify.custom.cas.vmg.SingleSignOutSender;

/**
 *
 * @author Phil Collins
 *
 */
public class SignOutMessageJmsSender implements SingleSignOutSender{

    private static final Logger LOGGER = Logger.getLogger(SignOutMessageJmsSender.class);

	/** The Spring JMS message template to use for sending */
    private JmsTemplate jmsTemplate;

    /**
     * Setter for the JMS Template
     *
     * @param jmsTemplate
     */
    //@Resource
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Send a Message to a sign out notification all all web apps listening.
     *
     */
	public void sendSignOutMessage(String TicketId) {

        sendMessage(TicketId);
	}


    private void sendMessage(final String messageContent) {
    	LOGGER.debug("Sending message " + messageContent);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {

                TextMessage message = session.createTextMessage();
                message.setText(messageContent);
                return message;
            }
        });
    }


}
