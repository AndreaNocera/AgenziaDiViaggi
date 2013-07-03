/*
 * 
 * Autori: Luca Paoli e Jessica Lucia
 * 
 */

package IndiciDAO;

import indici.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IndiciDAO{
	/*Costanti per il caricamento e la connessione al DB*/
	protected static final String driverName = "com.mysql.jdbc.Driver";
	protected static final String connectionURL = "jdbc:mysql:";
	protected static final String URL = connectionURL + "//" + "localhost:3306"
			+ "/";
	protected static final String dbName = URL + "voyager";
	
	/*Costanti per l'accesso al DB MySql*/
	private static final String usr = "root";
	private static final String pass = "root";
	
	//private static final String queryOfferta = "";
	
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
			closeResource();
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
	
	/*Ottiene l'id di tutte le offerte presenti nel catalogo con i parametri dati.*/
	private String[] getIdOffertabyCatalogo(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest){
		//Indica se non occorre filtrare i risultati tramite la clausola "WHERE"
		boolean flag_tutti_i_parametri = false;
		
		if(idAmbiente == null && idMezzo == null && idCittaSorg == null && idCittaDest == null)
			flag_tutti_i_parametri = true;
		
		else if(idAmbiente.replaceAll(" ", "").compareTo("") == 0 && idMezzo.replaceAll(" ", "").compareTo("") == 0 && idCittaSorg.replaceAll(" ", "").compareTo("") == 0 && idCittaDest.replaceAll(" ", "").compareTo("") == 0)
			flag_tutti_i_parametri = true;
		
		String[] risultato = new String[0];		//inizializzazione di un array vuoto
		StringBuilder strbld;
		
		ArrayList<String> lista_risultati = new ArrayList<String>();
		
		try{
			//Flag che indica se inserire o meno l'"AND" all'interno della query
			boolean flag_and = false;
			
			conn = getConnection(usr, pass);
			
			strbld = new StringBuilder();
			
			strbld.append("SELECT idOfferta FROM offerta WHERE idTratta = (");
			strbld.append("SELECT idTratta FROM catalogo ");
			
			if(flag_tutti_i_parametri == false)
				strbld.append("WHERE ");
			
			if(idAmbiente != null && idAmbiente.replaceAll(" ", "").compareTo("") != 0){
				if(flag_and == true)
					strbld.append("AND ");
				else flag_and = true;
				
				strbld.append("idAmbiente = ");
				strbld.append(idAmbiente);
				strbld.append(" ");
			}
			
			if(idMezzo != null && idMezzo.replaceAll(" ", "").compareTo("") != 0){
				if(flag_and == true)
					strbld.append("AND ");
				else flag_and = true;
				
				strbld.append("idMezzo = ");
				strbld.append(idMezzo);
				strbld.append(" ");
			}
			
			if(idCittaSorg != null && idCittaSorg.replaceAll(" ", "").compareTo("") != 0){
				if(flag_and == true)
					strbld.append("AND ");
				else flag_and = true;
				
				strbld.append("idCittaPartenza = ");
				strbld.append(idCittaSorg);
				strbld.append(" ");
			}
			
			if(idCittaDest != null && idCittaDest.replaceAll(" ", "").compareTo("") != 0){
				if(flag_and == true)
					strbld.append("AND ");
				else flag_and = true;
				
				strbld.append("idCittaArrivo = ");
				strbld.append(idCittaDest);
				strbld.append(" ");
			}
			
			strbld.append(")");
			
			//Per il debug
			System.out.println("Debug: " + strbld.toString());
			
			ps = conn.prepareStatement(strbld.toString());
			
			rs = ps.executeQuery();
			
			//Aggiunge i risultati in una lista di stringhe prelevandoli dall'unica colonna della tabella risultante dalla query
			//if(!rs.isClosed())		//da utilizzare solo se supportato!
				lista_risultati.add(rs.getString(1));
			
			while(rs.next())
				lista_risultati.add(rs.getString(1));
			
			risultato = new String[lista_risultati.size()];
			
			for(int i = 0; i < risultato.length; i++)
				risultato[i] = lista_risultati.get(i);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeResource();
		}
		
		return risultato;
	}
	
	/*Calcola il numero di biglietti venduti a partire dall'offerta e eventualmente dall'anno.*/
	private int getCountBigliettifromOfferta(String idOfferta, String anno){
		if(idOfferta == null)
			throw new NullPointerException("Parametri della funzione non corretti (nulli)!");

		if(idOfferta.replaceAll(" ", "").compareTo("") == 0)
			throw new IllegalArgumentException("Parametri della funzione non corretti (vuoti)!");
		
		//Se viene ritornato "-1" c'è stato un errore!
		int risultato = -1;
		//Costruisce la query in modo efficiente
		StringBuilder strbld;
		
		try{
			conn = getConnection(usr, pass);
			
			strbld = new StringBuilder();
			
			strbld.append("SELECT COUNT(idBiglietto) FROM biglietti WHERE idBiglietto = (" +
						  "SELECT idBiglietto FROM Prenotazioni WHERE idPrenotazione = (" +
						  "SELECT idPrenotazione FROM Offerta WHERE idOfferta = ");
			strbld.append(idOfferta);
			
			if(anno != null && anno.replaceAll(" ", "").compareTo("") != 0){
				strbld.append(" AND anno = ");
				strbld.append(anno);
			}
			
			strbld.append("))");
			
			ps = conn.prepareStatement(strbld.toString());
			
			rs = ps.executeQuery();
			
			//Prende l'unico valore dell'unica colonna ritornata dalla query e restituisce l'intero corrispondente (vedere la query)
			risultato = rs.getInt(1);
			
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeResource();
		}
		
		return risultato;
	}
	
	/*Funzione che ottiene il numero di biglietti prenotati in base ai parametri passati.*/
	public int getCountBiglietti(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest, String anno){
		
		int risultato = 0;
		String[] array_offerte = getIdOffertabyCatalogo(idAmbiente, idMezzo, idCittaSorg, idCittaDest);
		
		for(String temp : array_offerte){
			int risultato_parziale = getCountBigliettifromOfferta(temp, anno);
			
			if(risultato_parziale < 0)
				throw new IllegalArgumentException("Nel conteggio dei biglietti e' apparso un numero negativo!");
			
			risultato += risultato_parziale;
		}
		
		return risultato;
	}
}
