import javax.management.ObjectName
import javax.management.MBeanServerConnection
import javax.naming.InitialContext

def initialContextFactory = "org.jboss.security.jndi.JndiLoginInitialContextFactory"
def JMX_INVOKER_RMI_ADAPTOR = "jmx/invoker/RMIAdaptor"
//
// Connect to JBoss
//
Properties props = new Properties(System.getProperties())
props.put("java.naming.factory.initial", initialContextFactory)
props.put("java.naming.provider.url", jmxrmihost + ":" + jmxrmiport)
props.put("java.naming.security.principal", jmxrmiusername)
props.put("java.naming.security.credentials", jmxrmipassword)
def ctx = new InitialContext(props)
try {
	Object obj = ctx.lookup(JMX_INVOKER_RMI_ADAPTOR)
	ctx.close()
	def server = (MBeanServerConnection) obj
 
	def queryString = "jboss.web.deployment:war=${warfilename},*"
	String[] allNames = server.queryNames(new ObjectName(queryString), null)

	if(allNames.size() != 0) {
		def warfileMBean = new GroovyMBean(server, allNames[0])
		project.log("     [undeploy-war] ${warfilename} state is ${warfileMBean.StateString}")

		Object[] arguments = new Object[1]
		arguments[0] = new String("file:///${deployDir}/${warfilename}")
		String[] signatures = new String[1]
		signatures[0] = new String("java.lang.String")

		project.log("     [undeploy-war] calling undeploy for " + arguments[0])
		server.invoke(new ObjectName("jboss.system:service=MainDeployer"), "undeploy", arguments, signatures)
	}
} catch (Throwable swallowed) {
	project.log('     [undeploy-war] encountered an error: ' + swallowed.getMessage())
}