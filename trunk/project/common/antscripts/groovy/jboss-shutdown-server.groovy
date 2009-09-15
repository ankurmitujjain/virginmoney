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
Object obj = ctx.lookup(JMX_INVOKER_RMI_ADAPTOR)
def server = (MBeanServerConnection) obj
ctx.close()
 
def query = new ObjectName("jboss.system:type=Server")
String[] allNames = server.queryNames(query, null)

def serverMBean = new GroovyMBean(server, allNames[0])
project.log('     [server-shutdown] requesting server shutdown...')
serverMBean.shutdown()