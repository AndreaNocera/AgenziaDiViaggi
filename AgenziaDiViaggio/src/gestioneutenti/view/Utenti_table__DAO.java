package gestioneutenti.view;



import gestioneutenti.model.Login;
import gestioneutenti.model.Utente_db_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**Utenti_table__DAO
 * @author <GRUPPO 9>
 * Crea e assegna connessioni verso il db di utenti. 
 */
public class Utenti_table__DAO {

	public Connection conn;
	public PreparedStatement pstatement;
	public Statement statement;
	ResultSet resultset;


	private static class SingletonHolder { 
		public static final Utenti_table__DAO INSTANCE = new Utenti_table__DAO();
	}
	public static Utenti_table__DAO getinstance(){
		return SingletonHolder.INSTANCE;
	};


	private Utenti_table__DAO() {
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
	
	public boolean isthereAnyUser(Utente_db_DAO utente_verifica) {
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
}
