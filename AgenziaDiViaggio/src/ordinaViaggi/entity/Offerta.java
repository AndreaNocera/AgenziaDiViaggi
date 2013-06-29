/**
 * 
 */
package ordinaViaggi.entity;

import ordinaViaggi.dao.DAOOfferta;
import ordinaViaggi.exception.DAOException;


/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Offerta {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Integer idOfferta;
	private Integer idTratta;
	private Data data;
	private Ora ora;
	private Integer posti;
	
	public Offerta(){
		setIdOfferta(0);
		setData(null);
		setOra(null);
		setPosti(0);
	}
	public Offerta(Integer idOfferta, Integer idTratta, Data data, Ora ora, Integer posti){
		this.setIdOfferta(idOfferta);
		this.setIdTratta(idTratta);
		this.setData(data);
		this.setOra(ora);
		this.setPosti(posti);
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
	public void print(){
		System.out.print(idOfferta  + " " );
		data.print();
		ora.print();
		System.out.println(posti);
	}
	
	/**
	 * Salvataggio dell'offerta nella tabella Offerta
	 * @throws DAOException
	 */
	public void save() throws DAOException {
		DAOOfferta daoOfferta = DAOOfferta.getIstance();
		daoOfferta.insert(this);
	}
	
	
}