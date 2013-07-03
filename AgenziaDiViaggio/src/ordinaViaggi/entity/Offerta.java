/**
 * 
 */
package ordinaViaggi.entity;

import ordinaViaggi.dao.DAOOfferta;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;

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
	private Ora ora;
	private Integer posti;
	private MapPrenotazioni mapPrenotazioni;
	private Data dataInserimentoOfferta;

	public Offerta() throws DataException {
		setIdOfferta(0);
		setData(null);
		setOra(null);
		setPosti(0);
		mapPrenotazioni = new MapPrenotazioni();
		dataInserimentoOfferta = new Data(Data.generaGiorno(),
				Data.generaMese());
	}

	public Offerta(Integer idOfferta, Integer idTratta, Data data, Ora ora,
			Integer posti) throws DataException {
		this.setIdOfferta(idOfferta);
		this.setIdTratta(idTratta);
		this.setData(data);
		this.setOra(ora);
		this.setPosti(posti);
		this.mapPrenotazioni = new MapPrenotazioni();
		dataInserimentoOfferta = new Data(Data.generaGiorno(),
				Data.generaMese());
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

	public Ora getOra() {
		return ora;
	}

	public void setOra(Ora ora) {
		this.ora = ora;
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
		return idOfferta + " " + data.getString() + " " + ora.getString() + " "
				+ posti + " " + dataInserimentoOfferta.getString();
	}

	public boolean contains(Integer idTratta, Integer giorno, Integer mese,
			Integer anno, Integer ora, Integer minuti, Integer posti) {
		// TODO Auto-generated method stub
		if (this.idTratta.equals(idTratta)
				&& this.data.contains(giorno, mese, anno)
				&& this.ora.contains(ora, minuti) && this.posti.equals(posti))
			return true;
		return false;
	}

	public void print() {
		System.out.print(idOfferta + " ");
		data.print();
		ora.print();
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
		DAOOfferta daoOfferta = DAOOfferta.getIstance();
		daoOfferta.insert(this);
	}

	public void delete() throws DAOException {
		// TODO Auto-generated method stub
		DAOOfferta daoOfferta = DAOOfferta.getIstance();
		daoOfferta.delete(this);
	}

	public static Integer getNextId() throws DAOException {
		DAOOfferta daoOfferta = DAOOfferta.getIstance();
		return daoOfferta.getNextId();
	}

}