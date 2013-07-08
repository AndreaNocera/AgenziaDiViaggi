/**
 * 
 */
package gestione_Catalogo.dao;

import gestione_Catalogo.entity.IDEsternoElemento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @authors
 * Remo Sperlongano
 * Ivan Torre
 */
public abstract class DAO {
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	protected Connection getConnection(String user, String password) {
		Connection conn = Persistenza.getConnection();
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
