/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

import gestione_Catalogo.entity.Elemento;
import gestione_Catalogo.exception.IDEsternoElementoException;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;

public abstract class Mappa extends TreeMap<String, Elemento>implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Mappa(){
		super();
	}
	
	
	//metodi astratti
	
	public abstract Elemento getElemento(String k) throws IDEsternoElementoException;
	
	public abstract void removeElemento(String k) throws IDEsternoElementoException;
	
	
	//altri metodi
	public Set<String> listaChiaviElementi(){
		
		return super.keySet();
	}
	
	public boolean esistenzaElemento(String k){
		
		return super.containsKey(k);
	}

}
