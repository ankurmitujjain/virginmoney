<#include "header.ftl" parse="true">

<p>Dear ${EDI0130}</p>

<p class="style1">We&#39;ve updated your address details as requested. Please check the details below and let us know 
if you need to make any changes.</p>

<p>Charity name&#58; ${EDI0100}</p>

<p>Registration address&#58;</p>
<p><#if EDI0410??>${EDI0410}</#if><br />
<#if EDI0420??>${EDI0420}</#if><br />
<#if EDI0430??>${EDI0430}</#if><br />
<#if EDI0440??>${EDI0440}</#if><br />
<#if EDI0450??>${EDI0450}</#if></p>

<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">

