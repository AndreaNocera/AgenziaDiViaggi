package ispw.entity;

import ispw.dao.DAOBiglietto;
import ispw.dao.DAOPrenotazione;
import ispw.exception.DAOException;
import ispw.exception.PrenotazioneException;

import java.util.ArrayList;
import java.util.List;

public class Prenotazione {
	/**
	 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
	 * @author Gambella Riccardo
	 * @generated 
	 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Integer idPrenotazione;
	private Integer idOfferta;
	private String usernameAcquirente;
	private List<Biglietto> listaBiglietti;
	
	
	public Prenotazione(){
		this.idPrenotazione = new Integer(0);
		this.idOfferta = new Integer(0);
		setListaBiglietti(new ArrayList<Biglietto>());
	}
	public Prenotazione(Integer idPrenotazione, Integer idOfferta) {
		this.setIdPrenotazione(idPrenotazione);
		this.setIdOfferta(idOfferta);
		setListaBiglietti(new ArrayList<Biglietto>());
	}

	public Prenotazione(Integer idPrenotazione, Integer idOfferta,
			String usernameAcquirente) {
		this.setIdPrenotazione(idPrenotazione);
		this.setIdOfferta(idOfferta);
		this.setUsernameAcquirente(usernameAcquirente);
		setListaBiglietti(new ArrayList<Biglietto>());
	}

	public Prenotazione(Integer idPrenotazione, Integer idOfferta,
			String usernameAcquirente, List<Biglietto> listaBiglietti) {
		this.setIdPrenotazione(idPrenotazione);
		this.setIdOfferta(idOfferta);
		this.setUsernameAcquirente(usernameAcquirente);
		this.setListaBiglietti(listaBiglietti);
	}

	public Integer getIdPrenotazione() {
		return idPrenotazione;
	}

	public void setIdPrenotazione(Integer idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}

	public Integer getIdOfferta() {
		return idOfferta;
	}

	public void setIdOfferta(Integer idOfferta) {
		this.idOfferta = idOfferta;
	}

	public String getUsernameAcquirente() {
		return usernameAcquirente;
	}

	public void setUsernameAcquirente(String usernameAcquirente) {
		this.usernameAcquirente = usernameAcquirente;
	}

	public List<Biglietto> getListaBiglietti() {
		return listaBiglietti;
	}

	public void setListaBiglietti(List<Biglietto> listaBiglietti) {
		this.listaBiglietti = listaBiglietti;
	}

	public void addBiglietto(Biglietto biglietto) {
		this.listaBiglietti.add(biglietto);
	}
	public void removeBiglietto(Biglietto biglietto) {
		this.listaBiglietti.remove(biglietto);
	}

	public Biglietto getBigliettoById(Integer idBiglietto)
			throws PrenotazioneException {
		for (Biglietto biglietto : listaBiglietti) {
			if (biglietto.getIdBiglietto().equals(idBiglietto))
				return biglietto;
		}
		throw new PrenotazioneException("Errore in getBigliettoById");
	}

	public Biglietto getBigliettoByValue(Traveler traveler)
			throws PrenotazioneException {
		for (Biglietto biglietto : listaBiglietti) {
			if (biglietto.getTraveler().equals(traveler))
				return biglietto;
		}
		throw new PrenotazioneException("Errore in getBigliettoById");
	}
	
	public void save() throws DAOException {
		DAOPrenotazione daoPrenotazione = DAOPrenotazione.getInstance();
		daoPrenotazione.insert(this);
	}

	public void delete() throws DAOException {
		// TODO Auto-generated method stub
		//Cancellazione dei biglietti associati alla prenotazione
		DAOBiglietto daoBiglietto = DAOBiglietto.getInstance();
		List<Biglietto> listaBiglietti = daoBiglietto.getListaBigliettiByIdPrenotazione(this.idPrenotazione);
		for(Biglietto biglietto : listaBiglietti){
			biglietto.delete();
		}
		DAOPrenotazione daoPrenotazione = DAOPrenotazione.getInstance();
		daoPrenotazione.delete(this);
	}
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(idPrenotazione + " " + idOfferta + " " + usernameAcquirente + ".");
		for(Biglietto biglietto : listaBiglietti)
			System.out.println("  " + biglietto.getString());
	}
	public String getString(){
		String stringa = "";
		for(Biglietto biglietto : listaBiglietti){
			stringa += idPrenotazione + " ";
			stringa += usernameAcquirente + " ";
			stringa += biglietto.getTraveler().getNome() + " ";
			stringa += biglietto.getTraveler().getCognome() + " ";
			stringa += biglietto.getTraveler().getEmail();
			stringa +="\n";
		}
		
		return stringa;
	}
	
	public static Integer getNextId() throws DAOException{
		DAOPrenotazione daoPrenotazione = DAOPrenotazione.getInstance();
		return daoPrenotazione.getNextId();
	}

}
