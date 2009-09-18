<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.virginmoneygiving.security.verify.custom.business.UtilityFunctions" %>
<%@ page import="com.virginmoneygiving.security.verify.custom.business.CASConstants" %>

<c:set var="username" value="${credentials.username}" scope="request" />
<c:set var="dob" value="${credentials.dateOfBirth}" scope="request" />

<% String separator = "";
    String referrer = request.getHeader("Referer");
    String defaultURL = "http://uk.virginmoney.com";
    //out.println("\nRedirector");
    //out.println("\nReferrer 1: "+referrer);
    referrer = UtilityFunctions.resetUrl(referrer);
    referrer = UtilityFunctions.cleanXSS(referrer);
    UtilityFunctions.showRequest(request);
    String target = (String)request.getSession().getAttribute(CASConstants.REDIRECTION_TARGET_TYPE_REF);
    String redirectURL = UtilityFunctions.fetchRedirectionAction(target,((String)request.getSession().getAttribute("username")),((String)request.getSession().getAttribute("dob")));
    //out.println("\nTarget: " + target );
    if (!UtilityFunctions.isStringEmpty(redirectURL)) {
        referrer = redirectURL;
    }
    //out.println("\nLT: ${flowExecutionKey}");
    if (referrer == null || referrer.length() < 1) {
        referrer = request.getParameter("login-at");
        if (referrer == null || referrer.length() < 1) {
            referrer = defaultURL;
        }
        //out.print("Referrer 3: "+referrer);
    }
    //out.println("Referrer 3: "+referrer);
    if (referrer != null && referrer.length() > 0) {
        separator = (referrer.indexOf("?") > -1) ? "&" : "?"; %>
<html>
<head>
    <script>
        var redirectURL = "<%= referrer + separator %>lt=${flowExecutionKey}";
        //alert('RedirectURL-0 = ' + redirectURL);
        <%--
        <spring:hasBindErrors name="credentials">
        redirectURL += '&error_message=' + encodeURIComponent('
        <c:forEach var="error" items="${errors.allErrors}">
        <spring:message code="${error.code}" text="${error.defaultMessage}" />
        </c:forEach>
        ');
        </spring:hasBindErrors>
        --%>
        //alert('RedirectURL = ' + redirectURL);
        parent.window.location.href=redirectURL;
        //window.location.href = redirectURL;
    </script>
</head>
<body></body>
</html>
<%
    } else {
         out.print("Error has occurred");
    } %>