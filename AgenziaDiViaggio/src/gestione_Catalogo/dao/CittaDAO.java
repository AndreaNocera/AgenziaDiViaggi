package gestione_Catalogo.dao;

import gestione_Catalogo.entity.Citta;
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
public class CittaDAO extends DAO{
	private static CittaDAO istanza = null;

	private static final String getListaCittaQuery = "SELECT * FROM CITTA WHERE 1";

	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS CITTAPARTENZA(" +
					"ID INTEGER PRIMARY KEY, " +
					"IDESTERNOELEMENTO VARCHAR(30) " +
					")";

	private static final String insertQuery = 
			"INSERT INTO CITTA " +
			"VALUES(?, ?)";
	private static final String updateQuery = 
			"UPDATE CITTA SET " +
			"IDESTERNOELEMENTO=? " +
			"WHERE ID=?";
	private static final String deleteQuery = 
			"DELETE FROM " +
			"CITTA WHERE ID=?";
	private static final String findQuery = 
			"SELECT * FROM CITTA " +
			"WHERE ID=?";
	
	/*private static final String findExistsQuery = 
			"SELECT EXISTS( " +
			"SELECT * FROM Citta " +
			"WHERE value = ?)";
	*/
	
	private static final String dropQuery = 
			"DROP TABLE CITTA IF EXISTS";
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private CittaDAO() {
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

	public static CittaDAO getIstanza() {
		if (istanza == null)
			istanza = new CittaDAO();
		return istanza;
	}

	@Override
	public void insert(Object obj) throws DAOException {
		ResultSet rs;
		Citta citta;
		try {
			citta = (Citta) obj;
			
			System.out.println("Citta da inserire: " + citta.getIDEsternoElemento().toString());
			//Situazione 1. Tabella vuota. Inserisco.
			
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getListaCittaQuery);
			rs = ps.executeQuery();
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, citta.getID());
				ps.setString(2, citta.getIDEsternoElemento().toString());

				ps.executeUpdate();
			}
			
			//Situazione 2. Elemento non presente.
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, citta.getID());
			
			rs = ps.executeQuery();
			
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);
	
				ps.setInt(1, citta.getID());
				ps.setString(2, citta.getIDEsternoElemento().toString());
				
				System.out.println("Inserisco citta: " + citta.getIDEsternoElemento().toString());
				
				ps.executeUpdate();
			}
			//Situazione 3.Elemento Presente. Non inserisco.
			System.out.println("Elemento presente: " + citta.getIDEsternoElemento().toString());
			
		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}
	}

	@Override
	public Citta read(Integer id) throws DAOException {
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			Integer i = rs.getInt(1);
			String s = rs.getString(2);

			return new Città(i, new IDEsternoElemento(s));

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read.");
		}
	}

	@Override
	public void update(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Citta citta;
		try {
			citta = (Citta) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(updateQuery);

			ps.setString(1, citta.getIDEsternoElemento().toString());
			ps.setInt(2, citta.getID());

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
		Citta citta;
		try {
			citta = (Citta) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, citta.getID());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		}
	}

	/*private boolean isInDatabase(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Citta citta;
		try {
			citta = (Citta) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findExistsQuery);

			ps.setString(1, citta.getValore());

			rs = ps.executeQuery();
			
			rs.next();
			if((rs.getString(1)).equals("1"))
				return true;
			return false;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		}
	}*/

	public void printListaCitta() throws DAOException {
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
		}
	}
	
	public Integer getIdByValue(String valore) throws DAOException {
		String query = "SELECT * FROM CITTA WHERE IDESTERNOELEMENTO=?";
		ResultSet rs = null;
		try {
			conn = getConnection(usr, pass);
			//Situazione 1. Tabella Vuota. Id da ritornare 1.
			ps = conn.prepareStatement(getListaCittaQuery);
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
			ps = conn.prepareStatement(getListaCittaQuery);
			
			rs = ps.executeQuery();
			rs.last();
			return rs.getInt(1) + 1;
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
		String query = "SELECT * FROM CITTA WHERE ID=?";
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
