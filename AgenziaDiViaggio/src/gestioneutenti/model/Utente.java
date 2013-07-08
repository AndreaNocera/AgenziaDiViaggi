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

package gestioneutenti.model;

import gestioneutenti.exception.DatiUtenteInconsistentiException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.model.bean.UtenteBean;
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
	
	public Utente() {
		
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
	
	public Utente fromBean(UtenteBean bean) throws DatiUtenteInconsistentiException, LoginInconsistenteException {
		DatiUtente bDati = new DatiUtente(bean.getNome(), bean.getCognome(), bean.getMail(), bean.getCitta(), bean.getNascita(), bean.getSesso());
		Ruolo bRuolo = bean.getRuolo();
		Login bLogin = new Login(bean.getUsername(), bean.getPassword());
		return new Utente(bDati, bRuolo, bLogin);	
	}

}
