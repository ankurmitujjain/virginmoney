package com.virginmoneygiving.givingbatch.launcher.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.virginmoney.glis.messages.CollectedRegistrationFeeBatch;
import com.virginmoney.glis.messages.RegistrationFeePaymentCollected;
import com.virginmoneygiving.alert.service.messages.AlertDetail;
import com.virginmoneygiving.alert.service.messages.AlertPort;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.AlertServiceLocatorImpl;

public class TestJobExecutionServiceImpl {

	/** The class under test */
	JobExecutionServiceImpl serviceImpl = null;
	
	/** Mock alert-service */
	AlertPort mockAlertPort = null;
	
	/** Mock alert-service locator. */
	AlertServiceLocatorImpl mockAlertServiceLocatorImpl = null;
	
    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER = Logger.getLogger(TestJobExecutionServiceImpl.class);
	
	@Before
	public void setUp() throws Exception {
		serviceImpl = new JobExecutionServiceImpl();
		
		mockAlertPort = mock(AlertPort.class);
		mockAlertServiceLocatorImpl = mock(AlertServiceLocatorImpl.class);
		serviceImpl.setAlertServiceLocator(mockAlertServiceLocatorImpl);
		
		when(mockAlertServiceLocatorImpl.getAlertPort()).thenReturn(mockAlertPort);
	}

	@Test
	public void testExecuteCollectedRegistrationFeeJobNull() {
		try {
			serviceImpl.executeCollectedRegistrationFeeJob(null);
			fail("Expected NPE");
		}
		catch ( NullPointerException npe) {
			// Expected
		}
	}
	
	/**
	 * Should attempt to raise an alert.
	 */
	@Test
	public void testExecuteCollectedRegistrationFeeJobNoBatchId() {
		
	    CollectedRegistrationFeeBatch batch = new CollectedRegistrationFeeBatch();
		batch.setBatchNumber(null);

		serviceImpl.executeCollectedRegistrationFeeJob(batch);
		
		verify(mockAlertPort).logAlertRequest((AlertDetail) anyObject());
	}

	/**
	 * Should create a file with just a <list/> tag
	 * 
	 * Job invoke will throw an NPE that is ignored
	 * 
	 * @throws Exception
	 */
//	public void testExecuteCollectedRegistrationFeeJob_fileCreated() throws Exception {
//		
//		CollectedRegistrationFeeBatch batch = new CollectedRegistrationFeeBatch();
//		batch.setBatchNumber("somebatch");
//		
//		String tempDir = System.getProperty("java.io.tmpdir");
//		serviceImpl.setFilePath(tempDir);
//		
//		serviceImpl.executeCollectedRegistrationFeeJob(batch);
//		
//		File file = new File(tempDir + File.separator + "somebatch.xml");
//		assertTrue(file.exists());
//		
//		FileReader fileReader = new FileReader(file);
//		BufferedReader bufferedReader = new BufferedReader(fileReader);
//		try {
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
//	            System.out.println(line);
//	        }
//		}
//		finally {
//			bufferedReader.close();
//			fileReader.close();
//		}
//
//	}

	/**
	 * Should create a file with some data
	 * 
	 * Job invoke will throw an NPE that is ignored
	 * 
	 * @throws Exception
	 */
//	public void testExecuteCollectedRegistrationFeeJob_fileCreatedAndPopulated() throws Exception {
//		
//		CollectedRegistrationFeeBatch batch = new CollectedRegistrationFeeBatch();
//		batch.setBatchNumber("somebatch");
//		
//		List<RegistrationFeePaymentCollected> paymentsCollected = batch.getFeePayments();
//		
//		RegistrationFeePaymentCollected paymentOne = new RegistrationFeePaymentCollected();
//		paymentOne.setCharityReference("Charity One");
//		paymentOne.setFeeAmount(100);
//		paymentOne.setInvoiceNumber("Invoice-001");
//		paymentOne.setVatAmount(17.5);
//		paymentsCollected.add(paymentOne);
//		
//		RegistrationFeePaymentCollected paymentTwo = new RegistrationFeePaymentCollected();
//		paymentTwo.setCharityReference("Charity One");
//		paymentTwo.setFeeAmount(77.99);
//		paymentTwo.setInvoiceNumber("Invoice-002");
//		paymentTwo.setVatAmount(11.7);
//		paymentsCollected.add(paymentTwo);
//		
//		String tempDir = System.getProperty("java.io.tmpdir");
//		serviceImpl.setFilePath(tempDir);
//		
//		serviceImpl.executeCollectedRegistrationFeeJob(batch);
//		
//		File file = new File(tempDir + File.separator + "somebatch.xml");
//		assertTrue(file.exists());
//		
//		FileReader fileReader = new FileReader(file);
//		BufferedReader bufferedReader = new BufferedReader(fileReader);
//		try {
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
//	            System.out.println(line);
//	        }
//		}
//		finally {
//			bufferedReader.close();
//			fileReader.close();
//		}
//
//	}

	/**
	 * Create test data file
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateTestDataFile() throws Exception {
		
		CollectedRegistrationFeeBatch batch = new CollectedRegistrationFeeBatch();
		batch.setBatchNumber("somebatch");
		
		List<RegistrationFeePaymentCollected> paymentsCollected = batch.getFeePayments();
		
		for (String details : testRecords) {
			String[] values = details.split(";");

			RegistrationFeePaymentCollected rfpc = new RegistrationFeePaymentCollected();
			rfpc.setCharityReference(values[2]);
			rfpc.setFeeAmount(Double.parseDouble(values[1]));
			rfpc.setInvoiceNumber(values[0]);
			rfpc.setVatAmount(Double.parseDouble(values[3]));
			paymentsCollected.add(rfpc);
		}
		
		String tempDir = System.getProperty("java.io.tmpdir");
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {

			/*
			 * Open the file and prepare the BufferedWriter 
			 */
			fileWriter = new FileWriter(tempDir + File.separator + "collectedRegistrationFeeBatch.xml");
			bufferedWriter = new BufferedWriter(fileWriter);
			
			/*
			 * Set up XStream
			 */
	        XStream xstream = new XStream(new DomDriver());
	        xstream.setClassLoader(getClass().getClassLoader());
	
			/*
			 * Write out the contents of the batch as XML
			 */
	        xstream.toXML(batch, bufferedWriter);
		
		} catch (IOException e) {
		    LOGGER.error("IO Exception"+fileWriter, e);
		}
		finally {
			if ( bufferedWriter != null ) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
				    LOGGER.error("IO Exception : Buffer Writer "+bufferedWriter, e);
				}
			}
			if ( fileWriter != null ) {
				try {
					fileWriter.close();
				} catch (IOException e) {
				    LOGGER.error("IO Exception : File Writer"+fileWriter, e);
				}
			}
		}

		File file = new File(tempDir + File.separator + "collectedRegistrationFeeBatch.xml");
		assertTrue(file.exists());

	}
	
	private static String[] testRecords = new String[] {
			"ICR000000000001;100.00;VMG:CHARITY_REGISTRATION:1;17.5",
			"ICR000000000002;100.00;VMG:CHARITY_REGISTRATION:2;17.5",
			"ICR000000000003;100.00;VMG:CHARITY_REGISTRATION:3;17.5",
			"ICR000000000004;100.00;VMG:CHARITY_REGISTRATION:4;17.5",
	};
	
}
