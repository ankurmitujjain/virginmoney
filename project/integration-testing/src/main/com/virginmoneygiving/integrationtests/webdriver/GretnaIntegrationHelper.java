/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.ITable;

/**
 * Utility helper class for methods used by all helpers
 * @author saurabhh
 *
 */
public class GretnaIntegrationHelper {
	
	
	/**
	 * Method to get next id in a table based on table name/ Connection passed
	 * @param tableName
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public Integer getNextId(String tableName, IDatabaseConnection connection) throws Exception {
        QueryDataSet dataSet = new QueryDataSet(connection);
        dataSet.addTable("AUTO1", "select auto_increment from information_schema.tables where table_schema = 'giving' and table_name = '" + tableName + "'");
        ITable auto1 = dataSet.getTable("AUTO1");
        return Integer.parseInt(auto1.getValue(0, "auto_increment").toString());

    }	
	

}
