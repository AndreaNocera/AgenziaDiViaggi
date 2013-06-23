package gestioneutenti.model;

import gestioneutenti.exception.DatiUtenteInconsistentiException;

import java.util.GregorianCalendar;

public class DatiUtente {
	
	private String nome;
	private String cognome;
	private String citt�;
	private GregorianCalendar nascita;
	private String sesso;	
	private String mail;

	public DatiUtente(String nome, String cognome, String mail, String citt�, GregorianCalendar nascita, String sesso) throws DatiUtenteInconsistentiException {
		this.setNome(nome);
		this.setCognome(cognome);
		this.setMail(mail);
		this.setCitt�(citt�);
		this.setNascita(nascita);
		this.setSesso(sesso);			
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws DatiUtenteInconsistentiException {
		if (nome.isEmpty()) {
			throw new DatiUtenteInconsistentiException();
		}
		
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) throws DatiUtenteInconsistentiException {
		if (cognome.isEmpty()) {
			throw new DatiUtenteInconsistentiException();
		}
		
		this.cognome = cognome;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) throws DatiUtenteInconsistentiException {
		if (mail.isEmpty()) {
			throw new DatiUtenteInconsistentiException();
		}
		
		this.mail = mail;
	}

	public String getCitt�() {
		return citt�;
	}

	public void setCitt�(String citt�) throws DatiUtenteInconsistentiException {
		if (citt�.isEmpty()) {
			throw new DatiUtenteInconsistentiException();
		}
		
		this.citt� = citt�;
	}

	public GregorianCalendar getNascita() {
		return nascita;
	}

	public void setNascita(GregorianCalendar nascita) {
		this.nascita = nascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) throws DatiUtenteInconsistentiException {
		if (sesso.isEmpty() || (!sesso.equals("Uomo") && !sesso.equals("Donna"))) {
			throw new DatiUtenteInconsistentiException();
		}
		
		this.sesso = sesso;
	}



}
