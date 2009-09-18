package com.virginmoneygiving.indexerservice.jms;

/**
 * Handle indexing messages.
 * 
 * Interface for classes that can receive and process indexer messages.
 * 
 * @author Ian Priest
 */
public interface IndexerMessageDelegate {

	/**
	 * Handle a text message.
	 * 
	 * @param messageContent the message content
	 */
	public void handleMessage(String messageContent);
	
}
