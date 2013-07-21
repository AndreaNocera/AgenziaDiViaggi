package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voyager.nove.exception.ConnectionException;
import voyager.nove.exception.DAOException;
import voyager.nove.exception.DataException;
import voyager.nove.model.viaggio.Ambiente;
import voyager.nove.model.viaggio.Citta;
import voyager.nove.model.viaggio.Data;
import voyager.nove.model.viaggio.Mezzo;
import voyager.nove.model.viaggio.Tratta;
import voyager.nove.model.viaggio.Via;

public class DAOCatalogo extends DAO {

	private static DAOCatalogo instance = null;

	private static final String getCatalogoQuery = "SELECT * FROM `catalogo`";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS catalogo("
			+ "idTratta INT(10) PRIMARY KEY, "
			+ "idAmbiente INT(10), "
			+ "idMezzo INT(10), "
			+ "idCittaPartenza INT(10), "
			+ "idCittaArrivo INT(10), " 
			+ "idVia INT(10)," 
			+ "giornoInserimento INT(10),"
			+ "meseInserimento INT(10),"
			+ "annoInserimento INT(10)"
			+ ")";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	
	private DAOCatalogo() {
		try {
			//Class.forName(driverName);

			conn = getConnection();

			ps = conn.prepareStatement(createQuery);

			ps.executeUpdate();
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
	}

	public static synchronized DAOCatalogo getInstance() {
		if (instance == null)
			instance = new DAOCatalogo();
		return instance;
	}

	public synchronized List<Tratta> getCatalogo() throws DAOException, DataException {
		List<Tratta> tratte = new ArrayList<Tratta>();
		
		try {
			
			conn = getConnection();
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

				DAOAmbiente daoAmbiente = DAOAmbiente.getInstance();
				id = rs.getInt(2);
				valore = daoAmbiente.getValueById(id);
				ambiente = new Ambiente(id, valore);
				tratta.setAmbiente(ambiente);

				DAOMezzo daoMezzo = DAOMezzo.getInstance();
				id = rs.getInt(3);
				valore = daoMezzo.getValueById(id);
				mezzo = new Mezzo(id, valore);
				tratta.setMezzo(mezzo);

				DAOCitta daoCitta = DAOCitta.getInstance();
				id = rs.getInt(4);
				valore = daoCitta.getValueById(id);
				cittaPartenza = new Citta(id, valore);
				tratta.setCittaPartenza(cittaPartenza);

				id = rs.getInt(5);
				valore = daoCitta.getValueById(id);
				cittaArrivo = new Citta(id, valore);
				tratta.setCittaArrivo(cittaArrivo);

				DAOVia daoVia = DAOVia.getInstance();
				id = rs.getInt(6);
				valore = daoVia.getValueById(id);
				via = new Via(id, valore);
				tratta.setVia(via);
				
				Data data = new Data(rs.getInt(7), rs.getInt(8), rs.getInt(9));
				tratta.setDataInserimento(data);
				tratte.add(tratta);
			}

			return tratte;

		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new DAOException("Errore in Connection.");
		} catch (SQLException e) {
			throw new DAOException("Errore in SQL.");
		} finally {
			this.closeResource();
		}

	}

	@Override
	public void insert(Object obj) {}

	@Override
	public void update(Object obj) {}

	@Override
	public void delete(Object obj) {}

	@Override
	public Object read(Integer id) throws DAOException {
		return null;
	}

	public synchronized Integer getNextId() throws DAOException {
		try {
			
			conn = getConnection();
			ps = conn.prepareStatement(getCatalogoQuery);
			rs = ps.executeQuery();
			if (!rs.next())
				return 1;

			rs.last();
			return (rs.getInt(1)) + 1;
		} catch (ClassCastException e) {
			throw new DAOException("Errore in getNextId.");
		} catch (SQLException e) {
			throw new DAOException("Errore in getNextID.");
		}
	}
}