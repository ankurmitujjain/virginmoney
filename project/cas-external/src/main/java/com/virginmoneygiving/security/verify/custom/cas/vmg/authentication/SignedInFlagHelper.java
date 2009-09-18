package com.virginmoneygiving.security.verify.custom.cas.vmg.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;

public class SignedInFlagHelper {
	
	/** The name of the cookie that indicates if a user has signed in */
	public static final String SIGNED_IN_FLAG_COOKIE_NAME = "USER_FLAG";	
	/** The cookie path is set to the domain level */
	public static final String COOKIE_PATH = "/";
	/** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(SignedInFlagHelper.class);
    
    /**
     * Removes the cookie from the response that indicates that a user has signed in.
     * @param request
     * @param response
     */
	public void removeSignedInFlag(HttpServletRequest request, HttpServletResponse response){
		LOGGER.debug("SignedInFlagHelper removeSignedInFlag ...");
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i=0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (SIGNED_IN_FLAG_COOKIE_NAME.equals(cookie.getName())){
    				cookie.setMaxAge(0);
    				cookie.setValue("false");
    				cookie.setPath(COOKIE_PATH);
					response.addCookie(cookie);
					LOGGER.debug("SignedInFlagHelper removeSignedInFlag ... cookie removed");
				}
			}
		}	
	}
	
	/**
	 * Creates a cookie in the request indicating this user has signed in.
	 * 
	 * @param response
	 */
	public void setSignedInFlag(HttpServletResponse response){
		LOGGER.debug("SignedInFlagHelper setSignedInFlag");
		Cookie c = new Cookie(SIGNED_IN_FLAG_COOKIE_NAME,"true");
		c.setPath(COOKIE_PATH);		
		response.addCookie(c);
	}
	
	
}
