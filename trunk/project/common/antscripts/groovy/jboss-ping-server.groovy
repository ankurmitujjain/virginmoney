import javax.management.ObjectName
import javax.management.MBeanServerConnection
import javax.naming.InitialContext

def initialContextFactory = "org.jboss.security.jndi.JndiLoginInitialContextFactory"
def JMX_INVOKER_RMI_ADAPTOR = "jmx/invoker/RMIAdaptor"

// Connect to JBoss
Properties props = new Properties(System.getProperties())
props.put("java.naming.factory.initial", initialContextFactory)
props.put("java.naming.provider.url", jmxrmihost + ":" + jmxrmiport)
props.put("java.naming.security.principal", jmxrmiusername)
props.put("java.naming.security.credentials", jmxrmipassword)
def ctx = new InitialContext(props)

def result = false

try {
	Object obj = ctx.lookup(JMX_INVOKER_RMI_ADAPTOR)
	def server = (MBeanServerConnection) obj
	ctx.close()
	 
	def query = new ObjectName("jboss.system:type=Server")
	String[] allNames = server.queryNames(query, null)
	
	def serverMBean = new GroovyMBean(server, allNames[0])
	
	def started = serverMBean.Started
	def inShutdown = serverMBean.InShutdown
	result = (started && !inShutdown)
} catch (Throwable swallowed) {
	project.log('     [server-ping] Encountered an error: ' + swallowed.getMessage())
}
	
project.setProperty('jboss.status', "${result}")
if ( result ){
	project.setProperty('jboss.running', "true")
}