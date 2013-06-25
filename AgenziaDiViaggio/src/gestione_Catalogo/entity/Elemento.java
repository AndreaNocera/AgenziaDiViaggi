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
	protected IDEsterno 	idEsternoElemento;
	protected IDEsterno		idEsternoViaggio;
	private   Indice	  	indice;
	
	
	//Costruttori
	public Elemento(IDEsterno idEsternoElemento, IDEsterno idEsternoViaggio){
		this.idEsternoElemento = idEsternoElemento;
		this.idEsternoViaggio = idEsternoViaggio;
		indice = new Indice();
	}
	
	public Elemento(IDEsterno idEsternoElemento, IDEsterno idEsternoViaggio, Indice indice){
		this.idEsternoElemento = idEsternoElemento;
		this.idEsternoViaggio = idEsternoViaggio;
		this.indice = indice;
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
	
	public String getIDEsternoViaggio(){
		return idEsternoViaggio.toString();
	}

}
