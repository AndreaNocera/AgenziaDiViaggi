/*
 * 
 * Autori: Luca Paoli e Jessica Lucia
 * Revisione: Riccardo Gambella, Jessica Lucia e Luca Paoli
 * 
 */

package ispw.indici;

import ispw.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCalcoloIndici {
	/* Costanti per il caricamento e la connessione al DB */
	protected static final String driverName = "com.mysql.jdbc.Driver";
	protected static final String connectionURL = "jdbc:mysql:";
	protected static final String URL = connectionURL + "//" + "localhost:3306"
			+ "/";
	protected static final String dbName = URL + "voyager";

	/* Costanti per l'accesso al DB MySql */
	private static final String usr = "root";
	private static final String pass = "root";

	/*
	 * Variabili per la gestione della connessione e dei risultati delle query
	 * al DB MySQL
	 */
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	/* Variabile per il pattern "Singleton" (Unica istanza della classe) */
	private static DAOCalcoloIndici daoindex = null;

	/* Costruttore privato per l'implementazione del pattern Singleton */
	private DAOCalcoloIndici() {
		try {
			// Carica il driver MySQL connector Java a runtime tramite
			// reflection
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}

	/* Ottiene la connessione al DB */
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

	/* Chiude tutte le risorse allocate per il DB */
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

	/* Metodo del Singleton */
	public static DAOCalcoloIndici getInstance() {
		if (daoindex == null)
			daoindex = new DAOCalcoloIndici();

		return daoindex;
	}

	/*
	 * Ottiene l'id di tutte le offerte presenti nel catalogo con i parametri
	 * dati.
	 */
	private List<Integer> getIdOffertabyCatalogo(String idAmbiente,
			String idMezzo, String idCittaSorg, String idCittaDest, String anno) {
		List<Integer> listaTratte = new ArrayList<Integer>();
		// Indica se non occorre filtrare i risultati tramite la clausola
		// "WHERE"
		boolean flag_tutti_i_parametri = false;

		if (idAmbiente == null && idMezzo == null && idCittaSorg == null
				&& idCittaDest == null)
			flag_tutti_i_parametri = true;

		else if (idAmbiente.replaceAll(" ", "").compareTo("") == 0
				&& idMezzo.replaceAll(" ", "").compareTo("") == 0
				&& idCittaSorg.replaceAll(" ", "").compareTo("") == 0
				&& idCittaDest.replaceAll(" ", "").compareTo("") == 0)
			flag_tutti_i_parametri = true;

		StringBuilder strbld;

		ArrayList<Integer> lista_risultati = new ArrayList<Integer>();

		try {
			// Flag che indica se inserire o meno l'"AND" all'interno della
			// query
			boolean flag_and = false;

			conn = getConnection(usr, pass);

			strbld = new StringBuilder();

			strbld.append("SELECT idTratta FROM catalogo ");

			if (flag_tutti_i_parametri == false)
				strbld.append("WHERE ");

			if (idAmbiente != null
					&& idAmbiente.replaceAll(" ", "").compareTo("") != 0) {
				if (flag_and == true)
					strbld.append("AND ");
				else
					flag_and = true;

				strbld.append("idAmbiente = ");
				strbld.append(idAmbiente);
				strbld.append(" ");
			}

			if (idMezzo != null
					&& idMezzo.replaceAll(" ", "").compareTo("") != 0) {
				if (flag_and == true)
					strbld.append("AND ");
				else
					flag_and = true;

				strbld.append("idMezzo = ");
				strbld.append(idMezzo);
				strbld.append(" ");
			}

			if (idCittaSorg != null
					&& idCittaSorg.replaceAll(" ", "").compareTo("") != 0) {
				if (flag_and == true)
					strbld.append("AND ");
				else
					flag_and = true;

				strbld.append("idCittaPartenza = ");
				strbld.append(idCittaSorg);
				strbld.append(" ");
			}

			if (idCittaDest != null
					&& idCittaDest.replaceAll(" ", "").compareTo("") != 0) {
				if (flag_and == true)
					strbld.append("AND ");
				else
					flag_and = true;

				strbld.append("idCittaArrivo = ");
				strbld.append(idCittaDest);
				strbld.append(" ");
			}

			ps = conn.prepareStatement(strbld.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				listaTratte.add(rs.getInt(1));
			}

			for (Integer i : listaTratte)
				System.out.println(i);

			while (!listaTratte.isEmpty()) {
				strbld = new StringBuilder();
				Integer idTratta = listaTratte.remove(0);

				strbld.append("SELECT idOfferta FROM offerta WHERE idTratta = ");
				strbld.append(idTratta);
				// Per viaggi anno scelto.
				if (anno != null && anno.replaceAll(" ", "").compareTo("") != 0) {
					strbld.append(" AND annoInserimento = ");
					strbld.append(anno);
				}
				System.out.println("Debug: " + strbld.toString());
				ps = conn.prepareStatement(strbld.toString());

				rs = ps.executeQuery();

				while (rs.next()) {
					lista_risultati.add(rs.getInt(1));
				}
			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}

		return lista_risultati;
	}

	/*
	 * Calcola il numero di biglietti venduti a partire dall'offerta e
	 * eventualmente dall'anno.
	 */
	private int getCountBigliettifromOfferta(Integer idOfferta) {

		List<Integer> listaPrenotazioni = new ArrayList<Integer>();

		if (idOfferta == null)
			throw new NullPointerException(
					"Parametri della funzione non corretti (nulli)!");

		int risultato = 0;
		// Costruisce la query in modo efficiente
		StringBuilder strbld;

		try {
			conn = getConnection(usr, pass);

			strbld = new StringBuilder();

			strbld.append("SELECT idPrenotazione FROM Prenotazioni WHERE idOfferta = ");
			strbld.append(idOfferta);

			ps = conn.prepareStatement(strbld.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				listaPrenotazioni.add(rs.getInt(1));
			}

			while (!listaPrenotazioni.isEmpty()) {
				strbld = new StringBuilder();
				Integer idPrenotazione = listaPrenotazioni.remove(0);
				strbld.append("SELECT COUNT(*) FROM Biglietti WHERE idPrenotazione = ");
				strbld.append(idPrenotazione);

				ps = conn.prepareStatement(strbld.toString());

				rs = ps.executeQuery();

				rs.next();

				risultato += rs.getInt(1);

			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}

		return risultato;
	}

	/*
	 * Funzione che ottiene il numero di biglietti prenotati in base ai
	 * parametri passati.
	 */
	public int getCountBiglietti(String idAmbiente, String idMezzo,
			String idCittaSorg, String idCittaDest, String anno) {

		/*Flag che indica se occorre conteggiare anche la tratta inversa o meno*/
		boolean flag_tratta = (idCittaDest != null && idCittaSorg != null) ? true : false;
		
		int risultato = 0;
		int risultato_parziale = 0;
		
		List<Integer> listaOfferte = getIdOffertabyCatalogo(idAmbiente,
				idMezzo, idCittaSorg, idCittaDest,anno);
		
		for (Integer temp : listaOfferte) {
			risultato_parziale = getCountBigliettifromOfferta(temp);

			if (risultato_parziale < 0)
				throw new IllegalArgumentException(
						"Nel conteggio dei biglietti e' apparso un numero negativo!");

			risultato += risultato_parziale;
		}
		
		if(flag_tratta){
			List<Integer> listaOfferteInverse = getIdOffertabyCatalogo(idAmbiente,
					idMezzo, idCittaDest, idCittaSorg, anno);
			
			for(Integer temp : listaOfferteInverse){
				risultato_parziale = getCountBigliettifromOfferta(temp);
				
				if (risultato_parziale < 0)
					throw new IllegalArgumentException(
							"Nel conteggio dei biglietti e' apparso un numero negativo!");
	
				risultato += risultato_parziale;
			}
		}

		return risultato;
	}
}
