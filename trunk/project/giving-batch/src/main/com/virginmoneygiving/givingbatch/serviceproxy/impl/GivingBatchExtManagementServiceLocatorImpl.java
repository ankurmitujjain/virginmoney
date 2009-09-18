package com.virginmoneygiving.givingbatch.serviceproxy.impl;

import com.virginmoneygiving.givingbatch.exception.ServiceException;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.GivingBatchExtManagementWs;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.GivingBatchExtManagement;

/**
 * Web service locator for Giving Batch Ext Management web service.
 * 
 * @author Puneet Swarup
 */
public class GivingBatchExtManagementServiceLocatorImpl extends
		AbstractGivingBatchServiceLocator {

	private static GivingBatchExtManagementWs givingBatchExtManagementWs = null;
    /**
	 * Returns an instance of the GivingBatchExtManagementService.
	 * 
	 * @return {@link GivingBatchExtManagementService} instance.
	 * 
	 * @throws Exception in case of exception.
	 */
	public GivingBatchExtManagement getGivingBatchExtManagement()
	        throws ServiceException {
		return (GivingBatchExtManagement) this
				.getWebService("givingBatchExtManagement");
	}

	/**
	 * Returns an instance of the Payment Management SOAP port.
	 * 
	 * @return an instance of the Payment Management SOAP port.
	 * 
	 * @throws Exception in case of exception.
	 */
	public GivingBatchExtManagementWs getGivingBatchExtManagementPort()
	        throws ServiceException {
        if (givingBatchExtManagementWs != null) {
            return givingBatchExtManagementWs;
        }
        givingBatchExtManagementWs = getGivingBatchExtManagement().getGivingBatchExtManagementWs();
        return  givingBatchExtManagementWs;
        //return getGivingBatchExtManagement().getGivingBatchExtManagementWs();
	}
}
