<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.virginmoneygiving.security.verify.custom.business.UtilityFunctions" %>
<%@ page import="com.virginmoneygiving.security.verify.custom.business.CASConstants" %>

<c:set var="username" value="${credentials.username}" scope="request" />
<c:set var="dob" value="${credentials.dateOfBirth}" scope="request" />

<% String separator = "";
    String referrer = request.getHeader("Referer");
    referrer = UtilityFunctions.resetUrl(referrer);
    // Cleanup XSS characters from URL string.
    referrer = UtilityFunctions.cleanXSS(referrer);
    String target = (String)request.getSession().getAttribute(CASConstants.REDIRECTION_TARGET_TYPE_REF);
    String redirectURL = UtilityFunctions.fetchRedirectionAction(target,((String)request.getSession().getAttribute("username")),((String)request.getSession().getAttribute("dob")));
    if (!UtilityFunctions.isStringEmpty(redirectURL)) {
        referrer = redirectURL;
    }
    if (referrer != null && referrer.length() > 0) {
        separator = (referrer.indexOf("?") > -1) ? "&" : "?"; %>
<html>
<head>
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Cache-Control" content="no-cache" />
<%
	response.sendRedirect(referrer);
 %>
</head>
<body></body>
</html>
<%
    } else {
        out.print("Error has occurred");
    } %>