package gestione_Catalogo.dao;

import gestione_Catalogo.entity.Ambiente;
import gestione_Catalogo.entity.Citta;
import gestione_Catalogo.entity.Mezzo;
import gestione_Catalogo.entity.Tratta;
import gestione_Catalogo.entity.Via;
import gestione_Catalogo.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */

public class CatalogoDAO extends DAO {

	private static CatalogoDAO istanza = null;

	private static final String getCatalogoQuery = "SELECT * FROM CATALOGO";

	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS CATALOGO(" +
					"ID INTEGER PRIMARY KEY, " +
					"AMBIENTE INTEGER, " +
					"MEZZO INTEGER, " +
					"CITTAPARTENZA INTEGER, " +
					"CITTAARRIVO INTEGER, " +
					"VIA INTEGER, " +
					"INFO VARCHAR(100), " +
					"DATA DATE, " +
					"FOREIGN KEY (MEZZO) REFERENCES MEZZO (ID), " +
					"FOREIGN KEY (CITTAPARTENZA) REFERENCES CITTAPARTENZA (ID), " +
					"FOREIGN KEY (CITTAARRIVO) REFERENCES CITTAARRIVO (ID), " +
					"FOREIGN KEY (VIA) REFERENCES VIA (ID) " +
					")";

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
			"WHERE ID=?";

	private static final String dropQuery = 
			"DROP TABLE CATALOGO IF EXISTS";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	
	private CatalogoDAO() {
		try {
			Class.forName(driverName);

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(createQuery);

			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public List<Tratta> getCatalogo() throws DAOException {
		List<Tratta> tratte = new ArrayList<Tratta>();
		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getCatalogoQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				Tratta tratta = new Tratta();
				Ambiente ambiente;
				Mezzo mezzo;
				Citta cittaPartenza;
				Citta cittaArrivo;
				Via via;
				String valore;
				Integer id;

				tratta.setId(rs.getInt(1));

				DAOAmbiente daoAmbiente = DAOAmbiente.getIstance();
				id = rs.getInt(2);
				valore = daoAmbiente.getValueById(id);
				ambiente = new Ambiente(id, valore);
				tratta.setAmbiente(ambiente);

				DAOMezzo daoMezzo = DAOMezzo.getIstance();
				id = rs.getInt(3);
				valore = daoMezzo.getValueById(id);
				mezzo = new Mezzo(id, valore);
				tratta.setMezzo(mezzo);

				DAOCitta daoCitta = DAOCitta.getIstance();
				id = rs.getInt(4);
				valore = daoCitta.getValueById(id);
				cittaPartenza = new Citta(id, valore);
				tratta.setCittaPartenza(cittaPartenza);

				id = rs.getInt(5);
				valore = daoCitta.getValueById(id);
				cittaArrivo = new Citta(id, valore);
				tratta.setCittaArrivo(cittaArrivo);

				DAOVia daoVia = DAOVia.getIstance();
				id = rs.getInt(6);
				valore = daoVia.getValueById(id);
				via = new Via(id, valore);
				tratta.setVia(via);

				tratte.add(tratta);
			}

			return tratte;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in SQL.");
		}

	}

	@Override
	public void insert(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object read(Integer id){
		// TODO Auto-generated method stub
		return null;
	}

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
	}
}
