package com.virginmoneygiving.giving.domain;

import java.util.*;

/**
 * The Class FundraisingInfoDVO.
 * 
 * @author saurabhh
 * DVO to store fundraiser list and fundraiser count
 */
public class FundraisingInfoDVO {
	
/** The count of fundraisers. */	
private int fundraiserCount;

/** The list of current fundraisers based on event id and charity id. */
List<FundRaiserDVO> list= new ArrayList<FundRaiserDVO>();

/**
 * Gets the fundraiser count.
 * 
 * @return the fundraiserCount
 */
public int getFundraiserCount() {
	return fundraiserCount;
}

/**
 * Sets the fundraiser count.
 * 
 * @param fundraiserCount the fundraiserCount to set
 */
public void setFundraiserCount(int fundraiserCount) {
	this.fundraiserCount = fundraiserCount;
}

/**
 * Gets the list.
 * 
 * @return the list
 */
public List<FundRaiserDVO> getList() {
	return list;
}

/**
 * Sets the list.
 * 
 * @param list the list to set
 */
public void setList(List<FundRaiserDVO> list) {
	this.list = list;
}

}//end of class
