package com.virginmoneygiving.givingbatch.processor;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.domain.Address;
import com.virginmoneygiving.giving.domain.BankAccount;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.CharityAddress;
import com.virginmoneygiving.giving.domain.CharityStatus;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;

/**
 * This class is an Item Processor for CharityRegistrationAmendSupplier. This
 * class takes the object returned by the ItemReader creates the CharityRecord,
 * update the batch status in database and pass the CharityRecord to ItemWriter
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class CharityRegistrationAmendSupplierProcessor implements
		ItemProcessor<Charity, Charity> {

	/** instance for logger. * */
	private static Logger LOGGER = Logger
			.getLogger(CharityRegistrationAmendSupplierProcessor.class);

	/** giving Service. * */
	private GivingService givingService;

    /** {@inheritDoc} */
    public Charity process(Charity charity) throws GivingBatchException {
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("CharityRegistrationAmendSupplierProcessor.process (Flag version)- Start");
    
            LOGGER.debug("##################### V2:Amend supplier record: Charity Id#################");
            LOGGER.debug("Charity Id :" + charity.getId());
            LOGGER.debug("##################### V2:Amend supplier record: Charity Id#################");
        }
        Charity amendedSupplierRecord = new Charity();
        amendedSupplierRecord.setId(charity.getId());
        amendedSupplierRecord.setCharityStatus(charity.getCharityStatus());
        amendedSupplierRecord.setName(charity.getName());
        amendedSupplierRecord.setRegisteredCharityNumber(charity.getRegisteredCharityNumber());

        Set<CharityAddress> addressList = charity.getCharityAddresses();
        if (addressList != null && addressList.size() > 0) {
            Set<CharityAddress> updatedAddressSet = new HashSet<CharityAddress>();
            for (CharityAddress address : addressList) {
                Address addressAudit = address.getAddress();
                if (LOGGER.isDebugEnabled()){
                    LOGGER.debug("##################### V2:Amend supplier record: Address Id#################");
                    LOGGER.debug("Charity Id :" + charity.getId() + "Address Id :" + address.getAddress().getId());
                    LOGGER.debug("##################### V2:Amend supplier record: Address Id#################");
                }
                if (addressAudit.getAddressUpdateInd() != null
                        && addressAudit.getAddressUpdateInd().equalsIgnoreCase("Y")) {
                    if(LOGGER.isDebugEnabled()){
                        LOGGER.debug(" Sending Address Id :" + address.getAddress().getId() + " to GL");
                    }
                    if (addressAudit.getAddressLine1() != null) {
                        address.getAddress().setAddressLine1(addressAudit.getAddressLine1());
                    }
                    if (addressAudit.getAddressLine2() != null) {
                        address.getAddress().setAddressLine2(addressAudit.getAddressLine2());
                    }
                    if (addressAudit.getTownCity() != null) {
                        address.getAddress().setTownCity(addressAudit.getTownCity());
                    }
                    if (addressAudit.getCountry() != null) {
                        address.getAddress().getCountry().setIsoCode(
                                addressAudit.getCountry().getIsoCode());
                    }
                    if (addressAudit.getPostCode() != null) {
                        address.getAddress().setPostCode(addressAudit.getPostCode());
                    }
                    updatedAddressSet.add(address);
                }
                amendedSupplierRecord.setCharityAddresses(updatedAddressSet);
            }

        }
        Set<BankAccount> bankAccounts = charity.getBankAccounts();

        if (bankAccounts != null && bankAccounts.size() > 0) {
            Set<BankAccount> updatedBankAccounts = new HashSet<BankAccount>();

            for (BankAccount bankAccount : bankAccounts) {
                if(LOGGER.isDebugEnabled()){
                    LOGGER.debug("##################### V2:Amend supplier record: Bank Acc Id#################");
                    LOGGER.debug("Charity Id :" + charity.getId() + "BankAcc Id :" + bankAccount.getId());
                    LOGGER.debug("##################### V2:Amend supplier record: Bank Acc Id#################");
                }
                if (bankAccount.getBankAccountUpdateInd() != null
                        && bankAccount.getBankAccountUpdateInd().equalsIgnoreCase("Y")) {
                    if(LOGGER.isDebugEnabled()){
                        LOGGER.debug("Sending BankAcc Id :" + bankAccount.getId() + " to GL");
                    }
                    if (bankAccount.getAccountName() != null) {
                        bankAccount.setAccountName(bankAccount.getAccountName());
                    }
                    if (bankAccount.getAccountNumber() != null) {
                        bankAccount.setAccountNumber(bankAccount.getAccountNumber());
                    }
                    if (bankAccount.getSortCode() != null) {
                        bankAccount.setSortCode(bankAccount.getSortCode());
                    }

                    updatedBankAccounts.add(bankAccount);
                }
                amendedSupplierRecord.setBankAccounts(updatedBankAccounts);
            }

        }

        updateBatchStatus(charity.getId(),
                MasterDataCodeConstants.LIV_PENDING);
        LOGGER.debug("CharityRegistrationAmendSupplierProcessor.process (Flag version) - End");
        return amendedSupplierRecord;
    }

    /**
     * HunarC: This is the version prior to changes related to the Amendment Flag.
     * This version depends on retrieving details from the Audit table and passing those on to
     * the GL system. However, this is not correct. The Fetch****Audit methods retrieve based on revision
     * numbers, which in turn are based on system date (See JPACharityDAOImpl.java in giving-db).
     * Hence we'll never get the correct records because (a) the batch is run the next day and (b) this will
     * not cater for changes over 1 day old. hence this method has been replaced by the PROCESS method above.
     * 
     * @param charity the charity
     * 
     * @return the charity
     * 
     * @throws Exception the exception
     */
    // Changing the method name as per Naming convention : TarunA
/*    private Charity processPreFlagVersion(Charity charity) throws GivingBatchException {
        if (LOGGER.isTraceEnabled()){
    		LOGGER
    				.trace("CharityRegistrationAmendSupplierProcessor.process - Start");
        }
        if (LOGGER.isDebugEnabled()){
    		LOGGER
    				.debug("#####################Amend supplier record: Charity Id#################");
    		LOGGER.debug("Charity Id :" + charity.getId());
    		LOGGER
    				.debug("#####################Amend supplier record: Charity Id#################");
        }
		Charity amendedSupplierRecord = new Charity();
		amendedSupplierRecord.setId(charity.getId());
		Charity auditedCharity = givingService.fetchCharityAudit(charity
				.getId());
		if (auditedCharity != null) {
			if (auditedCharity.getCharityStatus() != null) {
				CharityStatus newCharityStatus = new CharityStatus();
				newCharityStatus.setCode(auditedCharity.getCharityStatus()
						.getCode());
				// charity.setCharityStatus(newCharityStatus);
				amendedSupplierRecord.setCharityStatus(newCharityStatus);
				amendedSupplierRecord.setName(charity.getName());
				amendedSupplierRecord.setRegisteredCharityNumber(charity
						.getRegisteredCharityNumber());

			} else if (auditedCharity.getName() != null) {
				amendedSupplierRecord.setName(auditedCharity.getName());
			}
		}
		Set<CharityAddress> addressList = charity.getCharityAddresses();
		if (addressList != null && addressList.size() > 0) {
			Set<CharityAddress> updatedAddressList = new HashSet<CharityAddress>();
			for (CharityAddress address : addressList) {
				Address addressAudit = givingService.fetchAddressAudit(address.getAddress()
						.getId());
				if(LOGGER.isDebugEnabled()){
    				LOGGER
    						.debug("#####################Amend supplier record: Address Id#################");
    				LOGGER.debug("Charity Id :" + charity.getId() + "Address Id :"
    						+ address.getAddress().getId());
    				LOGGER
    						.debug("#####################Amend supplier record: Address Id#################");
				}
				if (addressAudit != null) {

					amendedSupplierRecord.setCharityStatus(charity
							.getCharityStatus());
					amendedSupplierRecord.setName(charity.getName());
					amendedSupplierRecord.setRegisteredCharityNumber(charity
							.getRegisteredCharityNumber());
					Set<CharityAddress> updatedAddressSet = new HashSet<CharityAddress>();

					if (addressAudit.getAddressLine1() != null) {
						address.getAddress().setAddressLine1(addressAudit.getAddressLine1());
					}
					if (addressAudit.getAddressLine2() != null) {
						address.getAddress().setAddressLine2(addressAudit.getAddressLine2());
					}
					if (addressAudit.getTownCity() != null) {
						address.getAddress().setTownCity(addressAudit.getTownCity());
					}
					if (addressAudit.getCountry() != null) {
						address.getAddress().getCountry().setIsoCode(
								addressAudit.getCountry().getIsoCode());
					}
					if (addressAudit.getPostCode() != null) {
						address.getAddress().setPostCode(addressAudit.getPostCode());
					}
					updatedAddressSet.add(address);
					amendedSupplierRecord
							.setCharityAddresses(updatedAddressSet);

				}

			}

		}
		Set<BankAccount> bankAccounts = charity.getBankAccounts();

		if (bankAccounts != null && bankAccounts.size() > 0) {
			Set<BankAccount> updatedBankAccounts = new HashSet<BankAccount>();

			for (BankAccount bankAccount : bankAccounts) {
				BankAccount account = givingService
						.fetchBankAccountAudit(bankAccount.getId());
				if(LOGGER.isDebugEnabled()){
    				LOGGER
    						.debug("#####################Amend supplier record: Bank Acc Id#################");
    				LOGGER.debug("Charity Id :" + charity.getId() + "BankAcc Id :"
    						+ bankAccount.getId());
    				LOGGER
    						.debug("#####################Amend supplier record: Bank Acc Id#################");
				}
				if (account != null) {
					amendedSupplierRecord.setCharityStatus(charity
							.getCharityStatus());
					amendedSupplierRecord.setName(charity.getName());
					amendedSupplierRecord.setRegisteredCharityNumber(charity
							.getRegisteredCharityNumber());

					if (account.getAccountName() != null) {
						bankAccount.setAccountName(account.getAccountName());
					}
					if (account.getAccountNumber() != null) {
						bankAccount
								.setAccountNumber(account.getAccountNumber());
					}
					if (account.getSortCode() != null) {
						bankAccount.setSortCode(account.getSortCode());
					}

					updatedBankAccounts.add(bankAccount);
				}
				amendedSupplierRecord.setBankAccounts(updatedBankAccounts);
			}

		}

		updateBatchStatus(charity.getId(),
				MasterDataCodeConstants.LIV_PENDING);
		LOGGER.trace("CharityRegistrationAmendSupplierProcessor.process - End");
		return amendedSupplierRecord;
	}*/

	/**
	 * The updateBatchStatus method calls givingService and updates the
	 * batch status to 'LIV_PENDING'in the database in DB.
	 * 
	 * @param id of Charity
	 * @param status the status
	 */
	public void updateBatchStatus(Long id, String status) {
	    if (LOGGER.isTraceEnabled()){
		LOGGER
				.trace("CharityRegistrationAmendSupplierProcessor.updateBatchStatus - Start");
	    }
		givingService.updateBatchStatus(id, status);
		if (LOGGER.isTraceEnabled()){
		LOGGER
				.trace("CharityRegistrationAmendSupplierProcessor.updateBatchStatus - End");
		}
	}

	/**
	 * Sets the giving service.
	 * 
	 * @param givingService the giving service
	 */
	public void setGivingService(GivingService givingService) {
		this.givingService = givingService;

	}

}
