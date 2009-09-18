package com.virginmoneygiving.sugarcrmservice.processor;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import com.virginmoneygiving.sugarcrmservice.client.messages.CharityContactSugarCrmAlert;
import com.virginmoneygiving.sugarcrmservice.client.messages.CharityRegistrationSugarCrmAlert;
import com.virginmoneygiving.sugarcrmservice.client.messages.ClientVmgCharityContact;
import com.virginmoneygiving.sugarcrmservice.client.messages.SugarCrmMessageType;
import com.virginmoneygiving.sugarcrmservice.helper.DataMappingHelper;
import com.virginmoneygiving.sugarcrmservice.helper.ProcessorHelper;
import com.virginmoneygiving.sugarcrmservice.locator.SugarCRMWebServiceLocator;
import com.virginmoneygiving.sugarcrmservice.service.messages.VmgCharityContact;

/**
 * Utility class that processes {@link CharityRegistrationSugarCrmAlert} type of
 * messages received from the queue.
 * 
 * @author Vikas Kale
 */
public class CharityContactSugarCrmAlertProcessor {

	/** Logger instance. */
	private static final Logger LOGGER = Logger
			.getLogger(CharityContactSugarCrmAlertProcessor.class);

	/** Username to access SugarCRM web service. */
	private String username;

	/** Password to access SugarCRM web service. */
	private String password;

	/** SugarCRM services locator. */
	private SugarCRMWebServiceLocator service;

	/**
	 * Private constructor.
	 */
	public CharityContactSugarCrmAlertProcessor() {
		super();
	}

	/**
	 * Process {@link CharityContactSugarCrmAlert} object received from the
	 * message queue.
	 * 
	 * @param donationAlert
	 *            the {@link CharityContactSugarCrmAlert} object to process.
	 */
	public void processCharityContactSugarCrmAlert(
			final CharityContactSugarCrmAlert contactSugarCrmAlert) {
		boolean successfulExecution;
		if (contactSugarCrmAlert != null) {
			VmgCharityContact vmgCharityContact = new VmgCharityContact();
			ClientVmgCharityContact charityContact = contactSugarCrmAlert.getClientVmgCharityContact();
			if (charityContact != null) {
				vmgCharityContact = DataMappingHelper.mapCharityContact(charityContact);
			}

			if (SugarCrmMessageType.CREATE_CHARITY_CONTACT
					.equals(contactSugarCrmAlert.getSugarCrmMessageType())) {
				try {
					successfulExecution = service.getSugarsoapPortType()
							.vmgCharityContactCreate(username, password,
									vmgCharityContact);
					if (successfulExecution) {
						LOGGER
								.debug("CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert() ::" +
										" vmgCharityContactCreate() service is executed successfully.");
					} else {
						LOGGER
								.error("CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert() ::" +
										" vmgCharityContactCreate() service is failed.");
						LOGGER
								.error("Input Details : "
										+ ProcessorHelper
												.convertVmgCharityContactToString(vmgCharityContact));
					}
				} catch (RemoteException remoteException) {
					LOGGER
							.error("Thrown RemoteException in CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert()" +
									" while invoking in vmgCharityContactCreate() service ::");
					LOGGER
							.error("Input Details : "
									+ ProcessorHelper
											.convertVmgCharityContactToString(vmgCharityContact));
					LOGGER
							.error(
									"Thrown RemoteException in CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert()"
											+ remoteException.getMessage(),
									remoteException);
				}
			}

			else if (SugarCrmMessageType.UPDATE_CHARITY_CONTACT
					.equals(contactSugarCrmAlert.getSugarCrmMessageType())) {
				try {
					successfulExecution = service.getSugarsoapPortType()
							.vmgCharityContactUpdate(username, password,
									vmgCharityContact);
					if (successfulExecution) {
						LOGGER
								.debug("CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert() ::" +
										" vmgCharityContactUpdate() service is executed successfully.");
					} else {
						LOGGER
								.error("CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert() ::" +
										" vmgCharityContactUpdate() service is failed.");
						LOGGER
								.error("Input Details : "
										+ ProcessorHelper
												.convertVmgCharityContactToString(vmgCharityContact));
					}
				} catch (RemoteException remoteException) {
					LOGGER
							.error("Thrown RemoteException in CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert()" +
									" while invoking in vmgCharityContactUpdate() service ::");
					LOGGER
							.error("Input Details : "
									+ ProcessorHelper
											.convertVmgCharityContactToString(vmgCharityContact));
					LOGGER
							.error(
									"Thrown RemoteException in CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert()"
											+ remoteException.getMessage(),
									remoteException);
				}
			}

			else if (SugarCrmMessageType.REMOVE_CHARITY_CONTACT
					.equals(contactSugarCrmAlert.getSugarCrmMessageType())) {
				try {
					successfulExecution = service.getSugarsoapPortType()
							.vmgCharityContactRemove(username, password,
									vmgCharityContact);
					if (successfulExecution) {
						LOGGER
								.debug("CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert() ::" +
										" vmgCharityContactRemove() service is executed successfully.");
					} else {
						LOGGER
								.error("CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert() ::" +
										" vmgCharityContactRemove() service is failed.");
						LOGGER
								.error("Input Details : "
										+ ProcessorHelper
												.convertVmgCharityContactToString(vmgCharityContact));
					}
				} catch (RemoteException remoteException) {
					LOGGER
							.error("Thrown RemoteException in CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert()" +
									" while invoking in vmgCharityContactRemove() service ::");
					LOGGER
							.error("Input Details : "
									+ ProcessorHelper
											.convertVmgCharityContactToString(vmgCharityContact));
					LOGGER
							.error(
									"Thrown RemoteException in CharityContactSugarCrmAlertProcessor.processCharityContactSugarCrmAlert()"
											+ remoteException.getMessage(),
									remoteException);
				}
			}
		}
	}

	/**
	 * Sets the SugarCRMWebServiceLocator.
	 * 
	 * @param service
	 *            the service to set
	 */
	public void setService(SugarCRMWebServiceLocator service) {
		this.service = service;
	}

	/**
	 * Sets the username.
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
