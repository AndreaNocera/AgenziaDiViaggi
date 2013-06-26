/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

import java.io.Serializable;
import java.util.Set;

import gestione_Catalogo.entity.Elemento;
import gestione_Catalogo.entity.IDEsterno;
import gestione_Catalogo.exception.IDEsternoElementoException;


public abstract class ElementoCatalogo extends Elemento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	//Variabili istanza
	
	protected IDEsterno		idEsternoViaggio;
	private   MappaCatalogo			mappa;
	
	
	//costruttore
	public ElementoCatalogo(IDEsterno idEsternoElemento, IDEsterno idEsternoViaggio){
		super(idEsternoElemento);
		this.idEsternoViaggio = idEsternoViaggio;
		mappa = new MappaCatalogo();
		}
	
	public ElementoCatalogo(IDEsterno idEsternoElemento, IDEsterno idEsternoViaggio, Indice indice){
		super(idEsternoElemento, indice);
		this.idEsternoViaggio = idEsternoViaggio;
		mappa = new MappaCatalogo();
	}
	

	public ElementoCatalogo getElemento(String k) throws IDEsternoElementoException {   
		if (esistenzaElemento(k)) return mappa.getElemento(k);
		else throw new IDEsternoElementoException("Elemento "+k+" non presente");
	}
	
	public Set<String> listaChiaviElementi() {
		return mappa.listaChiaviElementi();
	}
	
	public boolean esistenzaElemento(String k){    //se in parametro gli passo una stringa
		return mappa.esistenzaElemento(k);
	}
	
	public boolean esistenzaElemento(ElementoCatalogo e){  //overloading, se in parametro gli passo un elemento
		return mappa.esistenzaElemento(e.getIDEsternoElemento());
	}
	
	public void aggiungiElemento(String k, ElementoCatalogo e) throws IDEsternoElementoException {
		mappa.addElemento(k, e);
	}
	
	public void rimuoviElemento(String k) throws IDEsternoElementoException {
		mappa.removeElemento(k);
	}
	
	
	public String getIDEsternoViaggio(){
		return idEsternoViaggio.toString();
	}

	
}
