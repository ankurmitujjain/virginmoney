package com.virginmoneygiving.schedulerservice.listeners;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

import com.virginmoneygiving.schedulerservice.serviceproxy.AlertServiceProxy;

/**
 * Trigger listener that notifies the Alert Service when a Trigger misfire has
 * occurred.
 * @see TriggerListener
 * @author John Allen (john.allen@opsera.com)
 */
public class AlertServiceTriggerListener implements TriggerListener {

    /** logger. */
    private static final Logger LOGGER = Logger.getLogger(AlertServiceTriggerListener.class);

    /** Alert service proxy. */
    @Resource
    private AlertServiceProxy alertService;

    /** Listeners global name. */
    private static final String LISTENER_NAME = "auditServiceTriggerListener";


    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return LISTENER_NAME;
    }

    /**
     * Trigger complete.
     * @param trigger the trigger
     * @param context job context
     * @param triggerInstructionCode code
     * 
     * @see org.quartz.TriggerListener#triggerComplete(org.quartz.Trigger, org.quartz.JobExecutionContext, int)
     */
    public void triggerComplete(Trigger trigger, JobExecutionContext context, int triggerInstructionCode) {
        // we let the Quartz history plugin record normal trigger operations
    }

    /**
     * Trigger fired.
     * @param trigger the trigger
     * @param context job context
     * @see org.quartz.TriggerListener#triggerFired(org.quartz.Trigger, org.quartz.JobExecutionContext)
     */
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        // we let the Quartz history plugin record normal trigger operations
    }

    /**
     * Notifies the audit service and logs a message when a trigger misfire
     * occures.
     * @param trigger the trigger that has misfired
     * @see org.quartz.TriggerListener#triggerMisfired(org.quartz.Trigger)
     */
    public void triggerMisfired(Trigger trigger) {
        try {
            // invoke alert service
            getAlertService().raiseAlert("TriggerMisfired, trigger name: " + trigger.getFullName());
        } catch (Exception e) {
            LOGGER.error("Error raising alert [TriggerMisfired]", e);
        }
    }

    /**
     * Veto job execution.
     * @param trigger the trigger
     * @param context job context
     * @return whether veto should happen
     * @see org.quartz.TriggerListener#vetoJobExecution(org.quartz.Trigger, org.quartz.JobExecutionContext)
     */
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        // never veto from the listener
        return false;
    }

    /**
     * Sets the alert service.
     * @param alertService the alertService to set
     */
    public void setAlertService(AlertServiceProxy alertService) {
        this.alertService = alertService;
    }

    /**
     * Gets the alert service.
     * @return the alertService
     */
    public AlertServiceProxy getAlertService() {
        return alertService;
    }
}
