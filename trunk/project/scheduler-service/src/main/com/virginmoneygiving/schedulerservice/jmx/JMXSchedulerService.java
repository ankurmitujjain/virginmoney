package com.virginmoneygiving.schedulerservice.jmx;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Exposes Quartz scheduler and basic trigger management operations via JMX.
 * @author John Allen (john.allen@opsera.com)
 */
@ManagedResource(objectName = "VirginMoneyGiving:name=schedulerService", description = "VMG Scheduler Service")
public class JMXSchedulerService {

    /** Logger for use by this class. */
    private static Logger log = Logger.getLogger(JMXSchedulerService.class);

    /** The scheduler we're managing. */
    private Scheduler scheduler;

    /**
     * start the scheduler service, use after the scheduler has been paused().
     * @throws SchedulerException exception
     */
    @ManagedOperation(description = "Start the scheduler service, use after the scheduler has been paused()")
    public void startScheduler() throws SchedulerException {
        getScheduler().start();
    }

    /**
     * Permanently stops the scheduler service, please note you can NOT restart
     * it once its been shutdown, use the pause() operation if you wish to
     * temporarily stop the scheduler.
     * @param waitForJobsToComplete whether to wait for jobs to complete
     * 
     * @throws SchedulerException exception
     */
    @ManagedOperation(description = "Permanently stops the scheduler service, please note "
            + "you can not restart it once its been shutdown, use the pause() operation if you " + "wish to temporarily stop the scheduler.")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "waitForJobsToComplete",
            description = "Wait for any running jobs to complete before shutting down") })
    public void shutdownSheduler(final boolean waitForJobsToComplete) throws SchedulerException {
        getScheduler().shutdown(waitForJobsToComplete);
    }

    /**
     * Put the scheduler into stand-by mode; use the start() operation to
     * restart the scheduler after its been put into stand-by mode.
     * @throws SchedulerException exception
     */
    @ManagedOperation(description = "Put the scheduler into stand-by mode; use the start()"
            + "operation to restart the scheduler after it's been put into stand-by mode.")
    public void standbyScheduler() throws SchedulerException {
        getScheduler().standby();
    }

    /**
     * Display the current state of the scheduler, one of either 'running',
     * 'standing-by' or 'shutdown'.
     * @return the string representation of the scheduler's state
     * @throws SchedulerException exception
     */
    @ManagedOperation(description = "Displays the current run state of the scheduler, " + "one of either 'running', 'standing-by' or 'shutdown'")
    public String showSchedulerState() throws SchedulerException {
        String status;
        if (getScheduler().isStarted()) {
            status = "running";
        } else if (getScheduler().isInStandbyMode()) {
            status = "standing-by";
        } else if (getScheduler().isShutdown()) {
            status = "shutdown";
        } else {
            status = "unknown";
        }
        return status;
    }

    /**
     * Display the scheduler's name.
     * @return the name
     * @throws SchedulerException exception
     */
    @ManagedOperation(description = "Displays the scheduler's name")
    public String showSchedulerName() throws SchedulerException {
        return getScheduler().getSchedulerName();
    }

    /**
     * Lists all the trigger group names known by the system.
     * @return the trigger group names
     * @throws SchedulerException exception
     */
    @ManagedOperation(description = "Lists all the trigger groups known by the system")
    public List<String> listTriggerGroups() throws SchedulerException {
        return Arrays.asList(getScheduler().getTriggerGroupNames());
    }

    /**
     * Lists all the triggers in a specific trigger group.
     * @param groupName group name
     * @return the trigger group names
     * @throws SchedulerException exception
     */
    @ManagedOperation(description = "Lists all the triggers in a specific trigger group. Call "
            + "listTriggerGroups() to get a list of the trigger groups within the system.")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "groupName", description = "The trigger group") })
    public List<String> listTriggersInGroup(final String groupName) throws SchedulerException {
        return Arrays.asList(getScheduler().getTriggerNames(groupName));
    }

    /**
     * Lists all the triggers known to the scheduler, trigger names are returned
     * using the Quartz 'full name' format, defined as triggerGroup.triggerName.
     * @return The list of triggers in the system, represented by their FQNs
     * @throws SchedulerException exception
     */
    @ManagedOperation(description = "Lists all the triggers known to the scheduler , trigger "
            + "names are returned in a the Quartz 'full name' format, defined as triggerGroup.triggerName")
    public List<String> listAllTriggers() throws SchedulerException {
        List<String> triggerFQNs = new ArrayList<String>();
        for (String triggerGroup : listTriggerGroups()) {
            String[] triggerNames = scheduler.getTriggerNames(triggerGroup);
            for (String triggerName : triggerNames) {
                triggerFQNs.add(triggerGroup + "." + triggerName);
            }
        }
        return triggerFQNs;
    }

    /**
     * Reschedules a CronTrigger with a new Cron expression.
     * @param cronTriggerFQN the trigger identifier.
     * @param cronExpression the new cron expression
     * @throws SchedulerException exception if the trigger is not found or the
     * passed trigger FQN is invalid
     * @throws ParseException if the cron expression could not be parsed.
     */
    @ManagedOperation(description = "Changes the specified CronTrigger's Cron expression and reschedules it")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "cronTriggerFQN", description = "The trigger's fully qualified name (FQN), "
                    + "FQN is defined as triggerGroup.triggerName"),
            @ManagedOperationParameter(name = "cronExpression", description = "The new schedule for the trigger expressed as a cron expression") })
    public void rescheduleCronTrigger(final String cronTriggerFQN, final String cronExpression) throws SchedulerException, ParseException {

        CronTrigger cronTrigger = getCronTrigger(cronTriggerFQN);
        String oldCron = cronTrigger.getCronExpression();
        cronTrigger.setCronExpression(cronExpression);
        log.info("Changed trigger: '" + cronTrigger + "', cron expresion from: '" + oldCron + "' to: '" + cronExpression + "'");

        // HunarC: Changed from unschedule/schedule to reschedule. unscheduleJob deletes the Job details,
        // and as a result the scheduleJob fails. Reschedule is better, as it rewrites the Cron trigger, instead of
        // deleting and creating a new one.
        //scheduler.unscheduleJob(cronTrigger.getName(), cronTrigger.getGroup());
        //scheduler.scheduleJob(cronTrigger);
        scheduler.rescheduleJob(cronTrigger.getName(), cronTrigger.getGroup(), cronTrigger);
    }

    /**
     * Displays the trigger data map for the specified trigger.
     * @param triggerFQN the trigger identifier.
     * @return data map
     * @throws SchedulerException if the trigger is not found or the passed
     * trigger FQN is invalid
     */
    @ManagedOperation(description = "Displays the trigger data map for the specified trigger")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "triggerFQN", description = "The trigger's fully qualified name (FQN), "
            + "FQN is defined as triggerGroup.triggerName") })
    public String showTriggerDataMap(final String triggerFQN) throws SchedulerException {
        // expect there is a much nicer way of doing this...
        StringBuffer result = new StringBuffer();
        result.append('[');
        Trigger trigger = getTrigger(triggerFQN);
        for (Iterator<Map.Entry<String, Object>> itr = trigger.getJobDataMap().entrySet().iterator(); itr.hasNext();) { // CHECKSTYLE:OFF
            Map.Entry<String, Object> entry = itr.next();
            result.append('{');
            result.append(entry.getKey());
            result.append('=');
            result.append(entry.getValue());
            result.append('}');
            if (itr.hasNext()) {
                result.append(',');
            }
        }
        result.append(']');
        return result.toString();
    }

    /**
     * Modifies (add/change/delete) the specified trigger's data map with the
     * specified value. Delete is indicated by passing the literal string value
     * of "null" as the value field.
     * @param triggerFQN the trigger identifier.
     * @param dataKey the data map key.
     * @param dataValue the data map value. Delete is indicated by passing the
     * literal string value of "null" as the value field
     * @throws SchedulerException exception if the trigger is not found or the
     * passed trigger FQN is invalid
     */
    @ManagedOperation(description = "Modifies (add/change/delete) the specified trigger's "
            + "data map with the specified value. Delete is indicated by passing the literal " + "string value of \"null\" as the value field")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "triggerFQN", description = "The trigger's fully qualified name (FQN), "
                    + "FQN is defined as triggerGroup.triggerName"), @ManagedOperationParameter(name = "dataKey", description = "The data map key"),
            @ManagedOperationParameter(name = "dataValue", description = "The data map value") })
    public void setTriggerDataMapEntry(final String triggerFQN, final String dataKey, String dataValue) throws SchedulerException {

        Trigger trigger = getTrigger(triggerFQN);

        String oldValue = trigger.getJobDataMap().getString(dataKey);
        if ("null".equals(dataValue)) {
            trigger.getJobDataMap().remove(dataKey);
            log.info("Removed trigger: '" + trigger + "' data map entry '" + dataKey + "=" + oldValue + "'");
        } else {
            trigger.getJobDataMap().put(dataKey, dataValue);
            log.info("Updated trigger: '" + trigger + "' data map entry from '" + dataKey + "=" + oldValue + "' to '" + dataKey + "=" + dataValue + "'");
        }
        scheduler.unscheduleJob(trigger.getName(), trigger.getGroup());
        scheduler.scheduleJob(trigger);

    }

    /**
     * Displays the specified CronTrigger's Cron expression.
     * @param cronTriggerFQN the trigger's FQN.
     * @return the trigger's cron expression.
     * @throws SchedulerException the scheduler exception
     */
    @ManagedOperation(description = "Displays the specified CronTrigger's Cron expression")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "cronTriggerFQN", description = "The trigger's fully qualified name "
            + "(FQN), FQN is defined as triggerGroup.triggerName") })
    public String showCronTriggerCronExpression(final String cronTriggerFQN) throws SchedulerException {
        return getCronTrigger(cronTriggerFQN).getCronExpression();
    }

    /**
     * Pauses the specified trigger; you can resume it again later with the
     * resumeTrigger() operation.
     * @param triggerFQN the trigger's FQN.
     * @throws SchedulerException the scheduler exception
     */
    @ManagedOperation(description = "Pauses the specified trigger; you can resume " + "it again later with the resumeTrigger() operation.")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "triggerFQN", description = "The trigger's fully qualified name "
            + "(FQN), FQN is defined as triggerGroup.triggerName") })
    public void pauseTrigger(final String triggerFQN) throws SchedulerException {
        Trigger trigger = getTrigger(triggerFQN);
        getScheduler().pauseTrigger(trigger.getName(), trigger.getGroup());
    }

    /**
     * Resume a previously paused trigger.
     * @param triggerFQN the trigger's FQN.
     * @throws SchedulerException the scheduler exception
     */
    @ManagedOperation(description = "Resume a previously paused trigger")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "triggerFQN", description = "The trigger's fully qualified name "
            + "(FQN), FQN is defined as triggerGroup.triggerName") })
    public void resumeTrigger(final String triggerFQN) throws SchedulerException {
        Trigger trigger = getTrigger(triggerFQN);
        getScheduler().resumeTrigger(trigger.getName(), trigger.getGroup());
    }

    /**
     * Show the run state of the specified trigger; one of either 'normal',
     * 'paused', 'error', 'blocked' or 'complete'".
     * @param triggerFQN the trigger's FQN.
     * @return the string representation of the trigger's state
     * @throws SchedulerException the scheduler exception
     */
    @ManagedOperation(description = "Show the run state of the specified trigger; " + "one of either 'normal', 'paused', 'error', 'blocked' or 'complete'")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "triggerFQN", description = "The trigger's fully qualified name "
            + "(FQN), FQN is defined as triggerGroup.triggerName") })
    public String showTriggerState(final String triggerFQN) throws SchedulerException {
        Trigger trigger = getTrigger(triggerFQN);
        int stateCode = getScheduler().getTriggerState(trigger.getName(), trigger.getGroup());
        String state;
        if (stateCode == Trigger.STATE_NORMAL) {
            state = "normal";
        } else if (stateCode == Trigger.STATE_BLOCKED) {
            state = "blocked";
        } else if (stateCode == Trigger.STATE_COMPLETE) {
            state = "complete";
        } else if (stateCode == Trigger.STATE_ERROR) {
            state = "error";
        } else if (stateCode == Trigger.STATE_PAUSED) {
            state = "paused";
        } else {
            state = "unknown";
        }
        return state;
    }

    /**
     * Pause an entire group of triggers; you can resume them all again later
     * with the resumeTriggerGroup() operation.
     * @param triggerGroup the trigger group's name.
     * @throws SchedulerException exception
     */
    @ManagedOperation(description = "Pause an entire group of triggers; you can resume them " + "all again later with the resumeTriggerGroup() operation")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "triggerGroup", description = "Thethe trigger group's name") })
    public void pauseTriggerGroup(final String triggerGroup) throws SchedulerException {
        getScheduler().pauseTriggerGroup(triggerGroup);

    }

    /**
     * Resume an entire group of previously paused triggers.
     * @param triggerGroup the trigger group's name.
     * @throws SchedulerException exception
     */
    @ManagedOperation(description = "Resume an entire group of previously paused triggers")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "triggerGroup", description = "Thethe trigger group's name") })
    public void resumeTriggerGroup(final String triggerGroup) throws SchedulerException {
        getScheduler().resumeTriggerGroup(triggerGroup);
    }

    // NON JMX OPS

    /**
     * locate and return the specified CronTrigger by it's trigger fully.
     * qualified name (FQN)
     * @param cronTriggerFQN the trigger identifier.
     * @return the cron trigger
     * @throws SchedulerException if the trigger is not found or the passed
     * trigger FQN is invalid or the specified trigger is not a Cron
     * trigger
     */
    protected CronTrigger getCronTrigger(final String cronTriggerFQN) throws SchedulerException {
        Trigger trigger = getTrigger(cronTriggerFQN);
        if (!(trigger instanceof CronTrigger)) {
            throw new IllegalArgumentException("Specified trigger: '" + cronTriggerFQN + "' is not a CronTrigger");
        }
        return (CronTrigger) trigger;
    }

    /**
     * locate and return the specified Trigger by it's trigger fully qualified.
     * name (FQN)
     * @param triggerFQN the trigger identifier.
     * @return the Trigger
     * @throws SchedulerException if the trigger is not found or the passed
     * trigger FQN is invalid
     */
    protected Trigger getTrigger(final String triggerFQN) throws SchedulerException {
        String[] fqnParts = JMXSchedulerService.splitTriggerFQN(triggerFQN);
        String triggerGroup = fqnParts[0];
        String triggerName = fqnParts[1];

        Trigger trigger = getScheduler().getTrigger(triggerName, triggerGroup);
        if (trigger == null) {
            throw new IllegalArgumentException("Specified trigger '" + triggerFQN + "' not found");
        }
        return trigger;
    }

    /**
     * Utility helper that splits the Trigger FQN into its component parts.
     * @param triggerFQN the trigger FQN, expected to be in the format
     * triggerGroup.triggerName
     * @return the two parts of the FQN
     */
    public static String[] splitTriggerFQN(final String triggerFQN) {
        int delimiterIndex = triggerFQN.indexOf('.');
        if (delimiterIndex == -1 || delimiterIndex == 0 || delimiterIndex + 1 == triggerFQN.length()
                || triggerFQN.substring(delimiterIndex + 1).indexOf('.') != -1) {
            throw new IllegalArgumentException("Illegal trigger FQN, FQN syntax is 'triggerGroup.triggerName'");
        }

        String[] parts = new String[2];
        parts[0] = triggerFQN.substring(0, delimiterIndex);
        parts[1] = triggerFQN.substring(delimiterIndex + 1);
        return parts;
    }

    /**
     * Returns the scheduler instance this class is managing.
     * @return the scheduler instance.
     */
    public Scheduler getScheduler() {
        return this.scheduler;
    }

    /**
     * Set the scheduler instance that this class is managing.
     * @param scheduler the scheduler instance.
     */
    @Resource
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
