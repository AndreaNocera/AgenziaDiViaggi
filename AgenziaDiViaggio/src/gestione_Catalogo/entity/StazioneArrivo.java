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
	public StazioneArrivo (IDEsternoElemento idEsterno){
		super(idEsterno);
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
						
		StazioneArrivo nuovoElemento = (StazioneArrivo) altroObject;
						
		return (this.idEsterno.equals(nuovoElemento.idEsterno));  //devo ridefinire equals anche per IDEsterno (equals in profondita')
	}

}
