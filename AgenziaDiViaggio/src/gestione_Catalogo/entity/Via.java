package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Via extends ElementoFinale  {
	
	private static int nextID = 1;
	
	
	public Via(){
		super(nextID, new IDEsternoElemento("(Diretto)"));
		nextID++;
	}
	
	
	public Via(IDEsternoElemento idEsternoElemento){
		super(nextID, idEsternoElemento);
		nextID++;

	}

}
