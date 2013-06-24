package gestioneutenti.model;


import gestioneutenti.view.Utenti_table__DAO;
import gestioneutenti.view.config_db_utenti;

import java.sql.ResultSet;
import java.sql.SQLException;
import gestioneutenti.exception.*;



/**
 * @author <GRUPPO 9>
 *Utente_db_DAO interagisce col db di utenti per salvataggi e query. si appoggia al Utenti_table__DAO per collegarsi al database.
 */
public class Utente_db_DAO implements utente_DAO {


	ResultSet rs_db_utenti;
	Utenti_table__DAO openhelper;


	String nome=null;
	String cognome=null;
	String citta=null;
	String nascita=null;
	String sesso=null;
	String mail=null;
	int ruolo;
	public Login login=null;



	public Utente_db_DAO(){
		openhelper=Utenti_table__DAO.getinstance();
	}

	public Utente_db_DAO(String nome, String cognome, String citta, String nascita, String sesso, String mail, int ruolo, Login login){

		openhelper=Utenti_table__DAO.getinstance();

		this.nome = nome;
		this.cognome = cognome;
		this.citta = citta;
		this.nascita = nascita;
		this.sesso = sesso;
		this.mail = mail;
		this.ruolo = ruolo;
		this.login=login;

	}

	public Utente_db_DAO(Utente nuovo_utente) {
		openhelper=Utenti_table__DAO.getinstance();
		this.nome=nuovo_utente.getDatiUtente().getNome();
		this.cognome=nuovo_utente.getDatiUtente().getCognome();
		this.citta= nuovo_utente.getDatiUtente().getCitta();
		this.sesso=nuovo_utente.getDatiUtente().getSesso();
		this.nascita= nuovo_utente.getDatiUtente().getNascita().toString();
		Login this_login= new Login(nuovo_utente.getLogin().getUsername(), nuovo_utente.getLogin().getPassword());
		this.login=this_login;
		this.ruolo=nuovo_utente.getRuolo().getId();
		this.mail=nuovo_utente.getMail();
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

	/*Esegue la query sul db di utenti con il criterio di ricerca username, che coincide con la chiave della tabella,
	 * dunque il result set e' costituito di un solo risultato oppure e'  null nel caso non ci sia una coincidenza.
	 * Di seguito mappa i risultati sulla istanza corrente di Utente_db_DAO.
	 * */
	@Override
	public void get(Login login) {

		openhelper.connect();

		try {
			rs_db_utenti=null;
			rs_db_utenti=openhelper.statement.executeQuery("SELECT * FROM "+ config_db_utenti.NOME_TABELLA_UTENTI+
					"WHERE "+config_db_utenti.COLONNA_USERNAME+"= "+login.getUsername());

			this.nome = rs_db_utenti.getString(config_db_utenti.INDICE_NOME);
			this.cognome = rs_db_utenti.getString(config_db_utenti.INDICE_COGNOME);
			this.citta = rs_db_utenti.getString(config_db_utenti.INDICE_CITTA);
			this.nascita = rs_db_utenti.getString(config_db_utenti.INDICE_NASCITA);
			this.sesso = rs_db_utenti.getString(config_db_utenti.INDICE_SESSO);
			this.mail = rs_db_utenti.getString(config_db_utenti.INDICE_MAIL);
			this.ruolo=rs_db_utenti.getInt(config_db_utenti.INDICE_RUOLO);
			this.login.setPassword(rs_db_utenti.getString(config_db_utenti.INDICE_PASSWORD));
			this.login.setUsername(rs_db_utenti.getString(config_db_utenti.INDICE_USERNAME));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		openhelper.close();
	}

	@Override
	public void save() throws UtenteEsistenteException{
		openhelper.connect();
	if(!openhelper.isthereAnyUser(this)){
			try {
				openhelper.pstatement=openhelper.conn.prepareStatement(config_db_utenti.STATEMENT_SALVA);
				openhelper.pstatement.setString(1, this.login.getUsername());
				openhelper.pstatement.setString(2, this.login.getPassword());
				openhelper.pstatement.setString(3, this.nome);
				openhelper.pstatement.setString(4, this.cognome);
				openhelper.pstatement.setString(5, this.citta);
				openhelper.pstatement.setString(6, this.nascita);
				openhelper.pstatement.setString(7, this.sesso);
				openhelper.pstatement.setString(8, this.mail);
				openhelper.pstatement.setInt(9, this.ruolo);

				openhelper.pstatement.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				openhelper.close();
			}
		}
	else throw new UtenteEsistenteException("Usename gia esistente per questo utente");
	}

	public void update(){ 
		openhelper.connect();
		try {
			openhelper.statement.executeUpdate("UPDATE "+config_db_utenti.NOME_TABELLA_UTENTI+
					" SET "+config_db_utenti.COLONNA_PASSWORD+"="+this.login.getPassword()+", "
					+config_db_utenti.COLONNA_NOME+"="+this.login.getPassword()+", "
					+config_db_utenti.COLONNA_COGNOME+"="+this.login.getPassword()+", "
					+config_db_utenti.COLONNA_CITTA+"="+this.login.getPassword()+", "
					+config_db_utenti.COLONNA_NASCITA+"="+this.login.getPassword()+", "
					+config_db_utenti.COLONNA_SESSO+"="+this.login.getPassword()+", "
					+config_db_utenti.COLONNA_MAIL+"="+this.login.getPassword()+", "
					+config_db_utenti.COLONNA_RUOLO+"="+this.login.getPassword()+
					" WHERE "+config_db_utenti.COLONNA_USERNAME+" = '"+this.login.getUsername()+"'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		openhelper.close();

	}

}
