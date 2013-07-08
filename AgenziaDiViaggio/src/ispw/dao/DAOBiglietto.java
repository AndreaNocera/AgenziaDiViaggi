/**
 * 
 */
package ispw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ispw.entity.Biglietto;
import ispw.exception.ConnectionException;
import ispw.exception.DAOException;


/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella Riccardo
 * @generated 
 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOBiglietto extends DAO{

	private static DAOBiglietto instance = null;

	private static final String getBigliettiQuery = "SELECT *"
			+ "FROM `biglietti`";
	private static final String getListaBigliettiByIdPrenotazione = "SELECT *" +
			"FROM `biglietti` WHERE idPrenotazione = ?";
			

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS `biglietti`("
			+ "idBiglietto INT(10) PRIMARY KEY, "
			+ "idPrenotazione INT(10), "
			+ "idTraveler INT(10)"
			+ ")";

	private static final String insertQuery = "INSERT INTO `biglietti`"
			+ "(`idBiglietto`, `idPrenotazione`,  `idTraveler`) "
			+ "VALUES (?,?,?)";

	private static final String updateQuery = "UPDATE `biglietti` "
			+ "SET `idBiglietto`=?, `idPrenotazione`=?, `idTraveler`=? "
			+ "WHERE `idBiglietto`=?";

	private static final String deleteQuery = "DELETE FROM `biglietti` "
			+ "WHERE `idBiglietto`=?";
	private static final String findQuery = "SELECT * FROM `biglietti`"
			+ "WHERE idBiglietto=?";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private DAOBiglietto() {
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

	public synchronized static DAOBiglietto getInstance() {
		if (instance == null)
			instance = new DAOBiglietto();
		return instance;
	}

	@Override
	public synchronized void insert(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		ResultSet rs;
		Biglietto biglietto;
		try {

			biglietto = (Biglietto) obj;
			// Situazione 1. Tabella vuota. Inserisco.

			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getBigliettiQuery);
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, biglietto.getIdBiglietto());
				ps.setInt(2, biglietto.getIdPrenotazione());
				ps.setInt(3, biglietto.getTraveler().getIdTraveler());

				ps.executeUpdate();
			}

			// Situazione 2. Elemento non presente.
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, biglietto.getIdBiglietto());

			rs = ps.executeQuery();

			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, biglietto.getIdBiglietto());
				ps.setInt(2, biglietto.getIdPrenotazione());
				ps.setInt(3, biglietto.getTraveler().getIdTraveler());

				ps.executeUpdate();
			}
			// Situazione 3.Elemento Presente. Non inserisco.

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}

	}

	@Override
	public synchronized void update(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Biglietto biglietto;
		try {
			biglietto = (Biglietto) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(updateQuery);

			ps.setInt(1, biglietto.getIdBiglietto());
			ps.setInt(2, biglietto.getIdPrenotazione());
			ps.setInt(3, biglietto.getTraveler().getIdTraveler());
			ps.setInt(4, biglietto.getIdBiglietto());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in update.");
		} catch (SQLException e) {
			throw new DAOException("Errore in update.");
		}
	}

	@Override
	public synchronized void delete(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Biglietto biglietto;
		try {
			biglietto = (Biglietto) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, biglietto.getIdBiglietto());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		}
	}

	@Override
	public synchronized Biglietto read(Integer idBiglietto) throws DAOException {
		Biglietto biglietto = new Biglietto();
		DAOTraveler daoTraveler = DAOTraveler.getInstance();
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, idBiglietto);

			rs = ps.executeQuery();

			rs.next();
			biglietto.setIdBiglietto(rs.getInt(1));
			biglietto.setIdPrenotazione(rs.getInt(2));
			biglietto.setTraveler(daoTraveler.read(rs.getInt(3)));

			return biglietto;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read.");
		}
	}
	
	public synchronized Integer getNextId() throws DAOException {
		try {
			// Situazione 1. Tabella Vuota. Id da ritornare 1.
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getBigliettiQuery);
			rs = ps.executeQuery();
			if (!rs.next())
				return 1;

			// Situazione 2. Almeno un Elemento presente.
			rs.last();
			return (rs.getInt(1)) + 1;
		} catch (ClassCastException e) {
			throw new DAOException("Errore in getNextId.");
		} catch (SQLException e) {
			throw new DAOException("Errore in getNextId.");
		}
	}

	public synchronized List<Biglietto> getListaBigliettiByIdPrenotazione(Integer idPrenotazione) throws DAOException {
		// TODO Auto-generated method stub
		List<Biglietto> listaBiglietti = new ArrayList<Biglietto>();
		DAOTraveler daoTraveler = DAOTraveler.getInstance();
		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getListaBigliettiByIdPrenotazione);
			ps.setInt(1,idPrenotazione);
			rs = ps.executeQuery();
			while(rs.next()){
				Biglietto biglietto = new Biglietto();
				biglietto.setIdBiglietto(rs.getInt(1));
				biglietto.setIdPrenotazione(rs.getInt(2));
				biglietto.setTraveler(daoTraveler.read(rs.getInt(3)));
				listaBiglietti.add(biglietto);
			}
		} catch (SQLException e) {
			throw new DAOException("Errore in getListaBigliettiByIdPrenotazione.");
		}
		return listaBiglietti;
	}
}














