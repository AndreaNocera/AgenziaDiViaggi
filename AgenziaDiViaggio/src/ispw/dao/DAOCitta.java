/**
 * 
 */
package ispw.dao;

import ispw.entity.Citta;
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
public class DAOCitta extends DAO {
	private static DAOCitta instance = null;

	private static final String getListaCittaQuery = "SELECT * FROM `citta` WHERE 1";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS citta("
			+ "id INT(10) PRIMARY KEY, " + "value VARCHAR(20)" + ")";

	private static final String insertQuery = "INSERT INTO `citta`"
			+ "(`id`, `value`) " + "VALUES (?, ?)";
	private static final String updateQuery = "UPDATE citta SET "
			+ "id=?, value=?" + "WHERE id=?";
	private static final String deleteQuery = "DELETE FROM "
			+ "citta WHERE id=?";
	private static final String findQuery = "SELECT * FROM citta "
			+ "WHERE id=?";

	/*
	 * private static final String findExistsQuery = "SELECT EXISTS( " +
	 * "SELECT * FROM Citta " + "WHERE value = ?)";
	 */

	private static final String dropQuery = "DROP TABLE 'citta'";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private DAOCitta() {
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

	public synchronized static DAOCitta getInstance() {
		if (instance == null)
			instance = new DAOCitta();
		return instance;
	}

	@Override
	public synchronized void insert(Object obj) throws DAOException {
		ResultSet rs;
		Citta citta;
		try {
			citta = (Citta) obj;

			System.out.println("Citta da inserire: " + citta.getValore());
			// Situazione 1. Tabella vuota. Inserisco.

			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getListaCittaQuery);
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, citta.getId());
				ps.setString(2, citta.getValore());

				ps.executeUpdate();
			}

			// Situazione 2. Elemento non presente.
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, citta.getId());

			rs = ps.executeQuery();

			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, citta.getId());
				ps.setString(2, citta.getValore());

				System.out.println("Inserisco citta: " + citta.getValore());

				ps.executeUpdate();
			}
			// Situazione 3.Elemento Presente. Non inserisco.
			System.out.println("Elemento presente: " + citta.getValore());

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		} finally {
			/*closeResource()*/;
		}
	}

	@Override
	public synchronized Citta read(Integer id) throws DAOException {
		Citta citta = new Citta();
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			citta.setId(rs.getInt(1));
			citta.setValore(rs.getString(2));

			return citta;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read.");
		} finally {
			/*closeResource()*/;
		}
	}

	@Override
	public synchronized void update(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Citta citta;
		try {
			citta = (Citta) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(updateQuery);

			ps.setInt(1, citta.getId());
			ps.setString(2, citta.getValore());
			ps.setInt(3, citta.getId());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in update.");
		} catch (SQLException e) {
			throw new DAOException("Errore in update.");
		} finally {
			/*closeResource()*/;
		}

	}

	@Override
	public synchronized void delete(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Citta citta;
		try {
			citta = (Citta) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, citta.getId());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		} finally {
			/*closeResource()*/;
		}
	}

	public synchronized void printListaCitta() throws DAOException {
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(getListaCittaQuery);

			rs = ps.executeQuery();
			System.out.println("Lista Citta.");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
		} catch (ClassCastException e) {
			throw new DAOException("Errore in printListaCitta.");
		} catch (SQLException e) {
			throw new DAOException("Errore in printListaCitta.");
		} finally {
			/*closeResource()*/;
		}
	}

	public synchronized Citta getObjectByValue(String valore) throws DAOException {
		String query = "SELECT * FROM `citta` WHERE `value` = ?";
		ResultSet rs = null;
		Citta citta;
		try {
			conn = getConnection(usr, pass);
			// Situazione 1. Tabella Vuota. Id da ritornare 1.
			ps = conn.prepareStatement(getListaCittaQuery);
			rs = ps.executeQuery();
			if (!rs.next()) {
				// Elemento non esistente. Creazione e salvataggio nel DB.
				citta = new Citta(1, valore);
				insert(citta);
				return citta;
			}
			// Situazione 2. Elemento presente

			ps = conn.prepareStatement(query);

			ps.setString(1, valore);

			rs = ps.executeQuery();
			if (rs.next()) {
				return new Citta(rs.getInt(1), valore);
			}

			// Situazione 3. Elemento non presente.
			ps = conn.prepareStatement(getListaCittaQuery);

			rs = ps.executeQuery();
			rs.last();
			// Elemento non esistente. Creazione e salvataggio nel DB.
			citta = new Citta((rs.getInt(1) + 1), valore);
			insert(citta);
			return citta;
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getID.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getID.");
		} finally {
			/*closeResource()*/;
		}
	}

	/**
	 * Se l'id passato non esiste lancia un'eccezione.
	 * 
	 * @param id
	 * @return
	 */
	public synchronized String getValueById(Integer id) throws DAOException {
		String query = "SELECT * FROM `citta` WHERE `id` = ?";
		ResultSet rs = null;
		try {
			// Situazione 2. Elemento presente

			ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(2);
			}

			throw new DAOException("Errore in getValue.");
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getValue.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getValue.");
		} finally {
			/*closeResource()*/;
		}

	}

	public synchronized void dropTable() throws DAOException {
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(dropQuery);

			ps.executeUpdate();
		} catch (ClassCastException e) {
			throw new DAOException("Errore in dropTable.");
		} catch (SQLException e) {
			throw new DAOException("Errore in dropTable.");
		} finally {
			/*closeResource()*/;
		}
	}
}