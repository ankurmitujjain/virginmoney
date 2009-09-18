
<#include "header.ftl" parse="true">

<p class="style1">Dear ${EDI0070}</p>
<p>You&#39;ve made some changes to your fundraising team, ${EDI0190} &#45; you can see your team&#39;s
page by clicking the link below&#58;</p>

<p><a href="${EDI0200}">${EDI0200}</a></p>

<#if displayAddedBlock??>
	
	<p>Here&#39;s a list of your team members&#58;</p>
	<p>${addedMembersList}</p>
</#if>

<#if displayRemovedBlock??>
	
	<p>And here&#39;s a list of people who are no longer in the team&#58;</p>
	<p>${removedMembersList}</p>
</#if>

<p>We&#39;ve emailed everyone who&#39;s been added to or has left the team so you don&#39;t need to worry
about updating them yourself.</p>


<#if displayInvitedBlock??>

	<p>We&#39;ve also emailed the following fundraisers and asked them to register at Virgin Money Giving.<br> 
	Once they&#39;ve done this, we&#39;ll add them to your team. </p>
	<p>${invitedMembersList}</p>
</#if>

<p>Thank you for using Virgin Money Giving. We really appreciate your help and hope your fundraising is a great success.</p>

<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">

