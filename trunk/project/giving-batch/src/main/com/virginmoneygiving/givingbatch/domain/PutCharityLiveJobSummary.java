package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Object to hold the summary data for the PutCharityLiveBatchJob.
 * 
 * @author Puneet Swarup
 */
public class PutCharityLiveJobSummary implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = -375678236L;
	
	/** The ok. */
	private Boolean ok;

	/** The batch number. */
	private String batchNumber;

	/** The process date. */
	private Date processDate;

	/** The number of records reviewed. */
	private Integer numberOfRecordsReviewed;

	/** The number of skipped records. */
	private Integer numberOfSkippedRecords;

	/** The number of charities put live. */
	private Integer numberOfCharitiesPutLive;

	/** The failed records. */
	private List<FailedToGoLiveCharity> failedRecords;

	/**
	 * Default Constructor.
	 */
	public PutCharityLiveJobSummary() {
		super();
		ok = false;
		batchNumber = "";
		processDate = Calendar.getInstance().getTime();
		numberOfCharitiesPutLive = 0;
		numberOfRecordsReviewed = 0;
		numberOfSkippedRecords = 0;
		failedRecords = new ArrayList<FailedToGoLiveCharity>();
	}

	/**
	 * Checks if is ok.
	 * 
	 * @return the ok
	 */
	public Boolean isOk() {
		return ok;
	}

	/**
	 * Sets the ok.
	 * 
	 * @param ok the ok to set
	 */
	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	/**
	 * Gets the batch number.
	 * 
	 * @return the batchNumber
	 */
	public String getBatchNumber() {
		return batchNumber;
	}

	/**
	 * Sets the batch number.
	 * 
	 * @param batchNumber the batchNumber to set
	 */
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	/**
	 * Gets the process date.
	 * 
	 * @return the processDate
	 */
	public Date getProcessDate() {
		return processDate;
	}

	/**
	 * Sets the process date.
	 * 
	 * @param processDate the processDate to set
	 */
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	/**
	 * Gets the number of records reviewed.
	 * 
	 * @return the numberOfRecordsReviewed
	 */
	public Integer getNumberOfRecordsReviewed() {
		return numberOfRecordsReviewed;
	}

	/**
	 * Sets the number of records reviewed.
	 * 
	 * @param numberOfRecordsReviewed the numberOfRecordsReviewed to set
	 */
	public void setNumberOfRecordsReviewed(Integer numberOfRecordsReviewed) {
		this.numberOfRecordsReviewed = numberOfRecordsReviewed;
	}

	/**
	 * Gets the number of skipped records.
	 * 
	 * @return the numberOfSkippedRecords
	 */
	public Integer getNumberOfSkippedRecords() {
		return numberOfSkippedRecords;
	}

	/**
	 * Sets the number of skipped records.
	 * 
	 * @param numberOfSkippedRecords the numberOfSkippedRecords to set
	 */
	public void setNumberOfSkippedRecords(Integer numberOfSkippedRecords) {
		this.numberOfSkippedRecords = numberOfSkippedRecords;
	}

	/**
	 * Gets the number of charities put live.
	 * 
	 * @return the numberOfCharitiesPutLive
	 */
	public Integer getNumberOfCharitiesPutLive() {
		return numberOfCharitiesPutLive;
	}

	/**
	 * Sets the number of charities put live.
	 * 
	 * @param numberOfCharitiesPutLive the numberOfCharitiesPutLive to set
	 */
	public void setNumberOfCharitiesPutLive(Integer numberOfCharitiesPutLive) {
		this.numberOfCharitiesPutLive = numberOfCharitiesPutLive;
	}

	/**
	 * Gets the failed records.
	 * 
	 * @return the failedRecords
	 */
	public List<FailedToGoLiveCharity> getFailedRecords() {
		return failedRecords;
	}

	/**
	 * Sets the failed records.
	 * 
	 * @param failedRecords the failedRecords to set
	 */
	public void setFailedRecords(List<FailedToGoLiveCharity> failedRecords) {
		this.failedRecords = failedRecords;
	}

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 * 
	 * @return a <code>String</code> representation of this object.
	 */
	public String toString() {

		final String tab = "    ";

		return new StringBuilder("PutCharityLiveJobSummary ( ").append(
				"batchNumber = ").append(this.batchNumber).append(tab).append(
				"processDate = ").append(this.processDate).append(tab).append(
				"numberOfRecordsReviewed = ").append(
				this.numberOfRecordsReviewed).append(tab).append(
				"numberOfSkippedRecords = ")
				.append(this.numberOfSkippedRecords).append(tab).append(
						"numberOfCharitiesPutLive = ").append(
						this.numberOfCharitiesPutLive).append(tab).append(
						"failedRecords = ").append(this.failedRecords).append(
						tab).append(super.toString()).append(" )").toString();
	}
}
