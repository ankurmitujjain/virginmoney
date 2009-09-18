<#include "header.ftl" parse="true">

<p class="style1">Some inappropriate language has been found on Virgin Money Giving.</p>

<p>Please investigate this issue and take any appropriate action. If the wording breaks the Virgin Money Giving terms and conditions, or is offensive or libellous, it will need to be removed.</p>

<p>Please record any actions in the box at the bottom of this email.</p>


<table style="font-size:12px;color:#000000;font-family:verdana;line-height:150%;" width="617" cellspacing="0" cellpadding="0">
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
    <td style="width:200px;"><p>Fundraiser's name</p></td>
    <td><p><#if EDI0520??>${EDI0520}</#if></p></td>
  </tr>
  <tr>
    <td style="width:200px;"><p>Fundraiser's reference number</p></td>
    <td><p><#if EDI0120??>${EDI0120}</#if></p></td>
  </tr>
  <tr>
      <td style="width:200px;"><p>Event type</p></td>
      <td><p><#if EDI0720??>${EDI0720}</#if></p></td>
  </tr>
    <tr>
      <td style="width:200px;"><p>Event name</p></td>
      <td><p><#if EDI0150??>${EDI0150}</#if></p></td>
  </tr>
</table>

<p>Details of the inappropriate language&#58;</p>
<p><#if EDI0530??>${EDI0530}</#if></p>

<p><strong>Action taken&#58;</strong></p>

<#include "footer-1.ftl" parse="true">