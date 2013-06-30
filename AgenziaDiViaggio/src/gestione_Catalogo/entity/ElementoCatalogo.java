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
	}
	
	public ElementoCatalogo(Integer ID, IDEsternoElemento idEsternoElemento){
		this.setID(ID);
		this.idEsternoElemento = idEsternoElemento;
	}
	
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getIDEsternoElemento() {
		return idEsternoElemento.toString();
	}
	
	
	public boolean equals(ElementoCatalogo elementoCatalogo){
		if((this.ID).equals(elementoCatalogo.getID()))
				return true;
		return false;
	}


}
