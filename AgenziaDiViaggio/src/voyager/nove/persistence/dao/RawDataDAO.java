/**
 * @author
 * Francesco Tomei
 */
package voyager.nove.persistence.dao;

//import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voyager.nove.model.viaggio.ElementoCatalogo;
import voyager.nove.model.viaggio.basic.IDEsternoElemento;
import voyager.nove.persistence.manager.ConnectionManager;
import voyager.nove.persistence.manager.JDBCConnectionManager;

public class RawDataDAO {
	
	private static ConnectionManager connectionManager;

	protected static RawDataDAO istanza = null;
	protected PreparedStatement prepStat;
	protected ResultSet resSet;
	protected Connection conn;
	private static String tabella;
	protected String createQuery, dropQuery, truncateQuery; 
	protected String getListQuery, insertQuery, updateQuery, deleteQuery, findByIdQuery, findByValueQuery; 
	
	protected RawDataDAO(String tabella) {
		try {
			createQuery = "CREATE TABLE IF NOT EXISTS " + tabella + " (ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, idesternoelemento VARCHAR(30) )";
			findByIdQuery = "SELECT * FROM " + tabella + " WHERE id=? LIMIT 1";
			findByValueQuery = "SELECT * FROM " + tabella + " WHERE idesternoelemento=? LIMIT 1";
			getListQuery = "SELECT * FROM " + tabella + " WHERE NOME=? LIMIT 1";
			insertQuery = "INSERT INTO " + tabella + "(idesternoelemento) VALUES(?)";
			updateQuery = "UPDATE " + tabella + " SET idesternoelemento=? WHERE id=? LIMIT 1";
			deleteQuery = "DELETE FROM " + tabella + " WHERE id=? LIMIT 1";
			dropQuery = "DROP TABLE " + tabella  + " IF EXISTS";
			truncateQuery = "TRUNCATE TABLE " + tabella + "IF EXISTS";
			connectionManager = JDBCConnectionManager.getInstance();
			conn = connectionManager.getConnection();
			prepStat = conn.prepareStatement(createQuery);
			prepStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}
	
	public static RawDataDAO getIstanza() {
		if (istanza == null)
			istanza = new RawDataDAO(tabella);
		return istanza;
	}
	
	public Integer insertAndReturnId(IDEsternoElemento valore) {
		ResultSet rs;
		Integer id ;
		try {
			// si comincia con il cercare l'elemento da inserire
			connectionManager = JDBCConnectionManager.getInstance();
			conn = connectionManager.getConnection();
			prepStat = conn.prepareStatement(findByValueQuery);
			prepStat.setString(1, valore.toString());
			rs = prepStat.executeQuery();
			if(rs.next()){ // se l'elemento è già presente, si ritorna il suo ID
				id = new Integer(rs.getInt(1));
				System.out.println("id " + id);
				closeResource();
				return(id);
			} else { // se l'elemento non era presente, lo si inserisce solo per valore
				prepStat = conn.prepareStatement(insertQuery);
				prepStat.setString(1, valore.toString());
				prepStat.executeUpdate();
				// ora che l'elemento è inserito, richiedo l'ID associato e lo ritorno.
				prepStat = conn.prepareStatement(findByValueQuery);
				prepStat.setString(1, valore.toString());
				rs = prepStat.executeQuery();
				if(rs.next()){ // se non ci sono risulati, il database non funziona.
					id = new Integer(rs.getInt(1));
					System.out.println("id "+ id);
					closeResource();
					return(id);
				}
			}
		} catch (ClassCastException e) {
			//throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			//throw new DAOException("Errore in insert SQLException.");
		}
		return null;
	
	}
	
	public void delete(Integer id){
		try {
			connectionManager = JDBCConnectionManager.getInstance();
			conn = connectionManager.getConnection();
			prepStat = conn.prepareStatement(deleteQuery);
			prepStat.setInt(1, id);
			prepStat.executeUpdate();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResource();
		}
	}
	
	public IDEsternoElemento readOnlyValue(Integer id) {
		String value;
		try {
			connectionManager = JDBCConnectionManager.getInstance();
			conn = connectionManager.getConnection();
			prepStat = conn.prepareStatement(findByIdQuery);
			prepStat.setInt(1, id);
			resSet = prepStat.executeQuery();
			if (resSet.next()) {
				value = resSet.getString(2);
				closeResource();
				return new IDEsternoElemento(value);
			}
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(ElementoCatalogo elemento){
		try {
			connectionManager = JDBCConnectionManager.getInstance();
			conn = connectionManager.getConnection();
			prepStat = conn.prepareStatement(updateQuery);
			prepStat.setString(1, elemento.getIDEsternoElemento().toString());
			prepStat.setInt(2, elemento.getID());
			prepStat.executeUpdate();
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
			connectionManager = JDBCConnectionManager.getInstance();
			conn = connectionManager.getConnection();
			prepStat = conn.prepareStatement(dropQuery);
			prepStat.executeUpdate();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResource();
		}
	}
	
	public void truncateTable() {
		try {
			connectionManager = JDBCConnectionManager.getInstance();
			conn = connectionManager.getConnection();
			prepStat = conn.prepareStatement(truncateQuery);
			prepStat.executeUpdate();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResource();
		}
	}
	
//	public void getElemento(Class c) {
//	//preparo i parametri
//	Class<?> primoParametro	 = Class.forName("gestione_Catalogo.entity.IDEsternoElemento");
//	Class<?>[] parametri = {primoParametro};
//	//prendo il costruttore della classe con i parametri indicati
//	Constructor<?> costruttore = c.getConstructor(parametri);
//	//creo l'oggetto
//	Ambiente a = (Ambiente) costruttore.newInstance(new IDEsternoElemento(""));
//	}
//	
	public void closeResource() {
		if (resSet != null) {
			try {
				resSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (prepStat != null) {
			try {
				prepStat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
