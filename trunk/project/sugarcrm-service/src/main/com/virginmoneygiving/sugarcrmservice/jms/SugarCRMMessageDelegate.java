package com.virginmoneygiving.sugarcrmservice.jms;

/**
 * Interface for classes that can intercept and process on sugar crm messages.
 * 
 * @author Vikas Kale
 */
public interface SugarCRMMessageDelegate {

	/**
	 * Handle a text message.
	 * 
	 * @param message
	 *            the message
	 * 
	 */
	void handleMessage(String message);

}
