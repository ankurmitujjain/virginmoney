package com.virginmoneygiving.security.verify.custom.cas.vmg.web.flow;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie; 
import org.springframework.webflow.context.servlet.ServletExternalContext;

import org.apache.commons.lang.StringUtils;
import org.inspektr.common.ioc.annotation.NotNull;
import org.jasig.cas.CentralAuthenticationService;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.virginmoneygiving.security.verify.custom.business.CASConstants;
import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.SignedInFlagHelper;
import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials;

/**
 * This class intercepts password validation request, populates the Session and IP address details
 * and then passes control to the CAS  AuthenticationViaFormAction class.
 *
 * User: choprah
 * Date: 04-Aug-2008
 * Time: 15:32:14
 */
public class AuthenticationViaFormActionStep2 extends org.jasig.cas.web.flow.AuthenticationViaFormAction{
    
    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(AuthenticationViaFormActionStep2.class);

    /** The cas. */
    @NotNull
    private CentralAuthenticationService cas;

    /** Delegates the creating of an indicator showing if a user has signed in **/
    private SignedInFlagHelper signedInFlagHelper;

    /**
     * Sets a class to help with setting and removing an indicator showing a user has logged on.
     * @param signedInFlagHelper
     */
    public void setSignedInFlagHelper(SignedInFlagHelper signedInFlagHelper){
    	this.signedInFlagHelper = signedInFlagHelper;
    }
    
    /**
     * Method to intercept call to bindAndValidate.
     * 
     * @param context Request context
     * 
     * @return Event event
     */
    public Event bindAndValidate(RequestContext context) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("bindAndValidate() -  START");
		}

		Credentials credentials = null;
		
		try {
			credentials = (Credentials) getFormObject(context);
		} catch (Exception e) {
			LOGGER.error("Error occured while retreiving form object associated with current context." , e);
			throw new RuntimeException("Error occured while retreiving from object associated with current context.");
		}

        final UsernamePasswordDOBCredentials usernamePassword = (UsernamePasswordDOBCredentials) credentials;
        HttpServletRequest httprequest = WebUtils.getHttpServletRequest(context);
        String ipAddress = httprequest.getRemoteAddr();
        String sessionId = httprequest.getSession().getId();
        usernamePassword.setSessionId(sessionId);
        usernamePassword.setIpAddress(ipAddress);
        boolean flag = WebUtils.getHttpServletRequest(context).getSession().getAttribute(CASConstants.AFTER_FORCED_RESET_PASSWORD)!=null;
        if(flag) {
			Event returnEvent2 = success();
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("bindAndValidate(RequestContext) - END");
			}
			return returnEvent2;
		}
		Event returnEvent = null;
		
		try {
			returnEvent = super.bindAndValidate(context);
		} catch (Exception e) {
			LOGGER.error("Error occured while binding and validating the context." , e);
			throw new RuntimeException("Error occured while binding and validating the context.");
		} 
		
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("bindAndValidate(RequestContext) - END");
		}
		return returnEvent;
	}

    /**
     * Identify target.
     * 
     * @param context the context
     * 
     * @return the event
     */
    public Event identifyTarget(RequestContext context) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("identifyTarget(RequestContext) - *** In AuthenticationViaFormActionStep2 (identifyTarget) START");
        }
                
        Credentials credentials = null;
		
		try {
			credentials = (Credentials) getFormObject(context);
		} catch (Exception e) {
			LOGGER.error("Error occured while retreiving form object associated with current context." , e);
			throw new RuntimeException("Error occured while retreiving from object associated with current context.");
		}
		
        final UsernamePasswordDOBCredentials usernamePassword = (UsernamePasswordDOBCredentials) credentials;
        String target = usernamePassword.getResponseTarget();

        HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        request.getSession().setAttribute(CASConstants.REDIRECTION_TARGET_TYPE_REF, target);

        if (!StringUtils.isEmpty(target) && target.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_FAILED)) {
        	if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AuthenticationViaFormActionStep2 (identifyTarget) - failed validation");
        	}
        	 if (LOGGER.isTraceEnabled()) {
                 LOGGER.trace("identifyTarget(RequestContext) - *** In AuthenticationViaFormActionStep2 (identifyTarget) END");
             }
            return error();
        }
        else if (!StringUtils.isEmpty(target) && target.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_TARGET_LOCKED_VMG)) {
        	if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AuthenticationViaFormActionStep2 (identifyTarget) - locked by VMG");
        	}
        	 if (LOGGER.isTraceEnabled()) {
                 LOGGER.trace("identifyTarget(RequestContext) - *** In AuthenticationViaFormActionStep2 (identifyTarget) END");
             }
            return result("lockedVMG");
        }
        else if (!StringUtils.isEmpty(target) && target.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_TARGET_LOCKED_FAILED)) {
        	if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AuthenticationViaFormActionStep2 (identifyTarget) - locked by errors");
        	}
        	 if (LOGGER.isTraceEnabled()) {
                 LOGGER.trace("identifyTarget(RequestContext) - *** In AuthenticationViaFormActionStep2 (identifyTarget) END");
             }
            return result("lockedError");
        }
        else if (!StringUtils.isEmpty(target) && target.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_EXPIRE)) {
        	if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AuthenticationViaFormActionStep2 (identifyTarget) - temp password expired");
        	}
        	 if (LOGGER.isTraceEnabled()) {
                 LOGGER.trace("identifyTarget(RequestContext) - *** In AuthenticationViaFormActionStep2 (identifyTarget) END");
             }
            return result("tempPwdExpired");
        }
        else if (!StringUtils.isEmpty(target) && target.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_OK)) {
        	if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AuthenticationViaFormActionStep2 (identifyTarget) - temp password Valid and issued");
        	}
        	 if (LOGGER.isTraceEnabled()) {
                 LOGGER.trace("identifyTarget(RequestContext) - *** In AuthenticationViaFormActionStep2 (identifyTarget) END");
             }
            return result("tempPwdValid");
        }
        if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("AuthenticationViaFormActionStep2 (identifyTarget) - unknown target: " + target);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("identifyTarget(RequestContext) - *** In AuthenticationViaFormActionStep2 (identifyTarget) END");
        }
        return error();
    }

    /**
     * Redirect to target.
     * 
     * @param context the context
     * 
     * @return the event
     */
    public Event redirectToTarget(RequestContext context) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("redirectToTarget(RequestContext) - *** In AuthenticationViaFormActionStep2 (redirectToTarget) START");
        }
        
        Credentials credentials = null;
		
		try {
			credentials = (Credentials) getFormObject(context);
		} catch (Exception e) {
			LOGGER.error("Error occured while retreiving form object associated with current context." , e);
			throw new RuntimeException("Error occured while retreiving from object associated with current context.");
		}
		
        final UsernamePasswordDOBCredentials usernamePassword = (UsernamePasswordDOBCredentials) credentials;
        String target = usernamePassword.getResponseTarget();

        HttpServletRequest request = WebUtils.getHttpServletRequest(context);

        request.getSession().setAttribute(CASConstants.REDIRECTION_TARGET_TYPE_REF, target);
        if(request.getSession().getAttribute(CASConstants.AFTER_FORCED_RESET_PASSWORD)!=null && !StringUtils.isEmpty(target)) {
            target = CASConstants.PASSWORD_VALIDATION_OK;
        }
        if (!StringUtils.isEmpty(target) && target.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_OK)) {
        	if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("AuthenticationViaFormActionStep2 (identifyTarget) - temp password Valid and issued");
        	}
        	if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("redirectToTarget(RequestContext) - *** In AuthenticationViaFormActionStep2 (redirectToTarget) END");
            }
            return result("tempPwdValid");
        }
        LOGGER.debug("AuthenticationViaFormActionStep2 (identifyTarget) - Success: " + target);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("redirectToTarget(RequestContext) - *** In AuthenticationViaFormActionStep2 (redirectToTarget) END");
        }
        return success();
    }


    /**
     * External submit.
     * 
     * @param context the context
     * 
     * @return the event
     */
    public final Event externalSubmit(final RequestContext context) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("externalSubmit(RequestContext) - *** In AuthenticationViaFormActionStep2 (externalSubmit) START");
        }
    	if (LOGGER.isDebugEnabled()) {
    	LOGGER.debug(" In ExternalUserAuthenticationViaFormAction  :: externalSubmit method ");
    	}
        
        UsernamePasswordDOBCredentials credentials = null;
		
		try {
			credentials = (UsernamePasswordDOBCredentials) getFormObject(context);
		} catch (Exception e) {
			LOGGER.error("Error occured while retreiving form object associated with current context." , e);
			throw new RuntimeException("Error occured while retreiving from object associated with current context.");
		}
		
        Event eve = null;
        try {
        	eve = super.submit(context);        	
    		//Create signed in flag
        	if("success".equals(eve.toString())){
        		this.createSignInFlagCookie(context);
        	}                    
        } catch (Exception e) {
        	LOGGER.error("Error occured while invoking super class submit method with current context." , e);
			throw new RuntimeException("Error occured while invoking super class submit method with current context.");
        }
        
        
        if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("ExternalUserAuthenticationViaFormAction-Completed local submit. Status=" + eve.toString());
        }
        String target = credentials.getResponseTarget();
        if (!StringUtils.isEmpty(target) && target.equalsIgnoreCase(CASConstants.PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_OK)) {
        	if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("AuthenticationViaFormActionStep2 (identifyTarget) - temp password Valid and issued");
        	}
            return result("error");
        }
        LOGGER.debug(" End ExternalUserAuthenticationViaFormAction  :: external cas submit method ");
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("externalSubmit(RequestContext) - *** In AuthenticationViaFormActionStep2 (externalSubmit) END");
        }
        return eve;
    }

    /**
     * Adds a cookie to the response showing a user has successfully logged in.
     * @param context
     */
    private void createSignInFlagCookie(RequestContext context){
        //Create signed in flag		
		ServletExternalContext externalContext = (ServletExternalContext)context.getExternalContext();
		HttpServletResponse response = externalContext.getResponse();
		signedInFlagHelper.setSignedInFlag(response);    	
    }
    
    /**
     * Sets the cas.
     * 
     * @param cas the cas to set
     */
    public void setCas(CentralAuthenticationService cas) {
        this.cas = cas;
    }


}
