package gestioneutenti.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	
	private static ConnectionManager singletonConnectionManager = null;
	
	private DataSource dataSource;
    private Connection connection;

	private ConnectionManager() {
		try {
            Context initContext  = new InitialContext();
            Context envContext  = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/VoyagerUtentiDB");             
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}
	
	public static synchronized ConnectionManager getInstance() {
		if (singletonConnectionManager == null) {
			singletonConnectionManager = new ConnectionManager();
		}
		
		return singletonConnectionManager;
	}
	
	public Connection getConnection() {
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void closeConnection() {
		
	}
	
	public void createDB() {
		
	}
	
	public synchronized void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
	}
	
	public synchronized void close(Statement statement, Connection connection) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		
		close(connection);
	}
	
	public synchronized void close(Statement statement, ResultSet result, Connection connection) {
		if (result != null) {
			try {
				result.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		
		close(statement, connection);
	}

}
