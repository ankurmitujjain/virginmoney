
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head></head>
<body>
<h3>JMS</h3>

<form method="POST" action="testJMS.action" >
	Message: 
	<s:textfield name="jobName" />
	 <s:submit value="Send!"/>
</form>
</body>
</html>
