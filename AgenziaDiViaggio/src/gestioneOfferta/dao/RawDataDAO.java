/**
 * @author
 * Francesco Tomei
 */
package gestioneOfferta.dao;

import gestionePersistenza.PersistencyConnection;
import gestioneOfferta.entity.Elemento;
import gestione_Catalogo.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RawDataDAO {

	protected static PreparedStatement prepStat;
	protected static ResultSet resSet;
	protected static Connection conn;
	protected static final String getList = "SELECT * FROM __TABLENAME__ WHERE 1";
	protected static final String createQuery = "CREATE TABLE IF NOT EXISTS __TABLENAME__(ID INTEGER PRIMARY KEY, nome VARCHAR(30) )";
	protected static final String insertQuery = "INSERT INTO __TABLENAME__(NOME) VALUES(?)";
	protected static final String updateQuery = "UPDATE __TABLENAME__ SET NOME WHERE ID=? LIMIT 1";
	protected static final String deleteQuery = "DELETE FROM __TABLENAME__ WHERE ID=? LIMIT 1";
	protected static final String findQuery = "SELECT * FROM __TABLENAME__ WHERE ID=? LIMIT 1";
	protected static final String findByNameQuery = "SELECT * FROM __TABLENAME__ WHERE NOME=? LIMIT 1";
	
	
	private RawDataDAO() {
		try {
			conn = PersistencyConnection.getConnection();
			prepStat = conn.prepareStatement(createQuery);
			prepStat.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
	}
	
	public static int insert(Elemento elemento) throws DAOException {
		ResultSet rs;
		try {
			//Situazione 1. Tabella vuota. Inserisco.
			RawDataDAO.conn = PersistencyConnection.getConnection();
			prepStat = conn.prepareStatement(RawDataDAO.getList.replaceFirst("__TABLENAME__", elemento.getPersistencyName()));
			rs = prepStat.executeQuery();
			if(!rs.next()){
				prepStat = conn.prepareStatement(insertQuery.replaceFirst("__TABLENAME__", elemento.getPersistencyName()));
				prepStat.setString(1, elemento.getNome());
				prepStat.executeUpdate();
				return 1;
			} else {
			
				//Situazione 2. Elemento non presente.
				prepStat = conn.prepareStatement(findQuery.replaceFirst("__TABLENAME__", elemento.getPersistencyName()));
				prepStat.setString(1, elemento.getNome());
				rs = prepStat.executeQuery();
				
				if(!rs.next()){
					prepStat = conn.prepareStatement(insertQuery.replaceFirst("__TABLENAME__", elemento.getPersistencyName()));
					prepStat.setString(1, elemento.getNome());
				prepStat.executeUpdate();
				return 1;
			}
			//Situazione 3.Elemento Presente. Non inserisco.
			}
		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}
	}
	
	
}
