package gestioneutenti.model;

import java.util.GregorianCalendar;

public class DatiUtente {
	
	private String nome;
	private String cognome;
	private String città;
	private GregorianCalendar nascita;
	private String sesso;	

	public DatiUtente(String nome, String cognome, String città, GregorianCalendar nascita, String sesso) {
		this.setNome(nome);
		this.setCognome(cognome);
		this.setCittà(città);
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

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
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
