package gestioneutenti.model.bean;

/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.bean
 * 
 * @name UtenteBean.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

import gestioneutenti.model.ruoli.Ruolo;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class UtenteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private String citta;
	private GregorianCalendar nascita;
	private String sesso;
	private String mail;
	private Ruolo ruolo;
	private String username;
	private String password;

	public UtenteBean(){}

	public String getNome() {
		return nome;
	}

	public UtenteBean setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public String getCognome() {
		return cognome;
	}

	public UtenteBean setCognome(String cognome) {
		this.cognome = cognome;
		return this;
	}

	public String getCitta() {
		return citta;
	}

	public UtenteBean setCitta(String citta) {
		this.citta = citta;
		return this;
	}

	public GregorianCalendar getNascita() {
		return nascita;
	}

	public UtenteBean setNascita(GregorianCalendar nascita) {
		this.nascita = nascita;
		return this;
	}

	public String getSesso() {
		return sesso;
	}

	public UtenteBean setSesso(String sesso) {
		this.sesso = sesso;
		return this;
	}

	public String getMail() {
		return mail;
	}

	public UtenteBean setMail(String mail) {
		this.mail = mail;
		return this;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public UtenteBean setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UtenteBean setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UtenteBean setPassword(String password) {
		this.password = password;
		return this;
	}

}
