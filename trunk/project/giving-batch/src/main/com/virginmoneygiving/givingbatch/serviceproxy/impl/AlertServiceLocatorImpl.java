package com.virginmoneygiving.givingbatch.serviceproxy.impl;

import com.virginmoneygiving.alert.service.messages.AlertPort;
import com.virginmoneygiving.alert.service.messages.AlertService;
import com.virginmoneygiving.givingbatch.exception.ServiceException;

/**
 * Inherited from AbstractGivingBatchServiceLocator.
 * 
 * @author Ian Priest
 */
public class AlertServiceLocatorImpl extends
		AbstractGivingBatchServiceLocator {

	/**
	 * Gets the alert service.
	 * 
	 * @return the alert service
	 * 
	 * @throws Exception the exception
	 */
	public AlertService getAlertService()
			throws  ServiceException {
		return (AlertService) this
				.getWebService("alert");
	}

	/**
	 * Returns an instance of the Payment Management SOAP port.
	 * 
	 * @return an instance of the Payment Management SOAP port.
	 * 
	 * @throws Exception the exception
	 */
	public AlertPort getAlertPort() throws ServiceException {
		return getAlertService().getAlertPort();
	}

}
