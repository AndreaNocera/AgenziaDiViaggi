package gestione_Catalogo.entity;


/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public abstract class ElementoCatalogo {
	
	//attributi di istanza
	private Integer ID;
	private IDEsternoElemento idEsternoElemento;

	
	//costruttori
	public ElementoCatalogo (IDEsternoElemento idEsternoElemento){
		this.idEsternoElemento = idEsternoElemento;		
		//this.setID(0);
	}
	
	public ElementoCatalogo(Integer ID, IDEsternoElemento idEsternoElemento){
		this.ID= ID;
		this.idEsternoElemento = idEsternoElemento;
	}
	
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
	}

	public String getIDEsternoElemento() {
		return idEsternoElemento.toString();
	}
	
	
	public boolean equals(ElementoCatalogo elementoCatalogo){
		if((this.idEsternoElemento.toString()).equals(elementoCatalogo.getIDEsternoElemento()))
				return true;
		return false;
	}
	
	public void print() {
		System.out.println(this.getClass().getSimpleName() + " " + this.getID().toString() + " " +  this.getIDEsternoElemento().toString());
		
	}
/*	public boolean equals(ElementoCatalogo elementoCatalogo){
		if((this.ID).equals(elementoCatalogo.getID()))
				return true;
		return false;
	}
*/
	
}	