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
public class Tratta {
	private Integer id;
	private Ambiente ambiente;
	private Mezzo mezzo;
	private Citta cittaPartenza;
	private Citta cittaArrivo;
	private Via via;
	public Tratta(){
		ambiente = new Ambiente();
		mezzo = new Mezzo();
		cittaPartenza = new Citta();
		cittaArrivo = new Citta();
		via = new Via();
	}
	public Tratta(Integer id, Ambiente ambiente, Mezzo mezzo, Citta cittaPartenza, Citta cittaArrivo, Via via){
		this.id = id;
		this.ambiente = ambiente;
		this.mezzo = mezzo;
		this.cittaPartenza = cittaPartenza;
		this.cittaArrivo = cittaArrivo;
		this.via = via;
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
	public Via getVia() {
		return via;
	}
	public void setVia(Via via) {
		this.via = via;
	}
	
	public void printTratta(){
		System.out.println(id + " " + 
			ambiente.getValore() + " " + 
			mezzo.getValore() + " " +		
			cittaPartenza.getValore() + " " +
			cittaArrivo.getValore() + " " +
			via.getValore() + ".");
	}
}