package com.virginmoney.addresslookup.business;

import org.apache.log4j.Logger;

/**
 * Contains very detailed data about an address, such as residential status, thoroughfare names, organisation names and so on.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class PAFData {

    private static Logger logger = Logger.getLogger(PAFData.class);
    private String id;
    private String organisationName;
    private String departmentName;
    private String postTown;
    private String county;
    private String postcode;
    private String mailsort;
    private String barcode;
    private boolean isResidential;
    private boolean isSmallOrganisation;
    private boolean isLargeOrganisation;
    private String deliveryPointSuffix;
    private String checksum;
    private String subBuildingName;
    private String buildingName;
    private String buildingNumber;
    private String thoroughfareName;
    private String thoroughfareDescriptor;
    private String dependentThoroughfareName;
    private String dependentThoroughfareDescriptor;
    private String doubleDependentLocality;
    private String dependentLocality;
    private String poBoxNumber;
    private String numberOfHouseholds;
    private String concatenationOperator;
    private int gridEastM;
    private int gridNorthM;
    private String districtCode;
    private String wardCode;
    private String nhsCode;
    private String nhsRegionCode;
    private String countyCode;
    private String countryCode;
    private String wardStatus;
    private String wardName;
    private String districtName;
    private boolean objective2;
    private String objective2Region;
    private boolean transitional;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    public String getPostTown() {
        return postTown;
    }

    public void setPostTown(String postTown) {
        this.postTown = postTown;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMailsort() {
        return mailsort;
    }

    public void setMailsort(String mailsort) {
        this.mailsort = mailsort;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isResidential() {
        return isResidential;
    }

    // duplicated for dozer
    public boolean isIsResidential() {
        return isResidential;
    }

    public void setIsResidential(boolean residential) {
        isResidential = residential;
    }
    // duplicated for dozer
    public void setResidential(boolean residential) {
        isResidential = residential;
    }

    public boolean isIsSmallOrganisation() {
        return isSmallOrganisation;
    }

    public void setIsSmallOrganisation(boolean smallOrganisation) {
        isSmallOrganisation = smallOrganisation;
    }

    public boolean isIsLargeOrganisation() {
        return isLargeOrganisation;
    }

    public void setIsLargeOrganisation(boolean largeOrganisation) {
        isLargeOrganisation = largeOrganisation;
    }

    public String getDeliveryPointSuffix() {
        return deliveryPointSuffix;
    }

    public void setDeliveryPointSuffix(String deliveryPointSuffix) {
        this.deliveryPointSuffix = deliveryPointSuffix;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getSubBuildingName() {
        return subBuildingName;
    }

    public void setSubBuildingName(String subBuildingName) {
        this.subBuildingName = subBuildingName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getThoroughfareName() {
        return thoroughfareName;
    }

    public void setThoroughfareName(String thoroughfareName) {
        this.thoroughfareName = thoroughfareName;
    }

    public String getThoroughfareDescriptor() {
        return thoroughfareDescriptor;
    }

    public void setThoroughfareDescriptor(String thoroughfareDescriptor) {
        this.thoroughfareDescriptor = thoroughfareDescriptor;
    }

    public String getDependentThoroughfareName() {
        return dependentThoroughfareName;
    }

    public void setDependentThoroughfareName(String dependentThoroughfareName) {
        this.dependentThoroughfareName = dependentThoroughfareName;
    }

    public String getDependentThoroughfareDescriptor() {
        return dependentThoroughfareDescriptor;
    }

    public void setDependentThoroughfareDescriptor(String dependentThoroughfareDescriptor) {
        this.dependentThoroughfareDescriptor = dependentThoroughfareDescriptor;
    }

    public String getDoubleDependentLocality() {
        return doubleDependentLocality;
    }

    public void setDoubleDependentLocality(String doubleDependentLocality) {
        this.doubleDependentLocality = doubleDependentLocality;
    }

    public String getDependentLocality() {
        return dependentLocality;
    }

    public void setDependentLocality(String dependentLocality) {
        this.dependentLocality = dependentLocality;
    }

    public String getPoBoxNumber() {
        return poBoxNumber;
    }

    public void setPoBoxNumber(String poBoxNumber) {
        this.poBoxNumber = poBoxNumber;
    }

    public String getNumberOfHouseholds() {
        return numberOfHouseholds;
    }

    public void setNumberOfHouseholds(String numberOfHouseholds) {
        this.numberOfHouseholds = numberOfHouseholds;
    }

    public String getConcatenationOperator() {
        return concatenationOperator;
    }

    public void setConcatenationOperator(String concatenationOperator) {
        this.concatenationOperator = concatenationOperator;
    }

    public int getGridEastM() {
        return gridEastM;
    }

    public void setGridEastM(int gridEastM) {
        this.gridEastM = gridEastM;
    }

    public int getGridNorthM() {
        return gridNorthM;
    }

    public void setGridNorthM(int gridNorthM) {
        this.gridNorthM = gridNorthM;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getNhsCode() {
        return nhsCode;
    }

    public void setNhsCode(String nhsCode) {
        this.nhsCode = nhsCode;
    }

    public String getNhsRegionCode() {
        return nhsRegionCode;
    }

    public void setNhsRegionCode(String nhsRegionCode) {
        this.nhsRegionCode = nhsRegionCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getWardStatus() {
        return wardStatus;
    }

    public void setWardStatus(String wardStatus) {
        this.wardStatus = wardStatus;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public boolean isObjective2() {
        return objective2;
    }

    public void setObjective2(boolean objective2) {
        this.objective2 = objective2;
    }

    public String getObjective2Region() {
        return objective2Region;
    }

    public void setObjective2Region(String objective2Region) {
        this.objective2Region = objective2Region;
    }

    public boolean isTransitional() {
        return transitional;
    }

    public void setTransitional(boolean transitional) {
        this.transitional = transitional;
    }

    public String toString() {
        return "PAFData{" + "id='" + id + '\'' + ", organisationName='" + organisationName + '\'' +
                ", departmentName='" + departmentName + '\'' +  ", postTown='" +
                postTown + '\'' + ", county='" + county + '\'' + ", postcode='" + postcode + '\'' + ", mailsort='" +
                mailsort + '\'' + ", barcode='" + barcode + '\'' + ", isIsResidential=" + isResidential +
                ", isIsSmallOrganisation=" + isSmallOrganisation + ", isIsLargeOrganisation=" + isLargeOrganisation +
                ", deliveryPointSuffix='" + deliveryPointSuffix + '\'' + ", checksum='" + checksum + '\'' +
                ", subBuildingName='" + subBuildingName + '\'' +
                ", buildingName='" + buildingName + '\'' + ", buildingNumber='" + buildingNumber + '\'' +
                ", thoroughfareName='" + thoroughfareName + '\'' + ", thoroughfareDescriptor='" +
                thoroughfareDescriptor + '\'' + ", dependentThoroughfareName='" + dependentThoroughfareName + '\'' +
                ", dependentThoroughfareDescriptor='" + dependentThoroughfareDescriptor + '\'' +
                ", doubleDependentLocality='" + doubleDependentLocality + '\'' + ", dependentLocality='" +
                dependentLocality + '\'' + ", poBoxNumber='" + poBoxNumber + '\'' + ", numberOfHouseholds='" +
                numberOfHouseholds + '\'' + ", concatenationOperator='" + concatenationOperator + '\'' +
                ", gridEastM=" + gridEastM + ", gridNorthM=" + gridNorthM + ", districtCode='" + districtCode + '\'' +
                ", wardCode='" + wardCode + '\'' + ", nhsCode='" + nhsCode + '\'' + ", nhsRegionCode='" +
                nhsRegionCode + '\'' + ", countyCode='" + countyCode + '\'' + ", countryCode='" + countryCode + '\'' +
                ", wardStatus='" + wardStatus + '\'' + ", wardName='" + wardName + '\'' + ", districtName='" +
                districtName + '\'' + ", objective2=" + objective2 + ", objective2Region='" + objective2Region + '\'' +
                ", transitional=" + transitional + '}';
    }
}

