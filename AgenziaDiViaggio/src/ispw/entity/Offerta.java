/**
 * 
 */
package ispw.entity;

import ispw.dao.DAOOfferta;
import ispw.exception.DAOException;
import ispw.exception.DataException;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella
 * @generated 
 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Offerta {
	/**
	 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Integer idOfferta;
	private Integer idTratta;
	private Data data;
	private Ora oraPartenza;
	private Ora oraArrivo;
	private Integer posti;
	private MapPrenotazioni mapPrenotazioni;
	private Data dataInserimentoOfferta;

	public Offerta() throws DataException {
		setIdOfferta(0);
		setData(null);
		setOraPartenza(null);
		setOraArrivo(null);
		setPosti(0);
		mapPrenotazioni = new MapPrenotazioni();
		dataInserimentoOfferta = new Data(Data.generaGiorno(),
				Data.generaMese());
	}

	public Offerta(Integer idOfferta, Integer idTratta, Data data, Ora oraPartenza, Ora oraArrivo,
			Integer posti) throws DataException {
		this.setIdOfferta(idOfferta);
		this.setIdTratta(idTratta);
		this.setData(data);
		this.setOraPartenza(oraPartenza);
		this.setOraArrivo(oraArrivo);
		this.setPosti(posti);
		this.mapPrenotazioni = new MapPrenotazioni();
		dataInserimentoOfferta = new Data(Data.generaGiorno(),
				Data.generaMese(), Data.generaAnno());
	}

	public Integer getIdOfferta() {
		return idOfferta;
	}

	public void setIdOfferta(Integer idOfferta) {
		this.idOfferta = idOfferta;
	}

	public Integer getIdTratta() {
		return idTratta;
	}

	public void setIdTratta(Integer idTratta) {
		this.idTratta = idTratta;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Ora getOraPartenza() {
		return oraPartenza;
	}

	public void setOraPartenza(Ora ora) {
		this.oraPartenza = ora;
	}

	public Ora getOraArrivo() {
		return oraArrivo;
	}
	
	public void setOraArrivo(Ora ora) {
		this.oraArrivo = ora;
	}

	public Integer getPosti() {
		return posti;
	}

	public void setPosti(Integer posti) {
		this.posti = posti;
	}

	public Data getDataInserimento() {
		return this.dataInserimentoOfferta;
	}

	public void setDataInserimento(Data data) {
		this.dataInserimentoOfferta = data;
	}

	public MapPrenotazioni getMapPrenotazioni() {
		return mapPrenotazioni;
	}

	public void setMapPrenotazioni(MapPrenotazioni mapPrenotazioni) {
		this.mapPrenotazioni = mapPrenotazioni;
	}

	public String getString() {
		return idOfferta + " " + data.getString() + " "
				+ oraPartenza.getString() + " " + oraArrivo.getString() + " "
				+ posti + " " + dataInserimentoOfferta.getString();
	}

	public boolean contains(Integer idTratta, Integer giorno, Integer mese,
			Integer anno, Integer oraPartenza, Integer minutiPartenza,
			Integer oraArrivo, Integer minutiArrivo, Integer posti) {
		// TODO Auto-generated method stub
		if (this.idTratta.equals(idTratta)
				&& this.data.contains(giorno, mese, anno)
				&& this.oraPartenza.contains(oraPartenza, minutiPartenza)
				&& this.oraArrivo.contains(oraArrivo, minutiArrivo)
				&& this.posti.equals(posti))
			return true;
		return false;
	}

	public void print() {
		System.out.print(idOfferta + " ");
		data.print();
		oraPartenza.print();
		oraArrivo.print();
		System.out.print(posti + " ");
		dataInserimentoOfferta.print();
		System.out.println();
	}

	/**
	 * Salvataggio dell'offerta nella tabella Offerta
	 * 
	 * @throws DAOException
	 */
	public void save() throws DAOException {
		DAOOfferta daoOfferta = DAOOfferta.getInstance();
		daoOfferta.insert(this);
	}

	public void delete() throws DAOException {
		// TODO Auto-generated method stub
		DAOOfferta daoOfferta = DAOOfferta.getInstance();
		daoOfferta.delete(this);
	}

	public static Integer getNextId() throws DAOException {
		DAOOfferta daoOfferta = DAOOfferta.getInstance();
		return daoOfferta.getNextId();
	}

	public void updatePosti(Integer posti) {
		// TODO Auto-generated method stub
		DAOOfferta daoOfferta = DAOOfferta.getInstance();
		daoOfferta.updatePosti(this, posti);
	}

}