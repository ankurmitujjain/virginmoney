package com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.handler.support;

import org.apache.log4j.Logger;

import org.inspektr.common.ioc.annotation.NotNull;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.PasswordEncoder;
import org.jasig.cas.authentication.handler.PlainTextPasswordEncoder;
import org.jasig.cas.authentication.principal.Credentials;
import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials;
import org.jasig.cas.authentication.handler.support.*;

/**
 * Abstract class to override supports so that we don't need to duplicate the
 * check for UsernameDOBPasswordCredentials. Based on the CAS
 * AbstractUsernamePasswordAuthenticationHandler
 * 
 * Author(s)     :  choprah
 * Creation Date :  01-May-2008
 * Copyright     :  Virgin Money Ltd.
 * <p>
 * This is a published and supported CAS Server 3 API.
 * </p>
 */
public abstract class AbstractPasswordAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {
	
	/** Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(AbstractPasswordAuthenticationHandler.class);

    /** Default class to support if one is not supplied. */
    private static final Class<UsernamePasswordDOBCredentials> DEFAULT_CLASS = UsernamePasswordDOBCredentials.class;

    /** Class that this instance will support. */
    @NotNull
    private Class< ? > classToSupport = DEFAULT_CLASS;

    /** Boolean to determine whether to support subclasses of the class to support. */
    private boolean supportSubClasses = true;

    /** PasswordEncoder to be used by subclasses to encode passwords for comparing against a resource. */
    @NotNull
    private PasswordEncoder passwordEncoder = new PlainTextPasswordEncoder();

    /**
     * Method automatically handles conversion to UsernamePasswordCredentials
     * and delegates to abstract authenticateUsernamePasswordInternal so
     * subclasses do not need to cast.
     * 
     * @param credentials the credentials
     * 
     * @return true, if do authentication
     * 
     * @throws AuthenticationException the authentication exception
     */
    protected final boolean doAuthentication(final Credentials credentials)
        throws AuthenticationException {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("doAuthentication() -  START");
		}

		boolean returnboolean = authenticateUsernamePasswordInternal((UsernamePasswordDOBCredentials) credentials);
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("doAuthentication(Credentials) - END");
		}
		return returnboolean;
	}

    /**
     * Abstract convenience method that assumes the credentials passed in are a
     * subclass of UsernamePasswordCredentials.
     * 
     * @param credentials the credentials representing the Username and Password
     * presented to CAS
     * 
     * @return true if the credentials are authentic, false otherwise.
     * 
     * @throws AuthenticationException if authenticity cannot be determined.
     */
    protected abstract boolean authenticateUsernamePasswordInternal(
        final UsernamePasswordDOBCredentials credentials)
        throws AuthenticationException;

    /**
     * Method to return the PasswordEncoder to be used to encode passwords.
     * 
     * @return the PasswordEncoder associated with this class.
     */
    protected final PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }

    /**
     * Method to set the class to support.
     * 
     * @param classToSupport the class we want this handler to support
     * explicitly.
     */
    public final void setClassToSupport(final Class< ? > classToSupport) {
        this.classToSupport = classToSupport;
    }

    /**
     * Method to set whether this handler will support subclasses of the
     * supported class.
     * 
     * @param supportSubClasses boolean of whether to support subclasses or not.
     */
    public final void setSupportSubClasses(final boolean supportSubClasses) {
        this.supportSubClasses = supportSubClasses;
    }

    /**
     * Sets the PasswordEncoder to be used with this class.
     * 
     * @param passwordEncoder the PasswordEncoder to use when encoding
     * passwords.
     */
    public final void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Supports.
     * 
     * @param credentials the credentials
     * 
     * @return true if the credentials are not null and the credentials class is
     * equal to the class defined in classToSupport.
     */
    public final boolean supports(final Credentials credentials) {
		boolean returnboolean = credentials != null && (this.classToSupport.isAssignableFrom(credentials.getClass()) && this.supportSubClasses);
		return returnboolean;
	}
}
