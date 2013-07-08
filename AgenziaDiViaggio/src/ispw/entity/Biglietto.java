/**
 * 
 */
package ispw.entity;

import ispw.dao.DAOBiglietto;
import ispw.exception.DAOException;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella Riccardo
 * @generated 
 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Biglietto {
	
	private Integer idBiglietto;
	public Integer idPrenotazione;
	private Traveler traveler;
	
	public Biglietto(){
		idBiglietto = new Integer(0);
		idPrenotazione = new Integer(0);
		traveler = new Traveler();
	}
	public Biglietto(Integer idBiglietto, Integer idPrenotazione){
		this.setIdBiglietto(idBiglietto);
		this.setIdPrenotazione(idPrenotazione);
		this.setTraveler(new Traveler());
	}
	public Biglietto(Integer idBiglietto, Integer idPrenotazione, Traveler traveler){
		this.setIdBiglietto(idBiglietto);
		this.setIdPrenotazione(idPrenotazione);
		this.setTraveler(traveler);
	}
	public Integer getIdBiglietto() {
		return idBiglietto;
	}
	public void setIdBiglietto(Integer idBiglietto) {
		this.idBiglietto = idBiglietto;
	}
	public Integer getIdPrenotazione() {
		return idPrenotazione;
	}
	public void setIdPrenotazione(Integer idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}
	public Traveler getTraveler() {
		return traveler;
	}
	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}
	public synchronized void save() throws DAOException {
		DAOBiglietto daoBiglietto = DAOBiglietto.getInstance();
		daoBiglietto.insert(this);
	}

	public synchronized void delete() throws DAOException {
		// TODO Auto-generated method stub
		DAOBiglietto daoBiglietto = DAOBiglietto.getInstance();
		daoBiglietto.delete(this);
	}
	
	public String getString(){
		return idBiglietto + " " + idPrenotazione + " " + traveler.getString();
	}
	public void print(){
		System.out.println(idBiglietto + " " + idPrenotazione + " " + traveler.getString());
	}
	public synchronized static Integer getNextId() throws DAOException {
		DAOBiglietto daoBiglietto = DAOBiglietto.getInstance();
		return daoBiglietto.getNextId();
	}
	public synchronized static Biglietto getObjectById(Integer idBiglietto) throws DAOException {
		// TODO Auto-generated method stub
		DAOBiglietto daoBiglietto = DAOBiglietto.getInstance();
		return daoBiglietto.read(idBiglietto);
	}
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Biglietto))
			return false;
		Biglietto biglietto = (Biglietto)obj;
		if(this.idBiglietto.equals(biglietto.getIdBiglietto()))
			return true;
		return false;
	}
}