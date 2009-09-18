<jsp:directive.include file="includes/vmgiFrameTop.jsp" />
<div id="content">
 	<div id="vm-signin" class="sec0020">
          <h3 class="blue-chevrons">
		  <strong>
          <spring:message code="vmg.external.screen.welcome.label.vm.header" /></strong>
		  </h3>
	          <form:form method="post" id="fm1" commandName="${commandName}" autocomplete="off">
		          <input type="hidden" name="lt" value="${flowExecutionKey}" />
		          <input type="hidden" name="_eventId" value="submit" />
		          <input type="hidden" id="brandReference" name="brandReference" value="Virgin Money Giving" />
				<form:errors id="error" element="div" cssClass="errorMessage" />
		          <p>
		            <label for="username">
		            	<spring:message code="vmg.external.screen.welcome.label.netid" />
		            </label>
		             <c:if test="${not empty sessionScope.openIdLocalId}">
                     	<strong>${sessionScope.openIdLocalId}</strong>
                     	<input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" size="30"/>
	                 </c:if>
	                 <c:if test="${empty sessionScope.openIdLocalId}">
	                    <form:input cssClass="required" cssErrorClass="error" id="username"  tabindex="1"  path="username" size="30"/>
	                 </c:if>
					 <spring:hasBindErrors name="credentials">
							<c:forEach var="errMsgObj" items="${errors.allErrors}">
								<c:if test="${errMsgObj.class.name == 'org.springframework.validation.FieldError' && errMsgObj.field == 'username'}">
										<img src="img/misc/nobutton.jpg" alt="" title=""/>
									</s:if>
								</c:if>
							</c:forEach>
						</spring:hasBindErrors>
		          </p>
				  <spring:hasBindErrors name="credentials">
						<c:forEach var="errMsgObj" items="${errors.allErrors}">
							<c:if test="${errMsgObj.class.name == 'org.springframework.validation.FieldError' && errMsgObj.field == 'username'}">
								<p>
									<span class="label-spacer">&nbsp;</span><form:errors path="username" cssClass="errorMessage"/>
								</p>
							</c:if>
						</c:forEach>
					</spring:hasBindErrors>
		          <p>

		            <label for="dobDay"> <spring:message code="vmg.external.screen.welcome.label.vm.dob" /><br />
		            <small style="font-weight:normal;"><spring:message code="vmg.external.screen.welcome.label.vm.dob.format.display" /></small></label>
		            <c:if test="${not empty sessionScope.openIdLocalIdDOB}">
                       <strong>${sessionScope.openIdLocalIdDOB}</strong>
                       <input type="hidden" id="dateOfBirth" name="dateOfBirth" value="${sessionScope.openIdLocalIdDOB}" />
                    </c:if>

                    <c:if test="${empty sessionScope.openIdLocalIdDOB}">
                       <form:input cssClass="required" cssErrorClass="error" id="dobDay"   size="2" maxlength="2" tabindex="2" path="dobDay"/>&nbsp;/&nbsp;
                       <form:input cssClass="required" cssErrorClass="error" id="dobMonth" size="2" maxlength="2" tabindex="3" path="dobMonth"/>&nbsp;/&nbsp;
                       <form:input cssClass="required" cssErrorClass="error" id="dobYear"  size="4" maxlength="4" tabindex="4" path="dobYear" />
                    </c:if>
		         <spring:hasBindErrors name="credentials">
							<c:forEach var="errMsgObj" items="${errors.allErrors}">
								<c:if test="${errMsgObj.class.name == 'org.springframework.validation.FieldError' && errMsgObj.field == 'dateOfBirth'}">
										<img src="img/misc/nobutton.jpg" alt="" title=""/>
									</s:if>
								</c:if>
							</c:forEach>
						</spring:hasBindErrors>
								</p>
<spring:hasBindErrors name="credentials">
						<c:forEach var="errMsgObj" items="${errors.allErrors}">
							<c:if test="${errMsgObj.class.name == 'org.springframework.validation.FieldError' && errMsgObj.field == 'dateOfBirth'}">
								<p>
									<span class="label-spacer">&nbsp;</span><form:errors path="dateOfBirth" cssClass="errorMessage"/>
								</p>
							</c:if>
						</c:forEach>
					</spring:hasBindErrors>
		          <p align="right">
		           <input class="vm-login" id="next-btn" name="submit" value="" tabindex="5" type="submit"  alt="Next" title="Next"/>
		          </p>
		       <p>&nbsp;</p>
     </form:form>
</div>
</div>
<jsp:directive.include file="includes/vmgiFrameBottom.jsp" />