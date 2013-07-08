/**
 * 
 */
package ispw.dao;

import ispw.entity.Traveler;
import ispw.exception.ConnectionException;
import ispw.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella Riccardo
 * @generated 
 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOTraveler extends DAO {
	private static DAOTraveler instance = null;

	private static final String getTravelersQuery = "SELECT *"
			+ "FROM `travelers`";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS travelers("
			+ "idTraveler INT(10) PRIMARY KEY, "
			+ "nome VARCHAR(20), "
			+ "cognome VARCHAR(20), " + "email VARCHAR(40)" + ")";

	private static final String insertQuery = "INSERT INTO `travelers`"
			+ "(`idTraveler`, `nome`, `cognome`, `email`) "
			+ "VALUES (?,?,?,?)";

	private static final String updateQuery = "UPDATE `travelers` "
			+ "SET `idTraveler`=?,`nome`=?,`cognome`=?,`email`=? "
			+ "WHERE `idTraveler`=?";

	private static final String deleteQuery = "DELETE FROM `travelers` "
			+ "WHERE `idTraveler`=?";
	private static final String findQuery = "SELECT * FROM `travelers`"
			+ "WHERE idTraveler=?";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private DAOTraveler() {
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

	public synchronized static DAOTraveler getInstance() {
		if (instance == null)
			instance = new DAOTraveler();
		return instance;
	}

	@Override
	public synchronized void insert(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		ResultSet rs;
		Traveler traveler;
		try {

			traveler = (Traveler) obj;
			// Situazione 1. Tabella vuota. Inserisco.

			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getTravelersQuery);
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, traveler.getIdTraveler());
				ps.setString(2, traveler.getNome());
				ps.setString(3, traveler.getCognome());
				ps.setString(4, traveler.getEmail());

				ps.executeUpdate();
			}

			// Situazione 2. Elemento non presente.
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, traveler.getIdTraveler());

			rs = ps.executeQuery();

			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, traveler.getIdTraveler());
				ps.setString(2, traveler.getNome());
				ps.setString(3, traveler.getCognome());
				ps.setString(4, traveler.getEmail());

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
		Traveler traveler;
		try {
			traveler = (Traveler) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(updateQuery);

			ps.setInt(1, traveler.getIdTraveler());
			ps.setString(2, traveler.getNome());
			ps.setString(2, traveler.getCognome());
			ps.setString(2, traveler.getEmail());
			ps.setInt(3, traveler.getIdTraveler());

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
		Traveler traveler;
		try {
			traveler = (Traveler) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, traveler.getIdTraveler());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		}
	}

	@Override
	public synchronized Traveler read(Integer idTraveler) throws DAOException {
		Traveler traveler = new Traveler();
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, idTraveler);

			rs = ps.executeQuery();

			rs.next();
			traveler.setIdTraveler(rs.getInt(1));
			traveler.setNome(rs.getString(2));
			traveler.setCognome(rs.getString(3));
			traveler.setEmail(rs.getString(4));

			return traveler;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read.");
		}
	}

	public synchronized Traveler getObjectByValue(String nome, String cognome, String email) throws DAOException {
		String query = "SELECT * FROM `travelers` WHERE `nome` = ? AND `cognome` = ? AND `email` = ?";
		ResultSet rs = null;
		Traveler traveler;
		try {
			conn = getConnection(usr, pass);
			// Situazione 1. Tabella Vuota. Id da ritornare 1.
			ps = conn.prepareStatement(getTravelersQuery);
			rs = ps.executeQuery();

			if (!rs.next()) {
				// Elemento non esistente. Creazione e salvataggio nel DB.
				traveler = new Traveler(1, nome, cognome, email);
				insert(traveler);
				return traveler;
			}
			// Situazione 2. Elemento presente

			ps = conn.prepareStatement(query);

			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.setString(3, email);

			rs = ps.executeQuery();
			if (rs.next()) {
				return new Traveler(rs.getInt(1), nome, cognome, email);
			}

			// Situazione 3. Elemento non presente.
			ps = conn.prepareStatement(getTravelersQuery);

			rs = ps.executeQuery();
			rs.last();
			// Elemento non esistente. Creazione e salvataggio nel DB.
			traveler = new Traveler((rs.getInt(1) + 1), nome, cognome, email);
			insert(traveler);
			return traveler;
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getID.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getID.");
		}
	}

}