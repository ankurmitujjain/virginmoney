package com.virginmoneygiving.sugarcrmservice.helper;

import com.virginmoneygiving.sugarcrmservice.client.messages.CharityRegistrationSugarCrmAlert;
import com.virginmoneygiving.sugarcrmservice.client.messages.ClientVmgAddress;
import com.virginmoneygiving.sugarcrmservice.client.messages.ClientVmgCharity;
import com.virginmoneygiving.sugarcrmservice.client.messages.ClientVmgCharityContact;
import com.virginmoneygiving.sugarcrmservice.service.messages.VmgAddress;
import com.virginmoneygiving.sugarcrmservice.service.messages.VmgCharity;
import com.virginmoneygiving.sugarcrmservice.service.messages.VmgCharityContact;

/**
 * Contains methods to map the data to service objects.
 * 
 * @author Vikas Kale
 *
 */
public class DataMappingHelper {

	/**
	 * Maps address details.
	 * 
	 * @param clientVmgAddress
	 *            object of
	 *            {@link ClientVmgAddress}
	 *            .
	 * @return mapped object of {@link VmgAddress}.
	 */
	public static VmgAddress mapAddress(ClientVmgAddress clientVmgAddress) {
		VmgAddress vmgAddress = new VmgAddress();
		if (clientVmgAddress != null) {
			vmgAddress.setAddress_line_1(clientVmgAddress.getAddressLine1());
			vmgAddress.setAddress_line_2(clientVmgAddress.getAddressLine2());
			vmgAddress.setCounty_state(clientVmgAddress.getCountyState());
			vmgAddress.setTown_city(clientVmgAddress.getTownCity());
			vmgAddress.setPostcode(clientVmgAddress.getPostcode());
		}
		return vmgAddress;
	}

	/**
	 * Maps Charity details.
	 * 
	 * @param clientVmgCharity
	 *            object of {@link CharityRegistrationSugarCrmAlert} .
	 * @return mapped object of {@link VmgCharity}.
	 */
	public static VmgCharity mapCharityDetails(
			final ClientVmgCharity clientVmgCharity) {
		VmgCharity vmgCharity = new VmgCharity();
		vmgCharity.setName(clientVmgCharity.getName());
		vmgCharity.setRegistered_charity_number(clientVmgCharity
				.getRegisteredCharityNumber());
		vmgCharity.setVmg_charity_id(clientVmgCharity
				.getVmgCharityId());
		vmgCharity.setCharity_status_code(clientVmgCharity
				.getCharityStatusCode());
		vmgCharity
				.setAdministration_address(mapAddress(clientVmgCharity
						.getAdministrationAddress()));
		vmgCharity.setRegistered_address(mapAddress(clientVmgCharity
				.getRegisteredAddress()));
		vmgCharity.setMain_telephone_number(clientVmgCharity.getMainTelephoneNumber());
		vmgCharity.setMain_email_address(clientVmgCharity.getMainEmailAddress());
		vmgCharity.setOnline_income(clientVmgCharity
				.getOnlineIncome());
		vmgCharity.setFundraising_percentage(clientVmgCharity
				.getFundraisingPercentage());
		vmgCharity.setAnnual_income(clientVmgCharity.getAnnualIncome());

		return vmgCharity;
	}

	/**
	 * Maps Charity Contact details.
	 * 
	 * @param clientVmgCharityContact
	 *            object of {@link CharityRegistrationSugarCrmAlert} .
	 * @return mapped object of {@link VmgCharityContact}.
	 */
	public static VmgCharityContact mapCharityContact(
			final ClientVmgCharityContact clientVmgCharityContact) {
		VmgCharityContact vmgCharityContact = new VmgCharityContact();
		
		vmgCharityContact.setCharity_administrator_id(clientVmgCharityContact
				.getCharityAdministratorId());
		vmgCharityContact.setCharity_id(clientVmgCharityContact
				.getCharityId());
		vmgCharityContact.setSurname(clientVmgCharityContact
				.getSurname());
		vmgCharityContact.setForename(clientVmgCharityContact
				.getForename());
		vmgCharityContact.setTitle(clientVmgCharityContact
				.getTitle());
		vmgCharityContact.setPhone_work(clientVmgCharityContact
				.getPhoneWork());
		vmgCharityContact.setPhone_other(clientVmgCharityContact
				.getPhoneOther());
		vmgCharityContact.setOccupation(clientVmgCharityContact
				.getOccupation());
		vmgCharityContact.setAddress(mapAddress(clientVmgCharityContact
				.getAddress()));
		vmgCharityContact.setEmail_address(clientVmgCharityContact
				.getEmailAddress());
		return vmgCharityContact;
	}
}
