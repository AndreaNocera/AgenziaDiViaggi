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
		
		String createQuery = null;
		PreparedStatement ps = null;
				
				
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
		
		
		
		
		System.out.println("Creo Tabella ambiente");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS ambiente(" +
						"ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
						"idesternoelemento VARCHAR(30) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		
		System.out.println("Creo Tabella mezzo");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS mezzo(" +
						"ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
						"idesternoelemento VARCHAR(30) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		System.out.println("Creo Tabella citta");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS citta(" +
						"ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
						"idesternoelemento VARCHAR(30) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		System.out.println("Creo Tabella via");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS via(" +
						"ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
						"idesternoelemento VARCHAR(30) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		
		System.out.println("Creo Tabella catalogo");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS catalogo(" +
						"ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
						"ambiente INTEGER, " +
						"mezzo INTEGER, " +
						"cittapartenza INTEGER, " +
						"cittaarrivo INTEGER, " +
						"via INTEGER, " +
						"info VARCHAR(100), " +
						"data DATETIME, " +
						"FOREIGN KEY (ambiente) REFERENCES ambiente (ID), "   +
						"FOREIGN KEY (mezzo) REFERENCES mezzo (ID), " +
						"FOREIGN KEY (cittapartenza) REFERENCES citta (ID), " +
						"FOREIGN KEY (cittaarrivo) REFERENCES citta (ID), " +
						"FOREIGN KEY (via) REFERENCES via (ID) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		System.out.println("Creo Tabella offerta");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS offerta(" +
						"ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
						"idtratta INTEGER, " +
						"datapartenza DATE, " +
						"dataarrivo DATE, " +
						"posti INTEGER, " +
						"FOREIGN KEY (idtratta) REFERENCES catalogo (ID) "   +
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
