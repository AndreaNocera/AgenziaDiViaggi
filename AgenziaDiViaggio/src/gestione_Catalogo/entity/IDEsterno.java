/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.entity;

import java.io.Serializable;

public class IDEsterno implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String IDEsterno;
	
	public IDEsterno(String IDEsterno){
		this.IDEsterno = IDEsterno;
	}
	
	public String toString(){
		return IDEsterno;
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
					
			IDEsterno nuovoElemento = (IDEsterno) altroObject;
					
					return (this.IDEsterno.equals(nuovoElemento.IDEsterno));  //devo ridefinire equals anche per IDEsterno (equals in profondita')
		}

}
