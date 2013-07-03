/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.dao
 * 
 * @name UtenteJdbcDAO.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;
import gestioneutenti.db.ConnectionManager;
import gestioneutenti.db.StandaloneConnectionManager;
import gestioneutenti.db.WebConnectionManager;
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.bean.LoginBean;
import gestioneutenti.model.bean.UtenteBean;
import gestioneutenti.model.ruoli.FactoryRuoli;

public class UtenteJdbcDAO implements UtenteDAO{
	
	private static UtenteJdbcDAO singletonUtenteDAO = null; 
	
	private static ConnectionManager connectionManager = null;
	//private Connection connection = null;
	
	private UtenteJdbcDAO() {}
	
	public static synchronized UtenteDAO getInstance() {
		if(singletonUtenteDAO == null) {
			singletonUtenteDAO = new UtenteJdbcDAO();
		}
		
		connectionManager = StandaloneConnectionManager.getInstance();	
		
		return singletonUtenteDAO;
	}
	
	public static synchronized UtenteDAO getWebInstance() {
		if(singletonUtenteDAO == null) {
			singletonUtenteDAO = new UtenteJdbcDAO();
		}
		
		connectionManager = WebConnectionManager.getInstance();
		
		return singletonUtenteDAO;
	}

	@Override
	public synchronized boolean save(UtenteBean utenteBean) {	
		GregorianCalendar cal = utenteBean.getNascita();
		String data = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
		String SQL_INSERT = "INSERT INTO `utenti` " +
				"(`username`, `password`, `ruolo`, `nome`, `cognome`, `citta`, `nascita`, `sesso`, `mail`) " +
				"VALUES (\"" + utenteBean.getUsername() + "\", " +
						"\"" + utenteBean.getPassword() + "\", " +
						"" + utenteBean.getRuolo().getId() + ", " +
						"\"" + utenteBean.getNome() + "\", " +
						"\"" + utenteBean.getCognome() + "\", " +
						"\"" + utenteBean.getCitta() + "\", " +
						"\"" + data + "\", " +
						"\"" + utenteBean.getSesso() + "\", " +
						"\"" + utenteBean.getMail() + "\")";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_INSERT);
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}

	@Override
	public synchronized boolean update(UtenteBean utenteBean) {
		GregorianCalendar cal = utenteBean.getNascita();
		String data = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
		String SQL_INSERT = "UPDATE `utenti` SET " +
				"`nome` = \"" + utenteBean.getNome() + "\", " +
				"`cognome` =  \"" + utenteBean.getCognome() + "\", " +
				"`citta` = \"" + utenteBean.getCitta() + "\", " +
				"`nascita` = \"" + data + "\", " +
				"`sesso` = \"" + utenteBean.getSesso() + "\", " +
				"`mail` = \"" + utenteBean.getMail() + "\", " +
				"`ruolo` = " + utenteBean.getRuolo().getId() + ", " +
				"`password` = \"" + utenteBean.getPassword() + "\" " + 
				"WHERE `username` = \"" + utenteBean.getUsername() + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_INSERT);
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}

	@Override
	public synchronized boolean delete(UtenteBean utenteBean) {
		String SQL_DELETE = "DELETE FROM `utenti` WHERE `username` = \"" + utenteBean.getUsername() + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_DELETE);
		} catch (SQLException exc) {
			exc.printStackTrace();
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
			exc.printStackTrace();
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
			exc.printStackTrace();
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}				
	}

	@Override
	public synchronized UtenteBean findByLogin(LoginBean loginBean) throws UtenteInesistenteException {
		String SQL_FIND_BY_LOGIN = "SELECT * FROM `utenti` " +
				"WHERE `username` = \"" + loginBean.getUsername() + "\" " + 
				"and `password` = \"" + loginBean.getPassword() + "\"";
		
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
			exc.printStackTrace();
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return utenteBean;
	}

	@Override
	public boolean verifyLogin(LoginBean loginBean)	throws UtenteInesistenteException {
		String SQL_FIND_BY_LOGIN = "SELECT * FROM `utenti` " +
				"WHERE `username` = \"" + loginBean.getUsername() + "\" " + 
				"and `password` = \"" + loginBean.getPassword() + "\"";
		
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
			exc.printStackTrace();
			return false;
		} finally {
			connectionManager.close(connection, statement, result);
		}
	}

}
