/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class CatalogoDAO {

	private static final String driverName = "com.mysql.jdbc.Driver";
	private static final String connectionURL = "jdbc:mysql:";
	private static final String URL = connectionURL + "//" + "localhost:3306" + "/";
	private static final String dbName = URL + "voyager";
	
	private static final String usr = "voyager";
	private static final String pass = "voyager";
	
	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS CATALOGO(" +
					"ID VARCHAR(20) PRIMARY KEY, " +
					"AMBIENTE VARCHAR(20), " +
					"MEZZO VARCHAR(20), " +
					"STAZIONEPARTENZA VARCHAR(20), " +
					"STAZIONEARRIVO VARCHAR(20), " +
					"STAZIONEINTERMEDIA VARCHAR(20)" +
					")";
	
	private static final String insertQuery = 
			"INSERT INTO CATALOGO " +
			"VALUES(?, ?, ?, ?, ?, ?)";
	private static final String updateQuery = 
			"UPDATE CATALOGO SET " +
			"ID=?, AMBIENTE=?, MEZZO=?, CITTAPARTENZA=?, CITTAARRIVO=?, VIA=? " +
			"WHERE ID=?";
	private static final String deleteQuery = 
			"DELETE FROM " +
			"CATALOGO WHERE ID=?";
	private static final String findQuery = 
			"SELECT * FROM CATALOGO " +
			"WHERE ID=?";

	private static final String dropQuery = 
			"DROP TABLE CATALOGO IF EXISTS";
	
	
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public CatalogoDAO(){
		try {
			Class.forName(driverName);
			
			conn = getConnection(usr, pass);
		
			ps = conn.prepareStatement(createQuery);
			
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeResource();
		}
	}
	
	private Connection getConnection(String user, String password){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(dbName, user, password);
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return conn;
	}
	
	private static void closeResource(){
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	public static void create(MappaCatalogo mappa) {
		if (mappa == null)
			return;

		try {
			Connection conn = null;
			PreparedStatement ps = null;
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(dbName, usr, pass);
			ps = conn.prepareStatement(insertQuery);

			/*ps.setString(1, dato.getId());
			ps.setString(2, dato.getAmbiente());
			ps.setString(3, dato.getMezzo());
			ps.setString(4, dato.getCittaPartenza());
			ps.setString(5, dato.getCittaArrivo());
			ps.setString(6, dato.getVia());
            */
			

			ps.executeUpdate();
		} 
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}

	//read(id) == findByPrimaryKey(id)
	
	public MappaCatalogo read(String id) {
		if (id == null)
			return null;

		MappaCatalogo result = null;

		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(findQuery);

			ps.setString(1, id);

			rs = ps.executeQuery();

			rs.next();

			result = new MappaCatalogo();

			//result.setId(rs.getString(1));
			//result.setAmbiente(rs.getString(2));
			//result.setMezzo(rs.getString(3));
			//result.setCittaPartenza(rs.getString(4));
			//result.setCittaArrivo(rs.getString(5));
			//result.setVia(rs.getString(6));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}

		return result;
	}


	
	public static void update(MappaCatalogo dato) {
		if (dato == null)
			return;

		try {
			Connection conn = null;
			PreparedStatement ps = null;
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(dbName, usr, pass);
			ps = conn.prepareStatement(updateQuery);

			/*ps.setString(1, dato.getId());
			ps.setString(2, dato.getAmbiente());
			ps.setString(3, dato.getMezzo());
			ps.setString(4, dato.getCittaPartenza());
			ps.setString(5, dato.getCittaArrivo());
			ps.setString(6, dato.getVia());
			ps.setString(7, dato.getId());
            */
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}

	
	public static void delete(String id) {
		if (id == null)
			return;

		try {
			Connection conn = null;
			PreparedStatement ps = null;
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(dbName, usr, pass);

			ps.setString(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}
	
	/* Elimina tutta la tabella CATALOGO con le sue entries */
	public void dropCatalogo() {
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(dropQuery);

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


