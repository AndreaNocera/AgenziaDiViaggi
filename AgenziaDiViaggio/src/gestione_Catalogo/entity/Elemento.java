/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

import java.io.Serializable;
import java.util.Set;

import gestione_Catalogo.exception.IDEsternoException;


public abstract class Elemento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	//Variabili istanza
	protected IDEsterno 	idEsterno;
	private   Indice	  	indice;
	
	private   Mappa			mappa;
	
	
	//costruttore
	public Elemento(IDEsterno idEsterno){
		this.idEsterno = idEsterno;
		indice = new Indice();
		mappa = new Mappa();
		
	}
	

	public Elemento getElemento(String k) throws IDEsternoException {   
		if (esistenzaElemento(k)) return mappa.getElemento(k);
		else throw new IDEsternoException("Elemento "+k+" non presente");
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
	
	public void aggiungiElemento(String k, Elemento e) throws IDEsternoException {
		mappa.addElemento(k, e);
	}
	
	public void rimuoviElemento(String k) throws IDEsternoException {
		mappa.removeElemento(k);
	}
	
	
	public String getIDEsterno(){
		return idEsterno.toString();
	}
	
}
