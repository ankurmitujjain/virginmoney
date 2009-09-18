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
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the address data to be persisted.
 * 
 * @author Sejal Shah.
 * @author Puneet Swarup - added audit attributes and toString.
 */

@Entity
@Table(name = "ADDRESS")
@Audited
public class Address extends EnversAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -3608364457419720217L;

    /** Primary key for Address. */
    private Long id;

    /** Building Number for Address. */
    private String buildingNumber;

    /** Building Name for Address. */
    private String buildingName;

    /** Address Line1 for Address. */
    private String addressLine1;

    /** Address Line2 for Address. */
    private String addressLine2;

    /** Town City for Address. */
    private String townCity;

    /** County State for Address. */
    private String countyState;

    /** PostCode for Address. */
    private String postCode;

    /** for country Iso code. */
    private Country country;

    /** address type code in charityAddress. */
    private AddressType addressType;

    /** Indicates if an amendment has been carried out on the address. */
    private String addressUpdateInd;

    /**
     * Getter method for address id.
     * 
     * @return Address Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
    public Long getId() {
        return id;
    }

    /**
     * Setter method for address id.
     * 
     * @param id of address.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the address type.
     * 
     * @return the addressTypeCode
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_TYPE_CODE")
    @NotAudited
    public AddressType getAddressType() {
        return addressType;
    }

    /**
     * Setter method for address type.
     * 
     * @param addressType to set object of {@link AddressType}.
     */
    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    /**
     * Getter method for Country.
     * 
     * @return the country.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ISO_CODE")
    @Audited
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the country.
     * 
     * @param country to set object of {@link Country}.
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Getter method for buildingNumber.
     *
     * @return the buildingNumber
     */
    @Column(name = "BUILDING_NUMBER")
    @Audited
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * Setter method for building number.
     * 
     * @param buildingNumber Building Number.
     */
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    /**
     * Getter method for buildingName.
     * 
     * @return the buildingName
     */
    @Column(name = "BUILDING_NAME")
    @NotAudited
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Setter method for buildingName.
     * 
     * @param buildingName to set the building name.
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * Getter method for addressLine1.
     * 
     * @return the addressLine1
     */
    @Column(name = "ADDRESS_LINE_1")
    @Audited
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Setter method for addressLine1.
     * 
     * @param addressLine1 to set address line 1.
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Getter method for addressLine2.
     * 
     * @return the addressLine2
     */
    @Column(name = "ADDRESS_LINE_2")
    @Audited
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Setter method for addressLine2.
     * 
     * @param addressLine2 to set the addressLine2.
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Getter method for townCity.
     * 
     * @return the townCity
     */
    @Column(name = "TOWN_CITY")
    @Audited
    public String getTownCity() {
        return townCity;
    }

    /**
     * Setter method for townCity.
     * 
     * @param townCity to set the townCity.
     */
    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    /**
     * Getter method for countyState.
     * 
     * @return the countyState
     */
    @Column(name = "COUNTY_STATE")
    @Audited
    public String getCountyState() {
        return countyState;
    }

    /**
     * Setter method for countyState.
     * 
     * @param countyState to set the countyState.
     */
    public void setCountyState(String countyState) {
        this.countyState = countyState;
    }

    /**
     * Getter method for postCode.
     * 
     * @return the postCode.
     */
    @Column(name = "POSTCODE")
    @Audited
    public String getPostCode() {
        return postCode;
    }

    /**
     * Setter method for postCode.
     * 
     * @param postCode postcode for address.
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * Gets the addressUpdateInd property.
     * 
     * @return String value
     */
    @Column(name = "ADDRESS_AMENDMENT_IND")
    @NotAudited
    public String getAddressUpdateInd() {
        return addressUpdateInd;
    }

    /**
     * Sets the addressUpdateInd property.
     * 
     * @param addressUpdateInd value to set
     */
    public void setAddressUpdateInd(String addressUpdateInd) {
        this.addressUpdateInd = addressUpdateInd;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation
     * of this object.
     */
     @Override
     public String toString() {

        final String tab = "    ";
        return new StringBuilder("Address ( ")
            .append("id = ").append(id).append(tab)
            .append("buildingNumber = ").append(buildingNumber).append(tab)
            .append("buildingName = ").append(buildingName).append(tab)
            .append("addressLine1 = ").append(addressLine1).append(tab)
            .append("addressLine2 = ").append(addressLine2).append(tab)
            .append("townCity = ").append(townCity).append(tab)
            .append("countyState = ").append(countyState).append(tab)
            .append("postCode = ").append(postCode).append(tab)
            .append("country = ").append(country).append(tab)
            //.append("location = ").append(location).append(tab)
            .append("addressType = ").append(addressType).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
