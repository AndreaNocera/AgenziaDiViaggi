package gestioneutenti.dao;



import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.model.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**Persistence_Db
 * Connection manager verso il db di utenti + altre funzioni utili.
 */
public class Persistence_Db {

	public Connection conn;
	public PreparedStatement pstatement;
	public Statement statement;
	ResultSet resultset;


	private static class SingletonHolder { 
		public static final Persistence_Db INSTANCE = new Persistence_Db();
	}
	public static Persistence_Db getinstance(){
		return SingletonHolder.INSTANCE;
	};


	private Persistence_Db() {
		try{
			Class.forName(config_db_utenti.NOME_DRIVER);
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		} 
		createDb();
	}

	public void createDb() {
		connect();
		try {
			pstatement=conn.prepareStatement(config_db_utenti.QUERY_CREA_TABELLA);
			pstatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}	
	}

	public synchronized void connect(){
		try {
			conn = DriverManager.getConnection(config_db_utenti.PERCORSO_COMPLETO_DB, config_db_utenti.USER_CONNESSIONE, config_db_utenti.PASSWORD_CONNESSIONE);
			statement=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public synchronized void close() {
		try {
			if(conn != null)conn.close();
			if(statement!=null)statement.close();
			if(statement!=null)pstatement.close();						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList <Utente_db_DAO> get_all() throws SQLException{
		ArrayList <Utente_db_DAO> lista_completa_utenti= new ArrayList<Utente_db_DAO>();
		try {
			connect();
			statement=conn.createStatement();
			resultset=statement.executeQuery(config_db_utenti.QUERY_SELECT_ALL);
			boolean element=resultset.next();

			while(element){
				Login login= new Login(resultset.getString(config_db_utenti.INDICE_USERNAME),resultset.getString(config_db_utenti.INDICE_PASSWORD));
				Utente_db_DAO utente=new Utente_db_DAO();

				utente.setLogin(login);
				utente.setNome((resultset.getString(config_db_utenti.INDICE_NOME)));
				utente.setCognome((resultset.getString(config_db_utenti.INDICE_COGNOME)));
				utente.setCitta((resultset.getString(config_db_utenti.INDICE_CITTA)));
				utente.setNascita((resultset.getString(config_db_utenti.INDICE_NASCITA)));
				utente.setSesso((resultset.getString(config_db_utenti.INDICE_SESSO)));
				utente.setMail((resultset.getString(config_db_utenti.INDICE_MAIL)));
				utente.setRuolo(resultset.getInt(config_db_utenti.INDICE_RUOLO));
				utente.setResetCode(resultset.getString(config_db_utenti.INDICE_RCODE));

				lista_completa_utenti.add(utente);
				element=resultset.next();
			}

		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close();
		}
		return lista_completa_utenti;
	}


	public ArrayList <Utente_db_DAO> getUsersbyName(ArrayList <Utente_db_DAO> lista_completa,String nome ){
		ArrayList <Utente_db_DAO> lista_filtrata_utenti= new ArrayList<Utente_db_DAO>();
		try {
			connect();
			pstatement=conn.prepareStatement(config_db_utenti.QUERY_SELECT_NOME);
			pstatement.setString(1, nome);
			System.out.println(pstatement.toString());
			resultset=pstatement.executeQuery();
			boolean element=resultset.next();

			while(element){
				Login login= new Login(resultset.getString(config_db_utenti.INDICE_USERNAME),resultset.getString(config_db_utenti.INDICE_PASSWORD));
				Utente_db_DAO utente=new Utente_db_DAO();

				utente.setLogin(login);
				utente.setNome((resultset.getString(config_db_utenti.INDICE_NOME)));
				utente.setCognome((resultset.getString(config_db_utenti.INDICE_COGNOME)));
				utente.setCitta((resultset.getString(config_db_utenti.INDICE_CITTA)));
				utente.setNascita((resultset.getString(config_db_utenti.INDICE_NASCITA)));
				utente.setSesso((resultset.getString(config_db_utenti.INDICE_SESSO)));
				utente.setMail((resultset.getString(config_db_utenti.INDICE_MAIL)));
				utente.setRuolo(resultset.getInt(config_db_utenti.INDICE_RUOLO));
				utente.setResetCode(resultset.getString(config_db_utenti.INDICE_RCODE));

				lista_filtrata_utenti.add(utente);
				element=resultset.next();
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (LoginInconsistenteException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return lista_filtrata_utenti;	

	}
	public ArrayList <Utente_db_DAO> getUsersbyLastname(ArrayList <Utente_db_DAO> lista_completa,String cognome ){
		ArrayList <Utente_db_DAO> lista_filtrata_utenti= new ArrayList<Utente_db_DAO>();
		try {
			connect();
			pstatement=conn.prepareStatement(config_db_utenti.QUERY_SELECT_COGNOME);
			pstatement.setString(1, cognome);
			System.out.println(pstatement.toString());
			resultset=pstatement.executeQuery();
			boolean element=resultset.next();

			while(element){
				Login login= new Login(resultset.getString(config_db_utenti.INDICE_USERNAME),resultset.getString(config_db_utenti.INDICE_PASSWORD));
				Utente_db_DAO utente=new Utente_db_DAO();

				utente.setLogin(login);
				utente.setNome((resultset.getString(config_db_utenti.INDICE_NOME)));
				utente.setCognome((resultset.getString(config_db_utenti.INDICE_COGNOME)));
				utente.setCitta((resultset.getString(config_db_utenti.INDICE_CITTA)));
				utente.setNascita((resultset.getString(config_db_utenti.INDICE_NASCITA)));
				utente.setSesso((resultset.getString(config_db_utenti.INDICE_SESSO)));
				utente.setMail((resultset.getString(config_db_utenti.INDICE_MAIL)));
				utente.setRuolo(resultset.getInt(config_db_utenti.INDICE_RUOLO));
				utente.setResetCode(resultset.getString(config_db_utenti.INDICE_RCODE));

				lista_filtrata_utenti.add(utente);
				element=resultset.next();
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close();
		}
		return lista_filtrata_utenti;	
	}
	public ArrayList <Utente_db_DAO> getUsersbyAgeUp(ArrayList <Utente_db_DAO> lista_completa,GregorianCalendar nascita ){
		ArrayList <Utente_db_DAO> lista_filtrata_utenti= new ArrayList<Utente_db_DAO>();

		try {
			connect();
			pstatement=conn.prepareStatement(config_db_utenti.QUERY_SELECT_AGEUP);
			pstatement.setString(1, config_db_utenti.formattoDateTime.format(nascita.getTime()));

			resultset=pstatement.executeQuery();
			boolean element=resultset.next();

			while(element){
				Login login= new Login(resultset.getString(config_db_utenti.INDICE_USERNAME),resultset.getString(config_db_utenti.INDICE_PASSWORD));
				Utente_db_DAO utente=new Utente_db_DAO();

				utente.setLogin(login);
				utente.setNome((resultset.getString(config_db_utenti.INDICE_NOME)));
				utente.setCognome((resultset.getString(config_db_utenti.INDICE_COGNOME)));
				utente.setCitta((resultset.getString(config_db_utenti.INDICE_CITTA)));
				utente.setNascita((resultset.getString(config_db_utenti.INDICE_NASCITA)));
				utente.setSesso((resultset.getString(config_db_utenti.INDICE_SESSO)));
				utente.setMail((resultset.getString(config_db_utenti.INDICE_MAIL)));
				utente.setRuolo(resultset.getInt(config_db_utenti.INDICE_RUOLO));
				utente.setResetCode(resultset.getString(config_db_utenti.INDICE_RCODE));

				lista_filtrata_utenti.add(utente);
				element=resultset.next();
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close();
		}
		return lista_filtrata_utenti;	
	}
	public ArrayList <Utente_db_DAO> getUsersbyAgeDown(ArrayList <Utente_db_DAO> lista_completa,GregorianCalendar nascita ){
		ArrayList <Utente_db_DAO> lista_filtrata_utenti= new ArrayList<Utente_db_DAO>();
		try {
			connect();
			pstatement=conn.prepareStatement(config_db_utenti.QUERY_SELECT_AGEDOWN);
			pstatement.setString(1, config_db_utenti.formattoDateTime.format(nascita.getTime()));

			resultset=pstatement.executeQuery();
			boolean element=resultset.next();

			while(element){
				Login login= new Login(resultset.getString(config_db_utenti.INDICE_USERNAME),resultset.getString(config_db_utenti.INDICE_PASSWORD));
				Utente_db_DAO utente=new Utente_db_DAO();

				utente.setLogin(login);
				utente.setNome((resultset.getString(config_db_utenti.INDICE_NOME)));
				utente.setCognome((resultset.getString(config_db_utenti.INDICE_COGNOME)));
				utente.setCitta((resultset.getString(config_db_utenti.INDICE_CITTA)));
				utente.setNascita((resultset.getString(config_db_utenti.INDICE_NASCITA)));
				utente.setSesso((resultset.getString(config_db_utenti.INDICE_SESSO)));
				utente.setMail((resultset.getString(config_db_utenti.INDICE_MAIL)));
				utente.setRuolo(resultset.getInt(config_db_utenti.INDICE_RUOLO));
				utente.setResetCode(resultset.getString(config_db_utenti.INDICE_RCODE));

				lista_filtrata_utenti.add(utente);
				element=resultset.next();
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close();
		}
		return lista_filtrata_utenti;
	}
	public Utente_db_DAO get_by_login(Login login){
		connect();
		boolean element;
		Utente_db_DAO utente =null;
		try {
			pstatement=conn.prepareStatement(config_db_utenti.QUERY_SELECT_USR_PASS);
			pstatement.setString(config_db_utenti.INDICE_USERNAME, login.getUsername());
			pstatement.setString(config_db_utenti.INDICE_PASSWORD, login.getPassword());
			System.out.println(pstatement.toString());
			resultset=pstatement.executeQuery();
			element=resultset.next();
			if(element){
				utente=new Utente_db_DAO();
				utente.setLogin(login);
				utente.setNome((resultset.getString(config_db_utenti.INDICE_NOME)));
				utente.setCognome((resultset.getString(config_db_utenti.INDICE_COGNOME)));
				utente.setCitta((resultset.getString(config_db_utenti.INDICE_CITTA)));
				utente.setNascita((resultset.getString(config_db_utenti.INDICE_NASCITA)));
				utente.setSesso((resultset.getString(config_db_utenti.INDICE_SESSO)));
				utente.setMail((resultset.getString(config_db_utenti.INDICE_MAIL)));
				utente.setRuolo(resultset.getInt(config_db_utenti.INDICE_RUOLO));
				utente.setResetCode(resultset.getString(config_db_utenti.INDICE_RCODE));

			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close();
		} 
		return utente;
	}
	public boolean isthereAnyUser(Utente_db_DAO utente_verifica){
		resultset=null;
		try {
			pstatement=conn.prepareStatement(config_db_utenti.QUERY_SELECT_USERNAME);
			pstatement.setString(1, utente_verifica.getLogin().getUsername());
			resultset=pstatement.executeQuery();
			if(resultset.next()) return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

