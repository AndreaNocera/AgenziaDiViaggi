package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voyager.nove.exception.ConnectionException;
import voyager.nove.exception.DAOException;
import voyager.nove.model.viaggio.Via;

public class DAOVia extends DAO{
	private static DAOVia instance = null;

	private static final String getListaViaQuery = "SELECT * FROM `via` WHERE 1";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS via("
			+ "id INT(10) PRIMARY KEY, " + "value VARCHAR(20)" + ")";

	private static final String insertQuery = "INSERT INTO `via`"
			+ "(`id`, `value`) " + "VALUES (?, ?)";
	private static final String updateQuery = "UPDATE via SET "
			+ "id=?, value=?" + "WHERE id=?";
	private static final String deleteQuery = "DELETE FROM "
			+ "via WHERE id=?";
	private static final String findQuery = "SELECT * FROM via "
			+ "WHERE id=?";

	private static final String dropQuery = "DROP TABLE 'via'";
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private DAOVia() {
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

	public static DAOVia getInstance() {
		if (instance == null)
			instance = new DAOVia();
		return instance;
	}

	@Override
	public void insert(Object obj) throws DAOException {
		ResultSet rs;
		Via via;
		try {
			
			via = (Via) obj;
			
			conn = getConnection();
			ps = conn.prepareStatement(getListaViaQuery);
			rs = ps.executeQuery();
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, via.getId());
				ps.setString(2, via.getValore());

				ps.executeUpdate();
			}
			
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, via.getId());
			
			rs = ps.executeQuery();
			
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);
	
				ps.setInt(1, via.getId());
				ps.setString(2, via.getValore());
	
				ps.executeUpdate();
			}

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}
	}

	@Override
	public Via read(Integer id) throws DAOException {
		Via via = new Via();
		try {
			conn = getConnection();

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			via.setId(rs.getInt(1));
			via.setValore(rs.getString(2));

			return via;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read.");
		}
	}

	@Override
	public void update(Object obj) throws DAOException {
		Via via;
		try {
			via = (Via) obj;

			conn = getConnection();

			ps = conn.prepareStatement(updateQuery);

			ps.setInt(1, via.getId());
			ps.setString(2, via.getValore());
			ps.setInt(3, via.getId());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in update.");
		} catch (SQLException e) {
			throw new DAOException("Errore in update.");
		}

	}

	@Override
	public void delete(Object obj) throws DAOException {
		Via via;
		try {
			via = (Via) obj;

			conn = getConnection();

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, via.getId());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		}
	}

	public void printListaVia() throws DAOException {
		try {
			conn = getConnection();

			ps = conn.prepareStatement(getListaViaQuery);

			rs = ps.executeQuery();
			System.out.println("Lista via.");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
		} catch (ClassCastException e) {
			throw new DAOException("Errore in printListaVia.");
		} catch (SQLException e) {
			throw new DAOException("Errore in printListaVia.");
		}
	}
	
	public Via getObjectByValue(String valore) throws DAOException {
		String query = "SELECT * FROM `via` WHERE `value` = ?";
		ResultSet rs = null;
		Via via;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(getListaViaQuery);
			rs = ps.executeQuery();
			if(!rs.next()){
				via = new Via(1,valore);
				insert(via);
				return via;
			}
			
			ps = conn.prepareStatement(query);

			ps.setString(1, valore);

			rs = ps.executeQuery();
			if(rs.next()){
				return new Via(rs.getInt(1), valore); 
			}
			
			ps = conn.prepareStatement(getListaViaQuery);
			
			rs = ps.executeQuery();
			rs.last();
			via = new Via((rs.getInt(1) + 1), valore);
			insert(via);
			return via;
		} catch (ConnectionException e) {
			throw new DAOException("Errore in getID.");
		} catch (SQLException e) {
			throw new DAOException("Errore in getID.");
		}
	}
	
	public String getValueById(Integer id) throws DAOException{
		String query = "SELECT * FROM `via` WHERE `id` = ?";
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString(2); 
			}
			
			throw new DAOException("Errore in getValue.");
		} catch (ConnectionException e) {
			throw new DAOException("Errore in getValue.");
		} catch (SQLException e) {
			throw new DAOException("Errore in getValue.");
		}
		
	}

	public void dropTable() throws DAOException {
		try {
			conn = getConnection();

			ps = conn.prepareStatement(dropQuery);

			ps.executeUpdate();
		} catch (ClassCastException e) {
			throw new DAOException("Errore in dropTable.");
		} catch (SQLException e) {
			throw new DAOException("Errore in dropTable.");
		}
	}
	
}