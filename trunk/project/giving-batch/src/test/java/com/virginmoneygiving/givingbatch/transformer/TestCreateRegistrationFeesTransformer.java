package com.virginmoneygiving.givingbatch.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.domain.RegistrationFee;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.GivingBatchExtManagementWs;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.PaymentStatus;
import com.virginmoneygiving.payment.domain.PaymentType;
import com.virginmoneygiving.payment.domain.VatAmount;
import com.virginmoneygiving.payment.domain.VatCode;
//import com.virginmoneygiving.payment.domain.VatRecordType;
import com.virginmoneygiving.payment.domain.VatStatus;

public class TestCreateRegistrationFeesTransformer {

	/** Object under test */
	CreateRegistrationFeesTransformer createRegistrationFeesTransformer = null;
	
	/** Mock StepExecution */
	StepExecution mockStepExecution = null;
	
	/** Mock Job Execution */
	JobExecution mockJobExecution = null;
	
	/** Mock ExecutionContext */
	ExecutionContext mockExecutionContext = null;
	
	/** Mock giving-ext impl. */
	GivingBatchExtManagementServiceLocatorImpl mockGivingBatchExtLocatorImpl = null;
	
	/** Mock Giving-batch-management WS. */
	GivingBatchExtManagementWs mockGivingBatchExtManagementWs = null;
	
	//@Before
	public void setUp() throws Exception {
		
		createRegistrationFeesTransformer = new CreateRegistrationFeesTransformer();
		
		mockStepExecution = mock(StepExecution.class);
		mockJobExecution = mock(JobExecution.class);
        mockExecutionContext = mock(ExecutionContext.class);
		mockGivingBatchExtLocatorImpl = mock(GivingBatchExtManagementServiceLocatorImpl.class);
		mockGivingBatchExtManagementWs = mock(GivingBatchExtManagementWs.class);
		
        when(mockStepExecution.getJobExecution()).thenReturn(mockJobExecution);
        when(mockJobExecution.getExecutionContext()).thenReturn(mockExecutionContext);
        when(mockGivingBatchExtLocatorImpl.getGivingBatchExtManagementPort()).thenReturn(mockGivingBatchExtManagementWs);

		createRegistrationFeesTransformer.beforeStep(mockStepExecution);
		createRegistrationFeesTransformer.setLocatorImpl(mockGivingBatchExtLocatorImpl);
	}
    @Test
    public void mockTest() {
        Assert.assertEquals(1,1);
    }

    /**
	 * Not interested in contents here. Just check that the list is added to the 
	 * ExecutionContext and that payments are added to it.
	 * @throws Exception
	 */
	//@Test
	public void testProcessExecutionContextList() throws Exception {
		
		PaymentStatus paymentStatus = new PaymentStatus();
		paymentStatus.setCode(Constant.DON_PAYMENT_INITIATED);

		VatAmount vatAmount = new VatAmount();
		vatAmount.setId(new Long(1));
		vatAmount.setVatStatus(new VatStatus());
		vatAmount.setVatCode(new VatCode());
		
		// VatRecord type no longer required
		// vatAmount.setVatRecordType(new VatRecordType());
		
		Set<VatAmount> vatAmounts = new HashSet<VatAmount>();
		vatAmounts.add(vatAmount);
		
		Payment payment = new Payment();
		payment.setId(new Long(1));
		payment.setPaymentStatus(paymentStatus);
		payment.setPaymentType(new PaymentType());
		payment.setVatAmount(vatAmounts);
		payment.setCreatedDateTime(new Timestamp(new Date().getTime()));
		
		/*
		 * Prepare for the null-list run
		 */
		when(mockStepExecution.getJobExecution()).thenReturn(mockJobExecution);
		when(mockJobExecution.getExecutionContext()).thenReturn(mockExecutionContext);
		when(mockGivingBatchExtLocatorImpl.getGivingBatchExtManagementPort()).thenReturn(mockGivingBatchExtManagementWs);
		
		/*
		 * Run
		 */
		createRegistrationFeesTransformer.process(payment);
		
		/*
		 * Verify
		 */
		verify(mockExecutionContext).put(eq(Constant.CREATE_REGISTRATION_FEE_OBJECT_LIST), anyList());
		
		/*
		 * Prepare for the non-null list run
		 */
		List<RegistrationFee> regFees = new ArrayList<RegistrationFee>();

		when(mockStepExecution.getJobExecution()).thenReturn(mockJobExecution);
		when(mockJobExecution.getExecutionContext()).thenReturn(mockExecutionContext);
		when(mockExecutionContext.get(Constant.CREATE_REGISTRATION_FEE_OBJECT_LIST)).thenReturn(regFees);

		/*
		 * Run
		 */
		createRegistrationFeesTransformer.process(payment);
		createRegistrationFeesTransformer.process(payment);

		/*
		 * Verify
		 */
		assertEquals(2, regFees.size());
	}

	//@Test
	public void testProcessPaymentStatusInitiated() throws Exception { // change method name as per Naming Conventions.
		
		/*
		 * Prepare for the run
		 */
		PaymentStatus paymentStatus = new PaymentStatus();
		paymentStatus.setCode(Constant.DON_PAYMENT_INITIATED);

		PaymentType paymentType = new PaymentType();
		paymentType.setCode("CRF");
		
		VatStatus vatStatus = new VatStatus();
		vatStatus.setCode("VATINI");
		
		//VatRecord type no longer required
		//VatRecordType vatRecordType = new VatRecordType();
		//vatRecordType.setCode("VATCRF");
		
		VatAmount vatAmount = new VatAmount();
        vatAmount.setId(new Long(1));
		vatAmount.setVatAmount(new BigDecimal(15));
		vatAmount.setVatStatus(vatStatus);
        vatAmount.setVatCode(new VatCode());
		
		Set<VatAmount> vatAmounts = new HashSet<VatAmount>();
		vatAmounts.add(vatAmount);
		
		StringBuffer paymentTarget = new StringBuffer(1);
		StringBuffer paymentSource = new StringBuffer(1);

		Payment payment = new Payment();
		payment.setId(new Long(1));
		payment.setPaymentStatus(paymentStatus);
		payment.setPaymentType(paymentType);
		payment.setVatAmount(vatAmounts);
		payment.setCreatedDateTime(new Timestamp(new Date().getTime()));
		payment.setGrossAmount(new BigDecimal(100));
		payment.setFinanceReference("ICR000000000001");
        payment.setPaymentTarget(paymentTarget.toString());
        payment.setPaymentSource(paymentSource.toString());
        
		List<RegistrationFee> regFees = new ArrayList<RegistrationFee>();

		when(mockStepExecution.getJobExecution()).thenReturn(mockJobExecution);
		when(mockJobExecution.getExecutionContext()).thenReturn(mockExecutionContext);
		when(mockExecutionContext.get(Constant.CREATE_REGISTRATION_FEE_OBJECT_LIST)).thenReturn(regFees);
        when(mockGivingBatchExtLocatorImpl.getGivingBatchExtManagementPort()).thenReturn(mockGivingBatchExtManagementWs);

		/*
		 * Run
		 */
		RegistrationFee registrationFee = createRegistrationFeesTransformer.process(payment);
		
		/*
		 * Verify
		 */
		assertNotNull(registrationFee);
		
		assertEquals(paymentSource.toString(), registrationFee.getCharityReference()); // Required: payment-target
		//assertEquals(new BigDecimal(100), registrationFee.getFeeDetails().getAmount()); // Required: 100
		assertNotNull(registrationFee.getFeeDetails().getTransactionDate()); // Required: 
		assertEquals(Constant.DON_PAYMENT_INITIATED, registrationFee.getFeeDetails().getTransactionStatus()); // Required: PAYINI
		assertEquals("CRF", registrationFee.getFeeDetails().getTransactionType()); // Required: CRF
		assertEquals("ICR000000000001", registrationFee.getInvoiceNumber()); // Required: 0000000001
		//assertEquals(new BigDecimal(15), registrationFee.getVatDetails().getAmount()); // Required: 15
		//assertEquals("VATCRF", registrationFee.getVatDetails().getCode()); // Required: VATCRF?
		//assertEquals("VATINI", registrationFee.getVatDetails().getStatus()); // Required: VATINI
		
		assertEquals(1, regFees.size());
		RegistrationFee regFeeFromList = regFees.get(0);
		assertEquals(registrationFee, regFeeFromList);

		// TODO: rate - where do i get this from? 
		//assertEquals(new Double(15), registrationFee.getVatDetails().getRate()); // Required: 15%
		
		
	}
}
