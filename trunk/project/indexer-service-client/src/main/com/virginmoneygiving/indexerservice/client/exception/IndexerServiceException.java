package com.virginmoneygiving.indexerservice.client.exception;

/**
 * The Class to handle exceptions.
 */
public class IndexerServiceException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new indexer service exception.
	 */
	public IndexerServiceException() {
	}

	/**
	 * Instantiates a new indexer service exception.
	 * 
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public IndexerServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Instantiates a new indexer service exception.
	 * 
	 * @param arg0 the arg0
	 */
	public IndexerServiceException(String arg0) {
		super(arg0);
	}

	/**
	 * Instantiates a new indexer service exception.
	 * 
	 * @param arg0 the arg0
	 */
	public IndexerServiceException(Throwable arg0) {
		super(arg0);
	}


}
