/**
 * 
 */
package ispw.entity;

import ispw.dao.DAOTraveler;
import ispw.exception.DAOException;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella Riccardo
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Traveler {
	private Integer idTraveler;
	private String nome;
	private String cognome;
	private String email;
	public Traveler(){
		idTraveler = 0;
		nome = "";
		cognome = "";
		email = "";
	}
	public Traveler(Integer idTraveler, String nome, String cognome, String email){
		this.idTraveler = idTraveler;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}
	public Integer getIdTraveler() {
		return idTraveler;
	}
	public void setIdTraveler(Integer idTraveler) {
		this.idTraveler = idTraveler;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getString(){
		return (idTraveler + " " + nome + " " + cognome + " " + email);
	}
	
	public void save() throws DAOException {
		DAOTraveler daoTraveler = DAOTraveler.getInstance();
		daoTraveler.insert(this);
	}

	public void delete() throws DAOException {
		// TODO Auto-generated method stub
		DAOTraveler daoTraveler = DAOTraveler.getInstance();
		daoTraveler.delete(this);
	}
	
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(idTraveler + " " + nome + " " + cognome + " " + email);
	}
	public static Traveler getObjectByValue(String nome, String cognome,
			String email) throws DAOException {
		DAOTraveler daoTraveler = DAOTraveler.getInstance();
		return daoTraveler.getObjectByValue(nome, cognome, email);
	}
}