<#include "header.ftl" parse="true">

<p>A charity has reported that their Virgin Money Giving page hasn&#39;t appeared on the website.<p>

<p>Please investigate this issue and take any appropriate action.
Please record any actions in the box at the bottom of this email.</p>

<table style="font-size:12px;color:#000000;font-family:verdana;line-height:150%" width="617" cellspacing="0" cellpadding="0">
<tr><td style="width:200px;">Date reported</td><td><#if EDI0490??>${EDI0490}</#if></td></tr>
<tr><td style="width:200px;">Time reported</td><td><#if EDI0500??>${EDI0500}</#if></td></tr>
<tr><td></td><td></td></tr>
<tr><td style="width:200px;">Charity</td><td><#if EDI0100??>${EDI0100}</#if></td></tr>
<tr><td style="width:200px;">Charity number</td><td><#if EDI0510??>${EDI0510}</#if></td></tr>
<tr><td style="width:200px;">Charity address</td><td><#if EDI0410??>${EDI0410}</#if></td></tr>
<tr><td></td>  <td><#if EDI0420??>${EDI0420}</#if></td></tr>
<tr><td></td>  <td><#if EDI0430??>${EDI0430}</#if></td></tr>
<tr><td></td>  <td><#if EDI0440??>${EDI0440}</#if></td></tr>
<tr><td></td>  <td><#if EDI0450??>${EDI0450}</#if></td></tr>
</table>

<p><strong>Action taken&#58;</strong<p>

<#include "footer-1.ftl" parse="true">
