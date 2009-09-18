package com.virginmoneygiving.indexerservice.solr;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;

import com.virginmoneygiving.indexerservice.messages.IndexedType;
import com.virginmoneygiving.indexerservice.messages.IndexerCharity;
import com.virginmoneygiving.indexerservice.messages.IndexerService;
import com.virginmoneygiving.indexerservice.messages.IndexerUpdate;

/**
 * Solr implementation of CharityIndexerClient.
 * 
 * @author Ian Priest
 */
public class SolrIndexerService implements IndexerService {

    /** Log messages. */
    private Logger LOGGER =
        Logger.getLogger(SolrIndexerService.class.getName());
    
    /** Client access to Solr server. */
    private SolrServer solrServer = null;

    /**
     * Getter for the underlying SolrService client.
     * 
     * @return SolrServer the solr instance
     */
    public SolrServer getSolrServer() {
        return solrServer;
    }

    /**
     * Setter for the SolrService client.
     * 
     * @param server the server
     */
    @Resource
    public void setSolrServer(SolrServer server) {
        this.solrServer = server;
    }

    /**
     * Remove a document from the index.
     * 
     * @param indexerUpdate the document to delete.
     */
    public void deleteFromIndexMessage(IndexerUpdate indexerUpdate) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("deleteFromIndexMessage() -  START");
		}

        QName qname = getCompoundId(indexerUpdate);

        // Convert exceptions to unchecked to avoid
        // coupling interface to Solr
        try {
            solrServer.deleteById(qname.toString());
            solrServer.commit();
        } catch (SolrServerException e) {

            LOGGER.warn("Re-throwing Solr exception trying to index indexerUpdate.id "
                    + indexerUpdate.getId(), e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.warn("Re-throwing Solr exception trying to index indexerUpdate.id "
                    + indexerUpdate.getId(), e);
            throw new RuntimeException(e);
        }
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("deleteFromIndexMessage(IndexerUpdate) - END");
		}
	}

    /**
     * Add a document to the index.
     * 
     * Document type indicates the real object being indexed. Index is flattened
     * so that all objects go into the same solr index. Duplicate ID's between
     * the types are avoided by using the type field as a QName namespace for
     * the ID.
     * 
     * @param indexerUpdate the object to index.
     */
    public void updateInIndexMessage(IndexerUpdate indexerUpdate) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("updateInIndexMessage() -  START");
		}
		SolrInputDocument doc1 = new SolrInputDocument();

        List<IndexerCharity> charities = indexerUpdate.getCharities();
        for (IndexerCharity indexerCharity : charities) {
            doc1.addField("charity-name", indexerCharity.getName());
            doc1.addField("charity-url", indexerCharity.getUrl());
            doc1.addField("charity-id", indexerCharity.getId());
        }

        /*
         * Date needs to be converted to restricted ISO8601 format.
         * 1995-12-31T23:59:59Z
         * Good old Jakarta Commons!
         */
        if (indexerUpdate.getDate() != null) {
            Calendar cal = indexerUpdate.getDate().toGregorianCalendar();
            String iso8601Format = DateFormatUtils.ISO_DATETIME_FORMAT.format(cal);
            doc1.addField("date", iso8601Format + "Z");
        }
        doc1.addField("description", indexerUpdate.getDescription());

        List<String> fundraisers = indexerUpdate.getFundraisers();
        for (String fundraiser : fundraisers) {
            doc1.addField("fundraiser", fundraiser);
        }

        doc1.addField("location", indexerUpdate.getLocation());
        doc1.addField("logo-url", indexerUpdate.getLogoUrl());
        doc1.addField("name", indexerUpdate.getName());
        doc1.addField("registered-number", indexerUpdate.getRegisteredNumber());
        doc1.addField("title", indexerUpdate.getTitle());
        doc1.addField("url", indexerUpdate.getUrl());
        doc1.addField("type", indexerUpdate.getType().value());
        doc1.addField("team-activity", indexerUpdate.isTeamActivity());

        QName qname = getCompoundId(indexerUpdate);
        doc1.addField("id", qname.toString());

        // Convert exceptions to unchecked to avoid
        // coupling interface to Solr
        try {
            solrServer.add(doc1);
            solrServer.commit();
        } catch (SolrServerException e) {
            LOGGER.warn("Re-throwing Solr exception trying to index indexerUpdate.id "
                    + indexerUpdate.getId(), e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.warn("Re-throwing Solr exception trying to index indexerUpdate.id "
                    + indexerUpdate.getId(), e);
            throw new RuntimeException(e);
        }
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("updateInIndexMessage(IndexerUpdate) - END");
		}
	}

    /*
     * Create a unique Id by combining some values of type being indexed.
     *
     * There are 3 different types of object we need to create ids for:
     * 
     * EVENT type can be created by operation-web and be open, and can be set up 
     * by charities as open, restricted or charity only.
     * Open event ids are just the id of the event and it's type. E.g. {1}EVENT
     * Specific charityy events also use the charity-id as the secondary part
     * of the id. E.g. {1}EVENT-999
     *
     * CHARITY types are just indexed using the charity-id and the type. E.g. {1}CHARITY
     * 
     * FUNDRAISER_ACTIVITY types are indexed using the activity-id, the type, and the fundraiser-id 
     * as the secondary part. E.g. {1}FUNDRAISER_ACTIVITY-999.
     *  
     * Note that the id of the object itself always forms the namespace so is
     * available using QName.getNamespace().
     */
    /**
     * Gets the compound id.
     * 
     * @param indexerUpdate the indexer update
     * 
     * @return the compound id
     */
    QName getCompoundId(IndexerUpdate indexerUpdate) {

        String type = indexerUpdate.getType().value();
        String primaryId = indexerUpdate.getId();
        String secondaryId = null;

        if ( IndexedType.EVENT == indexerUpdate.getType() ) {
            List<IndexerCharity> charities = indexerUpdate.getCharities();
            if ( charities.size() > 0 ) {
                // Closed events are indexed with the charity that will benefit,
                // so this should always work.
                secondaryId = charities.get(0).getId();
            }
        }
        if ( IndexedType.FUNDRAISER_ACTIVITY == indexerUpdate.getType() ) {
            secondaryId = indexerUpdate.getFundraiserId();
        }

        StringBuilder localPart = new StringBuilder(type);
        if ( secondaryId != null ) {
            localPart.append("-").append(secondaryId);
        }

        QName qname = new QName(primaryId, localPart.toString());
		return qname;
    }

}
