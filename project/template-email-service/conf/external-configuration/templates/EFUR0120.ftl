<#include "header.ftl" parse="true">


<p class="style1">Dear ${EDI0070},</p> 
 
<p>Thank you for signing up to ${event_name} through Virgin Money Giving.</p> 
 
<p>We&#39;ve sent your payment to ${charity_name}. They&#39;ll be in touch soon to confirm all the event details and let 
you know what happens next.</p>

<p>In the meantime, here's your receipt&#58;</p>

<table cellspacing="0" cellpadding="0" border="0" style="font-size:12px;color:#000000;line-height:150%;font-family:verdana;">
<tr>
  <td style="width:200px;"><p>Charity&#58;</p></td>
  <td><p>${charity_name}</p></td>
</tr>
<tr>
  <td><p>Event name&#58;</p></td>
  <td><p>${event_name}</p></td>
</tr>
<tr>
  <td><p>Fee&#58;</p></td>
  <td><p>${event_fee}</p></td>
</tr>
<tr>
  <td><p>Category&#58;</p></td>
  <td><p>${event_fee_situation}</p></td>
</tr>
</table>

<p>If you need any more information, you can contact the event's organiser&#58;</p>


<table cellspacing="0" cellpadding="0" border="0" style="font-size:12px;color:#000000;line-height:150%;font-family:verdana;">
<tr>
  <td style="width:200px;"><p>Event contact&#58;</p></td>
  <td><p>${contact_name}</p></td>
</tr>
<tr>
  <td><p>Phone Number&#58;</p></td>
  <td><p>${telephone_number}</p></td>
</tr>
<tr>
  <td><p>Email&#58;</p></td>
  <td><p>${email_address}</p></td>
</tr>
</table>


<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">



