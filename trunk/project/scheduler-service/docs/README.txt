Virgin Money Giving Scheduler Service
-------------------------------------

This document gives an overview of the service and highlights its configuration settings
and dependencies. For details of building and deploying this service see the end of the
document.


Jobs 
----

This component causes the following 'jobs' to be run at specific times by the system:

	* REGULAR_CARD_PAYMENTS
	* GENERAL_LEDGER_CASH_FEEDS
	* GENERAL_LEDGER_PAYMENTS_RECEIVED
	* GENERAL_LEDGER_CHARITY_REGISTRATION
	* GENERAL_LEDGER_CHARITY_UPDATE
	* BAC_REGISTRATION
	* BAC_CASH_BACK


Schedules
---------

All the jobs are currently configured to run with the same frequency via the cron expression 
'*/10 * * * * ?' which means run every 2 minutes forever and ever and ever. These obviously 
needs to be changed to the correct times/dates before the system goes into production.

The initial schedule information used by the system can be changed by editing the job schedule
properties (see later for customisation instructions) however it is important to note that 
once the service has been setup and run for the first time the job and schedule information 
is stored in the service's database and is no longer read from the initial set-up files. 

Therefore changing these timing properties (i.e. the cron expressions) in the property files
will have no effect on an already running system. Instead you must change you the schedule
information by using the service's JMX management interface (see later). If however you
are in development or testing, you can simply reset the system back to its initial state 
by re-running the database setup (see later for deployment instructions), this will 
cause the service to recreate the job schedules from the file and property specified 
settings.

The following properties are used control the INITIAL cron expressions for the jobs:

scheduler.trigger.bacCashBackTrigger.cronExpression=*/10 * * * * ?
scheduler.trigger.bacRegistrationTrigger.cronExpression=*/10 * * * * ?
scheduler.trigger.generalLedgerCharityUpdateTrigger.cronExpression=*/10 * * * * ?
scheduler.trigger.generalLedgerCharityRegistrationTrigger.cronExpression=*/10 * * * * ?
scheduler.trigger.generalLedgerPaymentsReceivedTrigger.cronExpression=*/10 * * * * ?
scheduler.trigger.generalLedgerCashFeedsTrigger.cronExpression=*/10 * * * * ?
scheduler.trigger.regularCardPaymentsTrigger.cronExpression=*/10 * * * * ?

To change these values to some other value simply re-define them in a file called 
scheduler-service.properties file included in the WAR.


Batch Job Invocation
--------------------

The service does not run the batch jobs itself, instead it requests that a specific batch 
job is run by the Batch Job Service, a component outside of the scope of this
document. The request to run a job is sent to the Batch Job Service via a JMS Text Message
with an empty message body and a message header property of 'batchJobName' containing one of 
the following strings:

	* REGULAR_CARD_PAYMENTS
	* GENERAL_LEDGER_CASH_FEEDS
	* GENERAL_LEDGER_PAYMENTS_RECEIVED
	* GENERAL_LEDGER_CHARITY_REGISTRATION
	* GENERAL_LEDGER_CHARITY_UPDATE
	* BAC_REGISTRATION
	* BAC_CASH_BACK

Depending on the type of job the message is sent to either the Giving Batch Job service or 
the Payment Batch Job service. The specific JNDI name used to identify the JMS queue for 
each of these two services is configured by the the following properties:

scheduler.jms.givingbatchJobsDestination.jndiName=queue/givingBatchJobRequestQueue
scheduler.jms.paymentbatchJobsDestination.jndiName=queue/paymentBatchJobRequestQueue

To change these values to some other value simply re-define them in a file called 
scheduler-service.properties file included in the WAR.

However please note that in the same way that scheduling information is stored in the 
database, so are these values. Therefore to change them in a running system you must either
use the service's JMX interface or reset the database back to its initial clean state.

As stated above, each job targets a specific Batch Job Service. The following details 
the mapping of jobs to Batch Job services:

	* REGULAR_CARD_PAYMENTS 								- Giving Batch Job Service
	* GENERAL_LEDGER_CASH_FEEDS							- Payment Batch Job Service
	* GENERAL_LEDGER_PAYMENTS_RECEIVED			- Payment Batch Job Service
	* GENERAL_LEDGER_CHARITY_REGISTRATION		- Giving Batch Job Service
	* GENERAL_LEDGER_CHARITY_UPDATE					- Giving Batch Job Service
	* BAC_REGISTRATION											- Giving Batch Job Service
	* BAC_CASH_BACK													- Payment Batch Job Service


Misfires and Auditing
---------------------

If a job misses its execution time, say because the scheduler was shutdown or 
unavailable, a misfire is said to have occurred. When this is detected by the system, the
system sends a message to the audit service (not yet implemented!) to record the event 
as well as logging the event in application server's log file. The misfired job 
is then rescheduled for execution at its next regular scheduled time.

In the same way that the Batch Job Services are located via JNDI names, so is the 
Audit Service. The specific JNDI name used to identify the Audit Service's JMS queue is 
configued by the the following property:

scheduler.jms.auditService.jndiName=queue/auditServiceQueue

To change these values to some other value simply re-define them in a file called 
scheduler-service.properties file included in the WAR.


Customisation via JMX 
---------------------

The scheduler service allows you to control the underlying scheduler and change the registered 
batch job trigger cron schedules at run-time. The service can be located in the JBoss JMX 
console under the name VMG:name=jmxSchdulerService and exposes the following operations:

	* startScheduler								-	Start the scheduler service, use after the scheduler has 
																		been put into standby mode by standbyScheduler()
	* shutdownSheduler							- Permanently stops the scheduler service, please note you can 
																		NOT restart it once its been shutdown, use the standbyScheduler() 
																		operation if you wish to temporarily stop the scheduler
	* standbyScheduler							- Pause the scheduler service; use the startScheduler() 
																		operation to restart the scheduler after its been paused
	* showSchedulerState						- Displays the current run state of the scheduler, one of either
																		'running', 'standing-by' or 'shutdown'
	* showSchedulerName							- Displays the schedulers registered name
	* listTriggerGroups							- Lists all the registered trigger group names
	* listTriggersInGroup						- Lists all the triggers in a specific trigger group. Call 
																		listTriggerGroups() to get a list of the trigger groups 
																		within the system
	* listAllTriggers								- Lists all the triggers known to the scheduler , trigger 
																		names are returned in a the Quartz 'full name' format, 
																		defined as triggerGroup.triggerName
	* rescheduleCronTrigger					- Reschedules a CronTrigger with a new Cron expression	
	* showCronTriggerCronExpression - Displays the specified CronTrigger's Cron expression
	* pauseTrigger									- Pauses the specified trigger; you can resume it again later 
																		with the resumeTrigger() operation
	* resumeTrigger									- Resume a previously paused trigger
	* showTriggerState							- Show the run state of the specified trigger; one of either 
																		'normal', 'paused', 'error', 'blocked' or 'complete'
	* pauseTriggerGroup							- Pause an entire group of triggers; you can resume them all 
																		again later with the resumeTriggerGroup() operation
	* resumeTriggerGroup						- Resume an entire group of previously paused triggers
	

Adding new Batch Jobs
---------------------

Currently there is no way to do this via the JMX interface. However you can add new Batch Jobs 
to the scheduler by editing the scheduler-context.xml file contained within the scheduler service
WAR and then restarting the service. Please note that the Batch Job Service will need to know 
about the new Batch Job you are requesting be run. 

The Batch Job to run is specified by the batchJobName attribute on the BatchJobCronTrigger POJO.


Dependencies
------------

The schedule service depends on the following resources which are all JNDI located. The customisation 
property name and the default value for these dependencies are also shown. As these are system bindings
any changes should really be made to conf/main/WEB-INF/resources/scheduler-service.properties file
rather than the external configuration file.

* Giving Batch Service 		- scheduler.quartz.jobStore.dataSource.jndiName=java:/VmgQuartzDS
* Payment Batch Service 	- scheduler.jms.givingBatchJobsDestination.jndiName=queue/givingBatchJobServiceQueue
* Audit Service 					- scheduler.jms.auditService.jndiName=queue/auditServiceQueue
* VmgQuartzDS 						- scheduler.quartz.jobStore.dataSource.jndiName=java:/VmgQuartzDS


External Configuration
----------------------

The service reads two configuration files at start-up. One that is included in the WAR itself 
(WEB-INF/resources/scheduler-service.properties) which is not intended for external (read customer)
customisation, and another which is read from the JBoss 'GIVING_HOME' external cojnfig location
which is intended for customer configuration. Note all the properties defined in the internal
WAR property file can be overriden by the external config version.


Building
--------

Standard VM build process

Deployment
----------

See DEPLOYMENT.txt for details of deploying the component
