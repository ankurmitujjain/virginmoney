<#include "header.ftl" parse="true">
<p class="style1">We&#39;re really pleased to let you know a ${fundraiserOrTeamLabel} who supports your 
charity has raised over ${currency}${EDI0570} within the last ${EDI0590} months through Virgin Money Giving&#58;</p>

<table style="font-size:12px;color:#000000;font-family:verdana;" cellspacing="0" cellpadding="0" border="0">
<tr><td style="width:240px;"><strong>Fundraiser</strong></td><td>${EDI0070} ${EDI0210}</td></tr>
<tr><td style="width:240px;">Virgin Money Giving reference</td><td>${EDI0560}</td></tr>
</table>


<#if teamDetails??>
<table style="font-size:12px;color:#000000;font-family:verdana;" cellspacing="0" cellpadding="0" border="0">
<tr><td style="width:210px;"><strong>Team name</strong></td><td>${EDI0190}</td></tr>
</table>
<p>Team members&#58;</p>
${teamDetails}
</#if>

<p>Check out their Virgin Money Giving page <a href="${EDI0200}">here</a></p>

<p>Thank you for using Virgin Money Giving.</p>

<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">
