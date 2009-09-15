import javax.management.ObjectName
import javax.management.MBeanServerConnection
import javax.naming.InitialContext

def initialContextFactory = "org.jboss.security.jndi.JndiLoginInitialContextFactory"
def JMX_INVOKER_RMI_ADAPTOR = "jmx/invoker/RMIAdaptor"

try
{

// Connect to JBoss
Properties props = new Properties(System.getProperties())
props.put("java.naming.factory.initial", initialContextFactory)
props.put("java.naming.provider.url", jmxrmihost + ":" + jmxrmiport)
props.put("java.naming.security.principal", jmxrmiusername)
props.put("java.naming.security.credentials", jmxrmipassword)
def ctx = new InitialContext(props)


//TODO: get from props - so we can default...
if(!failonerror) {
	def failonerror = true
}

// deploy or undeploy
def mode = 'deploy'

if(project.getProperty('verifymode')) {
	mode = project.getProperty('verifymode')
}


	Object obj = ctx.lookup(JMX_INVOKER_RMI_ADAPTOR)
	def server = (MBeanServerConnection) obj
	ctx.close()
 
	def queryString = "jboss.web.deployment:war=${warfilename},*"
	//println queryString

	def query = new ObjectName(queryString)

	long maxWaitMillis = new Long(maxWait) * 1000
	if(maxWaitMillis < 100) maxWaitMillis = 500 // allow the while to execute at least once ;)
	
	long checkEveryMillis = new Long(checkEvery) * 1000
	
	long end = System.currentTimeMillis() + maxWaitMillis 
	
	project.log("     [find-war] finding ${warfilename} deployment status (timeout: ${maxWait} secs)")
		
	while ((System.currentTimeMillis() < end) ) {
		String[] allNames = server.queryNames(query, null)
		if(allNames.size() == 0) {
			project.log("     [find-war] ${warfilename} is reported as being: no-such-mbean")
			if(mode == 'undeploy') {
				project.setProperty('war.undeployed', "true")
				project.log("     [find-war] ${warfilename} is not deployed")			
				return;
			} 
		} else {
			def warfileMBean = new GroovyMBean(server, allNames[0])
			project.log("     [find-war] ${warfilename} is reported as being: ${warfileMBean.StateString}")
			if (warfileMBean.StateString == 'Started' && mode == 'deploy') {
					project.setProperty('war.deployed', "true")
					project.log("     [find-war] ${warfilename} is deployed")			
					return;
			} else if (warfileMBean.StateString == 'Failed') {
					project.log("     [find-war] ${warfilename} is in a Failed state")			
					return;
			}
		}		
		try {
			project.log('     [find-war] sleeping...')
			Thread.sleep(checkEveryMillis);
		} catch (InterruptedException e) {
			// ignore
		}
	}  
	
} catch (Throwable swallowed) {
	project.log('     [find-war] encountered an error: ' + swallowed.getMessage())
	if(failonerror) {
		throw new Throwable(swallowed)
	}
}