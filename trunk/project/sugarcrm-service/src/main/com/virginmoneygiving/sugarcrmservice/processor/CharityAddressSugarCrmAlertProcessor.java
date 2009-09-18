package com.virginmoneygiving.sugarcrmservice.processor;


import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import com.virginmoneygiving.sugarcrmservice.client.messages.CharityAddressSugarCrmAlert;
import com.virginmoneygiving.sugarcrmservice.client.messages.CharityRegistrationSugarCrmAlert;
import com.virginmoneygiving.sugarcrmservice.client.messages.SugarCrmMessageType;
import com.virginmoneygiving.sugarcrmservice.helper.DataMappingHelper;
import com.virginmoneygiving.sugarcrmservice.helper.ProcessorHelper;
import com.virginmoneygiving.sugarcrmservice.locator.SugarCRMWebServiceLocator;
import com.virginmoneygiving.sugarcrmservice.service.messages.VmgAddress;

/**
 * Utility class that processes {@link CharityRegistrationSugarCrmAlert} type of
 * messages received from the queue.
 * 
 * @author Vikas Kale
 */
public class CharityAddressSugarCrmAlertProcessor {

	/** Logger instance. */
	private static final Logger LOGGER = Logger
			.getLogger(CharityAddressSugarCrmAlertProcessor.class);
	/** Username to access SugarCRM web service. */
	private String username;

	/** Password to access SugarCRM web service. */
	private String password;

	/** SugarCRM services locator. */
	private SugarCRMWebServiceLocator service;

	/**
	 * Private constructor.
	 */
	public CharityAddressSugarCrmAlertProcessor() {
		super();
	}

	/**
	 * Process {@link CharityAddressSugarCrmAlert} object received from the
	 * message queue.
	 * 
	 * @param donationAlert
	 *            the {@link CharityAddressSugarCrmAlert} object to process.
	 */
	public void processCharityAddressSugarCrmAlert(
			final CharityAddressSugarCrmAlert addressSugarCrmAlert) {
		boolean successfulExecution;
		if (addressSugarCrmAlert != null) {
			VmgAddress vmgAddress =  DataMappingHelper.mapAddress(addressSugarCrmAlert.getClientVmgAddress());
			
			
			if (SugarCrmMessageType.UPDATE_CHARITY_ADMIN_ADDRESS
					.equals(addressSugarCrmAlert.getSugarCrmMessageType())) {
				try {
					successfulExecution = service.getSugarsoapPortType().vmgCharityUpdateAdministrationAddress(username, password, addressSugarCrmAlert.getVmgCharityId(), vmgAddress);
					if (successfulExecution) {
						LOGGER
								.debug("CharityAddressSugarCrmAlertProcessor.processCharityAddressSugarCrmAlert() ::" +
										" vmgCharityUpdateAdministrationAddress() service is executed successfully.");
					} else {
						LOGGER
								.error("CharityAddressSugarCrmAlertProcessor.processCharityAddressSugarCrmAlert() ::" +
										" vmgCharityUpdateAdministrationAddress() service is failed.");
						LOGGER
								.error("Input Details : "
										+ ProcessorHelper
												.convertVmgCharityAddressToString(vmgAddress));
					}
				} catch (RemoteException remoteException) {
					LOGGER
							.error("Thrown RemoteException in CharityAddressSugarCrmAlertProcessor.processCharityAddressSugarCrmAlert()" +
									" while invoking in vmgCharityUpdateAdministrationAddress() service ::");
					LOGGER
							.error("Input Details : "
									+ ProcessorHelper.convertVmgCharityAddressToString(vmgAddress));
					LOGGER
							.error(
									"Thrown RemoteException in CharityAddressSugarCrmAlertProcessor.processCharityAddressSugarCrmAlert()"
											+ remoteException.getMessage(),
									remoteException);
				}
			} else if (SugarCrmMessageType.UPDATE_CHARITY_REGISTERED_ADDRESS
					.equals(addressSugarCrmAlert.getSugarCrmMessageType())) {
				try {
					successfulExecution = service.getSugarsoapPortType().vmgCharityUpdateRegisteredAddress(username, password, addressSugarCrmAlert.getVmgCharityId(), vmgAddress);
					if (successfulExecution) {
						LOGGER
								.debug("CharityAddressSugarCrmAlertProcessor.processCharityAddressSugarCrmAlert() ::" +
										" vmgCharityUpdateRegisteredAddress() service is executed successfully.");
					} else {
						LOGGER
								.error("CharityAddressSugarCrmAlertProcessor.processCharityAddressSugarCrmAlert() ::" +
										" vmgCharityUpdateRegisteredAddress() service is failed.");
						LOGGER
								.error("Input Details : "
										+ ProcessorHelper
												.convertVmgCharityAddressToString(vmgAddress));
					}
				} catch (RemoteException remoteException) {
					LOGGER
							.error("Thrown RemoteException in CharityAddressSugarCrmAlertProcessor.processCharityAddressSugarCrmAlert()" +
									" while invoking in vmgCharityUpdateRegisteredAddress() service ::");
					LOGGER
							.error("Input Details : "
									+ ProcessorHelper.convertVmgCharityAddressToString(vmgAddress));
					LOGGER
							.error(
									"Thrown RemoteException in CharityAddressSugarCrmAlertProcessor.processCharityAddressSugarCrmAlert()"
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
