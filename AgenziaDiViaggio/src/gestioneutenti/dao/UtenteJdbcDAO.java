/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.dao
 * 
 * @name UtenteJdbcDAO.java
 *
 * @description
 *
 * @author Giacomo
 * 
 */

package gestioneutenti.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import gestioneutenti.db.ConnectionManager;
import gestioneutenti.db.StandaloneConnectionManager;
import gestioneutenti.model.bean.UtenteBean;


public class UtenteJdbcDAO implements UtenteDAO{
	
	ConnectionManager connectionManager = null;
	Connection connection = null;	

	public UtenteJdbcDAO() {
		this.connectionManager = StandaloneConnectionManager.getInstance();		
	}

	@Override
	public boolean save(UtenteBean utenteBean) {		
		String SQL_INSERT = "INSERT INTO `utenti` " +
				"(`username`, `password`, `ruolo`, `nome`, `cognome`, `citta`, `nascita`, `sesso`, `mail`) " +
				"VALUES (\"" + utenteBean.getUsername() + "\", " +
						"\"" + utenteBean.getPassword() + "\", " +
						"\"" + utenteBean.getRuolo().asString() + "\", " +
						"\"" + utenteBean.getNome() + "\", " +
						"\"" + utenteBean.getCognome() + "\", " +
						"\"" + utenteBean.getCitta() + "\", " +
						"\"" + utenteBean.getNascita() + "\", " +
						"\"" + utenteBean.getSesso() + "\", " +
						"\"" + utenteBean.getMail() + "\")";
		
		this.connection = this.connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_INSERT);
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} finally {
			this.connectionManager.close(connection, statement);
		}
		
		return true;
	}

	@Override
	public boolean update(UtenteBean utenteBean) {
		String SQL_INSERT = "UPDATE `utenti` SET " +
				"`nome` = \"" + utenteBean.getNome() + "\", " +
				"`cognome` =  \"" + utenteBean.getCognome() + "\", " +
				"`citta` = \"" + utenteBean.getCitta() + "\", " +
				"`nascita` = \"" + utenteBean.getNascita() + "\", " +
				"`sesso` = \"" + utenteBean.getSesso() + "\", " +
				"`mail` = \"" + utenteBean.getMail() + "\", " +
				"`ruolo` = \"" + utenteBean.getRuolo() + "\", " +
				"`password` = \"" + utenteBean.getPassword() + "\" " + 
				"WHERE `username` = \"" + utenteBean.getUsername() + "\"";
		
		this.connection = this.connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_INSERT);
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} finally {
			this.connectionManager.close(connection, statement);
		}
		
		return true;
	}

	@Override
	public boolean delete(UtenteBean utenteBean) {
		String SQL_DELETE = "DELETE FROM `utenti` WHERE `username` = " + utenteBean.getUsername();
		
		this.connection = this.connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_DELETE);
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} finally {
			this.connectionManager.close(connection, statement);
		}
		
		return true;
	}

	@Override
	public UtenteBean[] findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
