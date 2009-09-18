import java.io.File
import java.util.ArrayList

import org.apache.solr.core.CoreContainer
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer
import org.apache.solr.common.SolrInputDocument
import org.apache.solr.client.solrj.SolrQuery

import com.virginmoneygiving.indexerservice.jms.impl.DefaultIndexerMessageDelegate
import com.virginmoneygiving.indexerservice.solr.SolrIndexerService
import com.virginmoneygiving.indexerservice.messages.*

import com.thoughtworks.xstream.XStream


narrative "Update Search Results When A Charity Is Modified", {
	as_a "Giver"
	i_want "to get updated search results when a charity is modified"
	so_that "I can be sure the search results are up to date"
}

before_each "Initialize the Search Index", {
	given "there are two charities called 'RSPC Cats' and 'RSP Dogs'", {
		
		// Underlying embedded solr server
		configFile = new File("W:/trunk/root/giving/indexer-service/test/solr-config/solr.xml")
		coreContainer = new CoreContainer("W:/trunk/root/giving/indexer-service/test/solr-config", configFile ) 
		solrCore = coreContainer.getCore("core0")
		solrServer = new EmbeddedSolrServer( solrCore )
		solrServer.deleteByQuery("*:*")
		solrServer.commit()
		
		// Gretna Indexer Client
		indexerService = new SolrIndexerService()
		indexerService.solrServer = solrServer
		
		// Gretna Message Delegate
		messageDelegate = new DefaultIndexerMessageDelegate()
		messageDelegate.indexerService = indexerService

		// XStream class -> xml converstion
		xstream = new XStream()

		// Add a couple of charities
		charity = new IndexerUpdate()
		charity.type = IndexedType.CHARITY
		charity.id = "1"
		charity.name = "RSPC Cats"
		addCharityMessage = new UpdateInIndexMessage()
		addCharityMessage.indexerUpdate = charity
		xml = xstream.toXML(addCharityMessage)
		messageDelegate.handleMessage(xml);
		
		charity = new IndexerUpdate()
		charity.type = IndexedType.CHARITY
		charity.id = "2"
		charity.name = "RSPC Dogs"
		addCharityMessage = new UpdateInIndexMessage()
		addCharityMessage.indexerUpdate = charity
		xml = xstream.toXML(addCharityMessage)
		messageDelegate.handleMessage(xml);
	}
}

scenario "An existing charity's name is changed", {
	when "'RSPC Cats' name is changed to 'RSPC Hens'", {

		charity = new IndexerUpdate()
		charity.type = IndexedType.CHARITY
		charity.id = "2"
		charity.name = "RSPC Hens"
		updateCharityMessage = new UpdateInIndexMessage()
		updateCharityMessage.indexerUpdate = charity
		xml = xstream.toXML(updateCharityMessage)
		messageDelegate.handleMessage(xml);
	
	}
	and
	when "a search string of 'pc he' is entered", {
		query = new SolrQuery().setQuery( "pc~0.4 he~0.4" );
	    resultSet = solrServer.query( query )
	    System.out.println(resultSet)
	    docs = resultSet.getResults()
	    System.out.println(docs)
	}
	then "the search result contains one hit for 'RSPC Hens'", {
		docs.numFound.shouldBe 1
		doc = docs.get(0)
		println doc
		doc.getFieldValue("id").toString().shouldBe "{CHARITY}2"
		doc.getFieldValue("type").toString().shouldBe "CHARITY"
		doc.getFieldValue("name").toString().shouldBe "RSPC Hens"
	}
}

scenario "An existing charity is deleted", {
	when "'RSPC Dogs' is deleted", {
		charity = new IndexerUpdate()
		charity.type = IndexedType.CHARITY
		charity.id = "2"
		deleteCharityMessage = new DeleteFromIndexMessage()
		deleteCharityMessage.indexerUpdate = charity
		xml = xstream.toXML(deleteCharityMessage)
		messageDelegate.handleMessage(xml);
	}
	and
	when "a search string of 'dogs' is entered", {
		query = new SolrQuery().setQuery( "dogs" );
	    resultSet = solrServer.query( query )
	    System.out.println(resultSet)
	    docs = resultSet.getResults()
	    System.out.println(docs)
	}
	then "the search result contains no hits", {
		docs.numFound.shouldBe 0
	}
}

scenario "An new charity is added", {
	when "'RSPC Hens  is created", {
		charity = new IndexerUpdate()
		charity.type = IndexedType.CHARITY
		charity.id = "3"
		charity.name = "RSPC Hens"
		updateCharityMessage = new UpdateInIndexMessage()
		updateCharityMessage.indexerUpdate = charity
		xml = xstream.toXML(updateCharityMessage)
		messageDelegate.handleMessage(xml);
	}
	and
	when "a search string of 'rspc' is entered", {
		query = new SolrQuery().setQuery( "rspc" );
	    resultSet = solrServer.query( query )
	    System.out.println(resultSet)
	    docs = resultSet.getResults()
	    System.out.println(docs)
	}
	then "the search result contains 3 hits for 'RSPC Dogs', 'RSCP Cats' and 'RSPC Hens'", {
		docs.numFound.shouldBe 3
	}

	scenario "A new FundraiserActivity is added", {
		when "'Growing a Beard is added", {
			fundraiserActivity = new IndexerUpdate()
			fundraiserActivity.type = IndexedType.FUNDRAISER_ACTIVITY
			fundraiserActivity.id = "1"
			fundraiserActivity.name = "Main Fundraiser"
			fundraiserActivity.logoUrl = "http://photo.url" 
			fundraiserActivity.url = "http://fundraiser.url" 
			fundraiserActivity.title = "The Title" 
			fundraiserActivity.fundraisers.add("Team Member 1")
			fundraiserActivity.fundraisers.add("Team Member 2")
			
			charity1 = new IndexerCharity();
			charity1.name = "Charity 1"
			charity1.url = "http://charity1.url"
			fundraiserActivity.charities.add(charity1)

			charity2 = new IndexerCharity();
			charity2.name = "Charity 2"
			charity2.url = "http://charity2.url"
			fundraiserActivity.charities.add(charity2)
			
			updateMessage = new UpdateInIndexMessage()
			updateMessage.indexerUpdate = fundraiserActivity
			xml = xstream.toXML(updateMessage)
			messageDelegate.handleMessage(xml);
		}
		and
		when "a search string of 'Main Fundraiser' is entered", {
			query = new SolrQuery().setQuery( "Main Fundraiser" );
		    resultSet = solrServer.query( query )
		    System.out.println(resultSet)
		    docs = resultSet.getResults()
		    System.out.println(docs)
		}
		then "the search result contains 1 hits for 'Main Fundraiser'", {
			docs.numFound.shouldBe 1
			doc = docs.get(0)
			println doc
			doc.getFieldValue("id").toString().shouldBe "{FUNDRAISER_ACTIVITY}1"
			doc.getFieldValue("type").toString().shouldBe "FUNDRAISER_ACTIVITY"
			doc.getFieldValue("name").toString().shouldBe "Main Fundraiser"
			charityUrlCollection = doc.getFieldValues("charity-url")
			charityNameCollection = doc.getFieldValues("charity-name")
			
			charityUrlCollection.size.shouldBe 2
			charityNameCollection.size.shouldBe 2
			
		}
	}
}
