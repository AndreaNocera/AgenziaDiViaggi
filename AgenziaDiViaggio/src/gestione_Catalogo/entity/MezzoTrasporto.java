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
	public MezzoTrasporto (IDEsternoElemento idEsterno){
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
						
		MezzoTrasporto nuovoElemento = (MezzoTrasporto) altroObject;
						
		return (this.idEsternoElemento.equals(nuovoElemento.idEsternoElemento));  //devo ridefinire equals anche per IDEsterno (equals in profondita')
	}

}
