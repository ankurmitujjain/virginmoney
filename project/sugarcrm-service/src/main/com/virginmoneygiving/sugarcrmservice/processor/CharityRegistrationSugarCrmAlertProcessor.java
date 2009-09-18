package com.virginmoneygiving.sugarcrmservice.processor;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import com.virginmoneygiving.sugarcrmservice.client.messages.CharityRegistrationSugarCrmAlert;
import com.virginmoneygiving.sugarcrmservice.client.messages.SugarCrmMessageType;
import com.virginmoneygiving.sugarcrmservice.helper.DataMappingHelper;
import com.virginmoneygiving.sugarcrmservice.helper.ProcessorHelper;
import com.virginmoneygiving.sugarcrmservice.locator.SugarCRMWebServiceLocator;
import com.virginmoneygiving.sugarcrmservice.service.messages.VmgCharity;
import com.virginmoneygiving.sugarcrmservice.service.messages.VmgCharityContact;

/**
 * Utility class that processes {@link CharityRegistrationSugarCrmAlert} type of
 * messages received from the queue.
 * 
 * @author Vikas Kale
 */
public class CharityRegistrationSugarCrmAlertProcessor {

	/** Logger instance. */
	private static final Logger LOGGER = Logger
			.getLogger(CharityRegistrationSugarCrmAlertProcessor.class);


	/** Username to access SugarCRM web service. */
	private String username;

	/** Password to access SugarCRM web service. */
	private String password;

	/** SugarCRM services locator. */
	private SugarCRMWebServiceLocator service;

	/**
	 * Private constructor.
	 */
	public CharityRegistrationSugarCrmAlertProcessor() {
		super();
	}

	/**
	 * Process {@link CharityRegistrationSugarCrmAlert} object received from the
	 * message queue.
	 * 
	 * @param registrationSugarCrmAlert
	 *            the {@link CharityRegistrationSugarCrmAlert} object to
	 *            process.
	 */
	public void processCharityRegistrationSugarCrmAlert(
			final CharityRegistrationSugarCrmAlert registrationSugarCrmAlert) {
		boolean successfulExecution;
		if (registrationSugarCrmAlert != null) {
			VmgCharity vmgCharity = new VmgCharity();
			VmgCharityContact vmgCharityContact = new VmgCharityContact();

			vmgCharity = DataMappingHelper.mapCharityDetails(registrationSugarCrmAlert.getClientVmgCharity());
			vmgCharityContact = DataMappingHelper.mapCharityContact(registrationSugarCrmAlert.getClientVmgCharityContact());
			
			if (SugarCrmMessageType.CHARITY_REGISTRATION_PART_ONE
					.equals(registrationSugarCrmAlert.getSugarCrmMessageType())) {
				
				try {
					successfulExecution = service.getSugarsoapPortType()
							.vmgCharityPart1Registration(username, password,
									vmgCharity, vmgCharityContact);
					if (successfulExecution) {
						LOGGER
								.debug("CharityRegistrationSugarCrmAlertProcessor.processCharityRegistrationSugarCrmAlert() ::" +
										" vmgCharityPart1Registration() service is executed successfully.");
					} else {
						LOGGER
								.error("CharityRegistrationSugarCrmAlertProcessor.processCharityRegistrationSugarCrmAlert() ::" +
										" vmgCharityPart1Registration() service is failed.");
						LOGGER
								.error("Input Details : "
										+ ProcessorHelper
												.convertVmgCharityToString(vmgCharity)
										+ "\n"
										+ ProcessorHelper
												.convertVmgCharityContactToString(vmgCharityContact));
					}
				} catch (RemoteException remoteException) {
					LOGGER
							.error("Thrown RemoteException in " +
									"CharityRegistrationSugarCrmAlertProcessor.processCharityRegistrationSugarCrmAlert() " +
									"while invoking in vmgCharityPart1Registration() service ::");
					LOGGER
							.error("Input Details : "
									+ ProcessorHelper
											.convertVmgCharityToString(vmgCharity)
									+ "\n"
									+ ProcessorHelper
											.convertVmgCharityContactToString(vmgCharityContact));
					LOGGER
							.error(
									"Thrown RemoteException in " +
									"CharityRegistrationSugarCrmAlertProcessor.processCharityRegistrationSugarCrmAlert()"
									+ remoteException.getMessage(), remoteException);
				}

			} else if (SugarCrmMessageType.CHARITY_REGISTRATION_PART_TWO
					.equals(registrationSugarCrmAlert.getSugarCrmMessageType())) {
				try {
					successfulExecution = service.getSugarsoapPortType()
							.vmgCharityCompleteRegistration(username, password,
									vmgCharity, vmgCharityContact);
					if (successfulExecution) {
						LOGGER
								.debug("CharityRegistrationSugarCrmAlertProcessor.processCharityRegistrationSugarCrmAlert() :: vmgCharityCompleteRegistration() service is executed successfully.");
					} else {
						LOGGER
								.error("CharityRegistrationSugarCrmAlertProcessor.processCharityRegistrationSugarCrmAlert() :: vmgCharityCompleteRegistration() service is failed.");
						LOGGER
								.error("Input Details : "
										+ ProcessorHelper
												.convertVmgCharityToString(vmgCharity)
										+ "\n"
										+ ProcessorHelper
												.convertVmgCharityContactToString(vmgCharityContact));
					}
				} catch (RemoteException remoteException) {
					LOGGER
							.error("Thrown RemoteException in CharityRegistrationSugarCrmAlertProcessor.processCharityRegistrationSugarCrmAlert while invoking in vmgCharityCompleteRegistration() service ::");
					LOGGER
							.error("Input Details : "
									+ ProcessorHelper
											.convertVmgCharityToString(vmgCharity)
									+ "\n"
									+ ProcessorHelper
											.convertVmgCharityContactToString(vmgCharityContact));
					LOGGER
							.error(
									"Thrown RemoteException in CharityRegistrationSugarCrmAlertProcessor.processCharityRegistrationSugarCrmAlert()"
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
	 *            the service to set locator.
	 */
	public void setService(SugarCRMWebServiceLocator service) {
		this.service = service;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param username
	 *            - user name for service authentication.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            - password for service authentication.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
