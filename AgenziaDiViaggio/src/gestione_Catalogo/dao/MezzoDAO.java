package gestione_Catalogo.dao;

import gestione_Catalogo.entity.Mezzo;
import gestione_Catalogo.entity.IDEsternoElemento;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class MezzoDAO extends DAO {
	private static MezzoDAO istanza = null;

	private static final String getListaMezziQuery = "SELECT * FROM MEZZO WHERE 1";

	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS MEZZO(" +
					"ID INTEGER NOT NULL AUTO_INCREMENT, " +
					"IDESTERNOELEMENTO VARCHAR(30), " +
					"PRIMARY KEY (`ID`)" + 
					")";

	private static final String insertQuery = 
			"INSERT INTO MEZZO " +
			"VALUES(?, ?)";
	
	private static final String insertByValueQuery = 
		"INSERT INTO MEZZO(IDESTERNOELEMENTO) " +
		"VALUES(?)";
	
	private static final String updateQuery = 
			"UPDATE MEZZO SET " +
			"IDESTERNOELEMENTO=? " +
			"WHERE ID=?";
	private static final String deleteQuery = 
			"DELETE FROM " +
			"MEZZO WHERE ID=?";
	private static final String findQuery = 
			"SELECT * FROM MEZZO " +
			"WHERE ID=?";

	private static final String findByValueQuery =
			"SELECT * FROM MEZZO WHERE IDESTERNOELEMENTO = ?";

	private static final String dropQuery = "DROP TABLE MEZZO IF EXISTS";
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private MezzoDAO() {
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

	
	public static MezzoDAO getIstanza() {
		if (istanza == null)
			istanza = new MezzoDAO();
		return istanza;
	}


	/*
	 * CRUD - Create
	 * La Insert viene invocata dal costruttore di Mezzo, collegata alla creazione dell'oggetto
	 * Questa particolare insert mi deve ritornare l'id da associare all'oggetto appena creato
	 */
	public Integer insertAndReturnId(IDEsternoElemento idEsternoElemento) {
		ResultSet rs;
		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(findByValueQuery);
			ps.setString(1, idEsternoElemento.toString());
			rs = ps.executeQuery();
			if(rs.next()) { // elemento già presente, ritorno direttamente l'ID. 
				Integer a = rs.getInt(1);
				closeResource();
				return a;
			} else { // elemento non presente: inserisco, inizialmente, solo il valore associato
				ps = conn.prepareStatement(insertByValueQuery);
				ps.setString(1,idEsternoElemento.toString());
				System.out.println("prova " +ps.toString());
				//ps.setInt(1, 1);
				ps.executeUpdate();
				System.out.println("prova " +idEsternoElemento.toString());
				// ora che l'elemento è inserito, richiedo l'ID associato e lo ritorno.
				ps = conn.prepareStatement(findByValueQuery);
				ps.setString(1,idEsternoElemento.toString());
				System.out.println("prova");
				rs = ps.executeQuery();
				if(rs.next()) { // elemento già presente, ritorno direttamente l'ID. 
					Integer a = rs.getInt(1);
					System.out.println("prova return:" + a);
					closeResource();
					return a;
				} else {
					closeResource();
					System.out.println("prova null");
					return null;
				}
			}
		} catch (ClassCastException e) {
			e.printStackTrace();
			closeResource();
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			closeResource();
			return null;
		}
	}
	
	/*
	 * CRUD - Read
	 * La Read invocata nel CatalogoDAO - siamo in fase di fetch del Catalogo dal DB 
	 * Questa particolare read, mi torna solo il valore, l'id l'ho preso dal CatalogoDAO
	 */

	public IDEsternoElemento readOnlyValue(Integer id) {
		String s;
		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(findQuery);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				s = rs.getString(2);
			} else { 
				s = null;
			}
			closeResource();
			return new IDEsternoElemento(s);
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * CRUD - Update
	 * Da invocare nei metodo set di Mezzo
	 */

	public void update(Mezzo mezzo){
		// TODO Auto-generated method stub
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(updateQuery);

			ps.setString(1, mezzo.getIDEsternoElemento().toString());
			ps.setInt(2, mezzo.getID());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResource();
		}

	}

	/*
	 * CRUD - Delete
	 * Da Invocare (probabilmente) alla rimozione di una tratta, quando non vi sono pi� Ambienti uguali
	 */
	public void delete(Mezzo mezzo){
		// TODO Auto-generated method stub
		try {

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, mezzo.getID());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResource();
		}
	}
	
	
	
	public void dropTable() {
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(dropQuery);

			ps.executeUpdate();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResource();
		}
	}
	
	/*  Metodi deprecabili

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

				ps.setInt(1, mezzo.getID());
				ps.setString(2, mezzo.getIDEsternoElemento().toString());

				ps.executeUpdate();
			}
			
			//Situazione 2. Elemento non presente.
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, mezzo.getID());
			
			rs = ps.executeQuery();
			
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);
	
				ps.setInt(1, mezzo.getID());
				ps.setString(2, mezzo.getIDEsternoElemento().toString());
	
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
		String query = "SELECT * FROM MEZZI WHERE IDESTERNOELEMENTO=?";
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getID.");
		}
	}
	
	
	public String getValueById(Integer id) throws DAOException{
		String query = "SELECT * FROM MEZZO WHERE ID=?";
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
	
	*/
	

	

}
