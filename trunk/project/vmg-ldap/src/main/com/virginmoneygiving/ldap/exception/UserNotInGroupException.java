package com.virginmoneygiving.ldap.exception;

/**
 * This exception is thrown to indicate that user is not member of a group
 * added/amended/accessed.
 * <p/>
 * @author vishals
 */
public class UserNotInGroupException extends Exception {
    
    /** Serial version UID. */
    private static final long serialVersionUID = -124453453567345L;

    /**
     * Constructs an UserNotInGroupException with the specified detail message.
     * @param message The error message.
     */
    public UserNotInGroupException(final String message) {
        super(message);
    }

    /**
     * Constructs an UserNotInGroupException with the specified detail message
     * and cause.
     * @param message The error message.
     * @param cause The cause.
     */
    public UserNotInGroupException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
