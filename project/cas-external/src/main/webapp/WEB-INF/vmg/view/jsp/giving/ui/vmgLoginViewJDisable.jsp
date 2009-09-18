
<jsp:directive.include file="includes/vmgTop.jsp" />
		<div id="content" class="template-d">
			<div>
				<div id="vm-signin" class="fm1">
					<div class="sec0020">
						<div>
							<form:form method="post" commandName="${commandName}"
								cssStyle="padding-bottom:10px!important;" autocomplete="off">
								<h3 class="blue-chevrons"> <strong>
									<spring:message
										code="vmg.external.screen.welcome.label.vm.header" /> </strong>
								</h3>
								<input type="hidden" name="lt" value="${flowExecutionKey}" />
								<input type="hidden" name="_eventId" value="submit" />
								<input type="hidden" id="brandReference" name="brandReference"
									value="Virgin Money Giving" />
									<form:errors id="error" element="div" cssClass="errorMessage" />
		
								<p>
									<label for="username">
										<spring:message code="vmg.external.screen.welcome.label.netid" />
									</label>
									<c:if test="${not empty sessionScope.openIdLocalId}">
										<strong>${sessionScope.openIdLocalId}</strong>
										<input type="hidden" id="username" name="username" maxlength="100" size="30"
											value="${sessionScope.openIdLocalId}"  />
									</c:if>
									<c:if test="${empty sessionScope.openIdLocalId}">
										<form:input cssClass="required" cssErrorClass="error" maxlength="100" size="30"
											id="username"  tabindex="1"
											path="username" />
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
									<label for="dobDay">
										<spring:message
											code="vmg.external.screen.welcome.label.vm.dob" />
										<br />
										<small style="font-weight: normal;"><spring:message
												code="vmg.external.screen.welcome.label.vm.dob.format.display" />
										</small>
									</label>
									<c:if test="${not empty sessionScope.openIdLocalIdDOB}">
										<strong>${sessionScope.openIdLocalIdDOB}</strong>
										<input type="hidden" id="dateOfBirth" name="dateOfBirth"
											value="${sessionScope.openIdLocalIdDOB}" />
									</c:if>

									<c:if test="${empty sessionScope.openIdLocalIdDOB}">
										<form:input cssClass="required" cssErrorClass="error"
											id="dobDay" size="2" maxlength="2" tabindex="2" path="dobDay"/>&nbsp;/&nbsp;
                       <form:input cssClass="required"
											cssErrorClass="error" id="dobMonth" size="2" maxlength="2"
											tabindex="3" path="dobMonth" />&nbsp;/&nbsp;
                       <form:input cssClass="required"
											cssErrorClass="error" id="dobYear" size="4" maxlength="4"
											tabindex="4" path="dobYear"/>
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
								<div class="continue-block" style="margin-top:155px;">
									<input class="vm-login" id="next-btn" name="submit" value=""
										tabindex="5" type="submit" alt="Next" title="Next" />
										<br />
										<br />
								 </div>
							</form:form>

						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<p>
				<a href="#" onclick="history.go(-1)"><img src="img/branding/btn-previous.gif" width="30"
						height="30" alt="" title="" class="previous" onclick="history.go(-1)" />Back</a>
			</p>
		</div>
<jsp:directive.include file="includes/vmgBottom.jsp" />



<%-- fix for when logon is initiated within a lightbox --%>
<script type="text/javascript">
    <%
     String host="https://" + request.getHeader("host");
     String refr = (String)request.getAttribute("javax.servlet.forward.query_string");
     String svlt = (String)request.getAttribute("javax.servlet.forward.request_uri");
     String redirect = host + svlt + "?" + refr;
     String baseAddress = host + svlt;

    %>
    var redirectURL = "<%= redirect %>";
    var baseAddressURL = "<%=baseAddress%>";
    var currentUrl  = new String(window.location.href);
    <%--alert("redirectURL JDisable =" + redirectURL + "\n\nbaseAddressURL=" + baseAddressURL +  --%>
    <%--"\n\ncurrentURL=" + window.location.href+ "\n\nparentURL=" + parent.window.location.href +  --%>
    <%--"\n\nindexof=" + currentUrl.indexOf(baseAddressURL,0)); --%>
    if (currentUrl.indexOf(baseAddressURL,0) < 0) {
        window.location.href = redirectURL;
        <%--alert("done : " + redirectURL); --%>
    }
</script>
