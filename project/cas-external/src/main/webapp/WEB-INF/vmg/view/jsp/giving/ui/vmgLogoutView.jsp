
<%@page import="com.virginmoneygiving.security.verify.custom.business.UtilityFunctions"%><jsp:directive.include file="includes/vmgTop.jsp" />
<%
response.sendRedirect(UtilityFunctions.getBaseServer()+"/giving/");
 %>
  <div id="mainContent">
     <div id="login">
        <h2><spring:message code="vmg.external.logout.header" /></h2>
        <p><spring:message code="vmg.external.logout.success" /></p><br/>
        <p><spring:message code="vmg.external.logout.security" /></p>
        <p>&nbsp;</p>
      </div>
  </div>
<jsp:directive.include file="includes/vmgBottom.jsp" />
