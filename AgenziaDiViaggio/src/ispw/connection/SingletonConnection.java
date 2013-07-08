package ispw.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	protected static final String driverName = "com.mysql.jdbc.Driver";
	protected static final String connectionURL = "jdbc:mysql:";
	protected static final String URL = connectionURL + "//" + "localhost:3306"
			+ "/";
	protected static final String dbName = URL + "voyager";

	protected static final String usr = "root";
	protected static final String pass = "root";
	
	
	private static SingletonConnection conn = null;
	private Connection connection;
	
	private SingletonConnection() {
		try {
			connection = DriverManager.getConnection(dbName, usr, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static /*synchronized*/ SingletonConnection getInstance(){
		if(conn == null)
			conn = new SingletonConnection();
		return conn;
	}
	
	public /*synchronized*/ Connection getConnection(){
		
		return connection;
	}
}
