package com.virginmoneygiving.services.template.freemarker;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.virginmoneygiving.services.template.TemplateService;
import com.virginmoneygiving.services.template.freemarker.FreemarkerTemplateServiceImpl;

import freemarker.template.Configuration;
import freemarker.template.Template;

import static org.mockito.Mockito.*;

/**
 * @author Robin Bramley, Opsera Ltd.
 *
 */
public class TestFreemarkerTemplateServiceImpl {
    
    /** unit under test. */
    private FreemarkerTemplateServiceImpl bean;

    @Mock
    private Configuration mockFreemarkerConfig;
    
    @Mock
    private Template mockTemplate;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bean = new FreemarkerTemplateServiceImpl();
        bean.setFreemarkerConfigFactory(mockFreemarkerConfig);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.virginmoneygiving.services.template.freemarker.FreemarkerTemplateServiceImpl#processTemplate(java.lang.String, java.util.Map)}.
     */
    @Test
    public void testProcessTemplate() throws Exception {
        System.setProperty(TemplateService.GIVING_CONF_HOME, System.getProperty("java.io.tmpdir"));
        when(mockFreemarkerConfig.getTemplate(anyString())).thenReturn(mockTemplate);
        
        Map model = new HashMap();
        model.put("a", "b");
        
        bean.processTemplate("foo", model);
        verify(mockFreemarkerConfig).setDirectoryForTemplateLoading(isA(File.class));
        verify(mockFreemarkerConfig).getTemplate(anyString());
    }

    /**
     * Test method for {@link com.virginmoneygiving.services.template.freemarker.FreemarkerTemplateServiceImpl#processTemplate(java.lang.String, java.util.Map)}.
     */
    @Test
    public void testProcessTemplateException() throws Exception {
        System.setProperty(TemplateService.GIVING_CONF_HOME, System.getProperty("java.io.tmpdir"));
        when(mockFreemarkerConfig.getTemplate(anyString())).thenThrow(new RuntimeException("oops."));
        
        Map model = new HashMap();
        model.put("a", "b");
        
        try {
            bean.processTemplate("foo", model);
            fail("Exception expected");
        } catch (Exception expected) {
            assertNotNull(expected.getMessage());
        }
        verify(mockFreemarkerConfig).setDirectoryForTemplateLoading(isA(File.class));
        verify(mockFreemarkerConfig).getTemplate(anyString());
    }
}
