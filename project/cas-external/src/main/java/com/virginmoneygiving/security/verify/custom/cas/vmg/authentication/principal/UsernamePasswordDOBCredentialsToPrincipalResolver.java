package com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal;

import org.apache.log4j.Logger;

/**
 * Implementation of CredentialsToPrincipalResolver for Credentials based on
 * UsernamePasswordDOBCredentials when a SimplePrincipal (username only) is
 * sufficient.
 * <p>
 * Implementation extracts the username from the Credentials provided and
 * constructs a new SimplePrincipal with the unique id set to the username.
 * </p>
 *
 * @author Scott Battaglia
 * @version $Revision: 1.2 $ $Date: 2007/01/22 20:35:26 $
 * @since 3.0
 * @see org.jasig.cas.authentication.principal.SimplePrincipal
 */
import org.apache.commons.lang.StringUtils;
import org.jasig.cas.authentication.principal.*;

/**
 * Implementation of CredentialsToPrincipalResolver for Credentials based on
 * UsernamePasswordDOBCredentials when a SimplePrincipal (username only) is
 * sufficient.
 * 
 * @author Scott Battaglia.
 */
public final class UsernamePasswordDOBCredentialsToPrincipalResolver extends
    AbstractPersonDirectoryCredentialsToPrincipalResolver {
	
	/** Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(UsernamePasswordDOBCredentialsToPrincipalResolver.class);

    /**
     * Extract principal id.
     * 
     * @param credentials the credentials
     * 
     * @return the string
     */
    protected String extractPrincipalId(final Credentials credentials) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("extractPrincipalId() -  START");
		}

		final UsernamePasswordDOBCredentials usernamePasswordCredentials = (UsernamePasswordDOBCredentials) credentials;
        String userContext = usernamePasswordCredentials.getUsername();
        if(StringUtils.isNotBlank(usernamePasswordCredentials.getDateOfBirth())){
            userContext = userContext + "," + usernamePasswordCredentials.getDateOfBirth();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("extractPrincipalId(credentials=" + credentials + ") - String userContext=" + userContext);
			}
        }

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("extractPrincipalId(Credentials) - END");
		}
		return userContext;
    }

    /**
     * Return true if Credentials are UsernamePasswordCredentials, false
     * otherwise.
     * 
     * @param credentials the credentials
     * 
     * @return true, if supports
     */
    public boolean supports(final Credentials credentials) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("supports() -  START");
		}

		boolean returnboolean = credentials != null && UsernamePasswordDOBCredentials.class.isAssignableFrom(credentials.getClass());
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("supports(Credentials) - END");
		}
		return returnboolean;
	}
}
