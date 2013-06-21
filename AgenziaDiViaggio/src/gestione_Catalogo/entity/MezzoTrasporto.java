/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.entity;

import java.io.Serializable;

public class MezzoTrasporto extends Elemento implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	//Costruttori
	public MezzoTrasporto (IDEsterno idEsterno){
		super(idEsterno);
	}

}
