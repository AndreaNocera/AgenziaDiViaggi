/**
 * 
 */
package ordinaViaggi.entity;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella
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
}