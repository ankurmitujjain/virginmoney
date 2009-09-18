package com.virginmoneygiving.services.email;

import com.virginmoneygiving.templateemail.service.messages.Message;
import java.util.List;

/**
 * The Interface TemplateEmailService.
 */
public interface TemplateEmailService {

    /**
     * Send email.
     * 
     * @param message the message
     */
    public void sendEmail(Message message);
	
	/**
	 * Send email.
	 * 
	 * @param from the from
	 * @param to the to
	 * @param subject the subject
	 * @param templateFile the template file
	 * @param message the message
	 * @param fileNames the file names
	 */
	public void sendEmail(String from, String to, String subject, String templateFile, String message,
	        List<String> fileNames);
	
	/**
	 * Send email.
	 * 
	 * @param from the from
	 * @param to the to
	 * @param subject the subject
	 * @param templateFile the template file
	 * @param message the message
	 * @param cc the cc
	 * @param bcc the bcc
	 * @param fileNames the file names
	 */
	@SuppressWarnings("unchecked")
	public void sendEmail(String from, String to, String subject, String templateFile, List message, String cc, String bcc,
	        List<String> fileNames);
}

