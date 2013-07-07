package gestione_Catalogo.entity;

import gestione_Catalogo.exception.IDEsternoElementoException;

import java.util.Set;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public abstract class ElementoIntermedio extends ElementoCatalogo {
	
	//attributi di istanza
	private MappaCatalogo mappaCatalogo;
	
	
	//costruttori
	public ElementoIntermedio(IDEsternoElemento idEsternoElemento) {
		super(idEsternoElemento);
		mappaCatalogo = new MappaCatalogo();
	}
	
	public ElementoIntermedio(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
		mappaCatalogo = new MappaCatalogo();
	}

	
	//metodi
	
	public void aggiungiElemento(String k, ElementoCatalogo e){
		mappaCatalogo.aggiungiElemento(k, e);
	}
	
	public void rimuoviElemento(String k) throws IDEsternoElementoException{
		mappaCatalogo.rimuoviElemento(k);
	}
	
	public ElementoCatalogo getElemento(String k) throws IDEsternoElementoException{
		return mappaCatalogo.getElemento(k);
	}
	
	
	public Set<String> listaChiaviElementi() {
		return mappaCatalogo.keySet();
	}
	
	
	public boolean esistenzaElemento(String k){    //se in parametro gli passo una stringa
		return mappaCatalogo.esistenzaElemento(k);
	}
	
	public boolean esistenzaElemento(ElementoCatalogo e){  //overloading, se in parametro gli passo un elemento
		return mappaCatalogo.esistenzaElemento(e);
	}
	
	public boolean isEmpty(){
		return mappaCatalogo.isEmpty();
	}
	


}
