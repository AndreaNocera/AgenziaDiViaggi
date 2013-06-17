package gestione_Catalogo.entity;

import java.io.Serializable;
import java.util.Set;

import gestione_Catalogo.exception.IDEsternoException;


public abstract class Elemento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	//Variabili istanza
	private IDEsterno 		idEsterno;
	private Indice	  		indice;
	private Info			info;
	private Orologio		data;
	
	private Mappa			mappa;
	
	
	//costruttore
	public Elemento(IDEsterno idEsterno, Info info){
		
		this.idEsterno = idEsterno;
		indice = new Indice();
		this.info = info;
		data = new Orologio();
		
		//aggiorno le info mettendoci la data di inserimento
		info.updateInfo("--- Inserito il " + data.stampaDataAttuale());
		mappa = new Mappa();
	}
	
	public Elemento getElemento(String k) throws IDEsternoException {
		if (esistenzaElemento(k)) return mappa.getElemento(k);
		else throw new IDEsternoException("Elemento "+k+" non presente");
	}
	
	public Set<String> listaChiaviElementi() {
		return mappa.listaChiaviElementi();
	}
	
	public boolean esistenzaElemento(String k){
		return mappa.containsKey(k);
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

}
