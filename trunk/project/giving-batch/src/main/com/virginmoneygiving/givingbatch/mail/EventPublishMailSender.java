package com.virginmoneygiving.givingbatch.mail;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.thoughtworks.xstream.XStream;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.CharityAdminDetailsVO;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.helper.EmailHelper;
import com.virginmoneygiving.templateemail.service.messages.Message;

/**
 * When an event is published, this class gets all email ids grouyped by charity
 * and sends mail to them.
 * 
 * @author dibaskumarp
 */
public class EventPublishMailSender implements ItemProcessor<Event, Event> {

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(EventPublishMailSender.class);

    /** giving Service. * */
    private GivingService givingService;

    /** Email template. */
    private JmsTemplate jmsEmailTemplate;

    /**
     * This method used to process the event details.
     * 
     * @param event of type Event
     * 
     * @return EventSummary object
     * 
     * @throws Exception -While process the event details throws Exception.
     */
    public Event process(Event event)  {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside EventPublishMailSender - process - Start");
        }
        String eventName = event.getName();
        Timestamp eventStartDate = event.getEventDatetime();
        // Get email list of all event administrators for this event

        List<CharityAdminDetailsVO> charityAdminDetails =
                givingService.fetchCharityAdministratorEmailList(event.getId());
        Map<Long, String> emailAndUrlPerCharity = new HashMap<Long, String>();
        for (CharityAdminDetailsVO charityAdminDetailsVO : charityAdminDetails) {
            Long charityId = charityAdminDetailsVO.getCharityId();
            if (charityId != null) {

                String eventAdminMailList = "";
                if (emailAndUrlPerCharity.containsKey(charityId)) {
                    eventAdminMailList = emailAndUrlPerCharity.get(charityId);
                }
                eventAdminMailList =
                        eventAdminMailList
                                + charityAdminDetailsVO.getEmailAddress() + ";";
                emailAndUrlPerCharity.put(charityId, eventAdminMailList);
            }
        }
        for (Long charitykey : emailAndUrlPerCharity.keySet()) {
            Charity charity = givingService.fetchCharityDetailsById(charitykey);
            String charityPageUrl = charity.getWebsiteURL();
            String charityAdminEmailAddress =
                    emailAndUrlPerCharity.get(charitykey);
            sendMailToEventAdmins(charityPageUrl, charityAdminEmailAddress,
                    eventName, eventStartDate);
        }
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside EventPublishMailSender - process - End");
        }
        return event;
    }

    /**
     * Creates the email essage object and send it.
     * 
     * @param charityPageUrl - charity url.
     * @param charityAdminEmailAddress - all admin mail ids concatenated with semicolon.
     * @param eventName - event name.
     * @param eventStartDate - event startdate.
     */
    public void sendMailToEventAdmins(String charityPageUrl,
            String charityAdminEmailAddress, String eventName,
            Timestamp eventStartDate) {
        String eventStartFormattedDate = "";
        if (eventStartDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            eventStartFormattedDate = dateFormat.format((eventStartDate));
        }
        if (StringUtils.isNotBlank(charityAdminEmailAddress)) {
            Message message =
                    EmailHelper.createMessageforMailingToEventAdmins(
                            charityPageUrl, charityAdminEmailAddress,
                            eventName, eventStartFormattedDate);
            sendEmail(message);
        }
    }

    /**
     * Take the message object and send it to publisher using jms template.
     * 
     * @param message - message object.
     */
    private void sendEmail(Message message) {
        XStream xstream = new XStream();
        final String textMessage = xstream.toXML(message);
        jmsEmailTemplate.send(new MessageCreator() {
            public javax.jms.Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage(textMessage);
            }
        });
    }

    /**
     * Gets the giving service.
     * 
     * @return the givingService
     */
    public GivingService getGivingService() {
        return givingService;
    }

    /**
     * Sets the giving service.
     * 
     * @param givingService the givingService to set
     */
    public void setGivingService(GivingService givingService) {
        this.givingService = givingService;
    }

    /**
     * Gets the jms email template.
     * 
     * @return the jmsEmailTemplate
     */
    public JmsTemplate getJmsEmailTemplate() {
        return jmsEmailTemplate;
    }

    /**
     * Sets the jms email template.
     * 
     * @param jmsEmailTemplate the jmsEmailTemplate to set
     */
    public void setJmsEmailTemplate(JmsTemplate jmsEmailTemplate) {
        this.jmsEmailTemplate = jmsEmailTemplate;
    }
}
