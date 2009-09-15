package com.virginmoneygiving.ldap.util;

import javax.naming.directory.Attributes;

import org.apache.log4j.Logger;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.ObjectRetrievalException;

import com.virginmoneygiving.ldap.domain.DomainPolicyAttributes;

/**
 * This class is used to load the active directory domain policy attributes at
 * application start up.
 * @author vishals
 */
public final class DomainPolicyAttributeMapper {

    /** Logger instance for class. */
    private static final Logger LOGGER = Logger.getLogger(DomainPolicyAttributeMapper.class);
    
    /** Context source instance. */
    private ContextSource contextSource;
    
    /** Domain attributes instance. */
    private DomainPolicyAttributes domainPolicyAttributes;

    /**
     * Method used to load the {@link DomainPolicyAttributes} instance from active directory.
     * <p/>
     * NOTE:This method is configured to execute at start up using spring configuration.
     */
    public void init() {
    	if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("DomainPolicyAttributeMapper :: init() -  START");
		}

        try {

            final Attributes directoryAttributes = contextSource.getReadOnlyContext().getAttributes("");
            //Instantiate DomainPolicyAttributes
            domainPolicyAttributes = new DomainPolicyAttributes();

            String lockoutDuraton =
                LdapUtil.getStringRepresentationForAttributeObject(directoryAttributes.get(LdapConstants.LOCKOUT_DURATION));
            String lockoutThreshold =
                LdapUtil.getStringRepresentationForAttributeObject(directoryAttributes.get(LdapConstants.LOCKOUT_THRESHOLD));
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(" Domain level lockoutDuraton :: " + lockoutDuraton);
            LOGGER.debug(" Domain level lockoutThreshold :: " + lockoutThreshold);
            }
            if (!lockoutDuraton.equals("")) {
                domainPolicyAttributes.setLockoutDuration(Long.parseLong(lockoutDuraton));
            }

            if (!lockoutThreshold.equals("")) {
                domainPolicyAttributes.setLockoutThreshold(Long.parseLong(lockoutThreshold));
            }

        }
        catch (javax.naming.NamingException namingException) {
            LOGGER.error("Naming Exception thrown while performing directory operation in DomainPolicyAttributeMapper : init method.",
                            namingException);
            throw new ObjectRetrievalException("Error ocured in DomainPolicyAttributeMapper : init method.",
                    namingException);
        }

        if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("DomainPolicyAttributeMapper :: init() -  START");
		}
    }

    /**
     * Setter for DomainPolicyAttributes.
     * @return DomainPolicyAttributes instance.
     */
    public DomainPolicyAttributes getDomainPolicyAttributes() {
        return domainPolicyAttributes;
    }

    /**
     * Setter for ContextSource.
     * @param contextSource directory context source.
     */
    public void setContextSource(ContextSource contextSource) {
        this.contextSource = contextSource;
    }

}
