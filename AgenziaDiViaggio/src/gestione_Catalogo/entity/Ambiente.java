/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

import java.io.Serializable;

public abstract class Ambiente extends Elemento implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	//costruttori
	public Ambiente (IDEsternoElemento idEsterno){
		super(idEsterno);
	}
	
	
	

}
