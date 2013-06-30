package gestione_Catalogo.dao;

import gestione_Catalogo.entity.Via;
import gestione_Catalogo.entity.IDEsternoElemento;
import gestione_Catalogo.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class ViaDAO extends DAO{
	private static ViaDAO istanza = null;

	private static final String getListaViaQuery = "SELECT * FROM VIA WHERE 1";

	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS VIA(" +
					"ID INTEGER PRIMARY KEY, " +
					"IDESTERNOELEMENTO VARCHAR(30) " +
					")";

	private static final String insertQuery = 
			"INSERT INTO VIA " +
			"VALUES(?, ?)";
	private static final String updateQuery = 
			"UPDATE VIA SET " +
			"IDESTERNOELEMENTO=?, " +
			"WHERE ID=?";
	private static final String deleteQuery = 
			"DELETE FROM " +
			"VIA WHERE ID=?";
	private static final String findQuery = 
			"SELECT * FROM VIA " +
			"WHERE ID=?";

	private static final String findByValueQuery =
			"SELECT * FROM VIA WHERE IDESTERNOELEMENTO = ?";


	private static final String dropQuery = 
			"DROP TABLE VIA IF EXISTS";
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private ViaDAO() {
		try {
			Class.forName(driverName);

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(createQuery);

			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}

	public static ViaDAO getIstanza() {
		if (istanza == null)
			istanza = new ViaDAO();
		return istanza;
	}

	
	

	/*
	 * CRUD - Create
	 * La Insert viene invocata dal costruttore di Via, collegata alla creazione dell'oggetto
	 * Questa particolare insert mi deve ritornare l'id da associare all'oggetto appena creato
	 */

	public int insertAndReturnId(IDEsternoElemento idEsternoElemento) throws DAOException {
		// TODO Auto-generated method stub
		ResultSet rs;
		try {
			//Situazione 1. Tabella vuota. Inserisco con ID 1.
			
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getListaViaQuery);
			rs = ps.executeQuery();
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, 1);
				ps.setString(2, idEsternoElemento.toString());

				ps.executeUpdate();
				return 1;
			} else {
				
				//Situazione 2. Elemento non presente. Inserisco con l'id successivo all'ultimo elemento
				ps = conn.prepareStatement(findByValueQuery);

				ps.setString(1, idEsternoElemento.toString());
				
				rs = ps.executeQuery();
				
				if(!rs.next()){
					//prima prendo l'id successivo all'ultimo elemento
					ps = conn.prepareStatement(getListaViaQuery);
					
					rs = ps.executeQuery();
					
					rs.last();
					
					int id = rs.getInt(1)+1;
					
					ps = conn.prepareStatement(insertQuery);
		
					ps.setInt(1, id);
					ps.setString(2, idEsternoElemento.toString());
		
					ps.executeUpdate();
					return id;
				} else {
					
					//Situazione 3.Elemento Presente. Non inserisco, ma ritorno il suo id.
					ps = conn.prepareStatement(findByValueQuery);

					ps.setString(1, idEsternoElemento.toString());
					
					rs = ps.executeQuery();
					
					rs.next(); //Lo sposto avanti alla prima, e unica, riga
					
					return rs.getInt(1);
									
				}			
				
			}			

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}finally {
			closeResource();
		}

	}
	
	/*
	 * CRUD - Read
	 * La Read invocata nel CatalogoDAO - siamo in fase di fetch del Catalogo dal DB 
	 * Questa particolare read, mi torna solo il valore, l'id l'ho preso dal CatalogoDAO
	 */

	public IDEsternoElemento readOnlyValue(Integer id) throws DAOException {
		
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			String s =rs.getString(2);

			return new IDEsternoElemento(s);

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read.");
		}finally {
			closeResource();
		}
	}
	
	/*
	 * CRUD - Update
	 * Da invocare nei metodo set di Via
	 */

	public void update(Via via) throws DAOException {
		// TODO Auto-generated method stub
		try {

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(updateQuery);

			ps.setString(1, via.getIDEsternoElemento().toString());
			ps.setInt(2, via.getID());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in update.");
		} catch (SQLException e) {
			throw new DAOException("Errore in update.");
		}finally {
			closeResource();
		}

	}

	/*
	 * CRUD - Delete
	 * Da Invocare (probabilmente) alla rimozione di una tratta, quando non vi sono più Ambienti uguali
	 */
	
	public void delete(Via via) throws DAOException {
		// TODO Auto-generated method stub
		try {

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, via.getID());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		}finally {
			closeResource();
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
		}finally {
			closeResource();
		}
	}

	
	/* Metodi da deprecare
	public void insert(Object obj) throws DAOException {
		ResultSet rs;
		Via via;
		try {
			
			via = (Via) obj;
			//Situazione 1. Tabella vuota. Inserisco.
			
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getListaViaQuery);
			rs = ps.executeQuery();
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, via.getID());
				ps.setString(2, via.getIDEsternoElemento().toString());

				ps.executeUpdate();
			}
			
			//Situazione 2. Elemento non presente.
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, via.getID());
			
			rs = ps.executeQuery();
			
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);
	
				ps.setInt(1, via.getID());
				ps.setString(2, via.getIDEsternoElemento().toString());
	
				ps.executeUpdate();
			}
			//Situazione 3.Elemento Presente. Non inserisco.

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}

	}
	
	public Integer getIdByValue(String valore) throws DAOException {
		String query = "SELECT * FROM VIA WHERE IDESTERNOELEMENTO=?";
		ResultSet rs = null;
		try {
			conn = getConnection(usr, pass);
			//Situazione 1. Tabella Vuota. Id da ritornare 1.
			ps = conn.prepareStatement(getListaViaQuery);
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
			ps = conn.prepareStatement(getListaViaQuery);
			
			rs = ps.executeQuery();
			rs.last();
			return rs.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getID.");
		}
	}
	
	
	public String getValueById(Integer id) throws DAOException{
		String query = "SELECT * FROM VIA WHERE ID=?";
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getValue.");
		}
		
	} */

	
}
