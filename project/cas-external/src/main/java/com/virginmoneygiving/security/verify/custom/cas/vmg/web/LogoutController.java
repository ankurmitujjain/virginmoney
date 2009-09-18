/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package com.virginmoneygiving.security.verify.custom.cas.vmg.web;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inspektr.common.ioc.annotation.NotNull;
import org.jasig.cas.CentralAuthenticationService;
import org.jasig.cas.web.support.CookieRetrievingCookieGenerator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;
import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.SignedInFlagHelper;

/**
 * Controller to delete ticket granting ticket cookie in order to log out of
 * single sign on. This controller implements the idea of the ESUP Portail's
 * Logout patch to allow for redirecting to a url on logout. It also exposes a
 * log out link to the view via the WebConstants.LOGOUT constant.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42776 $ $Date: 2008-01-04 09:15:42 -0500 (Fri, 04 Jan 2008) $
 * @since 3.0
 */
public final class LogoutController extends AbstractController {
	
	/** Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(LogoutController.class);

    /** The CORE to which we delegate for all CAS functionality. */
    @NotNull
    private CentralAuthenticationService centralAuthenticationService;

    /** CookieGenerator for TGT Cookie. */
    @NotNull
    private CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator;

    /** CookieGenerator for Warn Cookie. */
    @NotNull
    private CookieRetrievingCookieGenerator warnCookieGenerator;
    
    /** Delegates the creating of an indicator showing if a user has signed in **/
    private SignedInFlagHelper signedInFlagHelper;

    /** Logout view name. */
    @NotNull
    private String logoutView;

    /** Boolean to determine if we will redirect to any url provided in the service request parameter. */
    private boolean followServiceRedirects;

    /**
     * Instantiates a new logout controller.
     */
    public LogoutController() {
        setCacheSeconds(0);
    }

    /**
     * Handle request internal.
     * 
     * @param request the request
     * @param response the response
     * 
     * @return the model and view
     */
    protected ModelAndView handleRequestInternal(
        final HttpServletRequest request, final HttpServletResponse response) {

		final String ticketGrantingTicketId = this.ticketGrantingTicketCookieGenerator.retrieveCookieValue(request);
        final String service = request.getParameter("service");
        final String accountId = request.getParameter("accountId");

        if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("handleRequestInternal String accountId=" + accountId);
		}
		//Remove Signed in flag cookie.
        signedInFlagHelper.removeSignedInFlag(request, response);
        
        logoutOfService(accountId);


        if (ticketGrantingTicketId != null) {
            this.centralAuthenticationService
                .destroyTicketGrantingTicket(ticketGrantingTicketId);

            this.ticketGrantingTicketCookieGenerator.removeCookie(response);
            this.warnCookieGenerator.removeCookie(response);
        }

        if (this.followServiceRedirects && service != null) {
			ModelAndView returnModelAndView = new ModelAndView(new RedirectView(service));
			return returnModelAndView;
		}
        request.getSession().invalidate();

		ModelAndView returnModelAndView = new ModelAndView(this.logoutView);
		return returnModelAndView;
	}

    /**
     * Sets the ticket granting ticket cookie generator.
     * 
     * @param ticketGrantingTicketCookieGenerator the new ticket granting ticket cookie generator
     */
    public void setTicketGrantingTicketCookieGenerator(
        final CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator) {
        this.ticketGrantingTicketCookieGenerator = ticketGrantingTicketCookieGenerator;
    }

    /**
     * Sets the warn cookie generator.
     * 
     * @param warnCookieGenerator the new warn cookie generator
     */
    public void setWarnCookieGenerator(final CookieRetrievingCookieGenerator warnCookieGenerator) {
        this.warnCookieGenerator = warnCookieGenerator;
    }

    /**
     * Sets the central authentication service.
     * 
     * @param centralAuthenticationService The centralAuthenticationService to
     * set.
     */
    public void setCentralAuthenticationService(
        final CentralAuthenticationService centralAuthenticationService) {
        this.centralAuthenticationService = centralAuthenticationService;
    }

    /**
     * Sets a class to help with setting and removing an indicator showing a user has logged on.
     * @param signedInFlagHelper
     */
    public void setSignedInFlagHelper(SignedInFlagHelper signedInFlagHelper){
    	this.signedInFlagHelper = signedInFlagHelper;
    }
    
    /**
     * Sets the follow service redirects.
     * 
     * @param followServiceRedirects the new follow service redirects
     */
    public void setFollowServiceRedirects(final boolean followServiceRedirects) {
        this.followServiceRedirects = followServiceRedirects;
    }

    /**
     * Sets the logout view.
     * 
     * @param logoutView the new logout view
     */
    public void setLogoutView(final String logoutView) {
        this.logoutView = logoutView;
    }

    /**
     * Logout of service.
     * 
     * @param accountId the account id
     */
    private void logoutOfService(String accountId) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("logoutOfService() -  START");
		}

		LOGGER.debug("In LogoutController (logoutOfService) for Account: "+accountId);

        if (accountId == null || accountId.length() < 1) {
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("logoutOfService(String) - END");
			}
			return;
        }
        LOGGER.debug("LogoutController (logoutOfService) Got a handle on service ...");

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("logoutOfService(String) - END");
		}
	}
}
