
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.virginmoneygiving.paymentmanagement.service.messages package. 
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

    private final static QName _UpdateGiftAidTransitionalRlfResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "updateGiftAidTransitionalRlfResponse");
    private final static QName _CalculateTransitionalReliefResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "CalculateTransitionalReliefResponse");
    private final static QName _CalculateCharityRegistrationFeeVatResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "CalculateCharityRegistrationFeeVatResponse");
    private final static QName _MessageHeader_QNAME = new QName("http://www.virginmoneygiving.com/type/header/", "MessageHeader");
    private final static QName _GenerateSequenceResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "generateSequenceResponse");
    private final static QName _CalculateTransactionChargeVatResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "CalculateTransactionChargeVatResponse");
    private final static QName _UpdateCharityRegFeeStatusResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/operations/", "updateCharityRegFeeStatusResponse");
    private final static QName _DonationPaymentsInitiatedRequest_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "DonationPaymentsInitiatedRequest");
    private final static QName _ServiceFaultException_QNAME = new QName("http://www.virginmoneygiving.com/faults/", "ServiceFaultException");
    private final static QName _DonationPaymentsInitiatedResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "DonationPaymentsInitiatedResponse");
    private final static QName _CalculateVmgTransactionFeeResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "CalculateVmgTransactionFeeResponse");
    private final static QName _CalculateRegistrationFeeResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "CalculateRegistrationFeeResponse");
    private final static QName _CalculateTransactionChargeResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "CalculateTransactionChargeResponse");
    private final static QName _CalculateGiftAidResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "CalculateGiftAidResponse");
    private final static QName _CharityRegistrationFeePaidResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "charityRegistrationFeePaidResponse");
    private final static QName _CalculateVmgTransactionFeeVatResponse_QNAME = new QName("http://www.virginmoneygiving.com/type/payment-management/", "CalculateVmgTransactionFeeVatResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.virginmoneygiving.paymentmanagement.service.messages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SaveWebDonationResponse }
     * 
     */
    public SaveWebDonationResponse createSaveWebDonationResponse() {
        return new SaveWebDonationResponse();
    }

    /**
     * Create an instance of {@link PaymentReceiveSelection }
     * 
     */
    public PaymentReceiveSelection createPaymentReceiveSelection() {
        return new PaymentReceiveSelection();
    }

    /**
     * Create an instance of {@link Batch }
     * 
     */
    public Batch createBatch() {
        return new Batch();
    }

    /**
     * Create an instance of {@link PaymentType }
     * 
     */
    public PaymentType createPaymentType() {
        return new PaymentType();
    }

    /**
     * Create an instance of {@link UpdatePaymentMethodForRegistrationFeeResponse }
     * 
     */
    public UpdatePaymentMethodForRegistrationFeeResponse createUpdatePaymentMethodForRegistrationFeeResponse() {
        return new UpdatePaymentMethodForRegistrationFeeResponse();
    }

    /**
     * Create an instance of {@link SavePaymentDetailsResponse }
     * 
     */
    public SavePaymentDetailsResponse createSavePaymentDetailsResponse() {
        return new SavePaymentDetailsResponse();
    }

    /**
     * Create an instance of {@link FindLastGiftAidClaimedRequest }
     * 
     */
    public FindLastGiftAidClaimedRequest createFindLastGiftAidClaimedRequest() {
        return new FindLastGiftAidClaimedRequest();
    }

    /**
     * Create an instance of {@link SaveCardTransactionDetailsResponse }
     * 
     */
    public SaveCardTransactionDetailsResponse createSaveCardTransactionDetailsResponse() {
        return new SaveCardTransactionDetailsResponse();
    }

    /**
     * Create an instance of {@link SaveHandlingChargeResponse }
     * 
     */
    public SaveHandlingChargeResponse createSaveHandlingChargeResponse() {
        return new SaveHandlingChargeResponse();
    }

    /**
     * Create an instance of {@link TransitionalReliefPayment }
     * 
     */
    public TransitionalReliefPayment createTransitionalReliefPayment() {
        return new TransitionalReliefPayment();
    }

    /**
     * Create an instance of {@link PaymentReceive }
     * 
     */
    public PaymentReceive createPaymentReceive() {
        return new PaymentReceive();
    }

    /**
     * Create an instance of {@link ServiceEntity }
     * 
     */
    public ServiceEntity createServiceEntity() {
        return new ServiceEntity();
    }

    /**
     * Create an instance of {@link SavePaymentDetailsRequest }
     * 
     */
    public SavePaymentDetailsRequest createSavePaymentDetailsRequest() {
        return new SavePaymentDetailsRequest();
    }

    /**
     * Create an instance of {@link GiftAidAmountforViewRequestData }
     * 
     */
    public GiftAidAmountforViewRequestData createGiftAidAmountforViewRequestData() {
        return new GiftAidAmountforViewRequestData();
    }

    /**
     * Create an instance of {@link UpdateTransitionalReliefForSettlementResponse }
     * 
     */
    public UpdateTransitionalReliefForSettlementResponse createUpdateTransitionalReliefForSettlementResponse() {
        return new UpdateTransitionalReliefForSettlementResponse();
    }

    /**
     * Create an instance of {@link FetchTransitionalRlfByIdRequest }
     * 
     */
    public FetchTransitionalRlfByIdRequest createFetchTransitionalRlfByIdRequest() {
        return new FetchTransitionalRlfByIdRequest();
    }

    /**
     * Create an instance of {@link FetchPaymentReceivedTransactionDetailsRequest }
     * 
     */
    public FetchPaymentReceivedTransactionDetailsRequest createFetchPaymentReceivedTransactionDetailsRequest() {
        return new FetchPaymentReceivedTransactionDetailsRequest();
    }

    /**
     * Create an instance of {@link MultiCharityDivision }
     * 
     */
    public MultiCharityDivision createMultiCharityDivision() {
        return new MultiCharityDivision();
    }

    /**
     * Create an instance of {@link FetchCharityRegistrationInvoiceDetailsRequest }
     * 
     */
    public FetchCharityRegistrationInvoiceDetailsRequest createFetchCharityRegistrationInvoiceDetailsRequest() {
        return new FetchCharityRegistrationInvoiceDetailsRequest();
    }

    /**
     * Create an instance of {@link SaveTransitionalRlfResponse }
     * 
     */
    public SaveTransitionalRlfResponse createSaveTransitionalRlfResponse() {
        return new SaveTransitionalRlfResponse();
    }

    /**
     * Create an instance of {@link SaveCardChargeRequest }
     * 
     */
    public SaveCardChargeRequest createSaveCardChargeRequest() {
        return new SaveCardChargeRequest();
    }

    /**
     * Create an instance of {@link FetchCharityRegistrationInvoiceDetailsResponse }
     * 
     */
    public FetchCharityRegistrationInvoiceDetailsResponse createFetchCharityRegistrationInvoiceDetailsResponse() {
        return new FetchCharityRegistrationInvoiceDetailsResponse();
    }

    /**
     * Create an instance of {@link UpdateCharityRegFeeStatusRequest }
     * 
     */
    public UpdateCharityRegFeeStatusRequest createUpdateCharityRegFeeStatusRequest() {
        return new UpdateCharityRegFeeStatusRequest();
    }

    /**
     * Create an instance of {@link GiftAidClaimTotalnRecords }
     * 
     */
    public GiftAidClaimTotalnRecords createGiftAidClaimTotalnRecords() {
        return new GiftAidClaimTotalnRecords();
    }

    /**
     * Create an instance of {@link BusinessErrorMessage }
     * 
     */
    public BusinessErrorMessage createBusinessErrorMessage() {
        return new BusinessErrorMessage();
    }

    /**
     * Create an instance of {@link FetchPaymentReceiveDisplayRequest }
     * 
     */
    public FetchPaymentReceiveDisplayRequest createFetchPaymentReceiveDisplayRequest() {
        return new FetchPaymentReceiveDisplayRequest();
    }

    /**
     * Create an instance of {@link FetchHandlingChargeByIdResponse }
     * 
     */
    public FetchHandlingChargeByIdResponse createFetchHandlingChargeByIdResponse() {
        return new FetchHandlingChargeByIdResponse();
    }

    /**
     * Create an instance of {@link TransUpdateStatus }
     * 
     */
    public TransUpdateStatus createTransUpdateStatus() {
        return new TransUpdateStatus();
    }

    /**
     * Create an instance of {@link UpdateFeesAndChagresResponse }
     * 
     */
    public UpdateFeesAndChagresResponse createUpdateFeesAndChagresResponse() {
        return new UpdateFeesAndChagresResponse();
    }

    /**
     * Create an instance of {@link FetchExceptionPocessingCrireriaResultsResponse }
     * 
     */
    public FetchExceptionPocessingCrireriaResultsResponse createFetchExceptionPocessingCrireriaResultsResponse() {
        return new FetchExceptionPocessingCrireriaResultsResponse();
    }

    /**
     * Create an instance of {@link ErrorMessage }
     * 
     */
    public ErrorMessage createErrorMessage() {
        return new ErrorMessage();
    }

    /**
     * Create an instance of {@link TransitionalReliefAmountSettlement }
     * 
     */
    public TransitionalReliefAmountSettlement createTransitionalReliefAmountSettlement() {
        return new TransitionalReliefAmountSettlement();
    }

    /**
     * Create an instance of {@link ServiceFeesAndCharges }
     * 
     */
    public ServiceFeesAndCharges createServiceFeesAndCharges() {
        return new ServiceFeesAndCharges();
    }

    /**
     * Create an instance of {@link CharityCalculationDetails }
     * 
     */
    public CharityCalculationDetails createCharityCalculationDetails() {
        return new CharityCalculationDetails();
    }

    /**
     * Create an instance of {@link ServiceCardTransaction }
     * 
     */
    public ServiceCardTransaction createServiceCardTransaction() {
        return new ServiceCardTransaction();
    }

    /**
     * Create an instance of {@link MaintenanceResponse }
     * 
     */
    public MaintenanceResponse createMaintenanceResponse() {
        return new MaintenanceResponse();
    }

    /**
     * Create an instance of {@link SaveTransitionalRlfRequest }
     * 
     */
    public SaveTransitionalRlfRequest createSaveTransitionalRlfRequest() {
        return new SaveTransitionalRlfRequest();
    }

    /**
     * Create an instance of {@link ProcessInvoiceForCharityRequest }
     * 
     */
    public ProcessInvoiceForCharityRequest createProcessInvoiceForCharityRequest() {
        return new ProcessInvoiceForCharityRequest();
    }

    /**
     * Create an instance of {@link CreateRegularWebDonationResponse }
     * 
     */
    public CreateRegularWebDonationResponse createCreateRegularWebDonationResponse() {
        return new CreateRegularWebDonationResponse();
    }

    /**
     * Create an instance of {@link GetCharityRegistrationFeeRequest }
     * 
     */
    public GetCharityRegistrationFeeRequest createGetCharityRegistrationFeeRequest() {
        return new GetCharityRegistrationFeeRequest();
    }

    /**
     * Create an instance of {@link TransitionalReliefAmountforViewRequestData }
     * 
     */
    public TransitionalReliefAmountforViewRequestData createTransitionalReliefAmountforViewRequestData() {
        return new TransitionalReliefAmountforViewRequestData();
    }

    /**
     * Create an instance of {@link TransUpdateResponse }
     * 
     */
    public TransUpdateResponse createTransUpdateResponse() {
        return new TransUpdateResponse();
    }

    /**
     * Create an instance of {@link CalculateGiftAidRequest }
     * 
     */
    public CalculateGiftAidRequest createCalculateGiftAidRequest() {
        return new CalculateGiftAidRequest();
    }

    /**
     * Create an instance of {@link CreateEventRegistrationFeeRequest }
     * 
     */
    public CreateEventRegistrationFeeRequest createCreateEventRegistrationFeeRequest() {
        return new CreateEventRegistrationFeeRequest();
    }

    /**
     * Create an instance of {@link UpdateTransitionalReliefForSettlementRequest }
     * 
     */
    public UpdateTransitionalReliefForSettlementRequest createUpdateTransitionalReliefForSettlementRequest() {
        return new UpdateTransitionalReliefForSettlementRequest();
    }

    /**
     * Create an instance of {@link SaveCardChargeResponse }
     * 
     */
    public SaveCardChargeResponse createSaveCardChargeResponse() {
        return new SaveCardChargeResponse();
    }

    /**
     * Create an instance of {@link CalculateTransactionChargeRequest }
     * 
     */
    public CalculateTransactionChargeRequest createCalculateTransactionChargeRequest() {
        return new CalculateTransactionChargeRequest();
    }

    /**
     * Create an instance of {@link UpdateGiftAidClaimResponse }
     * 
     */
    public UpdateGiftAidClaimResponse createUpdateGiftAidClaimResponse() {
        return new UpdateGiftAidClaimResponse();
    }

    /**
     * Create an instance of {@link FetchHandlingChargeByIdRequest }
     * 
     */
    public FetchHandlingChargeByIdRequest createFetchHandlingChargeByIdRequest() {
        return new FetchHandlingChargeByIdRequest();
    }

    /**
     * Create an instance of {@link ServiceFault }
     * 
     */
    public ServiceFault createServiceFault() {
        return new ServiceFault();
    }

    /**
     * Create an instance of {@link FetchPaymentByIdResponse }
     * 
     */
    public FetchPaymentByIdResponse createFetchPaymentByIdResponse() {
        return new FetchPaymentByIdResponse();
    }

    /**
     * Create an instance of {@link FetchPaymentReceivedTransactionDetailsResponse }
     * 
     */
    public FetchPaymentReceivedTransactionDetailsResponse createFetchPaymentReceivedTransactionDetailsResponse() {
        return new FetchPaymentReceivedTransactionDetailsResponse();
    }

    /**
     * Create an instance of {@link CharityRegistrationFeePaidRequest }
     * 
     */
    public CharityRegistrationFeePaidRequest createCharityRegistrationFeePaidRequest() {
        return new CharityRegistrationFeePaidRequest();
    }

    /**
     * Create an instance of {@link GiftAidAmountforViewResponse }
     * 
     */
    public GiftAidAmountforViewResponse createGiftAidAmountforViewResponse() {
        return new GiftAidAmountforViewResponse();
    }

    /**
     * Create an instance of {@link CalculateVmgTransactionFeeRequest }
     * 
     */
    public CalculateVmgTransactionFeeRequest createCalculateVmgTransactionFeeRequest() {
        return new CalculateVmgTransactionFeeRequest();
    }

    /**
     * Create an instance of {@link SaveGiftAidRequest }
     * 
     */
    public SaveGiftAidRequest createSaveGiftAidRequest() {
        return new SaveGiftAidRequest();
    }

    /**
     * Create an instance of {@link DonationPaymentInitiatedBatch }
     * 
     */
    public DonationPaymentInitiatedBatch createDonationPaymentInitiatedBatch() {
        return new DonationPaymentInitiatedBatch();
    }

    /**
     * Create an instance of {@link FetchPaymentByIdRequest }
     * 
     */
    public FetchPaymentByIdRequest createFetchPaymentByIdRequest() {
        return new FetchPaymentByIdRequest();
    }

    /**
     * Create an instance of {@link ExceptionPocessingResult }
     * 
     */
    public ExceptionPocessingResult createExceptionPocessingResult() {
        return new ExceptionPocessingResult();
    }

    /**
     * Create an instance of {@link FetchFeesAndChagresRequest }
     * 
     */
    public FetchFeesAndChagresRequest createFetchFeesAndChagresRequest() {
        return new FetchFeesAndChagresRequest();
    }

    /**
     * Create an instance of {@link FetchCardChargeByIdResponse }
     * 
     */
    public FetchCardChargeByIdResponse createFetchCardChargeByIdResponse() {
        return new FetchCardChargeByIdResponse();
    }

    /**
     * Create an instance of {@link PaymentInitiated }
     * 
     */
    public PaymentInitiated createPaymentInitiated() {
        return new PaymentInitiated();
    }

    /**
     * Create an instance of {@link CalculateTransactionChargeVatRequest }
     * 
     */
    public CalculateTransactionChargeVatRequest createCalculateTransactionChargeVatRequest() {
        return new CalculateTransactionChargeVatRequest();
    }

    /**
     * Create an instance of {@link FailedRecords }
     * 
     */
    public FailedRecords createFailedRecords() {
        return new FailedRecords();
    }

    /**
     * Create an instance of {@link ResponseHeader }
     * 
     */
    public ResponseHeader createResponseHeader() {
        return new ResponseHeader();
    }

    /**
     * Create an instance of {@link BasicResponse }
     * 
     */
    public BasicResponse createBasicResponse() {
        return new BasicResponse();
    }

    /**
     * Create an instance of {@link SavePaymentRequest }
     * 
     */
    public SavePaymentRequest createSavePaymentRequest() {
        return new SavePaymentRequest();
    }

    /**
     * Create an instance of {@link FetchGiftAidClaimSettlementResponse }
     * 
     */
    public FetchGiftAidClaimSettlementResponse createFetchGiftAidClaimSettlementResponse() {
        return new FetchGiftAidClaimSettlementResponse();
    }

    /**
     * Create an instance of {@link UpdateGiftAidClaimRequest }
     * 
     */
    public UpdateGiftAidClaimRequest createUpdateGiftAidClaimRequest() {
        return new UpdateGiftAidClaimRequest();
    }

    /**
     * Create an instance of {@link CalculateRegistrationFeeRequest }
     * 
     */
    public CalculateRegistrationFeeRequest createCalculateRegistrationFeeRequest() {
        return new CalculateRegistrationFeeRequest();
    }

    /**
     * Create an instance of {@link SaveWebDonationRequest }
     * 
     */
    public SaveWebDonationRequest createSaveWebDonationRequest() {
        return new SaveWebDonationRequest();
    }

    /**
     * Create an instance of {@link FetchVatAmountByIdResponse }
     * 
     */
    public FetchVatAmountByIdResponse createFetchVatAmountByIdResponse() {
        return new FetchVatAmountByIdResponse();
    }

    /**
     * Create an instance of {@link ErrorList }
     * 
     */
    public ErrorList createErrorList() {
        return new ErrorList();
    }

    /**
     * Create an instance of {@link CalculateMultiCharityFeeRequest }
     * 
     */
    public CalculateMultiCharityFeeRequest createCalculateMultiCharityFeeRequest() {
        return new CalculateMultiCharityFeeRequest();
    }

    /**
     * Create an instance of {@link TransitionalReliefAmountforViewRequest }
     * 
     */
    public TransitionalReliefAmountforViewRequest createTransitionalReliefAmountforViewRequest() {
        return new TransitionalReliefAmountforViewRequest();
    }

    /**
     * Create an instance of {@link FetchFeesAndChagresResponse }
     * 
     */
    public FetchFeesAndChagresResponse createFetchFeesAndChagresResponse() {
        return new FetchFeesAndChagresResponse();
    }

    /**
     * Create an instance of {@link CalculateTransitionalReliefRequest }
     * 
     */
    public CalculateTransitionalReliefRequest createCalculateTransitionalReliefRequest() {
        return new CalculateTransitionalReliefRequest();
    }

    /**
     * Create an instance of {@link FetchTransitionalReliefAmountforSettlementResponse }
     * 
     */
    public FetchTransitionalReliefAmountforSettlementResponse createFetchTransitionalReliefAmountforSettlementResponse() {
        return new FetchTransitionalReliefAmountforSettlementResponse();
    }

    /**
     * Create an instance of {@link CreateEventRegistrationFeeResponse }
     * 
     */
    public CreateEventRegistrationFeeResponse createCreateEventRegistrationFeeResponse() {
        return new CreateEventRegistrationFeeResponse();
    }

    /**
     * Create an instance of {@link VoidResponse }
     * 
     */
    public VoidResponse createVoidResponse() {
        return new VoidResponse();
    }

    /**
     * Create an instance of {@link TransUpdateRecord }
     * 
     */
    public TransUpdateRecord createTransUpdateRecord() {
        return new TransUpdateRecord();
    }

    /**
     * Create an instance of {@link TransUpdateRequest }
     * 
     */
    public TransUpdateRequest createTransUpdateRequest() {
        return new TransUpdateRequest();
    }

    /**
     * Create an instance of {@link FetchVatAmountByIdRequest }
     * 
     */
    public FetchVatAmountByIdRequest createFetchVatAmountByIdRequest() {
        return new FetchVatAmountByIdRequest();
    }

    /**
     * Create an instance of {@link FetchTransitionalReliefAmountforSettlementRequest }
     * 
     */
    public FetchTransitionalReliefAmountforSettlementRequest createFetchTransitionalReliefAmountforSettlementRequest() {
        return new FetchTransitionalReliefAmountforSettlementRequest();
    }

    /**
     * Create an instance of {@link FetchPaymentReceiveDisplayResponse }
     * 
     */
    public FetchPaymentReceiveDisplayResponse createFetchPaymentReceiveDisplayResponse() {
        return new FetchPaymentReceiveDisplayResponse();
    }

    /**
     * Create an instance of {@link TransitionalReliefAmountforViewResponse }
     * 
     */
    public TransitionalReliefAmountforViewResponse createTransitionalReliefAmountforViewResponse() {
        return new TransitionalReliefAmountforViewResponse();
    }

    /**
     * Create an instance of {@link SaveVatAmountResponse }
     * 
     */
    public SaveVatAmountResponse createSaveVatAmountResponse() {
        return new SaveVatAmountResponse();
    }

    /**
     * Create an instance of {@link ServicePayment }
     * 
     */
    public ServicePayment createServicePayment() {
        return new ServicePayment();
    }

    /**
     * Create an instance of {@link FetchGiftAidByIdResponse }
     * 
     */
    public FetchGiftAidByIdResponse createFetchGiftAidByIdResponse() {
        return new FetchGiftAidByIdResponse();
    }

    /**
     * Create an instance of {@link SaveHandlingChargeRequest }
     * 
     */
    public SaveHandlingChargeRequest createSaveHandlingChargeRequest() {
        return new SaveHandlingChargeRequest();
    }

    /**
     * Create an instance of {@link GenerateSequenceRequest }
     * 
     */
    public GenerateSequenceRequest createGenerateSequenceRequest() {
        return new GenerateSequenceRequest();
    }

    /**
     * Create an instance of {@link SaveVatAmountRequest }
     * 
     */
    public SaveVatAmountRequest createSaveVatAmountRequest() {
        return new SaveVatAmountRequest();
    }

    /**
     * Create an instance of {@link ExceptionPocessingSearchCriteria }
     * 
     */
    public ExceptionPocessingSearchCriteria createExceptionPocessingSearchCriteria() {
        return new ExceptionPocessingSearchCriteria();
    }

    /**
     * Create an instance of {@link CalculateMultiCharityFeeResponse }
     * 
     */
    public CalculateMultiCharityFeeResponse createCalculateMultiCharityFeeResponse() {
        return new CalculateMultiCharityFeeResponse();
    }

    /**
     * Create an instance of {@link CalculateCharityRegistrationFeeVatRequest }
     * 
     */
    public CalculateCharityRegistrationFeeVatRequest createCalculateCharityRegistrationFeeVatRequest() {
        return new CalculateCharityRegistrationFeeVatRequest();
    }

    /**
     * Create an instance of {@link UpdatePaymentStatusRequest }
     * 
     */
    public UpdatePaymentStatusRequest createUpdatePaymentStatusRequest() {
        return new UpdatePaymentStatusRequest();
    }

    /**
     * Create an instance of {@link GetCharityRegistrationFeeResponse }
     * 
     */
    public GetCharityRegistrationFeeResponse createGetCharityRegistrationFeeResponse() {
        return new GetCharityRegistrationFeeResponse();
    }

    /**
     * Create an instance of {@link UpdateGiftAidTransitionalRlfRequest }
     * 
     */
    public UpdateGiftAidTransitionalRlfRequest createUpdateGiftAidTransitionalRlfRequest() {
        return new UpdateGiftAidTransitionalRlfRequest();
    }

    /**
     * Create an instance of {@link UpdateFeesAndChagresRequest }
     * 
     */
    public UpdateFeesAndChagresRequest createUpdateFeesAndChagresRequest() {
        return new UpdateFeesAndChagresRequest();
    }

    /**
     * Create an instance of {@link GiftAidAmountforViewRequest }
     * 
     */
    public GiftAidAmountforViewRequest createGiftAidAmountforViewRequest() {
        return new GiftAidAmountforViewRequest();
    }

    /**
     * Create an instance of {@link FetchExceptionPocessingCrireriaResultsRequest }
     * 
     */
    public FetchExceptionPocessingCrireriaResultsRequest createFetchExceptionPocessingCrireriaResultsRequest() {
        return new FetchExceptionPocessingCrireriaResultsRequest();
    }

    /**
     * Create an instance of {@link SaveCardTransactionDetailsRequest }
     * 
     */
    public SaveCardTransactionDetailsRequest createSaveCardTransactionDetailsRequest() {
        return new SaveCardTransactionDetailsRequest();
    }

    /**
     * Create an instance of {@link UpdateGiftAidClaimForSettlementResponse }
     * 
     */
    public UpdateGiftAidClaimForSettlementResponse createUpdateGiftAidClaimForSettlementResponse() {
        return new UpdateGiftAidClaimForSettlementResponse();
    }

    /**
     * Create an instance of {@link MessageHeader }
     * 
     */
    public MessageHeader createMessageHeader() {
        return new MessageHeader();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link DonationDistribution }
     * 
     */
    public DonationDistribution createDonationDistribution() {
        return new DonationDistribution();
    }

    /**
     * Create an instance of {@link GiftAidPayment }
     * 
     */
    public GiftAidPayment createGiftAidPayment() {
        return new GiftAidPayment();
    }

    /**
     * Create an instance of {@link FetchGiftAidClaimTotalRequest }
     * 
     */
    public FetchGiftAidClaimTotalRequest createFetchGiftAidClaimTotalRequest() {
        return new FetchGiftAidClaimTotalRequest();
    }

    /**
     * Create an instance of {@link FetchGiftAidByIdRequest }
     * 
     */
    public FetchGiftAidByIdRequest createFetchGiftAidByIdRequest() {
        return new FetchGiftAidByIdRequest();
    }

    /**
     * Create an instance of {@link PreviousGiftAidClaimDetails }
     * 
     */
    public PreviousGiftAidClaimDetails createPreviousGiftAidClaimDetails() {
        return new PreviousGiftAidClaimDetails();
    }

    /**
     * Create an instance of {@link SavePaymentResponse }
     * 
     */
    public SavePaymentResponse createSavePaymentResponse() {
        return new SavePaymentResponse();
    }

    /**
     * Create an instance of {@link ClaimPeriod }
     * 
     */
    public ClaimPeriod createClaimPeriod() {
        return new ClaimPeriod();
    }

    /**
     * Create an instance of {@link UpdateGiftAidClaimForSettlementRequest }
     * 
     */
    public UpdateGiftAidClaimForSettlementRequest createUpdateGiftAidClaimForSettlementRequest() {
        return new UpdateGiftAidClaimForSettlementRequest();
    }

    /**
     * Create an instance of {@link FetchGiftAidClaimTotalResponse }
     * 
     */
    public FetchGiftAidClaimTotalResponse createFetchGiftAidClaimTotalResponse() {
        return new FetchGiftAidClaimTotalResponse();
    }

    /**
     * Create an instance of {@link CalculateVmgTransactionFeeVatRequest }
     * 
     */
    public CalculateVmgTransactionFeeVatRequest createCalculateVmgTransactionFeeVatRequest() {
        return new CalculateVmgTransactionFeeVatRequest();
    }

    /**
     * Create an instance of {@link FetchGiftAidClaimSettlementRequest }
     * 
     */
    public FetchGiftAidClaimSettlementRequest createFetchGiftAidClaimSettlementRequest() {
        return new FetchGiftAidClaimSettlementRequest();
    }

    /**
     * Create an instance of {@link SaveGiftAidResponse }
     * 
     */
    public SaveGiftAidResponse createSaveGiftAidResponse() {
        return new SaveGiftAidResponse();
    }

    /**
     * Create an instance of {@link UpdatePaymentMethodForRegistrationFeeRequest }
     * 
     */
    public UpdatePaymentMethodForRegistrationFeeRequest createUpdatePaymentMethodForRegistrationFeeRequest() {
        return new UpdatePaymentMethodForRegistrationFeeRequest();
    }

    /**
     * Create an instance of {@link FindLastGiftAidClaimedResponse }
     * 
     */
    public FindLastGiftAidClaimedResponse createFindLastGiftAidClaimedResponse() {
        return new FindLastGiftAidClaimedResponse();
    }

    /**
     * Create an instance of {@link CreateRegularWebDonationRequest }
     * 
     */
    public CreateRegularWebDonationRequest createCreateRegularWebDonationRequest() {
        return new CreateRegularWebDonationRequest();
    }

    /**
     * Create an instance of {@link FetchCardChargeByIdRequest }
     * 
     */
    public FetchCardChargeByIdRequest createFetchCardChargeByIdRequest() {
        return new FetchCardChargeByIdRequest();
    }

    /**
     * Create an instance of {@link FetchTransitionalRlfByIdResponse }
     * 
     */
    public FetchTransitionalRlfByIdResponse createFetchTransitionalRlfByIdResponse() {
        return new FetchTransitionalRlfByIdResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "updateGiftAidTransitionalRlfResponse")
    public JAXBElement<Boolean> createUpdateGiftAidTransitionalRlfResponse(Boolean value) {
        return new JAXBElement<Boolean>(_UpdateGiftAidTransitionalRlfResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "CalculateTransitionalReliefResponse")
    public JAXBElement<BigDecimal> createCalculateTransitionalReliefResponse(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CalculateTransitionalReliefResponse_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "CalculateCharityRegistrationFeeVatResponse")
    public JAXBElement<BigDecimal> createCalculateCharityRegistrationFeeVatResponse(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CalculateCharityRegistrationFeeVatResponse_QNAME, BigDecimal.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "generateSequenceResponse")
    public JAXBElement<String> createGenerateSequenceResponse(String value) {
        return new JAXBElement<String>(_GenerateSequenceResponse_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "CalculateTransactionChargeVatResponse")
    public JAXBElement<BigDecimal> createCalculateTransactionChargeVatResponse(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CalculateTransactionChargeVatResponse_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", name = "updateCharityRegFeeStatusResponse")
    public JAXBElement<Boolean> createUpdateCharityRegFeeStatusResponse(Boolean value) {
        return new JAXBElement<Boolean>(_UpdateCharityRegFeeStatusResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DonationPaymentInitiatedBatch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "DonationPaymentsInitiatedRequest")
    public JAXBElement<DonationPaymentInitiatedBatch> createDonationPaymentsInitiatedRequest(DonationPaymentInitiatedBatch value) {
        return new JAXBElement<DonationPaymentInitiatedBatch>(_DonationPaymentsInitiatedRequest_QNAME, DonationPaymentInitiatedBatch.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link MaintenanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "DonationPaymentsInitiatedResponse")
    public JAXBElement<MaintenanceResponse> createDonationPaymentsInitiatedResponse(MaintenanceResponse value) {
        return new JAXBElement<MaintenanceResponse>(_DonationPaymentsInitiatedResponse_QNAME, MaintenanceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "CalculateVmgTransactionFeeResponse")
    public JAXBElement<BigDecimal> createCalculateVmgTransactionFeeResponse(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CalculateVmgTransactionFeeResponse_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "CalculateRegistrationFeeResponse")
    public JAXBElement<BigDecimal> createCalculateRegistrationFeeResponse(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CalculateRegistrationFeeResponse_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "CalculateTransactionChargeResponse")
    public JAXBElement<BigDecimal> createCalculateTransactionChargeResponse(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CalculateTransactionChargeResponse_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "CalculateGiftAidResponse")
    public JAXBElement<BigDecimal> createCalculateGiftAidResponse(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CalculateGiftAidResponse_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "charityRegistrationFeePaidResponse")
    public JAXBElement<Boolean> createCharityRegistrationFeePaidResponse(Boolean value) {
        return new JAXBElement<Boolean>(_CharityRegistrationFeePaidResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/payment-management/", name = "CalculateVmgTransactionFeeVatResponse")
    public JAXBElement<BigDecimal> createCalculateVmgTransactionFeeVatResponse(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CalculateVmgTransactionFeeVatResponse_QNAME, BigDecimal.class, null, value);
    }

}
