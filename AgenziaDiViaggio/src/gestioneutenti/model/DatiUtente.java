package gestioneutenti.model;

import gestioneutenti.exception.UtenteException;

import java.util.GregorianCalendar;

public class DatiUtente {
	
	private String nome;
	private String cognome;
	private String citt�;
	private GregorianCalendar nascita;
	private String sesso;	

	public DatiUtente(String nome, String cognome, String citt�, GregorianCalendar nascita, String sesso) throws UtenteException {
		this.setNome(nome);
		this.setCognome(cognome);
		this.setCitt�(citt�);
		this.setNascita(nascita);
		this.setSesso(sesso);		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws UtenteException {
		if (nome.isEmpty()) {
			throw new UtenteException();
		}
		
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) throws UtenteException {
		if (cognome.isEmpty()) {
			throw new UtenteException();
		}
		
		this.cognome = cognome;
	}

	public String getCitt�() {
		return citt�;
	}

	public void setCitt�(String citt�) throws UtenteException {
		if (citt�.isEmpty()) {
			throw new UtenteException();
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

	public void setSesso(String sesso) throws UtenteException {
		if (sesso.isEmpty() || (!sesso.equals("Uomo") && !sesso.equals("Donna"))) {
			throw new UtenteException();
		}
		
		this.sesso = sesso;
	}

}
