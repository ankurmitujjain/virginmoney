package com.virginmoneygiving.services.email;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;


import com.virginmoneygiving.templateemail.service.messages.Message;
import com.virginmoneygiving.templateemail.service.messages.EmailParameter;
import com.virginmoneygiving.services.constants.EmailTemplateNames;
import com.virginmoneygiving.services.template.TemplateService;


/**
 * The Class TemplateEmailServiceImpl.
 */
public class TemplateEmailServiceImpl implements TemplateEmailService {

	//TODO Possibly can be injected using spring property place holder.
	/** The Constant DEFAULT_SIGNATURE_FILE_NAME. */
	private static final String DEFAULT_SIGNATURE_FILE_NAME = "/templates/Jo_Barnett.jpg";
	//TODO Pick up this reference froma a property file.
	/** The GIVIN g_ con f_ home. */
	public final String GIVING_CONF_HOME = "GIVING_CONF_HOME";

	/** The email attachment form location. */
	public String emailAttachmentFormLocation = null;

	/** The LOGGER. */
	public static final Logger LOGGER = Logger.getLogger(TemplateEmailServiceImpl.class);
	
	/** The mail service. */
	private JavaMailSender mailService;
	
	/** The template service. */
	private TemplateService templateService;

    /* (non-Javadoc)
     * @see com.virginmoneygiving.services.email.TemplateEmailService#sendEmail(Message)
     */
    public void sendEmail(Message message) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::sendEmail() - START");
        }

        this.sendEmail(message.getSender(), message.getDestination(), message.getSubject(),
                message.getTemplateFile(), message.getEmailParameterList(),
                    message.getDestinationCC(), message.getDestinationBCC(), message.getFileNamesList());

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::sendEmail() - END");
        }
    }

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.services.email.TemplateEmailService#sendEmail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List)
	 */
	public void sendEmail(String from, String to, String subject, String templateFile, String message,
            List<String> fileNames) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::sendEmail() - START");
        }

        Map<String, String> model = new HashMap<String, String>();
		model.put("message", message);
		String templateFileName = EmailTemplateNames.getTemplateName(templateFile);
		sendMessage(from, to, subject, templateService.processTemplate(templateFileName, model), null, null,
		        fileNames);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::sendEmail() - END");
        }
    }

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.services.email.TemplateEmailService#sendEmail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.String, java.util.List)
	 */
	public void sendEmail(String from, String to, String subject, String templateFile, List messageData,
            String cc, String bcc, List<String> fileNames) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::sendEmail() - START");
        }

		Map<String, String> model = new HashMap<String, String>();
		String templateFileName = EmailTemplateNames.getTemplateName(templateFile);
		for(int i = 0;  i< messageData.size() ; i++ ) {
			EmailParameter emailParameter = (EmailParameter) messageData.get(i);
			model.put(emailParameter.getKey(), emailParameter.getValue());
		}
		sendMessage(from, to, subject, templateService.processTemplate(templateFileName, model),
		        cc, bcc, fileNames);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::sendEmail() - END");
        }
    }

	/**
	 * Send message.
	 * 
	 * @param from the from
	 * @param rcptAddress the rcpt address
	 * @param subject the subject
	 * @param bodyText the body text
	 * @param addDefaultSignature the add default signature
	 * @param ccAddress the cc address
	 * @param bccAddress the bcc address
	 * @param fileNames the file names
	 */
	private void sendMessage(String from, String rcptAddress, String subject, String bodyText,
	        String ccAddress, String bccAddress, final List<String> fileNames) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::sendMessage() - START");
        }

        if(LOGGER.isDebugEnabled()){
			LOGGER.debug("BodyText: " + bodyText);
        }
		final String prepSubject = subject;
		final String prepBody = bodyText;
		final String prepFrom = from;
		final String prepTo = rcptAddress;
		final String prepCc = ccAddress;
		final String prepBcc = bccAddress;

		MimeMessagePreparator prep = new MimeMessagePreparator() {
			public void prepare(final MimeMessage message) throws Exception {
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("TemplateEmailServiceImpl::$MimeMessagePreparator.prepare() - START");
                }

                MimeMessageHelper msgHelper = new MimeMessageHelper(message, true, "UTF-8");
				msgHelper.setFrom(prepFrom);
				msgHelper.setTo(convertToAddresstoStringArray(prepTo));
				if(prepCc != null && prepCc.length() > 0) {
				    msgHelper.setCc(convertToAddresstoStringArray(prepCc));
				}
				if(prepBcc != null && prepBcc.length() > 0) {
				    msgHelper.setBcc(convertToAddresstoStringArray(prepBcc));
				}
				msgHelper.setSubject(prepSubject);
				msgHelper.setText(prepBody,true);

				if (fileNames != null && fileNames.size() > 0) {
				    for (String s : fileNames) {
				        msgHelper.addAttachment(s, getFileToAttach(s));
				    }

				}

                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("TemplateEmailServiceImpl::$MimeMessagePreparator.prepare() - END");
                }
            }
		};

		mailService.send(prep);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::sendMessage() - END");
        }
    }

	/**
	 * Sets the mail service.
	 * 
	 * @param mailService the new mail service
	 */
	@Resource
	public void setMailService(JavaMailSender mailService) {
		this.mailService = mailService;
	}

	/**
	 * Sets the template service.
	 * 
	 * @param templateService the new template service
	 */
	@Resource
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

   /**
    * Gets the file to attach.
    * 
    * @param fileName the file name
    * 
    * @return the file to attach
    */
   private File getFileToAttach(String fileName) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::getFileToAttach() - START");
        }


       // build up the full file name, allow for the presence or absences of a trailing / in thr peoprty entry
       String fullFileName = StringUtils.removeEnd(emailAttachmentFormLocation,"/") + "/" +  fileName;
       File returnFile = new File(fullFileName);

       if (!returnFile.exists()) {
           LOGGER.error("getFileToAttach() Unable to load file " + fullFileName);
       }
        return returnFile;
    }

	/**
	 * Convert to addressto string array.
	 * 
	 * @param prepTo the prep to
	 * 
	 * @return the string[]
	 */
	private String[] convertToAddresstoStringArray(String prepTo) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::convertToAddresstoStringArray() - START");
        }

        String[] list = prepTo.split(";");
		int cnt=0;
		for (int i=0; i< list.length; i++){
			if(list[i] == null || list[i].trim().length() == 0 ){
				cnt++;
			}
		}
		String[] retArr = new String[list.length - cnt];
		int j = 0;
		for (int i=0; i< list.length; i++){
			if(list[i] != null && list[i].trim().length() > 0 ){
				retArr[j] = list[i];
				j++;
			}
		}

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TemplateEmailServiceImpl::convertToAddresstoStringArray() - END");
        }
        return retArr;
	}

    /**
     * Gets the email attachment form location.
     * 
     * @return the emailAttachmentFormLocation
     */
    public String getEmailAttachmentFormLocation() {
        return emailAttachmentFormLocation;
    }

    /**
     * Sets the email attachment form location.
     * 
     * @param emailAttachmentFormLocation the emailAttachmentFormLocation to set
     */
    public void setEmailAttachmentFormLocation(String emailAttachmentFormLocation) {
        this.emailAttachmentFormLocation = emailAttachmentFormLocation;
    }


}

