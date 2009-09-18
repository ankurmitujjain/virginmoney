package com.virginmoneygiving.bamservice.client.jms;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.thoughtworks.xstream.XStream;

/**
 * Class to send JMS message for Business Activity Monitoring to the JMS queue.
 *
 * @author Puneet Swarup
 */
@Resource
public class BusinessActivityJmsMessageSender {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(BusinessActivityJmsMessageSender.class);

    /** Spring JMS template. */
    private JmsTemplate jmsTemplate;

    /**
     * Setter for the JMS Template.
     *
     * @param jmsTemplate the {@link JmsTemplate}
     */
    @Resource
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Puts the message into the queue.
     *
     * @param messageContent the messageContent
     */
    public void sendMessage(final Object messageContent) {

        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {

                TextMessage message = session.createTextMessage();
                XStream xstream = new XStream();
                message.setText(xstream.toXML(messageContent));
                LOGGER.trace(message.getText());
                return message;
            }
        });
    }
}
