/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.dao
 * 
 * @name UtenteJdbcDAO.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;


import webvoyager.marciani.db.ConnectionManager;
import webvoyager.marciani.db.WebConnectionManager;
import webvoyager.marciani.exception.UtenteEsistenteException;
import webvoyager.marciani.exception.UtenteInesistenteException;
import webvoyager.marciani.model.Login;
import webvoyager.marciani.model.Utente;
import webvoyager.marciani.model.bean.UtenteBean;
import webvoyager.marciani.model.ruoli.FactoryRuoli;
import webvoyager.marciani.utils.DateUtils;

public class UtenteJdbcDAO implements UtenteDAO{
	
	private static UtenteJdbcDAO singletonUtenteDAO = null; 
	
	private static ConnectionManager connectionManager = null;
	
	private UtenteJdbcDAO() {}
	
	public static synchronized UtenteDAO getInstance() {
		if(singletonUtenteDAO == null) {
			singletonUtenteDAO = new UtenteJdbcDAO();
		}
		
		connectionManager = WebConnectionManager.getInstance();
		
		return singletonUtenteDAO;
	}

	@Override
	public synchronized boolean save(Utente utente) throws UtenteEsistenteException {	
		String SQL_INSERT = "INSERT INTO `utenti` " +
				"(`username`, `password`, `ruolo`, `nome`, `cognome`, `citta`, `nascita`, `sesso`, `mail`) " +
				"VALUES (\"" + utente.getLogin().getUsername() + "\", " +
						"\"" + utente.getLogin().getPassword() + "\", " +
						"" + utente.getRuolo().getId() + ", " +
						"\"" + utente.getDatiUtente().getNome() + "\", " +
						"\"" + utente.getDatiUtente().getCognome() + "\", " +
						"\"" + utente.getDatiUtente().getCitta() + "\", " +
						"\"" + DateUtils.getDBStringFromGregorianCalendar(utente.getDatiUtente().getNascita()) + "\", " +
						"\"" + utente.getDatiUtente().getSesso() + "\", " +
						"\"" + utente.getDatiUtente().getMail() + "\")";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_INSERT);
		} catch (SQLException exc) {
			if (exc.getErrorCode() == 1062) {
				throw new UtenteEsistenteException();
			}
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}

	@Override
	public synchronized boolean update(Utente utente) throws UtenteEsistenteException {
		String SQL_INSERT = "UPDATE `utenti` SET " +
				"`nome` = \"" + utente.getDatiUtente().getNome() + "\", " +
				"`cognome` =  \"" + utente.getDatiUtente().getCognome() + "\", " +
				"`citta` = \"" + utente.getDatiUtente().getCitta() + "\", " +
				"`nascita` = \"" + DateUtils.getDBStringFromGregorianCalendar(utente.getDatiUtente().getNascita()) + "\", " +
				"`sesso` = \"" + utente.getDatiUtente().getSesso() + "\", " +
				"`mail` = \"" + utente.getDatiUtente().getMail() + "\", " +
				"`ruolo` = " + utente.getRuolo().getId() + ", " +
				"`password` = \"" + utente.getLogin().getPassword() + "\" " + 
				"WHERE `username` = \"" + utente.getLogin().getUsername() + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_INSERT);
		} catch (SQLException exc) {
			if (exc.getErrorCode() == 1062) {
				throw new UtenteEsistenteException();
			}
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}

	@Override
	public synchronized boolean delete(Utente utente) {
		String SQL_DELETE = "DELETE FROM `utenti` WHERE `username` = \"" + utente.getLogin().getUsername() + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_DELETE);
		} catch (SQLException exc) {
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}
	
	@Override
	public boolean deleteByKey(String username) {
		String SQL_DELETE = "DELETE FROM `utenti` WHERE `username` = \"" + username + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_DELETE);
		} catch (SQLException exc) {
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}
	
	@Override
	public synchronized List<UtenteBean> findAll() {
		List<UtenteBean> listaUtenti = new ArrayList<UtenteBean>();
		String SQL_FIND_ALL = "SELECT * FROM `utenti`";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_ALL);
			
			while(result.next()) {
				UtenteBean utenteBean = new UtenteBean();
				utenteBean.setNome(result.getString("nome"));
				utenteBean.setCognome(result.getString("cognome"));
				utenteBean.setCitta(result.getString("citta"));
				Date data = result.getDate("nascita");
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(data);
				utenteBean.setNascita(cal);
				utenteBean.setSesso(result.getString("sesso"));
				utenteBean.setMail(result.getString("mail"));
				utenteBean.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(result.getInt("ruolo")));
				utenteBean.setUsername(result.getString("username"));
				utenteBean.setPassword(result.getString("password"));	
				
				listaUtenti.add(utenteBean);
			}
		} catch (SQLException exc) {
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return listaUtenti;
	}
	
	@Override
	public List<UtenteBean> find(String query) {
		List<UtenteBean> listaUtenti = new ArrayList<UtenteBean>();
		String SQL_FIND_ALL = "SELECT * FROM `utenti` WHERE `username` LIKE \"" + query + "%\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_ALL);
			
			while(result.next()) {
				UtenteBean utenteBean = new UtenteBean();
				utenteBean.setNome(result.getString("nome"));
				utenteBean.setCognome(result.getString("cognome"));
				utenteBean.setCitta(result.getString("citta"));
				Date data = result.getDate("nascita");
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(data);
				utenteBean.setNascita(cal);
				utenteBean.setSesso(result.getString("sesso"));
				utenteBean.setMail(result.getString("mail"));
				utenteBean.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(result.getInt("ruolo")));
				utenteBean.setUsername(result.getString("username"));
				utenteBean.setPassword(result.getString("password"));	
				
				listaUtenti.add(utenteBean);
			}
		} catch (SQLException exc) {
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return listaUtenti;
	}

	@Override
	public synchronized UtenteBean findByUsername(String username) throws UtenteInesistenteException {
		String SQL_FIND_BY_USERNAME = "SELECT * FROM `utenti` WHERE `username` = \"" + username + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		UtenteBean utenteBean = null;
		
		try {
			
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_BY_USERNAME);
			
			if(result.next()) {
				utenteBean = new UtenteBean();
				utenteBean.setNome(result.getString("nome"));
				utenteBean.setCognome(result.getString("cognome"));
				utenteBean.setCitta(result.getString("citta"));
				Date data = result.getDate("nascita");
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(data);
				utenteBean.setNascita(cal);
				utenteBean.setSesso(result.getString("sesso"));
				utenteBean.setMail(result.getString("mail"));
				utenteBean.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(result.getInt("ruolo")));
				utenteBean.setUsername(result.getString("username"));
				utenteBean.setPassword(result.getString("password"));
				
				return utenteBean;
			} else {
				throw new UtenteInesistenteException();
			}
			
		} catch (SQLException exc) {
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}				
	}

	@Override
	public synchronized UtenteBean findByLogin(Login login) throws UtenteInesistenteException {
		String SQL_FIND_BY_LOGIN = "SELECT * FROM `utenti` " +
				"WHERE `username` = \"" + login.getUsername() + "\" " + 
				"and `password` = \"" + login.getPassword() + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		UtenteBean utenteBean = null;
		
		try {
			
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_BY_LOGIN);
			
			if(result.next()) {
				utenteBean = new UtenteBean();
				utenteBean.setNome(result.getString("nome"));
				utenteBean.setCognome(result.getString("cognome"));
				utenteBean.setCitta(result.getString("citta"));
				Date data = result.getDate("nascita");
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(data);
				utenteBean.setNascita(cal);
				utenteBean.setSesso(result.getString("sesso"));
				utenteBean.setMail(result.getString("mail"));
				utenteBean.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(result.getInt("ruolo")));
				utenteBean.setUsername(result.getString("username"));
				utenteBean.setPassword(result.getString("password"));
			} else {
				throw new UtenteInesistenteException();
			}
			
		} catch (SQLException exc) {
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return utenteBean;
	}

	@Override
	public boolean verifyLogin(Login login)	throws UtenteInesistenteException {
		String SQL_FIND_BY_LOGIN = "SELECT * FROM `utenti` " +
				"WHERE `username` = \"" + login.getUsername() + "\" " + 
				"and `password` = \"" + login.getPassword() + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_BY_LOGIN);
			
			if(result.next()) {
				return true;
			} else {
				throw new UtenteInesistenteException();
			}
			
		} catch (SQLException exc) {
			return false;
		} finally {
			connectionManager.close(connection, statement, result);
		}
	}

		

}
