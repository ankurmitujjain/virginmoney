package com.virginmoneygiving.givingbatch.helper;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.virginmoneygiving.giving.domain.Donation;
import com.virginmoneygiving.givingbatch.common.VMGProperty;
import com.virginmoneygiving.templateemail.service.messages.EmailParameter;
import com.virginmoneygiving.templateemail.service.messages.Message;

/**
 * Helper class used for handling the data mapping for the Email object.
 * <p/>
 * NOTE: This class should be used for implementing data mapping for send email
 * module.
 * 
 * @author dibaskumarp
 */
public final class EmailHelper {

    /** creating instance of Logger. */
    private static final Logger LOGGER = Logger.getLogger(EmailHelper.class);

    /**
     * Default constructor.
     */
    private EmailHelper() {

    }

    /**
     * Creates Payment Settled Message.
     * 
     * @param email - email.
     * @param salutation the salutation
     * 
     * @return - message object.
     */
    public static Message paymentSettledMessage(String email, String salutation) {
        Message message = new Message();
        message.setSender(VMGProperty.getProperty("sender.email.address"));
        message.setDestination(email);
        message.setTemplateFile(VMGProperty
                .getProperty("payment.settled.template"));
        // message.setTemplateFile("C:/VMG-workspace/external_configuration/templates/UCPHB150.ftl");
        message.setSubject("We have transferred donations into your account");
        
        EmailParameter emailParameter = new EmailParameter();
        emailParameter.setKey("UCPHB01500");
        emailParameter.setValue(salutation);
        message.getEmailParameterList().add(emailParameter);

        
        emailParameter = new EmailParameter();
        emailParameter.setKey("UCPHB01501");
        String emailAddress =  "arrk.vmg@arrkgroup.com";
        emailParameter.setValue(emailAddress);
        message.getEmailParameterList().add(emailParameter);
        
        emailParameter = new EmailParameter();
        emailParameter.setKey("UCPHB01502");
        String number =  "044-2766114323";
        emailParameter.setValue(number);
        message.getEmailParameterList().add(emailParameter);
        
        emailParameter = new EmailParameter();
        emailParameter.setKey("EDI0060");
        emailParameter.setValue(VMGProperty
                .getProperty("vmg.email.signature.name"));
        message.getEmailParameterList().add(emailParameter);
        
        emailParameter = new EmailParameter();
        emailParameter.setKey("EDI0690");
        emailParameter.setValue(VMGProperty
                .getProperty("vmg.email.signature.designation"));
        
        return message;
    }
    
    /**
     * Creates the message for failed regular donation to donor.
     * 
     * @param email the email
     * @param donation the donation
     * 
     * @return the message
     */
    public static Message createMessageForFailedRegularDonationToDonor(Donation donation, String email){
    	Message message = new Message();
    	message.setSender(VMGProperty.getProperty("regular.donation.sender"));
    	//message.setSender("tarun.adiwal@arrkgroup.com");
    	//message.setDestination("kiran.dhawan@arrkgroup.com");
    	message.setDestination(email);
    	message.setTemplateFile(VMGProperty.getProperty("regular.donation.template"));
    	//message.setTemplateFile("C:/VMG-workspace/external_configuration/templates/UCPHB0460.ftl");
    	message.setSubject("Virgin Money Giving - Your regular donation to "+donation.getCharity().getName());
    	
    	EmailParameter emailParameter = new EmailParameter();
    	emailParameter.setKey("DONORNAME");
    	emailParameter.setValue(donation.getPerson().getForename());
    	
    	message.getEmailParameterList().add(emailParameter);
    	
    	emailParameter = new EmailParameter();
    	emailParameter.setKey("CHARITYNAME");
    	emailParameter.setValue(donation.getCharity().getName());
    	message.getEmailParameterList().add(emailParameter);
    	
    	//emailParameter = new EmailParameter();
    	//emailParameter.setKey("DONORPORTALURL");
    	//emailParameter.setValue(donation.getPerson().get)
    	//message.getEmailParameterList().add(emailParameter);
    	
    	return message;
    }

    /**
     * Create email message to send mail to charity admins when an event is
     * published.
     * 
     * @param charityPageUrl - charity url.
     * @param charityAdminEmailAddress - all admin mail ids concatenated with semicolon.
     * @param eventName - event name.
     * @param eventStartDate - event startdate.
     * 
     * @return mesaage object.
     */
    public static Message createMessageforMailingToEventAdmins(
            String charityPageUrl, String charityAdminEmailAddress,
            String eventName, String eventStartDate) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside EmailHelper - createMessageforMailingToEventAdmins - Start");
        }
        Message message = new Message();
        message.setSender(VMGProperty.getProperty("event.publish.from"));
        message.setDestination(charityAdminEmailAddress);
        message.setSubject(VMGProperty.getProperty("event.publish.subject"));
        message.setTemplateFile(VMGProperty
                .getProperty("event.publish.template"));

        // Set parameters
        EmailParameter emailParameter = new EmailParameter();
        emailParameter.setKey("EDI0340");
        if (StringUtils.isNotBlank(charityPageUrl)) {
            emailParameter.setValue(charityPageUrl);
        }
        else {
            emailParameter.setValue(VMGProperty
                    .getProperty("charity.url.notavailable"));
        }        
        message.getEmailParameterList().add(emailParameter);

        emailParameter = new EmailParameter();
        emailParameter.setKey("EDI0150");
        emailParameter.setValue(eventName);
        message.getEmailParameterList().add(emailParameter);

        emailParameter = new EmailParameter();
        emailParameter.setKey("EDI0170");
        emailParameter.setValue(eventStartDate);
        message.getEmailParameterList().add(emailParameter);
        
        emailParameter = new EmailParameter();
        emailParameter.setKey("EDI0050");
        emailParameter.setValue(VMGProperty.getProperty("vmg.email.signature.imagefile"));
        message.getEmailParameterList().add(emailParameter);

        emailParameter = new EmailParameter();
        emailParameter.setKey("EDI0060");
        emailParameter.setValue(VMGProperty
                .getProperty("vmg.email.signature.name"));
        message.getEmailParameterList().add(emailParameter);
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside EmailHelper - createMessageforMailingToEventAdmins - End");
        }
        return message;
    }
}
