package com.virginmoneygiving.integrationtests.selenium;

import com.thoughtworks.selenium.DefaultSelenium;

/**
 * "Help" with selenium by getting system properties and setting to 
 * default if not present.
 *
 * Means running manually will default, but running from ant will get the
 * System.property value.
 *
 * @author ian.priest@opsera.com
 */
public class SeleniumHelper extends DefaultSelenium {

    public static String SERVER_HOST = System.getProperty("selenium.rc.server.host", "localhost");
    public static int SERVER_PORT = Integer.parseInt(System.getProperty("selenium.rc.server.port", "5555"));
    public static String BROWSER_TYPE = System.getProperty("selenium.rc.browser", "*chrome");
    public static String BROWSER_BASE_URL = System.getProperty("jboss.url", "http://127.0.0.1:8080/");
        
    public SeleniumHelper() {
        super(
            SERVER_HOST,
            SERVER_PORT,
            BROWSER_TYPE,
            BROWSER_BASE_URL);
    }

    public SeleniumHelper(String serverHost, int serverPort,
        String browserStartCommand, String browserURL) {
        super(serverHost, serverPort, browserStartCommand, browserURL);
    }

    /**
     * Capture a screenshot and save to a filename that reflects the test 
     * and method that were executing.
     * Utility method to be called from @AfterFailure methods  
     * @param failure
     */
    public void captureScreenShotOnFailure(Class testClass, Throwable failure) {
        
        // Get test method name
        String testMethodName = null;
        for (StackTraceElement stackTrace : failure.getStackTrace()) {
            if (stackTrace.getClassName().equals(testClass.getName())) {
                testMethodName = stackTrace.getMethodName();
                break;
            }
        }
        
        String filename = 
            testClass.getName() + "." + testMethodName + ".png";
        
        /*
         * Maximise the window and screen-shot it. A pause seems necessary 
         * for it to work correctly.
         */
        windowFocus();
        windowMaximize();
        try {
            Thread.sleep(1000);
        }
        catch( InterruptedException e ) {
            // Ignored
        }
        captureScreenshot(filename);
    }

}
