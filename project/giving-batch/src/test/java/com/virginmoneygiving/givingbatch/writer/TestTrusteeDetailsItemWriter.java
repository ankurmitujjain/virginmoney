package com.virginmoneygiving.givingbatch.writer;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.virginmoneygiving.givingbatch.domain.Address;
import com.virginmoneygiving.givingbatch.domain.CharityTrusteeDetails;
import com.virginmoneygiving.givingbatch.domain.TrusteeDetails;

/**
 * Test Anti Money Laundering XML output.
 * @author Robin Bramley, Opsera Ltd.
 *
 */
public class TestTrusteeDetailsItemWriter {

    /** unit under test. */
    private TrusteeDetailsItemWriter bean;
    
    @Mock
    private StepExecution stepExecution;
    
    @Mock
    private ExecutionContext executionContext;

    @Test
    public void mockTest() {
        Assert.assertEquals(1,1);
    }
    
    /**
     * @throws java.lang.Exception
     */
    //@Before
    public void setUp() throws Exception {
        String filename = "testresults/SB_AML_data_" + System.currentTimeMillis() + ".xml";
        Resource resource = new FileSystemResource(filename);
        
        System.out.println("Filename: " + filename);

        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.addAlias("Charities", List.class); //AMLData.class);
        marshaller.addAlias("Charity", CharityTrusteeDetails.class);
        marshaller.addAlias("Trustee", TrusteeDetails.class);         

        Map<String,String> rootAttrs = new HashMap<String,String>();
        rootAttrs.put("xmlns","http://www.virginmoneygiving.com/type/anti-money-laundering/2009/1");
        
        bean = new TrusteeDetailsItemWriter();
        bean.setRootTagName("AMLData");
        bean.setRootElementAttributes(rootAttrs);
        bean.setMarshaller(marshaller);
        bean.setOverwriteOutput(true);
        bean.setResource(resource);
        
        MockitoAnnotations.initMocks(this);
        
        when(stepExecution.getExecutionContext()).thenReturn(executionContext);
        bean.beforeStep(stepExecution);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.virginmoneygiving.givingbatch.writer.TrusteeDetailsItemWriter#write(java.util.List)}.
     */
    //@Test
    public void testWriteList() throws Exception {
        // set up data
        Address address = new Address();
        address.setAddress1("line1");
        address.setAddress2("line2");
        address.setAddress3("line3");
        address.setAddress4("line4");
        address.setPostcode("F00 8AR");

        Address address1 = new Address();
        address1.setAddress1("line1");
        address1.setAddress2("line2");
        address1.setAddress3("line3");
        address1.setAddress4("line4");
        address1.setPostcode("F00 8AR");

        Address address2 = new Address();
        address2.setAddress1("line1");
        address2.setAddress2("line2");
        address2.setAddress3("line3");
        address2.setAddress4("line4");
        address2.setPostcode("F00 8AR");

        Address address3 = new Address();
        address3.setAddress1("line1");
        address3.setAddress2("line2");
        address3.setAddress3("line3");
        address3.setAddress4("line4");
        address3.setPostcode("F00 8AR");

        
        TrusteeDetails trusteeDetails = new TrusteeDetails();
        trusteeDetails.setAddress(address1);
        trusteeDetails.setDob(new Date());
        trusteeDetails.setForename("Mickey");
        trusteeDetails.setSurname("Mouse");

        TrusteeDetails trusteeDetails2 = new TrusteeDetails();
        trusteeDetails2.setAddress(address2);
        trusteeDetails2.setDob(new Date());
        trusteeDetails2.setForename("Minnie");
        trusteeDetails2.setSurname("Mouse");

        TrusteeDetails trusteeDetails3 = new TrusteeDetails();
        trusteeDetails3.setAddress(address3);
        trusteeDetails3.setDob(new Date());
        trusteeDetails3.setForename("Donald");
        trusteeDetails3.setSurname("Duck");

        List<TrusteeDetails> trustees = new ArrayList<TrusteeDetails>();
        trustees.add(trusteeDetails);
        trustees.add(trusteeDetails2);
        trustees.add(trusteeDetails3);
        
        Map<Long, CharityTrusteeDetails> map 
            = new HashMap<Long,CharityTrusteeDetails>();
        
        CharityTrusteeDetails charityTrusteeDetails = new CharityTrusteeDetails();
        charityTrusteeDetails.setName("Foo");
        charityTrusteeDetails.setReferenceNumber("12345");
        charityTrusteeDetails.setAddress(address);
        charityTrusteeDetails.setTrustees(trustees);
        
        map.put(1L, charityTrusteeDetails);

        when(executionContext.containsKey(anyString())).thenReturn(false);
        when(executionContext.get(anyString())).thenReturn(map);
        
        bean.open(executionContext);
        bean.write(null);
        bean.close();
        bean.afterStep(stepExecution);
        
        verify(executionContext).containsKey(anyString());
        verify(executionContext).get(anyString());
    }
}
