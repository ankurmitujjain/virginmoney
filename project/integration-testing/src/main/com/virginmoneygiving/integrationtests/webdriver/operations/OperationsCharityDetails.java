/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.operations;

/**
 * @author saurabhh
 *
 */
public class OperationsCharityDetails {
	
public class BankAccountDetails{

/** Part 1 of sort code*/	
private String sortCodePart1;

/** Part 2 of sort code*/	
private String sortCodePart2;

/** Part 3 of sort code*/	
private String sortCodePart3;

/** Account number*/	
private String accountNumber;

/**Account name */
private String accountName;

/**Bank name */
private String bankName;

/** Bank address Line 1 */
private String bankAddressLine1;

/** Bank address Line 2*/
private String bankAddressLine2;

/** Bank address Line 3*/
private String bankAddressLine3;

/** Bank address Line 4*/
private String bankAddressLine4;

/** Post code*/
private String postCode;

/** Account description*/

private String accountDescription;

/** Account type*/
private String accountType;

/**
 * @return the sortCodePart1
 */
public String getSortCodePart1() {
	return sortCodePart1;
}

/**
 * @param sortCodePart1 the sortCodePart1 to set
 */
public void setSortCodePart1(String sortCodePart1) {
	this.sortCodePart1 = sortCodePart1;
}

/**
 * @return the sortCodePart2
 */
public String getSortCodePart2() {
	return sortCodePart2;
}

/**
 * @param sortCodePart2 the sortCodePart2 to set
 */
public void setSortCodePart2(String sortCodePart2) {
	this.sortCodePart2 = sortCodePart2;
}

/**
 * @return the sortCodePart3
 */
public String getSortCodePart3() {
	return sortCodePart3;
}

/**
 * @param sortCodePart3 the sortCodePart3 to set
 */
public void setSortCodePart3(String sortCodePart3) {
	this.sortCodePart3 = sortCodePart3;
}

/**
 * @return the accountNumber
 */
public String getAccountNumber() {
	return accountNumber;
}

/**
 * @param accountNumber the accountNumber to set
 */
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}

/**
 * @return the accountName
 */
public String getAccountName() {
	return accountName;
}

/**
 * @param accountName the accountName to set
 */
public void setAccountName(String accountName) {
	this.accountName = accountName;
}

/**
 * @return the bankName
 */
public String getBankName() {
	return bankName;
}

/**
 * @param bankName the bankName to set
 */
public void setBankName(String bankName) {
	this.bankName = bankName;
}

/**
 * @return the bankAddressLine1
 */
public String getBankAddressLine1() {
	return bankAddressLine1;
}

/**
 * @param bankAddressLine1 the bankAddressLine1 to set
 */
public void setBankAddressLine1(String bankAddressLine1) {
	this.bankAddressLine1 = bankAddressLine1;
}

/**
 * @return the bankAddressLine2
 */
public String getBankAddressLine2() {
	return bankAddressLine2;
}

/**
 * @param bankAddressLine2 the bankAddressLine2 to set
 */
public void setBankAddressLine2(String bankAddressLine2) {
	this.bankAddressLine2 = bankAddressLine2;
}

/**
 * @return the bankAddressLine3
 */
public String getBankAddressLine3() {
	return bankAddressLine3;
}

/**
 * @param bankAddressLine3 the bankAddressLine3 to set
 */
public void setBankAddressLine3(String bankAddressLine3) {
	this.bankAddressLine3 = bankAddressLine3;
}

/**
 * @return the bankAddressLine4
 */
public String getBankAddressLine4() {
	return bankAddressLine4;
}

/**
 * @param bankAddressLine4 the bankAddressLine4 to set
 */
public void setBankAddressLine4(String bankAddressLine4) {
	this.bankAddressLine4 = bankAddressLine4;
}

/**
 * @return the postCode
 */
public String getPostCode() {
	return postCode;
}

/**
 * @param postCode the postCode to set
 */
public void setPostCode(String postCode) {
	this.postCode = postCode;
}

/**
 * @return the accountType
 */
public String getAccountType() {
	return accountType;
}

/**
 * @param accountType the accountType to set
 */
public void setAccountType(String accountType) {
	this.accountType = accountType;
}

/**
 * @return the accountDescription
 */
public String getAccountDescription() {
	return accountDescription;
}

/**
 * @param accountDescription the accountDescription to set
 */
public void setAccountDescription(String accountDescription) {
	this.accountDescription = accountDescription;
}





}	


public class VerificationDetails{

/** Charity HMRC reference number*/

private String charityHmrcReferenceNumber;	
	
/** Virgin HMRC reference number*/	

private String virginHmrcReferenceNumber;

/** Verify notes*/	

private String verifyNotes;

/**
 * @return the charityHmrcReferenceNumber
 */
public String getCharityHmrcReferenceNumber() {
	return charityHmrcReferenceNumber;
}

/**
 * @param charityHmrcReferenceNumber the charityHmrcReferenceNumber to set
 */
public void setCharityHmrcReferenceNumber(String charityHmrcReferenceNumber) {
	this.charityHmrcReferenceNumber = charityHmrcReferenceNumber;
}

/**
 * @return the virginHmrcReferenceNumber
 */
public String getVirginHmrcReferenceNumber() {
	return virginHmrcReferenceNumber;
}

/**
 * @param virginHmrcReferenceNumber the virginHmrcReferenceNumber to set
 */
public void setVirginHmrcReferenceNumber(String virginHmrcReferenceNumber) {
	this.virginHmrcReferenceNumber = virginHmrcReferenceNumber;
}

/**
 * @return the verifyNotes
 */
public String getVerifyNotes() {
	return verifyNotes;
}

/**
 * @param verifyNotes the verifyNotes to set
 */
public void setVerifyNotes(String verifyNotes) {
	this.verifyNotes = verifyNotes;
}


	
	
	
}


public class FormDetails{
	
/** Notes related to forms*/
private String formNotes;

/**
 * @return the formNotes
 */
public String getFormNotes() {
	return formNotes;
}

/**
 * @param formNotes the formNotes to set
 */
public void setFormNotes(String formNotes) {
	this.formNotes = formNotes;
}	


} 







/** Instance of bank account details*/
private BankAccountDetails bankAccountDetails=new BankAccountDetails();


/**
 * @return the bankAccountDetails
 */
public BankAccountDetails getBankAccountDetails() {
	return bankAccountDetails;
}


/**
 * @param bankAccountDetails the bankAccountDetails to set
 */
public void setBankAccountDetails(BankAccountDetails bankAccountDetails) {
	this.bankAccountDetails = bankAccountDetails;
}


/** Instance of verification details*/
private VerificationDetails verificationDetails =new VerificationDetails();


/**
 * @return the verificationDetails
 */
public VerificationDetails getVerificationDetails() {
	return verificationDetails;
}


/**
 * @param verificationDetails the verificationDetails to set
 */
public void setVerificationDetails(VerificationDetails verificationDetails) {
	this.verificationDetails = verificationDetails;
}

/**Instance of form details */

private FormDetails formDetails=new FormDetails();


/**
 * @return the formDetails
 */
public FormDetails getFormDetails() {
	return formDetails;
}


/**
 * @param formDetails the formDetails to set
 */
public void setFormDetails(FormDetails formDetails) {
	this.formDetails = formDetails;
}








	
	

}
