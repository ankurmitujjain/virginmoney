
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.virginmoneygiving.givingmanagement.service.messages package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FundraisingReasonResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/fundraiser/", "fundraisingReasonResponse");
    private final static QName _VmgCharityURLExistResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/charity/", "vmgCharityURLExistResponse");
    private final static QName _MessageHeader_QNAME = new QName("http://www.virginmoneygiving.com/type/header/", "MessageHeader");
    private final static QName _ResetUserPasswordResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/charity/", "resetUserPasswordResponse");
    private final static QName _GetTrusteeCountResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/charity/", "getTrusteeCountResponse");
    private final static QName _FetchTransitionalReliefAmountforSettlementResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/operations/", "fetchTransitionalReliefAmountforSettlementResponse");
    private final static QName _IsPaymentCardDetailsExistResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/donor/", "isPaymentCardDetailsExistResponse");
    private final static QName _FetchDonorIdByUserRoleIdResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/donor/", "fetchDonorIdByUserRoleIdResponse");
    private final static QName _ServiceFaultException_QNAME = new QName("http://www.virginmoneygiving.com/faults/", "ServiceFaultException");
    private final static QName _CancelRegularDonationResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/donor/", "cancelRegularDonationResponse");
    private final static QName _FundraiserCloseAccountResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/fundraiser/", "fundraiserCloseAccountResponse");
    private final static QName _FetchUserNameResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/common/", "fetchUserNameResponse");
    private final static QName _AccountExistsResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/charity/", "accountExistsResponse");
    private final static QName _UpdateDonorPersonalDetailsResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/charity/", "updateDonorPersonalDetailsResponse");
    private final static QName _FetchUserNameRequest_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/common/", "fetchUserNameRequest");
    private final static QName _Header_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/common/", "Header");
    private final static QName _CharityRegisterVerificationResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/charity/", "charityRegisterVerificationResponse");
    private final static QName _EmailResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/giving-management/operations/", "emailResponse");
    private final static QName _NewElement_QNAME = new QName("http://www.virginmoneygiving.com/giving-management/", "NewElement");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.virginmoneygiving.givingmanagement.service.messages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FetchEventFeeListRequest }
     * 
     */
    public FetchEventFeeListRequest createFetchEventFeeListRequest() {
        return new FetchEventFeeListRequest();
    }

    /**
     * Create an instance of {@link FetchUnregisteredOperationUsersLoginNameRequest }
     * 
     */
    public FetchUnregisteredOperationUsersLoginNameRequest createFetchUnregisteredOperationUsersLoginNameRequest() {
        return new FetchUnregisteredOperationUsersLoginNameRequest();
    }

    /**
     * Create an instance of {@link OwnedEventsSearchRequest }
     * 
     */
    public OwnedEventsSearchRequest createOwnedEventsSearchRequest() {
        return new OwnedEventsSearchRequest();
    }

    /**
     * Create an instance of {@link ServiceCharityOffRegLog }
     * 
     */
    public ServiceCharityOffRegLog createServiceCharityOffRegLog() {
        return new ServiceCharityOffRegLog();
    }

    /**
     * Create an instance of {@link ServiceTelephoneNumber }
     * 
     */
    public ServiceTelephoneNumber createServiceTelephoneNumber() {
        return new ServiceTelephoneNumber();
    }

    /**
     * Create an instance of {@link BasicResponseElement }
     * 
     */
    public BasicResponseElement createBasicResponseElement() {
        return new BasicResponseElement();
    }

    /**
     * Create an instance of {@link CharityAdminUsersRequest }
     * 
     */
    public CharityAdminUsersRequest createCharityAdminUsersRequest() {
        return new CharityAdminUsersRequest();
    }

    /**
     * Create an instance of {@link ServiceCharityOffReg }
     * 
     */
    public ServiceCharityOffReg createServiceCharityOffReg() {
        return new ServiceCharityOffReg();
    }

    /**
     * Create an instance of {@link PutCharityLiveRequest }
     * 
     */
    public PutCharityLiveRequest createPutCharityLiveRequest() {
        return new PutCharityLiveRequest();
    }

    /**
     * Create an instance of {@link CountriesResponse }
     * 
     */
    public CountriesResponse createCountriesResponse() {
        return new CountriesResponse();
    }

    /**
     * Create an instance of {@link FetchBiggestFundraiserActivityDonationResponse }
     * 
     */
    public FetchBiggestFundraiserActivityDonationResponse createFetchBiggestFundraiserActivityDonationResponse() {
        return new FetchBiggestFundraiserActivityDonationResponse();
    }

    /**
     * Create an instance of {@link SaveTrusteeDetailsRequest }
     * 
     */
    public SaveTrusteeDetailsRequest createSaveTrusteeDetailsRequest() {
        return new SaveTrusteeDetailsRequest();
    }

    /**
     * Create an instance of {@link FetchUserRolesRequest }
     * 
     */
    public FetchUserRolesRequest createFetchUserRolesRequest() {
        return new FetchUserRolesRequest();
    }

    /**
     * Create an instance of {@link FindRegularDonationsByDonorResponse }
     * 
     */
    public FindRegularDonationsByDonorResponse createFindRegularDonationsByDonorResponse() {
        return new FindRegularDonationsByDonorResponse();
    }

    /**
     * Create an instance of {@link FetchCategoriesRequest }
     * 
     */
    public FetchCategoriesRequest createFetchCategoriesRequest() {
        return new FetchCategoriesRequest();
    }

    /**
     * Create an instance of {@link FundraiserDetails }
     * 
     */
    public FundraiserDetails createFundraiserDetails() {
        return new FundraiserDetails();
    }

    /**
     * Create an instance of {@link ServiceCardType }
     * 
     */
    public ServiceCardType createServiceCardType() {
        return new ServiceCardType();
    }

    /**
     * Create an instance of {@link ServicePersonDetails }
     * 
     */
    public ServicePersonDetails createServicePersonDetails() {
        return new ServicePersonDetails();
    }

    /**
     * Create an instance of {@link FetchCharityDetailsByIdsResponse }
     * 
     */
    public FetchCharityDetailsByIdsResponse createFetchCharityDetailsByIdsResponse() {
        return new FetchCharityDetailsByIdsResponse();
    }

    /**
     * Create an instance of {@link CharityAdminListResponse }
     * 
     */
    public CharityAdminListResponse createCharityAdminListResponse() {
        return new CharityAdminListResponse();
    }

    /**
     * Create an instance of {@link FetchHMRCreferenceNumberRequest }
     * 
     */
    public FetchHMRCreferenceNumberRequest createFetchHMRCreferenceNumberRequest() {
        return new FetchHMRCreferenceNumberRequest();
    }

    /**
     * Create an instance of {@link CheckEventAttachedWithBankRequest }
     * 
     */
    public CheckEventAttachedWithBankRequest createCheckEventAttachedWithBankRequest() {
        return new CheckEventAttachedWithBankRequest();
    }

    /**
     * Create an instance of {@link CountriesRequest }
     * 
     */
    public CountriesRequest createCountriesRequest() {
        return new CountriesRequest();
    }

    /**
     * Create an instance of {@link FetchPersonDetailsRequest }
     * 
     */
    public FetchPersonDetailsRequest createFetchPersonDetailsRequest() {
        return new FetchPersonDetailsRequest();
    }

    /**
     * Create an instance of {@link CharityHomePageRegistrationRequest }
     * 
     */
    public CharityHomePageRegistrationRequest createCharityHomePageRegistrationRequest() {
        return new CharityHomePageRegistrationRequest();
    }

    /**
     * Create an instance of {@link FindPageDetailsbyFundraiserIdRequest }
     * 
     */
    public FindPageDetailsbyFundraiserIdRequest createFindPageDetailsbyFundraiserIdRequest() {
        return new FindPageDetailsbyFundraiserIdRequest();
    }

    /**
     * Create an instance of {@link FetchCharityHomePageDetailsRequest }
     * 
     */
    public FetchCharityHomePageDetailsRequest createFetchCharityHomePageDetailsRequest() {
        return new FetchCharityHomePageDetailsRequest();
    }

    /**
     * Create an instance of {@link FindFundraiserGroupsbyFundraiserIdRequest }
     * 
     */
    public FindFundraiserGroupsbyFundraiserIdRequest createFindFundraiserGroupsbyFundraiserIdRequest() {
        return new FindFundraiserGroupsbyFundraiserIdRequest();
    }

    /**
     * Create an instance of {@link CheckProfanityResponse }
     * 
     */
    public CheckProfanityResponse createCheckProfanityResponse() {
        return new CheckProfanityResponse();
    }

    /**
     * Create an instance of {@link OtherEventsSearchRequest }
     * 
     */
    public OtherEventsSearchRequest createOtherEventsSearchRequest() {
        return new OtherEventsSearchRequest();
    }

    /**
     * Create an instance of {@link ServiceFault }
     * 
     */
    public ServiceFault createServiceFault() {
        return new ServiceFault();
    }

    /**
     * Create an instance of {@link CharityAdminListRequest }
     * 
     */
    public CharityAdminListRequest createCharityAdminListRequest() {
        return new CharityAdminListRequest();
    }

    /**
     * Create an instance of {@link FetchVMGUserDetailsForExternalUserRequest }
     * 
     */
    public FetchVMGUserDetailsForExternalUserRequest createFetchVMGUserDetailsForExternalUserRequest() {
        return new FetchVMGUserDetailsForExternalUserRequest();
    }

    /**
     * Create an instance of {@link PayEventFeeWithNewCardResponse }
     * 
     */
    public PayEventFeeWithNewCardResponse createPayEventFeeWithNewCardResponse() {
        return new PayEventFeeWithNewCardResponse();
    }

    /**
     * Create an instance of {@link FindCharityByNameFromCommissionDataRequest }
     * 
     */
    public FindCharityByNameFromCommissionDataRequest createFindCharityByNameFromCommissionDataRequest() {
        return new FindCharityByNameFromCommissionDataRequest();
    }

    /**
     * Create an instance of {@link SavePageDetailsRequest }
     * 
     */
    public SavePageDetailsRequest createSavePageDetailsRequest() {
        return new SavePageDetailsRequest();
    }

    /**
     * Create an instance of {@link LoadAlertTriggersResponse }
     * 
     */
    public LoadAlertTriggersResponse createLoadAlertTriggersResponse() {
        return new LoadAlertTriggersResponse();
    }

    /**
     * Create an instance of {@link FetchCharityDetailsByIdResponse }
     * 
     */
    public FetchCharityDetailsByIdResponse createFetchCharityDetailsByIdResponse() {
        return new FetchCharityDetailsByIdResponse();
    }

    /**
     * Create an instance of {@link AlternateUrlForFundraiserRequest }
     * 
     */
    public AlternateUrlForFundraiserRequest createAlternateUrlForFundraiserRequest() {
        return new AlternateUrlForFundraiserRequest();
    }

    /**
     * Create an instance of {@link FetchDonorPersonalDetailsRequest }
     * 
     */
    public FetchDonorPersonalDetailsRequest createFetchDonorPersonalDetailsRequest() {
        return new FetchDonorPersonalDetailsRequest();
    }

    /**
     * Create an instance of {@link CancelRegularDonationRequest }
     * 
     */
    public CancelRegularDonationRequest createCancelRegularDonationRequest() {
        return new CancelRegularDonationRequest();
    }

    /**
     * Create an instance of {@link EmailHeader }
     * 
     */
    public EmailHeader createEmailHeader() {
        return new EmailHeader();
    }

    /**
     * Create an instance of {@link FetchBankAccountTypeRequest }
     * 
     */
    public FetchBankAccountTypeRequest createFetchBankAccountTypeRequest() {
        return new FetchBankAccountTypeRequest();
    }

    /**
     * Create an instance of {@link ServiceAlertTrigger }
     * 
     */
    public ServiceAlertTrigger createServiceAlertTrigger() {
        return new ServiceAlertTrigger();
    }

    /**
     * Create an instance of {@link FetchUserIdByUserNameRequest }
     * 
     */
    public FetchUserIdByUserNameRequest createFetchUserIdByUserNameRequest() {
        return new FetchUserIdByUserNameRequest();
    }

    /**
     * Create an instance of {@link FetchUnregisteredOperationUsersLoginNameResponse }
     * 
     */
    public FetchUnregisteredOperationUsersLoginNameResponse createFetchUnregisteredOperationUsersLoginNameResponse() {
        return new FetchUnregisteredOperationUsersLoginNameResponse();
    }

    /**
     * Create an instance of {@link FetchCharityDetailsByVmgCharityUrlResponse }
     * 
     */
    public FetchCharityDetailsByVmgCharityUrlResponse createFetchCharityDetailsByVmgCharityUrlResponse() {
        return new FetchCharityDetailsByVmgCharityUrlResponse();
    }

    /**
     * Create an instance of {@link CheckProfanityRequest }
     * 
     */
    public CheckProfanityRequest createCheckProfanityRequest() {
        return new CheckProfanityRequest();
    }

    /**
     * Create an instance of {@link ServiceRegistrationForm }
     * 
     */
    public ServiceRegistrationForm createServiceRegistrationForm() {
        return new ServiceRegistrationForm();
    }

    /**
     * Create an instance of {@link FetchEventHomePageDetailsRequest }
     * 
     */
    public FetchEventHomePageDetailsRequest createFetchEventHomePageDetailsRequest() {
        return new FetchEventHomePageDetailsRequest();
    }

    /**
     * Create an instance of {@link CancelEventResponse }
     * 
     */
    public CancelEventResponse createCancelEventResponse() {
        return new CancelEventResponse();
    }

    /**
     * Create an instance of {@link FetchUserPermissionsResponse }
     * 
     */
    public FetchUserPermissionsResponse createFetchUserPermissionsResponse() {
        return new FetchUserPermissionsResponse();
    }

    /**
     * Create an instance of {@link RemoveCharityBankAccountRequest }
     * 
     */
    public RemoveCharityBankAccountRequest createRemoveCharityBankAccountRequest() {
        return new RemoveCharityBankAccountRequest();
    }

    /**
     * Create an instance of {@link ServiceCharityOffRegNote }
     * 
     */
    public ServiceCharityOffRegNote createServiceCharityOffRegNote() {
        return new ServiceCharityOffRegNote();
    }

    /**
     * Create an instance of {@link FetchDonorPersonalDetailsResponse }
     * 
     */
    public FetchDonorPersonalDetailsResponse createFetchDonorPersonalDetailsResponse() {
        return new FetchDonorPersonalDetailsResponse();
    }

    /**
     * Create an instance of {@link ServiceOnlyEmailAddressChanged }
     * 
     */
    public ServiceOnlyEmailAddressChanged createServiceOnlyEmailAddressChanged() {
        return new ServiceOnlyEmailAddressChanged();
    }

    /**
     * Create an instance of {@link GivingServiceDonationResult }
     * 
     */
    public GivingServiceDonationResult createGivingServiceDonationResult() {
        return new GivingServiceDonationResult();
    }

    /**
     * Create an instance of {@link FetchCharityCustomFieldsRequest }
     * 
     */
    public FetchCharityCustomFieldsRequest createFetchCharityCustomFieldsRequest() {
        return new FetchCharityCustomFieldsRequest();
    }

    /**
     * Create an instance of {@link FindPageDetailsbyFundraiserIdResponse }
     * 
     */
    public FindPageDetailsbyFundraiserIdResponse createFindPageDetailsbyFundraiserIdResponse() {
        return new FindPageDetailsbyFundraiserIdResponse();
    }

    /**
     * Create an instance of {@link ServiceModule }
     * 
     */
    public ServiceModule createServiceModule() {
        return new ServiceModule();
    }

    /**
     * Create an instance of {@link LoadAlertTriggersRequest }
     * 
     */
    public LoadAlertTriggersRequest createLoadAlertTriggersRequest() {
        return new LoadAlertTriggersRequest();
    }

    /**
     * Create an instance of {@link GetUserAccessRWCountRequest }
     * 
     */
    public GetUserAccessRWCountRequest createGetUserAccessRWCountRequest() {
        return new GetUserAccessRWCountRequest();
    }

    /**
     * Create an instance of {@link ServiceFundraiserGroupPageDetail }
     * 
     */
    public ServiceFundraiserGroupPageDetail createServiceFundraiserGroupPageDetail() {
        return new ServiceFundraiserGroupPageDetail();
    }

    /**
     * Create an instance of {@link FetchUserIdByUserNameResponse }
     * 
     */
    public FetchUserIdByUserNameResponse createFetchUserIdByUserNameResponse() {
        return new FetchUserIdByUserNameResponse();
    }

    /**
     * Create an instance of {@link GrantPermissionsRequest }
     * 
     */
    public GrantPermissionsRequest createGrantPermissionsRequest() {
        return new GrantPermissionsRequest();
    }

    /**
     * Create an instance of {@link CharityAddressRequest }
     * 
     */
    public CharityAddressRequest createCharityAddressRequest() {
        return new CharityAddressRequest();
    }

    /**
     * Create an instance of {@link ServicePageWidget }
     * 
     */
    public ServicePageWidget createServicePageWidget() {
        return new ServicePageWidget();
    }

    /**
     * Create an instance of {@link FetchFundraiserDetailsForUserRequest }
     * 
     */
    public FetchFundraiserDetailsForUserRequest createFetchFundraiserDetailsForUserRequest() {
        return new FetchFundraiserDetailsForUserRequest();
    }

    /**
     * Create an instance of {@link CheckForProfanityResponse }
     * 
     */
    public CheckForProfanityResponse createCheckForProfanityResponse() {
        return new CheckForProfanityResponse();
    }

    /**
     * Create an instance of {@link SaveFundraiserEventFeeRequest }
     * 
     */
    public SaveFundraiserEventFeeRequest createSaveFundraiserEventFeeRequest() {
        return new SaveFundraiserEventFeeRequest();
    }

    /**
     * Create an instance of {@link CharityDonationDetailsResponse }
     * 
     */
    public CharityDonationDetailsResponse createCharityDonationDetailsResponse() {
        return new CharityDonationDetailsResponse();
    }

    /**
     * Create an instance of {@link UpdateUserPersonalDetailsRequest }
     * 
     */
    public UpdateUserPersonalDetailsRequest createUpdateUserPersonalDetailsRequest() {
        return new UpdateUserPersonalDetailsRequest();
    }

    /**
     * Create an instance of {@link ServiceEventActivity }
     * 
     */
    public ServiceEventActivity createServiceEventActivity() {
        return new ServiceEventActivity();
    }

    /**
     * Create an instance of {@link ServiceEvent }
     * 
     */
    public ServiceEvent createServiceEvent() {
        return new ServiceEvent();
    }

    /**
     * Create an instance of {@link PageWidgetResponse }
     * 
     */
    public PageWidgetResponse createPageWidgetResponse() {
        return new PageWidgetResponse();
    }

    /**
     * Create an instance of {@link FetchHMRCreferenceNumberResponse }
     * 
     */
    public FetchHMRCreferenceNumberResponse createFetchHMRCreferenceNumberResponse() {
        return new FetchHMRCreferenceNumberResponse();
    }

    /**
     * Create an instance of {@link CharityTrusteeDetailsResponse }
     * 
     */
    public CharityTrusteeDetailsResponse createCharityTrusteeDetailsResponse() {
        return new CharityTrusteeDetailsResponse();
    }

    /**
     * Create an instance of {@link FindLiveCharitiesLikeNameResponse }
     * 
     */
    public FindLiveCharitiesLikeNameResponse createFindLiveCharitiesLikeNameResponse() {
        return new FindLiveCharitiesLikeNameResponse();
    }

    /**
     * Create an instance of {@link ServiceTrusteeDetails }
     * 
     */
    public ServiceTrusteeDetails createServiceTrusteeDetails() {
        return new ServiceTrusteeDetails();
    }

    /**
     * Create an instance of {@link PaymentCardDetailsRequest }
     * 
     */
    public PaymentCardDetailsRequest createPaymentCardDetailsRequest() {
        return new PaymentCardDetailsRequest();
    }

    /**
     * Create an instance of {@link CharityDonationDetailsRequest }
     * 
     */
    public CharityDonationDetailsRequest createCharityDonationDetailsRequest() {
        return new CharityDonationDetailsRequest();
    }

    /**
     * Create an instance of {@link FundraisingReasonRequest }
     * 
     */
    public FundraisingReasonRequest createFundraisingReasonRequest() {
        return new FundraisingReasonRequest();
    }

    /**
     * Create an instance of {@link FetchPageWidgetTypeFromPageTypeRequest }
     * 
     */
    public FetchPageWidgetTypeFromPageTypeRequest createFetchPageWidgetTypeFromPageTypeRequest() {
        return new FetchPageWidgetTypeFromPageTypeRequest();
    }

    /**
     * Create an instance of {@link ResetUserPasswordRequest }
     * 
     */
    public ResetUserPasswordRequest createResetUserPasswordRequest() {
        return new ResetUserPasswordRequest();
    }

    /**
     * Create an instance of {@link PaymentCardResponse }
     * 
     */
    public PaymentCardResponse createPaymentCardResponse() {
        return new PaymentCardResponse();
    }

    /**
     * Create an instance of {@link FetchDonationsCountByDonorRequest }
     * 
     */
    public FetchDonationsCountByDonorRequest createFetchDonationsCountByDonorRequest() {
        return new FetchDonationsCountByDonorRequest();
    }

    /**
     * Create an instance of {@link CharitiesByCharityCategoryIdRequest }
     * 
     */
    public CharitiesByCharityCategoryIdRequest createCharitiesByCharityCategoryIdRequest() {
        return new CharitiesByCharityCategoryIdRequest();
    }

    /**
     * Create an instance of {@link RemoveCharityBankAccountResponse }
     * 
     */
    public RemoveCharityBankAccountResponse createRemoveCharityBankAccountResponse() {
        return new RemoveCharityBankAccountResponse();
    }

    /**
     * Create an instance of {@link FetchVMGUserDetailsForInternalUserResponse }
     * 
     */
    public FetchVMGUserDetailsForInternalUserResponse createFetchVMGUserDetailsForInternalUserResponse() {
        return new FetchVMGUserDetailsForInternalUserResponse();
    }

    /**
     * Create an instance of {@link ServiceSearchFundraisingEventResult }
     * 
     */
    public ServiceSearchFundraisingEventResult createServiceSearchFundraisingEventResult() {
        return new ServiceSearchFundraisingEventResult();
    }

    /**
     * Create an instance of {@link FetchVMGUserDetailsForInternalUserRequest }
     * 
     */
    public FetchVMGUserDetailsForInternalUserRequest createFetchVMGUserDetailsForInternalUserRequest() {
        return new FetchVMGUserDetailsForInternalUserRequest();
    }

    /**
     * Create an instance of {@link ServiceUserPersonalDetails }
     * 
     */
    public ServiceUserPersonalDetails createServiceUserPersonalDetails() {
        return new ServiceUserPersonalDetails();
    }

    /**
     * Create an instance of {@link FetchTeamMemberResponse }
     * 
     */
    public FetchTeamMemberResponse createFetchTeamMemberResponse() {
        return new FetchTeamMemberResponse();
    }

    /**
     * Create an instance of {@link PayCharityRegistrationFeeRequest }
     * 
     */
    public PayCharityRegistrationFeeRequest createPayCharityRegistrationFeeRequest() {
        return new PayCharityRegistrationFeeRequest();
    }

    /**
     * Create an instance of {@link ServiceAccountHolderInfo }
     * 
     */
    public ServiceAccountHolderInfo createServiceAccountHolderInfo() {
        return new ServiceAccountHolderInfo();
    }

    /**
     * Create an instance of {@link FetchCharityHomePageDetailsResponse }
     * 
     */
    public FetchCharityHomePageDetailsResponse createFetchCharityHomePageDetailsResponse() {
        return new FetchCharityHomePageDetailsResponse();
    }

    /**
     * Create an instance of {@link FetchEventRequest }
     * 
     */
    public FetchEventRequest createFetchEventRequest() {
        return new FetchEventRequest();
    }

    /**
     * Create an instance of {@link FetchDonationsCountByDonorResponse }
     * 
     */
    public FetchDonationsCountByDonorResponse createFetchDonationsCountByDonorResponse() {
        return new FetchDonationsCountByDonorResponse();
    }

    /**
     * Create an instance of {@link FindCharityByNameResponse }
     * 
     */
    public FindCharityByNameResponse createFindCharityByNameResponse() {
        return new FindCharityByNameResponse();
    }

    /**
     * Create an instance of {@link FindCharityByNameFromCommissionDataResponse }
     * 
     */
    public FindCharityByNameFromCommissionDataResponse createFindCharityByNameFromCommissionDataResponse() {
        return new FindCharityByNameFromCommissionDataResponse();
    }

    /**
     * Create an instance of {@link ServiceEventDetailsDVO }
     * 
     */
    public ServiceEventDetailsDVO createServiceEventDetailsDVO() {
        return new ServiceEventDetailsDVO();
    }

    /**
     * Create an instance of {@link GrantPermissionsWithRolesRequest }
     * 
     */
    public GrantPermissionsWithRolesRequest createGrantPermissionsWithRolesRequest() {
        return new GrantPermissionsWithRolesRequest();
    }

    /**
     * Create an instance of {@link EmailRequest }
     * 
     */
    public EmailRequest createEmailRequest() {
        return new EmailRequest();
    }

    /**
     * Create an instance of {@link GivingServicePaymentDetails }
     * 
     */
    public GivingServicePaymentDetails createGivingServicePaymentDetails() {
        return new GivingServicePaymentDetails();
    }

    /**
     * Create an instance of {@link FindFundraiserGroupRequest }
     * 
     */
    public FindFundraiserGroupRequest createFindFundraiserGroupRequest() {
        return new FindFundraiserGroupRequest();
    }

    /**
     * Create an instance of {@link UpdatePagewithFundraiserGroupIDRequest }
     * 
     */
    public UpdatePagewithFundraiserGroupIDRequest createUpdatePagewithFundraiserGroupIDRequest() {
        return new UpdatePagewithFundraiserGroupIDRequest();
    }

    /**
     * Create an instance of {@link ServiceReportingSearchResults }
     * 
     */
    public ServiceReportingSearchResults createServiceReportingSearchResults() {
        return new ServiceReportingSearchResults();
    }

    /**
     * Create an instance of {@link CreateEventResponse }
     * 
     */
    public CreateEventResponse createCreateEventResponse() {
        return new CreateEventResponse();
    }

    /**
     * Create an instance of {@link ServicePaymentCard }
     * 
     */
    public ServicePaymentCard createServicePaymentCard() {
        return new ServicePaymentCard();
    }

    /**
     * Create an instance of {@link UpdateUserPersonalDetailsResponse }
     * 
     */
    public UpdateUserPersonalDetailsResponse createUpdateUserPersonalDetailsResponse() {
        return new UpdateUserPersonalDetailsResponse();
    }

    /**
     * Create an instance of {@link FetchCategoriesResponse }
     * 
     */
    public FetchCategoriesResponse createFetchCategoriesResponse() {
        return new FetchCategoriesResponse();
    }

    /**
     * Create an instance of {@link LocationResponse }
     * 
     */
    public LocationResponse createLocationResponse() {
        return new LocationResponse();
    }

    /**
     * Create an instance of {@link FetchCharityOfflineRegDataRequest }
     * 
     */
    public FetchCharityOfflineRegDataRequest createFetchCharityOfflineRegDataRequest() {
        return new FetchCharityOfflineRegDataRequest();
    }

    /**
     * Create an instance of {@link ServiceFundraiserOrDonorDetails }
     * 
     */
    public ServiceFundraiserOrDonorDetails createServiceFundraiserOrDonorDetails() {
        return new ServiceFundraiserOrDonorDetails();
    }

    /**
     * Create an instance of {@link DonorPersonalDetailsResponse }
     * 
     */
    public DonorPersonalDetailsResponse createDonorPersonalDetailsResponse() {
        return new DonorPersonalDetailsResponse();
    }

    /**
     * Create an instance of {@link FindFundraiserGroupsbyFundraiserIdResponse }
     * 
     */
    public FindFundraiserGroupsbyFundraiserIdResponse createFindFundraiserGroupsbyFundraiserIdResponse() {
        return new FindFundraiserGroupsbyFundraiserIdResponse();
    }

    /**
     * Create an instance of {@link ServiceCommissionCharityDetails }
     * 
     */
    public ServiceCommissionCharityDetails createServiceCommissionCharityDetails() {
        return new ServiceCommissionCharityDetails();
    }

    /**
     * Create an instance of {@link FetchEventHomePageDetailsResponse }
     * 
     */
    public FetchEventHomePageDetailsResponse createFetchEventHomePageDetailsResponse() {
        return new FetchEventHomePageDetailsResponse();
    }

    /**
     * Create an instance of {@link VoidResponse }
     * 
     */
    public VoidResponse createVoidResponse() {
        return new VoidResponse();
    }

    /**
     * Create an instance of {@link SavePageDetailsResponse }
     * 
     */
    public SavePageDetailsResponse createSavePageDetailsResponse() {
        return new SavePageDetailsResponse();
    }

    /**
     * Create an instance of {@link ServiceCountry }
     * 
     */
    public ServiceCountry createServiceCountry() {
        return new ServiceCountry();
    }

    /**
     * Create an instance of {@link FetchRegisteredOperationUsersLoginNameResponse }
     * 
     */
    public FetchRegisteredOperationUsersLoginNameResponse createFetchRegisteredOperationUsersLoginNameResponse() {
        return new FetchRegisteredOperationUsersLoginNameResponse();
    }

    /**
     * Create an instance of {@link ReportAbuseEmailResponse }
     * 
     */
    public ReportAbuseEmailResponse createReportAbuseEmailResponse() {
        return new ReportAbuseEmailResponse();
    }

    /**
     * Create an instance of {@link CharityAddressResponse }
     * 
     */
    public CharityAddressResponse createCharityAddressResponse() {
        return new CharityAddressResponse();
    }

    /**
     * Create an instance of {@link ServiceDonorSecurityDetails }
     * 
     */
    public ServiceDonorSecurityDetails createServiceDonorSecurityDetails() {
        return new ServiceDonorSecurityDetails();
    }

    /**
     * Create an instance of {@link PayCharityRegistrationFeeResponse }
     * 
     */
    public PayCharityRegistrationFeeResponse createPayCharityRegistrationFeeResponse() {
        return new PayCharityRegistrationFeeResponse();
    }

    /**
     * Create an instance of {@link ServiceEmailAddressAndDateofBirthChanged }
     * 
     */
    public ServiceEmailAddressAndDateofBirthChanged createServiceEmailAddressAndDateofBirthChanged() {
        return new ServiceEmailAddressAndDateofBirthChanged();
    }

    /**
     * Create an instance of {@link CharityAdminUsersResponse }
     * 
     */
    public CharityAdminUsersResponse createCharityAdminUsersResponse() {
        return new CharityAdminUsersResponse();
    }

    /**
     * Create an instance of {@link FetchUserPermissionsRequest }
     * 
     */
    public FetchUserPermissionsRequest createFetchUserPermissionsRequest() {
        return new FetchUserPermissionsRequest();
    }

    /**
     * Create an instance of {@link RegisteredCharityUserService }
     * 
     */
    public RegisteredCharityUserService createRegisteredCharityUserService() {
        return new RegisteredCharityUserService();
    }

    /**
     * Create an instance of {@link ValidateDonationWithNewCardRequest }
     * 
     */
    public ValidateDonationWithNewCardRequest createValidateDonationWithNewCardRequest() {
        return new ValidateDonationWithNewCardRequest();
    }

    /**
     * Create an instance of {@link FundraiserTeamResponse }
     * 
     */
    public FundraiserTeamResponse createFundraiserTeamResponse() {
        return new FundraiserTeamResponse();
    }

    /**
     * Create an instance of {@link ValidateDonationWithNewCardResponse }
     * 
     */
    public ValidateDonationWithNewCardResponse createValidateDonationWithNewCardResponse() {
        return new ValidateDonationWithNewCardResponse();
    }

    /**
     * Create an instance of {@link Email }
     * 
     */
    public Email createEmail() {
        return new Email();
    }

    /**
     * Create an instance of {@link CharityAdminDetails }
     * 
     */
    public CharityAdminDetails createCharityAdminDetails() {
        return new CharityAdminDetails();
    }

    /**
     * Create an instance of {@link ServiceSearchCriteria }
     * 
     */
    public ServiceSearchCriteria createServiceSearchCriteria() {
        return new ServiceSearchCriteria();
    }

    /**
     * Create an instance of {@link GivingServiceAuthorisationResult }
     * 
     */
    public GivingServiceAuthorisationResult createGivingServiceAuthorisationResult() {
        return new GivingServiceAuthorisationResult();
    }

    /**
     * Create an instance of {@link CharityRegistrationResponse }
     * 
     */
    public CharityRegistrationResponse createCharityRegistrationResponse() {
        return new CharityRegistrationResponse();
    }

    /**
     * Create an instance of {@link SearchFundraisingEventResponse }
     * 
     */
    public SearchFundraisingEventResponse createSearchFundraisingEventResponse() {
        return new SearchFundraisingEventResponse();
    }

    /**
     * Create an instance of {@link FetchReportingSearchResultsResponse }
     * 
     */
    public FetchReportingSearchResultsResponse createFetchReportingSearchResultsResponse() {
        return new FetchReportingSearchResultsResponse();
    }

    /**
     * Create an instance of {@link CharityEventAdminListRequest }
     * 
     */
    public CharityEventAdminListRequest createCharityEventAdminListRequest() {
        return new CharityEventAdminListRequest();
    }

    /**
     * Create an instance of {@link MakeDonationWithActiveCardRequest }
     * 
     */
    public MakeDonationWithActiveCardRequest createMakeDonationWithActiveCardRequest() {
        return new MakeDonationWithActiveCardRequest();
    }

    /**
     * Create an instance of {@link FetchFundraiserDetailsForUserResponse }
     * 
     */
    public FetchFundraiserDetailsForUserResponse createFetchFundraiserDetailsForUserResponse() {
        return new FetchFundraiserDetailsForUserResponse();
    }

    /**
     * Create an instance of {@link FundraiserSearchCriteriaResponse }
     * 
     */
    public FundraiserSearchCriteriaResponse createFundraiserSearchCriteriaResponse() {
        return new FundraiserSearchCriteriaResponse();
    }

    /**
     * Create an instance of {@link CharityRegistrationSummaryDetailsRequest }
     * 
     */
    public CharityRegistrationSummaryDetailsRequest createCharityRegistrationSummaryDetailsRequest() {
        return new CharityRegistrationSummaryDetailsRequest();
    }

    /**
     * Create an instance of {@link DefaultUrlRequest }
     * 
     */
    public DefaultUrlRequest createDefaultUrlRequest() {
        return new DefaultUrlRequest();
    }

    /**
     * Create an instance of {@link FetchFundraiserActivityDonationRequest }
     * 
     */
    public FetchFundraiserActivityDonationRequest createFetchFundraiserActivityDonationRequest() {
        return new FetchFundraiserActivityDonationRequest();
    }

    /**
     * Create an instance of {@link ServiceCharitiesByCharityCategoryId }
     * 
     */
    public ServiceCharitiesByCharityCategoryId createServiceCharitiesByCharityCategoryId() {
        return new ServiceCharitiesByCharityCategoryId();
    }

    /**
     * Create an instance of {@link ReportAbuseEmailRequest }
     * 
     */
    public ReportAbuseEmailRequest createReportAbuseEmailRequest() {
        return new ReportAbuseEmailRequest();
    }

    /**
     * Create an instance of {@link FetchUserDetailsByIdResponse }
     * 
     */
    public FetchUserDetailsByIdResponse createFetchUserDetailsByIdResponse() {
        return new FetchUserDetailsByIdResponse();
    }

    /**
     * Create an instance of {@link FetchPageDetailsByIdRequest }
     * 
     */
    public FetchPageDetailsByIdRequest createFetchPageDetailsByIdRequest() {
        return new FetchPageDetailsByIdRequest();
    }

    /**
     * Create an instance of {@link SaveAlertTriggersResponse }
     * 
     */
    public SaveAlertTriggersResponse createSaveAlertTriggersResponse() {
        return new SaveAlertTriggersResponse();
    }

    /**
     * Create an instance of {@link SaveTrusteeCountRequest }
     * 
     */
    public SaveTrusteeCountRequest createSaveTrusteeCountRequest() {
        return new SaveTrusteeCountRequest();
    }

    /**
     * Create an instance of {@link FetchEmailForSettlementResponse }
     * 
     */
    public FetchEmailForSettlementResponse createFetchEmailForSettlementResponse() {
        return new FetchEmailForSettlementResponse();
    }

    /**
     * Create an instance of {@link ServiceOfflineRegStatus }
     * 
     */
    public ServiceOfflineRegStatus createServiceOfflineRegStatus() {
        return new ServiceOfflineRegStatus();
    }

    /**
     * Create an instance of {@link CharitySearchCriteriaRequest }
     * 
     */
    public CharitySearchCriteriaRequest createCharitySearchCriteriaRequest() {
        return new CharitySearchCriteriaRequest();
    }

    /**
     * Create an instance of {@link FetchDonorRequest }
     * 
     */
    public FetchDonorRequest createFetchDonorRequest() {
        return new FetchDonorRequest();
    }

    /**
     * Create an instance of {@link FetchFundraiserActivityDonationResponse }
     * 
     */
    public FetchFundraiserActivityDonationResponse createFetchFundraiserActivityDonationResponse() {
        return new FetchFundraiserActivityDonationResponse();
    }

    /**
     * Create an instance of {@link GetTrusteeCountRequest }
     * 
     */
    public GetTrusteeCountRequest createGetTrusteeCountRequest() {
        return new GetTrusteeCountRequest();
    }

    /**
     * Create an instance of {@link EventRequest }
     * 
     */
    public EventRequest createEventRequest() {
        return new EventRequest();
    }

    /**
     * Create an instance of {@link FundraiserCloseAccountRequest }
     * 
     */
    public FundraiserCloseAccountRequest createFundraiserCloseAccountRequest() {
        return new FundraiserCloseAccountRequest();
    }

    /**
     * Create an instance of {@link FetchPageWidgetTypeFromPageTypeResponse }
     * 
     */
    public FetchPageWidgetTypeFromPageTypeResponse createFetchPageWidgetTypeFromPageTypeResponse() {
        return new FetchPageWidgetTypeFromPageTypeResponse();
    }

    /**
     * Create an instance of {@link SaveCharityOfflineRegDataResponse }
     * 
     */
    public SaveCharityOfflineRegDataResponse createSaveCharityOfflineRegDataResponse() {
        return new SaveCharityOfflineRegDataResponse();
    }

    /**
     * Create an instance of {@link ServiceFundraiserSubset }
     * 
     */
    public ServiceFundraiserSubset createServiceFundraiserSubset() {
        return new ServiceFundraiserSubset();
    }

    /**
     * Create an instance of {@link CharityRegistrationSummaryDetailsResponse }
     * 
     */
    public CharityRegistrationSummaryDetailsResponse createCharityRegistrationSummaryDetailsResponse() {
        return new CharityRegistrationSummaryDetailsResponse();
    }

    /**
     * Create an instance of {@link UpdateBankDescriptionAndTypeRequest }
     * 
     */
    public UpdateBankDescriptionAndTypeRequest createUpdateBankDescriptionAndTypeRequest() {
        return new UpdateBankDescriptionAndTypeRequest();
    }

    /**
     * Create an instance of {@link ServiceBankAccount }
     * 
     */
    public ServiceBankAccount createServiceBankAccount() {
        return new ServiceBankAccount();
    }

    /**
     * Create an instance of {@link AlternateUrlForFundraiserResponse }
     * 
     */
    public AlternateUrlForFundraiserResponse createAlternateUrlForFundraiserResponse() {
        return new AlternateUrlForFundraiserResponse();
    }

    /**
     * Create an instance of {@link ServiceFundraiserDonationDetails }
     * 
     */
    public ServiceFundraiserDonationDetails createServiceFundraiserDonationDetails() {
        return new ServiceFundraiserDonationDetails();
    }

    /**
     * Create an instance of {@link ValidatePaymentWithNewCardResponse }
     * 
     */
    public ValidatePaymentWithNewCardResponse createValidatePaymentWithNewCardResponse() {
        return new ValidatePaymentWithNewCardResponse();
    }

    /**
     * Create an instance of {@link PayEventFeeWithNewCardRequest }
     * 
     */
    public PayEventFeeWithNewCardRequest createPayEventFeeWithNewCardRequest() {
        return new PayEventFeeWithNewCardRequest();
    }

    /**
     * Create an instance of {@link GetUserAccessRWCountResponse }
     * 
     */
    public GetUserAccessRWCountResponse createGetUserAccessRWCountResponse() {
        return new GetUserAccessRWCountResponse();
    }

    /**
     * Create an instance of {@link GivingServiceEventFeePayDetails }
     * 
     */
    public GivingServiceEventFeePayDetails createGivingServiceEventFeePayDetails() {
        return new GivingServiceEventFeePayDetails();
    }

    /**
     * Create an instance of {@link UpdateThankYouMessageResponse }
     * 
     */
    public UpdateThankYouMessageResponse createUpdateThankYouMessageResponse() {
        return new UpdateThankYouMessageResponse();
    }

    /**
     * Create an instance of {@link ServiceRegularDonationDetails }
     * 
     */
    public ServiceRegularDonationDetails createServiceRegularDonationDetails() {
        return new ServiceRegularDonationDetails();
    }

    /**
     * Create an instance of {@link CharityRegisterVerificationRequest }
     * 
     */
    public CharityRegisterVerificationRequest createCharityRegisterVerificationRequest() {
        return new CharityRegisterVerificationRequest();
    }

    /**
     * Create an instance of {@link UpdateRegularDonationRequest }
     * 
     */
    public UpdateRegularDonationRequest createUpdateRegularDonationRequest() {
        return new UpdateRegularDonationRequest();
    }

    /**
     * Create an instance of {@link SaveFundraiserEventFeeResponse }
     * 
     */
    public SaveFundraiserEventFeeResponse createSaveFundraiserEventFeeResponse() {
        return new SaveFundraiserEventFeeResponse();
    }

    /**
     * Create an instance of {@link SaveCharityBankDetailsResponse }
     * 
     */
    public SaveCharityBankDetailsResponse createSaveCharityBankDetailsResponse() {
        return new SaveCharityBankDetailsResponse();
    }

    /**
     * Create an instance of {@link FetchFundraiserActivityDonationCountRequest }
     * 
     */
    public FetchFundraiserActivityDonationCountRequest createFetchFundraiserActivityDonationCountRequest() {
        return new FetchFundraiserActivityDonationCountRequest();
    }

    /**
     * Create an instance of {@link CharityStatusResponse }
     * 
     */
    public CharityStatusResponse createCharityStatusResponse() {
        return new CharityStatusResponse();
    }

    /**
     * Create an instance of {@link UpdateThankYouMessageRequest }
     * 
     */
    public UpdateThankYouMessageRequest createUpdateThankYouMessageRequest() {
        return new UpdateThankYouMessageRequest();
    }

    /**
     * Create an instance of {@link ServiceCharityCategory }
     * 
     */
    public ServiceCharityCategory createServiceCharityCategory() {
        return new ServiceCharityCategory();
    }

    /**
     * Create an instance of {@link FetchCharityBankDetailsRequest }
     * 
     */
    public FetchCharityBankDetailsRequest createFetchCharityBankDetailsRequest() {
        return new FetchCharityBankDetailsRequest();
    }

    /**
     * Create an instance of {@link FundraiserDonationDetailsRequest }
     * 
     */
    public FundraiserDonationDetailsRequest createFundraiserDonationDetailsRequest() {
        return new FundraiserDonationDetailsRequest();
    }

    /**
     * Create an instance of {@link ValidatePaymentWithNewCardRequest }
     * 
     */
    public ValidatePaymentWithNewCardRequest createValidatePaymentWithNewCardRequest() {
        return new ValidatePaymentWithNewCardRequest();
    }

    /**
     * Create an instance of {@link FindCharityAdministratorUsersResponse }
     * 
     */
    public FindCharityAdministratorUsersResponse createFindCharityAdministratorUsersResponse() {
        return new FindCharityAdministratorUsersResponse();
    }

    /**
     * Create an instance of {@link ServiceAdministrator }
     * 
     */
    public ServiceAdministrator createServiceAdministrator() {
        return new ServiceAdministrator();
    }

    /**
     * Create an instance of {@link FetchModuleDataRequest }
     * 
     */
    public FetchModuleDataRequest createFetchModuleDataRequest() {
        return new FetchModuleDataRequest();
    }

    /**
     * Create an instance of {@link UrlAvailabilityRequest }
     * 
     */
    public UrlAvailabilityRequest createUrlAvailabilityRequest() {
        return new UrlAvailabilityRequest();
    }

    /**
     * Create an instance of {@link PaymentCardDetailsResponse }
     * 
     */
    public PaymentCardDetailsResponse createPaymentCardDetailsResponse() {
        return new PaymentCardDetailsResponse();
    }

    /**
     * Create an instance of {@link UpdateCharityBankDetailsStatusResponse }
     * 
     */
    public UpdateCharityBankDetailsStatusResponse createUpdateCharityBankDetailsStatusResponse() {
        return new UpdateCharityBankDetailsStatusResponse();
    }

    /**
     * Create an instance of {@link FetchPageDetailsRequest }
     * 
     */
    public FetchPageDetailsRequest createFetchPageDetailsRequest() {
        return new FetchPageDetailsRequest();
    }

    /**
     * Create an instance of {@link PutCharityLiveResponse }
     * 
     */
    public PutCharityLiveResponse createPutCharityLiveResponse() {
        return new PutCharityLiveResponse();
    }

    /**
     * Create an instance of {@link ServiceUserRoleDetails }
     * 
     */
    public ServiceUserRoleDetails createServiceUserRoleDetails() {
        return new ServiceUserRoleDetails();
    }

    /**
     * Create an instance of {@link SaveCharityStatusInfoResponse }
     * 
     */
    public SaveCharityStatusInfoResponse createSaveCharityStatusInfoResponse() {
        return new SaveCharityStatusInfoResponse();
    }

    /**
     * Create an instance of {@link FetchRegisteredUsersOfCharityByIdRequest }
     * 
     */
    public FetchRegisteredUsersOfCharityByIdRequest createFetchRegisteredUsersOfCharityByIdRequest() {
        return new FetchRegisteredUsersOfCharityByIdRequest();
    }

    /**
     * Create an instance of {@link FetchTransitionalReliefAmountforSettlementRequest }
     * 
     */
    public FetchTransitionalReliefAmountforSettlementRequest createFetchTransitionalReliefAmountforSettlementRequest() {
        return new FetchTransitionalReliefAmountforSettlementRequest();
    }

    /**
     * Create an instance of {@link ServiceRemoveCharityBankAccount }
     * 
     */
    public ServiceRemoveCharityBankAccount createServiceRemoveCharityBankAccount() {
        return new ServiceRemoveCharityBankAccount();
    }

    /**
     * Create an instance of {@link ClaimEmailRequest }
     * 
     */
    public ClaimEmailRequest createClaimEmailRequest() {
        return new ClaimEmailRequest();
    }

    /**
     * Create an instance of {@link MessageHeader }
     * 
     */
    public MessageHeader createMessageHeader() {
        return new MessageHeader();
    }

    /**
     * Create an instance of {@link PageWidgetRequest }
     * 
     */
    public PageWidgetRequest createPageWidgetRequest() {
        return new PageWidgetRequest();
    }

    /**
     * Create an instance of {@link FetchBiggestFundraiserActivityDonationRequest }
     * 
     */
    public FetchBiggestFundraiserActivityDonationRequest createFetchBiggestFundraiserActivityDonationRequest() {
        return new FetchBiggestFundraiserActivityDonationRequest();
    }

    /**
     * Create an instance of {@link GivingServiceNameValuePair }
     * 
     */
    public GivingServiceNameValuePair createGivingServiceNameValuePair() {
        return new GivingServiceNameValuePair();
    }

    /**
     * Create an instance of {@link PayEventFeeWithActiveCardRequest }
     * 
     */
    public PayEventFeeWithActiveCardRequest createPayEventFeeWithActiveCardRequest() {
        return new PayEventFeeWithActiveCardRequest();
    }

    /**
     * Create an instance of {@link DeleteTrusteeDetailsRequest }
     * 
     */
    public DeleteTrusteeDetailsRequest createDeleteTrusteeDetailsRequest() {
        return new DeleteTrusteeDetailsRequest();
    }

    /**
     * Create an instance of {@link CharityTrusteeDetailsRequest }
     * 
     */
    public CharityTrusteeDetailsRequest createCharityTrusteeDetailsRequest() {
        return new CharityTrusteeDetailsRequest();
    }

    /**
     * Create an instance of {@link SaveCustomFieldValuesRequest }
     * 
     */
    public SaveCustomFieldValuesRequest createSaveCustomFieldValuesRequest() {
        return new SaveCustomFieldValuesRequest();
    }

    /**
     * Create an instance of {@link CreateEventRequest }
     * 
     */
    public CreateEventRequest createCreateEventRequest() {
        return new CreateEventRequest();
    }

    /**
     * Create an instance of {@link EmailContent }
     * 
     */
    public EmailContent createEmailContent() {
        return new EmailContent();
    }

    /**
     * Create an instance of {@link ServiceFundraiserDetailsDVO }
     * 
     */
    public ServiceFundraiserDetailsDVO createServiceFundraiserDetailsDVO() {
        return new ServiceFundraiserDetailsDVO();
    }

    /**
     * Create an instance of {@link ServiceSendEmailToFriends }
     * 
     */
    public ServiceSendEmailToFriends createServiceSendEmailToFriends() {
        return new ServiceSendEmailToFriends();
    }

    /**
     * Create an instance of {@link DeleteCharityUserResponse }
     * 
     */
    public DeleteCharityUserResponse createDeleteCharityUserResponse() {
        return new DeleteCharityUserResponse();
    }

    /**
     * Create an instance of {@link FundraiserRegistrationFormResponse }
     * 
     */
    public FundraiserRegistrationFormResponse createFundraiserRegistrationFormResponse() {
        return new FundraiserRegistrationFormResponse();
    }

    /**
     * Create an instance of {@link ServiceOnlyDateofBirthChanged }
     * 
     */
    public ServiceOnlyDateofBirthChanged createServiceOnlyDateofBirthChanged() {
        return new ServiceOnlyDateofBirthChanged();
    }

    /**
     * Create an instance of {@link ServiceCharityAdditionalDetails }
     * 
     */
    public ServiceCharityAdditionalDetails createServiceCharityAdditionalDetails() {
        return new ServiceCharityAdditionalDetails();
    }

    /**
     * Create an instance of {@link FetchTeamMemberRequest }
     * 
     */
    public FetchTeamMemberRequest createFetchTeamMemberRequest() {
        return new FetchTeamMemberRequest();
    }

    /**
     * Create an instance of {@link OwnedEventsSearchResponse }
     * 
     */
    public OwnedEventsSearchResponse createOwnedEventsSearchResponse() {
        return new OwnedEventsSearchResponse();
    }

    /**
     * Create an instance of {@link DonorPersonalDetailsRequest }
     * 
     */
    public DonorPersonalDetailsRequest createDonorPersonalDetailsRequest() {
        return new DonorPersonalDetailsRequest();
    }

    /**
     * Create an instance of {@link ServiceDonationDetails }
     * 
     */
    public ServiceDonationDetails createServiceDonationDetails() {
        return new ServiceDonationDetails();
    }

    /**
     * Create an instance of {@link FetchFundraiserDetailsByEventIdResponse }
     * 
     */
    public FetchFundraiserDetailsByEventIdResponse createFetchFundraiserDetailsByEventIdResponse() {
        return new FetchFundraiserDetailsByEventIdResponse();
    }

    /**
     * Create an instance of {@link UrlAvailabilityResponse }
     * 
     */
    public UrlAvailabilityResponse createUrlAvailabilityResponse() {
        return new UrlAvailabilityResponse();
    }

    /**
     * Create an instance of {@link SaveCharityStatusInfoRequest }
     * 
     */
    public SaveCharityStatusInfoRequest createSaveCharityStatusInfoRequest() {
        return new SaveCharityStatusInfoRequest();
    }

    /**
     * Create an instance of {@link ViewEventRequest }
     * 
     */
    public ViewEventRequest createViewEventRequest() {
        return new ViewEventRequest();
    }

    /**
     * Create an instance of {@link ServiceEmailAddress }
     * 
     */
    public ServiceEmailAddress createServiceEmailAddress() {
        return new ServiceEmailAddress();
    }

    /**
     * Create an instance of {@link FundraiserTeamMemberResponse }
     * 
     */
    public FundraiserTeamMemberResponse createFundraiserTeamMemberResponse() {
        return new FundraiserTeamMemberResponse();
    }

    /**
     * Create an instance of {@link DeleteCharityUserRequest }
     * 
     */
    public DeleteCharityUserRequest createDeleteCharityUserRequest() {
        return new DeleteCharityUserRequest();
    }

    /**
     * Create an instance of {@link CharityHomePageRegistrationResponse }
     * 
     */
    public CharityHomePageRegistrationResponse createCharityHomePageRegistrationResponse() {
        return new CharityHomePageRegistrationResponse();
    }

    /**
     * Create an instance of {@link CharityRegistrationFeePayResult }
     * 
     */
    public CharityRegistrationFeePayResult createCharityRegistrationFeePayResult() {
        return new CharityRegistrationFeePayResult();
    }

    /**
     * Create an instance of {@link ViewEventResponse }
     * 
     */
    public ViewEventResponse createViewEventResponse() {
        return new ViewEventResponse();
    }

    /**
     * Create an instance of {@link SaveAlertTriggersRequest }
     * 
     */
    public SaveAlertTriggersRequest createSaveAlertTriggersRequest() {
        return new SaveAlertTriggersRequest();
    }

    /**
     * Create an instance of {@link ServiceFundraiserActivity }
     * 
     */
    public ServiceFundraiserActivity createServiceFundraiserActivity() {
        return new ServiceFundraiserActivity();
    }

    /**
     * Create an instance of {@link ServiceCharityBankAccountType }
     * 
     */
    public ServiceCharityBankAccountType createServiceCharityBankAccountType() {
        return new ServiceCharityBankAccountType();
    }

    /**
     * Create an instance of {@link ServiceFundraiserTeam }
     * 
     */
    public ServiceFundraiserTeam createServiceFundraiserTeam() {
        return new ServiceFundraiserTeam();
    }

    /**
     * Create an instance of {@link DefaultUrlForFundraiserRequest }
     * 
     */
    public DefaultUrlForFundraiserRequest createDefaultUrlForFundraiserRequest() {
        return new DefaultUrlForFundraiserRequest();
    }

    /**
     * Create an instance of {@link ServiceProfanityCheckResult }
     * 
     */
    public ServiceProfanityCheckResult createServiceProfanityCheckResult() {
        return new ServiceProfanityCheckResult();
    }

    /**
     * Create an instance of {@link SaveCharityAdministratorResponse }
     * 
     */
    public SaveCharityAdministratorResponse createSaveCharityAdministratorResponse() {
        return new SaveCharityAdministratorResponse();
    }

    /**
     * Create an instance of {@link CheckForProfanityRequest }
     * 
     */
    public CheckForProfanityRequest createCheckForProfanityRequest() {
        return new CheckForProfanityRequest();
    }

    /**
     * Create an instance of {@link FetchRegisteredUsersOfCharityByIdResponse }
     * 
     */
    public FetchRegisteredUsersOfCharityByIdResponse createFetchRegisteredUsersOfCharityByIdResponse() {
        return new FetchRegisteredUsersOfCharityByIdResponse();
    }

    /**
     * Create an instance of {@link IsFundraiserUrlAvailableRequest }
     * 
     */
    public IsFundraiserUrlAvailableRequest createIsFundraiserUrlAvailableRequest() {
        return new IsFundraiserUrlAvailableRequest();
    }

    /**
     * Create an instance of {@link ServiceActivityType }
     * 
     */
    public ServiceActivityType createServiceActivityType() {
        return new ServiceActivityType();
    }

    /**
     * Create an instance of {@link ServiceDonorDetailsDVO }
     * 
     */
    public ServiceDonorDetailsDVO createServiceDonorDetailsDVO() {
        return new ServiceDonorDetailsDVO();
    }

    /**
     * Create an instance of {@link CharityDescriptionRequest }
     * 
     */
    public CharityDescriptionRequest createCharityDescriptionRequest() {
        return new CharityDescriptionRequest();
    }

    /**
     * Create an instance of {@link ActivityTypeResponse }
     * 
     */
    public ActivityTypeResponse createActivityTypeResponse() {
        return new ActivityTypeResponse();
    }

    /**
     * Create an instance of {@link ServiceCharityAdministrator }
     * 
     */
    public ServiceCharityAdministrator createServiceCharityAdministrator() {
        return new ServiceCharityAdministrator();
    }

    /**
     * Create an instance of {@link MakeDonationWithActiveCardResponse }
     * 
     */
    public MakeDonationWithActiveCardResponse createMakeDonationWithActiveCardResponse() {
        return new MakeDonationWithActiveCardResponse();
    }

    /**
     * Create an instance of {@link VmgCharityURLExistRequest }
     * 
     */
    public VmgCharityURLExistRequest createVmgCharityURLExistRequest() {
        return new VmgCharityURLExistRequest();
    }

    /**
     * Create an instance of {@link FindCharityByNameRequest }
     * 
     */
    public FindCharityByNameRequest createFindCharityByNameRequest() {
        return new FindCharityByNameRequest();
    }

    /**
     * Create an instance of {@link FetchDonorResponse }
     * 
     */
    public FetchDonorResponse createFetchDonorResponse() {
        return new FetchDonorResponse();
    }

    /**
     * Create an instance of {@link LocationRequest }
     * 
     */
    public LocationRequest createLocationRequest() {
        return new LocationRequest();
    }

    /**
     * Create an instance of {@link EventsSearchResponse }
     * 
     */
    public EventsSearchResponse createEventsSearchResponse() {
        return new EventsSearchResponse();
    }

    /**
     * Create an instance of {@link SearchFundraisingEventRequest }
     * 
     */
    public SearchFundraisingEventRequest createSearchFundraisingEventRequest() {
        return new SearchFundraisingEventRequest();
    }

    /**
     * Create an instance of {@link FundraiserTeamRequest }
     * 
     */
    public FundraiserTeamRequest createFundraiserTeamRequest() {
        return new FundraiserTeamRequest();
    }

    /**
     * Create an instance of {@link PayEventFeeWithActiveCardResponse }
     * 
     */
    public PayEventFeeWithActiveCardResponse createPayEventFeeWithActiveCardResponse() {
        return new PayEventFeeWithActiveCardResponse();
    }

    /**
     * Create an instance of {@link PaymentCardRequest }
     * 
     */
    public PaymentCardRequest createPaymentCardRequest() {
        return new PaymentCardRequest();
    }

    /**
     * Create an instance of {@link StoreUrlResponse }
     * 
     */
    public StoreUrlResponse createStoreUrlResponse() {
        return new StoreUrlResponse();
    }

    /**
     * Create an instance of {@link CheckForProfanityResponseDetails }
     * 
     */
    public CheckForProfanityResponseDetails createCheckForProfanityResponseDetails() {
        return new CheckForProfanityResponseDetails();
    }

    /**
     * Create an instance of {@link FetchModuleDataResponse }
     * 
     */
    public FetchModuleDataResponse createFetchModuleDataResponse() {
        return new FetchModuleDataResponse();
    }

    /**
     * Create an instance of {@link ServiceCharityDonationDetails }
     * 
     */
    public ServiceCharityDonationDetails createServiceCharityDonationDetails() {
        return new ServiceCharityDonationDetails();
    }

    /**
     * Create an instance of {@link SaveTrusteeDetailsResponse }
     * 
     */
    public SaveTrusteeDetailsResponse createSaveTrusteeDetailsResponse() {
        return new SaveTrusteeDetailsResponse();
    }

    /**
     * Create an instance of {@link EventResponse }
     * 
     */
    public EventResponse createEventResponse() {
        return new EventResponse();
    }

    /**
     * Create an instance of {@link ServiceCharitableActivity }
     * 
     */
    public ServiceCharitableActivity createServiceCharitableActivity() {
        return new ServiceCharitableActivity();
    }

    /**
     * Create an instance of {@link ContactDetails }
     * 
     */
    public ContactDetails createContactDetails() {
        return new ContactDetails();
    }

    /**
     * Create an instance of {@link FetchFundraiserActivityDonationCountResponse }
     * 
     */
    public FetchFundraiserActivityDonationCountResponse createFetchFundraiserActivityDonationCountResponse() {
        return new FetchFundraiserActivityDonationCountResponse();
    }

    /**
     * Create an instance of {@link FundraiserTeamMemberRequest }
     * 
     */
    public FundraiserTeamMemberRequest createFundraiserTeamMemberRequest() {
        return new FundraiserTeamMemberRequest();
    }

    /**
     * Create an instance of {@link ServiceAddress }
     * 
     */
    public ServiceAddress createServiceAddress() {
        return new ServiceAddress();
    }

    /**
     * Create an instance of {@link ServicePaymentCardDetails }
     * 
     */
    public ServicePaymentCardDetails createServicePaymentCardDetails() {
        return new ServicePaymentCardDetails();
    }

    /**
     * Create an instance of {@link FetchRegistrationFormsResponse }
     * 
     */
    public FetchRegistrationFormsResponse createFetchRegistrationFormsResponse() {
        return new FetchRegistrationFormsResponse();
    }

    /**
     * Create an instance of {@link ServiceFundraiserPageDetailsDVO }
     * 
     */
    public ServiceFundraiserPageDetailsDVO createServiceFundraiserPageDetailsDVO() {
        return new ServiceFundraiserPageDetailsDVO();
    }

    /**
     * Create an instance of {@link FindFundraiserGroupResponse }
     * 
     */
    public FindFundraiserGroupResponse createFindFundraiserGroupResponse() {
        return new FindFundraiserGroupResponse();
    }

    /**
     * Create an instance of {@link FetchVMGUserDetailsForExternalUserResponse }
     * 
     */
    public FetchVMGUserDetailsForExternalUserResponse createFetchVMGUserDetailsForExternalUserResponse() {
        return new FetchVMGUserDetailsForExternalUserResponse();
    }

    /**
     * Create an instance of {@link SaveCharityOfflineRegDataRequest }
     * 
     */
    public SaveCharityOfflineRegDataRequest createSaveCharityOfflineRegDataRequest() {
        return new SaveCharityOfflineRegDataRequest();
    }

    /**
     * Create an instance of {@link ServiceCharitySplitDetails }
     * 
     */
    public ServiceCharitySplitDetails createServiceCharitySplitDetails() {
        return new ServiceCharitySplitDetails();
    }

    /**
     * Create an instance of {@link AccountExistsRequest }
     * 
     */
    public AccountExistsRequest createAccountExistsRequest() {
        return new AccountExistsRequest();
    }

    /**
     * Create an instance of {@link GivingServiceCardDetails }
     * 
     */
    public GivingServiceCardDetails createGivingServiceCardDetails() {
        return new GivingServiceCardDetails();
    }

    /**
     * Create an instance of {@link FindCharityAdministratorUsersRequest }
     * 
     */
    public FindCharityAdministratorUsersRequest createFindCharityAdministratorUsersRequest() {
        return new FindCharityAdministratorUsersRequest();
    }

    /**
     * Create an instance of {@link ServiceRole }
     * 
     */
    public ServiceRole createServiceRole() {
        return new ServiceRole();
    }

    /**
     * Create an instance of {@link ServicePageDetail }
     * 
     */
    public ServicePageDetail createServicePageDetail() {
        return new ServicePageDetail();
    }

    /**
     * Create an instance of {@link FetchUserDetailsByIdRequest }
     * 
     */
    public FetchUserDetailsByIdRequest createFetchUserDetailsByIdRequest() {
        return new FetchUserDetailsByIdRequest();
    }

    /**
     * Create an instance of {@link FetchRegistrationFormsRequest }
     * 
     */
    public FetchRegistrationFormsRequest createFetchRegistrationFormsRequest() {
        return new FetchRegistrationFormsRequest();
    }

    /**
     * Create an instance of {@link UpdateBankDescriptionAndTypeResponse }
     * 
     */
    public UpdateBankDescriptionAndTypeResponse createUpdateBankDescriptionAndTypeResponse() {
        return new UpdateBankDescriptionAndTypeResponse();
    }

    /**
     * Create an instance of {@link FetchDonorIdByUserRoleIdRequest }
     * 
     */
    public FetchDonorIdByUserRoleIdRequest createFetchDonorIdByUserRoleIdRequest() {
        return new FetchDonorIdByUserRoleIdRequest();
    }

    /**
     * Create an instance of {@link FetchPersonDetailsResponse }
     * 
     */
    public FetchPersonDetailsResponse createFetchPersonDetailsResponse() {
        return new FetchPersonDetailsResponse();
    }

    /**
     * Create an instance of {@link ClaimEmailResponse }
     * 
     */
    public ClaimEmailResponse createClaimEmailResponse() {
        return new ClaimEmailResponse();
    }

    /**
     * Create an instance of {@link CharityRegistrationRequest }
     * 
     */
    public CharityRegistrationRequest createCharityRegistrationRequest() {
        return new CharityRegistrationRequest();
    }

    /**
     * Create an instance of {@link EventHomePageDetails }
     * 
     */
    public EventHomePageDetails createEventHomePageDetails() {
        return new EventHomePageDetails();
    }

    /**
     * Create an instance of {@link FindRegularDonationsByDonorRequest }
     * 
     */
    public FindRegularDonationsByDonorRequest createFindRegularDonationsByDonorRequest() {
        return new FindRegularDonationsByDonorRequest();
    }

    /**
     * Create an instance of {@link TransitionalReliefAmountSettlement }
     * 
     */
    public TransitionalReliefAmountSettlement createTransitionalReliefAmountSettlement() {
        return new TransitionalReliefAmountSettlement();
    }

    /**
     * Create an instance of {@link CharityDescriptionResponse }
     * 
     */
    public CharityDescriptionResponse createCharityDescriptionResponse() {
        return new CharityDescriptionResponse();
    }

    /**
     * Create an instance of {@link ServiceLocation }
     * 
     */
    public ServiceLocation createServiceLocation() {
        return new ServiceLocation();
    }

    /**
     * Create an instance of {@link IsFundraiserUrlAvailableResponse }
     * 
     */
    public IsFundraiserUrlAvailableResponse createIsFundraiserUrlAvailableResponse() {
        return new IsFundraiserUrlAvailableResponse();
    }

    /**
     * Create an instance of {@link CheckEventAttachedWithBankResponse }
     * 
     */
    public CheckEventAttachedWithBankResponse createCheckEventAttachedWithBankResponse() {
        return new CheckEventAttachedWithBankResponse();
    }

    /**
     * Create an instance of {@link GivingServiceDonationDetails }
     * 
     */
    public GivingServiceDonationDetails createGivingServiceDonationDetails() {
        return new GivingServiceDonationDetails();
    }

    /**
     * Create an instance of {@link FetchCharityOfflineRegDataResponse }
     * 
     */
    public FetchCharityOfflineRegDataResponse createFetchCharityOfflineRegDataResponse() {
        return new FetchCharityOfflineRegDataResponse();
    }

    /**
     * Create an instance of {@link EmailMessageRequest }
     * 
     */
    public EmailMessageRequest createEmailMessageRequest() {
        return new EmailMessageRequest();
    }

    /**
     * Create an instance of {@link SaveTrusteeStatusRequest }
     * 
     */
    public SaveTrusteeStatusRequest createSaveTrusteeStatusRequest() {
        return new SaveTrusteeStatusRequest();
    }

    /**
     * Create an instance of {@link ServiceFundraiserGroupDetail }
     * 
     */
    public ServiceFundraiserGroupDetail createServiceFundraiserGroupDetail() {
        return new ServiceFundraiserGroupDetail();
    }

    /**
     * Create an instance of {@link CharityAdditionalDetailsResponse }
     * 
     */
    public CharityAdditionalDetailsResponse createCharityAdditionalDetailsResponse() {
        return new CharityAdditionalDetailsResponse();
    }

    /**
     * Create an instance of {@link FetchRegisteredOperationUsersLoginNameRequest }
     * 
     */
    public FetchRegisteredOperationUsersLoginNameRequest createFetchRegisteredOperationUsersLoginNameRequest() {
        return new FetchRegisteredOperationUsersLoginNameRequest();
    }

    /**
     * Create an instance of {@link CharityStatusRequest }
     * 
     */
    public CharityStatusRequest createCharityStatusRequest() {
        return new CharityStatusRequest();
    }

    /**
     * Create an instance of {@link BasicResponse }
     * 
     */
    public BasicResponse createBasicResponse() {
        return new BasicResponse();
    }

    /**
     * Create an instance of {@link DefaultUrlForFundraiserResponse }
     * 
     */
    public DefaultUrlForFundraiserResponse createDefaultUrlForFundraiserResponse() {
        return new DefaultUrlForFundraiserResponse();
    }

    /**
     * Create an instance of {@link ServiceVMGUserDetails }
     * 
     */
    public ServiceVMGUserDetails createServiceVMGUserDetails() {
        return new ServiceVMGUserDetails();
    }

    /**
     * Create an instance of {@link ServiceCharityStatusInfo }
     * 
     */
    public ServiceCharityStatusInfo createServiceCharityStatusInfo() {
        return new ServiceCharityStatusInfo();
    }

    /**
     * Create an instance of {@link ServiceCharityHomePageRegistration }
     * 
     */
    public ServiceCharityHomePageRegistration createServiceCharityHomePageRegistration() {
        return new ServiceCharityHomePageRegistration();
    }

    /**
     * Create an instance of {@link ServiceCharityCustomField }
     * 
     */
    public ServiceCharityCustomField createServiceCharityCustomField() {
        return new ServiceCharityCustomField();
    }

    /**
     * Create an instance of {@link EmailBodyParameters }
     * 
     */
    public EmailBodyParameters createEmailBodyParameters() {
        return new EmailBodyParameters();
    }

    /**
     * Create an instance of {@link CharityAdditionalDetailsRequest }
     * 
     */
    public CharityAdditionalDetailsRequest createCharityAdditionalDetailsRequest() {
        return new CharityAdditionalDetailsRequest();
    }

    /**
     * Create an instance of {@link UpdateFundraiserActivityRequest }
     * 
     */
    public UpdateFundraiserActivityRequest createUpdateFundraiserActivityRequest() {
        return new UpdateFundraiserActivityRequest();
    }

    /**
     * Create an instance of {@link FundraiserRegistrationFormRequest }
     * 
     */
    public FundraiserRegistrationFormRequest createFundraiserRegistrationFormRequest() {
        return new FundraiserRegistrationFormRequest();
    }

    /**
     * Create an instance of {@link ServiceDonorPersonalDetails }
     * 
     */
    public ServiceDonorPersonalDetails createServiceDonorPersonalDetails() {
        return new ServiceDonorPersonalDetails();
    }

    /**
     * Create an instance of {@link UpdateCharityUserRequest }
     * 
     */
    public UpdateCharityUserRequest createUpdateCharityUserRequest() {
        return new UpdateCharityUserRequest();
    }

    /**
     * Create an instance of {@link WebServiceEvent }
     * 
     */
    public WebServiceEvent createWebServiceEvent() {
        return new WebServiceEvent();
    }

    /**
     * Create an instance of {@link FetchCharityCustomFieldsResponse }
     * 
     */
    public FetchCharityCustomFieldsResponse createFetchCharityCustomFieldsResponse() {
        return new FetchCharityCustomFieldsResponse();
    }

    /**
     * Create an instance of {@link SaveCharityAdministratorRequest }
     * 
     */
    public SaveCharityAdministratorRequest createSaveCharityAdministratorRequest() {
        return new SaveCharityAdministratorRequest();
    }

    /**
     * Create an instance of {@link FetchBankAccountTypeResponse }
     * 
     */
    public FetchBankAccountTypeResponse createFetchBankAccountTypeResponse() {
        return new FetchBankAccountTypeResponse();
    }

    /**
     * Create an instance of {@link UpdateDonorPersonalDetailsRequest }
     * 
     */
    public UpdateDonorPersonalDetailsRequest createUpdateDonorPersonalDetailsRequest() {
        return new UpdateDonorPersonalDetailsRequest();
    }

    /**
     * Create an instance of {@link ServiceCharityDetails }
     * 
     */
    public ServiceCharityDetails createServiceCharityDetails() {
        return new ServiceCharityDetails();
    }

    /**
     * Create an instance of {@link ServiceEventStatus }
     * 
     */
    public ServiceEventStatus createServiceEventStatus() {
        return new ServiceEventStatus();
    }

    /**
     * Create an instance of {@link FundraiserSearchCriteriaRequest }
     * 
     */
    public FundraiserSearchCriteriaRequest createFundraiserSearchCriteriaRequest() {
        return new FundraiserSearchCriteriaRequest();
    }

    /**
     * Create an instance of {@link UpdateCharityBankDetailsStatusRequest }
     * 
     */
    public UpdateCharityBankDetailsStatusRequest createUpdateCharityBankDetailsStatusRequest() {
        return new UpdateCharityBankDetailsStatusRequest();
    }

    /**
     * Create an instance of {@link ServiceCharityEventAdministrator }
     * 
     */
    public ServiceCharityEventAdministrator createServiceCharityEventAdministrator() {
        return new ServiceCharityEventAdministrator();
    }

    /**
     * Create an instance of {@link ServiceFundraiserActivityCharityFundSplit }
     * 
     */
    public ServiceFundraiserActivityCharityFundSplit createServiceFundraiserActivityCharityFundSplit() {
        return new ServiceFundraiserActivityCharityFundSplit();
    }

    /**
     * Create an instance of {@link SaveCustomFieldValuesResponse }
     * 
     */
    public SaveCustomFieldValuesResponse createSaveCustomFieldValuesResponse() {
        return new SaveCustomFieldValuesResponse();
    }

    /**
     * Create an instance of {@link ServiceEventFeeDetails }
     * 
     */
    public ServiceEventFeeDetails createServiceEventFeeDetails() {
        return new ServiceEventFeeDetails();
    }

    /**
     * Create an instance of {@link MakeDonationWithNewCardResponse }
     * 
     */
    public MakeDonationWithNewCardResponse createMakeDonationWithNewCardResponse() {
        return new MakeDonationWithNewCardResponse();
    }

    /**
     * Create an instance of {@link CancelEventRequest }
     * 
     */
    public CancelEventRequest createCancelEventRequest() {
        return new CancelEventRequest();
    }

    /**
     * Create an instance of {@link ErrorList }
     * 
     */
    public ErrorList createErrorList() {
        return new ErrorList();
    }

    /**
     * Create an instance of {@link ErrorMessage }
     * 
     */
    public ErrorMessage createErrorMessage() {
        return new ErrorMessage();
    }

    /**
     * Create an instance of {@link GivingServiceCardSecurityInformation }
     * 
     */
    public GivingServiceCardSecurityInformation createGivingServiceCardSecurityInformation() {
        return new GivingServiceCardSecurityInformation();
    }

    /**
     * Create an instance of {@link FindLiveCharitiesLikeNameRequest }
     * 
     */
    public FindLiveCharitiesLikeNameRequest createFindLiveCharitiesLikeNameRequest() {
        return new FindLiveCharitiesLikeNameRequest();
    }

    /**
     * Create an instance of {@link ActivityTypeRequest }
     * 
     */
    public ActivityTypeRequest createActivityTypeRequest() {
        return new ActivityTypeRequest();
    }

    /**
     * Create an instance of {@link FetchCharityDetailsByIdRequest }
     * 
     */
    public FetchCharityDetailsByIdRequest createFetchCharityDetailsByIdRequest() {
        return new FetchCharityDetailsByIdRequest();
    }

    /**
     * Create an instance of {@link ServiceCharitySubset }
     * 
     */
    public ServiceCharitySubset createServiceCharitySubset() {
        return new ServiceCharitySubset();
    }

    /**
     * Create an instance of {@link ServiceCharity }
     * 
     */
    public ServiceCharity createServiceCharity() {
        return new ServiceCharity();
    }

    /**
     * Create an instance of {@link MakeDonationWithNewCardRequest }
     * 
     */
    public MakeDonationWithNewCardRequest createMakeDonationWithNewCardRequest() {
        return new MakeDonationWithNewCardRequest();
    }

    /**
     * Create an instance of {@link ServiceFundraisingReason }
     * 
     */
    public ServiceFundraisingReason createServiceFundraisingReason() {
        return new ServiceFundraisingReason();
    }

    /**
     * Create an instance of {@link BusinessErrorMessage }
     * 
     */
    public BusinessErrorMessage createBusinessErrorMessage() {
        return new BusinessErrorMessage();
    }

    /**
     * Create an instance of {@link CharitiesByCharityCategoryIdResponse }
     * 
     */
    public CharitiesByCharityCategoryIdResponse createCharitiesByCharityCategoryIdResponse() {
        return new CharitiesByCharityCategoryIdResponse();
    }

    /**
     * Create an instance of {@link ServiceBankAccountType }
     * 
     */
    public ServiceBankAccountType createServiceBankAccountType() {
        return new ServiceBankAccountType();
    }

    /**
     * Create an instance of {@link ServiceFundraisingReasonFundSplit }
     * 
     */
    public ServiceFundraisingReasonFundSplit createServiceFundraisingReasonFundSplit() {
        return new ServiceFundraisingReasonFundSplit();
    }

    /**
     * Create an instance of {@link IsPaymentCardDetailsExistRequest }
     * 
     */
    public IsPaymentCardDetailsExistRequest createIsPaymentCardDetailsExistRequest() {
        return new IsPaymentCardDetailsExistRequest();
    }

    /**
     * Create an instance of {@link GrantPermissionsWithRolesResponse }
     * 
     */
    public GrantPermissionsWithRolesResponse createGrantPermissionsWithRolesResponse() {
        return new GrantPermissionsWithRolesResponse();
    }

    /**
     * Create an instance of {@link ServiceModuleData }
     * 
     */
    public ServiceModuleData createServiceModuleData() {
        return new ServiceModuleData();
    }

    /**
     * Create an instance of {@link FetchCharityBankDetailsResponse }
     * 
     */
    public FetchCharityBankDetailsResponse createFetchCharityBankDetailsResponse() {
        return new FetchCharityBankDetailsResponse();
    }

    /**
     * Create an instance of {@link SaveCharityCustomFieldsRequest }
     * 
     */
    public SaveCharityCustomFieldsRequest createSaveCharityCustomFieldsRequest() {
        return new SaveCharityCustomFieldsRequest();
    }

    /**
     * Create an instance of {@link ServiceFundraiserTeamMember }
     * 
     */
    public ServiceFundraiserTeamMember createServiceFundraiserTeamMember() {
        return new ServiceFundraiserTeamMember();
    }

    /**
     * Create an instance of {@link StoreUrlRequest }
     * 
     */
    public StoreUrlRequest createStoreUrlRequest() {
        return new StoreUrlRequest();
    }

    /**
     * Create an instance of {@link ServicePageDetails }
     * 
     */
    public ServicePageDetails createServicePageDetails() {
        return new ServicePageDetails();
    }

    /**
     * Create an instance of {@link FetchFundraiserDetailsByEventIdRequest }
     * 
     */
    public FetchFundraiserDetailsByEventIdRequest createFetchFundraiserDetailsByEventIdRequest() {
        return new FetchFundraiserDetailsByEventIdRequest();
    }

    /**
     * Create an instance of {@link CharityHMRCDetails }
     * 
     */
    public CharityHMRCDetails createCharityHMRCDetails() {
        return new CharityHMRCDetails();
    }

    /**
     * Create an instance of {@link FetchPageDetailsResponse }
     * 
     */
    public FetchPageDetailsResponse createFetchPageDetailsResponse() {
        return new FetchPageDetailsResponse();
    }

    /**
     * Create an instance of {@link EventFeeStatusResponse }
     * 
     */
    public EventFeeStatusResponse createEventFeeStatusResponse() {
        return new EventFeeStatusResponse();
    }

    /**
     * Create an instance of {@link FetchCharityDetailsByVmgCharityUrlRequest }
     * 
     */
    public FetchCharityDetailsByVmgCharityUrlRequest createFetchCharityDetailsByVmgCharityUrlRequest() {
        return new FetchCharityDetailsByVmgCharityUrlRequest();
    }

    /**
     * Create an instance of {@link FundraiserDonationDetailsResponse }
     * 
     */
    public FundraiserDonationDetailsResponse createFundraiserDonationDetailsResponse() {
        return new FundraiserDonationDetailsResponse();
    }

    /**
     * Create an instance of {@link CardTypesResponse }
     * 
     */
    public CardTypesResponse createCardTypesResponse() {
        return new CardTypesResponse();
    }

    /**
     * Create an instance of {@link TeamMemberDetail }
     * 
     */
    public TeamMemberDetail createTeamMemberDetail() {
        return new TeamMemberDetail();
    }

    /**
     * Create an instance of {@link EventFeeListResponse }
     * 
     */
    public EventFeeListResponse createEventFeeListResponse() {
        return new EventFeeListResponse();
    }

    /**
     * Create an instance of {@link CharitySearchCriteriaResponse }
     * 
     */
    public CharitySearchCriteriaResponse createCharitySearchCriteriaResponse() {
        return new CharitySearchCriteriaResponse();
    }

    /**
     * Create an instance of {@link SearchRegisteredFundraiserByEmailAddressRequest }
     * 
     */
    public SearchRegisteredFundraiserByEmailAddressRequest createSearchRegisteredFundraiserByEmailAddressRequest() {
        return new SearchRegisteredFundraiserByEmailAddressRequest();
    }

    /**
     * Create an instance of {@link GrantPermissionsResponse }
     * 
     */
    public GrantPermissionsResponse createGrantPermissionsResponse() {
        return new GrantPermissionsResponse();
    }

    /**
     * Create an instance of {@link FetchCharityDetailsByIdsRequest }
     * 
     */
    public FetchCharityDetailsByIdsRequest createFetchCharityDetailsByIdsRequest() {
        return new FetchCharityDetailsByIdsRequest();
    }

    /**
     * Create an instance of {@link UpdateCharityUserResponse }
     * 
     */
    public UpdateCharityUserResponse createUpdateCharityUserResponse() {
        return new UpdateCharityUserResponse();
    }

    /**
     * Create an instance of {@link ServiceFundraiserSecurityDetails }
     * 
     */
    public ServiceFundraiserSecurityDetails createServiceFundraiserSecurityDetails() {
        return new ServiceFundraiserSecurityDetails();
    }

    /**
     * Create an instance of {@link ServiceFundraiser }
     * 
     */
    public ServiceFundraiser createServiceFundraiser() {
        return new ServiceFundraiser();
    }

    /**
     * Create an instance of {@link FetchUserRolesResponse }
     * 
     */
    public FetchUserRolesResponse createFetchUserRolesResponse() {
        return new FetchUserRolesResponse();
    }

    /**
     * Create an instance of {@link CardTypesRequest }
     * 
     */
    public CardTypesRequest createCardTypesRequest() {
        return new CardTypesRequest();
    }

    /**
     * Create an instance of {@link ServiceSearchFundraisingEventParameter }
     * 
     */
    public ServiceSearchFundraisingEventParameter createServiceSearchFundraisingEventParameter() {
        return new ServiceSearchFundraisingEventParameter();
    }

    /**
     * Create an instance of {@link DefaultUrlResponse }
     * 
     */
    public DefaultUrlResponse createDefaultUrlResponse() {
        return new DefaultUrlResponse();
    }

    /**
     * Create an instance of {@link GivingServiceEventFeePayResult }
     * 
     */
    public GivingServiceEventFeePayResult createGivingServiceEventFeePayResult() {
        return new GivingServiceEventFeePayResult();
    }

    /**
     * Create an instance of {@link FetchEmailForSettlementRequest }
     * 
     */
    public FetchEmailForSettlementRequest createFetchEmailForSettlementRequest() {
        return new FetchEmailForSettlementRequest();
    }

    /**
     * Create an instance of {@link FetchFundraiserDetailsRequest }
     * 
     */
    public FetchFundraiserDetailsRequest createFetchFundraiserDetailsRequest() {
        return new FetchFundraiserDetailsRequest();
    }

    /**
     * Create an instance of {@link SaveCharityBankDetailsRequest }
     * 
     */
    public SaveCharityBankDetailsRequest createSaveCharityBankDetailsRequest() {
        return new SaveCharityBankDetailsRequest();
    }

    /**
     * Create an instance of {@link UpdateRegularDonationResponse }
     * 
     */
    public UpdateRegularDonationResponse createUpdateRegularDonationResponse() {
        return new UpdateRegularDonationResponse();
    }

    /**
     * Create an instance of {@link FetchFundraiserDetailsResponse }
     * 
     */
    public FetchFundraiserDetailsResponse createFetchFundraiserDetailsResponse() {
        return new FetchFundraiserDetailsResponse();
    }

    /**
     * Create an instance of {@link ServiceUserLoginDetails }
     * 
     */
    public ServiceUserLoginDetails createServiceUserLoginDetails() {
        return new ServiceUserLoginDetails();
    }

    /**
     * Create an instance of {@link FetchReportingSearchResultsRequest }
     * 
     */
    public FetchReportingSearchResultsRequest createFetchReportingSearchResultsRequest() {
        return new FetchReportingSearchResultsRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", name = "fundraisingReasonResponse")
    public JAXBElement<Long> createFundraisingReasonResponse(Long value) {
        return new JAXBElement<Long>(_FundraisingReasonResponse_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/charity/", name = "vmgCharityURLExistResponse")
    public JAXBElement<Boolean> createVmgCharityURLExistResponse(Boolean value) {
        return new JAXBElement<Boolean>(_VmgCharityURLExistResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/header/", name = "MessageHeader")
    public JAXBElement<MessageHeader> createMessageHeader(MessageHeader value) {
        return new JAXBElement<MessageHeader>(_MessageHeader_QNAME, MessageHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/charity/", name = "resetUserPasswordResponse")
    public JAXBElement<Boolean> createResetUserPasswordResponse(Boolean value) {
        return new JAXBElement<Boolean>(_ResetUserPasswordResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/charity/", name = "getTrusteeCountResponse")
    public JAXBElement<Integer> createGetTrusteeCountResponse(Integer value) {
        return new JAXBElement<Integer>(_GetTrusteeCountResponse_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", name = "fetchTransitionalReliefAmountforSettlementResponse")
    public JAXBElement<Boolean> createFetchTransitionalReliefAmountforSettlementResponse(Boolean value) {
        return new JAXBElement<Boolean>(_FetchTransitionalReliefAmountforSettlementResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", name = "isPaymentCardDetailsExistResponse")
    public JAXBElement<Boolean> createIsPaymentCardDetailsExistResponse(Boolean value) {
        return new JAXBElement<Boolean>(_IsPaymentCardDetailsExistResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", name = "fetchDonorIdByUserRoleIdResponse")
    public JAXBElement<Long> createFetchDonorIdByUserRoleIdResponse(Long value) {
        return new JAXBElement<Long>(_FetchDonorIdByUserRoleIdResponse_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/faults/", name = "ServiceFaultException")
    public JAXBElement<ServiceFault> createServiceFaultException(ServiceFault value) {
        return new JAXBElement<ServiceFault>(_ServiceFaultException_QNAME, ServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", name = "cancelRegularDonationResponse")
    public JAXBElement<Boolean> createCancelRegularDonationResponse(Boolean value) {
        return new JAXBElement<Boolean>(_CancelRegularDonationResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", name = "fundraiserCloseAccountResponse")
    public JAXBElement<Boolean> createFundraiserCloseAccountResponse(Boolean value) {
        return new JAXBElement<Boolean>(_FundraiserCloseAccountResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", name = "fetchUserNameResponse")
    public JAXBElement<String> createFetchUserNameResponse(String value) {
        return new JAXBElement<String>(_FetchUserNameResponse_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/charity/", name = "accountExistsResponse")
    public JAXBElement<Boolean> createAccountExistsResponse(Boolean value) {
        return new JAXBElement<Boolean>(_AccountExistsResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/charity/", name = "updateDonorPersonalDetailsResponse")
    public JAXBElement<Long> createUpdateDonorPersonalDetailsResponse(Long value) {
        return new JAXBElement<Long>(_UpdateDonorPersonalDetailsResponse_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceUserLoginDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", name = "fetchUserNameRequest")
    public JAXBElement<ServiceUserLoginDetails> createFetchUserNameRequest(ServiceUserLoginDetails value) {
        return new JAXBElement<ServiceUserLoginDetails>(_FetchUserNameRequest_QNAME, ServiceUserLoginDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", name = "Header")
    public JAXBElement<MessageHeader> createHeader(MessageHeader value) {
        return new JAXBElement<MessageHeader>(_Header_QNAME, MessageHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/charity/", name = "charityRegisterVerificationResponse")
    public JAXBElement<Boolean> createCharityRegisterVerificationResponse(Boolean value) {
        return new JAXBElement<Boolean>(_CharityRegisterVerificationResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", name = "emailResponse")
    public JAXBElement<Boolean> createEmailResponse(Boolean value) {
        return new JAXBElement<Boolean>(_EmailResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/giving-management/", name = "NewElement")
    public JAXBElement<String> createNewElement(String value) {
        return new JAXBElement<String>(_NewElement_QNAME, String.class, null, value);
    }

}
