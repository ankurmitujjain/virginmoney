package com.virginmoneygiving.services.template;

import java.util.Map;

/**
 * The Interface TemplateService.
 */
public interface TemplateService {
	
	/** The GIVIN g_ con f_ home. */
	public final String GIVING_CONF_HOME = "GIVING_CONF_HOME";
	
	/**
	 * Process template.
	 * 
	 * @param templateName the template name
	 * @param model the model
	 * 
	 * @return the string
	 */
	public String processTemplate(String templateName, Map model);
	
}
