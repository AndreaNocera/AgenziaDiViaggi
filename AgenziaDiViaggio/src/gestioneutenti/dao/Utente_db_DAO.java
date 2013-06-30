package gestioneutenti.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import gestioneutenti.exception.*;
import gestioneutenti.model.Utente;
import gestioneutenti.model.Login;

/**
 *Utente_db_DAO interagisce col db di utenti per salvataggi e query. si appoggia al Persistence_Db per collegarsi al database.
 */
public class Utente_db_DAO{


	ResultSet rs_db_utenti;
	Persistence_Db openhelper;


	private String nome=null;
	private String cognome=null;
	private String citta=null;
	private String nascita=null;
	private String sesso=null;
	private String mail=null;
	private int ruolo;
	private Login login=null;
	private String resetCode;



	public Utente_db_DAO(){
		openhelper=Persistence_Db.getinstance();
	}

	public Utente_db_DAO(String nome, String cognome, String citta, String nascita, String sesso, String mail, int ruolo, Login login, String rcode){

		openhelper=Persistence_Db.getinstance();

		this.nome = nome;
		this.cognome = cognome;
		this.citta = citta;
		this.nascita = nascita;
		this.sesso = sesso;
		this.mail = mail;
		this.ruolo = ruolo;
		this.login=login;
		this.resetCode=rcode;

	}

	public Utente_db_DAO(Utente nuovo_utente)  {
		try {
			openhelper=Persistence_Db.getinstance();

			this.nome=nuovo_utente.getDatiUtente().getNome();
			this.cognome=nuovo_utente.getDatiUtente().getCognome();
			this.citta= nuovo_utente.getDatiUtente().getcitta();
			this.sesso=nuovo_utente.getDatiUtente().getSesso();
			this.nascita=config_db_utenti.formattoDateTime.format(nuovo_utente.getDatiUtente().getNascita().getTime());
			this.login= new Login(nuovo_utente.getLogin().getUsername(), nuovo_utente.getLogin().getPassword());
			this.ruolo=nuovo_utente.getRuolo().getId();
			this.mail=nuovo_utente.getDatiUtente().getMail();

		} catch (LoginInconsistenteException e) {
			e.printStackTrace();
		}
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getNascita() {
		return nascita;
	}

	public void setNascita(String nascita) {
		this.nascita = nascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getRuolo() {
		return ruolo;
	}

	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	public void setResetCode(String rc) {
		this.resetCode= rc;
	}
	public String getResetCode(String rc) {
		return this.resetCode;
	}


	public void save() throws UtenteEsistenteException{
		openhelper.connect();
		if(openhelper.isthereAnyUser(this)){
			try {
				openhelper.pstatement=openhelper.conn.prepareStatement(config_db_utenti.STATEMENT_SALVA);
				openhelper.pstatement.setString(config_db_utenti.INDICE_USERNAME, this.login.getUsername());
				openhelper.pstatement.setString(config_db_utenti.INDICE_PASSWORD, this.login.getPassword());
				openhelper.pstatement.setString(config_db_utenti.INDICE_NOME, this.nome);
				openhelper.pstatement.setString(config_db_utenti.INDICE_COGNOME, this.cognome);
				openhelper.pstatement.setString(config_db_utenti.INDICE_CITTA, this.citta);
				openhelper.pstatement.setString(config_db_utenti.INDICE_NASCITA, this.nascita);
				openhelper.pstatement.setString(config_db_utenti.INDICE_SESSO, this.sesso);
				openhelper.pstatement.setString(config_db_utenti.INDICE_MAIL, this.mail);
				openhelper.pstatement.setInt(config_db_utenti.INDICE_RUOLO, this.ruolo);
				openhelper.pstatement.setString(config_db_utenti.INDICE_RCODE, this.resetCode);
				openhelper.pstatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				openhelper.close();
			}
		}else{
			throw new UtenteEsistenteException("L'username '"+this.getLogin().getUsername()+"' è gia stato usato");
		}
	}

	public void update() throws UtenteEsistenteException{ 
		openhelper.connect();
		try {
			openhelper.pstatement=openhelper.conn.prepareStatement(config_db_utenti.STATEMENT_UPDATE);
			openhelper.pstatement.setString(config_db_utenti.INDICE_PASSWORD-1, this.login.getPassword());
			openhelper.pstatement.setString(config_db_utenti.INDICE_NOME-1, this.nome);
			openhelper.pstatement.setString(config_db_utenti.INDICE_COGNOME-1, this.cognome);
			openhelper.pstatement.setString(config_db_utenti.INDICE_CITTA-1, this.citta);
			openhelper.pstatement.setString(config_db_utenti.INDICE_NASCITA-1, this.nascita);
			openhelper.pstatement.setString(config_db_utenti.INDICE_SESSO-1, this.sesso);
			openhelper.pstatement.setString(config_db_utenti.INDICE_MAIL-1, this.mail);
			openhelper.pstatement.setInt(config_db_utenti.INDICE_RUOLO-1, this.ruolo);
			openhelper.pstatement.setString(config_db_utenti.INDICE_RCODE-1,this.resetCode);
			openhelper.pstatement.setString(10, this.login.getUsername());
			openhelper.pstatement.executeUpdate();

		}   catch (SQLException e) {
			e.printStackTrace();
		}finally{
			openhelper.close();
		}
	}

	public void delete(){
		openhelper.connect();
		try {
			openhelper.pstatement=openhelper.conn.prepareStatement(config_db_utenti.STATEMENT_DELETE);
			openhelper.pstatement.setString(config_db_utenti.INDICE_USERNAME, this.login.getUsername());
			openhelper.pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			openhelper.close();
		}
	}

}
