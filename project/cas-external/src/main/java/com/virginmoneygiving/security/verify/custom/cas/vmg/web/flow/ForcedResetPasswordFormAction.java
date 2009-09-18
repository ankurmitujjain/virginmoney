package com.virginmoneygiving.security.verify.custom.cas.vmg.web.flow;

import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.virginmoneygiving.security.verify.custom.business.CASConstants;
import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials;
import com.virginmoneygiving.security.verify.custom.serviceproxy.AuthenticationProxy;

/**
 * The Class is used to force user to reset the password.
 * 
 * @author AnkurJ
 */
public class ForcedResetPasswordFormAction extends org.jasig.cas.web.flow.AuthenticationViaFormAction{
    
    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(ForcedResetPasswordFormAction.class);

    /** The authentication proxy. */
    private AuthenticationProxy authenticationProxy;

    /**
     * Sets the authentication proxy.
     * 
     * @param authenticationProxy the new authentication proxy
     */
    public void setAuthenticationProxy(AuthenticationProxy authenticationProxy) {
        this.authenticationProxy = authenticationProxy;
    }


    /**
     * Display password strength.
     * 
     * @param context the context
     * 
     * @return the event
     */
    public Event displayPasswordStrength(RequestContext context){
    	if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("displayPasswordStrength(RequestContext) - START");
		}
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("displayPasswordStrength(RequestContext) - *** In ForcedResetPasswordFormAction (displayPasswordStrength) ... ***"); //$NON-NLS-1$
		}
		Event returnEvent = success();
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("displayPasswordStrength(RequestContext) - END");
		}
		return returnEvent;

	}


    /**
     * Bind and validate.
     * 
     * @param context the context
     * 
     * @return the event
     * 
     * @throws Exception the exception
     */
    public Event bindAndValidate(RequestContext context) throws Exception {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("bindAndValidate() -  START");
		}

		// validation
        WebUtils.getHttpServletRequest(context).getSession().setAttribute(CASConstants.AFTER_FORCED_RESET_PASSWORD, CASConstants.AFTER_FORCED_RESET_PASSWORD);
        final Credentials credentials = (Credentials) getFormObject(context);
        final UsernamePasswordDOBCredentials usernamePassword = (UsernamePasswordDOBCredentials) credentials;
        usernamePassword.setIpAddress(((ServletExternalContext)context.getExternalContext()).getRequest().getRemoteAddr());
        usernamePassword.setSessionId((((ServletExternalContext)context.getExternalContext()).getRequest().getSession().getId()));
        MutableAttributeMap map = context.getExternalContext().getRequestMap();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("bindAndValidate(RequestContext) - " + map.toString()); //$NON-NLS-1$
        }
        Map m =map.asMap();
        Set<String> s =m.keySet();
        for (Iterator iterator = s.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("bindAndValidate(RequestContext) - key  :  " + string + "    value : " + m.get(string)); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

		usernamePassword.setSessionPassThru(true);
		Event returnEvent = (super.bindAndValidate(context));
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("bindAndValidate(RequestContext) - END");
		}
		return returnEvent;
	}

}
