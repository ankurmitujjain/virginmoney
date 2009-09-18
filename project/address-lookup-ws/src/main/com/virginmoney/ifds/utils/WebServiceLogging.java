package com.virginmoney.ifds.utils;



import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Logging Utility for IFDS web service calls.
 */
public class WebServiceLogging
{

   private static NumberFormat secondsFormatter        = new DecimalFormat("###0.000");
   private static NumberFormat reponseLengthFormatter  = new DecimalFormat("###,###,###,##0");

   private static Logger localLogger = Logger.getLogger(WebServiceLogging.class);
    private static final String LOGGING_PREFIX = "address-lookup-ws";




    /**
     * Writes a pair of log records for a web service call, including the elapsed time.
     *
     * One record is written using the calling class's logger and the other using this classes own logger so
     * that they can be seperated into different log4j log files.
     *
     * @param logger log4j logger from the calling class.
     * @param request request message, marshalled into an Object, that was sent to IFDS.
     * @param startTime time request sent, use System currentTimeMillis() to obtain this.
     * @param finishTime time reply returned from IFDS.
     * @param response xml response from IFDS
     * @param msgSource classname of calling class.
     */
     public static void logWebServiceCall(Logger logger, Object request, long startTime, long finishTime, String response, String msgSource) {
        try {
            StringBuffer messageLine = new StringBuffer();
            messageLine.append("** " + LOGGING_PREFIX + " <");
            messageLine.append(msgSource);
            messageLine.append("> I: WebService Call Timing : ");

            // format the elapsed time for the call
            String elapsed;
            if (finishTime > 0 && startTime > 0 && finishTime > startTime)
                elapsed = secondsFormatter.format((float) (finishTime - startTime) / 1000);
            else
                elapsed = "(error)";

            messageLine.append(" elapsed secs=");
            messageLine.append(StringUtils.rightPad(elapsed, 10));

            //extract the class name from the fully qualified class name
            messageLine.append(" request class=");
            String fullClassName;
            String className;
            if (request != null) {
                fullClassName = request.getClass().getName();
                int firstChar = fullClassName.lastIndexOf('.') + 1;
                if (firstChar > 0) className = fullClassName.substring(firstChar);
                else className = fullClassName;
            } else {
                fullClassName = "(null)";
                className = "(null)";
            }

            messageLine.append(StringUtils.rightPad(className, 50));

            //extract the length of the response string (useful when evaluating response size vs elapsed time)
            String responseLength;
            if (response != null)
                responseLength = reponseLengthFormatter.format(response.length());
            else
                responseLength = "0";

            messageLine.append(" response bytes=");
            messageLine.append(StringUtils.rightPad(responseLength, 10));

            // Try to extract a unique id for the request (only works for IFDS web service calls at present)
            // this is the equivalent of request.getHeader().getSource().getRequestUniqueID()
            String requestID = ("unknown");

            if (request != null) {
                String step = "none";
                try {

                    // this is slightly dirty because it doesnt check for non-null objects before moving to the next
                    // step. This makes the code a little more readable at the expense of having to handle null pointer exceptions

                    // get the getHeader method from the request
                    //step = "getting request.getHeader method";
                    Method getHeader = request.getClass().getMethod("getHeader");
                    //logger.debug("Got request.getHeader method =" + getHeader);

                    // get the header object from the request
                    //step = "invoking request.getHeader method";
                    Object header = getHeader.invoke(request);
                    //logger.debug("Got header object =" + header);

                    // get the getSource method from the header object
                    //step = "getting header.getSource method";
                    Method headerGetSource = header.getClass().getMethod("getSource");
                    //logger.debug("Got header.getSource method =" + getHeader);

                    // get the Source object from the header object
                    //step = "invoking header.getSource method";
                    Object source = headerGetSource.invoke(header);
                    //logger.debug("Got Source object =" + source);

                    // get the getRequestUniqueId method from the source object
                    //step = "getting source.getRequestUniqueId method";
                    Method sourceGetRequestUniqueId = source.getClass().getMethod("getRequestUniqueID");
                    //logger.debug("Got source.getRequestUniqueId method =" + sourceGetRequestUniqueId);

                    // get the requestUniqueId object from the Source object
                    //step = "invoking source.getRequestUniqueId method";
                    Object requestUniqueId = sourceGetRequestUniqueId.invoke(source);
                    //logger.debug("Got requestUniqueId object =" + requestUniqueId);

                    requestID = requestUniqueId.toString();

                }
                catch(IllegalArgumentException e) {
                    logger.debug("An exception has been thrown performing a method invocation. Msg source: " + msgSource + " Stage=" + step, e);
                }
                catch(IllegalAccessException e) {
                    logger.debug("An exception has been thrown performing a method invocation. Msg source: " + msgSource + " Stage=" + step, e);
                }
                catch(InvocationTargetException e) {
                    logger.debug("An exception has been thrown performing a method invocation. Msg source: " + msgSource + " Stage=" + step, e);
                }
                catch(SecurityException e) {
                    logger.debug("A SecurityException has been thrown whilst retrieving a method from a class definition. Msg source: " + msgSource + " Stage=" + step, e);
                }
            }


            messageLine.append(" request unique id=");
            messageLine.append(StringUtils.rightPad(requestID, 15));


            logger.info(messageLine.toString());

            //relog using the local logger so it can be seperated out by log4j
            localLogger.info(messageLine.toString());

        }
        catch(NoSuchMethodException e) {
            logger.error("Index out of bounds exception.", e);        
            logger.error("Exception caught when logging web service call from " + msgSource);
        }
        catch(IndexOutOfBoundsException e) {
            logger.error("Index out of bounds exception.", e);
            logger.error("Exception caught when logging web service call from " + msgSource);
        }
    }
}
