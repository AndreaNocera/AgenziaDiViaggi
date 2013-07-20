package voyager.nove.model.viaggio;

import java.util.Set;

import voyager.nove.exception.IDEsternoElementoException;
import voyager.nove.model.viaggio.basic.IDEsternoElemento;
import voyager.nove.model.viaggio.map.MappaCatalogo;

public abstract class ElementoIntermedio extends ElementoCatalogo {
	
	private MappaCatalogo mappaCatalogo;
	
	public ElementoIntermedio(IDEsternoElemento idEsternoElemento) {
		super(idEsternoElemento);
		mappaCatalogo = new MappaCatalogo();
	}
	
	public ElementoIntermedio(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
		mappaCatalogo = new MappaCatalogo();
	}
	
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
	
	public boolean esistenzaElemento(String k){  
		return mappaCatalogo.esistenzaElemento(k);
	}
	
	public boolean esistenzaElemento(ElementoCatalogo e){ 
		return mappaCatalogo.esistenzaElemento(e);
	}
	
	public boolean isEmpty(){
		return mappaCatalogo.isEmpty();
	}

}
