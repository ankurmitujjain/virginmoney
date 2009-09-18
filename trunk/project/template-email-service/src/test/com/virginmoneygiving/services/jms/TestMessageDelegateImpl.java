package com.virginmoneygiving.services.jms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.thoughtworks.xstream.XStream;
import com.virginmoneygiving.services.email.TemplateEmailService;
import com.virginmoneygiving.templateemail.service.messages.Message;
import com.virginmoneygiving.services.jms.MessageDelegateImpl;

import static org.mockito.Mockito.*;

/**
 * Test the email message JMS endpoint. 
 * @author Robin Bramley, Opsera Ltd.
 *
 */
public class TestMessageDelegateImpl {

    /** Unit under test. */
    private MessageDelegateImpl bean;
    
    @Mock
    private TemplateEmailService mockTemplateEmailService;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bean = new MessageDelegateImpl();
        bean.setTemplateEmailService(mockTemplateEmailService);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.virginmoneygiving.services.jms.MessageDelegateImpl#handleMessage(java.lang.String)}.
     */
    @Test
    public void testHandleMessageEmptyString() throws Exception {
        try {
            bean.handleMessage("");
            fail("Exception expected");
        } catch (Exception expected) {
            assertNotNull(expected.getMessage());
        }
        
        verify(mockTemplateEmailService, never()).sendEmail(isA(Message.class));
    }

    /**
     * Test method for {@link com.virginmoneygiving.services.jms.MessageDelegateImpl#handleMessage(java.lang.String)}.
     */
    @Test
    public void testHandleMessageString() throws Exception {
        Message message = new Message();
        message.setDestination("test@foo.com");
        message.setMessage("foo");

        XStream xstream = new XStream();
        
        bean.handleMessage(xstream.toXML(message));
        verify(mockTemplateEmailService).sendEmail(isA(Message.class));
    }
}
