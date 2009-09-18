<%
response.sendRedirect(UtilityFunctions.getSecureServer()+"/charity-web/charity/signInAction.action");
 %>
<jsp:directive.include file="includes/vmgTop.jsp" />
<%@ page import="com.virginmoneygiving.security.verify.custom.business.UtilityFunctions" %>
<%@ page import="com.virginmoneygiving.security.verify.custom.business.CASConstants" %>

  <div id="content" class="template-d">
    <div class="content-block">
      <div class="vm-passwordsecurity-details">
        <h2 class="s5">Your session has expired</h2>
        <div class="last-minheight">
          <p>We've closed your secure session because you haven't done anything on the Virgin Money Giving
            site for a while. This is done as a security precaution.</p>
          <p>If you want to sign in again, click on the button below.</p>
        </div>
        <div class="continue-block">
			<a href="<%=UtilityFunctions.getSecureServer()%>/charity-web/charity/signInAction.action" >
			<img src="img/branding/btn-sec-signin.gif" alt="Sign in" title="Sign in" />
			</a>
        </div>
      </div>
    </div>
  </div>
<jsp:directive.include file="includes/vmgBottom.jsp" />
