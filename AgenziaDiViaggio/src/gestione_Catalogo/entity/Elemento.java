/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

import java.io.Serializable;
import java.util.Set;

import gestione_Catalogo.exception.IDEsternoElementoException;


public abstract class Elemento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	//Variabili istanza
	protected IDEsternoElemento 	idEsterno;
	private   Indice	  	indice;
	
	private   Mappa			mappa;
	
	
	//costruttore
	public Elemento(IDEsternoElemento idEsterno){
		this.idEsterno = idEsterno;
		indice = new Indice();
		mappa = new Mappa();
		
	}
	

	public Elemento getElemento(String k) throws IDEsternoElementoException {   
		if (esistenzaElemento(k)) return mappa.getElemento(k);
		else throw new IDEsternoElementoException("Elemento "+k+" non presente");
	}
	
	public Set<String> listaChiaviElementi() {
		return mappa.listaChiaviElementi();
	}
	
	public boolean esistenzaElemento(String k){    //se in parametro gli passo una stringa
		return mappa.esistenzaElemento(k);
	}
	
	public boolean esistenzaElemento(Elemento e){  //overloading, se in parametro gli passo un elemento
		return mappa.esistenzaElemento(e.getIDEsterno());
	}
	
	public void aggiungiElemento(String k, Elemento e) throws IDEsternoElementoException {
		mappa.addElemento(k, e);
	}
	
	public void rimuoviElemento(String k) throws IDEsternoElementoException {
		mappa.removeElemento(k);
	}
	
	
	public String getIDEsterno(){
		return idEsterno.toString();
	}
	
}
