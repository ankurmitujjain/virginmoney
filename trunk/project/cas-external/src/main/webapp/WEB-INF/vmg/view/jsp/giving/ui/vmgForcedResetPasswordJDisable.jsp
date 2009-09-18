<jsp:directive.include file="includes/vmgTop.jsp" />
		<div id="content" class="template-d">			
				<div id="vm-signin" class="fm1">				
				<form:form method="post"  commandName="${commandName}" autocomplete="off">
					<div class="sec0020 last-minheight">								
								<h3 class="blue-chevrons">
									My password
								</h3>
								<input type="hidden" name="lt" value="${flowExecutionKey}" />
								<input type="hidden" name="_eventId" value="submit" />
								<input type="hidden" id="brandReference" name="brandReference"
									value="Virgin Money Giving" />
								<spring:hasBindErrors name="credentials">
									<div id="error">
										<form:errors path="*" cssClass="errorMessage" element="ul" />
									</div>
								</spring:hasBindErrors>


										<p>Your temporary password has now expired</p>
										<p>Please create a new password for your account, then click ‘next'.</p>

                                      <p>
									 <label for="password">Password*</label>
								<form:password cssClass="required" cssErrorClass="error" maxlength="20"
											id="password"  tabindex="1"  
											path="password"/>

								<a href="##tooltip-form-help-1" onmouseover="return open_error_popup(event, 'tooltip-form-help-1');"
															onmouseout="return close_error_popup('tooltip-form-help-1')";
															onclick="return close_error_popup('tooltip-form-help-1');" tabindex="2">

					 <img src="img/branding/img-help-icon.gif" alt="Help" title="Help" class="next"
						width="26" height="26" />
					 </a>
								</p>


				<p class="pw-info"><span class="label-spacer" style="height:15px;">&nbsp;</span><small>This needs to be at least 6 characters long and include at least one <br />number or special character.</small></p>
				<div class="pw-strength"></div>
				<div class="clearer"></div>
					<p>
						<label for="confirmPassword">Confirm password*</label>
			          	<form:password cssClass="required" cssErrorClass="error" id="confirmPassword" tabindex="3"
			          	 path="confirmPassword"  maxlength="20" />
					  </p>					  					
					</div>
					<div class="continue-block"><input type="image" name="submit" tabindex="4" title="Next" alt="Next" class="submit" src="img/branding/btn-process-next.gif"/></div>

					</form:form>	
				</div>
				<div class="clear"></div>			
			<p>
				<a href="#" onclick="history.go(-1)" tabindex="5"><img src="img/branding/btn-previous.gif" width="30"
						height="30" title="" alt="" class="previous" onclick="history.go(-1)" />Back</a>
			</p>
		</div>
	
	<div id="tooltip-form-help-1" class="open_error_popup">
        <div class="header"></div>
        <div class="content"> <strong>Password</strong><br />
          So other people can't guess your password, please use a combination of letters, numbers and special characters (for example &pound;%*=).<br />
          <br />
          Avoid using your name or date of birth, or an obvious choice like 'password' or letters and numbers in the order they appear on your keyboard (such as , 'qwerty' or 'A12345').
		</div>
		<div class="footer"></div>
	</div>
<jsp:directive.include file="includes/vmgBottom.jsp" />

