package gestione_Catalogo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author
 * Francesco Tomei
 */
public final class Persistenza {
	private static final String driverName = "com.mysql.jdbc.Driver";
	private static final String dbName = "jdbc:mysql://localhost:3306/voyager";
	private static final String usr = "voyager";
	private static final String pass = "voyager";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbName, Persistenza.usr, Persistenza.pass);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
