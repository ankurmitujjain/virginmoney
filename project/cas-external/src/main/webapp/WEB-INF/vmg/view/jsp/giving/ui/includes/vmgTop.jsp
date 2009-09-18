
<%@ page import="com.virginmoneygiving.security.verify.custom.business.UtilityFunctions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
	    <title>Virgin Money Giving Authentication Service</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta name="viewport" content="width=1024, user-scalable=yes, minimum-scale=0, maximum-scale=5" />
	    <meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="-1" />
		<meta http-equiv="Cache-Control" content="no-cache" />
	    <script type="text/javascript" src="js/vm_common.js"></script>
	    <script type="text/javascript" src="js/password.js"></script>
		<script type="text/javascript" src="js/prototype/prototype.js"></script>
		<script type="text/javascript" src="js/prototype/scriptaculous.js"></script>
		<script type="text/javascript" src="js/vmg.js"></script>
		<script type="text/javascript" src="js/utils/browser.js"></script>
		<script type="text/javascript" src="js/utils/site.js"></script>
<!-- stylesheets -->
<link href="css/basic.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/styles.css" rel="stylesheet" type="text/css" media="screen, projection" />
<!-- mobile safari -->
<link href="css/ms/ms.css" rel="stylesheet" type="text/css" media="only screen and (max-device-width: 480px)" />
<link href="css/lightbox.css" rel="stylesheet" type="text/css" media="screen, projection" />
<link href="css/tooltip.css" rel="stylesheet" type="text/css" media="screen, projection" />

<meta name="viewport" content="width=1024, user-scalable=yes, minimum-scale=0, maximum-scale=5" />
<!-- IE conditional comments -->
<!--[if IE 7]>
	<style type="text/css">@import url("css/ie/ie7.css");</style>
<![endif]-->
<!--[if IE 6]>
	<style type="text/css">@import url("css/ie/ie6.css");</style>
<![endif]-->
<!-- javascript -->
    </head>
  <body>
	<div id="container">
		<div id="header">
			<a href="/giving/-NOSSL">
			<img src="img/branding/img-logo.gif" width="252" height="85"
				alt="Virgin Money Giving" title="Virgin Money Giving" id="branding-logo" />
				</a>
			<!-- end #nav-access -->
			<div id="site-controls">
				<a href="/giving/-NOSSL" class="home">Home</a>
				<a href="<%=UtilityFunctions.getSecureServer()%>/charity-web/charity/postSignInRedirectControllerAction.action" class="signin">sign in</a>
			</div>
			<!-- end #site-controls -->
			<div class="container">
				 <ul id="nav-site">
                    <li class="about"><a href="/giving/about-us/index.jsp-NOSSL">About Virgin Money Giving</a></li>
                    <li class="raising"><a href="/giving/raise-more/index.jsp-NOSSL">Raising more</a></li>
                    <li class="your-news"><a href="/forums/">Our community</a></li>
                    <li class="guides"><a href="/giving/help-guides/index.jsp-NOSSL">Help guides</a></li>
                 </ul>
				<!-- end #nav-site -->
			</div>
		</div>
		<!-- end #header -->