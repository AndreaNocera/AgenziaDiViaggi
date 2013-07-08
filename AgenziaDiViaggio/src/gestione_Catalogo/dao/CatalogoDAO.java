package gestione_Catalogo.dao;

import gestione_Catalogo.entity.Ambiente;
import gestione_Catalogo.entity.Citta;
import gestione_Catalogo.entity.Data;
import gestione_Catalogo.entity.IDEsternoElemento;
import gestione_Catalogo.entity.Info;
import gestione_Catalogo.entity.Mezzo;
import gestione_Catalogo.entity.Tratta;
import gestione_Catalogo.entity.Via;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;



/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */

public class CatalogoDAO extends DAO {

	private static CatalogoDAO istanza = null;

	private static final String getCatalogoQuery = "SELECT * FROM catalogo";

	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS catalogo(" +
					"ID INTEGER PRIMARY KEY, " +
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

/*	SPOSTATE IN TRATTA DAO
	private static final String insertQuery = 
			"INSERT INTO CATALOGO " +
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String updateQuery = 
			"UPDATE CATALOGO SET " +
			"ID=?, AMBIENTE=?, MEZZO=?, CITTAPARTENZA=?, CITTAARRIVO=?, VIA=?, INFO=?, DATA=? " +
			"WHERE ID=?";
	private static final String deleteQuery = 
			"DELETE FROM " +
			"CATALOGO WHERE ID=?";
	private static final String findQuery = 
			"SELECT * FROM CATALOGO " +
			"WHERE ID=?";    */

	private static final String dropQuery = 
			"DROP TABLE catalogo IF EXISTS";     

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null; 
	
	
	private CatalogoDAO() {
		try {
			conn = Persistenza.getConnection();
			ps = conn.prepareStatement(createQuery);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}

	public static CatalogoDAO getIstanza() {
		if (istanza == null)
			istanza = new CatalogoDAO();
		return istanza;
	}
	
	/*
	 * FETCH del catalogo
	 * Viene letto tutta la tabella catalogo, e per ogni riga vengono lette tutti gli elementi e creati i corrispettivi oggetti
	 * la riga non contiene il nome dei vari elementi, ma solo il loro id!!!
	 * Per questo viene invocato il rispettivo DAO per ottenere dall'id il valore giusto
	 * Infine verr� creato l'oggetto usando il giusto controllore (per capirci, quello che non invoca il DAO per salvare da db)
	 * Creati tutti gli oggetti per un riga, lo aggiunge ad un arrayList.
	 * Terminato il resultSet, ritorna l'Arraylist
	 */

	public ArrayList<Tratta> getCatalogo(){
		ArrayList<Tratta> tratte = new ArrayList<Tratta>();
		try {
			conn = Persistenza.getConnection();
			ps = conn.prepareStatement(getCatalogoQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
		
				Ambiente ambiente;
				Mezzo mezzo;
				Citta cittaPartenza;
				Citta cittaArrivo;
				Via via;
				Info info;
				Data data;
				
				IDEsternoElemento valore;
				Integer id;
				
				//prendo l'id
				Integer idTratta = rs.getInt(1);
				System.out.println("idTratta" + idTratta.toString());
				//creo oggetto ambiente
				AmbienteDAO daoAmbiente = AmbienteDAO.getIstanza();
				id = rs.getInt(2);
				System.out.println("id " + id.toString());
				valore = daoAmbiente.readOnlyValue(id);
				System.out.println(valore.toString());
				Class<?> c = Class.forName("gestione_Catalogo.entity."+valore);   // per classi in un package, va messo il nome del package!!!"
				
				//preparo i parametri
				Class<?> primoParametro  = Integer.class;
				Class<?> secondoParametro	 = Class.forName("gestione_Catalogo.entity.IDEsternoElemento");
				
				Class<?>[] parametri = {primoParametro, secondoParametro};
				
				//prendo il costruttore della classe con i parametri indicati
				Constructor<?> costruttore = c.getConstructor(parametri);
				
				//creo l'oggetto
				ambiente = (Ambiente) costruttore.newInstance(id, valore);

				
				//creo oggetto mezzo
				MezzoDAO daoMezzo = MezzoDAO.getIstanza();
				id = rs.getInt(3);
				valore = daoMezzo.readOnlyValue(id);
				mezzo = new Mezzo(id, valore);

                //creo oggetto citta per la partenza
				CittaDAO daoCitta = CittaDAO.getIstanza();
				id = rs.getInt(4);
				valore = daoCitta.readOnlyValue(id);
				cittaPartenza = new Citta(id, valore);

				//creo oggetto citta per l'arrivo
				id = rs.getInt(5);
				valore = daoCitta.readOnlyValue(id);
				cittaArrivo = new Citta(id, valore);

				//creo oggetto via
				ViaDAO daoVia = ViaDAO.getIstanza();
				id = rs.getInt(6);
				valore = daoVia.readOnlyValue(id);
				via = new Via(id, valore);

				
				//creo l'oggetto per le Info
				info = new Info(rs.getString(7));
				
				//creo l'oggetto per la data
				data = new Data(rs.getTimestamp(8));
				
				//creo l'oggetto tratta e l'aggiungo
				Tratta tratta = new Tratta(idTratta, ambiente, mezzo, cittaPartenza, cittaArrivo, via, info, data);
				tratte.add(tratta);
				
			}

			closeResource();
			return tratte;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			closeResource();
		}

	}


	/* SPOSTATO IN TRATTA DAO
	public Integer getNextId() throws DAOException {
		try {
			// Situazione 1. Tabella Vuota. Id da ritornare 1.
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getCatalogoQuery);
			rs = ps.executeQuery();
			if (!rs.next())
				return 1;

			// Situazione 2. Almeno un Elemento presente.
			rs.last();
			return (rs.getInt(1)) + 1;
		} catch (ClassCastException e) {
			throw new DAOException("Errore in getNextId.");
		} catch (SQLException e) {
			throw new DAOException("Errore in getNextID.");
		}
	}   */
}
