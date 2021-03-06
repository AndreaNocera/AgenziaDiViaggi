package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voyager.nove.exception.ConnectionException;
import voyager.nove.exception.DAOException;
import voyager.nove.exception.DataException;
import voyager.nove.persistence.manager.ConnectionManager;
import voyager.nove.persistence.manager.JDBCConnectionManager;

public abstract class DAO {
	/*
	protected static final String driverName = "com.mysql.jdbc.Driver";
	protected static final String connectionURL = "jdbc:mysql:";
	protected static final String URL = connectionURL + "//" + "localhost:3306"
			+ "/";
	protected static final String dbName = URL + "voyager";

	protected static final String usr = "root";
	protected static final String pass = "root";*/
	
	private ConnectionManager conectionManager = null;

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	protected synchronized Connection getConnection() throws ConnectionException {
		Connection conn = null;
		conn = JDBCConnectionManager.getInstance().getConnection();
		return conn;
	}

	protected synchronized void closeResource() {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public abstract void insert(Object obj) throws DAOException;

	public abstract Object read(Integer id) throws DAOException, DataException;

	public abstract void update(Object obj) throws DAOException;

	public abstract void delete(Object obj) throws DAOException, SQLException;
}
