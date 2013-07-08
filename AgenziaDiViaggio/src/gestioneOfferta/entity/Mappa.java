/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestioneOfferta.entity;

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
	
	public abstract Elemento getElemento(IDEsterno k) throws IDEsternoElementoException;
	
	public abstract void removeElemento(IDEsterno k) throws IDEsternoElementoException;
	
	
	//altri metodi
	public Set<String> listaChiaviElementi(){
		
		return super.keySet();
	}
	
	public boolean esistenzaElemento(String k){
		
		return super.containsKey(k);
	}

}
