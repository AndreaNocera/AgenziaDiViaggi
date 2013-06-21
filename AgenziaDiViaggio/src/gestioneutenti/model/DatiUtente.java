package gestioneutenti.model;

import java.util.GregorianCalendar;

public class DatiUtente {
	
	private String nome;
	private String cognome;
	private String citt�;
	private GregorianCalendar nascita;
	private String sesso;	

	public DatiUtente(String nome, String cognome, String citt�, GregorianCalendar nascita, String sesso) {
		this.setNome(nome);
		this.setCognome(cognome);
		this.setCitt�(citt�);
		this.setNascita(nascita);
		this.setSesso(sesso);
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

	public String getCitt�() {
		return citt�;
	}

	public void setCitt�(String citt�) {
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

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

}
