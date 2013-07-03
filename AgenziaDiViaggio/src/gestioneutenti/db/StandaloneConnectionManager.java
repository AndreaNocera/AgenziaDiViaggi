/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.db
 * 
 * @name StandaloneConnectionManager.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StandaloneConnectionManager implements ConnectionManager {
	
	private static StandaloneConnectionManager singletonConnectionManager = null;
	
	private static final String URL = "jdbc:mysql://localhost:3306/Voyager";
	private static final String USER = "root";
	private static final String PASSWORD = "password";
	
	private String url = null;
	private String user = null;
	private String password = null;

	private StandaloneConnectionManager() {
		this.url = URL;
		this.user = USER;
		this.password = PASSWORD;
	}
	
	public static synchronized StandaloneConnectionManager getInstance() {
		if (singletonConnectionManager == null) {
			singletonConnectionManager = new StandaloneConnectionManager();
		}
		
		return singletonConnectionManager;
	}
	
	@Override
	public synchronized Connection getConnection() {
		try {
			return DriverManager.getConnection(this.url, this.user, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	@Override
	public synchronized void close(Connection connection) {
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
