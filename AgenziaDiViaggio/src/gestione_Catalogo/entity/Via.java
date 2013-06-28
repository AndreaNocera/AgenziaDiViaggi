package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Via extends ElementoCatalogo  {
	
	private static int nextID = 1;
	
	private MappaOfferta mappaOfferta;
	
	
	public Via(){
		super(nextID, new IDEsternoElemento("(Diretto)"));
		nextID++;
		
		mappaCatalogo = null;
		mappaOfferta = new MappaOfferta();
	}
	
	
	public Via(IDEsternoElemento idEsternoElemento){
		super(nextID, idEsternoElemento);
		nextID++;
		
		mappaCatalogo = null;
		mappaOfferta = new MappaOfferta();	
	}

}
