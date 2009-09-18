package com.virginmoneygiving.givingbatch.serviceproxy.impl;

import org.apache.log4j.Logger;

import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.exception.ServiceException;
import com.virginmoneygiving.paymentmanagement.service.messages.PaymentManagementService;
import com.virginmoneygiving.paymentmanagement.service.messages.PaymentManagementWs;

/**
 * Inherited from AbstractGivingBatchServiceLocator.
 * 
 * @author Srinivas Nageli
 */
public class PaymentManagementServiceLocatorImpl extends
		AbstractGivingBatchServiceLocator {


	/** creating Logger Instance. */
	private static final Logger LOGGER = Logger
			.getLogger(PaymentManagementServiceLocatorImpl.class);
	
	private static PaymentManagementWs paymentManagementWs = null;
    /**
	 * Returns an instance of the PaymentManagementService.
	 * 
	 * @return an instance of the PaymentManagementService.
	 * 
	 * @throws ServiceException the exception
	 */
	public PaymentManagementService getPaymentManagementService()
			throws  ServiceException {
		return (PaymentManagementService) this
				.getWebService("paymentManagement");
	}

	/**
	 * Returns an instance of the Payment Management SOAP port.
	 * 
	 * @return an instance of the Payment Management SOAP port.
	 * 
	 * @throws ServiceException the exception
	 */
	public PaymentManagementWs getPaymentManagementPort() throws ServiceException {
        if (paymentManagementWs != null) {
            return paymentManagementWs;
        }
        paymentManagementWs = getPaymentManagementService().getPaymentManagementWs();
        return paymentManagementWs;
        //return getPaymentManagementService().getPaymentManagementWs();
	}

}
