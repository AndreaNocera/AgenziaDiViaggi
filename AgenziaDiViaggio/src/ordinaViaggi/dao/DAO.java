package ordinaViaggi.dao;

import ordinaViaggi.exception.ConnectionException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author Gambella Riccardo
 *
 */
public abstract class DAO {
	protected static final String driverName = "com.mysql.jdbc.Driver";
	protected static final String connectionURL = "jdbc:mysql:";
	protected static final String URL = connectionURL + "//" + "localhost:3306"
			+ "/";
	protected static final String dbName = URL + "voyager";

	protected static final String usr = "root";
	protected static final String pass = "root";
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	protected Connection getConnection(String user, String password)
			throws ConnectionException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbName, user, password);
		} catch (SQLException sqle) {
			throw new ConnectionException("Errore nella connessione al DB.");
		}

		return conn;
	}

	protected  void closeResource() {
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
