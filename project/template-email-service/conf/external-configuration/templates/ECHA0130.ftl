<#include "header.ftl" parse="true">

<p>Dear ${EDI0130}</p>

<p class="style1">We&#39;ve updated your address details as requested. Please check the details below and let us know 
if you need to make any changes.</p>

<p>Charity name&#58; ${EDI0100}</p>

<p>Administration address&#58;</p>
<p><#if EDI0230??>${EDI0230}</#if><br />
<#if EDI0240??>${EDI0240}</#if><br />
<#if EDI0250??>${EDI0250}</#if><br />
<#if EDI0260??>${EDI0260}</#if><br />
<#if EDI0270??>${EDI0270}</#if></p>

<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">
