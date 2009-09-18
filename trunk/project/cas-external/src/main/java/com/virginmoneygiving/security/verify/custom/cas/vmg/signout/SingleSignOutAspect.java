package com.virginmoneygiving.security.verify.custom.cas.vmg.signout;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.jasig.cas.authentication.principal.Service;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.registry.TicketRegistry;

import com.virginmoneygiving.security.verify.custom.cas.vmg.SingleSignOutSender;

/**
 * Intercepts requests to CAS for destroying TicketGrantingTickets TGT. This happens when a 
 * web application signs a user out and sends a signout request to CAS. 
 * The Service Ticket sent with the signout request is used to get the TGT and from this all 
 * the services this user has requested can be found. Each service has a Service Ticket (ST)
 * associated with it. 
 * 
 * <p>A Sign out request (JMS message is the only sender currently implemented) is created for 
 * each Service ticket. It is expected that each application participating in the Single Sign 
 * Out will listen for this message (JMS Broadcast) and destroy the users session. The normal 
 * CAS functionality then occurs.
 *
 * @author Phil Collins.
 *
 */
@Aspect
public class SingleSignOutAspect {

    /** logger instance. */
    private static final Logger LOGGER = Logger.getLogger(SingleSignOutAspect.class);

    /** Sender **/
    private SingleSignOutSender sender;

    /**
     * Store of the Ticket Granting tickets for services.
     */
    private TicketRegistry registry;

    /**
     * Sets the store for TGTs
     * @param registry
     */
    public void setTicketRegistry(TicketRegistry registry){
    	this.registry = registry;
    }
    /**
     * Creates a Signout request.
     * @param sender
     */
    public void setSingleSignOutSender(SingleSignOutSender sender){
		this.sender = sender;
	}

    //@Pointcut("execution(* org.jasig.cas.CentralAuthenticationServiceImpl.destroyTicketGrantingTicket(ticketId,..))")
    //@Pointcut("execution(* org.jasig.cas.CentralAuthenticationServiceImpl.destroyTicketGrantingTicket(..))")
    //@Pointcut("execution(org.jasig.cas.CentralAuthenticationServiceImpl destroyTicketGrantingTicket(ticketId,..))")
   // private void inCASTicketDestroy(String ticketId) {}

    //@Around("inCASTicketDestroy()")
    //public Object doBasicProfiling(ProceedingJoinPoint pjp, String ticketId) throws Throwable {

    /**
     * A point cut declaration.
     */
    @Pointcut("within(org.jasig.cas.*)")
    private void inCAS() {}

    @Pointcut("execution(void destroyTicketGrantingTicket(String))")
    private void inDestroyTicket() {}


    /** 
     * Intercepts requests defined by the pointcut.
     * Controlling method for retrieving all services for the users TGT and sending
     * a sign out message.
     * 
     * */
    //@Around("org.jasig.cas.CentralAuthenticationService.destroyTicketGrantingTicket(..)")
    @Around("inCAS() && inDestroyTicket()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
    	String ticketId = "";
    	ticketId = (String)pjp.getArgs()[0];    	
    	LOGGER.trace("SignOut for ticket " + ticketId);

    	TicketGrantingTicket ticket = (TicketGrantingTicket)registry.getTicket(ticketId);
    	
      if (ticket != null){

    	  LOGGER.trace("getting services for ticket " + ticketId);
    	  HashMap<String,Service> services = getServices(ticket);
          
          for(Map.Entry<String,Service> e : services.entrySet()){
        	  LOGGER.debug("sending logout request " + e.getKey() + " to " + e.getValue());    	  
        	  sender.sendSignOutMessage(e.getKey());
          }
      } else {
    	  LOGGER.trace("ticket is null");
      }
      //Carry on with normal application flow
      Object retVal = pjp.proceed();

      return retVal;
    }
    /**
     * Get this of registered services using reflection from the TGT.
     *
     * @param ticket
     * @return
     */
    private HashMap<String,Service> getServices(TicketGrantingTicket ticket){
    	HashMap<String,Service> services = null;
    	
    	Class c = ticket.getClass();
    	
    	try {
    		Field f = c.getDeclaredField("services");
    		
    		if (f != null){
  	    	  // f is a private field
  	    	  f.setAccessible(true);
  	    	  LOGGER.trace("SignOut AOP : getting services");    	    
  	    	  //Get the services
  	    	  services = (HashMap<String,Service>)(f.get(ticket));  	    	  
    		}    	    
		} catch (SecurityException e) {
			LOGGER.error("Single Sign Out has failed: unable to access the class with reflection.", e);
		} catch (NoSuchFieldException e) {
			LOGGER.error("Single Sign Out has failed. No field: services found in the ticket for " + ticket.getId() , e);
		} catch (IllegalAccessException e) {
			LOGGER.error("Single Sign Out has failed: unable to access the field with reflection.", e);
		}	
		
		return services;
    }


}
