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
		
		
		
		
		
		System.out.println("Creo Tabella AMBIENTE");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS AMBIENTE(" +
						"ID INTEGER PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(30) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		
		System.out.println("Creo Tabella MEZZO");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS MEZZO(" +
						"ID INTEGER PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(30) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		System.out.println("Creo Tabella CITTA");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS CITTA(" +
						"ID INTEGER PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(30) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		System.out.println("Creo Tabella VIA");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS VIA(" +
						"ID INTEGER PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(30) " +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		
		System.out.println("Creo Tabella CATALOGO");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS CATALOGO(" +
						"ID INTEGER PRIMARY KEY, " +
						"AMBIENTE INTEGER, " +
						"MEZZO INTEGER, " +
						"CITTAPARTENZA INTEGER, " +
						"CITTAARRIVO INTEGER, " +
						"VIA INTEGER, " +
						"INFO VARCHAR(100), " +
						"DATA DATETIME, " +
						"FOREIGN KEY (AMBIENTE) REFERENCES AMBIENTE (ID), "   +
						"FOREIGN KEY (MEZZO) REFERENCES MEZZO (ID), " +
						"FOREIGN KEY (CITTAPARTENZA) REFERENCES CITTA (ID), " +
						"FOREIGN KEY (CITTAARRIVO) REFERENCES CITTA (ID), " +
						"FOREIGN KEY (VIA) REFERENCES VIA (ID) " +
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
