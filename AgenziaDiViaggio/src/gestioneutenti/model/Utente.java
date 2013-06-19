package gestioneutenti.model;

public class Utente {
	
	private String nome;
	private String cognome;
	private String citt�;
	private String nascita;
	private String sesso;
	private String mail;
	private String ruolo;
	private Login login;
	
	public Utente() {
		
	}
	
	public Utente(String nome, String cognome, String citt�, String nascita, String sesso, String mail, String ruolo, Login login) {
		this.nome = nome;
		this.cognome = cognome;
		this.citt� = citt�;
		this.nascita = nascita;
		this.sesso = sesso;
		this.mail = mail;
		this.ruolo = ruolo;
		this.login = login;
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

	public String getNascita() {
		return nascita;
	}

	public void setNascita(String nascita) {
		this.nascita = nascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
