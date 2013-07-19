/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model
 * 
 * @name DatiUtente.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import webvoyager.marciani.exception.DatiUtenteInconsistentiException;

public class DatiUtente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private String citta;
	private GregorianCalendar nascita;
	private String sesso;	
	private String mail;

	public DatiUtente(String nome, String cognome, String mail, String citta, GregorianCalendar nascita, String sesso) throws DatiUtenteInconsistentiException {
		this.setNome(nome);
		this.setCognome(cognome);
		this.setMail(mail);
		this.setCitta(citta);
		this.setNascita(nascita);
		this.setSesso(sesso);			
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws DatiUtenteInconsistentiException {
		if (nome.isEmpty() || nome == null) {
			throw new DatiUtenteInconsistentiException();
		}
		
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) throws DatiUtenteInconsistentiException {
		if (cognome.isEmpty() || cognome == null) {
			throw new DatiUtenteInconsistentiException();
		}
		
		this.cognome = cognome;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) throws DatiUtenteInconsistentiException {
		if (mail.isEmpty() || mail == null) {
			throw new DatiUtenteInconsistentiException();
		}
		
		this.mail = mail;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) throws DatiUtenteInconsistentiException {
		if (citta.isEmpty() || citta == null) {
			throw new DatiUtenteInconsistentiException();
		}
		
		this.citta = citta;
	}

	public GregorianCalendar getNascita() {
		return nascita;
	}

	public void setNascita(GregorianCalendar nascita) throws DatiUtenteInconsistentiException {
		if (nascita == null) {
			throw new DatiUtenteInconsistentiException();
		}
		this.nascita = nascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) throws DatiUtenteInconsistentiException {
		if (!sesso.equals("Uomo") && !sesso.equals("Donna")) {
			throw new DatiUtenteInconsistentiException();
		}
		
		this.sesso = sesso;
	}

}
