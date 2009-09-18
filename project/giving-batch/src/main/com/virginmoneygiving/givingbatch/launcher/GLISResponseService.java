package com.virginmoneygiving.givingbatch.launcher;

import com.virginmoney.glis.messages.MaintenanceResponse;

/**
 * Service to handle the response from GLIS.
 * 
 * @author Puneet Swarup
 */
public interface GLISResponseService {

	/**
	 * Process the Registration Fee - Payment Initiated response from GLIS.
	 * 
	 * @param maintenanceResponse the response from GLIS.
	 */
	public void processMaintenanceResponse(
			MaintenanceResponse maintenanceResponse);
	
	
}
