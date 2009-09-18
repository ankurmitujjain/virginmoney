<#include "header.ftl" parse="true">

<p class="style1">Dear ${EDI0070}</p>

<p>We&#39;ve made the changes to your fundraising network, ${EDI0220}.</p>

<#if displayAddedBlock??>
<p>Here&#39;s a list of your fundraisers&#58;

    <p>${addedMembersList}</p>
</#if>

<#if displayRemovedBlock??>

<p>And here&#39;s a list of fundraisers who are no longer in your network&#58;</p>

    <p>${removedMembersList}</p>
</#if>

<p>We&#39;ve emailed everyone who&#39;s been added to or has left the network so you don&#39;t need to worry about updating them yourself.</p>

<p>Thank you for using Virgin Money Giving. We really appreciate your help and hope your fundraising is a great success.</p>

<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">

