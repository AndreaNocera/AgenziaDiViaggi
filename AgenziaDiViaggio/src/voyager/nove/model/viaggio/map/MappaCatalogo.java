package voyager.nove.model.viaggio.map;

import java.util.TreeMap;

import voyager.nove.exception.IDEsternoElementoException;
import voyager.nove.model.viaggio.ElementoCatalogo;

public class MappaCatalogo extends TreeMap<String,ElementoCatalogo> {	

	private static final long serialVersionUID = 1L;

	public MappaCatalogo(){
		super();
	}	
	
	public void aggiungiElemento(String k, ElementoCatalogo e){
		if(!containsKey(k)) {
			super.put(k, e);
		}			
	}	
	
	public void rimuoviElemento(String k) throws IDEsternoElementoException{
		if(!containsKey(k)){
			throw new IDEsternoElementoException("Errore in rimozione. Elemento \""+k+"\" non presente.");
		}
		
		super.remove(k);		
	}	
	
	public ElementoCatalogo getElemento(String k) throws IDEsternoElementoException{
		if (!containsKey(k)){
			throw new IDEsternoElementoException("Elemento \""+k+"\" non presente.");
		}
		
		return super.get(k);
	}	
	
	public boolean esistenzaElemento(String k){
		return super.containsKey(k);
	}
	
	public boolean esistenzaElemento(ElementoCatalogo e){
		return super.containsKey(e.getIDEsternoElemento());
	}
	
}
