package com.virginmoneygiving.sugarcrmservice.jms.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.virginmoneygiving.sugarcrmservice.client.messages.CharityAddressSugarCrmAlert;
import com.virginmoneygiving.sugarcrmservice.client.messages.CharityContactSugarCrmAlert;
import com.virginmoneygiving.sugarcrmservice.client.messages.CharityRegistrationSugarCrmAlert;
import com.virginmoneygiving.sugarcrmservice.jms.SugarCRMMessageDelegate;
import com.virginmoneygiving.sugarcrmservice.processor.CharityAddressSugarCrmAlertProcessor;
import com.virginmoneygiving.sugarcrmservice.processor.CharityContactSugarCrmAlertProcessor;
import com.virginmoneygiving.sugarcrmservice.processor.CharityRegistrationSugarCrmAlertProcessor;

/**
 * Implementation class for SugarCRMMessageDelegate. Reads messages from queue
 * and invokes processors to process message using class names of messages.
 */
public class DefaultSugarCRMMessageDelegateImpl implements SugarCRMMessageDelegate {

	/** Logger to log events. */
	private static final Logger LOGGER = Logger
			.getLogger(DefaultSugarCRMMessageDelegateImpl.class);

	/** JMS template to send email messages. */
	private JmsTemplate jmsTemplate;

	/** Simple class name for Charity Registration Alert. */
	private static final String CHARITY_REGISTRATION_ALERT_CLASS_NAME = CharityRegistrationSugarCrmAlert.class
			.getSimpleName();

	/** Simple class name for Charity Contact Alert. */
	private static final String CHARITY_CONTACT_ALERT_CLASS_NAME = CharityContactSugarCrmAlert.class
			.getSimpleName();

	/** Simple class name for Charity Contact Alert. */
	private static final String CHARITY_ADDRESS_ALERT_CLASS_NAME = CharityAddressSugarCrmAlert.class
			.getSimpleName();

	/** Processor to handle operations related Charity Registration. */
	private CharityRegistrationSugarCrmAlertProcessor charityRegistrationProcessor;

	/** Processor to handle operations related Charity Contact. */
	private CharityContactSugarCrmAlertProcessor charityContactProcessor;

	/** Processor to handle operations related Charity Address. */
	private CharityAddressSugarCrmAlertProcessor charityAddressProcessor;

	/**
	 * Sets the jms template.
	 * 
	 * @param jmsTemplate
	 *            the jmsTemplate to set
	 */
	@Resource
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/** {@inheritDoc} */
	public void handleMessage(final String message) {
		final Object messageContent = getMessageObject(message);
		if (messageContent != null) {
			try {
				processMessage(messageContent);
			} catch (Exception exception) {
				final String errorMessage = "Exception occurred while processing message received from sugar crm queue.";
				LOGGER.error(errorMessage, exception);
				// TODO throw new UserAlertsException(errorMessage, exception);
			}
		} else {
			LOGGER.error("Unable to identify object from message queue.");
		}
	}

	/**
	 * Decodes the given string message and returns the object embedded in
	 * message.
	 * 
	 * @param message
	 *            the string message received from the queue.
	 * 
	 * @return the embedded object from the message, if any, else
	 *         <code>null</code>.
	 */
	private Object getMessageObject(final String message) {
		final XStream xstream = new XStream(new DomDriver());
		xstream.setClassLoader(getClass().getClassLoader());
		return xstream.fromXML(message);
	}

	/**
	 * Process the object received from message queue.
	 * 
	 * @param messageContent
	 *            the object received from queue.
	 */
	protected void processMessage(final Object messageContent) {

		if (LOGGER.isTraceEnabled()) {
			LOGGER
					.trace("DefaultSugarCRMMessageDelegateImpl.processMessage - START");
		}

		if (CHARITY_REGISTRATION_ALERT_CLASS_NAME
				.equalsIgnoreCase(messageContent.getClass().getSimpleName())) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER
						.debug("Intercepted object of charity registration alert.");
			}

			final CharityRegistrationSugarCrmAlert registrationSugarCrmAlert = (CharityRegistrationSugarCrmAlert) messageContent;
			charityRegistrationProcessor
					.processCharityRegistrationSugarCrmAlert(registrationSugarCrmAlert);

		} else if (CHARITY_CONTACT_ALERT_CLASS_NAME
				.equalsIgnoreCase(messageContent.getClass().getSimpleName())) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Intercepted object of charity contact alert.");
			}

			final CharityContactSugarCrmAlert contactSugarCrmAlert = (CharityContactSugarCrmAlert) messageContent;
			charityContactProcessor
					.processCharityContactSugarCrmAlert(contactSugarCrmAlert);

		} else if (CHARITY_ADDRESS_ALERT_CLASS_NAME
				.equalsIgnoreCase(messageContent.getClass().getSimpleName())) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Intercepted object of charity address alert.");
			}
			final CharityAddressSugarCrmAlert addressSugarCrmAlert = (CharityAddressSugarCrmAlert) messageContent;
			charityAddressProcessor
					.processCharityAddressSugarCrmAlert(addressSugarCrmAlert);
		}

		if (LOGGER.isTraceEnabled()) {
			LOGGER
					.trace("DefaultSugarCRMMessageDelegateImpl.processMessage - END");
		}
	}

	/**
	 * @param charityRegistrationProcessor
	 *            the charityRegistrationProcessor to set
	 */
	public void setCharityRegistrationProcessor(
			CharityRegistrationSugarCrmAlertProcessor charityRegistrationProcessor) {
		this.charityRegistrationProcessor = charityRegistrationProcessor;
	}

	/**
	 * @param charityContactProcessor
	 *            the charityContactProcessor to set
	 */
	public void setCharityContactProcessor(
			CharityContactSugarCrmAlertProcessor charityContactProcessor) {
		this.charityContactProcessor = charityContactProcessor;
	}

	/**
	 * @param charityAddressProcessor
	 *            the charityAddressProcessor to set
	 */
	public void setCharityAddressProcessor(
			CharityAddressSugarCrmAlertProcessor charityAddressProcessor) {
		this.charityAddressProcessor = charityAddressProcessor;
	}

}
