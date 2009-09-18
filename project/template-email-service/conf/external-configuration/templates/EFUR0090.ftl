<#include "header.ftl" parse="true">

<p class="style1"><p>Dear ${EDI0070},</p>

<p class="style1">Great news! Your fundraising network, ${EDI0220}, is up and running.</p> 

<p>Here&#39;s a list of fundraisers who belong to your network &#45; we&#39;ve emailed them all to let them know.</p>

<#if displayAddedBlock??>
    </p>${addedMembersList}</p>
</#if>

<p>Thank you for using Virgin Money Giving. We really appreciate your help and hope your fundraising
is a great success.</p>


<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">

