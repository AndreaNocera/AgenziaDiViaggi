/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.dao;

import gestione_Catalogo.entity.IDEsterno;
import gestione_Catalogo.entity.MezzoTrasporto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MezzoTrasportoDAO {
	
	private static final String driverName = "com.mysql.jdbc.Driver";
	private static final String connectionURL = "jdbc:mysql:";
	private static final String URL = connectionURL + "//" + "localhost:3306" + "/";
	private static final String dbName = URL + "voyager";
	
	private static final String usr = "voyager";
	private static final String pass = "voyager";
	
	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS MEZZOTRASPORTO(" +
					"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
					"IDESTERNOELEMENTO VARCHAR(100), " +
					"FOREIGN KEY (IDESTERNOVIAGGIO) REFERENCES INDICE (IDESTERNOVIAGGIO)" +
					")";
	
	private static final String insertQuery = 
			"INSERT INTO MEZZOTRASPORTO " +
			"VALUES(?, ?)";
	private static final String updateQuery = 
			"UPDATE MEZZOTRASPORTO SET " +
			"IDESTERNOELEMENTO=? " +
			"WHERE IDESTERNOVIAGGIO=?";
	private static final String deleteQuery = 
			"DELETE FROM " +
			"MEZZOTRASPORTO WHERE IDESTERNOVIAGGIO=?";
	private static final String findQuery = 
			"SELECT * FROM MEZZOTRASPORTO " +
			"WHERE IDESTERNOVIAGGIO=?";

	private static final String dropQuery = 
			"DROP TABLE MEZZOTRASPORTO IF EXISTS";
	
	
	//Costruttore
	
	private MezzoTrasportoDAO(){
	}
	
	//METODI CRUD
	public static void create(IDEsterno idEsternoViaggio, IDEsterno idEsternoElemento) throws SQLException, ClassNotFoundException{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbName, usr, pass);
		
		pstmt = con.prepareStatement(insertQuery);
		pstmt.setString(1, idEsternoViaggio.toString());
		pstmt.setString(2, idEsternoElemento.toString());
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	
	
	public static MezzoTrasporto read(IDEsterno idEsternoViaggio) throws SQLException, ClassNotFoundException{
		
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
		
		rs.close();
		pstmt.close();
		con.close();
		
		return new MezzoTrasporto(new IDEsterno(id2), new IDEsterno(id));
		
		
		
	}
	
	
	public static void update(IDEsterno idEsternoViaggio, IDEsterno idEsternoElemento) throws SQLException, ClassNotFoundException{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbName, usr, pass);
		pstmt = con.prepareStatement(updateQuery);
		pstmt.setString(2, idEsternoViaggio.toString());
		pstmt.setString(1, idEsternoElemento.toString());
		
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
