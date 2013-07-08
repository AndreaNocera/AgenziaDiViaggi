package ispw.entity;

import java.sql.SQLException;
import java.util.List;

import ispw.dao.DAOUtente;
import ispw.exception.DAOException;
import ispw.exception.MoreThanOneException;
import ispw.exception.UtenteException;

public class Utente {

	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String ruolo;
	
	public Utente(String username, String password, String nome, String cognome, String ruolo) {
		
		this.username = username;
		this.password = password;
		this.setNome(nome);
		this.setCognome(cognome);
		this.setRuolo(ruolo);
	}

	public Utente() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
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

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public synchronized void save() throws DAOException{
		DAOUtente daoUtente = DAOUtente.getIstance();
		daoUtente.insert(this);
	}
	public synchronized static Utente findByNameAndPassword(String username, String password) throws MoreThanOneException, DAOException{
		DAOUtente daoUtente = DAOUtente.getIstance();
		return daoUtente.findByNameAndPassword(username, password);
	}
	public String getString(){
		return this.getUsername() + " " + this.getPassword() + " " + this.getNome() + " " + this.getCognome() + " " + this.getRuolo();
	}
	
	public void print(){
		System.out.println(this.getUsername() + " " + this.getPassword() + " " + this.getNome() + " " + this.getCognome() + " " + this.getRuolo());
	}

	public static List<Utente> getListaUtenti() throws DAOException {
		// TODO Auto-generated method stub
		DAOUtente daoUtente = DAOUtente.getIstance();
		return daoUtente.getListaUtenti();
	}

	public static synchronized Utente getUtenteByUsername(String username) throws UtenteException, DAOException {
		// TODO Auto-generated method stub
		DAOUtente daoUtente = DAOUtente.getIstance();
		Utente utente = daoUtente.readUtenteByUsername(username);
		if(utente == null)
			throw new UtenteException("Utente non esistente.");
		return utente;
}

	public synchronized void delete() throws DAOException, SQLException {
		// TODO Auto-generated method stub
		DAOUtente daoUtente = DAOUtente.getIstance();
		daoUtente.delete(this);
	}

}
