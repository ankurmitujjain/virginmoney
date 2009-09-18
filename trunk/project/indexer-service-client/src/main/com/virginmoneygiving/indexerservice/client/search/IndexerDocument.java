package com.virginmoneygiving.indexerservice.client.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.solr.common.SolrDocument;

import com.virginmoneygiving.indexerservice.messages.IndexedType;

/**
 * Proxy to SolrDocument.
 *
 * Provides standard getters for the indexed properties, de-namespaces the
 * id and allows highlighting of the found text. (Solr highlighting doesn't
 * work for wildcard searches).
 *
 * @author ipriest
 */
public class IndexerDocument {

    SolrDocument solrDocument = null;
    private boolean highlight;
    private List<String> queryKeywords;
  
    /**
     * 
     * @param solrDocument the result
     * @param highlight true if the matched name field values should be highlighted
     * @param queryKeywords the keywords to highlight in the name files, from the lucene query
     */
    public IndexerDocument(SolrDocument solrDocument, boolean highlight, List<String> queryKeywords) {
        this.solrDocument = solrDocument;
        this.highlight = highlight;
        this.queryKeywords = queryKeywords;
    }
    
    public boolean isHighlight() {
        return highlight;
    }


    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        QName qname = QName.valueOf((String) solrDocument.getFieldValue("id"));
        return qname.getNamespaceURI();
    }

    /**
     * Gets the value of the logoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoUrl() {
        return (String)solrDocument.getFieldValue("logo-url");
    }

    /**
     * Gets the value of the name property.
     * 
     * If highlight was enabled, will wrap <B></B> around the discovered text.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        String name = (String)solrDocument.getFieldValue("name");
        if ( isHighlight() ) {
            name = hightlight(name);
        }
        
        return name;
    }

    String hightlight(String name) {
        
        // Create a regex to find the query text in the name
        // Just add a "." in front of any "*".
        String highlightedName = name; 
        for (String queryKeyword : queryKeywords) {
            highlightedName = replace(queryKeyword, highlightedName);
        }
        return highlightedName;
    }

    String replace(String queryKeyword, String name) {

        // Build a case-insensitive Pattern and match it in name
        Pattern pattern = Pattern.compile(queryKeyword, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        
        // Build a new String with the pattern wrapped by <b></b>
        StringBuffer sb = new StringBuffer();
        while ( matcher.find() ) {
            matcher.appendReplacement(sb, "<b>" + matcher.group() + "</b>");
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return (String)solrDocument.getFieldValue("title");
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return (String)solrDocument.getFieldValue("url");
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return (String)solrDocument.getFieldValue("description");
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return (String)solrDocument.getFieldValue("location");
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDate() {
        return (Date)solrDocument.getFieldValue("date");
    }

    /**
     * Gets the value of the registeredNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegisteredNumber() {
        return (String)solrDocument.getFieldValue("registered-number");
    }

    /**
     * Gets the value of the charity-id property.
     *
     * @return List<String> the values
     */
    public List<String> getCharityIds() {
        
        Object value = solrDocument.getFieldValue("charity-id");
        return toList(value);
        
    }

    /**
     * Gets the value of the charity-url property.
     *
     * @return List<String> the values
     */
    public List<String> getCharityUrls() {
        Object value = solrDocument.getFieldValue("charity-url");
        return toList(value);

    }

    /**
     * Gets the value of the charity-name property.
     *
     * @return List<String> the values
     */
    public List<String> getCharityNames() {

        Object value = solrDocument.getFieldValue("charity-name");
        return toList(value);
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link IndexedType }
     *     
     */
    public IndexedType getType() {
        return IndexedType.fromValue((String)solrDocument.getFieldValue("type"));
    }


    /**
     * Get the solr scrore for this hit.
     * @return Float the solr score
     */
    public Float getScore() {
        return (Float)solrDocument.getFieldValue("score");
    }
    
    /**
     * Get the teamActivity flag.
     * @return Boolean true or false
     */
    public boolean isTeamActivity() {
        return (Boolean) solrDocument.getFieldValue("team-activity");
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getName());
        sb.append("[").append(solrDocument.toString()).append(", ");
        sb.append("highlight=").append(highlight).append(", ");
        sb.append("queryKeywords=").append(queryKeywords).append("]");
        return sb.toString();
    }

    /**
     * @return the queryKeywords
     */
    public List<String> getQueryKeywords() {
        return queryKeywords;
    }
    
    /**
     * Solr will return a String if just one entry, a list
     * if multiple entries. Coerce to a List<String>
     *
     * @param field value from solr
     * @return List<String>, empty list if value is null
     */
    private List<String> toList(Object value) {
        List<String> values = null;

        if ( value instanceof List ) {
            values = (List<String>)value;
        }
        else if ( value instanceof String ) {
            values = new ArrayList<String>();
            values.add((String) value);
        }
        else if ( value == null ) {
            values = new ArrayList<String>();
        }
        else {
            throw new RuntimeException("Value has unexpected class:" + value.getClass().getName());
        }
        return values;
    }

}
