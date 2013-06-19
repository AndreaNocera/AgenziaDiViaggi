package ordinaViaggi.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.ConnectionException;

public class ConnectionFactoryHSQL implements IConnectionFactory{
	private static final String driverName = "org.hsqldb.jdbcDriver";
	private static final String connectionURL = "jdbc:hsqldb:file:";
	private static final String dbName = connectionURL + "hlogin.sql";
	
	private static IConnectionFactory factory = null;
	
	private ConnectionFactoryHSQL(){
		try{
			Class.forName(driverName);
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection(String u, String pass) throws ConnectionException {		
		Connection conn = null;
		
		try{
			conn = DriverManager.getConnection(dbName, u, pass);
		}catch(SQLException sqle){
			throw new ConnectionException();
		}
		
		return conn;
	}
	
	public static IConnectionFactory getInstance(){
		if(factory == null)
			factory = new ConnectionFactoryHSQL();
		
		return factory;
	}
}