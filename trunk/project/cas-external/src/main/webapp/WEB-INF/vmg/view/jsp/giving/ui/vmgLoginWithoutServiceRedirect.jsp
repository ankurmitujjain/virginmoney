<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.virginmoneygiving.security.verify.custom.business.UtilityFunctions" %>
<html>
<head>
<%
    response.sendRedirect(UtilityFunctions.getNoServiceDefinedRedirectionTarget());
%>
</head>
<body></body>
</html>