package ispw.entity;

public class ElementoCatalogo {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @author Gambella Riccardo
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	
	private Integer id;
	private String valore;
	public ElementoCatalogo(){
		id = 0;
		valore = "";
	}
	public ElementoCatalogo(Integer id, String valore){
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
	
	public boolean equals(ElementoCatalogo elementoCatalogo){
		if((this.id).equals(elementoCatalogo.getId()))
				return true;
		return false;
	}
}
