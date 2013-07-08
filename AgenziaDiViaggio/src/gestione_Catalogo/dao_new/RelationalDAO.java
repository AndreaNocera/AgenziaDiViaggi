/**
 * @author
 * Francesco Tomei
 */
package gestione_Catalogo.dao_new;

import gestione_Catalogo.dao.Persistenza;
import gestione_Catalogo.entity.ElementoCatalogo;
import gestione_Catalogo.entity.IDEsternoElemento;
//import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class RelationalDAO {

	protected static RelationalDAO istanza = null;
	protected PreparedStatement prepStat;
	protected ResultSet resSet;
	protected Connection conn;
	
	private static String tabella;
	private Vector<String> fields;
	private Vector<String> fields_attributes;
	private String idField;
	protected String createQuery, dropQuery, truncateQuery;
	protected String getListQuery, insertQuery, updateQuery, deleteQuery, findByIdQuery, findByValueQuery; 
	
	private String getStruttura() {
		String struttura = "(" + idField + " INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " ;
		Iterator<String> i = fields.listIterator();
		Iterator<String> k = fields_attributes.listIterator();
	    while (i.hasNext()) {
	    	struttura += i.next() + " " + k.next() + " , " ;
	    }
	    i = fields.listIterator();
	    while (i.hasNext()) {
	    	String campo = i.next();
	    	struttura += i.next() + " , FOREIGN KEY ("+ campo + ") REFERENCES " + campo + "("+ idField + ")";
	    }
	    struttura += ") ";
		return struttura;
	}
	
	protected RelationalDAO(String tabella) {
		try {
			createQuery = "CREATE TABLE IF NOT EXISTS " + tabella + this.getStruttura();
			findByIdQuery = "SELECT * FROM " + tabella + " WHERE id=? LIMIT 1";
			findByValueQuery = "SELECT * FROM " + tabella + " WHERE ?=? LIMIT 1";
			getListQuery = "SELECT * FROM " + tabella + " WHERE NOME=? LIMIT 1";
			insertQuery = "INSERT INTO " + tabella + "(idesternoelemento) VALUES(?)";
			updateQuery = "UPDATE " + tabella + " SET ?=? WHERE id=? LIMIT 1";
			deleteQuery = "DELETE FROM " + tabella + " WHERE ID=? LIMIT 1";
			dropQuery = "DROP TABLE " + tabella  + " IF EXISTS";
			truncateQuery = "TRUNCATE TABLE " + tabella + "IF EXISTS";
			conn = Persistenza.getConnection();
			prepStat = conn.prepareStatement(createQuery);
			prepStat.executeUpdate();
			System.out.println("creazione DAO");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}
	
	public static RelationalDAO getIstanza() {
		if (istanza == null)
			istanza = new RelationalDAO(tabella);
		return istanza;
	}
	
	public Integer insertAndReturnId(IDEsternoElemento valore) {
		ResultSet rs;
		Integer id ;
		try {
			// si comincia con il cercare l'elemento da inserire
			conn = Persistenza.getConnection();
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
			conn = Persistenza.getConnection();
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
			conn = Persistenza.getConnection();
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
			conn = Persistenza.getConnection();
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
			conn = Persistenza.getConnection();
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
			conn = Persistenza.getConnection();
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
