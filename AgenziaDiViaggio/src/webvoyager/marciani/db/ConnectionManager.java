/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.db
 * 
 * @name ConnectionManager.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface ConnectionManager {
	
	public Connection getConnection();
	
	public void close(Connection connection);
	
	public void close(Connection connection, Statement statement);
	
	public void close(Connection connection, Statement statement, ResultSet resultSet);

}
