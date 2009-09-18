package com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.handler.support;

import org.apache.log4j.Logger;

import com.virginmoney.security.service.client.messages.ServiceFaultException;
import com.virginmoney.security.service.client.messages.ValidateLoginTicketResponse;
import com.virginmoneygiving.bamservice.client.event.ActivityEvent;
import com.virginmoneygiving.bamservice.client.event.BusinessActivityEventType;
import com.virginmoneygiving.bamservice.client.event.OriginatingService;
import com.virginmoneygiving.bamservice.client.event.OriginatingServiceSubsystem;
import com.virginmoneygiving.bamservice.client.service.BusinessActivityMonitoringService;
import com.virginmoneygiving.common.utility.UUIDGenerator;
import com.virginmoneygiving.security.verify.custom.business.AuthenticationDetails;
import com.virginmoneygiving.security.verify.custom.business.CASConstants;
import com.virginmoneygiving.security.verify.custom.business.PasswordDetails;
import com.virginmoneygiving.security.verify.custom.cas.vmg.authentication.principal.UsernamePasswordDOBCredentials;
import com.virginmoneygiving.security.verify.custom.serviceproxy.AuthenticationProxy;

/**
 * Virgin Money's implementation of a AuthenticationHandler that calls the
 * password validation process in the security service.
 * 
 * @author Ankur J
 * @vm.creation.date 01 May 2008
 * @vm.copyright.year 2008
 * @vm.modified.date 07 March 2009
 */
public final class PasswordAuthenticationHandler extends AbstractPasswordAuthenticationHandler {

	/** The Constant BLANK. */
	private static final String BLANK = "";

	/** Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(PasswordAuthenticationHandler.class);

	/**
	 * Default Constructor.
	 */
    public PasswordAuthenticationHandler() {
		LOGGER.warn(this.getClass().getName() + " is only to be used in a Virgin Money Giving (External users) environment.");
    }

    /** The authentication proxy. */
    private AuthenticationProxy authenticationProxy;

    /** Business Activity monitoring Service. */
    private BusinessActivityMonitoringService businessActivityMonitoringService;

    /**
     * Sets the authentication proxy.
     * 
     * @param authenticationProxy the new authentication proxy
     */
    public void setAuthenticationProxy(AuthenticationProxy authenticationProxy) {
		this.authenticationProxy = authenticationProxy;
    }


    /**
     * Sets the business activity monitoring service.
     * 
     * @param businessActivityMonitoringService the businessActivityMonitoringService to set
     */
    public void setBusinessActivityMonitoringService(
            BusinessActivityMonitoringService businessActivityMonitoringService) {
        this.businessActivityMonitoringService =
                businessActivityMonitoringService;
    }

    /**
     * method to authenticate users ID and Date of birth.
     * 
     * @param credentials Credentials to authenticate
     * 
     * @return Boolean Authentication result
     */
    public boolean authenticateUsernamePasswordInternal(final UsernamePasswordDOBCredentials credentials) {

        if(credentials.isSessionPassThru()) {
			return true;
        }

        String username = credentials.getUsername();

		final String dob = credentials.getDateOfBirth();

        final String validationAction = credentials.getValidationTarget();

        String ipAddress = credentials.getIpAddress();
        String sessionId = credentials.getSessionId();
        final Long userId = credentials.getUserId();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Sending Message header creation request (IP/Session): " + ipAddress + "/" + sessionId);
			LOGGER.debug("PasswordAuthenticationHandler user " + userId + " is being processed for action: " + validationAction);
		}
		
        boolean validated = false;

        try {

			if (validationAction == null || validationAction.equalsIgnoreCase("PASSWORD")) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Details: " +  username + "/" + dob);
				}

				AuthenticationDetails authDetails = new AuthenticationDetails();
                authDetails.setBrandRef(credentials.getBrandReference());
                authDetails.setDateOfBirth(dob);
                authDetails.setIpAddress(ipAddress);
                authDetails.setSessionId(sessionId);
                authDetails.setSystemTransactionId(UUIDGenerator.generateUUID());
                authDetails.setUserName(username);

                PasswordDetails pass1 = new PasswordDetails();
                PasswordDetails pass2 = new PasswordDetails();
                pass1.setPasswordCharacter(credentials.getPasswordCharacter1());
                pass1.setPasswordPosition(credentials.getPasswordLocation1());
                pass2.setPasswordCharacter(credentials.getPasswordCharacter2());
                pass2.setPasswordPosition(credentials.getPasswordLocation2());

                authDetails.getPassword().add(pass1);
                authDetails.getPassword().add(pass2);

                authDetails = authenticationProxy.validateVMGUserpassword(authDetails);
                // Log BAM event
                logAuthenticationResultEvent(authDetails);
                validated = authDetails.isValidated();
                credentials.setResponseTarget(authDetails.getResponseTarget());
                if (validated) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Login Validated. Unique ID/Ticket = " + authDetails.getUniqueReferenceId()
	                            + "/" + authDetails.getAccountId());
					}

					return true;
                } else {
                        credentials.setUsername(BLANK);
                        credentials.setDobDay(BLANK);
                        credentials.setDobMonth(BLANK);
                        credentials.setDobYear(BLANK);
                }

            }
            else if (validationAction != null && validationAction.equalsIgnoreCase("PASSTHRU")) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("PASSTHRU user/Ticket: " + credentials.getUsername() + "/" + credentials.getAccountId());
				}
                ValidateLoginTicketResponse response = authenticationProxy.validateLoginTicket(credentials);
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Got a Service Response ..." + response.isLoginTicketValidated());
				}
                validated = response.isLoginTicketValidated();
                if (validated) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Login Ticket Validated. ");
					}
                	if(response.getUserEmailDob() != null){
                		username = response.getUserEmailDob();
                	}
                    // change username to only email and no date of birth
                    String[] emailAndDob = username.split(",");
                    credentials.setUsername(emailAndDob[0]);
                    String[] date = emailAndDob[1].split("/");
                    credentials.setDobDay(date[0]);
                    credentials.setDobMonth(date[1]);
                    credentials.setDobYear(date[2]);
					return true;
                }
                else {
                    credentials.setUsername(BLANK);
                    credentials.setDobDay(BLANK);
                    credentials.setDobMonth(BLANK);
                    credentials.setDobYear(BLANK);
                }
            }
            else if (validationAction != null && validationAction.equalsIgnoreCase("INTERNALUSERPASSTHRU")) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("INTERNALUSERPASSTHRU user/Ticket: " + credentials.getUsername() + "/" + credentials.getAccountId());
				}
                boolean response = authenticationProxy.validateInternalUser(credentials.getUsername() ,credentials.getAccountId() );
                validated = response;
                if (validated) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Login Ticket Validated. ");
					}
                    // change username to only email and no date of birth
                    credentials.setUsername(username);
                    credentials.setDobDay(BLANK);
                    credentials.setDobMonth(BLANK);
                    credentials.setDobYear(BLANK);
					return true;
                }
                else {
                    credentials.setUsername(BLANK);
                    credentials.setDobDay(BLANK);
                    credentials.setDobMonth(BLANK);
                    credentials.setDobYear(BLANK);
                }
            						}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Got a Service Response. values = " + validated);
			}
        }
        catch (ServiceFaultException e) {
			LOGGER.error("Error in password verification for user account.", e);
			LOGGER.debug("Details: " +  username + "/" + dob);
            return false;
		}

        return false;
    }

    /**
     * Logs the event with the Business Activity Monitoring Service.
     * 
     * @param authDetails the authentication details.
     */
    private void logAuthenticationResultEvent(
            final AuthenticationDetails authDetails) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("logAuthenticationResultEvent() -  START");
		}

            if (CASConstants.PASSWORD_VALIDATION_FAILED.equals(authDetails
                    .getResponseTarget())) {
                businessActivityMonitoringService.logEvent(new ActivityEvent(
                        authDetails.getUserName(), authDetails.getIpAddress(),
                        authDetails.getSessionId(), authDetails
                                .getSystemTransactionId(), null, null,
                        BusinessActivityEventType.USER_LOGIN_FAILURE,
                        OriginatingService.GIVING,
                        OriginatingServiceSubsystem.CAS_EXTERNAL));
            }
            else if (CASConstants.PASSWORD_VALIDATION_OK.equals(authDetails
                    .getResponseTarget())) {
                businessActivityMonitoringService.logEvent(new ActivityEvent(
                        authDetails.getUserName(), authDetails.getIpAddress(),
                        authDetails.getSessionId(), authDetails
                                .getSystemTransactionId(), null, String
                                .valueOf(authDetails.getUserId()),
                        BusinessActivityEventType.USER_LOGIN_SUCCESS,
                        OriginatingService.GIVING,
                        OriginatingServiceSubsystem.CAS_EXTERNAL));
            }
            else if (CASConstants.PASSWORD_VALIDATION_TARGET_LOCKED_FAILED
                    .equals(authDetails.getResponseTarget())) {
                businessActivityMonitoringService.logEvent(new ActivityEvent(
                        authDetails.getUserName(), authDetails.getIpAddress(),
                        authDetails.getSessionId(), authDetails
                                .getSystemTransactionId(), null, String
                                .valueOf(authDetails.getUserId()),
                        BusinessActivityEventType.USER_ACCOUNT_LOCKED,
                        OriginatingService.GIVING,
                        OriginatingServiceSubsystem.CAS_EXTERNAL));

            }

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("logAuthenticationResultEvent(AuthenticationDetails) - END");
		}
    }
}
