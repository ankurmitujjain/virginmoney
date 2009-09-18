package com.virginmoneygiving.services.template.freemarker;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.virginmoneygiving.services.template.TemplateService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * The Class FreemarkerTemplateServiceImpl.
 */
public class FreemarkerTemplateServiceImpl implements TemplateService {

	/** The freemarker config. */
	private Configuration freemarkerConfig;	
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(FreemarkerTemplateServiceImpl.class);
	
	/**
	 * Sets the freemarker config factory.
	 * 
	 * @param freemarkerConfig the new freemarker config factory
	 */
	public void setFreemarkerConfigFactory(Configuration freemarkerConfig) {
		this.freemarkerConfig = freemarkerConfig;
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.services.template.TemplateService#processTemplate(java.lang.String, java.util.Map)
	 */
	public String processTemplate(String templateName, Map model) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("FreemarkerTemplateServiceImpl::processTemplate() - START");
        }

        Template template = null;
	    String templateContent = null;
	    
    	String fileLocation = System.getProperty(GIVING_CONF_HOME);	
    	File document = new File(fileLocation);
    	
    	try {
    	    freemarkerConfig.setDirectoryForTemplateLoading(document);
    	    template = freemarkerConfig.getTemplate(templateName);
    	    templateContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    	} catch (IOException e) {
    	    LOGGER.error("Error occured while setting up the directory for email template loading.", e);
            throw new RuntimeException("Error occured while setting up the directory for email template loading.", e);
        } catch (TemplateException e) {
            LOGGER.error("Error occured while processing email template.", e);
            throw new RuntimeException("Error occured while processing email template.", e);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("FreemarkerTemplateServiceImpl::processTemplate() - END");
        }
        return templateContent;
			
	}

}
