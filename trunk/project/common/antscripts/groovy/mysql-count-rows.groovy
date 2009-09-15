import groovy.sql.Sql

try {
    		project.log("     [mysql-count-rows] url: " + url)
    		project.log("     [mysql-count-rows] sql: " + sql)
				def sqlInstance = Sql.newInstance(url,
                   username, password,
                   "com.mysql.jdbc.Driver")    		
				def rows = sqlInstance.rows(sql)
				if ( rows.size() > 0 )
				{
						project.setProperty("rows.count", rows.size().toString() )
				}
	    	project.log("     [mysql-count-rows] rows: " + rows.size())
} 
catch (Throwable swallowed) {
	project.log('     [mysql-count-rows] error: ' + swallowed.getMessage())
}
