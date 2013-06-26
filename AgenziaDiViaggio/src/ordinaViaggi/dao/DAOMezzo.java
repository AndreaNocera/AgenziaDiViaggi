/**
 * 
 */
package ordinaViaggi.dao;

import ordinaViaggi.exception.ConnectionException;
import ordinaViaggi.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ordinaViaggi.entity.Mezzo;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella
 * @generated 
 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOMezzo extends DAO {
	private static DAOMezzo istance = null;

	private static final String getListaMezziQuery = "SELECT * FROM `mezzi` WHERE 1";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS mezzi("
			+ "id INT(10) PRIMARY KEY, " + "value VARCHAR(20)" + ")";

	private static final String insertQuery = "INSERT INTO `mezzi`"
			+ "(`id`, `value`) " + "VALUES (?, ?)";
	private static final String updateQuery = "UPDATE mezzi SET "
			+ "id=?, value=?" + "WHERE id=?";
	private static final String deleteQuery = "DELETE FROM "
			+ "mezzi WHERE id=?";
	private static final String findQuery = "SELECT * FROM mezzi "
			+ "WHERE id=?";

	/*
	 * private static final String findExistsQuery = "SELECT EXISTS( " +
	 * "SELECT * FROM Mezzi " + "WHERE value = ?)";
	 */

	private static final String dropQuery = "DROP TABLE 'mezzi'";
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private DAOMezzo() {
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
			closeResource();
		}
	}

	public static DAOMezzo getIstance() {
		if (istance == null)
			istance = new DAOMezzo();
		return istance;
	}

	@Override
	public void insert(Object obj) throws DAOException {
		ResultSet rs;
		Mezzo mezzo;
		try {
			
			mezzo = (Mezzo) obj;
			//Situazione 1. Tabella vuota. Inserisco.
			
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getListaMezziQuery);
			rs = ps.executeQuery();
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, mezzo.getId());
				ps.setString(2, mezzo.getValore());

				ps.executeUpdate();
			}
			
			//Situazione 2. Elemento non presente.
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, mezzo.getId());
			
			rs = ps.executeQuery();
			
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);
	
				ps.setInt(1, mezzo.getId());
				ps.setString(2, mezzo.getValore());
	
				ps.executeUpdate();
			}
			//Situazione 3.Elemento Presente. Non inserisco.

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}

	}

	@Override
	public Mezzo read(Integer id) throws DAOException {
		Mezzo mezzo = new Mezzo();
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			mezzo.setId(rs.getInt(1));
			mezzo.setValore(rs.getString(2));

			return mezzo;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read.");
		}
	}

	@Override
	public void update(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Mezzo mezzo;
		try {
			mezzo = (Mezzo) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(updateQuery);

			ps.setInt(1, mezzo.getId());
			ps.setString(2, mezzo.getValore());
			ps.setInt(3, mezzo.getId());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in update.");
		} catch (SQLException e) {
			throw new DAOException("Errore in update.");
		}

	}

	@Override
	public void delete(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Mezzo mezzo;
		try {
			mezzo = (Mezzo) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, mezzo.getId());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		}
	}

	/*
	 * private boolean isInDatabase(Object obj) throws DAOException { // TODO
	 * Auto-generated method stub Mezzo mezzo; try { mezzo = (Mezzo)
	 * obj;
	 * 
	 * conn = getConnection(usr, pass);
	 * 
	 * ps = conn.prepareStatement(findExistsQuery);
	 * 
	 * ps.setString(1, mezzo.getValore());
	 * 
	 * rs = ps.executeQuery();
	 * 
	 * rs.next(); if((rs.getString(1)).equals("1")) return true; return false;
	 * 
	 * } catch (ClassCastException e) { throw new
	 * DAOException("Errore in delete."); } catch (SQLException e) { throw new
	 * DAOException("Errore in delete."); } }
	 */

	public void printListaMezzi() throws DAOException {
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(getListaMezziQuery);

			rs = ps.executeQuery();
			System.out.println("Lista mezzi.");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
		} catch (ClassCastException e) {
			throw new DAOException("Errore in printListaMezzi.");
		} catch (SQLException e) {
			throw new DAOException("Errore in printListaMezzi.");
		}
	}
	
	public Integer getIdByValue(String valore) throws DAOException {
		String query = "SELECT * FROM `mezzi` WHERE `value` = ?";
		ResultSet rs = null;
		try {
			conn = getConnection(usr, pass);
			//Situazione 1. Tabella Vuota. Id da ritornare 1.
			ps = conn.prepareStatement(getListaMezziQuery);
			rs = ps.executeQuery();
			if(!rs.next())
				return 1;
			//Situazione 2. Elemento presente
			
			ps = conn.prepareStatement(query);

			ps.setString(1, valore);

			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1); 
			}
			
			// Situazione 3. Elemento non presente.
			ps = conn.prepareStatement(getListaMezziQuery);
			
			rs = ps.executeQuery();
			rs.last();
			return rs.getInt(1) + 1;
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getID.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getID.");
		}
	}
	
	/**
	 * Se l'id passato non esiste lancia un'eccezione.
	 * @param id
	 * @return
	 */
	public String getValueById(Integer id) throws DAOException{
		String query = "SELECT * FROM `mezzi` WHERE `id` = ?";
		ResultSet rs = null;
		try {
			//Situazione 2. Elemento presente
			
			ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString(2); 
			}
			
			throw new DAOException("Errore in getValue.");
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getValue.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getValue.");
		}
		
	}
	

	public void dropTable() throws DAOException {
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(dropQuery);

			ps.executeUpdate();
		} catch (ClassCastException e) {
			throw new DAOException("Errore in dropTable.");
		} catch (SQLException e) {
			throw new DAOException("Errore in dropTable.");
		}
	}

}