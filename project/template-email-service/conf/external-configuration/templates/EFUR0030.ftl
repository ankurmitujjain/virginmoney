
<#include "header.ftl" parse="true">

<p class="style">Dear ${EDI0070},</p>

<p>Good news &#45; your fundraising team, ${EDI0190}, is up and running. You can see your team&#39;s page by clicking on the link below&#58;</p>
<p><a href="${EDI0200}">${EDI0200}</a></p>
<#if displayAddedBlock??>

<p>Here&#39;s a list of your team members. We&#39;ve emailed them all to let them know they&#39;re in your team.</p>

<p>${addedMembersList}</p>

</#if>

<#if displayInvitedBlock??>
 <p>We&#39;ve also emailed the following fundraisers and asked them to register at Virgin Money Giving. <br>
 Once they&#39;ve done this, we&#39;ll add them to the team.</p>
 <p>${invitedMembersList}</p>

</#if>

<p>Thank you for using Virgin Money Giving. We really appreciate your help and hope your fundraising is a great success.</p>
<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">

