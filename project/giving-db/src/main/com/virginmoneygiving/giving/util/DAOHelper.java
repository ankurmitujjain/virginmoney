package com.virginmoneygiving.giving.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.datatransferobjects.FundraiserGroupDetailDTO;
import com.virginmoneygiving.giving.datatransferobjects.FundraiserGroupPageDetailDTO;
import com.virginmoneygiving.giving.datatransferobjects.TeamMemberDetailDTO;
import com.virginmoneygiving.giving.domain.AuditAttributes;
import com.virginmoneygiving.giving.domain.SearchFundraisingEventResult;

/**
 * Helper for DAO classes.
 * 
 * @author vishakha sawant
 */
public final class DAOHelper {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(DAOHelper.class);

    /**
     * Private constructor for DAOHelper utility classes.
     */
    private DAOHelper() {
    }

    /**
     * This method is used to append result set to list.
     * 
     * @param searchFundraisingEventList list of {@link Object}
     * @param searchFundraisingEventResultList List of {@link SearchFundraisingEventResult}
     */
    public static void appendResultsetToList(
            List<Object[]> searchFundraisingEventList,
            List<SearchFundraisingEventResult> searchFundraisingEventResultList) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("DAOHelper::appendResultsetToList() method - START");
        }
        for (Object[] objArray : searchFundraisingEventList) {
            SearchFundraisingEventResult searchFundraisingEventResult =
                    new SearchFundraisingEventResult();
            searchFundraisingEventResult.setEventId((Long) objArray[0]);
            searchFundraisingEventResult.setEventName((String) objArray[1]);
            searchFundraisingEventResult.setLocation((String) objArray[2]);
            searchFundraisingEventResult.setCharityId((Long) objArray[3]);
            searchFundraisingEventResult.setCharityName((String) objArray[4]);
            searchFundraisingEventResult
                    .setEventDatetime((Timestamp) objArray[5]);
            searchFundraisingEventResult.setDescription((String) (objArray[6]));

            if (objArray[7] != null) {
                int maxCharities = Integer.parseInt(objArray[7].toString());
                if (maxCharities == 1) {
                    // single charity can participate in the event
                    searchFundraisingEventResult
                            .setCharitySupportedInd(MasterDataCodeConstants.CHARITIES_SUPPORTED_BY_EVENT_SINGLE);
                }
                else {
                    // restricted
                    searchFundraisingEventResult
                            .setCharitySupportedInd(MasterDataCodeConstants.CHARITIES_SUPPORTED_BY_EVENT_RESTRICTED);
                }
            }
            else {
                // All charities can participate in the event
                searchFundraisingEventResult
                        .setCharitySupportedInd(MasterDataCodeConstants.CHARITIES_SUPPORTED_BY_EVENT_ALL);
            }

            searchFundraisingEventResult
                    .setFundraiserSplitOverrideInd((String) objArray[8]);
            searchFundraisingEventResult
                    .setSplitPercentage((objArray[9] == null ? null
                            : ((Integer) objArray[9]).toString()));

            searchFundraisingEventResultList.add(searchFundraisingEventResult);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("DAOHelper::appendResultsetToList() method - END");
        }

    }

    /**
     * This method is used convert the resultset in to List TeamMemberDetailDTO
     * object.
     * 
     * @param searchResult list of {@link Object}
     * @param fundraiserIds list of fundraiser Ids.
     * 
     * @return List of {@link TeamMemberDetailDTO}
     */
    public static List<TeamMemberDetailDTO> createTeamMemberList(
            List<Object[]> searchResult, List<Long> fundraiserIds) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("DAOHelper::createTeamMemberList() method - START");
        }
        List<TeamMemberDetailDTO> returnResultset =
                new ArrayList<TeamMemberDetailDTO>();
        boolean addRecordToList;
        for (Object[] objArray : searchResult) {
        	addRecordToList = true;
        	if (objArray[4] != null && (objArray[4].toString()).equals(MasterDataCodeConstants.EMAIL_ADDRESS_TYPE_PERSONAL)) {
        		for (Long fundraiserId : fundraiserIds){
        			if (objArray[0] != null && objArray[0].equals(fundraiserId)) {
        				addRecordToList = false;
        				break;
					}
        		}
        	}
        	if (addRecordToList){
	            TeamMemberDetailDTO teamMemberDetail = new TeamMemberDetailDTO();
	            teamMemberDetail.setFundraiserId((Long) objArray[0]);
	            teamMemberDetail.setForeName((String) objArray[1]);
	            teamMemberDetail.setSurName((String) objArray[2]);
	            teamMemberDetail.setEmailAddress((String) objArray[3]);
	            returnResultset.add(teamMemberDetail);
        	}
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("DAOHelper::createTeamMemberList() method - END");
        }
        return returnResultset;
    }

    /**
     * This method is used convert the resultset in to
     * List FundraiserGroupPageDetailDTO object.
     * 
     * @param searchResult list of {@link Object}
     * 
     * @return List of {@link FundraiserGroupPageDetailDTO}
     */
    public static List<FundraiserGroupPageDetailDTO> createPageDetailsList(
            List<Object[]> searchResult) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("DAOHelper::createPageDetailsList() method - START");
        }
        List<FundraiserGroupPageDetailDTO> returnResultset =
                new ArrayList<FundraiserGroupPageDetailDTO>();
        for (Object[] objArray : searchResult) {
            FundraiserGroupPageDetailDTO fundraiserGroupPageDetail =
                    new FundraiserGroupPageDetailDTO();
            fundraiserGroupPageDetail.setPageId((Long) objArray[0]);
            fundraiserGroupPageDetail.setPageTitle((String) objArray[1]);
            fundraiserGroupPageDetail.setFundraiserId((Long) objArray[2]);
            fundraiserGroupPageDetail.setFundraiserGroupId((Long) objArray[3]);
            fundraiserGroupPageDetail.setPageUrl((String) objArray[4]);
            returnResultset.add(fundraiserGroupPageDetail);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("DAOHelper::createPageDetailsList() method - END");
        }
        return returnResultset;
    }

    /**
     * This method is used convert the resultset in to
     * List FundraiserGroupDetailDTO object.
     * 
     * @param searchResult list of {@link Object}
     * 
     * @return List of {@link FundraiserGroupDetailDTO}
     */
    public static List<FundraiserGroupDetailDTO> createFundraiserGroupDetailsList(
            List<Object[]> searchResult) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("DAOHelper::createFundraiserGroupDetailsList() method - START");
        }
        List<FundraiserGroupDetailDTO> returnResultset =
                new ArrayList<FundraiserGroupDetailDTO>();
        for (Object[] objArray : searchResult) {
            FundraiserGroupDetailDTO fundraiserGroupDetail =
                    new FundraiserGroupDetailDTO();
            fundraiserGroupDetail.setFundraiserId((Long) objArray[0]);
            fundraiserGroupDetail.setFundraiserGroupId((Long) objArray[1]);
            fundraiserGroupDetail.setName((String) objArray[2]);
            fundraiserGroupDetail.setDescription((String) objArray[3]);
            fundraiserGroupDetail.setGroupMemberId((Long) objArray[4]);
            returnResultset.add(fundraiserGroupDetail);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("DAOHelper::createFundraiserGroupDetailsList() method - END");
        }
        return returnResultset;
    }

    /**
     * Sets audit related information from source to destination object.
     * 
     * @param source the source
     * @param destination the destination
     */
    public static void setAuditInformation(
            final AuditAttributes source,
            final AuditAttributes destination) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("DAOHelper::setAuditInformation() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("Audit info logged from source " + source + " to " + destination);
        }
        if (source != null && destination != null) {
            if (destination.getCreatedUser() == null
                    && destination.getCreatedIPAddress() == null
                    && destination.getCreatedProcess() == null) {

                destination.setCreatedUser(source.getCreatedUser());
                destination.setCreatedIPAddress(source.getCreatedIPAddress());
                destination.setCreatedProcess(source.getCreatedProcess());
            }
            else {
                destination.setUpdatedUser(source.getCreatedUser());
                destination.setUpdatedIPAddress(source.getCreatedIPAddress());
                destination.setUpdatedProcess(source.getCreatedProcess());
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("DAOHelper::setAuditInformation() method - END");
        }
    }
    
    /**
     * returns a comma delimited string representation of the input list.
     * Relies upon the list object's toString method to render the item contents.
     * 
     * @param inputList the input list of objects
     * 
     * @return string representation of list
     */
    public static String formatListToCommaSeparatedString(List inputList) {
        Iterator<Object> it = inputList.iterator();
        StringBuffer list = new StringBuffer();
        while ( it.hasNext() ) {                
            list.append(it.next());
            if ( it.hasNext() ) {
                list.append(",");
            }
        }
        return list.toString();
    }    
}
