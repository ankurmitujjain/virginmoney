<#include "header.ftl" parse="true">

<p class="style1">A user has reported a post on Virgin Money Giving which they feel is offensive or inappropriate.</p>

<p>Please investigate this issue and take any appropriate action. If the post breaks the Virgin Money Giving terms and conditions, or is offensive or libellous, it will need to be removed.</p>

<p>Please record any actions in the at the bottom of this email.</p>


<table style="font-size:12px;color:#000000;font-family:verdana;line-height:150%;" width="617" cellspacing="0" cellpadding="0">
  <tr>
    <td style="width:200px;"><p><strong>Report reference number</strong></p></td>
    <td><p><#if EDI0470??>${EDI0470}</#if></p></td>
  </tr>
  <tr>
    <td style="width:200px;"><p><strong>Page reported</strong></p></td>
    <td><p><#if EDI0480??>${EDI0480}</#if></p></td>
  </tr>
  <tr>
    <td>&nbsp</td>
    <td>&nbsp</td>
  </tr>
  <tr>
    <td style="width:200px;"><p>Date reported</p></td>
    <td><p><#if EDI0490??>${EDI0490}</#if></p></td>
  </tr>
  <tr>
    <td style="width:200px;"><p>Time reported</p></td>
    <td><p><#if EDI0500??>${EDI0500}</#if></p></td>
  </tr>
  <tr>
    <td style="width:200px;"><p>Charity name</p></td>
    <td><p><#if EDI0100??>${EDI0100}</#if></p></td>
  </tr>
  <tr>
    <td style="width:200px;"><p>Charity number</p></td>
    <td><p><#if EDI0510??>${EDI0510}</#if></p></td>
  </tr>
  <tr>
    <td style="width:200px;"><p>Fundraiser's first name</p></td>
    <td><p><#if EDI0520??>${EDI0520}</#if></p></td>
  </tr>
  <tr>
    <td style="width:200px;"><p>Fundraiser's last name</p></td>
    <td><p><#if EDI0120??>${EDI0120}</#if></p></td>
  </tr>
</table>

<p>Details of the inappropriate language&#58;</p>
<p><#if EDI0540??>${EDI0540}</#if></p>

<p><strong>Action taken&#58;</strong></p>

<#include "footer-1.ftl" parse="true">