<#include "header.ftl" parse="true">

<p class="style1">Dear ${EDI0070},</p>

<p>Thank you for setting up a fundraising page with Virgin Money Giving &#45; just go to <a href="${fundraiserUrl}">${fundraiserUrl}</a> to see it now.</p>

<p>Don&#39;t forget to tell all your friends, family and colleagues about your page so they can sponsor you and see how you&#39;re getting on with your fundraising. Simply sign into your Virgin Money Giving account to send an email or get our Facebook application.</p>

<#if isCharityThanksFundraiser??>
<#if multipleCharities??>
<p>Your chosen charities would also like to say thank you for your support&#58;</p>
<#else>
<p>Your charity would also like to say thank you for your support&#58;</p>
</#if>
${charityThankYouMessages!""}
</#if>

<p>Good luck with your fundraising!</p>

<#include "signature-jb.ftl" parse="true">
<#include "footer-1.ftl" parse="true">