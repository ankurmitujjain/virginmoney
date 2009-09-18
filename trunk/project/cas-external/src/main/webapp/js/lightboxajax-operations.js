// -----------------------------------------------------------------------------------
//
//	Based on Lightbox v2.04
//	by Lokesh Dhakar - http://www.lokeshdhakar.com
//	Modified by Jim Starling to enable Lightbox of AJAX driven content
//
//
//
// -----------------------------------------------------------------------------------
/*

    Table of Contents
    -----------------
    Configuration

    Lightbox Class Declaration
    - initialize()
    - updateAjaxList()
    - start()
    - changeContent()
    - resizeImageContainer()
    - end()

    Function Calls
    - document.observe()

*/
// -----------------------------------------------------------------------------------

//
//  Configurationl
//
LightboxOptions = Object.extend({
    fileLoadingImage:        '/Images/loading_tcm21-25470.gif',
    fileBottomNavCloseImage: '../../img/lightbox/close_opration.png',

    overlayOpacity: 0.8,   // controls transparency of shadow overlay

    animate: true,         // toggles resizing animations
    resizeSpeed: 7,        // controls the speed of the image resizing animations (1=slowest and 10=fastest)

    borderSize: 10,         //if you adjust the padding in the CSS, you will need to update this variable

	// When grouping images this is used to write: Image # of #.
	// Change it for non-english localization
	labelImage: "Image",
	labelOf: "of"
}, window.LightboxOptions || {});

// -----------------------------------------------------------------------------------

var AjaxLightbox = Class.create();

AjaxLightbox.prototype = {
    imageArray: [],
    activeImage: undefined,

    // initialize()
    // Constructor runs on completion of the DOM loading. Calls updateImageList and then
    // the function inserts html at the bottom of the page which is used to display the shadow
    // overlay and the image container.
    //
    initialize: function() {

        this.updateAjaxList(); // Definately needed!

        if (LightboxOptions.resizeSpeed > 10) LightboxOptions.resizeSpeed = 10;
        if (LightboxOptions.resizeSpeed < 1)  LightboxOptions.resizeSpeed = 1;

	    this.resizeDuration = LightboxOptions.animate ? ((11 - LightboxOptions.resizeSpeed) * 0.15) : 0;
	    this.overlayDuration = LightboxOptions.animate ? 0.2 : 0;  // shadow fade in/out duration

        // When Lightbox starts it will resize itself from 250 by 250 to the current image dimension.
        // If animations are turned off, it will be hidden as to prevent a flicker of a
        // white 250 by 250 box.
        var size = (LightboxOptions.animate ? 250 : 1) + 'px';
		

        var objBody = $$('body')[0];

		objBody.appendChild(Builder.node('div',{id:'contentOverlayOperation'}));

        objBody.appendChild(Builder.node('div',{id:'lightboxAjax'}, [

            Builder.node('div',{id:'outerContentContainerOperation'}, [
            	Builder.node('div',{style:'height:30px;'},Builder.node('a',{style:'display:block;height:20px;width:400px:clear:right', id:'bottomNavContentClose', href: '#' },
	    		                Builder.node('img', { src: LightboxOptions.fileBottomNavCloseImage })
                        )
                ),
                //Builder.node('div',{style:'clear:both;height:1px'}),
                Builder.node('div',{id:'contentContainer'}, [

					Builder.node('div',{id:'contentPanel',style:'display:none'}),
					Builder.node('div',{id:'contentLoading'},
						Builder.node('a',{id:'loadingLink', href: '#' },
							Builder.node('img', {src: LightboxOptions.fileLoadingImage})
						)
					)
                ])
            ]),

        ]));


		$('contentOverlayOperation').hide().observe('click', (function() { this.end(); }).bind(this));
		$('lightboxAjax').hide().observe('click', (function(event) { if (event.element().id == 'lightbox') this.end(); }).bind(this));
		$('outerContentContainerOperation').setStyle({ width: size, height: size });
		$('loadingLink').observe('click', (function(event) { event.stop(); this.end(); }).bind(this));
		$('bottomNavContentClose').observe('click', (function(event) { event.stop(); this.end(); }).bind(this));

        var th = this;
        (function(){
            var ids =
                'contentOverlayOperation lightboxAjax outerContentContainerOperation contentContainer contentPanel lightboxImage hoverNav contentLoading loadingLink ' +
                'contentDataContainer imageData imageDetails caption numberDisplay bottomNav bottomNavContentClose';
            $w(ids).each(function(id){ th[id] = $(id); });
        }).defer();
    },

    //
    // updateAjaxList()
    // Loops through anchor tags looking for 'lightbox' references and applies onclick
    // events to appropriate links. You can rerun after dynamically adding images w/ajax.
    //
    updateAjaxList: function() {
        this.updateAjaxList = Prototype.emptyFunction;
        document.observe('click', (function(event){			
            var target = event.findElement('a[rel^=ajaxLightbox]') || event.findElement('area[rel^=ajaxLightbox]');
            if (target) {				
                event.stop();
                this.start(target);
            }
        }).bind(this));
    },

    //
    //  start()
    //  Display overlay and lightbox. If image is part of a set, add siblings to imageArray.
    //
    start: function(contentLink) {

        $$('select', 'object', 'embed').each(function(node){ node.style.visibility = 'hidden' });

        // stretch overlay to fill page and fade in
        var arrayPageSize = this.getPageSize();
        $('contentOverlayOperation').setStyle({ width: arrayPageSize[0] + 'px', height: arrayPageSize[1] + 'px' });
        $('contentLoading').show();
        new Effect.Appear(this.contentOverlayOperation, { duration: this.overlayDuration, from: 0.0, to: LightboxOptions.overlayOpacity });

        // calculate top and left offset for the lightbox
        var arrayPageScroll = document.viewport.getScrollOffsets();
        var lightboxTop = arrayPageScroll[1] + (document.viewport.getHeight() / 10);
        var lightboxLeft = arrayPageScroll[0];
        this.lightboxAjax.setStyle({ top: lightboxTop + 'px', left: lightboxLeft + 'px' }).show();

        this.changeContent(contentLink.rev);
    },

    //
    //  changeContent()
    //  Hide most elements and preload image in preparation for resizing image container.
    //
    changeContent: function(link) {

        this.activeImage = link; // update global var

	new Ajax.Updater(this.contentPanel, link, {
		method: 'GET',
		onSuccess: function(transport) {

		this.contentLoading.hide();

		}.bind(this)

	});

        this.resizeImageContainer(600, 400);


    },


    //
    //  resizeImageContainer()
    //
    resizeImageContainer: function(imgWidth, imgHeight) {

        $("contentPanel").hide()

        // get current width and height
        var widthCurrent  = this.outerContentContainerOperation.getWidth();
        var heightCurrent = this.outerContentContainerOperation.getHeight();
		
        // get new width and height
        var widthNew  = (imgWidth  + LightboxOptions.borderSize * 2);
        var heightNew = (imgHeight + LightboxOptions.borderSize * 2);		
        // scalars based on change from old to new
        var xScale = (widthNew  / widthCurrent)  * 100;
        var yScale = (heightNew / heightCurrent) * 100;

        // calculate size difference between new and old image, and resize if necessary
        var wDiff = widthCurrent - widthNew;
        var hDiff = heightCurrent - heightNew;
		
        new Effect.Scale(this.outerContentContainerOperation, yScale, {scaleX: false, duration: this.resizeDuration, queue: 'front'});
        new Effect.Scale(this.outerContentContainerOperation, xScale, {scaleY: false, duration: this.resizeDuration, delay: this.resizeDuration, afterFinish: function() {
        $("contentPanel").show()
		$('contentLoading').hide();
        }});		


    },

    //
    //  end()
    //
    end: function() {
        new Effect.Fade(this.lightboxAjax, { duration: this.overlayDuration });
        new Effect.Fade(this.contentOverlayOperation, { duration: this.overlayDuration });
        this.resizeImageContainer(250, 250);
        $$('select', 'object', 'embed').each(function(node){ node.style.visibility = 'visible' });
    },

    //
    //  getPageSize()
    //
    getPageSize: function() {

	     var xScroll, yScroll;

		if (window.innerHeight && window.scrollMaxY) {
			xScroll = window.innerWidth + window.scrollMaxX;
			yScroll = window.innerHeight + window.scrollMaxY;
		} else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
			xScroll = document.body.scrollWidth;
			yScroll = document.body.scrollHeight;
		} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
			xScroll = document.body.offsetWidth;
			yScroll = document.body.offsetHeight;
		}

		var windowWidth, windowHeight;

		if (self.innerHeight) {	// all except Explorer
			if(document.documentElement.clientWidth){
				windowWidth = document.documentElement.clientWidth;
			} else {
				windowWidth = self.innerWidth;
			}
			windowHeight = self.innerHeight;
		} else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
			windowWidth = document.documentElement.clientWidth;
			windowHeight = document.documentElement.clientHeight;
		} else if (document.body) { // other Explorers
			windowWidth = document.body.clientWidth;
			windowHeight = document.body.clientHeight;
		}

		// for small pages with total height less then height of the viewport
		if(yScroll < windowHeight){
			pageHeight = windowHeight;
		} else {
			pageHeight = yScroll;
		}

		// for small pages with total width less then width of the viewport
		if(xScroll < windowWidth){
			pageWidth = xScroll;
		} else {
			pageWidth = windowWidth;
		}

		return [pageWidth,pageHeight];
	}
}

document.observe('dom:loaded', function () { new AjaxLightbox(); });