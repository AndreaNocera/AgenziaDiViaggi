package gestione_Catalogo.entity;

import gestione_Catalogo.exception.IDEsternoElementoException;

import java.util.Set;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public abstract class ElementoCatalogo {
	
	//attributi di istanza
	private Integer ID;
	private IDEsternoElemento idEsternoElemento;
	protected MappaCatalogo mappaCatalogo;
	
	//costruttori
	public ElementoCatalogo (IDEsternoElemento idEsternoElemento){
		this.idEsternoElemento = idEsternoElemento;
		mappaCatalogo = new MappaCatalogo();
	}
	
	public ElementoCatalogo(Integer ID, IDEsternoElemento idEsternoElemento){
		this.ID = ID;
		this.idEsternoElemento = idEsternoElemento;
		mappaCatalogo = new MappaCatalogo();
	}
	
	
	//metodi
	public ElementoCatalogo getElemento(String k){   
		return mappaCatalogo.get(k);
	}
	
	public Set<String> listaChiaviElementi() {
		return mappaCatalogo.keySet();
	}
	
	public boolean esistenzaElemento(String k){    //se in parametro gli passo una stringa
		return mappaCatalogo.containsKey(k);
	}
	
	public boolean esistenzaElemento(ElementoCatalogo e){  //overloading, se in parametro gli passo un elemento
		return mappaCatalogo.containsKey(e.getIDEsternoElemento().toString());
	}
	
	public void aggiungiElemento(String k, ElementoCatalogo e){
		mappaCatalogo.put(k, e);
	}
	
	public void rimuoviElemento(String k){
		mappaCatalogo.remove(k);
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
