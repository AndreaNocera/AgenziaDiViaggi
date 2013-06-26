/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

import gestione_Catalogo.entity.IDEsterno;

import java.io.Serializable;

public abstract class Ambiente extends ElementoCatalogo implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	//costruttori
	public Ambiente(IDEsterno idEsternoElemento, IDEsterno idEsternoViaggio){
		super(idEsternoElemento, idEsternoViaggio);
		}
	
	public Ambiente(IDEsterno idEsternoElemento, IDEsterno idEsternoViaggio, Indice indice){
		super(idEsternoElemento, idEsternoViaggio, indice);
	}
	
	
	

}
