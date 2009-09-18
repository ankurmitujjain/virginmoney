// -----------------------------------------------------------------------------------
//
//	Virgin Money Giving - Social Bookmarking
//	by Jim Starling
//
//             
//      19/03/09 - EBO Microsite Functionality
//
// -----------------------------------------------------------------------------------

var bookmarking = Class.create();

bookmarking.prototype = {

	// initialize()
    
    	initialize: function() {

		this.findShareLink();
	},



	// Social Bookmarking methods

	findShareLink: function() {


		if($('share-link')) {


			shareLink = $('share-link');
			this.activateShareLink(shareLink)
			this.writeShareElement();

		}


	},


	activateShareLink: function(element) {

		Event.observe(element,'mouseover', this.shareMouseOver.bindAsEventListener(this));
		Event.observe(element,'mouseout', this.shareMouseOut.bindAsEventListener(this));

	},

	shareMouseOver: function() {


		window.clearTimeout(this.shareTimeout)

		buttonPos = $("shareButton").cumulativeOffset();
		popPosTop = buttonPos[1] + 18
		popPosLeft = buttonPos[0] - 200 + $("shareButton").getWidth()
		$("share-popup").setStyle({'position':'absolute','top':popPosTop+'px','left':popPosLeft+'px'});
		$("share-popup").show();

		Event.observe("share-popup",'mouseover',this.sharePopupMouseOver.bind(this));
		Event.observe("share-popup",'mouseout',this.sharePopupMouseOut.bind(this));


	},

	shareMouseOut: function() {

		window.clearTimeout(this.shareTimeout)
		this.shareTimeout = setTimeout(function() { $("share-popup").hide(); }, 1000 )

	},

	writeShareElement: function() {

		var pageUrl   = document.location;
		var pageTitle = document.title;

		var bodyElmt = $$('body')[0];

	   // Determine which browser we're in //

		var IE = document.all ? true : false;

		if (BrowserDetect.browser == "Explorer") {
		  bookmarkLink = "<a title='Add this page to your favourites' href='javascript:window.external.AddFavorite(\""+pageUrl+"\", \""+pageTitle+"\")'><img src='img/branding/bookmarking/favourites.gif'>&nbsp;Favourites</a>";
		}else if (BrowserDetect.browser == "Firefox") {
		  bookmarkLink = "<a title='Add this page to your bookmarks' href='javascript:window.sidebar.addPanel(\""+pageTitle+"\", \""+pageUrl+"\",\"\")'><img src='img/branding/bookmarking/favourites.gif'>&nbsp;Bookmark</a>";
		} else {	
		  bookmarkLink = "";
		}

	   // Create the content for the popup //

		sharePopupContent  = "<div class='share-column'><div>";
		sharePopupContent += "<a title='Post this page to Facebook' href='http://www.facebook.com/sharer.php?u="+pageUrl+"' target='_blank'><img src='img/branding/bookmarking/facebook.gif'>&nbsp;Facebook</a><br />";
		sharePopupContent += "<a title='Post this page to Del.icio.us' href='http://del.icio.us/post?url="+pageUrl+"&title="+pageTitle+"' target='_blank'><img src='img/branding/bookmarking/delicious.gif'>&nbsp;Delicious</a><br />";
		sharePopupContent += "<a title='Post this page to Digg' href='http://digg.com/submit?url="+pageUrl+"&title="+pageTitle+"' target='_blank'><img src='img/branding/bookmarking/digg.gif'>&nbsp;Digg</a>";
		sharePopupContent += "</div></div><div class='share-column'><div>";
		sharePopupContent += "<a title='Post this page to Reddit' href='http://reddit.com/submit?url="+pageUrl+"&title="+pageTitle+"' target='_blank'><img src='img/branding/bookmarking/reddit.gif'>&nbsp;Reddit</a><br />";
		sharePopupContent += "<a title='Post this page to Stumbleupon' href='http://www.stumbleupon.com/submit?url="+pageUrl+"&title="+pageTitle+"' target='_blank'><img src='img/branding/bookmarking/stumbleupon.gif'>&nbsp;StumbleUpon</a><br />";
		sharePopupContent += bookmarkLink+"</div></div>";


		var sharePopupContainerElmt = new Element('div', { 'id':'popup-message'}).update(sharePopupContent);
		var sharePopupElmt = new Element('div', { 'id':'share-popup'}).update(sharePopupContainerElmt).hide();

		new Element.insert(bodyElmt, {bottom:sharePopupElmt});

	},



	sharePopupMouseOver: function() {

		window.clearTimeout(this.shareTimeout);

	},


	sharePopupMouseOut: function() {

		window.clearTimeout(this.shareTimeout)
		this.shareTimeout = setTimeout(function() { $("share-popup").hide(); }, 1000 )
		
		
	}
	
}

document.observe('dom:loaded', function () { new bookmarking() });



/*-----------------------------------------------------
sIFR
-------------------------------------------------------*/


/*-----------------------------------------------------
Apply selected class to navigation
-------------------------------------------------------*/
function applySelectedTo(link) {
  var ul = document.getElementsByTagName("ul")[0];
  var allLinks = ul.getElementsByTagName("a");
  for (var i=0; i<allLinks.length; i++) { 
    allLinks[i].className = ""; 
  }
  link.className = "selected"; 
}