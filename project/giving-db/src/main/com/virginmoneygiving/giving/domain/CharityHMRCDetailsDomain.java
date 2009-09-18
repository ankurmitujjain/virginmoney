package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

/**
 * The Class CharityHMRCDetailsDomain.
 */
public class CharityHMRCDetailsDomain implements Serializable {

    /** serialVersionUID. */
    
    private static final long serialVersionUID = 1L;
    
    /** charity id Ref number. */
    private Long id = null;
    
    /** HMRC Ref number. */
    private String hmrcRefNo = null;
	
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the hmrc ref no.
	 * 
	 * @return the hmrcRefNo
	 */
	public String getHmrcRefNo() {
		return hmrcRefNo;
	}
	
	/**
	 * Sets the hmrc ref no.
	 * 
	 * @param hmrcRefNo the hmrcRefNo to set
	 */
	public void setHmrcRefNo(String hmrcRefNo) {
		this.hmrcRefNo = hmrcRefNo;
	}
    
}
