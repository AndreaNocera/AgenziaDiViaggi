package ordinaViaggi.entity;

public class ElementoOfferta extends Elemento{
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	
	private Integer id;
	private String valore;
	public ElementoOfferta(){
		id = 0;
		valore = "";
	}
	public ElementoOfferta(Integer id, String valore){
		this.id = id;
		this.valore = valore;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValore() {
		return valore;
	}
	public void setValore(String valore) {
		this.valore = valore;
	}
}
