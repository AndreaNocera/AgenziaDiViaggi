/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.bean
 * 
 * @name UtenteBean.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.model.bean;

import java.io.Serializable;
import java.util.GregorianCalendar;


import webvoyager.marciani.model.ruoli.Ruolo;
import webvoyager.marciani.utils.DateUtils;

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
		return this.cognome;
	}

	public UtenteBean setCognome(String cognome) {
		this.cognome = cognome;
		return this;
	}

	public String getCitta() {
		return this.citta;
	}

	public UtenteBean setCitta(String citta) {
		this.citta = citta;
		return this;
	}

	public GregorianCalendar getNascita() {
		return this.nascita;
	}

	public UtenteBean setNascita(GregorianCalendar nascita) {
		this.nascita = nascita;
		return this;
	}

	public String getSesso() {
		return this.sesso;
	}

	public UtenteBean setSesso(String sesso) {
		this.sesso = sesso;
		return this;
	}

	public String getMail() {
		return this.mail;
	}

	public UtenteBean setMail(String mail) {
		this.mail = mail;
		return this;
	}

	public Ruolo getRuolo() {
		return this.ruolo;
	}

	public UtenteBean setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
		return this;
	}

	public String getUsername() {
		return this.username;
	}

	public UtenteBean setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public UtenteBean setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public String asJson() {
		String str = new String();
		
		str += this.getNome() + ":" + this.getCognome() + ":" + this.getCitta() + ":" + DateUtils.getStringFromGregorianCalendar(this.getNascita()) + ":" + this.getSesso() + ":" + this.getMail() + ":" + this.getUsername() + ":" + this.getRuolo().asString() + ":" + this.getPassword();
		
		return str;
	}

}
