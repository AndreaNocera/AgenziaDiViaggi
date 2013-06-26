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
		
		
		
		System.out.println("Creo Tabella indice");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS INDICE(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"NEROGATI INTEGER " +
						")";
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		System.out.println("Creo Tabella VIAARIA");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS VIAARIA(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(100), " +
						"FOREIGN KEY (IDESTERNOVIAGGIO) REFERENCES INDICE (IDESTERNOVIAGGIO)" +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		
		System.out.println("Creo Tabella VIAMARE");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS VIAMARE(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(100), " +
						"FOREIGN KEY (IDESTERNOVIAGGIO) REFERENCES INDICE (IDESTERNOVIAGGIO)" +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		System.out.println("Creo Tabella VIATERRA");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS VIATERRA(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(100), " +
						"FOREIGN KEY (IDESTERNOVIAGGIO) REFERENCES INDICE (IDESTERNOVIAGGIO)" +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		System.out.println("Creo Tabella MEZZOTRASPORTO");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS MEZZOTRASPORTO(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(100), " +
						"FOREIGN KEY (IDESTERNOVIAGGIO) REFERENCES INDICE (IDESTERNOVIAGGIO)" +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		System.out.println("Creo Tabella STAZIONEPARTENZA");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS STAZIONEPARTENZA(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(100), " +
						"FOREIGN KEY (IDESTERNOVIAGGIO) REFERENCES INDICE (IDESTERNOVIAGGIO)" +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		System.out.println("Creo Tabella STAZIONEARRIVO");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS STAZIONEARRIVO(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(100), " +
						"FOREIGN KEY (IDESTERNOVIAGGIO) REFERENCES INDICE (IDESTERNOVIAGGIO)" +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		System.out.println("Creo Tabella STAZIONEINTERMEDIA");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS STAZIONEINTERMEDIA(" +
						"IDESTERNOVIAGGIO VARCHAR(100) PRIMARY KEY, " +
						"IDESTERNOELEMENTO VARCHAR(100), " +
						"INFO VARCHAR(100), " +
						"DATA DATE, " +
						"FOREIGN KEY (IDESTERNOVIAGGIO) REFERENCES INDICE (IDESTERNOVIAGGIO)" +
						")";
		
		ps = conn.prepareStatement(createQuery);
		ps.executeUpdate();
		
		
		
		
		
		
		
		
		
		System.out.println("Creo Tabella catalogo");
		createQuery = 
				"CREATE TABLE IF NOT EXISTS CATALOGO(" +
						"ID VARCHAR(20) PRIMARY KEY, " +
						"AMBIENTE VARCHAR(100), " +
						"MEZZO VARCHAR(100), " +
						"STAZIONEPARTENZA VARCHAR(100), " +
						"STAZIONEARRIVO VARCHAR(100), " +
						"STAZIONEINTERMEDIA VARCHAR(100)" +
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
