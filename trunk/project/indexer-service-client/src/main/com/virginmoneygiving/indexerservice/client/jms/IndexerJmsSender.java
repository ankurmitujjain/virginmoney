package com.virginmoneygiving.indexerservice.client.jms;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.thoughtworks.xstream.XStream;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.Fundraiser;
import com.virginmoneygiving.giving.domain.FundraiserActivity;
import com.virginmoneygiving.giving.domain.FundraiserGroup;
import com.virginmoneygiving.giving.domain.FundraisingCharitySplit;
import com.virginmoneygiving.giving.domain.UrlDetails;
import com.virginmoneygiving.indexerservice.messages.DeleteFromIndexMessage;
import com.virginmoneygiving.indexerservice.messages.IndexedType;
import com.virginmoneygiving.indexerservice.messages.IndexerCharity;
import com.virginmoneygiving.indexerservice.messages.IndexerUpdate;
import com.virginmoneygiving.indexerservice.messages.UpdateInIndexMessage;

/**
 * Send a FundraiserActivity JMS message to the indexer service so that the activity 
 * can be indexed for searching.
 * 
 * @author Ian Priest
 *
 */
public class IndexerJmsSender {

    private static final Logger LOGGER = Logger.getLogger(IndexerJmsSender.class);
    
	/** The Spring JMS message template to use for sending */
    private JmsTemplate jmsTemplate;

    /**
     * Setter for the JMS Template
     * 
     * @param jmsTemplate
     */
    @Resource
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Send a Message to the indexer service (and any other client of the 
     * JMS destination) to notify that a FundraiserActivity has been updated.
     * 
     * Converts the FundraiserActivity to an instance of IndexerUpdate and sets the
     * relevant details before sending the message.
     * 
     * Note that an IndexerUpdate will be sent for each individual fundraiser that is
     * involved in the activity.
     * @param fundraiser TODO
     * @param fundraiserActivity
     */
	public void sendFundraiserActivitySavedMessage(Fundraiser fundraiser, FundraiserActivity fundraiserActivity) {

        IndexerUpdate indexerUpdate = getIndexerUpdate(fundraiser, fundraiserActivity);
        sendUpdateMessage(indexerUpdate);            
	}
	
    /**
     * Send a Message to the indexer service (and any other client of the 
     * JMS destination) to notify that a FundraiserActivity has been deleted.
     * 
     * Converts the FundraiserActivity to an instance of IndexerUpdate and sets the
     * relevant details before sending the message.
     * @param fundraiser TODO
     * @param fundraiserActivity
     */
    public void sendFundraiserActivityDeletedMessage(Fundraiser fundraiser, FundraiserActivity fundraiserActivity) {

        IndexerUpdate indexerUpdate = getIndexerUpdate(fundraiser, fundraiserActivity);
        sendDeleteMessage(indexerUpdate);
    }

    /**
     * Send a Message to the indexer service (and any other client of the 
     * JMS destination) to notify that a Charity has been updated.
     * 
     * Converts the Charity to an instance of IndexerUpdate and sets the
     * relevant details before sending the message.
     * 
     * @param charity
     */
    public void sendCharitySavedMessage(Charity charity) {

        /*
         * Create IndexerUpdate object 
         */
        IndexerUpdate indexerUpdate = new IndexerUpdate();

        indexerUpdate.setId(Long.toString(charity.getId()));
        indexerUpdate.setType(IndexedType.CHARITY);

        indexerUpdate.setLogoUrl(charity.getLogoUrl());
        indexerUpdate.setUrl(charity.getVmgCharityURL());
        indexerUpdate.setTitle(charity.getName());
        indexerUpdate.setName(charity.getName());
        indexerUpdate.setDescription(charity.getDescription());
        indexerUpdate.setRegisteredNumber(charity.getRegisteredCharityNumber());
        
        sendUpdateMessage(indexerUpdate);
    }
    
    /**
     * Send a Message to the indexer service (and any other client of the 
     * JMS destination) to notify that a FundraiserActivity has been deleted.
     * 
     * Converts the FundraiserActivity to an instance of IndexerUpdate and sets the
     * relevant details before sending the message.
     * 
     * @param charity
     */
    public void sendCharityDeletedMessage(Charity charity) {

        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(Long.toString(charity.getId()));
        indexerUpdate.setType(IndexedType.CHARITY);
        indexerUpdate.setName(charity.getName());
        
        sendDeleteMessage(indexerUpdate);
    }

    /**
     * Send a Message to the indexer service (and any other client of the 
     * JMS destination) to notify that an Event has been updated.
     * 
     * Converts the Event to an instance of IndexerUpdate and sets the
     * relevant details before sending the message.
     * @param charity The charity hosting or leaving the event. Can be null for ops-web open events.
     * @param event The event to index
     */
    public void sendEventSavedMessage(Charity charity, Event event) {

        if ( LOGGER.isTraceEnabled() ) {
            LOGGER.trace("Sending to indexer: " + event );
            LOGGER.trace("Sending to indexer: " + charity );
        }
        
        /*
         * Create IndexerUpdate object 
         */
        IndexerUpdate indexerUpdate = new IndexerUpdate();

        indexerUpdate.setId(Long.toString(event.getId()));
        indexerUpdate.setType(IndexedType.EVENT);

        indexerUpdate.setLogoUrl(event.getLogoUrl());
        indexerUpdate.setUrl(event.getWebsiteUrl());
        indexerUpdate.setTitle(event.getName());
        indexerUpdate.setName(event.getName());
        indexerUpdate.setDescription(event.getDescription());
        indexerUpdate.setLocation(event.getLocation().getDescription());
        
        // Event date.
        Timestamp eventCompletionDate = event.getEventDatetime();
        convertAndSetDate(indexerUpdate, eventCompletionDate);

        if ( charity != null ) {
            IndexerCharity indexerCharity = new IndexerCharity();
            indexerCharity.setId(charity.getId().toString());
            indexerCharity.setName(charity.getName());
            if( charity.getUrlDetails() != null ) {
                indexerCharity.setId(charity.getUrlDetails().getUrl());
            }
            indexerUpdate.getCharities().add(indexerCharity);
        }
        sendUpdateMessage(indexerUpdate);
    }
    
    /**
     * Send a Message to the indexer service (and any other client of the 
     * JMS destination) to notify that an Event has been deleted.
     * 
     * Converts the event to an instance of IndexerUpdate and sets the
     * relevant details before sending the message.
     * @param charity The charity hosting or leaving the event. Can be null for ops-web open events.
     * @param event The event.
     */
    public void sendEventDeletedMessage(Charity charity, Event event) {

        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(Long.toString(event.getId()));
        indexerUpdate.setType(IndexedType.EVENT);
        indexerUpdate.setName(event.getName());
        
        if ( charity != null ) {
            IndexerCharity indexerCharity = new IndexerCharity();
            indexerCharity.setId(charity.getId().toString());
            indexerUpdate.getCharities().add(indexerCharity);
        }
        sendDeleteMessage(indexerUpdate);
    }

    private void sendUpdateMessage(final IndexerUpdate indexerUpdate) {
	    
        final UpdateInIndexMessage updateMessage = new UpdateInIndexMessage();
        updateMessage.setIndexerUpdate(indexerUpdate);
        sendMessage(updateMessage);
	}

    private void sendDeleteMessage(final IndexerUpdate indexerUpdate) {
        
        final DeleteFromIndexMessage deleteMessage = new DeleteFromIndexMessage();
        deleteMessage.setIndexerUpdate(indexerUpdate);
        sendMessage(deleteMessage);
    }

    private void sendMessage(final Object messageContent) {
        
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {

                TextMessage message = session.createTextMessage();
                XStream xstream = new XStream();
                message.setText(xstream.toXML(messageContent));
                return message;
            }
        });
    }

    /**
     * Convert timestamp to XMLGregorianCalendar and set on the indexerUpdate.
     *
     * @param indexerUpdate to set the date on
     * @param eventCompletionDate to convert
     */
    private void convertAndSetDate(IndexerUpdate indexerUpdate,
        Timestamp eventCompletionDate) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(eventCompletionDate.getTime());
        try {
            XMLGregorianCalendar xmlDate = null;
            xmlDate = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(cal);
            indexerUpdate.setDate(xmlDate);
        } catch (DatatypeConfigurationException e) {
            LOGGER.warn("Unable to convert eventCompletionDate " + eventCompletionDate + " to XMLGregorianCalendar", e);
        }
    }

    /**
     * Convert activity to IndexerUpdate for a specific fundraiser.
     *
     * @param fundraiser The fundraiser to act on
     * @param fundraiserActivity The fundraising activity
     * @return an IndexerUpdate with filled in fields
     */
    IndexerUpdate getIndexerUpdate(Fundraiser fundraiser, FundraiserActivity fundraiserActivity) {

        if ( fundraiser == null ) {
            throw new IllegalArgumentException( "fundraiser cannot be null");
        }
        if ( fundraiserActivity == null ) {
            throw new IllegalArgumentException( "fundraiser cannot be null");
        }
        
        IndexerUpdate indexerUpdate = new IndexerUpdate();

        indexerUpdate.setId(Long.toString(fundraiserActivity.getId()));
        indexerUpdate.setType(IndexedType.FUNDRAISER_ACTIVITY);

        // Fundraiser id is used to create the solr doc id. 
        // It's not actually indexed in it's own right
        if (fundraiser.getId() != null) {
        	indexerUpdate.setFundraiserId(Long.toString(fundraiser.getId()));
        }

        // Rather silly amount of redirection for a name!
        StringBuilder fundraiserName = new StringBuilder();
        fundraiserName.append(fundraiser.getUserRole().getUser().getPerson().getForename());
        fundraiserName.append(" ");
        fundraiserName.append(fundraiser.getUserRole().getUser().getPerson().getSurname());
        indexerUpdate.setName(fundraiserName.toString());
        
        Timestamp eventCompletionDate = fundraiserActivity.getEventCompletionDate();
        convertAndSetDate(indexerUpdate, eventCompletionDate);
        
        indexerUpdate.setLogoUrl(fundraiserActivity.getFundraiserImageUrl());
        
        if ( fundraiserActivity.getFundraiserPage() != null ) {
            indexerUpdate.setTitle(fundraiserActivity.getFundraiserPage().getTitle());
        }
        
        /*
         * If it's a team activity, use the team URL rather than the individual
         * fundraiser url.
         * Also need to check that fundraiser has a url set, as they may not.
         */
        UrlDetails urlDetails = null;
        if( fundraiserActivity.isTeamActivity() ) {
            FundraiserGroup fundraiserGroup = fundraiserActivity.getFundraiserGroup();
            if ( fundraiserGroup != null ) {
                urlDetails = fundraiserGroup.getUrlDetails();
            }
            indexerUpdate.setTeamActivity(true);
        }
        else {
            if (fundraiser.getUrlDetails() != null) {
                urlDetails = fundraiser.getUrlDetails();
            }
        }
        if ( urlDetails != null ) {
            indexerUpdate.setUrl(urlDetails.getUrl());
        }
        
        Set<FundraisingCharitySplit> charities = fundraiserActivity.getFundraisingCharitySplit();
        for (FundraisingCharitySplit fundraisingCharitySplit : charities) {
            IndexerCharity charity = new IndexerCharity();
            charity.setId(fundraisingCharitySplit.getCharity().getId().toString());
            charity.setName(fundraisingCharitySplit.getCharity().getName());
            charity.setUrl(fundraisingCharitySplit.getCharity().getVmgCharityURL());
            indexerUpdate.getCharities().add(charity);
        }

        return indexerUpdate;
    }

}
