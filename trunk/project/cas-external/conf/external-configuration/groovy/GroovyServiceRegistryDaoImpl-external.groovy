import org.jasig.cas.services.*
import org.jasig.cas.util.DefaultLongNumericGenerator
import org.jasig.cas.util.LongNumericGenerator

/**
 * Based on In Memory impl.
 * @author Robin Bramley, Opsera Ltd.
 */
public class GroovyServiceRegistryDaoImpl implements ServiceRegistryDao {

    // number generator
    def generator = new DefaultLongNumericGenerator();

    // declare records here
    // will have defaults of - enabled:true, ssoEnabled:true, allowedToProxy:true, anonymousAccess:true
    def r1 = new RegisteredServiceImpl(id:generator.getNextLong(), name:'VMG', description:'VMG apps', ignoreAttributes:true, serviceId:'http://**.vmoney.local/**')
    def r2 = new RegisteredServiceImpl(id:generator.getNextLong(), name:'VMG', description:'VMG apps', ignoreAttributes:true, serviceId:'https://**.vmoney.local/**')
    def r3 = new RegisteredServiceImpl(id:generator.getNextLong(), name:'LOCAL', description:'Local access', ignoreAttributes:true, serviceId:'https://localhost:8443/**')
    def r4 = new RegisteredServiceImpl(id:generator.getNextLong(), name:'LOCAL', description:'Local access', ignoreAttributes:true, serviceId:'http://localhost:8080/**')
    def r5 = new RegisteredServiceImpl(id:generator.getNextLong(), name:'VMG', description:'Prod access', ignoreAttributes:true, serviceId:'http://**.virginmoneygiving.com/**')
    def r6 = new RegisteredServiceImpl(id:generator.getNextLong(), name:'VMG', description:'Prod access', ignoreAttributes:true, serviceId:'https://**.virginmoneygiving.com/**')
    // following entries to be removed for prod
    def opsera_https = new RegisteredServiceImpl(id:generator.getNextLong(), name:'Opsera', description:'Opsera https', ignoreAttributes:true, serviceId:'https://**.opsera.com:8443/**')
    def opsera_http = new RegisteredServiceImpl(id:generator.getNextLong(), name:'Opsera', description:'Opsera http', ignoreAttributes:true, serviceId:'http://**.opsera.com:8080/**')

    // add records into this list
    def registeredServices = [r1, r2, r3, r4, r5, r6, opsera_https, opsera_http]
    
    // rest of impl borrowed from in memory impl
    public boolean delete(RegisteredService registeredService) {
        return this.registeredServices.remove(registeredService);
    }

    public RegisteredService findServiceById(final long id) {
		println 'find called ' + id
        for (RegisteredService r : this.registeredServices) {
            if (r.getId() == id) {
                return r;
            }
        }
        
        return null;
    }

    public List<RegisteredService> load() {
		println 'load called'
        return this.registeredServices;
    }

    public RegisteredService save(final RegisteredService registeredService) {
        if (registeredService.getId() == -1) {
            ((RegisteredServiceImpl) registeredService).setId(this.generator.getNextLong());
        }

        this.registeredServices.remove(registeredService);
        this.registeredServices.add(registeredService);
        
        return registeredService;
    }

    public void setRegisteredServices(final List<RegisteredService> registeredServices) {
        this.registeredServices = registeredServices;
    }
}
