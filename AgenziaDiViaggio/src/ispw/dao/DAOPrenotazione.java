package ispw.dao;

import ispw.entity.Prenotazione;
import ispw.exception.ConnectionException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.OraException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Gambella Riccardo
 */
public class DAOPrenotazione extends DAO{

	private static DAOPrenotazione instance = null;

	private static final String getPrenotazioniQuery = "SELECT *"
			+ "FROM `prenotazioni`";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS `prenotazioni`("
			+ "idPrenotazione INT(10) PRIMARY KEY, "
			+ "idOfferta INT(10), "
			+ "acquirente VARCHAR(20)"
			+ ")";

	private static final String insertQuery = "INSERT INTO `prenotazioni`"
			+ "(`idPrenotazione`, `idOfferta`,  `acquirente`) "
			+ "VALUES (?,?,?)";

	private static final String updateQuery = "UPDATE `prenotazioni` "
			+ "SET `idPrenotazione`=?, `idOfferta`=?, `acquirente`=? "
			+ "WHERE `idPrenotazione`=?";

	private static final String deleteQuery = "DELETE FROM `prenotazioni` "
			+ "WHERE `idPrenotazione`=?";
	private static final String findQuery = "SELECT * FROM `prenotazioni`"
			+ "WHERE idPrenotazione=?";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	
	private DAOPrenotazione() {
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

	public synchronized static DAOPrenotazione getInstance() {
		if (instance == null)
			instance = new DAOPrenotazione();
		return instance;
	}

	@Override
	public synchronized void insert(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		ResultSet rs;
		Prenotazione prenotazione;
		try {

			prenotazione = (Prenotazione) obj;
			// Situazione 1. Tabella vuota. Inserisco.

			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getPrenotazioniQuery);
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, prenotazione.getIdPrenotazione());
				ps.setInt(2, prenotazione.getIdOfferta());
				ps.setString(3, prenotazione.getUsernameAcquirente());

				ps.executeUpdate();
			}

			// Situazione 2. Elemento non presente.
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, prenotazione.getIdPrenotazione());

			rs = ps.executeQuery();

			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, prenotazione.getIdPrenotazione());
				ps.setInt(2, prenotazione.getIdOfferta());
				ps.setString(3, prenotazione.getUsernameAcquirente());

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
		Prenotazione prenotazione;
		try {
			prenotazione = (Prenotazione) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(updateQuery);

			ps.setInt(1, prenotazione.getIdPrenotazione());
			ps.setInt(2, prenotazione.getIdOfferta());
			ps.setString(3, prenotazione.getUsernameAcquirente());
			ps.setInt(4, prenotazione.getIdPrenotazione());

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
		Prenotazione prenotazione;
		try {
			prenotazione = (Prenotazione) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, prenotazione.getIdPrenotazione());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		}
	}

	@Override
	public synchronized Prenotazione read(Integer idPrenotazione) throws DAOException {
		Prenotazione prenotazione = new Prenotazione();
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, idPrenotazione);

			rs = ps.executeQuery();

			rs.next();
			prenotazione.setIdPrenotazione(rs.getInt(1));
			prenotazione.setIdOfferta(rs.getInt(2));
			prenotazione.setUsernameAcquirente(rs.getString(3));

			return prenotazione;

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
			ps = conn.prepareStatement(getPrenotazioniQuery);
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

	public synchronized List<Prenotazione> getListaPrenotazioni() throws SQLException, DataException, OraException {
		// TODO Auto-generated method stub
		List<Prenotazione> listPrenotazioni = new ArrayList<Prenotazione>();

		conn = getConnection(usr, pass);

		ps = conn.prepareStatement(getPrenotazioniQuery);

		rs = ps.executeQuery();

		while (rs.next()) {
			Prenotazione prenotazione = new Prenotazione();
			prenotazione.setIdPrenotazione(rs.getInt(1));
			prenotazione.setIdOfferta(rs.getInt(2));
			prenotazione.setUsernameAcquirente(rs.getString(3));

			listPrenotazioni.add(prenotazione);
		}
		return listPrenotazioni;
	}
}
