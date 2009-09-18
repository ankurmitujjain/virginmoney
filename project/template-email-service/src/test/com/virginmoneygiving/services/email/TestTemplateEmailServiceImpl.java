package com.virginmoneygiving.services.email;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import com.virginmoneygiving.templateemail.service.messages.Message;
import com.virginmoneygiving.services.constants.EmailTemplateNames;
import com.virginmoneygiving.services.constants.EmailTemplateNamesHelper;
import com.virginmoneygiving.services.email.TemplateEmailServiceImpl;
import com.virginmoneygiving.services.template.TemplateService;

import static org.mockito.Mockito.*;

/**
 * @author Robin Bramley, Opsera Ltd.
 *
 */
public class TestTemplateEmailServiceImpl {

    /** unit under test. */
    private TemplateEmailServiceImpl bean;

    @Mock
    private JavaMailSender mockMailService;
    
    @Mock
    private TemplateService mockTemplateService;

    private Message message;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bean = new TemplateEmailServiceImpl();
        bean.setMailService(mockMailService);
        bean.setTemplateService(mockTemplateService);
        
        message = new Message();
        message.setDestination("test@foo.com");
        message.setMessage("foo");
        message.setTemplateFile("foo");
        
        System.setProperty(TemplateService.GIVING_CONF_HOME, System.getProperty("java.io.tmpdir"));

        Properties props = new Properties();
        props.put("foo", "bar");
        EmailTemplateNamesHelper.fixEmailTemplateNames(props); // nasty hack for package visibility
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.virginmoneygiving.services.email.TemplateEmailServiceImpl#sendEmail(com.virginmoneygiving.givingmanagement.service.messages.Message)}.
     */
    @Test
    public void testSendEmailMessage() throws Exception {
        bean.sendEmail(message); 
    }

    /**
     * Test method for {@link com.virginmoneygiving.services.email.TemplateEmailServiceImpl#sendEmail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testSendEmailStringStringStringStringString() throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("AmendBankAccountDetailsForm.pdf");
        bean.sendEmail("from", "to", "subject", "foo", "foo", null);
    }

    /**
     * Test method for {@link com.virginmoneygiving.services.email.TemplateEmailServiceImpl#sendEmail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testSendEmailStringStringStringStringListStringString() throws Exception {
        // TODO
    }

}
