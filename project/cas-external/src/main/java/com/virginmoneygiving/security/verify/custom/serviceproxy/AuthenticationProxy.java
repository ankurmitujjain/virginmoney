package com.virginmoneygiving.security.verify.custom.serviceproxy;

import com.virginmoney.security.service.client.messages.ServiceFaultException;
import com.virginmoney.security.service.client.messages.ValidateLoginTicketResponse;
import com.virginmoneygiving.security.verify.custom.business.AuthenticationDetails;
import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials;

/**
 * Proxy interface for the Authentication web service.
 * 
 * User: choprah, ankurj
 * Date: 03-Feb-2009
 * Time: 11:24:40
 * To change this template use File | Settings | File Templates.
 */
public interface AuthenticationProxy {

    /**
     * Authenticate vmg user.
     * 
     * @param details the details
     * 
     * @return the authentication details
     * 
     * @throws ServiceFaultException the service fault exception
     */
    AuthenticationDetails authenticateVMGUser(AuthenticationDetails details);

    /**
     * Validate vmg userpassword.
     * 
     * @param details the details
     * 
     * @return the authentication details
     * 
     * @throws ServiceFaultException the service fault exception
     */
    AuthenticationDetails validateVMGUserpassword(AuthenticationDetails details) throws ServiceFaultException;

    /**
     * Validate login ticket.
     * 
     * @param credentials the credentials
     * 
     * @return the validate login ticket response
     */
    ValidateLoginTicketResponse validateLoginTicket(UsernamePasswordDOBCredentials credentials);

    /**
     * Change forgotten password.
     * 
     * @param credentials the credentials
     * 
     * @return true, if successful
     * 
     * @throws ServiceFaultException the service fault exception
     */
    boolean changeForgottenPassword(UsernamePasswordDOBCredentials credentials) throws ServiceFaultException;


    /**
     * Validate internal user.
     * 
     * @param username the username
     * @param loginTicket the login ticket
     * 
     * @return true, if successful
     * 
     * @throws ServiceFaultException the service fault exception
     */
    boolean validateInternalUser(String username ,String loginTicket) throws ServiceFaultException;

}
