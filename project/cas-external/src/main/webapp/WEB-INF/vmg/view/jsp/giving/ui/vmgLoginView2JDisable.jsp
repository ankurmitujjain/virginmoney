<jsp:directive.include file="includes/vmgTop.jsp" />
<%@ page import="com.virginmoneygiving.security.verify.custom.business.UtilityFunctions" %>
<!-- end #header -->
		<div id="content" class="template-d">

				<div id="vm-signin" class="fm1">
	   <div class="sec0020">
	    <h3 class="blue-chevrons">
	    <spring:message code="vmg.external.screen.welcome.label.vm.step2.header"/></h3>
		 	<form:form method="post" commandName="${commandName}" htmlEscape="true" autocomplete="off">
		           <input type="hidden" name="lt" value="${flowExecutionKey}"/>
		           <input type="hidden" name="_eventId" value="submit"/>
		            <p>
			        <spring:message code="vmg.external.screen.welcome.label.vm.step2.para1" />
			        </p>
			        <p><span class="passwordDetailsContainer">
			          <label for="firstCharacter" class="radioLabel lineheight">
			          <spring:message code="vmg.external.password.characters.message.character"
                                       arguments="${credentials.formattedPosition1}"/>
                      </label>
			          </span>
			          	<form:password tabindex="1" cssClass="required" cssErrorClass="error" id="firstCharacter" size="1" maxlength="1" path="passwordCharacter1" />
			       <spring:hasBindErrors name="credentials">
							<c:forEach var="errMsgObj" items="${errors.allErrors}">
								<c:if test="${errMsgObj.class.name == 'org.springframework.validation.FieldError' && errMsgObj.field == 'passwordCharacter1'}">
										<img src="img/misc/nobutton.jpg" alt="" title=""/>
									</s:if>
								</c:if>
							</c:forEach>
						</spring:hasBindErrors>
						
								</p>
								<spring:hasBindErrors name="credentials">
						<c:forEach var="errMsgObj" items="${errors.allErrors}">
							<c:if test="${errMsgObj.class.name == 'org.springframework.validation.FieldError' && errMsgObj.field == 'passwordCharacter1'}">
								<p>
									<form:errors path="passwordCharacter1" cssClass="errorMessage"/>
								</p>
							</c:if>
						</c:forEach>
					</spring:hasBindErrors>

			        <p><span class="passwordDetailsContainer">
			          <label for="secondCharacter" class="radioLabel lineheight">
			          	 <spring:message code="vmg.external.password.characters.message.character"
                                       arguments="${credentials.formattedPosition2}"/>
					  </label>
			          </span>
			          <form:password cssClass="required" cssErrorClass="error" id="secondCharacter" size="1" maxlength="1"   tabindex="2" path="passwordCharacter2"/>
			        <spring:hasBindErrors name="credentials">
							<c:forEach var="errMsgObj" items="${errors.allErrors}">
								<c:if test="${errMsgObj.class.name == 'org.springframework.validation.FieldError' && errMsgObj.field == 'passwordCharacter2'}">
										<img src="img/misc/nobutton.jpg" alt="" title=""/>
									</s:if>
								</c:if>
							</c:forEach>
						</spring:hasBindErrors>
						
								</p>
								<spring:hasBindErrors name="credentials">
						<c:forEach var="errMsgObj" items="${errors.allErrors}">
							<c:if test="${errMsgObj.class.name == 'org.springframework.validation.FieldError' && errMsgObj.field == 'passwordCharacter2'}">
								<p>
									<form:errors path="passwordCharacter2" cssClass="errorMessage"/>
								</p>
							</c:if>
						</c:forEach>
					</spring:hasBindErrors>

	</div>
                               <div class="continue-block"  style="margin-top:137px;">
			          	<input class="vm-login" id="login-btn" name="submit" value=""  tabindex="3" type="submit" alt="Next" title="Next"/>
			          	<br />
			          	<br />
			        </div>
			</form:form>

			  <div class="clear"></div>
			</div>
			<form action="<%=UtilityFunctions.getSecureServer()%>/fundraiser-web/fundraiser/forgottenPasswordAction.action" method="post">
				<input type="hidden" id="email" name="email" value="${credentials.username}"/>
				<input type="hidden" id="dob" name="dob" value="${credentials.dateOfBirth}"/>
				<p>	
					<a href="#" onclick="history.go(-1)"><img src="img/branding/btn-previous.gif" width="30" title="" height="30" alt=""  class="previous" onclick="history.go(-1)"/>Back</a>
					<input type="image" class="passbtmlnkwthoutleftcol" tabindex="5" src="img/branding/btn-forgotten-your-password.jpg" /> 				
				</p>
			</form>
								
				
			
		
		</div>
		<!-- end #content -->
<jsp:directive.include file="includes/vmgBottom.jsp" />