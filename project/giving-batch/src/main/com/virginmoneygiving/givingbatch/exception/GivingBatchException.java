/**
 * 
 */
package com.virginmoneygiving.givingbatch.exception;

import org.apache.log4j.Logger;

/**
 * @author Yogesh Salunkhe
 *
 */
public class GivingBatchException extends RuntimeException{

    /**serial version Id.     */
    private static final long serialVersionUID = 1L;
    
   /**
    * Throws GivingBatchException Exception.
    *
    * @param log of type Logger
    * @param message of type String
    * @param cause of type Throwable
    */
   public GivingBatchException(Logger log, String message, Throwable cause) {
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
   public GivingBatchException(Logger log, String message) {
       super(message);
       log.error(message);
   }
}
