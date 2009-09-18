<jsp:directive.include file="includes/vmgiFrameTop.jsp" />

		<div id="content" class="template-d">
			<div>
				<div id="vm-signin">
					<div class="sec0020">
						<div>
							<form:form method="post"  commandName="${commandName}" cssStyle="padding-bottom:10px!important;" autocomplete="off">
								<h3 class="blue-chevrons">
									My Password
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


										<p><strong>Your temporary password has now expired</strong></p>
										<p><strong>Please change your password</strong></p>

                                      <p>
									 <label for="password">Password *</label>
								<form:password cssClass="required" cssErrorClass="error" maxlength="20"
											id="password"  tabindex="1" 
											path="password" />

								<a href="##tooltip-form-help-1" onmouseover="return open_error_popup(event, 'tooltip-form-help-1');"
															onmouseout="return close_error_popup('tooltip-form-help-1')";
															onclick="return close_error_popup('tooltip-form-help-1');" tabindex="2">

					 <img src="img/branding/img-help-icon.gif" alt="Help" title="Help" class="next"
						width="26" height="26" />
					 </a>
								</p>


				<p class="pw-info"><span class="label-spacer" style="height:15px;">&nbsp;</span>This needs to be at least 6 characters long and include at least one <br />number or special character.</p>
				<div class="pw-strength">
				</div>
				<div class="clearer"></div>
					<p>
								 <label for="confirmPassword">Confirm Password*</label>
			          	<form:password cssClass="required" cssErrorClass="error" id="confirmPassword" tabindex="3"
			          	 path="confirmPassword"  maxlength="20" />
					  </p>


					  <div class="continue-block"><input type="image" name="submit" tabindex="4" title="Save changes" alt="Save changes" class="submit" src="img/branding/btn-save-changes.gif"/></div>

							</form:form>

						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
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
<jsp:directive.include file="includes/vmgiFrameBottom.jsp" />
