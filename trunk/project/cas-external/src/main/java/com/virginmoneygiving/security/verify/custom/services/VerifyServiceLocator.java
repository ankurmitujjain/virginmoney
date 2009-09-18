/**
 * Used to locate Security services. This abstracts how to find a suitable service at runtime. The services found will be
 * on a server suitable to where this class is deployed provided the system properties set in the config file are correct.
 *
 * @author choprah
 * @author $Author$
 * @version $Version$
 * @vm.creation.date 01 May 2008
 * @vm.copyright.year 2008
 */
package com.virginmoneygiving.security.verify.custom.services;

import com.virginmoney.security.service.client.messages.SecurityServiceWS;

/**
 * Used to locate Security services. This abstracts how to find a suitable service at runtime. The services found will be
 * on a server suitable to where this class is deployed provided the system properties set in the config file are correct.
 *
 * @author HunarC
 */
public class VerifyServiceLocator extends AbstractWebServiceLocator {

    /**
     * Locates the Security Service.
     * 
     * @return Sercurity Service
     */
    public SecurityServiceWS getSecurityService() {
        return (SecurityServiceWS) getWebService("securityservice");
    }

}
