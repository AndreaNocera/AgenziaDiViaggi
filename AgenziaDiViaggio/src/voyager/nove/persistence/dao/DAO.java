package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voyager.nove.persistence.manager.ConnectionManager;
import voyager.nove.persistence.manager.JDBCConnectionManager;

public abstract class DAO {
	
	private static ConnectionManager connectionManager;
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	protected Connection getConnection(String user, String password) {
		connectionManager = JDBCConnectionManager.getInstance();
		conn = connectionManager.getConnection();
		return conn;
	}

	protected  void closeResource() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
}
