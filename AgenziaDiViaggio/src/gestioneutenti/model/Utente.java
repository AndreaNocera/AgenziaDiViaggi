package gestioneutenti.model;

/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model
 * 
 * @name Utente.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

import gestioneutenti.dao.Utente_db_DAO;
import gestioneutenti.exception.UtenteEsistenteException;
import gestioneutenti.exception.UtenteNonEsistenteException;
import gestioneutenti.model.ruoli.Ruolo;

public class Utente implements Comparable<Utente> {
	
	private DatiUtente dati;
	private Ruolo ruolo;
	private Login login;

	public Utente(DatiUtente dati, Ruolo ruolo, Login login) {
		this.setDatiUtente(dati);
		this.setRuolo(ruolo);
		this.setLogin(login);
	}

	@Override
	public int compareTo(Utente other) {
		return getLogin().getUsername().compareTo(other.getLogin().getUsername());
	}

	public DatiUtente getDatiUtente() {
		return this.dati;
	}
	
	private void setDatiUtente(DatiUtente dati) {
		this.dati = dati;
	}
	
	private void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Ruolo getRuolo() {
		return this.ruolo;
	}
	
	private void setLogin(Login login) {
		this.login = login;
	}

	public Login getLogin() {
		return this.login;
	}	
	
	public void save() throws UtenteEsistenteException{
		if(true){ //dipende dal tipo di persistenza
			Utente_db_DAO utentedbDao= new Utente_db_DAO(this);
			utentedbDao.save();
		}else {
			//Serializzazione
		}
	}	


	public void delete() throws UtenteNonEsistenteException {
		if(true){ //dipende dal tipo di persistenza
			Utente_db_DAO utentedbDao= new Utente_db_DAO(this);
			utentedbDao.delete();
		}else{
			//Serializzazione
		}			
	}

	public void update() throws UtenteNonEsistenteException, UtenteEsistenteException {
		if(true){ //dipende dal tipo di persistenza
			Utente_db_DAO utentedbDao= new Utente_db_DAO(this);
			utentedbDao.update();
		}else{
			//Serializzazione
		}			
	}

}
