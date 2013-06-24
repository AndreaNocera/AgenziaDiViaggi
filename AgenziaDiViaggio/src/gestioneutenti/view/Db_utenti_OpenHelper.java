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




/**Db_utenti_OpenHelper
 * @author Jesus Cevallos
 * Crea e assegna connessioni verso il db di utenti. 
 */
public class Db_utenti_OpenHelper {

	public Connection conn;
	public PreparedStatement pstatement;
	public Statement statement;
	ResultSet resultset;


	private static class SingletonHolder { 
		public static final Db_utenti_OpenHelper INSTANCE = new Db_utenti_OpenHelper();
	}
	public static Db_utenti_OpenHelper getinstance(){
		return SingletonHolder.INSTANCE;
	};


	private Db_utenti_OpenHelper() {
		try{
			Class.forName(config_db_utenti.NOME_DRIVER);
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		} 
		createDb();
	}


	public void createDb() {
		String query="";
		connect();

		try {
			query="CREATE TABLE IF NOT EXISTS "
					+config_db_utenti.NOME_TABELLA_UTENTI+"( "+
					config_db_utenti.COLONNA_USERNAME+" VARCHAR(20) PRIMARY KEY, "+
					config_db_utenti.COLONNA_PASSWORD+" VARCHAR(20), "+
					config_db_utenti.COLONNA_NOME+" VARCHAR(20), "+
					config_db_utenti.COLONNA_COGNOME+" VARCHAR(20), "+
					config_db_utenti.COLONNA_CITTA+" VARCHAR(20), "+
					config_db_utenti.COLONNA_NASCITA+" DATETIME, "+
					config_db_utenti.COLONNA_SESSO+" VARCHAR(20), "+
					config_db_utenti.COLONNA_MAIL+" VARCHAR(50), "+
					config_db_utenti.COLONNA_RUOLO+" INT(2))";


			pstatement=conn.prepareStatement(query);
			pstatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}	

	}



	public void connect() {
		try {
			conn = DriverManager.getConnection(config_db_utenti.PERCORSO_COMPLETO_DB, config_db_utenti.USER_CONNESSIONE, config_db_utenti.PASSWORD_CONNESSIONE);
			statement=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			pstatement=conn.prepareStatement("SELECT * FROM "+config_db_utenti.NOME_TABELLA_UTENTI+
					" WHERE "+config_db_utenti.COLONNA_USERNAME+"= '"+utente_verifica.login.getUsername()+"' ");
			
			resultset=pstatement.executeQuery();
			if(resultset.next()) return true;
			else return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList <Utente_db_DAO> get_all() throws SQLException{
		ArrayList <Utente_db_DAO> lista_completa_utenti= new ArrayList<Utente_db_DAO>();

		
		try {
			connect();
			statement=conn.createStatement();
			resultset=statement.executeQuery("SELECT * FROM "+config_db_utenti.NOME_TABELLA_UTENTI+"");
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
