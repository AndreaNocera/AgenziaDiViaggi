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
	private IDEsterno 		idEsterno;
	private Indice	  		indice;
	private Mappa			mappa;
	private Info            info;
	
	
	//costruttore
	public Elemento(IDEsterno idEsterno){
		this.idEsterno = idEsterno;
		indice = new Indice();
		mappa = new Mappa();
		info = new Info();
	}
	
	public Elemento(IDEsterno idEsterno, Info info){
		this.idEsterno = idEsterno;
		indice = new Indice();
		mappa = new Mappa();
		this.info = info;
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
	
	public String getInfo(){
		return info.getInfo();
	}
	
	public String getIDEsterno(){
		return idEsterno.toString();
	}
	
	
	//ridefinisco il metodo equals
	public boolean equals(Object altroObject){
		
		// verifico se sono lo stesso oggetto
		if (this == altroObject) return true;
				
		// verifico se il parametro implicito è null
		if (altroObject == null) return false;
				
		//verifico se le classi non coincidono
		if (getClass() != altroObject.getClass()) return false;
				
		//ok, ora so che altroOggetto è un elemento non nullo, per cui faccio i confronti tra attributi
				
		Elemento nuovoElemento = (Elemento) altroObject;
				
				return (this.idEsterno.equals(nuovoElemento.idEsterno));  //devo ridefinire equals anche per IDEsterno (equals in profondita')
	}
	

}
