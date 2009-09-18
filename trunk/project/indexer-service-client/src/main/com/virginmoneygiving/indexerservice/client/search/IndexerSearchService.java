package com.virginmoneygiving.indexerservice.client.search;

import java.io.IOException;
import java.util.List;

import com.virginmoneygiving.indexerservice.client.exception.IndexerServiceException;
import com.virginmoneygiving.indexerservice.messages.IndexedType;

/**
 * The Interface IndexerSearchService contains a search method
 * to search for the specified types by name.
 * 
 * @author Ian Priest
 */
public interface IndexerSearchService {

    /**
     * Search for the specified types by name.
     * 
     * searchString will be tokenized and filtered before being converted into a
     * wildcard query. Query will be an OR of the keywords with wildcard tails,
     * ANDed with an OR of the types Keywords are matched against name, location
     * and description.
     * E.g.
     * Search for "Royal Bird" of type CHARITY will result in the query
     * "+(name:royal* name:bird*) +(type:CHARITY)" and would get (for example)
     * CHARITY name=Royal Bird Society CHARITY name=Society for the Protection
     * of Birds CHARITY name=Royal Society all with differing scores
     * 
     * and wouldn't get CHARITY name="Animal Society" (type correct, name
     * contains no match) EVENT name="Royal Bird Shoot" (name matches both
     * keywords but type wrong)
     * 
     * Unfortunately, Solr is in a sulk about wildcards, so doesn't support
     * field boosting with wildcard searches. Hence rather bizzare comparator
     * stuff.
     * 
     * @param searchString the search string
     * @param type the type
     * 
     * @return the list< indexer document>
     * 
     * @throws IOException      * @throws SolrServerException      * @throws Exception      * @throws IllegalArgumentException      * @throws IndexerServiceException the indexer service exception
     */

    public List<IndexerDocument> search(String searchString, IndexedType... type) throws IndexerServiceException ;

}
