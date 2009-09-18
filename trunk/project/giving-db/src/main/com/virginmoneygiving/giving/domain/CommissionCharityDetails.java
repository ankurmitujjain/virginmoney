package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * Domain object to hold charity details which will be fetched from UK
 * government database.
 * 
 * @author vikas kale
 */
@NamedNativeQuery(name = "findCharityByNameFromCommissionData",
        resultClass = CommissionCharityDetails.class,
        query = "select charity.*, 1  as sort_order FROM giving.Commission_Charity_Details as charity "
        			+ "where (charity.CHARITY_NAME like :searchTextForLike OR charity.REGISTRATION_NUMBER like :searchTextForLike)"
        		 +"UNION "
				 +"SELECT charity.*, 2 as sort_order FROM giving.Commission_Charity_Details as charity "
				  	+ "WHERE match (CHARITY_NAME,REGISTRATION_NUMBER) against (:searchText IN BOOLEAN MODE) " +
				  			"AND charity.org_Type = 'R'	and (charity.CHARITY_NAME not like :searchTextForLike AND charity.REGISTRATION_NUMBER not like :searchTextForLike) "
				 +"UNION "
				 +"SELECT charity.*, 3 as sort_order FROM giving.Commission_Charity_Details as charity "
				  	+ "WHERE match (CHARITY_NAME,REGISTRATION_NUMBER) against (:searchTextForSingleWord IN BOOLEAN MODE) "
				  			+"AND charity.org_Type = 'R' and (charity.CHARITY_NAME not like :searchTextForLike AND charity.REGISTRATION_NUMBER not like :searchTextForLike) "
				 +"ORDER BY sort_order, CHARITY_NAME, REGISTRATION_NUMBER")
@Entity
@Table(name = "COMMISSION_CHARITY_DETAILS")
public class CommissionCharityDetails extends BaseAuditAttributes implements
        Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -6389558351856032200L;

    /** Charity Details id. */
    private Long id;

    /** Charity registration number. */
    private String registrationNumber;

    /** Charity Details id. */
    private String charityName;

    /** Charity originated type. */
    private String orgType;

    /** Charity address line 1. */
    private String addressLine1;

    /** Charity address line 2. */
    private String addressLine2;

    /** Charity address line 3. */
    private String addressLine3;

    /** Charity address line 4. */
    private String addressLine4;

    /** Charity address postcode. */
    private String postCode;

    /** Charity register code. */
    private CharityRegister charityRegisterCode;
    
    /** Charity website address. */
    private String charityWebSiteUrl;
    
    /** URL for charity microsite on the VMG website. */
    private String derivedUrl;
    
    /** Charity company number. */
    private String companyNumber;

    /**
     * Getter method for charity details ID.
     * 
     * @return the id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    /**
     * Setter method for charity details id.
     * 
     * @param id to set charity details id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for charity registration number.
     * 
     * @return the registrationNumber
     */
    @Column(name = "REGISTRATION_NUMBER", length=20)
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Setter method for charity registration number.
     * 
     * @param registrationNumber to set charity registration number.
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Getter method for charity name.
     * 
     * @return the charityName
     */
    @Column(name = "CHARITY_NAME")
    public String getCharityName() {
        return charityName;
    }

    /**
     * Setter method for charity name.
     * 
     * @param charityName to set charity name.
     */
    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    /**
     * Getter method for charity org type.
     * 
     * @return the orgType can be 'R' - registered or 'RM' - removed.
     */
    @Column(name = "ORG_TYPE")
    public String getOrgType() {
        return orgType;
    }

    /**
     * Setter method for charity org type.
     * 
     * @param orgType to set charity org type.
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * Getter method for charity address line 1.
     * 
     * @return the addressLine1
     */
    @Column(name = "ADDRESS_LINE_1")
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Setter method for charity address line 1.
     * 
     * @param addressLine1 to set charity address line 1.
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Getter method for charity address line 2.
     * 
     * @return the addressLine2
     */
    @Column(name = "ADDRESS_LINE_2")
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Setter method for charity address line 3.
     * 
     * @param addressLine3 to set charity address line 3.
     */
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    /**
     * Getter method for charity address line 3.
     * 
     * @return the addressLine3
     */
    @Column(name = "ADDRESS_LINE_3")
    public String getAddressLine3() {
        return addressLine3;
    }

    /**
     * Getter method for charity address line 4.
     * 
     * @return the addressLine4
     */
    @Column(name = "ADDRESS_LINE_4")
    public String getAddressLine4() {
        return addressLine4;
    }

    /**
     * Setter method for charity address line 4.
     * 
     * @param addressLine4 to set charity address line 4.
     */
    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    /**
     * Setter method for charity address line 2.
     * 
     * @param addressLine2 to set charity address line 2.
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Getter method for charity address postcode.
     * 
     * @return the postCode
     */
    @Column(name = "POSTCODE")
    public String getPostCode() {
        return postCode;
    }

    /**
     * Setter method for charity address postcode.
     * 
     * @param postCode to set charity address postcode.
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * Getter method for charity address .
     * 
     * @return the charityRegisterCode
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_REGISTER_CODE")
    public CharityRegister getCharityRegisterCode() {
        return charityRegisterCode;
    }

    /**
     * Setter method for charityRegisterCode.
     * 
     * @param charityRegisterCode the charityRegisterCode to set
     */
    public void setCharityRegisterCode(CharityRegister charityRegisterCode) {
        this.charityRegisterCode = charityRegisterCode;
    }

    /**
     * Getter method for charityWebSiteUrl.
     * 
     * @return the charityWebSiteUrl
     */
    @Column(name = "CHARITY_WEB_SITE_URL")
    public String getCharityWebSiteUrl() {
        return charityWebSiteUrl;
    }

    /**
     * Setter method for charityWebSiteUrl.
     * 
     * @param charityWebSiteUrl the charityWebSiteUrl to set
     */
    public void setCharityWebSiteUrl(String charityWebSiteUrl) {
        this.charityWebSiteUrl = charityWebSiteUrl;
    }

    /**
     * Getter method for derivedUrl.
     * 
     * @return the derivedUrl
     */
    @Column(name = "DERIVED_URL")
    public String getDerivedUrl() {
        return derivedUrl;
    }

    /**
     * Setter method for derivedUrl.
     * 
     * @param derivedUrl the derivedUrl to set
     */
    public void setDerivedUrl(String derivedUrl) {
        this.derivedUrl = derivedUrl;
    }

    /**
     * Getter method for companyNumber.
     * 
     * @return the companyNumber
     */
    @Column(name = "COMPANY_NUMBER")
    public String getCompanyNumber() {
        return companyNumber;
    }

    /**
     * Setter method for companyNumber.
     * 
     * @param companyNumber the companyNumber to set
     */
    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }
    
    
}
