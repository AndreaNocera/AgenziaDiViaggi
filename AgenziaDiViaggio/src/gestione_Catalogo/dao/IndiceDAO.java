/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.dao;

import gestione_Catalogo.entity.IDEsternoElemento;
import gestione_Catalogo.entity.Indice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndiceDAO {
	
	private static final String driverName = "com.mysql.jdbc.Driver";
	private static final String connectionURL = "jdbc:mysql:";
	private static final String URL = connectionURL + "//" + "localhost:3306" + "/";
	private static final String dbName = URL + "voyager";
	
	private static final String usr = "voyager";
	private static final String pass = "voyager";
	
	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS INDICE(" +
					"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
					"NEROGATI INTEGER " +
					")";
	
	private static final String insertQuery = 
			"INSERT INTO INDICE " +
			"VALUES(?, ?)";
	private static final String updateQuery = 
			"UPDATE INDICE SET " +
			"NEROGATI=? " +
			"WHERE IDESTERNOVIAGGIO=?";
	private static final String deleteQuery = 
			"DELETE FROM " +
			"INDICE WHERE IDESTERNOVIAGGIO=?";
	private static final String findQuery = 
			"SELECT * FROM INDICE " +
			"WHERE IDESTERNOVIAGGIO=?";

	private static final String dropQuery = 
			"DROP TABLE INDICE IF EXISTS";
	
	//costruttore
	private IndiceDAO(){
		
	}
	
	
	//Metodi CRUD
	
	public static void create(IDEsternoElemento idEsternoViaggio, Indice indice) throws SQLException, ClassNotFoundException{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbName, usr, pass);
		
		pstmt = con.prepareStatement(insertQuery);
		pstmt.setString(1, idEsternoViaggio.toString());
		pstmt.setInt(2, indice.getErogati());
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	
	
	public static Indice read(IDEsternoElemento idEsternoViaggio) throws SQLException, ClassNotFoundException{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbName, usr, pass);
		pstmt = con.prepareStatement(findQuery);
		pstmt.setString(1, idEsternoViaggio.toString());
		rs=pstmt.executeQuery();
		
		rs.next();
		
		String id = rs.getString("IDESTERNOVIAGGIO");
		int index = rs.getInt("NEROGATI");
		
		rs.close();
		pstmt.close();
		con.close();
		
		return new Indice(index);
		
		
		
	}
	
	
	public static void update(IDEsternoElemento idEsternoViaggio, Indice indice) throws SQLException, ClassNotFoundException{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbName, usr, pass);
		pstmt = con.prepareStatement(updateQuery);
		pstmt.setString(2, idEsternoViaggio.toString());
		pstmt.setInt(1, indice.getErogati());
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
	}
	
	public static void delete(IDEsternoElemento idEsternoViaggio) throws  SQLException, ClassNotFoundException{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbName, usr, pass);
		pstmt = con.prepareStatement(deleteQuery);
		pstmt.setString(1, idEsternoViaggio.toString());
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	
	
	

}
