package com.virginmoneygiving.sugarcrmservice.helper;

import com.virginmoneygiving.sugarcrmservice.service.messages.VmgAddress;
import com.virginmoneygiving.sugarcrmservice.service.messages.VmgCharity;
import com.virginmoneygiving.sugarcrmservice.service.messages.VmgCharityContact;

/**
 * Helper class being used in all processor classes.
 * 
 * @author Vikas Kale
 * 
 */
public final class ProcessorHelper {

	/**
	 * Private constructor.
	 */
	private ProcessorHelper() {
		// Empty Constructor.
	}

	/**
	 * Converts object of {@link VmgCharity} to string.
	 * 
	 * @param vmgCharity
	 *            - object of {@link VmgCharity} to convert in string.
	 * @return vmgCharityString - String for VmgCharity object.
	 */
	public static String convertVmgCharityToString(VmgCharity vmgCharity) {
		final String tab = "    ";
		StringBuffer vmgCharityString = new StringBuffer();

        if (vmgCharity != null) {
			vmgCharityString.append("VmgCharity Details : ").append(tab)
					.append(" vmg_charity_id  = ").append(
							vmgCharity.getVmg_charity_id()).append(tab).append(
							" registered_charity_number = ").append(
							vmgCharity.getRegistered_charity_number()).append(
							tab).append(" name  = ").append(
							vmgCharity.getName()).append(tab).append(
							" charity_status_code  = ").append(
							vmgCharity.getCharity_status_code()).append(tab)
					.append(" main_telephone_number  = ").append(
							vmgCharity.getMain_telephone_number()).append(tab)
					.append(" main_email_address  = ").append(
							vmgCharity.getMain_email_address()).append(tab)
					.append(" online_income  = ").append(
							vmgCharity.getOnline_income()).append(tab).append(
							" fundraising_percentage  = ").append(
							vmgCharity.getFundraising_percentage()).append(tab)
					.append(" annual_income  = ").append(
							vmgCharity.getAnnual_income()).append(tab).append(
							" administration_address  = ").append(
							convertVmgCharityAddressToString(vmgCharity
									.getAdministration_address())).append(tab)
					.append(" registered_address  = ").append(
							convertVmgCharityAddressToString(vmgCharity
									.getRegistered_address())).append(tab);

		} else {
			vmgCharityString.append("VmgCharity object is null.");
		}
		return vmgCharityString.toString();
	}

	/**
	 * Converts object of {@link VmgCharityAddress} to string.
	 * 
	 * @param vmgAddress
	 *            - object of {@link VmgCharityAddress} to convert in string.
	 * @return vmgCharityAddressString - String for {@link VmgCharityAddress}
	 *         object.
	 */
	public static String convertVmgCharityAddressToString(VmgAddress vmgAddress) {
		final String tab = "    ";
		StringBuffer vmgCharityAddressString = new StringBuffer();
		if (vmgAddress != null) {
			vmgCharityAddressString.append("VmgCharityAddress Details : ")
					.append(tab).append(" address_line_1  = ").append(
							vmgAddress.getAddress_line_1()).append(tab).append(
							" address_line_2  = ").append(
							vmgAddress.getAddress_line_2()).append(tab).append(
							" town_city  = ").append(vmgAddress.getTown_city())
					.append(tab).append(" county_state  = ").append(
							vmgAddress.getCounty_state()).append(tab).append(
							" postcode  = ").append(vmgAddress.getPostcode())
					.append(tab);
		} else {
			vmgCharityAddressString.append("VmgAddress object is null.");
		}
		return vmgCharityAddressString.toString();
	}

	/**
	 * Converts object of {@link VmgCharityContact} to string.
	 * 
	 * @param vmgCharityContact
	 *            - object of {@link VmgCharityContact} to convert in string.
	 * @return vmgCharityContactString - String for {@link VmgCharityContact}
	 *         object.
	 */
	public static String convertVmgCharityContactToString(
			VmgCharityContact vmgCharityContact) {
		final String tab = "    ";
		StringBuffer vmgCharityContactsString = new StringBuffer();
		if (vmgCharityContact != null) {
			vmgCharityContactsString.append("VmgCharityContact Details : ")
					.append(tab).append(" title  = ").append(
							vmgCharityContact.getTitle()).append(tab).append(
							" forename  = ").append(
							vmgCharityContact.getForename()).append(tab)
					.append(" surname  = ").append(
							vmgCharityContact.getSurname()).append(tab).append(
							" occupation  = ").append(
							vmgCharityContact.getOccupation()).append(tab)
					.append(" email_address  = ").append(
							vmgCharityContact.getEmail_address()).append(tab)
					.append(" phone_work  = ").append(
							vmgCharityContact.getPhone_work()).append(tab)
					.append(" phone_other  = ").append(
							vmgCharityContact.getPhone_other()).append(tab)
					.append(" charity_id  = ").append(
							vmgCharityContact.getCharity_id()).append(tab)
					.append(" charity_administrator_id  = ").append(
							vmgCharityContact.getCharity_administrator_id())
					.append(tab).append(" address  = ").append(
							convertVmgCharityAddressToString(vmgCharityContact
									.getAddress()));
		} else {
			vmgCharityContactsString
					.append("VmgCharityContact object is null.");
		}
		return vmgCharityContactsString.toString();
	}
}
