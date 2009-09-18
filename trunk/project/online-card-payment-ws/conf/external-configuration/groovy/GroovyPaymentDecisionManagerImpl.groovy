package com.virginmoneygiving.cardpayment.service.impl

import org.apache.log4j.*

import com.virginmoneygiving.cardpayment.business.AuthenticateActionEnum
import com.virginmoneygiving.cardpayment.business.AuthoriseActionEnum
import com.virginmoneygiving.cardpayment.service.PaymentDecisionManager

/**
 * Groovy script for payment decision management
 * 
 * @author Robin Bramley, Opsera Ltd.
 *
 */
public class GroovyPaymentDecisionManagerImpl implements PaymentDecisionManager {

     Logger logger = Logger.getInstance(GroovyPaymentDecisionManagerImpl.class)
             
	/**
	 * Governs 3D Secure usage by provider and payment type.
	 * Non-existence will default to false (3D Secure not required).
	 * 
	 * Providers: 
	 *	V = Visa, X = Visa Debit (Delta), E = visa Electron
	 *  M = Mastercard, S = Maestro (was Switch), O = maestrO international, L = soLo 
	 *
	 * Providers who do not support 3D Secure
	 *  A = American Express (Amex)
	 *
	 * Not accepted providers:
	 *  D = Diners  
	 *  
	 * Payment types (3D Secure should always be false for REGULAR (batch)):
	 *  EVENT_FEE, PAYMENT, REGISTRATION_FEE, REGULAR, REGULAR_INITIAL, REGULAR_UPDATE
	 * 
	 */
	def providerAuthMaps = [
	            V:[EVENT_FEE:true,PAYMENT:true,REGISTRATION_FEE:false, REGULAR:false, REGULAR_INITIAL:true, REGULAR_UPDATE:true],
	            X:[EVENT_FEE:true,PAYMENT:true,REGISTRATION_FEE:false, REGULAR:false, REGULAR_INITIAL:true, REGULAR_UPDATE:true],
	            E:[EVENT_FEE:true,PAYMENT:true,REGISTRATION_FEE:false, REGULAR:false, REGULAR_INITIAL:true, REGULAR_UPDATE:true],
	            M:[EVENT_FEE:true,PAYMENT:true,REGISTRATION_FEE:false, REGULAR:false, REGULAR_INITIAL:true, REGULAR_UPDATE:true],
	            S:[EVENT_FEE:true,PAYMENT:true,REGISTRATION_FEE:false, REGULAR:false, REGULAR_INITIAL:true, REGULAR_UPDATE:true],
	            O:[EVENT_FEE:true,PAYMENT:true,REGISTRATION_FEE:false, REGULAR:false, REGULAR_INITIAL:true, REGULAR_UPDATE:true],
	            L:[EVENT_FEE:true,PAYMENT:true,REGISTRATION_FEE:false, REGULAR:false, REGULAR_INITIAL:true, REGULAR_UPDATE:true]
			]

	
	/*
	 * MdStatus values:
	 * 0 Authentication failed
	 * 1 Fully authenticated
	 * 2 Card not participating
	 * 3 Card range not in cache (no longer used by the card schemes)
	 * 4 Card authentication attempted
	 * 5 Authentication not available
	 * 6 3-D Secure error
	 * 7 System error (MPI)
	 * 8 Unknown card scheme
	 */
	def permittedMdStatusList = ['1','2','4']
	
	/* Determine next action for authorisation responses (though this may get
	 * overruled by AVS/CV2 checking).
	 *
	 * Possible actions: COMPLETE, CANCEL, NONE
	 *
	 * Authorise response code values:
	 * 0 = validated 
	 * 1 = terminal 
	 * 2 = on-line 
	 * 3 = manual 
	 * 4 = declined 
	 * 5 = cancelled 
	 * 6 = unable_to_cancel 
	 * 7 = get_manual_auth 
	 * 8 = get_sig_auth 
	 * 9 = telephone 
	 * 10 = get_manual_auth_re-submit 
	 * 11 = hot_card_decline
	 * R = GetManualAuthorisationAndResubmit
	 *
	 * 5/6 shouldn't happen
	 *
	 * Unmapped codes will be defaulted to CANCEL by the code 
	 * - if this causes a timeout - explicitly map it to NONE.
	 */
	def authoriseResponseMap = [
	        '0':AuthoriseActionEnum.COMPLETE,
	        '1':AuthoriseActionEnum.CANCEL,
	        '2':AuthoriseActionEnum.COMPLETE,
	        '3':AuthoriseActionEnum.CANCEL,
	        '4':AuthoriseActionEnum.NONE,
	        '5':AuthoriseActionEnum.NONE,
	        '6':AuthoriseActionEnum.NONE,
	        '7':AuthoriseActionEnum.CANCEL,
	        '8':AuthoriseActionEnum.COMPLETE,
	        '9':AuthoriseActionEnum.CANCEL,
	        '10':AuthoriseActionEnum.NONE,
	        '11':AuthoriseActionEnum.NONE,
	        'R':AuthoriseActionEnum.NONE
	    ]

	/* 
	 * Cancel response code values:
	 * 5 = cancelled 
	 * 6 = unable_to_cancel 
	 */
	def permittedCancelResponseCodeList = ['5']

	// next two govern authentication Opal error code processing

	/*
	 * Normal codes 
	 */
	def authoriseWith3DList = ['0']

	/*
	 * 3D Secure not applicable for this transaction
	 */
	def authoriseWithout3DList = ['6112','6113','6114','6115','6116','6117','6118','6119','6120','6121','6122','6123','6124']

	
	/*
	 * Governs decisions on CV2 - no longer uses auth checker as it is irrelevant.
	 * All AVS house number digits are now accepted so this is excluded from the map. 
	 *
	 * Response values (all 3): no_information, not_checked, matched, not_matched
	 */
	def cv2AvsDecisionMap = [
     ([cv2Response:'matched',avsPostCodeResponse:'matched']):true,
     ([cv2Response:'matched',avsPostCodeResponse:'not_matched']):true,
     ([cv2Response:'matched',avsPostCodeResponse:'not_checked']):true,
     ([cv2Response:'matched',avsPostCodeResponse:'no_information']):true,
     ([cv2Response:'no_information',avsPostCodeResponse:'no_information']):true
    ]

	
	/* (non-Javadoc)
	 * @see com.virginmoneygiving.cardpayment.service.PaymentDecisionManager#isAuthenticationRequired(java.lang.String, java.lang.String)
	 */
	public boolean isAuthenticationRequired(String cardProvider, String paymentType){
		if(providerAuthMaps.get(cardProvider)) {
		    logger.debug("Found authentication map entry for card provider ${cardProvider}")
			def typeAuthMap = providerAuthMaps.get(cardProvider)
		    if(typeAuthMap.get(paymentType)) {
		    	return typeAuthMap.get(paymentType)
		    }
		}

		return false
	}
	 
	/* (non-Javadoc)
	 * @see com.virginmoneygiving.cardpayment.service.PaymentDecisionManager#assessAuthenticationResponseCode(java.lang.String)
	 */
	public AuthenticateActionEnum assessAuthenticationResponseCode(String responseCode) {
		if (authoriseWith3DList.contains(responseCode.trim())) {
		    return AuthenticateActionEnum.AUTH_3D
		} else if (authoriseWithout3DList.contains(responseCode.trim())) {
		    return AuthenticateActionEnum.AUTH_NON_3D
		} 

		// not configured so must mean a true error
		return AuthenticateActionEnum.ERROR
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.cardpayment.service.PaymentDecisionManager#assessAuthenticationResponse(java.lang.String)
	 */
	public boolean assessAuthenticationResponse(String mdStatus){
		return permittedMdStatusList.contains(mdStatus)
	}
	
	/* (non-Javadoc)
	 * @see com.virginmoneygiving.cardpayment.service.PaymentDecisionManager#assessAuthoriseResponse(String)
	 */
	public boolean assessAuthoriseResponse(String statusCode) {
	    def rcAction = authoriseResponseMap.get(statusCode.trim())
	    if (rcAction == null) {
		    logger.warn("No action for TLG statusCode ${statusCode}")
	        return false
	    } else {
	        return rcAction.equals(AuthoriseActionEnum.COMPLETE)
	    }
	}
	
	/* (non-Javadoc)
	 * @see com.virginmoneygiving.cardpayment.service.PaymentDecisionManager#assessAuthoriseResponse(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public AuthoriseActionEnum assessAuthoriseResponse(String statusCode, String authChecker, String cv2Response, String avsAddressResponse, String avsPostCodeResponse){
	    def rcAction = authoriseResponseMap.get(statusCode.trim())
	    
	    if (rcAction == null) {
		    logger.warn("No action for TLG statusCode ${statusCode}")
	        // default is cancel
	        return AuthoriseActionEnum.CANCEL
	    } else if (rcAction.equals(AuthoriseActionEnum.COMPLETE)) {
			// build a map for key lookup on cv2AvsDecisionMap
			def cv2Avs = ['cv2Response':cv2Response,'avsPostCodeResponse':avsPostCodeResponse]
			
			if (cv2AvsDecisionMap.get(cv2Avs)) {
			    logger.debug('cv2Avs check passed...')
			    return rcAction
			} else {
			    logger.debug('cv2Avs check failed...')
			    return AuthoriseActionEnum.CANCEL
			}
		}
		
		// if in doubt just hand back
		return rcAction
	}
	
	public boolean assessValidateResponse(String statusCode, String validationPassed) {
		return (statusCode.trim().equals('0') && validationPassed.toUpperCase().equals('YES'))
	}
	
	public boolean assessCancelResponse(String statusCode) {
		return permittedCancelResponseCodeList.contains(statusCode.trim())
	}
}
