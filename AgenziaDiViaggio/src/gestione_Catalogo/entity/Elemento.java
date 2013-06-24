/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

import gestione_Catalogo.exception.IDEsternoElementoException;

import java.io.Serializable;
import java.util.Set;

public abstract class Elemento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected IDEsternoElemento 	idEsternoElemento;
	private   Indice	  	indice;
	
	
	public Elemento(IDEsternoElemento idEsternoElemento){
		this.idEsternoElemento = idEsternoElemento;
		indice = new Indice();
	}
	
	//Metodi Astratti, forzo l'implementazione	

	//Cambiato da Elemento a ElementoCatalogo
	public abstract Elemento getElemento(String k) throws IDEsternoElementoException;
	
	public abstract Set<String> listaChiaviElementi();
	
	public abstract boolean esistenzaElemento(String k);
	
	public abstract boolean esistenzaElemento(ElementoCatalogo e);
	
	public abstract void aggiungiElemento(String k, ElementoCatalogo e) throws IDEsternoElementoException;
	
	public abstract void rimuoviElemento(String k) throws IDEsternoElementoException;
	
	
	
	//altri metodi
	public String getIDEsternoElemento(){
		return idEsternoElemento.toString();
	}

}
