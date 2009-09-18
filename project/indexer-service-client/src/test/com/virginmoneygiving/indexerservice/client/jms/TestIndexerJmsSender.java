package com.virginmoneygiving.indexerservice.client.jms;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.Fundraiser;
import com.virginmoneygiving.giving.domain.FundraiserActivity;
import com.virginmoneygiving.giving.domain.FundraiserGroup;
import com.virginmoneygiving.giving.domain.FundraiserGroupType;
import com.virginmoneygiving.giving.domain.FundraisingCharitySplit;
import com.virginmoneygiving.giving.domain.Page;
import com.virginmoneygiving.giving.domain.Person;
import com.virginmoneygiving.giving.domain.UrlDetails;
import com.virginmoneygiving.giving.domain.User;
import com.virginmoneygiving.giving.domain.UserRole;
import com.virginmoneygiving.indexerservice.messages.IndexerUpdate;

public class TestIndexerJmsSender {

    private static final Long ID = 1L;
    private static final String FORENAME = "Ian";
    private static final String SURNAME = "Priest";
    private static final String EXPECTED_FUNDRAISER_NAME = "Ian Priest";
    private static final String EXPECTED_DATE = "01/01/2010";
    private static final String URL = "http://some/url";
    private static final String PAGE_TITLE = "Page Title";
    private static final String CHARITY_NAME = "Charity Name";
    private static final String TEAM_URL = "http://some/team/url";
    
    private IndexerJmsSender indexerJmsSender = null;
    private JmsTemplate mockJmsTemplate = null;
    
    @Before
    public void setUp() throws Exception {
        mockJmsTemplate = mock(JmsTemplate.class);
        
        indexerJmsSender = new IndexerJmsSender();
        indexerJmsSender.setJmsTemplate(mockJmsTemplate);
    }

    @Test
    public void testGetIndexerUpdate_nulls() {
        try {
            indexerJmsSender.getIndexerUpdate(null, new FundraiserActivity());
            fail("Expected IllegalArgumentException");
        }
        catch( IllegalArgumentException e) {
            // Expected
        }

        try {
            indexerJmsSender.getIndexerUpdate(new Fundraiser(), null);
            fail("Expected IllegalArgumentException");
        }
        catch( IllegalArgumentException e) {
            // Expected
        }
        
        // Will blow a NPE as sub-classes are null
        try {
            indexerJmsSender.getIndexerUpdate(new Fundraiser(), new FundraiserActivity());
            fail("Expected NullPointerException");
        }
        catch( NullPointerException e) {
            // Expected
        }
    }
    
    @Test
    public void testGetIndexerUpdate() throws Exception {
        
        Fundraiser fundraiser = new Fundraiser();
        fundraiser.setId(ID);
        fundraiser.setUserRole(new UserRole());
        fundraiser.getUserRole().setUser(new User());
        fundraiser.getUserRole().getUser().setPerson(new Person());
        fundraiser.getUserRole().getUser().getPerson().setForename(FORENAME);
        fundraiser.getUserRole().getUser().getPerson().setSurname(SURNAME);
        fundraiser.setUrlDetails(new UrlDetails());
        fundraiser.getUrlDetails().setUrl(URL);
        
        FundraiserActivity fundraiserActivity = new FundraiserActivity();
        fundraiserActivity.setId(ID);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(EXPECTED_DATE);
        fundraiserActivity.setEventCompletionDate(new Timestamp(date.getTime()));
        fundraiserActivity.setFundraiserImageUrl(URL);
        fundraiserActivity.setFundraiserPage(new Page());
        fundraiserActivity.getFundraiserPage().setTitle(PAGE_TITLE);
        fundraiserActivity.setFundraisingCharitySplit(new HashSet<FundraisingCharitySplit>());
        FundraisingCharitySplit fcs1 = new FundraisingCharitySplit();
        fcs1.setCharity(new Charity());
        fcs1.getCharity().setId(ID);
        fcs1.getCharity().setName(CHARITY_NAME);
        fcs1.getCharity().setVmgCharityURL(URL);
        fundraiserActivity.getFundraisingCharitySplit().add(fcs1);
        FundraisingCharitySplit fcs2 = new FundraisingCharitySplit();
        fcs2.setCharity(new Charity());
        fcs2.getCharity().setId(ID);
        fcs2.getCharity().setName(CHARITY_NAME);
        fcs2.getCharity().setVmgCharityURL(URL);
        fundraiserActivity.getFundraisingCharitySplit().add(fcs2);
        
        IndexerUpdate indexerUpdate = indexerJmsSender.getIndexerUpdate(fundraiser, fundraiserActivity);
        
        assertEquals(ID.toString(), indexerUpdate.getId());
        assertEquals(ID.toString(), indexerUpdate.getFundraiserId());
        assertEquals(EXPECTED_FUNDRAISER_NAME, indexerUpdate.getName());
        assertEquals(EXPECTED_DATE, sdf.format(indexerUpdate.getDate().toGregorianCalendar().getTime()));
        assertEquals(URL, indexerUpdate.getLogoUrl());
        assertEquals(PAGE_TITLE, indexerUpdate.getTitle());
        assertEquals(URL, indexerUpdate.getUrl());
        assertFalse(indexerUpdate.isTeamActivity());
        assertEquals(2, indexerUpdate.getCharities().size());
        
        // Now convert to a team activity and check url is correct. (Everything else will be the same).
        fundraiserActivity.setFundraisingAsIndicator(MasterDataCodeConstants.FUNDRAISER_AS_GROUP);
        fundraiserActivity.setFundraiserGroup(new FundraiserGroup());
        fundraiserActivity.getFundraiserGroup().setFundraiserGroupType(new FundraiserGroupType());
        fundraiserActivity.getFundraiserGroup().getFundraiserGroupType().setCode(MasterDataCodeConstants.GROUP_TYPE_TEAM);
        fundraiserActivity.getFundraiserGroup().setUrlDetails(new UrlDetails());
        fundraiserActivity.getFundraiserGroup().getUrlDetails().setUrl(TEAM_URL);
        
        indexerUpdate = indexerJmsSender.getIndexerUpdate(fundraiser, fundraiserActivity);
        
        assertEquals(TEAM_URL, indexerUpdate.getUrl());
        assertTrue(indexerUpdate.isTeamActivity());
    }

    //@Test
    public void testSendFundraiserActivitySavedMessage() {
        fail("Not yet implemented");
    }

    //@Test
    public void testSendFundraiserActivityDeletedMessage() {
        fail("Not yet implemented");
    }

    //@Test
    public void testSendCharitySavedMessage() {
        fail("Not yet implemented");
    }

    //@Test
    public void testSendCharityDeletedMessage() {
        fail("Not yet implemented");
    }

    //@Test
    public void testSendEventSavedMessage() {
        fail("Not yet implemented");
    }

    //@Test
    public void testSendEventDeletedMessage() {
        fail("Not yet implemented");
    }

}
