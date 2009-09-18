package com.virginmoneygiving.givingbatch.serviceproxy.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.Charity;
import com.virginmoneygiving.givingbatch.domain.Donor;
import com.virginmoneygiving.givingbatch.domain.Person;
import com.virginmoneygiving.givingbatch.serviceproxy.GiftAidClaimServiceProxy;
import com.virginmoneygiving.givingmanagement.service.messages.FetchCharityDetailsByIdRequest;
import com.virginmoneygiving.givingmanagement.service.messages.FetchCharityDetailsByIdResponse;
import com.virginmoneygiving.givingmanagement.service.messages.FetchCharityOfflineRegDataRequest;
import com.virginmoneygiving.givingmanagement.service.messages.FetchCharityOfflineRegDataResponse;
import com.virginmoneygiving.givingmanagement.service.messages.FetchDonorRequest;
import com.virginmoneygiving.givingmanagement.service.messages.FetchDonorResponse;
import com.virginmoneygiving.givingmanagement.service.messages.FetchPersonDetailsOfDonationRequest;
import com.virginmoneygiving.givingmanagement.service.messages.FetchPersonDetailsOfDonationResponse;
import com.virginmoneygiving.givingmanagement.service.messages.GivingManagementServiceFaultException;
import com.virginmoneygiving.givingmanagement.service.messages.MessageHeader;
import com.virginmoneygiving.givingmanagement.service.messages.ServiceCharityOffReg;
import com.virginmoneygiving.paymentmanagement.service.messages.CalculateTransitionalReliefRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.GenerateSequenceRequest;
import com.virginmoneygiving.paymentmanagement.service.messages.PaymentManagementServiceFaultException;

/**
 * Inherited from GiftAidClaimServiceProxy.
 * 
 * @author Srinivas Nageli
 * @author taruna
 */
public class GiftAidClaimServiceProxyImpl implements GiftAidClaimServiceProxy {

    /** The giving management service locator. */
    private GivingManagementServiceLocatorImpl givingManagementServiceLocator;

	/** The payment management service locator. */
	private PaymentManagementServiceLocatorImpl paymentManagementServiceLocator;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(GiftAidClaimServiceProxyImpl.class);

	/**
	 * Sets the giving management service locator.
	 * 
	 * @param givingManagementServiceLocator the giving management service locator
	 */
	public void setGivingManagementServiceLocator(
			GivingManagementServiceLocatorImpl givingManagementServiceLocator) {
		this.givingManagementServiceLocator = givingManagementServiceLocator;
	}

	/**
	 * Sets the payment management service locator.
	 * 
	 * @param paymentManagementServiceLocator the payment management service locator
	 */
	public void setPaymentManagementServiceLocator(
			PaymentManagementServiceLocatorImpl paymentManagementServiceLocator) {
		this.paymentManagementServiceLocator = paymentManagementServiceLocator;
	}

	/**
	 * {@inheritDoc}
	 * @throws  
	 * @throws PaymentManagementServiceFaultException 
	 */
	public BigDecimal calculateTransitionalRelief(BigDecimal giftAidAmount,
			String transactionType) throws PaymentManagementServiceFaultException  {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::calculateTransitionalRelief() - START");
        }

		final CalculateTransitionalReliefRequest calculateTransitionalReliefRequest = new CalculateTransitionalReliefRequest();
		calculateTransitionalReliefRequest.setHeader(Util.getPaymentHeader("giftAidClaimProcessJob"));
		calculateTransitionalReliefRequest.setGiftAidAmount(giftAidAmount);
		calculateTransitionalReliefRequest.setTransactionType(transactionType);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::calculateTransitionalRelief() - END");
        }
		return (BigDecimal) paymentManagementServiceLocator
				.getPaymentManagementPort().calculateTransitionalRelief(
						calculateTransitionalReliefRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	public Charity fetchCharityDetailsById(final Long id) throws GivingManagementServiceFaultException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::fetchCharityDetailsById() - START");
        }

		final FetchCharityDetailsByIdRequest serviceRequest = new FetchCharityDetailsByIdRequest();
		serviceRequest.setCharityId(id);
		serviceRequest.setHeader(Util.getGivingHeader("giftAidClaimProcessJob"));

		final FetchCharityDetailsByIdResponse fetchCharityDetailsByIdResponse = givingManagementServiceLocator
				.getGivingManagementPort().fetchCharityDetailsById(
						serviceRequest);

		final Charity charity = new Charity();
		charity.setCharityName(fetchCharityDetailsByIdResponse
				.getServiceCharity().getCharityName());
		charity.setCharityReference(fetchCharityDetailsByIdResponse
				.getServiceCharity().getCharityRegistrationNumber());

		XMLGregorianCalendar xmlGregorianCalender = (XMLGregorianCalendar) fetchCharityDetailsByIdResponse
				.getServiceCharity().getAccountingPeriodEndDate();

		charity
				.setAccountingPeriodEndDate(xmlGregorianCalender != null ? new Date(
						xmlGregorianCalender.toGregorianCalendar()
								.getTimeInMillis())
						: null);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::fetchCharityDetailsById() - END");
        }
		return charity;
	}

	/**
	 * {@inheritDoc}
	 */
	public String generateSequence(String transactionType, String referenceType)
			throws PaymentManagementServiceFaultException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::generateSequence() - START");
        }
		final GenerateSequenceRequest generateSequenceRequest = new GenerateSequenceRequest();
		generateSequenceRequest.setHeader(Util.getPaymentHeader("giftAidClaimProcessJob"));
		generateSequenceRequest.setTransactionType(transactionType);
		generateSequenceRequest.setReferenceType(referenceType);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::generateSequence() - END");
        }
		return paymentManagementServiceLocator.getPaymentManagementPort()
				.generateSequence(generateSequenceRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	public Donor fetchDonorDetails(Long donationId) throws GivingManagementServiceFaultException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::fetchDonorDetails() - START");
        }

		final FetchDonorRequest request = new FetchDonorRequest();
		request.setDonationId(donationId);
		request.setHeader(Util.getGivingHeader("giftAidClaimProcessJob"));
		final FetchDonorResponse response = givingManagementServiceLocator
				.getGivingManagementPort().fetchDonorDetails(request);

		final Donor donor = new Donor();
		donor.setSurname(response.getDonorDetails().getSurname());
		donor.setForename(response.getDonorDetails().getForename());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::fetchDonorDetails() - END");
        }

		return donor;
	}
	
	/**
     * {@inheritDoc}
     */
    public Person fetchPersonDetailsOfDonation(Long donationId) throws GivingManagementServiceFaultException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::fetchPersonDetailsOfDonation() - START");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("fetch person details Of Donation start.");
        }
        final FetchPersonDetailsOfDonationRequest request = new FetchPersonDetailsOfDonationRequest();
        request.setDonationId(donationId);
        request.setHeader(Util.getGivingHeader("giftAidClaimProcessJob"));
        final FetchPersonDetailsOfDonationResponse response = givingManagementServiceLocator
                .getGivingManagementPort().fetchPersonDetailsOfDonation(request);

        final Person person = new Person();
        person.setSurname(response.getPersonDetailsOfDonation().getSurname());
        person.setForename(response.getPersonDetailsOfDonation().getForename());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::fetchPersonDetailsOfDonation() - END");
        }

        return person;
    }


	/**
	 * {@inheritDoc}
	 */
	public String fetchVmgHMRCReference(Long charityId) throws GivingManagementServiceFaultException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::fetchVmgHMRCReference() - START");
        }	    
	    String vmgHmrcReference = null;
	    final FetchCharityOfflineRegDataRequest fetchCharityOfflineRegDataRequest = new FetchCharityOfflineRegDataRequest();
	    
	    fetchCharityOfflineRegDataRequest.setHeader(Util.getGivingHeader("giftAidClaimProcessJob"));
	    fetchCharityOfflineRegDataRequest.setCharityId(charityId);
	    final FetchCharityOfflineRegDataResponse fetchCharityOfflineRegDataResponse = givingManagementServiceLocator.getGivingManagementPort().fetchCharityOfflineRegData(fetchCharityOfflineRegDataRequest);
	    List<ServiceCharityOffReg> list = fetchCharityOfflineRegDataResponse.getServiceCharityOffRegList();
	    for (ServiceCharityOffReg serviceCharityOffReg : list) {
	        vmgHmrcReference = serviceCharityOffReg.getVmgHmrcRefNumber();
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimServiceProxyImpl::fetchVmgHMRCReference() - END");
        }
	    return vmgHmrcReference;
    }

}
