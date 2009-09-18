package com.virginmoneygiving.integrationtests.webdriver.event;

/**
 * Class that will be used to hold the mock data required for event webdriver
 * test case execution.
 * 
 * @author vishals
 */
public class EventRegistrationDetails {
	/**
	 * This class will hold the event details.
	 */
    public class EventBasicDetails {
        public String eventName;
        public String startDay;
        public String startMonth;
        public String startYear;
        public String locationCode;
        public String eventDescription;
        public String contactName;
        
		public void setEventName(String eventName) {
			this.eventName = eventName;
		}
		public void setStartDay(String startDay) {
			this.startDay = startDay;
		}
		public void setStartMonth(String startMonth) {
			this.startMonth = startMonth;
		}
		public void setStartYear(String startYear) {
			this.startYear = startYear;
		}
		public void setLocationCode(String locationCode) {
			this.locationCode = locationCode;
		}
		public void setEventDescription(String eventDescription) {
			this.eventDescription = eventDescription;
		}
		public void setContactName(String contactName) {
			this.contactName = contactName;
		}
        
        
    }    
    /** EventBasicDetails instance.*/
    public EventBasicDetails eventBasicDetails = new EventBasicDetails();
	
    
    public EventBasicDetails getEventBasicDetails() {
		return eventBasicDetails;
	}
	public void setEventBasicDetails(EventBasicDetails eventBasicDetails) {
		this.eventBasicDetails = eventBasicDetails;
	}
    
    
}
