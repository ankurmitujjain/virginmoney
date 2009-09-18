package com.virginmoneygiving.cardpayment.event.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import com.virginmoneygiving.cardpayment.event.AuthenticationResponseReceivedEvent;
import com.virginmoneygiving.cardpayment.event.BasePaymentEvent;
import com.virginmoneygiving.cardpayment.helper.DelimitedStringHandler;
import com.virginmoneygiving.cardpayment.helper.LogicGroupConstants;

/**
 * Logs relevant events. This class implements the Observer pattern
 * using the Spring ApplicationListener interface. <br>
 * The Spring container will automatically register any ApplicationListener
 * beans that are declared within the Spring context.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class LoggingEventListener implements ApplicationListener {

    /** The logger - protected so we can unit test with a mock. */
    protected Logger LOGGER = Logger.getLogger(LoggingEventListener.class);

    /** {@inheritDoc}*/
    public void onApplicationEvent(ApplicationEvent event) {
        // check event type (registered listeners will receive every App Event)
        if (event instanceof AuthenticationResponseReceivedEvent) {
            String message = ((AuthenticationResponseReceivedEvent) event).getMessage();
            String guid = ((AuthenticationResponseReceivedEvent) event).getGuid();

            String response = DelimitedStringHandler.nvpMapToString(
                    ((AuthenticationResponseReceivedEvent) event).getAuthResponseData(),
                    LogicGroupConstants.REC_SEPARATOR,
                    LogicGroupConstants.FIELD_SEPARATOR);
            // not adding isInfoEnable here as it fails junit testcases for this class
            LOGGER.info("3D Secure response received for - GUID: " +
                    guid + "; message: " + message + "\n[" +
                    response + "]");

        } else if (event instanceof BasePaymentEvent) {
            if (LOGGER.isDebugEnabled()) {
                String message = ((BasePaymentEvent) event).getMessage();
                String guid = ((BasePaymentEvent) event).getGuid();

                LOGGER.debug("Logging event [" +
                        event.getClass().getSimpleName() + " - GUID: " +
                        guid + "; message: " + message + "]");
            }
        }
    }
}
