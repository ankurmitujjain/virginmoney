<#include "header.ftl" parse="true">


<#if paymentMode??>
<#if paymentMode == 'S'>
<p class="style1">Your donation has been processed &#45; we&#39;ll pass it directly to ${charity_name}.</p>
<#elseif paymentMode == 'M'>
<p>Your monthly donation to ${charity_name} has been set&#45;up. Your first payment will be taken on ${first_collection_date}, then on the ${collection_day} of each month.</p>
</#if>
</#if>

<p><strong>Here&#39;s a summary of your donation&#58;</strong></p>

<table style="font-size:12px;color:#000000;font-family:verdana;line-height:150%;" cellspacing="0" cellpadding="0" border="0" >
  <tr>
    <td style="width:200px;"><p>Amount &#58; </p></td><td><p>&nbsp ${currency}${amount}</p></td>
  </tr>
  <tr>
    <td style="width:200px;"><p>Gift Aid (incl. transitional relief) &#58; </p></td><td><p>&nbsp ${currency}${gift_aid_amount}</p></td>
  </tr>
  </tr>
  <#if EDI0150??>
   <tr>
      <td style="width:200px;"><p>Event &#58; </p></td><td><p>&nbsp ${EDI0150}</p></td>
  </tr>
  </#if>
  </tr>
    <tr>
      <td style="width:200px;"><p>Charities &#58; </p></td><td><p>&nbsp ${charity_name}</p></td>
  </tr>
  </tr>
    <tr>
      <td style="width:200px;"><p>Donation Reference &#58; </p></td><td><p>&nbsp ${reference_number}</p></td>
  </tr>
</table>


<#if donation_indicator??>
<#if donation_indicator == 'F'>
<#if fundraiser_message??>
<p><strong>${fundraiser_name} says&#58;</strong></p>
<p>${fundraiser_message}</p>
</#if>
</#if>
</#if>

<#if charity_message??>
<p><strong>${charity_name} says&#58;</strong></p>
<p>${charity_message}</p>
</#if>

<p>For more information or to start fundraising, please go to <a href="www.virginmoneygiving.com">www.virginmoneygiving.com</a></p>

<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">