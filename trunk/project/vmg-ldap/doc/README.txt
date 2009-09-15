Usage
---------------------------

1) Copy vmg-ldap.properties inside the poject's external-configuration folder.
   Example : giving-managment-ws project defines vmg-ldap as a dependency. 
             vmg-ldap.properties has been copied into giving-managment-ws -> conf/external-configuration folder. 

2) Modify the vmg-ldap.properties as specified in Appendix-A below

3) Set GIVING_CONF_HOME system property on jboss to point to external-configuration location.
   Example
   -DGIVING_CONF_HOME=C:/VMG-workspace/external-configuration


Appendix-A
------------------


   Modify external-configuration -> vmg-ldap.properties as shown below

   > ldap.url=ldap://{directory server location}:{directory server port}
     Replace placeholders to point to correct location
     Example:
     ldap.url=ldap://10.0.5.100:389 

   > ldap.base={Base domain controller pattern}
     Replace placeholders to point to correct domain controller structure of the active directory server.
     Example :
     ldap.base=dc=vmg,dc=com	
 	
   > ldap.adminName={Admin userDn}
     Replace placeholders to point to correct a userDn on the active directory server.
     This user account will be used to establish connection with active directory server and perform subsequent operations.
     Example :
     ldap.adminName=CN=Administrator,CN=Users,DC=vmg,DC=com

   > ldap.adminPassword={Admin user password}
     Replace placeholders to point to correct value.

   > ldap.giving.userbasedn={User base DN on the active directory server}
     Replace placeholders to point to correct userbase dn on the active directory server.
     #Example ldap.giving.userbasedn=ou=giving,ou=uk
 
   > ldap.giving.userbasednfilter={User based filter on the active directory server}
     Replace placeholders to point to correct userbasednfilter on the active directory server.
     This property will define the scope of user search on the active directory server tree.
     Example
     ldap.giving.userbasednfilter=cn={0},ou=giving,ou=uk

   > giving.default.group={DEFAULT USER GROUP}
     Replace placeholders to point to correct group on the active directory server.
     This property will be used to perform user group verification on the active directory server.
     Example:
     giving.default.group=GIVING


NOTE:
------
Active directory tree structure used in the example is setup as below

com (defined as domain controller (dc))
   -|
     vmg (defined as domain controller (dc))
        -|
          uk (defined as Organisational Unit (ou))
            -|
              giving (defined as Organisational Unit(ou))

** All user accounts are created under 'giving' organisational unit.	
** An active directory group GIVING has been created.
** Users are added to GIVING group.




