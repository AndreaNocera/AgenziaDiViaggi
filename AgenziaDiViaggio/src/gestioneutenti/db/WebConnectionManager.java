/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.db
 * 
 * @name WebConnectionManager.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WebConnectionManager implements ConnectionManager {
	
	private static WebConnectionManager singletonConnectionManager = null;
	
	private DataSource dataSource;
    private Connection connection;

	private WebConnectionManager() {
		try {
            Context initContext  = new InitialContext();
            Context envContext  = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/VoyagerDB");             
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}
	
	public static synchronized WebConnectionManager getInstance() {
		if (singletonConnectionManager == null) {
			singletonConnectionManager = new WebConnectionManager();
		}
		
		return singletonConnectionManager;
	}
	
	@Override
	public synchronized Connection getConnection() {
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	@Override
	public void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}		
	}
	
	@Override
	public synchronized void close(Connection connection, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		
		close(connection);
	}
	
	@Override
	public synchronized void close(Connection connection, Statement statement, ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		
		close(connection, statement);
	}	

}
