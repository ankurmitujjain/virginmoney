package com.virginmoneygiving.security.verify.custom.business;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.virginmoneygiving.security.verify.custom.services.RedirectActionsResolver;

/**
 * Utility functions which may be required for customised variations. User:
 * choprah Date: 13-Jun-2008 Time: 08:56:45
 *
 * @author Ankur J
 */
public class UtilityFunctions {

     /** The log. */
     private static final Logger LOGGER = Logger.getLogger(UtilityFunctions.class);

     /** The Constant paramsToBeRemoved. */
     private static final String[] paramsToBeRemoved = new String[]{"lt", "error_message", "get-lt"};

    /**
     * Removes the previously attached GET parameters "lt" and "error_message"
     * to be able to send new ones.
     *
     * @param casUrl the cas url
     *
     * @return the string
     */
    public static String resetUrl(String casUrl) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("resetUrl() -  START");
		}
		String cleanedUrl = removeHttpGetParameters(casUrl, paramsToBeRemoved);

        if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("resetUrl(String) - END");
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("resetUrl(casUrl=" + casUrl + ") - String cleanedUrl=" + cleanedUrl);
		}
		return cleanedUrl;

    }

    /**
     * Removes selected HTTP GET parameters from a given URL.
     *
     * @param casUrl the cas url
     * @param paramsToBeRemoved the params to be removed
     *
     * @return the string
     */
    public static String removeHttpGetParameters (String casUrl, String[] paramsToBeRemoved) {

		String cleanedUrl = casUrl;
        if (casUrl != null)
        {
            // check if there is any query string at all
            if (casUrl.indexOf("?") == -1) {
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace("removeHttpGetParameters(String, String[]) - END");
				}
				return casUrl;
            } else
            {
                // determine the start and end position of the parameters to be removed
                int startPosition, endPosition;
                boolean containsOneOfTheUnwantedParams = false;
                for (String paramToBeErased : paramsToBeRemoved)
                {
                    startPosition = -1;
                    endPosition = -1;
                    if (cleanedUrl.indexOf("?" + paramToBeErased + "=") > -1)
                    {
                        startPosition = cleanedUrl.indexOf("?"
                                + paramToBeErased + "=") + 1;
                    } else if (cleanedUrl.indexOf("&" + paramToBeErased + "=") > -1)
                    {
                        startPosition = cleanedUrl.indexOf("&"
                                + paramToBeErased + "=") + 1;
                    }
                    if (startPosition > -1)
                    {
                        int temp = cleanedUrl.indexOf("&", startPosition);
                        endPosition = (temp > -1) ? temp + 1 : cleanedUrl
                                .length();
                        // remove that parameter, leaving the rest untouched
                        cleanedUrl = cleanedUrl.substring(0, startPosition)
                                + cleanedUrl.substring(endPosition, cleanedUrl
                                        .length());
                        containsOneOfTheUnwantedParams = true;
                    }
                }

                if (cleanedUrl.endsWith("?") || cleanedUrl.endsWith("&")) {
                    cleanedUrl = cleanedUrl.substring(0,
                            cleanedUrl.length() - 1);
                }

                if (!containsOneOfTheUnwantedParams) {
					if (LOGGER.isTraceEnabled()) {
						LOGGER.trace("removeHttpGetParameters(String, String[]) - END");
					}
					return casUrl;
                }
                else {
                    cleanedUrl = removeHttpGetParameters(cleanedUrl, paramsToBeRemoved);
                }
            }
        }

		return cleanedUrl;

    }

    /**
     * Gets the formatted password position.
     *
     * @param passwordPosition the password position
     *
     * @return the formatted password position
     */
    public static String getFormattedPasswordPosition(int passwordPosition) {


		String[] positions = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth",
                              "Eleventh", "Twelfth", "Thirteenth", "Fourteenth", "Fifteenth", "Sixteenth", "Seventeenth",
                              "Eighteenth", "Nineteenth", "Twentieth"};

        String result = "" + passwordPosition;
        int pSize = positions.length;
        if (pSize < passwordPosition) {
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("getFormattedPasswordPosition(int) - END");
			}
			return result;
        }
        if(positions.length > passwordPosition - 1 && passwordPosition > 0){
            result = positions[passwordPosition - 1];
        }else{
            LOGGER.error("getFormattedPasswordPosition(int)");
            result = "" + passwordPosition;
        }

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getFormattedPasswordPosition(passwordPosition=" + passwordPosition + ") - String result=" + result);
		}
		return result;

    }


    /**
     * Gets the account locked action.
     *
     * @return the account locked action
     */
    public static String getAccountLockedAction() {

		String result  = System.getProperty(CASConstants.REDIRECT_ACTION_ACCOUNT_LOCKED_BY_FAILURE);
        if (isStringEmpty(result)) {
            result = RedirectActionsResolver.getInstance().
                               getRedirectProperty(CASConstants.REDIRECT_ACTION_ACCOUNT_LOCKED_BY_FAILURE);
        }
        LOGGER.debug("getAccountLockedAction-Value: " + result);
        return getSecureServer() + result;
    }

    /**
     * Gets the account locked vmg action.
     *
     * @return the account locked vmg action
     */
    public static String getAccountLockedVMGAction() {

		String result = System.getProperty(CASConstants.REDIRECT_ACTION_ACCOUNT_LOCKED_BY_VMG);
        if (isStringEmpty(result)) {
            result = RedirectActionsResolver.getInstance().
                               getRedirectProperty(CASConstants.REDIRECT_ACTION_ACCOUNT_LOCKED_BY_VMG);
        }
        LOGGER.debug("getAccountLockedVMGAction-value: " + result);
        return getSecureServer() + result;
    }

    /**
     * Gets the temp password expired action.
     *
     * @return the temp password expired action
     */
    public static String getTempPasswordExpiredAction() {

		String result = System.getProperty(CASConstants.REDIRECT_ACTION_PASSWORD_EXPIRED);
        if (isStringEmpty(result)) {
            result = RedirectActionsResolver.getInstance().
                               getRedirectProperty(CASConstants.REDIRECT_ACTION_PASSWORD_EXPIRED);
        }
        LOGGER.debug("getTempPasswordExpiredAction-value; " + result);
		String tempPasswordExpiredAction = getSecureServer() + result;
		return tempPasswordExpiredAction;
	}

    /**
     * Gets the temp password process action.
     *
     * @return the temp password process action
     */
    public static String getTempPasswordProcessAction() {

		String result = System.getProperty(CASConstants.REDIRECT_ACTION_PASSWORD_RESET);
        if (isStringEmpty(result)) {
            result = RedirectActionsResolver.getInstance().
                               getRedirectProperty(CASConstants.REDIRECT_ACTION_PASSWORD_RESET);
        }
        LOGGER.debug("getTempPasswordProcessAction-value; " + result);
		String tempPasswordProcessAction = getSecureServer() + result;
		return tempPasswordProcessAction;
	}

    /**
     * Checks if is string empty.
     *
     * @param toCheck the to check
     *
     * @return true, if is string empty
     */
    public static boolean isStringEmpty(String toCheck) {
		boolean result = false;
        if (toCheck == null || toCheck.trim().length() < 1) {
            result = true;
        }

		return result;
    }

    /**
     * Fetch redirection action.
     *
     * @param actionType the action type
     * @param accountId the account id
     * @param dob the dob
     *
     * @return the string
     *
     * @author ankurj Fetch redirection action.
     */
    public static String fetchRedirectionAction(String actionType , String accountId , String dob) {
    	
    	if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("fetchRedirectionAction(actionType=" + actionType);
		}
    	
		if (isStringEmpty(actionType)) {
			return null;
        }
        else if (actionType.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_TARGET_LOCKED_FAILED)) {
			String redirectAction = getAccountLockedAction() + "?email=" + accountId + "&dob=" + dob;
            return redirectAction;

		}
        else if (actionType.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_TARGET_LOCKED_VMG)) {
			String redirectAction = getAccountLockedVMGAction() + "?email=" + accountId + "&dob=" + dob;
            return redirectAction;
        }
        else if (actionType.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_EXPIRE)) {
			String redirectAction = getTempPasswordExpiredAction() + "?email=" + accountId + "&dob=" + dob;
            return redirectAction;
        }
        else if (actionType.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_OK)) {
			String redirectAction = getTempPasswordProcessAction();
            return redirectAction;
        }

        return null;
    }

    /**
     * Show request.
     *
     * @param request the request
     */
    public static void showRequest(HttpServletRequest request){

        for (Object key : request.getParameterMap().keySet()) {
            String sk = (String) key;
            Object value = request.getParameter(sk);
        }

        Enumeration en = request.getHeaderNames();
        while (en.hasMoreElements()) {
            String key = (String)en.nextElement();
        }

        en = request.getAttributeNames();
        while (en.hasMoreElements()) {
            String key = (String)en.nextElement();
        }


        en = request.getSession().getAttributeNames();

        while (en.hasMoreElements()) {
            String key = (String)en.nextElement();
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("showRequest(HttpServletRequest) - ============================================="); //$NON-NLS-1$
        }
    }


   /**
    * Gets the secure server.
    *
    * @return the secure server
    */
    public static String getSecureServer() {
        String result = System.getProperty("current.vm.secure.server.name");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("getSecureServer() - String result=" + result);
        }

        return result;
    }

     /**
	    * Gets the non-secure server.
	    *
	    * @return the non-secure server
	    */
	public static String getBaseServer() {
        String result = System.getProperty("current.vm.server.name");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("getBaseServer() - String result=" + result);
        }

        return result;
    }

    /**
     * Gets the temp password process action.
     *
     * @return the temp password process action
     */
    public static String getServiceErrorRedirectionTarget() {

		String result = System.getProperty(CASConstants.REDIRECT_ACTION_SERVICE_ERROR);
        if (isStringEmpty(result)) {
            result = RedirectActionsResolver.getInstance().
                               getRedirectProperty(CASConstants.REDIRECT_ACTION_SERVICE_ERROR);
        }
        LOGGER.debug("getServiceErrorRedirectionTarget-value; " + result);
		String returnString = getSecureServer() + result;
		return returnString;
	}

    /**
     * This method is used to filter out XSS characters from request.
     * <br/>
     * NOTE: This is a very simple implementation of XSS character filter.
     * This method should be replaced with more advance options that are now available
     * in open source projects like <b>OWASP-ESAPI</b> after a complete evaluation.
     * This method implementation is taken from</br>
     * http://greatwebguy.com/programming/java/simple-cross-site-scripting-xss-servlet-filter/
     *
     * @param value input string.
     * @return filtered string.
     */
    public static String cleanXSS(String value) {
        if(value != null){
        	value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        	value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        	value = value.replaceAll("'", "& #39;");
        	value = value.replaceAll("eval\\((.*)\\)", "");
        	value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        	value = value.replaceAll("script", "");
        	value = value.replaceAll("\r", "");
        	value = value.replaceAll("\n", "");
        }
        return value;

    }

    /**
     * Gets the temp password process action.
     *
     * @return the temp password process action
     */
    public static String getNoServiceDefinedRedirectionTarget() {

		String result = System.getProperty(CASConstants.REDIRECT_ACTION_NO_SERVICE_DEFINED);
        if (isStringEmpty(result)) {
            result = RedirectActionsResolver.getInstance().
                               getRedirectProperty(CASConstants.REDIRECT_ACTION_NO_SERVICE_DEFINED);
        }
        LOGGER.debug("getNoServiceDefinedRedirectionTarget-value; " + result);
		String returnString = getBaseServer() + result;
		return returnString;
	}

}
