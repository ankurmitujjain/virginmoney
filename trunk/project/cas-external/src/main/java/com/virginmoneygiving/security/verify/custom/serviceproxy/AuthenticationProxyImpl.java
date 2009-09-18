package com.virginmoneygiving.security.verify.custom.serviceproxy;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.virginmoney.security.service.client.messages.ChangeForgottenPasswordRequest;
import com.virginmoney.security.service.client.messages.ChangeForgottenPasswordResponse;
import com.virginmoney.security.service.client.messages.InternalUserRequest;
import com.virginmoney.security.service.client.messages.MessageHeader;
import com.virginmoney.security.service.client.messages.PasswordCharacterType;
import com.virginmoney.security.service.client.messages.SecurityServicePort;
import com.virginmoney.security.service.client.messages.SecurityServiceWS;
import com.virginmoney.security.service.client.messages.ServiceFaultException;
import com.virginmoney.security.service.client.messages.UserCredentialsResponseType;
import com.virginmoney.security.service.client.messages.ValidateGivingUserPasswordResponseType;
import com.virginmoney.security.service.client.messages.ValidateLoginTicketRequest;
import com.virginmoney.security.service.client.messages.ValidateLoginTicketResponse;
import com.virginmoney.security.service.client.messages.ValidateUserCredentialsRequestType;
import com.virginmoney.security.service.client.messages.ValidateUserCredentialsResponseType;
import com.virginmoney.security.service.client.messages.ValidateUserPasswordRequestType;
import com.virginmoneygiving.common.CommonConstants;
import com.virginmoneygiving.common.utility.DateBuilder;
import com.virginmoneygiving.common.utility.UUIDGenerator;
import com.virginmoneygiving.common.exception.ServiceException;
import com.virginmoneygiving.security.verify.custom.business.AuthenticationDetails;
import com.virginmoneygiving.security.verify.custom.business.CASConstants;
import com.virginmoneygiving.security.verify.custom.business.PasswordDetails;
import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials;
import com.virginmoneygiving.security.verify.custom.services.VerifyServiceLocator;

/**
 * Proxy implementation for the Authentication web service.
 * 
 * @author AnkurJ
 * @date Mar 24, 2009
 */
public class AuthenticationProxyImpl implements AuthenticationProxy {

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(AuthenticationProxyImpl.class);

    /**
     * Authenticate vmg user.
     * 
     * @param details the details
     * 
     * @return the authentication details
     * 
     * @throws ServiceFaultException the service fault exception
     * 
     * @see com.virginmoneygiving.security.verify.custom.serviceproxy.AuthenticationProxy#authenticateVMGUser(com.virginmoneygiving.security.verify.custom.business.AuthenticationDetails)
     */
    public AuthenticationDetails authenticateVMGUser(
            AuthenticationDetails details) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("authenticateVMGUser() -  START");
		}

		if (details == null) {
            details = new AuthenticationDetails();
            details.setValidated(false);

			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("authenticateVMGUser(AuthenticationDetails) - END");
			}
			return details;
        }

        details.setUserId(new Long(-1));
        
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("In AuthenticationProxyImpl - Start User Authentication");
        }
        
        VerifyServiceLocator sl = new VerifyServiceLocator();
        SecurityServiceWS verifyService = null;
		verifyService = sl.getSecurityService();

        SecurityServicePort port =
                verifyService.getSecurityServicePortPort();

        MessageHeader msgHeader = new MessageHeader();
        msgHeader.setSystemTransactionID(details.getSystemTransactionId());
        msgHeader.setSessionID(details.getSessionId());
        ValidateUserCredentialsRequestType request =
                new ValidateUserCredentialsRequestType();
        request.setUserLoginID(details.getUserName());
        request.setUserDateOfBirth(convertDOB(details.getDateOfBirth()));
        request.setHeader(msgHeader);
        request.setLoginRequestSource(details.getIpAddress());
        
        ValidateUserCredentialsResponseType response = null;
        
        try {
            response =
                    port.validateUserCredentails(request);
        } catch (ServiceFaultException serviceFaultException) {
        	LOGGER.error("Error occured in security service during authentication of VMG user. ", serviceFaultException);
        	LOGGER.error("Input details :" + details.getDateOfBirth() + "/t" + details.getUserName());
        	throw new ServiceException("Error occured in security service during authentication of VMG user. ", serviceFaultException);
        }
        
        if(response == null) {
        	throw new ServiceException("Error occured in security service during authentication of VMG user. ");
        }
        
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("Got a Service Response ...");
        }
        boolean validated = response.isOk();
        boolean accountLocked = response.isAccountLocked();

        details.setAccountLocked(accountLocked);
        details.setValidated(true);

        UserCredentialsResponseType ucType = response.getUserCredentials();

        Long userId = new Long(-1);
        if (!validated) {
            userId = new Long(-1);
        }

        details.setUserId(userId);
        if (ucType.getPasswordCharacters().size() == 2) {
            setupPasswordPositions(details, ucType);
        }
        else {
            LOGGER.error("Invalid number of password positions for user ["
                    + details.getUserName() + "]="
                    + ucType.getPasswordCharacters().size());
            details.setValidated(false);
            details.setResponseTarget(CASConstants.AUTHENTICATION_ERROR);
        }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("Completed AuthenticationProxyImpl (User) with status: "
            + details.isValidated());
        }
        return details;
    }

    /**
     * Validate login ticket.
     * 
     * @param credentials the credentials
     * 
     * @return the validate login ticket response
     * 
     * @see com.virginmoneygiving.security.verify.custom.serviceproxy.AuthenticationProxy#validateLoginTicket(com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials)
     */
    public ValidateLoginTicketResponse validateLoginTicket(
            UsernamePasswordDOBCredentials credentials) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("validateLoginTicket() -  START");
		}

        ValidateLoginTicketRequest request = new ValidateLoginTicketRequest();
        request.setUniqueReferenceId(credentials.getUsername());
        request.setAccountId(credentials.getAccountId());
        request.setLoginRequestSource(credentials.getIpAddress());

        if(StringUtils.isEmpty(request.getLoginRequestSource())){
        	request.setLoginRequestSource("THIS");
        }
        VerifyServiceLocator sl = new VerifyServiceLocator();
        SecurityServiceWS verifyService;

            verifyService = sl.getSecurityService();
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Got a handle on service ...");
            }
            SecurityServicePort port =
                    verifyService.getSecurityServicePortPort();
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Got a handle on PORT ...");
            }
            try {
			ValidateLoginTicketResponse returnValidateLoginTicketResponse = port.validateLoginTicket(request);
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("validateLoginTicket(UsernamePasswordDOBCredentials) - END");
			}
			return returnValidateLoginTicketResponse;
		} catch (ServiceFaultException e) {
				LOGGER.error("Service fault exception occured while validating login ticket" ,e);
			}

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("validateLoginTicket(UsernamePasswordDOBCredentials) - END");
		}
		return null;
    }

    /**
     * Validate vmg userpassword.
     * 
     * @param details the details
     * 
     * @return the authentication details
     * 
     * @throws ServiceFaultException the service fault exception
     * 
     * @see com.virginmoneygiving.security.verify.custom.serviceproxy.AuthenticationProxy#validateVMGUserpassword(com.virginmoneygiving.security.verify.custom.business.AuthenticationDetails)
     */
    public AuthenticationDetails validateVMGUserpassword(
            AuthenticationDetails details) throws ServiceFaultException {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("validateVMGUserpassword() -  START");
		}

		if (details == null) {
            details = new AuthenticationDetails();
            details.setValidated(false);

			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("validateVMGUserpassword(AuthenticationDetails) - END");
			}
			return details;
        }
		 if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("In AuthenticationProxyImpl - Start password Authentication");
		 }
        details.setValidated(false);

            VerifyServiceLocator sl = new VerifyServiceLocator();
            SecurityServiceWS verifyService = sl.getSecurityService();
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Got a handle on service ...");
            }
            SecurityServicePort port =
                    verifyService.getSecurityServicePortPort();
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Got a handle on PORT ...");

            LOGGER.debug("PWD Details: " + "/"
                    + details.getUserName() + "/" + details.getDateOfBirth());
            }
            MessageHeader msgHeader = new MessageHeader();
            msgHeader.setSystemTransactionID(details.getSystemTransactionId());
            msgHeader.setSessionID(details.getSessionId());

            ValidateUserPasswordRequestType request =
                    new ValidateUserPasswordRequestType();
            request.setUserLoginID(details.getUserName());
            request.setUserDateOfBirth(convertDOB(details.getDateOfBirth()));
            request.setHeader(msgHeader);
            request.setLoginRequestSource(details.getIpAddress());

            PasswordCharacterType pass1 = new PasswordCharacterType();
            PasswordCharacterType pass2 = new PasswordCharacterType();

            pass1.setPasswordCharacter(details.getPassword().get(0)
                    .getPasswordCharacter());
            pass1.setPasswordCharacterNumber(details.getPassword().get(0)
                    .getPasswordPosition());

            pass2.setPasswordCharacter(details.getPassword().get(1)
                    .getPasswordCharacter());
            pass2.setPasswordCharacterNumber(details.getPassword().get(1)
                    .getPasswordPosition());

            request.getPasswordCharacters().add(pass1);
            request.getPasswordCharacters().add(pass2);

            ValidateGivingUserPasswordResponseType response = null;
			try {
				response = port.validateUserPassword(request);
			} catch (ServiceFaultException e) {
				LOGGER.error("Unexpected error (Password validation) for user [" + details.getUserName() + "]", e);
			    throw e;

			}
            LOGGER.debug("Got a Service Response ...");
            boolean accountLockedVMG = response.isAccountLockedByVMG();
            boolean accountLockedFailure =
                    response.isAccountLockedloginFailure();
            boolean invalidPassword = response.isPasswordCharWrong();
            boolean tempPassword = response.isValidTempPassIssue();
            boolean tempPasswordExpired = response.isTempPassExpired();
            details.setValidated(false);

            if (!accountLockedVMG && !accountLockedFailure && !invalidPassword
                    && !tempPassword && !tempPasswordExpired) {
            	 if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Login Validated. user name = "
                        + details.getUserName());
            	 }
                details.setResponseTarget(CASConstants.PASSWORD_VALIDATION_OK);
                details.setValidated(true);

				if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("validateVMGUserpassword(AuthenticationDetails) - END");
			}
                return details;
            }
            else if (invalidPassword) {
            	 if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Password Invalidated. user name = "
                        + details.getUserName());
            	 }
                details
                        .setResponseTarget(CASConstants.PASSWORD_VALIDATION_FAILED);
                details.setValidated(false);

				if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("validateVMGUserpassword(AuthenticationDetails) - END");
				}
                return details;
            }

            if (accountLockedVMG) {
                details.setResponseTarget(CASConstants.PASSWORD_VALIDATION_TARGET_LOCKED_VMG);
            }
            else if (accountLockedFailure) {
                details.setResponseTarget(CASConstants.PASSWORD_VALIDATION_TARGET_LOCKED_FAILED);
            }
            else if (tempPassword && !tempPasswordExpired) {
                details.setResponseTarget(CASConstants.PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_OK);
                details.setValidated(true);

				if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("validateVMGUserpassword(AuthenticationDetails) - END");
			}
                return details;
            }
            else if (tempPasswordExpired) {
                details
                        .setResponseTarget(CASConstants.PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_EXPIRE);
            }
            else {
                details
                        .setResponseTarget(CASConstants.PASSWORD_VALIDATION_FAILED);
            }

            if (LOGGER.isDebugEnabled()) {
            	LOGGER.debug("Completed AuthenticationProxyImpl (Password) with status: "
                        + details.isValidated());
            }
        return details;
    }

    /**
     * Validates and Converts the Date of birth value to an XMLGregorianCalendar
     * object.
     * 
     * @param dob value to convert
     * 
     * @return XMLGregorianCalendar value
     */
    private XMLGregorianCalendar convertDOB(String dob) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("convertDOB() -  START");
		}

        if (dob == null) {
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("convertDOB(String) - END");
			}
            return null;
        }
		XMLGregorianCalendar returnXMLGregorianCalendar = DateBuilder.buildXMLGregorianFromDate(DateBuilder.buildDateFromString(dob));
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("convertDOB(String) - END");
		}
        return returnXMLGregorianCalendar;

    }

    /**
     * Sets up the password locations.
     * 
     * @param details Credential to populate
     * @param ucType JAX service response object
     */
    private void setupPasswordPositions(AuthenticationDetails details,
            UserCredentialsResponseType ucType) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("setupPasswordPositions() -  START");
		}

        details.getPassword().clear();
        PasswordDetails pass1 = new PasswordDetails();
        pass1.setPasswordPosition(ucType.getPasswordCharacters().get(0)
                .getPasswordCharacterNumber());
        PasswordDetails pass2 = new PasswordDetails();
        pass2.setPasswordPosition(ucType.getPasswordCharacters().get(1)
                .getPasswordCharacterNumber());

        details.getPassword().add(pass1);
        details.getPassword().add(pass2);

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("setupPasswordPositions(AuthenticationDetails, UserCredentialsResponseType) - END");
		}
    }

    /**
     * Change forgotten password.
     * 
     * @param credentials the credentials
     * 
     * @return boolean for successful response
     * 
     * @throws ServiceFaultException the service fault exception
     * 
     * @see com.virginmoneygiving.security.verify.custom.serviceproxy.AuthenticationProxy#changeForgottenPassword(com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials)
     */
    public boolean changeForgottenPassword(
            UsernamePasswordDOBCredentials credentials) throws ServiceFaultException {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("changeForgottenPassword() -  START");
		}

        ChangeForgottenPasswordRequest request =
                new ChangeForgottenPasswordRequest();
        ChangeForgottenPasswordResponse response = null;
        request.setReferenceId(credentials.getUsername());
        request.setNewPassword(credentials.getPassword());
        request.setUserDateOfBirth(convertDOB(credentials.getDateOfBirth()));
        MessageHeader header = new MessageHeader();

        header.setSessionID(credentials.getSessionId());
        header.setIPAddress(credentials.getIpAddress());
        header.setUserName(credentials.getUsername());
        header.setSystemTransactionID(UUIDGenerator.generateUUID());
        header.setSourceSystemId(CommonConstants.GIVING);
        header.setSourceSubSystemId(CommonConstants.CAS_EXTERNAL);
        request.setHeader(header);
        VerifyServiceLocator sl = new VerifyServiceLocator();
        SecurityServiceWS verifyService;

            verifyService = sl.getSecurityService();
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Got a handle on service ...");
            }
            SecurityServicePort port =
                    verifyService.getSecurityServicePortPort();
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Got a handle on PORT ...");
            }
            response = port.changeForgottenPassword(request);

        if (response == null || response.getErrors() != null) {
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("changeForgottenPassword(UsernamePasswordDOBCredentials) - END");
			}
            return false;
        }

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("changeForgottenPassword(UsernamePasswordDOBCredentials) - END");
		}
        return true;
    }

    /**
     * Validate internal user.
     * 
     * @param username the username
     * @param loginTicket the login ticket
     * 
     * @return true, if validate internal user
     * 
     * @throws ServiceFaultException the service fault exception
     * 
     * @see com.virginmoneygiving.security.verify.custom.serviceproxy.AuthenticationProxy#validateInternalUser(java.lang.String,
     * java.lang.String)
     */
    public boolean validateInternalUser(String username, String loginTicket) throws ServiceFaultException {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("validateInternalUser() -  START");
		}

        boolean userValidated = false;
        InternalUserRequest request = new InternalUserRequest();
        request.setUsername(username);
        request.setLoginTicket(loginTicket);

        VerifyServiceLocator sl = new VerifyServiceLocator();
        SecurityServiceWS verifyService;

            verifyService = sl.getSecurityService();
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Got a handle on service ...");
            }
            SecurityServicePort port =
                    verifyService.getSecurityServicePortPort();
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Got a handle on PORT ...");
            }
            userValidated = port.validateInternalUserLoginTicket(request);
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("User validated?  -----  " + userValidated);
            }
            if (LOGGER.isTraceEnabled()) {
    			LOGGER.trace("validateInternalUser() -  END");
    		}

        return userValidated;
    }

}
