package com.virginmoneygiving.giving.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * <p>
 * BaseExcception calls used to handle Exceptions.
 * </p>
 * 
 * @author diptirmaya.rout
 * @version 0.1
 * @purpose: - To be used by every exception handling class
 */
public class BaseException extends Exception {

    /** Serial Id. */
    private static final long serialVersionUID = 1L;

    /** Root Exception Throwable type. */
    protected Throwable rootException;

    /** Base Exception Type. */
    protected List<BaseException> exceptions;

    /** Place holder for customize message. */
    private String messageKey;

    /** Place holder for different exception objects. */
    private Object[] messageArgs;
    
    /** Logger for the class. */
    private static final Logger LOGGER =
            Logger.getLogger(BaseException.class);

    /**
     * Default Constructor Initialize all the parameters.
     */
    public BaseException() {
        rootException = null;
        exceptions = new ArrayList<BaseException>();
        messageKey = null;
        messageArgs = null;
    }

    /**
     * The Constructor.
     * 
     * @param message customize message
     */
    public BaseException(String message) {
        super(message);
        rootException = null;
        exceptions = new ArrayList<BaseException>();
        messageKey = null;
        messageArgs = null;
    }

    /**
     * The Constructor.
     * 
     * @param rootException Root Exception Type.
     */
    public BaseException(Throwable rootException) {
        exceptions = new ArrayList<BaseException>();
        messageKey = null;
        messageArgs = null;
        this.rootException = rootException;
    }

    /**
     * The Constructor.
     * 
     * @param rootException Root Exception type.
     * @param message Customize message .
     */
    public BaseException(Throwable rootException, String message) {
        super(message);
        exceptions = new ArrayList<BaseException>();
        messageKey = null;
        messageArgs = null;
        this.rootException = rootException;
    }

    /**
     * Gets the exceptions.
     * 
     * @return List type
     */
    public List<BaseException> getExceptions() {
        return exceptions;
    }

    /**
     * Adds the exception.
     * 
     * @param exception Exception Type.
     */
    public void addException(BaseException exception) {
        exceptions.add(exception);
    }

    /**
     * Sets the message key.
     * 
     * @param key mesasge key
     */
    public void setMessageKey(String key) {
        messageKey = key;
    }

    /**
     * Gets the message key.
     * 
     * @return String
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * Sets the message args.
     * 
     * @param args object[]
     */
    public void setMessageArgs(Object[] args) {
        messageArgs = args;
    }

    /**
     * Gets the message args.
     * 
     * @return Object[]
     */
    public Object[] getMessageArgs() {
        return messageArgs;
    }

    /**
     * Sets the root exception.
     * 
     * @param exception Root Exception
     */
    public void setRootException(Throwable exception) {
        rootException = exception;
    }

    /**
     * Gets the root exception.
     * 
     * @return rootException
     */
    public Throwable getRootException() {
        return rootException;
    }

    /**
     * <p>
     * Print the Stack Trace of Errors.
     * </p>
     */
    public void printStackTrace() {
        LOGGER.error(this);
        if (getRootException() != null) {
            LOGGER.error(getRootException());
        }
    }
}
