package com.virginmoneygiving.givingbatch.exception;

import org.apache.log4j.Logger;

/**
 * @author Siva Kumar
 *
 */
public class GlisServiceException extends Exception {
    /**serial version Id.     */
    private static final long serialVersionUID = 1L;
    
   /**
    * Throws ServiceLocation Exception.
    *
    * @param log of type Logger
    * @param message of type String
    * @param cause of type Throwable
    */
   public GlisServiceException(Logger log, String message, Throwable cause) {
       super(message, cause);
       log.error(message);
       log.error(cause);
   }
   
   /**
    * Throwing Exception with log and exception message.
    *
    * @param log of type Logger
    * @param message of type String
    */
   public GlisServiceException(Logger log, String message) {
       super(message);
       log.error(message);
   }
}
