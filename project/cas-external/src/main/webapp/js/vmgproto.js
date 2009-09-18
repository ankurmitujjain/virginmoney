

/*this script is written for EVT0020A by Dayanand on 24/12/08 [Start]*/

document.observe("dom:loaded", function() {


	$$('div.open_error_popup').each(function(elmt){
		elmt.className = 'error_popup_closed';
	});

	/** Start EVT0140A */
/*
      $$('[name="authorisedUsers"]').each(function(elmt) {
            elmt.observe('click',function(){
                  var elementId = elmt.identify();
                  if(elementId == "all"){
                        if($('all').checked == true) {
                              $$('[name="authorisedUsers"]').each(function(element) {
                                    element.clear();
                                    element.writeAttribute("checked", "checked" );
                              });

                        }else{
                              $$('[name="authorisedUsers"]').each(function(element2) {
                                    element2.clear();
                                    element2.removeAttribute("checked", "checked" );
                              });

                        }

                  }else{

                        $$('[name="authorisedUsers"]').each(function(element3) {
                              element3.clear();
                              if(element3.identify() != elementId){
                              element3.removeAttribute("checked", "checked" );

                              }else{
                                    element3.writeAttribute("checked", "checked" );
                              }
                        });


                  }
            });
      });

      /** End EVT0140A */


	/** Start EVT0020A */
	$$('[name="ownevent"]').each(function(elmt) {
		elmt.observe('click',function(){
			if($('existingevent').checked == true) {
				$('content-secondary').show();
				$('createownevent').hide();
			}else {
				$('content-secondary').hide();
				$('createownevent').show();
				$('eventsearch-result').hide();
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
	$$('[name="manageyourfee"]').each(function(elmt) {
		elmt.observe('click',function(){
			if($('vmgmanageno').checked == true) {
				$('charityforyouonly').show();
				$('managepaymentyes').hide();
			}else {
				$('charityforyouonly').hide();
				$('whatothercharity').hide();
				$('chooseyourcharity').hide();
				$('howtosplit').hide();
				$('managepaymentyes').show();
			}
		});
	});

	$$('[name="charity"]').each(function(elmt) {
		elmt.observe('click',function(){
			if($('charityno').checked == true) {
				$('whatothercharity').show();
			}else {
				$('whatothercharity').hide();
				$('chooseyourcharity').hide();
				$('howtosplit').hide();
			}
		});
	});

	$$('[name="otherCharity"]').each(function(elmt) {
		elmt.observe('click',function(){
			if($('certaincharities').checked == true) {
				$('chooseyourcharity').show();
			}else {
				$('chooseyourcharity').hide();
				$('howtosplit').hide();
			}
		});
	});

	$$('[name="fromlist"]').each(function(elmt) {
		elmt.observe('click',function(){
			if($('fromlistno').checked == true) {
				$('howtosplit').show();
			}else {
				$('howtosplit').hide();
			}
		});
	});

	if($('other') != null){

	$('other').checked=false;/*uncheck onload*/
	$('otherTextField').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('other').observe('click', function(ownevent){
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

	/* script for ALT0100 Screens start here*/

	if($('emailme2don') != null){
	$('emailme2don').checked=false;/*uncheck onload*/

	$('emailmefield').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('emailme2don').observe('click', function(ownevent){
		if ($('emailme2don').checked == true){
			//alert("checked");
			$('emailmefield').removeAttribute("disabled", "disabled" );
		}else{
			//alert("unchecked");
			$('emailmefield').clear();/*clear at time of disabled*/
			$('emailmefield').writeAttribute("disabled", "disabled" );
		}

		});

	}
	if($('emailmoredon') != null){
	$('emailmoredon').checked=false;/*uncheck onload*/

	$('emailmoredonfield').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('emailmoredonfield1').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('emailmoredon').observe('click', function(ownevent){
		if ($('emailmoredon').checked == true){
			//alert("checked");
			$('emailmoredonfield').removeAttribute("disabled", "disabled" );
			$('emailmoredonfield1').removeAttribute("disabled", "disabled" );/*disabled onload*/
		}else{
			//alert("unchecked");
			$('emailmoredonfield').clear();/*clear at time of disabled*/
			$('emailmoredonfield').writeAttribute("disabled", "disabled" );
			$('emailmoredonfield1').clear();/*clear at time of disabled*/
			$('emailmoredonfield1').writeAttribute("disabled", "disabled" );
		}

		});

	}

	if($('emailmoretimes') != null){
	$('emailmoretimes').checked=false;/*uncheck onload*/

	$('emailmoretimesfield').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('emailmoretimesfield1').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('emailmoretimes').observe('click', function(ownevent){
		if ($('emailmoretimes').checked == true){
			//alert("checked");
			$('emailmoretimesfield').removeAttribute("disabled", "disabled" );
			$('emailmoretimesfield1').removeAttribute("disabled", "disabled" );/*disabled onload*/
		}else{
			//alert("unchecked");
			$('emailmoretimesfield').clear();/*clear at time of disabled*/
			$('emailmoretimesfield').writeAttribute("disabled", "disabled" );
			$('emailmoretimesfield1').clear();/*clear at time of disabled*/
			$('emailmoretimesfield1').writeAttribute("disabled", "disabled" );
		}

		});

	}

	if($('raisemorethan') != null){
	$('raisemorethan').checked=false;/*uncheck onload*/

	$('raisemorethanfield').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('raisemorethan').observe('click', function(ownevent){
		if ($('raisemorethan').checked == true){
			//alert("checked");
			$('raisemorethanfield').removeAttribute("disabled", "disabled" );
		}else{
			//alert("unchecked");
			$('raisemorethanfield').clear();/*clear at time of disabled*/
			$('raisemorethanfield').writeAttribute("disabled", "disabled" );
		}

		});

	}

	if($('morethantotal') != null){
	$('morethantotal').checked=false;/*uncheck onload*/

	$('morethantotalfield').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('morethantotalfield1').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('morethantotal').observe('click', function(ownevent){
		if ($('morethantotal').checked == true){
			//alert("checked");
			$('morethantotalfield').removeAttribute("disabled", "disabled" );
			$('morethantotalfield1').removeAttribute("disabled", "disabled" );/*disabled onload*/
		}else{
			//alert("unchecked");
			$('morethantotalfield').clear();/*clear at time of disabled*/
			$('morethantotalfield').writeAttribute("disabled", "disabled" );
			$('morethantotalfield1').clear();/*clear at time of disabled*/
			$('morethantotalfield1').writeAttribute("disabled", "disabled" );
		}

		});

	}

	if($('moreinlastmonth') != null){
	$('moreinlastmonth').checked=false;/*uncheck onload*/

	$('moreinlastmonthfield').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('moreinlastmonthfield1').writeAttribute("disabled", "disabled" );/*disabled onload*/
	$('moreinlastmonth').observe('click', function(ownevent){
		if ($('moreinlastmonth').checked == true){
			//alert("checked");
			$('moreinlastmonthfield').removeAttribute("disabled", "disabled" );
			$('moreinlastmonthfield1').removeAttribute("disabled", "disabled" );/*disabled onload*/
		}else{
			//alert("unchecked");
			$('moreinlastmonthfield').clear();/*clear at time of disabled*/
			$('moreinlastmonthfield').writeAttribute("disabled", "disabled" );
			$('moreinlastmonthfield1').clear();/*clear at time of disabled*/
			$('moreinlastmonthfield1').writeAttribute("disabled", "disabled" );
		}

		});

	}
	/* script for ALT0100 Screens ends here*/

	if($('howmanyplaces') != null){
		$('howmanyplaces').writeAttribute("disabled","disabled");
	}
	$$('[name="limitedplaces"]').each(function(elmt){
		elmt.checked=false;
		elmt.observe('click',function(){
				if($('limitedplacesYes').checked == true) {
					$('howmanyplaces').removeAttribute("disabled","disabled");
				}else {
					$('howmanyplaces').clear();
					$('howmanyplaces').writeAttribute("disabled","disabled");
				}
		});
	});
	/**End EVT0040A */

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

	if($('ondate') != null){
	$('ondate').checked=false;
	$('ondatefield').clear();
	$('ondatefield1').clear();
	$('ondatefield2').clear();
	$('ondatefield').writeAttribute("disabled","disabled");
	$('ondatefield1').writeAttribute("disabled","disabled");
	$('ondatefield2').writeAttribute("disabled","disabled");
	$$('[name="dateofevent"]').each(
		function(elmt) {
			elmt.observe('click',function(){
				if($('ondate').checked == true) {
					$('ondatefield').removeAttribute("disabled","disabled");
					$('ondatefield1').removeAttribute("disabled","disabled");
					$('ondatefield2').removeAttribute("disabled","disabled");
				}else {
					$('ondatefield').clear();
					$('ondatefield1').clear();
					$('ondatefield2').clear();
					$('ondatefield').writeAttribute("disabled","disabled");
					$('ondatefield1').writeAttribute("disabled","disabled");
					$('ondatefield2').writeAttribute("disabled","disabled");
				}
			});
		});
	}

	if($$('[name="deadline"]')!= null){
		$$('[name="deadline"]').each(function(elmt){
		elmt.checked=false;
		});

		$$('[name="deadline"]').each(function(elmt){
		elmt.observe('click',function(){
				if($('deadlineYes').checked == true) {
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
		$('date').writeAttribute("disabled","disabled");
		$('month').writeAttribute("disabled","disabled");
		$('year').writeAttribute("disabled","disabled");
		}

	}

});

/*this script is written for EVT0020A by Dayanand on 24/12/08 [End]*/


/* script for tooltip starts here */

// global variable for error popups

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
/*tooltip script Ends here */


