<#include "header.ftl" parse="true">
<p class="style1">We&#39;re really pleased to let you know your charity has received a large donation through Virgin Money Giving&#58;</p>
<table style="font-size:12px;color:#000000;font-family:verdana;" cellspacing="0" cellpadding="0" border="0" >
<tr>
<td style="width:240px;"><strong>Donor</strong></td> <td>${EDI0070}<#if EDI0210??>${EDI0210}</#if></td>
</tr>

<#if EDI0560??>
<tr>
<td style="width:240px;">Virgin Money Giving reference</td><td>${EDI0560}</td>
</tr>
</#if>
<#if EDI0620??>
<tr>
<td style="width:240px;">Your reference</td><td>${EDI0620}</td>
</tr>
</#if>
</table>
<p>They&#39;ve made a single donation of more than <strong>${currency}${EDI0630}</strong> &#45; see your charity&#39;s reports for full details.</p>

<p>Thank you for using Virgin Money Giving.</p>

<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">


