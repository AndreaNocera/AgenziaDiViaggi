package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voyager.nove.exception.ConnectionException;
import voyager.nove.exception.DAOException;
import voyager.nove.model.viaggio.Citta;

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

	private static final String dropQuery = "DROP TABLE 'citta'";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private DAOCitta() {
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

			conn = getConnection();
			ps = conn.prepareStatement(getListaCittaQuery);
			rs = ps.executeQuery();
			
			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, citta.getId());
				ps.setString(2, citta.getValore());

				ps.executeUpdate();
			}

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

			System.out.println("Elemento presente: " + citta.getValore());

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		} finally {
			this.closeResource();
		}
	}

	@Override
	public synchronized Citta read(Integer id) throws DAOException {
		Citta citta = new Citta();
		
		try {
			
			conn = getConnection();

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
			this.closeResource();
		}
	}

	@Override
	public synchronized void update(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Citta citta;
		try {
			citta = (Citta) obj;

			conn = getConnection();

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
			this.closeResource();
		}

	}

	@Override
	public synchronized void delete(Object obj) throws DAOException {
		Citta citta;
		
		try {
			citta = (Citta) obj;

			conn = getConnection();

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, citta.getId());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		} finally {
			this.closeResource();
		}
	}

	public synchronized void printListaCitta() throws DAOException {
		try {
			conn = getConnection();

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
			this.closeResource();
		}
	}

	public synchronized Citta getObjectByValue(String valore) throws DAOException {
		String query = "SELECT * FROM `citta` WHERE `value` = ?";
		ResultSet rs = null;
		Citta citta;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(getListaCittaQuery);
			rs = ps.executeQuery();
			
			if (!rs.next()) {
				citta = new Citta(1, valore);
				insert(citta);
				return citta;
			}

			ps = conn.prepareStatement(query);

			ps.setString(1, valore);

			rs = ps.executeQuery();
			if (rs.next()) {
				return new Citta(rs.getInt(1), valore);
			}

			ps = conn.prepareStatement(getListaCittaQuery);

			rs = ps.executeQuery();
			rs.last();
			citta = new Citta((rs.getInt(1) + 1), valore);
			insert(citta);
			return citta;
		} catch (ConnectionException e) {
			throw new DAOException("Errore in getID.");
		} catch (SQLException e) {
			throw new DAOException("Errore in getID.");
		} finally {
			this.closeResource();
		}
	}
	
	public synchronized String getValueById(Integer id) throws DAOException {
		String query = "SELECT * FROM `citta` WHERE `id` = ?";
		ResultSet rs = null;
		
		try {

			ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(2);
			}

			throw new DAOException("Errore in getValue.");
		} catch (ConnectionException e) {
			throw new DAOException("Errore in getValue.");
		} catch (SQLException e) {
			throw new DAOException("Errore in getValue.");
		} finally {
			this.closeResource();
		}

	}

	public synchronized void dropTable() throws DAOException {
		try {
			conn = getConnection();

			ps = conn.prepareStatement(dropQuery);

			ps.executeUpdate();
		} catch (ClassCastException e) {
			throw new DAOException("Errore in dropTable.");
		} catch (SQLException e) {
			throw new DAOException("Errore in dropTable.");
		} finally {
			this.closeResource();
		}
	}
}