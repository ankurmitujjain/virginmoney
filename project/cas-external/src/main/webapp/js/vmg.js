document.observe("dom:loaded", function() {
	
	/* Added by gaurav - Ops Locking/Unlocking */
	if($('accntLockStatustrue') != null) {
		
		if($('accntLockStatustrue').checked == true) {
			if($('accntLockedBy') != null) {
				$('accntLockedBy').show();	
			}
			
			if($('accntLockedDate') != null) {
				$('accntLockedDate').show();
			}			
			$('accntLockedReason').show();
				
			} else {
				if($('accntLockedBy') != null) {
					$('accntLockedBy').hide();	
				}
				
				if($('accntLockedDate') != null) {
					$('accntLockedDate').hide();
				}				
				$('accntLockedReason').hide();
		}	 
	}
	
	$$('[name="userAccountStatus.accountLocked"]').each(function(elmtOpsLockUnlock) {
		
		elmtOpsLockUnlock.observe('click',function() {
			
			if($('accntLockStatustrue').checked == true) {
				if($('accntLockedBy') != null) {
					$('accntLockedBy').show();	
				}
				
				if($('accntLockedDate') != null) {
					$('accntLockedDate').show();
				}
				$('accntLockedReason').show();
				
			}else {
				if($('accntLockedBy') != null) {
					$('accntLockedBy').hide();	
				}
				
				if($('accntLockedDate') != null) {
					$('accntLockedDate').hide();
				}
				$('accntLockedReason').hide();
			}
			});
		});
	/* End - Ops Locking/Unlocking */	
	
	//Site.js code need to move there.
	if($('branding-logo') != null){
		$('branding-logo').setStyle({'cursor':'pointer'}).observe('click', function(){
				window.location = "#";
		});
	}
	
	/** Display image Gallery Functionality*/
	  if($('chkbox1')!=null){
		  if($('chkbox1').checked == true) {
				$('galleryId').show();
			}
		  
		  Event.observe('chkbox1', 'click', function(event) {
			if($('chkbox1').checked == true) {
				$('galleryId').show();
			} else if($('chkbox1').checked == false) {
				$('galleryId').hide();
			}
		});
		}
		 /**End of Image Gallery Functionality*/

	/** Code for tooltip start*/
	$$('div.open_error_popup').each(function(elmt){
		elmt.className = 'error_popup_closed';
	});
	/** Code for tooltip end*/
	
	/** Display image Gallery Functionality*/
	  if($('chkbox1')!=null){
		  if($('chkbox1').checked == true) {
				$('galleryId').show();
			}
		  
		  Event.observe('chkbox1', 'click', function(event) {
			if($('chkbox1').checked == true) {
				$('galleryId').show();
			} else if($('chkbox1').checked == false) {
				$('galleryId').hide();
			}
		});
		}
		 /**End of Image Gallery Functionality*/
	  

	if($('adminAddressSameAsRegistrationAddress')!= null){
		hideAddressLookupComponentLabels();

		if($('adminAddressSameAsRegistrationAddress').checked == true)
		{
			 if($('addressLookupTextComponent') != null ){
				$('addressLookupTextComponent').hide();
			 }


			 if( $('displayAddress')!=null){
				$('displayAddress').hide();
			 }

			 if( $('displayAddressData')!=null){
				$('displayAddressData').hide();
			 }
			 
			
			 if( $('registeredAddressDetails')!=null){
					$('registeredAddressDetails').show();
					displayAddressLookupComponentLabels();
			   }

			 /* */

			   
		}else{

			 if($('addressLookupTextComponent') != null ){
					 $('addressLookupTextComponent').show();
			 } else if( $('displayAddressData')!=null){
				$('displayAddressData').show();
				$('displayAddress').hide();
				displayAddressLookupComponentLabels();
			 } else  if( $('displayAddress')!=null){
				$('displayAddress').show();
				displayAddressLookupComponentLabels();
			 }

			  if( $('registeredAddressDetails')!=null){
				$('registeredAddressDetails').hide();
			  }
		}
	}


	
	if($('adminAddressSameAsRegistrationAddress')!= null){
		$('adminAddressSameAsRegistrationAddress').observe('click', function() {
			hideAddressLookupComponentLabels();

			if($('regPostCode') != null ){
				$('regPostCode').value="";
			}

			if($(this).checked == true){

				if($('addressLookupTextComponent') != null ){
					$('addressLookupTextComponent').hide();
				}

				if( $('displayAddressData')!=null){
					$('displayAddressData').hide();
				}

				if( $('displayAddress')!=null){
					$('displayAddress').hide();
				}

				if( $('registeredAddressDetails')!=null){
					$('registeredAddressDetails').show();
					displayAddressLookupComponentLabels();
				}

			}else {
				
				if($('addressLookupTextComponent') != null ){
					 $('addressLookupTextComponent').show();					 
				} else if( $('displayAddressData')!=null){
					$('displayAddressData').show();
					$('displayAddress').hide();
					displayAddressLookupComponentLabels();
				 } else  if( $('displayAddress')!=null){
					$('displayAddress').show();
					displayAddressLookupComponentLabels();
				 }


				if( $('registeredAddressDetails')!=null){
					$('registeredAddressDetails').hide();			 			 
				}
			}
			
		});
	}

	if($('charityDescription')!=null){
		var count = "2000";
		var tex = $('charityDescription').value;
        var len = tex.length;
        
        if (len >= count) {
        	$('remainidCharacterCount').innerHTML = "No";
        } else if (len <= count) {
        	$('remainidCharacterCount').innerHTML = count-len;
        }
	}

	if($('uploadButton')!=null) {
		$('uploadButton').hide();
	}
	
	if ($('vm-categoryselect')) {
		$('vm-categoryselect').addClassName('js');
	}
	$$('ul.groupUl').each( function(elmt) {
		elmt.hide();
	});
	$$('legend').each( function(elemt) {
		elemt.observe('click', function() {
			var ele = elemt.siblings('ul')[0];
			Effect.toggle(ele, 'blind', {
				duration :0.5
			});
			elemt.toggleClassName("open");
		});
	});

	/** Charity portal */

	$$('ul.groupSubmenu').each( function(elmt) {
			elmt.hide();
		});

    if($('linkIndicator') != null){

	     var menuIndicator = $F('linkIndicator');
		
			if( menuIndicator == 'event') {
				$('accountMaintenance').hide();
				$('users').hide();
				$('reporting').hide();
				$('events').show();
			}
			else if(menuIndicator == 'accountMaintenance') {
				$('events').hide();
				$('users').hide();
				$('reporting').hide();
				$('accountMaintenance').show();

			}// UCCHA090 -start
			else if(menuIndicator == 'reporting') {
				$('events').hide();
				$('users').hide();
				$('accountMaintenance').hide();
				$('reporting').show();
			}// UCCHA090 -end
			else if(menuIndicator == 'users') {
				$('events').hide();
				$('accountMaintenance').hide();
				$('users').show();
				$('reporting').hide();

					}
			else if(menuIndicator == 'other') {
				$('events').hide();
				$('accountMaintenance').hide();
				$('users').hide();
				$('reporting').hide();
			}
			else {
				$('events').hide();
				$('accountMaintenance').hide();
				$('users').hide();
				$('reporting').hide();
			}
		
	}
	
	$$('legend.legendportal').each( function(elemt) {
		elemt.observe('click', function() {
			
		
			$$('ul.groupSubmenu').each( function(elmt) {
		     elmt.hide();
	});
			var ele = elemt.siblings('ul')[0];
			Effect.toggle(ele.id, 'blind', {
				duration :0.5
			});
			elemt.toggleClassName("open");
		});
	});
	
	// Start For page registrationFees.jsp page 
	if($('target-id2') != null && $('target-id1') != null && $('target-id') != null){
		$('target-id2').hide();
		$('target-id1').hide();
		$('target-id').hide();
		
		$$('[name="paytype"]').each(function(paytypeElmt) {
			paytypeElmt.observe('click',function(){
				if($('cheque').checked == true) {
					$('target-id').show();
					$('target-id2').hide();
					$('target-id1').hide();
				}else if($('direct').checked == true) {
					$('target-id1').show();
					$('target-id').hide();
					$('target-id2').hide();
				}else if($('credit').checked == true) {
					$('target-id2').show();
					$('target-id1').hide();
					$('target-id').hide();
				}
			});
		});
	}
	// End For page registrationFees.jsp page
	
	/*this script is written for EVT0020A by Dayanand on 24/12/08 [Start]*/
	/** Start EVT0020A */
	$$('[name="createevent"]').each(function(elmt) {
		elmt.observe('click',function(){
				if($('existingevent').checked == true) {
				$('content-secondary').show();
					//result page will display
					if($('eventsearch-result')!=null){
						$('eventsearch-result').show();	
					}
				if($('createownevent')!=null){
				$('createownevent').hide();}
				
				if($('navigationButton_comeback')!=null){
				$('navigationButton_comeback').hide(); }
				if($('navigationButton_change')!=null){
				$('navigationButton_change').hide();}
			}else {
				$('content-secondary').hide();
				if($('createownevent')!=null){
				$('createownevent').show();}
				if($('eventsearch-result')!=null){
				$('eventsearch-result').hide();
				}

				//TODO Hide 
				if($('navigationButton_comeback')!=null){
				$('navigationButton_comeback').show(); }
				

			}
		});
	});
	
	if($('eventSearchResultNext') != null){
		$('eventSearchResultNext').hide();
	}
	
	if($('findeventlink') != null){
	$('findeventlink').observe('click',function(){
		$('eventsearch-result').show();
		$('minhightid').hide();
		$('eventSearchResultNext').show();
	});
	}
	/** End EVT0020A */
	
	/** Start EVT0040A */
	/** Below code is use for onclick and onload event logic process
	 *  for fee collection page in UCEVT0020. */
	$$('[name="organisedEventBean.organisedEventFeeDetails.vmgManagingFeeIndicator"]').each(function(elmt) {
		elmt.observe('click',function(){
			if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_vmgManagingFeeIndicatorN').checked == true) {
				onChangeVmgManageFeeIndicator();
				$('charityforyouonly').show();
				$('managepaymentyes').hide();
			}else {
				onChangeVmgManageFeeIndicator();
				$('charityforyouonly').hide();
				$('whatothercharity').hide();
				$('chooseyourcharity').hide();
				$('howtosplit').hide();
				$('managepaymentyes').show();
			}
		});
	});
	if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_vmgManagingFeeIndicatorN') != null){
		if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_vmgManagingFeeIndicatorN').checked == true) {
			$('charityforyouonly').show();
			$('managepaymentyes').hide();
		}else if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_vmgManagingFeeIndicatorY').checked == true) {				
			onLoadVmgManageFeeIndForEventDetails();
			$('charityforyouonly').hide();
			$('whatothercharity').hide();
			$('chooseyourcharity').hide();
			$('howtosplit').hide();
			$('managepaymentyes').show();
		}
	}
	
	$$('[name="organisedEventBean.organisedEventFeeDetails.eventSolelyAidOfCharityInd"]').each(function(elmt) {
		elmt.observe('click',function(){
			if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_eventSolelyAidOfCharityIndN').checked == true) {
				$('whatothercharity').show();
			}else {
				onChangeOwnCharityIndicator();
				$('whatothercharity').hide();
				$('chooseyourcharity').hide();
				$('howtosplit').hide();
			}
		});
	});
	if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_eventSolelyAidOfCharityIndN') != null){
		if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_eventSolelyAidOfCharityIndN').checked == true) {
			$('whatothercharity').show();
		}else {
			$('whatothercharity').hide();
			$('chooseyourcharity').hide();
			$('howtosplit').hide();
		}
	}
	
	$$('[name="organisedEventBean.organisedEventFeeDetails.charitiesSupportedInd"]').each(function(elmt) {
		elmt.observe('click',function(){
			if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_charitiesSupportedIndlimited').checked == true) {
				$('chooseyourcharity').show();
			}else {
				onChangeCharitySupportedIndicator();
				$('chooseyourcharity').hide();
				$('howtosplit').hide();
			}
		});
	});
	if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_charitiesSupportedIndlimited') != null){
		if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_charitiesSupportedIndlimited').checked == true) {
			$('chooseyourcharity').show();
			onloadEventdetails();
		}else {
			$('chooseyourcharity').hide();
			$('howtosplit').hide();
		}
	}
	
	$$('[name="organisedEventBean.organisedEventFeeDetails.donationSplitReqInd"]').each(function(elmt) {
		elmt.observe('click',function(){
			if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndN').checked == true) {
				onChangeDonationSplitReqIndicator();
				$('howtosplit').show();				
			}else {
				onChangeDonationSplitReqIndicator();
				$('howtosplit').hide();
			}
		});
	});
	if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndN') != null){
		if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndN').checked == true) {
			$('howtosplit').show();				
		}else {
			$('howtosplit').hide();
		}
	}
	
	/**End EVT0040A */
	/**START EVT0040B */
	
	if($('howmanyplaces') != null){
		if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineRegistationLimtIndY').checked == true) {
					$('howmanyplaces').removeAttribute("disabled","disabled");
		} else {
					$('howmanyplaces').clear();
					$('howmanyplaces').writeAttribute("disabled","disabled");
		}
		//for cancelled and expired event amend flow 
		if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineRegistationLimtIndY').checked == true) {
			if ($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineRegistationLimtIndY').disabled)
			{
				$('howmanyplaces').writeAttribute("disabled","disabled");
			}
		}
	}
	$$('[name="organisedEventBean.organisedEventFeeDetails.onlineRegistationLimtInd"]').each(function(elmt){
		//elmt.checked=false;
		elmt.observe('click',function(){
				if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineRegistationLimtIndY').checked == true) {
					$('howmanyplaces').removeAttribute("disabled","disabled");
				}else {
					$('howmanyplaces').clear();
					$('howmanyplaces').writeAttribute("disabled","disabled");
				}
		});
	});
	
	if($$('[name="organisedEventBean.organisedEventFeeDetails.onlineEntryDeadlineInd"]')!= null){
		/*$$('[name="organisedEventBean.organisedEventFeeDetails.onlineEntryDeadlineInd"]').each(function(elmt){
		elmt.checked=false;
		});*/

		$$('[name="organisedEventBean.organisedEventFeeDetails.onlineEntryDeadlineInd"]').each(function(elmt){
			elmt.observe('click',function(){
				if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineEntryDeadlineIndY').checked == true) {
					$('date').removeAttribute("disabled","disabled");
					$('month').removeAttribute("disabled","disabled");
					$('year').removeAttribute("disabled","disabled");
				}else {
					$('date').clear();
					$('month').clear();
					$('year').clear();
					$('date').writeAttribute("disabled","disabled");
					$('month').writeAttribute("disabled","disabled");
					$('year').writeAttribute("disabled","disabled");
				}		
			});
		});

		if($('date') != null){
			if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineEntryDeadlineIndY').checked == true) {
				$('date').removeAttribute("disabled","disabled");
				$('month').removeAttribute("disabled","disabled");
				$('year').removeAttribute("disabled","disabled");
			}else {
				$('date').clear();
				$('month').clear();
				$('year').clear();
				$('date').writeAttribute("disabled","disabled");
				$('month').writeAttribute("disabled","disabled");
				$('year').writeAttribute("disabled","disabled");
			}
			//for cancelled and expired event amend flow
			if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineEntryDeadlineIndY').checked == true) {
				if ($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineEntryDeadlineIndY').disabled){
					$('date').writeAttribute("disabled","disabled");
					$('month').writeAttribute("disabled","disabled");
					$('year').writeAttribute("disabled","disabled");
				}
			}
		}

	}
	/**End EVT0040B */
	
	if($('other') != null){

		$('other').checked=false;/*uncheck onload*/
		$('otherTextField').writeAttribute("disabled", "disabled" );/*disabled onload*/
		$('other').observe('click', function(event){
			if ($('other').checked == true){
				//alert("checked");
				$('otherTextField').removeAttribute("disabled", "disabled" );			
			}else{
				//alert("unchecked");
				$('otherTextField').clear();/*clear at time of disabled*/
				$('otherTextField').writeAttribute("disabled", "disabled" );
			}	
		
			});
	}
	
	//This is temporary code for demo of HTML flow.
	$$('[name="eventlist"]').each(function(elmt) {
		elmt.observe('click',function(){
			if($('eventName3').checked == true || $('eventName4').checked == true) {
				$('eventStatusCancelled').show();
				$('eventStatusDraftorPanding').hide();
			}else{
				$('eventStatusCancelled').hide();
				$('eventStatusDraftorPanding').show();
			}
		});
	});
	
	//[END] This is temporary code for demo of HTML flow.
	//if($('ondate') != null)
	if($('createEventBasicSave_organisedEventDetailsVo_eventSetUpDateIndicatorN') != null){
	//$('createEventBasicSave_organisedEventDetailsVo_eventSetUpDateIndicatorN').checked=false;
		
		
		
		
		$('createEventBasicSave_eventPageSetupDateVo_eventStartDay').writeAttribute("disabled","disabled");
		$('createEventBasicSave_eventPageSetupDateVo_eventStartMonth').writeAttribute("disabled","disabled");
		$('createEventBasicSave_eventPageSetupDateVo_eventStartYear').writeAttribute("disabled","disabled");
		$$('[name="organisedEventDetailsVo.eventSetUpDateIndicator"]').each(
			function(elmt) {
				elmt.observe('click',function(){
					if($('createEventBasicSave_organisedEventDetailsVo_eventSetUpDateIndicatorN').checked == true) {
						
						$('createEventBasicSave_eventPageSetupDateVo_eventStartDay').removeAttribute("disabled","disabled");
						$('createEventBasicSave_eventPageSetupDateVo_eventStartMonth').removeAttribute("disabled","disabled");
						$('createEventBasicSave_eventPageSetupDateVo_eventStartYear').removeAttribute("disabled","disabled");

										
						
					}else {
						$('createEventBasicSave_eventPageSetupDateVo_eventStartDay').clear();
						$('createEventBasicSave_eventPageSetupDateVo_eventStartMonth').clear();
						$('createEventBasicSave_eventPageSetupDateVo_eventStartYear').clear();
						$('createEventBasicSave_eventPageSetupDateVo_eventStartDay').writeAttribute("disabled","disabled");
						$('createEventBasicSave_eventPageSetupDateVo_eventStartMonth').writeAttribute("disabled","disabled");
						$('createEventBasicSave_eventPageSetupDateVo_eventStartYear').writeAttribute("disabled","disabled");
					}
				});
			});
		
		if($('createEventBasicSave_organisedEventDetailsVo_eventSetUpDateIndicatorN').checked==true){

				if($('createEventBasicSave_organisedEventDetailsVo_eventSetUpDateIndicatorN').disabled){
					$('createEventBasicSave_eventPageSetupDateVo_eventStartDay').writeAttribute("disabled","disabled");
					$('createEventBasicSave_eventPageSetupDateVo_eventStartMonth').writeAttribute("disabled","disabled");
					$('createEventBasicSave_eventPageSetupDateVo_eventStartYear').writeAttribute("disabled","disabled");
					
				}
				else{
					$('createEventBasicSave_eventPageSetupDateVo_eventStartDay').removeAttribute("disabled","disabled");
					$('createEventBasicSave_eventPageSetupDateVo_eventStartMonth').removeAttribute("disabled","disabled");
					$('createEventBasicSave_eventPageSetupDateVo_eventStartYear').removeAttribute("disabled","disabled");
					
				}

		

		
			}else{
			
				$('createEventBasicSave_eventPageSetupDateVo_eventStartDay').clear();
				$('createEventBasicSave_eventPageSetupDateVo_eventStartMonth').clear();
				$('createEventBasicSave_eventPageSetupDateVo_eventStartYear').clear();
			}
	
	}
	//Event basic page to select join or basic tab
	if($('createEventBasicSave_organisedEventDetailsVo_selectEventBasicPageIndicatorY') != null){

			$$('[name="organisedEventDetailsVo.selectEventBasicPageIndicator"]').each(
				function(elmt) {
					elmt.observe('click',function(){
						if($('createEventBasicSave_organisedEventDetailsVo_selectEventBasicPageIndicatorY').checked == true) {
							//alert("Create event called");
							if($('content-secondary')!=null){
							$('content-secondary').hide();
							}
							if($('createownevent')!=null){
							$('createownevent').show();}
							if($('eventsearch-result')!=null){
							$('eventsearch-result').hide();
							}

							//TODO Hide 
							if($('navigationButton_comeback')!=null){
							$('navigationButton_comeback').show(); }
							
						}else {
							//alert("Join event called");
							if($('content-secondary')!=null){
							$('content-secondary').show();
							}
							//result page will display
							if($('eventsearch-result')!=null){
								$('eventsearch-result').show();	
							}
						if($('createownevent')!=null){
						$('createownevent').hide();}
						
						if($('navigationButton_comeback')!=null){
						$('navigationButton_comeback').hide(); }
						if($('navigationButton_change')!=null){
						$('navigationButton_change').hide();}
						}
					});
				});
			
			/*if($('createEventBasicSave_organisedEventDetailsVo_selectEventBasicPageIndicatorY').checked==true){
					if($('content-secondary')!=null){
						$('content-secondary').hide();
						}
						if($('createownevent')!=null){
						$('createownevent').show();}
						if($('eventsearch-result')!=null){
						$('eventsearch-result').hide();
						}
	
						//TODO Hide 
						if($('navigationButton_comeback')!=null){
						$('navigationButton_comeback').show(); }
				}else if($('createEventBasicSave_organisedEventDetailsVo_selectEventBasicPageIndicatorN').checked==true){
					if($('content-secondary')!=null){
						$('content-secondary').show();
						}
						//result page will display
						if($('eventsearch-result')!=null){
							$('eventsearch-result').show();	
						}
					if($('createownevent')!=null){
					$('createownevent').hide();}
					
					if($('navigationButton_comeback')!=null){
					$('navigationButton_comeback').hide(); }
					if($('navigationButton_change')!=null){
					$('navigationButton_change').hide();}
					
				}else{
					//DO nothing
				}*/
		
		}
	//Event basic page to select join or basic tab
	
	/*this script is written for EVT0020A by Dayanand on 24/12/08 [End]*/
	
  });

/** script for tooltip starts here */

//global variable for error popups

var IE = document.all ? true : false;
var currentOpenErrorPop = "";
var errorPopDelay = 100;
var popupOver = false;
var popupOpen = false;
var popupEvent;
var popupEventY;
var popupEventX
var popupName;
var popupTimeout;

function open_error_popup(e, name) {
popupOver = true;
popupEventY = e.clientY - 10;
popupEventX = e.clientX - 250;
popupName = name;
popupTimeout = setTimeout("fire_error_popup_from_mem()", errorPopDelay);
}

function fire_error_popup_from_mem() {
fire_error_popup(popupEvent, popupName);
}

function fire_error_popup(e, name) {
if (popupOver) {
    popupOpen = true;
    var xPos = popupEventX;
    var yPos = popupEventY;
                    closeOpenErrorPopup();
    if ($(name)) {
        $(name).className = 'error_popup_open';
        if (IE) {
            var leftScroll = document.documentElement.scrollLeft;
            var topScroll = document.documentElement.scrollTop;
            $(name).style.left = xPos;
            $(name).style.top = yPos + topScroll;
        }
        else {
            var leftScroll = pageXOffset;
            var topScroll = pageYOffset;
            $(name).style.left = xPos + "px";
            $(name).style.top = (yPos + topScroll) + "px";
        }
        currentOpenErrorPop = name;
    }
}
return false;
}

//closes error popups
function close_error_popup(category) {
if (popupOpen) {
    if ($(category)) {
        $(category).className = 'error_popup_closed';
    }
    currentOpenErrorPop = "";
    popupOpen = false;
    popupOver = false;
    popupEvent = null;
    popupName = null;
}
else {
    clearTimeout(popupTimeout);
    popupOver = false;
    popupEvent = null;
    popupName = null;
}
return false;
}

function closeOpenErrorPopup() {
if (currentOpenErrorPop != "") {
    if ($(currentOpenErrorPop)) {
        $(currentOpenErrorPop).className = 'error_popup_closed';
    }
}
}
/** Tooltip script Ends here */

/** ajax action calling method for UCEVT0020 - START */

/* This is ajax call method when user click on add button in EVT040A page. */
function addSelectedCharity() {
	 var selectedCharityName = $F('addCharityName');
	 var selectedCharityId = $F('addCharityId');
	 var allowChars=/[a-zA-Z0-9]{1}[a-zA-Z0-9 ]{0,40}/;
	 var error;

	$('showError').hide(); 
	$('multipleCharityWorkingIndicator').show();
   var url = "ajaxAddCharityAction.action";
   var params = 'selectedCharityName=' + selectedCharityName;
   params = params + '&selectedCharityId=' + selectedCharityId;
   new Ajax.Request(url, {
			method :'POST',	
			parameters :params,
			asynchronous :true,
			onSuccess :displayUrlResponse
		});		
	   	   
	 function displayUrlResponse(transport){
		    $('multiCharityErrorImage').hide();
			$('multiCharityErrorMessage').hide();
			if ($('fundSplitErrorMessage') != null) {
				$('fundSplitErrorMessage').hide();
			}						
			$('showError').hide();
			$('showExceedFive').hide();
			$('charityAlreadyExist').hide();
			$('multipleCharityWorkingIndicator').hide();
			if ($('totalNotHundredErrorMessage') != null) {
				$('totalNotHundredErrorMessage').hide();
			}			
			if ($('charityAdminErrorMessage') != null) {
				$('charityAdminErrorMessage').hide();
			}
			
		    var NODE_ERROR_CODE = "errorCode";
		    var NODE_ERROR_DESCRIPTION = "errorDescription";
		    var VALIDATION_CODE_BLANK_TEXT_PROVIDED =  "BLANK_TEXT_PROVIDED";
		    var VALIDATION_CODE_INVALID_CHARACTERS =  "INVALID_CHARACTERS_FOR_CHARITY_NAME";
		    
		    var xml_doc = transport.responseXML.documentElement;
		    var errorMessage = xml_doc.getAttribute("errorMessage");
		    var charityName = xml_doc.getAttribute("selcharityName");
		    var errorCode = xml_doc.getAttribute(NODE_ERROR_CODE);
		    var errorDescription = xml_doc.getAttribute(NODE_ERROR_DESCRIPTION);

		    if (errorCode == VALIDATION_CODE_BLANK_TEXT_PROVIDED) {
				 error=errorDescription;
				 document.getElementById('showError').innerHTML=error;
				 $('showError').show();
				 $('multiCharityErrorImage').show();
		    }
		    else if (errorCode == VALIDATION_CODE_INVALID_CHARACTERS) {
				 error=errorDescription;
				 document.getElementById('showError').innerHTML=error;
				 $('showError').show();
				 $('multiCharityErrorImage').show();
		    }
		    else {	
		    
				theList = $('createCharity');
				newList = document.createElement('span');
				theList.parentNode.replaceChild(newList,theList);		    	
				newList.id = "createCharity";	
		    		
				charityListSize = xml_doc.childNodes[0].childNodes.length;
				createCharityList(xml_doc, charityListSize);
				
				if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndN').checked == true) {
					theSplitList = $('createFundSplit');
					newSplitList = document.createElement('span');
					theSplitList.parentNode.replaceChild(newSplitList,theSplitList);		    	
					newSplitList.id = "createFundSplit";
					createDonationSplitTotal(xml_doc,charityListSize);
				}

			
			if (errorMessage == "No" ) {					
			    $('addCharityName').clear();
				$('addCharityId').clear();
			    $('showExceedFive').hide();
			    $('charityAlreadyExist').hide();
			} else if (errorMessage == "Yes") {			
				var errorDisplay = "You can only select a maximum of 5 charities.";
				document.getElementById('showExceedFive').innerHTML = errorDisplay;
				$('addCharityName').clear();
				$('addCharityId').clear();
				$('showExceedFive').show();
				$('charityAlreadyExist').hide();
		    } else if (errorMessage == "charityAlreadyExist") {						
				var errorDisplay = "This charity has already been selected.";
				document.getElementById('charityAlreadyExist').innerHTML = errorDisplay;
				$('addCharityName').clear();
				$('addCharityId').clear();
				$('showExceedFive').hide();
				$('charityAlreadyExist').show();
			}
		}
	}
	
}

/* This function is iterate xml_doc response to display on screen
 * this is use for EVT0040A page in UCEVT0020. */
function createCharityList(xml_doc, charityListSize) {

	for (i = 0; i < charityListSize; i++) {
				
				var addCharityName = xml_doc.childNodes[0].childNodes[i]
					.getAttribute("charityName");
				var addCharityId = xml_doc.childNodes[0].childNodes[i]
				    .getAttribute("charityId");	
				var eventStatus = xml_doc.childNodes[0].childNodes[i]
				    .getAttribute("eventStatus");

				if (eventStatus == "cancelOrExpire") {
					//remove addCharityName and addCharityButton from screen.
					$('addCharityName').hide();
					$('addCharityButton').hide();
				}				
				
				if (navigator.appName == "Microsoft Internet Explorer") {
					newCharity = document.createElement('<span class="charityContainer" id="'+i+'"/>');
					newCharity.setAttribute('class', 'charityContainer');
					newLabel = document.createElement('<LABEL class="createeventLabelwidth"/>');
					newLabel.innerHTML = "&nbsp;";
					newCharity.appendChild(newLabel);
					newCharityName = document.createElement('<SPAN class="spanreadonlytxt"/>');
					newCharityName.appendChild(document.createTextNode(addCharityName));						
					newCharity.appendChild(newCharityName);
				}else if (navigator.appName == "Netscape")	{
					newCharity = document.createElement('span');
					newCharity.setAttribute('class', 'charityContainer');				
					newCharity.id = i;
					newLabel = document.createElement('label');
					newLabel.setAttribute('class', 'createeventLabelwidth');
					newLabel.innerHTML = "&nbsp;";
					newCharity.appendChild(newLabel);
					newCharityName = document.createElement('span');
					newCharityName.setAttribute('class', 'spanreadonlytxt');
					newCharityName.appendChild(document.createTextNode(addCharityName));
					newCharity.appendChild(newCharityName);
				}
				newCharity.appendChild(document.createTextNode(" "));
				charityId = document.createElement('input');
				charityId.setAttribute('type', 'hidden');
				charityId.setAttribute('id', 'charityId'+i);
				charityId.setAttribute('name', 'charityId'+i);
				charityId.setAttribute('value', addCharityId);
				newCharity.appendChild(charityId);
				if (eventStatus == "cancelOrExpire") {
					//not show remove link on screen.
				} else {
					removeCharity = document.createElement('a');
					removeCharity.id = "removeCharity"+i;
					removeCharity.setAttribute('href', "javascript:deletedSelectedCharity("+i+");");
					removeCharity.appendChild(document.createTextNode("Remove"));
					if (navigator.appName == "Microsoft Internet Explorer") {
						newImage = document.createElement('<IMG class="removeImgAlign" src="../img/branding/btn-exit.gif"/>');
						removeCharity.appendChild(newImage);
					} else if (navigator.appName == "Netscape") {
						newImage = document.createElement('img');
						newImage.setAttribute('class', 'removeImgAlign');
						newImage.setAttribute('src', '../img/branding/btn-exit.gif');
						removeCharity.appendChild(newImage);
					}
					newCharity.appendChild(removeCharity);
				}
				newList.appendChild(newCharity);
			}
}

/* This is ajax call for remove selected charity from EVT0040A page. */
function deletedSelectedCharity(charityIndex) {
	 var selectedCharityIndex = charityIndex;
	 	 
	 var url = "ajaxDeleteCharityAction.action";
	 var params = 'selectedCharityIndex=' + selectedCharityIndex;
	 new Ajax.Request(url, {
		 	method :'POST',	
			parameters :params,
			asynchronous :true,
			onSuccess :reDisplayUrlResponse
	});	
	 
	   	   
	 function reDisplayUrlResponse(transport){   
		    var xml_doc = transport.responseXML.documentElement;		    		    
		    
		    theList = $('createCharity');
		    charityListSize = xml_doc.childNodes[0].childNodes.length;
		    if (charityListSize == 0) {
		    	newList = document.createElement('span');
		    	theList.parentNode.replaceChild(newList,theList);		    	
		    	newList.id = "createCharity";
		    	$('createCharity').innerHTML = "";

				theSplitList = $('createFundSplit');
				newSplitList = document.createElement('span');
				theSplitList.parentNode.replaceChild(newSplitList,theSplitList);		    	
				newSplitList.id = "createFundSplit";
				$('createFundSplit').innerHTML = "";

		    	$('charityAlreadyExist').hide();
		    	$('showExceedFive').hide();
				$('showError').hide();
				$('multiCharityErrorImage').hide();
		    } else {
		    	newList = document.createElement('span');
		    	theList.parentNode.replaceChild(newList,theList);		    	
		    	newList.id = "createCharity";
  			
		    	createCharityList(xml_doc, charityListSize);
		    	 
		    	if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndN').checked == true) {
					theSplitList = $('createFundSplit');
					newSplitList = document.createElement('span');
					theSplitList.parentNode.replaceChild(newSplitList,theSplitList);		    	
					newSplitList.id = "createFundSplit";
					createDonationSplitTotal(xml_doc,charityListSize);
				}

				$('showExceedFive').hide();
				$('charityAlreadyExist').hide();
				$('showError').hide();
				$('multiCharityErrorImage').hide();
		    }
		   
	}	
}

/* This ajax function is called when donation split required indicator status
 * is changed When User change donationSplitReqIndicator that time all selected
 * content of this page is remove from bean and page show no selected value on screen.*/
function onChangeDonationSplitReqIndicator() {
	 
	 var url = "ajaxOnChangeDonationSplitReqIndicator.action";
	 new Ajax.Request(url, {
		 	method :'POST',	
			asynchronous :true,
			onSuccess :displayUrlResponse
	});		 
	   	   
	 function displayUrlResponse(transport){   
		    var xml_doc = transport.responseXML.documentElement;		    		    
		    if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndY').checked == true) {
				theList = $('createFundSplit');
				charityListSize = xml_doc.childNodes[0].childNodes.length;
				newList = document.createElement('span');
				theList.parentNode.replaceChild(newList,theList);		    	
				newList.id = "createFundSplit";
				$('createFundSplit').innerHTML = "";					
			} else if ($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndN').checked == true) {
				theSplitList = $('createFundSplit');
			    charityListSize = xml_doc.childNodes[0].childNodes.length;
			    if (charityListSize != 0) {	
			    	newSplitList = document.createElement('span');
			    	theSplitList.parentNode.replaceChild(newSplitList,theSplitList);		    	
			    	newSplitList.id = "createFundSplit";
					
			    	createDonationSplitTotal(xml_doc,charityListSize);			    	
			    }
			    $('showTotalFundError').hide();			    
				$('totalNotHundredErrorMessage').hide();
			}		   
	}		
}

/* This ajax function is called when Charity Administrator is change donation
 * split on screen that time this function is called and check entered value
 * is proper or and also check divided donation split is 100 percentage or not. */
function onChangeDisplayTotal(textIndex,charityIndex) {
	 var charityUpdatedValue = textIndex;
	 var selectedCharityIndex = charityIndex;	 
	 var error;
	
	 $('showError').hide();
		
	 var url = "ajaxOnChangeCharityFund.action";
	 var params = 'selectedCharityIndex=' + escape(selectedCharityIndex) + '&charityUpdatedValue=' + escape(charityUpdatedValue);
	 new Ajax.Request(url, {
		 	method :'POST',
		 	parameters :params,
			asynchronous :true,
			onSuccess :showUrlResponse
	 });
	 		 
	 function showUrlResponse(transport){
		 	var NODE_ERROR_CODE = "errorCode";
		    var NODE_ERROR_DESCRIPTION = "errorDescription";
		    var VALIDATION_CODE_BLANK_TEXT_PROVIDED =  "BLANK_TEXT_PROVIDED";
		    var VALIDATION_CODE_INVALID_CHARACTERS =  "INVALID_CHARACTERS_FOR_CHARITY_NAME";
		    
		    var xml_doc = transport.responseXML.documentElement;
		    var errorMessage = xml_doc.getAttribute("errorMessage");		    
		    var errorCode = xml_doc.getAttribute(NODE_ERROR_CODE);
		    var errorDescription = xml_doc.getAttribute(NODE_ERROR_DESCRIPTION);

		    if (errorCode == VALIDATION_CODE_BLANK_TEXT_PROVIDED) {
				 error=errorDescription;
				 document.getElementById('showTotalFundError').innerHTML=error;
				 $('showTotalFundError').show();
				 if ($('totalSplit') != null) {
					 $('totalSplit').hide();
				 }	
		    }
		    else if (errorCode == VALIDATION_CODE_INVALID_CHARACTERS) {
				 error=errorDescription;
				 document.getElementById('showTotalFundError').innerHTML=error;
				 $('showTotalFundError').show();
				 if ($('totalSplit') != null) {
					 $('totalSplit').hide();
				 }	
		    }
		    else {		    
		   
		    var totalFundSplit = xml_doc.getAttribute("totalFundSplit");
		    
		    theSplitList = $('createFundSplit');
		    newSplitList = document.createElement('span');
			theSplitList.parentNode.replaceChild(newSplitList,theSplitList);		    	
			newSplitList.id = "createFundSplit";
          
		   charityListSize = xml_doc.childNodes[0].childNodes.length;

		   createDonationSplitTotal(xml_doc,charityListSize);

		    if (errorMessage == "No" ) {
				$('totalSplit').remove();
			    if (navigator.appName == "Microsoft Internet Explorer") {
					fundSplitPercent = document.createElement('<P class="Columntotal" id="totalSplit"/>');
				}else if (navigator.appName == "Netscape")	{
					fundSplitPercent = document.createElement('p');
					fundSplitPercent.setAttribute('class', 'Columntotal'); 
					fundSplitPercent.id = 'totalSplit';	
				}
			    fundSplitPercent.appendChild(document.createTextNode(totalFundSplit));
			    fundSplitPercent.appendChild(document.createTextNode("%"));
				newSplitList.appendChild(fundSplitPercent);
			    $('showTotalFundError').hide();			    
				$('charityAlreadyExist').hide();
			    $('totalNotHundredErrorMessage').hide();
		    } else if (errorMessage == "Yes") {
				$('totalSplit').remove();
				if (totalFundSplit == "100") {
					var errorDisplay = "One of the fund splits you have chosen is 0%. Please update or remove this charity.";
					document.getElementById('showTotalFundError').innerHTML = errorDisplay;
				} else {
					var errorDisplay = "Your fundraising split does not equal 100%. Please update the values.";
					document.getElementById('showTotalFundError').innerHTML = errorDisplay;
				}		    	
		    	$('showTotalFundError').show();
		    	$('charityAlreadyExist').hide();		    	
		    	$('totalNotHundredErrorMessage').hide();
		    	$('totalSplit').hide();
		    }
		}
	}		
}

/* This function is iterate xml_doc response to display on screen
 * this is use for donation split on EVT0040A page in UCEVT0020. */
function createDonationSplitTotal(xml_doc,charityListSize) {	
			newCharity = document.createElement('p');				
			if (navigator.appName == "Microsoft Internet Explorer") {
				newLabel = document.createElement('<LABEL class="createeventLabelwidth"/>');
				newLabel.innerHTML = "How would you like the money to be split between the charities?*";
				newCharity.appendChild(newLabel);
				outerSpan = document.createElement('<span class="charityAmountSplitContainer">');
			} else if (navigator.appName == "Netscape")	{
				newLabel = document.createElement('label');
				newLabel.setAttribute('class', 'createeventLabelwidth');
				newLabel.innerHTML = "How would you like the money to be split between the charities?*";
				newCharity.appendChild(newLabel);
				outerSpan = document.createElement('span');
				outerSpan.setAttribute('class', 'charityAmountSplitContainer');
			}
			
			for (i = 0; i < charityListSize; i++) {
				
				var addCharityName = xml_doc.childNodes[0].childNodes[i]
					.getAttribute("charityName");
				var addCharityId = xml_doc.childNodes[0].childNodes[i]
				    .getAttribute("charityId");
				var addCharityFundPercentage = xml_doc.childNodes[0].childNodes[i]
				    .getAttribute("charityFundPercentage");
				var eventStatus = xml_doc.childNodes[0].childNodes[i]
				    .getAttribute("eventStatus");	
				
				if (navigator.appName == "Microsoft Internet Explorer") {
					innerSpan = document.createElement('<SPAN class="multipleElementContainer">');	
					newCharityName = document.createElement('<SPAN class="spanreadonlytxt"/>');
					newCharityName.appendChild(document.createTextNode(addCharityName));						
					innerSpan.appendChild(newCharityName);
				}else if (navigator.appName == "Netscape")	{
					innerSpan = document.createElement('span');
					innerSpan.setAttribute('class', 'multipleElementContainer');
					newCharityName = document.createElement('span');
					newCharityName.setAttribute('class', 'spanreadonlytxt');
					newCharityName.appendChild(document.createTextNode(addCharityName));
					innerSpan.appendChild(newCharityName);
				}
				newCharity.appendChild(document.createTextNode(" "));
				charityId = document.createElement('input');
				charityId.setAttribute('type', 'hidden');
				charityId.setAttribute('id', 'charityId'+i);
				charityId.setAttribute('name', 'charityId'+i);
				charityId.setAttribute('value', addCharityId);
				innerSpan.appendChild(charityId);
				if (navigator.appName == "Microsoft Internet Explorer") {
					if (eventStatus == "cancelOrExpire") {
						charityFundPercent = document.createElement('<input TYPE="text" onChange="onChangeDisplayTotal('+'value'+','+i+');" id="'+'charityFundPercentage'+i+'" value="'+addCharityFundPercentage+'" size="3" maxlength="3" disabled="disabled" />');
					} else {
						charityFundPercent = document.createElement('<input TYPE="text" onChange="onChangeDisplayTotal('+'value'+','+i+');" id="'+'charityFundPercentage'+i+'" value="'+addCharityFundPercentage+'" size="3" maxlength="3" />');
					}
					innerSpan.appendChild(charityFundPercent);
				
				} else if (navigator.appName == "Netscape") {
					charityFundPercent = document.createElement('input');
					charityFundPercent.setAttribute('type', 'text');
					charityFundPercent.setAttribute('id', 'charityFundPercentage'+i);
					charityFundPercent.setAttribute('name', 'charityFundPercentage'+i);
					charityFundPercent.setAttribute('value', addCharityFundPercentage);
					charityFundPercent.setAttribute('onchange', "javascript:onChangeDisplayTotal("+"value"+","+i+");");
					charityFundPercent.setAttribute('size','3');
					charityFundPercent.setAttribute('maxlength','3');
					if (eventStatus == "cancelOrExpire") {
						charityFundPercent.setAttribute('disabled','disabled');
					}
					innerSpan.appendChild(charityFundPercent);
				}
				innerSpan.appendChild(document.createTextNode("%"));
				outerSpan.appendChild(innerSpan);
			}
			newCharity.appendChild(outerSpan);
			newSplitList.appendChild(newCharity);
			if (navigator.appName == "Microsoft Internet Explorer") {
				fundSplitPercent = document.createElement('<P class="Columntotal" id="totalSplit"/>');
			}else if (navigator.appName == "Netscape")	{
				fundSplitPercent = document.createElement('p');
				fundSplitPercent.setAttribute('class', 'Columntotal'); 
				fundSplitPercent.id = 'totalSplit';	
			}
			fundSplitPercent.appendChild(document.createTextNode("100%"));			
			newSplitList.appendChild(fundSplitPercent);
}

/* This function is create empty span display on screen when
 * no charity added on screen.*/
function createEmptySpan(xml_doc) {
	theList = $('createCharity');
	theListForFundSplit = $('createFundSplit');
	charityListSize = xml_doc.childNodes[0].childNodes.length;
	if (charityListSize == 0) {
		newList = document.createElement('span');
		theList.parentNode.replaceChild(newList,theList);		    	
		newList.id = "createCharity";
		$('createCharity').innerHTML = "";
		
		newListForFundSplit = document.createElement('span');
		theListForFundSplit.parentNode.replaceChild(newListForFundSplit,theListForFundSplit);		    	
		newListForFundSplit.id = "createFundSplit";
		$('createFundSplit').innerHTML = "";
	}
}

/* This ajax function is called when VMG Manage Fee indicator status is changed
 * When User change vmgManageFeeIndicator that time all selected content
 * of this page is remove from bean and page show no selected value on screen.*/
function onChangeVmgManageFeeIndicator() {
	 
	 var url = "ajaxOnChangeVmgManageFeeIndicator.action";
	 new Ajax.Request(url, {
		 	method :'POST',	
			asynchronous :true,
			onSuccess :displayUrlResponse
	});	
	 
	   	   
	 function displayUrlResponse(transport){   
		    var xml_doc = transport.responseXML.documentElement;		    		    
		    if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_vmgManagingFeeIndicatorN').checked == true) {
				createEmptySpan(xml_doc);
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_payRegistrationFeeIndY').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_payRegistrationFeeIndN').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineRegistationLimtIndY').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineRegistationLimtIndN').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineEntryDeadlineIndY').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_onlineEntryDeadlineIndN').checked = false;
				if($('howmanyplaces') != null){
					$('howmanyplaces').writeAttribute("disabled","disabled");
				}
				if($('date') != null){
					$('date').writeAttribute("disabled","disabled");
					$('month').writeAttribute("disabled","disabled");
					$('year').writeAttribute("disabled","disabled");
				}

				$('showTotalFundError').hide();				
				$('totalNotHundredErrorMessage').hide();
				$('howmanyplaces').value = "";
				$('date').value = "";
				$('month').value = "";
				$('year').value = "";
				$('notesorcomment').value = "";		
			} else if ($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_vmgManagingFeeIndicatorY').checked == true) {
				onChangeVmgManageFeeCleanEventFeeDetails();
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_eventSolelyAidOfCharityIndN').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_eventSolelyAidOfCharityIndY').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_charitiesSupportedIndlimited').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_charitiesSupportedIndall').checked = false;
				$('addCharityId').value = "";
				$('addCharityName').value = "";
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndN').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndY').checked = false;				
				
				$('multiCharityErrorImage').hide();
				$('multiCharityErrorMessage').hide();
				$('fundSplitErrorMessage').hide();
				$('showError').hide();
				$('showExceedFive').hide();
				$('charityAlreadyExist').hide();
				$('showErrorMessage').hide();
				if ($('charityAdminErrorMessage') != null) {
					$('charityAdminErrorMessage').hide();
				}				
			}		   
	}		
}

/* This ajax function is called when Own Charity Indicator status is changed
 * When User change OwnCharityIndicator that time all selected content
 * of this page is remove from bean and page show no selected value on screen.*/
function onChangeOwnCharityIndicator() {
	 
	 var url = "ajaxOnChangeVmgManageFeeIndicator.action";
	 new Ajax.Request(url, {
		 	method :'POST',	
			asynchronous :true,
			onSuccess :displayUrlResponse
	});	

	 function displayUrlResponse(transport){   
		    var xml_doc = transport.responseXML.documentElement;		    		    
		    if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_eventSolelyAidOfCharityIndY').checked = true) {
				createEmptySpan(xml_doc);
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_charitiesSupportedIndlimited').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_charitiesSupportedIndall').checked = false;
				$('addCharityId').value = "";
				$('addCharityName').value = "";
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndN').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndY').checked = false;				
				
				$('multiCharityErrorImage').hide();
				$('multiCharityErrorMessage').hide();
				$('fundSplitErrorMessage').hide();
				$('showError').hide();
				$('showExceedFive').hide();
				$('charityAlreadyExist').hide();
				$('showErrorMessage').hide();
				if ($('charityAdminErrorMessage') != null) {
					$('charityAdminErrorMessage').hide();
				}
			}
	 }	 
}

/* This ajax function is called when Charity Supported Indicator status is changed
 * When User change charitySupportedIndicator that time all selected content
 * of this page is remove from bean and page show no selected value on screen.*/
function onChangeCharitySupportedIndicator() {
	 
	 var url = "ajaxOnChangeVmgManageFeeIndicator.action";
	 new Ajax.Request(url, {
		 	method :'POST',	
			asynchronous :true,
			onSuccess :displayUrlResponse
	});	

	 function displayUrlResponse(transport){   
		    var xml_doc = transport.responseXML.documentElement;		    		    
		    if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_charitiesSupportedIndall').checked = true) {
				createEmptySpan(xml_doc);
				$('addCharityId').value = "";
				$('addCharityName').value = "";
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndN').checked = false;
				$('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndY').checked = false;				
				
				$('multiCharityErrorImage').hide();
				$('multiCharityErrorMessage').hide();
				$('fundSplitErrorMessage').hide();
				$('showError').hide();
				$('showExceedFive').hide();
				$('charityAlreadyExist').hide();
				$('showErrorMessage').hide();
				if ($('charityAdminErrorMessage') != null) {
					$('charityAdminErrorMessage').hide();
				}
			}
	 }	 
}

/** AJAX action call for event fee addition - START*/
/* This AJAX function is called when VMG Manage Fee and user and event fee
 * details in page or amend event details that time when page is load and
 * VMG manage fee this AJAX function is called and user selected value is
 * displayed on screen .*/
function onloadEventdetails() {
	 
	 var url = "ajaxLoadEventDetails.action";
	 new Ajax.Request(url, {
		 	method :'POST',	
			asynchronous :true,
			onSuccess :displayUrlResponse
	});	
	 
	   	   
	 function displayUrlResponse(transport){   
		    var xml_doc = transport.responseXML.documentElement;		    		    
		    
		    theList = $('createCharity');
			theSplitList = $('createFundSplit');
			charityListSize = xml_doc.childNodes[0].childNodes.length;
			if (charityListSize == 0) {
				newList = document.createElement('span');
				theList.parentNode.replaceChild(newList,theList);		    	
				newList.id = "createCharity";
				$('createCharity').innerHTML = "";
				
				newSplitList = document.createElement('span');
				theSplitList.parentNode.replaceChild(newSplitList,theSplitList);		    	
				newSplitList.id = "createFundSplit";
				$('createFundSplit').innerHTML = "";
			} else {
		    	newList = document.createElement('span');
		    	theList.parentNode.replaceChild(newList,theList);		    	
		    	newList.id = "createCharity";
		    	
		    	newSplitList = document.createElement('span');
				theSplitList.parentNode.replaceChild(newSplitList,theSplitList);		    	
				newSplitList.id = "createFundSplit";
  			
				createCharityList(xml_doc, charityListSize);
				
				if($('charityDonationSplit_organisedEventBean_organisedEventFeeDetails_donationSplitReqIndN').checked == true) {
					createDonationSplitTotal(xml_doc,charityListSize);
				}
				$('showExceedFive').hide();
				$('charityAlreadyExist').hide();
		    }
		   
	}	
}

/* When adding event fee details on EVT0040B page that time this AJAX
 * function is called*/
function addEventFeeDetails() {
	 var selectedEventRef = $F('eventRef');
	 var selectedEventSituation = $F('eventSituation');
	 var selectedEventFee = $F('eventFee');	 
	
	$('showErrorMessage').hide(); 
	var url = "ajaxAddEventFeeDetails.action";
	var params = 'selectedEventRef=' + escape(selectedEventRef);
	params = params + '&selectedEventSituation=' + escape(selectedEventSituation);
	params = params + '&selectedEventFee=' + escape(selectedEventFee);
	new Ajax.Request(url, {
			method :'POST',	
			parameters :params,
			asynchronous :true,
			onSuccess :displayEventFeeDetailsResponse
		});		
	   	   
	 function displayEventFeeDetailsResponse(transport){	    
		    
		 	var NODE_ERROR_CODE = "errorCode";
		    var NODE_ERROR_DESCRIPTION = "errorDescription";
		    var VALIDATION_CODE_BLANK_TEXT_PROVIDED =  "BLANK_TEXT_PROVIDED";
		    var VALIDATION_CODE_INVALID_CHARACTERS =  "INVALID_CHARACTERS_FOR_CHARITY_NAME";
		    
		    var xml_doc = transport.responseXML.documentElement;
		    var errorMessage = xml_doc.getAttribute("errorMessage");		   
		    var errorCode = xml_doc.getAttribute(NODE_ERROR_CODE);
		    var errorDescription = xml_doc.getAttribute(NODE_ERROR_DESCRIPTION);

		    if (errorCode == VALIDATION_CODE_BLANK_TEXT_PROVIDED) {
				 error=errorDescription;
				 document.getElementById('showErrorMessage').innerHTML=error;
				 $('showErrorMessage').show();
				 //$('multiCharityErrorImage').show();
		    }
		    else if (errorCode == VALIDATION_CODE_INVALID_CHARACTERS) {
				 error=errorDescription;
				 document.getElementById('showErrorMessage').innerHTML=error;
				 $('showErrorMessage').show();
				// $('multiCharityErrorImage').show();
		    }
		    else {
		    	//create new dynamic table to populate on screen.
				createTable(xml_doc);				
			}
	}
	
}

/* When change VMG manage fee indicator no to yes that time this AJAX
 * function is called and clear previously selected value.*/
function onChangeVmgManageFeeCleanEventFeeDetails() {

	 var url = "onChangeVmgManageFeeChangeEventDetails.action";
	 new Ajax.Request(url, {
		 	method :'POST',	
			asynchronous :true,
			onSuccess :displayUrlResponse
	});	
		   	   
	function displayUrlResponse(transport){   
		    var xml_doc = transport.responseXML.documentElement;
			//create new dynamic table to populate on screen.
		    createTable(xml_doc);
			$('howmanyplaces').value = "";						
			$('date').value = "";
			$('month').value = "";
			$('year').value = "";
			$('notesorcomment').value = "";
			//error message
			$('accountDetailsError').hide();
			$('accountDetailsErrorImage').hide();			
			$('eventFeeDetailsError').hide();
			$('eventFeeDetailsErrorImage').hide();
			$('fundraiserPaymentIndError').hide();
			$('fundraiserPaymentIndErrorImage').hide();
			$('onlineEntryLimitIndError').hide();
			$('onlineEntryLimitIndErrorImage').hide();
			$('onlineEntryLimitError').hide();
			$('onlineEntryLimitErrorImage').hide();
			$('onlineClosureIndError').hide();
			$('onlineClosureIndErrorImage').hide();
			$('closureDateError').hide();
			$('closureDateErrorImage').hide();
			$('feeInstuctionErrorMessage').hide();
			$('feeInstuctionErrorMessageImage').hide();
	}
	
}

/*This function is use for dynamically added table on EVT0040B screen.*/
function createTable(xml_doc) {

	theTable = $('createEventFeeTable');
	if (navigator.appName == "Microsoft Internet Explorer") {
		newTable = document
				.createElement('<TABLE class="setupevent-search-results" id="createEventFeeTable"/>');
		theTable.parentNode.replaceChild(newTable,theTable);
	} else if (navigator.appName == "Netscape") {
		newTable = document.createElement('TABLE');
		theTable.parentNode.replaceChild(newTable,theTable);
		newTable.setAttribute('id', 'createEventFeeTable');
		newTable.setAttribute('class', 'setupevent-search-results');
	}
	tabBody = document.createElement('TBODY');					
					
	tableColTitle = document.createElement('TR');
	titleCell1 = document.createElement("TD");
	textStrong1 = document.createElement("strong");
	textStrong1.appendChild(document.createTextNode("Your ref."));
	titleCell1.appendChild(textStrong1);
	tableColTitle.appendChild(titleCell1);				
	titleCell2 = document.createElement("TD");
	textStrong2 = document.createElement("strong");
	textStrong2.appendChild(document.createTextNode("Situation"));
	titleCell2.appendChild(textStrong2);
	tableColTitle.appendChild(titleCell2);				
	titleCell3 = document.createElement("TD");
	textStrong3 = document.createElement("strong");
	textStrong3.appendChild(document.createTextNode("Fee"));
	titleCell3.appendChild(textStrong3);
	tableColTitle.appendChild(titleCell3);				
	tabBody.appendChild(tableColTitle);
					
	disableRow =document.createElement("TR");
	cell1 = document.createElement("TD");				
	inputText1 = document.createElement("input");
	inputText1.setAttribute('type', 'text');
	inputText1.setAttribute('value', 'e.g. ABC001');
	inputText1.setAttribute('disabled', 'disabled');
	cell1.appendChild(inputText1);
	disableRow.appendChild(cell1);
	cell2 = document.createElement("TD");
	inputText2 = document.createElement("input");
	inputText2.setAttribute('type', 'text');
	inputText2.setAttribute('value', 'e.g. under 16');
	inputText2.setAttribute('disabled', 'disabled');
	cell2.appendChild(inputText2);
	disableRow.appendChild(cell2);
	cell3 = document.createElement("TD");
	cell3.appendChild(document.createTextNode("£"));
	inputText3 = document.createElement("input");
	inputText3.setAttribute('type', 'text');
	inputText3.setAttribute('value', 'e.g. £10.00');
	inputText3.setAttribute('disabled', 'disabled');		         
	cell3.appendChild(inputText3);				
	disableRow.appendChild(cell3);
	tabBody.appendChild(disableRow); 			
			
	eventDetailsListSize = xml_doc.childNodes[0].childNodes.length;

	var eventStatus;
	if (eventDetailsListSize != 0) {
		for (i = 0; i < eventDetailsListSize; i++) {
		 eventStatus = xml_doc.childNodes[0].childNodes[i]
	                  .getAttribute("eventStatus");
		}
		 // create dynamically record as per ajax response.
		 createTableDynamicRow(xml_doc, eventDetailsListSize)	
	} else {
		 eventStatus = "No";
	}
					
	if (eventStatus == "No") {
		newrow = document.createElement("TR");
		eventRefcell = document.createElement("TD");
		if (navigator.appName == "Microsoft Internet Explorer") {
			inputEventRef = document.createElement('<input TYPE="text" name="organisedEventBean.organisedEventFeeDetails.eventReference" id="eventRef" maxlength="15" />');
			eventRefcell.appendChild(inputEventRef);
		} else if (navigator.appName == "Netscape") {
			inputEventRef = document.createElement("input");
			inputEventRef.setAttribute('type', 'text');
			inputEventRef.setAttribute('name',
				'organisedEventBean.organisedEventFeeDetails.eventReference');
			inputEventRef.setAttribute('id', 'eventRef');
			inputEventRef.setAttribute('maxlength', '15');
			eventRefcell.appendChild(inputEventRef);
		}		
		newrow.appendChild(eventRefcell);
		eventSituationCell = document.createElement("TD");
		if (navigator.appName == "Microsoft Internet Explorer") {
			inputEventSituation = document.createElement('<input TYPE="text" name="organisedEventBean.organisedEventFeeDetails.eventFeeSituation" id="eventSituation" maxlength="50" />');
			eventSituationCell.appendChild(inputEventSituation);
		} else if (navigator.appName == "Netscape") {
			inputEventSituation = document.createElement("input");
			inputEventSituation.setAttribute('type', 'text');
			inputEventSituation.setAttribute('name',
					'organisedEventBean.organisedEventFeeDetails.eventFeeSituation');
			inputEventSituation.setAttribute('id', 'eventSituation');
			inputEventSituation.setAttribute('maxlength', '50');
			eventSituationCell.appendChild(inputEventSituation);
		}
		newrow.appendChild(eventSituationCell);
		eventFeeCell = document.createElement("TD");
		eventFeeCell.appendChild(document.createTextNode("£"));
		if (navigator.appName == "Microsoft Internet Explorer") {
			inputEventFee = document.createElement('<input TYPE="text" name="organisedEventBean.organisedEventFeeDetails.eventFee" id="eventFee" maxlength="7" />');
			eventFeeCell.appendChild(inputEventFee);
		} else if (navigator.appName == "Netscape") {
			inputEventFee = document.createElement("input");
			inputEventFee.setAttribute('type', 'text');
			inputEventFee.setAttribute('name',
					'organisedEventBean.organisedEventFeeDetails.eventFee');
			inputEventFee.setAttribute('id', 'eventFee');
			inputEventFee.setAttribute('maxlength', '7');
			eventFeeCell.appendChild(inputEventFee);
		}	
		addButton = document.createElement('a');
		addButton.setAttribute('href', "javascript:addEventFeeDetails();");
		if (navigator.appName == "Microsoft Internet Explorer") {
			newImage = document
					.createElement('<IMG class="verticlealign-add-btn" title="Add" alt="Add" src="../img/branding/btn-add.gif"/>');
			addButton.appendChild(newImage);
		} else if (navigator.appName == "Netscape") {
			newImage = document.createElement('img');
			newImage.setAttribute('class', 'verticlealign-add-btn');
			newImage.setAttribute('src', '../img/branding/btn-add.gif');
			addButton.appendChild(newImage);
		}
		eventFeeCell.appendChild(addButton);
		newrow.appendChild(eventFeeCell);
		tabBody.appendChild(newrow);
	} else {
		/*Above row is not display when event is canceled and expired state.*/ 
	}
	newTable.appendChild(tabBody);
}

/* This function is use for dynamically added table on EVT0040B screen
 * base on XML response. */
function createTableDynamicRow(xml_doc, eventDetailsListSize){	
	for (i = 0; i < eventDetailsListSize; i++) {

		var eventRef = xml_doc.childNodes[0].childNodes[i]
				.getAttribute("eventRef");
		var eventSituation = xml_doc.childNodes[0].childNodes[i]
				.getAttribute("eventSituation");
		var eventFee = xml_doc.childNodes[0].childNodes[i]
				.getAttribute("eventFee");
		var eventId = xml_doc.childNodes[0].childNodes[i]
		        .getAttribute("eventId");
		var eventStatus = xml_doc.childNodes[0].childNodes[i]
		        .getAttribute("eventStatus");
		
		addRow = document.createElement("TR");
		addRow.setAttribute('id', i);
		addEventRefcell = document.createElement("TD");
		if (navigator.appName == "Microsoft Internet Explorer") {
			if (eventStatus == "cancelOrExpire") {
				addEventRef = document.createElement('<input TYPE="text" onChange="onChangeEventDetails('+'value'+','+i+','+'1'+');" id="'+'eventRef'+i+'" value="'+eventRef+'" maxlength="15" disabled= "disabled"/>');
			} else {
				addEventRef = document.createElement('<input TYPE="text" onChange="onChangeEventDetails('+'value'+','+i+','+'1'+');" id="'+'eventRef'+i+'" value="'+eventRef+'" maxlength="15" />');
			}		
			addEventRefcell.appendChild(addEventRef);		
		} else if (navigator.appName == "Netscape") {
		addEventRef = document.createElement("input");
		addEventRef.setAttribute('type', 'text');
		addEventRef.setAttribute('name', 'eventRef' + i);
		addEventRef.setAttribute('id', 'eventRef' + i);
		addEventRef.setAttribute('value', eventRef);
		if (eventStatus == "cancelOrExpire") {
			addEventRef.setAttribute('disabled', 'disabled');
		}
		addEventRef.setAttribute('onchange', "javascript:onChangeEventDetails("+"value"+","+i+","+"1);");
		addEventRef.setAttribute('maxlength', '15');
		addEventRefcell.appendChild(addEventRef);
		}
		addRow.appendChild(addEventRefcell);		
		addEventSituationCell = document.createElement("TD");
		if (navigator.appName == "Microsoft Internet Explorer") {
			if (eventStatus == "cancelOrExpire") {
				addEventSituation = document.createElement('<input TYPE="text" onChange="onChangeEventDetails('+'value'+','+i+','+'2'+');" id="'+'addEventSituation'+i+'" value="'+eventSituation+'" maxlength="50" disabled="disabled" />');
			} else {
				addEventSituation = document.createElement('<input TYPE="text" onChange="onChangeEventDetails('+'value'+','+i+','+'2'+');" id="'+'addEventSituation'+i+'" value="'+eventSituation+'" maxlength="50" />');
			}
			addEventSituationCell.appendChild(addEventSituation);		
		} else if (navigator.appName == "Netscape") {
		addEventSituation = document.createElement("input");
		addEventSituation.setAttribute('type', 'text');
		addEventSituation.setAttribute('name', 'eventSituation' + i);
		addEventSituation.setAttribute('id', 'eventSituation' + i);
		addEventSituation.setAttribute('value', eventSituation);
		if (eventStatus == "cancelOrExpire") {
			addEventSituation.setAttribute('disabled', 'disabled');
		}
		addEventSituation.setAttribute('onchange', "javascript:onChangeEventDetails("+"value"+","+i+","+"2);");
		addEventSituation.setAttribute('maxlength', '50');
		addEventSituationCell.appendChild(addEventSituation);
		}
		addRow.appendChild(addEventSituationCell);
		addEventFeeCell = document.createElement("TD");		
		addEventFeeCell.appendChild(document.createTextNode("£"));
		if (navigator.appName == "Microsoft Internet Explorer") {
			if (eventStatus == "cancelOrExpire") {
				addEventFee = document.createElement('<input TYPE="text" onChange="onChangeEventDetails('+'value'+','+i+','+'3'+');" id="'+'addEventFee'+i+'" value="'+eventFee+'" maxlength="7" disabled="disabled" />');
			} else {
				addEventFee = document.createElement('<input TYPE="text" onChange="onChangeEventDetails('+'value'+','+i+','+'3'+');" id="'+'addEventFee'+i+'" value="'+eventFee+'" maxlength="7" />');
			}
			addEventFeeCell.appendChild(addEventFee);		
		} else if (navigator.appName == "Netscape") {
		addEventFee = document.createElement("input");
		addEventFee.setAttribute('type', 'text');
		addEventFee.setAttribute('name', 'eventFee' + i);
		addEventFee.setAttribute('id', 'eventFee' + i);
		addEventFee.setAttribute('value', eventFee);
		if (eventStatus == "cancelOrExpire") {
			addEventFee.setAttribute('disabled', 'disabled');
		}
		addEventFee.setAttribute('onchange', "javascript:onChangeEventDetails("+"value"+","+i+","+"3);");
		addEventFee.setAttribute('maxlength', '7');
		addEventFeeCell.appendChild(addEventFee);
		}
		addEventId = document.createElement('input');
		addEventId.setAttribute('type', 'hidden');
		addEventId.setAttribute('id', 'eventId'+i);
		addEventId.setAttribute('name', 'eventId'+i);
		addEventId.setAttribute('value', eventId);
		addEventFeeCell.appendChild(addEventId);		
		addRow.appendChild(addEventFeeCell);
		tabBody.appendChild(addRow);
	}
}

/* OnLoad when VMG manage fee that time selected value is properly
 * displayed on screen.  */
function onLoadVmgManageFeeIndForEventDetails() {
	 var url = "onLoadVmgManageFeeIndForEventDetails.action";
	 new Ajax.Request(url, {
		 	method :'POST',	
			asynchronous :true,
			onSuccess :displayUrlResponse
	});	
		   	   
	function displayUrlResponse(transport){   
		    var xml_doc = transport.responseXML.documentElement;
			//create new dynamic table to populate on screen.
		    createTable(xml_doc);
	}
}

/* onChange Event Fee Details validate fee details value. If value is
 * proper then properly updated on screen. */
function onChangeEventDetails(textValue, selectedRowIndex, selectedFieldName) {
	var selectedUpdatedValue = textValue;
	var selectedEventFeeIndex = selectedRowIndex;
	var selectedFieldNameText = selectedFieldName;
	
	$('showErrorMessage').hide(); 
	var url = "onChangeEventFeeDetails.action";	
	var params = 'selectedUpdatedValue=' + escape(selectedUpdatedValue);
	params = params + '&selectedEventFeeIndex=' + selectedEventFeeIndex;
	params = params + '&selectedFieldNameText=' + selectedFieldNameText;
	new Ajax.Request(url, {
			method :'POST',	
			parameters :params,
			asynchronous :true,
			onSuccess :displayEventFeeDetailsResponse
		});		
	   	   
	 function displayEventFeeDetailsResponse(transport){	    
		    
		 	var NODE_ERROR_CODE = "errorCode";
		    var NODE_ERROR_DESCRIPTION = "errorDescription";
		    var VALIDATION_CODE_BLANK_TEXT_PROVIDED =  "BLANK_TEXT_PROVIDED";
		    var VALIDATION_CODE_INVALID_CHARACTERS =  "INVALID_CHARACTERS_FOR_CHARITY_NAME";
		    
		    var xml_doc = transport.responseXML.documentElement;
		    var errorMessage = xml_doc.getAttribute("errorMessage");		   
		    var errorCode = xml_doc.getAttribute(NODE_ERROR_CODE);
		    var errorDescription = xml_doc.getAttribute(NODE_ERROR_DESCRIPTION);

		    if (errorCode == VALIDATION_CODE_BLANK_TEXT_PROVIDED) {
				 error=errorDescription;
				 document.getElementById('showErrorMessage').innerHTML=error;
				 $('showErrorMessage').show();
				 //$('multiCharityErrorImage').show();
		    }
		    else if (errorCode == VALIDATION_CODE_INVALID_CHARACTERS) {
				 error=errorDescription;
				 document.getElementById('showErrorMessage').innerHTML=error;
				 $('showErrorMessage').show();
				// $('multiCharityErrorImage').show();
		    }
		    else {
		    	//create new dynamic table to populate on screen.
				createTable(xml_doc);				
			}
	}
}

/** ajax action call for event fee addition - END */

/** ajax action calling method for UCEVT0020 - END */

/*
* This fundtion is used to diplay the labels(Adminsitration address / postcode) of the address lookup component.
*/
function displayAddressLookupComponentLabels(){
	if( $('adminPostCode')!=null){
		$('adminPostCode').show();
	}
	if( $('adminBuildingnameOrNumber')!=null){
		$('adminBuildingnameOrNumber').show();
	}
}

/*
* This fundtion is used to hide the labels(Adminsitration address / postcode) of the address lookup component.
*/
function hideAddressLookupComponentLabels(){
	if( $('adminPostCode')!=null){
		$('adminPostCode').hide();
	}
	if( $('adminBuildingnameOrNumber')!=null){
		$('adminBuildingnameOrNumber').hide();
	}
}

/** Start EVT0140A */
function checkUncheckAll(ele){
	var state = ele.checked;
	var checks = document.getElementsByName("selectAdminUsers");
	for (i = 0; i < checks.length; i++) {
		var checkbox = checks[i];
		checkbox.checked = state;
	}
}

function selectAll(ele){
	var state = ele.checked;
	if(!state) {
		document.getElementsByName("selectAllAdminUsers")[0].checked = false;
	} else {
		var checks = document.getElementsByName("selectAdminUsers");
		var totalLength = checks.length;
		var selectedLength = 0;
		for (i = 0; i < totalLength; i++) {
			var checkbox = checks[i];
			if(checkbox.checked) {
				selectedLength = selectedLength +1;
			}
		}
		if(totalLength == selectedLength) {
			document.getElementsByName("selectAllAdminUsers")[0].checked = true;
		}
	}
}
/** End EVT0140A */


/*Make Dynamic on country selection, it will hide the postcode ,
building name or number and addressLookup fields*/
function manualAddressEntry () {
	if($('displayLookUpAddress') != null) {
		$('displayLookUpAddress').remove();
	}
	if ($('displayAddress') != null) {
		$('displayAddress').hide();
	}
	if ($('displayAddressData') != null) {		
		$('displayAddressData').show();
	}
	if ($('displayPostcodeAndHouseNO') != null) {		
		$('displayPostcodeAndHouseNO').show();
	}
	
	if ($('validatePostcodeAndHouseName') != null) {		
		$('validatePostcodeAndHouseName').value = true;
	}
	
	if ($('postcodeErrorImage') != null) {		
		$('postcodeErrorImage').hide();
	}
	if ($('postcodeErrorMessage') != null) {		
		$('postcodeErrorMessage').hide();
	}
	if ($('buildingErrorImage') != null) {		
		$('buildingErrorImage').hide();
	}
	if ($('buildingErrorMessage') != null) {		
		$('buildingErrorMessage').hide();
	}
	
	
   markUpdated();	
   addressListShow();
   clearPostcodeDetails();
   showDetailsOfPostcode();
}
/*Clear Address parameter List*/
function clearParameter() {
	
	if ($('insertAddressLine1') != null) {
		var addressLine1 =$F('insertAddressLine1');
	}
	if ($('insertAddressLine2') != null) {
		var addressLine2 = $F('insertAddressLine2');
	}
	if ($('insertTownOrCity') != null) {
		var city = $F('insertTownOrCity');
	}
	if ($('insertCounty') != null) {
		var country = $F('insertCounty');
	}
	if ($('insertPostCode') != null) {
		var postcode = $F('insertPostCode');
	}	
 
	if( addressLine1 != "" && addressLine1 != null) {
		$('insertAddressLine1').value = "";
	}
	if( addressLine2 != "" && addressLine2 != null) {
		$('insertAddressLine2').value = "";
	}
	if( city != "" && city != null) {
		$('insertTownOrCity').value = "";
	}
	if( country != "" && country != null) {
		$('insertCounty').value = "";
	}
	if( country != "" && country != null) {
		$('insertPostCode').value = "";
	}

	if ($('displayAddressLine1') != null ) {
		$('displayAddressLine1').innerHTML="";
	}	

	if ($('displayAddressLine2') != null ) {
		$('displayAddressLine2').innerHTML="";
	}

	if ($('displayCity') != null ) {
		$('displayCity').innerHTML="";
	}

	if ($('displayCounty') != null ) {
		$('displayCounty').innerHTML="";
	}

	if ($('displayPostCode') != null ) {
		$('displayPostCode').innerHTML="";
	}
	
}

/**Clear post code and  Building number*/
function clearPostcodeDetails()
{
	if ($('regPostCode') != null) {
		var pagePostcode =$F('regPostCode');
	}
	if ($('regBuildingNmNo') != null) {
		var buildingNumber =$F('regBuildingNmNo');
	}
	if( buildingNumber != "" && buildingNumber != null) {		
		$('regBuildingNmNo').value = "";
	}
	if( pagePostcode != "" && pagePostcode != null) {
		$('regPostCode').value = "";
	}
	clearParameter();

}

/**Show and Hide Address Arrow*/
function  addressListShow()
{
	if ($('countryOfResidence') != null) {
		var countryName = $F('countryOfResidence');
	}
	
if(countryName=='GB'){
  if ($('findAddress') != null) {
			 $('findAddress').show();
  }

  if ($('detailedAddress') != null) {
			 $('detailedAddress').hide();
  }
}
else {
		 if ($('findAddress') != null) {
			 $('findAddress').hide();
	 }

	if ($('detailedAddress') != null) {
			 $('detailedAddress').show();
	 }
}
}

function  showDetailsOfPostcode()
{
	 if ($("displayIdForbuildingNameNumber") != null) {
			 $('displayIdForbuildingNameNumber').show();
  }
  if ($("regBuildingNmNo") != null) {
			 $('regBuildingNmNo').show();
  }
  if ($("regBuildingNmNo") != null) {
			 $('regBuildingNmNo').show();
  }

  if ($("displayIdPostCode") != null) {
			 $('displayIdPostCode').show();
  }
  if ($("regPostCode") != null) {
			 $('regPostCode').show();
  }
}

//Implement Image Functionality for java script enable.
function onclickAddAnother() {

	if($('chkbox1').checked == true) {
			$('charityHomeAddOther').href="charityHomeAddAnotherPage.action?imageEnable=true";	
			} else if($('chkbox1').checked == false) {
			$('charityHomeAddOther').href="charityHomeAddAnotherPage.action";
			}
}

	/* Function to check the number of characters entered in Text area. */ 
	var count = 400; 
	function limiter() {
		var tex = document.userAccountStatus.reason.value;
	    var len = tex.length;
	    
	    if (len > count) {
	        tex = tex.substring(0, count);
	        document.userAccountStatus.reason.value = tex;            
	        return false;
	        
	    } else {            
	        return true;
	    }        
	}


