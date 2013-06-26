/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.dao;

import gestione_Catalogo.entity.IDEsterno;
import gestione_Catalogo.entity.Info;
import gestione_Catalogo.entity.Orologio;
import gestione_Catalogo.entity.StazioneIntermedia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class StazioneIntermediaDAO {
	
	private static final String driverName = "com.mysql.jdbc.Driver";
	private static final String connectionURL = "jdbc:mysql:";
	private static final String URL = connectionURL + "//" + "localhost:3306" + "/";
	private static final String dbName = URL + "voyager";
	
	private static final String usr = "voyager";
	private static final String pass = "voyager";
	
	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS STAZIONEINTERMEDIA(" +
					"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
					"IDESTERNOELEMENTO VARCHAR(100), " +
					"INFO VARCHAR(100), " +
					"DATA DATE, " +
					"FOREIGN KEY (IDESTERNOVIAGGIO) REFERENCES INDICE (IDESTERNOVIAGGIO)" +
					")";
	
	
	private static final String insertQuery = 
			"INSERT INTO STAZIONEINTERMEDIA " +
			"VALUES(?, ?, ?, ?)";
	private static final String updateQuery = 
			"UPDATE STAZIONEINTERMEDIA SET " +
			"IDESTERNOELEMENTO=?, " +
			"INFO=?, " + 
			"DATA=? " +
			"WHERE IDESTERNOVIAGGIO=?";
	private static final String deleteQuery = 
			"DELETE FROM " +
			"STAZIONEINTERMEDIA WHERE IDESTERNOVIAGGIO=?";
	private static final String findQuery = 
			"SELECT * FROM STAZIONEINTERMEDIA " +
			"WHERE IDESTERNOVIAGGIO=?";

	private static final String dropQuery = 
			"DROP TABLE STAZIONEINTERMEDIA IF EXISTS";
	
	
	//Costruttore
	
	private StazioneIntermediaDAO(){
	}
	
	//METODI CRUD
	public static void create(IDEsterno idEsternoViaggio, IDEsterno idEsternoElemento, Info info, Orologio data) throws SQLException, ClassNotFoundException{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbName, usr, pass);
		
		pstmt = con.prepareStatement(insertQuery);
		pstmt.setString(1, idEsternoViaggio.toString());
		pstmt.setString(2, idEsternoElemento.toString());
		pstmt.setString(3, info.toString());
		pstmt.setDate(4, data.getDataPerDB());
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	
	
	public static StazioneIntermedia read(IDEsterno idEsternoViaggio) throws SQLException, ClassNotFoundException{
		
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
		String id2 = rs.getString("IDESTERNOELEMENTO");
		String info = rs.getString("INFO");
		Date data = rs.getDate("DATA");
		
		rs.close();
		pstmt.close();
		con.close();
		
		return new StazioneIntermedia(new IDEsterno(id2), new IDEsterno(id), new Info(info), new Orologio(data));
		
		
		
	}
	
	
	public static void update(IDEsterno idEsternoViaggio, IDEsterno idEsternoElemento, Info info, Orologio data) throws SQLException, ClassNotFoundException{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbName, usr, pass);
		pstmt = con.prepareStatement(updateQuery);
		pstmt.setString(2, idEsternoViaggio.toString());
		pstmt.setString(1, idEsternoElemento.toString());
		pstmt.setString(3, info.toString());
		pstmt.setDate(4, data.getDataPerDB());
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
	}
	
	public static void delete(IDEsterno idEsternoViaggio) throws  SQLException, ClassNotFoundException{
		
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
