
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head></head>
<body>
<h3>JMS</h3>

<form method="POST" action="glisResponseTestJMS.action" >
	Message: 
	<s:textarea name="text" />
	 <s:submit value="Send!"/>
</form>
</body>
</html>
