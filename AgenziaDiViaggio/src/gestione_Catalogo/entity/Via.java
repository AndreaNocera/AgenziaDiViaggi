package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Via extends ElementoFinale  {
	
	public Via(){
		super(new IDEsternoElemento("(Diretto)"));
	}
	
	public Via(Integer ID){
		super(ID, new IDEsternoElemento("(Diretto)"));
	}
	
	public Via(IDEsternoElemento idEsternoElemento){
		super(idEsternoElemento);
	}
	
	public Via(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}

}
