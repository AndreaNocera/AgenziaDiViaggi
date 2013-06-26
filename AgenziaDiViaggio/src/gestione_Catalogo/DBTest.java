/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Test di connessione a MySQL.");
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		String connectionURL = "jdbc:mysql:";
		String url = connectionURL + "//" + "localhost:3306" + "/";
		String dbName = url + "voyager";


		String userName = "voyager";
		String password = "voyager";
		try {
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(dbName,userName,password);
		System.out.println("Connesso.");
		
		System.out.println("Creo Tabella catalogo");
		String createQuery = 
				"CREATE TABLE IF NOT EXISTS CATALOGO(" +
						"ID VARCHAR(20) PRIMARY KEY, " +
						"AMBIENTE VARCHAR(100), " +
						"MEZZO VARCHAR(100), " +
						"STAZIONEPARTENZA VARCHAR(100), " +
						"STAZIONEARRIVO VARCHAR(100), " +
						"STAZIONEINTERMEDIA VARCHAR(100)" +
						")";
		
		PreparedStatement ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		
		System.out.println("Creo Tabella ViaMare");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS VIAMARE(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(100) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		System.out.println("Creo Tabella ViaAria");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS VIAARIA(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(100) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		System.out.println("Creo Tabella ViaTerra");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS VIATERRA(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(100) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		conn.close();
		System.out.println("Non connesso.");
		} catch (Exception e) {
		e.printStackTrace();
		}

	}

}
