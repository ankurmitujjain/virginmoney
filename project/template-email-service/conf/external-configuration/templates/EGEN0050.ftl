<#include "header.ftl" parse="true">

<p class="style1">Dear <#if EDI0130??>${EDI0130}</#if><#if EDI0070??>${EDI0070}</#if>,</p>

<p>We&#39;ve created a temporary password for you. Please use it to sign into your Virgin Money Giving account.
You&#39;ll then be asked to choose a password of your own.</p>

<p><strong>Your temporary password&#58;</strong> ${EDI0360}</p>
<p>(Please note when you type in this password you&#39;ll need to use capital and lower case letters 
as shown here.)</p>

<p>Thank you for using Virgin Money Giving.</p>

<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">
 
