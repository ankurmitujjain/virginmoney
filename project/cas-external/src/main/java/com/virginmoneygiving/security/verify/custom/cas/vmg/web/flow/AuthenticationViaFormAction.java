package com.virginmoneygiving.security.verify.custom.cas.vmg.web.flow;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie; 

import org.apache.log4j.Logger;
import org.inspektr.common.ioc.annotation.NotNull;
import org.jasig.cas.CentralAuthenticationService;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.ticket.TicketException;
import org.jasig.cas.web.bind.CredentialsBinder;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.util.CookieGenerator;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.context.servlet.ServletExternalContext;

import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.SignedInFlagHelper;
import com.virginmoneygiving.common.utility.UUIDGenerator;
import com.virginmoneygiving.security.verify.custom.business.AuthenticationDetails;
import com.virginmoneygiving.security.verify.custom.business.PasswordDetails;
import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials;
import com.virginmoneygiving.security.verify.custom.cas.vmg.validation.UsernameDOBCredentialsValidator;
import com.virginmoneygiving.security.verify.custom.exceptions.ClassNotSupportedException;
import com.virginmoneygiving.security.verify.custom.serviceproxy.AuthenticationProxy;

/**
 * This is the VirginMoney implementation of the FormAction to authenticate
 * credentials and retrieve a TicketGrantingTicket for those credentials. This
 * implements a 2-step process, where step 1 authenticates the Login ID and date
 * of birth, and Step 2 vaerifies the password. If there is a request for renew,
 * then it also generates the Service Ticket required.
 * <p/>
 * 
 * @author: AnkurJ
 * Creation Date : 01-May-2008
 * Copyright : Virgin Money Ltd.
 */
public class AuthenticationViaFormAction extends FormAction {
    
    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(AuthenticationViaFormAction.class);
    
    /** Binder that allows additional binding of form object beyond Spring defaults. */
    private CredentialsBinder credentialsBinder;

    /** Core we delegate to for handling all ticket related tasks. */
    @NotNull
    private CentralAuthenticationService centralAuthenticationService;

    /** Cookie generator object. */
    @NotNull
    private CookieGenerator warnCookieGenerator;

    /** The authentication proxy. */
    private AuthenticationProxy authenticationProxy;

    /** Delegates the creating of an indicator showing if a user has signed in **/
    private SignedInFlagHelper signedInFlagHelper;
    
    /**
     * Sets the authentication proxy.
     * 
     * @param authenticationProxy the new authentication proxy
     */
    public void setAuthenticationProxy(AuthenticationProxy authenticationProxy) {
		this.authenticationProxy = authenticationProxy;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setAuthenticationProxy(AuthenticationProxy) -  AuthProxy has been set (2)"); //$NON-NLS-1$
        }
    }

    /**
     * Binds the object.
     * 
     * @param context Request Context
     * @param binder Data Binder
     */
    protected final void doBind(final RequestContext context, final DataBinder binder) {

		final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        final Credentials credentials = (Credentials) binder.getTarget();

        if (this.credentialsBinder != null) {
            this.credentialsBinder.bind(request, credentials);
        }
        
		try {
			super.doBind(context, binder);
		} catch (Exception e) {
			LOGGER.error("Error occured while binding the object to curretn context context." , e);
			throw new RuntimeException("Error occured while binding the object to curretn context context.");
		}
        
	}

    /**
     * Sets the reference data item commandName in the context.
     * 
     * @param context Request Context
     * 
     * @return Event
     */
    public Event referenceData(final RequestContext context) {
		context.getRequestScope().put("commandName", getFormObjectName());
		Event returnEvent = success();
		return returnEvent;
	}

    /**
     * Submit action - calls the Security service to authenticate the user ID
     * and Date of birth.
     * 
     * @param context Request context
     * 
     * @return Event
     */
    public final Event submit(final RequestContext context) throws ClassNotSupportedException{

		Credentials credentials = null;
		
		try {
			credentials = (Credentials) getFormObject(context);
		} catch (Exception e) {
			LOGGER.error("Error occured while retreiving form object associated with current context." , e);
			throw new RuntimeException("Error occured while retreiving from object associated with current context.");
		}        

        if (!(credentials instanceof UsernamePasswordDOBCredentials)) {
            throw new ClassNotSupportedException(LOGGER , "Credentials not instance of UsernamePasswordDOBCredentials");
        }

        HttpServletRequest httprequest = WebUtils.getHttpServletRequest(context);
        final UsernamePasswordDOBCredentials usernamePassword = (UsernamePasswordDOBCredentials) credentials;

        final String username = usernamePassword.getUsername();
        // final String password = usernamePassword.getPassword();
        final String dob = usernamePassword.getDateOfBirth();
        final String brand = usernamePassword.getBrandReference();
        httprequest.getSession().setAttribute("username", username);
        httprequest.getSession().setAttribute("dob", dob);

        LOGGER.debug("In AuthenticationViaFormAction SUBMIT-brand/user/DOB: " + brand + "/" + username + "/" + dob);

        String ipAddress = httprequest.getRemoteAddr();
        String sessionId = httprequest.getSession().getId();

        LOGGER.debug("Sending request to create message header (IP/Session): "
				+ ipAddress + "/" + sessionId);

		LOGGER
				.debug("In AuthenticationViaFormAction SUBMIT - Start Authentication");
		boolean validated = true;
		boolean accountLocked = false;

		AuthenticationDetails authDetails = new AuthenticationDetails();
		authDetails.setBrandRef(brand);
		authDetails.setDateOfBirth(dob);
		authDetails.setIpAddress(ipAddress);
		authDetails.setSessionId(sessionId);
		authDetails.setSystemTransactionId(UUIDGenerator.generateUUID());
		authDetails.setUserName(username);
		authDetails = authenticationProxy.authenticateVMGUser(authDetails);
		accountLocked = authDetails.isAccountLocked();
		validated = authDetails.isValidated();

		if (accountLocked) {
			LOGGER.debug("In AuthenticationViaFormAction SUBMIT Service Response. Account is locked");
			return result("locked");
		}

		Long userId = authDetails.getUserId();
		if (!validated) {
			userId = new Long(-1);
		}
		usernamePassword.setUserId(userId);
		setupPasswordPositions(usernamePassword, authDetails.getPassword());
		usernamePassword.setValidationTarget("PASSWORD");

		LOGGER.debug("In AuthenticationViaFormAction SUBMIT Service Response. values = "
						+ validated + " / " + userId);
		
		return success();

    }

    /**
     * Returns a WARN event.
     * 
     * @return Event
     */
    private final Event warn() {
		Event returnEvent = result("warn");
		return returnEvent;
	}

    /**
     * Populates the context with errors.
     * 
     * @param context Request context
     * @param e Ticket exception
     */
    private final void populateErrorsInstance(final RequestContext context, final TicketException e) {

		Errors errors = null;
		
		try {
			errors = getFormErrors(context);
			if(errors != null) {
				errors.reject(e.getCode(), e.getCode());
			}
		} catch (Exception exception) {
			LOGGER.error("Error occured while retreiving form error associated with current context." , exception);
			throw new RuntimeException("Error occured while retreiving form error associated with current context.");
		}   

	}

    /**
     * Put warn cookie if request parameter present.
     * 
     * @param context the context
     */
    private void putWarnCookieIfRequestParameterPresent(final RequestContext context) {
		final HttpServletResponse response = WebUtils.getHttpServletResponse(context);

        if (StringUtils.hasText(context.getExternalContext().getRequestParameterMap().get("warn"))) {
            this.warnCookieGenerator.addCookie(response, "true");
        } else {
            this.warnCookieGenerator.removeCookie(response);
        }
	}
    /**
     * Sets a class to help with setting and removing an indicator showing a user has logged on.
     * @param signedInFlagHelper
     */
    public void setSignedInFlagHelper(SignedInFlagHelper signedInFlagHelper){
    	this.signedInFlagHelper = signedInFlagHelper;
    }
    
    /**
     * Injector. Sets the centralAuthenticationService property.
     * 
     * @param centralAuthenticationService Object to set
     */
    public final void setCentralAuthenticationService(final CentralAuthenticationService centralAuthenticationService) {
        this.centralAuthenticationService = centralAuthenticationService;
    }

    /**
     * Set a CredentialsBinder for additional binding of the HttpServletRequest
     * to the Credentials instance, beyond our default binding of the
     * Credentials as a Form Object in Spring WebMVC parlance. By the time we
     * invoke this CredentialsBinder, we have already engaged in default binding
     * such that for each HttpServletRequest parameter, if there was a JavaBean
     * property of the Credentials implementation of the same name, we have set
     * that property to be the value of the corresponding request parameter.
     * This CredentialsBinder plugin point exists to allow consideration of
     * things other than HttpServletRequest parameters in populating the
     * Credentials (or more sophisticated consideration of the
     * HttpServletRequest parameters).
     * 
     * @param credentialsBinder the credentials binder
     */
    public final void setCredentialsBinder(final CredentialsBinder credentialsBinder) {
        this.credentialsBinder = credentialsBinder;
    }

    /**
     * Injector. Sets the warnCookieGenerator property
     * 
     * @param warnCookieGenerator Object to set
     */
    public final void setWarnCookieGenerator(final CookieGenerator warnCookieGenerator) {
        this.warnCookieGenerator = warnCookieGenerator;
    }

    /**
     * Initial Action method.
     */
    protected void initAction() {
        if (this.getFormObjectClass() == null) {
            this.setFormObjectClass(UsernamePasswordDOBCredentials.class);
            this.setFormObjectName("credentials");
            this.setValidator(new UsernameDOBCredentialsValidator());

            LOGGER.info("FormObjectClass not set.  Using default class of " + this.getFormObjectClass().getName() + " with formObjectName "
                    + this.getFormObjectName() + " and validator " + this.getValidator().getClass().getName() + ".");
        }

        Assert.isTrue(Credentials.class.isAssignableFrom(this.getFormObjectClass()), "CommandClass must be of type Credentials.");

        if (this.credentialsBinder != null) {
            Assert.isTrue(this.credentialsBinder.supports(this.getFormObjectClass()), "CredentialsBinder does not support supplied FormObjectClass: "
                    + this.getClass().getName());
        }
    }

    /**
     * Sets up the password locations.
     * 
     * @param usernamePassword Credential to populate
     * @param password JAX service response object
     */
    private void setupPasswordPositions(UsernamePasswordDOBCredentials usernamePassword, List<PasswordDetails> password) {
        PasswordDetails pass1 = password.get(0);
        PasswordDetails pass2 = password.get(1);

        usernamePassword.setPasswordLocation1(pass1.getPasswordPosition());
        usernamePassword.setPasswordLocation2(pass2.getPasswordPosition());
    }

    /**
     * Process pass thru.
     * 
     * @param context the context
     * 
     * @return Event
     */
    public final Event processPassThru(final RequestContext context) {
        LOGGER.debug("In AuthenticationViaFormAction processPassThru");
        Credentials credentials = null;
        try {
			credentials = (Credentials) getFormObject(context);
		} catch (Exception e) {
			LOGGER.error("Error occured while retreiving form object associated with current context." , e);
			throw new RuntimeException("Error occured while retreiving from object associated with current context.");
		}
        // START: ChangesBusinessMart: check, whether the posting has been sent
        // from a remote server

        HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        String referrer = request.getParameter("login-at");
        String myServerName = request.getLocalName();
        String userId = request.getParameter("referenceId");
        String loginTicket = request.getParameter("accountId");
        LOGGER.debug("In AuthenticationViaFormAction (processPassThru) Server/Referrer = " + referrer + "/" + myServerName);
        LOGGER.debug("In AuthenticationViaFormAction (processPassThru) User/LoginTkt = " + userId + "/" + loginTicket);
        if (userId == null || loginTicket == null) {
			Event returnEvent = result("error");
            return returnEvent;
        }
        final UsernamePasswordDOBCredentials usernamePassword = (UsernamePasswordDOBCredentials) credentials;
        usernamePassword.setUsername(userId);
        usernamePassword.setAccountId(loginTicket);
        usernamePassword.setValidationTarget("PASSTHRU");
        try {
            final String ticketGrantingTicketId = this.centralAuthenticationService.createTicketGrantingTicket(credentials);
            LOGGER.debug("In AuthenticationViaFormAction (processPassThru) TGT = " + ticketGrantingTicketId);
            WebUtils.putTicketGrantingTicketInRequestScope(context, ticketGrantingTicketId);
            putWarnCookieIfRequestParameterPresent(context);           
            //Create signed in flag
            this.createSignInFlagCookie(context);    		
            LOGGER.debug("In AuthenticationViaFormAction (processPassThru) completed for TGT = " + ticketGrantingTicketId);

        } catch (final TicketException e) {
            LOGGER.debug("***** In AuthenticationViaFormAction (processPassThru) ERROR *****");
            LOGGER.error("processPassThru(RequestContext)", e); //$NON-NLS-1$
            populateErrorsInstance(context, e);
			Event returnEvent = result("error");
            return returnEvent;
        }
		Event returnEvent = result("tempPwdValid");
        return returnEvent;

    }


    /**
     * Process ops pass thru.
     * 
     * @param context the context
     * 
     * @return Event
     */
    public final Event processOpsPassThru(final RequestContext context) {
        LOGGER.debug("In AuthenticationViaFormAction processPassThru");
        Credentials credentials = null;
        
        try {
			credentials = (Credentials) getFormObject(context);
		} catch (Exception e) {
			LOGGER.error("Error occured while retreiving form object associated with current context." , e);
			throw new RuntimeException("Error occured while retreiving from object associated with current context.");
		}
		
        // START: ChangesBusinessMart: check, whether the posting has been sent
        // from a remote server
        HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        String referrer = request.getParameter("login-at");
        String myServerName = request.getLocalName();
        String userId = request.getParameter("opsAccountId");
        String loginTicket = request.getParameter("loginTicket");
        LOGGER.debug("In AuthenticationViaFormAction (processOpsPassThru) Server/Referrer = " + referrer + "/" + myServerName);
        LOGGER.debug("In AuthenticationViaFormAction (processOpsPassThru) User/LoginTkt = " + userId + "/" + loginTicket);
        if (userId == null || loginTicket == null) {
			Event returnEvent = result("error");
            return returnEvent;
        }

        final UsernamePasswordDOBCredentials usernamePassword = (UsernamePasswordDOBCredentials) credentials;
        usernamePassword.setUsername(userId);
        usernamePassword.setAccountId(loginTicket);
        usernamePassword.setValidationTarget("INTERNALUSERPASSTHRU");
        try {
            final String ticketGrantingTicketId = this.centralAuthenticationService.createTicketGrantingTicket(usernamePassword);
            LOGGER.debug("In AuthenticationViaFormAction (processPassThru) TGT = " + ticketGrantingTicketId);
            WebUtils.putTicketGrantingTicketInRequestScope(context, ticketGrantingTicketId);
            putWarnCookieIfRequestParameterPresent(context);
            LOGGER.debug("In AuthenticationViaFormAction (processPassThru) completed for TGT = " + ticketGrantingTicketId);

        } catch (final TicketException e) {
            LOGGER.debug("***** In AuthenticationViaFormAction (processPassThru) ERROR *****");
            LOGGER.error("processOpsPassThru(RequestContext)", e); //$NON-NLS-1$
            populateErrorsInstance(context, e);
			Event returnEvent = result("error");
            return returnEvent;
        }
		Event returnEvent = result("tempPwdValid");
        return returnEvent;

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

}
