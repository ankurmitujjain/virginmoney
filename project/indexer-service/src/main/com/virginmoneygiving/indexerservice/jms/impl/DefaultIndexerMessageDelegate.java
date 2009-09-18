package com.virginmoneygiving.indexerservice.jms.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.virginmoneygiving.indexerservice.jms.IndexerMessageDelegate;
import com.virginmoneygiving.indexerservice.messages.DeleteFromIndexMessage;
import com.virginmoneygiving.indexerservice.messages.IndexerService;
import com.virginmoneygiving.indexerservice.messages.UpdateInIndexMessage;

/**
 * Message-driven POJO implementation of IndexerMessageDelegate.
 * 
 * Receives notification of updates via messages. Converts message to a specific
 * type then passes on to the search indexer's client implementation.
 * 
 * @author Ian Priest
 */
public class DefaultIndexerMessageDelegate implements IndexerMessageDelegate {

	/** Possible classname of message payload. */
	private static final String UPDATE_IN_INDEX_MESSAGE_CLASS_NAME = UpdateInIndexMessage.class
			.getSimpleName();
	
	/** Possible classname of message payload. */
	private static final String DELETE_FROM_INDEX_MESSAGE_CLASS_NAME = DeleteFromIndexMessage.class
			.getSimpleName();

	/** Log messages. */
	private Logger LOGGER = Logger
			.getLogger(DefaultIndexerMessageDelegate.class.getName());
	
	/** Search indexer client. */
	private IndexerService indexerService;

	/**
	 * Gets the indexer service.
	 * 
	 * @return the indexer service
	 */
	public IndexerService getIndexerService() {
		return indexerService;
	}

	/**
	 * Sets the indexer service.
	 * 
	 * @param indexerService the new indexer service
	 */
	@Resource
	public void setIndexerService(IndexerService indexerService) {
		this.indexerService = indexerService;
	}

	/**
	 * Receives an update message.
	 * 
	 * Work out what the message type is then pass on to the specific handlers.
	 * 
	 * @param messageContent the inbound message content
	 */
	public void handleMessage(String messageContent) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("handleMessage(String) - START");
		}
		if(LOGGER.isInfoEnabled()){
		LOGGER.info("Starting payload deconstruct for XML: "
						+ messageContent);
		}
		Object msgObject = null;
		XStream xstream = new XStream(new DomDriver());
		// Set up the classloader to avoid false ClassCastExceptions
		xstream.setClassLoader(getClass().getClassLoader());

		msgObject = xstream.fromXML(messageContent);
		Class msgClass = msgObject.getClass();
		String msgClassName = msgClass.getSimpleName();
		if(LOGGER.isInfoEnabled()){
		LOGGER
				.info("Audit Message Listener - Payload deconstructed for class: "
						+ msgClassName);
		}
		if (msgClassName.equalsIgnoreCase(UPDATE_IN_INDEX_MESSAGE_CLASS_NAME)) {
			indexerService
					.updateInIndexMessage(((UpdateInIndexMessage) msgObject)
							.getIndexerUpdate());
		} else if (msgClassName
				.equalsIgnoreCase(DELETE_FROM_INDEX_MESSAGE_CLASS_NAME)) {
			indexerService
					.deleteFromIndexMessage(((DeleteFromIndexMessage) msgObject)
							.getIndexerUpdate());
		} else {
			if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Payload class (" + msgClassName + ") cannot be handled by this listener");
			}
		}

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("handleMessage(String) - END");
		}
	}

}
