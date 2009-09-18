
function validateAlertUsers(checkboxObject) {
	if(checkboxObject.checked == false) { 	
		if(document.getElementById("all") != null) {
			document.getElementById("all").checked = false;
		}
	}
}

function validateDonationAlert(checkboxObject) {
	//alert(checkboxObject);alert(checkboxObject.value);	alert(checkboxObject.name);	alert(checkboxObject.checked);	alert(checkboxObject.id);
	
	if(checkboxObject.id == 'all') {
		var checkBoxList = document.getElementsByName("charityGlobalAlertsDVO.adminUsers");
		if(checkboxObject.checked == true) { 
			for (var i = 0; i < checkBoxList.length; i++) {
				if(checkBoxList[i].id != 'all'){
					checkBoxList[i].checked = true;
				}
			} 
		}
		else {
			for (var j = 0; j < checkBoxList.length; j++) {
				if(checkBoxList[j].id  != 'all'){
					checkBoxList[j].checked = false;
				}
			} 
		} //End of Else
	}

	if(checkboxObject.id == 'chkDonationAlert1') {
		if(checkboxObject.checked == true) {
			document.getElementById("txtDonationAlert1Amount").disabled = false;
		}
		else {
			document.getElementById("txtDonationAlert1Amount").value = "";			
			document.getElementById("txtDonationAlert1Amount").disabled = true;
		}
	}
	 
	if(checkboxObject.id == 'chkDonationAlert2') {
		if(checkboxObject.checked == true) { 
			document.getElementById("txtDonationAlert2Amount").disabled = false;
			document.getElementById("txtDonationAlert2Duration").disabled = false;
		}
		else {
			document.getElementById("txtDonationAlert2Amount").value="";			
			document.getElementById("txtDonationAlert2Amount").disabled = true;
			document.getElementById("txtDonationAlert2Duration").value ="";
			document.getElementById("txtDonationAlert2Duration").disabled = true;
		}
	}

	if(checkboxObject.id == 'chkDonationAlert3') {
		if(checkboxObject.checked == true) { 
			document.getElementById("txtDonationAlert3Frequency").disabled = false;
			document.getElementById("txtDonationAlert3Duration").disabled = false;
		}
		else { 
			document.getElementById("txtDonationAlert3Frequency").value="";
			document.getElementById("txtDonationAlert3Frequency").disabled = true;
			document.getElementById("txtDonationAlert3Duration").value ="";
			document.getElementById("txtDonationAlert3Duration").disabled = true;
		}
	}

	if(checkboxObject.id == 'chkFundraiserAlert2') {
		if(checkboxObject.checked == true) { 
			document.getElementById("txtFundraiserAlert2Amount").disabled = false;
		}
		else { 
			document.getElementById("txtFundraiserAlert2Amount").value ="";
			document.getElementById("txtFundraiserAlert2Amount").disabled = true;
		}
	}

	if(checkboxObject.id == 'chkFundraiserAlert3') {
		if(checkboxObject.checked == true) { 
			document.getElementById("txtFundraiserAlert3Amount").disabled = false;
			document.getElementById("txtFundraiserAlert3Duration").disabled = false;
		}
		else { 
			document.getElementById("txtFundraiserAlert3Amount").value = "";
			document.getElementById("txtFundraiserAlert3Amount").disabled = true;
			document.getElementById("txtFundraiserAlert3Duration").value ="";
			document.getElementById("txtFundraiserAlert3Duration").disabled = true;
		}
	}

	if(checkboxObject.id == 'chkFundraiserAlert4') {
		if(checkboxObject.checked == true) { 
			document.getElementById("txtFundraiserAlert4Frequency").disabled = false;
			document.getElementById("txtFundraiserAlert4Duration").disabled = false;
		}
		else {
			document.getElementById("txtFundraiserAlert4Frequency").value = "";
			document.getElementById("txtFundraiserAlert4Frequency").disabled = true;
			document.getElementById("txtFundraiserAlert4Duration").value = "";
			document.getElementById("txtFundraiserAlert4Duration").disabled = true;
		}
	}

	if(document.getElementById("chkDonationAlert1").checked || document.getElementById("chkDonationAlert2").checked  
			|| document.getElementById("chkDonationAlert3").checked || document.getElementById("chkFundraiserAlert1").checked 
			|| document.getElementById("chkFundraiserAlert2").checked || document.getElementById("chkFundraiserAlert3").checked 
			|| document.getElementById("chkFundraiserAlert4").checked ||  document.getElementById("chkPaymentAlert").checked){
			
			var checkBoxListForCheck = document.getElementsByName("charityGlobalAlertsDVO.adminUsers");
			for (var k = 0; k < checkBoxListForCheck.length; k++) {
					checkBoxListForCheck[k].disabled = false;
			}
		} 
		else {
			var checkBoxListForCheck = document.getElementsByName("charityGlobalAlertsDVO.adminUsers");
			for (var m = 0; m < checkBoxListForCheck.length; m++) {
					checkBoxListForCheck[m].disabled = true;
					checkBoxListForCheck[m].checked = false;
			} 
		}
	
}