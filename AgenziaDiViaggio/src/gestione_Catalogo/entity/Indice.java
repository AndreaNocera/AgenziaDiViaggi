/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.entity;

import java.io.Serializable;

public class Indice implements Serializable{
	
	private int nErogati;

	
	private static final long serialVersionUID = 1L;

	//Costruttore
	public Indice(int nErogati){
		this.nErogati = nErogati;
	}
	
	public Indice(){
		nErogati = 0;
	}
	
	
	public int getErogati(){
		return nErogati;
	}
	
	public void setErogati(int e){
		this.nErogati = e;
	}

}
