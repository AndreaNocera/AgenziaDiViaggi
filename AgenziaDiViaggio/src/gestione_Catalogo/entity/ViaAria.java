/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.entity;

import java.io.Serializable;

public class ViaAria extends Ambiente implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public ViaAria (IDEsternoElemento idEsterno){
		super(idEsterno);
	}
	
	//ridefinisco il metodo equals
	public boolean equals(Object altroObject){
			
		// verifico se sono lo stesso oggetto
		if (this == altroObject) return true;
					
		// verifico se il parametro implicito � null
		if (altroObject == null) return false;
					
		//verifico se le classi non coincidono
		if (getClass() != altroObject.getClass()) return false;
			
					
		//ok, ora so che altroOggetto � un elemento non nullo, per cui faccio i confronti tra attributi
					
		ViaAria nuovoElemento = (ViaAria) altroObject;
		
		System.out.println(this.getIDEsternoElemento().toString());
		System.out.println(nuovoElemento.getIDEsternoElemento().toString());
		System.out.println("fdfdfd");
					
		return (this.idEsternoElemento.equals(nuovoElemento.idEsternoElemento));  //devo ridefinire equals anche per IDEsterno (equals in profondita')
	}

}
