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
		this.ID = ID;
		this.idEsternoElemento = idEsternoElemento;
	}
	
	
	public Integer getID() {
		return ID;
	}
	public void setId(Integer ID) {
		this.ID = ID;
	}
	public IDEsternoElemento getIDEsternoElemento() {
		return idEsternoElemento;
	}
	public void setIdEsternoElemento(IDEsternoElemento idEsternoElemento) {
		this.idEsternoElemento = idEsternoElemento;
	}

}
