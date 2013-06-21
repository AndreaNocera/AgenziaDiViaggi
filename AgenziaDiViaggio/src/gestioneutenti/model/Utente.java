package gestioneutenti.model;

import gestioneutenti.model.ruoli.Ruolo;

public class Utente implements Comparable<Utente> {
	
	private DatiUtente dati;
	private String mail;
	private Ruolo ruolo;
	private Login login;

	public Utente(DatiUtente dati, String mail, Ruolo ruolo, Login login) {
		this.setDatiUtente(dati);
		this.setMail(mail);
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
	
	public String getMail() {
		return this.mail;
	}
	
	private void setMail(String mail) {
		this.mail = mail;
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

}
