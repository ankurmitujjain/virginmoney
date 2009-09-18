package com.virginmoneygiving.integrationtests.webdriver.charity;


/**
 * Model class used to hold the mock data required during webdriver test case executions.
 * @author jallen
 */
public class CharityRegistrationDetails {
  
	
	/** Holds information for trustee details. */
    public class TrusteeDetails {
        public String title;        
		public String firstName;
        public String lastName;
        public String dobDay;
        public String dobMonth;
        public String dobYear;
        public String countryCode;
        public String addressLine1;
        public String addressLine2;
        public String town;
        public String county;
        public String postCode;
        
        public void setTitle(String title) {
			this.title = title;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public void setDobDay(String dobDay) {
			this.dobDay = dobDay;
		}
		public void setDobMonth(String dobMonth) {
			this.dobMonth = dobMonth;
		}
		public void setDobYear(String dobYear) {
			this.dobYear = dobYear;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		public void setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
		}
		public void setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
		}
		public void setTown(String town) {
			this.town = town;
		}
		public void setCounty(String county) {
			this.county = county;
		}
		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}
    }
    
    /** Holds information for charity details. */
    public class CharityDetails {
		public String name;    
		public String registrationNumber;
        public String addressLine1;
        public String addressLine2;
        public String town;
        public String county;
        public String postCode;
        public String[] categories;
        public String description;
        public String commonName;
        public String abbreviations;
        public String accountingEndDateDay;
        public String accountingEndDateMonth;
        public String accountingEndDateYear;
        public String logoPath;
        
        public String getAbbreviations() {
			return abbreviations;
		}
		public void setAbbreviations(String abbreviations) {
			this.abbreviations = abbreviations;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setRegistrationNumber(String registrationNumber) {
			this.registrationNumber = registrationNumber;
		}
		public void setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
		}
		public void setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
		}
		public void setTown(String town) {
			this.town = town;
		}
		public void setCounty(String county) {
			this.county = county;
		}
		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}
		public void setCategories(String[] categories) {
			this.categories = categories;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public void setCommonName(String commonName) {
			this.commonName = commonName;
		}
		public void setAccountingEndDateDay(String accountingEndDateDay) {
			this.accountingEndDateDay = accountingEndDateDay;
		}
		public void setAccountingEndDateMonth(String accountingEndDateMonth) {
			this.accountingEndDateMonth = accountingEndDateMonth;
		}
		public void setAccountingEndDateYear(String accountingEndDateYear) {
			this.accountingEndDateYear = accountingEndDateYear;
		}
		public void setLogoPath(String logoPath) {
			this.logoPath = logoPath;
		}

    }
    
    /** Holds information for charity administrator details. */
    public class AdministratorDetails {        
		public String title;
        public String firstName;
        public String lastName;
        public String occupation;
        public String telephoneNumber;
        public String email;
        public boolean acceptToC;
        
        public void setTitle(String title) {
			this.title = title;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		public void setTelephoneNumber(String telephoneNumber) {
			this.telephoneNumber = telephoneNumber;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public void setAcceptToC(boolean acceptToC) {
			this.acceptToC = acceptToC;
		}
    }

    /** Holds information for charity administrator security details. */
    public class SecurityDetails {
		public String dobDay;
        public String dobMonth;
        public String dobYear;
        public String password;
        
        public void setDobDay(String dobDay) {
			this.dobDay = dobDay;
		}
		public void setDobMonth(String dobMonth) {
			this.dobMonth = dobMonth;
		}
		public void setDobYear(String dobYear) {
			this.dobYear = dobYear;
		}
		public void setPassword(String password) {
			this.password = password;
		}
    }
    
    /** CharityDetails instance. */
    public CharityDetails charityDetails = new CharityDetails();
	/** AdministratorDetails instance. */
    public AdministratorDetails administratorDetails = new AdministratorDetails();
	/** SecurityDetails instance. */
    public SecurityDetails securityDetails = new SecurityDetails();
    /** TrusteeDetails instance. */
    public TrusteeDetails trusteeDetails = new TrusteeDetails();
	public CharityDetails getCharityDetails() {
		return charityDetails;
	}
	public void setCharityDetails(CharityDetails charityDetails) {
		this.charityDetails = charityDetails;
	}
	public AdministratorDetails getAdministratorDetails() {
		return administratorDetails;
	}
	public void setAdministratorDetails(AdministratorDetails administratorDetails) {
		this.administratorDetails = administratorDetails;
	}
	public SecurityDetails getSecurityDetails() {
		return securityDetails;
	}
	public void setSecurityDetails(SecurityDetails securityDetails) {
		this.securityDetails = securityDetails;
	}
	public TrusteeDetails getTrusteeDetails() {
		return trusteeDetails;
	}
	public void setTrusteeDetails(TrusteeDetails trusteeDetails) {
		this.trusteeDetails = trusteeDetails;
	}    

}
