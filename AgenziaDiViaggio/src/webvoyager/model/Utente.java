/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model
 * 
 * @name Utente.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.model;

import java.io.Serializable;
import webvoyager.exception.BeanInconsistenteException;
import webvoyager.exception.DatiUtenteInconsistentiException;
import webvoyager.exception.LoginInconsistenteException;
import webvoyager.model.bean.UtenteBean;
import webvoyager.model.ruoli.Ruolo;

public class Utente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DatiUtente dati;
	private Ruolo ruolo;
	private Login login;

	public Utente(DatiUtente dati, Ruolo ruolo, Login login) {
		this.setDatiUtente(dati);
		this.setRuolo(ruolo);
		this.setLogin(login);
	}
	
	public Utente() {
		
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
	
	public Utente fromBean(UtenteBean bean) throws BeanInconsistenteException {
		try {
			DatiUtente bDati = new DatiUtente(bean.getNome(), bean.getCognome(), bean.getMail(), bean.getCitta(), bean.getNascita(), bean.getSesso());
			Ruolo bRuolo = bean.getRuolo();
			Login bLogin = new Login(bean.getUsername(), bean.getPassword());
			return new Utente(bDati, bRuolo, bLogin);
		} catch (DatiUtenteInconsistentiException | LoginInconsistenteException exc) {
			throw new BeanInconsistenteException();
		}			
	}

}
