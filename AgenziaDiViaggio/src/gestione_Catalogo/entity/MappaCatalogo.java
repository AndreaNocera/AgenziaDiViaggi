package gestione_Catalogo.entity;

import gestione_Catalogo.exception.IDEsternoElementoException;

import java.util.TreeMap;



/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class MappaCatalogo extends TreeMap<String,ElementoCatalogo> {
	

	private static final long serialVersionUID = 1L;

	public MappaCatalogo(){
		super();
	}
	
	
	public ElementoIntermedio getElemento(String k){
		return (ElementoIntermedio) super.get(k);
	}
	
	public ElementoFinale getElementoFinale(String k){
		return (ElementoFinale) super.get(k);
	}
	
	public void aggiungiElemento(String k, ElementoCatalogo e){
		if(!containsKey(k))
			super.put(k, e);
	}
	
	
	public void rimuoviElemento(String k) throws IDEsternoElementoException{
		if(!containsKey(k)){
			throw new IDEsternoElementoException("Elemento non presente");
		}
		super.remove(k);
		
	}
}
