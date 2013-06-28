/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

import gestione_Catalogo.entity.IDEsternoElemento;

import java.io.Serializable;

public abstract class Ambiente extends ElementoCatalogo implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	//costruttori
	public Ambiente(IDEsternoElemento idEsternoElemento){
		super(idEsternoElemento);
		}
	
	public Ambiente(IDEsternoElemento idEsternoElemento, Indice indice){
		super(idEsternoElemento, indice);
	}
	
	
	

}
