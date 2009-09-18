package com.virginmoneygiving.givingbatchcontroller.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.core.Message;

/**
 * Gateway interface.
 * User: choprah
 * Date: 05-May-2009
 * Time: 15:06:36
 */
public interface BatchGateway {

    /**
     * Gateway to start the job.
     * 
     * @param trigger Message to initiate.
     */
    @Gateway(requestChannel="triggers")
    void triggerJob(Message trigger);
}
