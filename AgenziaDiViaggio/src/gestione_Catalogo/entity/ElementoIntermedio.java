package gestione_Catalogo.entity;

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
	public ElementoIntermedio getElemento(String k){   
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
	
	public void aggiungiElemento(String k, ElementoIntermedio e){
		mappaCatalogo.put(k, e);
	}
	
	public void rimuoviElemento(String k){
		mappaCatalogo.remove(k);
	}
		
	

}
