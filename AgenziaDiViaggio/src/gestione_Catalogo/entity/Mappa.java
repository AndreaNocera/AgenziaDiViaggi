package gestione_Catalogo.entity;


import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;

import gestione_Catalogo.exception.IDEsternoException;


public class Mappa extends TreeMap<String,Elemento> implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//costruttore
	public Mappa(){
		super();
	}
	
	//metodi
	
	public void addElemento(String idEsterno, Elemento elemento) throws IDEsternoException {
		
		if(!containsKey(idEsterno))
			super.put(idEsterno, elemento);
		else 
			throw new IDEsternoException ("ID esterno " + idEsterno.toString() + "  gia presente/n");
	}
	
	public Elemento getElemento(String k) throws IDEsternoException {
		
		if(k==null)
			throw new IDEsternoException("ID esterno non deve essere nullo/n");
		Elemento e = super.get(k);		
		if(e==null)
			throw new IDEsternoException("ID esterno " + k.toString() + "  non presente/n");
		else
			return e;
		
	}
	
	public void removeElemento(String k) throws IDEsternoException {
		
		Elemento e = super.get(k);		
		if(e==null)
			throw new IDEsternoException("ID esterno " + k.toString() + "  non presente/n");
		else 
			super.remove(k);
		
	}
	
	public Set<String> listaChiaviElementi(){
		
		return super.keySet();
	}
	
	public boolean esistenzaElemento(String k){
		
		return super.containsKey(k);
	}

}
