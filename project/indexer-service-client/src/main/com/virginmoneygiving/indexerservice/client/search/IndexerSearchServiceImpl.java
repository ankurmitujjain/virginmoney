package com.virginmoneygiving.indexerservice.client.search;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceTokenizer;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.RemoveDuplicatesTokenFilterFactory;
import org.apache.solr.analysis.WhitespaceTokenizerFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.virginmoneygiving.indexerservice.client.exception.IndexerServiceException;
import com.virginmoneygiving.indexerservice.messages.IndexedType;

/**
 * The Class contains method to search for the specified types by name.
 * 
 * @author Ian Priest
 */
public class IndexerSearchServiceImpl implements IndexerSearchService {

    /** The Constant FIELD_DESCRIPTION. */
    private static final String FIELD_DESCRIPTION = "description";

    /** The Constant FIELD_LOCATION. */
    private static final String FIELD_LOCATION = "location";

    /** The Constant FIELD_NAME. */
    private static final String FIELD_NAME = "name";

    /**
     * Re-order docs to virgin required ordering.
     * 
     * Name match beats location match beats description match.
     * If all those are equal it uses alphabetical name ordering and finally,
     * if and event, uses charity name ordering.
     * 
     * @author ian.priest@opsera.com
     */
    static class VirginComparator implements

    Comparator<IndexerDocument>,Serializable {

        /**
         * Default version Id.
         */
        private static final long serialVersionUID = 1L;
        /** The Constant WORD_BOUNDARY. */
        private static final String WORD_BOUNDARY = "\\b";

        /**
         * Reminder of the comparator contract: return -1 if
         * lhs is less than rhs, 0 if lhs equals rhs, 1 if lhs greater than rhs.
         * 
         * @param lhs the lhs
         * @param rhs the rhs
         * 
         * @return the int
         */
        public int compare(IndexerDocument lhs, IndexerDocument rhs) {

            /*
             * Check for name match.
             */
            int lhsScore = fieldMatch(lhs.getName(), lhs.getQueryKeywords());
            int rhsScore = fieldMatch(rhs.getName(), rhs.getQueryKeywords());
            if (lhsScore != rhsScore) {
                return lhsScore - rhsScore;
            }

            /*
             * Check for location match
             */
            lhsScore = fieldMatch(lhs.getLocation(), lhs.getQueryKeywords());
            rhsScore = fieldMatch(rhs.getLocation(), rhs.getQueryKeywords());
            if (lhsScore != rhsScore) {
                return lhsScore - rhsScore;
            }

            /*
             * Check for description match
             */
            lhsScore = fieldMatch(lhs.getDescription(), lhs.getQueryKeywords());
            rhsScore = fieldMatch(rhs.getDescription(), rhs.getQueryKeywords());
            if (lhsScore != rhsScore) {
                return lhsScore - rhsScore;
            }

            /*
             * All else being equal, sort alphabetical by name.
             * Note that the results are ordered in a different direction for
             * second-rank ordering. While the result shows higher keyword scores  
             * first, it must show names in alphabetical order: 'A' is smaller than 
             * 'B', so alphabetical ordering must be smallest to largest.
             * Hence we "reverse" the alphabetical score here to make it work
             * correctly.
             */
            int alphabeticScore = lhs.getName().compareToIgnoreCase(rhs.getName());
            if (alphabeticScore != 0) {
                return alphabeticScore * -1;
            }

            /*
             * Finally, if we're comparing events, order by the charity name.
             * No name always comes first, followed by alphabetic ordering.
             */
            if ( lhs.getType() == IndexedType.EVENT ) {
                
                int lhsCharityNamesSize = lhs.getCharityNames().size();
                int rhsCharityNamesSize = rhs.getCharityNames().size();
                
                // d1 is no-name, d2 isn't, so d1 is bigger
                if ( lhsCharityNamesSize == 0 && rhsCharityNamesSize > 0 ) {
                    return 1;
                }
                // d2 is no-name, d1 isn't, so d2 is bigger
                if ( rhsCharityNamesSize == 0 && lhsCharityNamesSize > 0 ) {
                    return -1;
                }

                // They both have charity names, so order alphabetically
                if ( lhsCharityNamesSize > 0 && rhsCharityNamesSize > 0 ) {
                    
                    String lhsCharityName = lhs.getCharityNames().get(0); 
                    String rhsCharityName = rhs.getCharityNames().get(0); 

                    /*
                     * See note above about alphabetic ordering.
                     */
                    alphabeticScore = lhsCharityName.compareToIgnoreCase(rhsCharityName);
                    if (alphabeticScore != 0) {
                        return alphabeticScore * -1;
                    }
                }                
            }
            
            // They are equal
            return 0;
        }

        /**
         * See if the field value contains any keyword matches.
         * 
         * Note that the match attempts to mimic Solr matches, so we only return
         * matches for any field that Solr would have found a match for. Hence
         * we look for "keyword" matches from the start of words. E.g. Keyword
         * "one" will match field value "Charity One" but not field value "Some
         * money".
         * Will (crudely) return a higher score the more keyword matches there are
         * on the field.
         * 
         * @param fieldValue the field value
         * @param keywords the keywords
         * 
         * @return +1 for each match found, 0 if no matches.
         */
        int fieldMatch(String fieldValue, List<String> keywords) {

            int val = 0;

            if (StringUtils.isEmpty(fieldValue)) {
                return 0;
            }

            for (String keyword : keywords) {
                StringBuilder regex =
                    new StringBuilder(WORD_BOUNDARY).append(keyword);
                Pattern p =
                    Pattern.compile(regex.toString(), Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(fieldValue);
                while (m.find()) {
                    val++;
                }
            }

            return val;
        }
    }

    /** The Constant LOGGER. */
    private static final Logger LOGGER =
        Logger.getLogger(IndexerSearchServiceImpl.class);

    /** The solr server. */
    public SolrServer solrServer = null;

    /**
     * Gets the solr server.
     * 
     * @return the solr server
     */
    public SolrServer getSolrServer() {
        return solrServer;
    }

    /**
     * Sets the solr server.
     * 
     * @param solrServer the new solr server
     */
    public void setSolrServer(SolrServer solrServer) {
        this.solrServer = solrServer;
    }

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
    public List<IndexerDocument> search(String searchString,
        IndexedType... type) throws IndexerServiceException {

        // Analyze and filter the searchTerm
        TokenStream analyzedSearchString = analyze(searchString);
        // Also keep a list of the filtered keywords - we'll need them later
        List<String> queryKeywords = new ArrayList<String>();

        // The final lucene query
        BooleanQuery finalQuery = new BooleanQuery(false);

        /*
         * Construct the lucene name, location and descrption keyword querys
         */
        BooleanQuery keywordQuery = new BooleanQuery(false);
        Term nameTerm = new Term(FIELD_NAME);
        Term locationTerm = new Term(FIELD_LOCATION);
        Term descriptionTerm = new Term(FIELD_DESCRIPTION);
        Token token = new Token();
        try {
			while ((token = analyzedSearchString.next(token)) != null) {
			    WildcardQuery nameQuery = createWildcardQuery(nameTerm, token);
			    keywordQuery.add(nameQuery, BooleanClause.Occur.SHOULD);

			    WildcardQuery locationQuery =
			        createWildcardQuery(locationTerm, token);
			    keywordQuery.add(locationQuery, BooleanClause.Occur.SHOULD);

			    WildcardQuery descriptionQuery =
			        createWildcardQuery(descriptionTerm, token);
			    keywordQuery.add(descriptionQuery, BooleanClause.Occur.SHOULD);

			    queryKeywords.add(token.term());
			}
		} catch (IOException e) {
			LOGGER.error("Got IO exception" , e);
			throw new IndexerServiceException(e);
		}
        finalQuery.add(keywordQuery, BooleanClause.Occur.MUST);

        /*
         * Construct the lucene type query
         */
        if (type != null && type.length > 0) {
            BooleanQuery typeQuery = new BooleanQuery(false);
            Term typeTerm = new Term("type");

            for (IndexedType indexedType : type) {
                TermQuery termQuery =
                    new TermQuery(typeTerm.createTerm(indexedType.value()));
                typeQuery.add(termQuery, BooleanClause.Occur.SHOULD);
            }

            finalQuery.add(typeQuery, BooleanClause.Occur.MUST);
        }
        LOGGER.debug("lucene query = " + finalQuery.toString(FIELD_NAME));

        /*
         * Construct the Solr query, using the final lucene query as the text
         * base with "name" as the default field, ordered by score descending
         */
        SolrQuery query = new SolrQuery(finalQuery.toString(FIELD_NAME));
        LOGGER.debug("solr query = " + query.toString());

        /*
         * Run the query
         */
        QueryResponse queryResponse = null;
        try {
			queryResponse = solrServer.query(query);
		} catch (SolrServerException e) {
			LOGGER.error("Got SolrServerException exception" , e);
			throw new IndexerServiceException(e);
		}
        SolrDocumentList docs = queryResponse.getResults();

        /*
         * Convert the results to IndexerDocument and re-order.
         */
        List<IndexerDocument> indexerDocs =
            new ArrayList<IndexerDocument>(docs.size());
        for (SolrDocument solrDocument : docs) {
            IndexerDocument indexerDocument =
                new IndexerDocument(solrDocument, false, queryKeywords);
            indexerDocs.add(indexerDocument);
        }
        return reorderList(indexerDocs);
    }

    /**
     * Use comparator to order by name match, then location match, then
     * description match. Comparator orders smallest -> greatest, so reverse the
     * order then return.
     * 
     * @param indexerDocs the indexer docs
     * 
     * @return the list< indexer document>
     */
    static List<IndexerDocument> reorderList(List<IndexerDocument> indexerDocs) {
        Collections.sort(indexerDocs, new VirginComparator());
        Collections.reverse(indexerDocs);
        return indexerDocs;
    }

    /**
     * Creates the wildcard query.
     * 
     * @param nameTerm the name term
     * @param token the token
     * 
     * @return the wildcard query
     */
    private WildcardQuery createWildcardQuery(Term nameTerm, Token token) {
        WildcardQuery wildQuery =
            new WildcardQuery(nameTerm.createTerm(token.term() + "*"));
        return wildQuery;
    }

    /**
     * Analyze the searchString in a way that will match the one used to index
     * the input docs.
     * 
     * Need to find a way of making this configurable rather than hard-coded.
     * 
     * @param searchString the search string
     * 
     * @return the token stream
     */
    private TokenStream analyze(String searchString) {

        Reader searchStringReader = new StringReader(searchString);
        WhitespaceTokenizer whitespaceTokenizer =
            new WhitespaceTokenizerFactory().create(searchStringReader);

        TokenStream lowerCaseFilter =
            new LowerCaseFilterFactory().create(whitespaceTokenizer);
        TokenStream removeDuplicates =
            new RemoveDuplicatesTokenFilterFactory().create(lowerCaseFilter);

        return removeDuplicates;
    }
}
