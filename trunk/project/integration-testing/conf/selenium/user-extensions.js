
// UI-Element map file to define mappings for VMG charity-web

// in general, the map should capture structural aspects of the system, instead
// of "content". In other words, interactive elements / assertible elements
// that can be counted on to always exist should be defined here. Content -
// for example text or a link that appears in a blog entry - is always liable
// to change, and will not be fun to represent in this way. You probably don't
// want to be testing specific content anyway.

// create the UI mapping object.
var myMap = new UIMap();

// ===========================================================================================================
// Define common mappings - these have the same name on all forms
// ===========================================================================================================
myMap.addPageset({
    name: 'common-pages'
    , description: 'VMG common elements'
    , pathRegexp: '.*'
});
myMap.addElement('common-pages', {
    name: 'firstName'
    , description: 'firstName field'
    , locator: "//*[@id='firstName']"
});
myMap.addElement('common-pages', {
    name: 'lastName'
    , description: 'lastName field'
    , locator: "//*[@id='lastName']"
});
myMap.addElement('common-pages', {
    name: 'regBuildingNmNo'
    , description: 'Building name or number field'
    , locator: "//*[@id='regBuildingNmNo']"
});
myMap.addElement('common-pages', {
    name: 'telephoneNumber'
    , description: 'Telephone Number field'
    , locator: "//*[@id='telephoneNumber']"
});
myMap.addElement('common-pages', {
    name: 'regPostCode'
    , description: 'PostCode field'
    , locator: "//*[@id='regPostCode']"
});
myMap.addElement('common-pages', {
    name: 'emailAddress'
    , description: 'Email Address field'
    , locator: "//*[@id='emailAddress']"
});
myMap.addElement('common-pages', {
    name: 'dayOfBirth'
    , description: 'Day Of Birth field'
    , locator: "//*[@id='DOB']"
});

myMap.addElement('common-pages', {
    name: 'monthOfBirth'
    , description: 'Month Of Birth field'
    , locator: "//*[@id='vm-sponsor-info_securityDetailsDVO_monthOfBirth']"
});

myMap.addElement('common-pages', {
    name: 'yearOfBirth'
    , description: 'Year Of Birth field'
    , locator: "//*[@id='vm-sponsor-info_securityDetailsDVO_yearOfBirth']"
});
myMap.addElement('common-pages', {
    name: 'password'
    , description: 'Password field'
    , locator: "//*[@id='password']"
});
myMap.addElement('common-pages', {
    name: 'confirmpass'
    , description: 'Confirm password field'
    , locator: "//*[@id='confirmpass']"
});

// ===========================================================================================================
// Define charity-web mappings
// ===========================================================================================================

// The top-level actions that can be performed in charity-web
var charityTopLevelActions = [
    'Click here to proceed with charity registration part-1',
    'Click here to proceed with charity registration part-2',
    'Click here to proceed to Charity Portal',
    'Click here to View Charity Final Home Page',
    'Click here to proceed to Event'
];


// The Charity Account Summary page actions
var charityAccountSummaryActions = [
    'Start up fee',
    'Forms',
    'Charity category',
    'Charity description',
    ' Charity logo',
	'Charity details',
	'Trustee details',
	'Virgin Money Giving checks'
];

// define charity-web front-page and actions
// ^' is automatically prepended, and '$' is automatically postpended.
// Please note that because the regular expression is being represented as a
// string, all backslashes must be escaped with an additional backslash. Also
// note that the URL being matched will always have any trailing forward slash
// stripped.
myMap.addPageset({
    name: 'charity-web'
    , description: 'VMG charity-web top-page'
    , pathRegexp: 'charity-web'
});

myMap.addElement('charity-web', {
    name: 'charityTopLevelActions'
    , description: 'links to front-page actions'
    , args: [
        {
            name: 'action'
            , description: 'the text of the action'
            , defaultValues: charityTopLevelActions
        }
    ]
    , getLocator: function(args) {
        return "//div/ul/li" +
            "/a[text()=" + args.action.quoteForXPath() + "]";
    }
    , testcase1: {
        args: { action: 'Click here to proceed with charity registration part-1' }
        , xhtml: '<div><ul><li>'
            + '<a expected-result="1">Click here to proceed with charity registration part-1</a>'
            + '</li></ul></div>'
    }
});


// Define 'charity-pages' sub-pages and various UI elements
myMap.addPageset({
    name: 'charity-pages'
    , description: 'VMG charity-web charity pages'
    , pathRegexp: 'charity-web/charity/.*'
});

// Define the fields and buttons. Done here rather than in the java
// as hopefully it'll be easier to edit if they ever change

/*
 * Search page UI elements
 */
myMap.addElement('charity-pages', {
    name: 'charitySearchInput'
    , description: 'Input field for search text'
    , locator: "//*[@id='search-input-charity']"
});
myMap.addElement('charity-pages', {
    name: 'charitySearchSubmit'
    , description: 'Submit button for search text'
    , locator: "//*[@class='findcharity']"
});

/*
 * Search Results UI elements
 */
myMap.addElement('charity-pages', {
    name: 'charityNotListedNext'
    , description: 'Next button for a not-found charity'
    , locator: "//*[@id='vm-register-cha0010_0']"
});

/*
 * Registered Address UI elements
 */
myMap.addElement('charity-pages', {
    name: 'charityName'
    , description: 'Charity Name field'
    , locator: "//*[@id='charityName']"
});

myMap.addElement('charity-pages', {
    name: 'registerCharityNumber'
    , description: 'Registered Number field'
    , locator: "//*[@id='registerCharityNumber']"
});
myMap.addElement('charity-pages', {
    name: 'charityAddressNext'
    , description: 'Next button for registered address page'
    , locator: "//*[@id='newCharityDetailsSave_0']"
});

/*
 * Admin Address UI elements
 */
myMap.addElement('charity-pages', {
    name: 'adminAddressSameAsRegistrationAddress'
    , description: 'Same as the registered address box'
    , locator: "//*[@id='adminAddressSameAsRegistrationAddress']"
});

myMap.addElement('charity-pages', {
    name: 'adminAddressNext'
    , description: 'Next button for admin address page'
    , locator: "//*[@id='vm-register-cha0070_0']"
});

/*
 * Account Holders Details UI elements
 */
myMap.addElement('charity-pages', {
    name: 'title'
    , description: 'title select'
    , locator: "//*[@id='registercharity.title']"
});
myMap.addElement('charity-pages', {
    name: 'occupation'
    , description: 'occupation field'
    , locator: "//*[@id='occupation']"
});
myMap.addElement('charity-pages', {
    name: 'acceptTermsAndCondition'
    , description: 'acceptTermsAndCondition box'
    , locator: "//*[@id='acceptTermsAndCondition']"
});
myMap.addElement('charity-pages', {
    name: 'accountHolderNext'
    , description: 'Next button for account holder details page'
    , locator: "//*[@id='vm-register-cha0080_0']"
});

/*
 * Security Details UI elements
 */
myMap.addElement('charity-pages', {
    name: 'registerNewCharity'
    , description: 'Register button for security details page'
    , locator: "//*[@name='submit']"
});

/*
 * Registration Confirmed UI Elements
 */
myMap.addElement('charity-pages', {
    name: 'gotoApplicationSummary'
    , description: 'Go To Application Summary button'
    , locator: "//a[contains(@href, 'registrationSummary.action')]"
});


/*
 * Charity Account Summary UI Elements
 */
myMap.addElement('charity-pages', {
    name: 'charityAccountSummaryActions'
    , description: 'links to account summary actions'
    , args: [
        {
            name: 'action'
            , description: 'the text of the action'
            , defaultValues: charityAccountSummaryActions
        }
    ]
    , getLocator: function(args) {
        return "//a[text()=" + args.action.quoteForXPath() + "]";
    }
});
myMap.addElement('charity-pages', {
    name: 'animalsThatHelp'
    , description: 'Animals That Help box'
    , locator: "//*[@id='id5']"
});
myMap.addElement('charity-pages', {
    name: 'saveCategory'
    , description: 'Save category button'
    , locator: "//*[@id='saveId']"
});

// ===========================================================================================================
// Define fundraiser-web mappings
// ===========================================================================================================

// The top-level actions that can be performed in fundraiser-web
var fundraiserTopLevelActions = [
    'Choose Fundraising Reason',
    'Make donation to fundraiser'
];

// Possible values for fundraiser events (grow a beard, run a marathon etc.)
// Ids taken from database so could change
var fundraiserEvents = [
    '028',
	'029',
	'030',
	'031',
	'032',
	'033',
	'034',
	'035',
	'036',
	'037',
	'038',
	'039',
	'OTHER'
];

// define fundraiser-web front-page and actions
// ^' is automatically prepended, and '$' is automatically postpended.
// Please note that because the regular expression is being represented as a
// string, all backslashes must be escaped with an additional backslash. Also
// note that the URL being matched will always have any trailing forward slash
// stripped.
myMap.addPageset({
    name: 'fundraiser-web'
    , description: 'VMG fundraiser-web top-page'
    , pathRegexp: 'fundraiser-web'
});

myMap.addElement('fundraiser-web', {
    name: 'fundraiserTopLevelActions'
    , description: 'links to front-page actions'
    , args: [
        {
            name: 'action'
            , description: 'the text of the action'
            , defaultValues: fundraiserTopLevelActions
        }
    ]
    , getLocator: function(args) {
        return "//div/li" +
            "/a[text()=" + args.action.quoteForXPath() + "]";
    }
    , testcase1: {
        args: { action: 'Choose Fundraising Reason' }
        , xhtml: '<div><li>'
            + '<a expected-result="1">Choose Fundraising Reason</a>'
            + '</li></div>'
    }
});


// Define 'fundraiser-pages' sub-pages and various UI elements
myMap.addPageset({
    name: 'fundraiser-pages'
    , description: 'VMG fundraiser-web fundraiser pages'
    , pathRegexp: 'fundraiser-web/fundraiser/.*'
});

// Define the fields and buttons. Done here rather than in the java
// as hopefully it'll be easier to edit if they ever change

/*
 * Start Fundraising UI elements
 */
myMap.addElement('fundraiser-pages', {
    name: 'personalChallenge'
    , description: 'Personal Challenge button'
    , locator: "//a[contains(@href, 'personalChallengeEventsDisplay.action')]"
});

/*
 * Start Fundraising: event tab UI elements
 */
myMap.addElement('fundraiser-pages', {
    name: 'fundraiserEvents'
    , description: 'Possible personal challenge events'
    , args: [
        {
            name: 'action'
            , description: 'the id of the action'
            , defaultValues: fundraiserEvents
        }
    ]
    , getLocator: function(args) {
        return "//input[@id=" + args.action.quoteForXPath() + "]"
    }
});
myMap.addElement('fundraiser-pages', {
    name: 'eventsDetails'
    , description: 'Events Details field'
    , locator: "//*[@id='eventsDetails']"
});
myMap.addElement('fundraiser-pages', {
    name: 'eventCompletionDay'
    , description: 'Event Completion Day field'
    , locator: "//*[@id='eventCompletionDay']"
});
myMap.addElement('fundraiser-pages', {
    name: 'eventCompletionMonth'
    , description: 'Event Completion Month field'
    , locator: "//*[@id='eventCompletionMonth']"
});
myMap.addElement('fundraiser-pages', {
    name: 'eventCompletionYear'
    , description: 'Event Completion Year field'
    , locator: "//*[@id='eventCompletionYear']"
});
myMap.addElement('fundraiser-pages', {
    name: 'eventDuration'
    , description: 'Event Duration field'
    , locator: "//*[@id='eventDuration']"
});
myMap.addElement('fundraiser-pages', {
    name: 'alone'
    , description: 'Alone radio button'
    , locator: "//*[@id='I']"
});
myMap.addElement('fundraiser-pages', {
    name: 'eventTabNext'
    , description: 'Next button for event page'
    , locator: "//*[@name='submit']"
});

/*
 * Start Fundraising: Charity tab UI elements
 */
myMap.addElement('fundraiser-pages', {
    name: 'oneCharity'
    , description: 'One Charity field'
    , locator: "//*[@id='S']"
});
myMap.addElement('fundraiser-pages', {
    name: 'onOwn'
    , description: 'On Own field'
    , locator: "//*[@id='1']"
});
myMap.addElement('fundraiser-pages', {
    name: 'charityName'
    , description: 'Charity Name field'
    , locator: "//*[@id='charityName']"
});
myMap.addElement('fundraiser-pages', {
    name: 'fundraisingTargetAmount'
    , description: 'Fundraising Target Amount field'
    , locator: "//*[@id='fundraisingTargetAmount']"
});
myMap.addElement('fundraiser-pages', {
    name: 'contributedNo'
    , description: 'Contributed No field'
    , locator: "//*[@id='N']"
});
myMap.addElement('fundraiser-pages', {
    name: 'eventNext'
    , description: 'Next button for event page'
    , locator: "//*[@id='fundraiserCharitySave_0']"
});

/*
 * Start Fundraising: Register tab UI elements
 */
myMap.addElement('fundraiser-pages', {
    name: 'unregisteredNext'
    , description: 'Unregistered next button for event register page'
    , locator: "//a[contains(@href, 'fundraiserDetailsDisplay.action')]"
});

/*
 * Start Fundraising: Register tab UI elements
 */
myMap.addElement('fundraiser-pages', {
    name: 'registerfundraiser.title'
    , description: 'registerfundraiser.title field'
    , locator: "//*[@id='registerfundraiser.title']"
});
myMap.addElement('fundraiser-pages', {
    name: 'countryOfResidence'
    , description: 'countryOfResidence field'
    , locator: "//*[@id='countryOfResidence']"
});
myMap.addElement('fundraiser-pages', {
    name: 'preferredTelephoneNumberType'
    , description: 'preferredTelephoneNumberType field'
    , locator: "//*[@id='registerfundraiser.preferredTelephoneNumberType']"
});
myMap.addElement('fundraiser-pages', {
    name: 'registerNext'
    , description: 'Next button for event register page'
    , locator: "//input[@name, 'submit')]"
});

// Create Fundraiser Page button on fundraiser registration confirmed page
myMap.addElement('fundraiser-pages', {
    name: 'createFundraiserPage'
    , description: 'Unregistered next button for event register page'
    , locator: "//a[contains(@href, 'createFundraiserPage.action')]"
});
// My Page button on fundraiser page created page
myMap.addElement('fundraiser-pages', {
    name: 'myPage'
    , description: 'Unregistered next button for event register page'
    , locator: "//a[contains(@href, 'fundraiserPortalHome.action')]"
});
