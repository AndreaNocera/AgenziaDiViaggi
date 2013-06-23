/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;


import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;

import gestione_Catalogo.exception.IDEsternoElementoException;


public class Mappa extends TreeMap<String,Elemento> implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;

	//costruttore
	public Mappa(){
		super();
	}
	
	//metodi
	
	public void addElemento(String idEsterno, Elemento elemento) throws IDEsternoElementoException {
		
		if(!containsKey(idEsterno))
			super.put(idEsterno, elemento);
		else 
			throw new IDEsternoElementoException ("ID esterno " + idEsterno.toString() + "  gia presente/n");
	}
	
	public Elemento getElemento(String k) throws IDEsternoElementoException {
		
		if(k==null)
			throw new IDEsternoElementoException("ID esterno non deve essere nullo/n");
		Elemento e = super.get(k);		
		if(e==null)
			throw new IDEsternoElementoException("ID esterno " + k.toString() + "  non presente/n");
		else
			return e;
		
	}
	
	
	public void removeElemento(String k) throws IDEsternoElementoException {
		
		Elemento e = super.get(k);		
		if(e==null)
			throw new IDEsternoElementoException("ID esterno " + k.toString() + "  non presente/n");
		else 
			super.remove(k);
		
	}
	
	public Set<String> listaChiaviElementi(){
		
		return super.keySet();
	}
	
	public boolean esistenzaElemento(String k){
		
		return super.containsKey(k);
	}
	
	public boolean esistenzaElemento(Elemento e){ //overloading
		
		//Verifico su questa mappa la presenza di un elemento con lo stesso id di quello che voglio confrontare
		if (!super.containsKey(e.getIDEsterno())) return false; //Se non c'e' nessun elemento con quell'id, torno false subito
		
		//Se invece vi è un elemento con quell'id, lo prendo e faccio equals, controllo se entrambi gli elementi sono della stessa classe
		Elemento ele = super.get(e.getIDEsterno());
		
		return ele.equals(e);
	}

}
