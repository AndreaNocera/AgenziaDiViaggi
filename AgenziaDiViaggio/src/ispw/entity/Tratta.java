/**
 * 
 */
package ispw.entity;

import ispw.dao.DAOTratta;
import ispw.exception.DAOException;
import ispw.exception.DataException;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella Riccardo
 * @generated 
 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Tratta {
	private Integer id;
	private Ambiente ambiente;
	private Mezzo mezzo;
	private Citta cittaPartenza;
	private Citta cittaArrivo;
	private Via via;
	private Data dataInserimentoTratta;

	public Tratta() throws DataException {
		ambiente = new Ambiente();
		mezzo = new Mezzo();
		cittaPartenza = new Citta();
		cittaArrivo = new Citta();
		via = new Via();
		dataInserimentoTratta = new Data(Data.generaGiorno(),Data.generaMese());
	}

	public Tratta(Integer id, Ambiente ambiente, Mezzo mezzo,
			Citta cittaPartenza, Citta cittaArrivo, Via via) throws DataException {
		this.id = id;
		this.ambiente = ambiente;
		this.mezzo = mezzo;
		this.cittaPartenza = cittaPartenza;
		this.cittaArrivo = cittaArrivo;
		this.via = via;
		dataInserimentoTratta = new Data(Data.generaGiorno(),Data.generaMese());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public Citta getCittaPartenza() {
		return cittaPartenza;
	}

	public void setCittaPartenza(Citta cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
	}

	public Citta getCittaArrivo() {
		return cittaArrivo;
	}

	public void setCittaArrivo(Citta cittaArrivo) {
		this.cittaArrivo = cittaArrivo;
	}
	public Data getDataInserimento(){
		return this.dataInserimentoTratta;
	}
	public void setDataInserimento(Data data){
		this.dataInserimentoTratta = data;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public synchronized void save() throws DAOException {
		DAOTratta daoTratta = DAOTratta.getInstance();
		daoTratta.insert(this);
	}

	public synchronized void delete() throws DAOException {
		// TODO Auto-generated method stub
		DAOTratta daoTratta = DAOTratta.getInstance();
		daoTratta.delete(this);
	}

	public String getString() {
		return (id + " " + ambiente.getValore() + " " + mezzo.getValore() + " "
				+ cittaPartenza.getValore() + " " + cittaArrivo.getValore()
				+ " " + via.getValore() + " " + dataInserimentoTratta.getString() + ".");

	}

	public void printTratta() {
		System.out.println(id + " " + ambiente.getValore() + " "
				+ mezzo.getValore() + " " + cittaPartenza.getValore() + " "
				+ cittaArrivo.getValore() + " " + via.getValore() + " " + dataInserimentoTratta.getString() + ".");
	}

	public synchronized boolean contains(Ambiente ambiente, Mezzo mezzo,
			Citta cittaPartenza, Citta cittaArrivo, Via via) {
		// TODO Auto-generated method stub
		if (this.ambiente.equals(ambiente) && this.mezzo.equals(mezzo)
				&& this.cittaPartenza.equals(cittaPartenza)
				&& this.cittaArrivo.equals(cittaArrivo) && this.via.equals(via))
			return true;
		return false;
	}

}