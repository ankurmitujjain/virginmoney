/*---[ Details ]---------------------------------------
Basic Style Sheet - Older Browsers
Site: www.foobar.com
Author: Lee Powell
Contact: lee.powell@soup.co.uk
-------------------------------------------------------*/

/*-----------------------------------------------------
jQuery
-------------------------------------------------------*/
var $j = jQuery.noConflict();
jQuery(function(){

	// LOGO LINK
	$j('#branding-logo').css('cursor', 'pointer').click(
		function()
		{
			window.location = "/";
		}
	);
	
	// DONATION CALCULATOR
	$j('#vm-donationcalc input.submit').click(
		function()
		{
			var amount = $j(this).siblings('input.amount').val();
			
			if( !isNaN(amount) )
			{
				amount = parseFloat(amount);
				
				amount = amount.toFixed(2);
				
				// Update the value with the trimmed amount
				$j(this).siblings('input.amount').val(amount);
				
				// Convert amount to a valid number
				amount = parseFloat(amount);
				
				// Perform calulation based on 2.5% card usage fee
				var cardFee = (amount/100)*2.5;
				
				// Round up to 2 decimal places
				cardFee = cardFee.toFixed(2);
				
				// Perform calulation based on 2% virgin money fee
				var vmgFee = (amount/100)*2;
				
				// Round up to 2 decimal places
				vmgFee = vmgFee.toFixed(2);
				
				// Perform calulation based on 20% Gift Aid allowance
				var giftAid = (amount/100)*20;
				
				// Round up to 2 decimal places
				giftAid = giftAid.toFixed(2);
				
				// Final Calculation
				var donation = (amount - (parseFloat(cardFee) + parseFloat(vmgFee))) + parseFloat(giftAid);
				
				donation = donation.toFixed(2);
				
				$j('#vm-donationcalc .amount').html('&pound;' + donation);
			}
			else
			{
				alert('Please enter a valid donation amount.');
			}
		}
	);
	
	// TOOLTIPS
	(function()
	{
		var _tooltipTimeout = null;
		var _tooltipDelay = 350;
		
		$j('div.tooltip').addClass('js').hide();
		
		$j('a.tooltip').mouseover(
			function()
			{
				var offset = $j(this).offset();
				
				var target = $j(this).attr('href');
				
				// Use a set timeout so there is a slight delay to avoid annoying users on quick mouse rollovers
				_tooltipTimeout = setTimeout(function(){ showToolTip(target, (offset.left - 228), (offset.top + 23)) }, _tooltipDelay);
			}
		);
		
		$j('a.tooltip').mouseout(
			function()
			{
				clearTimeout(_tooltipTimeout)
				
				var target = $j(this).attr('href');
				
				$j(target).hide();
			}
		);
		
		function showToolTip(tooltip, left, top)
		{
			$j(tooltip).show();
				
			$j(tooltip).css({ 'left': left, 'top': top });
		}
	})();
	
	// SHOW AND HIDE
	(function()
	{
		$j('.js-hide').css({'visibility': 'hidden'});
		
		$j('.js-show').click(function(){
			var target = $j(this).attr('rel');
				
			$j('#' + target + ':hidden').css({'visibility': 'visible'}).hide().fadeIn();
				
			return false;
		});
	}
	)();
	
	// CATEGORY SELECT
	(function()
	{
		$j('#vm-categoryselect').addClass('js').find('ul').hide();
		
		$j('#vm-categoryselect input[type="checkbox"]:checked').each(function(){
			var category = $j(this).parent('li').parent('ul').siblings('legend').html();
			
			var categoryText = $j('label[for="' + $j(this).attr('id') + '"]').html();
			
			categoryText = '<p rel="' + $j(this).attr('id') + '">' + category + ' > ' + categoryText + '</p>';
			
			$j('#vm-categorychosen').append(categoryText);
		});
		
		$j('#vm-categoryselect legend').toggle(
			function(){
				$j(this).siblings('ul').slideDown(function(){
					$j(this).siblings('legend').toggleClass('open');
				});
			},
			function(){
				$j(this).siblings('ul').slideUp(function(){
					$j(this).siblings('legend').toggleClass('open');
				});
			}
		);
		
		$j('#vm-categoryselect input[type="checkbox"]').click(function(){
			var category = $j(this).parent('li').parent('ul').siblings('legend').html();
			
			var categoryText = $j('label[for="' + $j(this).attr('id') + '"]').html();
			
			categoryText = '<p rel="' + $j(this).attr('id') + '">' + category + ' > ' + categoryText + '</p>';
			
			if( $j(this).attr('checked') )
			{
				$j('#vm-categorychosen').append(categoryText);	
			}
			else
			{
				$j('#vm-categorychosen p[rel="' + $j(this).attr('id') + '"]').remove();
			}
		});
	}
	)();
	
});




/*-----------------------------------------------------
sIFR
-------------------------------------------------------*/