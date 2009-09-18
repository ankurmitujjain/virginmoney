package com.virginmoneygiving.cardpayment.helper;

import com.logicgroup.extended.service.messages.ArrayOfProtocol;
import com.logicgroup.extended.service.messages.TransactionSecurityEnum;

/**
 * Helper class for the Logic Group Secure Payment proxy service impl. <br>
 * Provides:
 * <ul>
 * <li>response conversion for logging / persistence</li>
 * <li>etc.</li>
 * </ul>
 * TODO: constants for keys
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public interface LogicGroupSecurePaymentHelper {

    /**
     * Decide the protocol name and result for extended authorise.
     * 
     * @param scheme the scheme
     * @param mdStatus the md status
     * 
     * @return the array of protocol
     */
    public ArrayOfProtocol determineProtocolByMdStatus(String scheme, String mdStatus);

    /**
     * Determine the security mode.
     * 
     * @param scheme - not currently used as rules are same
     * @param mdStatus the md status
     * 
     * @return the transaction security enum
     */
    public TransactionSecurityEnum determineSecurityModeByMdStatus(String scheme, String mdStatus);

    /**
     * Determine whether this payment type is recurring.
     * 
     * @param paymentType the payment type
     * 
     * @return true, if determine recurring
     */
    public boolean determineRecurring(String paymentType);

}
