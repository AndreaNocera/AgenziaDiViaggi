package ordinaViaggi.entity;

import ordinaViaggi.dao.DAOPrenotazione;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.PrenotazioneException;

import java.util.ArrayList;
import java.util.List;

public class Prenotazione {
	/**
	 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
	 * 
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
		DAOPrenotazione daoPrenotazione = DAOPrenotazione.getIstance();
		daoPrenotazione.insert(this);
	}

	public void delete() throws DAOException {
		// TODO Auto-generated method stub
		DAOPrenotazione daoPrenotazione = DAOPrenotazione.getIstance();
		daoPrenotazione.delete(this);
	}
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(idPrenotazione + " " + idOfferta + " " + usernameAcquirente + ".");
		for(Biglietto biglietto : listaBiglietti)
			System.out.println("  " + biglietto.getString());
	}

}
