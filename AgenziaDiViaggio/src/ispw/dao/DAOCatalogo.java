/**
 * 
 */
package ispw.dao;

import ispw.entity.Ambiente;
import ispw.entity.Citta;
import ispw.entity.Data;
import ispw.entity.Mezzo;
import ispw.entity.Tratta;
import ispw.entity.Via;
import ispw.exception.ConnectionException;
import ispw.exception.DAOException;
import ispw.exception.DataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella Riccardo
 * @generated 
 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
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
			Class.forName(driverName);

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(createQuery);

			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*closeResource()*/;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("Errore in Connection.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in SQL.");
		} finally {
			/*closeResource()*/;
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
	public Object read(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public synchronized Integer getNextId() throws DAOException {
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