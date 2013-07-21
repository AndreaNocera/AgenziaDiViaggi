package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voyager.nove.exception.ConnectionException;
import voyager.nove.exception.DAOException;
import voyager.nove.model.viaggio.Ambiente;

public class DAOAmbiente extends DAO {

	private static DAOAmbiente instance = null;

	private static final String getListaAmbientiQuery = "SELECT * FROM `ambienti` WHERE 1";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS ambienti("
			+ "id INT(10) PRIMARY KEY, "
			+ "value VARCHAR(20)" + ")";

	private static final String insertQuery = "INSERT INTO `ambienti`"
			+ "(`id`, `value`) " + "VALUES (?, ?)";
	private static final String updateQuery = "UPDATE ambienti SET "
			+ "id=?, value=?" + "WHERE id=?";
	private static final String deleteQuery = "DELETE FROM "
			+ "ambienti WHERE id=?";
	private static final String findQuery = "SELECT * FROM ambienti "
			+ "WHERE id=?";

	private static final String dropQuery = "DROP TABLE 'ambienti'";
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private DAOAmbiente() {
		try {
			//Class.forName(driverName);

			conn = getConnection();

			ps = conn.prepareStatement(createQuery);

			ps.executeUpdate();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*closeResource*/;
		}
	}

	public synchronized static DAOAmbiente getInstance() {
		if (instance == null)
			instance = new DAOAmbiente();
		return instance;
	}

	@Override
	public synchronized void insert(Object obj) throws DAOException {
		ResultSet rs;
		Ambiente ambiente;
		try {
			
			ambiente = (Ambiente) obj;
			
			conn = getConnection();
			ps = conn.prepareStatement(getListaAmbientiQuery);
			rs = ps.executeQuery();
			
			if(!rs.next()){
				
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, ambiente.getId());
				ps.setString(2, ambiente.getValore());

				ps.executeUpdate();
			}
			
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, ambiente.getId());
			
			rs = ps.executeQuery();
			
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);
	
				ps.setInt(1, ambiente.getId());
				ps.setString(2, ambiente.getValore());
	
				ps.executeUpdate();
			}

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		} finally {
			this.closeResource();
		}

	}

	@Override
	public synchronized Ambiente read(Integer id) throws DAOException {
		Ambiente ambiente = new Ambiente();
		try {
			conn = getConnection();

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			ambiente.setId(rs.getInt(1));
			ambiente.setValore(rs.getString(2));

			return ambiente;

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
		Ambiente ambiente;
		try {
			ambiente = (Ambiente) obj;

			conn = getConnection();

			ps = conn.prepareStatement(updateQuery);

			ps.setInt(1, ambiente.getId());
			ps.setString(2, ambiente.getValore());
			ps.setInt(3, ambiente.getId());

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
		Ambiente ambiente;
		try {
			ambiente = (Ambiente) obj;

			conn = getConnection();

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, ambiente.getId());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		} finally {
			this.closeResource();
		}
	}

	public synchronized void printListaAmbienti() throws DAOException {
		try {
			conn = getConnection();

			ps = conn.prepareStatement(getListaAmbientiQuery);

			rs = ps.executeQuery();
			System.out.println("Lista ambienti.");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
		} catch (ClassCastException e) {
			throw new DAOException("Errore in printListaAmbienti.");
		} catch (SQLException e) {
			throw new DAOException("Errore in printListaAmbienti.");
		} finally {
			this.closeResource();
		}
	}
	
	public synchronized Ambiente getObjectByValue(String valore) throws DAOException {
		String query = "SELECT * FROM `ambienti` WHERE `value` = ?";
		Ambiente ambiente;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			ps = conn.prepareStatement(getListaAmbientiQuery);
			rs = ps.executeQuery();
			if(!rs.next()){

				ambiente = new Ambiente(1,valore);
				insert(ambiente);
				return ambiente;
			}
			
			ps = conn.prepareStatement(query);

			ps.setString(1, valore);

			rs = ps.executeQuery();
			if(rs.next()){
				return new Ambiente(rs.getInt(1), valore); 
			}
			
			ps = conn.prepareStatement(getListaAmbientiQuery);
			
			rs = ps.executeQuery();
			rs.last();
			
			ambiente = new Ambiente((rs.getInt(1) + 1), valore);
			insert(ambiente);
			return ambiente;
			
		} catch (ConnectionException e) {
			throw new DAOException("Errore in getIDByValue in Connection.");
		} catch (SQLException e) {
			throw new DAOException("Errore in getIDByValue in SQL.");
		} finally {
			this.closeResource();
		}
	}
	
	public synchronized String getValueById(Integer id) throws DAOException{
		String query = "SELECT * FROM `ambienti` WHERE `id` = ?";
		
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