<jsp:directive.include file="includes/vmgTop.jsp" />

  <div id="mainContent">
     <div id="login">
        <h2><spring:message code="vmg.external.account.locked.header" /></h2>
        <p><spring:message code="vmg.external.account.locked.text.line1" /></p>
        <p>
           <spring:message code="vmg.external.account.locked.text.line2" /><br/>
           <spring:message code="vmg.external.account.locked.text.line3" />
        </p>
      </div>
  </div>
<jsp:directive.include file="includes/vmgBottom.jsp"/>
<%session.invalidate();%>