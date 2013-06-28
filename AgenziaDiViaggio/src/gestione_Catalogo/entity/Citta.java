package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Citta extends ElementoIntermedio {
	
	private static int nextID = 1;
	
	public Citta(IDEsternoElemento idEsternoElemento){
		super(nextID, idEsternoElemento);
		nextID++;
	}

}
