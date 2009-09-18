
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
	    <style type="text/css">

	    body {
	    	font: 80% Verdana, Arial, Helvetica, sans-serif;
	    	margin: 0;
	    	padding: 0;
	    	text-align: center;
	    	color: #000000;
	    }
	    .oneColFixCtrHdr #container {
	    	width: 961px;
	    	background: #FFFFFF;
	    	margin: 0 auto;
	    	text-align: left;
	    }
	    .oneColFixCtrHdr #header {
	    	padding: 0;
	    }
	    .oneColFixCtrHdr #header h1 {
	    	margin: 0;
	    	padding: 0;
	    }
	    .oneColFixCtrHdr #mainContent {
	    	padding:0;
	    	background: #FFFFFF;
	    }
	    .oneColFixCtrHdr #footer {
	    	padding: 0;
	    }
	    .oneColFixCtrHdr #footer p {
	    	margin: 0;
	    	padding: 10px 0;
	    }
        .vm-next
        {
            background: url(<%=request.getContextPath()%>/img/branding/btn-sec-signin.gif) no-repeat;
            height: 50px;
            width: 150px;
            border: none;
            padding-top:3px;
	    }
        .vm-next-submit
        {
            background: url(<%=request.getContextPath()%>/img/branding/btn-save-changes.gif) no-repeat;
            height: 50px;
            width: 150px;
            border: none;
            padding-top:3px;
        }
        .vm-login
        {
            background: url(<%=request.getContextPath()%>/img/branding/btn-process-next.gif) no-repeat;
            display:inline;
            float:right;
            height: 50px;
            width: 150px;
            border: none;
            padding-top:3px;
        }
        #login{
	    	background-image:url(<%=request.getContextPath()%>/images/login-background.gif);
	    	background-position:left top;
	    	background-repeat:no-repeat;
	    	width:602px;
	    	height:365px;
	    	margin:0;
	    	padding-left:80px;
	    	padding-top:80px;
	    	margin-left:150px;
	    }

	    </style>
	    <script type="text/javascript" src="js/vm_common.js"></script>
	    <script type="text/javascript" src="js/password.js"></script>
		<script type="text/javascript" src="js/prototype/prototype.js"></script>
		<script type="text/javascript" src="js/prototype/scriptaculous.js"></script>
		<script type="text/javascript" src="js/vmg.js"></script>
        <!-- stylesheets -->
<link href="css/basic.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/styles.css" rel="stylesheet" type="text/css" media="screen, projector" />
<!-- mobile safari -->
<link href="css/ms/ms.css" rel="stylesheet" type="text/css" media="only screen and (max-device-width: 480px)" />
<link href="css/lightbox.css" rel="stylesheet" type="text/css" media="screen, projector" />
<link href="css/tooltip.css" rel="stylesheet" type="text/css" media="screen, projector" />

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
 