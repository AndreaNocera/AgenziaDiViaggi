/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.entity;

import java.io.Serializable;

public class StazioneArrivo extends Elemento implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	//Costruttore
	public StazioneArrivo (IDEsterno idEsterno){
		super(idEsterno);
	}

}
