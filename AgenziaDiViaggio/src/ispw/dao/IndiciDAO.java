package ispw.dao;


import ispw.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndiciDAO{
	
	/*Costanti per la connessione al DB MySQL*/
	private static final String driverName = "com.mysql.jdbc.Driver";
	private static final String connectionURL = "jdbc:mysql:";
	private static final String URL = connectionURL + "//" + "localhost:3306" + "/";
	private static final String dbName = URL + "voyager";
	
	//private static final String driverName = "org.sqlite.JDBC";
	//private static final String connectionURL = "jdbc:sqlite:";
	//private static final String dbName = connectionURL + "voyager.sqlite";
	
	/*Costanti per l'accesso al DB MySql*/
	private static final String usr = "root";
	private static final String pass = "root";
	
	/*Constante che definisce la query da eseguire sul DB MySQL*/
	private static final String countQuery = "SELECT COUNT(*) FROM CATALOGO WHERE ";
	
	/*Variabili per la gestione della connessione e dei risultati delle query al DB MySQL*/
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	/*Variabile per il pattern "Singleton" (Unica istanza della classe)*/
	private static IndiciDAO daoindex = null;
	
	/*Costruttore privato per l'implementazione del pattern Singleton*/
	private IndiciDAO(){
		try {
			//Carica il driver MySQL connector Java a runtime tramite reflection
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*closeResource()*/;
		}		
	}
	
	/*Ottiene la connessione al DB*/
	private static Connection getConnection(String user, String password)
			throws ConnectionException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbName, user, password);
		} catch (SQLException sqle) {
			throw new ConnectionException("Errore nella connessione al DB.");
		}

		return conn;
	}
	
	/*Chiude tutte le risorse allocate per il DB*/
	private static void closeResource() {
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
	
	/*Metodo del Singleton*/
	public static IndiciDAO getInstance(){
		if(daoindex == null)
			daoindex = new IndiciDAO();
		
		return daoindex;
	}
	
	public Integer getCount(String nomeColonna, Integer id) {
		// TODO Auto-generated method stub
		
		if(nomeColonna == null || id == null)
			throw new NullPointerException("Parametri della funzione non corretti (nulli)!");
		
		//il valore "-1" indica un errore nel fetch dei risultati!
		Integer risultato_count = -1;
		
		try {
			StringBuilder strbld = new StringBuilder();
			
			conn = getConnection(usr, pass);
			
			/*Si è scelto di costruire la query a mano poichè la funzione "setString" non costruiva la query corretta.*/
			strbld.append(countQuery);
			strbld.append(nomeColonna);
			strbld.append(" = ");
			strbld.append("'");
			strbld.append(id);
			strbld.append("'");
			
			System.out.println("Tento la query: " + strbld.toString());
			
			ps = conn.prepareStatement(strbld.toString());
			
			rs = ps.executeQuery();
			//Prende l'unico valore dell'unica colonna ritornata dalla query e restituisce l'intero corrispondente (vedere la query)
			rs.next();
			risultato_count = rs.getInt(1);
			
			
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			/*closeResource()*/;
		}
		
		return (int)risultato_count;
	}	
}