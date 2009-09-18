package com.virginmoneygiving.services.jms;

import org.apache.log4j.Logger;

import java.util.Map;
import java.io.Serializable;

import com.virginmoneygiving.services.email.TemplateEmailService;
import com.virginmoneygiving.templateemail.service.messages.Message;
import com.thoughtworks.xstream.XStream;

import javax.annotation.Resource;


/**
 * The Class MessageDelegateImpl.
 */
public class MessageDelegateImpl implements MessageDelegate {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(MessageDelegateImpl.class);
    
    /** The template email service. */
    private TemplateEmailService templateEmailService;
    
    /** The xstream. */
    private XStream xstream = new XStream();

    /* (non-Javadoc)
     * @see com.virginmoneygiving.services.jms.MessageDelegate#handleMessage(java.lang.String)
     */
    public void handleMessage(String messageText) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("MessageDelegateImpl::handleMessage() - START");
        }
        LOGGER.debug("################################################################################## ");
        LOGGER.debug("#####################   String message received: ################################# ");
        LOGGER.debug("################################################################################## ");
        LOGGER.debug(messageText);
        
        Message message = (Message)xstream.fromXML(messageText);
        templateEmailService.sendEmail(message);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("MessageDelegateImpl::handleMessage() - END");
        }
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.services.jms.MessageDelegate#handleMessage(java.util.Map)
     */
    public void handleMessage(Map message) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("MessageDelegateImpl::handleMessage() - START");
        }
        LOGGER.debug("################################################################################## ");
        LOGGER.debug("#####################  Map message received: ##################################### ");
        LOGGER.debug("################################################################################## ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("MessageDelegateImpl::handleMessage() - END");
        }
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.services.jms.MessageDelegate#handleMessage(byte[])
     */
    public void handleMessage(byte[] message) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("MessageDelegateImpl::handleMessage() - START");
        }
        LOGGER.debug("################################################################################## ");
        LOGGER.debug("#####################  byte message received: #################################### ");
        LOGGER.debug("################################################################################## ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("MessageDelegateImpl::handleMessage() - END");
        }
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.services.jms.MessageDelegate#handleMessage(java.io.Serializable)
     */
    public void handleMessage(Serializable message) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("MessageDelegateImpl::handleMessage() - START");
        }
        LOGGER.debug("################################################################################## ");
        LOGGER.debug("#####################  Serializable message received: ############################ ");
        LOGGER.debug("################################################################################## ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("MessageDelegateImpl::handleMessage() - END");
        }
    }

    /**
     * Sets the template email service.
     * 
     * @param templateEmailService the new template email service
     */
    @Resource
    public void setTemplateEmailService(TemplateEmailService templateEmailService) {
        this.templateEmailService = templateEmailService;
    }
}
